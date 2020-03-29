package com.shadow.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,() -> {
            System.out.println("任务完成！");
        });
        for (int i = 1; i <=5 ; i++) {
            final int index = i;
            new Thread(() -> {
                System.out.println(String.valueOf(index) + "执行完毕！");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
