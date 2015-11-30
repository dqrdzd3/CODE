package com.hw.smarthome.ui.home.adapter.homeup.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.home.HomeFragment;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.weather.db.po.City;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;
import com.hw.util.UIUtil;

public class HomePagerUtil {
	public static final int LOW = 0;
	public static final int NORMAL = 1;
	public static final int HIGH = 2;

	// 气体类型（0:温度，1:湿度，2:co2，3:pm2.5，4:voc，5:c6h6，6:ch2o,7:酒精，8:co,9:ch4）
	private static final int CO2_NORMAL = 800;
	private static final int CO2_MIDDLE = 1200;

	private static final int TEMPERATURE_THRESHOLD = 25;
	private static final int HUMIDITY_THRESHOLD = 50;
	public static final int PM25_THRESHOLD = 100;
	public static final int PM25_HIGH_THRESHOLD = 200;
	public static final int CH4_THRESHOLD = 7;
	public static final int CO_THRESHOLD = 100;

	public static final int VOC_NORMAL = 20;
	public static final int VOC_MIDDLE = 40;

	public static Map<String, Boolean> blurBgCache = new HashMap<String, Boolean>();

	public static void blurBg(String sensorId, int x, int y,
			Context context) {

		Ln.d("页面滚动:x " + x + ",y " + y);
		if (blurBgCache.get(sensorId) == null) {
			setNormal(sensorId, context);
		} else {
			if (y > 500) {
				if (!blurBgCache.get(sensorId)) {
					setBlur(sensorId, context);
				}
			} else {
				if (blurBgCache.get(sensorId)) {
					setNormal(sensorId, context);
				}
			}
		}

	}

	public static void setNormal(String sensorId, Context context) {
		if (VERSION.SDK_INT > 16) {
			HomeFragment.uiHomeLayout.setBackground(context
					.getResources().getDrawable(
							R.drawable.ui_home_bg));
			blurBgCache.put(sensorId, false);
		}
	}

	public static void setBlur(String sensorId, Context context) {
		if (VERSION.SDK_INT > 16) {
			HomeFragment.uiHomeLayout.setBackground(context
					.getResources().getDrawable(
							R.drawable.ui_home_bg_blur));
			blurBgCache.put(sensorId, true);
		}
	}

	/**
	 * 计算各个控件的高度
	 * 
	 * @author 曾凡
	 * @param down_space_view
	 * @param up_space_view
	 * @param space_view
	 * @param mParentView
	 * @param mMainActivity
	 * @time 2015年7月6日 上午11:09:28
	 */
	public static void updateSpace(
			final FragmentActivity mMainActivity,
			View mParentView, final View space_view,
			final View up_space_view, final View down_space_view) {
		/*
		 * 计算高度，重新布局
		 */
		LinearLayout mainLayout = (LinearLayout) mParentView
				.findViewById(R.id.uiHomeLayout);
		ViewTreeObserver vto = mainLayout.getViewTreeObserver();
		vto.addOnPreDrawListener(new OnPreDrawListener() {
			@Override
			public boolean onPreDraw() {
				LayoutParams params1 = space_view
						.getLayoutParams();
				LayoutParams up = up_space_view
						.getLayoutParams();
				LayoutParams down = down_space_view
						.getLayoutParams();

				Rect frame = new Rect();
				mMainActivity.getWindow().getDecorView()
						.getWindowVisibleDisplayFrame(frame);

				int contentHeight = frame.height()
						- UIUtil.dip2px(mMainActivity, 45);

				int upHeight = (int) (contentHeight * 0.70) - 80;
				int downHeight = (int) (contentHeight * 0.28);
				if (downHeight < 400) {
					upHeight = upHeight + 50;
					downHeight = 350;
					down.height = 250;
					params1.height = contentHeight - upHeight
							- downHeight;
				} else {
					down.height = 430;
					params1.height = contentHeight - upHeight
							- downHeight;
				}
				up.height = upHeight;

				return true;
			}
		});
	}

	public static void chkIsShowBtns(Context context,
			ImageView upgradeImg, ImageView historyAlarm,
			SensorDetail detail) {
		if (HardUpgradeUtil.chkIsShow(context, detail)) {
			upgradeImg.setVisibility(View.VISIBLE);
		} else {
			upgradeImg.setVisibility(View.GONE);
		}
		int type = SmartHomeServiceUtil.getSensorType(detail);
		if (type == SensorConstant.SENSOR_TYPE_GAS) {
			historyAlarm.setVisibility(View.VISIBLE);
		} else {
			historyAlarm.setVisibility(View.GONE);
		}
	}

	public static int getCo2State(String arg) {
		Float tempValue = Float.valueOf(arg == null
				|| "".equals(arg) ? "0" : arg);

		if (tempValue >= CO2_MIDDLE) {
			return HIGH;
		} else if ((tempValue < CO2_MIDDLE)
				&& (tempValue > CO2_NORMAL)) {
			return NORMAL;
		} else if (tempValue <= CO2_NORMAL) {
			return LOW;
		}
		return NORMAL;

	}

	public static int getTemperatureState(String arg) {
		Float tempValue = Float.valueOf(arg == null
				|| "".equals(arg) ? "0" : arg);
		if (tempValue <= (TEMPERATURE_THRESHOLD + 2)
				&& tempValue >= (TEMPERATURE_THRESHOLD - 2)) {
			return NORMAL;
		} else if (tempValue >= TEMPERATURE_THRESHOLD) {
			return HIGH;
		} else if (tempValue < TEMPERATURE_THRESHOLD) {
			return LOW;
		}
		return NORMAL;
	}

	public static int getHumidityState(String arg) {
		Float tempValue = Float.valueOf(arg == null
				|| "".equals(arg) ? "0" : arg);
		if (tempValue < (HUMIDITY_THRESHOLD + 15)
				&& tempValue > (HUMIDITY_THRESHOLD - 15)) {
			return NORMAL;
		} else if (tempValue <= HUMIDITY_THRESHOLD) {
			return LOW;
		} else if (tempValue > HUMIDITY_THRESHOLD) {
			return HIGH;
		}
		return NORMAL;
	}

	public static int getPm25State(String arg) {
		Float tempValue = Float.valueOf(arg == null
				|| "".equals(arg) ? "0" : arg);
		if (tempValue <= PM25_THRESHOLD) {
			return LOW;
		} else if (tempValue > PM25_THRESHOLD
				&& tempValue <= PM25_HIGH_THRESHOLD) {
			return NORMAL;
		} else if (tempValue > PM25_HIGH_THRESHOLD) {
			return HIGH;
		}
		return NORMAL;
	}

	public static int getCH4State(String arg) {
		Float tempValue = Float.valueOf(arg == null
				|| "".equals(arg) ? "0" : arg);
		// if (tempValue == 0) return 0;
		if (tempValue < CH4_THRESHOLD) {
			return NORMAL;
		} else if (tempValue >= CH4_THRESHOLD) {
			return HIGH;
		}
		return NORMAL;
	}

	public static int getCOState(String arg) {
		Float tempValue = Float.valueOf(arg == null
				|| "".equals(arg) ? "0" : arg);
		// if (tempValue == 0.0) return 0;
		if (tempValue < CO_THRESHOLD) {
			return NORMAL;
		} else if (tempValue >= CO_THRESHOLD) {
			return HIGH;
		}
		return NORMAL;
	}

	public static int getVocState(String arg) {
		Float tempValue = Float.valueOf(arg == null
				|| "".equals(arg) ? "0" : arg);
		if ((tempValue > VOC_NORMAL) && (tempValue < VOC_MIDDLE)) {
			return NORMAL;
		} else if (tempValue >= VOC_MIDDLE) {
			return HIGH;
		} else if (tempValue <= VOC_NORMAL) {
			return LOW;
		}
		return NORMAL;
	}

	/**
	 * 保存城市和传感器的对应关系
	 */
	public static final String SENSOR_LIST_KEY = "sensor_city_key";
	public static final String SAVE_FILE_NAME = "sensor_city";

	/**
	 * 删除缓存
	 * 
	 * @author 曾凡
	 * @param context
	 * @time 2014年7月21日 下午2:07:53
	 */
	public static void removeSensor(Context context) {
		try {
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.clear();
			userData.commit();
		} catch (Exception e) {
			Ln.e(e);
		}
	}

	public static void saveCity(Context context, City city,
			String sensorId) {
		Map<String, City> map = getCitys(context);
		if (map == null) {
			map = new HashMap<String, City>();
		}
		map.put(sensorId, city);
		saveCity(context, map);
	}

	public static void saveCity(Context context,
			Map<String, City> map) {
		String saveByteStr = null;
		try {
			saveByteStr = PreferenceUtil.obj2String(map);

			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			userData.putString(SENSOR_LIST_KEY, saveByteStr);
			userData.commit();
		} catch (Exception e) {
			Ln.e("存储" + map + "异常！", e);
			e.printStackTrace();
		}
	}

	public static Map<String, City> getCitys(Context context) {
		SharedPreferences userData = context
				.getSharedPreferences(SAVE_FILE_NAME, 0);
		String saveStr = userData.getString(SENSOR_LIST_KEY, "");
		Map<String, City> map = null;
		if (saveStr != null && !"".equals(saveStr)) {
			try {
				map = (Map<String, City>) PreferenceUtil
						.String2Object(saveStr);
			} catch (Exception e) {
				Ln.e("获取" + saveStr + "异常！", e);
			}
		}
		return map;

	}

	public static City getCity(Context context, String sensorId) {
		Map<String, City> map = getCitys(context);
		if (map == null) {
			return null;
		}
		return map.get(sensorId);
	}
}
