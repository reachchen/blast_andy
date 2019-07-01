package retrofit2;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.yhbc.base.BaseApplication;
import com.yhbc.base.framework.exception.ApiException;
import com.yhbc.base.net.UrlEnum;
import com.yhbc.base.net.YKey;
import com.yhbc.base.net.YUrl;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 银盒子的retrofit<br>
 * 与retrofit相同包名是为了绕过retrofit中一些类不能引用的问题
 * @author xuhaijiang on 2018/10/9.
 */
public class YhzRetrofit {
    /**
     * 在retrofit外再做一次代理
     * @param service 接口类
     * @param build   原有的retrofit的builder 缺少baseUrl
     * @param <T>     t
     * @return 接口的代理实现类
     */
    @SuppressWarnings("unchecked")
    public static <T> T create(final Class<T> service, Retrofit.Builder build) {

//        Retrofit retrofit = build.build();
//        final T obj = retrofit.create(service);
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new Handler(service, build));
    }

    /**
     * 代理处理 <br>
     *
     * @author xuhaijaing
     * created at 2018/10/09 下午2:41
     */
    public static class Handler<T> implements InvocationHandler {

        private final Map<Method, ServiceMethod> serviceMethodCache = new LinkedHashMap<>();

        private Class<T> service;
        private Retrofit newRetrofit;
        private Retrofit.Builder build;

        Handler(Class<T> service, Retrofit.Builder build) {
            this.service = service;
            this.build = build;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Annotation[] annotations = method.getAnnotations();

            YKey yKey = null;
            YUrl yUrl = null;
            if (null != annotations && annotations.length > 0) {
                for (Annotation ann : annotations) {
                    if (ann instanceof YKey) {
                        yKey = (YKey) ann;
                    } else if (ann instanceof YUrl) {
                        yUrl = (YUrl) ann;
                    }

                }
            }
            if (null == yUrl) {
                //在方法上没有注解，去class上寻找
                Annotation[] annotation = service.getAnnotations();
                if (null != annotation && annotation.length > 0) {
                    for (Annotation sub : annotation) {
                        if (sub instanceof YUrl) {
                            yUrl = (YUrl) sub;
                            break;
                        }
                    }

                }
            }
            if (null == yUrl && null == yKey) {
                throw new ApiException("请在接口或者方法中加入注解 YUrl或者YKey");
            }

            if (null != yKey) {
                return handleYkey(yKey, method, args);
            } else {
                build.baseUrl(yUrl.value().getUrl());
                Retrofit retrofit = build.build();
                T obj = retrofit.create(service);
                return method.invoke(obj, args);
            }

        }

        /**
         * 处理YKey注解
         * @param yKey ykey
         * @param method 方法
         * @param args 参数
         * @return 重新生成的Retrofit对象
         */
        private Object handleYkey(YKey yKey, Method method, Object[] args) {
            //重构http请求
            Map<String, String> apiMap = BaseApplication.baseApp.getApiConfigMap();
            String value = null;
            if (null != apiMap) {
                value = apiMap.get(yKey.value());
            }
            if (TextUtils.isEmpty(value)) {
                value = yKey.defaultValue();
            }
            System.out.println("InvocationHandler_value:" + value);

            synchronized (serviceMethodCache) {
                build.baseUrl(UrlEnum.SCM_SYSTEM.getUrl());
                if (null == newRetrofit) {
                    newRetrofit = build.build();
                }
            }

            //去掉方法前'/'
            boolean isInFirst = value.indexOf("/") == 0;
            value = isInFirst ? value.substring(1) : value;
            ServiceMethod serviceMethod = loadServiceMethod(method, yKey, value);
            //noinspection unchecked
            OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);
            //noinspection unchecked
            return serviceMethod.adapt(okHttpCall);
        }

        /**
         * 获取方法上注解的http各head、body参数封装类
         *
         * @param method 接口中的方法
         * @param yKey   注解
         * @param value  YKey对应的relativeUrl
         * @return {@link ServiceMethod} 相同包名主要为了这个类
         */
        @NonNull
        private ServiceMethod loadServiceMethod(Method method, YKey yKey, String value) {
            ServiceMethod result;
            synchronized (serviceMethodCache) {
                result = serviceMethodCache.get(method);
                if (result == null) {
                    ServiceMethod.Builder newBuild = new ServiceMethod.Builder(newRetrofit, method);
                    newBuild.relativeUrl = value;
                    newBuild.httpMethod = yKey.httpMethod();
                    newBuild.hasBody = yKey.hasBody();
                    newBuild.relativeUrlParamNames = ServiceMethod.parsePathParameters(value);
                    result = newBuild.build();

                    serviceMethodCache.put(method, result);
                }
            }
            return result;

        }
    }
}
