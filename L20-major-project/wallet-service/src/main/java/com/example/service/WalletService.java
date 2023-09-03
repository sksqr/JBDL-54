package com.example.service;

import com.example.config.KafkaConsumerConfig;
import com.example.payload.TransactionInitPayload;
import com.example.entity.Wallet;
import com.example.exception.InsufficientBalanceException;
import com.example.payload.WalletUpdatedPayload;
import com.example.repo.WalletRepo;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class WalletService {

    private static String WALLET_UPDATED_TOPIC = "WALLET-UPDATED";


    private static Logger LOGGER = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;


    @Transactional
    public void doWalletTxn(TransactionInitPayload transactionInitPayload) throws InsufficientBalanceException, ExecutionException, InterruptedException {
        Wallet fromWallet = walletRepo.findByUserId(transactionInitPayload.getFromUserId());
        if(fromWallet.getBalance() >= transactionInitPayload.getAmount()){
            Wallet toWallet = walletRepo.findByUserId(transactionInitPayload.getToUserId());
            fromWallet.setBalance(fromWallet.getBalance() - transactionInitPayload.getAmount());
            toWallet.setBalance(toWallet.getBalance() + transactionInitPayload.getAmount());
            walletRepo.save(fromWallet);
            walletRepo.save(toWallet);
            WalletUpdatedPayload payload1 = WalletUpdatedPayload.builder()
                    .userName(fromWallet.getName())
                    .userEmail(fromWallet.getEmail())
                    .balance(fromWallet.getBalance())
                    .requestId(MDC.get("requestId"))
                    .build();
            CompletableFuture<SendResult<String,Object>> future = kafkaTemplate.send(WALLET_UPDATED_TOPIC,String.valueOf(transactionInitPayload.getFromUserId()),payload1);
            LOGGER.info("Pushed WalletUpdatedPayload1 to kafka: {}",future.get());

            WalletUpdatedPayload payload2 = WalletUpdatedPayload.builder()
                    .userName(toWallet.getName())
                    .userEmail(toWallet.getEmail())
                    .balance(toWallet.getBalance())
                    .requestId(MDC.get("requestId"))
                    .build();
            CompletableFuture<SendResult<String,Object>> future2 = kafkaTemplate.send(WALLET_UPDATED_TOPIC,String.valueOf(transactionInitPayload.getToUserId()),payload2);
            LOGGER.info("Pushed WalletUpdatedPayload2 to kafka: {}",future2.get());

        }
        else {
            throw new InsufficientBalanceException("Low Balance");
        }
    }
}
