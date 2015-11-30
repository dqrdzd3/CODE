//package com.hw.smarthome.ui.home.adapter.homeup.pager.solution;
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.RadioGroup;
//import android.widget.RadioGroup.OnCheckedChangeListener;
//
//import com.hw.smarthome.R;
//import com.hw.smarthome.po.SensorAirDetail;
//import com.hw.smarthome.po.SensorDetail;
//import com.hw.smarthome.server.constant.ServerConstant;
//import com.hw.smarthome.service.util.SmartHomeServiceUtil;
//import com.hw.smarthome.ui.home.HomeFragment;
//import com.hw.smarthome.ui.home.adapter.homeup.util.HomePagerUtil;
//import com.hw.smarthome.ui.home.solution.constant.SolutionConstants;
//import com.hw.smarthome.ui.home.util.HomeUtil;
//import com.hw.smarthome.ui.sensor.constant.SensorConstant;
//import com.hw.smarthome.ui.util.MainAcUtil;
//import com.zf.view.BottomBarView;
//import com.zf.view.TitleBarView;
//
///**
// * @author 曾凡
// * @time 2015年5月29日 下午4:43:30
// */
//public class SolutionStep3Fragment extends Fragment {
//	private static SolutionStep3Fragment instance;
//	public FragmentActivity mContext;
//	private View mParent;
//
//	private TitleBarView mTitleBarView;
//	private EditText solutionStep1Pm25;
//	private EditText solutionStep1CO2;
//	private EditText solutionStep1VOC;
//	private RadioGroup radioGroup;
//	private BottomBarView bottomBarView;
//
//	private FragmentManager fManager;
//	private String sensorId;
//	private SensorDetail mCurrentSensor;
//	private int sceneType = SolutionConstants.SCENE_TYPE_NEW_HOUSE;
//
//	public static SolutionStep3Fragment getInstance(
//			String sensorId) {
//		if (instance == null) {
//			instance = new SolutionStep3Fragment();
//		}
//		instance.setSensorId(sensorId);
//		return instance;
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater,
//			ViewGroup container, Bundle savedInstanceState) {
//		mParent = inflater.inflate(
//				R.layout.ui_solution_step_old_fragment, container,
//				false);
//		mContext = getActivity();
//		fManager = mContext.getSupportFragmentManager();
//		if (sensorId != null && !"".equals(sensorId)) {
//			mCurrentSensor = HomeUtil.getSensorDetail(mContext,
//					sensorId);
//		}
//		findView();
//		initTitleView();
//		init();
//		return mParent;
//	}
//
//	private void findView() {
//		mTitleBarView = (TitleBarView) mParent
//				.findViewById(R.id.title_bar);
//		solutionStep1Pm25 = (EditText) mParent
//				.findViewById(R.id.solutionStep1Pm25);
//		solutionStep1CO2 = (EditText) mParent
//				.findViewById(R.id.solutionStep1CO2);
//		solutionStep1VOC = (EditText) mParent
//				.findViewById(R.id.solutionStep1VOC);
//		radioGroup = (RadioGroup) mParent
//				.findViewById(R.id.radioGroup);
//		radioGroup.setOnCheckedChangeListener(new SceneOnChk());
//		bottomBarView = (BottomBarView) mParent
//				.findViewById(R.id.bottomBar);
//	}
//
//	private class SceneOnChk implements OnCheckedChangeListener {
//		@Override
//		public void onCheckedChanged(RadioGroup arg0, int arg1) {
//			// 获取变更后的选中项的ID
//			int radioButtonId = arg0.getCheckedRadioButtonId();
//			switch (radioButtonId) {
//			case R.id.solutionNewHome:
//				sceneType = SolutionConstants.SCENE_TYPE_NEW_HOUSE;
//				break;
//			case R.id.solutionNewCar:
//				sceneType = SolutionConstants.SCENE_TYPE_NEW_CAR;
//				break;
//			default:
//				break;
//			}
//		}
//	}
//
//	private void init() {
//		solutionStep1Pm25
//				.setText(mCurrentSensor != null ? mCurrentSensor
//						.getAir().getPm25() : "0");
//		solutionStep1CO2
//				.setText(mCurrentSensor != null ? mCurrentSensor
//						.getAir().getCo2() : "0");
//		solutionStep1VOC
//				.setText(mCurrentSensor != null ? mCurrentSensor
//						.getAir().getVoc() : "0");
//
//		bottomBarView.setBtnForwardText(getResources()
//				.getString(R.string.ui_solution_cancel));
//		bottomBarView
//				.setForwardListener(new BtnForwardListener());
//		bottomBarView.setBtnNextText(getResources().getString(
//				R.string.ui_solution_submit));
//		bottomBarView.setNextListener(new BtnNextListener());
//	}
//
//	private void initTitleView() {
//		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE,
//				View.GONE, View.GONE);
//		mTitleBarView.setTitleText(R.string.ui_solution_title);
//	}
//
//	private class BtnForwardListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			MainAcUtil.changeFragment(fManager,
//					HomeFragment.getInstance());
//		}
//	}
//
//	private class BtnNextListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//
//			if (sensorId != null && !"".equals(sensorId)) {
//				int type = SmartHomeServiceUtil
//						.getSensorTypeWithId(mCurrentSensor
//								.getSensorId());
//				// 气体类型（0:温度，1:湿度，2:co2，3:pm2.5，4:voc，5:c6h6，6:ch2o,7:酒精，8:co,9:ch4）
//				// 气体状态 0 低报 1正常 2 高报
//				if (type == SensorConstant.SENSOR_TYPE_AIR) {
//					Uri uri = Uri.parse(buildNewUrl());
//					Intent it = new Intent(Intent.ACTION_VIEW,
//							uri);
//					getActivity().startActivity(it);
//
//				}
//			}
//		}
//	}
//
//	private String buildNewUrl() {
//		StringBuilder url = new StringBuilder(
//				ServerConstant.SERVER_BASE_URI);
//		url.append(ServerConstant.SOLUTION);
//		url.append("?FILETYPE=10");
//		url.append("&CO2=");
//		url.append((Integer.valueOf(HomePagerUtil
//				.getCo2State(solutionStep1CO2.getText() + "") + 1) * 10));
//		url.append("&PM25=");
//		url.append((Integer.valueOf(HomePagerUtil
//				.getPm25State(solutionStep1Pm25.getText() + "") + 1) * 10));
//		url.append("&VOC=");
//		url.append((Integer.valueOf(HomePagerUtil
//				.getVocState(solutionStep1VOC.getText() + "") + 1) * 10));
//		url.append("&SCENE=").append(sceneType);
//
//		return url.toString();
//	}
//
//	/**
//	 * 拼接URL
//	 * 
//	 * @author 曾凡
//	 * @param airDetail
//	 *            GASTYPE // GASSTATE // TIMEINUSE
//	 * @return
//	 * @time 2015年6月3日 下午4:30:45
//	 */
//	private String buildUrl(SensorAirDetail airDetail) {
//		StringBuilder url = new StringBuilder(
//				ServerConstant.SERVER_BASE_URI);
//		url.append(ServerConstant.SOLUTION);
//		url.append("?&GASTYPE=");
//		url.append(SolutionConstants.CO2).append(",");
//		url.append(SolutionConstants.PM25).append(",");
//		url.append(SolutionConstants.VOC);
//		url.append("&GASSTATE=");
//		url.append(
//				Integer.valueOf(HomePagerUtil
//						.getCo2State(solutionStep1CO2.getText()
//								+ ""))).append(",");
//		url.append(
//				Integer.valueOf(HomePagerUtil
//						.getPm25State(solutionStep1Pm25
//								.getText() + ""))).append(",");
//		url.append(Integer.valueOf(HomePagerUtil
//				.getVocState(solutionStep1VOC.getText() + "")));
//
//		GregorianCalendar g = new GregorianCalendar();
//		int month = (int) g.get(Calendar.MONTH) + 1;
//		url.append("&TIMEINUSE=").append(month);
//		if (sceneType != 0) {
//			url.append("&SCENE=").append(sceneType);
//		}
//
//		return url.toString();
//	}
//
//	public String getSensorId() {
//		return sensorId;
//	}
//
//	public void setSensorId(String sensorId) {
//		this.sensorId = sensorId;
//	}
//}
