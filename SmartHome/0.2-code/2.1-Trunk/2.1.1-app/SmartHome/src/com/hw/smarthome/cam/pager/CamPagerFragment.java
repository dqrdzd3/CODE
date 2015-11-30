package com.hw.smarthome.cam.pager;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.cam.CamActivity;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.ui.weather.po.WeatherPo;
import com.hw.util.DateUtils;
import com.hw.util.UIUtil;

/**
 * @author 曾凡
 * @time 2015年3月20日 下午3:49:15
 */
public class CamPagerFragment extends Fragment {
	private Context mContext;
	public static String KEY_SENSOR_ID = "KEY_SENSOR_ID";
	public static String KEY_POS = "KEY_POS";
	private String mSensorId;
	private int mPos;
	private int pagerLayoutId;
	private View parentView;

	private TextView camTmp;
	private TextView camHum;
	private TextView camCo2;
	private TextView camPm25;
	private TextView camVoc;

	private TextView camCity;
	private TextView camLoc;
	private TextView camDate;
	private TextView camTime;
	private TextView camState;
	private float cityTextSize;

	private TextView camSensorName;
	private TextView camWeatherPM25;
	private TextView camWeatherTem;
	private TextView camSysTime;

	public static CamPagerFragment getInstance() {
		return new CamPagerFragment();
	}

	public CamPagerFragment() {

	}

	public void initFragment(int pos, String sensorId) {
		mPos = pos;
		mSensorId = sensorId;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle args) {

		switch (mPos) {
//		case 1:
//			pagerLayoutId = R.layout.cam_pager_layout1;
//			break;
//		case 2:
//			pagerLayoutId = R.layout.cam_pager_layout2;
//			break;
//		case 3:
//			pagerLayoutId = R.layout.cam_pager_layout3;
//			break;
//		case 4:
//			pagerLayoutId = R.layout.cam_pager_layout4;
//			break;
//		case 5:
//			pagerLayoutId = R.layout.cam_pager_layout5;
//			break;
//		case 6:
//			pagerLayoutId = R.layout.cam_pager_layout6;
//			break;
		case 2:
			pagerLayoutId = R.layout.cam_pager_weather_layout1;
			break;
		case 3:
			pagerLayoutId = R.layout.cam_pager_weather_layout2;
			break;
		case 4:
			pagerLayoutId = R.layout.cam_pager_weather_layout3;
			break;
		case 1:
			pagerLayoutId = R.layout.cam_pager_weather_layout4;
			break;
		case 5:
			pagerLayoutId = R.layout.cam_pager_weather_layout5;
			break;
		default:
			pagerLayoutId = R.layout.cam_pager_layout1;
			break;
		}
		parentView = inflater.inflate(pagerLayoutId, container,
				false);
		camTmp = (TextView) parentView.findViewById(R.id.camTmp);
		camHum = (TextView) parentView.findViewById(R.id.camHum);
		camCo2 = (TextView) parentView.findViewById(R.id.camCo2);
		camPm25 = (TextView) parentView
				.findViewById(R.id.camPm25);
		camVoc = (TextView) parentView.findViewById(R.id.camVoc);

		camCity = (TextView) parentView
				.findViewById(R.id.camCity);
		cityTextSize = camCity.getTextSize();
		cityTextSize = UIUtil.px2sp(mContext, cityTextSize);
		camLoc = (TextView) parentView.findViewById(R.id.camLoc);
		camDate = (TextView) parentView
				.findViewById(R.id.camDate);
		camTime = (TextView) parentView
				.findViewById(R.id.camTime);
		camState = (TextView) parentView
				.findViewById(R.id.camState);

		camSensorName = (TextView) parentView
				.findViewById(R.id.camSensorName);
		camWeatherPM25 = (TextView) parentView
				.findViewById(R.id.camWeatherPM25);
		camWeatherTem = (TextView) parentView
				.findViewById(R.id.camWeatherTem);
		camSysTime = (TextView) parentView
				.findViewById(R.id.camSysTime);

		startThreads();
		return parentView;
	}

	@Override
	public void onResume() {
		super.onResume();
		startThreads();
	}

	private void startThreads() {
		if (mDetailHandler != null) {
			mDetailHandler.removeCallbacks(updateDetailThread);
			mDetailHandler.post(updateDetailThread);
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		endThreads();
	}

	private void endThreads() {
		if (mDetailHandler != null) {
			mDetailHandler.removeCallbacks(updateDetailThread);
		}
	}

	/** 用于执行实时数据更新 FIXME功能完善后使用任务调度 */
	private Handler mDetailHandler = new Handler();
	private Runnable updateDetailThread = new Runnable() {
		@Override
		public void run() {
			camSysTime.setText(DateUtils.getCurrentMMDDTime());
			SensorDetail detail = HomeUtil.getSensorDetail(
					mContext, mSensorId);
			SensorAirDetail air = null;
			String city = "";
//			if (detail != null && detail.getName() != null
//					&& !"".equals(detail.getName())) {
//				camSensorName.setText(detail.getName());
//			}
			if (detail.getAir() != null) {
				air = detail.getAir();
				camTmp.setText(air.getTemperature());
				camHum.setText(air.getHumidity());
				camCo2.setText(air.getCo2());
				camPm25.setText(air.getPm25());
				camVoc.setText(air.getVoc());

				if (air.getCreateTime() != null
						&& air.getCreateTime().length() > 11) {
					camDate.setText(air.getCreateTime()
							.substring(0, 10));
					camTime.setText(air.getCreateTime()
							.substring(11));
				}

				if (CamActivity.mLocPo != null) {

					camState.setText(CamActivity.mLocPo
							.getProvince());
					city = CamActivity.mLocPo.getCity();
					if (pagerLayoutId == R.layout.cam_pager_weather_layout1
							|| pagerLayoutId == R.layout.cam_pager_weather_layout2
							|| pagerLayoutId == R.layout.cam_pager_weather_layout3
							|| pagerLayoutId == R.layout.cam_pager_weather_layout4
							|| pagerLayoutId == R.layout.cam_pager_weather_layout5) {
						if (CamActivity.mLocPo.isTalk()) {
							camLoc.setText(CamActivity.mLocPo
									.getCity());
						} else {
							camLoc.setText(CamActivity.mLocPo
									.getLoc());
							setCityText(city);
						}
					} else {
						camLoc.setText(CamActivity.mLocPo
								.getLoc());
						setCityText(city);
					}

				}
				if (CamActivity.weather != null) {
					WeatherPo weather = CamActivity.weather;
					if (weather.getEnvironment() != null) {
						camWeatherPM25.setText(weather
								.getEnvironment().getPm25());
					}
					camWeatherTem.setText(weather.getTemp());
				}

			}
			if (camLoc.getText().equals("")
					|| camLoc.getText() == null) {
				camLoc.setVisibility(View.GONE);
			} else {
				camLoc.setVisibility(View.VISIBLE);
			}
			/* 登陆这个页面后从服务器获取最新的实时数据 */
			MainAcUtil.send2Service(mContext,
					ServerConstant.SH01_02_02_01, 0);
			mDetailHandler.postDelayed(updateDetailThread, 1500);

		}
	};

	private void setCityText(String city) {

		float cityTempSize = 0;
		if (camCity != null && !"".equals(camCity)) {
			if (city.length() <= 3) {
				cityTempSize = cityTextSize;
			} else if (city.length() <= 5) {
				cityTempSize = cityTextSize - 5;
			} else if (city.length() <= 8) {
				cityTempSize = cityTextSize - 15;
			} else if (city.length() <= 10) {
				cityTempSize = cityTextSize - 20;
			}

			if (cityTempSize < 20) {
				cityTempSize = 15;
			}
			camCity.setTextSize(cityTempSize);
		}
		camCity.setText(city);
	}
}
