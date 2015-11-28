package com.hw.smarthome.daq.server.socket.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.hw.smarthome.daq.po.DAqPo;

public class SocketQueue {
//	private static Queue<DAqPo> fromServerTask = new ConcurrentLinkedQueue<DAqPo>();
	private static Queue<DAqPo> toServerTask = new ConcurrentLinkedQueue<DAqPo>();

//	public static Queue<DAqPo> getFromServerTask() {
//		return fromServerTask;
//	}
//
//	public static void setFromServerTask(
//			Queue<DAqPo> fromServerTask) {
//		SocketQueue.fromServerTask = fromServerTask;
//	}
	public static void addToServerTask(DAqPo po){
		toServerTask.add(po);
	}
	public static Queue<DAqPo> getToServerTask() {
		return toServerTask;
	}

	public static void setToServerTask(Queue<DAqPo> toServerTask) {
		SocketQueue.toServerTask = toServerTask;
	}

}
