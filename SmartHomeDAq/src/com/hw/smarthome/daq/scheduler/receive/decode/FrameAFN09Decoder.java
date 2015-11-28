package com.hw.smarthome.daq.scheduler.receive.decode;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN09FN01;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.receive.decode.base.FrameDecoder;
import com.hw.util.ByteUtils;

/**
 * 应用层功能码AFN=0x09，请求终端配置
 * 
 * @author 曾凡
 * @time 2015年1月6日 下午6:00:06
 */
public class FrameAFN09Decoder extends FrameDecoder {
	private static FrameAFN09Decoder instance = null;

	public static FrameAFN09Decoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN09Decoder();
		}
		return instance;
	}

	@Override
	public void handleFrame(DAqPo frame) {
		List<DataItem> dataItems = new LinkedList<DataItem>();
		int pos = FrameConstant.CONTENT_INDEX;
		byte[] bytes = frame.getReceiveData();
		/* 获取传感器数量 */
		int fnCount = ByteUtils.getBin(bytes, pos++);
		for (int i = 0; i < fnCount; i++) {
			pos += decode(dataItems, frame, pos);
			if (i == 0) {
				pos -= FrameConstant.CONTENT_INDEX;
			}
		}
		frame.setDataItems(dataItems);
		decodeFinish(frame, pos);
	}

	private int decode(List<DataItem> dataItems, DAqPo frame,
			int pos) {

		byte[] bytes = frame.getReceiveData();
		int fn = ByteUtils.getBin(bytes, pos++);
		switch (fn) {
		case 0x01:
			pos = decode01(dataItems, frame, pos);
			break;
		}
		return pos;
	}

	/**
	 * @author 曾凡
	 * @param frame
	 * @param pos
	 * @return 返回最新的pos
	 * @time 2015年1月9日 上午9:54:06
	 */
	private int decode01(List<DataItem> dataItems, DAqPo frame,
			int pos) {
		DataItemAFN09FN01 tempItem = new DataItemAFN09FN01();
		tempItem.setFn((byte) 0x01);
		byte[] bytes = frame.getReceiveData();
		/* 设备型号 */
		String deviceType = ByteUtils.getBinHexStrs(bytes, pos,
				2);
		tempItem.setDeviceType(deviceType);
		pos += 2;
		/* 序列号 */
		String serialNum = ByteUtils
				.getBinHexStrs(bytes, pos, 6);
		tempItem.setSerialNum(serialNum);
		pos += 6;
		/* 设备ID号 */
		String deviceID = ByteUtils.getBinHexStrs(bytes, pos, 6);
		tempItem.setDeviceID(deviceID);
		pos += 6;
		/* 硬件版本 */
		String hardVer = ByteUtils.getBinHexStrs(bytes, pos, 4);
		tempItem.setHardVer(hardVer);
		pos += 4;
		/* 软件版本 */
		String softVer = ByteUtils.getBinHexStrs(bytes, pos, 4);
		tempItem.setSoftVer(softVer);
		pos += 4;
		/* 生产日期 */
		Calendar productDate = Calendar.getInstance();
		int year = ByteUtils.getBin(bytes, pos++);
		int month = ByteUtils.getBin(bytes, pos++);
		int date = ByteUtils.getBin(bytes, pos++);
		int hourOfDay = ByteUtils.getBin(bytes, pos++);
		productDate.set(year, month, date, hourOfDay, 0);
		tempItem.setProductDate(productDate.getTime());
		dataItems.add(tempItem);
		return pos;
	}
}
