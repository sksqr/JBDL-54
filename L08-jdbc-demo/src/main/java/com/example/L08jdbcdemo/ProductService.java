package com.example.L08jdbcdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    private Integer nextId=1;
    private List<Product> dataStore = new ArrayList<>();

    public ProductService() {
        dataStore.add(new Product(nextId,"laptop",100000.00));
        nextId++;
    }

    public List<Product> getAllProducts(){
        return productDAO.getAllProducts();
    }

    public Product createProduct(Product product){
        return productDAO.createProductWithPS(product);
        //return productDAO.createProduct(product);
//        product.setId(nextId);
//        nextId++;
//        dataStore.add(product);
//        return product;
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
        return productDAO.getByIdWithPS(id);
//        return productDAO.getById(id);
    }
}
