package com.mphantom.autotext;

import android.app.Application;

import com.mphantom.realmhelper.RealmHelper;

/**
 * Created by wushaorong on 16-10-14.
 */

public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        RealmHelper.init(this);
    }

    public static App getInstance() {
        return instance;
    }
}
