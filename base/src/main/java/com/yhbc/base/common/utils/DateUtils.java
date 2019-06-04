package com.yhbc.base.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具类
 * Created by xhj on 2018/3/9.
 */
public class DateUtils {

    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD_HH_mm = "yyyy-MM-dd HH:mm";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        } else if (pattern == null) {
            return "";
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.CHINA);
            return formatter.format(date);
        }
    }

    public static String formateDate1(long milliseconds, String format) {
        Date date = new Date(milliseconds);
        SimpleDateFormat formatter = new SimpleDateFormat(format, Locale.getDefault());
        return formatter.format(date);
    }

    /**
     * String 转 Date
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_mm);
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String 转 Date
     *
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date strToDate(String strDate, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
