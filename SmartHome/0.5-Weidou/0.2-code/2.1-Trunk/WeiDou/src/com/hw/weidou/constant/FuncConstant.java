package com.hw.weidou.constant;

/**
 * 功能码枚举
 * 
 * @author 曾凡
 * @time 2014年10月11日 下午1:55:30
 */
public class FuncConstant {
	/** 威豆版本号 */
	public static final byte WEIDOU_VERSION = 0x16;
	/** 传输数据 */
	public static final byte TESTDATA_CONTENT = 0x15;
	/** 电量数据 */
	public static final byte POWER_DATA = 0x0A;
	/** 威豆使用时间 */
	public static final byte USED_TIME = 0x0B;

	/** 准备进行酒精测试 */
	public static final byte ALCOHOL_READY = 0x19;
	/** 酒精检测中 */
	public static final byte ALCOHOL_PROCESS = 0x14;

}
