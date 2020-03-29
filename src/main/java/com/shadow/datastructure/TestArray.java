package com.shadow.datastructure;

/**
 * 顺序表
 */
public class TestArray {
    public static void main(String[] args) {

        java.util.ArrayList list = new java.util.ArrayList();
//        ArrayList list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        System.out.println(list.get(-1));

        list.add(3,666);
        list.add(3,1212);
//        list.addBefore(666,777);
//        list.addAfter(777,888);
        System.out.println(list.remove(6));
        System.out.println(list.remove(Integer.valueOf(777)));
//        System.out.println(list.replace(4, 999));
        System.out.println(list.contains(888));

        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(3));
        System.out.println(list);


    }
}
