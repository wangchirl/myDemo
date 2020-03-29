package com.shadow.aop;

import java.lang.annotation.*;

/**
 * @Target 指定注解可以使用的地方
 * @Retention 指定注解适用的环境
 */
@Documented // 注解是否将包含再JavaDoc中
@Inherited // 是否允许子类继承该注解
@Target({ElementType.METHOD, // 方法
        ElementType.TYPE,  // 类，接口，注解类型或枚举类型
//        ElementType.PACKAGE, //包
//        ElementType.PARAMETER, //参数
//        ElementType.ANNOTATION_TYPE, // 修饰注解类
//        ElementType.FIELD, // 属性
//        ElementType.CONSTRUCTOR, // 构造器
//        ElementType.LOCAL_VARIABLE //局部变量
})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {


    String value(); // 值

    String cacheName();

    boolean openLog() default false;


}
