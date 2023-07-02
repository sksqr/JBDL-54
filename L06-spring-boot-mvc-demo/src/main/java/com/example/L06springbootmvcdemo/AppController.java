package com.example.L06springbootmvcdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app")
public class AppController {

    private static Logger LOGGER = LoggerFactory.getLogger(AppController.class);


    @GetMapping("/product")
    public ResponseEntity<Product> getProduct(@RequestParam String name, @RequestParam Double cost){
        return ResponseEntity.ok(new Product(name,500000.00));
    }



    @GetMapping("/product-path-param/{id}")
    public ResponseEntity<Product> getProductWithPathParam(@PathVariable Integer id){
        return ResponseEntity.ok(new Product(id,"Laptop",500000.00));
    }


    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        System.out.println("Creating Product :"+product);
        LOGGER.info("Request Data: {}",product);
        return ResponseEntity.ok().build();
    }










}
