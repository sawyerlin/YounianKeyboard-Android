package com.android.inputmethod.younian.source;

/**
 * Created by slin on 13/04/16.
 */
public class Item {
    String key;
    String value;

    public Item(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
