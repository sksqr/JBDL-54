package org.gfg.multithreading;

public class RunnableDemo {

    public static void main(String[] args) {

        System.out.println("Starting :"+Thread.currentThread().getName());
        MyTask task = new MyTask("API Call");
        Thread t1 = new Thread(task);
        //t1.setName("thread-A");
        Thread t2 = new Thread(() -> System.out.println("Executing in "+Thread.currentThread().getName()));
        t1.start();
//        t2.start();



        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        t1.start();
        System.out.println("Done :"+Thread.currentThread().getName());
    }
}
