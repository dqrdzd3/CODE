package com.hw.smarthome.schedule.task;

import it.sauronsoftware.cron4j.Task;
import it.sauronsoftware.cron4j.TaskExecutionContext;

/**
 * @author 曾凡
 * @time 2014年7月10日 上午9:45:25
 */
public class UpdateHomeTask extends Task {
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
