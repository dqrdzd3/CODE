package com.hw.smarthome.ui.sensor.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.EditText;

import com.easylink.android.utils.EasyLinkWifiManager;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.util.Ln;

/**
 * 传感器的帮助类
 * 
 * @author 曾凡
 * @time 2014年7月1日 下午5:01:24
 */
public class SensorUtil {
	/**
	 * 获取传感器
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月1日 下午5:25:53
	 */
	public static SensorDetail getSensorEquip(Context context,
			String id) {
		SensorDetailList detailList = DealWithSensor
				.getSensorList(context);
		List<SensorDetail> list = null;
		if (detailList != null) {
			list = detailList.getSensorList();
			for (SensorDetail detail : list) {
				if (id.equalsIgnoreCase(detail.getSensorId())) {
					return detail;
				}
			}
		}
		return null;
	}

	/**
	 * 获取传感器列表
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月1日 下午5:25:53
	 */
	public static List<SensorDetail> getSensorDetails(
			Context context) {
		SensorDetailList detailList = DealWithSensor
				.getSensorList(context);
		List<SensorDetail> list = null;
		if (detailList != null) {
			list = detailList.getSensorList();
		}
		return list;
	}

	/**
	 * 获取被控设备的设备列表
	 * 
	 * @author 曾凡
	 * @param context
	 * @param ctrlId
	 * @param media
	 * @return
	 * @time 2015年4月22日 下午3:38:04
	 */
	public static List<SensorCtrlDetail> getCtrlDetails(
			Context context, String ctrlId, String media) {
		List<SensorCtrlDetail> resList = new LinkedList<SensorCtrlDetail>();
		try {
			for (SensorCtrlDetail temp : getCtrlDetails(context,
					ctrlId)) {
				if (media.equalsIgnoreCase(temp.getLinkSensor())) {
					resList.add(temp);
				}
			}
		} catch (Exception e) {
			Ln.e(e);
		} finally {
			return resList;
		}

	}

	public static List<SensorCtrlDetail> getCtrlDetails(
			Context context, String sensorId) {
		List<SensorCtrlDetail> resList = new LinkedList<SensorCtrlDetail>();
		SensorCtrlDetail temp = null;
		try {
			List<SensorDetail> detailList = getSensorDetails(context);
			for (SensorDetail detail : detailList) {
				temp = detail.getCtrl();
				if (temp != null && temp.getDeviceId() != null
						&& !"".equals(temp.getDeviceId())) {
					if (temp.getCtrlId().equalsIgnoreCase(
							sensorId)) {
						resList.add(temp);
					}
				}
			}
		} catch (Exception e) {
			Ln.e(e);
		} finally {
			return resList;
		}
	}

	/**
	 * 编辑传感器
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午5:28:05
	 */
	public static void editSensorDetail() {
		// FIXME
	}

	/**
	 * 初始化SSID
	 * 
	 * @author 曾凡
	 * @param uiSensorSSID
	 * @time 2014年7月2日 下午2:30:43
	 */
	public static void initSSID(Context context,
			EditText uiSensorSSID) {
		WifiManager myWifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = myWifiManager.getConnectionInfo();
		uiSensorSSID.setText(EasyLinkWifiManager
				.removeSSIDQuotes(wifiInfo.getSSID()));
		uiSensorSSID.setEnabled(false);
		uiSensorSSID.setFocusable(false);
		uiSensorSSID.setFocusableInTouchMode(false);
	}

	public static String[] initSSID(Context context) {
		List<String> ssid = new ArrayList<String>();
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		List<ScanResult> results = null;
		results = wifi.getScanResults();
		if (results != null) {
			for (ScanResult result : results) {
				// String itenName = "BSSID=" + result.BSSID + ", SSID=" +
				// result.SSID
				// + ", capabilities=" + result.capabilities;
				ssid.add(result.SSID);
			}
			String dd = ssid.toString().replace("[", "")
					.replace("]", "");
			return dd.split(",");
		}
		return null;
	}

}
