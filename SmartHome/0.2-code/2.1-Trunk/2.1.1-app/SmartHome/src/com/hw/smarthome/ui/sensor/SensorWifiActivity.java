package com.hw.smarthome.ui.sensor;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.UIUtil;
import com.hw.util.WindowTools;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

/**
 * @author 曾凡
 * @time 2014年11月21日 下午7:20:48
 */
public class SensorWifiActivity extends Activity {
	protected View parentView;
	protected Activity mContext;
	@NotEmpty(messageId = R.string.sensor_wifi_not_null, order = 1)
	private TextView uiSensorSSID = null;
	private EditText uiSensorPw = null;
	private ImageView uiSensorWifiShowImage = null;
	private TextView uiSensorWifiHint = null;
	private BootstrapButton uiSensorSubmit = null;
	private BootstrapButton uiSensorWifiReturn = null;
	private BootstrapButton uiSensorWifiFinish = null;
	private String sensorId = null; // 上一个页面注册的设备
	private MyReceiver mReceiver;
	public PopProgress popProgress = null;

	private static Drawable gasImage = null;
	private static Drawable gasHintImage = null;
	private static Drawable airImage = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
		setContentView(R.layout.ui_sensor_wifi);
		parentView = findViewById(R.id.uiSensorWiFiLayout);
		mContext = this;
		initView();
	}

	private void initData() {
		Intent intent = getIntent();
		if (intent.getExtras() != null) {
			sensorId = intent.getExtras().getString("sensorId");
			if (sensorId != null) {
				sensorId = intent.getExtras().getString(
						"sensorId");
				if (gasImage == null && airImage == null) {
					gasImage = getResources().getDrawable(
							R.drawable.ui_sensor_wifi_show_gas);
					gasHintImage = getResources().getDrawable(
							R.drawable.ui_sensor_wifi_gas_hint);
					airImage = getResources().getDrawable(
							R.drawable.ui_sensor_wifi_show_air);
				}

			}
		}
	}

	private void initView() {
		WindowTools.initSystemBar(getWindow());

		uiSensorWifiShowImage = (ImageView) parentView
				.findViewById(R.id.uiSensorWifiShowImage);
		uiSensorSSID = (TextView) parentView
				.findViewById(R.id.uiSensorSSID);
		updateSSID();
		uiSensorPw = (EditText) parentView
				.findViewById(R.id.uiSensorPw);
		uiSensorWifiHint = (TextView) parentView
				.findViewById(R.id.uiSensorWifiHint);
		uiSensorSubmit = (BootstrapButton) parentView
				.findViewById(R.id.uiSensorSubmit);
		uiSensorSubmit
				.setOnClickListener(new WifiButtonListener());
		uiSensorWifiReturn = (BootstrapButton) parentView
				.findViewById(R.id.uiSensorWifiReturn);
		uiSensorWifiReturn
				.setOnClickListener(new WifiButtonListener());

		uiSensorWifiFinish = (BootstrapButton) parentView
				.findViewById(R.id.uiSensorWifiFinish);
		uiSensorWifiFinish
				.setOnClickListener(new WifiButtonListener());

		if (sensorId != null && !"".equals(sensorId)) {
			int type = SmartHomeServiceUtil
					.getSensorTypeWithId(sensorId);
			Spanned content = null;
			if (type == SensorConstant.SENSOR_TYPE_GAS) {
				uiSensorWifiShowImage.setImageDrawable(gasImage);
				content = Html
						.fromHtml(UIConstant.SENSOR_WIFI_GAS_HINT);

			} else if (type == SensorConstant.SENSOR_TYPE_AIR) {
				uiSensorWifiShowImage.setImageDrawable(airImage);
				content = Html
						.fromHtml(UIConstant.SENSOR_WIFI_AIR_HINT);
			}
			uiSensorWifiHint.setText(content);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		updateSSID();
		initReceiver();
		if (mUpdateListHandler != null) {
			mUpdateListHandler
					.removeCallbacks(mUpdateListThread);
			mUpdateListHandler.post(mUpdateListThread);
		}
	}

	private void updateSSID() {
		String ssid = getSSID();
		if (ssid != null) {
			uiSensorSSID.setText(ssid);
		} else {
			UIUtil.ToastMessage(this, "配置设备需要打开手机/平板的wifi");
		}
	}

	private String getSSID() {
		WifiManager wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		if (wifi.getWifiState() == WifiManager.WIFI_STATE_ENABLED) {
			String ssid = info.getSSID();
			if (ssid != null && ssid.contains("\"")) {
				ssid = ssid.replace("\"", "");
			}
			return ssid;
		}
		return null;
	}

	protected void initReceiver() {
		// 注册广播接收器
		mReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		mContext.registerReceiver(mReceiver, filter);
	}

	private class WifiButtonListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (v.getId() == uiSensorSubmit.getId()) {
				if (NetUtil.getNetworkType(mContext) != 1) {
					UIUtil.ToastMessage(mContext, "请链接WIFI");
				} else {
					if (validate()) {
						InputMethodManager imm = (InputMethodManager) mContext
								.getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(
								v.getWindowToken(), 0);
						popProgress = PopProgress.getInstance(
								mContext, parentView, true,
								"配网失败");
						popProgress.setText("设备配网中");
						popProgress.setOutsideTouchable(true);
						popProgress.showProgress();
						/* 发送一个设备编号，用户绑定设备与用户 */
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("name",
								ServerConstant.SH01_01_01_05);
						bundle.putString("ssid",
								uiSensorSSID.getText() + "");
						bundle.putString("passwd",
								uiSensorPw.getText() + "");
						intent.putExtra("action", bundle);
						intent.setAction(MainActivity.class
								.getName());
						sendBroadcast(intent);
					}
				}
			} else if (v.getId() == uiSensorWifiReturn.getId()) {
				returnActivity();
			} else if (v.getId() == uiSensorWifiFinish.getId()) {
				returnMain();
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			returnActivity();
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 返回上一个页面
	 * 
	 * @author 曾凡
	 * @time 2014年11月21日 下午9:04:56
	 */
	private void returnActivity() {
		Intent intent = new Intent();
		intent.setClass(SensorWifiActivity.this,
				SensorHardwareActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle b = new Bundle();
		b.putString("sensorId", sensorId);
		// 此处使用putExtras，接受方就响应的使用getExtra
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}

	/**
	 * 验证
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月21日 下午1:57:13
	 */
	private boolean validate() {
		uiSensorSSID.requestFocus();
		final boolean isValid = FormValidator.validate(this,
				new SimpleErrorPopupCallback(mContext));
		return isValid;
	}

	/**
	 * 获取广播数据 更新页面内容
	 */
	public class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {

			try {
				Bundle bundle = intent.getBundleExtra("action");
				String actionName = bundle.getString("name");
				int type = bundle.getInt("type");
				boolean result = bundle.getBoolean("result");
				String message = bundle.getString("message");
				if (ServerConstant.SH01_02_02_01
						.equals(actionName)) {
					/* 0是查询列表的结果 */
					if (type == 0) {
						chkLink(context);
					}
				}
			} catch (Exception e) {
				Ln.e(e, "传感器配置异常");
			}
		}
	}

	/**
	 * 检测设备是否连接成功
	 * 
	 * @author 曾凡
	 * @time 2014年11月24日 上午10:06:06
	 */
	private void chkLink(Context context) {
		/* [SH01_02_02_01] 查看设备实时数据 */
		List<SensorDetail> detailList = HomeUtil
				.getSensorDetails(context);
		/* 初始化在线信息 */
		DealWithSensor.initIsOnLine(context, detailList);
		for (SensorDetail realTime : detailList) {
			if (sensorId.equals(realTime.getSensorId())) {
				if (realTime.isOnline()) {
					if (popProgress != null) {
						updateSccuessHint();
					}
				}
			}
		}
	}

	/**
	 * 更新成功的状态
	 * 
	 * @author 曾凡
	 * @time 2014年11月26日 下午3:48:45
	 */
	private void updateSccuessHint() {
		popProgress.showResult(true,
				UIConstant.SENSOR_WIFI_SUCESS);
		uiSensorWifiFinish.setVisibility(View.VISIBLE);
		int type = SmartHomeServiceUtil
				.getSensorTypeWithId(sensorId);
		if (type == SensorConstant.SENSOR_TYPE_GAS) {
			uiSensorWifiShowImage.setImageDrawable(gasHintImage);
			uiSensorWifiHint
					.setText(Html
							.fromHtml(UIConstant.SENSOR_WIFI_GAS_SUCCESS_HINT));
		}
	}

	private void returnMain() {
		Intent mainIntent = new Intent();
		mainIntent.setClass(SensorWifiActivity.this,
				MainActivity.class);
		mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(mainIntent);

	}

	/** 更新列表 */
	private Handler mUpdateListHandler = new Handler();
	private Runnable mUpdateListThread = new Runnable() {
		@Override
		public void run() {
			/* 登陆这个页面后从服务器获取最新的实时数据 */
			MainAcUtil.send2Service(mContext,
					ServerConstant.SH01_02_02_01, 0);

			mUpdateListHandler.postDelayed(mUpdateListThread,
					5 * 1000);

		}
	};

	protected void onStop() {
		super.onStop();
		Ln.d("onStop");
		try {
			mContext.unregisterReceiver(mReceiver);
		} catch (Exception e) {
			Ln.e(e, "注销SensorFragment的监听器异常");
		}
		if (mUpdateListHandler != null) {
			mUpdateListHandler
					.removeCallbacks(mUpdateListThread);
		}
	};

	@Override
	public void onDestroy() {
		super.onDestroy();
		Ln.d("onDestroy");
		try {
			mContext.unregisterReceiver(mReceiver);
		} catch (Exception e) {
			Ln.e(e, "注销SensorFragment的监听器异常");
		}
		if (mUpdateListHandler != null) {
			mUpdateListHandler
					.removeCallbacks(mUpdateListThread);
		}
	}
}
