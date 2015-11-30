package com.hw.smarthome.daq.scheduler.send.encode;

import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.scheduler.send.encode.base.FrameEncoder;

public class FrameAFN00Encoder extends FrameEncoder {
	private static FrameAFN00Encoder instance = null;

	public static FrameAFN00Encoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN00Encoder();
		}
		return instance;
	}

	@Override
	public byte[] handleContent(DAqPo frame) {
		int pos = 0;
		byte[] resArray = new byte[2];
		resArray[pos++] = (byte) 0x01; // FN
		/* 指针要引入下一位 */
		resArray[pos++] = (byte) 0x01; // 全部应答
		return resArray;
	}
}
