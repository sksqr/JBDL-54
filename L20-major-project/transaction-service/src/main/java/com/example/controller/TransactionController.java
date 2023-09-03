package com.example.controller;


import com.example.dto.TransactionRequest;
import com.example.dto.TxnStatusDto;
import com.example.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/transaction-service")
public class TransactionController {

    private static Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    private TransactionService transactionService;


    @PostMapping("/doTransaction")
    ResponseEntity<String> doTransaction(@RequestBody TransactionRequest transactionRequest) throws ExecutionException, InterruptedException {
        LOGGER.info("Initiating txn {}", transactionRequest);
        String txnId = transactionService.doTransaction(transactionRequest);
        return ResponseEntity.ok(txnId);
    }

    @GetMapping("/status/{txnId}")
    ResponseEntity<TxnStatusDto> getStatus(@PathVariable String txnId){
        LOGGER.info("Fetching txn status for txnId {}", txnId);
        return ResponseEntity.ok(transactionService.getStatus(txnId));
    }





    @GetMapping("/hello")
    ResponseEntity<String> getBalance(){
        LOGGER.info("Processing hello request");
        return ResponseEntity.ok("Hello from transaction-service");
    }




}
