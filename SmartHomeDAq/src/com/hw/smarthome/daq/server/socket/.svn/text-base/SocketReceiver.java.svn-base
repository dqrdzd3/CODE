package com.hw.smarthome.daq.server.socket;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

public class SocketReceiver extends ThreadClassTemplet {

	private ServerSocket server = null;
	private Socket client = null;
	public static InetAddress clientIp;

	public SocketReceiver(int sleepTimes) {
		super(sleepTimes);
		try {
			server = new ServerSocket(
					SysConstant.SCHEDULER_SOCKET_RECEIVE_PORT);
		} catch (IOException e) {
			Ln.e(this.getClass(),
					HintConstant.SOCKET_SERVER_ERROR, e);
		}
	}

	@Override
	protected void runFun() {
		try {
			client = server.accept();
			clientIp = client.getInetAddress();
			ObjectInputStream oin = new ObjectInputStream(
					new BufferedInputStream(
							client.getInputStream()));
			Object obj = oin.readObject();
			oin.close();
			SchedulerQueue.addFromServer((DAqPo) obj);
		} catch (Exception e) {

			Ln.e(this.getClass(),
					HintConstant.SOCKET_SERVER_ERROR, e);
		}
	}
}
