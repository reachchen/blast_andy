package com.yhbc.base.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * Created by xhj on 2018/3/20.
 */
public class ToolUtils {

    /**
     * 是否是手机/电话号码
     *
     * @param str str
     * @return true:is phone
     */
    public static boolean isPhoneNum(String str) {
        return isMobile(str) || isPhone(str);
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     * @author ：shijing
     * 2016年12月5日下午4:34:46
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return 验证通过返回true
     * @author ：shijing
     * 2016年12月5日下午4:34:21
     */
    public static boolean isPhone(final String str) {
        Pattern p1 = null, p2 = null;
        Matcher m = null;
        boolean b = false;
        p1 = Pattern.compile("^[0][1-9]{2,3}[-]?[0-9]{5,10}$");  // 验证带区号的
        p2 = Pattern.compile("^[1-9][0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            b = m.matches();
        } else {
            m = p2.matcher(str);
            b = m.matches();
        }
        return b;
    }
}
