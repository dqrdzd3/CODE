package com.hw.weidou.service.match;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import android.net.wifi.WifiManager;

import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

/**
 * @author 曾凡
 * @time 2014年10月17日 下午2:25:55
 */
public class ReceiveUDP extends ThreadClassTemplet {
	private static ReceiveUDP instance;
	public static final int SERVER_PORT = 38628;

	public static ReceiveUDP getInstance() {
		if (instance == null) {
			instance = new ReceiveUDP(1);
		}
		return instance;
	}

	public ReceiveUDP(int sleepTimes) {
		super(sleepTimes);
	}

	/* 1. 创建一个MulticastSocket 对象，并指定监听的端口号 */
	private MulticastSocket multicastSocket = null;
	private static int BROADCAST_PORT = 9898;
	private InetAddress serverAddress = null;
	/* 4. 创建一个byte数组用于接收 */
	private static byte[] data = new byte[1024];
	/* 5. 创建一个空的DatagramPackage对象 */
	private DatagramPacket udpPacket = null;
	private static WifiManager.MulticastLock lock;

	public void init(WifiManager manager) {
		try {
			this.lock = manager.createMulticastLock("UDPwifi");
			multicastSocket = new MulticastSocket(SERVER_PORT);
			serverAddress = InetAddress
					.getByName(SendUDP.BROADCAST_IP);
			/* 3. 将该MulticastSocket对象加入到指定的多点广播地址 */
			multicastSocket.joinGroup(serverAddress);
			udpPacket = new DatagramPacket(data, data.length,
					serverAddress, BROADCAST_PORT);
		} catch (Exception e) {
			Ln.e(e, "接受UDP广播初始化失败");
		}
	}

	@Override
	protected void runFun() {

		try {
			this.lock.acquire();
			/* 6. 使用receive方法接收发送方所发送的数据,同时这也是一个阻塞的方法 */
			multicastSocket.receive(udpPacket);
			/* 7. 得到发送过来的数据 */
			String str = new String(udpPacket.getData(),
					udpPacket.getOffset(), udpPacket.getLength());
			System.out.println("接受到组播 "+str);
			this.lock.release();
		} catch (Exception e) {
			Ln.e(e, "接受UDP广播失败");
		}

	}
}
