package com.yhbc.base.net.token.api;

import android.app.Application;

import com.yhbc.base.net.token.TokenInterceptorImpl;

import okhttp3.Interceptor;

/**
 * token拦截器
 *
 * @author xuhaijiang on 2018/9/28.
 */
public interface ITokenInterceptor extends Interceptor {

    class Factory {
        public static ITokenInterceptor newInstance(LoginCallback callback, Application app) {
            return new TokenInterceptorImpl(callback, app);
        }
    }
}
