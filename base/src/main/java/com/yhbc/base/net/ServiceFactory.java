package com.yhbc.base.net;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;

/**
 * 工厂
 * Created by xuhaijiang on 2018/4/17.
 */
public class ServiceFactory {
    private final static ServiceManager manager = new ServiceManagerImpl();

    public static ServiceManager getServiceManager() {
        // TODO: 2018/4/18 暂时这样实现
        return manager;
    }

    public static void addOkHttpInterceptor(Interceptor interceptor){
        ((ServiceManagerImpl)manager).setmInterceptor(interceptor);
    }


    /**
     * 发现服务<br>
     * {@link ServiceManager#findApiService(Class)}
     */
    public static <T> T findApiService(Class<T> cla) {
        return getServiceManager().findApiService(cla);
    }

    /**
     * 发现服务<br>
     * {@link ServiceManager#findApiService(Class)}
     */
    public static <T> T findApiXmlService(Class<T> cla) {
        return getServiceManager().findXmlApiService(cla);
    }

    /**
     * 发现服务<br>
     * {@link ServiceManager#findApiService(Class, UrlEnum)}
     */
    public static <T> T findApiService(Class<T> cla, UrlEnum inMethod) {
        return getServiceManager().findApiService(cla, inMethod);
    }

    /**
     * 发现服务<br>
     * {@link ServiceManager#findApiService(Class, UrlEnum, boolean)}
     */
    public static <T> T findApiService(Class<T> cla, UrlEnum inMethod, boolean isAddInterceptor) {
        return getServiceManager().findApiService(cla, inMethod, isAddInterceptor);
    }

    /**
     * 发现服务<br>
     * {@link ServiceManager#findApiService(Class, UrlEnum, String, OkHttpClient, Converter.Factory, CallAdapter.Factory)}
     */
    public static <T> T findApiService(Class<T> cla, UrlEnum inMethod, String tag, OkHttpClient client, Converter.Factory converFactory, CallAdapter.Factory callFactor) {
        return getServiceManager().findApiService(cla, inMethod, tag, client, converFactory, callFactor);
    }

    /**
     * 移除缓存<br>
     * {@link ServiceManager#removeApiService(Class, String, UrlEnum)}
     */
    public static void removeApiService(Class<?> cla, String tag, UrlEnum inMethod) {
        getServiceManager().removeApiService(cla, tag, inMethod);
    }

}
