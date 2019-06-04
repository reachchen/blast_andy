package com.yhbc.base.net.interceptor;

import android.text.TextUtils;

import com.yhbc.base.BaseAppication;
import com.yhbc.base.net.token.TokenUtils;
import com.yhbc.base.net.token.bean.TokenBean;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author SXQ
 * Date 2018/11/6
 * Explain
 */
public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        TokenBean bean = TokenUtils.getDataFromSp(BaseAppication.baseApp);
        Request request = chain.request()
                .newBuilder()
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("token", !TextUtils.isEmpty(bean.getToken()) ? bean.getToken() : "")
                .addHeader("invoke_source", "2234")
// .addHeader("Accept-Encoding", "*")
//                .addHeader("Connection", "keep-alive")
                .addHeader("Accept", "*/*")
//                .addHeader("Access-Control-Allow-Origin", "*")
//                .addHeader("Access-Control-Allow-Headers", "X-Requested-With")
//                .addHeader("Vary", "Accept-Encoding")
// .addHeader("Cookie", "add cookies here")
                .build();
        return chain.proceed(request);
    }
}
