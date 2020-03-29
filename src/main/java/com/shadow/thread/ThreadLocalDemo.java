package com.shadow.thread;

public class ThreadLocalDemo {
    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
        threadLocal.set("demo");
        T1 t1 = new T1(threadLocal);
        Thread t = new Thread(t1);
        t.start();
        System.out.println(threadLocal.get());
    }
}

class T1 implements Runnable{
    private ThreadLocal threadLocal;

    public T1(ThreadLocal threadLocal){
        this.threadLocal = threadLocal;
    }

    @Override
    public void run() {
        System.out.println(threadLocal.get());
    }
}
