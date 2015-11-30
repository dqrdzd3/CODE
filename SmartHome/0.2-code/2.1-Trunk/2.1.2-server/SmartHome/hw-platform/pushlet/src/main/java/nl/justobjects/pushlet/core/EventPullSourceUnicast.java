// Copyright (c) 2000 Just Objects B.V. <just@justobjects.nl>
// Distributable under LGPL license. See terms of license at gnu.org.

package nl.justobjects.pushlet.core;

import nl.justobjects.pushlet.util.Log;

/**
 * Abstract Event source from which Events are pulled.(Unicast)
 *
 * @version $Id: EventPullSourceUnicast.java,v 1.15 2007/11/23 14:33:07 justb Exp $
 * @author Just van den Broecke - Just Objects &copy;
 **/

/**
 * @author 马宁
 * @创建时间 2013-09-02 11:32
 * 
 * @修改人 马宁
 * @修改时间 2013-09-04 15:21
 * @修改内容 不再在这里处理推送业务,而是在抽象方法中去完成
 */
abstract public class EventPullSourceUnicast implements EventSource, Runnable {
	private volatile boolean alive = false;
	private volatile boolean active = false;
	private static int threadNum = 0;
	private Thread thread;

	public EventPullSourceUnicast() {
	}

	/**
	 * 获取轮询时间(毫秒)
	 */
	abstract protected long getSleepTime();

	/**
	 * 推送消息
	 */
	abstract protected void pullEvent() throws Exception;

	public void start() {
		thread = new Thread(this, "EventPullSourceUnicast-" + (++threadNum));
		thread.setDaemon(true);
		thread.start();
	}

	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * Stop the event generator thread.
	 */
	public void stop() {
		alive = false;

		if (thread != null) {
			thread.interrupt();
			thread = null;
		}

	}

	/**
	 * Activate the event generator thread.
	 */
	synchronized public void activate() {
		if (active) {
			return;
		}
		active = true;
		if (!alive) {
			start();
			return;
		}
		Log.debug(getClass().getName() + ": notifying...");
		notifyAll();
	}

	/**
	 * Deactivate the event generator thread.
	 */
	public void passivate() {
		if (!active) {
			return;
		}
		active = false;
	}

	/**
	 * Main loop: sleep, generate event and publish.
	 */
	public void run() {
		Log.debug(getClass().getName() + ": starting...");
		alive = true;
		while (alive) {
			try {

				Thread.sleep(getSleepTime());

				// Stopped during sleep: end loop.
				if (!alive) {
					break;
				}

				// If passivated wait until we get
				// get notify()-ied. If there are no subscribers
				// it wasts CPU to remain producing events...
				synchronized (this) {
					while (!active) {
						Log.debug(getClass().getName() + ": waiting...");
						wait();
					}
				}

			} catch (InterruptedException e) {
				break;
			}

			try {
				pullEvent();
			} catch (Throwable t) {
				Log.warn("EventPullSourceUnicast exception while unicasting ", t);
				t.printStackTrace();
			}
		}
		Log.debug(getClass().getName() + ": stopped");
	}
}
