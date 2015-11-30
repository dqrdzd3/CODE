package com.hw.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * <br>
 * (maximum line width 65)
 * 
 * @author 曾凡
 * @date 2014-4-24下午6:45:07
 */
public class CodeUtil {

	public static String transIso2Utf(String iso88591) {
		String str = null;
		try {
			str = java.net.URLDecoder.decode(iso88591, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Ln.e("转换ISO-8859-1到UTF-8失败", e);
		}
		return str;
	}

	public static String transUtf2Iso(String utf8) {
		String str = null;
		try {
			str = java.net.URLEncoder.encode(utf8, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			Ln.e("转换ISO-8859-1到UTF-8失败", e);
		}
		return str;

	}

	public static void main(String[] args)
			throws UnsupportedEncodingException {
		String iso = "http://218.28.136.21:8081/gps.asp?xl=126%E8%B7%AF&ud=0&fx=%E4%BA%AC%E5%B9%BF%E8%B7%AF%E5%8D%97%E7%8E%AF%E8%B7%AF%E7%AB%99&sno=13&hczd=%E7%A7%A6%E5%B2%AD%E8%B7%AF%E9%99%87%E6%B5%B7%E8%B7%AF%E7%AB%99&ref=4";
		String utf8 = transIso2Utf(iso);
		System.out.println(utf8);

		String message = java.net.URLEncoder.encode("中文",
				"UTF-8");
		System.out.println(message);
		String str = java.net.URLDecoder
				.decode(message, "UTF-8");
		System.out.println(str);

		String raw = "co=7.0&ch4=7.0&name=[一氧化碳, 天然气]&unit=[ppm, 天然气]&dates=[星期三, 星期四, 星期五, 星期六, 星期日, 星期一, 星期二]&data1=[79.0, 79.0, 79.0, 79.0, 79.0, 79.0, 0]&data2=[79.0, 79.0, 79.0, 79.0, 79.0, 79.0, 0]";
		String raw2iso = transUtf2Iso(raw);
		System.out.println(raw2iso);

	}
}
