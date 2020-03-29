package com.shadow.datastructure;

public class Sum100 {
    public static void main(String[] args) {
        // 1.循环解法
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
        sum = (1 + 100)*100/2;
        System.out.println(sum);

        System.out.println(sum(10));
    }


    public static int sum(int num){
        if(num == 1){
            return num;
        } else {
            return num + sum(num-1);
        }
    }

}
