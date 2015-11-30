package com.hw.weidou.signal.sample;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;

import com.hw.util.StringUtil;
import com.hw.util.ThreadClassTemplet;
import com.hw.weidou.signal.constant.SignalConstant;

/**
 * 模拟信号取样后缓存
 * 
 * @author 曾凡
 * @time 2014年9月15日 下午3:02:43
 */
public class SignalFourier {
	private AudioRecord ar;
	private int bs;
	private static int SAMPLE_RATE_IN_HZ = 8000;
	private boolean isRun = false;
	private Queue<String> sampleSignalQueue = new ConcurrentLinkedQueue<String>();

	private static SignalFourier fourier = null;

	public static SignalFourier getInstance() {
		if (fourier == null) {
			fourier = new SignalFourier();
		}
		return fourier;
	}

	public SignalFourier() {
		initRecord();
	}

	private void initRecord() {
		bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT);
		ar = new AudioRecord(MediaRecorder.AudioSource.MIC,
				SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, bs);
	}

	/**
	 * 启动采样线程
	 * 
	 * @author 曾凡
	 * @time 2014年9月18日 下午6:26:50
	 */
	public void startRecord() {
		new AudioRecordThread(2).start();
	}

	private class AudioRecordThread extends ThreadClassTemplet {
		short[] buffer = new short[bs];
		private StringBuilder sb = null;
		private String content = "";

		public AudioRecordThread(int sleepTimes) {
			super(sleepTimes);
			ar.startRecording();
		}

		@Override
		protected void runFun() {
			if (sb != null) {
				if (sb.length() > 10000) {
					content = sb.toString();
					content = noiseFilter(content);
					content = contentAdapter(content);
					splitContentRecursion(content);
					sb = null;
				}
			}
			if (sb == null) {
				sb = new StringBuilder();
			}
			short temp = 0;
			short hightFilterSize = 20000; // 过滤器阀值
			short lowFilterSize = -20000; // 过滤器阀值

			for (int i = 0; i < buffer.length; i++) {
				temp = buffer[i];
				if (temp > hightFilterSize) {
					sb.append("0");
				} else if (temp < lowFilterSize) {
					sb.append("1");
				} else {
					sb.append(" ");
				}
			}
		}

		private String noiseFilter(String content) {
			content = content.replace("01111110", "011110");
			content = content.replace("10000001", "100001");

			content = content.replace("01110", "0110");
			content = content.replace("10001", "1001");
			return content;
		}

		private String contentAdapter(String content) {
			content = content.replace("1001", "101");
			content = content.replace("0110", "010");
			return content;
		}

		private void splitContentRecursion(String content) {
			/* 分段SPACE的索引 */
			int tempIndex = 0;
			/* 用来保存缓存的分段数据 */
			String tempChars = "";

			tempIndex = content
					.lastIndexOf(SignalConstant.FOURIER_SPACE)
					+ SignalConstant.FOURIER_SPACE.length() - 2;
			tempChars = content.substring(tempIndex,
					content.length());
			sampleSignalQueue.add(tempChars.substring(0, 20));
			content = content.substring(0, tempIndex
					- SignalConstant.FOURIER_SPACE.length());
			if ((content != null && !"".equals(content))
					&& content.length() > 20) {
				splitContentRecursion(content);
			}
		}
	}

	public Queue<String> getSampleSignalQueue() {
		return sampleSignalQueue;
	}

	public void setSampleSignalQueue(
			Queue<String> sampleSignalQueue) {
		this.sampleSignalQueue = sampleSignalQueue;
	}

	public boolean isRun() {
		return isRun;
	}

	public void setRun(boolean isRun) {
		this.isRun = isRun;
	}

}
