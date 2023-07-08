package com.example.L07springbootanotations;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private Integer nextId=1;
    private List<Product> dataStore = new ArrayList<>();

    public ProductService() {
        dataStore.add(new Product(nextId,"laptop"));
        nextId++;
    }

    public List<Product> getAllProducts(){
        return dataStore;
    }

    public Product createProduct(Product product){
        product.setId(nextId);
        nextId++;
        dataStore.add(product);
        return product;
    }
}
