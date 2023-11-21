package com.innowise.innowise_practice.ui.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Aspect
public class AssertionsAspect implements CustomLogger{

    @Pointcut("execution(* org.junit.jupiter.api.Assertions.*(..))")
    void anyAssertions() {

    }

//    @Around("execution(* org.junit.jupiter.api.Assertions.*(..))")
    public Object beforeAssertions(ProceedingJoinPoint proceedingJoinPoint) {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        staticLogger.info("start of assertion " +  methodSignature.getName());
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
