package com.shadow.jvm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Jconsole {

    Map<String,Integer> m = new HashMap<>();
    public static void main(String[] args) throws Exception{
        String a = "abc";
        String b = "def";
        a += b;

        Map<String,String> map = new HashMap<>();

        Function<String,Integer> function1 = (String s) -> {return s.length();};
        Function<String,Integer> function2 = (s) -> {return s.length();};
        Function<String,Integer> function3 = s -> {return s.length();};
        Function<String,Integer> function4 = s -> s.length();
        System.out.println(function1.apply("abc"));
        System.out.println(function2.apply("1"));
        System.out.println(function3.apply("22"));
        System.out.println(function4.apply("hello"));


        Predicate<String> predicate1 = (String s) -> {return s.isEmpty();};
        Predicate<String> predicate2 = (s) -> {return s.isEmpty();};
        Predicate<String> predicate3 = s -> {return s.contains("a");};
        Predicate<String> predicate4 = s ->  s.contains("a");
        System.out.println(predicate1.test("a"));
        System.out.println(predicate2.test(""));
        System.out.println(predicate3.test("lambda"));
        System.out.println(predicate4.test("www"));


        Consumer<String> consumer1 = (String s) -> {System.out.println(s);};
        Consumer<String> consumer2 =  (s) -> {System.out.println(s);};
        Consumer<String> consumer3 = s -> {System.out.println(s);};
        Consumer<String> consumer4 = s -> System.out.println(s);
        consumer1.accept("a");
        consumer2.accept("b");
        consumer3.accept("cde");
        consumer4.accept("qww");

        Supplier<String> supplier1 = () -> {return "supplier";};
        Supplier<String> supplier2 = () ->  "supplier...";
        System.out.println(supplier1.get());
        System.out.println(supplier2.get());


    }
}
