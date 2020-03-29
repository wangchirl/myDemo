package com.shadow.locks;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class RedisLockTest {
    public static void main(String[] args) throws InterruptedException {
        Redisson redisson = redisson();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{
                dosomething(redisson,countDownLatch);
            },"T"+i).start();
        }
        countDownLatch.await();
        redisson.shutdown();
    }

    public static void dosomething(Redisson redisson,CountDownLatch countDownLatch){

        String lockkey = "redislock";
        RLock lock = redisson.getLock(lockkey);
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() +" 加锁成功");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }  finally {
            System.out.println(Thread.currentThread().getName() + " 释放锁");
            countDownLatch.countDown();
            lock.unlock();
        }
    }

    public static Redisson redisson(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setDatabase(0);
        return (Redisson) Redisson.create(config);
    }

}
