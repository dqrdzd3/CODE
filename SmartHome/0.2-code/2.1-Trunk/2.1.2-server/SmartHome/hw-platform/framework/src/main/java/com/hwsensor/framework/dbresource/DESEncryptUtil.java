/**
 * 文件名：DESEncryptUtil.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.framework.dbresource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * 
 * 项目名称：framework
 * 类名称：DESEncryptUtil
 * 类描述：DES加密工具类
 * 创建人：马宁
 * 创建时间：2012-11-29 下午12:43:12
 * 修改人：马宁
 * 修改时间：2012-11-29 下午12:43:12
 * 修改备注：
 * @version 
 *
 */
final class DESEncryptUtil {
	
	private DESEncryptUtil() {
		// do nothing
	}

	/**
	 * 
	 * 函 数 名：createKey
	 * 功能描述：创建密匙
	 * @author 马宁
	 * 创建时间：2012-11-28 下午2:12:36
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	@SuppressWarnings("restriction")
	static Key createKey() throws NoSuchAlgorithmException {
		Security.insertProviderAt(new com.sun.crypto.provider.SunJCE(), 1);
		KeyGenerator generator = KeyGenerator.getInstance("DES");
		generator.init(new SecureRandom());
		Key key = generator.generateKey();
		return key;
	}

	/**
	 * 
	 * 函 数 名：getKey
	 * 功能描述：获取密匙
	 * @param is - 输入流
	 * @author 马宁
	 * 创建时间：2012-11-28 下午2:13:23
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	static Key getKey(InputStream is) {
		try {
			ObjectInputStream ois = new ObjectInputStream(is);
			return (Key) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 
	 * 函 数 名：doDecrypt
	 * 功能描述：解密操作
	 * @param key - 密匙
	 * @param in - 输入流
	 * @author 马宁
	 * 创建时间：2012-11-28 下午2:15:14
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	static InputStream doDecrypt(Key key, InputStream in) {
		try {
			Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, key);
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			byte[] tmpbuf = new byte[1024];
			int count = 0;
			while ((count = in.read(tmpbuf)) != -1) {
				bout.write(tmpbuf, 0, count);
				tmpbuf = new byte[1024];
			}
			in.close();
			byte[] orgData = bout.toByteArray();
			byte[] raw = cipher.doFinal(orgData);
			ByteArrayInputStream bin = new ByteArrayInputStream(raw);
			return bin;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
