package com.hw.weidou.constant;

/**
 * 信号解析常量
 * 
 * @author 曾凡
 * @time 2014年9月15日 下午3:16:43
 */
public class SignalConstant {

	/** 默认是国内版本 */
	public static boolean IS_INTERNATIONAL_MIC = false;
	/** MIC口采样率为 8000 */
	public static final int SAMPLING_8K = 8000;
	/** MIC口采样率为 16000 */
	public static final int SAMPLING_16K = 16000;
	/** MIC口采样率 */
	public static int SAMPLE_RATE_IN_HZ = SAMPLING_8K;

	/** 帧与帧之间的填充的内容（去噪过滤之后的模拟信号），用以区分截取每个数据帧 ，现在有6个10，一个开始位011 */
	private static final String FOURIER_8K_SPACE = "10101010101011";

	public static String FOURIER_SPACE = FOURIER_8K_SPACE;

	private static final int FOURIER_8K_BINARY_LENGTH = 20;
	/** 一个byte波形的长度 */
	public static int FOURIER_BINARY_LENGTH = FOURIER_8K_BINARY_LENGTH;
	/** 取样记录间隔时间 */
	public static final int SAMPLING_SPACE_TIME = 1;

	/** 过滤阀值上极限 */
	public static final int FILTER_THRESHOLD_UP = 20000;

	/** 过滤阀值下极限 */
	public static final int FILTER_THRESHOLD_DOWN = -20000;

	/** 采样记录内容长度，它是解析的原材料 */
	public static final int SAMPLING_CONTENT_LENGTH = 2500;

}
