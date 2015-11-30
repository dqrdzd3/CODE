package com.hw.smarthome.daq.scheduler.util;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.util.ByteUtils;

public class FrameUtil {
	public static void updateFrameCtrlSeq(DAqPo frame) {
		int seq = ByteUtils.getBins(frame.getReceiveData(),
				FrameConstant.SEQ_INDEX,
				FrameConstant.SEQ_LENGTH);
		int ctrl = ByteUtils.getBins(frame.getReceiveData(),
				FrameConstant.SEQ_INDEX,
				FrameConstant.SEQ_LENGTH);
		updateFrameCtrlSeq(frame, (byte) seq, (byte) ctrl);
	}

	public static void updateFrameCtrlSeq(DAqPo frame, int seq,
			int ctrl) {
		updateFrameCtrlSeq(frame, (byte) seq, (byte) ctrl);
	}

	public static void updateFrameCtrlSeq(DAqPo frame, byte seq,
			byte ctrl) {
		String seqStr = Integer.toBinaryString(0xFF & seq);
		/** 表示该帧内含有无时间标签 */
		boolean tpv = "1".equals(seqStr.substring(0, 1));
		/** 报文的第一帧 */
		boolean fir = "1".equals(seqStr.substring(1, 2));
		/** 报文的最后一帧 */
		boolean fin = "1".equals(seqStr.substring(2, 3));
		/** 1,表示该帧必须回应，0，表示该帧无须回应 */
		boolean con = "1".equals(seqStr.substring(3, 4));

		frame.setTpv(tpv);
		frame.setFir(fir);
		frame.setFin(fin);
		frame.setCon(con);
		frame.setSeqCount((byte) (seq & 0x0F));

		String ctrlStr = Integer.toBinaryString(0xFF & ctrl);
		/** 表示该帧内含有无时间标签 */
		boolean dir = "1".equals(ctrlStr.substring(0, 1));
		/** 报文的第一帧 */
		boolean prm = "1".equals(ctrlStr.substring(1, 2));
		boolean fcb = "1".equals(ctrlStr.substring(2, 3));
		boolean fcv = "1".equals(ctrlStr.substring(3, 4));
		frame.setDir(dir);
		frame.setPrm(prm);
		frame.setFcb(fcb);
		frame.setFcv(fcv);
		frame.setCtrlCount((byte) (ctrl & 0x0F));
	}

	public static byte generateCode(boolean a, boolean b,
			boolean c, boolean d, byte count) {
		StringBuilder sb = new StringBuilder();
		sb.append(a ? "1" : "0");
		sb.append(b ? "1" : "0");
		sb.append(c ? "1" : "0");
		sb.append(d ? "1" : "0");
		byte res = (byte) (0xFF & ((Integer.valueOf(
				sb.toString(), 2) << 4) + (0x0F & count)));
		return res;
	}

	public static byte getCtrl(DAqPo frame) {
		byte ctrl = FrameConstant.FRAME_CTRL;
		switch (frame.getAfn()) {
		case FrameConstant.AFN_ACK:// 应答帧
			ctrl = 0x00;
			break;
		case FrameConstant.AFN_RESET:// 复位
			break;
		case FrameConstant.AFN_SET_CONFIG:// 设置参数
			break;
		case FrameConstant.AFN_CTRL_CMD:// 控制命令
			break;
		case FrameConstant.AFN_CTRL_DEVICE_UPLOAD:// 被控设备主动上报
			break;
		case FrameConstant.AFN_ACTIVE_UPLOAD:// 主动数据上传
			ctrl = 0x00;
			break;
		case FrameConstant.AFN_ASK_CONFIG_INFO:// 请求配置及信息
			break;
		case FrameConstant.AFN_QUERY_PARA:// 查询参数
			break;
		case FrameConstant.AFN_TRANS_FILE:// 文件传输
			ctrl = generateCode(frame.isDir() ? false : true,
					frame.isPrm() ? false : true,
					frame.isFcb() ? false : true, frame.isFcv(),
					frame.getCtrlCount());
			break;
		}
		return ctrl;
	}

	public static byte getSeq(DAqPo frame) {
		byte seq = 0;
		switch (frame.getAfn()) {
		case FrameConstant.AFN_ACK:// 应答帧
			seq = (byte) 0xE0;
			break;
		case FrameConstant.AFN_RESET:// 复位

			break;
		case FrameConstant.AFN_SET_CONFIG:// 设置参数

			break;
		case FrameConstant.AFN_CTRL_CMD:// 控制命令
			seq = (byte) 0xF0;
			break;
		case FrameConstant.AFN_CTRL_DEVICE_UPLOAD:// 被控设备主动上报
			if (frame.isCon()) {
				seq = (byte) 0xE0;
			} else {
				seq = (byte) 0xF0;
			}
			break;
		case FrameConstant.AFN_ACTIVE_UPLOAD:// 主动数据上传
			if (frame.isCon()) {
				seq = (byte) 0xE0;
			} else {
				seq = (byte) 0xF0;
			}
			break;
		case FrameConstant.AFN_ASK_CONFIG_INFO:// 请求配置及信息
			seq = (byte) 0xE0;
			break;
		case FrameConstant.AFN_QUERY_PARA:// 查询参数

			break;
		case FrameConstant.AFN_TRANS_FILE:// 文件传输
			/* 如果是最后一个就不用回复了 */
			if ((frame.isFir() == false)
					&& (frame.isFin() == true)) {
				frame.setCon(false);
			}
			seq = generateCode(frame.isTpv(), frame.isFir(),
					frame.isFin(), frame.isCon(),
					frame.getSeqCount());
			break;
		}
		return seq;
	}
}
