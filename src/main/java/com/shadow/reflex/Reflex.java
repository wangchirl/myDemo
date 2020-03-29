package com.shadow.reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflex {
    public static void main(String[] args) throws Exception{

       /* // 1.Class.forName("全类名")
        Class aClass1 = Class.forName("com.shadow.reflex.Person");
        System.out.println(aClass1);
        // 2.类.class
        Class aClass2 = Person.class;
        System.out.println(aClass2);
        // 3.对象.getClass()
        Person p = new Person();
        Class aClass3 = p.getClass();
        System.out.println(aClass3);
        // Class对象是否一样？
        System.out.println(aClass1 == aClass2); // true
        System.out.println(aClass2 == aClass3); // true
        */

       /*// 1.获取 public 修饰的成员变量
        Class personClass = Person.class;
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }
        Person p = new Person();
        Field a = personClass.getField("a");
        a.set(p,"hello");
        System.out.println(a.get(p));

        // 2.获取所有成员变量
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            System.out.println(declaredField);
        }
        Field d = personClass.getDeclaredField("d");
        // 暴力反射
        d.setAccessible(true);
        d.set(p,"world");
        System.out.println(d.get(p));
        */

       /*// 1.获取 public 修饰的构造器
        Class<Person> personClass = Person.class;
        Constructor<?>[] constructors = personClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }
        Constructor<Person> constructor = personClass.getConstructor(String.class, int.class);
        System.out.println(constructor);
        Person person1 = constructor.newInstance("hello", 22);

        Constructor<Person> constructor1 = personClass.getConstructor();
        Person person2 = constructor1.newInstance();
        System.out.println(person2);
        Person person = personClass.newInstance();
        System.out.println(person);

        // 2.获取所有构造器
        Constructor<?>[] declaredConstructors = personClass.getDeclaredConstructors();
        for (Constructor<?> declaredConstructor : declaredConstructors) {
            System.out.println(declaredConstructor);
        }
        Constructor<Person> declaredConstructor = personClass.getDeclaredConstructor(String.class, int.class);
        System.out.println(declaredConstructor);
        */

       // 1.获取public 修饰的方法
       // 这里获取方法时，会将 父类的方法也会找出来
        Class personClass = Person.class;
        Method[] methods = personClass.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }
        Person p = new Person();
        // 获取指定名称的方法
        Method eat = personClass.getMethod("eat");
        // 执行方法
        eat.invoke(p);
        // 获取指定名称及参数的方法
        Method method = personClass.getMethod("eat",String.class);
        method.invoke(p,"fruit");

        // 2.获取所有方法
        Method[] declaredMethods = personClass.getDeclaredMethods();
        for (Method declaredMethod : declaredMethods) {
            System.out.println(declaredMethod);
            System.out.println(declaredMethod.getName());
        }
        Method read = personClass.getDeclaredMethod("read");
        // 暴力反射
        read.setAccessible(true);
        read.invoke(p);

        // 1.获取类名称
        String name = personClass.getName();
        System.out.println(name);

    }
}
