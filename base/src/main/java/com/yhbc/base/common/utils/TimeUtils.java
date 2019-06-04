package com.yhbc.base.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Alan on 2017/3/1.
 */
public class TimeUtils {
    public final static String MM_DD_HH_MM_SS = "MM-dd HH:mm:ss";
    public final static String MM_DD_HH_MM = "MM-dd HH:mm";
    public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public final static String YYYY_MM_DD = "yyyy-MM-dd";
    public final static String YYYYMMDD = "yyyy/MM/dd";
    public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static String getNowDate() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String str = sdf.format(date);
        return str;
    }


    /**
     * 是否是今天
     *
     * @param date
     * @return
     */
    public static boolean isToday(Date date) {
        if (null == date) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        calendar.setTime(date);
        int year2 = calendar.get(Calendar.YEAR);
        int month2 = calendar.get(Calendar.MONTH);
        int day2 = calendar.get(Calendar.DAY_OF_MONTH);
        return (year == year2) && (month == month2) && (day == day2);
    }

    /**
     * 是否大于现在的时间
     *
     * @param date
     * @return
     */
    public static boolean isOverNow(Date date) {
        return null != date && date.getTime() > Calendar.getInstance().getTime().getTime();

    }

    public static String getNowDate2() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String str = sdf.format(date);
        return str;
    }

    /**
     * 获取当前的时间戳，精确到毫秒
     *
     * @return
     */
    public static String getNowDate3() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String str = sdf.format(date);
        return str;
    }

    public static long DateToLong(Date date) {
        return date.getTime();
    }

    public static long stringToLong(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(time);
            long l = date.getTime();
            String l1 = l + "";
            l1 = l1.substring(0, l1.length() - 3);
            long l3 = Long.parseLong(l1);
            return l3;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    /*
        * 将时间戳转换为时间
        */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String stampToDateByFormat(String s,String format) {
        String res = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = DateUtils.strToDate(s,"yyyy-MM-dd");
        try {
            res = simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static String week(String time) {
        Date date = null;
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(time);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "0";
        } else if (mydate == 2) {
            week = "1";
        } else if (mydate == 3) {
            week = "2";
        } else if (mydate == 4) {
            week = "3";
        } else if (mydate == 5) {
            week = "4";
        } else if (mydate == 6) {
            week = "5";
        } else if (mydate == 7) {
            week = "6";
        }
        return week;
    }


    public static String hours(long dateTime) {
        SimpleDateFormat sdf3 = new SimpleDateFormat("HH:mm:ss");
        String hours = sdf3.format(new Date(dateTime));
        return hours;
    }

    //----------------------通用时间转换----------------------
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_DATE = "yyyy-MM-dd";

    public static final String FORMAT_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 获取当前时间
     * return format对应的时间格式
     */
    public static String getTimeStamp(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        return str != null ? str : "";
    }

    /**
     * unix to String
     */
    public static String unix2String(Date date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
            if (date != null){
                String time = sdf.format(date);
                return time;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * unix to String
     */
    public static String unix2String(Date date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String time = sdf.format(date);
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * unix to String
     */
    public static String unix2String(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            String time = sdf.format(Long.parseLong(date));
            return time;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * String to unix
     */
    public static Date string2Unix(String time, String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String to unix
     */
    public static Date string2Unix(String time) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT);
        Date date = null;
        try {
            date = formatter.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    //String转换为Calendar时间
    public static Calendar getStrCalendar(String time, String format) {
        Calendar theCa = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date today = sdf.parse(time);
            theCa.setTime(today);
            return theCa;
        } catch (ParseException e) {
            return theCa;
        }
    }

    //返回明天的时间
    public static String getYesterday() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -1);//前一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(calendar.getTime());
        return date;
    }

    /**
     * 获取跟特定日期相隔特定天数的时间
     *
     * @param date   特定日期
     * @param add    相隔特定天数
     * @param format 时间格式
     * @return date string
     */
    public static String getDataFromSpec(Date date, int add, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, add);//前一天
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    //返回7点的开始时间
    public static String get7Day() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -6);//前一天
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(calendar.getTime());
        return date;
    }

    //返回本周的时间
    public static String getTimeOfWeekStart() {
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.clear(Calendar.MINUTE);
        ca.clear(Calendar.SECOND);
        ca.clear(Calendar.MILLISECOND);
        ca.set(Calendar.DAY_OF_WEEK, ca.getFirstDayOfWeek());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(ca.getTimeInMillis());
        return date;
    }


    /**
     * 得到本日0点
     */
    public static String getDayFirst() {
        Calendar calendar = Calendar.getInstance();
        Date date = new Date();
        calendar.setTime(date);
        date = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(date) + " 00:00:00";
    }


    /**
     * 获取指定时间到当前时间的相隔天数
     *
     * @param time
     * @return
     */
    public static int getDayByDate(long time) {
        int days = (int) ((System.currentTimeMillis() - time) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔(天数)
     *
     * @param time1
     * @param time2
     * @return
     */
    public static int differentDaysByMillisecond(String time1, String time2) {
        Date date1 = string2Unix(time1, "yyyy-MM-dd HH:mm:ss");
        Date date2 = string2Unix(time2, "yyyy-MM-dd HH:mm:ss");
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * 取得今天的第一个时刻
     *
     * @return 今天的第一个时刻
     */
    public static Date getTodayStartTime() {
        return getStartDate(new Date());
    }

    /**
     * 取得今天的最后一个时刻
     *
     * @return 今天的最后一个时刻
     */
    public static Date getTodayEndTime() {
        return getEndDate(new Date());
    }

    /**
     * 获取某天的起始时间, e.g. 2018-6-04 00:00:00.000
     *
     * @param date
     *            日期对象
     * @return 该天的起始时间
     */
    public static Date getStartDate(Date date) {
        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        return cal.getTime();
    }

    /**
     * 获取第七天前的时间
     *
     * @return
     */
    public static Date get7DayBefore(Date date) {
        long time = date.getTime();
        long time1 = time - 1000 * 60 * 60 * 24 * 7;
        return new Date(time1);
    }

    /**
     * 获取某天的结束时间, e.g. 2018-6-04 23:59:59.999
     *
     * @param date
     *            日期对象
     * @return 该天的结束时间
     */
    public static Date getEndDate(Date date) {

        if (date == null) {
            return null;
        }

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);

        return cal.getTime();
    }

    /**
     * 取得指定天数后的时间
     *
     * @param date
     *            基准时间
     * @param dayAmount
     *            指定天数，允许为负数
     * @return 指定天数后的时间
     */
    public static Date addDay(Date date, int dayAmount) {
        if (date == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayAmount);
        return calendar.getTime();
    }

    public static long stringToLong2(String time) {
        SimpleDateFormat format = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
        try {
            Date date = format.parse(time);
            return  date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取今年是哪一年
     *
     * @return
     */
    public static Integer getNowYear() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return Integer.valueOf(gc.get(1));
    }

    /**
     * 获取本月是哪一月
     *
     * @return
     */
    public static int getNowMonth() {
        Date date = new Date();
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(date);
        return gc.get(2) + 1;
    }

    /**
     * 获取某个日期的开始时间
     *
     * @param d
     * @return yyyy-MM-dd HH:mm:ss  格式
     */
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取某个日期的结束时间
     *
     * @param d
     * @return yyyy-MM-dd HH:mm:ss  格式
     */
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    /**
     * 获取本月的开始时间
     *
     * @return yyyy-MM-dd HH:mm:ss  格式
     */
    public static Date getBeginDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        return getDayStartTime(calendar.getTime());
    }

    /**
     * 获取本月的结束时间
     *
     * @return yyyy-MM-dd HH:mm:ss  格式
     */
    public static Date getEndDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(getNowYear(), getNowMonth() - 1, 1);
        int day = calendar.getActualMaximum(5);
        calendar.set(getNowYear(), getNowMonth() - 1, day);
        return getDayEndTime(calendar.getTime());
    }

    // 获取本周的开始时间
    @SuppressWarnings("unused")
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return getDayStartTime(cal.getTime());
    }

    // 获取本周的结束时间
    public static Date getEndDayOfWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

}