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
import android.text.InputFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.google.gson.Gson;
import com.hw.smarthome.R;
import com.hw.smarthome.po.ScenePO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithScene;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.home.scene.constant.SceneConstant;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;
import com.hw.util.UIUtil;
import com.zf.view.TitleBarView;

import eu.inmite.android.lib.validations.form.FormValidator;
import eu.inmite.android.lib.validations.form.annotations.NotEmpty;
import eu.inmite.android.lib.validations.form.callback.SimpleErrorPopupCallback;

public class SceneAddActivity extends Activity implements
		OnClickListener {
	private TitleBarView mTitleBarView;
	private Spinner sceneTypeSpinner;
	@NotEmpty(messageId = R.string.infor_null, order = 1)
	private EditText sceneNameEditText;
	private BootstrapButton sceneAddButton;
	private Activity mContext;
	private ArrayList<Integer> imageList;
	private ArrayList<String> valueList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ui_home_scene_add_activity);
		mContext = this;
		initView();
		initSpinner();
	}

	@Override
	public void onClick(View v) {
		boolean isValid = FormValidator.validate(mContext,
				new SimpleErrorPopupCallback(mContext, true));
		if (isValid) {
			List<ScenePO> scenes = DealWithScene
					.getScenes(mContext);
			int sceneOrder = -1;
			if (scenes == null) {
				sceneOrder = 0;
			} else {
				sceneOrder = scenes.size();
			}
			Map<String, String> paraMap = new HashMap<String, String>();
			ScenePO scenePO = new ScenePO();
			scenePO.setMa004(sceneNameEditText.getText()
					.toString());
			scenePO.setMa007("" + sceneOrder);
			scenePO.setMa008(valueList.get(sceneTypeSpinner
					.getSelectedItemPosition()));
			paraMap.put("PO", new Gson().toJson(scenePO));

			MainAcUtil.send2Service(this,
					ServerConstant.SH01_06_04_01, 0, paraMap);
		}
	}

	private void initView() {
		mContext = this;
		sceneNameEditText = (EditText) findViewById(R.id.sceneName);
		sceneNameEditText
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
						32) });
		sceneTypeSpinner = (Spinner) findViewById(R.id.sceneType);
		sceneAddButton = (BootstrapButton) findViewById(R.id.sceneAdd);
		sceneAddButton.setOnClickListener(this);
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		initTitleView();
	}

	private void initTitleView() {
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,
				View.GONE, View.GONE);
		mTitleBarView.setTitleText(R.string.scene_add);
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

	private void initSpinner() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(getString(R.string.scene_custom));
		list.add(getString(R.string.scene_livingroom));
		list.add(getString(R.string.scene_bedroom));
		list.add(getString(R.string.scene_kitchen));

		imageList = new ArrayList<Integer>();
		imageList
				.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_custom));
		imageList
				.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_livingroom));
		imageList
				.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_bedroom));
		imageList
				.add(Integer
						.valueOf(R.drawable.ui_home_scene_default_kitchen));

		valueList = new ArrayList<String>();
		valueList.add(SceneConstant.SCENE_TYPE_CUSTOM);
		valueList.add(SceneConstant.SCENE_TYPE_LIVINGROOM);
		valueList.add(SceneConstant.SCENE_TYPE_BEDROOM);
		valueList.add(SceneConstant.SCENE_TYPE_KITCHEN);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				this, android.R.layout.simple_spinner_item, list) {

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
		sceneTypeSpinner.setAdapter(adapter);
		sceneTypeSpinner
				.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
					public void onItemSelected(
							AdapterView<?> parent, View view,
							int position, long id) {

					}

					public void onNothingSelected(
							AdapterView<?> parent) {
					}
				});
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
				boolean res = bundle.getBoolean("result");

				/* [SH01_06_04_01] 添加场景 */
				if (ServerConstant.SH01_06_04_01
						.equals(actionName)) {
					if (!res) {
						String message = bundle
								.getString("message");
						UIUtil.ToastMessage(
								mContext,
								mContext.getString(R.string.scene_add_res_fail)
										+ ":" + message);
					} else {
						UIUtil.ToastMessage(
								mContext,
								mContext.getString(R.string.scene_add_res_success));
						finish();
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

	// private byte[] getByteFromBitmap(int resid) {
	// Bitmap bitmap = BitmapFactory.decodeResource(
	// getResources(), resid);
	// ByteArrayOutputStream baos = new ByteArrayOutputStream();
	// bitmap.compress(CompressFormat.PNG, 100, baos);
	// return baos.toByteArray();
	// }
}
