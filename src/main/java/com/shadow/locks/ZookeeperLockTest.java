package com.shadow.locks;

import java.util.concurrent.TimeUnit;

public class ZookeeperLockTest {
    public static void main(String[] args) throws InterruptedException {
        ZookeeperLock zookeeperLock = new ZookeeperLock();
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                Lock lock = zookeeperLock.lock("dd", 10000000);
                try {
                    TimeUnit.SECONDS.sleep(2);
                    zookeeperLock.unlock(lock);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        Thread.sleep(Long.MAX_VALUE);
    }
}
