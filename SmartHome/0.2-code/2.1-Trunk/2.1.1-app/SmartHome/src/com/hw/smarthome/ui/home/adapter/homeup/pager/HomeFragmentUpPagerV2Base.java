package com.hw.smarthome.ui.home.adapter.homeup.pager;

import java.io.IOException;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetInputDialog;

import com.baidu.location.LocationClient;
import com.hw.smarthome.R;
import com.hw.smarthome.cam.CamActivity;
import com.hw.smarthome.cam.util.CamUtils;
import com.hw.smarthome.ctrl.setting.ui.CtrlSettingActivity;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.AlarmHistoryFragment;
import com.hw.smarthome.ui.home.adapter.homeup.InitDatas;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.SolutionStepChkFragment;
import com.hw.smarthome.ui.home.adapter.homeup.util.HardUpgradeUtil;
import com.hw.smarthome.ui.home.adapter.homeup.util.HomePagerUtil;
import com.hw.smarthome.ui.home.themecartoon.util.ThemeCartoonUtil;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.ui.weather.db.po.City;
import com.hw.smarthome.ui.weather.po.EnvironmentPo;
import com.hw.smarthome.ui.weather.po.WeatherPo;
import com.hw.smarthome.ui.weather.util.CityUtils;
import com.hw.smarthome.view.scrollview.ObservableScrollView;
import com.hw.smarthome.view.scrollview.ScrollViewListener;
import com.hw.smarthome.view.widget.MyGridView;
import com.hw.util.Ln;
import com.hw.util.ScreenShot;
import com.hw.util.application.SHApplication;
import com.hw.util.loc.LocPo;
import com.hw.util.loc.LocUtils;

/**
 * @author 曾凡 分享功能 闫威
 * @time 2014年7月8日 下午3:31:42
 */
public abstract class HomeFragmentUpPagerV2Base extends Fragment
		implements CityUtils.EventHandler {

	protected FragmentActivity mMainActivity;
	protected View mParentView;
	protected ImageView uiHomePagerBird;
	protected AnimationDrawable birdAnime;
	protected View uiHomeAQILayout;
	protected View uiHomeGasLayout;
	protected TextView uiHomeCh4;
	protected TextView uiHomeCO;

	protected ObservableScrollView uiHomePagerScrollView;
	protected TextView uiHomeAQI;
	protected TextView uiHomeScore;
	protected TextView uiHomeUpdateTime;
	protected TextView uiHomePagerSumSensor;
	protected View uiHomePagerSumSensorLayout;
	private MyReceiver mReceiver;
	protected SensorDetail mCurrentSensor; // 传感器当前值
	protected TextView uiHomeSensorId; // 在页面中缓存id信息
	private MyGridView currentDataGridview;
	private static HomeFragmentUpPager instance = null;
	private InitDatas initDatas = null;

	protected Button uiHomeSolution;// 解决方案
	private View space_view, up_space_view, down_space_view; // 适应屏幕大小的空隙间隔

	private ImageView upgradeImg;
	protected ImageView shareRealData;
	protected ImageView historyAlarm;

	private ImageView goShopImageView; // 购物
	private ImageView uiHomeCam;
	private ImageView uiHomeCtrl;

	protected View uiHomePagerLoc;
	protected View cityLayout;
	private LocationClient mLocationClient;
	private SHApplication application;
	private LocPo mLocPo;
	private ImageView LocImg;
	private TextView locTime;
	private TextView locCity;
	private TextView locAqi;
	private TextView locAqiStatus;
	private TextView locTem;
	private TextView locHum;
	private TextView locMajorTitle;
	private TextView locMajorName;
	private TextView lcoMajor;
	private CityUtils mCity;
	private City city;

	public City getCity() {
		return city;
	}

	/**
	 * 统一的设置城市的入口
	 * 
	 * @author 曾凡
	 * @param city
	 * @time 2015年7月8日 下午4:22:43
	 */
	public void setCity(City city) {
		this.city = city;
		String cityStr = "";
		if (city != null) {
			HomePagerUtil.saveCity(mMainActivity, city,
					mCurrentSensor.getSensorId());
			cityStr = city.getCity();
			if (cityStr != null && !"".equals(cityStr)) {
				// if (cityStr.length() == 2) {
				// locCity.setTextSize(45);
				// }
				// if (cityStr.length() == 3) {
				// locCity.setTextSize(20);
				// }
				// if (cityStr.length() == 4) {
				// locCity.setTextSize(20);
				// }
				// if (cityStr.length() == 5) {
				// locCity.setTextSize(20);
				// }
				// if (cityStr.length() >= 6) {
				// locCity.setTextSize(20);
				// }
				locCity.setText(cityStr);
				/* 获取天气信息 */
				mCity = new CityUtils(city.getCity());
			}
		}
	}

	public static HomeFragmentUpPager getInstance() {
		instance = new HomeFragmentUpPager();
		return instance;
	}

	public void init(SensorDetail currentSensor) {
		mCurrentSensor = currentSensor;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivity = (FragmentActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		mParentView = inflater.inflate(
				R.layout.ui_home_pager_v2, container, false);

		uiHomePagerScrollView = (ObservableScrollView) mParentView
				.findViewById(R.id.uiHomePagerScrollView);
		uiHomePagerScrollView.setVerticalScrollBarEnabled(false);
		uiHomePagerScrollView
				.setScrollViewListener(new ScrollViewListenerImpl());
		CityUtils.mListeners.add(this);
		/* 初始化上半部分 */
		initUpView();
		if (mCurrentSensor != null
				&& mCurrentSensor.getSensorId() != null
				&& !"".equals(mCurrentSensor.getSensorId())) {
			/* 初始化下半部分 */
			initDownView();
			initDownData();
			initShare();
			/* 初始化监听器 */
			// initReceiver();
		}
		return mParentView;
	}

	private class ScrollViewListenerImpl implements
			ScrollViewListener {

		@Override
		public void onScrollChanged(
				ObservableScrollView scrollView, int x, int y,
				int oldx, int oldy) {
			if (mCurrentSensor != null
					&& mCurrentSensor.getSensorId() != null
					&& !"".equals(mCurrentSensor.getSensorId())) {

				HomePagerUtil.blurBg(
						mCurrentSensor.getSensorId(), x, y,
						mMainActivity);
			}

		}

	}

	private void initUpView() {
		try {
			Typeface fontFace = Typeface.createFromAsset(
					mMainActivity.getAssets(),
					"fonts/UIHomePagerFont.ttf");
			uiHomePagerBird = (ImageView) mParentView
					.findViewById(R.id.uiHomePagerBird);
			uiHomePagerBird
					.setBackgroundResource(R.anim.ui_home_pager_bird_anim);
			birdAnime = (AnimationDrawable) uiHomePagerBird
					.getBackground();
			uiHomeAQILayout = mParentView
					.findViewById(R.id.uiHomeAQILayout);
			uiHomeGasLayout = mParentView
					.findViewById(R.id.uiHomeGasLayout);
			uiHomeCh4 = (TextView) mParentView
					.findViewById(R.id.uiHomeCh4);
			uiHomeCO = (TextView) mParentView
					.findViewById(R.id.uiHomeCO);
			uiHomeSensorId = (TextView) mParentView
					.findViewById(R.id.uiHomeSensorId);
			uiHomeAQI = (TextView) mParentView
					.findViewById(R.id.uiHomeAQI);
			uiHomeAQI.setTypeface(fontFace);
			uiHomeScore = (TextView) mParentView
					.findViewById(R.id.uiHomeScore);
			cityLayout = mParentView
					.findViewById(R.id.cityLayout);
			// uiHomeScore.setTypeface(fontFace);

			shareRealData = (ImageView) mParentView
					.findViewById(R.id.share_realtime_data);
			// 购物车
			goShopImageView = (ImageView) mParentView
					.findViewById(R.id.uiHomeShopping);
			goShopImageView
					.setOnClickListener(new ImgOnClickListener());

			historyAlarm = (ImageView) mParentView
					.findViewById(R.id.history_alarm);
			historyAlarm
					.setOnClickListener(new ImgOnClickListener());
			upgradeImg = (ImageView) mParentView
					.findViewById(R.id.upgrade); // 更新硬件
			upgradeImg
					.setOnClickListener(new ImgOnClickListener());
			uiHomeUpdateTime = (TextView) mParentView
					.findViewById(R.id.uiHomeUpdateTime);
			uiHomeUpdateTime
					.setOnClickListener(new ImgOnClickListener());
			uiHomePagerSumSensorLayout = mParentView
					.findViewById(R.id.uiHomePagerSumSensorLayout);
			uiHomePagerSumSensor = (TextView) mParentView
					.findViewById(R.id.uiHomePagerSumSensor);
			uiHomeSolution = (Button) mParentView
					.findViewById(R.id.uiHomeSolution);
			uiHomeSolution
					.setOnClickListener(new ImgOnClickListener());

			space_view = (View) mParentView
					.findViewById(R.id.ui_space);
			up_space_view = (View) mParentView
					.findViewById(R.id.uiHomeBgLayout);
			down_space_view = (View) mParentView
					.findViewById(R.id.ui_down_space);
			/* 计算高度 */
			HomePagerUtil.updateSpace(mMainActivity,
					mParentView, space_view, up_space_view,
					down_space_view);
		} catch (Exception e) {
			Ln.e(e, "初始化上半部分异常");
		}
	}

	private class ImgOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.uiHomeShopping:
				Uri uri = Uri.parse(ServerConstant.SHOP_URL);
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				mMainActivity.startActivity(it);
				break;
			case R.id.history_alarm:
				try {
					String sensorId = mCurrentSensor
							.getSensorId();
					Ln.i("查询报警信息:" + sensorId);
					onHistoryAlarm(sensorId);
				} catch (Exception e) {
					Ln.e(e, "历史报警异常");
				}
				break;
			case R.id.upgrade:
				SensorDetail detail = HomeUtil.getSensorDetail(
						mMainActivity, mCurrentSensor);
				HardUpgradeUtil.checkHard(mMainActivity, detail,
						true);
				break;
			case R.id.uiHomeSolution:
				SolutionStepChkFragment fragment = SolutionStepChkFragment
						.getInstance();
				fragment.setSensorId(mCurrentSensor
						.getSensorId());
				FragmentManager fManager = mMainActivity
						.getSupportFragmentManager();
				MainAcUtil.changeFragment(fManager, fragment);
				break;
			}
		}

	}

	/**
	 * 初始化下半部分页面控件（不包括请求数据）
	 * 
	 * @author 曾凡
	 * @time 2014年7月9日 下午5:19:00
	 */
	private void initDownView() {
		try {
			// ignoreReside();
			// final ImageView pagedownImageView = (ImageView) mParentView
			// .findViewById(R.id.uiHomePageUp);
			// final LinearLayout ll_gridview = (LinearLayout) mParentView
			// .findViewById(R.id.ll_gridview);
			View left = mParentView
					.findViewById(R.id.uiHomePageLeft);
			View right = mParentView
					.findViewById(R.id.uiHomePageRight);
			// left.setVisibility(View.GONE);
			// pagedownImageView
			// .setImageResource(R.drawable.ui_home_page_up);

			currentDataGridview = (MyGridView) mParentView
					.findViewById(R.id.currentDataGridview);
			int type = 0;
			String name = null;

			List<SensorDetail> detailList = SensorUtil
					.getSensorDetails(mMainActivity);
			if (detailList != null && detailList.size() > 0) {
				for (int i = 0; i < detailList.size(); i++) {
					if (detailList
							.get(i)
							.getSensorId()
							.equals(mCurrentSensor.getSensorId())) {
						if (i == 0 && i == detailList.size() - 1) {
							left.setVisibility(View.GONE);
							right.setVisibility(View.GONE);
						} else if (i == 0) {
							left.setVisibility(View.GONE);
							right.setVisibility(View.VISIBLE);
						} else if (i == detailList.size() - 1) {
							left.setVisibility(View.VISIBLE);
							right.setVisibility(View.GONE);
						} else {
							left.setVisibility(View.VISIBLE);
							right.setVisibility(View.VISIBLE);
						}

					}
				}

			} else {
				left.setVisibility(View.GONE);
				right.setVisibility(View.GONE);
			}

			name = mCurrentSensor.getName();
			if (name == null || "".equals(name)) {
				type = SmartHomeServiceUtil
						.getSensorTypeWithId(mCurrentSensor
								.getSensorId());
				name = SmartHomeServiceUtil
						.getDefaultSensorName(type);
			}
			mCurrentSensor.setViewName(name);

			uiHomeCam = (ImageView) mParentView
					.findViewById(R.id.uiHomeCam);
			uiHomeCam
					.setOnClickListener(new CamOnClickListener());

			uiHomeCtrl = (ImageView) mParentView
					.findViewById(R.id.uiHomeCtrl);
			uiHomeCtrl
					.setOnClickListener(new CtrlOnClickListener());
			List<SensorCtrlDetail> listSensorCtrlDetails = SensorUtil
					.getCtrlDetails(mMainActivity,
							mCurrentSensor.getSensorId());
			if (listSensorCtrlDetails.size() > 0) {
				uiHomeCtrl.setVisibility(View.VISIBLE);
			} else {
				uiHomeCtrl.setVisibility(View.GONE);
			}

			uiHomePagerLoc = mParentView
					.findViewById(R.id.uiHomePagerLoc);

			LocImg = (ImageView) mParentView
					.findViewById(R.id.uiHomePagerLocImg);
			LocImg.setOnClickListener(new LocImgOnClickListener());
			locTime = (TextView) mParentView
					.findViewById(R.id.uiHomePagerLocTime);
			locCity = (TextView) mParentView
					.findViewById(R.id.uiHomePagerLocCity);
			locCity.setOnClickListener(new LocOnClickListener());
			locAqi = (TextView) mParentView
					.findViewById(R.id.uiHomePagerLocAqi);
			locAqiStatus = (TextView) mParentView
					.findViewById(R.id.uiHomePagerLocAqiStatus);
			locTem = (TextView) mParentView
					.findViewById(R.id.uiHomePagerLocTem);
			locHum = (TextView) mParentView
					.findViewById(R.id.uiHomePagerLocHum);

			locMajorTitle = (TextView) mParentView
					.findViewById(R.id.locMajorTitle);
			lcoMajor = (TextView) mParentView
					.findViewById(R.id.lcoMajor);
			locMajorName = (TextView) mParentView
					.findViewById(R.id.locMajorName);
		} catch (Exception e) {
			Ln.e(e, "初始化失败");
		}

	}

	private class LocImgOnClickListener implements
			OnClickListener {
		@Override
		public void onClick(View v) {
			loc();
		}
	}

	private class LocOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			showCityList();
		}
	}

	private class CamOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			try {
				Bundle bundle = new Bundle();
				bundle.putString(MainActivity.KEY_SENSOR_ID,
						mCurrentSensor.getSensorId());
				Intent intent = new Intent(mMainActivity,
						CamActivity.class);
				intent.putExtras(bundle);
				intent.putExtra(MainActivity.KEY_WEATHER,
						mCity != null ? mCity.getWeather()
								: null);
				startActivity(intent);
			} catch (Exception e) {
				Ln.e(e, "照相机调用异常");
				Bundle bundle = new Bundle();
				bundle.putString(MainActivity.KEY_SENSOR_ID,
						mCurrentSensor.getSensorId());
				Intent intent = new Intent(mMainActivity,
						CamActivity.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
		}
	}

	private class CtrlOnClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			Bundle bundle = new Bundle();
			bundle.putString(MainActivity.KEY_SENSOR_ID,
					mCurrentSensor.getSensorId());
			Intent intent = new Intent(mMainActivity,
					CtrlSettingActivity.class);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	private void initDownData() {
		application = (SHApplication) mMainActivity
				.getApplication();
		mLocationClient = application.mLocationClient;
		initDatas = new InitDatas(mMainActivity, mParentView,
				currentDataGridview, mCurrentSensor);
	}

	private String shotFilePath;
	/**
	 * 分享
	 * 
	 * @author lijing
	 * @param type
	 * @param view
	 * @time 2014-7-22 上午10:51:31
	 */
	ExifInterface exif;

	private void initShare() {

		shareRealData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mMainActivity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						// 截图
						shotFilePath = ScreenShot
								.getBitmapByView(uiHomePagerScrollView);
					}
				});
				try {
					exif = new ExifInterface(shotFilePath);
					/*
					 * TAG_DATETIME时间日期 　　TAG_FLASH闪光灯 　　TAG_GPS_LATITUDE纬度
					 * 　　TAG_GPS_LATITUDE_REF纬度参考 　　TAG_GPS_LONGITUDE经度
					 * 　　TAG_GPS_LONGITUDE_REF经度参考 　　TAG_IMAGE_LENGTH图片长
					 * 　　TAG_IMAGE_WIDTH图片宽 　　TAG_MAKE设备制造商 　　TAG_MODEL设备型号
					 * 　　TAG_ORIENTATION方向 　　TAG_WHITE_BALANCE白平衡
					 */
					exif.setAttribute(
							ExifInterface.TAG_ORIENTATION, "");
					exif.saveAttributes();
				} catch (IOException e) {
					e.printStackTrace();
				}

				CamUtils.share(mMainActivity, mParentView,
						shotFilePath);
			}
		});
	}

	protected void showHistoryAlarm() {

	}

	public void onHistoryAlarm(String id) {
		FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		AlarmHistoryFragment fragment = AlarmHistoryFragment
				.getInstance();
		fragment.setSensorId(mCurrentSensor.getSensorId());
		fragment.setStyle(R.style.MyDialog, 0);
		fragment.show(ft, "df");
	}

	public abstract void showCityList();

	// /** 用于排名更新 */
	// private void updateRank() {
	// try {
	//
	// Map<String, String> paramMap = new HashMap<String, String>();
	// paramMap.put("SCORE", uiHomeAQI.getText().toString());
	// paramMap.put("GASTYPE", "3"); // 3 ：PM2.5
	//
	// MainAcUtil.send2Service(mMainActivity,
	// ServerConstant.RANKING, 0, paramMap);
	//
	// } catch (Exception e) {
	// Ln.e(e, "初始化数据异常");
	// }
	// }

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
			try {

				Bundle bundle = intent.getBundleExtra("action");
				String name = bundle.getString("name");
				SensorDetail detail = HomeUtil.getSensorDetail(
						mMainActivity, mCurrentSensor);
				mCurrentSensor = detail;
				if(mCurrentSensor==null){
					return;
				}
				if (initDatas == null) {
					initDownData();
				}
				/* [SH01_02_01] 查看历史信息 */
				if (ServerConstant.SH01_02_01_03.equals(name)) {
					if (bundle.getBoolean("result")) {
						initDatas.initHistory();
					}
				}

				/* 如果设备更新了则刷新页面 */
				if (ServerConstant.SH01_01_01_03.equals(name)) {
					/* 0是查询列表的结果 */
					if (bundle.getInt("type") == 0) {
						if (bundle.getBoolean("result")) {
							initDatas.instantiateDatas();
						}
					}
				}/* 如果设备更新了则刷新页面 */
				if (ServerConstant.SH01_02_02_01.equals(name)) {
					/* 0是查询列表的结果 */
					if (bundle.getInt("type") == 0) {
						if (bundle.getBoolean("result")) {
							initDatas.initRealTime();

							String talk = ThemeCartoonUtil
									.getInfoStrs(mCurrentSensor);
							if (!talk
									.equals(uiHomePagerSumSensor
											.getText() + "")) {
								uiHomePagerSumSensor
										.setText(talk);
							}

							// 20150602 曾凡 disable此功能
							// updateRank();
							city = HomePagerUtil
									.getCity(
											context,
											mCurrentSensor
													.getSensorId());
							if (city != null
									&& city.getCity() != null
									&& !"".equals(city.getCity())) {
								mCity = new CityUtils(
										city.getCity());
							} else {
								loc();
							}
						}
					}
				}

			} catch (Exception e) {
				Ln.e(e);
			}

			// 20150602 曾凡 disable此功能
			/* 排名 */
			// if (ServerConstant.RANKING.equals(name)) {
			// /* 0是查询列表的结果 */
			// String leader = bundle.getString("data");
			// leader = leader == null || "".equals(leader) ? "全国"
			// : leader;
			// if (bundle.getBoolean("result")) {
			// strRank = "领先全国" + leader + "用户 >>";
			// uiRankingTextView.setText(strRank);
			// }
			// }
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
		// ThemeCartoon.setInstance(null);
		HomePagerUtil.chkIsShowBtns(mMainActivity, upgradeImg,
				historyAlarm, mCurrentSensor);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		endReceiver();
	}

	/**
	 * 定位
	 * 
	 * @author 曾凡
	 * @time 2015年3月23日 下午5:55:21
	 */
	public void loc() {
		mMainActivity.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				String urlj = ServerConstant.LOC_INFO
						+ "&location=" + application.locInfo;
				FinalHttp fhh = new FinalHttp();
				fhh.get(urlj, new AjaxCallBack<Object>() {
					@Override
					public void onSuccess(Object t) {
						try {
							City city = new City();
							mLocPo = LocUtils.parserLocPo(t
									.toString());
							city.setCity(mLocPo.getCity());
							setCity(city);
							mLocationClient.stop();
						} catch (Exception e) {
							Ln.e(e, "解析地理信息异常");
							FinalHttp fhh = new FinalHttp();
							fhh.get(ServerConstant.LOC_BY_IP_INFO,
									new AjaxCallBack<Object>() {
										@Override
										public void onSuccess(
												Object t) {
											try {
												City city = new City();
												mLocPo = LocUtils
														.parserIpLocPo(t
																.toString());
												city.setCity(mLocPo
														.getCity());
												setCity(city);
												mLocationClient
														.stop();
											} catch (Exception e) {
												Ln.e(e,
														"解析地理信息异常");
												// locDialog();
											} finally {

											}
										}
									});

						} finally {

						}
					}
				});
			}
		});
	}

	private void locDialog() {
		SweetInputDialog dialog = new SweetInputDialog(
				mMainActivity)
				.setTitleText(
						getString(R.string.ui_pager_location_query_failed))
				.setCancelText(
						getString(R.string.ui_pager_weather_cancel))
				.setConfirmText(
						getString(R.string.ui_pager_weather_confirm))
				.showCancelButton(true)
				.setCancelClickListener(
						new SweetInputDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetInputDialog sDialog) {
								sDialog.dismiss();
							}
						})
				.setConfirmClickListener(
						new SweetInputDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetInputDialog sDialog) {
								sDialog.dismiss();
								showCityList();
							}
						});

		dialog.show();
	}

	@Override
	public void onCityComplite() {
	}

	@Override
	public void onNetChange() {
	}

	@Override
	public void onCityWeatherComplite() {
		try {
			if(mMainActivity==null){
				mMainActivity = (FragmentActivity)getActivity();
			}
			String updateTime = mMainActivity
					.getResources()
					.getString(
							R.string.ui_pager_weather_update_time);
			String noCity = mMainActivity
					.getResources()
					.getString(R.string.ui_pager_weather_no_city);
			String noInfo = mMainActivity
					.getResources()
					.getString(
							R.string.ui_pager_weather_no_city_info);
			if (mCity != null && mCity.getWeather() != null) {
				WeatherPo weather = mCity.getWeather();
				locCity.setText(city.getCity());
				if (weather.getUpdateTime() == null) {
					locTime.setText(noCity);
					locTem.setText(noInfo);
					locHum.setText("");
					locAqi.setText(noInfo);
					locAqiStatus.setText(noInfo);
				} else {
					locTime.setText(updateTime
							+ weather.getUpdateTime());

					if (weather.getEnvironment() != null) {
						locAqi.setText(weather.getEnvironment()
								.getAqi());
						locAqiStatus.setText(weather
								.getEnvironment().getQuality());

						initMajor(weather.getEnvironment());
					}
					locTem.setText(weather.getTemp() + "°/");
					locHum.setText(weather.getHum());
				}
			}
		} catch (Exception e) {
			Ln.e(e, "获取地理信息异常");
		}
	}

	private void initMajor(EnvironmentPo po) {
		String majorTitle = po.getMajorPollutants();
		String text = "";
		String value = "";
		if (majorTitle != null && !"".equals(majorTitle)) {
			if (majorTitle.contains("(")
					&& majorTitle.contains(")")) {
				text = majorTitle.substring(
						majorTitle.indexOf('(') + 1,
						majorTitle.indexOf(')'));
				text = text.toLowerCase();
			} else {
				text = majorTitle;
			}

			if (text.startsWith("臭氧")) {
				text = mMainActivity.getResources().getString(
						R.string.ui_pager_weather_unit_o3);
				value = po.getO3();
			} else if (text.startsWith("pm1")) {
				text = mMainActivity.getResources().getString(
						R.string.ui_pager_weather_unit_pm10);
				value = po.getPm10();
			} else if (text.startsWith("pm2")) {
				text = mMainActivity.getResources().getString(
						R.string.ui_pager_weather_unit_pm25);
				value = po.getPm25();
			} else if (text.startsWith("二氧化硫")) {
				text = mMainActivity.getResources().getString(
						R.string.ui_pager_weather_unit_so2);
				value = po.getSo2();
			} else if (text.startsWith("二氧化氮")) {
				text = mMainActivity.getResources().getString(
						R.string.ui_pager_weather_unit_no2);
				value = po.getNo2();
			}

		} else {
			text = mMainActivity.getResources().getString(
					R.string.ui_pager_weather_unit_pm25);
			value = (po.getPm25() == null || "".equals(po
					.getPm25())) ? "0" : po.getPm25();
		}
		locMajorTitle.setText(text);
		lcoMajor.setText(value);
		if (locMajorTitle.getText() != null
				&& !"".equals(locMajorTitle.getText())) {
			locMajorName
					.setText(mMainActivity
							.getResources()
							.getString(
									R.string.ui_pager_weather_major_title));
		} else {
			locMajorName.setText("");
		}
	}
}
