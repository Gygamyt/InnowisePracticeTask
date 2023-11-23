package com.innowise.innowise_practice.ui.annotations;

import org.openqa.selenium.support.How;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NameForLogger {
    String name() default "";
}
