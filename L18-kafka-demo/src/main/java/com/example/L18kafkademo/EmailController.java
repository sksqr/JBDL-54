package com.example.L18kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class EmailController {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private KafkaTemplate<String,EmailRequest> kafkaTemplate;

    @PostMapping("/sendEmail")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest emailRequest){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setTo(emailRequest.getToEmail());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setCc(emailRequest.getCc());
        javaMailSender.send(simpleMailMessage);
        return ResponseEntity.ok("Email Sent");
    }

    @PostMapping("/sendEmailAsync")
    public ResponseEntity<String> sendEmailAsync(@RequestBody EmailRequest emailRequest) throws ExecutionException, InterruptedException {
        CompletableFuture<SendResult<String,EmailRequest>> future = kafkaTemplate.send("topic03",emailRequest.getToEmail(),emailRequest);
        LOGGER.info("Pushed data to kafka, kafka response: {}",future.get());
        return new ResponseEntity<>("Email will be triggered",HttpStatus.ACCEPTED);
    }
}
