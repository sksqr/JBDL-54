package com.example.L09SpringJPAdemo.singletondemo;

public class SingletonClass {
//    private static SingletonClass instance = new SingletonClass();

    private static SingletonClass instance;

    private SingletonClass() {

    }

    public static SingletonClass getInstance(){
        synchronized (SingletonClass.class){
        if(instance == null){
            instance = new SingletonClass();
            }
        }
        return instance;
    }
}
