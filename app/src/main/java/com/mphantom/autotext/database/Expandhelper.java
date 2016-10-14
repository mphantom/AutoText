package com.mphantom.autotext.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mphantom.autotext.App;
import com.mphantom.autotext.ExpandModle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wushaorong on 16-10-14.
 */

public class Expandhelper {
    private SQLiteDatabase database;
    private ExpandDb dbhelper;
    public static Expandhelper instance;


    private static class SingletonHolder {
        private static final Expandhelper instance = new Expandhelper();
    }

    public static Expandhelper getInstance() {
        return SingletonHolder.instance;
    }

    private Expandhelper() {
        dbhelper = new ExpandDb(App.getInstance());
    }

    public List<ExpandModle> getExpands() {
        database = dbhelper.getReadableDatabase();
        String sql = "select * from " + "expand";
        Cursor cursor = database.rawQuery(sql, null);
        List<ExpandModle> expandModles = new ArrayList<>();
        int key = cursor.getColumnIndex("key");
        int value = cursor.getColumnIndex("value");
        if (cursor.moveToFirst()) {
            do {
                ExpandModle item = new ExpandModle();
                item.setKey(cursor.getString(key));
                item.setValue(cursor.getString(value));
                expandModles.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
//        if (database != null && database.isOpen()) {
//            database.close();
//        }
        return expandModles;
    }

    public ExpandModle getExpand(String key) {
        database = dbhelper.getReadableDatabase();
        String sql = "select * from " + "expand" + " where key= " + key;
        Cursor cursor = database.rawQuery(sql, null);
        ExpandModle expand = new ExpandModle();
        int value = cursor.getColumnIndex("value");
        if (cursor.moveToFirst()) {
        }
        cursor.close();
//        if (database != null && database.isOpen()) {
//            database.close();
//        }
        return expand;
    }


    public void closeDB() {
        if (database != null && database.isOpen()) {
            database.close();
        }
    }
}
