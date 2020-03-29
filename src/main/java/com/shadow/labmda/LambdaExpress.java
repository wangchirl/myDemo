package com.shadow.labmda;

import java.util.Optional;

// 函数式接口 @FunctionalInterface
@FunctionalInterface
interface Foo{
    // 无参数
//    public void sayHello();

    // 有参数有返回值
    public int add(int x, int y);

    // default 方法
    default void hello() {
        System.out.println("hello default method....");
    }

    default int mul(int x, int y) {
        return x*y;
    }

    // 静态方法实现
    public static int div(int x, int y) {
        return x/y;
    }
    public static int mv(int x, int y) {
        return x%y;
    }
}

/**
 * lambda表达式：接口方法有且只能有一个未实现的方法，default方法可以多个
 * 1. 拷贝小括号 写死右箭头 落地大括号
 * 2. 函数式接口 @FunctionalInterface
 * 3. default 方法
 * 4. 静态方法实现
 */
public class LambdaExpress {

    public static void main(String[] args) {

        /* Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("hello lambda...");
            }
        }; */
        // 无参数lambda表达式
//        Foo foo = () -> {System.out.println("hello lambda...");};
//        foo.sayHello();
        // 有参数有返回值
        Foo foo = (x,y) -> {return x+y;};
        System.out.println(foo.add(5, 8));
        foo.hello();
        System.out.println(foo.mul(2, 4));
        System.out.println(Foo.div(10, 2));
        System.out.println(Foo.mv(5, 2));


        Optional.of("Hello world").ifPresent(System.out::println);
    }
}
