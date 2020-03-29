package com.shadow.reflex;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 框架类
 */
public class ReflectFramwork {
    public static void main(String[] args) throws Exception{
        // 1.加载配置文件
        Properties properties = new Properties();
        ClassLoader loader = ReflectFramwork.class.getClassLoader();
        InputStream resourceAsStream = loader.getResourceAsStream("class.properties");
        properties.load(resourceAsStream);

        // 2.获取配置文件中定义的数据
        String cls = properties.getProperty("class.name");
        String mtd = properties.getProperty("method.name");

        // 3.加载内进内存
        Class<?> aClass = Class.forName(cls);
        // 4.创建对象
        Object o = aClass.newInstance();
        // 5.获取对象方法
        Method method = aClass.getMethod(mtd);
        // 6.执行方法
        method.invoke(o);

    }
}
