package com.shadow;

import com.sun.org.apache.regexp.internal.RE;

import java.io.IOError;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class A {
    public A() {
        System.out.println("A构造块");
    }

    {
        System.out.println("A普通块");
    }

    static {
        System.out.println("A静态块");
    }
}

class B extends A {
    public B() {
        System.out.println("B构造块");
    }

    {
        System.out.println("B普通块");
    }

    static {
        System.out.println("B静态块");
    }
}

public class Demo implements ww {

    public Demo() {
        System.out.println("构造函数");
    }

    {
        System.out.println("构造代码块");
    }

    static {
        System.out.println("静态代码块");
    }


    static {
        int i = 5;
    }

    static int i, j;

    public static void main(String[] args) throws InterruptedException {
        B b = new B();
//        Demo d = new Demo();

        System.out.println(test());
        i--;
        t();
        System.out.println(i + j + ++i);

        System.out.println(tryf(2));


        System.out.println(judgeStr("abbaa"));

        Thread.sleep(Integer.MAX_VALUE);

    }

    public static void t() {
        j = i++ + ++i;
    }




    public static int test() {
        int a = 10;
        try {
            a++;
            return a;
        } finally {
            a = 88;
        }
    }

    @Override
    public Long hello() throws IOError {
        return null;
    }

    public static class Inner {

    }


    public static boolean judgeStr(String str) {
        if (str == null || "".equals(str)) {
            return false;// 姑且认为空字符串不是回文
        }
        // 利用数组的方式实现回文判断arr[0].equals(arr[str.length()-1])
        // 将字符串转为数组存储
        List<Character> arr = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            arr.add(str.charAt(i));
        }
        // 循环判断是否相等
        int count = 0;// 记录循环次数
        int preIndex = 0;// 数组前下标
        int postIndex = arr.size() - 1;//数组后下标
        while (preIndex <= postIndex && arr.get(preIndex) == arr.get(postIndex)) {
            preIndex++;
            postIndex--;
            count++;
        }
        // 判断循环次数是否与数组大小的一半相等
        int halfSize = 0;
        if (arr.size() % 2 == 1) {
            halfSize = (arr.size() / 2) + 1;
        }
        if (arr.size() % 2 == 0) {
            halfSize = arr.size() / 2;
        }
        if (count == halfSize) {
            return true;
        }
        return false;
    }

    public static int tryf(int n) {
        int i = 0;
        try {
            i = 5;
            return i;
        } catch (Exception e) {
            i = 10;
            return i;
        } finally {
            if (n == 2) {
                return 2;
            }
        }
    }


}

interface ww {
    Object hello() throws Exception;
}


