package com.hw.weidou.test;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.hw.util.Ln;

/**
 * 测试录音
 * 
 * @author 曾凡
 * @time 2014年9月15日 下午3:43:07
 */
public class TestMediaRecordConsole extends Activity {
	private final String TAG = "MediaRecord";
	private MediaRecorder mMediaRecorder;
	public static final int MAX_LENGTH = 1000 * 60 * 10;// 最大录音时长1000*60*10;
	private String sdDir = Environment
			.getExternalStorageDirectory().toString();
	private String filePath = sdDir + "/smarthome/voice.wav";
	private long startTime;
	private long endTime;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		startRecord();
	}

	private void startRecord() {
		// 开始录音
		/* ①Initial：实例化MediaRecorder对象 */
		if (mMediaRecorder == null)
			mMediaRecorder = new MediaRecorder();
		try {
			/* ②setAudioSource/setVedioSource */
			mMediaRecorder
					.setAudioSource(MediaRecorder.AudioSource.MIC);// 设置麦克风
			/* ②设置音频文件的编码：AAC/AMR_NB/AMR_MB/Default 声音的（波形）的采样 */
//			mMediaRecorder
//					.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
			mMediaRecorder
			.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
			/*
			 * ②设置输出文件的格式：THREE_GPP/MPEG-4/RAW_AMR/Default THREE_GPP(3gp格式
			 * ，H263视频/ARM音频编码)、MPEG-4、RAW_AMR(只支持音频且音频编码要求为AMR_NB)
			 */
			mMediaRecorder
					.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

			/* ③准备 */
			File file = new File(filePath);
			if (!file.exists()) {
				file.createNewFile();
			}
			mMediaRecorder.setOutputFile(filePath);
			mMediaRecorder.setMaxDuration(MAX_LENGTH);
			mMediaRecorder.prepare();
			/* ④开始 */
			mMediaRecorder.start();
			// AudioRecord audioRecord.
			/* 获取开始时间* */
			startTime = System.currentTimeMillis();

			new Thread(new AudioRecordThread()).start();

			Log.i("ACTION_START", "startTime" + startTime);
		} catch (IllegalStateException e) {
			Log.i(TAG,
					"call startAmr(File mRecAudioFile) failed!"
							+ e.getMessage());
		} catch (Exception e) {
			Log.i(TAG,
					"call startAmr(File mRecAudioFile) failed!"
							+ e.getMessage());
		}
	}

	private int SPACE = 3;// 间隔取样时间

	class AudioRecordThread implements Runnable {
		@Override
		public void run() {
			while (true) {
				try {
					updateMicStatus();// 往文件中写入裸数据
					Thread.sleep(SPACE);
				} catch (Exception e) {
					Ln.e(e, "音频" + SPACE + "毫秒异常");
				}
			}
		}
	}

	/**
	 * 更新话筒状态
	 * 
	 */
	private int BASE = 1;
	private double BASE_DB = 00.0;
	private StringBuilder sb = null;

	public void updateMicStatus() {
		if (sb != null) {
			if (sb.length() > 1000) {
				System.out.println(sb.toString()); // 方便统计
//				Log.d(TAG, "分贝值：" + sb.toString());
				sb = null;
			}
		}

		if (mMediaRecorder != null) {
			double ratio = (double) mMediaRecorder
					.getMaxAmplitude() / BASE;
			double db = 0.0;// 分贝
			if (ratio > 1)
				db = 20 * Math.log10(ratio);
			if (sb == null) {
				sb = new StringBuilder();
			}
			if (db >= BASE_DB) {
				sb.append(((int)db)+"").append(" ");
				// Log.d(TAG,"分贝值："+db);
			} else {
				sb.append("0").append(" ");
				// Log.d(TAG,"分贝值："+db);
			}

		}
	}

	/**
	 * 停止录音
	 * 
	 */
	public long stopRecord() {
		if (mMediaRecorder == null)
			return 0L;
		endTime = System.currentTimeMillis();
		Log.i("ACTION_END", "endTime" + endTime);
		mMediaRecorder.stop();
		mMediaRecorder.reset();
		mMediaRecorder.release();
		mMediaRecorder = null;
		Log.i("ACTION_LENGTH", "Time" + (endTime - startTime));
		return endTime - startTime;
	}

	@Override
	protected void onDestroy() {
		stopRecord();
		super.onDestroy();
	}
}
