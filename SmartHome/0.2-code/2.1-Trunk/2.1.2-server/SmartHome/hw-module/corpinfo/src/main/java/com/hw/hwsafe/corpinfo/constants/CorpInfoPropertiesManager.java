/**
 * 文件名：CorpInfoPropertiesManager.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.constants;

import com.hw.hwsafe.platform.constants.BasePropertiesManager;
/**
 * 
 * 项目名称：hw-corpinfo
 * 类名称：CorpInfoPropertiesManager
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2013-4-11 上午10:42:27
 * @version 
 * 
 */
public class CorpInfoPropertiesManager extends BasePropertiesManager {
	
	/**
	 * 资源文件路径
	 */
	private static final String PROPERTIES_PATH = "/corpinfo_constans.properties";
	/**
	 * 实例
	 */
	private static final CorpInfoPropertiesManager instance = new CorpInfoPropertiesManager();
	
	/**
	 * 构造函数
	 */
	private CorpInfoPropertiesManager() {
	}
	
	/**
	 * 获取类的实例
	 */
	public static CorpInfoPropertiesManager getInstance() {
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
