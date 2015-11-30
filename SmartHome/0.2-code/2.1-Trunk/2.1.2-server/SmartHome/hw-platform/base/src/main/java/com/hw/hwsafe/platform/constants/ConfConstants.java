/**
 * 文件名：ConfConstants.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.constants;


/**
 * <p>
 * 配置常量类
 * </p>
 */
public class ConfConstants {

	private static final BasePropertiesManager confPropertiesManager = ConfPropertiesManager.getInstance();
	
	// -------------------- CAS ------------------
	public static final String CAS_SERVER;
	public static final String CAS_CLIENT;
	public static final String CAS_CLIENT_SERVERNAME;
	
	// -------------------- UPLOAD -----------------
	public static final String FILE_REPOSITORY_PATH;
	public static final String FILE_REPOSITORY;
	public static final String MAX_FILE_SIZE;
	public static final String IMAGE_REPOSITORY_PATH;
	public static final String VEDIO_REPOSITORY_PATH;
	public static final String FILE_TOO_LARGE_MSG;
	public static final String CONTENT_TYPE_NOT_ALLOWED_MSG;
	
	// -------------------- SMS -------------------
	public static final String SMS_GATEWAY;
	public static final String SMS_PORT;
	public static final String SMS_MANUFACTURER;
	public static final String SMS_PIN;
	public static final Integer SMS_BAUDRATE;
	public static final boolean SMS_IN_BOUND;
	public static final boolean SMS_OUT_BOUND;
	public static final String SMS_MODE;
	public static final String HW_SMS_URL;
	public static final String HW_SMS_SERVER_NAME;
	public static final int HW_SMS_SERVER_PORT;
	public static final int HW_SMS_PLAT_PORT;
	
	// -------------------- MAIL --------------------
	public static final String MAIL_ADDRESS;
	public static final String MAIL_USERNAME;
	public static final String MAIL_PASSWORD;
	public static final boolean MAIL_VALIDATE;
	public static final String MAIL_HOST;
	public static final String MAIL_PORT;
	
	// -------------------- PUSHLET ------------------
	public static final Long PUSHLET_INTERVAL;
	
	//--------------------webservice------------------
	
	public static final String HWCLOUD_EQUIPMENT_URL;
	public static final String HWCLOUD_EQUIPMENT_NAMESPACE;
	public static final String HWCLOUD_ANDROIDPUSH_URL;
	
	public static final Integer HW_INTERVAL_TIME;
	
	//--------------------IOS push------------------
	public static final String IOS_CERTIFI_PATH;
	public static final String IOS_CERTIFI_PASS;
	//public static final String IOS_HOST;
	public static final Boolean IOS_PRODUCTION;
	
	//--------------------Android push------------------
	public static final Boolean ANDROID_PUSH_SWITCH;
	
	//--------------------net shop------------------
	public static final String WEIDOU_SHOP;
	public static final String WEB_DIY;
	public static final String WEB_BUSINESS;
	/**
	 * 设备状态
	 */
	public static final String ALERTTYPE00;
	public static final String ALERTTYPE01;
	public static final String ALERTTYPE02;
	public static final String ALERTTYPE03;
	public static final String ALERTTYPE04;
	public static final String ALERTTYPE05;
	public static final String ALERTTYPE06;
	public static final String ALERTTYPE07;
	
	public static final String PRODUCT_TOKEN;
	
	static {
		CAS_SERVER = confPropertiesManager.getPropertyStr("cas.server");
		CAS_CLIENT = confPropertiesManager.getPropertyStr("cas.client");
		CAS_CLIENT_SERVERNAME = confPropertiesManager.getPropertyStr("cas.client.serverName");
		
		FILE_REPOSITORY_PATH = confPropertiesManager.getPropertyStr("fileRepositoryPath");
		FILE_REPOSITORY = confPropertiesManager.getPropertyStr("fileRepository");
		MAX_FILE_SIZE = confPropertiesManager.getPropertyStr("maxFileSize");
		IMAGE_REPOSITORY_PATH = confPropertiesManager.getPropertyStr("imageRepositoryPath");
		VEDIO_REPOSITORY_PATH = confPropertiesManager.getPropertyStr("vedioRepositoryPath");
		FILE_TOO_LARGE_MSG = confPropertiesManager.getPropertyStr("struts.messages.error.file.too.large");
		CONTENT_TYPE_NOT_ALLOWED_MSG = confPropertiesManager.getPropertyStr("struts.messages.error.content.type.not.allowed");
		
		SMS_GATEWAY = confPropertiesManager.getPropertyStr("sms.gateway");
		SMS_PORT = confPropertiesManager.getPropertyStr("sms.port");
		SMS_MANUFACTURER = confPropertiesManager.getPropertyStr("sms.manufacturer");
		SMS_PIN = confPropertiesManager.getPropertyStr("sms.pin");
		SMS_BAUDRATE = confPropertiesManager.getPropertyInt("sms.baudrate");
		SMS_IN_BOUND = confPropertiesManager.getPropertyBoolean("sms.inBound");
		SMS_OUT_BOUND = confPropertiesManager.getPropertyBoolean("sms.outBound");
		SMS_MODE = confPropertiesManager.getPropertyStr("sms.mode");
		
		MAIL_ADDRESS = confPropertiesManager.getPropertyStr("mail.address");
		MAIL_USERNAME = confPropertiesManager.getPropertyStr("mail.username");
		MAIL_PASSWORD = confPropertiesManager.getPropertyStr("mail.password");
		MAIL_VALIDATE = confPropertiesManager.getPropertyBoolean("mail.validate");
		MAIL_HOST = confPropertiesManager.getPropertyStr("mail.host");
		MAIL_PORT = confPropertiesManager.getPropertyStr("mail.port");
		
		PUSHLET_INTERVAL = confPropertiesManager.getPropertyLong("pushlet.interval");
		
		HWCLOUD_EQUIPMENT_URL = confPropertiesManager.getPropertyStr("HWCLOUD_EQUIPMENT_URL");
		HWCLOUD_EQUIPMENT_NAMESPACE = confPropertiesManager.getPropertyStr("HWCLOUD_EQUIPMENT_NAMESPACE");
		HWCLOUD_ANDROIDPUSH_URL = confPropertiesManager.getPropertyStr("HWCLOUD_ANDROIDPUSH_URL");
		HW_SMS_URL = confPropertiesManager.getPropertyStr("HW_SMS_URL");
		HW_SMS_SERVER_NAME = confPropertiesManager.getPropertyStr("HW_SMS_SERVER_NAME");
		HW_SMS_SERVER_PORT = confPropertiesManager.getPropertyInt("HW_SMS_SERVER_PORT");
		HW_SMS_PLAT_PORT = confPropertiesManager.getPropertyInt("HW_SMS_PLAT_PORT");
		HW_INTERVAL_TIME = confPropertiesManager.getPropertyInt("HW_INTERVAL_TIME");
		
		ALERTTYPE00 = confPropertiesManager.getProperty("ALERTTYPE00");
		ALERTTYPE01 = confPropertiesManager.getProperty("ALERTTYPE01");
		ALERTTYPE02 = confPropertiesManager.getProperty("ALERTTYPE02");
		ALERTTYPE03 = confPropertiesManager.getProperty("ALERTTYPE03");
		ALERTTYPE04 = confPropertiesManager.getProperty("ALERTTYPE04");
		ALERTTYPE05 = confPropertiesManager.getProperty("ALERTTYPE05");
		ALERTTYPE06 = confPropertiesManager.getProperty("ALERTTYPE06");
		ALERTTYPE07 = confPropertiesManager.getProperty("ALERTTYPE07");
		
		IOS_CERTIFI_PATH = confPropertiesManager.getPropertyStr("certificatePath");
		IOS_CERTIFI_PASS = confPropertiesManager.getPropertyStr("certificatePassword");
		//IOS_HOST = confPropertiesManager.getPropertyStr("ios_host");
		IOS_PRODUCTION = confPropertiesManager.getPropertyBoolean("production");
		
		ANDROID_PUSH_SWITCH = confPropertiesManager.getPropertyBoolean("apppush_switch");
		
		WEIDOU_SHOP = confPropertiesManager.getPropertyStr("website");
		WEB_DIY = confPropertiesManager.getPropertyStr("website_diy");
		WEB_BUSINESS = confPropertiesManager.getPropertyStr("website_business");
		
		PRODUCT_TOKEN = confPropertiesManager.getProperty("product_token");
	}
	
}
