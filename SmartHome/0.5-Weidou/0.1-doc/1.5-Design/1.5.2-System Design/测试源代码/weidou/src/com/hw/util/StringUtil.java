package com.hw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具包
 * 
 * @author liux (http://my.oschina.net/liux)
 * @version 1.0
 * @created 2012-3-21
 */
public class StringUtil {
	private final static Pattern emailer = Pattern
			.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	private final static SimpleDateFormat dateFormater = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat dateFormater2 = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static String nowDateTime() {
		return dateFormater.format(new Date());
	}

	public static String nowDate() {
		return dateFormater2.format(new Date());
	}

	/**
	 * 将字符串转位日期类型
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date toDate(String sdate) {
		try {
			return dateFormater.parse(sdate);

		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将日期字符串去掉时分秒
	 * 
	 * @param sdate
	 * @return
	 */
	public static String offHMS(String sdate) {
		Date tempDate;
		if (sdate != null && !sdate.equals("")) {
			try {
				tempDate = dateFormater.parse(sdate);
				return dateFormater2.format(tempDate);
			} catch (ParseException e) {
				return null;
			}
		} else {
			return sdate;
		}

	}

	/**
	 * 以友好的方式显示时间
	 * 
	 * @param sdate
	 * @return
	 */
	public static String friendly_time(String sdate) {
		Date time = toDate(sdate);
		if (time == null) {
			return "Unknown";
		}
		String ftime = "";
		Calendar cal = Calendar.getInstance();

		// 判断是否是同一天
		String curDate = dateFormater2.format(cal.getTime());
		String paramDate = dateFormater2.format(time);
		if (curDate.equals(paramDate)) {
			int hour = (int) ((cal.getTimeInMillis() - time
					.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math
						.max((cal.getTimeInMillis() - time
								.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
			return ftime;
		}

		long lt = time.getTime() / 86400000;
		long ct = cal.getTimeInMillis() / 86400000;
		int days = (int) (ct - lt);
		if (days == 0) {
			int hour = (int) ((cal.getTimeInMillis() - time
					.getTime()) / 3600000);
			if (hour == 0)
				ftime = Math
						.max((cal.getTimeInMillis() - time
								.getTime()) / 60000, 1)
						+ "分钟前";
			else
				ftime = hour + "小时前";
		} else if (days == 1) {
			ftime = "昨天";
		} else if (days == 2) {
			ftime = "前天";
		} else if (days > 2 && days <= 10) {
			ftime = days + "天前";
		} else if (days > 10) {
			ftime = dateFormater2.format(time);
		}
		return ftime;
	}

	/**
	 * 判断给定字符串时间是否为今日
	 * 
	 * @param sdate
	 * @return boolean
	 */
	public static boolean isToday(String sdate) {
		boolean b = false;
		Date time = toDate(sdate);
		Date today = new Date();
		if (time != null) {
			String nowDate = dateFormater2.format(today);
			String timeDate = dateFormater2.format(time);
			if (nowDate.equals(timeDate)) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 判断给定字符串是否空白串。 空白串是指由空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串，返回true
	 * 
	 * @param input
	 * @return boolean
	 */
	public static boolean isEmpty(String input) {
		if (input == null || "".equals(input))
			return true;

		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 字符串转整数
	 * 
	 * @param str
	 * @param defValue
	 * @return
	 */
	public static int toInt(String str, int defValue) {
		try {
			return Integer.parseInt(str);
		} catch (Exception e) {
		}
		return defValue;
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static int toInt(Object obj) {
		if (obj == null)
			return 0;
		return toInt(obj.toString(), 0);
	}

	/**
	 * 对象转整数
	 * 
	 * @param obj
	 * @return 转换异常返回 0
	 */
	public static long toLong(String obj) {
		try {
			return Long.parseLong(obj);
		} catch (Exception e) {
		}
		return 0;
	}

	/**
	 * 字符串转布尔值
	 * 
	 * @param b
	 * @return 转换异常返回 false
	 */
	public static boolean toBool(String b) {
		try {
			return Boolean.parseBoolean(b);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * 使用delimiter来分割str，生成数组
	 * 
	 * @param str
	 * @param delimiter
	 * @return
	 */
	public static String[] delimitedListToStringArray(
			String str, String delimiter) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] { str };
		}

		List<String> result = new ArrayList<String>();
		if ("".equals(delimiter)) {
			result.add(str);
		} else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(str.substring(pos));
			}
		}
		return result.toArray(new String[result.size()]);
	}

	// 判断str不是（null或者""）
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 清除str前面的不可见字符
	 * 
	 * @param str
	 * @return
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0
				&& Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	public static int[] GetNums(String s) {
		ArrayList<String> nums = new ArrayList<String>();
		String num = "";
		boolean lastIsNum = false;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {
				lastIsNum = true;
				num += s.substring(i, i + 1);
			}
			// } else {
			// if (lastIsNum)
			// nums.add(num);
			// num = "";
			// lastIsNum = false;
			// }
		}
		System.out.println("num:" + num);
		int[] rs = new int[nums.size()];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = Integer.parseInt((nums.get(i)));
		}
		return rs;

	}

	public static int GetDigit(String s) {
		int result = 0;
		String num = "";

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) <= '9' && s.charAt(i) >= '0') {

				num += s.substring(i, i + 1);
			}

		}
		if (num.length() > 0) {
			result = Integer.parseInt(num);
		}

		return result;

	}

	/**
	 * 分割
	 * 
	 * @param originalText
	 * @return
	 */
	public static List<String> getTagsList(String originalText,
			int c) {

		List<String> tags = new ArrayList<String>();
		if (originalText == null || originalText.equals("")) {
			tags.add("普通用户");
			return tags;
		}
		int indexOfComma = originalText.indexOf(',');
		String tag;
		while (indexOfComma != -1) {
			tag = originalText.substring(0, indexOfComma);
			tags.add(tag);

			originalText = originalText
					.substring(indexOfComma + 1);
			indexOfComma = originalText.indexOf(c);
		}

		tags.add(originalText);
		return tags;
	}

	/**
	 * 
	 * 
	 * isMobile(判断是否是手机号)
	 * 
	 * TODO(这里描述这个方法适用条件 – 可选)
	 * 
	 * TODO(这里描述这个方法的执行流程 – 可选)
	 * 
	 * TODO(这里描述这个方法的使用方法 – 可选)
	 * 
	 * TODO(这里描述这个方法的注意事项 – 可选)
	 * 
	 * @param name
	 * 
	 * @param @return 设定文件
	 * 
	 * @return true 是手机号
	 * 
	 * @Exception 异常对象
	 * 
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static boolean isMobile(String mobile) {
		Pattern p = Pattern.compile("1[358]\\d{9}");
		Matcher m = p.matcher(mobile);
		return m.matches();
	}

	/**
	 * 
	 * 函 数 名：Html2Text 功能描述：(去掉html标签) 输入参数：
	 * 
	 * @param inputString
	 *            String 输入字符串 返 回 值：
	 * @return String 去除html标签后字符串 异 常： (按照异常名字的字母顺序) 创建人：lijing 创建时间：2013-11-25
	 *         上午11:10:50 修改人： 修改时间： 修改原因描述：
	 */
	public static String Html2Text(String inputString) {
		String htmlStr = inputString; // 含html标签的字符串
		String textStr = "";
		java.util.regex.Pattern p_script;
		java.util.regex.Matcher m_script;
		java.util.regex.Pattern p_style;
		java.util.regex.Matcher m_style;
		java.util.regex.Pattern p_html;
		java.util.regex.Matcher m_html;
		java.util.regex.Pattern p_other;
		java.util.regex.Matcher m_other;

		try {
			String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; // 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script>
																										// }
			String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; // 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style>
																									// }
			String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式

			String regEx_other = "&[a-z]+;";// 一些特殊字符处理，主要是&开头;结尾

			p_script = java.util.regex.Pattern.compile(
					regEx_script,
					java.util.regex.Pattern.CASE_INSENSITIVE);
			m_script = p_script.matcher(htmlStr);
			htmlStr = m_script.replaceAll(""); // 过滤script标签

			p_style = java.util.regex.Pattern.compile(
					regEx_style,
					java.util.regex.Pattern.CASE_INSENSITIVE);
			m_style = p_style.matcher(htmlStr);
			htmlStr = m_style.replaceAll(""); // 过滤style标签

			p_html = java.util.regex.Pattern.compile(regEx_html,
					java.util.regex.Pattern.CASE_INSENSITIVE);
			m_html = p_html.matcher(htmlStr);
			htmlStr = m_html.replaceAll(""); // 过滤html标签

			p_other = java.util.regex.Pattern.compile(
					regEx_other,
					java.util.regex.Pattern.CASE_INSENSITIVE);
			m_other = p_other.matcher(htmlStr);
			htmlStr = m_other.replaceAll(""); // 过滤特殊字符标签

			textStr = htmlStr;

			try {
				byte[] bytes = textStr.getBytes("GBK");
				for (int i = 0; i < bytes.length; i++) {
					if (bytes[i] > 0 && bytes[i] < 32)
						bytes[i] = 32;
				}
				textStr = new String(bytes, "GBK");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			System.err.println("Html2Text: " + e.getMessage());
		}

		textStr = textStr.replaceAll(" ", "")
				.replaceAll("　", "").replaceAll("，", ",")
				.replaceAll("。", ".");
		return textStr;// 返回文本字符串
	}

	/**
	 * 
	 * 函 数 名：isNumLetter 功能描述：(验证只包含字母和数字) 输入参数：(验证的字符串) 返 回 值： (
	 * 
	 * @return true 只好喊字母数字 false 包含其他字符) 创建人：lijing 创建时间：2013-12-20 上午9:16:35
	 *         修改人： 修改时间： 修改原因描述：
	 */
	public static boolean isNumLetter(String s) {
		String regex = "^[a-zA-Z0-9]+$";
		Pattern pattern = Pattern.compile(regex);
		Matcher match = pattern.matcher(s);
		boolean b = match.matches();
		if (b) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 将字符串逆序返回
	 * 
	 * @author 曾凡
	 * @param str
	 * @return
	 * @time 2014年9月18日 下午7:21:48
	 */
	public String revStr(String str) {
		String tempStr = new String();
		for (int i = str.length() - 1; i >= 0; i--) {
			tempStr += str.charAt(i);
		}
		return tempStr;
	}
}