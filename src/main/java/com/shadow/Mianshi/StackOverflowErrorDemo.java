package com.shadow.Mianshi;

/**
 * java.lang.StackOverflowError
 */
public class StackOverflowErrorDemo {

    public static void test(){
        test();
    }

    public static void main(String[] args) {
        test();
    }
}
