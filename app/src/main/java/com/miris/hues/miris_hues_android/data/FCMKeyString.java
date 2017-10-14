package com.miris.hues.miris_hues_android.data;

/**
 * Created by secret on 10/14/17.
 */

public class FCMKeyString {
    private String name;
    private String key;

    public FCMKeyString(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
