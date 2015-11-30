package com.hw.weidou.parser.alcohol;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.hw.util.Ln;
import com.hw.weidou.constant.UnitConstant;
import com.hw.weidou.ui.MainActivity;
import com.hw.weidou.ui.constant.UIConstant;

/**
 * 酒精算法
 * 
 * @author 曾凡
 * @time 2014年10月16日 下午2:29:12
 */
public class AlcoholAlgorithm {
	private static final String TAG = AlcoholAlgorithm.class.getSimpleName();
	public static final String ALCOHOL_UNIT_SAVE_FILE_NAME = "alcohol_unit";
	public static final String ALCOHOL_UNIT_SAVE_KEY = "alcohol_unit";
	public static String currentValue = "0";

	/**
	 * 存储酒精单位
	 * 
	 * @author 曾凡
	 * @param context
	 * @param unit
	 * @time 2014年10月16日 下午2:39:33
	 */
	public static void saveData(String unit) {
		UIConstant.HOME_UNIT_ALCOHOL = unit;
		Context context = MainActivity.mContext;
		try {
			Editor userData = context.getSharedPreferences(
					ALCOHOL_UNIT_SAVE_FILE_NAME, 0).edit();
			userData.putString(ALCOHOL_UNIT_SAVE_KEY, unit);
			userData.commit();
			Log.d(TAG, "保存单位为"+unit);
		} catch (Exception e) {
			Ln.d(e, "存储酒精单位算法异常");
		}
	}

	/**
	 * 获取酒精单位
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年10月16日 下午2:39:23
	 */
	public static String getData() {
		String resByte = UnitConstant.MG100ML_CN_NAME;
		String str = "";
		try {
			Context context = MainActivity.mContext;
			SharedPreferences userData = context
					.getSharedPreferences(
							ALCOHOL_UNIT_SAVE_FILE_NAME, 0);
			str = userData.getString(ALCOHOL_UNIT_SAVE_KEY, "");
			if (str == null || "".equals(str)) {
				str = UnitConstant.MG100ML_CN_NAME + "";// 默认是国标
			}
			UIConstant.HOME_UNIT_ALCOHOL = str;
			Log.d(TAG, "获取单位为"+ UIConstant.HOME_UNIT_ALCOHOL);
		} catch (Exception e) {
			Ln.d(e, "获取酒精单位算法异常");
		} finally {
			return resByte;
		}
	}

	/**
	 * 转换单位
	 * 
	 * @author 曾凡
	 * @param mgL
	 * @return
	 * @time 2014年10月16日 下午2:53:11
	 */
	public static double transUnit(double mgL) {
		double res = 0;
		// g/L=BAC‰=mg/L * 2
		if (UIConstant.HOME_UNIT_ALCOHOL
				.equals(UnitConstant.GL_NAME)) {
			res = mgL * 2;
			UIConstant.ALCOHOL_LOW = UIConstant.ALCOHOL_BASE_LOW * 2;
			UIConstant.ALCOHOL_MIDDLE = UIConstant.ALCOHOL_BASE_MIDDLE * 2;
		} else if (UIConstant.HOME_UNIT_ALCOHOL
				.equals(UnitConstant.BAC100_NAME)) {
			res = mgL / 5;
			UIConstant.ALCOHOL_LOW = UIConstant.ALCOHOL_BASE_LOW / 5;
			UIConstant.ALCOHOL_MIDDLE = UIConstant.ALCOHOL_BASE_MIDDLE / 5;
		} else if (UIConstant.HOME_UNIT_ALCOHOL
				.equals(UnitConstant.BAC1000_NAME)) {
			res = mgL * 2;
			UIConstant.ALCOHOL_LOW = UIConstant.ALCOHOL_BASE_LOW * 2;
			UIConstant.ALCOHOL_MIDDLE = UIConstant.ALCOHOL_BASE_MIDDLE * 2;
		} else if (UIConstant.HOME_UNIT_ALCOHOL
				.equals(UnitConstant.MG100ML_US_NAME)) {
			res = mgL * 200;
			UIConstant.ALCOHOL_LOW = UIConstant.ALCOHOL_BASE_LOW * 200;
			UIConstant.ALCOHOL_MIDDLE = UIConstant.ALCOHOL_BASE_MIDDLE * 200;
		} else if (UIConstant.HOME_UNIT_ALCOHOL
				.equals(UnitConstant.MG100ML_CN_NAME)) {
			res = mgL * 220;
			UIConstant.ALCOHOL_LOW = UIConstant.ALCOHOL_BASE_LOW * 220;
			UIConstant.ALCOHOL_MIDDLE = UIConstant.ALCOHOL_BASE_MIDDLE * 220;
		} else {
			res = mgL;
			UIConstant.ALCOHOL_LOW = UIConstant.ALCOHOL_BASE_LOW;
			UIConstant.ALCOHOL_MIDDLE = UIConstant.ALCOHOL_BASE_MIDDLE;
		}
		return res;
	}
}
