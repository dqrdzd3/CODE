package com.hw.weidou.decode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import android.util.Log;

import com.hw.util.Ln;
import com.hw.weidou.constant.SignalConstant;
import com.hw.weidou.decode.base.WeidouDecoder;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.po.FrameContent;

/**
 * byte解析器
 * 
 * @author 曾凡
 * @time 2014年10月11日 下午1:37:40
 */
public class ByteDecoder implements WeidouDecoder {
	private Queue<FrameContent> byteQueue = new ConcurrentLinkedQueue<FrameContent>();
	/** 存放临时的数据帧 */
	private List<Integer> tempFrame = new LinkedList<Integer>();
	private List<String> binaryByte = new LinkedList<String>();
	private List<Integer> byteList = new LinkedList<Integer>();
	private static ByteDecoder instance;

	public static ByteDecoder getInstance() {
		// if (instance == null) {
		instance = new ByteDecoder();
		// }
		return instance;
	}

	@Override
	public void startPreΤreatment() {

	}

	@Override
	public void decode(FrameContent content) {
		for (String binaryContent : content.getStrArray()) {
			adptFourier(binaryContent);
		}
		for (String b : binaryByte) {
			adptBinary(b);
		}
		/* 拼装帧&处理帧 */
		for (int i = byteList.size() - 1; i >= 0; i--) {
			assemble(byteList.get(i));
		}

		binaryByte.clear();
		byteList.clear();
	}

	/**
	 * 组装帧
	 * 
	 * @author 曾凡
	 * @param b
	 * @time 2014年10月13日 下午3:44:10
	 */
	private void assemble(int b) {
		if (b == 0xAC) {
			tempFrame.add(b);
		} else if (tempFrame.size() > 0 && b != 0xAE) {
			tempFrame.add(b);
		} else if (tempFrame.size() > 0 && b == 0xAE) {
			tempFrame.add(b);
			/* 处理帧 */
			frameHandler();
			tempFrame.clear();
		}

	}

	/**
	 * 处理帧
	 * 
	 * @author 曾凡
	 * @time 2014年10月11日 下午5:18:01
	 */
	private void frameHandler() {
		StringBuilder frameStr = new StringBuilder();
		byte[] frameBytes = null;
		String temp = "";
		for (int i = 0; i < tempFrame.size(); i++) {
			temp = Integer.toHexString(tempFrame.get(i));
			frameStr.append(temp.toUpperCase());
			if (i < tempFrame.size()) {
				frameStr.append(",");
			}

		}
		frameBytes = transFrame(frameStr.toString());
		StringBuilder sb = new StringBuilder();
		for (byte b : frameBytes) {
			sb.append(Integer.toHexString(0xFF & b)
					.toUpperCase() + ",");
		}
		Log.d("bytes", "解调后的数据帧为：" + sb.toString());
		FrameContent frame = new FrameContent();
		frame.setByteContent(frameBytes);
		validate(frame);
		if (frame.isLegal()) {
			sent2NextProc(frame);
		}
	}

	/**
	 * 帧转义
	 * 
	 * @author 曾凡
	 * @time 2014年10月11日 下午5:21:42
	 */
	private byte[] transFrame(String frameStr) {
		byte[] res = null;
		try {
			/* 掐头去尾 */
			String content = frameStr.substring(2,
					frameStr.length() - 3);
			/* 如果帧数据中出现 AC H 则发送 AD H 5C H */
			content = content.replace("AD,5C", "AC");
			/* 如果帧数据中出现 AE H 则发送 AD H 5E H */
			content = content.replace("AD,5E", "AE");
			/* 如果帧数据中出现 AD H 则发送 AD H 5D H */
			content = content.replace("AD,5D", "AD");
			frameStr = "AC" + content + "AE";
			String[] strBytes = frameStr.split(",");
			res = new byte[strBytes.length];
			String tempStr = null;
			for (int i = 0; i < strBytes.length; i++) {
				tempStr = strBytes[i];
				res[i] = (byte) (0xFF & Integer.valueOf(tempStr,
						16));
			}
		} catch (Exception e) {
			Ln.e(e, "帧转义异常");
		}
		return res;
	}

	/**
	 * CRC校验
	 * 
	 * @author 曾凡
	 * @param content
	 * @time 2014年10月11日 下午5:20:47
	 */
	@Override
	public void validate(FrameContent content) {
		try {
			int contentLength = content.getByteContent().length;
			int sum = 0;
			for (int i = 0; i < contentLength; i++) {
				if (i != contentLength - 2) {
					sum += content.getByteContent()[i] & 0xFF;
				}
			}
			int crc = content.getByteContent()[contentLength - 2] & 0xFF;
			if (crc == (sum & 0xFF)) {
				content.setLegal(true);
			} else {
				content.setLegal(false);
			}
		} catch (Exception e) {
			Ln.e(e, "CRC校验失败[" + content.getByteContent() + "]");
			content.setLegal(false);
		}
	}

	private void adptBinary(String binaryContent) {
		String dataContent = binaryContent.substring(1, 9);
		char[] conentChars = dataContent.toCharArray();
		byte b1 = Byte.valueOf(conentChars[0] + "");
		byte b2 = Byte.valueOf(conentChars[1] + "");
		byte b3 = Byte.valueOf(conentChars[2] + "");
		byte b4 = Byte.valueOf(conentChars[3] + "");

		byte b5 = Byte.valueOf(conentChars[4] + "");
		byte b6 = Byte.valueOf(conentChars[5] + "");
		byte b7 = Byte.valueOf(conentChars[6] + "");
		byte b8 = Byte.valueOf(conentChars[7] + "");
		int b = (0xFF & ((b8 << 7) + (b7 << 6) + (b6 << 5)
				+ (b5 << 4) + (b4 << 3) + (b3 << 2) + (b2 << 1) + b1));
		byteList.add(b);
		Log.d("byte", Integer.toHexString(b));
		count0(b);
	}

	private int lastLetter = 0;
	private int zeroCount = 0;

	/**
	 * 统计连续出现的0
	 * 
	 * @author 曾凡
	 * @time 2014年12月23日 下午2:57:31
	 */
	private void count0(int b) {
		if (zeroCount >= 5) {
			if (SignalConstant.IS_INTERNATIONAL_MIC == true) {
				SignalConstant.IS_INTERNATIONAL_MIC = false;

			} else {
				SignalConstant.IS_INTERNATIONAL_MIC = true;
			}
			zeroCount = 0;
		}
		if (b == lastLetter && (b == 0||b == 0x40||b == 0xC0)) {
			zeroCount++;
		} else {
			zeroCount = 0;
		}
		lastLetter = b;
	}

	private void adptFourier(String fourier) {
		try {
			String temp = "";
			int currentNum = 0;
			StringBuilder sb = new StringBuilder();
			/* 一个byte的长度是10位 */
			for (int i = 0; i < 10; i++) {
				temp = fourier.substring(i * 2, i * 2 + 2);
				switch (temp) {
				case "01":
					currentNum = 0;
					break;
				case "00":
					currentNum = 1;
					break;
				case "11":
					currentNum = 0;
					break;
				case "10":
					currentNum = 1;
					break;
				}
				sb.append(currentNum);
			}

			binaryByte.add(sb.toString());
		} catch (Exception e) {
			Ln.e(e, "波形适配失败");
		}
	}

	/**
	 * 发送一个帧到处理类
	 * 
	 * @author 曾凡
	 * @param content
	 * @time 2014年10月13日 上午9:09:00
	 */
	@Override
	public void sent2NextProc(FrameContent content) {
		ParserDeamon.getInstance().setFrame(content);
	}

	public Queue<FrameContent> getQueue() {
		return byteQueue;
	}

	public FrameContent getContent() {
		return byteQueue.poll();
	}

	public void setContent(FrameContent content) {
		byteQueue.add(content);
	}

}
