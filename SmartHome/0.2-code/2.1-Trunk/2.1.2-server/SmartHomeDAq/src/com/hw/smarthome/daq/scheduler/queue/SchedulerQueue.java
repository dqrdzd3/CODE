package com.hw.smarthome.daq.scheduler.queue;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.hw.smarthome.daq.po.DAqPo;

public class SchedulerQueue {
	/** 存储解析异常的数据 */
	private static Map<Byte, Map<String, DAqPo>> errorCode = new ConcurrentHashMap<Byte, Map<String, DAqPo>>();
	/** 发送给前置机前进行处理(组帧) */
	private static Queue<DAqPo> toFrontWait = new ConcurrentLinkedQueue<DAqPo>();
	/** 发送给前置机，前置机进行加密，然后处理发送给终端 */
	private static Queue<DAqPo> toFront = new ConcurrentLinkedQueue<DAqPo>();

	private static Queue<DAqPo> toServer = new ConcurrentLinkedQueue<DAqPo>();
	private static Queue<DAqPo> fromServer = new ConcurrentLinkedQueue<DAqPo>();

	public static DAqPo pollToFrontWait() {
		return toFrontWait.poll();
	}

	public static void addToFrontWait(DAqPo frame) {
		toFrontWait.add(frame);
	}

	public static Queue<DAqPo> getToFrontWait() {
		return toFrontWait;
	}

	public static void setToFrontWait(Queue<DAqPo> toFrontWait) {
		SchedulerQueue.toFrontWait = toFrontWait;
	}

	public static DAqPo pollToServer() {
		return toServer.poll();
	}

	public static void addToServer(DAqPo frame) {
		toServer.add(frame);
	}

	public static Queue<DAqPo> getToServer() {
		return toServer;
	}

	public static void setToServer(Queue<DAqPo> toServer) {
		SchedulerQueue.toServer = toServer;
	}

	public static DAqPo pollFromServer() {
		return fromServer.poll();
	}

	public static void addFromServer(DAqPo frame) {
		fromServer.add(frame);
	}

	public static Queue<DAqPo> getFromServer() {
		return fromServer;
	}

	public static void setFromServer(Queue<DAqPo> fromServer) {
		SchedulerQueue.fromServer = fromServer;
	}

	public static DAqPo pollToFront() {
		return toFront.poll();
	}

	public static void addToFront(DAqPo frame) {
		toFront.add(frame);
	}

	public static DAqPo getErrorCode(byte afn, String sensorId) {
		if (errorCode.containsKey(afn)) {
			if (errorCode.get(afn).containsKey(sensorId)) {
				return errorCode.get(afn).get(sensorId);
			}
		}
		return null;
	}

	public static Map<String, DAqPo> getErrorCodes(byte afn) {
		if (errorCode.containsKey(afn)) {
			return errorCode.get(afn);
		}
		return null;
	}

	public static void putErrorCode(byte afn, String sensorId,
			DAqPo po) {
		if (errorCode.containsKey(afn)) {
			errorCode.get(po.getAfn()).put(sensorId, po);
		} else {
			Map<String, DAqPo> map = new ConcurrentHashMap<String, DAqPo>();
			map.put(po.getSensorId(), po);
			errorCode.put(po.getAfn(), map);
		}
	}

	public static Map<Byte, Map<String, DAqPo>> getErrorCode() {
		return errorCode;
	}

	public static void setErrorCode(
			Map<Byte, Map<String, DAqPo>> errorCode) {
		SchedulerQueue.errorCode = errorCode;
	}

	public static Queue<DAqPo> getToFront() {
		return toFront;
	}

	public static void setToFront(Queue<DAqPo> toFront) {
		SchedulerQueue.toFront = toFront;
	}

}
