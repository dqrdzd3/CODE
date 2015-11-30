package com.hw.smarthome.server.deal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.hw.smarthome.po.SensorAlert;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.DateUtils;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;

/**
 * 处理[传感器]功能
 * 
 * @author 曾凡
 * @time 2014年6月26日 下午4:42:14
 */
public class DealWithSensor {

	public static final String SENSOR_LIST_KEY = "sensor_list";
	public static final String SAVE_FILE_NAME = "sensor";

	/**
	 * 处理[配置设备]
	 * 
	 * @author 曾凡
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public static void dealSensor(Context context, String json,
			String actionType) {
		boolean result = true;
		String message = null;
		try {
			/* 1.解析json */
			SensorDetailList detailList = SmartHomeJsonUtil
					.getSensorList(json);

			if (detailList != null
					&& detailList.getSensorList() != null
					&& detailList.getSensorList().size() > 0) {

				/* 2.将结果持久化 */
				saveSensorList(context, detailList);

			} else {
				if (context != null) {
					removeSensor(context);
				}

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

	/**
	 * 判断添加的设备是否在线
	 * 
	 * @author 曾凡
	 * @param context
	 * @param detailList
	 * @time 2014年7月23日 上午10:50:48
	 */
	public static void initIsOnLine(Context context,
			List<SensorDetail> detailList) {
		/* 1.获取实时数据，通过判断实时数据的时间去确定当前配置的设备是否在线 */
		SensorDetailList realTimeList = DealWithHome
				.getDetail(context);
		boolean isOnline = false;
		if (realTimeList == null)
			return;
		if (detailList == null)
			return;
		for (SensorDetail detail : detailList) {
			for (SensorDetail realTime : realTimeList
					.getSensorList()) {
				/* 判断设备编号是否一致 */
				if (detail.getSensorId() == null
						|| realTime.getSensorId() == null) {
					break;
				}
				if (detail.getSensorId().equals(
						realTime.getSensorId())) {
					isOnline = isInTime(getCreateTime(realTime));
					setOnline(detail, isOnline);
				}
			}
		}
	}

	/**
	 * 判断添加的设备是否在线
	 * 
	 * @author 曾凡
	 * @param context
	 * @param detailList
	 * @time 2014年7月23日 上午10:50:48
	 */
	public static void initIsOnLine(Context context,
			SensorDetail sensor) {
		/* 1.获取实时数据，通过判断实时数据的时间去确定当前配置的设备是否在线 */
		SensorDetailList realTimeList = DealWithHome
				.getDetail(context);
		boolean isOnline = false;
		if (realTimeList == null)
			return;
		if (sensor == null)
			return;
		for (SensorDetail realTime : realTimeList
				.getSensorList()) {
			/* 判断设备编号是否一致 */
			if (sensor.getSensorId() == null
					|| realTime.getSensorId() == null) {
				break;
			}
			if (sensor.getSensorId().equals(
					realTime.getSensorId())) {
				isOnline = isInTime(getCreateTime(realTime));
				setOnline(sensor, isOnline);
			}
		}
	}

	/**
	 * 获取创建时间
	 * 
	 * @author 曾凡
	 * @param sensor
	 * @return
	 * @time 2014年7月23日 上午11:34:50
	 */
	private static String getCreateTime(SensorDetail sensor) {
		int type = SmartHomeServiceUtil.getSensorType(sensor);
		switch (type) {
		case SensorConstant.SENSOR_TYPE_GAS:
			return sensor.getGas().getCreateTime();
		case SensorConstant.SENSOR_TYPE_AIR:
			return sensor.getAir().getCreateTime();
		case SensorConstant.SENSOR_TYPE_CTRL:
			return sensor.getCtrl().getCreateTime();
		default:
			return "";
		}
	}

	/**
	 * 设置是否在线
	 * 
	 * @author 曾凡
	 * @param detail
	 *            从服务端获取到的设备列表明细
	 * @param isOnline
	 *            设备是否在线
	 * @time 2014年7月23日 上午11:08:55
	 */
	private static void setOnline(SensorDetail detail,
			boolean isOnline) {
		SensorAlert alert = detail.getAlert();
		if (alert == null) {
			alert = new SensorAlert();
		}
		alert.setSensorId(detail.getSensorId());
		alert.setOnline(isOnline);
		detail.setAlert(alert);
	}

	/**
	 * 判断是否在时间段内有数据的更新，国内算法
	 * 
	 * @author 曾凡
	 * @param realTime
	 *            实时数据的时间
	 * @param sensorTime
	 *            传感器列表的时间
	 * @return
	 * @time 2014年7月23日 上午10:55:23
	 */
	private static boolean isInTime(String realTime) {
		boolean res = true;
		/* 得出时间之间的分钟数 */
		Integer min = DateUtils.getMinitusFromNow(realTime);
		// if (min > 3) {// 20141205 曾 由2分钟改完两分钟
		// return false;
		// }
		// if (min > 4) {// 20150706 曾 4分钟
		if (min > 5) {// 20150713 曾 5分钟
			res = false;
		}
		return res;
	}

	private static Map<String, String> hashMap = new HashMap<String, String>();

	/**
	 * 国际更新时间，英文版专用
	 * 
	 * @author 曾凡
	 * @param detail
	 * @return
	 * @time 2015年8月26日 上午10:57:08
	 */
	private static boolean inInTime(SensorDetail detail) {
		boolean res = false;
		String lastTimeStr = hashMap.get(detail.getSensorId());
		String curTimeStr = getCreateTime(detail);
		if (lastTimeStr == null || "".equals(lastTimeStr)) {
			res = true;
		} else {
			int differ = DateUtils.getMinitusInDates(
					lastTimeStr, curTimeStr);
			if (differ < 5) {
				res = true;
			} else {
				res = false;
			}
		}
		hashMap.put(detail.getSensorId(), curTimeStr);
		return res;
	}

	/**
	 * 删除缓存
	 * 
	 * @author 曾凡
	 * @param context
	 * @time 2014年7月21日 下午2:07:53
	 */
	public static void removeSensor(Context context) {
		try {
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.clear();
			userData.commit();
		} catch (Exception e) {
			Ln.e(e);
		}
	}

	/**
	 * 保存[传感器列表]
	 * 
	 * @author 曾凡
	 * @param context
	 * @param detailList
	 * @time 2014年7月1日 下午5:18:13
	 */
	public static void saveSensorList(Context context,
			SensorDetailList detailList) {
		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(detailList);

			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.putString(SENSOR_LIST_KEY, saveByteStr);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + detailList + "异常！", e);
			e.printStackTrace();
		}
	}

	/**
	 * 获取[传感器列表]
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年7月1日 下午5:18:29
	 */
	public static SensorDetailList getSensorList(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_FILE_NAME, 0);
		String saveStr = userData.getString(SENSOR_LIST_KEY, "");
		SensorDetailList detailList = null;
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				detailList = (SensorDetailList) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
			}
		}
		return detailList;

	}

	/**
	 * 清除传感器列表
	 * 
	 * @author 曾凡
	 * @param context
	 * @param history
	 * @time 2014年7月10日 下午4:45:00
	 */
	public static void clearDetailList(Context context) {
		try {
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.clear();
			userData.commit();
		} catch (Exception e) {
			Ln.e("删除实时明细异常！", e);
			e.printStackTrace();
		}
	}

}
