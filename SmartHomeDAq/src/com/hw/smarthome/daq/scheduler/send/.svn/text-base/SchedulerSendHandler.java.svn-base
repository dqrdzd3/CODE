package com.hw.smarthome.daq.scheduler.send;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN00Encoder;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN05Encoder;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN09Encoder;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN0FEncoder;
import com.hw.smarthome.daq.scheduler.send.encode.FrameAFN10Encoder;
import com.hw.smarthome.daq.scheduler.send.encode.base.FrameEncoder;
import com.hw.smarthome.daq.scheduler.util.FrameUtil;
import com.hw.util.AESUtils;
import com.hw.util.ByteUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

/**
 * 发送数据给前置机
 * 
 * @author 曾凡
 * @time 2015年1月13日 下午4:48:37
 */
public class SchedulerSendHandler extends ThreadClassTemplet {
	private static Logger log = Logger
			.getLogger(SchedulerSendHandler.class);

	public SchedulerSendHandler(int sleepTimes) {
		super(sleepTimes);
	}

	/** 接受前置机和服务器的返回任务 */
	@Override
	protected void runFun() {
		DAqPo frame = null;

		if (!SchedulerQueue.getToFrontWait().isEmpty()) {
			frame = SchedulerQueue.pollToFrontWait();
			if (frame != null) {
				try {
					handleFrame(frame);
				} catch (Exception e) {
					Ln.e(this.getClass(),
							HintConstant.SCHEDULER_SEND_HANDLER_FAIL
									+ ":" + frame.toString(), e);
				} finally {
					SchedulerQueue.addToFront(frame);
				}
			}
		}

		if (!SchedulerQueue.getFromServer().isEmpty()) {
			frame = SchedulerQueue.pollFromServer();
			if (frame != null) {
				try {
					handleFrame(frame);
					if (Ln.IS_DEBUG) {
						log.debug(frame.getSensorId()
								+ ":"
								+ ByteUtils.toHex(frame
										.getSendData()));
					}
					SchedulerQueue.addToFront(frame);
				} catch (Exception e) {
					Ln.e(this.getClass(),
							HintConstant.SCHEDULER_SEND_HANDLER_FAIL
									+ ":" + frame.toString(), e);
					e.printStackTrace();
				} finally {

				}
			}
		}

	}

	private void handleFrame(DAqPo frame) throws Exception {
		/* 结束 */
		byte[] res = null;
		boolean isRes = true;
		try {
			byte[] headBytes = getHeadBytes(frame);
			byte afn = (byte) 0;
			FrameEncoder encoder = null;
			switch (frame.getAfn()) {

			case FrameConstant.AFN_ACK:// 应答帧
				afn = (byte) 0x00;
				encoder = FrameAFN00Encoder.getInstance();
				break;
			case 0x0C:// 应答帧
				afn = (byte) 0x00;
				encoder = FrameAFN00Encoder.getInstance();
				break;
			case FrameConstant.AFN_RESET:// 复位

				break;
			case FrameConstant.AFN_SET_CONFIG:// 设置参数

				break;
			case FrameConstant.AFN_CTRL_CMD:// 控制命令
				afn = (byte) 0x05;
				encoder = FrameAFN05Encoder.getInstance();
				break;
			case FrameConstant.AFN_CTRL_DEVICE_UPLOAD:// 被控设备主动上报
				afn = (byte) (FrameConstant.AFN_ACK & 0xFF);
				encoder = FrameAFN00Encoder.getInstance();
				break;
			case FrameConstant.AFN_ACTIVE_UPLOAD:// 主动数据上传
				afn = (byte) (FrameConstant.AFN_ACK & 0xFF);
				encoder = FrameAFN00Encoder.getInstance();
				break;
			case FrameConstant.AFN_ASK_CONFIG_INFO:// 请求配置及信息
				afn = (byte) 0x09;
				encoder = FrameAFN09Encoder.getInstance();
				break;
			case FrameConstant.AFN_CTRL_DEVICE_TRANS:// 被控设备数据传输
				afn = (byte) (FrameConstant.AFN_CTRL_DEVICE_TRANS & 0xFF);
				encoder = FrameAFN10Encoder.getInstance();
				break;
			case FrameConstant.AFN_QUERY_PARA:// 查询参数

				break;
			case FrameConstant.AFN_TRANS_FILE:// 文件传输
				afn = (byte) 0x0F;
				encoder = FrameAFN0FEncoder.getInstance();
				break;
			default:
				break;
			}
			// frame.setAfn(afn);
			headBytes[FrameConstant.AFN_INDEX] = afn;
			headBytes[FrameConstant.SEQ_INDEX] = FrameUtil
					.getSeq(frame);
			/* 1.解析内容 */
			byte[] frameBytes = encoder.handleFrame(frame);
			/* 待转码 */
			byte[] raw = new byte[frameBytes.length + 9];// 数据内容加上SEQ到C的数组，用来进行转码
			/* 待转码 */
			System.arraycopy(headBytes, 4, raw, 0, 9);
			System.arraycopy(frameBytes, 0, raw, 9,
					frameBytes.length);
			byte[] sendBytes = null;
			/* 将内容转码 */
			if (SysConstant.IS_DECODE_ENCODE) {
				sendBytes = fillEncrypt(raw, frame);
			} else {
				sendBytes = raw;
			}

			res = finishFrame(sendBytes);
		} catch (Exception e) {
			/* 户外机有可能就不是16进制的  */
			log.debug("拼接发送帧异常:"+frame, e);
			isRes = false;
		} finally {
			if (isRes) {
				frame.setSendData(res);
			}
		}
	}

	private byte[] fillEncrypt(byte[] raw, DAqPo frame) {

		int num = raw.length % 16;
		if ((num) != 0) {
			num = 16 - num;
		} else {
			return raw;
		}
		byte[] res = new byte[raw.length + num];
		System.arraycopy(raw, 0, res, 0, raw.length);
		try {
			res = AESUtils.encrypt(FrameConstant.SEED, res);
		} catch (Exception e) {
			Ln.e(this.getClass(),
					"数据加密异常" + ":" + frame.toString(), e);
		}
		return res;
	}

	private byte[] finishFrame(byte[] content) {
		byte[] res = new byte[content.length + 6]; // 头4和尾2
		System.arraycopy(content, 0, res, 4, content.length);
		int chkSum = 0;

		for (int i = 0; i < content.length; i++) {
			chkSum += ByteUtils.toHEX(content[i]);
		}
		chkSum = 0xFF & chkSum;
		res[res.length - 2] = (byte) chkSum;
		res[res.length - 1] = FrameConstant.FRAME_END;
		String l = ByteUtils.getHexWithFill(content.length, 4);

		res[0] = FrameConstant.FRAME_HEAD;
		res[1] = (byte) (0xFF & Integer.valueOf(
				l.substring(0, 2), 16));
		res[2] = (byte) (0xFF & Integer.valueOf(
				l.substring(2, 4), 16));
		res[3] = FrameConstant.FRAME_HEAD;
		return res;
	}

	/**
	 * 初始化帧头和帧尾（不包括长度和CS校验）
	 * 
	 * @author 曾凡
	 * @param bytes
	 * @time 2015年1月13日 下午6:39:49
	 */
	private byte[] getHeadBytes(DAqPo frame) {
		byte[] resArray = new byte[13];
		resArray[FrameConstant.CTRL_INDEX] = FrameUtil
				.getCtrl(frame);
		byte[] addrs = ByteUtils.toBytes(frame.getSensorId());
		int addrsPos = 0;
		/* 初始化地址位 */
		for (int i = FrameConstant.ADDR_INDEX; i < FrameConstant.AFN_INDEX; i++) {
			resArray[i] = addrs[addrsPos++];
		}
		return resArray;
	}
}
