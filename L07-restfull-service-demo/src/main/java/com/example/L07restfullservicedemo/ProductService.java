package com.example.L07restfullservicedemo;

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

    public Product updateProduct(Integer id,Product product){
        Product oldProduct = null;
        for(Product prod: dataStore){
            if(prod.getId().equals(id)){
                oldProduct = prod;
                break;
            }
        }
        if(oldProduct == null){
            return null;
        }
        oldProduct.setName(product.getName());
        return oldProduct;
    }

    public Product deleteProduct(Integer id){
        Product oldProduct = null;
        for(Product prod: dataStore){
            if(prod.getId().equals(id)){
                oldProduct = prod;
                break;
            }
        }
        if(oldProduct == null){
            return null;
        }
        dataStore.remove(oldProduct);
        return oldProduct;
    }

    public Product getById(Integer id){
        Product product = null;
        for(Product prod: dataStore){
            if(prod.getId().equals(id)){
                product = prod;
                break;
            }
        }
        return product;
    }
}
