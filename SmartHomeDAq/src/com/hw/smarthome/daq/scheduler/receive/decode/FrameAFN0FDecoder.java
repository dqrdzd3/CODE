package com.hw.smarthome.daq.scheduler.receive.decode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN0FFN01;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.receive.decode.base.FrameDecoder;
import com.hw.util.ByteUtils;

/**
 * 应用层功能码AFN=0x0F，文件传输
 * 
 * @author 曾凡
 * @time 2015年1月6日 下午6:00:06
 */
public class FrameAFN0FDecoder extends FrameDecoder {
	private static FrameAFN0FDecoder instance = null;

	public static FrameAFN0FDecoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN0FDecoder();
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
				pos -= (FrameConstant.CONTENT_INDEX + 1);
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
	 * 08没有FN
	 * 
	 * @author 曾凡
	 * @param frame
	 * @param pos
	 * @return 返回最新的pos
	 * @time 2015年1月9日 上午9:54:06
	 */
	private int decode01(List<DataItem> dataItems, DAqPo frame,
			int pos) {
		DataItemAFN0FFN01 tempItem = new DataItemAFN0FFN01();
		tempItem.setFn((byte) 0x01);
		byte[] bytes = frame.getReceiveData();
		/* 硬件类型 */
		String deviceType = ByteUtils.getBinHexStrs(bytes, pos,
				2);
		tempItem.setDeviceType(deviceType);
		pos += 2;
		/* 硬件版本 */
		String hwVersion = ByteUtils
				.getBinHexStrs(bytes, pos, 4);
		tempItem.setHwVersion(hwVersion);
		pos += 4;
		/* 软件版本 */
		String swVersion = ByteUtils
				.getBinHexStrs(bytes, pos, 4);
		tempItem.setSwVersion(swVersion);
		pos += 4;
		/* 传输类型 */
		String transDir = ByteUtils.getBinHexStr(bytes, pos++);
		tempItem.setTransDir(transDir);
		/* 传输状态 */
		String transState = ByteUtils.getBinHexStr(bytes, pos++);
		tempItem.setTransState(transState);
		/* 文件名 */
		String fileName = ByteUtils.getAscII(bytes, pos, 12);
		if (fileName != null && !"".equals(fileName)) {
			fileName = fileName.trim();
		}
		tempItem.setFileName(fileName);
		pos += 12;
		/* 总文件大小 */
		int fileSize = ByteUtils.getReverseBins(bytes, pos, 4);
		tempItem.setFileSize(fileSize);
		pos += 4;
		/* 包偏移地址 */
		int fileOffset = ByteUtils.getReverseBins(bytes, pos, 4);
		tempItem.setFileOffset(fileOffset);
		pos += 4;
		/* 包长度 */
		int dataLen = ByteUtils.getReverseBins(bytes, pos, 2);
		tempItem.setDataLen(dataLen);
		pos += 2;
		dataItems.add(tempItem);
		return pos;
	}
}
