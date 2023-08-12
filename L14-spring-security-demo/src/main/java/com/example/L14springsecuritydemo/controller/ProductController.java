package com.example.L14springsecuritydemo.controller;

import com.example.L14springsecuritydemo.Product;
import com.example.L14springsecuritydemo.service.AppUserDetailsService;
import com.example.L14springsecuritydemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class ProductController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @Autowired
    private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.ok(productService.createProduct(product));
    }

//    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/changePassword")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String newPassword){
        appUserDetailsService.changePassword(userDetails,newPassword);
        return ResponseEntity.ok("Password Changed");
    }
}
