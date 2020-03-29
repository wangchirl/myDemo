package com.shadow.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * @Aspect 切面类
 */
@Aspect
public class MyAspect {

    @Pointcut("execution(* com.shadow.aop..*(..))")
    public void MyannotationPointcut(){

    }


    @Around("@annotation(com.shadow.aop.MyAnnotation)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
        Method method = ((MethodSignature) (pjp.getSignature())).getMethod();
        MyAnnotation annotation = method.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.value());
        System.out.println(annotation.cacheName());
        System.out.println(annotation.openLog());
        System.out.println("--------------------do before");
        Object proceed = pjp.proceed();
        System.out.println("--------------------do after");
        return proceed;
    }


}
