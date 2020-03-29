package com.shadow.thread;


public class AtomicDemo {
    public static void main(String[] args) {
        A a = new A();
        for (int i = 1; i <=10 ; i++) {
            new Thread(() -> {
                for (int j = 0; j < 2000; j++) {
                    a.add();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(a.num);
    }
}

class A{
    public volatile int num = 0;

    public void add(){
        num++;
    }
}