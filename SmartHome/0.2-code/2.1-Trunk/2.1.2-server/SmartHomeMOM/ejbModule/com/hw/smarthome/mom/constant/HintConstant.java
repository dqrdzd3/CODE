package com.hw.smarthome.mom.constant;

public class HintConstant {
	public static final String GET_CUR_SENSORS_ERROR = "获取实时数据异常";

	public static final String GET_HOURS_ERROR = "获取小时历史异常";
	public static final String GET_DAYS_ERROR = "获取日历史异常";

	public static final String SET_HISTORY_ERROR = "保存历史异常";
	public static final String SET_HOURS_ERROR = "保存小时历史异常";
	public static final String SET_HOURS_MAX_ERROR ="判断24小时最大值 po是新数据异常";
	public static final String SET_DAYS_ERROR = "保存日历史异常";
	public static final String SET_DAYS_MAX_ERROR ="判断24小时最大值 po是新数据异常";
	
	public static final String PUSH_ALERT_ERROR = "推送报警异常";
	public static final String WS_RECEIVE_WARN = "Webservice接收异常";
	
	public static final String WEBSERVICE_INVOKING_ERROR = "调用Webservice异常";
	public static final String WEBSERVICE_INVOKING_COMPLET = "调用Webservice成功";
	public static final String WEBSERVICE_INVOKING_FAULT = "调用Webservice失败";
	
	public static final String SEND_JMS_FAIL = "发送JMS异常";
	public static final String RECEIVE_JMS_FAIL = "接受JMS异常";
	public static final String SEND_WS_FAIL = "发送Webservice异常";

	public static final String READ_CONF_FAIL = "读取配置文件异常";
	
}
