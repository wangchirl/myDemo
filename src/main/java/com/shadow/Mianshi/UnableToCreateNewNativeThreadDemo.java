package com.shadow.Mianshi;

import java.util.concurrent.TimeUnit;

/**
 * java.lang.OutOfMemoryError: unable to create new native thread
 */
public class UnableToCreateNewNativeThreadDemo {
    public static void main(String[] args) {
        for (int i = 1;  ; i++) {
            System.out.println("************************" + i);
            new Thread(() -> {
                try {TimeUnit.SECONDS.sleep(Long.MAX_VALUE);} catch (InterruptedException e) {e.printStackTrace();};
            },String.valueOf(i)).start();
        }
    }
}
