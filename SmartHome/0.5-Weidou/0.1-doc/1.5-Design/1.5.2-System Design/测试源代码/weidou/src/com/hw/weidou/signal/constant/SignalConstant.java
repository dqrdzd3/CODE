package com.hw.weidou.signal.constant;

/**
 * 信号解析常量
 * 
 * @author 曾凡
 * @time 2014年9月15日 下午3:16:43
 */
public class SignalConstant {

	// /** Byte信号取样截取时间的时间间隔:始+内容+止=10ms+8*10ms+10ms=100ms */
	// public static final int BYTE_TIME_SUB = 100;
	// /** Byte接受后间隔的时间 */
	// public static final int BYTE_TIME_SPACE = 0;
	// /** 一个Byte解析的总时间 */
	// public static final int BYTE_TIME = BYTE_TIME_SPACE
	// + BYTE_TIME_SUB;
	//
	// /** 帧信号取样截取时间的时间间隔:帧字节长度*单字节的处理时间=6*150ms = 900ms */
	// public static final int FRAME_TIME_SUB = 7 * BYTE_TIME;
	// /** 帧接受后间隔的时间 */
	// public static final int FRAME_TIME_SPACE = 500;
	// /** 一个数据帧的总时间 */
	// public static final int FRAME_TIME = FRAME_TIME_SPACE
	// + FRAME_TIME_SUB;
	/** 帧与帧之间的填充的内容（去噪过滤之后的模拟信号），用以区分截取每个数据帧 ，现在有6个10，一个开始位011 */
	public static final String FOURIER_SPACE = "10101010101011";
}
