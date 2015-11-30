package com.hw.smarthome.ui.home.scene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.gson.Gson;
import com.hw.smarthome.R;
import com.hw.smarthome.po.ScenePO;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithScene;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.scene.constant.SceneConstant;
import com.hw.smarthome.ui.home.util.SceneUtil;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.UIUtil;
import com.zf.view.TitleBarView;

/**
 * @author 曾凡
 * @time 2015年5月13日 下午3:13:52
 */
public class SceneUpdateActivity extends Activity {

	private Spinner sceneTypeSpinner;
	private boolean isSpinnerFirstLoad = true;
	private EditText sensorNameView;
	private BootstrapButton deviceNameUpdateBtn;
	private Context mContext;
	private String sensorId = "";
	public PopProgress popProgress = null;
	private String[] sceneIdStrings = null;
	private String[] sceneTypeStrings = null;
	private String[] sceneNameStrings = null;
	private String newSensorName = "";
	private TitleBarView mTitleBarView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_home_scene_update_activity);
		mContext = this;
		if (getIntent().getExtras() != null) {
			sensorId = getIntent().getExtras().getString(
					"sensor");
		}
		initView();
		initSpinner();
	}

	private void initView() {
		sensorNameView = (EditText) findViewById(R.id.sensorName);
		deviceNameUpdateBtn = (BootstrapButton) findViewById(R.id.deviceNameUpdateBtn);
		deviceNameUpdateBtn
				.setOnClickListener(new SceneUpdateOnClick());
		sceneTypeSpinner = (Spinner) findViewById(R.id.sceneType);

		if (sensorId != null) {
			SensorDetail sensor = SensorUtil.getSensorEquip(
					mContext, sensorId);
			sensorNameView.setText(sensor.getName());
		}
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		initTitleView();
		initSceneArray();

	}

	private class SceneUpdateOnClick implements OnClickListener {
		@Override
		public void onClick(final View v) {
			if (v == deviceNameUpdateBtn) {
				if (NetUtil.getNetworkType(mContext) != 1) {
					UIUtil.ToastMessage(mContext,
							R.string.open_wifi_hint);
				} else {
					popProgress = PopProgress.getInstance(
							mContext, v);
					popProgress
							.setText(getString((R.string.sensor_changing)));
					popProgress.showProgress();
					newSensorName = sensorNameView.getText()
							.toString();
					Intent intent = new Intent();
					Bundle bundle = new Bundle();
					bundle.putString("name",
							ServerConstant.SH01_01_01_07);
					bundle.putString("sensorId", sensorId);
					// bundle.putString("CTRL", "dd"); // 随便不为空就行
					bundle.putInt("type", 0);
					bundle.putString("DRIVERNAME", newSensorName);

					intent.putExtra("action", bundle);
					intent.setAction(MainActivity.class
							.getName());
					mContext.sendBroadcast(intent);

				}
			}
		}
	}

	private void initTitleView() {
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,
				View.GONE, View.GONE);
		mTitleBarView.setTitleText(R.string.scene_change);
		mTitleBarView.setBtnLeft(
				R.drawable.boss_unipay_icon_back, R.string.back);
		mTitleBarView
				.setBtnLeftOnclickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
	}

	/**
	 * 初始化场景数据数组
	 * 
	 * @author 曾凡
	 * @time 2015年5月14日 下午5:56:20
	 */
	private void initSceneArray() {
		List<ScenePO> scenes = DealWithScene.getScenes(mContext);
		ScenePO unSign = new ScenePO();
		unSign.setMa001("");
		unSign.setMa004(getString(R.string.scene_unsign));
		scenes.add(unSign);
		boolean isSceneOk = scenes != null && scenes.size() > 0;
		ScenePO lastPO = getLastScene(sensorId);
		if (lastPO == null) {
			lastPO = unSign;
		}
		if (isSceneOk) {
			ScenePO scene = null;
			for (int i = 0; i < scenes.size(); i++) {
				scene = scenes.get(i);
				if (scene.getMa001().equals(lastPO.getMa001())) {
					scenes.remove(i);
					scenes.add(0, scene);
					break;
				}
			}
		}
		if (isSceneOk) {
			sceneIdStrings = new String[scenes.size()];
			sceneTypeStrings = new String[scenes.size()];
			sceneNameStrings = new String[scenes.size()];
		}
		ScenePO tempScenePO = null;
		for (int i = 0; i < scenes.size(); i++) {
			tempScenePO = scenes.get(i);
			sceneIdStrings[i] = tempScenePO.getMa001();
			sceneTypeStrings[i] = tempScenePO.getMa008();
			sceneNameStrings[i] = tempScenePO.getMa004();
		}
	}

	private void initSpinner() {
		final ArrayList<String> nameList = new ArrayList<String>();
		final ArrayList<String> valueList = new ArrayList<String>();
		final ArrayList<Integer> imageList = new ArrayList<Integer>();

		initSpinerData(imageList, valueList, nameList);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item,
				nameList) {

			@Override
			public View getDropDownView(int position,
					View convertView, ViewGroup parent) {
				if (convertView == null) {
					convertView = getLayoutInflater().inflate(
							R.layout.ui_home_scene_spinner_item,
							parent, false);
				}
				TextView label = (TextView) convertView
						.findViewById(R.id.label);
				label.setText(getItem(position));
				ImageView icon = (ImageView) convertView
						.findViewById(R.id.icon);
				icon.setImageResource(imageList.get(position));

				return convertView;
			}
		};
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sceneTypeSpinner
				.setOnItemSelectedListener(new SpinnerListener());
		sceneTypeSpinner
				.setOnTouchListener(new SpinnerOnTouchListener());
		sceneTypeSpinner.setAdapter(adapter);
		// isSpinnerFirstLoad = false;
	}

	/**
	 * Spinner的点击事件
	 * 
	 * @author 曾凡
	 * @time 2015年5月14日 下午6:03:22
	 */
	private class SpinnerListener implements
			OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> parent,
				View view, int position, long id) {
			try {
				/* 获取之前关联的设备，如果设备没有关联过场景则不进行上一个场景的修改 */
				ScenePO lastPO = getLastScene(sensorId);
				/* 获取当前场景 */
				ScenePO currentPO = findSceneById(sceneIdStrings[sceneTypeSpinner
						.getSelectedItemPosition()]);
				/* 如果场景没有发生改变则跳出一下的功能逻辑 */
				if (lastPO != null
						&& currentPO != null
						&& lastPO.getMa001().equals(
								currentPO.getMa001())) {
					return;
				}
				Map<String, String> paraMap = new HashMap<String, String>();
				if (!isSpinnerFirstLoad) {
					if (lastPO != null) {
						String deleteStr = new Gson()
								.toJson(getDeleteScene(sensorId));
						paraMap.put("PO", deleteStr);
						/* 提交修改 */
						MainAcUtil.send2Service(mContext,
								ServerConstant.SH01_06_04_02, 0,
								paraMap);
					}

					/* 更新当前场景 */
					ScenePO update = getAddSecene(sensorId,
							currentPO);
					/* 有可能选择的是未分配 */
					if (update != null) {
						paraMap = new HashMap<String, String>();
						paraMap.put("PO",
								new Gson().toJson(update));
						/* 提交修改 */
						MainAcUtil.send2Service(mContext,
								ServerConstant.SH01_06_04_02, 1,
								paraMap);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				isSpinnerFirstLoad = true;
			}
		}

		public void onNothingSelected(AdapterView<?> parent) {

		}

	}

	private class SpinnerOnTouchListener implements
			OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			isSpinnerFirstLoad = false;
			return false;
		}
	}

	private void initSpinerData(ArrayList<Integer> images,
			ArrayList<String> values, ArrayList<String> nameList) {
		for (int i = 0; i < sceneNameStrings.length; i++) {
			nameList.add(sceneNameStrings[i]);
			values.add(sceneTypeStrings[i]);
			if (SceneConstant.SCENE_TYPE_CUSTOM
					.equals(sceneTypeStrings[i])) {
				images.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_custom));
			} else if (SceneConstant.SCENE_TYPE_LIVINGROOM
					.equals(sceneTypeStrings[i])) {
				images.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_livingroom));
			} else if (SceneConstant.SCENE_TYPE_BEDROOM
					.equals(sceneTypeStrings[i])) {
				images.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_bedroom));
			} else if (SceneConstant.SCENE_TYPE_KITCHEN
					.equals(sceneTypeStrings[i])) {
				images.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_kitchen));
			} else {
				images.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_unsign));
			}

		}
	}

	private ScenePO findSceneById(String id) {
		List<ScenePO> scenes = DealWithScene.getScenes(mContext);
		if (scenes != null) {
			for (ScenePO po : scenes) {
				if (id.equals(po.getMa001())) {
					return po;
				}
			}
		}
		return null;
	}

	private ScenePO getLastScene(String deviceId) {
		/* 获取当前设备绑定的场景 */
		List<ScenePO> scenes = DealWithScene.getScenes(mContext);
		ScenePO currentPO = SceneUtil.findSeceneBySensorId(
				scenes, deviceId);
		return currentPO;
	}

	private ScenePO getDeleteScene(String deviceId) {
		ScenePO currentPO = getLastScene(deviceId);
		SensorDetail sensor = SensorUtil.getSensorEquip(
				mContext, deviceId);
		SceneUtil.deleteSceneDevice(currentPO, sensor);
		return currentPO;
	}

	private ScenePO getAddSecene(String deviceId,
			ScenePO updatePo) {
		SensorDetail sensor = SensorUtil.getSensorEquip(
				mContext, deviceId);
		SceneUtil.addSeceneDevice(updatePo, sensor);
		return updatePo;
	}

	/**
	 * 获取广播数据 更新页面内容
	 */
	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String jsonData = "";
			try {
				Bundle bundle = intent.getBundleExtra("action");
				String actionName = bundle.getString("name");

				String message = bundle.getString("message");
				Boolean res = bundle.getBoolean("result");

				int type = bundle.getInt("type");

				/* [SH01_06_04_01] 添加场景 */
				if (ServerConstant.SH01_06_04_02
						.equals(actionName)) {

					if (type == 0) {
						return;
					}
					if (!res) {
						popProgress = PopProgress.getInstance(
								mContext, sceneTypeSpinner);
						popProgress
								.showResult(
										false,
										mContext.getString(R.string.scene_update_res_fail));
						// UIUtil.ToastMessage(
						// mContext,
						// mContext.getString(R.string.scene_update_res_fail)
						// + ":" + message);
					} else {
						popProgress = PopProgress.getInstance(
								mContext, sceneTypeSpinner);

						popProgress
								.showResult(
										true,
										mContext.getString(R.string.scene_update_res_success));
						// UIUtil.ToastMessage(
						// mContext,
						// mContext.getString(R.string.scene_update_res_success));
					}
				}
				/* [SH01_01_01_07] 修改场景别名 */
				if (ServerConstant.SH01_01_01_07
						.equals(actionName)) {
					if (res) {
						popProgress
								.showResult(
										true,
										mContext.getResources()
												.getString(
														R.string.sensor_change_sccuess));
						sensorNameView.setText(newSensorName);
					} else {
						popProgress.showResult(false, message);
					}

				}

			} catch (Exception e) {
				Ln.e(e, "解析报警数据异常:" + jsonData);
			}
		}
	}

	/**
	 * 注册监听器
	 * 
	 * @author 曾凡
	 * @time 2014年7月9日 下午4:51:11
	 */
	protected MyReceiver mReceiver;

	protected void initReceiver() {
		/* 注册广播接收器，用来接受返回的明细和历史信息，一定要最后去监听 */
		mReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		mContext.registerReceiver(mReceiver, filter);
	}

	protected void startReceiver() {
		initReceiver();

	}

	protected void endReceiver() {

		if (mReceiver != null) {
			try {
				mContext.unregisterReceiver(mReceiver);
			} catch (Exception e) {
				Ln.e(e, "注销HomeFragment的监听器异常");
			}
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		Ln.d("onResume");
		startReceiver();

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public void onPause() {
		super.onPause();
		Ln.d("onPause");
		endReceiver();

	}

}