package com.hw.smarthome.daq.front.queue;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

import com.hw.smarthome.daq.po.DAqPo;

public class FrontQueue {
	/** 曾凡 20150901 汉威无线智能终端（分布式大气监测，室外机）<SN，Socket连接> */
	private static Map<String, Socket> atmospherCache = new HashMap<String, Socket>();
//	private static Queue<DAqPo> atmospherQueue = new ConcurrentLinkedDeque<DAqPo>();

	/** 接受的连接缓存 */
	private static Queue<DAqPo> receiveCache = new ConcurrentLinkedDeque<DAqPo>();
	/** 自启动以后 传感器Id，设备连接信息（主要使用最后一次的连接ip和端口，有可能每次都不一样） */
	private static Map<String, DAqPo> currentLinksMap = new ConcurrentHashMap<String, DAqPo>();
	
	/** 发送到任务调度的队列 */
	private static Queue<DAqPo> toScheduler = new ConcurrentLinkedDeque<DAqPo>();
	/** 发送给硬件 */
	private static Queue<DAqPo> toTerminal = new ConcurrentLinkedDeque<DAqPo>();

	public static DAqPo pollToTerminalData() {
		return toTerminal.poll();
	}

	public static void addToTerminalData(DAqPo link) {
		toTerminal.add(link);
	}

	public static Queue<DAqPo> getToTerminal() {
		return toTerminal;
	}

	public static void setToTerminal(Queue<DAqPo> toTerminal) {
		FrontQueue.toTerminal = toTerminal;
	}

	public static DAqPo getToSchedulerData() {
		return toScheduler.poll();
	}

	public static void addToSchedulerData(DAqPo link) {
		toScheduler.add(link);
	}

	/**
	 * 获取最后的一个连接信息
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2015年1月5日 下午6:50:50
	 */
	public static DAqPo getLatestLinkData() {
		return receiveCache.poll();
	}

	/**
	 * 获取最后的一个连接信息
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2015年1月5日 下午6:50:50
	 */
	public static void addLatestLinkData(DAqPo link) {
		receiveCache.add(link);
	}

	/**
	 * 获取这个设备编号的连接信息
	 * 
	 * @author 曾凡
	 * @param sensorId
	 * @return
	 * @time 2015年1月5日 下午6:50:22
	 */
	public static DAqPo getLink(String sensorId) {
		if (currentLinksMap.containsKey(sensorId)) {
			return currentLinksMap.get(sensorId);
		} else {
			return null;
		}

	}

	public static void putLink(String sensorId, DAqPo link) {
		currentLinksMap.put(sensorId, link);

	}

	public static Queue<DAqPo> getReceiveCache() {
		return receiveCache;
	}

	public static void setReceiveCache(Queue<DAqPo> receiveCache) {
		FrontQueue.receiveCache = receiveCache;
	}

	public static Map<String, DAqPo> getCurrentLinksMap() {
		return currentLinksMap;
	}

	public static void setCurrentLinksMap(
			Map<String, DAqPo> currentLinksMap) {
		FrontQueue.currentLinksMap = currentLinksMap;
	}

	public static Queue<DAqPo> getToScheduler() {
		return toScheduler;
	}

	public static void setToScheduler(Queue<DAqPo> toScheduler) {
		FrontQueue.toScheduler = toScheduler;
	}

	public static Map<String, Socket> getAtmospherCache() {
		return atmospherCache;
	}

	public static void setAtmospherCache(
			Map<String, Socket> atmospherCache) {
		FrontQueue.atmospherCache = atmospherCache;
	}

//	public static Queue<DAqPo> getAtmospherQueue() {
//		return atmospherQueue;
//	}
//
//	public static void setAtmospherQueue(
//			Queue<DAqPo> atmospherQueue) {
//		FrontQueue.atmospherQueue = atmospherQueue;
//	}

}
