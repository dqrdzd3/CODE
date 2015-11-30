package com.hw.weidou.parser;

import java.text.DecimalFormat;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.hw.util.Ln;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;
import com.hw.weidou.parser.base.ParserBase;
import com.hw.weidou.po.FrameContent;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.util.UnitUtil;

/**
 * 调用
 * 
 * @author 曾凡
 * @time 2014年10月13日 下午5:00:20
 */
public class ParserDeamon extends ParserBase {
	private static ParserDeamon instance;

	public static ParserDeamon getInstance() {
		if (instance == null) {
			instance = new ParserDeamon(1);
		}
		return instance;
	}

	private Queue<FrameContent> queue = new ConcurrentLinkedQueue<FrameContent>();

	public ParserDeamon(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void usageParser(byte[] bytes, WeidouPo weidou) {
		int time = ((bytes[3] << 8) & 0xFF00)
				+ ((bytes[4]) & 0xFF);
		if (time > 5) {
			return;
		}
		weidou.setUsage(time);
	}

	@Override
	protected void powerParser(byte[] bytes, WeidouPo weidou) {
		int power = bytes[3] & 0xFF;
		if (power > 5) {
			return;
		}
		weidou.setPower(power);
	}

	@Override
	protected void dataContentParser(byte[] bytes,
			WeidouPo weidou) {
		int data = ((bytes[3] << 8) & 0xFF00)
				+ ((bytes[4]) & 0xFF);
		// FIXME 测试威豆实时数据
		// data = new Random().nextInt(120);
		String dataStr = "";
		byte equip = getCurrentWeidou().getEquip();

		switch (equip) {
		case EquipConstant.EQUIP_CH2O:// 甲醛
			if ((Float.valueOf(data) / 100) > 3) {
				break;
			}
			dataStr = UnitUtil.getValue(equip, data + "");
			break;
		case EquipConstant.EQUIP_ALCOHOL:// 酒精 0-2.000 单位mg/L
			if ((Float.valueOf(data) / 1000) > 2) {
				break;
			}
			dataStr = UnitUtil.getValue(equip,
					(Float.valueOf(data) / 1000) + "");
			dataStr = AlcoholAlgorithm.transUnit(Double
					.valueOf(dataStr)) + "";
			break;
		case EquipConstant.EQUIP_CO:// 一氧化碳 0-1000 单位ppm
			if (data > 1000) {
				break;
			}
			dataStr = data + "";
			break;
		}
		weidou.setDataContent(dataStr);
	}

	@Override
	protected void versionParser(byte[] bytes, WeidouPo weidou) {
		String str = "";
		try {
			String pattern = "000";
			DecimalFormat df = new DecimalFormat(pattern);
			str = df.format(bytes[3] & 0xFF);
			StringBuilder sb = new StringBuilder();
			for (char c : str.toCharArray()) {
				sb.append(c).append(".");
			}
			sb.delete(5, 6);
			str = sb.toString();
		} catch (Exception e) {
			Ln.e(e, "解析版本号异常");
		}
		weidou.setVersion(str);

	}

	public FrameContent getFrame() {
		return queue.poll();
	}

	public void setFrame(FrameContent content) {
		queue.add(content);
	}

	@Override
	protected void alcoholProcessParser(byte[] bytes,
			WeidouPo weidou) {
		byte equip = getCurrentWeidou().getEquip();
		weidou.setDataContent(UnitUtil.getDefaultValue(equip));
	}

	@Override
	protected void alcoholReadyParser(byte[] bytes,
			WeidouPo weidou) {

	}

}
