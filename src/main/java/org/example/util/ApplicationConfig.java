package org.example.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {

    private static Properties properties = new Properties();

    static {
        try {
            InputStream input = ApplicationConfig.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties");

            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}