package com.shadow.aqs;

import sun.misc.Unsafe;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 抽象队列同步器AQS
 * synchronized -> c++
 * AQS -> java
 * <p>
 * 1、任意时刻，只能有一个线程加锁成功
 * cas比较与交换 - 原子操作
 * Atomic原子类
 * <p>
 * Unsafe魔法类
 * 绕过虚拟机，直接操作底层的内存
 * <p>
 * 对象内存结构
 * Mark Word
 * 数据区
 * 对齐填充区
 *
 * 自旋 + LockSupport + CAS
 */
public class MyFairLock {

    /**
     * 当前加锁状态，记录加锁的次数
     */
    private volatile int state = 0;
    /**
     * 当前持有锁的线程
     */
    private Thread lockHolder;

    /**
     * 加锁失败的线程存储在队列中
     */
    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue<>();


    /**
     * 加锁
     */
    public void lock() {
        if (aquire()) { // 加锁成功
            return;
        }
        // 没有加锁的，必须让其阻塞
        Thread current = Thread.currentThread();
        waiters.add(current);
        // 未获取到锁的线程全部在这里自旋
        for(;;){
            /* 这里为什么不用这些？
            Thread.yield();
            Thread.sleep(222);
            */
            // 唤醒后的线程尝试获取锁，判断当前线程是否为队列中的第一个线程，
            // 也就是我们unpark唤醒的线程，成功获取锁则退出，并且需要将队列
            // 头部的线程从队列中踢出
            if(current == waiters.peek() && aquire()){
                waiters.poll(); // 踢出队首的线程
                return;
            }
            // 阻塞当前线程，保存对线程的引用
            LockSupport.park(current);
        }
    }

    /**
     * 解锁
     */
    public void unlock() {
        // 如果当前线程不等于持有锁的线程，抛出异常
        if(Thread.currentThread() != lockHolder){
            throw new RuntimeException("lockholder is not current thred");
        }
        // 获取锁状态
        int state = getState();
        // cas原子操作，还原锁状态
        if(compareAndSwapState(state,0)){
            setLockHolder(null); // 锁持有者置为空
            Thread first = waiters.peek(); // 获取队列中的第一个线程
            // 唤醒线程
            if(first != null)
                LockSupport.unpark(first);
        }
    }

    private boolean aquire() {
        // 当前线程
        Thread current = Thread.currentThread();
        // 初始值
        int c = getState();
        // 当前同步器还没有被持有
        if (c == 0) {
            // 判断队列为空或者当前线程是队首的线程，并且cas获取锁成功
            if ((waiters.size() == 0 || current == waiters.peek()) && compareAndSwapState(0, 1)) {
                setLockHolder(current);
                return true;
            }
        }
        return false;
    }




    /**
     * 原子操作
     * 如果与期望值相等，则进行更新
     *
     * @param except 期望值
     * @param update 更新值
     * @return
     */
    public final boolean compareAndSwapState(int except, int update) {
        return unsafe.compareAndSwapInt(this, stateOffset, except, update);
    }

    private static final Unsafe unsafe = UnsafeInstace.reflectGetUnsafe();

    private static final long stateOffset;

    static {
        try {
            stateOffset = unsafe.objectFieldOffset(MyFairLock.class.getDeclaredField("state"));
        } catch (Exception e) {
            throw new Error();
        }
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getLockHolder() {
        return lockHolder;
    }

    public void setLockHolder(Thread lockHolder) {
        this.lockHolder = lockHolder;
    }
}
