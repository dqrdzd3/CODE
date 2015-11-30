package com.hw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.hw.smarthome.ui.constant.UIConstant;

import android.R.anim;

/**
 * 日期工具类
 * 
 * @author 曾凡
 * @time 2014年7月4日 上午10:42:59
 */
public class DateUtils {

	private static final SimpleDateFormat hhssmm = new SimpleDateFormat(
			"HHmmss");

	private static final SimpleDateFormat yyyyMMddFt = new SimpleDateFormat(
			"yyyyMMdd");
	private static final SimpleDateFormat yyyymmddhhssmmFt = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	
	private static final SimpleDateFormat yyyymmddhhssmmFt1 = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	private static final SimpleDateFormat yyyymmddhhIntFt = new SimpleDateFormat(
			"yyyyMMddHH");
	private static final SimpleDateFormat mmddhhssmmFt = new SimpleDateFormat(
			"MM/dd HH:mm:ss");
	
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
//				sb.append(getWeekOfDateCn(date1)).append(" ");
				sb.append("今天");
				sb.append(time);
			} else if (day == 1) {
//				sb.append(getWeekOfDateCn(date1)).append(" ");
				sb.append("昨天 ");
				sb.append(time);
			} else if (day == 2) {
//				sb.append(getWeekOfDateCn(date1)).append(" ");
				sb.append("前天 ");
				sb.append(time);
			} else {
				sb.append(par1.substring(5, par1.length()).replace("-", "/"));
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
	 * 删除yyyyMMdd ，返回HH24
	 * 
	 * @author 曾凡
	 * @param time
	 * @return
	 * @time 2014年7月4日 上午11:34:58
	 */
	public static Integer deleteyyyyMMdd(String time) {
		if (time == null || "".equals(time)) {
			Ln.e("日期为空！");
			return -1;
		}
		Integer hour = 0;
		try {
			Date date = yyyymmddhhssmmFt.parse(time);
			hour = getHhSsMm(date);
			String temp = "";
			if (hour < 10000) {
				temp = "00" + hour;
			} else if (hour < 100000) {
				temp = "0" + hour;
			} else {
				temp = hour + "";
			}
			hour = Integer.valueOf(temp.substring(0, 2));
		} catch (ParseException e) {
			Ln.e("日期转换异常！", e);
		} finally {
			return hour;
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

	public static Integer getHhSsMm(Date date) {
		return Integer.valueOf(hhssmm.format(date));
	}
	public static Integer getYyyymmddhhIntFt(Date date) {
		return Integer.valueOf(yyyymmddhhIntFt.format(date));
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

	public static String[] get7DaysBeforeStr() {
		String[] days = new String[7];
		Calendar now = Calendar.getInstance();
		String tempDay = "";
		for (int i = days.length - 1; i >= 0; i--) {
			now.add(Calendar.DATE, -1);
			tempDay = getWeekOfDate(now.getTime());
			days[i] = tempDay;
		}

		return days;

	}

	public static Integer[] get30DaysBefore() {
		Integer[] days = new Integer[30];
		Calendar now = Calendar.getInstance();

		Integer tempDay = 0;
		for (int i = days.length - 1; i >= 0; i--) {
			now.add(Calendar.DATE, -1);
			tempDay = getYearMonDay(now.getTime());
			days[i] = tempDay;
		}
		return days;
	}

	public static String[] get30HoursBeforeCn() {
		String[] res = new String[30];
		int hour = 0;
		for (int i = 0; i < 30; i++) {
			hour = get30DaysBefore()[i];
			res[i] = ((hour + "").substring(4, 6)) + "/"
					+ (hour + "").substring(6, 8) + "";
		}

		return res;
	}

	public static Integer[] get24HoursBefore() {
		Integer[] days = new Integer[24];
		Calendar now = Calendar.getInstance();

		Integer tempDay = 0;
		for (int i = days.length - 1; i >= 0; i--) {
			now.add(Calendar.HOUR_OF_DAY, -1);
			tempDay = getYyyymmddhhIntFt(now.getTime());
			days[i] = tempDay;
		}
		return days;
	}

	public static String[] get24HoursBeforeCn() {
		String[] res = new String[24];
		Integer[] times = get24HoursBefore();
		int hour = 0;
		String temp = "";
		for (int i = 0; i < 24; i++) {
			hour = times[i];
			if (hour < 10000) {
				temp = "00" + hour;
			} else if (hour < 100000) {
				temp = "0" + hour;
			} else {
				temp = hour + "";
			}
			res[i] = temp.substring(8, 10) + "时";
		}

		return res;
	}

	public static String[] get7DaysBeforCn() {
		String[] days = new String[7];
		Calendar now = Calendar.getInstance();
		String tempDay = "";
		for (int i = days.length - 1; i >= 0; i--) {
			now.add(Calendar.DATE, -1);
			tempDay = getWeekOfDateCn(now.getTime());
			days[i] = tempDay;
		}

		return days;

	}

	/**
	 * 获取当前日期是周几<br>
	 * 
	 * @param dt
	 * @return 当前日期是周几
	 */
	public static String getWeekOfDate(Date dt) {
		// String[] weekDays = { "周日", "周一", "周二", "周三", "周四",
		// "周五", "周六" };
		String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	/**
	 * 获取当前日期是周几<br>
	 * 
	 * @param dt
	 * @return 当前日期是周几
	 */
	public static String getWeekOfDateCn(Date dt) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四",
				"周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
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
			result = 999;
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
	public static String getCurrentDateTime() {
		String temp_str = "";
		Date dt = new Date();
		// 最后的aa表示“上午”或“下午” HH表示24小时制 如果换成hh表示12小时制
		// SimpleDateFormat sdf = new
		// SimpleDateFormat("yyyy-MM-dd HH:mm:ss aa");
	
		temp_str = yyyymmddhhssmmFt1.format(dt);
		return temp_str;
	}
	public static String getCurrentMMDDTime() {
		String temp_str = "";
		Date dt = new Date();
		temp_str = mmddhhssmmFt.format(dt);
		return temp_str;
	}
	// 获得节假日
	public static String whichHoliday() {
		Calendar calendar = Calendar.getInstance();
		int m = calendar.get(calendar.MONTH);
		int d = calendar.get(calendar.DAY_OF_MONTH);
		if (m + 1 == 1 && d - 1 < 3) { // 元旦
			return UIConstant.NEWYEAR;
		}
		return UIConstant.COMMON;
	}

	public static void main(String[] args) {
		for (int day : get7DaysBefore()) {
			System.out.println(day);
		}
	}
	
	public static Integer getyyyymmddhhIntFtIntDate(String createTime) {

		if (createTime == null || "".equals(createTime)) {
			Ln.e("日期为空！");
			return -1;
		}
		Integer returnDate = 0;
		try {
			Date date = yyyymmddhhssmmFt.parse(createTime);
			returnDate = getYyyymmddhhIntFt(date);
		} catch (ParseException e) {
			Ln.e("日期转换异常！", e);
		} finally {
			return returnDate;
		}
	}
}
