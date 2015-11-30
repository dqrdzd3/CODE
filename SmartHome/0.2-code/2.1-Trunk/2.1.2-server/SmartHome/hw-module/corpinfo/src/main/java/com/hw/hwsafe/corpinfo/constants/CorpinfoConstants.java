/**
 * 文件名：CorpinfoConstants.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.constants;


/**
 * 
 * 项目名称：hw-corpinfo
 * 类名称：CorpinfoConstants
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2013-4-11 上午10:55:07
 * @version 
 * 
 */
public class CorpinfoConstants {
	/**
	 * 单例
	 */
	private static CorpInfoPropertiesManager corpinfoconstantsPropertiesManager = CorpInfoPropertiesManager.getInstance();

	/**
	 * 返回的消息
	 */
	public static final String NAME_EXIST_MSG;
	public static final String NUMBER_EXIST_MSG;
	public static final String CERTIFICATE_NUMBER_EXIST_MSG;
	/**
	 * 静态块
	 */
	static{
		NAME_EXIST_MSG = corpinfoconstantsPropertiesManager.getPropertyStr("NAME_EXIST_MSG");
		NUMBER_EXIST_MSG = corpinfoconstantsPropertiesManager.getPropertyStr("NUMBER_EXIST_MSG");
		CERTIFICATE_NUMBER_EXIST_MSG = corpinfoconstantsPropertiesManager.getPropertyStr("CERTIFICATE_NUMBER_EXIST_MSG");
	}
}
