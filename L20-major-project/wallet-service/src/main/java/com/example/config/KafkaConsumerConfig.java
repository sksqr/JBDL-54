package com.example.config;

import com.example.payload.TransactionInitPayload;
import com.example.payload.TxnCompPayload;
import com.example.payload.UserCreatedPayload;
import com.example.entity.Wallet;
import com.example.exception.InsufficientBalanceException;
import com.example.payload.WalletUpdatedPayload;
import com.example.repo.WalletRepo;
import com.example.service.WalletService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Configuration
public class KafkaConsumerConfig {

    private static String TXN_COMPLETED_TOPIC = "TXN-COMPLETED";

    private static String WALLET_UPDATED_TOPIC = "WALLET-UPDATED";

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private WalletService walletService;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    private static ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "USER-CREATED", groupId = "walletapp")
    public void consumeFromUSerCreatedTopic(ConsumerRecord payload) throws JsonProcessingException {
        UserCreatedPayload userCreatedPayload = objectMapper.readValue(payload.value().toString(),UserCreatedPayload.class);
        MDC.put("requestId",userCreatedPayload.getRequestId());
        LOGGER.info("Getting payload from kafka : {}",payload);
        Wallet wallet = Wallet.builder()
                .userId(userCreatedPayload.getUserId())
                .name(userCreatedPayload.getUserName())
                .email(userCreatedPayload.getUserEmail())
                .balance(100.00)
                .build();
        walletRepo.save(wallet);
        MDC.clear();
    }


    @KafkaListener(topics = "TXN-INIT", groupId = "walletapp")
    public void consumeFromTxnInitTopic(ConsumerRecord payload) throws JsonProcessingException, ExecutionException, InterruptedException {
        TransactionInitPayload transactionInitPayload = objectMapper.readValue(payload.value().toString(),TransactionInitPayload.class);
        MDC.put("requestId",transactionInitPayload.getRequestId());
        LOGGER.info("Getting payload from kafka : {}",payload);
        TxnCompPayload txnCompPayload = new TxnCompPayload();
        txnCompPayload.setRequestId(transactionInitPayload.getRequestId());
        txnCompPayload.setId(transactionInitPayload.getId());
        try {
            walletService.doWalletTxn(transactionInitPayload);
            txnCompPayload.setSuccess(Boolean.TRUE);
        } catch (InsufficientBalanceException e) {
            txnCompPayload.setSuccess(Boolean.FALSE);
            txnCompPayload.setReason("Insufficient Balance");
        }
        catch (Exception e){
            txnCompPayload.setSuccess(Boolean.FALSE);
            txnCompPayload.setReason("Server Error");
        }
        CompletableFuture<SendResult<String,Object>> future = kafkaTemplate.send(TXN_COMPLETED_TOPIC,String.valueOf(transactionInitPayload.getFromUserId()),txnCompPayload);
        LOGGER.info("Pushed txnCompPayload to kafka: {}",future.get());
        MDC.clear();
    }

}
