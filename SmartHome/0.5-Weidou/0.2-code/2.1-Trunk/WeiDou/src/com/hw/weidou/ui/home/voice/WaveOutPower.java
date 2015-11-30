package com.hw.weidou.ui.home.voice;

import java.util.Date;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年10月16日 上午10:21:02
 */
public class WaveOutPower {
	private int sampleRateInHz = 8000; // 采样率
	private int mChannel = AudioFormat.CHANNEL_CONFIGURATION_MONO;// 声道 ：单声道
	private int mSampBit = AudioFormat.ENCODING_PCM_16BIT;// 采样精度 :16bit
	private AudioTrackZThread audioTrackZThread;
	private boolean isRunning = false;
	private AudioTrack audioTrackz;

	private static WaveOutPower instance;

	public static WaveOutPower getInstance() {
		if (instance == null) {
			instance = new WaveOutPower();
		}
		return instance;
	}

	public void palyWaveZ() {
		int bufferSize = AudioTrack.getMinBufferSize(
				sampleRateInHz, mChannel, mSampBit);
		audioTrackz = new AudioTrack(AudioManager.STREAM_SYSTEM,
				sampleRateInHz, mChannel, mSampBit,
				bufferSize * 2, AudioTrack.MODE_STREAM);
		audioTrackz.setStereoVolume(1.0f, 0.0f);
		audioTrackz.play();
		audioTrackZThread = new AudioTrackZThread();
		audioTrackZThread.start();

		TimeMonitor timer = new TimeMonitor();
		timer.start();
	}

	public void colseWaveOutLow() {
		if (audioTrackz != null) {
			if (!AudioTrackZThread.interrupted()) {
				isRunning = false;
			}
			audioTrackz.stop();
			audioTrackz.release();
		}
	}

	private class AudioTrackZThread extends Thread {
		private short m_bitDateZ[] = new short[sampleRateInHz];

		@Override
		public void run() {
			isRunning = true;
			for (int i = 0; i < sampleRateInHz; i++) {
				m_bitDateZ[i] = (short) -1000;
			}

			int m_bitDateZSize = m_bitDateZ.length;
			do {
				audioTrackz.write(m_bitDateZ, 0, m_bitDateZSize);
			} while (isRunning);
			super.run();
		}
	}

	/**
	 * 声音只持续发出1秒
	 * 
	 * @author 曾凡
	 * @time 2014年11月11日 下午1:36:22
	 */
	private class TimeMonitor extends Thread {
		@Override
		public void run() {
			super.run();
			long bgn = new Date().getTime();
			while (true) {
				if (new Date().getTime() - bgn > 1000) {
					colseWaveOutLow();
					break;
				}
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					Ln.e(e, "耳机口输入异常");
				}
			}
		}
	}
}
