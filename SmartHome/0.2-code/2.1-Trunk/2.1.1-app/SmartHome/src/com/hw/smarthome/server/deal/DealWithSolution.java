package com.hw.smarthome.server.deal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hw.smarthome.po.S007PO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;

/**
 * @author 曾凡
 * @time 2015年3月11日 下午5:24:19
 */
public class DealWithSolution {
	/* 气体类型和设备的 */
	private static Map<String, S007PO> solutions = new HashMap<String, S007PO>();
	public static final String SOLUTION_KEY = "solution_key";
	public static final String SOLUTION_CONTENT = "solution";
	public static String SHOP_URL = "shopUrl";
	public static String DIY_URL = "diyUrl";
	public static String JOIN_URL = "joinUrl";

	public static void dealSolution(Context context,
			String json, String actionType) {
		boolean result = true;
		String message = null;
		try {
			/* 1.解析json */
			List<S007PO> solutionList = SmartHomeJsonUtil
					.getSolution(json);

			if (solutionList != null && solutionList.size() > 0) {
				solutions.clear();
				for (S007PO po : solutionList) {
					solutions.put(
							po.getMa002() + "_" + po.getMa003(),
							po);
				}
				/* 2.将结果持久化 */
				saveSolutionMap(context);

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

	private static void saveSolutionMap(Context context) {

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

	public static Map<String, S007PO> getSolutionMap(
			Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SOLUTION_KEY, 0);
		String saveStr = userData
				.getString(SOLUTION_CONTENT, "");
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				solutions = (Map<String, S007PO>) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
			}
		}
		return solutions;

	}

	public static void dealShopUrl(Context context, String json,
			String actionType) {
		boolean result = true;
		String message = null;
		try {
			/* 1.解析json */
			String url = SmartHomeJsonUtil.getMessage(json);

			if (url != null && !"".equals(url)
					&& url.startsWith("http")) {
				/* 2.将结果持久化 */
				saveShopUrl(context, url);
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

	private static void saveShopUrl(Context context, String url) {
		try {
			Editor userData = context.getSharedPreferences(
					SOLUTION_KEY, 0).edit();
			userData.putString(SHOP_URL, url);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + solutions + "异常！", e);
			e.printStackTrace();
		}
	}

	public static String getShopUrl(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SOLUTION_KEY, 0);
		String url = userData.getString(SHOP_URL, "");
		if (url != null && !"".equals(url)
				&& url.startsWith("http")) {
			ServerConstant.SHOP_URL = url;
		}
		return url;

	}

	public static void dealJoinUrl(Context context, String json,
			String actionType) {
		boolean result = true;
		String message = null;
		try {
			/* 1.解析json */
			String url = SmartHomeJsonUtil.getMessage(json);

			if (url != null && !"".equals(url)
					&& url.startsWith("http")) {
				/* 2.将结果持久化 */
				saveJoinUrl(context, url);
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

	private static void saveJoinUrl(Context context, String url) {
		try {
			Editor userData = context.getSharedPreferences(
					SOLUTION_KEY, 0).edit();
			userData.putString(JOIN_URL, url);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + solutions + "异常！", e);
			e.printStackTrace();
		}
	}

	public static String getJoinUrl(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SOLUTION_KEY, 0);
		String url = userData.getString(JOIN_URL, "");
		if (url != null && !"".equals(url)
				&& url.startsWith("http")) {
			ServerConstant.JOIN_URL = url;
		}
		return url;

	}

	public static void dealDIYUrl(Context context, String json,
			String actionType) {
		boolean result = true;
		String message = null;
		try {
			/* 1.解析json */
			String url = SmartHomeJsonUtil.getMessage(json);

			if (url != null && !"".equals(url)
					&& url.startsWith("http")) {
				/* 2.将结果持久化 */
				saveDIYUrl(context, url);
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

	private static void saveDIYUrl(Context context, String url) {
		try {
			Editor userData = context.getSharedPreferences(
					SOLUTION_KEY, 0).edit();
			userData.putString(DIY_URL, url);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + solutions + "异常！", e);
			e.printStackTrace();
		}
	}

	public static String getDIYUrl(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SOLUTION_KEY, 0);
		String url = userData.getString(DIY_URL, "");
		if (url != null && !"".equals(url)
				&& url.startsWith("http")) {
			ServerConstant.DIY_URL = url;
		}
		return url;

	}

	public static Map<String, S007PO> getSolutions() {
		return solutions;
	}

	public static void setSolutions(Map<String, S007PO> solutions) {
		DealWithSolution.solutions = solutions;
	}

}
