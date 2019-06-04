package com.yhbc.base.net;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * http服务
 * Created by xuhaijiang on 2018/4/17.
 */
public interface ServiceManager {

    /**
     * 设置最大缓存
     *
     * @param macCache 缓存最大个数
     */
    void setMacCache(int macCache);

    /**
     * http服务
     * @param cla retrofit 接口类
     * @param <T> retrofit 接口动态生成类
     * @return retrofit 接口动态生成类
     */
    <T> T findApiService(Class<T> cla);

    /**
     * http服务
     * @param cla retrofit 接口类
     * @param <T> retrofit 接口动态生成类
     * @return retrofit 接口动态生成类
     */
    <T> T findXmlApiService(Class<T> cla);

    /**
     * http服务
     * @param cla      retrofit 接口类
     * @param inMethod 在方法上的注解YUrl,替换在接口上的注解Url地址
     * @param <T>      retrofit 接口动态生成类
     * @return retrofit 接口动态生成类
     */
    <T> T findApiService(Class<T> cla, UrlEnum inMethod);


    /**
     * http服务
     * @param cla              retrofit 接口类
     * @param inMethod         在方法上的注解YUrl,替换在接口上的注解Url地址
     * @param <T>              retrofit 接口动态生成类
     * @param isAddInterceptor 是否添加拦截器
     * @return retrofit 接口动态生成类
     */
    <T> T findApiService(Class<T> cla, UrlEnum inMethod, boolean isAddInterceptor);

    /**
     * http服务
     * @param cla           retrofit 接口类
     * @param inMethod      在方法上的注解YUrl,替换在接口上的注解Url地址
     * @param tag           当cla重复时,增加一个标志作为key,防止重复
     * @param client        OkHttpClient
     * @param converFactory 数据解析工厂
     * @param callFactor    RxJava工厂
     * @param <T>           retrofit 接口动态生成类
     * @return retrofit 接口动态生成类
     */
    <T> T findApiService(Class<T> cla, UrlEnum inMethod, String tag, OkHttpClient client, Converter.Factory converFactory, CallAdapter.Factory callFactor);

    /**
     * 移除缓存数据
     *
     * @param cla retrofit 接口类
     * @param tag 当cla重复时,增加一个标志作为key,防止重复
     */
    void removeApiService(Class<?> cla, String tag, UrlEnum inMethod);

}
