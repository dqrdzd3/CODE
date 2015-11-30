package com.hw.smarthome.server.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import android.content.SharedPreferences.Editor;

import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.ui.home.po.HomeAirHistory;
import com.hw.smarthome.ui.home.po.HomeAlertHistory;
import com.hw.smarthome.ui.home.po.HomeGasHistory;
import com.hw.smarthome.ui.home.po.HomeHistoryList;
import com.hw.util.DateUtils;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年7月4日 上午10:23:07
 */
public class DealUtil {
	/**
	 * 函数名：cleanLoginInfo 功能描述：清除信息
	 */
	public static void cleanSensorList(Context context,
			String fileName) {
		Editor userData = context.getSharedPreferences(fileName,
				0).edit();
		userData.clear();
		userData.commit();
	}

	/**
	 * 获取空气历史
	 * 
	 * @author 曾凡
	 * @param history
	 * @param tempAir
	 * @param tempCreateDay
	 * @time 2014年7月4日 下午12:50:04
	 */
	public static void transAirHistory(HomeHistoryList history,
			SensorAirDetail tempAir, Integer tempCreateDay,
			int count) {
		/* 获取昨天开始的7天的日期 yyyyMMdd */
		Integer[] days = null;
		if (count == 24) {
			days = DateUtils.get24HoursBefore();
		} else if (count == 30) {
			days = DateUtils.get30DaysBefore();
		} else {
			days = DateUtils.get7DaysBefore();
		}
		int tempIndex = -1;
		for (HomeAirHistory airHistory : history.getAirSensors()) {
			if (tempAir.getSensorId().equals(
					airHistory.getSensorId())) {
				tempIndex = returnIndex(days, tempCreateDay);
				if (tempIndex == -1) {
					Ln.w(tempCreateDay + "不在过去");
					break;
				}
				airHistory.getCo2s()[tempIndex] = tempAir
						.getCo2();
				airHistory.getHumiditys()[tempIndex] = tempAir
						.getHumidity();
				airHistory.getPm25s()[tempIndex] = tempAir
						.getPm25();
				airHistory.getTemperatures()[tempIndex] = tempAir
						.getTemperature();
				airHistory.getVocs()[tempIndex] = tempAir
						.getVoc();
				airHistory.getCreateTimes()[tempIndex] = tempCreateDay
						+ "";
			}
		}
	}

	/**
	 * 获取燃气历史
	 * 
	 * @author 曾凡
	 * @param history
	 * @param tempAir
	 * @param tempCreateDay
	 * @time 2014年7月4日 下午12:49:40
	 */
	public static void transGasHistory(HomeHistoryList history,
			SensorGasDetail tempGas, Integer tempCreateDay,
			int count) {
		/* 获取昨天开始的7天的日期 yyyyMMdd */
		Integer[] days = null;
		if (count == 24) {
			days = DateUtils.get24HoursBefore();
		} else if (count == 30) {
			days = DateUtils.get30DaysBefore();
		} else {
			days = DateUtils.get7DaysBefore();
		}
		int tempIndex = -1;
		for (HomeGasHistory gasHistory : history.getGasSensors()) {
			if (tempGas.getSensorId().equals(
					gasHistory.getSensorId())) {
				tempIndex = returnIndex(days, tempCreateDay);
				if (tempIndex == -1) {
					Ln.w(tempCreateDay + "不在过去7天");
					break;
				}
				gasHistory.getSwitchStatuses()[tempIndex] = tempGas
						.getSwitchStatus();
				gasHistory.getCos()[tempIndex] = tempGas.getCo();
				gasHistory.getCh4s()[tempIndex] = tempGas
						.getCh4();
				gasHistory.getCreateTimes()[tempIndex] = tempCreateDay
						+ "";
			}
		}
	}

	/**
	 * 查询target对应在arrays中的下标
	 * 
	 * @author 曾凡
	 * @param arrays
	 * @param target
	 * @return
	 * @time 2014年7月4日 下午12:42:54
	 */
	private static int returnIndex(Integer[] arrays,
			Integer target) {

		for (int i = 0; i < arrays.length; i++) {
			if (arrays[i].equals(target)) {
				return i;
			}
		}
		// int length = 0;
		// String temp = "";
		// String targetStr = "";
		// for (int i : arrays) {
		// length = (i > length) ? (i + "").length() : length;
		// }
		// for (int i = 0; i < arrays.length; i++) {
		// temp = arrays[i] + "";
		// targetStr = target + "";
		// temp = fillStr(temp, length - temp.length());
		// if ((length - targetStr.length()) > 3) {
		// targetStr = fillStr(targetStr, target < 10 ? 1
		// : 0);
		// } else {
		// targetStr = fillStr(targetStr,
		// (length - targetStr.length()));
		// }
		//
		// if (temp.startsWith(targetStr)) {
		// return i;
		// }
		// }
		return -1;
	}

	private static String fillStr(String str, int length) {
		for (int i = 0; i < length; i++) {
			str = "0" + str;
		}
		return str;
	}

	/**
	 * 初始化历史
	 * 
	 * @author 曾凡
	 * @param history
	 * @time 2014年7月4日 下午12:10:09
	 */
	public static void initHistory(HomeHistoryList history,
			SensorDetailList detailList, int count) {
		List<HomeAirHistory> airSensors = new LinkedList<HomeAirHistory>();
		List<HomeGasHistory> gasSensors = new LinkedList<HomeGasHistory>();
		List<HomeAlertHistory> alerts = new LinkedList<HomeAlertHistory>();
		history.setAirSensors(airSensors);
		history.setGasSensors(gasSensors);
		history.setAlerts(alerts);

		HomeAirHistory tempAirHistory = null;
		HomeGasHistory tempGasHistory = null;
		SensorAirDetail tempAir = null;
		SensorGasDetail tempGas = null;
		Map<String, String> airNum = new HashMap<String, String>();
		Map<String, String> gasNum = new HashMap<String, String>();
		for (SensorDetail detail : detailList.getSensorList()) {
			tempAir = detail.getAir();
			tempGas = detail.getGas();
			if (tempAir != null && tempAir.getSensorId() != null) {
				airNum.put(tempAir.getSensorId(),
						tempAir.getName());
			}
			if (tempGas != null && tempGas.getSensorId() != null) {
				gasNum.put(tempGas.getSensorId(),
						tempGas.getName());
			}
		}
		for (Entry<String, String> entry : airNum.entrySet()) {
			tempAirHistory = new HomeAirHistory();
			tempAirHistory.setSensorId(entry.getKey());
			tempAirHistory.setName(entry.getValue());
			initAirHistory(tempAirHistory, count);
			history.getAirSensors().add(tempAirHistory);
		}
		for (Entry<String, String> entry : gasNum.entrySet()) {
			tempGasHistory = new HomeGasHistory();
			tempGasHistory.setSensorId(entry.getKey());
			tempGasHistory.setName(entry.getValue());
			initGasHistory(tempGasHistory, count);
			history.getGasSensors().add(tempGasHistory);
		}
	}

	/**
	 * 初始化燃气传感器历史
	 * 
	 * @author 曾凡
	 * @time 2014年7月4日 下午12:02:37
	 */
	private static void initGasHistory(
			HomeGasHistory gasHistory, int count) {
		String[] cos = new String[count];
		Arrays.fill(cos, "");
		String[] ch4s = new String[count];
		Arrays.fill(ch4s, "");
		String[] switchStatuses = new String[count];
		String[] gasTimes = new String[count];

		gasHistory.setCh4s(ch4s);
		gasHistory.setCos(cos);
		gasHistory.setSwitchStatuses(switchStatuses);
		gasHistory.setCreateTimes(gasTimes);
	}

	/**
	 * 初始化空气传感器历史
	 * 
	 * @author 曾凡
	 * @time 2014年7月4日 下午12:02:37
	 */
	private static void initAirHistory(
			HomeAirHistory airHistory, int count) {
		String[] temperatures = new String[count];
		Arrays.fill(temperatures, "");
		String[] humiditys = new String[count];
		Arrays.fill(temperatures, "");
		String[] co2s = new String[count];
		Arrays.fill(co2s, "");
		String[] pm25s = new String[count];
		Arrays.fill(pm25s, "");
		String[] c6h6 = new String[count];
		Arrays.fill(c6h6, "");
		String[] ch2o = new String[count];
		Arrays.fill(ch2o, "");
		String[] voc = new String[count];
		Arrays.fill(voc, "");
		String[] airTimes = new String[count];
		Arrays.fill(airTimes, "");
		airHistory.setCo2s(co2s);
		airHistory.setTemperatures(temperatures);
		airHistory.setHumiditys(humiditys);
		airHistory.setPm25s(pm25s);
		airHistory.setC6h6s(c6h6);
		airHistory.setCh2os(ch2o);
		airHistory.setVocs(voc);
		airHistory.setCreateTimes(airTimes);
	}
}
