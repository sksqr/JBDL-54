package com.example.L18springactuatordemo;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String getHello(){
        return "Hello from -"+Thread.currentThread().getName();
    }
}
