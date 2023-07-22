package com.example.L09SpringJPAdemo.singletondemo;

public class SingletonDemo {
    public static void main(String[] args) {

//        SingletonClass obj1 = new SingletonClass();
//        SingletonClass obj2 = new SingletonClass();

        SingletonClass obj1 = SingletonClass.getInstance();

        SingletonClass obj2 = SingletonClass.getInstance();

    }
}
