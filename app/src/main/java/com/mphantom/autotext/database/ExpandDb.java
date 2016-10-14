package com.mphantom.autotext.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wushaorong on 16-10-14.
 */

public class ExpandDb extends SQLiteOpenHelper {
    public static String DATABASE_FILE = "expand.db";
    public static int DATABASE_VERSION = 1;
    public static String DB_NAME = "expand";
    public static String DB_COLUMN_KEY = "key";
    public static String DB_COLUMN_VALUE = "value";

    public ExpandDb(Context context) {
        super(context, DATABASE_FILE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE IF NOT EXISTS expand (id INTEGER PRIMARY KEY, key VARCHAR,value VARCHAR)";
        db.execSQL(query);
        //// TODO: 16-10-14  do something after create sqlite db
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
