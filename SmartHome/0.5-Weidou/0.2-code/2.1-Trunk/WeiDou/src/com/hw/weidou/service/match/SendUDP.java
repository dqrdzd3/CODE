package com.hw.weidou.service.match;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年10月17日 下午2:25:22
 */
public class SendUDP {
	private static SendUDP instance;

	public static SendUDP getInstance() {
		if (instance == null) {
			instance = new SendUDP();
		}
		return instance;
	}

	private MulticastSocket listenSocket;
	private static final int sentPost = 43522;
	public static final String BROADCAST_IP = "224.0.0.1";
	private InetAddress serverAddress = null;

	public SendUDP() {
		createBroadcastUDPSocket();
	}

	public void createBroadcastUDPSocket() {
		try {
			this.listenSocket = new MulticastSocket(sentPost);
			/* 将该MulticastSocket对象加入到指定的多点广播地址 */
			serverAddress = InetAddress.getByName(BROADCAST_IP);
			this.listenSocket.setTimeToLive(255);
			this.listenSocket.joinGroup(serverAddress);
			this.listenSocket.setBroadcast(true);
		} catch (Exception e) {
			Ln.e(e, "初始化发送组播失败");
		}
	}

	/**
	 * 发送UDP广播
	 * 
	 * @author 曾凡
	 * @param packet
	 * @param localSendingPort
	 * @time 2014年10月17日 下午2:27:57
	 */
	public void sendData(String content) {
		try {
			byte data[] = content.getBytes();
			/* 创建一个DatagramPacket 对象，并指定要讲这个数据包发送到网络当中的哪个地址，以及端口号 */
			DatagramPacket packet = new DatagramPacket(data,
					data.length, serverAddress, sentPost);
			/* 调用MulticastSocket对象的send方法 发送数据 */
			this.listenSocket.send(packet);
			System.out.println("发送广播：" + content);
		} catch (Exception e) {
			Ln.e(e, "发送" + content + "失败");
		}
	}
}
