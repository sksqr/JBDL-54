package com.example.L18clouddemoapp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class AppController {

    @GetMapping("/hello")
    public ResponseEntity<String> hello(@RequestParam String name){
        String response = "Hello! "+name+" - from "+Thread.currentThread().getName();
        return ResponseEntity.ok(response);
    }
}
