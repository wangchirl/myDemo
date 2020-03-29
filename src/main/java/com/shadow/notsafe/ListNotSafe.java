package com.shadow.notsafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListNotSafe  {

    public static void main(String[] args) {

        Person p = new Person();
        new Thread(p).start();

    }
}
class Person implements Runnable{

    private static List list =  new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList<>());
    static {
        list.add("a");
        list.add("b");
        list.add("c");
    }
    @Override
    public void run() {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
            list.add("w");
        }
        System.out.println(list);
    }
}
