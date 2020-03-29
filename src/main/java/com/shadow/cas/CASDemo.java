package com.shadow.cas;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
public class CASDemo {
    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);
    static AtomicStampedReference atomicStampedReference = new AtomicStampedReference(100,1);
    public static void main(String[] args) {
        System.out.println("===============ABA问题的产生====================");
        new Thread(() -> {
            System.out.println(atomicReference.compareAndSet(100, 101));
            System.out.println(atomicReference.compareAndSet(101, 100));
        },"T1").start();
        new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicReference.compareAndSet(100,1) + "\t" + atomicReference.get());
        },"T2").start();
        try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}

        System.out.println("===============ABA问题的解决====================");
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("第一次版本号：" + stamp);
            try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            atomicStampedReference.compareAndSet(100,101,stamp,stamp+1);
            System.out.println("第二次版本号：" + atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println("第三次版本号：" + atomicStampedReference.getStamp());
            },"T3").start();
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("第一次版本号：" + stamp);
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(atomicStampedReference.compareAndSet(100, 10, stamp, stamp + 1) + "\t" + atomicStampedReference.getStamp() +"\t" + atomicStampedReference.getReference());
        },"T3").start();
    }
}
