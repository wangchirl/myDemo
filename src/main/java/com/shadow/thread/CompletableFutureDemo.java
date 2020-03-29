package com.shadow.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo
{

    public static void main(String[] args) throws Exception {

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(() ->{
            System.out.println(Thread.currentThread().getName()+"没有返回值的CompletableFuture.runAsync...");
        });
        voidCompletableFuture.get();

        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() ->{
            System.out.println("有返回值的CompletableFuture.supplyAsync...");
            //int i = 10/0;
            return 1024;
        });
        integerCompletableFuture.whenComplete((t, u) -> {
            System.out.println("****t:" + t);
            System.out.println("****u:" + u);
        }).exceptionally(t -> { // 处理异常情况
            System.out.println("异常监听exceptionally..."+t.getMessage());
            return 2048;
        }).get();

    }

}
