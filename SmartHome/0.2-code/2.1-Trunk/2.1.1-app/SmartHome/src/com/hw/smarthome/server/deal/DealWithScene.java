package com.hw.smarthome.server.deal;

import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hw.smarthome.R;
import com.hw.smarthome.po.ScenePO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;

/**
 * @author 曾凡
 * @time 2015年5月12日 上午11:28:46
 */
public class DealWithScene {
	private static final String SCENE_NAME = "SCENE_NAME";
	private static final String SCENE_KEY = "SCENE_KEY";

	public static void dealScene(Context context, String json,
			String actionType) {

		boolean result = true;
		String message = null;
		int type = 0;
		try {
			/* 1.解析json */
			List<ScenePO> detailList = SmartHomeJsonUtil
					.getScenes(json);

			if (detailList != null) {
				/* 2.将结果持久化 */
				saveScenes(context, detailList);
			}

		} catch (Exception e) {
			result = false;
			Ln.e(e, "");
			e.printStackTrace();
			try {
				message = SmartHomeJsonUtil.getMessage(json);
			} catch (Exception e1) {
				result = false;
				message = context
						.getString(R.string.scene_delete_server_err);
				Ln.e(e, "");
				e1.printStackTrace();
			}

		} finally {
			if (ServerConstant.SH01_06_04_02.equals(actionType)) {
				type = 1;
			}
			/* 3.发送处理结果广播，页面随之更新 */
			MainAcUtil.send2Activity(context, actionType, type,
					result, message);
		}

	}

	/**
	 * 保存场景
	 * 
	 * @author 曾凡
	 * @param context
	 * @param sceneList
	 * @time 2015年5月12日 下午3:34:05
	 */
	public static void saveScenes(Context context,
			List<ScenePO> sceneList) {

		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(sceneList);

			Editor userData = context.getSharedPreferences(
					SCENE_NAME, 0).edit();
			userData.putString(SCENE_KEY, saveByteStr);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + sceneList + "异常！", e);
			e.printStackTrace();
		}

	}

	/**
	 * 获取所有缓存的场景
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2015年5月12日 下午3:33:41
	 */
	public static List<ScenePO> getScenes(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SCENE_NAME, 0);
		String saveStr = userData.getString(SCENE_KEY, "");
		List<ScenePO> detailList = null;
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				detailList = (List<ScenePO>) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
			}
		}
		return detailList;
	}

	/**
	 * 清除场景的缓存
	 * 
	 * @author 曾凡
	 * @param context
	 * @time 2015年5月12日 下午3:33:53
	 */
	public static void clearScenes(Context context) {

		try {
			Editor userData = context.getSharedPreferences(
					SCENE_NAME, 0).edit();
			userData.clear();
			userData.commit();
		} catch (Exception e) {
			Ln.e(e);
		}

	}
}
