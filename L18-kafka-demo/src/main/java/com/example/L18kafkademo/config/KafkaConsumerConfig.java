package com.example.L18kafkademo.config;

import com.example.L18kafkademo.EmailRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class KafkaConsumerConfig {
    private static Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerConfig.class);

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private JavaMailSender javaMailSender;

    @KafkaListener(topics = "topic02", groupId = "app1")
    public void consumeFromKafka(Object payload){
        LOGGER.info("Getting payload from kafka: {}", payload);
    }

    @KafkaListener(topics = "topic03", groupId = "emailSender")
    public void emailConsumer(Object payload) throws JsonProcessingException {
        String value = (String) ((ConsumerRecord)payload).value();
        EmailRequest emailRequest = objectMapper.readValue(value,EmailRequest.class);
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("jbdl.ewallet@gmail.com");
        simpleMailMessage.setSubject(emailRequest.getSubject());
        simpleMailMessage.setTo(emailRequest.getToEmail());
        simpleMailMessage.setText(emailRequest.getBody());
        simpleMailMessage.setCc(emailRequest.getCc());
        javaMailSender.send(simpleMailMessage);
        LOGGER.info("Getting payload from kafka: {}", payload);
    }
}
