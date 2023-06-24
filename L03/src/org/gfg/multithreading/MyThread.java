package org.gfg.multithreading;

public class MyThread extends Thread{
    @Override
    public void run(){
        System.out.println("Hello from thread :"+Thread.currentThread().getName());
    }
}
