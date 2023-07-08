package com.example.L07springbootmvcdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {


    private static Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @GetMapping("/productList")
    public ResponseEntity<List<Product>> getAllProduct(){
        LOGGER.info("Processing getAllProduct");
        List<Product> response = productService.getAllProduct();
        return  ResponseEntity.ok(response);
    }
}
