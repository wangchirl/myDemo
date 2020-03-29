package com.shadow.aqs;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 1; i <=5 ; i++) {
            final int index = i;
            new Thread(() -> {
                System.out.println(String.valueOf(index) + "执行完成！");
                latch.countDown();
            },String.valueOf(i)).start();
        }
        latch.await();
        System.out.println("任务完成！");
    }
}
