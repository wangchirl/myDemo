package com.shadow.Mianshi;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MetaspaceDemo {
    static class Obj{

    }
    static int i = 0;
    public static void main(String[] args) {
        // -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
        try {
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(Obj.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return method.invoke(o,args) ;
                    }
                });
                enhancer.create();
            }
        } catch (Exception e){
            System.out.println("出现异常：" + i);
            e.printStackTrace();
        }
    }
}
