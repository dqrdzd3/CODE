package com.hw.smarthome.ui.sensor;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.util.Ln;
import com.hw.util.WindowTools;

/**
 * @author 曾凡
 * @time 2014年11月21日 下午7:20:48
 */
public class SensorHardwareActivity extends Activity {
	protected View parentView;
	protected Activity mContext;
	private ImageView uiSensorHardWareShowImage = null;
	private TextView uiSensorHardWareHint = null;

	private ImageView uiSensorHardWarePowerImage = null;
	private TextView uiSensorHardWarePowerHint = null;

	private BootstrapButton uiSensorHardwareReturn = null;
	private BootstrapButton uiSensorHardwareNext = null;

	private String sensorId = null; // 上一个页面注册的设备

	private static Drawable gasPowerImage = null;
	private static Drawable airPowerImage = null;

	private static Drawable gasImage = null;
	private static Drawable airImage = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initData();
		setContentView(R.layout.ui_sensor_hardware_ready);
		parentView = findViewById(R.id.uiSensorHardwareLayout);
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
					gasImage = getResources()
							.getDrawable(
									R.drawable.ui_sensor_hardware_show_gas);
					gasPowerImage = getResources()
							.getDrawable(
									R.drawable.ui_sensor_hardware_power_gas);

					airImage = getResources()
							.getDrawable(
									R.drawable.ui_sensor_hardware_show_air);
					airPowerImage = getResources()
							.getDrawable(
									R.drawable.ui_sensor_hardware_power_air);

				}

			}
		}
	}

	private void initView() {
		WindowTools.initSystemBar(getWindow());
		
		uiSensorHardWarePowerImage = (ImageView) parentView
				.findViewById(R.id.uiSensorHardWarePowerImage);
		uiSensorHardWarePowerHint = (TextView) parentView
				.findViewById(R.id.uiSensorHardWarePowerHint);

		uiSensorHardWareShowImage = (ImageView) parentView
				.findViewById(R.id.uiSensorHardWareShowImage);
		uiSensorHardWareHint = (TextView) parentView
				.findViewById(R.id.uiSensorHardWareHint);

		uiSensorHardwareReturn = (BootstrapButton) parentView
				.findViewById(R.id.uiSensorHardwareReturn);
		uiSensorHardwareReturn
				.setOnClickListener(new HardWareButtonListener());

		uiSensorHardwareNext = (BootstrapButton) parentView
				.findViewById(R.id.uiSensorHardwareNext);
		uiSensorHardwareNext
				.setOnClickListener(new HardWareButtonListener());

		if (sensorId != null && !"".equals(sensorId)) {
			int type = SmartHomeServiceUtil
					.getSensorTypeWithId(sensorId);
			Spanned content = null;
			Spanned powerContent = null;
			if (type == SensorConstant.SENSOR_TYPE_GAS) {
				uiSensorHardWareShowImage
						.setImageDrawable(gasImage);
				uiSensorHardWarePowerImage
						.setImageDrawable(gasPowerImage);
				content = Html
						.fromHtml(UIConstant.SENSOR_HARDWARE_GAS_HINT);

				powerContent = Html
						.fromHtml(UIConstant.SENSOR_HARDWARE_POWER_GAS_HINT);
			} else if (type == SensorConstant.SENSOR_TYPE_AIR) {
				uiSensorHardWareShowImage
						.setImageDrawable(airImage);
				uiSensorHardWarePowerImage
						.setImageDrawable(airPowerImage);
				content = Html
						.fromHtml(UIConstant.SENSOR_HARDWARE_AIR_HINT);
				powerContent = Html
						.fromHtml(UIConstant.SENSOR_HARDWARE_POWER_AIR_HINT);
			}
			uiSensorHardWareHint.setText(content);
			uiSensorHardWarePowerHint.setText(powerContent);
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	private class HardWareButtonListener implements
			OnClickListener {
		@Override
		public void onClick(View v) {
			if (v.getId() == uiSensorHardwareReturn.getId()) {
				jumpActivity(SensorRegActivity.class);
			} else if (v.getId() == uiSensorHardwareNext.getId()) {
				jumpActivity(SensorWifiActivity.class);
			}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			jumpActivity(SensorRegActivity.class);
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 返回上一个页面
	 * 
	 * @author 曾凡
	 * @time 2014年11月21日 下午9:04:56
	 */
	private void jumpActivity(Class clazz) {
		Intent intent = new Intent();
		intent.setClass(SensorHardwareActivity.this, clazz);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		Bundle b = new Bundle();
		b.putString("sensorId", sensorId);
		// 此处使用putExtras，接受方就响应的使用getExtra
		intent.putExtras(b);
		startActivity(intent);
		finish();
	}

	protected void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Ln.d("onDestroy");
	}
}
