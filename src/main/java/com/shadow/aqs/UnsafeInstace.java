package com.shadow.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeInstace {

    public static Unsafe reflectGetUnsafe(){
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
