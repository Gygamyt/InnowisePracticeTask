package com.innowise.innowise_practice.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropReader {
    public static final String VK_FILE = "/vk.properties";

    public static final Properties VK_Properties = new Properties();

    static {
        initProperties(VK_FILE, VK_Properties);
    }

    public static String getVKProperty(String property) {
        return VK_Properties.getProperty(property);
    }

    public static synchronized void initProperties(String fileName, Properties properties) {
        try (InputStream inputStream = PropReader.class.getResourceAsStream(fileName)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException("No file" + fileName);
        }
    }
}
