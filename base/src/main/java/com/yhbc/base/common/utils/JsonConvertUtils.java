package com.yhbc.base.common.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by wangshuo on 2018/1/30.
 */

public class JsonConvertUtils {

    public static String objectToJson(Object object) throws Exception{
        return JSON.toJSONString(object);
    }
}
