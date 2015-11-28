package com.hw.smarthome.daq.front.send;

import java.net.DatagramPacket;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.front.queue.FrontQueue;
import com.hw.smarthome.daq.front.receive.FrontReceiver;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.smarthome.daq.util.ClearUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

public class FrontSender extends ThreadClassTemplet {
	private static Logger log = Logger
			.getLogger(FrontSender.class);
	private DatagramPacket recdp = null;

	public FrontSender(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		DAqPo frame = null;
		try {
			if (!SchedulerQueue.getToFront().isEmpty()) {
				frame = SchedulerQueue.pollToFront();
				if (frame != null) {

					sendMessage(frame);
					if (Ln.IS_DEBUG) {
						log.debug("设备ID:"
								+ frame.getSensorId()
								+ " "
								+ HintConstant.FRONT_SEND_HANDLER_DEBUG
								+ ":" + frame);
					}
				}
			}
		} catch (Exception e) {
			Ln.e(this.getClass(),
					HintConstant.FRONT_SEND_UDP_FAIL, e);
			ClearUtils.clearSend(frame);
		}
	}

	public void sendMessage(DAqPo frame) {
		DAqPo link = FrontQueue.getLink(frame.getSensorId());
		if (link == null || link.getPort() == 0) {
			log.debug(HintConstant.FRONT_DEVICE_NOT_LINKED + " "
					+ frame);
			return;
		}
		try {
			if (frame != null && frame.getSendData() != null) {
				// FIXME 回来修正
				recdp = new DatagramPacket(frame.getSendData(),
						frame.getSendData().length,
						link.getIp(), link.getPort());
				sleep(100);
				FrontReceiver.udp.send(recdp);
			}
		} catch (Exception e) {
			Ln.e(this.getClass(),
					HintConstant.FRONT_SEND_UDP_FAIL + ":"
							+ frame, e);
		} finally {
			ClearUtils.clearSend(frame);
		}
	}

	// public static void main(String[] args)
	// throws UnknownHostException {
	// DAqPo frame = new DAqPo();
	// InetAddress address = InetAddress
	// .getByName("192.168.111.202");
	// frame.setIp(address);
	// frame.setPort(45625);
	// byte[] test = { (byte) 123, (byte) 123, (byte) 123,
	// (byte) 123, (byte) 123, (byte) 123, (byte) 123,
	// (byte) 123, (byte) 123, (byte) 123, (byte) 123,
	// (byte) 123 };
	// frame.setSendData(test);
	// new FrontSender(-1).sendMessage(frame);
	// }
}
