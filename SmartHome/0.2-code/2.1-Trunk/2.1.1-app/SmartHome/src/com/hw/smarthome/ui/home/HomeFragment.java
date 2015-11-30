package com.hw.smarthome.ui.home;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Handler;

import com.hw.smarthome.po.HistoryAlarm;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.home.base.HomeFragmentBase;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;

/**
 * 主页面
 * 
 * @author 曾凡
 * @time 2014年6月9日 上午10:48:12
 */
public class HomeFragment extends HomeFragmentBase {
	private static HomeFragment instance = null;

	public static HomeFragment getInstance() {
		if (instance == null) {
			instance = new HomeFragment();
		}
		return instance;
	}

	private Handler mHistoryHandler = new Handler();
	private Runnable updateHistoryThread = new Runnable() {
		@Override
		public void run() {
			/* 如果没有历史数据2秒刷新一次 */
			sensorList = SensorUtil
					.getSensorDetails(mMainActivity);
			Ln.d("请求服务端[SH01_01_01_03]查看已发现设备 ");
			if (sensorList == null) {
				/* 登陆这个页面后从服务器获取最新的列表 */
				MainAcUtil.send2Service(mMainActivity,
						ServerConstant.SH01_01_01_03, 0);
				mHistoryHandler.postDelayed(updateHistoryThread,
				// 15*1000);
						15 * 1000);
			} else {
				/* 请求获取7天历史数据 */
				MainAcUtil.send2Service(mMainActivity,
						ServerConstant.SH01_02_01_03, 0);

				/* 如果用户在当前页面则15分钟请求一次 */
				mHistoryHandler.postDelayed(updateHistoryThread,
						2 * 60 * 1000);
			}

		}

	};
	/** 用于执行实时数据更新 FIXME功能完善后使用任务调度 */
	private Handler mDetailHandler = new Handler();
	private Runnable updateDetailThread = new Runnable() {
		@Override
		public void run() {
			/* 登陆这个页面后从服务器获取最新的实时数据 */
			MainAcUtil.send2Service(mMainActivity,
					ServerConstant.SH01_02_02_01, 0);
			/* 2秒更新一次,TODO 调整时间，XXX别忘了在正式使用时恢复到合理的时间 */
			mDetailHandler.postDelayed(updateDetailThread,
					15 * 1000);

		}
	};

	/** 用于执行页面数据更新 */
	List<HistoryAlarm> alarmList;
	private Handler mAlarmHandler = new Handler();
	private Runnable updateAlarmThread = new Runnable() {
		@Override
		public void run() {
			int gasSensor = 0;

			/* 判断设备列表中有无燃气报警器 */
			if (sensorList != null && sensorList.size() > 0) {
				for (SensorDetail sensor : sensorList) {
					int type = SmartHomeServiceUtil
							.getSensorTypeWithId(sensor
									.getSensorId());
					// HardUpgradeUtil.checkHard(mMainActivity, sensor, false);
					if (type == SensorConstant.SENSOR_TYPE_GAS) {
						gasSensor++;
						queryAlarmHistory(sensor);
					}
				}
			} else {
				/* 如果没有查询到设备则一秒一次建成设备列表 */
				mAlarmHandler.postDelayed(updateAlarmThread,
						15 * 1000);
			}
			if (gasSensor > 0) {
				/* 如果有燃气报警器则60秒请求一次 */
				mAlarmHandler.postDelayed(updateAlarmThread,
						1 * 60 * 1000);
			} else {
				/* 如果没有燃气报警器则3分钟查询一次，因为添加设备一定是需要扫描设备的，扫描插拔电源预热需要三分钟 */
				mAlarmHandler.postDelayed(updateAlarmThread,
						2 * 60 * 1000);
			}

		}

	};

	/**
	 * 请求获取历史数据
	 * 
	 * @author 曾凡
	 * @time 2014年12月11日 下午3:53:45
	 */
	private void queryAlarmHistory(SensorDetail sensor) {
		/* 请求获取历史数据 */
		Map<String, String> map = null;
		/* 获取最新的报警值 */
		map = new HashMap<String, String>();
		map.put("DRIVERID", sensor.getSensorId());
		map.put("PAGENO", "1");
		map.put("PAGESIZE", "1");

		MainAcUtil.send2Service(getActivity(),
				ServerConstant.SH01_02_01_02, 0, map);
	}

	@Override
	protected void startReceiver() {
		initReceiver();
		// 曾凡 20140811 因为太卡了，所以改成弹出框的形式
		if (mHistoryHandler != null) {
			mHistoryHandler.removeCallbacks(updateHistoryThread);
			mHistoryHandler.post(updateHistoryThread);
		}
		if (mDetailHandler != null) {
			mDetailHandler.removeCallbacks(updateDetailThread);
			mDetailHandler.post(updateDetailThread);
		}
		if (mAlarmHandler != null) {
			mAlarmHandler.removeCallbacks(updateAlarmThread);
			mAlarmHandler.post(updateAlarmThread);
		}
	}

	@Override
	protected void endReceiver() {
		if (mHistoryHandler != null) {
			mHistoryHandler.removeCallbacks(updateHistoryThread);
		}
		if (mDetailHandler != null) {
			mDetailHandler.removeCallbacks(updateDetailThread);
		}
		if (mAlarmHandler != null) {
			mAlarmHandler.removeCallbacks(updateAlarmThread);
		}
		if (mReceiver != null) {
			try {
				mMainActivity.unregisterReceiver(mReceiver);
			} catch (Exception e) {
				Ln.e(e, "注销HomeFragment的监听器异常");
			}
		}
	}
}
