package com.innowise.innowise_practice.ui.logger;


import com.github.dockerjava.core.dockerfile.DockerfileStatement;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
public class LoggingAspect implements CustomLogger {

    String utilityString;

    @After("@annotation(LoggerAnnotation) && execution(* *(..))")
    public void afterClickAspect(JoinPoint joinPoint) {
        if (utilityString != null) {
            staticLogger.info(getActionName(joinPoint) + utilityString);
        } else staticLogger.info(getActionName(joinPoint) + " 'element name is not defined'");

        utilityString = null;
    }

    @After("@annotation(NameForLogger)")
    public void getElementName(JoinPoint joinPoint) {
        StringBuilder stringBuilder = new StringBuilder();
        FieldSignature fieldSignature = (FieldSignature) joinPoint.getSignature();
        Field field = fieldSignature.getField();
        stringBuilder.append(" \"");
        stringBuilder.append(field.getAnnotation(NameForLogger.class).name());
        stringBuilder.append("\"");
        utilityString = stringBuilder.toString();
    }

    private String getActionName(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        StringBuilder stringBuilder = new StringBuilder();

        if (method.getAnnotation(LoggerAnnotation.class).action().equals("default")) {
            stringBuilder.append(methodSignature.getName());
            stringBuilder.append(" method interacted with element ");
        } else stringBuilder.append(method.getAnnotation(LoggerAnnotation.class).action());

        return stringBuilder.toString();
    }
}
