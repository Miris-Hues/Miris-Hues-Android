package com.miris.hues.miris_hues_android.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Null on 2017-10-02.
 */

public class PropertiesUtil {
    private static PropertiesUtil ourInstance = new PropertiesUtil();
    private Properties prop = new Properties();

    public static PropertiesUtil getInstance() {
        if (ourInstance == null) {
            return new PropertiesUtil();
        }
        return ourInstance;
    }

    public void setup() {
        try {
            prop.load(new FileInputStream("data.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    public void setProperty(String key, String value) {
        prop.setProperty(key, value);
    }
}
