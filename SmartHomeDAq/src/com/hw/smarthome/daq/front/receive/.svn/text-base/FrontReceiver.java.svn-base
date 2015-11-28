package com.hw.smarthome.daq.front.receive;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.front.queue.FrontQueue;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.util.ByteUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

/**
 * 前置机接收只有一个线程
 * 
 * @author 曾凡
 * @time 2015年1月6日 下午5:16:05
 */
public class FrontReceiver extends ThreadClassTemplet {
	private Logger log = Logger.getLogger(this.getClass());
	// 建立数据包套接字用来接收数据包
	public static DatagramSocket udp = null;
	// 建立数据报包用来实现无连接包投递服务
	private DatagramPacket packet = null;
	private DAqPo link = null;
	// 建立一个接受数据的缓冲区
	private byte[] buff = new byte[2048];
	public static int sumLink;

	public FrontReceiver(int sleepTimes) {
		super(sleepTimes);
		init();
	}

	private void init() {
		try {
			log.info(HintConstant.UDP_LINK_CREATE_START);
			udp = new DatagramSocket(SysConstant.FRONT_UDP_PORT);
			udp.setTrafficClass(0x04 | 0x10);
		} catch (Exception e) {
			Ln.e(this.getClass(),
					HintConstant.UDP_LINK_CREATE_FAIL, e);
			/* 再异常就关闭应用程序 */
			System.exit(0);
		}

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
				Ln.e(this.getClass(),
						HintConstant.UDP_SOCKET_FAIL, e);
				System.exit(0);
			}
			// 获得数据包的缓冲区从0下标到缓冲区尾指针的数据，返回的是接收的数据和发送的数据
			link = new DAqPo();
			byte[] temps = null;
			temps = new byte[packet.getLength()];
			System.arraycopy(packet.getData(), 0, temps, 0,
					temps.length);
			link.setReceiveData(temps);
			link.setPort(packet.getPort());
			link.setIp(packet.getAddress());
			link.setDate(new Date());
			FrontQueue.addLatestLinkData(link);
			sumLink++;
			if (Ln.IS_DEBUG) {
				log.debug(link.getIp() + ":" + link.getPort()
						+ " "
						+ ByteUtils.toHex(link.getReceiveData()));
			}
		} catch (Exception e) {
			Ln.e(this.getClass(),
					HintConstant.UDP_LINK_RECEIVE_FAIL, e);
		}
	}
}
