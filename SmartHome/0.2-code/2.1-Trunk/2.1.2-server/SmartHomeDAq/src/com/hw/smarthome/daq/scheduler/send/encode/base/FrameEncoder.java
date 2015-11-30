package com.hw.smarthome.daq.scheduler.send.encode.base;

import java.util.Calendar;

import com.hw.smarthome.daq.po.DAqPo;

public abstract class FrameEncoder {
	/**
	 * @author 曾凡
	 * @param frame
	 * @return 返回处理指针
	 * @throws Exception
	 * @time 2015年1月14日 上午8:41:25
	 */
	public byte[] handleFrame(DAqPo frame) throws Exception {
		int pos = 0;
		byte[] content = handleContent(frame);
		byte[] res = null;
		if (frame.isTpv()) {
			res = new byte[content.length + 7];
			System.arraycopy(content, 0, res, 0, content.length);
			pos = content.length;
			pos = handleAttach(res, pos);
		} else {
			res = content;
		}
		return res;
	}

	public abstract byte[] handleContent(DAqPo frame)
			throws Exception;

	/**
	 * 处理附加信息
	 * 
	 * @author 曾凡
	 * @param frame
	 * @param pos
	 * @return
	 * @time 2015年1月12日 下午2:33:23
	 */
	public int handleAttach(byte[] attach, int pos) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR) - 2000;// 得到年
		int month = cal.get(Calendar.MONTH) + 1;// 得到月，因为从0开始的，所以要加1
		int day = cal.get(Calendar.DAY_OF_MONTH);// 得到天
		int hour = cal.get(Calendar.HOUR_OF_DAY);// 得到小时
		int minute = cal.get(Calendar.MINUTE);// 得到分钟
		int second = cal.get(Calendar.SECOND);// 得到秒
		// 测试
		// int year = 15;// 得到年
		// int month = 1;// 得到月，因为从0开始的，所以要加1
		// int day = 12;// 得到天
		// int hour = 17;// 得到小时
		// int minute = 2;// 得到分钟
		// int second = 28;// 得到秒
		attach[pos++] = (byte) (0xFF & year);
		attach[pos++] = (byte) month;
		attach[pos++] = (byte) day;
		attach[pos++] = (byte) hour;
		attach[pos++] = (byte) minute;
		attach[pos++] = (byte) second;
		attach[pos++] = (byte) 0x00; // 无需判断延迟
		return pos;
	}

//	public static void print(byte[] decryptCode) {
//		print("", decryptCode);
//	}
//
//	public static void print(String name, byte[] decryptCode) {
//		System.out.println();
//		System.out.println(name + ": ");
//		for (byte b : decryptCode) {
//			System.out.print((Integer.toHexString(0xFF & b))
//					+ " ");
//		}
//		System.out.println();
//	}
}
