package com.hw.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.hw.util.ByteUtils;

public class CopyOfUdpClient {
	private static final int UDP_PORT = 59999;
//	private static final int UDP_PORT = 60001;
	private DatagramSocket udp = null;
	private DatagramPacket recdp = null;

	public CopyOfUdpClient(byte[] bytes) {
		try {
			udp = new DatagramSocket();
			// 设置广播开关
			udp.setBroadcast(false);
//			recdp = new DatagramPacket(bytes, bytes.length,
//					InetAddress.getByName("219.150.156.51"), UDP_PORT);
			recdp = new DatagramPacket(bytes, bytes.length,
					InetAddress.getByName("127.0.0.1"), UDP_PORT);
			udp.send(recdp);
			udp.close();

		} catch (SocketException e) {
			System.out.println("创建端口监听客户端失败" + e);
			e.printStackTrace();
		} catch (UnknownHostException ex) {
			System.out.println("UnknownHostException" + ex);
			ex.printStackTrace();
		} catch (IOException e) {
			System.out.println("发送失败" + e);
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		/* afn 0x08*/
		String hexString = "68004068B6735B0E30F4B00D7FCDBC47A22057372C7BC56C341F3229DD103F08C54427C9C69FE8AE7840526BD41068ACBA91DF7AA7D8FA44214F4D8664CCC74345A41C92A216";
		byte[] bytes = ByteUtils.toBytes(hexString);
		while(true){
			new CopyOfUdpClient(bytes);
			Thread.sleep(10*1000);
//			Thread.sleep(-1);
		}
	}

}
