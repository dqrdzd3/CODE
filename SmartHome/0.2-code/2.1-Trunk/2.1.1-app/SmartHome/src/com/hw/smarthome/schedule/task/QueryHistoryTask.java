package com.hw.smarthome.schedule.task;

import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

/**
 * [SH01_02_01]查看历史信息
 * 
 * @author 曾凡
 * @time 2014年7月4日 上午9:00:30
 */
public class QueryHistoryTask extends Task {
	@Override
	public boolean canBePaused() {
		return true;
	}

	@Override
	public boolean canBeStopped() {
		return true;
	}

	@Override
	public boolean supportsCompletenessTracking() {
		return true;
	}

	@Override
	public boolean supportsStatusTracking() {
		return true;
	}

	/**
	 * 执行的任务
	 * 
	 * @author 曾凡
	 * @param context
	 * @throws RuntimeException
	 * @time 2014年6月11日 下午12:35:54
	 */
	public void execute(TaskExecutionContext context)
			throws RuntimeException {

	}
}
