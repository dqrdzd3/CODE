/**
 * 文件名：KnowLedgePropertiesManager.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 KnowLedgePropertiesManager 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.constants;

import com.hw.hwsafe.platform.constants.BasePropertiesManager;
/**
 * 
 * 项目名称：hw-KnowLedgePropertiesManager
 * 类名称：KnowLedgePropertiesManager
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2013-4-11 上午10:42:27
 * @version 
 * 
 */
public class KnowLedgePropertiesManager extends BasePropertiesManager {
	
	/**
	 * 资源文件路径
	 */
	private static final String PROPERTIES_PATH = "/knowledge_constans.properties";
	/**
	 * 实例
	 */
	private static final KnowLedgePropertiesManager instance = new KnowLedgePropertiesManager();
	
	/**
	 * 构造函数
	 */
	private KnowLedgePropertiesManager() {
	}
	
	/**
	 * 获取类的实例
	 */
	public static KnowLedgePropertiesManager getInstance() {
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
