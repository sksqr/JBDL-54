package com.example.L07springbootmvcdemo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {


    private static Logger LOGGER = LoggerFactory.getLogger(ProductService.class);


    public List<Product> getAllProduct(){
        LOGGER.info("Processing getAllProduct");
        List<Product> list = new ArrayList<>();
        list.add(new Product(1,"laptop",40000.00));
        list.add(new Product(2,"Mobile",4000.00));
        return list;
    }
}
