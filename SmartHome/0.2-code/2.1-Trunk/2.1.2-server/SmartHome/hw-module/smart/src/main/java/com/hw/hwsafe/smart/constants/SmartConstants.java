/**
 * 文件名：KnowledgeConstants.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 KnowledgeConstants 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.constants;


/**
 * 
 * 项目名称：hw-smart
 * 类名称：SmartConstants
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2013-6-25 上午午10:30
 * @version 
 *
 */
public class SmartConstants {
	/**
	 * 单例
	 */
	private static SmartPropertiesManager smartconstantsPropertiesManager = SmartPropertiesManager.getInstance();

	/**
	 * 返回的消息
	 */
	public static final String USER_ID_ISNULL;
	
	public static final String SESSION_ID_ISNULL;
	
	public static final String DRIVER_ID_ISNULL;
	
	public static final String DRIVER_TYPE_ISNULL;
	
	public static final String DRIVER_NAME_ISNULL;
	
	public static final String QUERY_DATA_SUCCESS;
	
	public static final String QUERY_DATA_FAILURE;
	
	public static final String AUTHENTICATION_FAILURE;
	
	public static final String ADD_DRIVER_SUCCESS;
	
	public static final String ADD_DRIVER_FAILURE;
	
	public static final String EDIT_DRIVER_SUCCESS;
	
	public static final String EDIT_DRIVER_FAILURE;
	
	public static final String DELETE_DRIVER_SUCCESS;
	
	public static final String DELETE_DRIVER_FAILURE;
	
	public static final String DUPLICATE_DRIVER_FAILURE;
	
	public static final String HWCLOUD_EQUIPMENT_URL;

	/**
	 * 静态块
	 */
	static{
		USER_ID_ISNULL = smartconstantsPropertiesManager.getPropertyStr("USER_ID_ISNULL");
		SESSION_ID_ISNULL = smartconstantsPropertiesManager.getPropertyStr("SESSION_ID_ISNULL");
		DRIVER_ID_ISNULL = smartconstantsPropertiesManager.getPropertyStr("DRIVER_ID_ISNULL");
		DRIVER_TYPE_ISNULL = smartconstantsPropertiesManager.getPropertyStr("DRIVER_TYPE_ISNULL");
		DRIVER_NAME_ISNULL = smartconstantsPropertiesManager.getPropertyStr("DRIVER_NAME_ISNULL");
		QUERY_DATA_SUCCESS = smartconstantsPropertiesManager.getPropertyStr("QUERY_DATA_SUCCESS");
		QUERY_DATA_FAILURE = smartconstantsPropertiesManager.getPropertyStr("QUERY_DATA_FAILURE");
		AUTHENTICATION_FAILURE = smartconstantsPropertiesManager.getPropertyStr("AUTHENTICATION_FAILURE");
		ADD_DRIVER_SUCCESS = smartconstantsPropertiesManager.getPropertyStr("ADD_DRIVER_SUCCESS");
		ADD_DRIVER_FAILURE = smartconstantsPropertiesManager.getPropertyStr("ADD_DRIVER_FAILURE");
		EDIT_DRIVER_SUCCESS = smartconstantsPropertiesManager.getPropertyStr("EDIT_DRIVER_SUCCESS");
		EDIT_DRIVER_FAILURE = smartconstantsPropertiesManager.getPropertyStr("EDIT_DRIVER_FAILURE");
		DELETE_DRIVER_SUCCESS = smartconstantsPropertiesManager.getPropertyStr("DELETE_DRIVER_SUCCESS");
		DELETE_DRIVER_FAILURE = smartconstantsPropertiesManager.getPropertyStr("DELETE_DRIVER_FAILURE");
		DUPLICATE_DRIVER_FAILURE = smartconstantsPropertiesManager.getPropertyStr("DUPLICATE_DRIVER_FAILURE");
		HWCLOUD_EQUIPMENT_URL = smartconstantsPropertiesManager.getPropertyStr("HWCLOUD_EQUIPMENT_URL");
	}
}
