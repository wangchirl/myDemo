package com.shadow.dataStruct.thought;

import org.springframework.transaction.annotation.Transactional;

/**
 * 动态规划思想
 * 1. 自顶向下备忘录
 * 2. 自底向上
 * 斐波拉契数列 优化
 */
public class DynamicPlaning {


    public static void main(String[] args) {
        System.out.println(fibonaci(7));
        System.out.println(fib(7));
        System.out.println(fibPlus(7));


        int[] p = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};
        int n = 7;
        System.out.println(cut(p, n));
        int i = 5;
        int j = 10;
        System.out.println(~j);

        System.out.println(minAdd("a"));
        System.out.println(minAdd("ab"));
        System.out.println(minAdd("abca"));
        System.out.println(minAdd("abcd"));


        new Thread(() -> {

        },"t").start();



    }

    @Transactional
    public void a(Object o){

    }

    public void a(String a){

    }

    public static int fibonaci(int n) {
        // check
        if (n <= 0) {
            return -1;
        }
        // 创建备忘录
        int[] memory = new int[n + 1];
        // 初始化备忘录
        for (int i = 0; i < memory.length; i++) {
            memory[i] = -1;
        }
        return fib(n, memory);
    }


    /**
     * 计算斐波拉契数列 - 备忘录法
     *
     * @param n      要计算的那一列
     * @param memory 记录已经计算过的值
     * @return 返回的计算后某一项结果
     */
    public static int fib(int n, int[] memory) {
        // 用 -1 表示备忘录中没有记录f(n)的值
        if (memory[n] != -1) {
            return memory[n];
        }
        // 前两项
        if (n <= 2) {
            memory[n] = 1;
        } else {
            memory[n] = fib(n - 1, memory) + fib(n - 2, memory);
        }
        return memory[n];
    }


    /**
     * 斐波拉契数列 - 自底向上
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        // check
        if (n <= 0) {
            return -1;
        }
        // 创建备忘录
        int[] memory = new int[n + 1];
        // 前两项
        memory[1] = 1;
        memory[2] = 1;
        for (int i = 3; i <= n; i++) {
            memory[i] = memory[i - 1] + memory[i - 2];
        }
        return memory[n];
    }


    /**
     * 斐波拉契数列 - 升级版
     *
     * @param n
     * @return
     */
    public static int fibPlus(int n) {
        // check
        if (n <= 1) {
            return -1;
        }
        int memo_i_2 = 0;
        int memo_i_1 = 1;
        int memo_i = 1;
        for (int i = 2; i <= n; i++) {
            memo_i = memo_i_2 + memo_i_1;
            memo_i_2 = memo_i_1;
            memo_i_1 = memo_i;
        }
        return memo_i;
    }


    /**
     * 切钢管问题
     *
     * @param p 钢条长度与价格对应表
     * @param n 钢条长度
     * @return
     */
    public static int cut(int[] p, int n) {
        if (n == 0) {
            return 0;
        }
        int q = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            q = Math.max(q, p[i - 1] + cut(p, n - i));
        }
        return q;
    }


    /**
     *  最少插入多少字符使字符串变成回文串
     * @param string
     * @return
     */
    public static int minAdd(String string) {
        char[] str = string.toCharArray();
        int len = str.length;
        int[][] vec = new int[len][len];//用于存储字符串从i位置到j位置构成汇文串需要最少添加的字符数量
        for (int k = 1; k < len; k++) {//i位置与j位置的间隔（自字符串长度-1），一般情况下，拆分成最小子问题时，k的初始值应为0，但因java创建数组是已经对每一个节点赋予了初始值0，故k为0时可以省略
            for (int i = 0; i + k < len; i++) {
                int j = i + k;
                if (str[i] == str[j]) {
                    vec[i][j] = vec[i + 1][j - 1];
                } else {
                    vec[i][j] = Math.min(vec[i + 1][j], vec[i][j - 1]) + 1;
                }
            }
        }
        return vec[0][len - 1];
    }

}
