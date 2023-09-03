package com.example.service;

import com.example.payload.UserCreatedPayload;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.repo.UserRepo;
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
public class UserService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private static String USER_CREATED_TOPIC = "USER-CREATED";

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    @Autowired
    private UserRepo userRepo;

    public Long createUser(UserDto userDto) throws ExecutionException, InterruptedException {

//        User u1 = new User();
//        u1.setName();
//        u1.setEmail();

        User user = User.builder()
                .name(userDto.getName())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .address(userDto.getAddress())
                .kycId(userDto.getKycId())
                .build();
        userRepo.save(user);
        UserCreatedPayload userCreatedPayload = new UserCreatedPayload(user.getId(),user.getName(),user.getEmail(), MDC.get("requestId"));
        CompletableFuture<SendResult<String,Object>> future = kafkaTemplate.send(USER_CREATED_TOPIC,String.valueOf(user.getId()),userCreatedPayload);
        LOGGER.info("Pushed userCreatedPayload to kafka: {}",future.get());

        return user.getId();
    }
}
