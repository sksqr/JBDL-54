package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.example"})
public class UserApp {
    public static void main(String[] args) {
        SpringApplication.run(UserApp.class);
        System.out.println("Hello world!");
    }
}