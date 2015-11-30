/**
 * 文件名：ConstansPropertiesManager.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.constants;

/**
 * 常量资源文件管理类(单例)
 * 
 * @author 马宁
 * @创建日期: 2013-04-02 10:10
 */
final class ConstansPropertiesManager extends BasePropertiesManager {

	/**
	 * 资源文件路径
	 */
	private static final String PROPERTIES_PATH = "/hw_constans.properties";

	/**
	 * 实例
	 */
	private static final ConstansPropertiesManager instance = new ConstansPropertiesManager();

	/**
	 * 不对外暴露的构造函数
	 */
	private ConstansPropertiesManager() {
	}

	/**
	 * 获取类的实例
	 */
	public static ConstansPropertiesManager getInstance() {
		return instance;
	}

	@Override
	protected String getPorpertiesPath() {
		return PROPERTIES_PATH;
	}

}
