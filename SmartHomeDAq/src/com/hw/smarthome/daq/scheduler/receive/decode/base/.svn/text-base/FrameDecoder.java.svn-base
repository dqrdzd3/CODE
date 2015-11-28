package com.hw.smarthome.daq.scheduler.receive.decode.base;

import java.util.Calendar;

import com.hw.smarthome.daq.po.DAqPo;
import com.hw.util.ByteUtils;

public abstract class FrameDecoder {
	public abstract void handleFrame(DAqPo frame);

	public void decodeFinish(DAqPo frame, int pos) {
		if (frame.isTpv()) {
			decodeAttach(frame, pos);
		}
	}

	/**
	 * 处理附加信息
	 * 
	 * @author 曾凡
	 * @param frame
	 * @param pos
	 * @return
	 * @time 2015年1月12日 下午2:33:23
	 */
	private void decodeAttach(DAqPo frame, int pos) {
		byte[] bytes = frame.getReceiveData();
		Calendar calendar = Calendar.getInstance();
		int year = ByteUtils.getBin(bytes, pos++);
		int month = ByteUtils.getBin(bytes, pos++);
		int date = ByteUtils.getBin(bytes, pos++);
		int hourOfDay = ByteUtils.getBin(bytes, pos++);
		int minute = ByteUtils.getBin(bytes, pos++);
		int second = ByteUtils.getBin(bytes, pos++);
		calendar.set(year, month, date, hourOfDay, minute,
				second);
		frame.setDate(calendar.getTime());
		frame.setDelaySecond(ByteUtils.getBin(bytes, pos));
	}
}
