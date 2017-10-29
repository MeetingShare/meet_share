package com.meet.common.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期操作类
 * 
 * @author frank
 *
 */
public class DateUtil {

	public static final String DEFAULT_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 格式化当前日期
	 * 
	 * @param format
	 * @return
	 */
	public static String format(String format) {
		if (StringUtils.isBlank(format))
			format = DEFAULT_TIME_FORMAT;
		return (new SimpleDateFormat(format)).format(new Date());
	}
	/**
	 * 格式化当前日期
	 * 
	 * @param format
	 * @return
	 * @throws ParseException 
	 */
	public static String formatDate(Date date) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		return format.format(date);
	}
	/**
	 * 格式化当前日期
	 * 
	 * @param format
	 * @return
	 * @throws ParseException 
	 */
	public static Date formatDate(String date) throws ParseException {
		SimpleDateFormat format=new SimpleDateFormat(DEFAULT_TIME_FORMAT);
		return format.parse(date);
	}
	/**
	 * 传入日期相差分钟数
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int minutesBetweenData(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long minutes = (time2 - time1) / (1000 * 60);
		return Integer.parseInt(String.valueOf(minutes));
	}

}
