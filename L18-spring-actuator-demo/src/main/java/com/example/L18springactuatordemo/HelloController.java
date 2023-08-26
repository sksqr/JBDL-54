package com.example.L18springactuatordemo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app")
public class HelloController {
    @Autowired
    private HelloService helloService;
    @GetMapping("/hello")
    public ResponseEntity<String> getHello(){
        String msg = helloService.getHello();
        return ResponseEntity.ok(msg);
    }

}
