package com.yhbc.base.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 保存一些常量数据
 *
 * @author chengwen
 */
public class SpUtil {
    private static SharedPreferences sp;

    private static String KEY_SETTINGS = "pos";

    public static void init(Context context) {
        sp = context.getSharedPreferences(KEY_SETTINGS, Context.MODE_PRIVATE);
    }

    public static synchronized void putInt(String key, int value) {
        sp.edit().putInt(key, value).commit();
    }

    public static synchronized void putLong(String key, long value) {
        sp.edit().putLong(key, value).commit();
    }

    public static synchronized void putBoolean(String key, boolean value) {
        sp.edit().putBoolean(key, value).commit();
    }

    public static synchronized void putString(String key, String value) {
        sp.edit().putString(key, value).commit();
    }

    public static synchronized int getInt(String key) {
        return sp.getInt(key, 0);
    }

    public static synchronized long getLong(String key) {
        return sp.getLong(key, 0);
    }

    public static synchronized String getString(String key) {
        return sp.getString(key, "");
    }

    public static synchronized boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public static boolean isContains(String key) {
        return sp.contains(key);

    }

    public static void removeSetting(String key) {
        sp.edit().remove(key).commit();
    }

}