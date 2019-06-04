package com.yhbc.base.net.converter;

import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.yhbc.base.framework.exception.ApiException;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;

/**
 * Created by chengwen on 2016/8/2.
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
     * 转换方法
     */
    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        if (TextUtils.isEmpty(tempStr)) {
            throw new ApiException("数据请求失败了");
        }
        try {
            return JSON.parseObject(tempStr, type);
        } catch (Exception e) {
            Log.e("JSONException", tempStr);
            throw new ApiException("数据请求失败了");
        }

    }

}
