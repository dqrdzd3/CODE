package com.hw.smarthome.cache;

import java.util.HashMap;
import java.util.Map;

import com.hw.smarthome.ctrl.po.SettingStatusPo;

/**
 * @author 曾凡
 * @time 2015年4月23日 上午9:50:02
 */
public class SysCache {
	/**
	 * 联动控制设备功能(CtrlSettingAdapter.java)会用到
	 * Wheel的状态缓存 A1的SensorId={Media,wheel的内容}
	 */
	private static Map<String, Map<String, SettingStatusPo>> wheelCache = new HashMap<String, Map<String, SettingStatusPo>>();

	public static Map<String, Map<String, SettingStatusPo>> getWheelCache() {
		return wheelCache;
	}

	public static void setWheelCache(
			Map<String, Map<String, SettingStatusPo>> wheelCache) {
		SysCache.wheelCache = wheelCache;
	}

}
