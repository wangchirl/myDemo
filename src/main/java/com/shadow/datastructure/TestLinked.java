package com.shadow.datastructure;

public class TestLinked {
    public static void main(String[] args) {
        List list = new SingleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);

        list.addBefore(2,666);
        list.addAfter(2,777);

        System.out.println(list.replace(1, 999));
        System.out.println(list.contains(666));
        System.out.println(list.indexOf(777));

//        System.out.println(list.remove(2));
//        System.out.println(list.remove(Integer.valueOf(50)));

        System.out.println(list.size());
        System.out.println(list.isEmpty());
        System.out.println(list.get(3));
        System.out.println(list);

    }
}
