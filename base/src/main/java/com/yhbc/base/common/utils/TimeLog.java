package com.yhbc.base.common.utils;

import com.yhbc.base.BaseApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * 时间戳
 * Created by xuhaijiang on 2018/4/25.
 */
public class TimeLog {
    private static final String TAG = TimeLog.class.getSimpleName();

    private static Map<String, Time> cache;

    static {
        if (BaseApplication.baseApp.getIsDebug()) {
            cache = new HashMap<>();
        }
    }

    /**
     * 开始
     *
     * @param key key
     * @param tag 说明
     */
    public static void start(String key, String tag) {
        if (BaseApplication.baseApp.getIsDebug()) {
            Time time = getCacheTime(key);
            System.out.println(TAG + " " + key + " : start " + tag);
            time.start = time.current = System.currentTimeMillis();
        }

    }

    /**
     * 结束
     *
     * @param key key
     * @param tag 说明
     */
    public static void end(String key, String tag) {
        if (BaseApplication.baseApp.getIsDebug()) {
            Time time = getCacheTime(key);
            long cache = System.currentTimeMillis();
            long jiange = cache - time.current;
            long total = cache - time.start;
            time.current = cache;
            System.out.println(TAG + " " + key + " end:" + tag + ",jiange:" + jiange + ",total:" + total);
        }
    }

    /**
     * @param key key
     * @return 缓存时间
     */
    private static Time getCacheTime(String key) {
        Time time = cache.get(key);
        if (null == time) {
            time = new Time();
            cache.put(key, time);
        }
        return time;
    }

    static class Time {
        //开始时间
        long start;
        //当前时间
        long current;
    }
}
