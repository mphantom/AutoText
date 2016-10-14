package com.mphantom.autotext.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wushaorong on 16-10-14.
 */

public class ExpandDb extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "expand.db";
    public static int DATABASE_VERSION = 1;

    public ExpandDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
