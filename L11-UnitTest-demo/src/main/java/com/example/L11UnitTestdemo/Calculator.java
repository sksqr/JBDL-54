package com.example.L11UnitTestdemo;

public class Calculator {
    public Integer multiply(Integer a, Integer b){
        Integer result=0;
        for(int i=1; i<=b; i++){
            result = result +a;
        }
        return result;
    }


    public Integer add(Integer a, Integer b){
        Integer result= a + b;
        return result;
    }
}
