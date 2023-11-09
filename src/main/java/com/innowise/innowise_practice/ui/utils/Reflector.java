package com.innowise.innowise_practice.ui.utils;

import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Arrays;

public interface Reflector {
    default String getElementName(WebElement element) {
        System.out.println(Arrays.toString(element.getClass().getFields()));
        return element.getClass().getName();
    }
}
