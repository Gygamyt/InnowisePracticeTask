package com.innowise.innowise_practice.ui.logger;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggingAspect implements CustomLogger {
    @Around(value = "@within(com.innowise.innowise_practice.ui.logger.LoggerAnnotation)" +
            "|| @annotation(com.innowise.innowise_practice.ui.logger.LoggerAnnotation)" +
            "|| @within(com.innowise.innowise_practice.ui.logger.NameForLogger)" +
            "|| @annotation(com.innowise.innowise_practice.ui.logger.NameForLogger)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {

        return null;
    }
}
