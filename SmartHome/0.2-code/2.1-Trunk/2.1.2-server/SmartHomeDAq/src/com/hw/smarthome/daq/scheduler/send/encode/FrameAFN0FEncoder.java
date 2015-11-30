package com.hw.smarthome.daq.scheduler.send.encode;

import java.util.LinkedList;
import java.util.List;

import sun.misc.CRC16;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN0FFN01;
import com.hw.smarthome.daq.po.FileInfo;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.send.encode.base.FrameEncoder;
import com.hw.util.ByteUtils;

public class FrameAFN0FEncoder extends FrameEncoder {
	private static FrameAFN0FEncoder instance = null;

	public static FrameAFN0FEncoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN0FEncoder();
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
				if (!(item instanceof DataItemAFN0FFN01)) {
					throw new Exception(
							HintConstant.ENCODE_DATAITEM_FAIL
									+ ":" + frame.toString());
				}
				fnList.add(f1((DataItemAFN0FFN01) item));
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

	private byte[] f1(DataItemAFN0FFN01 item) throws Exception {
		int pos = 0;
		byte[] res = new byte[37 + (int) item.getDataLen()];
		res[pos++] = 0x01;
		FileInfo fileInfo = SysConstant.getUpdateFileInfo(
				item.getDeviceType(), item.getHwVersion(),
				item.getSwVersion());
		if (fileInfo == null) {
			throw new Exception(
					HintConstant.UPDATE_FILE_NOT_FOUND);
		}
		byte[] deviceType = ByteUtils.toBytes(fileInfo
				.getDeviceType());
		System.arraycopy(deviceType, 0, res, pos, 2);
		pos += 2;
		/* 硬件版本 */
		byte[] hwVersion = ByteUtils
				.toBytes(item.getHwVersion());
		System.arraycopy(hwVersion, 0, res, pos, 4);
		pos += 4;
		/* 软件版本 */
		byte[] swVersion = ByteUtils
				.toBytes(item.getSwVersion());
		System.arraycopy(swVersion, 0, res, pos, 4);
		pos += 4;
		/* 传输类型 */
		byte transDir = 0x01;
		res[pos++] = transDir;
		/* 传输状态 */
		byte TransState = 0x01;
		res[pos++] = TransState;
		/* 文件名 */
		byte[] fileName = ByteUtils.fillAscII(
				fileInfo.getFileName(), 12);
		System.arraycopy(fileName, 0, res, pos, 12);
		pos += 12;
		/* 总文件大小 */
		String fileSizeStr = ByteUtils.getHexWithFill(
				(int) (fileInfo.getFileSize()), 8);
		byte[] fileSize = ByteUtils.reverseBins(ByteUtils
				.toBytes(fileSizeStr));
		System.arraycopy(fileSize, 0, res, pos, 4);
		pos += 4;
		/* 包偏移地址 */
		String fileOffsetStr = ByteUtils.getHexWithFill(
				(int) (item.getFileOffset()), 8);
		byte[] fileOffset = ByteUtils.reverseBins(ByteUtils
				.toBytes(fileOffsetStr));
		System.arraycopy(fileOffset, 0, res, pos, 4);
		pos += 4;
		/* 包长度 */
		String datalenStr = ByteUtils.getHexWithFill(
				(int) (item.getDataLen()), 4);
		byte[] dataLen = ByteUtils.reverseBins(ByteUtils
				.toBytes(datalenStr));
		System.arraycopy(dataLen, 0, res, pos, 2);
		pos += 2;
		/* 包内容 */
		System.arraycopy(fileInfo.getContent(),
				(int) item.getFileOffset(), res, pos,
				(int) item.getDataLen());
		pos += item.getDataLen();
		/* 校验 */
		// String dataCheckStr = ByteUtils.getHexWithFill(
		// getCrc16(res, (int) (pos - item.getDataLen()),
		// (int) item.getDataLen()), 4);
		byte[] dataCheck = getChkSum(res,
				(int) (pos - item.getDataLen()),
				(int) item.getDataLen());
		item.setDataCheck(ByteUtils.toHex(dataCheck));
		System.arraycopy(dataCheck, 0, res, pos, 2);
		pos += 2;
		return res;
	}

	private static byte[] getChkSum(byte[] data, int start,
			int length) {
		byte[] res = new byte[2];
		long crc = 0;
		for (int i = start; i < start + length; i++) {
			crc += ByteUtils.toHEX(data[i]);
		}
		res[0] = (byte) (0xFF & crc);
		return res;
	}

	private static int getCrc16(byte[] data, int start,
			int length) {
		CRC16 crc16 = new CRC16();
		for (int i = start; i < start + length; i++) {
			crc16.update(data[i]);
		}
		return crc16.value;
	}
}
