package com.hw.hwsafe.sms.socket;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.sms.SMSService;
import com.hw.hwsafe.sms.socket.po.SMSMessage;
import com.hw.hwsafe.sms.socket.util.ThreadClassTemplet;

/**
 * 短信平台
 * 
 * @author 曾凡
 * @time 2015年6月9日 下午12:43:55
 */
public class SMSSender extends ThreadClassTemplet {
	// 建立数据包套接字用来接收数据包
	public static DatagramSocket udp = null;
	/** 服务器IP */
	private static String SERVER_IP = ConfConstants.HW_SMS_SERVER_NAME;//"192.168.111.186";
	/** 服务器端口 */
	private static int SERVER_PORT = ConfConstants.HW_SMS_SERVER_PORT;

	private static int LOCAL_PORT = ConfConstants.HW_SMS_PLAT_PORT;
	private static Queue<SMSMessage> wait2SendQueue = new ConcurrentLinkedQueue<SMSMessage>();
	private static Logger log = Logger.getLogger(SMSSender.class);
	
	private static SMSSender instance;

	public static SMSSender getInstant() {
		if (instance == null) {
			instance = new SMSSender(10);
			new HreatBeat(1).start(); // 向服务端发送心跳包
			new ReceiveSMS(1).start();// 接受服务器返回的数据
		}
		return instance;
	}

	public static void main(String[] args) {
		SMSSender sender = new SMSSender(100);
		sender.start(); // 发送短信线程

		new HreatBeat(1).start(); // 向服务端发送心跳包
		new ReceiveSMS(1).start();// 接受服务器返回的数据
	}

	public SMSSender(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		SMSMessage frame = null;
		try {
			if (!wait2SendQueue.isEmpty()) {
				frame = wait2SendQueue.poll();
				if (frame != null) {
					sendMessage(frame);
				}
			}
		} catch (Exception e) {
			log.error(this.getClass(), e);
		}
	}

	/**
	 * 发送短信
	 * 
	 * @author 曾凡
	 * @param frame
	 * @time 2015年6月9日 下午12:37:54
	 */
	private void sendMessage(SMSMessage frame) {
		// TODO 替换为正常逻辑
		System.out.println("短信平台发送短信" + frame);
		log.info("短信平台发送短信" + frame);
		SMSService smsService =new SMSService();
		smsService.sendMsg(frame.getPhoneNo(), frame.getContent()); //4：向用户手机号发送短信，短信内容为报警信息
		System.out.println("向用户手机号"+frame.getPhoneNo()+"发送短信，短信内容为"+frame.getContent());
	}

	private static class HreatBeat extends ThreadClassTemplet {

		public HreatBeat(int sleepTimes) {
			super(sleepTimes);
		}

		@Override
		protected void runFun() {
			try {
				InetAddress address = InetAddress.getByName(SERVER_IP);
				byte[] bs = { (byte) 0 };
				DatagramPacket recdp = new DatagramPacket(bs, bs.length,
						address, SERVER_PORT);

				if (udp != null) {
					udp.send(recdp);
				} else {
					log.info("【心跳线程】创建短信发送平台UDP SERVER");
				}
				sleep(15 * 1000);
			} catch (Exception e) {
				log.error(this.getClass(), e);
			} finally {
			}
		}

	}

	private static class ReceiveSMS extends ThreadClassTemplet {

		public DatagramPacket packet = null;
		// 建立一个接受数据的缓冲区
		private byte[] buff = new byte[2048 * 32];

		public ReceiveSMS(int sleepTimes) {
			super(sleepTimes);
			init();
		}

		@Override
		protected void runFun() {
			try {

				// 构造数据报包，接收长度为 length 的包。
				packet = new DatagramPacket(buff, buff.length);
				if (udp == null) {
					init();
				}
				try {
					udp.receive(packet);
				} catch (Exception e) {
					log.error(this.getClass(), e);
					init();
				}
				// 获得数据包的缓冲区从0下标到缓冲区尾指针的数据，返回的是接收的数据和发送的数据
				SMSMessage sms = null;
				byte[] temps = null;
				temps = new byte[packet.getLength()];
				System.arraycopy(packet.getData(), 0, temps, 0, temps.length);
				ByteArrayInputStream bais = new ByteArrayInputStream(temps);
				ObjectInputStream ois = null;
				ois = new ObjectInputStream(bais);
				sms = (SMSMessage) ois.readObject();
				wait2SendQueue.add(sms);
			} catch (Exception e) {
				log.error(this.getClass(), e);
			} finally {
			}
		}

	}

	private static void init() {

		try {
			udp = new DatagramSocket(LOCAL_PORT);
			udp.setTrafficClass(0x04 | 0x10);
		} catch (Exception e) {
			log.error("短信发送平台创建UDP SERVER失败", e);
			try {
				if (udp != null) {
					udp.close();
				}
				init();
			} catch (Exception e1) {
				log.error("短信发送平台异常创建UDP SERVER失败", e);
			}
		}

	}
}
