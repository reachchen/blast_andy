package com.yhbc.base.common.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理,查看方法调用的时间日志
 * @author xuhaijiang on 2018/6/21.
 */
public class DynamicProxy implements InvocationHandler {

    private Object subject;

    public DynamicProxy(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] args)
            throws Throwable {

        TimeLog.start(subject.getClass().getName(), method.getName());

        Object result = method.invoke(subject, args);

        TimeLog.end(subject.getClass().getName(), method.getName());

        return result;
    }

    /**
     *
     * @param service 接口
     * @param subject 接口的实现类
     * @param <T> 接口泛型
     * @return 代理对象
     */
    public static <T> T create(final Class<T> service, Object subject) {
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service},
                new DynamicProxy(subject));
    }

}
