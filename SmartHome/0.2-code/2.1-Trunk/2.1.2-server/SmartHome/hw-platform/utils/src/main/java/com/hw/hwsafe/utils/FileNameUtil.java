/**
 * 文件名：FileNameUtil.java
 *
 * 版本信息：
 * 日期：2012-11-08
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils;

/**
 * 
 * 项目名称：framework
 * 类名称：StringConvertor
 * 类描述：字符串转换工具类
 * 创建人：马宁
 * 创建时间：2012-11-8 下午1:08:58
 * 修改人：马宁
 * 修改时间：2012-11-8 下午1:08:58
 * 修改备注：
 * @version 
 *
 */
public class FileNameUtil {
	
	/**
	 * 文件名后缀转化为大写
	 * 				为了解决linux下文件后缀名大写时无法上传的BUG
	 * @author 马宁
	 */
	public static String convertSuffixToLowerCase(String fileName){
		int index = fileName.lastIndexOf(".");
		String prefix = fileName.substring(0, index);
		String suffix = fileName.substring(index);
		return prefix + suffix.toLowerCase();
	}
	
	/**
	 * 删除后缀
	 * @param fileName
	 * 				- 文件名(aaa.txt)
	 * 
	 * @author 马宁
	 */
	public static String delSuffix(String fileName){
		int index = fileName.lastIndexOf(".");
		return fileName.substring(0, index);
	}
	
}
