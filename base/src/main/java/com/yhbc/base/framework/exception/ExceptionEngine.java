package com.yhbc.base.framework.exception;

import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;

import retrofit2.adapter.rxjava.HttpException;


/**
 * Created by chengwen on 2017-11-18.
 */
public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * 将异常都转化为可读的信息
     */
    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof ApiException) {
            ex = (ApiException) e;
        } else if (e instanceof HttpException) {             //HTTP错误
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                    ex = new ApiException("网络请求超时");//超时
                    break;
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex = new ApiException("网络请求错误！");//均视为网络错误
                    break;
            }
            e.printStackTrace();
        } else if (e instanceof JSONException || e instanceof ParseException
                || e instanceof com.alibaba.fastjson.JSONException
                || e instanceof org.xmlpull.v1.XmlPullParserException) {
            ex = new ApiException("数据解析错误！");
            e.printStackTrace();
        } else if (e instanceof IOException) {
            ex = new ApiException("网络连接失败，请检查网络！");
            e.printStackTrace();
        } else {
            ex = new ApiException("数据错误！");
            e.printStackTrace();
        }
        return ex;
    }

}
