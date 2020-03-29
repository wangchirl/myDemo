package com.shadow.jvm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.concurrent.TimeUnit;

public class CgLib {
    public static void main(String[] args) {
        for (;;){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(CgLib.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor)(obj, method, args1, proxy)-> proxy.invokeSuper(obj, args1));
            System.out.println("proxy");

            enhancer.create();
        }
    }
}
