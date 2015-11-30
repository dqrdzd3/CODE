package com.hw.smarthome.ui.home.adapter.homeup.pager;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.ctrl.util.CtrlUtils;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.home.adapter.homeup.adpter.ControlPagerSwitcherAdapter;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.util.Ln;

public class HomeFragmentControlPager extends Fragment {
	private static HomeFragmentControlPager instance;
	private SensorDetail mCurrentSensor;
	private View layout;
	private ViewGroup left, right;
	private FragmentActivity mMainActivity;
	private MyReceiver mReceiver;
	private TextView linkSensorVal;
	// private SwitchButton switchButton1;
	private ListView switcherListView;
	private ControlPagerSwitcherAdapter adapter;
	private TextView updateView, statusView, codeView,
			sensorTypeTextView, aliasTextView;

	public static HomeFragmentControlPager getInstance() {

		instance = new HomeFragmentControlPager();

		return instance;
	}

	public void init(SensorDetail currentSensor) {
		mCurrentSensor = currentSensor;

	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.ui_home_control_page,
				container, false);
		initView();
		initInfo();
		initData();
		return layout;
	}

	private void initView() {
		mMainActivity = getActivity();
		sensorTypeTextView = (TextView) layout
				.findViewById(R.id.sensorType);

		aliasTextView = (TextView) layout
				.findViewById(R.id.alias);

		statusView = (TextView) layout.findViewById(R.id.status);

		codeView = (TextView) layout.findViewById(R.id.code);

		linkSensorVal = (TextView) layout
				.findViewById(R.id.linkSensorVal);

		// switchButton1 = (SwitchButton)
		// layout.findViewById(R.id.switch_main_1);
		// switchButton1.setOnCheckedChangeListener(new ChangeClickListner());
		updateView = (TextView) layout
				.findViewById(R.id.uiHomeUpdateTime);
		left = (ViewGroup) layout.findViewById(R.id.leftImage);
		right = (ViewGroup) layout.findViewById(R.id.rightImage);

		List<SensorDetail> detailList = SensorUtil
				.getSensorDetails(mMainActivity);
		if (detailList != null && detailList.size() > 0) {
			for (int i = 0; i < detailList.size(); i++) {
				if (detailList.get(i).getSensorId()
						.equals(mCurrentSensor.getSensorId())) {
					if (i == 0 && i == detailList.size() - 1) {
						left.setVisibility(View.INVISIBLE);
						right.setVisibility(View.INVISIBLE);
					} else if (i == 0) {
						left.setVisibility(View.INVISIBLE);
						right.setVisibility(View.INVISIBLE);
					} else if (i == detailList.size() - 1) {
						left.setVisibility(View.VISIBLE);
						right.setVisibility(View.INVISIBLE);
					} else {
						left.setVisibility(View.VISIBLE);
						right.setVisibility(View.VISIBLE);
					}

				}
			}

		} else {
			left.setVisibility(View.INVISIBLE);
			right.setVisibility(View.INVISIBLE);
		}

		switcherListView = (ListView) layout
				.findViewById(R.id.listSwitcher);

		SensorCtrlDetail cur = CtrlUtils.getCurCtrlDevice(
				mMainActivity, mCurrentSensor.getCtrl()
						.getDeviceId());
		if (cur != null) {
			adapter = new ControlPagerSwitcherAdapter(
					mMainActivity, cur);
			switcherListView.setAdapter(adapter);
			setListViewHeight(switcherListView);
		}

	}

	private void initInfo() {
		sensorTypeTextView.setText(CtrlUtils
				.getLinkedSensorName(mCurrentSensor.getCtrl()
						.getLinkSensor()));
		aliasTextView
				.setText(mCurrentSensor.getCtrl().getName());
		codeView.setText(mCurrentSensor.getSensorId());

	}

	private void initData() {
		SensorDetail detail = HomeUtil.getSensorDetail(
				mMainActivity, mCurrentSensor);
		if (detail != null) {
			DealWithSensor.initIsOnLine(mMainActivity, detail);
			statusView.setText("["
					+ (detail.isOnline() ? "在线" : "不在线") + "]");
			updateView.setText(HomeUtil.getUpdateTime(
					mMainActivity, detail));
		}
	}

	/**
	 * 注册监听器
	 * 
	 * @author 曾凡
	 * @time 2014年7月9日 下午4:51:11
	 */
	private void initReceiver() {
		/* 注册广播接收器，用来接受返回的明细和历史信息，一定要最后去监听 */
		mReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		mMainActivity.registerReceiver(mReceiver, filter);
	}

	/**
	 * 获取广播数据 更新页面内容
	 */
	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getBundleExtra("action");
			String name = bundle.getString("name");

			/* 如果设备更新了则刷新页面 */
			if (ServerConstant.SH01_02_02_01.equals(name)) {
				/* 0是查询列表的结果 */
				if (bundle.getInt("type") == 0) {

					if (bundle.getBoolean("result")) {
						SensorDetail ctrlDetail = HomeUtil
								.getSensorDetail(mMainActivity,
										mCurrentSensor.getCtrl()
												.getCtrlId());
						int type = SmartHomeServiceUtil
								.getSensorTypeWithId(mCurrentSensor
										.getCtrl().getCtrlId());
						switch (type) {
						case SensorConstant.SENSOR_TYPE_AIR: {
							linkSensorVal
									.setText(getSensorValue(
											ctrlDetail,
											mCurrentSensor
													.getCtrl()
													.getLinkSensor()));
						}
							break;
						case SensorConstant.SENSOR_TYPE_GAS: {
							linkSensorVal
									.setText(getSensorValue(
											ctrlDetail,
											mCurrentSensor
													.getCtrl()
													.getLinkSensor()));
						}
							break;

						}
						initData();
					}

				}
			}

		}
	}

	private void endReceiver() {
		// if (mDetailHandler != null) {
		// mDetailHandler.removeCallbacks(updateDetailThread);
		// }
		if (mReceiver != null) {
			try {
				mMainActivity.unregisterReceiver(mReceiver);
			} catch (Exception e) {
				Ln.e(e, "注销HomeFragment的监听器异常");
			}
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		endReceiver();
	}

	@Override
	public void onResume() {
		super.onResume();
		Ln.d("onResume");
		initReceiver();
		// startHandler();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		endReceiver();
	}

	// private void startHandler() {
	// if (mDetailHandler != null) {
	// mDetailHandler.removeCallbacks(updateDetailThread);
	// mDetailHandler.post(updateDetailThread);
	// }
	//
	// }

	// /** 用于执行实时数据更新 */
	// private Handler mDetailHandler = new Handler();
	// private Runnable updateDetailThread = new Runnable() {
	// @Override
	// public void run() {
	// /* 登陆这个页面后从服务器获取最新的实时数据 */
	// MainAcUtil.send2Service(mMainActivity,
	// ServerConstant.SH01_02_02_01, 0);
	// mDetailHandler.postDelayed(updateDetailThread,
	// 15 * 1000);
	//
	// }
	// };

	private String getSensorValue(SensorDetail detail,
			String name) {
		try {
			SensorAirDetail airDetail = detail.getAir();
			SensorGasDetail gasDetail = detail.getGas();
			if (SensorConstant.MEDIA_TYPE_CO.equals(name)) {
				return gasDetail.getCo();
			}
			if (SensorConstant.MEDIA_TYPE_CO2.equals(name)) {
				return airDetail.getCo2();
			}
			if (SensorConstant.MEDIA_TYPE_CH4.equals(name)) {
				return gasDetail.getCh4();
			}
			if (SensorConstant.MEDIA_TYPE_PM25.equals(name)) {
				return airDetail.getPm25();
			}
			if (SensorConstant.MEDIA_TYPE_TEMPERATURE
					.equals(name)) {
				return airDetail.getTemperature();
			}
			if (SensorConstant.MEDIA_TYPE_HUMIDITY.equals(name)) {
				return airDetail.getHumidity();
			}

			if (SensorConstant.MEDIA_TYPE_VOC.equals(name)) {
				return airDetail.getVoc();
			}
		} catch (Exception e) {
			Ln.e(e, "根据Media获取设备值异常");
			e.printStackTrace();
		}
		return "--";
	}

	private void setListViewHeight(ListView lv) {
		ControlPagerSwitcherAdapter la = (ControlPagerSwitcherAdapter) lv
				.getAdapter();
		if (null == la) {
			return;
		}
		// calculate height of all items.
		int h = 0;
		final int cnt = la.getCount();
		for (int i = 0; i < cnt; i++) {
			View item = la.getView(i, null, lv);
			item.measure(0, 0);
			h += item.getMeasuredHeight();
		}
		// reset ListView height
		ViewGroup.LayoutParams lp = lv.getLayoutParams();
		lp.height = h + (lv.getDividerHeight() * (cnt - 1));
		lv.setLayoutParams(lp);
	}

	private class ChangeClickListner implements
			OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			if (isChecked) {
				CtrlUtils.turnOnDevice(mMainActivity,
						mCurrentSensor.getCtrl());
			} else {
				CtrlUtils.turnOffDevice(mMainActivity,
						mCurrentSensor.getCtrl());
			}

		}

	}
}
