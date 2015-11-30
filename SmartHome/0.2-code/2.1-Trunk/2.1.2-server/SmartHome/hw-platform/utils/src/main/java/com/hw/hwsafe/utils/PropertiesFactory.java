/**
 * 文件名：PropertiesFactory.java
 *
 * 版本信息：
 * 日期：May 10, 2012
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;


/**
 * 
 * 项目名称：framework
 * 类名称：PropertiesFactory
 * 类描述：
 * 创建人：Administrator
 * 创建时间：May 10, 2012 8:45:01 PM
 * 修改人：Administrator
 * 修改时间：May 10, 2012 8:45:01 PM
 * 修改备注：
 * @version 
 * 
 */
public class PropertiesFactory {
	private static PropertiesFactory instance;

	private Properties properties;

	private String fileName;

	protected PropertiesFactory() {
		super();
	}

	public PropertiesFactory(String fileName) {
		this.fileName=fileName;
		this.init();
	}

	public static synchronized PropertiesFactory reload(String fileName) {
		instance = new PropertiesFactory(fileName);
		return instance;
	}

	public final void init() {
		InputStream inputStream = null;
		try {
			inputStream = getClass().getResourceAsStream(fileName);
			properties = new Properties();
			properties.load(inputStream);
			Enumeration en = properties.propertyNames();
			while (en.hasMoreElements()) {
				String key = (String) en.nextElement();
				String value = properties.getProperty(key);
				properties.setProperty(key, value);
			}
		} catch (Exception e) {
			this.create();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					inputStream = null;
				}
			}
		}
	}

	public String getProperty(String name) {
		return properties.getProperty(name);
	}

	public void setProperty(String name, String value) {
		OutputStream outputStream = null;
		try {
			String path = getClass().getResource(fileName).getPath();
			outputStream = new FileOutputStream(path);
			properties.setProperty(name, value);
			properties.store(outputStream, "");
		} catch (Exception e) {
			System.err.println("不能写取属性文件");
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close(); // 关闭流
				} catch (Exception e) {
					outputStream = null;
				}
			}
		}
	}

	private void create() {
		StringBuffer sb = new StringBuffer();
		sb.append(ClassLoader.getSystemResource(""));
		sb.append(fileName);
		sb.delete(0, 5);
		PrintWriter out = null;
		try {
			out = new PrintWriter(new BufferedWriter(new FileWriter(sb
					.toString())));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null){
				out.close();
			}
		}
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
