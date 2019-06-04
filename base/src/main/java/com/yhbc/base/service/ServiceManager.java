package com.yhbc.base.service;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 服务管理类
 * Created by xuhaijiang on 2018/5/2.
 */
class ServiceManager implements IManager {

    /**
     * 服务缓存
     */
    private HashMap<String, Object> services = new LinkedHashMap<String, Object>(
            (int) Math.ceil(ManagerFactory.getCacheSize() / 0.75f) + 1, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            return size() > ManagerFactory.getCacheSize();
        }
    };
    /**
     * 服务二级缓存
     */
    private HashMap<String, Class> serviceCache = new HashMap<>();

    /**
     *
     */
    private HashMap<String, IApplicationLike> components = new HashMap<>();

    private static volatile ServiceManager instance;

    private ServiceManager() {
    }

    public static ServiceManager getInstance() {
        if (instance == null) {
            synchronized (ServiceManager.class) {
                if (instance == null) {
                    instance = new ServiceManager();
                }
            }
        }
        return instance;
    }

    /**
     * 注册服务
     *
     * @param cla 服务接口
     * @param t   服务实现类
     * @param <T> t
     */
    @Override
    public synchronized <T> void registerService(Class<T> cla, T t) {
        if (null == cla || t == null) {
            return;
        }
        serviceCache.put(cla.getName(), t.getClass());
        services.put(cla.getName(), t);
    }

    @Override
    public <T, SubT extends T> void registerService(Class<T> cla, Class<SubT> subTClass) {
        if (null == cla || subTClass == null) {
            return;
        }
        serviceCache.put(cla.getName(), subTClass);
    }

    /**
     * 取消服务
     *
     * @param cla 服务接口
     */
    @Override
    public synchronized void unRegisterService(Class cla) {
        if (null == cla) {
            return;
        }
        serviceCache.remove(cla.getName());
        services.remove(cla.getName());
    }

    /**
     * 获取服务
     *
     * @param cla 服务接口
     * @param <T> t
     * @return 服务实现类
     */
    @Override
    public synchronized <T> T service(Class<T> cla) {
        if (null == cla) {
            return null;
        }
        Object obj = services.get(cla.getName());
        if (null != obj) {
            //noinspection unchecked
            return (T) obj;
        }
        //noinspection unchecked
        Class<T> tClass = serviceCache.get(cla.getName());
        if (null != tClass) {
            try {
                T t = tClass.newInstance();
                services.put(cla.getName(), t);
                return t;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 注册组件
     *
     * @param classname 组件名
     */
    @Override
    public void registerComponent(@Nullable String classname) {
        if (TextUtils.isEmpty(classname)) {
            return;
        }
        if (components.keySet().contains(classname)) {
            return;
        }
        try {
            Class clazz = Class.forName(classname);
            IApplicationLike applicationLike = (IApplicationLike) clazz.newInstance();
            applicationLike.onCreate();
            components.put(classname, applicationLike);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反注册组件
     *
     * @param classname 组件名
     */
    @Override
    public void unregisterComponent(@Nullable String classname) {
        if (TextUtils.isEmpty(classname)) {
            return;
        }
        if (components.keySet().contains(classname)) {
            components.get(classname).onStop();
            components.remove(classname);
        }
    }

}
