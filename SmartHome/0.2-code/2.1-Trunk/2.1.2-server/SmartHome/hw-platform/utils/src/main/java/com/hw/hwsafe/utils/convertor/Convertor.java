/**
 * 文件名：Convertor.java
 *
 * 版本信息：
 * 日期：2012-6-14
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils.convertor;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * 项目名称：framework
 * 类名称：Convertor
 * 类描述：
 * 创建人：lm
 * 创建时间：2012-6-14 下午5:17:36
 * 修改人：lm
 * 修改时间：2012-6-14 下午5:17:36
 * 修改备注：
 * @version 
 * 
 */
public final class Convertor {
	
	private Convertor(){}
	
	public static String toLing(String arg){
		return arg == null 
				? "0"
				: arg.trim();
	}
	
	public static double parseDouble(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return Double.parseDouble(arg);
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String numberFormat(double arg){
		try{
			DecimalFormat df = new DecimalFormat("############0.00");
			return df.format(arg);
		}catch(Exception e){
			return "";
		}
	}

	public static String numberFormat(String arg){
		try{
			double dArg = Double.valueOf(arg).doubleValue();
			DecimalFormat df = new DecimalFormat("############0.00");
			return df.format(dArg);
		}catch(Exception e){
			return "";
		}
	}
	
	public static Double getDouble(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return new Double(Double.parseDouble(arg));
		} catch (Exception e) {
			return new Double(0);
		}
	}

	public static double parseDouble(int arg) {
		try {
			return arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public static double parseDouble(float arg) {
		try {
			return arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public double parseDouble(long arg) {
		try {
			return arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public static long parseLong(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return Long.parseLong(arg);
		} catch (Exception e) {
			return 0;
		}
	}

	public static Long getLong(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return new Long(Long.parseLong(arg));
		} catch (Exception e) {
			return new Long(0);
		}
	}

	public static long parseLong(int arg) {
		try {
			return arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public static long parseLong(float arg) {
		try {
			return (long) arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public long parseLong(double arg) {
		try {
			return (long) arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int parseInt(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return Integer.parseInt(arg);
		} catch (Exception e) {
			return 0;
		}
	}

	public static int parseInt(long arg) {
		try {
			return (int) arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public static int parseInt(float arg) {
		try {
			return (int) arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public int parseInt(double arg) {
		try {
			return (int) arg;
		} catch (Exception e) {
			return 0;
		}
	}

	public static float parseFloat(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return Float.parseFloat(arg);
		} catch (Exception e) {
			return 0;
		}
	}

	public static Float getFloat(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return new Float(Float.parseFloat(arg));
		} catch (Exception e) {
			return new Float(0);
		}
	}

	public static Boolean parseBoolean(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return Boolean.valueOf(arg);
		} catch (Exception e) {
			return new Boolean(false);
		}
	}

	public static boolean parseBooleanValue(String arg) {
		if (arg != null) {
			arg = arg.trim();
		}
		try {
			return Boolean.valueOf(arg).booleanValue();
		} catch (Exception e) {
			return false;
		}
	}

	public static Long[] parseLong(String args[]) {
		if (args != null) {
			Long temp[] = new Long[args.length];
			for (int i = 0; i < args.length; i++) {
				try {
					temp[i] = new Long(Long.parseLong(args[i]));
				} catch (Exception e) {
					temp[i] = new Long(0);
				}
			}
			return temp;
		} else {
			return new Long[] { new Long(0) };
		}
	}
	
	public static double[] parseDouble(String args[]) {
		if (args != null) {
			double temp[] = new double[args.length];
			for (int i = 0; i < args.length; i++) {
				try {
					temp[i] =Double.parseDouble(args[i]);
				} catch (Exception e) {
					temp[i] = Double.parseDouble("0");
				}
			}
			return temp;
		} else {
			return new double[] {Double.parseDouble("0")};
		}
	}

	public static Date parseDate(String dateStr) {
		try {
			if (dateStr.length() > 10) {
				dateStr = dateStr.substring(0, 10);
			}
			return java.sql.Date.valueOf(dateStr);
		} catch (Exception e) {
			return new java.util.Date();
		}
	}
	
	public static Date parseDate(String dateStr,String arg0) {
		try {
			if (dateStr.length() > 10) {
				dateStr = dateStr.substring(0, 10);
			}
			return java.sql.Date.valueOf(dateStr);
		} catch (Exception e) {
			return new java.util.Date();
		}
	}

	public static String parseDate(Date date) {
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}
	
	public static String parseDate(Date date,String arg0) {
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(arg0);
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	public static String changeToBig(double value) {
		if(value<=0){
			return "零";
		}
		char[] hunit = { '拾', '佰', '仟' };
		// 段内位置表示
		char[] vunit = { '万', '亿' };
		// 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示

		long midVal = (long) (value * 100);
		// 转化成整形
		String valStr = String.valueOf(midVal);
		// 转化成字符串
		String head = valStr.substring(0, valStr.length() - 2);
		// 取整数部分
		String rail = valStr.substring(valStr.length() - 2);
		// 取小数部分
		String prefix = "";
		// 整数部分转化的结果
		String suffix = "";
		// 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) {
			// 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分";
			// 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray();
		// 把整数部分转化成字符数组
		char zero = '0';
		// 标志'0'表示出现过0
		byte zeroSerNum = 0;
		// 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) {
			// 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4;
			// 取段内位置
			int vidx = (chDig.length - i - 1) / 4;
			// 取段位置
			if (chDig[i] == '0') {
				// 如果当前字符是0
				zeroSerNum++;
				// 连续0次数递增
				if (zero == '0') {
					// 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0;
			// 连续0次数清零
			if (zero != '0') {
				// 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0'];
			// 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1];
				// 段结束位置应该加上段名如万,亿
			}
		}
		if (prefix.length() > 0)
			prefix += '圆';
		// 如果整数部分存在,则有圆的字样
		return prefix + suffix;
		// 返回正确表示
	}
	
	public static String getSqlInString(String[] obj) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < obj.length; i++) {
			sb.append("'");
			sb.append(obj[i]);
			sb.append("'");
			if (i != (obj.length - 1)) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	public static String getSqlInString(Long[] obj) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < obj.length; i++) {
			sb.append(obj[i]);
			if (i != (obj.length - 1)) {
				sb.append(",");
			}
		}
		return sb.toString();
	}

}
