package com.hw.smarthome.daq.scheduler.receive.decode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN05FN01;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.receive.decode.base.FrameDecoder;
import com.hw.util.ByteUtils;

/**
 * 应用层功能码AFN=0x05，控制设备
 * 
 * @author 曾凡
 * @time 2015年1月6日 下午6:00:06
 */
public class FrameAFN05Decoder extends FrameDecoder {
	private static FrameAFN05Decoder instance = null;

	public static FrameAFN05Decoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN05Decoder();
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
		DataItemAFN05FN01 tempItem = new DataItemAFN05FN01();
		tempItem.setFn((byte) 0x01);
		byte[] bytes = frame.getReceiveData();
		boolean success = (1 == ByteUtils.getBin(bytes, pos)) ? true
				: false;
		tempItem.setSuccess(success);
		dataItems.add(tempItem);
		return pos;
	}
}
