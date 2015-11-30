package com.hw.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
/**
 * 
 * <p>
 * 硬件工具类<br>
 * </p>
 */
public class HardUtil {

	/**
	 * 唯一的设备ID：GSM手机的IMEI和CDMA的MEID Return null if device ID is not available.
	 * 
	 * @return
	 */

	public static String getDeviceId(Context context) {
		TelephonyManager tm = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String deviceId = tm.getDeviceId();
		if (deviceId == null || deviceId.trim().length() == 0) {
			deviceId = getLocalMacAddress(context);
			deviceId = deviceId.replaceAll("\r|\n|:", "");
		}
		return deviceId;
	}

	/**
	 * 获取手机的MAc地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getLocalMacAddress(Context context) {
		WifiManager wifi = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress().replaceAll("\r|\n|:", "");
	}

	/**
	 * 获取设备型号
	 * 
	 * @return
	 */
	public static String getDevicename(Context context) {
		// 设备名称
		Build build = new Build();
		String modle = "";
		modle = build.MODEL;

		if (!StringUtil.isEmpty(modle)) {
			return modle;
		} else {
			return "无法获取手机型号";
		}

	}

	/**
	 * 17 * TelephonyManager提供设备上获取通讯服务信息的入口。 应用程序可以使用这个类方法确定的电信服务商和国家
	 * 以及某些类型的用户访问信息。
	 * 
	 * 18 * 应用程序也可以注册一个监听器到电话收状态的变化。不需要直接实例化这个类
	 * 
	 * 19 * 使用Context.getSystemService(Context.TELEPHONY_SERVICE)来获取这个类的实例。
	 * 
	 * 20
	 */
	/**
	 * 23 * 国际移动用户识别码
	 * 
	 * 24
	 */

	/**
	 * 33 * Role:获取当前设置的电话号码
	 * 
	 * 34 * <BR>
	 * Date:2012-3-12
	 * 
	 * 35 * <BR>
	 * @author CODYY)peijiangping
	 * 
	 * 36
	 */
	public static String getNativePhoneNumber(Context context) {
		String nativePhoneNumber = "";
		nativePhoneNumber = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
		return nativePhoneNumber;

	}

	public static String getProvidersName(Context context) {
		String IMSI;
		String providersName = "";
		IMSI = ((TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE)).getSubscriberId();
		System.out.println(IMSI == null ? "" : IMSI);
		if (IMSI != null) {
			if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
				providersName = "中国移动";
			} else if (IMSI.startsWith("46001")) {
				providersName = "中国联通";
			} else if (IMSI.startsWith("46003")) {
				providersName = "中国电信";
			}
		}

		return providersName;
	}

	// GPS 模块功能是否正常
	public static boolean isGPSEnabled(Context context) {

		LocationManager alm = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);

		return alm
				.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
	}

	// 打电话
	public static void dialog(Context context, String inputStr) {
		Intent phoneIntent = new Intent("android.intent.action.CALL",
				Uri.parse("tel:" + inputStr));

		context.startActivity(phoneIntent);

	}
	
	// 查找前置摄像头
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static int FindFrontCamera() {
		int cameraCount = 0;
		Camera.CameraInfo cameraInfo = new CameraInfo();
		cameraCount = Camera.getNumberOfCameras();
		for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo);
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
				return camIdx;
			}
		}
		return -1;

	}
	
	// 查找后置摄像头
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	public static int FindBackCamera() {
		int cameraCount = 0;
		Camera.CameraInfo cameraInfo = new CameraInfo();
		cameraCount = Camera.getNumberOfCameras();
		for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
			Camera.getCameraInfo(camIdx, cameraInfo);
			if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_BACK) {
				return camIdx;
			}
		}
		return -1;
	}

}
