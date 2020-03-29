package com.shadow.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        User u1 = new User(11,"a",23);
        User u2 = new User(12,"b",24);
        User u3 = new User(13,"c",22);
        User u4 = new User(14,"d",28);
        User u5 = new User(16,"e",26);


        List<User> list = Arrays.asList(u1,u2,u3,u4,u5);

        List<User> collect = list.stream().sorted((t1, t2) -> {
            return u1.getId().compareTo(u2.getAge());
        }).collect(Collectors.toList());
        for (User u: collect
             ) {
            System.out.println(u.getId());
        }

        // 偶数ID 且年龄大于24 且用户名称转为大写 且用户名字字母倒排序，只输出一个用户名字
        list.stream()
                .filter(u -> {return u.getId()%2==0;})
                .filter(u -> {return u.getAge() > 24;})
                .map(u -> {return u.getUserName().toUpperCase();}) // 这里已经是 String 啦
                .sorted((s1,s2) -> {return s2.compareTo(s1);})
                .limit(1);

        list.stream()
                .filter(u -> u.getId()%2==0)
                .filter(u -> u.getAge() > 24)
                .map(u -> u.getUserName().toUpperCase()) // 这里已经是 String 啦
                .sorted((s1,s2) -> s2.compareTo(s1))
                .limit(1);


        // lambda表达式
        Function<String,Integer> function1 = (String s) -> {return s.length();};
        Function<String,Integer> function2 = (s) -> {return s.length();};
        Function<String,Integer> function3 = s -> {return s.length();};
        System.out.println(function1.apply("abc"));
        System.out.println(function2.apply("1"));
        System.out.println(function3.apply("22"));

        Predicate<String> predicate1 = (String s) -> {return s.isEmpty();};
        Predicate<String> predicate2 = (s) -> {return s.isEmpty();};
        Predicate<String> predicate3 = s -> {return s.contains("a");};
        System.out.println(predicate1.test("a"));
        System.out.println(predicate1.test(""));
        System.out.println(predicate1.test("lambda"));


        Consumer<String> consumer1 = (String s) -> {System.out.println(s);};
        Consumer<String> consumer2 =  (s) -> {System.out.println(s);};
        Consumer<String> consumer3 = s -> {System.out.println(s);};
        consumer1.accept("a");
        consumer2.accept("b");
        consumer3.accept("cde");

        Supplier<String> supplier1 = () -> {return "supplier";};
        System.out.println(supplier1.get());
    }
}








class User{
    private Integer id;
    private String userName;
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public User() {
    }

    public User(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }
}
