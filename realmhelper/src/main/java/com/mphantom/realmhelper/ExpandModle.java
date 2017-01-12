package com.mphantom.realmhelper;

import io.realm.RealmObject;

/**
 * Created by mphantom on 17/1/12.
 */

public class ExpandModle extends RealmObject {
    private String key;
    private String value;

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
