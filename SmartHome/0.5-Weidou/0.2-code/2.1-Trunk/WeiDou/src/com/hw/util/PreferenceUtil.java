package com.hw.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Base64;

/**
 * 
 * @ClassName: Preference
 * @Description: TODO 保存系统运行数据
 * @author lijing
 * @date 2013-9-16 下午1:56:43
 * 
 */
public class PreferenceUtil {

	private static final String PREFS_NAME = "ONE-PLATFORM";
	/**
	 * Object转String 存数据
	 * @author 曾凡
	 * @param obj
	 * @return
	 * @throws IOException
	 * @time 2014年7月1日 下午2:51:55
	 */
	public static String obj2String(Object obj)
			throws IOException {
		// 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
		String resStr = null;
		ByteArrayOutputStream bOut = null;
		ObjectOutputStream oOut = null;
		try {
			// 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
			bOut = new ByteArrayOutputStream();
			// 然后将得到的字符数据装载到ObjectOutputStream
			oOut = new ObjectOutputStream(bOut);
			// writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
			oOut.writeObject(obj);
			resStr = new String(Base64.encode(
					bOut.toByteArray(), Base64.DEFAULT));
			// 关闭objectOutputStream
			oOut.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bOut != null) {
				bOut.close();
			}
			if (oOut != null) {
				oOut.close();
			}
		}
		return resStr;
	}
	/**
	 * String转Object取数据
	 * @author 曾凡
	 * @param saveData
	 * @return
	 * @throws Exception
	 * @time 2014年7月1日 下午2:51:34
	 */
	public static Object String2Object(String saveData)
			throws Exception {
		Object obj = null;
		ByteArrayInputStream bIn = null;
		ObjectInputStream oIn = null;
		try {
			byte[] mobileBytes = Base64
					.decode(saveData.getBytes(),
							Base64.DEFAULT);
			bIn = new ByteArrayInputStream(mobileBytes);
			oIn = new ObjectInputStream(bIn);
			obj = oIn.readObject();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bIn != null) {
				bIn.close();
			}
			if (bIn != null) {
				oIn.close();
			}
		}
		return obj;
	}

	/**
	 * 函数名：saveSharedPreferencesSingle 功能描述：保存单条信息
	 * 
	 * @param sharedName
	 *            保存文件的名称
	 * @param key
	 *            修改的键
	 * @param value
	 *            值
	 */
	public void saveSharedPreferencesSingle(Context context,
			String sharedName, String key, String value) {
		Editor userData = context.getSharedPreferences(
				sharedName, 0).edit();
		userData.putString(key, value);
		userData.commit();

	}

	/**
	 * 函数名：getSharedPreferencesSingle 功能描述：获得单个值
	 * 
	 * @param sharedName
	 *            保存文件的名称
	 * @param key
	 *            修改的键
	 */
	public String getSharedPreferencesSingle(Context context,
			String sharedName, String key) {
		SharedPreferences userData = context
				.getSharedPreferences(sharedName, 0);
		return userData.getString(key, "");

	}

	/**
	 * 函数名：cleanLoginInfo 功能描述：清除信息
	 */
	public void cleanLoginInfo(Context context, String sharedName) {
		Editor userData = context.getSharedPreferences(
				sharedName, 0).edit();
		userData.clear();
		userData.commit();
	}

	public static void saveIsShowGuide(Context context,
			boolean isShowGuide) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("isShowGuide", isShowGuide);
		editor.commit();
	}

	public static boolean getIsShowGuide(Context context) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		return settings.getBoolean("isShowGuide", true);
	}

	public static void saveThemeId(Context context, int id) {
		SharedPreferences settings = context
				.getSharedPreferences(PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putInt("themeID", id);
		editor.commit();
	}

	public static void saveDeviceSN(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("device_num", s);
		editor.commit();
	}

	public static void saveAccount(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("phone_account", s);
		editor.commit();
	}

	public static String getDeviceSN(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getString("device_num", "");
	}

	public static String getAccount(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getString("phone_account", "");
	}

	public static void saveIPAddress(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("ip_address", s);
		editor.commit();
	}

	public static String getIPAddress(Context c) {
		SharedPreferences setting = c.getSharedPreferences(
				PREFS_NAME, 0);
		String ip = setting.getString("ip_address",
				"218.206.237.125");
		return ip;
	}

	public static void saveIPProt(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("ip_port", s);
		editor.commit();
	}

	public static String getIpPort(Context c) {
		SharedPreferences setting = c.getSharedPreferences(
				PREFS_NAME, 0);
		String port = setting.getString("ip_port", "8080");
		return port;
	}

	public static void saveRealtimeDataFrequency(Context c,
			int seconds) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putInt("realtime_data_frequency", seconds);
		editor.commit();
	}

	public static int getRealtimeDataFrequency(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		int f = settings.getInt("realtime_data_frequency",
				3 * 1000);
		return f;
	}

	public static String getBaseUrl(Context c) {
		String url = "http://" + getIPAddress(c) + ":"
				+ getIpPort(c) + "/ews/";
		return url;
	}

	public static void saveMsgSendSwitch(Context c,
			boolean isOpen) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("msg_is_send", isOpen);
		editor.commit();
	}

	public static boolean getMsgSendSwitch(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		boolean isOpen = settings.getBoolean("msg_is_send",
				false);
		return isOpen;
	}

	public static void savePhoneDialSwitch(Context c,
			boolean isOpen) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("phone_is_send", isOpen);
		editor.commit();
	}

	public static boolean getPhoneDialSwitch(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getBoolean("phone_is_send", false);
	}

	public static void saveMsgSendPhone1(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("msg_phone1", s);
		editor.commit();
	}

	public static String getMsgSendPhone1(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getString("msg_phone1", "");
	}

	public static void saveMsgSendPhone2(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("msg_phone2", s);
		editor.commit();
	}

	public static String getMsgSendPhone2(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getString("msg_phone2", "");
	}

	public static void savePhoneHelp1(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("help_phone1", s);
		editor.commit();
	}

	public static String getPhoneHelp1(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getString("help_phone1", "");
	}

	public static void savePhoneHelp2(Context c, String s) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putString("help_phone2", s);
		editor.commit();
	}

	public static String getPhoneHelp2(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getString("help_phone2", "");
	}

	public static int getNotificationTime(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getInt("notificationTime", 1);
	}

	public static void saveNotificationTime(Context c, int select) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putInt("notificationTime", select);
		editor.commit();
	}

	public static void saveMsgSendTime(Context c, int select) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putInt("msgSendTime", select);
		editor.commit();
	}

	public static int getMsgSendTime(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getInt("msgSendTime", 1);
	}

	public static void saveIsLogined(Context c, boolean isLogin) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("is_login", isLogin);
		editor.commit();
	}

	public static boolean getIsLogined(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getBoolean("is_login", false);
	}

	public static void saveLoginPhoneChecked(Context c,
			boolean isLogin) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("login_phone_checked", isLogin);
		editor.commit();
	}

	public static boolean getLoginPhoneChecked(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getBoolean("login_phone_checked", false);
	}

	public static boolean getIsHomeHintImageShowed(Context c) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getBoolean("is_home_showed", false);
	}

	public static void saveIsHomeHintImageShowed(Context c,
			boolean isShowed) {
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("is_home_showed", isShowed);
		editor.commit();
	}
	
	public static void saveIsPushAlarm(Context c,boolean is){
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("is_push_alarm", is);
		editor.commit();
	}
	
	public static boolean getIsPushAlarm(Context c){
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getBoolean("is_push_alarm", true);
	}
	
	public static void saveIsPushWarn(Context c,boolean is){
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("is_push_warn", is);
		editor.commit();
	}
	
	public static boolean getIsPushWarn(Context c){
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getBoolean("is_push_warn", true);
	}
	
	public static void saveIsPushOrdinary(Context c,boolean is){
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		Editor editor = settings.edit();
		editor.putBoolean("is_push_ordinary", is);
		editor.commit();
	}
	
	public static boolean getIsPushOrdinary(Context c){
		SharedPreferences settings = c.getSharedPreferences(
				PREFS_NAME, 0);
		return settings.getBoolean("is_push_ordinary", true);
	}
}
