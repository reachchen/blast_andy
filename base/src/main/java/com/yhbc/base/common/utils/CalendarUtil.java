package com.yhbc.base.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarUtil {
	
	private static final Calendar CALENDAR = Calendar.getInstance();
	private static final SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat sf2 =new SimpleDateFormat("MM-dd");
	private static final SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat sdf1 =new SimpleDateFormat("yyyyMMddHHmmssS");
	private static final SimpleDateFormat time =new SimpleDateFormat("HHmmss");
	
	public static int getYear(){
		return CALENDAR.get(Calendar.YEAR);
	}
	
	public static int getMonth(){
		return CALENDAR.get(Calendar.MONTH);
	}
	
	public static int getDay(){
		return CALENDAR.get(Calendar.DAY_OF_MONTH);
	}
	
	public static int getHour(){
		return CALENDAR.get(Calendar.HOUR_OF_DAY);
	}


	/**
	 * yyyyMMddHHmmssS
	 * @param date
	 * @return String yyyyMMddHHmmssS
	 */
	public static String formatByMillisecond(Date date){
		return sdf1.format(date);
	}
	


	/**
	 * 根据间隔计算前N天
	 * @param date
	 * @param space
	 * @return Date
	 */
	public static Date findDatesBySpace(Date date,int space){
    	try {
			return  sf.parse(sf.format((date).getTime() - 60*60*1000*24L*space));   //间隔的日期
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    }


    /**
	 * 根据yyyy-MM-dd HH:mm:ss格式化
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static Date formatByTime(String date){
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			newDate = null;
		}
		return newDate;
	}
	
	/**
	 * 根据yyyy-MM-dd HH:mm:ss格式化
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String format2String(Date date){
		return sdf.format(date);
	}

	/**
	 * 获取当前时间的时分秒 HHmmss（24小时格式）
	 * @return Integer
	 */
	public static Integer date2HHmmss(){
		return Integer.valueOf(time.format(new Date()));
	}


	/**获取当前日期是星期几*/
	public static String getWeekOfDate(Date dt) {
		String[] weekDays = {"0", "1", "2", "3", "4", "5", "6"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}


}
