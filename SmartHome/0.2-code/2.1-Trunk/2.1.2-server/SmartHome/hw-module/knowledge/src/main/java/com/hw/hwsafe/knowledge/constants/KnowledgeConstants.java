/**
 * 文件名：KnowledgeConstants.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 KnowledgeConstants 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.constants;


/**
 * 
 * 项目名称：hw-knowledge
 * 类名称：KnowledgeConstants
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2013-4-11 下午2:55:31
  * @version 
 *
 */
public class KnowledgeConstants {
	/**
	 * 单例
	 */
	private static KnowLedgePropertiesManager knowledgeconstantsPropertiesManager = KnowLedgePropertiesManager.getInstance();

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
		NAME_EXIST_MSG = knowledgeconstantsPropertiesManager.getPropertyStr("NAME_EXIST_MSG");
		NUMBER_EXIST_MSG = knowledgeconstantsPropertiesManager.getPropertyStr("NUMBER_EXIST_MSG");
		CERTIFICATE_NUMBER_EXIST_MSG = knowledgeconstantsPropertiesManager.getPropertyStr("CERTIFICATE_NUMBER_EXIST_MSG");
	}
}
