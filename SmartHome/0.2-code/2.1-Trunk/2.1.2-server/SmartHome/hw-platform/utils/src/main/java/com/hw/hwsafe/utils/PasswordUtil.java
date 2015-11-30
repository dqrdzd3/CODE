/**
 * 文件名：Password.java
 *
 * 版本信息：
 * 日期：2012-5-23
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils;

import java.security.MessageDigest;

/**
 * 
 * 项目名称：framework
 * 类名称：Password
 * 类描述：
 * 创建人：lm
 * 创建时间：2012-5-23 下午2:38:09
 * 修改人：lm
 * 修改时间：2012-5-23 下午2:38:09
 * 修改备注：
 * @version 
 * 
 */
public class PasswordUtil {

	/**	十六进制下数字到字符的映射数组	*/
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * 把inputString加密。
	 * @param inputString	待加密的字符串
	 * @return
	 */
	public static String createPassword(String inputString){
		return encodeByMD5(inputString);
	}
	
	/**
	 * 验证输入的密码是否正确
	 * @param password		真正的密码（加密后的真密码）
	 * @param inputString	输入的字符串
	 * @return
	 */
	public static boolean authenticatePassword(String password, String inputString){
		if (password.equals(encodeByMD5(inputString))){
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对字符串进行MD5编码
	 * @param originString
	 * @return
	 */
	private static String encodeByMD5(String originString) {
		if (originString != null){
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] results = md.digest(originString.getBytes());
				String resultString = byteArrayToHexString(results);
				return resultString.toUpperCase();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b  字节数组
	 * @return 十六进制字串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/**
	 * 将一个字节转化成16进制形式的字符串
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}
