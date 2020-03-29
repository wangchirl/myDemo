package com.shadow.Mianshi;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class HashMapTest {

    static A a;

    public static void main(String[] args) throws Exception {

        Map m = new HashMap(9);
        Class c = m.getClass();
        Field field = c.getDeclaredField("threshold");
        field.setAccessible(true);
        System.out.println(field.getInt(m));

    }

    public static class A{
//            StringBuffer
//        AtomicReference
    }


}

class B {

}

