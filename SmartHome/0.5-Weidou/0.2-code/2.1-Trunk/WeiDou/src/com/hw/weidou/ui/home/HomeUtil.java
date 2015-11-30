package com.hw.weidou.ui.home;

import java.math.BigDecimal;

import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.po.WeidouPo;

/**
 * @author 曾凡
 * @time 2014年10月15日 上午10:38:05
 */
public class HomeUtil {
	/**
	 * 判断是不是酒精
	 * 
	 * @author 曾凡
	 * @param weidou
	 * @return
	 * @time 2014年11月5日 下午7:02:54
	 */
	public static boolean isAlcohol(WeidouPo weidou) {
		if (weidou == null) {
			return false;
		}
		if (weidou.getEquip() == EquipConstant.EQUIP_ALCOHOL) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 对比两个数，获取最大值
	 * 
	 * @author 曾凡
	 * @param dataStr
	 * @param highStr
	 * @return
	 * @time 2014年10月15日 上午10:40:41
	 */
	public static String getBiggerValue(String dataStr,
			String highStr) {
		dataStr = (dataStr == null || "".equals(dataStr)) ? "0"
				: dataStr;
		float tempData = Float.valueOf(dataStr);
		highStr = (highStr == null || "".equals(highStr)) ? "0"
				: highStr;
		float highData = Float.valueOf(highStr);
		if (tempData >= highData) {
			highStr = dataStr;
		}
		return highStr;
	}

	/**
	 * 对比两个数，获取平均
	 * 
	 * @author 曾凡
	 * @param dataStr
	 * @param lowStr
	 * @return
	 * @time 2014年10月15日 上午10:40:41
	 */
	public static String getAvgValue(float latestValue,
			float before) {
		if (latestValue > 0 && before > 0) {
			return (Float.valueOf(before + latestValue) / 2)
					+ "";
		}
		return before + "";
	}
}
