package com.yhbc.base.service;

/**
 * 回调
 * Created by xuhaijiang on 2018/6/19.
 */
public interface ICallback<T> {
    /**
     * 回调
     *
     * @param t 回调的对象
     */
    void callback(T t);
}
