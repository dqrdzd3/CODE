/**
 * 文件名：PropertiesUtil.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 资源文件工具类
 * 
 * @author 马宁
 * @创建时间: 2013-04-02 10:32
 */
public class PropertiesUtil {

	/**
	 * 获取资源文件对象
	 * 
	 * @param path
	 *            - 资源文件的对象
 	 */
	public static Properties getProperties(String path) {
		Properties result = new Properties();

		Resource location = new ClassPathResource(path);
		PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
		InputStream is = null;
		try {
			is = location.getInputStream();
			propertiesPersister.load(result, is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeInputStream(is);
		}
		return result;
	}

	// ----------- private methods ------------
	
	/*
	 * 关闭输入流
	 */
	private static void closeInputStream(InputStream is){
		if(is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
