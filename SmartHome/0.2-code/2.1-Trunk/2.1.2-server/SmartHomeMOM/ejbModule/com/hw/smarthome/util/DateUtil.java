package com.hw.smarthome.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static String getTime() {
		SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		return formater.format(new Date());
	}

	// 判断是否是同一天
	public static boolean isSameDay(Date date1, Date date2) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

		return sf.format(date1).equals(sf.format(date2));
	}

	// 判断是否是几天前的 date1 是否是 date2 前pastDays天内
	public static boolean isLastManyDays(Date date1, Date date2,
			int pastDays) {
		if (date1 == null)
			return false;
		Date tempDate = getDateBefore(date2, pastDays);
		return tempDate.before(date1);
	}

	// 判断是否是过去24小时的 date1 是否是 date2 前pastHours小时内
	public static boolean isLastOneDay(Date date1, Date date2,
			int pastHours) {
		if (date1 == null)
			return false;
		long cha = date2.getTime() - date1.getTime();
		double result = cha * 1.0 / (1000 * 60 * 60);
		if (result <= pastHours) {

			return true;
		} else {

			return false;
		}

	}

	/**
	 * * 得到几天前的时间 * * @param d * @param day * @return
	 * */
	public static Date getDateBefore(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return now.getTime();
	}

	/**
	 * * 得到几天后的时间 * * @param d * @param day * @return
	 */
	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}
}
