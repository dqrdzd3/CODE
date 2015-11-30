package com.hw.smarthome.daq.constant;

public class HintConstant {

	public static final String READ_CONF_FAIL = "读取配置文件异常";

	public static final String UDP_LINK_CREATE_FAIL = "创建UDP服务异常";
	public static final String UDP_LINK_CREATE_START = "开始创建UDP服务";

	public static final String UDP_PORT_FAIL = "UDP端口异常";
	public static final String UDP_SOCKET_FAIL = "UDP连接关闭异常";
	public static final String UDP_LINK_RECEIVE_FAIL = "接受UDP服务异常";

	public static final String FRONT_RECEIVE_HANDLER_FAIL = "前置机处理报文异常";
	public static final String FRONT_RECEIVE_HANDLER_DEBUG = "解码后的内容为";

	public static final String FRONT_SEND_DECODE_FAIL = "前置机解析报文异常";
	public static final String FRONT_SEND_DECODE_FAIL_MIN = FRONT_SEND_DECODE_FAIL
			+ "上报报文长度小于16";
	public static final String FRONT_SEND_HANDLER_FAIL = "前置机处理报文异常";
	public static final String FRONT_SEND_UDP_FAIL = "前置机发送失败";
	public static final String FRONT_SEND_HANDLER_DEBUG = "发送UDP";

	public static final String FRONT_HANDLER_CHKSUM_FAIL = "CheckSum校验错误";
	public static final String FRONT_HANDLER_SENSOR_ID_FAIL = "获取设备ID失败";

	public static final String SCHEDULER_HANDLER_FAIL = "任务调度处理报文异常";
	public static final String SCHEDULER_SEND_HANDLER_FAIL = "拼装待发送帧异常";
	public static final String SEND_JMS_FAIL = "发送JMS异常";
	public static final String SEND_WS_FAIL = "发送Webservice异常";
	public static final String SEND_WS_RECEIVE = "接收Webservice";

	public static final String SEND_WS_SUCCESS = "发送WebService中";
	public static final String SEND_JMS_SUCCESS = "发送JMS成功";

	public static final String ENCODE_FAIL = "组帧失败";
	public static final String ENCODE_DATAITEM_FAIL = "服务端发送了错误的DataItem";

	public static final String SOCKET_SERVER_ERROR = "Sokcet接受连接异常";
	public static final String SOCKET_CLIENT_ERROR = "Sokcet发送连接异常";
	public static final String SOCKET_CLIENT_NULL_WARN = "Socket未建立连接";

	public static final String SOCKET_SERVER_HANDLE_ERROR = "Sokcet接受处理异常";
	public static final String SOCKET_CLIENT_HANDLE_ERROR = "Sokcet发送处理异常";
	public static final String SOCKET_CLIENT_LINK_ERROR = "Sokcet没有主站去创建连接";

	public static final String FRONT_DEVICE_NOT_LINKED = "这台设备没有连接过前置机";
	public static final String UPDATE_FILE_NOT_FOUND = "没有查询到升级文件";

	public static final String UPDATE_FILE_LIST_SCUESS = "更新(升级)文件列表成功";
	public static final String UPDATE_FILE_LIST_FAIL = "更新(升级)文件列表失败";

	public static final String WEBSERVICE_INVOKING_ERROR = "调用Webservice异常";
	public static final String WEBSERVICE_INVOKING_COMPLET = "调用Webservice结束";
	public static final String WEBSERVICE_INVOKING_FAULT = "调用Webservice失败";

	public static final String ATMOSPHERIC_DETECTOR_HANDLER_FAULT = "分布式大气监测异常";
}
