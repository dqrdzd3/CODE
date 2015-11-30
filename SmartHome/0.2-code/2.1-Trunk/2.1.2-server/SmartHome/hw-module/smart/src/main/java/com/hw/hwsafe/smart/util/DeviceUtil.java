package com.hw.hwsafe.smart.util;

import com.hw.hwsafe.smart.pojo.SensorConstant;

public class DeviceUtil {
	public static int getSensorTypeWithId(String sensorId) {
		if (sensorId == null || "".equals(sensorId)) {
			return SensorConstant.SENSOR_TYPE_ERROR;
		}
		if (sensorId.length() < 1) {
			return SensorConstant.SENSOR_TYPE_ERROR;
		}
		int typeSrc = Integer.valueOf(sensorId.substring(0, 1),
				16);
		if (typeSrc <= 4) {
			return SensorConstant.SENSOR_TYPE_GAS;
		} else if (typeSrc > 4 && typeSrc <= 8) {
			return SensorConstant.SENSOR_TYPE_AIR;
		} else if (typeSrc > 8 && typeSrc <= 10) {
			return SensorConstant.SENSOR_TYPE_SOMKE;
		}
		return SensorConstant.SENSOR_TYPE_ERROR;
	}
}
