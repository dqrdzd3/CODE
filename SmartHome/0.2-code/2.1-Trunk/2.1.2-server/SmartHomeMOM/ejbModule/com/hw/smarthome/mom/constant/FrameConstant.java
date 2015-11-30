package com.hw.smarthome.mom.constant;

public class FrameConstant {

	public static final int AFN_ACK = 0x00;// 应答帧
	public static final int AFN_RESET = 0x01;// 复位
	public static final int AFN_SET_CONFIG = 0x04;// 设置参数
	public static final int AFN_CTRL_CMD = 0x05;// 控制命令
	public static final int AFN_ASK_UPLOAD = 0x07;// 请求数据上传
	public static final int AFN_ACTIVE_UPLOAD = 0x08;// 主动数据上传
	public static final int AFN_ASK_CONFIG_INFO = 0x09;// 请求配置及信息
	public static final int AFN_QUERY_PARA = 0x0A;// 查询参数
	public static final int AFN_TRANS_FILE = 0x0F;// 文件传输
}
