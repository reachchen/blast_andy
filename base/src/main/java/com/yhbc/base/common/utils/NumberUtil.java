package com.yhbc.base.common.utils;

import android.text.TextUtils;

/**
 * 创建时间： 2018/9/17
 * 作者：yangxd
 * 描述：数字帮助类
 **/
public class NumberUtil {

    /**
     * Str转换Int
     *
     * @param object
     * @return
     */
    public static int parseInt(String object) {
        if(TextUtils.isEmpty(object)) {
            return 0;
        }
        int obj = 0;
        try {
            obj = Integer.parseInt(object);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return obj;
    }

}
