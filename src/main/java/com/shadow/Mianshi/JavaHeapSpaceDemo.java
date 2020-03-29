package com.shadow.Mianshi;

import com.shadow.datastructure.ArrayList;
import com.shadow.datastructure.List;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * java.lang.OutOfMemoryError: java heap space
 */
public class JavaHeapSpaceDemo {
    public static void main(String[] args) throws InterruptedException {
        // 参数设置 -Xms10m -Xmx10m
        List list = new ArrayList();
        for(;;){
            list.add(new JavaHeapSpaceDemo());
            TimeUnit.MICROSECONDS.sleep(100);
        }
        //byte[] data = new byte[10*1024*1024];
    }
}
