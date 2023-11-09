package com.innowise.innowise_practice.ui.utils;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect implements CustomLogger {
    @Around(value = "@within(LoggerAnnotation) || @annotation(LoggerAnnotation)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {


    }
}
