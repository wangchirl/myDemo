package com.shadow.aop;

/**
 * spring AOP 的注解方式
 * 1. 自定义注解
 * 2. 编写切面类
 * 3. 业务类使用注解
 */
@MyAnnotation(value = "class",cacheName = "class_cache_name")
public class SpringAOP {



    @MyAnnotation(value = "method",cacheName = "method_cache_name")
    public void getById(int id){

        System.out.println("get by id" + id);

    }

    @MyAnnotation(value = "method",cacheName = "method_cache_name",openLog = true)
    public void save(String name){

        System.out.println("save name "+ name);

    }


}
