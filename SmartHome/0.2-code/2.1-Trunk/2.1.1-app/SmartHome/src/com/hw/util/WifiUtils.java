package com.hw.util;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * @author 曾凡
 * @time 2014年10月16日 上午11:08:17
 */
public class WifiUtils {
	
	public static String getSSID(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
			String ssid = info.getSSID();
			if (ssid != null && ssid.contains("\"")) {
				ssid = ssid.replace("\"", "");
			}
			return ssid;
		}
		return null;
	}

	/**
	 * 获取Mac地址
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年10月16日 上午11:09:18
	 */
	public String getLocalMacAddress(Context context) {

		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifi.getConnectionInfo();

		return info.getMacAddress();

	}

}
