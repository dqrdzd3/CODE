package com.hw.smarthome.daq.scheduler.receive.decode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN08;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.receive.decode.base.FrameDecoder;
import com.hw.util.ByteUtils;

/**
 * 应用层功能码AFN=0x08，数据上传，主动上传
 * 
 * @author 曾凡
 * @time 2015年1月6日 下午6:00:06
 */
public class FrameAFN08Decoder extends FrameDecoder {
	private static FrameAFN08Decoder instance = null;

	public static FrameAFN08Decoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN08Decoder();
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
	 * 08没有FN
	 * 
	 * @author 曾凡
	 * @param frame
	 * @param pos
	 * @return 返回最新的pos
	 * @time 2015年1月9日 上午9:54:06
	 */
	private int decode(DAqPo frame, int pos) {
		byte[] bytes = frame.getReceiveData();
		/* 获取传感器数量 */
		int sensorCount = ByteUtils.getBin(bytes, pos++);
		DataItemAFN08 tempItem = null;
		String unit = "";
		int pointerIndex = 0;
		int contentLength = 0;
		String content = "";
		List<DataItem> dataItems = new LinkedList<DataItem>();
		frame.setDataItems(dataItems);
		for (int i = 0; i < sensorCount; i++) {
			tempItem = new DataItemAFN08();
			tempItem.setSensorCount(ByteUtils.getBin(bytes,
					pos++));// 传感器的编号
			tempItem.setMedia(ByteUtils.getBinHexStr(bytes,
					pos++));// 传感器类型
			tempItem.setAddresss(ByteUtils.getBin(bytes, pos++));
			unit = ByteUtils.getHexWithFill(
					((bytes[pos]) & 0xF8) >> 3, 2); // 单位
			tempItem.setUnit(unit);
			pointerIndex = ByteUtils.getBin(bytes, pos++) & 0x07;
			tempItem.setStatus(ByteUtils.getBinHexStr(bytes,
					pos++));
			contentLength = ByteUtils.getBin(bytes, pos++);
			content = ByteUtils.getBins(bytes, pos,
					contentLength) + "";
			pos += contentLength;
			tempItem.setChanlvalue(ByteUtils.formatPoint(
					content, pointerIndex));
			dataItems.add(tempItem);

		}
		return pos;
	}
}
