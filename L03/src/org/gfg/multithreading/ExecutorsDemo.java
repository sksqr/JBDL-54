package org.gfg.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {

    public static void main(String[] args) {
        ExecutorService fixExecutorService = Executors.newFixedThreadPool(100);
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
        long start = System.currentTimeMillis();
        for(int i=0; i<20; i++){
            fixExecutorService.submit(() -> {
                System.out.println("Task Running in : " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        fixExecutorService.shutdown();
        try {
            fixExecutorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time taken:"+(end-start)+" ms");
    }
}
