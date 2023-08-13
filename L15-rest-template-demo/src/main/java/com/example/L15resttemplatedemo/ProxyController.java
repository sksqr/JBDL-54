package com.example.L15resttemplatedemo;

import com.example.L15resttemplatedemo.dto.Blog;
import com.example.L15resttemplatedemo.dto.Product;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/proxy")
public class ProxyController {

    private static Logger LOGGER = LoggerFactory.getLogger(ProxyController.class);

    @Value("${blog.service.baseUrl}")
    private String blogBaseUrl;

    @Value("${product.service.baseUrl}")
    private String productBaseUrl;
    @Autowired
    private RestTemplate restTemplate;

//    @GetMapping("/blog/{id}")
//    ResponseEntity<JsonNode> getBlogById(@PathVariable Long id){
//        String url = blogBaseUrl+id;
//        JsonNode response = restTemplate.getForObject(url, JsonNode.class);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping("/blog/{id}")
    ResponseEntity<Blog> getBlogById(@PathVariable Long id){
        LOGGER.info("Processing Blog API");
        String url = blogBaseUrl+id;
        Blog response = restTemplate.getForObject(url, Blog.class);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/product")
    ResponseEntity<JsonNode> createProduct(@RequestBody Product product){
        ResponseEntity<JsonNode> response = restTemplate.postForEntity(productBaseUrl,product,JsonNode.class);
        return response;
    }


}
