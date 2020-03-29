package com.shadow.datastructure;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 十进制转二进制
 */
public class TestConvertTen2two {
    public static void main(String[] args) {
        // 1.给定十进制数
        int n = 13;
//        String res = convertByCycle(n);
        String res = convertByStack(n);


        // 3.输出结果
        System.out.println(n + "-----------> " + res);
    }

    private static String convertByCycle(int n) {
        // 2.转换二进制
        int t = n; // 被除数
        String res = "";
        do {
            // 求2的模数
            int mod = t % 2;
            // 输出模数
            res = mod + res;
            // 除以2得商,用商做被除数
            t  = t / 2;
        }while (t > 0); // 商大于0
        return res;
    }


    private static String  convertByStack(int n){
        int t = n;
        Deque stack = new LinkedList();
        do {
            // 求2的模数
            int mod = t % 2;
            // 模数入栈
            stack.push(mod);
            // 求2的商，以商再运算
            t = t / 2;
        }while (t > 0); // 商大于0
        // 元素从栈中取出
        String str = "";
        while (!stack.isEmpty()) {
            str = str + stack.pop();
        }
        return str;
    }
}
