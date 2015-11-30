/**
 * 文件名：SmartPropertiesManager.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 SmartPropertiesManager 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.constants;

import com.hw.hwsafe.platform.constants.BasePropertiesManager;
/**
 * 
 * 项目名称：hw-SmartPropertiesManager
 * 类名称：SmartPropertiesManager
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2013-4-11 上午10:42:27
 * @version 
 * 
 */
public class SmartPropertiesManager extends BasePropertiesManager {
	
	/**
	 * 资源文件路径
	 */
	private static final String PROPERTIES_PATH = "/smart_constans.properties";
	/**
	 * 实例
	 */
	private static final SmartPropertiesManager instance = new SmartPropertiesManager();
	
	/**
	 * 构造函数
	 */
	private SmartPropertiesManager() {
	}
	
	/**
	 * 获取类的实例
	 */
	public static SmartPropertiesManager getInstance() {
		return instance;
	}
	
	/**
	 * 实现父类的抽象方法
	 */
	@Override
	protected String getPorpertiesPath() {
		return PROPERTIES_PATH;
	}

}
