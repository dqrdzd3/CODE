package com.hw.smarthome.daq.scheduler.send.encode;

import java.util.LinkedList;
import java.util.List;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.po.DataItemAFN05FN01;
import com.hw.smarthome.daq.po.FileInfo;
import com.hw.smarthome.daq.po.base.DataItem;
import com.hw.smarthome.daq.scheduler.send.encode.base.FrameEncoder;
import com.hw.util.ByteUtils;

public class FrameAFN05Encoder extends FrameEncoder {
	private static FrameAFN05Encoder instance = null;

	public static FrameAFN05Encoder getInstance() {
		if (instance == null) {
			instance = new FrameAFN05Encoder();
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
				if (!(item instanceof DataItemAFN05FN01)) {
					throw new Exception(
							HintConstant.ENCODE_DATAITEM_FAIL
									+ ":" + frame.toString());
				}
				fnList.add(f1((DataItemAFN05FN01) item));
				break;
			default:
				break;
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

	private byte[] f1(DataItemAFN05FN01 item) throws Exception {
		int pos = 0;
		byte[] res = new byte[30];
		res[pos++] = 0x01;
		FileInfo fileInfo = SysConstant.getUpdateFileInfo(
				item.getDeviceType(), item.getHardVer(),
				item.getSoftVer());
		if (fileInfo == null) {
			throw new Exception(
					HintConstant.UPDATE_FILE_NOT_FOUND);
		}
		byte[] deviceType = ByteUtils.toBytes(fileInfo
				.getDeviceType());
		System.arraycopy(deviceType, 0, res, pos, 2);
		pos += 2;
		byte[] hwVersion = ByteUtils.toBytes(fileInfo
				.getHwVersion());
		System.arraycopy(hwVersion, 0, res, pos, 4);
		pos += 4;
		byte[] swVersion = ByteUtils.toBytes(fileInfo
				.getSwVersion());
		System.arraycopy(swVersion, 0, res, pos, 4);
		pos += 4;
		byte[] fileName = ByteUtils.fillAscII(
				fileInfo.getFileName(), 12);
		System.arraycopy(fileName, 0, res, pos, 12);
		pos += 12;
		String fileSizeStr = ByteUtils.getHexWithFill(
				(int) (fileInfo.getFileSize()), 8);
		byte[] fileSize = ByteUtils.reverseBins(ByteUtils
				.toBytes(fileSizeStr));
		System.arraycopy(fileSize, 0, res, pos, 4);
		pos += 4;
		String fileChkSumStr = ByteUtils.getHexWithFill(
				(int) (fileInfo.getDataCheck()), 4);
		byte[] fileChkSum = ByteUtils.reverseBins(ByteUtils
				.toBytes(fileChkSumStr));
		System.arraycopy(fileChkSum, 0, res, pos, 2);
		pos += 2;
		return res;
	}
	// public static void main(String[] args) {
	// String fileSizeStr = ByteUtils.getHexWithFill(
	// (int) (199020), 8);
	// byte[] fileSize = ByteUtils.reverseBins(ByteUtils
	// .toBytes(fileSizeStr));
	// for (byte b : fileSize) {
	// System.out.print(Integer.toHexString(b) + " ");
	// }
	// }
}
