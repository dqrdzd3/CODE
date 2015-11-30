package com.hw.weidou.signal.row;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.hw.util.StringUtil;
import com.hw.util.ThreadClassTemplet;
import com.hw.weidou.signal.constant.SignalConstant;
import com.hw.weidou.signal.sample.SignalFourier;

/**
 * 转换为二进制信号并缓存
 * 
 * @author 曾凡
 * @time 2014年9月15日 下午3:05:45
 */
public class SignalBinary {
	private boolean isRun = false;
	private Queue<String> binarySignalQueue = new ConcurrentLinkedQueue<String>();
	private static SignalBinary binary = null;

	public static SignalBinary getInstance() {
		if (binary == null) {
			binary = new SignalBinary();
		}
		return binary;
	}

	public SignalBinary() {
		init();
	}

	private void init() {

	}

	/**
	 * 开始将模拟信号转换为数据信号
	 * 
	 * @author 曾凡
	 * @time 2014年9月18日 下午6:34:38
	 */
	public void startSampling() {
		new Thread(new Fourier2BinaryThread(-1)).start();
	}

	private class Fourier2BinaryThread extends
			ThreadClassTemplet {
		private StringBuilder sb = new StringBuilder();

		public Fourier2BinaryThread(int sleepTimes) {
			super(sleepTimes);
		}

		@Override
		protected void runFun() {
			/* 取出缓存的信号 */
			String fourierSignal = SignalFourier.getInstance()
					.getSampleSignalQueue().poll();
//			binarySignalQueue.add(e);
//			for(fourierSignal.replace(oldChar, newChar))
//			
//			/* 模拟信号的暴行数据是低位在前高位在上需要逆序排列*/
//			StringUtil.revStr(
		}
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

	public Queue<String> getBinarySignalQueue() {
		return binarySignalQueue;
	}

	public void setBinarySignalQueue(
			Queue<String> binarySignalQueue) {
		this.binarySignalQueue = binarySignalQueue;
	}
}
