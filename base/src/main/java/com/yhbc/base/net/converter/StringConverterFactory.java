package com.yhbc.base.net.converter;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by chengwen on 2017/12/04.
 * retrofit转换成字符串支持
 */
public final class StringConverterFactory extends Converter.Factory {


    public static StringConverterFactory create() {
        return new StringConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new ConfigurationServiceConverter();
    }

    final class ConfigurationServiceConverter implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody value) throws IOException {
            BufferedReader r = new BufferedReader(new InputStreamReader(value.byteStream()));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            String result = total.toString();
            Log.d("Converter", result);
            return result;
        }
    }
}
