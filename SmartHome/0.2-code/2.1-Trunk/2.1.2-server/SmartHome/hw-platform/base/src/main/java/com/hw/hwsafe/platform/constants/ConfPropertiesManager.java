/**
 * 文件名：ConfPropertiesManager.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.constants;

/**
 * 
 * <p>
 * 配置资源文件管理类(单例)
 * </p>
 */
final class ConfPropertiesManager extends BasePropertiesManager {

	/**
	 * 资源文件路径
	 */
	private static final String CONF_PROPERTIES_PATH = "/conf.properties";

	/**
	 * 实例
	 */
	private static final ConfPropertiesManager instance = new ConfPropertiesManager();

	/**
	 * <p>
	 * 不对外暴露的构造函数
	 * </p>
	 * Constructor Method
	 */
	private ConfPropertiesManager() {
	}

	/**
	 * 
	 * 获取类的实例
	 * 
	 * @return ConfPropertiesManager
	 * @author:马宁
	 * @create_time:2013-5-8 上午10:37:26
	 */
	public static ConfPropertiesManager getInstance() {
		return instance;
	}

	/*
	 * (non-Javadoc)
	 * @see com.hw.hwsafe.platform.constants.BasePropertiesManager#getPorpertiesPath()
	 */
	@Override
	protected String getPorpertiesPath() {
		return CONF_PROPERTIES_PATH;
	}

}
