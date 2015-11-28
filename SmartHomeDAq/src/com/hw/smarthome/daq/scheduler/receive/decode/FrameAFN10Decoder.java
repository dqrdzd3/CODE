package com.hw.smarthome.daq.scheduler.receive.decode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN10FN01;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.receive.decode.base.FrameDecoder;
import com.hw.util.ByteUtils;

/**
 * 应用层功能码AFN=0x10，被控设备透传
 * 
 * @author 曾凡
 * @time 2015年1月6日 下午6:00:06
 */
public class FrameAFN10Decoder extends FrameDecoder {
	private static FrameAFN10Decoder instance = null;

	public static FrameAFN10Decoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN10Decoder();
		}
		return instance;
	}

	@Override
	public void handleFrame(DAqPo frame) {
		int pos = FrameConstant.CONTENT_INDEX;
		pos = decode(frame, pos);
		decodeFinish(frame, pos);
	}

	/**
	 * 有Fn2就必须有Fn1
	 * 
	 * @author 曾凡
	 * @param frame
	 * @param pos
	 * @return
	 * @time 2015年4月14日 上午11:14:18
	 */
	private int decode(DAqPo frame, int pos) {
		byte[] bytes = frame.getReceiveData();
		/* 获取Fn的数量 */
		int sensorCount = ByteUtils.getBin(bytes, pos++);
		int fn = 0;
		List<DataItem> dataItems = new LinkedList<DataItem>();
		frame.setDataItems(dataItems);
		for (int i = 0; i < sensorCount; i++) {
			fn = ByteUtils.getBin(bytes, pos++);
			switch (fn) {
			case 0x01: {
				List<DataItemAFN10FN01> list = new LinkedList<DataItemAFN10FN01>();
				decodeFn01(list, bytes, pos);
				dataItems.addAll(list);
			}

				break;
			}
		}
		return pos;
	}

	/**
	 * 格式与AFN07的Fn02相同
	 * 
	 * @author 曾凡
	 * @param tempItem
	 * @param bytes
	 * @param pos
	 * @return
	 * @time 2015年4月14日 上午11:43:19
	 */
	private int decodeFn01(List<DataItemAFN10FN01> dataItems,
			byte[] bytes, int pos) {

		DataItemAFN10FN01 tempItem10 = null;
		/* token */
		String token = ByteUtils.getBinHexStrs(bytes, pos, 2);
		pos += 2;
		/* 开关数量 */
		int switchAmount = ByteUtils.getBin(bytes, pos++);
		for (int i = 0; i < switchAmount; i++) {
			tempItem10 = new DataItemAFN10FN01();
			tempItem10.setToken(token);
			tempItem10.setSwitchAmount(switchAmount);
			/* 开关序号 */
			int switchNum = ByteUtils.getBin(bytes, pos++);
			tempItem10.setSwitchNum(switchNum);
			/* 开关类型 */
			String switchType = ByteUtils.getBinHexStr(bytes,
					pos++);
			tempItem10.setSwitchType(switchType);
			/* 开关状态 */
			String switchState = ByteUtils.getBinHexStr(bytes,
					pos++);
			tempItem10.setSwitchState(switchState);
			/* 保留2字节 */
			String reserve2 = ByteUtils.getBinHexStrs(bytes, pos, 2);
			pos += 2;
			tempItem10.setReserve2(reserve2);
			dataItems.add(tempItem10);
		}

		return pos;
	}

}
