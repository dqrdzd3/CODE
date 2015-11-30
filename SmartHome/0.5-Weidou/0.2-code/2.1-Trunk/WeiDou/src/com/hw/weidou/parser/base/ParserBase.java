package com.hw.weidou.parser.base;

import com.hw.util.ThreadClassTemplet;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.constant.FuncConstant;
import com.hw.weidou.po.FrameContent;
import com.hw.weidou.po.WeidouPo;

/**
 * @author 曾凡
 * @time 2014年10月13日 下午4:57:59
 */
public abstract class ParserBase extends ThreadClassTemplet {
	private static WeidouPo currentWeidou = new WeidouPo();

	public ParserBase(int sleepTimes) {
		super(sleepTimes);
	}

	@Override
	protected void runFun() {
		FrameContent frame = getFrame();
		if (frame != null && frame.isLegal()) {
			frameHandler(frame);
		}
	}

	@Override
	public synchronized void Go() {
		super.Go();
		parser = new Parser(1);
		parser.start();
	}

	private Parser parser = null;

	private class Parser extends ThreadClassTemplet {

		public Parser(int sleepTimes) {
			super(sleepTimes);
		}

		@Override
		protected void runFun() {
			FrameContent frame = getFrame();
			if (frame != null && frame.isLegal()) {
				frameHandler(frame);
			}
		}

	}

	public void frameHandler(FrameContent frame) {

		byte[] bytes = frame.getByteContent();
		byte func = (byte) (bytes[1] & 0x1F);
		currentWeidou.setFunc(func);
		byte equip = (byte) ((bytes[1] & 0xE0) >> 5);
		String equipName = "";
		currentWeidou.setEquip(equip);
		switch (equip) {
		case EquipConstant.EQUIP_CH2O:// 甲醛
			equipName = "甲醛";
			break;
		case EquipConstant.EQUIP_ALCOHOL:// 酒精
			equipName = "酒精";
			break;
		case EquipConstant.EQUIP_CO:// 一氧化碳
			equipName = "一氧化碳";
			break;
		default:
			break;
		}
		currentWeidou.setEquipName(equipName);
		/* 根据功能码判断功能 */
		switch (func) {
		case FuncConstant.WEIDOU_VERSION:// 威豆版本号
			currentWeidou.setFuncName("威豆版本号");
			versionParser(bytes, currentWeidou);
			break;
		case FuncConstant.TESTDATA_CONTENT:// 传输数据
			currentWeidou.setFuncName("传感器数据");
			dataContentParser(bytes, currentWeidou);
			break;
		case FuncConstant.POWER_DATA:// 电量数据
			currentWeidou.setFuncName("可用电量");
			powerParser(bytes, currentWeidou);
			break;
		case FuncConstant.USED_TIME:// 威豆使用时间
			currentWeidou.setFuncName("使用时间");
			usageParser(bytes, currentWeidou);
			break;
		case FuncConstant.ALCOHOL_READY:// 威豆使用时间
			currentWeidou.setFuncName("准备进行酒精测试");
			alcoholReadyParser(bytes, currentWeidou);
			break;
		case FuncConstant.ALCOHOL_PROCESS:// 威豆使用时间
			currentWeidou.setFuncName("酒精处理中");
			alcoholProcessParser(bytes, currentWeidou);
			break;
		default:
			break;
		}
		// Ln.i(currentWeidou.toString());
	}

	protected abstract void alcoholProcessParser(byte[] bytes,
			WeidouPo currentWeidou2);

	protected abstract void alcoholReadyParser(byte[] bytes,
			WeidouPo currentWeidou2);

	/**
	 * 威豆使用时间
	 * 
	 * @author 曾凡
	 * @param frame
	 * @return
	 * @time 2014年10月13日 下午5:38:39
	 */
	protected abstract void usageParser(byte[] bytes,
			WeidouPo weidou);

	/**
	 * 电量数据
	 * 
	 * @author 曾凡
	 * @param frame
	 * @return
	 * @time 2014年10月13日 下午5:38:43
	 */
	protected abstract void powerParser(byte[] bytes,
			WeidouPo weidou);

	/**
	 * 传输数据
	 * 
	 * @author 曾凡
	 * @param frame
	 * @return
	 * @time 2014年10月13日 下午5:38:43
	 */
	protected abstract void dataContentParser(byte[] bytes,
			WeidouPo weidou);

	/**
	 * 威豆版本号
	 * 
	 * @author 曾凡
	 * @param frame
	 * @return
	 * @time 2014年10月13日 下午5:38:43
	 */
	protected abstract void versionParser(byte[] bytes,
			WeidouPo weidou);

	public abstract FrameContent getFrame();

	public abstract void setFrame(FrameContent content);

	public static WeidouPo getCurrentWeidou() {
		return currentWeidou;
	}

	public static void setCurrentWeidou(WeidouPo currentWeidou) {
		ParserBase.currentWeidou = currentWeidou;
	}

}
