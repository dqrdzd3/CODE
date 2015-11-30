/**
 * 文件名：StringUtil.java
 *
 * 版本信息：1.0
 * 日期：2012-7-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils;
import java.io.UnsupportedEncodingException;

public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * mode=1为小写 mode=其他为大写
	 * @param str
	 * @param mode
	 * @return
	 */
	public static String toJP(String str, int mode) {

		char[] chars = str.toCharArray();

		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < chars.length; i++) {
			sb.append(getJP(chars[i]));
		}
		if (mode == 1) {
			return sb.toString().toLowerCase();
		} else {
			return sb.toString().toUpperCase();
		}
	}

	public static String getJP(char c) {
		byte[] array = new byte[2];
		try {
			array = String.valueOf(c).getBytes("gb2312");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (array.length < 2)
			return String.valueOf(c);
		int i = (short) (array[0] - '\0' + 256) * 256
				+ ((short) (array[1] - '\0' + 256));
		if (i < 0xB0A1)
			return String.valueOf(c);
		if (i < 0xB0C5)
			return "a";
		if (i < 0xB2C1)
			return "b";
		if (i < 0xB4EE)
			return "c";
		if (i < 0xB6EA)
			return "d";
		if (i < 0xB7A2)
			return "e";
		if (i < 0xB8C1)
			return "f";
		if (i < 0xB9FE)
			return "g";
		if (i < 0xBBF7)
			return "h";
		if (i < 0xBFA6)
			return "j";
		if (i < 0xC0AC)
			return "k";
		if (i < 0xC2E8)
			return "l";
		if (i < 0xC4C3)
			return "m";
		if (i < 0xC5B6)
			return "n";
		if (i < 0xC5BE)
			return "o";
		if (i < 0xC6DA)
			return "p";
		if (i < 0xC8BB)
			return "q";
		if (i < 0xC8F6)
			return "r";
		if (i < 0xCBFA)
			return "s";
		if (i < 0xCDDA)
			return "t";
		if (i < 0xCEF4)
			return "w";
		if (i < 0xD1B9)
			return "x";
		if (i < 0xD4D1)
			return "y";
		if (i < 0xD7FA)
			return "z";
		return String.valueOf(c);
	}
	
	/**
	 * 字符串为null或""时，转换成自定义的默认值 String2DefVal
	 * 
	 * @param str
	 *            要转换的字符串
	 * @param defStr
	 *            自定义的默认值
	 * @return String
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String string2DefVal(String str, String defStr) {
		return str == null || str.isEmpty()
				? defStr
				: str;
	}
	
	/**
	 * 字符串转换,为null时默认转为空字符串（""） String2DefVal
	 * 
	 * @param str
	 * @return String
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String string2DefVal(String str) {

		return string2DefVal(str, "");
	}

	/**
	 * 字符串数组转换，当字符串数组为空，或者有一个元素且为""时，转换成NULL StringArr2Null
	 * 
	 * @param strArr
	 * @return String[]
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public static String[] stringArr2Null(String[] strArr) {

		String[] result = null;

		if (strArr != null
				&& ((strArr.length == 1 && !strArr[0].isEmpty()) || strArr.length > 1)) {
			result = strArr;
		}

		return result;
	}
	
	/**
	 * 填充字符串
	 * 
	 * @param str
	 *            - 原始的字符串
	 * @param filler
	 *            - 填充物
	 * @param length
	 *            - 长度
	 * 
	 * @author 马宁
	 * 
	 */
	public static String fillPrefix(String str, char filler, int length) {
		if (str.length() >= length) {
			return str;
		}

		StringBuffer sb = new StringBuffer();
		int replenishLength = length - str.length();
		for (int i = 0; i < replenishLength; i++) {
			sb.append(filler);
		}
		return sb.append(str).toString();
	}
}
