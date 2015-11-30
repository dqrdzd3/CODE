package com.hw.smarthome.server.deal;

import java.io.IOException;
import java.util.Map;

import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hw.smarthome.po.HistoryAlarm;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.util.DealUtil;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.po.HomeHistoryList;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.DateUtils;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;

/**
 * @author 曾凡
 * @time 2014年6月26日 下午4:46:51
 */
public class DealWithHome {

	public static final String HOME_DETAIL_KEY = "home_detail";
	public static final String HOME_HISTORY_7DAYS_KEY = "home_history_7days";
	public static final String HOME_HISTORY_30DAYS_KEY = "home_history_30days";
	public static final String HOME_HISTORY_24HOURS_KEY = "home_history_24hours";
	public static final String HOME_ALARM_KEY = "alarm_history";
	public static final String SAVE_FILE_NAME = "home";

	/**
	 * 保存处理过的[报警信息]列表
	 * 
	 * @author 曾凡
	 * @param context
	 * @param history
	 * @time 2014年7月1日 下午5:18:13
	 */
	public static void saveAlarm(Context context,
			Map<String, HistoryAlarm> alarms) {
		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(alarms);
		} catch (IOException e) {
			Ln.e("存储" + alarms + "异常！", e);
			e.printStackTrace();
		}
		try {
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.putString(HOME_ALARM_KEY, saveByteStr);
			userData.commit();
		} catch (Exception e) {
			Ln.e(e, "存储" + alarms + "异常！");
		}

	}

	/**
	 * 获取处理过的设备列表
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年12月11日 下午3:35:31
	 */
	public static Map<String, HistoryAlarm> getAlarm(
			Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_FILE_NAME, 0);
		String saveStr = userData.getString(HOME_ALARM_KEY, "");
		Map<String, HistoryAlarm> alarms = null;
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				alarms = (Map<String, HistoryAlarm>) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e(e, "获取" + saveStr + "异常！");
			}
		}
		return alarms;
	}

	/**
	 * 处理历史信息，内容太多了，单独启动一个线程去处理
	 * 
	 * @author 曾凡
	 * @param context
	 * @param json
	 * @param name
	 * @time 2014年7月4日 下午1:46:49
	 */
	public static void dealHistory(final MainActivity context,
			final String json, final String name) {
		new Thread() {
			@Override
			public void run() {
				super.run();
				boolean result = true;
				HomeHistoryList history = null;
				try {

					if (ServerConstant.SH01_02_01_03
							.equals(name)) {
						/* 解析json */
						history = deal7DaysHistory(json);
					} else if (ServerConstant.SH01_02_01_04
							.equals(name)) {
						history = deal24HoursHistory(json);
					} else if (ServerConstant.SH01_02_01_05
							.equals(name)) {
						history = deal30DaysHistory(json);
					}
				} catch (Exception e) {
					result = false;
					Ln.e(e, "解析历史信息异常");
				} finally {
					if (history == null) {
						result = false;
					}
					if (result) {
						if (ServerConstant.SH01_02_01_03
								.equals(name)) {
							save7DaysHistory(context, history);
						} else if (ServerConstant.SH01_02_01_04
								.equals(name)) {
							save24HoursHistory(context, history);
						} else if (ServerConstant.SH01_02_01_05
								.equals(name)) {
							save30DaysHistory(context, history);
						}
					}
					/* 发送处理结果广播，页面随之更新 */
					MainAcUtil.send2Activity(context, name, 0,
							result);
				}
			}
		}.start();

	}

	@SuppressWarnings("finally")
	public static HomeHistoryList deal7DaysHistory(String json) {
		return handleHistory(json, 7);
	}

	@SuppressWarnings("finally")
	public static HomeHistoryList deal24HoursHistory(String json) {
		return handleHistory(json, 24);
	}

	@SuppressWarnings("finally")
	public static HomeHistoryList deal30DaysHistory(String json) {
		return handleHistory(json, 30);
	}

	/**
	 * 处理主页面上的历史信息
	 * 
	 * @author 曾凡
	 * @param mContext
	 * @param json
	 * @param name
	 * @time 2014年7月4日 上午10:21:25
	 */
	private static HomeHistoryList handleHistory(String json,
			int count) {
		SensorAirDetail tempAir = null;
		SensorGasDetail tempGas = null;
		HomeHistoryList history = null;
		SensorDetailList detailList = null;
		try {
			/* 1. 解析json */
			detailList = SmartHomeJsonUtil.getSensorList(json);
			/* 2. 初始化展示历史信息列表 */
			history = new HomeHistoryList();
			DealUtil.initHistory(history, detailList, count);

			/* 3. 转换格式为历史分析数据 */
			Integer tempCreateDay = 0;

			for (SensorDetail detail : detailList
					.getSensorList()) {
				tempAir = detail.getAir();
				tempGas = detail.getGas();
				if (tempAir != null
						&& tempAir.getSensorId() != null) {
					/* 历史传感器的日期 */
					tempCreateDay = getCreateDay(tempAir, count);
					DealUtil.transAirHistory(history, tempAir,tempCreateDay,
							count);
				}
				if (tempGas != null
						&& tempGas.getSensorId() != null) {
					/* 历史传感器的日期 */
					tempCreateDay = getCreateDay(tempGas, count);
					DealUtil.transGasHistory(history, tempGas,tempCreateDay,
							 count);
				}
			}
		} catch (Exception e) {
			Ln.e("本地分析历史记录异常！", e);
		} finally {
			return history;
		}
	}

	/**
	 * 处理传感器实时明细数据的解析
	 * 
	 * @author 曾凡
	 * @param context
	 * @param json
	 * @param name
	 * @time 2014年7月10日 下午5:16:30
	 */
	public static void dealDetail(final MainActivity context,
			final String json, final String name) {

		new Thread() {
			@Override
			public void run() {
				super.run();
				boolean result = true;
				SensorDetailList detailList = null;
				try {
					/* 1. 解析json */
					detailList = SmartHomeJsonUtil
							.getSensorList(json);
				} catch (Exception e) {
					result = false;
					Ln.e(e, "解析历史信息异常");
				} finally {
					if (json == null) {
						result = false;
					}
					if (result) {
						saveDetail(context, detailList);
					}
					/* 发送处理结果广播，页面随之更新 */
					MainAcUtil.send2Activity(context, name, 0,
							result);
				}
			}
		}.start();
	}

	/**
	 * 用重载代替instanceOf 实现获取创建日
	 * 
	 * @author 曾凡
	 * @param air
	 * @return
	 * @time 2014年7月4日 上午11:41:05
	 */
	private static Integer getCreateDay(SensorAirDetail air,
			int count) {
		if (count == 24) {
			return DateUtils.getyyyymmddhhIntFtIntDate(air.getCreateTime());
		} else {
			return DateUtils.deletehhmmss(air.getCreateTime());
		}
	}
	private static Integer getCreateDay(SensorGasDetail gas,
			int count) {
		if (count == 24) {
			return DateUtils.getyyyymmddhhIntFtIntDate(gas.getCreateTime());
		} else {
			return DateUtils.deletehhmmss(gas.getCreateTime());
		}
	}

	public static void save7DaysHistory(Context context,
			HomeHistoryList history) {
		saveHistory(context, history, HOME_HISTORY_7DAYS_KEY);
	}

	public static void save24HoursHistory(Context context,
			HomeHistoryList history) {
		saveHistory(context, history, HOME_HISTORY_24HOURS_KEY);
	}

	public static void save30DaysHistory(Context context,
			HomeHistoryList history) {
		saveHistory(context, history, HOME_HISTORY_30DAYS_KEY);
	}

	/**
	 * 保存[传感器列表]
	 * 
	 * @author 曾凡
	 * @param context
	 * @param history
	 * @time 2014年7月1日 下午5:18:13
	 */
	public static void saveHistory(Context context,
			HomeHistoryList history, String key) {

		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(history);
		} catch (IOException e) {
			Ln.e("存储" + history + "异常！", e);
			e.printStackTrace();
		}
		try {
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.putString(key, saveByteStr);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + history + "异常！", e);
			e.printStackTrace();
		}

	}

	/**
	 * 清楚历史
	 * 
	 * @author 曾凡
	 * @param context
	 * @param history
	 * @time 2014年7月10日 下午4:45:00
	 */
	public static void clearHistory(Context context) {

		Editor userData = context.getSharedPreferences(
				SAVE_FILE_NAME, 0).edit();
		userData.putString("HOME_HISTORY_KEY", "");

		userData.commit();
	}

	/**
	 * 传感器的明细
	 * 
	 * @author 曾凡
	 * @param context
	 * @param history
	 * @time 2014年7月10日 下午4:45:00
	 */
	private static void saveDetail(Context context,
			SensorDetailList detailList) {
		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(detailList);

			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.putString(HOME_DETAIL_KEY, saveByteStr);
			userData.commit();
		} catch (Exception e) {
			Ln.e(e, "存储" + detailList + "异常！");
		}
	}

	/**
	 * 清除传感器本地
	 * 
	 * @author 曾凡
	 * @param context
	 * @param history
	 * @time 2014年7月10日 下午4:45:00
	 */
	public static void clearDetail(Context context) {
		try {
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.putString("HOME_DETAIL_KEY", "");

			userData.commit();
		} catch (Exception e) {
			Ln.e(e, "清除传感器本地异常！");
		}
	}

	/**
	 * 删除缓存
	 * 
	 * @author 曾凡
	 * @param context
	 * @time 2014年7月21日 下午2:07:53
	 */
	public static void remove7DaysHistory(Context context) {
		Editor userData = context.getSharedPreferences(
				SAVE_FILE_NAME, 0).edit();
		userData.remove(HOME_HISTORY_7DAYS_KEY);
		userData.commit();
	}

	/**
	 * 删除缓存
	 * 
	 * @author 曾凡
	 * @param context
	 * @time 2014年7月21日 下午2:07:53
	 */
	public static void removeDetail(Context context) {
		Editor userData = context.getSharedPreferences(
				SAVE_FILE_NAME, 0).edit();
		userData.remove(HOME_DETAIL_KEY);
		userData.commit();
	}

	public static HomeHistoryList get7DaysHistory(Context context) {
		return getHistory(context, HOME_HISTORY_7DAYS_KEY);
	}

	public static HomeHistoryList get30DaysHistory(
			Context context) {
		return getHistory(context, HOME_HISTORY_30DAYS_KEY);
	}

	public static HomeHistoryList get24HoursHistory(
			Context context) {
		return getHistory(context, HOME_HISTORY_24HOURS_KEY);
	}

	/**
	 * 获取[传感器列表]
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年7月1日 下午5:18:29
	 */
	public static HomeHistoryList getHistory(Context context,
			String key) {
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_FILE_NAME, 0);
		String saveStr = userData.getString(key, "");
		HomeHistoryList history = null;
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				history = (HomeHistoryList) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
				e.printStackTrace();
			}
		}
		return history;

	}

	/**
	 * 获取传感器的明细
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年7月10日 下午4:47:25
	 */
	public static SensorDetailList getDetail(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_FILE_NAME, 0);
		String saveStr = userData.getString(HOME_DETAIL_KEY, "");
		SensorDetailList detail = null;
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				detail = (SensorDetailList) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
				e.printStackTrace();
			}
		}
		return detail;

	}

	/**
	 * 排名
	 * 
	 * @author 曾凡
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void dealRank(Context context, String json,
			String actionType) {
		boolean result = true;
		String message = null;
		int type = 0;
		try {
			if (json == null || "".equals(json)) {
				Ln.e("排名没有返回值");
				return;
			}
			JSONObject jsonObject = new JSONObject(json);
			if (jsonObject.getInt("code") == 0) {
				type = 2;
				message = "0%";
			} else {
				if (jsonObject.has("data")) {
					if (jsonObject.getString("data") == null) {
						message = "0%";
					} else {
						message = jsonObject.getString("data");
					}

					if (jsonObject.getString("dataObject")
							.equals("up")) {
						type = 1;
					}
					if (jsonObject.getString("dataObject")
							.equals("down")) {
						type = 2;
					}

				} else {
					message = "0%";
				}
			}

		} catch (Exception e) {
			Ln.e(e, "");
			message = SmartHomeJsonUtil.getMessage(json);
			result = false;
		} finally {
			/* 3.发送处理结果广播，页面随之更新 */
			MainAcUtil.send2ActivityData(context, actionType,
					type, result, message);
		}
	}
}
