package com.hw.weidou.signal;

import com.hw.weidou.signal.row.SignalBinary;
import com.hw.weidou.signal.sample.SignalFourier;

/**
 * 耳机信号调制解调器
 * 
 * @author 曾凡
 * @time 2014年9月18日 下午6:28:36
 */
public class SignalModel {
	public SignalModel() {
		/* 1. 启动采样程序 */
		SignalFourier sample = SignalFourier.getInstance();
		sample.startRecord();
		/* 2. 分析采样数据并将其转换为二进制代码 */
		SignalBinary binary = SignalBinary.getInstance();
		binary.startSampling();
		/* 3. 将二进制代码分解为帧 */

		/* 4. 将帧解析为byte数组 */

		/* 5. 将byte数组转换为po对象 */

	}
}
