package com.hw.smarthome.daq.scheduler.send.encode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN10FN01;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.send.encode.base.FrameEncoder;
import com.hw.util.ByteUtils;

public class FrameAFN10Encoder extends FrameEncoder {
	private static FrameAFN10Encoder instance = null;

	public static FrameAFN10Encoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN10Encoder();
		}
		return instance;
	}

	/**
	 * 看不懂是sb
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
		List<DataItem> dataItems = frame.getDataItems();
		if (dataItems == null || dataItems.size() == 0) {
			throw new Exception(HintConstant.ENCODE_FAIL + ": "
					+ frame.toString());
		}
		byte fn = 0;
		List<byte[]> fnList = new LinkedList<byte[]>();
		/* Fn1的数量 */
		List<DataItemAFN10FN01> fn01Items = new LinkedList<DataItemAFN10FN01>();
		List<DataItem> restDataItems = new LinkedList<DataItem>();
		for (DataItem dataItem : dataItems) {
			if (dataItem instanceof DataItemAFN10FN01) {
				fn01Items.add((DataItemAFN10FN01) dataItem);
			} else {
				restDataItems.add(dataItem);
			}
		}

		count = restDataItems.size()
				+ (fn01Items.size() == 0 ? 0 : 1);
		byte fnCount = (byte) (0xFF & count); // 定义长度

		for (DataItem item : restDataItems) {
			/* TODO 有的话添加到这里 */
		}
		if (fn01Items.size() > 0) {
			fnList.add(f1(fn01Items));
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

	/**
	 * IO口命令下发
	 * 
	 * @author 曾凡
	 * @param item
	 * @return
	 * @throws Exception
	 * @time 2015年4月14日 下午3:25:17
	 */
	private byte[] f1(List<DataItemAFN10FN01> fn01Items)
			throws Exception {
		int pos = 0;
		/* 4（fn+token+开关数量）+2(上报的开关数量)*5（一个fn的长度） */
		byte[] res = new byte[4+fn01Items.size()*5];
		res[pos++] = 0x01; // Fn1

		int count = 0;
		for (DataItemAFN10FN01 item : fn01Items) {
			if (count == 0) {
				byte[] token = ByteUtils.toBytes(item
						.getToken()==null?"FFFF":item
								.getToken());
				System.arraycopy(token, 0, res, pos, 2);
				pos += 2;
				res[pos++] = ByteUtils.getByte(fn01Items.size());
			}
			res[pos++] = ByteUtils.getByte(item.getSwitchNum());
			res[pos++] = ByteUtils.toByteFromHex(item
					.getSwitchType());
			res[pos++] = ByteUtils.toByteFromHex(item
					.getSwitchState());
			byte[] reserve2 = ByteUtils.toBytes(item
					.getReserve2()==null?"FFFF":item
							.getReserve2());
			System.arraycopy(reserve2, 0, res, pos, 2);
			pos += 2;
			count++;
		}
		return res;
	}
}
