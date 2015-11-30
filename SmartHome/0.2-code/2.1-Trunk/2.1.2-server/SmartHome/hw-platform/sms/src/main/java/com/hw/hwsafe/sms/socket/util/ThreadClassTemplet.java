package com.hw.hwsafe.sms.socket.util;

import java.util.Date;
import org.apache.log4j.Logger;

public abstract class ThreadClassTemplet extends Thread {
	Logger logger = Logger.getLogger(this.getClass().getName());
	private boolean isRunning = true;
	private long sleepTimes = 10;
	private boolean willStop = false;
	private long pauseSleepTime = 1000;
	private Date lastPauseTime;
	private Date lastGoTime;
	private Date lastRunTime;

	public boolean getIsRunning() {
		return isRunning;
	}

	public Date getLastGoTime() {
		return lastGoTime;
	}

	public String getThreadStateComments() {
		String str = "";
		return str;
	}

	public Date getLastPauseTime() {
		return lastPauseTime;
	}

	public long getSleepTimes() {
		return sleepTimes;
	}

	public ThreadClassTemplet(int sleepTimes) {
		setsleepTimes(sleepTimes);
	}

	public void setsleepTimes(int sleepTimes) {
		this.sleepTimes = sleepTimes;
	}

	public void setPauseSleepTime(long pauseSleepTime) {
		this.pauseSleepTime = pauseSleepTime;
	}

	public Date getLastRunTime() {
		return this.lastRunTime;
	}

	public void pause() {
		logger.fatal(this.getName() + "已暂停" + "  线程ID="
				+ this.getId());
		this.isRunning = false;
		this.lastPauseTime = new Date();
	}

	public synchronized void go() {
		logger.fatal(this.getName() + "开始运行" + "  线程ID="
				+ this.getId());
		this.isRunning = true;
		this.lastGoTime = new Date();
	}

	public void exitThread() {
		this.willStop = true;
	}

	@Override
	public void run() {
		while (true) {
			try {
//				lastRunTime = new Date();

				if (this.willStop) {
					logger.debug(this.getName() + "已中断");
					break;
				}

				if (this.isRunning == false) {
					if (this.pauseSleepTime > 0) {
						Thread.sleep(pauseSleepTime);
					}
				} else {
					runFun();
					if (this.sleepTimes > 0) {
						Thread.sleep(sleepTimes);
					}
				}
			} catch (InterruptedException ex) {
				logger.error(this.getName() + "已中断", ex);
				break;
			} catch (Exception ex) {
				logger.error(this.getName(), ex);
			}
		}
	}

	protected abstract void runFun();
}
