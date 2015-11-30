package com.hw.smarthome.server.deal;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import android.util.Log;

import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;

/**
 * 
 * 
 * 项目名称：SmartHome 类名称：DealWithDiscussTheme 类描述：处理讨论区主题 创建人：lijing 创建时间：2014-7-4
 * 下午3:54:34 修改人： 修改时间： 修改备注：
 * 
 * @version
 * 
 */
public class DealWithDiscuss {

	private static final String tag = DealWithDiscuss.class
			.getSimpleName();
	public static final String DISCUSS_SAVE_FILE_NAME = "discuss_theme";

	public static void dealDiscuss(Context context, String json,
			String actionType) {

		boolean result = false;
		String data = null;
		if (!TextUtils.isEmpty(json)) {
			Log.i(tag, json);
			JSONObject jo;
			try {
				jo = new JSONObject(json);
				String resultCode = jo.getString("code");
				if ("1".equals(resultCode)) {
					data = jo.get("dataObject").toString();
					if (!TextUtils.isEmpty(data)) {
						saveData(context, data, actionType);
					}
					result = true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Ln.i(e);
			}
		}
		/* 3.发送处理结果广播，页面随之更新 */
		MainAcUtil.send2ActivityData(context, actionType, 0,
				result, data);
	}

	public static void dealDiscuss(Context context, String json,
			String actionType, int type) {

		boolean result = false;
		String data = null;
		if (!TextUtils.isEmpty(json)) {
//			Ln.i(json);
			JSONObject jo;
			try {
				jo = new JSONObject(json);
				String resultCode = jo.getString("code");
				if ("1".equals(resultCode)) {
					data = jo.get("dataObject").toString();
					if (!TextUtils.isEmpty(data)) {
						saveData(context, data, actionType);
					}
					result = true;
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				Ln.i(e);
			}
		}
		/* 3.发送处理结果广播，页面随之更新 */
		MainAcUtil.send2ActivityData(context, actionType, type,
				result, data);
	}

	public static void saveData(Context context, String list,
			String actionType) {
		try {
			Editor userData = context.getSharedPreferences(
					DISCUSS_SAVE_FILE_NAME, 0).edit();
			userData.putString(actionType, list);
			userData.commit();
		} catch (Exception e) {
			Ln.e(e, "存储讨论区信息异常");
		}
	}

	public static String getData(Context context,
			String actionType) {
		String saveStr = null;
		try {
			SharedPreferences userData = context
					.getSharedPreferences(
							DISCUSS_SAVE_FILE_NAME, 0);
			saveStr = userData.getString(actionType, "");

		} catch (Exception e) {
			Ln.e(e, "获取讨论区信息异常");
		}
		return saveStr;
	}

}
