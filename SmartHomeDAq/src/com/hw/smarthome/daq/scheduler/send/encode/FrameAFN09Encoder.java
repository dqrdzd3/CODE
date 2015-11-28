package com.hw.smarthome.daq.scheduler.send.encode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN09FN01;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.send.encode.base.FrameEncoder;

public class FrameAFN09Encoder extends FrameEncoder {
	private static FrameAFN09Encoder instance = null;

	public static FrameAFN09Encoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN09Encoder();
		}
		return instance;
	}

	/**
	 * 
	 * @author 曾凡
	 * @param frame
	 * @return
	 * @throws Exception
	 * @time 2015年1月21日 下午7:49:15
	 */
	@Override
	public byte[] handleContent(DAqPo frame) throws Exception {

		int count = 1;
		List<DataItem> dataItem = frame.getDataItems();
		if (dataItem == null || dataItem.size() == 0) {
			throw new Exception(HintConstant.ENCODE_FAIL + ": "
					+ frame.toString());
		}
		count = dataItem.size();
		byte fnCount = (byte) (0xFF & count); // 定义长度
		byte fn = 0;
		List<byte[]> fnList = new LinkedList<byte[]>();
		for (DataItem item : dataItem) {
			fn = (byte) (0xFF & item.getFn()); // 判断FN
			switch (fn) {
			case 0x01:
				if (!(item instanceof DataItemAFN09FN01)) {
					throw new Exception(
							HintConstant.ENCODE_DATAITEM_FAIL
									+ ":" + frame.toString());
				}
				fnList.add(f1((DataItemAFN09FN01) item));
				break;
			default:
				throw new Exception(
						HintConstant.ENCODE_DATAITEM_FAIL + ":"
								+ frame.toString());
			}
		}
		int length = 0;
		for (byte[] bytes : fnList) {
			length += bytes.length;
		}
		byte[] resArray = new byte[length + 1];
		int pos = 0;
		resArray[pos++] = fnCount;
		for (byte[] bytes : fnList) {
			pos += bytes.length;
			System.arraycopy(bytes, 0, resArray, pos
					- bytes.length, bytes.length);
		}
		return resArray;
	}

	private byte[] f1(DataItemAFN09FN01 item) {
		byte[] res = new byte[1];
		res[0] = 0x01;
		return res;
	}
}
