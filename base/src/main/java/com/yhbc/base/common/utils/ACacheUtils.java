package com.yhbc.base.common.utils;

import android.content.Context;

import com.yhbc.base.BaseAppication;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-04-25.
 * 用于缓存各种数据的
 */

public class ACacheUtils<T extends Serializable> {

    //懒汉模式
    private static ACacheUtils interfice;
    private static ACache mACache;

    private ACacheUtils() {
        Context context = BaseAppication.baseApp.getBaseContext();
        mACache = ACache.get(context);
    }

    public static ACacheUtils getInstance() {
        if (interfice == null) {
            synchronized (ACacheUtils.class) {
                interfice = new ACacheUtils();
            }
        }
        return interfice;
    }

    /**
     * 保存任意实现了Serializable接口的对象
     * @param key
     * @param t
     */
    public void saveObject(String key, T t) {
        mACache.put(key, t);
    }

    /**
     * @param key
     * @return
     */
    public T getObject(String key) {
        return (T) mACache.getAsObject(key);
    }

}
