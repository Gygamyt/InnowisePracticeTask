package com.innowise.innowise_practice.ui.logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.FieldSignature;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Aspect
public class LoggingAspect implements CustomLogger {

    private String nameSaver = null;

    @After("@annotation(LoggerAnnotation) && execution(* *(..))")
    public void getMethodName(JoinPoint joinPoint) {
        if (nameSaver != null) {
            staticLogger.info(" " + getActionName(joinPoint) + nameSaver);
        } else staticLogger.info(getActionName(joinPoint) + " null name");


        nameSaver = null;
    }

    @After("@annotation(org.openqa.selenium.support.FindBy)")
    public void forceSetNameIfNotSetOfElementAndLogIt(JoinPoint joinPoint) {
        FieldSignature fieldSignature = (FieldSignature) joinPoint.getSignature();
        Field field = fieldSignature.getField();

        StringBuilder stringBuilder = new StringBuilder();

        if (field.isAnnotationPresent(NameForLogger.class)) {
            if (field.getAnnotation(NameForLogger.class).name().matches("")){
                stringBuilder.append(field.getName());
            } else stringBuilder.append(field.getAnnotation(NameForLogger.class).name());
        } else stringBuilder.append(field.getName()).append(" element");

//        staticLogger.info(stringBuilder);

        nameSaver = stringBuilder.toString();
    }


    private String getFieldName(JoinPoint joinPoint) {
        FieldSignature fieldSignature = (FieldSignature) joinPoint.getSignature();
        Field field = fieldSignature.getField();
        StringBuilder stringBuilder = new StringBuilder();

        if (field.getAnnotation(NameForLogger.class).name().equals("")
                || field.isAnnotationPresent(NameForLogger.class)) {
            stringBuilder.append(fieldSignature.getName());
            stringBuilder.append(" element");
        } else stringBuilder.append(field.getAnnotation(NameForLogger.class).name());

        return stringBuilder.toString();
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
