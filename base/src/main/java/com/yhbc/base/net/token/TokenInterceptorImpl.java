package com.yhbc.base.net.token;


import android.app.Application;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yhbc.base.net.token.api.ITokenHelper;
import com.yhbc.base.net.token.api.ITokenInterceptor;
import com.yhbc.base.net.token.api.LoginCallback;
import com.yhbc.base.net.token.api.TokenApiService;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @author xuhaijiang on 2018/9/21.
 */
public class TokenInterceptorImpl implements ITokenInterceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");

    /**
     * token过期
     */
    public final static String CODE_TOKEN_OVER = "40003";
    private ITokenHelper helper;

    public TokenInterceptorImpl(LoginCallback callback, Application app) {
        init(callback, app);
    }

    private void init(LoginCallback callback, Application app) {
        if (null == callback) {
            throw new RuntimeException("TokenInterceptorImpl 添加的 LoginCallback 不能为空");
        }
        helper = ITokenHelper.Factory.newHelper(callback, app);
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        //有token才会有token过期，根据header中的token来过滤
        String token = request.header("token");
        request = idempotent(request, token);
        Response response = chain.proceed(request);

        if (isRefreshRequest(request)) {
            return response;
        }
        if (response != null && !TextUtils.isEmpty(token)) {
            String rBody = readResponseData(response);
            if (isTokenOver(rBody)) {
                Response newRes = helper.refreshToken(chain, request);
                if (null != newRes) {
                    return newRes;
                }
            }
        }
        return response;
    }

    /**
     * 是否是刷新token的请求
     *
     * @param request request
     * @return true：是
     */
    private boolean isRefreshRequest(Request request) {
        return request.url().toString().contains(TokenApiService.URL_HEAD_SPEC);
    }

    private static final String OUT_REQUEST_NO = "out_request_no";

    /**
     * 对请求做幂等处理
     *
     * @param request 请求
     * @param token   token token 不为空时，添加out_request_no参数
     * @return 请求
     */
    public static Request idempotent(Request request, String token) {
        if (!TextUtils.isEmpty(token)) {

            int urlHash = request.url().hashCode();
            String body = readRequestBody(request.body());
            int bodyHash = TextUtils.isEmpty(body) ? 0 : body.hashCode();
            int headHash;
            //幂等参数
            String outRequestNo = request.header(OUT_REQUEST_NO);
            if (TextUtils.isEmpty(outRequestNo)) {
                headHash = request.headers().hashCode();
            } else {
                Headers headers = request.headers().newBuilder().removeAll(OUT_REQUEST_NO).build();
                headHash = headers.hashCode();
            }
            Log.i("idempotent", urlHash + "," + bodyHash + "," + headHash);
            request = request.newBuilder().removeHeader(OUT_REQUEST_NO).
                    addHeader(OUT_REQUEST_NO, String.valueOf(urlHash + headHash + bodyHash))
                    .build();
        }
        return request;
    }

    /**
     * 读取请求body数据
     *
     * @param requestBody 。
     * @return 。
     */
    private static String readRequestBody(RequestBody requestBody) {
        if (requestBody != null) {
            Buffer buffer = new Buffer();
            try {
                requestBody.writeTo(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Charset charset = UTF8;
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(UTF8);
            }
            return buffer.readString(charset);
        }
        return null;
    }

    /**
     * 读取body数据
     *
     * @param response 响应
     * @return 数据
     * @throws IOException io
     */

    private static String readResponseData(@NonNull Response response) throws IOException {
        String rBody;
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        Charset charset = UTF8;
        MediaType contentType = responseBody.contentType();
        if (contentType != null) {
            try {
                charset = contentType.charset(UTF8);
            } catch (UnsupportedCharsetException e) {
                e.printStackTrace();
            }
        }
        buffer = buffer.clone();
        rBody = buffer.readString(charset);
        buffer.close();
        return rBody;
    }


    /**
     * 判断token是否过期
     *
     * @param rBody body
     */
    private boolean isTokenOver(String rBody) {
        try {
            JSONObject json = JSON.parseObject(rBody);
            String code = (String) json.get("code");
            if (CODE_TOKEN_OVER.equals(code)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
