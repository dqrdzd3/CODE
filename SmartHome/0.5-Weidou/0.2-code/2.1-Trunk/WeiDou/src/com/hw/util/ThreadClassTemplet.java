package com.hw.util;

import java.util.Date;

/**
 * 线程模板
 * 
 * @author 曾凡
 * @time 2014年9月18日 下午6:48:14
 */
public abstract class ThreadClassTemplet extends Thread {
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

	public void Pause() {
		Ln.i(this.getName() + "已暂停" + "  线程ID=" + this.getId());
		this.isRunning = false;
		this.lastPauseTime = new Date();
	}

	public synchronized void Go() {
		Ln.i(this.getName() + "开始运行" + "  线程ID=" + this.getId());
		this.isRunning = true;
		this.lastGoTime = new Date();
	}

	public void ExitThread() {
		this.willStop = true;
	}

	@Override
	public void run() {
		while (true) {
			try {
				lastRunTime = new Date();

				if (this.willStop) {
					Ln.d(this.getName() + "已中断");
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
				Ln.e(this.getName() + "已中断", ex);
				break;
			} catch (Exception ex) {
				Ln.e(this.getName(), ex);
			}
		}
	}

	protected abstract void runFun();
}
