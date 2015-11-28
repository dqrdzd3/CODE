package com.hw.smarthome.daq.server.socket;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.hw.smarthome.daq.constant.HintConstant;
import com.hw.smarthome.daq.constant.SysConstant;
import com.hw.smarthome.daq.po.DAqPo;
import com.hw.smarthome.daq.server.socket.queue.SocketQueue;
import com.hw.smarthome.daq.util.ClearUtils;
import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;

public class SocketSender extends ThreadClassTemplet {
	private Logger log = Logger.getLogger(this.getClass());
	private Socket client;

	public SocketSender(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		DAqPo po = null;
		if (!SocketQueue.getToServerTask().isEmpty()) {
			po = SocketQueue.getToServerTask().poll();
			try {
				if (SocketReceiver.clientIp == null) {
					log.warn(HintConstant.SOCKET_CLIENT_LINK_ERROR+":"+po.toString());
					return;
				}
				if (client == null || client.isClosed()) {
					client = new Socket(
							SocketReceiver.clientIp,
							SysConstant.SCHEDULER_SOCKET_SEND_PORT);
				}
				
				/**
				 * 设置一个Object输出流
				 */
				ObjectOutputStream oout = new ObjectOutputStream(
						new BufferedOutputStream(
								client.getOutputStream()));
				oout.writeObject(po);
				oout.flush();
				oout.close();
			} catch (Exception e) {
				Ln.e(this.getClass(),
						HintConstant.SOCKET_CLIENT_ERROR, e);
			} finally{
				if(po!=null){
					ClearUtils.clearSend(po);
				}
			}
		}
	}
}
