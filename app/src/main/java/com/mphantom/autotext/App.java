package com.mphantom.autotext;

import android.app.Application;

/**
 * Created by wushaorong on 16-10-14.
 */

public class App extends Application {
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }
}
