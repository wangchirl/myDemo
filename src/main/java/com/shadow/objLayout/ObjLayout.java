package com.shadow.objLayout;
import org.openjdk.jol.info.ClassLayout;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

class L{
    boolean flag;
    int a;
}
// -XX:+UseCompressedClassPointers 默认开启 klass pointer 压缩
public class ObjLayout {
    static L l = new L();
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        System.out.println(Integer.toHexString(l.hashCode()));
        countHash(l);
        System.out.println(ClassLayout.parseInstance(l).toPrintable());
    }

    public static void countHash(Object o) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        long hashCode = 0;
        for(long index = 7; index > 0;index--){
            hashCode |=(unsafe.getByte(o, index) & 0xFF) << ((index -1) * 8);
        }
        String code = Long.toHexString(hashCode);
        System.out.println("hashCode===0X" + code);
    }
}
