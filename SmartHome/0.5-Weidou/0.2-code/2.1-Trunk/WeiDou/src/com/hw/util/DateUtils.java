package com.hw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * 
 * @author 曾凡
 * @time 2014年7月4日 上午10:42:59
 */
public class DateUtils {

	private static final SimpleDateFormat yyyyMMddFt = new SimpleDateFormat(
			"yyyyMMdd");
	private static final SimpleDateFormat yyyymmddhhssmmFt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public final static String getDaysFromNow(String par1) {

		StringBuilder sb = new StringBuilder();

		try {
			String time = par1.substring(11, par1.length());
			Date date1 = yyyymmddhhssmmFt.parse(par1);
			int realtime = Integer.valueOf(yyyyMMddFt
					.format(date1));
			Date date2 = new Date();
			int currenTime = Integer.valueOf(yyyyMMddFt
					.format(date2));

			// long between = (date2.getTime() - date1.getTime()) / 1000;//
			// 除以1000是为了转换成秒
			long day = currenTime - realtime;
			if (day < 1) {
				sb.append("今天");
				sb.append(time);
			} else if (day == 1) {
				sb.append("昨天 ");
				sb.append(time);
			} else if (day == 2) {
				sb.append("前天 ");
				sb.append(time);
			} else if (day == 3) {
				sb.append("大前天 ");
				sb.append(time);
			} else {
				sb.append(par1);
			}

		} catch (ParseException e) {
			Ln.e(e, "获取时间");
		} finally {
			return sb.toString();
		}
	}

	/**
	 * 删除时分秒，返回yyyyMMdd
	 * 
	 * @author 曾凡
	 * @param time
	 * @return
	 * @time 2014年7月4日 上午11:34:58
	 */
	public static Integer deletehhmmss(String time) {
		if (time == null || "".equals(time)) {
			Ln.e("日期为空！");
			return -1;
		}
		Integer returnDate = 0;
		if (time.length() == 8) {
			return Integer.valueOf(time);
		}
		try {
			Date date = yyyymmddhhssmmFt.parse(time);
			returnDate = getYearMonDay(date);
		} catch (ParseException e) {
			Ln.e("日期转换异常！", e);
		} finally {
			return returnDate;
		}
	}

	/**
	 * 获取yyyyMMdd格式int日期
	 * 
	 * @author 曾凡
	 * @param date
	 * @return
	 * @time 2014年7月4日 上午11:05:48
	 */
	public static Integer getYearMonDay(Date date) {
		return Integer.valueOf(yyyyMMddFt.format(date));
	}

	/**
	 * 返回前7天的年月日
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月4日 上午10:45:45
	 */
	public static Integer[] get7DaysBefore() {
		Integer[] days = new Integer[7];
		Calendar now = Calendar.getInstance();

		Integer tempDay = 0;
		for (int i = days.length - 1; i >= 0; i--) {
			now.add(Calendar.DATE, -1);
			tempDay = getYearMonDay(now.getTime());
			days[i] = tempDay;
		}
		return days;
	}

	/**
	 * 测试现在到par1的时间差
	 * 
	 * @author 曾凡
	 * @param par1
	 * @return
	 * @time 2014年7月23日 上午11:51:41
	 */
	public final static int getMinitusFromNow(String par1) {

		int result = 0;
		SimpleDateFormat ft = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = ft.parse(par1);
			Date date2 = new Date();
			long between = (date2.getTime() - date1.getTime()) / 1000;// 除以1000是为了转换成秒
			long minute1 = between / 60;
			result = Integer.parseInt(minute1 + "");
		} catch (ParseException e) {
			Ln.e(e, "得出日期之间的分钟数异常");
		} finally {
			return result;
		}
	}

	/**
	 * 得出日期之间的分钟数
	 */
	public final static int getMinitusInDates(String par1,
			String par2) {

		int result = 0;
		SimpleDateFormat ft = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		try {
			Date date1 = ft.parse(par1);
			Date date2 = ft.parse(par2);
			long between = (date2.getTime() - date1.getTime()) / 1000;// 除以1000是为了转换成秒
			long minute1 = between / 60;
			result = Integer.parseInt(minute1 + "");
		} catch (ParseException e) {
			Ln.e(e, "得出日期之间的分钟数异常");
		} finally {
			return result;
		}
	}

	/**
	 * 
	 * 函 数 名：getCurrentTime 功能描述：(获取系统当前时间) 输入参数：() 返 回 值： (yyyy-MM-dd HH:mm:ss
	 * 时间) 异 常： (按照异常名字的字母顺序) 创建人：lijing 创建时间：2013-12-17 上午8:56:38 修改人： 修改时间：
	 * 修改原因描述：
	 */
	public static String getCurrentTime() {
		String temp_str = "";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		// SimpleDateFormat sdf = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		temp_str = sdf.format(dt);
		return temp_str;
	}

	public static void main(String[] args) {
		for (int day : get7DaysBefore()) {
			System.out.println(day);
		}
	}
}
