package org.gfg.multithreading;

public class ThreadDemo {

    public static void main(String[] args) {

        System.out.println("Hello World By:"+Thread.currentThread().getName());

        MyThread thread = new MyThread();
        thread.setName("Thread-01");
        thread.run();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Done :"+Thread.currentThread().getName());
    }
}
