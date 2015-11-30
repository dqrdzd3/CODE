package com.hw.hwsafe.sms.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Queue;




import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.log4j.Logger;

import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.sms.socket.po.SMSMessage;
import com.hw.hwsafe.sms.socket.util.ThreadClassTemplet;


/**
 * 
 * 短信平台调用端，发送短信给短信平台
 * 
 * @author 曾凡
 * @time 2015年1月6日 下午5:16:05
 */
public class ServerSender extends ThreadClassTemplet {
	private static ServerSender instance;
	public static ServerSender getInstance(){
		if(instance==null){
			instance = new ServerSender(10);
			new HreatBeat(1).start(); // 接收短信平台心跳包
		}
		return instance;
	}
	// 建立数据包套接字用来接收数据包
	public static DatagramSocket udp = null;
	/** 短信平台IP */
	private static InetAddress SMS_IP = null;
	/** 短信平台端口 */
	private static int SMS_PORT = ConfConstants.HW_SMS_PLAT_PORT;

	private static int LOCAL_PORT = ConfConstants.HW_SMS_SERVER_PORT;

	private static Queue<SMSMessage> wait2SendQueue = new ConcurrentLinkedQueue<SMSMessage>();
	private static Logger log = Logger
			.getLogger(ServerSender.class);

//	public static void main(String[] args) {
//		new ServerSender(1).start(); // 发送短信线程
////		sender.sendMessage(frame);
//		
//	}

	public ServerSender(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		SMSMessage frame = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			if (SMS_IP != null || SMS_PORT != 0) {
				if (!wait2SendQueue.isEmpty()) {
					frame = wait2SendQueue.poll();
					if (frame != null) {

						byte[] bs = { (byte) 0 };
						DatagramPacket recdp = new DatagramPacket(
								bs, bs.length, SMS_IP, SMS_PORT);
						baos = new ByteArrayOutputStream();
						oos = null;
						oos = new ObjectOutputStream(baos);
						oos.writeObject(frame);
						oos.flush();
						byte arr[] = baos.toByteArray();
						if (arr == null) {
							log.warn(ServerSender.class
									.getName() + "没有检测到要发送的数据");
							return;
						}
						recdp.setData(arr);
						if (udp != null) {
							udp.send(recdp);
							log.info("服务器发送给短信平台一组数据" + frame);
						} else {
							log.info("调用端【心跳线程】创建短信发送平台UDP SERVER");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error(this.getClass(), e);
		} finally {
			try {
				if (baos != null)
					baos.close();
				if (oos != null)
					oos.close();
			} catch (IOException e) {
				log.error(this.getClass(), e);
			}
		}
	}

	/**
	 * 发送短信接口
	 * 
	 * @author 曾凡
	 * @param frame
	 * @time 2015年6月9日 下午12:37:54
	 */
	public static void sendMessage(SMSMessage frame) {
		wait2SendQueue.add(frame);
	}

	/**
	 * 根据心跳包获取最新的短信平台IP
	 * 
	 * @author 曾凡
	 * @time 2015年6月9日 下午12:59:44
	 */
	private static class HreatBeat extends ThreadClassTemplet {
		private byte[] buff = new byte[32];

		public HreatBeat(int sleepTimes) {
			super(sleepTimes);
		}

		@Override
		protected void runFun() {
			try {
				// 构造数据报包，接收长度为 length 的包。
				DatagramPacket packet = new DatagramPacket(buff,
						buff.length);
				if (udp == null) {
					init();
				}
				try {
					udp.receive(packet);
//					// FIXME 测试
//					SMSMessage msg = new SMSMessage();
//					msg.setContent("Hello");
//					msg.setPhoneNo("13938578189");
//					sendMessage(msg);
				} catch (Exception e) {
					log.error(this.getClass(), e);
					init();
				}
				SMS_IP = packet.getAddress();
				SMS_PORT = packet.getPort();
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
			log.error("短信发送平台调用端创建UDP SERVER失败", e);
			try {
				if (udp != null) {
					udp.close();
				}
				init();
			} catch (Exception e1) {
				log.error("短信发送平台调用端异常创建UDP SERVER失败", e);
			}
		}

	}
}
