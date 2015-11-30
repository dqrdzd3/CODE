package com.hw.smarthome.server.deal;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hw.smarthome.po.SoluChkSumPO;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;

/**
 * @author 曾凡
 * @time 2015年3月11日 下午5:24:19
 */
public class DealWithSoluChkSum {
	/* 气体类型和设备的 */
	private static Map<String, SoluChkSumPO> solutions = new HashMap<String, SoluChkSumPO>();
	public static final String SOLUTION_KEY = "soluchksum_key";
	public static final String SOLUTION_CONTENT = "soluchksum_content";

	public static void dealSolution(Context context,
			String json, SoluChkSumPO po, String actionType) {
		boolean result = true;
		String message = null;
		try {
			/* 1.解析json */
			String content = SmartHomeJsonUtil
					.getDataObject(json);
			if (content != null && !"".equals(content)) {
				po.setCentent(content);
				solutions.put(po.toString(), po);
				/* 2.将结果持久化 */
				saveSolution(context);

			}

		} catch (Exception e) {
			Ln.e(e, "");
			message = SmartHomeJsonUtil.getMessage(json);
			result = false;
		} finally {
			/* 3.发送处理结果广播，页面随之更新 */
			MainAcUtil.send2Activity(context, actionType, 0,
					result, message);
		}
	}

	private static void saveSolution(Context context) {

		solutions.putAll(getSolutionMap(context));
		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(solutions);

			Editor userData = context.getSharedPreferences(
					SOLUTION_KEY, 0).edit();
			userData.putString(SOLUTION_CONTENT, saveByteStr);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + solutions + "异常！", e);
			e.printStackTrace();
		}

	}

	public static Map<String, SoluChkSumPO> getSolutionMap(
			Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SOLUTION_KEY, 0);
		String saveStr = userData
				.getString(SOLUTION_CONTENT, "");
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				solutions
						.putAll((Map<String, SoluChkSumPO>) PreferenceUtil
								.String2Object(saveStr));
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
			}
		}
		return solutions;

	}

	public static Map<String, SoluChkSumPO> getSolutions() {
		return solutions;
	}

	public static void setSolutions(
			Map<String, SoluChkSumPO> solutions) {
		DealWithSoluChkSum.solutions = solutions;
	}

}
