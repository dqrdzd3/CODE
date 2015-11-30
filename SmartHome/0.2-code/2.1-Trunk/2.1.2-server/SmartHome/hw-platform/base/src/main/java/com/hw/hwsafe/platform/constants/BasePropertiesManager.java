/**
 * 文件名：BasePropertiesManager.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.constants;

import java.util.Properties;

import com.hw.hwsafe.utils.PropertiesUtil;

/**
 * 资源文件管理抽象基类
 * 
 * @author 马宁
 * @创建日期: 2013-04-02 10:17
 */
public abstract class BasePropertiesManager {

	/**
	 * 资源文件对象
	 */
	private final Properties PROPERTIES = PropertiesUtil
			.getProperties(getPorpertiesPath());

	/**
	 * 获取资源文件路径
	 */
	protected abstract String getPorpertiesPath();

	// ------------- 对外暴露的方法 ----------------

	/**
	 * 获取资源
	 * 
	 * @param key
	 *            - 键
	 */
	public String getProperty(String key) {
		return PROPERTIES.getProperty(key);
	}

	/**
	 * 获取String类型的资源
	 * 
	 * @param key
	 *            - 键
	 */
	public String getPropertyStr(String key) {
		return PROPERTIES.getProperty(key);
	}

	/**
	 * 获取Integer类型的资源
	 * 
	 * @param key
	 *            - 键
	 */
	public Integer getPropertyInt(String key) {
		return Integer.parseInt(getPropertyStr(key));
	}
	
	/**
	 * 获取boolean类型资源
	 * @param key
	 * @return
	 */
	public boolean getPropertyBoolean(String key){
		return Boolean.parseBoolean(getPropertyStr(key));
	}
	
	/**
	 * 获取Long类型的资源
	 */
	public Long getPropertyLong(String key){
		return Long.parseLong(getPropertyStr(key));
	}
}
