package com.hw.smarthome.ctrl.util;

import java.util.List;
import java.util.Map.Entry;

import android.content.Context;

import com.hw.smarthome.po.CtrlContent;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2015年4月22日 下午5:10:52
 */
public class CtrlUtils {
	/**
	 * 实现从缓存中读取注册的设备
	 * 
	 * @author 曾凡
	 * @param sensorId
	 * @param media
	 * @return
	 * @time 2015年4月22日 下午3:41:13
	 */
	public static List<SensorCtrlDetail> getDevices(
			Context context, String sensorId, String media) {
		return SensorUtil.getCtrlDetails(context, sensorId,
				media);
	}

	/**
	 * 获取当前的被控设备的内容
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2015年4月22日 下午6:19:56
	 */
	public static SensorCtrlDetail getCurCtrlDevice(
			Context context, String deviceId) {
		SensorDetail sensorDetail = HomeUtil.getSensorDetail(
				context, deviceId);
		SensorCtrlDetail ctrlEquip = null;
		if (sensorDetail == null) {
			return null;
		}
		SensorCtrlDetail ctrlCur = sensorDetail.getCtrl();
		if (ctrlCur != null && ctrlCur.getDeviceId() != null
				&& !"".equals(ctrlCur.getDeviceId())) {

			SensorDetail equip = SensorUtil.getSensorEquip(
					context, deviceId);
			if (equip != null && equip.getCtrl() != null
					&& equip.getCtrl().getDeviceId() != null
					&& !"".equals(equip.getCtrl().getDeviceId())) {
				ctrlEquip = equip.getCtrl();
				ctrlEquip.setCtrlContent(sensorDetail.getCtrl()
						.getCtrlContent());
				ctrlEquip.setToken(sensorDetail.getCtrl()
						.getToken());
			}
		}
		return ctrlEquip;
	}

	/**
	 * 比较传感器的值
	 * 
	 * @author 曾凡
	 * @param sensorValue
	 * @param wheel9999
	 * @param wheel99
	 * @time 2015年4月22日 下午5:15:17
	 */
	public static void compareSensorValue(Context context,
			SensorDetail sensorDetail, SensorCtrlDetail ctrl,
			int wheel9999, int wheel99) {
		if (ctrl == null) {
			return;
		}
		float sensorValue = (float) 0;
		/* FIXME 控制 */
		String media = ctrl.getLinkSensor();
		if (media == null || "".equals(media)) {
			return;
		}
		if (sensorDetail.getAir() != null
				&& sensorDetail.getAir().getSensorId() != null
				&& !"".equals(sensorDetail.getAir()
						.getSensorId())) {
			if (SensorConstant.MEDIA_TYPE_VOC
					.equalsIgnoreCase(media)) {
				sensorValue = Float.valueOf(sensorDetail
						.getAir().getVoc());
			} else if (SensorConstant.MEDIA_TYPE_CO2
					.equalsIgnoreCase(media)) {
				sensorValue = Float.valueOf(sensorDetail
						.getAir().getCo2());
			} else if (SensorConstant.MEDIA_TYPE_PM25
					.equalsIgnoreCase(media)) {
				sensorValue = Float.valueOf(sensorDetail
						.getAir().getPm25());
			} else if (SensorConstant.MEDIA_TYPE_TEMPERATURE
					.equalsIgnoreCase(media)) {
				sensorValue = Float.valueOf(sensorDetail
						.getAir().getTemperature());
			} else if (SensorConstant.MEDIA_TYPE_HUMIDITY
					.equalsIgnoreCase(media)) {
				sensorValue = Float.valueOf(sensorDetail
						.getAir().getHumidity());
			}
		}
		if (sensorDetail.getGas() != null
				&& sensorDetail.getGas().getSensorId() != null
				&& !"".equals(sensorDetail.getGas()
						.getSensorId())) {
			if (SensorConstant.MEDIA_TYPE_CO
					.equalsIgnoreCase(media)) {
				sensorValue = Float.valueOf(sensorDetail
						.getGas().getCo());
			} else if (SensorConstant.MEDIA_TYPE_CH4
					.equalsIgnoreCase(media)) {
				sensorValue = Float.valueOf(sensorDetail
						.getGas().getCh4());
			}
		}
		int sensor = (int) sensorValue;

		if (sensor >= wheel9999) {
			turnOnDevice(context, ctrl);
		} else if (sensor < wheel99) {
			turnOffDevice(context, ctrl);
		} else {

		}
	}

	/**
	 * 打开设备
	 * 
	 * @author 曾凡
	 * @param context
	 * @param device
	 * @time 2015年4月22日 下午5:15:28
	 */
	public static void turnOnDevice(Context context,
			SensorCtrlDetail device) {
		for (Entry<String, CtrlContent> entry : device
				.getCtrlContent().entrySet()) {
			Ln.i("打开设备[" + entry.getKey() + "]");
			entry.getValue().setSwitchState("01");
		}
		MainAcUtil.send2CtrlService(context,
				ServerConstant.SH01_06_02_01, device);
	}

	/**
	 * 关闭设备
	 * 
	 * @author 曾凡
	 * @param context
	 * @param device
	 * @time 2015年4月22日 下午5:15:33
	 */
	public static void turnOffDevice(Context context,
			SensorCtrlDetail device) {
		for (Entry<String, CtrlContent> entry : device
				.getCtrlContent().entrySet()) {
			Ln.i("关闭设备[" + entry.getKey() + "]");
			entry.getValue().setSwitchState("00");
		}
		MainAcUtil.send2CtrlService(context,
				ServerConstant.SH01_06_02_01, device);

	}

	/**
	 * 传感器类型
	 * 
	 * @author 曾凡
	 * @param context
	 * @param device
	 * @time 2015年4月22日 下午5:15:33
	 */
	public static String getLinkedSensorName(String name) {
		String resultString = "未知";
		try {
			if (SensorConstant.MEDIA_TYPE_CO.equals(name)) {
				resultString = "一氧化碳";
			}
			if (SensorConstant.MEDIA_TYPE_CO2.equals(name)) {
				resultString = "二氧化碳";
			}
			if (SensorConstant.MEDIA_TYPE_CH4.equals(name)) {
				resultString = "燃气";
			}
			if (SensorConstant.MEDIA_TYPE_PM25.equals(name)) {
				resultString = "PM2.5";
			}
			if (SensorConstant.MEDIA_TYPE_TEMPERATURE
					.equals(name)) {
				resultString = "温度";
			}
			if (SensorConstant.MEDIA_TYPE_HUMIDITY.equals(name)) {
				resultString = "湿度";
			}

			if (SensorConstant.MEDIA_TYPE_VOC.equals(name)) {
				resultString = "VOC";
			}
		} catch (Exception e) {
			Ln.e(e, "根据Media获取设备值异常");
			e.printStackTrace();

		}
		return resultString;

	}
}
