package com.hw.smarthome.daq.main.service.impl;

import com.hw.smarthome.daq.front.queue.FrontQueue;
import com.hw.smarthome.daq.main.po.QueueViewPo;
import com.hw.smarthome.daq.main.service.DAqService;
import com.hw.smarthome.daq.scheduler.queue.SchedulerQueue;
import com.hw.smarthome.daq.server.socket.queue.SocketQueue;

public class DAqServiceImpl implements DAqService {
	private static DAqService instance = null;

	public static DAqService getInstance() {
		if (instance == null) {
			instance = new DAqServiceImpl();
		}
		return instance;
	}

	@Override
	public QueueViewPo monitorQueue() {
		QueueViewPo view = new QueueViewPo();
		/* [前置机]接受的[连接缓存]队列 */
		view.setReceiveCache(FrontQueue.getReceiveCache().size());
		/* [连接缓存]发送到[任务调度]的队列 */
		view.setToScheduler(FrontQueue.getToScheduler().size());
		/* [前置机]发送给[硬件]的队列 */
		view.setToTerminal(FrontQueue.getToTerminal().size());
		/* [任务调度]发送给[前置机]待处理队列 */
		view.setToFrontWait(SchedulerQueue.getToFrontWait()
				.size());
		/* [任务调度]发送给[前置机]的已处理队列 */
		view.setToFront(SchedulerQueue.getToFront().size());
		/* [任务调度]发送给[服务端]队列 */
		view.setToServer(SchedulerQueue.getToServer().size());
		/* [任务调度]接收[服务端]队列 */
		view.setFromServer(SchedulerQueue.getFromServer().size());

		// /* [服务端]接收[采集]任务的未处理队列 */
		// view.setSocketToServer(SocketQueue.getFromServerTask()
		// .size());
		/* [采集]发送给[服务端]任务的未处理队列 */
		view.setSocketToServer(SocketQueue.getToServerTask()
				.size());
		return view;
	}
}
