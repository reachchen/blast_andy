package com.yhbc.base.service;

import android.support.annotation.Nullable;

/**
 * 服务类接口(注册服务 注册组件)
 * Created by xuhaijiang on 2018/5/4.
 */
public interface IManager {
    /**
     * 注册服务
     *
     * @param cla 服务接口
     * @param t   服务实现类 ,<font color='red'>必须以无参数构造实现</font>
     * @param <T> t
     */
    <T> void registerService(Class<T> cla, T t);

    /**
     * 注册服务
     *
     * @param cla       服务接口
     * @param subTClass 服务接口的实现类class
     * @param <T>       接口Class
     * @param <SubT>    接口实现类Class
     */
    <T, SubT extends T> void registerService(Class<T> cla, Class<SubT> subTClass);

    /**
     * 取消服务
     *
     * @param cla 服务接口
     */
    void unRegisterService(Class cla);


    /**
     * 获取服务
     *
     * @param cla 服务接口
     * @param <T> t
     * @return 服务实现类
     */
    <T> T service(Class<T> cla);

    /**
     * 注册组件
     *
     * @param classname 组件名
     */
    void registerComponent(@Nullable String classname);

    /**
     * 注销组件
     *
     * @param classname 组件名
     */
    void unregisterComponent(@Nullable String classname);

    /**
     * 工厂
     */
    class ManagerFactory {

        /**
         * 缓存数量
         */
        private static int cacheSize = 20;

        /**
         *
         * @param cacheSize 缓存服务数量，用于LruC
         */
        public static void setCacheSize(int cacheSize) {
            ManagerFactory.cacheSize = cacheSize;
        }

        public static int getCacheSize() {
            return cacheSize;
        }

        public static IManager getManage() {
            return ServiceManager.getInstance();
        }

        /**
         * 获取服务
         *
         * @param cla 服务接口
         * @param <T> t
         * @return 服务实现类
         */
        public static <T> T getService(Class<T> cla) {
            return ServiceManager.getInstance().service(cla);
        }
    }
}
