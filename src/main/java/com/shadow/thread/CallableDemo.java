package com.shadow.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class Call implements Callable<Boolean> {
    @Override
    public Boolean call() throws Exception {
        return true;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException {
        FutureTask futureTask = new FutureTask(new Call());
        new Thread(futureTask).start();
        System.out.println(Runtime.getRuntime().totalMemory()/1024/1024);
        System.out.println(Runtime.getRuntime().maxMemory()/1024/1024);
//        Thread.sleep(Integer.MAX_VALUE);
    }
}
