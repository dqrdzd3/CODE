package com.hw.smarthome.mom.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.smarthome.mom.po.DataItemAFN08MomPo;

public class MOMCache {
	private static ConcurrentHashMap<String, DataItemAFN08MomPo> currentDevicesList = new ConcurrentHashMap<String, DataItemAFN08MomPo>();
	private static ConcurrentHashMap<String, DataItemAFN08MomPo> maxInHourDevicesList = new ConcurrentHashMap<String, DataItemAFN08MomPo>();
	private static ConcurrentHashMap<String, DataItemAFN08MomPo> maxInDayDevicesList = new ConcurrentHashMap<String, DataItemAFN08MomPo>();

	private static ConcurrentHashMap<String, SensorDetail> currentCtrlDevicesList = new ConcurrentHashMap<String, SensorDetail>();

	public static Map<String, DataItemAFN08MomPo> getCurrentDevicesList() {
		return currentDevicesList;
	}

	public static void setCurrentDevicesList(
			ConcurrentHashMap<String, DataItemAFN08MomPo> currentDevicesList) {
		MOMCache.currentDevicesList = currentDevicesList;
	}

	public static Map<String, DataItemAFN08MomPo> getMaxInHourDevicesList() {
		return maxInHourDevicesList;
	}

	public static void setMaxInHourDevicesList(
			ConcurrentHashMap<String, DataItemAFN08MomPo> maxInHourDevicesList) {
		MOMCache.maxInHourDevicesList = maxInHourDevicesList;
	}

	public static Map<String, DataItemAFN08MomPo> getMaxInDayDevicesList() {
		return maxInDayDevicesList;
	}

	public static void setMaxInDayDevicesList(
			ConcurrentHashMap<String, DataItemAFN08MomPo> maxInDayDevicesList) {
		MOMCache.maxInDayDevicesList = maxInDayDevicesList;
	}

	public static ConcurrentHashMap<String, SensorDetail> getCurrentCtrlDevicesList() {
		return currentCtrlDevicesList;
	}

	public static void setCurrentCtrlDevicesList(
			ConcurrentHashMap<String, SensorDetail> currentCtrlDevicesList) {
		MOMCache.currentCtrlDevicesList = currentCtrlDevicesList;
	}

}
