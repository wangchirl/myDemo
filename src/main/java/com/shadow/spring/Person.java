package com.shadow.spring;

import org.springframework.stereotype.Component;

@Component
public class Person {

    public Person(){
        System.out.println("person1");
    }

    public Person(Cat cat){
        System.out.println("person2");
    }

}
