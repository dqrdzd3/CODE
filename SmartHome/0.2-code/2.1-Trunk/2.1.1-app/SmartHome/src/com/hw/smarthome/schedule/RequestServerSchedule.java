package com.hw.smarthome.schedule;

import it.sauronsoftware.cron4j.Scheduler;

import com.hw.smarthome.schedule.task.QueryHistoryTask;
import com.hw.smarthome.schedule.task.UpdateHomeTask;

/**
 * 请求服务器的任务调度
 * 
 * @author 曾凡
 * @time 2014年6月11日 下午12:50:13
 */
public class RequestServerSchedule {

	private static RequestServerSchedule schedule;

	public RequestServerSchedule getInstance() {
		if (schedule != null) {
			schedule = new RequestServerSchedule();
		}
		return schedule;
	}

	private Scheduler historyScheduler;
	private Scheduler homeScheduler;

	public RequestServerSchedule() {
		/* 启动[SH01_02_01]任务调度 */
		startHistorySchedule();
		/* 刷新主页面历史内容 */
		startHomeViewSchedule();
	}

	/**
	 * 启动[SH01_02_01]任务调度
	 * 
	 * @author 曾凡
	 * @time 2014年7月4日 上午9:40:59
	 */
	public void startHistorySchedule() {

		/* 实例化任务 */
		QueryHistoryTask historyTask = new QueryHistoryTask();
		/* 启动任务调度 */
		historyScheduler = new Scheduler();
		/* 每天早上5:30分执行一次任务调度 */
		historyScheduler.schedule("30 5 * * *", historyTask);
		historyScheduler.start();

	}

	public void stopHistory() {
		historyScheduler.stop();
	}

	/**
	 * 刷新主页面历史内容
	 * 
	 * @author 曾凡
	 * @time 2014年7月4日 上午9:40:59
	 */
	public void startHomeViewSchedule() {
		/* 实例化任务 */
		UpdateHomeTask homeTask = new UpdateHomeTask();
		/* 启动任务调度 */
		homeScheduler = new Scheduler();
		/* 每天早上5:30分执行一次任务调度 */
		homeScheduler.schedule("* * * * *", homeTask);
		homeScheduler.start();
	}

	public void stopHomeViewHistory() {
		historyScheduler.stop();
	}
}
