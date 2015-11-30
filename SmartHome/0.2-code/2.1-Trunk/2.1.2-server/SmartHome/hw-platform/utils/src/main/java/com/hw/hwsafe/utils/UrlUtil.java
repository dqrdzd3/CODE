/**
 * 文件名：UrlUtil.java
 *
 * 版本信息：1.0
 * 日期：2012-7-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils;

/**
 * 
 * URL操作工具类 
 * 
 * @author 马宁
 *
 */
public final class UrlUtil {
	
	private static final String SUFFIX_DO = ".do";
	
	private static final String SUFFIX_ACTION = ".action";
	
	private UrlUtil(){}
	
	/**
	 * 获取第二个单独的"/"的index
	 * 
	 * @param url
	 *            - 参数
	 * 
	 * @author 马宁
	 */
	public static int getSecondSingleSlashIndex(String url) {
		int firstDoubleSlashesIndex = url.indexOf("//");
		int firstSingleSlashIndex = url.indexOf("/",
				firstDoubleSlashesIndex + 2);
		return firstSingleSlashIndex == -1
				? firstSingleSlashIndex
				: url.indexOf("/", firstSingleSlashIndex + 1);
	}
	
	/**
	 * 删除不必要的url后缀,如.do,.action
	 * @param url
	 * @return
	 */
	public static String delUnnecessaryUrlSuffix(String url){
		return url.endsWith(SUFFIX_DO) 
				? url.substring(0, url.lastIndexOf(SUFFIX_DO))
				: url.endsWith(SUFFIX_ACTION)
					? url.substring(0, url.lastIndexOf(SUFFIX_ACTION))
					: url;
	}
}
