package com.example.L18kafkademo.config;

import com.example.L18kafkademo.EmailRequest;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Bean
    public ProducerFactory getProducerFactory(){
        Map<String,Object> cofigs = new HashMap<>();
        cofigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        cofigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        cofigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory(cofigs);
    }

    @Bean
    public KafkaTemplate<String,String> getKafkaTemplate(){
        return new KafkaTemplate<>(getProducerFactory());
    }

    @Bean
    public ProducerFactory getJsonProducerFactory(){
        Map<String,Object> cofigs = new HashMap<>();
        cofigs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
        cofigs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        cofigs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory(cofigs);
    }

    @Bean
    public KafkaTemplate<String, EmailRequest> getKafkaTemplateForEmailRequest(){
        return new KafkaTemplate<>(getJsonProducerFactory());
    }
}
