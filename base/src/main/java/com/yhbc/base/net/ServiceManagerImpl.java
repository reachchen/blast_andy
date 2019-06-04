package com.yhbc.base.net;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.yhbc.base.BaseAppication;
import com.yhbc.base.net.converter.FastJsonConverterFactory;
import com.yhbc.base.net.interceptor.LoggingInterceptor;
import com.yhbc.base.net.interceptor.RequestInterceptor;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.YhzRetrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;


/**
 * 服务管理类
 * Created by xuhaijiang on 2018/4/17.
 */
public class ServiceManagerImpl implements ServiceManager {

    private int macCache = 10;
    /**
     * 接口缓存
     */
    private final Map<String, Object> cacheMap = new LinkedHashMap<String, Object>(
            (int) Math.ceil(macCache / 0.75f) + 1, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
            return size() > macCache;
        }
    };

    @Override
    public void setMacCache(int macCache) {
        this.macCache = macCache;
    }

    private List<Interceptor> interceptors;

    public void setmInterceptor(Interceptor mInterceptor) {
        if (null == interceptors) {
            interceptors = new LinkedList<>();
        }
        interceptors.add(mInterceptor);
//        this.mInterceptor = mInterceptor;
    }


    @Override
    public <T> T findApiService(Class<T> cla) {
        return findApiService(cla, null, null, null, null, null);
    }

    @Override
    public <T> T findApiService(Class<T> cla, UrlEnum inMethod) {
        return findApiService(cla, inMethod, null, null, null, null);
    }

    @Override
    public <T> T findXmlApiService(Class<T> cla) {
        return findApiService(cla, null, "xml", null, SimpleXmlConverterFactory.create(), null);
    }

    @Override
    public <T> T findApiService(Class<T> cla, UrlEnum inMethod, boolean isAddInterceptor) {
        return findApiService(cla, inMethod, null, null, null, null, isAddInterceptor);
    }

    @Override
    public <T> T findApiService(Class<T> cla, UrlEnum inMethod, String tag, OkHttpClient client, Converter.Factory converFactory, CallAdapter.Factory callFactor) {
        return findApiService(cla, inMethod, tag, client, converFactory, callFactor, true);
    }

    private <T> T findApiService(Class<T> cla, UrlEnum inMethod, String tag, OkHttpClient client, Converter.Factory converFactory, CallAdapter.Factory callFactor, boolean isAddInterceptor) {
        String key = getKey(cla, tag, inMethod);
        //noinspection unchecked
        T t = (T) cacheMap.get(key);
        if (null != t) {
            return t;
        }
//        //调用的是接口上的注解url
//        if (null == inMethod) {
//            //Type 注解
//            Annotation[] annotation = cla.getAnnotations();
//            if (null != annotation && annotation.length > 0) {
//                for (Annotation sub : annotation) {
//                    if (sub instanceof YUrl) {
//                        YUrl yUrl = (YUrl) sub;
//                        t = addCache(cla, client, converFactory, callFactor, key, yUrl.value().getUrl(), isAddInterceptor);
//                        addMainViceCache(key, yUrl.value());
//                        return t;
//                    }
//                }
//
//            }
//        } else {
        t = addCache(cla, client, converFactory, callFactor, key, null, isAddInterceptor);
        return t;

//        }

//        throw new ApiException("请在接口中加入注解 YUrl");
    }


    @Override
    public void removeApiService(Class<?> cla, String tag, UrlEnum inMethod) {
        String key = getKey(cla, tag, inMethod);
        cacheMap.remove(key);
    }

    @NonNull
    private String getKey(Class<?> cla, String tag, UrlEnum inMethod) {
        return cla.getSimpleName() + (null == tag ? "" : tag) + (null == inMethod ? "" : inMethod.name());
    }

    private <T> T addCache(Class<T> cla, OkHttpClient client, Converter.Factory converFactory, CallAdapter.Factory callFactor, String key, String baseUrl, boolean isAddInterceptor) {
        Retrofit.Builder build = new Retrofit.Builder()
                .client(null == client ? getClient(interceptors, isAddInterceptor) : client)
                .addConverterFactory(null == converFactory ? FastJsonConverterFactory.create() : converFactory)
                .addCallAdapterFactory(null == callFactor ? RxJava2CallAdapterFactory.create() : callFactor);
        if (!TextUtils.isEmpty(baseUrl)) {
            build.baseUrl(baseUrl);
        }
        T t = YhzRetrofit.create(cla, build);
        cacheMap.put(key, t);
        return t;
    }

    //构造client
    public static OkHttpClient getClient(List<Interceptor> interceptors, boolean isAddInterceptor) {
        // 指定缓存路径,缓存大小10Mb
        Cache cache = new Cache(getSDKRootDir(BaseAppication.baseApp, "winboxcash/HttpCache"),
                1024 * 1024 * 10);
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .cache(cache)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        client.addInterceptor(new RequestInterceptor());
        if (isAddInterceptor) {
//            client.addInterceptor(new RetryInterceptor.Builder().build());
            if (interceptors != null) {
                for (Interceptor inter : interceptors) {
                    client.addInterceptor(inter);
                }
            }

//        client.addInterceptor(new HostInterceptor());
        }
//        if (BaseAppication.baseApp.getIsDebug()) {
        client.addInterceptor(new LoggingInterceptor());
//        }

        return client.build();
    }

    /**
     * 创建sdCard跟目录下的文件或者应用内文件
     *
     * @param context    context
     * @param uniqueName 文件夹名称
     * @return 根路径下的文件夹
     * @author xuhaijiang
     * @since 2015年9月28日下午3:04:55
     */
    public static File getSDKRootDir(Context context, String uniqueName) {
        String cachePath = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable() ? Environment
                .getExternalStorageDirectory().getPath() : context.getCacheDir().getPath();
        File file = new File(cachePath + File.separator + uniqueName);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;

    }

}
