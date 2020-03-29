package com.shadow.thread;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket { // 资源类

    private int number = 30;

    // 使用juc - lock
    private Lock lock = new ReentrantLock();
    public void saleTicket() {
        lock.lock();
        try{
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第：" + (number--) + "还剩下：" + number);
            }
        }finally {
            lock.unlock();
        }
    }

    // 使用 synchronized
    /** public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出第：" + (number--) + "还剩下：" + number);
        }
    }*/
}

/**
 * 题目： 三个售票员  同时出售 30张票
 * <p>
 * 多线程企业级开发 套路 + 模板
 * 1.高内聚低耦合的前提下，线程  操作  资源类
 */
public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket = new Ticket();

        // lambda表达式
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();}, "A").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();}, "B").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();}, "C").start();

        for (int i = 1; i <=10 ; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 40; j++) {
                    ticket.saleTicket();
                }
            },String.valueOf(i)).start();
        }


        //  Thread(Runnable target, String name)
        /** new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=40 ; i++) {
                    ticket.saleTicket();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40 ; i++) {
                    ticket.saleTicket();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 40 ; i++) {
                    ticket.saleTicket();
                }
            }
        },"C").start(); */
    }
}

