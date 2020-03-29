package com.shadow.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        ForkJoinPool;
//        ForkJoinTask;
//        RecursiveTask

        MyTask myTask = new MyTask(0,100);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        System.out.println(forkJoinTask.get());
        forkJoinPool.shutdown();;


    }
}

// 递归任务
class MyTask extends RecursiveTask<Integer> {
    private int DEFAULT_VALUE = 10;
    private int start;
    private int end;
    private int result;
    @Override
    protected Integer compute() {
        if ((end - start) <= DEFAULT_VALUE) {
            for (int i = start; i <=end ; i++) {
                result = result + i;
            }
        } else {
            int middle = (end+start)/2;
            MyTask myTask1 = new MyTask(start,middle);
            MyTask myTask2 = new MyTask(middle+1,end);
            myTask1.fork();
            myTask2.fork();
            result = myTask1.join() + myTask2.join();
        }
        return result;
    }
    public MyTask() {
    }
    public MyTask(Integer start, Integer end) {
        this.start = start;
        this.end = end;
    }
}