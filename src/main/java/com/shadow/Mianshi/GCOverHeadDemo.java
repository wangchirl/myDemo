package com.shadow.Mianshi;

import java.util.ArrayList;
import java.util.List;

/**
 * java.lang.OutOfMemoryError: GC overhead limit exceeded
 */
public class GCOverHeadDemo {
    public static void main(String[] args) {
        // -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
        int i = 0;
        List<String> list = new ArrayList<>();
        try {
          while (true){
              list.add(String.valueOf(i++).intern());// intern() 存入常量池
          }
        } catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
        }
    }
}
