package com.hw.weidou.test;

import android.app.Activity;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;

public class TestAudioRecordConsole extends Activity {
	private AudioRecord ar;
	private int bs;
	private static int SAMPLE_RATE_IN_HZ = 8000;
	private boolean isRun = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		startRecord();
	}

	public void startRecord() {
		bs = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT);
		ar = new AudioRecord(MediaRecorder.AudioSource.MIC,
				SAMPLE_RATE_IN_HZ,
				AudioFormat.CHANNEL_CONFIGURATION_MONO,
				AudioFormat.ENCODING_PCM_16BIT, bs);
		new Thread(new AudioRecordThread()).start();
	}

	private int SPACE = 2;// 间隔取样时间

	class AudioRecordThread implements Runnable {
		short[] buffer = new short[bs];
		private StringBuilder sb = null;
		private double BASE_DB = 00.0;

		public AudioRecordThread() {
			ar.startRecording();
			isRun = true;
		}

		@Override
		public void run() {
			while (isRun) {
				if (sb != null) {
					if (sb.length() > 10000) {
						System.out.println(sb.toString()); // 方便统计
						// Log.d(TAG, "分贝值：" + sb.toString());
						sb = null;
					}
				}
				int r = ar.read(buffer, 0, bs);
				int v = 0;
				if (sb == null) {
					sb = new StringBuilder();
				}
				// // 将 buffer 内容取出，进行平方和运算
				// for (int i = 0; i < buffer.length; i++) {
				// // 这里没有做运算的优化，为了更加清晰的展示代码
				// v += buffer[i] * buffer[i];
				// }
				// int db = (int) (10 * Math.log10(v / (double) r));
				int db = 0;
				short tempP = 0;
				short temp = 0;
				short hightFilterSize = 20000; // 过滤器阀值
				short lowFilterSize = -20000; // 过滤器阀值

				for (int i = 0; i < buffer.length; i++) {
					temp = buffer[i];
					tempP = i != 0 ? buffer[i] : 0;
					if (temp > hightFilterSize) {
						sb.append("0");
					} else if (temp < lowFilterSize) {
						sb.append("1");
					} else {
						sb.append("");
					}
					// if (temp != 0) {
					// sb.append(buffer[i] + "").append(" ");
					// }

				}

				// if (db >= BASE_DB) {
				// sb.append(((int) db) + "").append(" ");
				// // Log.d(TAG,"分贝值："+db);
				// } else {
				// sb.append("0").append(" ");
				// // Log.d(TAG,"分贝值："+db);
				// }
				// 平方和除以数据总长度，得到音量大小。可以获取白噪声值，然后对实际采样进行标准化。
				// 如果想利用这个数值进行操作，建议用 sendMessage 将其抛出，在 Handler 里进行处理。
				try {
					Thread.sleep(SPACE);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void pause() {
		// 在调用本线程的 Activity 的 onPause 里调用，以便 Activity 暂停时释放麦克风
		isRun = false;
	}

	@Override
	protected void onDestroy() {
		pause();
		super.onDestroy();
	}
}