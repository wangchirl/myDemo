package com.shadow.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConifg.class);
        Person person = ac.getBean(Person.class);
        System.out.println(person);
    }
}
