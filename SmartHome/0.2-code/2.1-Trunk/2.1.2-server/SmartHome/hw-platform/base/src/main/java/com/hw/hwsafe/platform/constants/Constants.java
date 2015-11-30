/**
 * 文件名：Constants.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.constants;

/**
 * 常量类
 * 
 * @author 马宁
 * @创建时间: 2013-04-02 10:20
 */
public class Constants {

	private static BasePropertiesManager constantsPropertiesManager = ConstansPropertiesManager.getInstance();
	
	/**
	 * 用户session的key
	 */
	public static final String USER_SESSION_KEY;
	
	/**
	 * 用户ID
	 */
	public static final String USER_ID;
	
	/**
	 * 用户模块权限
	 */
	public static final String ALL_USER_PERM;
	/**
	 * 用户操作权限
	 */
	public static final String ALL_USER_OPER;
	/**
	 * 用户信息是否被覆盖
	 */
	public static String IS_COVER;
	/**
	 * 用户类型
	 */
	public static final String ENT;//企业
	public static final String GOV;//政府
	public static final String SYS;//系统
	/**
	 * 超级管理员职位
	 */
	public static final String ADMINISTRATORS;
	/**
	 * 列表页大小
	 */
	public static final int PAGE_SIZE;
	
	public static final int ACTION_VIEW ;
	public static final int ACTION_ADD;
	public static final int ACTION_DELETE;
	public static final int ACTION_EDIT;
	
	/**
	 * 返回消息类型
	 */
	public static final int MSG_NONE;
	public static final int MSG_OK;
	public static final int MSG_INFO;
	public static final int MSG_QUESTION;
	public static final int MSG_WARNING;
	public static final int MSG_ERROR;
	
	/**
	 * 返回的消息
	 */
	public static final String QUERY_FAILED_MSG;
	public static final String ADD_SUCCESS_MSG;
	public static final String ADD_SUCCESS_CONTINUE_MSG;
	public static final String ADD_FAILED_MSG;
	public static final String UPDATE_SUCCESS_MSG;
	public static final String UPDATE_FAILED_MSG;
	public static final String DEL_SUCCESS_MSG;
	public static final String DEL_SUCCESS_MSG_PRE;
	public static final String DEL_SUCCESS_MSG_SUF;
	public static final String DEL_FAILED_MSG;
	public static final String EMPTY_DATA_MSG;
	public static final String CONVERT_FAILED_MSG;
	public static final String IDENTIFY_FAILED_MSG;
	public static final String OPREATE_SUCCESS_MSG;
	public static final String OPREATE_FAILED_MSG;
	public static final String DATA_ABNORMAL_MSG;
	public static final String SUBMIT_SUCCESS_MSG;
	public static final String SUBMIT_FAILED_MSG;
	
	/**
	 * 返回的标题
	 */
	public static final String ERROR_TITLE;
	
	/**
	 * 返回对象
	 */
	public static final  String JSON_MSG;
	
	public static final String SERVER_MSG;
	
	/**
	 * job所用
	 * */
	public static final String EXAM_JOB;
	public static final String EXAM_JOBGROUP;
	public static final String EXAM_TRIGGER;
	public static final String EXAM_TRIGGERGROUP;
	
	/**
	 * service层出错时，设置错误消息，action层取出设置错误提示信息
	 */
	public static String RETURN_MSG;
	
	/**
	 * 危险源辨识标志
	 */
	public static final String DANGER_NO;
	public static final String DANGER_OK;
	/**
	 * 危险源辨识消息标志
	 */
	public static final Integer Message_NO;
	public static final Integer Message_OK;
	
	/**
	 * 用户最后一次访问ip和时间
	 */
	public static final String LAST_ACCESS_IP;
	public static final String LAST_ACCESS_DATETIME;
	
	/**
	 * 危险源登记模块 自定义提示消息
	 */
	public static final String NAME_EXITS_MSG;
	public static final String AUDIT_MSG;
	public static final String RECORD_NOTEXITS_MSG;
	public static final String PARENT_RECORD_NOTEXITS_MSG;
	public static final String REPORTED_MSG;
	public static final String NUM_EXITS_MSG;
	

	

	
	static{
		USER_SESSION_KEY = constantsPropertiesManager.getPropertyStr("USER_SESSION_KEY");
		USER_ID = constantsPropertiesManager.getPropertyStr("USER_ID");
		ALL_USER_PERM =  constantsPropertiesManager.getPropertyStr("ALL_USER_PERM");
		ALL_USER_OPER =  constantsPropertiesManager.getPropertyStr("ALL_USER_OPER");
		IS_COVER =  constantsPropertiesManager.getPropertyStr("IS_COVER");
		ENT =  constantsPropertiesManager.getPropertyStr("ENT");
		GOV =  constantsPropertiesManager.getPropertyStr("GOV");
		SYS =  constantsPropertiesManager.getPropertyStr("SYS");
		ADMINISTRATORS =  constantsPropertiesManager.getPropertyStr("ADMINISTRATORS");
		PAGE_SIZE =  constantsPropertiesManager.getPropertyInt("PAGE_SIZE");
		ACTION_VIEW =  constantsPropertiesManager.getPropertyInt("ACTION_VIEW");
		ACTION_ADD =  constantsPropertiesManager.getPropertyInt("ACTION_ADD");
		ACTION_DELETE =  constantsPropertiesManager.getPropertyInt("ACTION_DELETE");
		ACTION_EDIT =  constantsPropertiesManager.getPropertyInt("ACTION_EDIT");
		MSG_NONE =  constantsPropertiesManager.getPropertyInt("MSG_NONE");
		MSG_OK =  constantsPropertiesManager.getPropertyInt("MSG_OK");
		MSG_INFO =  constantsPropertiesManager.getPropertyInt("MSG_INFO");
		MSG_QUESTION =  constantsPropertiesManager.getPropertyInt("MSG_QUESTION");
		MSG_WARNING =  constantsPropertiesManager.getPropertyInt("MSG_WARNING");
		MSG_ERROR =  constantsPropertiesManager.getPropertyInt("MSG_ERROR");
		QUERY_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("QUERY_FAILED_MSG");
		ADD_SUCCESS_MSG =  constantsPropertiesManager.getPropertyStr("ADD_SUCCESS_MSG");
		ADD_SUCCESS_CONTINUE_MSG =  constantsPropertiesManager.getPropertyStr("ADD_SUCCESS_CONTINUE_MSG");
		ADD_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("ADD_FAILED_MSG");
		UPDATE_SUCCESS_MSG =  constantsPropertiesManager.getPropertyStr("UPDATE_SUCCESS_MSG");
		UPDATE_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("UPDATE_FAILED_MSG");
		DEL_SUCCESS_MSG =  constantsPropertiesManager.getPropertyStr("DEL_SUCCESS_MSG");
		DEL_SUCCESS_MSG_PRE =  constantsPropertiesManager.getPropertyStr("DEL_SUCCESS_MSG_PRE");
		DEL_SUCCESS_MSG_SUF =  constantsPropertiesManager.getPropertyStr("DEL_SUCCESS_MSG_SUF");
		DEL_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("DEL_FAILED_MSG");
		EMPTY_DATA_MSG =  constantsPropertiesManager.getPropertyStr("EMPTY_DATA_MSG");
		CONVERT_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("CONVERT_FAILED_MSG");
		IDENTIFY_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("IDENTIFY_FAILED_MSG");
		OPREATE_SUCCESS_MSG =  constantsPropertiesManager.getPropertyStr("OPREATE_SUCCESS_MSG");
		OPREATE_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("OPREATE_FAILED_MSG");
		DATA_ABNORMAL_MSG =  constantsPropertiesManager.getPropertyStr("DATA_ABNORMAL_MSG");
		SUBMIT_SUCCESS_MSG =  constantsPropertiesManager.getPropertyStr("SUBMIT_SUCCESS_MSG");
		SUBMIT_FAILED_MSG =  constantsPropertiesManager.getPropertyStr("SUBMIT_FAILED_MSG");
		ERROR_TITLE =  constantsPropertiesManager.getPropertyStr("ERROR_TITLE");
		JSON_MSG =  constantsPropertiesManager.getPropertyStr("JSON_MSG"); 
		SERVER_MSG =  constantsPropertiesManager.getPropertyStr("SERVER_MSG");
		EXAM_JOB =  constantsPropertiesManager.getPropertyStr("EXAM_JOB");
		EXAM_JOBGROUP =  constantsPropertiesManager.getPropertyStr("EXAM_JOBGROUP");
		EXAM_TRIGGER =  constantsPropertiesManager.getPropertyStr("EXAM_TRIGGER");
		EXAM_TRIGGERGROUP =  constantsPropertiesManager.getPropertyStr("EXAM_TRIGGERGROUP");
		RETURN_MSG =  constantsPropertiesManager.getPropertyStr("RETURN_MSG");
		DANGER_NO =  constantsPropertiesManager.getPropertyStr("DANGER_NO");
		DANGER_OK =  constantsPropertiesManager.getPropertyStr("DANGER_OK");
		Message_NO =  constantsPropertiesManager.getPropertyInt("Message_NO");
		Message_OK =  constantsPropertiesManager.getPropertyInt("Message_OK");
		LAST_ACCESS_IP =  constantsPropertiesManager.getPropertyStr("LAST_ACCESS_IP");
		LAST_ACCESS_DATETIME =  constantsPropertiesManager.getPropertyStr("LAST_ACCESS_DATETIME");
		
		// 危险源登记自定义提示消息
		NAME_EXITS_MSG = constantsPropertiesManager.getProperty("NAME_EXITS_MSG");
		AUDIT_MSG = constantsPropertiesManager.getProperty("AUDIT_MSG");
		RECORD_NOTEXITS_MSG = constantsPropertiesManager.getProperty("RECORD_NOTEXITS_MSG");
		PARENT_RECORD_NOTEXITS_MSG = constantsPropertiesManager.getProperty("PARENT_RECORD_NOTEXITS_MSG");
		REPORTED_MSG = constantsPropertiesManager.getProperty("REPORTED_MSG");
		NUM_EXITS_MSG = constantsPropertiesManager.getProperty("NUM_EXITS_MSG");
		
		


	
	}

}
