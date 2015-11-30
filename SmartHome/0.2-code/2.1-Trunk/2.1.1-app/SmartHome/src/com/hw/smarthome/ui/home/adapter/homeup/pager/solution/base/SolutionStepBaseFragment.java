package com.hw.smarthome.ui.home.adapter.homeup.pager.solution.base;

import java.io.IOException;
import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.po.S007PO;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SoluChkSumPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithSoluChkSum;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.SolutionUtils;
import com.hw.smarthome.ui.home.adapter.homeup.util.HomePagerUtil;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.solution.constant.SolutionConstants;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;
import com.zf.view.TitleBarTranView;

public abstract class SolutionStepBaseFragment extends Fragment {

	protected MainActivity mMainActivity;
	protected FragmentManager fManager;
	protected String sensorId;
	protected static SensorDetail mCurrentSensor;
	protected MyReceiver mReceiver;
	protected View parentView;
	protected TitleBarTranView mTitleBarView;
	public SoluChkSumPO mSolution = new SoluChkSumPO();

	protected View soluAllContent;
	protected View soluAllContent2;
	protected TextView soluContent1;
	protected TextView soluContent2;
	protected TextView soluContent3;
	protected TextView soluContent4;
	protected TextView soluContent5;

	protected TextView soluSumText;

	protected TextView soluPm25Value;
	protected TextView soluPm25Content;

	protected TextView soluVOCValue;
	protected TextView soluVOCContent;

	protected TextView soluCO2Value;
	protected TextView soluCO2Content;

	protected TextView soluContent;

	protected Button soluUpBtn;
	protected Button soluDownBtn;

	/**
	 * 注册监听器
	 * 
	 * @author 曾凡
	 * @time 2014年7月9日 下午4:51:11
	 */
	protected void initReceiver() {
		/* 注册广播接收器，用来接受返回的明细和历史信息，一定要最后去监听 */
		mReceiver = new MyReceiver();
		IntentFilter filter = new IntentFilter();
		filter.addAction(SmartHomeService.class.getName());
		mMainActivity.registerReceiver(mReceiver, filter);
	}

	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String jsonData = "";
			try {
				Bundle bundle = intent.getBundleExtra("action");
				String actionName = bundle.getString("name");
				boolean result = bundle.getBoolean("result");

				/* [SH01_02_03_02] 获取单条解决方案数据 */
				if (ServerConstant.SH01_02_03_02
						.equals(actionName)) {
					if (result) {
						loadSolution();
					}
				}
			} catch (Exception e) {
				Ln.e(e, "解析数据异常:" + jsonData);
			}
		}
	}

	private void endReceiver() {
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
		initData();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		endReceiver();
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		loadFragment(container, inflater);
		fManager = mMainActivity.getSupportFragmentManager();
		if (sensorId != null && !"".equals(sensorId)) {
			mCurrentSensor = HomeUtil.getSensorDetail(
					mMainActivity, sensorId);
		}
		initView();
		initTitleView();
		initData();
		return parentView;
	}

	protected abstract void loadFragment(ViewGroup container,
			LayoutInflater inflater);

	private void initView() {
		soluUpBtn = (Button) parentView
				.findViewById(R.id.soluUpBtn);
		soluDownBtn = (Button) parentView
				.findViewById(R.id.soluDownBtn);
		soluUpBtn.setOnClickListener(new BtnClickListener());
		soluDownBtn.setOnClickListener(new BtnClickListener());

		mTitleBarView = (TitleBarTranView) parentView
				.findViewById(R.id.title_bar);

		soluAllContent = parentView
				.findViewById(R.id.soluAllContent);
		soluAllContent2 = parentView
				.findViewById(R.id.soluAllContent2);
		soluSumText = (TextView) parentView
				.findViewById(R.id.soluSumText);

		soluPm25Value = (TextView) parentView
				.findViewById(R.id.soluPm25Value);
		soluPm25Content = (TextView) parentView
				.findViewById(R.id.soluPm25Content);

		soluVOCValue = (TextView) parentView
				.findViewById(R.id.soluVOCValue);
		soluVOCContent = (TextView) parentView
				.findViewById(R.id.soluVOCContent);

		soluCO2Value = (TextView) parentView
				.findViewById(R.id.soluCO2Value);
		soluCO2Content = (TextView) parentView
				.findViewById(R.id.soluCO2Content);

		soluContent = (TextView) parentView
				.findViewById(R.id.soluContent);
		soluContent1 = (TextView) parentView
				.findViewById(R.id.soluContent1);
		soluContent2 = (TextView) parentView
				.findViewById(R.id.soluContent2);
		soluContent3 = (TextView) parentView
				.findViewById(R.id.soluContent3);
		soluContent4 = (TextView) parentView
				.findViewById(R.id.soluContent4);
		soluContent5 = (TextView) parentView
				.findViewById(R.id.soluContent5);
		initSolutionViews();
	}

	private class BtnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.soluUpBtn:
				upBtnClick();
				break;
			case R.id.soluDownBtn:
				downBtnClick();
				break;
			}
		}
	}

	protected abstract void upBtnClick();

	protected abstract void downBtnClick();

	private int titleId;

	private void initTitleView() {
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,
				View.GONE, View.GONE);
		mTitleBarView.setBtnLeft(R.drawable.cam_back_arrow,
				R.string.back);
		titleId = getTitleId();
		mTitleBarView.setTitleText(titleId);
		mTitleBarView
				.setBtnLeftOnclickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						back();
					}
				});
	}

	protected abstract int getTitleId();

	private void back() {
		try {
			Runtime runtime = Runtime.getRuntime();
			runtime.exec("input keyevent "
					+ KeyEvent.KEYCODE_BACK);
		} catch (IOException e) {
			Ln.e(e, "返回失效");
		}
	}

	private void initData() {
		try {
			Map<Integer, S007PO> solutions = SolutionUtils
					.getSolutionMap(mCurrentSensor);
			S007PO co2 = solutions.get(UIConstant.SOLUTION_CO2);

			soluCO2Value.setText(mCurrentSensor.getAir()
					.getCo2());
			soluCO2Content.setText(co2.getMa005());

			S007PO pm25 = solutions
					.get(UIConstant.SOLUTION_PM25);

			soluPm25Value.setText(mCurrentSensor.getAir()
					.getPm25());
			soluPm25Content.setText(pm25.getMa005());

			S007PO voc = solutions.get(UIConstant.SOLUTION_VOC);

			soluVOCValue.setText(mCurrentSensor.getAir()
					.getVoc());
			soluVOCContent.setText(voc.getMa005());

			soluSumText.setText(SolutionUtils.getSolutionHint(
					mMainActivity, mCurrentSensor));
			setSolutionColor(mMainActivity, mCurrentSensor);
			setSolutionBtnText(mMainActivity, mCurrentSensor);
			initSolution();
		} catch (Exception e) {
			Ln.e(e, "初始化解决方案异常");
		}
	}

	/**
	 * 设置点击按钮标题的数据内容
	 * 
	 * @author 曾凡
	 * @param context
	 * @param detail
	 * @time 2015年8月20日 下午1:53:02
	 */
	private void setSolutionBtnText(Context context,
			SensorDetail detail) {

		int type = 0;
		int state = 0;
		int stringId = R.string.ui_solution_solu1;
		if (detail != null) {
			type = SmartHomeServiceUtil.getSensorType(detail);
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				state = SolutionUtils.getSolutionHintState(
						context, detail);
				switch (state) {
				case HomePagerUtil.LOW:
					stringId = R.string.ui_solution_solu1;
					break;
				case HomePagerUtil.NORMAL:
					stringId = R.string.ui_solution_solu;
					break;
				case HomePagerUtil.HIGH:
					stringId = R.string.ui_solution_solu;
					break;
				}
			}
		}
		soluUpBtn.setText(context.getResources().getString(
				stringId));

	}

	private void setSolutionColor(Context context,
			SensorDetail detail) {

		int type = 0;
		int state = 0;
		int colorId = R.color.white;
		if (detail != null) {
			type = SmartHomeServiceUtil.getSensorType(detail);
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				state = SolutionUtils.getSolutionHintState(
						context, detail);
				switch (state) {
				case HomePagerUtil.LOW:
					colorId = R.color.ui_solution_hint_1;
					break;
				case HomePagerUtil.NORMAL:
					colorId = R.color.ui_solution_hint_2;
					break;
				case HomePagerUtil.HIGH:
					colorId = R.color.ui_solution_hint_3;
					break;
				}
			}
		}
		soluSumText.setTextColor(context.getResources()
				.getColor(colorId));

	}

	private void initSolution() {
		mSolution.setFileType(loadPageType());
		mSolution
				.setScene(SolutionConstants.SCENE_TYPE_NEW_HOUSE
						+ "");
		mSolution
				.setPm25((Integer.valueOf(HomePagerUtil
						.getPm25State(soluPm25Value.getText()
								+ "") + 1) * 10)
						+ "");
		mSolution
				.setVoc((Integer.valueOf(HomePagerUtil
						.getVocState(soluVOCValue.getText() + "") + 1) * 10)
						+ "");
		mSolution
				.setCo2((Integer.valueOf(HomePagerUtil
						.getCo2State(soluCO2Value.getText() + "") + 1) * 10)
						+ "");
		loadSolution();
		if (mSolution.getCentent() == null
				|| "".equals(mSolution.getCentent())
				|| mSolution.getCentent().length() < 50) {
			/* [SH01_02_03_02]获取单条解决方案数据 */
			MainAcUtil.send2SuluService(mMainActivity,
					ServerConstant.SH01_02_03_02, mSolution);
		}
	}

	protected abstract String loadPageType();

	private void loadSolution() {
		SoluChkSumPO solution = DealWithSoluChkSum
				.getSolutionMap(mMainActivity).get(
						mSolution.toString());
		if (solution != null) {
			mSolution = solution;
			String content = mSolution.getCentent().replace(
					"\\n", "\n");
			content = content.replace("\\r", "\r");
			setValues(content);
		}
	}

	private void setValues(String content) {
		soluContent.setText(content);
		try {
			content = content.replace("此方案最终解释权归北京威果智能科技有限公司所有。", "");
			String row1 = "", row2 = "", row3 = "", row4 = "", row5 = "";
			row1 = content.substring(content.indexOf("1."),
					content.indexOf("\n2."));
			row2 = content.substring(content.indexOf("\n2."),
					content.indexOf("\n3."));
			if (content.contains("综上所述")) {
				row3 = content.substring(content.indexOf("3."),
						content.indexOf("综上所述"));
				if (content.contains("\n申明")) {
					row4 = content.substring(
							content.indexOf("综上所述"),
							content.indexOf("\n申明"));
				} else {
					row4 = content.substring(
							content.indexOf("综上所述"),
							content.length());
				}

				row5 = content.substring(
						content.indexOf("\n申明"),
						content.length());
			} else {
				if (content.contains("\n申明")) {
					row3 = content.substring(
							content.indexOf("\n3."),
							content.indexOf("\n申明"));
					row5 = content.substring(
							content.indexOf("\n申明"),
							content.length());
				} else {
					row3 = content.substring(
							content.indexOf("\n3."),
							content.length());
				}

			}

			if (row1 == null || "".equals(row1)) {
				soluContent1.setVisibility(View.GONE);
			} else {
				soluContent1.setVisibility(View.VISIBLE);
			}
			if (row2 == null || "".equals(row2)) {
				soluContent2.setVisibility(View.GONE);
			} else {
				soluContent2.setVisibility(View.VISIBLE);
			}
			if (row3 == null || "".equals(row3)) {
				soluContent3.setVisibility(View.GONE);
			} else {
				soluContent3.setVisibility(View.VISIBLE);
			}
			if (row4 == null || "".equals(row4)) {
				soluContent4.setVisibility(View.GONE);
			} else {
				soluContent4.setVisibility(View.VISIBLE);
			}
			if (row5 == null || "".equals(row5)) {
				soluContent5.setVisibility(View.GONE);
			} else {
				soluContent5.setVisibility(View.VISIBLE);
			}
			soluContent1.setText(trimRN(row1));
			soluContent2.setText(trimRN(row2));
			soluContent3.setText(trimRN(row3));
			soluContent4.setText(trimRN(row4));
			soluContent5.setText(trimRN(row5));
		} catch (Exception e) {
			Ln.e(e, "解析数据异常");
			soluAllContent.setVisibility(View.VISIBLE);
			soluAllContent2.setVisibility(View.GONE);
		}
	}

	private static String trimRN(String content) {
		if (content.endsWith("\n")) {
			content = content.substring(0,
					content.lastIndexOf("\n"));
			content = trimRN(content);
		} else if (content.endsWith("\r")) {
			content = content.substring(0,
					content.lastIndexOf("\r"));
			content = trimRN(content);
		}

		if (content.startsWith("\n")) {
			content = content.substring(
					content.indexOf("\n") + 1, content.length());
			content = trimRN(content);
		} else if (content.startsWith("\r")) {
			content = content.substring(
					content.indexOf("\r") + 1, content.length());
			content = trimRN(content);
		}
		return content.trim();
	}

	protected abstract void initSolutionViews();

	public String getSensorId() {
		return sensorId;
	}

	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

}
