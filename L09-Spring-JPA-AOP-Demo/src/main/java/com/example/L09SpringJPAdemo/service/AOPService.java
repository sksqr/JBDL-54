package com.example.L09SpringJPAdemo.service;


import com.example.L09SpringJPAdemo.aop.LogExecutionTime;
import org.springframework.stereotype.Service;

@Service
public class AOPService {

    @LogExecutionTime
    public String getData(){
        return "Hello from AOP :"+Thread.currentThread().getName();
    }
}
