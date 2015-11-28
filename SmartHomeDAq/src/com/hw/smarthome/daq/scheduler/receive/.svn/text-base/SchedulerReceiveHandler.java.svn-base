package com.hw.smarthome.daq.scheduler.receive;

import java.util.Date;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.FrameConstant;
import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.front.queue.FrontQueue;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.smarthome.daq.scheduler.receive.decode.FrameAFN05Decoder;
import com.hw.smarthome.daq.scheduler.receive.decode.FrameAFN07Decoder;
import com.hw.smarthome.daq.scheduler.receive.decode.FrameAFN08Decoder;
import com.hw.smarthome.daq.scheduler.receive.decode.FrameAFN09Decoder;
import com.hw.smarthome.daq.scheduler.receive.decode.FrameAFN0FDecoder;
import com.hw.smarthome.daq.scheduler.receive.decode.FrameAFN10Decoder;
import com.hw.smarthome.daq.scheduler.receive.decode.base.FrameDecoder;
import com.hw.smarthome.daq.scheduler.util.FrameUtil;
import com.hw.smarthome.daq.server.socket.queue.SocketQueue;
import com.hw.util.ByteUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

public class SchedulerReceiveHandler extends ThreadClassTemplet {
	private Logger log = Logger.getLogger(this.getClass());

	public SchedulerReceiveHandler(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		if (!FrontQueue.getToScheduler().isEmpty()) {
			DAqPo frame = FrontQueue.getToSchedulerData();
			try {
				// 20150505 116.7.225.130的ip发送了一个解析后全部为0的字段，具体见日志
				if ((frame != null)
						&& (frame.getReceiveData() != null)) {
					if (frame.getReceiveData().length > 1) {
						if (frame.getReceiveData()[0] != 0x68) {
							log.warn(HintConstant.SCHEDULER_HANDLER_FAIL
									+ ":" + frame);
							return;
						}
					}
					if (Ln.IS_DEBUG) {
						log.debug(frame);
					}
					/* 0表示不返回 给后端，1返回给服务器，0返回给socket */
					final int resType = handler(frame);
					/* 如果需要返回就返回给前置机 */
					if (frame.isCon()) {
						frame.setDate(new Date());// 修改发送时间
						SchedulerQueue.addToFrontWait(frame);
					}

					switch (resType) {
					case SysConstant.RETURN_2_SERVER:
						SchedulerQueue.addToServer(frame);
						break;
					case SysConstant.RETURN_2_SOCKET:
						SocketQueue.getToServerTask().add(frame);
						break;
					case SysConstant.RETURN_2_SERVER
							| SysConstant.RETURN_2_SOCKET:
						/* 发送后端 */
						SchedulerQueue.addToServer(frame);
						SocketQueue.getToServerTask().add(frame);
						break;
					default:
						break;
					}

				}
			} catch (Exception e) {
				Ln.e(this.getClass(),
						HintConstant.SCHEDULER_HANDLER_FAIL
								+ ":" + frame, e);
			} finally {

			}
		}
	}

	/**
	 * 处理这个报文
	 * 
	 * @author 曾凡
	 * @param frame
	 * @time 2015年1月6日 下午5:54:25
	 */
	private int handler(DAqPo frame) throws Exception {

		/* 0表示不返回 给后端，1返回给服务器，0返回给socket */
		int resType = 0;
		FrameDecoder decoder = null;
		int afn = initFrame(frame);
		switch (afn) {
		case 0x0C: // AFN没有08
			SchedulerQueue.putErrorCode(frame.getAfn(),
					frame.getSensorId(), frame);
			decoder = FrameAFN08Decoder.getInstance();
			resType = SysConstant.RETURN_2_SERVER;
			break;
		case FrameConstant.AFN_ACK:// 应答帧
			resType = SysConstant.RETURN_2_SOCKET;
			break;
		case FrameConstant.AFN_RESET:// 复位
			break;
		case FrameConstant.AFN_SET_CONFIG:// 设置参数
			break;
		case FrameConstant.AFN_CTRL_CMD:// 控制命令
			decoder = FrameAFN05Decoder.getInstance();
			resType = SysConstant.RETURN_2_SOCKET;
			break;
		case FrameConstant.AFN_CTRL_DEVICE_UPLOAD:// 被控设备主动上报
			decoder = FrameAFN07Decoder.getInstance();
			resType = SysConstant.RETURN_2_SERVER;
			break;
		case FrameConstant.AFN_ACTIVE_UPLOAD:// 主动数据上传
			decoder = FrameAFN08Decoder.getInstance();
			resType = SysConstant.RETURN_2_SERVER;
			break;
		case FrameConstant.AFN_ASK_CONFIG_INFO:// 请求配置及信息
			decoder = FrameAFN09Decoder.getInstance();
			resType = SysConstant.RETURN_2_SOCKET;
			break;
		case FrameConstant.AFN_CTRL_DEVICE_TRANS:
			decoder = FrameAFN10Decoder.getInstance();
			resType = SysConstant.RETURN_2_SOCKET;
			break;
		case FrameConstant.AFN_QUERY_PARA:// 查询参数
			break;
		case FrameConstant.AFN_TRANS_FILE:// 文件传输
			decoder = FrameAFN0FDecoder.getInstance();
			resType = SysConstant.RETURN_2_SOCKET;
			break;
		default:
			break;
		}
		if (decoder != null) {
			decoder.handleFrame(frame);
		}
		return resType;
	}

	/**
	 * 初始化帧的AFN和SEQ
	 * 
	 * @author 曾凡
	 * @param frame
	 * @return
	 * @time 2015年1月8日 下午4:09:25
	 */
	private int initFrame(DAqPo frame) {
		int afn = ByteUtils.getBins(frame.getReceiveData(),
				FrameConstant.AFN_INDEX,
				FrameConstant.AFN_LENGTH);
		frame.setAfn(ByteUtils.getByte(afn));
		int seq = ByteUtils.getBins(frame.getReceiveData(),
				FrameConstant.SEQ_INDEX,
				FrameConstant.SEQ_LENGTH);
		int ctrl = ByteUtils.getBins(frame.getReceiveData(),
				FrameConstant.CTRL_INDEX,
				FrameConstant.CTRL_LENGTH);
		FrameUtil.updateFrameCtrlSeq(frame, seq, ctrl);

		return afn;
	}

	// public static void main(String[] args) {
	// DAqPo current = new DAqPo();
	// String metaDate =
	// "68004068C050000105A8DF08E005013001180002023502C9012800011103CA013000011404D8019800016C05D901000001030E0C1A00011A0000000000000000000000003A16";
	// byte[] metaBytes = ByteUtils.toBytes(metaDate);
	// current.setReceiveData(metaBytes);
	//
	// try {
	// new SchedulerReceiveHandler(1).handler(current);
	// System.out.println(current.toString());
	// System.out.println(current.getWsJson());
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}
