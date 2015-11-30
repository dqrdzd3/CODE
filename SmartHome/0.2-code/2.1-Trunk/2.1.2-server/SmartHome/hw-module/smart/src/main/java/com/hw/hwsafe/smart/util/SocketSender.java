package com.hw.hwsafe.smart.util;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.hw.hwsafe.attachment.util.AppFileUpLoad;
import com.hw.smarthome.daq.po.DAqPo;

public class SocketSender {
	private static SocketSender instance = null;
	public static SocketSender getInstance(){
		if(instance==null){
			instance = new SocketSender();
		}
		return instance;
	}
	private Socket client = null;

	public void send(DAqPo po) {
		try {
			if (client == null||client.isClosed()) {
				client = new Socket(AppFileUpLoad.socket_remote_ip, Integer.parseInt(AppFileUpLoad.socket_remote_port));
			}
			ObjectOutputStream oout = new ObjectOutputStream(
					new BufferedOutputStream(
							client.getOutputStream()));
			oout.writeObject(po);
			oout.flush();
			oout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
