package com.shadow.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 1; i <=10 ; i++) {
            final int index = i;
            new Thread(() -> {
                try {
                    semaphore.acquire(1);
                    try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
                    System.out.println(String.valueOf(index) + " 完成任务！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release(1);
                }
            },String.valueOf(i)).start();
        }
    }

}
