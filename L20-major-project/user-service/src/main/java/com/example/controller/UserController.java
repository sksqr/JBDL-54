package com.example.controller;

import com.example.RequestFilter;
import com.example.dto.UserDto;
import com.example.entity.User;
import com.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user-service")
public class UserController {

    private static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<Long> createUser(@RequestBody UserDto userDto) throws ExecutionException, InterruptedException {
        LOGGER.info("Creating User : {}",userDto);
        Long userId = userService.createUser(userDto);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/hello")
    ResponseEntity<String> getBalance(){
        LOGGER.info("Processing hello request");
        return ResponseEntity.ok("Hello from transaction-service");
    }
}
