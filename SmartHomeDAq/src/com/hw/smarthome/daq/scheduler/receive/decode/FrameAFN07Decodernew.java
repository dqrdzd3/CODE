package com.hw.smarthome.daq.scheduler.receive.decode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.dic.SensorDictionary;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN07FN01new;
import com.hw.smarthome.daq.po.DataItemAFN07FN02new;
import com.hw.smarthome.daq.po.DataItemAFN07FN03;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.receive.decode.base.FrameDecoder;
import com.hw.util.ByteUtils;

/**
 * 应用层功能码AFN=0x07，被控设备主动上报
 * 
 * @author 王珂
 * @time 2015/10/20
 */
public class FrameAFN07Decodernew extends FrameDecoder {
	private static FrameAFN07Decodernew instance = null;

	public static FrameAFN07Decodernew getInstance() {
		if (instance == null) {
			instance = new FrameAFN07Decodernew();
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
	 * 
	 * @author 王珂
	 * @param frame
	 * @param pos
	 * @return
	 * @time 2015/10/20
	 */
	private int decode(DAqPo frame, int pos) {
		byte[] bytes = frame.getReceiveData();
		/* 获取Fn的数量 */
		int sensorCount = ByteUtils.getBin(bytes, pos++);
		int fn = 0;
		List<DataItem> dataItems = new LinkedList<DataItem>();
		frame.setDataItems(dataItems);
		String mac = "";
		for (int i = 0; i < sensorCount; i++) {
			fn = ByteUtils.getBin(bytes, pos++);
			switch (fn) {
			case 0x01:
				DataItemAFN07FN01new tempItem1 = new DataItemAFN07FN01new();
				pos = decodeFn01(tempItem1, bytes, pos);
				mac = tempItem1.getSerialNum();
				tempItem1.setSensorId(mac);
				/* Fn1可以单独使用，Fn2必须配合Fn1 */
				// frame.setSensorId(tempItem1.getSerialNum());
				dataItems.add(tempItem1);
				break;
			case 0x02:
				pos = decodeFn02(dataItems, bytes, pos);
				break;
			case 0x03:
				pos = decodeFn03(dataItems, bytes, pos);
				break;
			}
		}
		return pos;
	}

	private int decodeFn01(DataItemAFN07FN01new tempItem, byte[] bytes, int pos) {
		/* 设备类型 */
		String deviceType = ByteUtils.getBinHexStrs(bytes, pos, 2);
		tempItem.setDeviceType(deviceType);
		pos += 2;
		/* 硬件版本 */
		String hardVer = ByteUtils.getBinHexStrs(bytes, pos, 4);
		tempItem.setHardVer(hardVer);
		pos += 4;
		/* 软件版本 */
		String softVer = ByteUtils.getBinHexStrs(bytes, pos, 4);
		tempItem.setSoftVer(softVer);
		pos += 4;
		/* 序列号(MAC) */
		String serialNum = ByteUtils.getBinHexStrs(bytes, pos, 6);
		tempItem.setSerialNum(serialNum);
		pos += 6;
		/* token */
		String token = ByteUtils.getBinHexStrs(bytes, pos, 2);
		tempItem.setToken(token);
		pos += 2;
		/* 保留6byte */
		String reserve6 = ByteUtils.getBinHexStrs(bytes, pos, 6);
		tempItem.setReserve6(reserve6);
		pos += 6;
		/* 开关数量 */
		int switchAmount = ByteUtils.getBin(bytes, pos++);
		tempItem.setSwitchAmount(switchAmount);
		/* 串口数量 */
		int portAmount = ByteUtils.getBin(bytes, pos++);
		tempItem.setPortAmount(portAmount);
		/* 控制模式 */
		int controlMode = ByteUtils.getBin(bytes, pos++);
		tempItem.setControlMode(controlMode);
		/* 保留1byte */
		String reserve1 = ByteUtils.getBinHexStrs(bytes, pos, 1);
		tempItem.setReserve1(reserve1);
		pos += 1;
		return pos;
	}

	/**
	 * 
	 * @author 王珂
	 * @param tempItem
	 * @param bytes
	 * @param pos
	 * @return
	 * @time 2015/10/20
	 */
	private int decodeFn02(List<DataItem> dataItems, byte[] bytes, int pos) {
		/* 控制量数量 */
		int controlAmountNum = ByteUtils.getBin(bytes, pos++);
		DataItemAFN07FN02new tempItem = null;
		for (int i = 0; i < controlAmountNum; i++) {
			tempItem = new DataItemAFN07FN02new();
			/* 控制对象 */
			int controlObject = ByteUtils.getBin(bytes, pos++);
			tempItem.setControlObject(controlObject);
			/* 控制量类型 */
			String controlType = ByteUtils.getBinHexStrs(bytes, pos, 2);
			pos += 2;
			tempItem.setControlType(controlType);
			/* 控制量范围下界 */
			int controlAmountBound = ByteUtils.getBins(bytes, pos, 4);
			tempItem.setControlAmountBound(controlAmountBound);
			pos += 4;
			/* 控制量范围上界 */
			int controlAmountLowBound = ByteUtils.getBins(bytes, pos, 4);
			tempItem.setControlAmountLowBound(controlAmountLowBound);
			pos += 4;
			/* 小数位+单位 3bit+5bit */
			byte pointerUnit = bytes[pos++];
			/*单位*/
			String unit = SensorDictionary.UNIT_DICTIONARY.get((0x1F & pointerUnit));
			tempItem.setUnit(unit);	
			/*小数位*/
			int pointer = (int)(0x07 & ((0xE0 & pointerUnit) >> 5));
			tempItem.setPointer(pointer);
			pos += 1;
			/* 控制值 */
			String controlValue = ByteUtils.getBinHexStrs(bytes, pos, 4);
			/* 控制量 */
			String controlAmount = ByteUtils.formatPoint(controlValue, pointer);
			tempItem.setControlAmount(controlAmount);
			pos += 4;/*5 单位的那一位加过了*/
			dataItems.add(tempItem);
		}

		return pos;
	}

	private int decodeFn03(List<DataItem> dataItems, byte[] bytes, int pos) {
		/* 控制量数量 */
		int sensorNum = ByteUtils.getBin(bytes, pos++);
		DataItemAFN07FN03 tempItem = null;
		for (int i = 0; i < sensorNum; i++) {
			tempItem = new DataItemAFN07FN03();
			/* 传感器类型 */
			String sensorType = ByteUtils.getBinHexStr(bytes, pos++);
			tempItem.setSensorType(sensorType);
			/* 小数位+单位 3bit+5bit */
			byte pointerUnit = bytes[pos++];
			/*单位*/
			String unit = SensorDictionary.UNIT_DICTIONARY.get((0x1F & pointerUnit));
			tempItem.setUnit(unit);			
			/*小数位*/
			int pointer = (int)(0x07 & ((0xE0 & pointerUnit) >> 5));
			tempItem.setPointer(pointer);
			pos += 1;
			/* 控制值 */
			String controlValue = ByteUtils.getBinHexStrs(bytes, pos, 4);
			/* 控制量*/
			String controlAmount = ByteUtils.formatPoint(controlValue, pointer);
			tempItem.setControlAmount(controlAmount);
			pos += 4;
			dataItems.add(tempItem);
		}
		return pos;
	}
}
