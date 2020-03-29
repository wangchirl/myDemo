package com.shadow.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ParseAnnotation {
    public static void main(String[] args) {
        parseAnnotation();
    }



    public static void parseAnnotation(){
        Class clazz = SpringAOP.class;
        // 获得注解类对象
        Class anno = MyAnnotation.class;

        // 通过反射机制判断类是否加了指定注解
        if(clazz.isAnnotationPresent(anno)){
            // 通过反射获取注解实列
            MyAnnotation annotation = (MyAnnotation) clazz.getAnnotation(anno);
            // 打印注解携带的信息
            String value = annotation.value();
            String cacheName = annotation.cacheName();
            boolean openLog = annotation.openLog();
            System.out.println(value);
            System.out.println(cacheName);
            System.out.println(openLog);
        }

        System.out.println("-----------------------");

        // 通过发射机制判断方法是否加了指定注解
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if(method.isAnnotationPresent(anno)){
                MyAnnotation annotation = (MyAnnotation) method.getAnnotation(anno);
                String value = annotation.value();
                String cacheName = annotation.cacheName();
                boolean openLog = annotation.openLog();
                System.out.println(method.getName());
                System.out.println(value);
                System.out.println(cacheName);
                System.out.println(openLog);
            }
        }
    }



}
