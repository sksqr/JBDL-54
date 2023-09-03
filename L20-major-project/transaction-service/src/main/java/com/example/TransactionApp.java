package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionApp {
    public static void main(String[] args) {
        SpringApplication.run(TransactionApp.class);
        System.out.println("Hello world!");
    }
}