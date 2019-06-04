package com.yhbc.base.net.token.api;

import android.app.Application;

import com.yhbc.base.net.token.TokenHelperImpl;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * token辅助类
 *
 * @author xuhaijiang on 2018/9/25.
 */
public interface ITokenHelper {

    /**
     * 刷新token
     *
     * @param request 在刷新token后重新请求token
     * @return 刷新token后，重新请求后的Response
     */
    Response refreshToken(Interceptor.Chain chain, Request request);

    /**
     * 处理token过期
     */
    void handlerTokenOver();


    /**
     * 工厂
     */
    class Factory {

        /**
         * 初始化
         *
         * @return 辅助类
         */
        public static ITokenHelper newHelper(LoginCallback callback, Application app) {
            return new TokenHelperImpl(callback, app);
        }
    }
}
