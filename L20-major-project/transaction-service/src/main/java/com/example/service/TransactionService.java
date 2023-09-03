package com.example.service;

import com.example.payload.TransactionInitPayload;
import com.example.TransactionStatusEnum;
import com.example.dto.TransactionRequest;
import com.example.dto.TxnStatusDto;
import com.example.entity.TransactionEntity;
import com.example.repo.TransactionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionService {


    private static Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

    private static String TXN_INIT_TOPIC="TXN-INIT";

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public String doTransaction(TransactionRequest transactionRequest) throws ExecutionException, InterruptedException {
        TransactionEntity transaction = TransactionEntity.builder()
                .fromUserId(transactionRequest.getFromUserId())
                .toUserId(transactionRequest.getToUserId())
                .amount(transactionRequest.getAmount())
                .remark(transactionRequest.getRemark())
                .status(TransactionStatusEnum.PENDING)
                .txnId(UUID.randomUUID().toString())
                .build();
        transactionRepo.save(transaction);

        TransactionInitPayload transactionInitPayload = TransactionInitPayload.builder()
                .id(transaction.getId())
                .fromUserId(transaction.getFromUserId())
                .toUserId(transaction.getToUserId())
                .amount(transaction.getAmount())
                .remark(transaction.getRemark())
                .requestId(MDC.get("requestId"))
                .build();

        CompletableFuture<SendResult<String,Object>> future = kafkaTemplate.send(TXN_INIT_TOPIC,String.valueOf(transaction.getFromUserId()),transactionInitPayload);
        LOGGER.info("Pushed userCreatedPayload to kafka: {}",future.get());
        return transaction.getTxnId();
    }

    public TxnStatusDto getStatus(String txnId){
        TransactionEntity transaction = transactionRepo.findByTxnId(txnId);
        TxnStatusDto txnStatusDto = TxnStatusDto.builder()
                .reason(transaction.getReason())
                .status(transaction.getStatus().name())
                .build();
        return txnStatusDto;
    }
}
