package com.example.L14springsecuritydemo.service;

import com.example.L14springsecuritydemo.Product;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private Map<Long, Product> dataStore = new HashMap<>();

    private Long nextId=0l;

    @PostConstruct
    public void init(){
        Product product = new Product();
        product.setId(++nextId);
        product.setName("Laptop");
        product.setCost(40000.00);
        dataStore.put(product.getId(),product);
    }

    public Product createProduct(Product product){
        product.setId(++nextId);
        dataStore.put(product.getId(),product);
        return product;
    }

    public Product getProduct(Long id){
        return dataStore.get(id);
    }

    public Product deleteProduct(Long id){
        return dataStore.remove(id);
    }

}
