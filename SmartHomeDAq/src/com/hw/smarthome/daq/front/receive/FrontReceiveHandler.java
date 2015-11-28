package com.hw.smarthome.daq.front.receive;

import java.util.UUID;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.front.queue.FrontQueue;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.util.AESUtils;
import com.hw.util.ByteUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

/**
 * 处理连接信息
 * 
 * @author 曾凡
 * @time 2015年1月6日 上午10:13:13
 */
public class FrontReceiveHandler extends ThreadClassTemplet {
	private static Logger log = Logger
			.getLogger(FrontReceiveHandler.class);

	public FrontReceiveHandler(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		try {
			handle();
		} catch (Exception e) {
			Ln.e(this.getClass(),
					HintConstant.FRONT_RECEIVE_HANDLER_FAIL, e);
			System.exit(0);
		} finally {
			return;
		}
	}

	private void handle() {
		if (!FrontQueue.getReceiveCache().isEmpty()) {
			DAqPo current = null;
			try {
				current = FrontQueue.getLatestLinkData();
				if (current != null) {
					if (chkSumLegal(current)) {
						// 20150505 116.7.225.130的ip发送了一个解析后全部为0的字段，cs校验居然也通过了
						processLink(current);
					}
				}
			} catch (Exception e) {
				Ln.e(this.getClass(),
						HintConstant.FRONT_RECEIVE_HANDLER_FAIL
								+ " " + current, e);
				current = null;
			}
		}
	}

	@SuppressWarnings("finally")
	private static boolean chkSumLegal(DAqPo current) {
		byte[] tempBytes = null;
		int sum = 0;
		int cs = 0;
		try {
			tempBytes = current.getReceiveData();
			for (int i = FrameConstant.CTRL_INDEX; i < tempBytes.length - 2; i++) {
				sum += ByteUtils.toHEX(tempBytes[i]);
			}
			sum = sum & 0xFF;
			cs = ByteUtils
					.toHEX(tempBytes[tempBytes.length - 2]);
			if (sum == cs) {
				current.setChkSumLegal(true);
			} else {
				current.setChkSumLegal(false);
			}

		} catch (Exception e) {
			log.warn(HintConstant.FRONT_HANDLER_CHKSUM_FAIL
					+ ":" + current, e);
			current.setChkSumLegal(false);
		} finally {
			return current.isChkSumLegal();
		}
	}

	/**
	 * 处理链接内容
	 * 
	 * @author 曾凡
	 * @param current
	 * @throws Exception
	 * @time 2015年1月6日 下午12:08:17
	 */
	private static void processLink(DAqPo current)
			throws Exception {
		if (current.getReceiveData() == null
				|| current.getReceiveData().length < 16) {
			log.warn(HintConstant.FRONT_SEND_DECODE_FAIL_MIN
					+ " " + current);
			return;
		}
		current.setId(UUID.randomUUID().toString());

		int l = ByteUtils.getBins(current.getReceiveData(),
				FrameConstant.L_INDEX, FrameConstant.L_LENGTH);
		/* 内容长度=总长度-帧头和帧尾 */
		byte[] tempContent = new byte[l];
		/* 获取需要转码的内容 */
		System.arraycopy(current.getReceiveData(),
				FrameConstant.CTRL_INDEX, tempContent, 0, l);

		byte[] tempBytes = null;
		/* 将内容转码 */
		if (SysConstant.IS_DECODE_ENCODE) {
			if ((tempContent.length % 16) != 0) {
				log.warn(HintConstant.FRONT_SEND_DECODE_FAIL
						+ " " + current);
				return;
			}
			tempBytes = AESUtils.decrypt(FrameConstant.SEED,
					tempContent);
		} else {
			tempBytes = tempContent;
		}
		/* 将转码后的数据替换到原来的数组里 */
		for (int i = 0; i < l; i++) {
			current.getReceiveData()[i
					+ FrameConstant.CTRL_INDEX] = tempBytes[i];
		}
		String sensorId = getSensorId(current);
		current.setSensorId(sensorId);
		/* 加入到连接列表里 */
		FrontQueue.putLink(sensorId, current);
		if (Ln.IS_DEBUG) {
			log.debug("设备ID:" + sensorId + " "
					+ HintConstant.FRONT_RECEIVE_HANDLER_DEBUG
					+ ":"
					+ ByteUtils.toHex(current.getReceiveData()));
		}
		/* 发送给任务调度，如果异常就加入不到这个队列里 */
		FrontQueue.addToSchedulerData(current);
	}

	/**
	 * 获取传感器ID
	 * 
	 * @author 曾凡
	 * @param current
	 * @return
	 * @time 2015年1月6日 下午12:08:09
	 */
	private static String getSensorId(DAqPo current) {
		String res = "000000000000";
		try {
			res = ByteUtils.getBinHexStrs(
					current.getReceiveData(),
					FrameConstant.ADDR_INDEX,
					FrameConstant.ADDR_LENGTH);

		} catch (Exception e) {
			log.warn(HintConstant.FRONT_HANDLER_SENSOR_ID_FAIL
					+ ":" + current, e);
		}
		return res;
	}
	private static String getSn(){
		return "59999"+"DQJC004";
	}

	public static void main(String[] args) throws Exception {
		DAqPo current = new DAqPo();
		String metaDate = "68004068752B494D90FB6098A0314AFC241D94941C6291DAA4B4AE50083396B7A4DE273DFD425127848D6F2A9E31AE22278B79BFAFCCB90B02978D54BE08C19313771F1B6716";
		byte[] metaBytes = ByteUtils.toBytes(metaDate);
		current.setReceiveData(metaBytes);
		if (Ln.IS_DEBUG) {
			log.debug("xml文件DEBUG");
			log.debug("前置机UDP接收端口号为："
					+ SysConstant.FRONT_UDP_PORT);
		}

		if (chkSumLegal(current)) {
			processLink(current);
		}

	}
}
