package com.hw.smarthome.ui.sensor;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetInputDialog;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.easylink.android.FirstTimeConfig2;
import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.sensor.adpter.SensorListAdapter;
import com.hw.smarthome.ui.sensor.po.SensorListView;
import com.hw.smarthome.ui.sensor.scaner.ScanActivity;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.UIUtil;
import com.hw.util.WifiUtils;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.annotations.RegExp;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

/**
 * 传感器，wifi入网和校验（不能放到超类里），代码超长了
 * 
 * @author 曾凡
 * @time 2014年6月9日 上午10:47:45
 */
public class SensorOneKeyFragment extends Fragment {
	protected Context mContext;
//	protected ResideMenu resideMenu;
	private ListView lv = null;
	private View sensorView = null;
	private BootstrapButton uiSensorScan = null;
	private BootstrapButton uiSensorSubmit = null;
	private BootstrapButton uiSensorWifiSubmit = null;
	private TextView uiSensorListTitle = null;
	@NotEmpty(messageId = R.string.sensor_2dbar_not_null, order = 1)
	@RegExp(value = "^(([a-fA-F0-9]{8})|([a-fA-F0-9]{12}))$", messageId = R.string.sensor_2dbar_form)
	private EditText uiSensor2D = null;
	@NotEmpty(messageId = R.string.sensor_wifi_not_null, order = 2)
	private TextView uiSensorSSID = null;
	private EditText uiSensorPw = null;
	protected SensorListAdapter sensorListAdapter = null;
	/** 配置WIFI模块入网 */
	protected FirstTimeConfig2 firstConfig = null;
	public PopProgress popProgress = null;

	private static SensorOneKeyFragment instance;
	private MyReceiver mReceiver;

	public static SensorOneKeyFragment getInstance() {
		if (instance == null) {
			instance = new SensorOneKeyFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
//		resideMenu = mContext.getResideMenu();
		/* 登陆这个页面后从服务器获取最新的列表 */
		MainAcUtil.send2Service(mContext,
				ServerConstant.SH01_01_01_03, 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		sensorView = inflater.inflate(R.layout.ui_sensor,
				container, false);
		List<SensorDetail> detailList = SensorUtil
				.getSensorDetails(mContext);
		sensorListAdapter = new SensorListAdapter(getActivity(),
				detailList, sensorView);
		lv = (ListView) sensorView
				.findViewById(R.id.uiSensorList);
		lv.setAdapter(sensorListAdapter);
//		resideMenu.addIgnoredView(lv);
		UIUtil.setListViewHeightBasedOnChildren(lv);
		lv.setOnItemClickListener(new ListClick());
		lv.setOnItemLongClickListener(new ListLongClick());
		uiSensorScan = (BootstrapButton) sensorView
				.findViewById(R.id.uiSensorScan);
		uiSensorScan.setOnClickListener(new ButtonOnclick());
		uiSensorSubmit = (BootstrapButton) sensorView
				.findViewById(R.id.uiSensorSubmit);
		uiSensorSubmit.setOnClickListener(new ButtonOnclick());
		uiSensorWifiSubmit = (BootstrapButton) sensorView
				.findViewById(R.id.uiSensorWifiSubmit);
		uiSensorWifiSubmit
				.setOnClickListener(new ButtonOnclick());
		uiSensor2D = (EditText) sensorView
				.findViewById(R.id.uiSensor2D);

		uiSensorSSID = (TextView) sensorView
				.findViewById(R.id.uiSensorSSID);
		updateSSID();

		uiSensorPw = (EditText) sensorView
				.findViewById(R.id.uiSensorPw);

		uiSensorListTitle = (TextView) sensorView
				.findViewById(R.id.uiSensorListTitle);
		return sensorView;
	}

	

	private void updateSSID() {
		String ssid = WifiUtils.getSSID(mContext);
		if (ssid != null) {
			uiSensorSSID.setText(ssid);
		} else {
			UIUtil.ToastMessage(mContext, "配置设备需要打开手机/平板的wifi");
		}
	}

	protected void initReceiver() {
		try {
			// 注册广播接收器
			mReceiver = new MyReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(SmartHomeService.class.getName());
			mContext.registerReceiver(mReceiver, filter);
		} catch (Exception e) {
			Ln.e(e, "注销异常");
		}
	}

	private class ListClick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent,
				final View view, int position, long id) {
			BootstrapButton deleteButton = ((SensorListView) view
					.getTag()).getDeleteButton();
			if (deleteButton.isShown()) {
				deleteButton.setVisibility(View.INVISIBLE);
			} else {
				SweetInputDialog dialog =new SweetInputDialog(mContext)
				.setTitleText("修改设备名称")
				.setCancelText("取消")

				.setConfirmText("确认")
				.showCancelButton(true)
				.setCancelClickListener(
						new SweetInputDialog.OnSweetClickListener() {
							@Override
							public void onClick(SweetInputDialog sDialog) {
								sDialog.dismiss();
							}
						})
				;	
			
				dialog.show();
		
				dialog.editable(true);
				dialog.setInputEditText(((SensorListView) view.getTag()).getSensorName().getText().toString());
				final EditText cEditText = dialog.getInputEditText();
				dialog.setConfirmClickListener(new SweetInputDialog.OnSweetClickListener() {
					
					@Override
					public void onClick(SweetInputDialog sweetAlertDialog) {
						
						if (NetUtil.getNetworkType(mContext) != 1) {
							UIUtil.ToastMessage(mContext,
									"请联网");
						} else {
							sweetAlertDialog.dismiss();
							popProgress = PopProgress
									.getInstance(getActivity(),
											sensorView);
							popProgress.setText(mContext.getString(R.string.sensor_changing));
							popProgress.showProgress();
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putString("name",
									ServerConstant.SH01_01_01_07);
							bundle.putString(
									"sensorId",
									((SensorListView) view
											.getTag())
											.getSensorId()
											.getText()
											.toString()
											+ "");
							bundle.putInt("type", 0);
							bundle.putString("DRIVERNAME", cEditText
									.getText().toString());
							intent.putExtra("action", bundle);
							intent.setAction(MainActivity.class
									.getName());
							getActivity().sendBroadcast(intent);
		
							
						}
					}
				});
			}

		}
	}

	private class ListLongClick implements
			OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> arg0,
				View arg1, int arg2, long arg3) {
			BootstrapButton deleteButton = ((SensorListView) arg1
					.getTag()).getDeleteButton();
			deleteButton.setVisibility(View.VISIBLE);
			return true;
		}
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
				Intent intent = new Intent(getActivity(),
						ScanActivity.class);
				startActivity(intent);
			} else if (v.getId() == uiSensorSubmit.getId()) {
				if (validate()) {//
					// if(){
					// uiSensor2D.getText()
					// }
					Ln.i("校验设备信息成功");
					if (NetUtil.getNetworkType(mContext) != 1) {
						UIUtil.ToastMessage(mContext, "请链接WIFI");
					} else {

						InputMethodManager imm = (InputMethodManager) getActivity()
								.getSystemService(
										Context.INPUT_METHOD_SERVICE);
						imm.hideSoftInputFromWindow(
								v.getWindowToken(), 0);
						popProgress = PopProgress.getInstance(
								getActivity(), sensorView);
						popProgress.setText("配置设备中");
						popProgress.showProgress();
						// 替换为service
						// settingSensorWIFI();
						/* 发送一个设备编号，用户绑定设备与用户 */
						Intent intent = new Intent();
						Bundle bundle = new Bundle();
						bundle.putString("name",
								ServerConstant.SH01_01_01_02);
						bundle.putString("sensorId",
								uiSensor2D.getText() + "");
						bundle.putInt("type", 0);
						intent.putExtra("action", bundle);
						intent.setAction(MainActivity.class
								.getName());
						getActivity().sendBroadcast(intent);
					}

				}
			} else if (v.getId() == uiSensorWifiSubmit.getId()) {
				/* 发送一个设备编号，用户绑定设备与用户 */
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("name",
						ServerConstant.SH01_01_01_05);
				String ssidFieldTxt = uiSensorSSID.getText()
						+ "";
				String passwdText = uiSensorPw.getText()
						.toString().trim();
				bundle.putString("ssid", ssidFieldTxt);
				bundle.putString("passwd", passwdText);
				bundle.putInt("type", 0);
				intent.putExtra("action", bundle);
				intent.setAction(MainActivity.class.getName());
				getActivity().sendBroadcast(intent);
			}
		}
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
				int type = bundle.getInt("type");
				boolean result = bundle.getBoolean("result");
				String message = bundle.getString("message");
				List<SensorDetail> detailList = SensorUtil
						.getSensorDetails(mContext);
				/* [SH01_01_01_02] 发现设备 上传传感器 */
				if (ServerConstant.SH01_01_01_02
						.equals(actionName)) {
					try {
						popProgress.hiddenProgerss();
						if (result) {
							popProgress.showResult(true, mContext.getResources().getString(R.string.sensor_reg_sccuess));
							sensorListAdapter
									.setDetailList(detailList);
							sensorListAdapter
									.notifyDataSetChanged();
							UIUtil.setListViewHeightBasedOnChildren(lv);
						} else {
							popProgress.showResult(false,
									message);
						}
					} catch (Exception e) {
						Ln.e(e, "注册设备&配网异常！");
					} finally {
						// stopPacketData();
					}

				}
				/* [SH01_01_01_03] 查看已发现设备 */
				else if (ServerConstant.SH01_01_01_03
						.equals(actionName)) {
					/* 0是查询列表的结果 */
					if (type == 0) {
						if (result) {
							sensorListAdapter
									.setDetailList(detailList);
							sensorListAdapter
									.notifyDataSetChanged();
//							UIUtil.setListViewHeightBasedOnChildren(lv);
						}
						/* 1是扫描二维码的结果 */
					} else {
						getUiSensor2D().setText(
								bundle.getString("2DBar") + "");
					}
				}/* [SH01_01_01_04] 删除设备设备 */
				else if (ServerConstant.SH01_01_01_04
						.equals(actionName)) {
					popProgress.hiddenProgerss();
					if (result) {
						popProgress.showResult(true, mContext.getResources().getString(R.string.sensor_delete_sccuess));
						sensorListAdapter
								.setDetailList(detailList);
						sensorListAdapter.notifyDataSetChanged();
						UIUtil.setListViewHeightBasedOnChildren(lv);
					} else {
						popProgress.showResult(false, mContext.getResources().getString(R.string.sensor_delete_fail));
					}

				} else if (ServerConstant.SH01_01_01_07
						.equals(actionName)) {
					popProgress.hiddenProgerss();
					if (result) {
						popProgress.showResult(true, mContext.getResources().getString(R.string.sensor_change_sccuess));
						sensorListAdapter
								.setDetailList(detailList);
						sensorListAdapter.notifyDataSetChanged();
//						UIUtil.setListViewHeightBasedOnChildren(lv);
					} else {
						popProgress.showResult(false, mContext.getResources().getString(R.string.sensor_change_fail));
					}

				}
				int deviceCount = detailList!=null?detailList.size():0;
				uiSensorListTitle.setText("设备列表(设备数:"+deviceCount+"), 点击重命名，长按删除");
				/* 关闭弹出窗 */
				// new SafeAsyncTask<Boolean>() {
				// @Override
				// public Boolean call() throws Exception {
				// Thread.sleep(1500);
				// popProgress.getPopupWindow().dismiss();
				// return true;
				// }
				// }.execute();
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
			MainAcUtil.send2Service(mContext,
					ServerConstant.SH01_01_01_03, 0);

			mUpdateListHandler.postDelayed(mUpdateListThread,
					2000);
		}
	};
	/** 更新列表 */
	private Handler mUpdateOnlineHandler = new Handler();
	private Runnable mUpdateOnlineThread = new Runnable() {
		@Override
		public void run() {
			/* 登陆这个页面后从服务器获取最新的实时数据 */
			MainAcUtil.send2Service(mContext,
					ServerConstant.SH01_02_02_01, 0);
			/* 2秒更新一次,TODO 调整时间，XXX别忘了在正式使用时恢复到合理的时间 */
			mUpdateOnlineHandler.postDelayed(mUpdateOnlineThread,
					15 * 1000); // FIXME 展会需求，2秒一更新

		}
	};
	private void startThread(){
		if (mUpdateListHandler != null) {
			mUpdateListHandler
					.removeCallbacks(mUpdateListThread);
			mUpdateListHandler.post(mUpdateListThread);
		}
		if (mUpdateOnlineHandler != null) {
			mUpdateOnlineHandler
					.removeCallbacks(mUpdateOnlineThread);
			mUpdateOnlineHandler.post(mUpdateOnlineThread);
		}
	}
	
	private void endThread(){
		
		if (mUpdateListHandler != null) {
			mUpdateListHandler
					.removeCallbacks(mUpdateListThread);
		}
		if (mUpdateOnlineHandler != null) {
			mUpdateOnlineHandler
					.removeCallbacks(mUpdateOnlineThread);
		}
	}
	@Override
	public void onResume() {
		super.onResume();
		Ln.d("onResume");
		initReceiver();
		updateSSID();
		startThread();
	}
	@Override
	public void onPause() {
		super.onPause();
		Ln.d("onPause");
		endThread();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Ln.d("onDestroy");
		try {
			mContext.unregisterReceiver(mReceiver);
		} catch (Exception e) {
			Ln.e(e, "注销SensorFragment的监听器异常");
		}
	}

	static class CustomArrayAdapter<T> extends ArrayAdapter<T> {
		public CustomArrayAdapter(Context ctx, T[] objects) {
			super(ctx, android.R.layout.simple_spinner_item,
					objects);
		}
	}

	public PopProgress getPopProgress() {
		return popProgress;
	}

	public void setPopProgress(PopProgress popProgress) {
		this.popProgress = popProgress;
	}

}