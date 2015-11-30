package com.hw.smarthome.ui.sensor;

import java.util.List;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.login.LoginActivity;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.scaner.ScanActivity;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.UIUtil;
import com.hw.util.WindowTools;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

/**
 * @author 曾凡
 * @time 2014年11月21日 下午7:20:33
 */
public class SensorRegActivity extends Activity {

	protected Activity mContext;
	protected View parentView;
	private BootstrapButton uiSensorScan = null;
	private BootstrapButton uiSensorSubmit = null;
	private BootstrapButton uiSensorRegReturn = null;
	private BootstrapButton uiSensorRegNext = null;
	// private TextView uiSensorNextHint = null;
	@NotEmpty(messageId = R.string.sensor_2dbar_not_null, order = 1)
	@RegExp(value = "^(([a-fA-F0-9]{8})|([a-fA-F0-9]{12}))$", messageId = R.string.sensor_2dbar_form)
	private EditText uiSensor2D = null;
	public PopProgress popProgress = null;

	private MyReceiver mReceiver;
	/** 传值给下一个页面 */
	private String sensorId = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.ui_sensor_reg);
		parentView = findViewById(R.id.uiSensorRegLayout);
		mContext = this;
		initView();
		Intent intent = getIntent();
		if (intent.getExtras() != null) {
			sensorId = intent.getExtras().getString("sensorId");
			if (sensorId != null) {
				uiSensor2D.setText(sensorId);
			}
		}
	}

	private void initView() {
		WindowTools.initSystemBar(getWindow());

		uiSensorScan = (BootstrapButton) findViewById(R.id.uiSensorScan);
		uiSensorScan.setOnClickListener(new ButtonOnclick());
		uiSensorSubmit = (BootstrapButton) findViewById(R.id.uiSensorSubmit);
		uiSensorSubmit.setOnClickListener(new ButtonOnclick());
		uiSensor2D = (EditText) findViewById(R.id.uiSensor2D);

		uiSensorRegReturn = (BootstrapButton) findViewById(R.id.uiSensorRegReturn);
		uiSensorRegReturn
				.setOnClickListener(new ButtonOnclick());
		uiSensorRegNext = (BootstrapButton) findViewById(R.id.uiSensorRegNext);
		uiSensorRegNext.setOnClickListener(new ButtonOnclick());

		// uiSensorNextHint = (TextView) findViewById(R.id.uiSensorNextHint);
	}

	@Override
	public void onResume() {
		super.onResume();
		Ln.d("onResume");
		initReceiver();
		if (mUpdateListHandler != null) {
			mUpdateListHandler
					.removeCallbacks(mUpdateListThread);
			mUpdateListHandler.post(mUpdateListThread);
		}
	}

	protected void initReceiver() {
		// 注册广播接收器
		mReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		mContext.registerReceiver(mReceiver, filter);
	}

	/**
	 * 按钮的点击事件
	 * 
	 * @author 曾凡
	 * @time 2014年7月3日 上午11:14:03
	 */
	private class ButtonOnclick implements OnClickListener {
		@Override
		public void onClick(View v) {
			if (v.getId() == uiSensorScan.getId()) {
				Intent intent = new Intent(mContext,
						ScanActivity.class);
				startActivity(intent);
			} else if (v.getId() == uiSensorSubmit.getId()) {
				if (validate()) {//
					Ln.i("校验设备信息成功");
					Editable twoD = uiSensor2D.getText();
					String str = twoD == null ? "" : twoD
							.toString();
					sensorId = str;
					if (NetUtil.getNetworkType(mContext) != 1) {
						UIUtil.ToastMessage(mContext, "请链接WIFI");
					} else {

						InputMethodManager imm = (InputMethodManager) mContext
								.getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(
								v.getWindowToken(), 0);
						popProgress = PopProgress.getInstance(
								mContext, parentView);
						popProgress.setText("配置设备中");
						popProgress.showProgress();
						/* 发送一个设备编号，用户绑定设备与用户 */
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("name",
								ServerConstant.SH01_01_01_02);

						bundle.putString("sensorId", sensorId);
						bundle.putInt("type", 0);
						intent.putExtra("action", bundle);
						intent.setAction(SensorRegActivity.class
								.getName());
						mContext.sendBroadcast(intent);
					}

				}
			} else if (v.getId() == uiSensorRegReturn.getId()) {
				returnActivity();
			} else if (v.getId() == uiSensorRegNext.getId()) {
				Intent intent = new Intent();
				intent.setClass(SensorRegActivity.this,
						SensorHardwareActivity.class);
				Bundle b = new Bundle();
				b.putString("sensorId", uiSensor2D.getText()
						+ "");
				// 此处使用putExtras，接受方就响应的使用getExtra
				intent.putExtras(b);
				startActivity(intent);
				finish();
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
		List<SensorDetail> detailList = SensorUtil
				.getSensorDetails(mContext);
		if (detailList == null || detailList.size() == 0) {
			HomeUtil.quit(mContext);
			Intent startMain = new Intent(Intent.ACTION_MAIN);
			startMain.setClass(mContext, LoginActivity.class);
			startActivity(startMain);
		} else {
			Intent intent = new Intent();
			intent.setClass(SensorRegActivity.this,
					MainActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
		}
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
		uiSensor2D.requestFocus();
		final boolean isValid = FormValidator.validate(this,
				new SimpleErrorPopupCallback(mContext));
		return isValid;
	}

	public EditText getUiSensor2D() {
		return uiSensor2D;
	}

	public void setUiSensor2D(EditText uiSensor2D) {
		this.uiSensor2D = uiSensor2D;
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
				boolean result = bundle.getBoolean("result");
				String message = bundle.getString("message");
				int type = bundle.getInt("type");
				/* [SH01_01_01_02] 发现设备 上传传感器 */
				if (ServerConstant.SH01_01_01_02
						.equals(actionName)) {
					try {
						List<SensorDetail> detailList = SensorUtil
								.getSensorDetails(mContext);
						boolean sensorRes = false;
						if (detailList != null) {
							for (SensorDetail detail : detailList) {
								if (sensorId
										.equalsIgnoreCase(detail
												.getSensorId())) {
									sensorRes = true;
								}
							}
						}

						popProgress.hiddenProgerss();
						if (sensorRes) {
							popProgress
									.showResult(
											true,
											UIConstant.SENSOR_REG_SUCESS);
							// uiSensorNextHint
							// .setVisibility(View.VISIBLE);
							uiSensorRegNext
									.setVisibility(View.VISIBLE);
						} else {
							popProgress.showResult(false,
									message);
						}
					} catch (Exception e) {
						Ln.e(e, "注册设备&配网异常！");
					} finally {
						// stopPacketData();
					}

				}/* [SH01_01_01_03] 查看已发现设备 */
				else if (ServerConstant.SH01_01_01_03
						.equals(actionName)) {
					/* 0是查询列表的结果 */
					if (type == 1) {
						getUiSensor2D().setText(
								bundle.getString("2DBar") + "");
					}
				}
			} catch (Exception e) {
				Ln.e(e, "传感器配置异常");
			}
		}
	}

	/** 更新列表 */
	private Handler mUpdateListHandler = new Handler();
	private Runnable mUpdateListThread = new Runnable() {
		@Override
		public void run() {
			/* 登陆这个页面后从服务器获取最新的实时数据 */
			MainAcUtil.send2Service(mContext,
					ServerConstant.SH01_01_01_03, 0);

			mUpdateListHandler.postDelayed(mUpdateListThread,
					5 * 1000);

		}
	};

	protected void onStop() {
		super.onStop();
		Ln.d("onStop");
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

	public PopProgress getPopProgress() {
		return popProgress;
	}

	public void setPopProgress(PopProgress popProgress) {
		this.popProgress = popProgress;
	}

}
