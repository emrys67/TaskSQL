package com.foxminded.jdbc.connection;

import com.foxminded.jdbc.exceptions.ConnectionException;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    private static final String PROPERTIES_STRING = "application.properties";
    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    private PropertiesUtil() {
    }

    private static void loadProperties() {
        try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(PROPERTIES_STRING)) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new ConnectionException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }


}