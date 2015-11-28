package com.hw.smarthome.daq.constant;

public class FrameConstant {
	/** 通信秘钥 */
	public static final byte[] SEED = new byte[] { (byte) 0xCA,
			(byte) 0xC7, (byte) 0xBE, (byte) 0xEA, (byte) 0xBB,
			(byte) 0xB3, (byte) 0xD3, (byte) 0xEA, (byte) 0xBA,
			(byte) 0xA3, (byte) 0xB9, (byte) 0xA4, (byte) 0xC7,
			(byte) 0xE9, (byte) 0xBD, (byte) 0xB3 };

	public static final byte FRAME_HEAD = (byte) 0x68;
	public static final byte FRAME_CTRL = (byte) 0x40; // 需要组装的肯定是下发
	public static final int FRAME_END = (byte) 0x16;
	public static final int L_INDEX = 1;
	public static final int L_LENGTH = 2;
	public static final int CTRL_INDEX = 4;
	public static final int CTRL_LENGTH = 1;
	public static final int ADDR_INDEX = 5;
	public static final int ADDR_LENGTH = 6;
	public static final int AFN_INDEX = 11;
	public static final int AFN_LENGTH = 1;
	public static final int SEQ_INDEX = 12;
	public static final int SEQ_LENGTH = 1;
	public static final int FN_INDEX = 13;// 有的有有的没
	public static final int FN_LENGTH = 1;
	public static final int CONTENT_INDEX = 13;

	public static final int AFN_ACK = 0x00;// 应答帧
	public static final int AFN_RESET = 0x01;// 复位
	public static final int AFN_SET_CONFIG = 0x04;// 设置参数
	public static final int AFN_CTRL_CMD = 0x05;// 控制命令
	public static final int AFN_CTRL_DEVICE_UPLOAD = 0x07;// 被控设备主动上报
	public static final int AFN_ACTIVE_UPLOAD = 0x08;// 主动数据上传
	public static final int AFN_ASK_CONFIG_INFO = 0x09;// 请求配置及信息
	public static final int AFN_CTRL_DEVICE_TRANS = 0x10;// 被控设备数据传输
	public static final int AFN_QUERY_PARA = 0x0A;// 查询参数
	public static final int AFN_TRANS_FILE = 0x0F;// 文件传输
	/* 非电网协议内容 bgn */
	/** 分布式大气监测传感器主动上报 */
	public static final int AFN_ATMOSPHERIC_DETECTOR_UPLOAD = 0xF1;
	/* 非电网协议内容 end */
	
}
