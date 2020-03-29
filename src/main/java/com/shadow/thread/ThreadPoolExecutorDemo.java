package com.shadow.thread;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(8);
        ExecutorService pool1 = Executors.newCachedThreadPool();
        ScheduledExecutorService pool2 = Executors.newScheduledThreadPool(10);
        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        ExecutorService pool4 = Executors.newWorkStealingPool();
        ExecutorService pool5 = Executors.newWorkStealingPool(10);
        Future<Integer> future = pool.submit(() -> {
            return 1024;
        });
        try {
            int value = future.get().intValue();
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
    }
}
