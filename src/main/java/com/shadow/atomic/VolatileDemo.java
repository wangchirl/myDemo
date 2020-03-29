package com.shadow.atomic;


import java.util.concurrent.TimeUnit;

/**
 * 1.验证volatile的可见性
 */
public class VolatileDemo {
    public static void main(String[] args) throws InterruptedException {
        Source source = new Source();
        for (int i = 1; i <=10 ; i++) {
            new Thread(() -> {
                for (int j = 0; j <1000 ; j++) {
                    source.addPlus();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println("main over--------------------" + source.number);
    }

}
// 资源类
class Source{
    volatile int number = 0;
    public void addPlus() {
        this.number++;
    }
}
