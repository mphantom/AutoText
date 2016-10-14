package com.mphantom.autotext;

/**
 * Created by wushaorong on 16-10-14.
 */

public class ExpandModle {
    private String key;
    private String value;

    public ExpandModle() {
    }

    public ExpandModle(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
