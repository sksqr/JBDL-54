package com.example.L13redisdemo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Value("${api.wait.time:400}")
    private Long apiWaitTime;

    @Autowired
    private RedisTemplate<String,Product> redisTemplate;



    public Product createProduct(Product product){
        Long id = redisTemplate.opsForValue().increment("next:product:id");
        product.setId(id);
        /*
          add product to database;
         */
        redisTemplate.opsForValue().set("product:"+id,product);
        return product;
    }

    public Product getProduct(Long id){
        String key = "product:"+id;
        Product product = redisTemplate.opsForValue().get(key);
        /*
        if product == null
            read from database
            store in redis
         */
        return product;
    }

    public Product deleteProduct(Long id){
        String key = "product:"+id;
        /*
         delete from DB;
         */
        Product product = redisTemplate.opsForValue().get(key);
        if(product != null) {
            redisTemplate.delete(key);
        }
        return product;
    }

}
