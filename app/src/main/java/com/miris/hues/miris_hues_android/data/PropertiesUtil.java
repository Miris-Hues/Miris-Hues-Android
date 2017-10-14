package com.miris.hues.miris_hues_android.data;

import android.content.Context;
import android.content.res.AssetManager;

import com.miris.hues.miris_hues_android.log.Loging;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Null on 2017-10-02.
 */

public class PropertiesUtil {
    private static PropertiesUtil ourInstance = new PropertiesUtil();
    private Properties prop;

    private PropertiesUtil() {

    }

    public static PropertiesUtil getInstance() {
        if (ourInstance == null) {
            return new PropertiesUtil();
        }
        return ourInstance;
    }

    public void setup(Context context) {
        try {
            prop = new Properties();
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open("data.properties");
            prop.load(inputStream);
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
