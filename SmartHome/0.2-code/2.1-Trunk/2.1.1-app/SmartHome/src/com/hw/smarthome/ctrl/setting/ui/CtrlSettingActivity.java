package com.hw.smarthome.ctrl.setting.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.hw.smarthome.R;
import com.hw.smarthome.ctrl.setting.ui.adapter.CtrlSettingAdapter;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.util.MainAcUtil;

/**
 * @author 曾凡
 * @time 2015年4月15日 下午2:12:30
 */
public class CtrlSettingActivity extends FragmentActivity {
	private Context mContext;
	private ListView ctrlList = null;
	private CtrlSettingAdapter settingAdapter = null;
	private String sensorId;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ctrl_setting_ui_activity);
		mContext = this;
		ctrlList = (ListView) findViewById(R.id.ctrlSettingList);
		Intent intent = getIntent();
		Bundle pass = intent.getExtras();
		sensorId = pass.getString(MainActivity.KEY_SENSOR_ID);
		SensorDetail detail = HomeUtil.getSensorDetail(mContext,
				sensorId);
		settingAdapter = new CtrlSettingAdapter(mContext, detail);
		ctrlList.setAdapter(settingAdapter);

		((Button) findViewById(R.id.barBtnLeft))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
	}

	@Override
	protected void onResume() {
		super.onResume();
		startHandler();
	}

	@Override
	protected void onPause() {
		super.onPause();
		endReceiver();
	}

	/** 用于执行实时数据更新 */
	private Handler mDetailHandler = new Handler();
	private Runnable updateDetailThread = new Runnable() {
		@Override
		public void run() {
			/* 登陆这个页面后从服务器获取最新的实时数据 */
			MainAcUtil.send2Service(mContext,
					ServerConstant.SH01_02_02_01, 0);
			mDetailHandler.postDelayed(updateDetailThread,
					15 * 1000);

		}
	};
	/** 用于执行更新检测控制信号线程 */
	private Handler mCtrlHandler = new Handler();
	private Runnable updateCtrlThread = new Runnable() {
		@Override
		public void run() {
			mCtrlHandler
					.postDelayed(updateCtrlThread, 15 * 1000);
			settingAdapter.setmSensorDetail(HomeUtil
					.getSensorDetail(mContext, sensorId));
			settingAdapter.notifyDataSetChanged();
		}
	};

	private void startHandler() {
		if (mDetailHandler != null) {
			mDetailHandler.removeCallbacks(updateDetailThread);
			mDetailHandler.post(updateDetailThread);
		}
		if (mCtrlHandler != null) {
			mCtrlHandler.removeCallbacks(updateCtrlThread);
			mCtrlHandler.post(updateCtrlThread);
		}
	}

	private void endReceiver() {
		if (mDetailHandler != null) {
			mDetailHandler.removeCallbacks(updateDetailThread);
		}
		if (mCtrlHandler != null) {
			mCtrlHandler.removeCallbacks(updateCtrlThread);
			mCtrlHandler.post(updateCtrlThread);
		}
	}

}
