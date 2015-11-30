package com.hw.smarthome.service.util;

import android.R.integer;

import com.easylink.android.FirstTimeConfig;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年7月3日 上午10:18:34
 */
public class SmartHomeServiceUtil {
	public static int getSensorType(SensorDetail sensor) {
		if (sensor == null) {
			return 0;
		}
		if (sensor.getAir() != null
				&& sensor.getAir().getSensorId() != null
				&& !"".equals(sensor.getAir().getSensorId())) {
			return SensorConstant.SENSOR_TYPE_AIR;
		}
		if (sensor.getGas() != null
				&& sensor.getGas().getSensorId() != null
				&& !"".equals(sensor.getGas().getSensorId())) {
			return SensorConstant.SENSOR_TYPE_GAS;
		}
		if (sensor.getCtrl() != null
				&& sensor.getCtrl().getDeviceId() != null
				&& !"".equals(sensor.getCtrl().getDeviceId())) {
			return SensorConstant.SENSOR_TYPE_CTRL;
		}
		return SensorConstant.SENSOR_TYPE_ERROR;
	}

	/**
	 * 根据设备ID获取设备类型
	 * 
	 * @author 曾凡
	 * @param sensorId
	 * @return
	 * @time 2014年7月3日 上午10:07:28
	 */
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

	/**
	 * 获取默认的传感器值信息
	 * 
	 * @author 曾凡
	 * @param type
	 * @return
	 * @time 2014年7月3日 上午10:45:10
	 */
	public static String getDefaultSensorName(int type) {
		switch (type) {
		case SensorConstant.SENSOR_TYPE_GAS:
			return "可燃气体";
		case SensorConstant.SENSOR_TYPE_AIR:
			return "空气质量";
		case SensorConstant.SENSOR_TYPE_SOMKE:
			return "烟雾传感器";
		default:
			return "未定义设备";
		}
	}

	/**
	 * 设置传感器的WIFI联网
	 * 
	 * @author 曾凡
	 * @time 2014年7月2日 下午3:32:42
	 */
	public static void settingSensorWIFI(
			FirstTimeConfig firstConfig) {
		try {

			firstConfig.transmitSettings();

			MainAcUtil.send2Activity(MainActivity.mContext,
					ServerConstant.SH01_01_01_05, 0, true);

		} catch (Exception e) {
			Ln.e(e, "配网失败！");
			MainAcUtil.send2Activity(MainActivity.mContext,
					ServerConstant.SH01_01_01_05, 0, false);
		}
	}

	/**
	 * 关闭配网
	 * 
	 * @author 曾凡
	 * @time 2014年8月2日 上午10:08:14
	 */
	public static void stopPacketData(FirstTimeConfig firstConfig) {
		if (firstConfig != null) {
			try {
				firstConfig.stopTransmitting();
				MainAcUtil.send2Activity(MainActivity.mContext,
						ServerConstant.SH01_01_01_06, 0, true);
			} catch (Exception e) {
				Ln.e("配网关闭失败！");
				MainAcUtil.send2Activity(MainActivity.mContext,
						ServerConstant.SH01_01_01_06, 0, false);
			}
		}
	}

}
