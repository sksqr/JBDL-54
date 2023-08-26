package com.example.L18kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/kafka")
public class KafkaDemoController {
    private static Logger LOGGER = LoggerFactory.getLogger(KafkaDemoController.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/push")
    public ResponseEntity<String> pushMsg(@RequestParam String msg) throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String,String>> future = kafkaTemplate.send("topic02",msg,msg);
        LOGGER.info("Pushed data to kafka, kafka response: {}",future.get());
        return ResponseEntity.ok("Pushed");
    }
}
