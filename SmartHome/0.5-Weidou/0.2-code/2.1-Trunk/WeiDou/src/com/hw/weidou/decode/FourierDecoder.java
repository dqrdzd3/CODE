package com.hw.weidou.decode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.hw.util.Ln;
import com.hw.util.ThreadClassTemplet;
import com.hw.weidou.constant.SignalConstant;
import com.hw.weidou.decode.base.WeidouDecoder;
import com.hw.weidou.po.FrameContent;

/**
 * 波形解码器
 * 
 * @author 曾凡
 * @time 2014年10月11日 下午1:36:38
 */
public class FourierDecoder extends ThreadClassTemplet implements
		WeidouDecoder {
	private static short ARRAY_LENGTH = 10;
	/* 隐神经元层 */
	private static String[] bigArray = new String[ARRAY_LENGTH];
	private static String[] smallArray = new String[ARRAY_LENGTH];

	/* 输入神经元层 */
	/** 最大幅值 */
	private static int maxWaveValue;
	/** 阀值激发函数 动态上界，高于它就是1 */
	private static int upperWaveLine;
	/** 最小幅值 */
	private static int miniWaveValue;
	/** 阀值激发函数 动态下界，低于它就是0 */
	private static int lowerWaveLine;

	/**
	 * 源节点到隐藏神经元层
	 * 
	 * @author 曾凡
	 * @time 2014年11月17日 下午4:37:11
	 */
	private static void source2Middle(short[] arg) {
		int partLength = arg.length / ARRAY_LENGTH;
		int temp = 0;
		for (int i = 0; i < ARRAY_LENGTH; i++) {
			bigArray[i] = max(arg, i * partLength, (i + 1)
					* partLength - 1)
					+ "";
			smallArray[i] = min(arg, i * partLength, (i + 1)
					* partLength - 1)
					+ "";
			temp = Integer.valueOf((smallArray[i] == null || ""
					.equals(smallArray[i])) ? "0"
					: smallArray[i]);
			if (temp > 0) {
				smallArray[i] = "0";
			}
			temp = Integer.valueOf((bigArray[i] == null || ""
					.equals(bigArray[i])) ? "0" : bigArray[i]);
			if (temp < 0) {
				bigArray[i] = "0";
			}
		}
	}

	/* 隐藏神经元到输出层 */
	private static void Middle2Output(int up, int down) {
		/* XXX 测试国际版威豆 */
		// SignalConstant.IS_INTERNATIONAL_MIC = true;
		maxWaveValue = min(bigArray);
		miniWaveValue = max(smallArray);

		// maxWaveValue = max(rawCodeArray);
		// miniWaveValue = min(rawCodeArray);
		/* 确定高低的阀值 */
		upperWaveLine = maxWaveValue - (maxWaveValue / up);
		lowerWaveLine = miniWaveValue - (miniWaveValue / down);

		String phoneModel = android.os.Build.MODEL;
		String manufacturer = android.os.Build.MANUFACTURER;
		Log.d("manufacturer", phoneModel + " " + manufacturer);
		/* 魅族 mx4 */
		if ("Meizu".equals(manufacturer)) {
			if ("MX4".equals(phoneModel)) {
				upperWaveLine = 8500; // 过滤器阀值
				lowerWaveLine = -8500; // 过滤器阀值
			} else if ("m1 note".equals(phoneModel)) {
				upperWaveLine = 8500; // 过滤器阀值
				lowerWaveLine = -8500; // 过滤器阀值
			}

		} else if ("samsung".equals(manufacturer)) {
			if (phoneModel.startsWith("SM-N900")) {
				/* note3测试一个是国际口的，而且高低电平是反的 */
				SignalConstant.IS_INTERNATIONAL_MIC = true;
				upperWaveLine = 8500; // 过滤器阀值
				lowerWaveLine = -8500; // 过滤器阀值
			} 
			else if (phoneModel.startsWith("SM-N9100")) {
				SignalConstant.IS_INTERNATIONAL_MIC = true;
				upperWaveLine = 6000; // 过滤器阀值
				lowerWaveLine = -6000; // 过滤器阀值
			}

		}
		// else if("HUAWEI".equals(manufacturer)){
		// if (phoneModel.startsWith("G520-5000")) {
		// upperWaveLine = 8500; // 过滤器阀值
		// lowerWaveLine = -8500; // 过滤器阀值
		// }
		// }
	}

	/** 接收缓存的波形 */
	private Queue<FrameContent> fourierQueue = new ConcurrentLinkedQueue<FrameContent>();
	/** 一次decode解析到的数据 */
	private List<String> binaryFourier = new LinkedList<String>();
	private static FourierDecoder instance;

	public static FourierDecoder getInstance() {
		if (instance == null) {
			instance = new FourierDecoder(-1);
		}
		return instance;
	}

	public static FourierDecoder getInstance(int sleepTimes) {
		if (instance == null) {
			instance = new FourierDecoder(sleepTimes);
		}
		return instance;
	}

	public FourierDecoder(int sleepTimes) {
		super(sleepTimes);
		startPreΤreatment();
	}

	@Override
	protected void runFun() {
		FrameContent content = getContent();
		if (content != null) {
			decode(content);
		}
		binaryFourier.clear();
	}

	/**
	 * 向BinaryDecoder里插入内容
	 * 
	 * @author 曾凡
	 * @param content
	 * @time 2014年10月11日 下午4:03:59
	 */
	@Override
	public void decode(FrameContent content) {
		try {
			/* 去噪 */
			content.setStrContent(denoiseFourier(
					content.getStrContent(),
					SignalConstant.SAMPLE_RATE_IN_HZ));
			splitContentRecursion(content);
			content.setStrContent(null);
			String[] strArray = new String[binaryFourier.size()];
			for (int i = 0; i < strArray.length; i++) {
				strArray[i] = binaryFourier.get(i);
			}
			content.setStrArray(strArray);
			/* 校验并发送到下一个处理阶段 */
			sent2NextProc(content);
		} catch (Exception e) {
			Ln.e(e, "解析波形异常");
		}
	}

	private void splitContentRecursion(FrameContent frameContent) {
		String content = "";
		try {
			if (frameContent == null) {
				return;
			}
			content = frameContent.getStrContent();
			/* 分段SPACE的索引 */
			int tempIndex = 0;
			/* 用来保存缓存的分段数据 */
			String tempChars = "";
			/* 分段后 */
			tempIndex = content
					.lastIndexOf(SignalConstant.FOURIER_SPACE)
					+ SignalConstant.FOURIER_SPACE.length() - 2;
			tempChars = content.substring(tempIndex,
					content.length());
			if ((content != null && !"".equals(content))
					&& content.length() > 20) {
				if ("0011010101010101".equals(tempChars)) {
					String tempContent = content.substring(0,
							tempIndex - 18);
					tempIndex = tempContent.length();

					tempChars = content.substring(tempIndex,
							content.length());

					content = tempContent;

				} else {
					/* 如果小于20则再向上找 */
					content = content.substring(0, tempIndex);
				}
				frameContent.setStrContent(content);
				if (tempChars != null
						&& tempChars.length() >= 34) {
					binaryFourier.add(tempChars);
				}
				splitContentRecursion(frameContent);
			}
		} catch (Exception e) {
			// Ln.d(e, "波形分段异常，还剩：" + content);
		}
	}

	/**
	 * 信号高低电平特征值提取，信号去噪，内容提取
	 * 
	 * @author 曾凡
	 * @param content
	 * @param samplingType
	 * @return
	 * @time 2014年10月11日 下午2:44:24
	 */
	private String denoiseFourier(String content,
			int samplingType) {
		// switch (samplingType) {
		// case SignalConstant.SAMPLING_8K:
		content = content.replace("011111110", "011110");
		content = content.replace("01111110", "011110");
		content = content.replace("0111110", "011110");

		content = content.replace("100000001", "100001");
		content = content.replace("10000001", "100001");
		content = content.replace("1000001", "100001");

		content = content.replace("10001", "1001");
		content = content.replace("10001", "1001");
		content = content.replace("01110", "0110");
		content = content.replace("01110", "0110");
		// break;
		// default:
		// break;
		// }
		content = content.replace("11", "1");
		content = content.replace("00", "0");

		return content;
	}

	/**
	 * 向BinaryDecoder里插入内容
	 * 
	 * @author 曾凡
	 * @param content
	 * @time 2014年10月11日 下午4:03:59
	 */
	@Override
	public void validate(FrameContent content) {
		boolean isLegal = true;
		String[] StrArray = content.getStrArray();
		if (StrArray == null || StrArray.length == 0) {
			isLegal = false;
		}
		content.setLegal(isLegal);
	}

	@Override
	public void sent2NextProc(FrameContent content) {
		validate(content);
		if (content.isLegal()) {
			ByteDecoder.getInstance().decode(content);
		}
	}

	public Queue<FrameContent> getQueue() {
		return fourierQueue;
	}

	public FrameContent getContent() {
		return fourierQueue.poll();
	}

	public void setContent(FrameContent content) {
		fourierQueue.add(content);
	}

	private AudioRecord ar;
	private int bs = 4500;

	@Override
	public void startPreΤreatment() {

		new Thread(new AudioRecordThread()).start();
	}

	// public void updateAudio() {
	//
	// if (ar != null) {
	// if (ar.getRecordingState() == AudioRecord.RECORDSTATE_RECORDING) {
	// ar.stop();
	// }
	// ar = new AudioRecord(MediaRecorder.AudioSource.MIC,
	// SignalConstant.SAMPLE_RATE_IN_HZ,
	// AudioFormat.CHANNEL_CONFIGURATION_MONO,
	// AudioFormat.ENCODING_PCM_16BIT, bs);
	// ar.startRecording();
	// }
	// }
	@Override
	public synchronized void Go() {
		super.Go();
		startAudio();
	}

	public void startAudio() {
		// FIXME
		// bs = AudioRecord.getMinBufferSize(
		// SignalConstant.SAMPLE_RATE_IN_HZ,
		// AudioFormat.CHANNEL_CONFIGURATION_MONO,
		// AudioFormat.ENCODING_PCM_16BIT);
		// 根据手机类型切换采样率

		try {
			String phoneModel = android.os.Build.MODEL;
			String manufacturer = android.os.Build.MANUFACTURER;

			if ("samsung".equals(manufacturer)) {
				if (phoneModel.startsWith("GT-N71")) {
					SignalConstant.SAMPLE_RATE_IN_HZ = SignalConstant.SAMPLING_16K;
				} 
//				else if (phoneModel.startsWith("SM-N9100")) {
//					SignalConstant.SAMPLE_RATE_IN_HZ = SignalConstant.SAMPLING_16K;
//				}

			}
			ar = new AudioRecord(MediaRecorder.AudioSource.MIC,
					SignalConstant.SAMPLE_RATE_IN_HZ,
					AudioFormat.CHANNEL_CONFIGURATION_MONO,
					AudioFormat.ENCODING_PCM_16BIT, bs);
			if (ar.getState() != ar.STATE_INITIALIZED) {
				try {
					int min = AudioRecord
							.getMinBufferSize(
									SignalConstant.SAMPLE_RATE_IN_HZ,
									AudioFormat.CHANNEL_CONFIGURATION_MONO,
									AudioFormat.ENCODING_PCM_16BIT);
					if (min < 4096) {
						min = 4096;
					}
					ar = new AudioRecord(
							MediaRecorder.AudioSource.MIC,
							SignalConstant.SAMPLE_RATE_IN_HZ,
							AudioFormat.CHANNEL_CONFIGURATION_MONO,
							AudioFormat.ENCODING_PCM_16BIT, min);
					if (ar.getState() == AudioRecord.STATE_INITIALIZED) {
						Log.d("Recorder",
								"Audio recorder initialised at "
										+ ar.getSampleRate());
						ar.startRecording();
					} else {
						ar.release();
						ar = null;
					}
				} catch (Exception e) {
					Ln.e("初始化录音机异常");
				}
				Ln.e("初始化录音机异常");
			}
			ar.startRecording();
		} catch (Exception e) {
			Ln.e(e);
		}

	}

	@Override
	public void Pause() {
		super.Pause();
		if (ar != null) {
			ar.release();
			ar = null;
		}

	}

	private int SPACE = 1;// 间隔取样时间

	class AudioRecordThread implements Runnable {
		short[] buffer = new short[bs];
		private StringBuilder sb = null;

		public AudioRecordThread() {

		}

		@Override
		public void run() {
			while (true) {
				recordFourier();
			}
		}

		private void recordFourier() {
			if (sb != null) {
				if (sb.length() > SignalConstant.SAMPLING_CONTENT_LENGTH) {
					// Ln.w(sb.toString()); // 方便统计
					FrameContent content = new FrameContent();
					content.setStrContent(sb.toString());
					setContent(content);
					sb = null;
					if (content != null) {
						decode(content);
					}
					binaryFourier.clear();
				}
			}
			if (ar == null) {
				return;
			}
			int r = ar.read(buffer, 0, bs);
			if (r < 0) {
				Ln.e("初始化录音机异常");
				return;
			}
			source2Middle(buffer);
			Middle2Output(2, 2);
			if (sb == null) {
				sb = new StringBuilder();
			}
			short temp = 0;
			int hightFilterSize = upperWaveLine; // 过滤器阀值
			int lowFilterSize = lowerWaveLine; // 过滤器阀值
			for (int i = 0; i < buffer.length; i++) {
				temp = buffer[i];
				/* 单片机输入国内/国际标准的手机Mic口高低电平是反的 */
				if (SignalConstant.IS_INTERNATIONAL_MIC) {
					if (temp >= hightFilterSize) {
						sb.append("1");
					} else if (temp <= lowFilterSize) {
						sb.append("0");
					}
				} else {
					if (temp >= hightFilterSize) {
						sb.append("0");
					} else if (temp <= lowFilterSize) {
						sb.append("1");
					}

				}

				// else {
				// sb.append("");
				// }

			}

			// FIXME 测试波形
			StringBuilder sb = new StringBuilder();
			for (short s : buffer) {
				sb.append(s + ",");
			}
			Log.d("testAudio", sb.toString());
			try {
				Thread.sleep(SPACE);
			} catch (InterruptedException e) {
				Ln.e("读取波形间隔异常");
			}

		}
	}

	public static int max(String[] num) {
		int max = 0;
		int temp = 0;
		for (int i = 0; i < num.length; i++) {
			temp = Integer.valueOf((num[i] == null || ""
					.equals(num[i])) ? "0" : num[i]);
			if ((max == 0) || (temp != 0 && temp > max)) {
				max = temp;
			}
		}
		return max;
	}

	public static int min(String[] num) {
		int max = 0;
		int temp = 0;
		for (int i = 0; i < num.length; i++) {
			temp = Integer.valueOf((num[i] == null || ""
					.equals(num[i])) ? "0" : num[i]);
			if ((max == 0) || (temp != 0 && temp < max)) {
				max = temp;
			}
		}
		return max;
	}

	public static int max(short[] num, int start, int end) {
		int max = 0;
		int temp = 0;
		for (int i = start; i < end; i++) {
			temp = (short) (num[i]);
			if ((max == 0) || (temp != 0 && temp > max)) {
				max = temp;
			}
		}
		return max;
	}

	public static int min(short[] num, int start, int end) {
		int max = 0;
		int temp = 0;
		for (int i = start; i < end; i++) {
			temp = (short) (num[i]);
			if ((max == 0) || (temp != 0 && temp < max)) {
				max = temp;
			}
		}
		return max;
	}
}
