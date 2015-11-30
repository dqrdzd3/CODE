package com.hw.weidou.ui.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;

/**
 * @author 曾凡
 * @time 2014年12月11日 上午10:04:42
 */
public class UnitUtil {

	public static String getDefaultValue(byte equip) {
		switch (equip) {
		case EquipConstant.EQUIP_CH2O:// 甲醛
			return "0.00";
		case EquipConstant.EQUIP_ALCOHOL:// 酒精 0-2.000 单位mg/L
			return "";
		case EquipConstant.EQUIP_CO:// 一氧化碳 0-1000 单位ppm
			return "0";
		}
		return "0";
	}

	/**
	 * 根据不同的类型返回不同的值
	 * 
	 * @author 曾凡
	 * @param equip
	 * @param data
	 * @return
	 * @time 2014年12月11日 上午10:19:15
	 */
	public static String getValue(byte equip, String data) {
		switch (equip) {
		case EquipConstant.EQUIP_CH2O:// 甲醛
			if (data == null || "".equals(data)) {
				return getDefaultValue(equip);
			}
			return getCH2O(data);
		case EquipConstant.EQUIP_ALCOHOL:// 酒精 0-2.000 单位mg/L
			if (data == null || "".equals(data)) {
				return getDefaultValue(equip);
			}
			return getAlcohol(data);
		case EquipConstant.EQUIP_CO:// 一氧化碳 0-1000 单位ppm
			if (data == null || "".equals(data)) {
				return getDefaultValue(equip);
			}
			return data.replace(".0", "");
		}
		return "";
	}

	public static String getCH2O(String data) {
		BigDecimal ml = null;
		if (data.contains(".")) {
			ml = new BigDecimal(Float.valueOf(data));
		} else {
			ml = new BigDecimal(Float.valueOf(data) / 100);
		}
		DecimalFormat g1 = new DecimalFormat("0.00");
		return g1.format(ml
				.setScale(2, BigDecimal.ROUND_HALF_UP)
				.floatValue());
	}

	public static String getAlcohol(int data) {
		return getAlcohol(data + "");
	}

	public static String getAlcohol(String data) {
		if("undefined".equals(data)){
			return "0";
		}
		double val = Double.valueOf(data);
//		val = AlcoholAlgorithm.transUnit(val);
		if ("0.0".equals(val+ "")) {
			return "0";
		}
		return val + "";
		// BigDecimal ml = null;
		// if (data.contains(".")) {
		// ml = new BigDecimal(Float.valueOf(data));
		// } else {
		// ml = new BigDecimal(Float.valueOf(data) / 1000);
		// }
		// // dataStr = AlcoholAlgorithm.transUnit(mgL) + "";
		// DecimalFormat g1 = new DecimalFormat("0.000");
		// return g1.format(ml
		// .setScale(3, BigDecimal.ROUND_HALF_UP)
		// .floatValue());
	}
}
