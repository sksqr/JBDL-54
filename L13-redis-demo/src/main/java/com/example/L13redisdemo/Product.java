package com.example.L13redisdemo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Product implements Serializable {

    private Long id;

    private String name;

    private Double cost;
}
