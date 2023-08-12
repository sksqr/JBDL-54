package com.example.L14springsecuritydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {

    @GetMapping("/list")
    public ResponseEntity<String> getList(){
        return ResponseEntity.ok("Listing API from "+Thread.currentThread().getName());
    }

    @GetMapping("/blogs")
    public ResponseEntity<String> getBlogs(){
        return ResponseEntity.ok("Blogs API from "+Thread.currentThread().getName());
    }
}
