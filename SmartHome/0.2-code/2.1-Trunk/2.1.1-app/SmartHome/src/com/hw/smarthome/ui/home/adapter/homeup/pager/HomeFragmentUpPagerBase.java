//package com.hw.smarthome.ui.home.adapter.homeup.pager;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.content.IntentFilter;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.Rect;
//import android.graphics.drawable.Drawable;
//import android.media.ExifInterface;
//import android.net.Uri;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.ViewGroup.LayoutParams;
//import android.view.ViewTreeObserver;
//import android.view.ViewTreeObserver.OnPreDrawListener;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.ScrollView;
//import android.widget.TextView;
//
//import com.hw.smarthome.R;
//import com.hw.smarthome.cam.CamActivity;
//import com.hw.smarthome.cam.util.CamUtils;
//import com.hw.smarthome.ctrl.setting.ui.CtrlSettingActivity;
//import com.hw.smarthome.po.SensorCtrlDetail;
//import com.hw.smarthome.po.SensorDetail;
//import com.hw.smarthome.server.constant.ServerConstant;
//import com.hw.smarthome.service.SmartHomeService;
//import com.hw.smarthome.service.util.SmartHomeServiceUtil;
//import com.hw.smarthome.ui.MainActivity;
//import com.hw.smarthome.ui.home.AlarmHistoryFragment;
//import com.hw.smarthome.ui.home.adapter.homeup.InitDatas;
//import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.SolutionStep1Fragment;
//import com.hw.smarthome.ui.home.adapter.homeup.util.HardUpgradeUtil;
//import com.hw.smarthome.ui.home.adapter.homeup.util.HomePagerUtil;
//import com.hw.smarthome.ui.home.themecartoon.ThemeCartoon;
//import com.hw.smarthome.ui.home.themecartoon.pop.CartoonSaidPopWindow;
//import com.hw.smarthome.ui.home.util.HomeUtil;
//import com.hw.smarthome.ui.sensor.util.SensorUtil;
//import com.hw.smarthome.ui.util.MainAcUtil;
//import com.hw.smarthome.view.widget.MyGridView;
//import com.hw.util.ImageUtil;
//import com.hw.util.ImgUtil;
//import com.hw.util.Ln;
//import com.hw.util.ScreenShot;
//import com.hw.util.UIUtil;
//
///**
// * @author 曾凡 分享功能 闫威
// * @time 2014年7月8日 下午3:31:42
// */
//public class HomeFragmentUpPagerBase extends Fragment {
//
//	protected FragmentActivity mMainActivity;
//	protected View mParentView;
//	protected TextView uiHomeAQI;
//	protected TextView uiHomeScore;
//	protected TextView uiHomeUpdateTime;
//
//	protected CartoonSaidPopWindow saidPop;
//	protected ImageView uiHomeThemeImage;
//	private MyReceiver mReceiver;
//	protected SensorDetail mCurrentSensor; // 传感器当前值
//	protected TextView uiHomeSensorId; // 在页面中缓存id信息
//	private MyGridView currentDataGridview;
//	private static HomeFragmentUpPager instance = null;
//	private InitDatas initDatas = null;
//
//	protected TextView uiRankingTextView; // 排名文本
//	private View uiHomeSolution;// 解决方案
//	private View space_view, up_space_view, down_space_view; // 适应屏幕大小的空隙间隔
//
//	// protected TextView uiHomeSensorName;
//	protected ImageView uiRankImageView;
//
//	private String strRank = "暂无排名";
//
//	private ImageView upgradeImg;
//	protected ImageView shareRealData;
//	protected ImageView historyAlarm;
//
//	private ImageView goShopImageView; // 购物
//	private ImageView uiHomeCam;
//	private ImageView uiHomeCtrl;
//
//	public static HomeFragmentUpPager getInstance() {
//
//		instance = new HomeFragmentUpPager();
//		return instance;
//	}
//
//	public void init(SensorDetail currentSensor) {
//		mCurrentSensor = currentSensor;
//	}
//
//	@Override
//	public void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		mMainActivity = (FragmentActivity) getActivity();
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater,
//			ViewGroup container, Bundle savedInstanceState) {
//		mParentView = inflater.inflate(R.layout.ui_home_pager,
//				container, false);
//		/* 初始化上半部分 */
//		initUpView();
//		if (mCurrentSensor != null
//				&& mCurrentSensor.getSensorId() != null
//				&& !"".equals(mCurrentSensor.getSensorId())) {
//			/* 初始化下半部分 */
//			initDownView();
//			initDownData();
//			initShare();
//			/* 初始化监听器 */
//			// initReceiver();
//		}
//		return mParentView;
//	}
//
//	private void initUpView() {
//		try {
//			uiHomeSensorId = (TextView) mParentView
//					.findViewById(R.id.uiHomeSensorId);
//			uiHomeAQI = (TextView) mParentView
//					.findViewById(R.id.uiHomeAQI);
//			uiHomeScore = (TextView) mParentView
//					.findViewById(R.id.uiHomeScore);
//			shareRealData = (ImageView) mParentView
//					.findViewById(R.id.share_realtime_data);
//			// 购物车
//			goShopImageView = (ImageView) mParentView
//					.findViewById(R.id.uiHomeShopping);
//			goShopImageView
//					.setOnClickListener(new ImgOnClickListener());
//
//			historyAlarm = (ImageView) mParentView
//					.findViewById(R.id.history_alarm);
//			historyAlarm
//					.setOnClickListener(new ImgOnClickListener());
//			upgradeImg = (ImageView) mParentView
//					.findViewById(R.id.upgrade); // 更新硬件
//			upgradeImg
//					.setOnClickListener(new ImgOnClickListener());
//			uiHomeUpdateTime = (TextView) mParentView
//					.findViewById(R.id.uiHomeUpdateTime);
//			/* 初始化一个对话框 */
//			saidPop = CartoonSaidPopWindow
//					.getInstance(mMainActivity);
//			uiHomeThemeImage = (ImageView) mParentView
//					.findViewById(R.id.uiHomeThemeImage);
//			uiHomeThemeImage.setOnClickListener(ThemeCartoon
//					.getInstance(uiHomeThemeImage, saidPop,
//							mCurrentSensor.getSensorId()));
//			uiHomeThemeImage
//					.setImageResource(R.drawable.ui_home_001);
//
//			uiRankingTextView = (TextView) mParentView
//					.findViewById(R.id.ranking);
//			uiHomeSolution = mParentView
//					.findViewById(R.id.uiHomeSolution);
//			uiHomeSolution
//					.setOnClickListener(new ImgOnClickListener());
//			uiRankImageView = (ImageView) mParentView
//					.findViewById(R.id.rank_state);
//
//			space_view = (View) mParentView
//					.findViewById(R.id.ui_space);
//			up_space_view = (View) mParentView
//					.findViewById(R.id.uiHomeBgLayout);
//			down_space_view = (View) mParentView
//					.findViewById(R.id.ui_down_space);
//			/* 计算高度 */
//			updateSpace();
//		} catch (Exception e) {
//			Ln.e(e, "初始化上半部分异常");
//		}
//	}
//
//	private class ImgOnClickListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.uiHomeShopping:
//				Uri uri = Uri.parse(ServerConstant.SHOP_URL);
//				Intent it = new Intent(Intent.ACTION_VIEW, uri);
//				mMainActivity.startActivity(it);
//				break;
//			case R.id.history_alarm:
//				try {
//					String sensorId = mCurrentSensor
//							.getSensorId();
//					Ln.i("查询报警信息:" + sensorId);
//					onHistoryAlarm(sensorId);
//				} catch (Exception e) {
//					Ln.e(e, "历史报警异常");
//				}
//				break;
//			case R.id.upgrade:
//				SensorDetail detail = HomeUtil.getSensorDetail(
//						mMainActivity, mCurrentSensor);
//				HardUpgradeUtil.checkHard(mMainActivity, detail,
//						true);
//				break;
//			case R.id.uiHomeSolution:
//
//				FragmentManager fManager = mMainActivity
//						.getSupportFragmentManager();
//				MainAcUtil.changeFragment(fManager,
//						SolutionStep1Fragment
//								.getInstance(mCurrentSensor
//										.getSensorId()));
//				break;
//			}
//		}
//
//	}
//
//	/**
//	 * 计算高度
//	 * 
//	 * @author 曾凡
//	 * @time 2015年5月15日 下午1:11:59
//	 */
//	private void updateSpace() {
//		/*
//		 * 计算高度，重新布局
//		 */
//		LinearLayout mainLayout = (LinearLayout) mParentView
//				.findViewById(R.id.uiHomeLayout);
//		ViewTreeObserver vto = mainLayout.getViewTreeObserver();
//		vto.addOnPreDrawListener(new OnPreDrawListener() {
//			@Override
//			public boolean onPreDraw() {
//				// TODO Auto-generated method stub
//				LayoutParams params1 = space_view
//						.getLayoutParams();
//				LayoutParams params2 = up_space_view
//						.getLayoutParams();
//				LayoutParams params3 = down_space_view
//						.getLayoutParams();
//
//				Rect frame = new Rect();
//				mMainActivity.getWindow().getDecorView()
//						.getWindowVisibleDisplayFrame(frame);
//
//				int contentHeight = frame.height()
//						- UIUtil.dip2px(mMainActivity, 45);
//
//				int upHeight = (int) (contentHeight * 0.48);
//				int downHeight = (int) (contentHeight * 0.35);
//				if (downHeight < 440) {
//					downHeight = 440;
//					params3.height = 250;
//					params1.height = contentHeight - upHeight
//							- downHeight;
//				} else {
//					params3.height = 400;
//					params1.height = contentHeight - upHeight
//							- downHeight - 200;
//				}
//				params2.height = upHeight;
//
//				return true;
//			}
//		});
//	}
//
//	/**
//	 * 初始化下半部分页面控件（不包括请求数据）
//	 * 
//	 * @author 曾凡
//	 * @time 2014年7月9日 下午5:19:00
//	 */
//	private void initDownView() {
//		try {
//			// ignoreReside();
//			final ImageView pagedownImageView = (ImageView) mParentView
//					.findViewById(R.id.uiHomePageUp);
//			final LinearLayout ll_gridview = (LinearLayout) mParentView
//					.findViewById(R.id.ll_gridview);
//			View left = mParentView
//					.findViewById(R.id.uiHomePageLeft);
//			View right = mParentView
//					.findViewById(R.id.uiHomePageRight);
//			// left.setVisibility(View.GONE);
//			pagedownImageView
//					.setImageResource(R.drawable.ui_home_page_up);
//
//			currentDataGridview = (MyGridView) mParentView
//					.findViewById(R.id.currentDataGridview);
//			int type = 0;
//			String name = null;
//
//			List<SensorDetail> detailList = SensorUtil
//					.getSensorDetails(mMainActivity);
//			if (detailList != null && detailList.size() > 0) {
//				for (int i = 0; i < detailList.size(); i++) {
//					if (detailList
//							.get(i)
//							.getSensorId()
//							.equals(mCurrentSensor.getSensorId())) {
//						if (i == 0 && i == detailList.size() - 1) {
//							left.setVisibility(View.GONE);
//							right.setVisibility(View.GONE);
//						} else if (i == 0) {
//							left.setVisibility(View.GONE);
//							right.setVisibility(View.VISIBLE);
//						} else if (i == detailList.size() - 1) {
//							left.setVisibility(View.VISIBLE);
//							right.setVisibility(View.GONE);
//						} else {
//							left.setVisibility(View.VISIBLE);
//							right.setVisibility(View.VISIBLE);
//						}
//
//					}
//				}
//
//			} else {
//				left.setVisibility(View.GONE);
//				right.setVisibility(View.GONE);
//			}
//
//			name = mCurrentSensor.getName();
//			if (name == null || "".equals(name)) {
//				type = SmartHomeServiceUtil
//						.getSensorTypeWithId(mCurrentSensor
//								.getSensorId());
//				name = SmartHomeServiceUtil
//						.getDefaultSensorName(type);
//			}
//			mCurrentSensor.setViewName(name);
//
//			uiHomeCam = (ImageView) mParentView
//					.findViewById(R.id.uiHomeCam);
//			uiHomeCam
//					.setOnClickListener(new CamOnClickListener());
//
//			uiHomeCtrl = (ImageView) mParentView
//					.findViewById(R.id.uiHomeCtrl);
//			uiHomeCtrl
//					.setOnClickListener(new CtrlOnClickListener());
//			List<SensorCtrlDetail> listSensorCtrlDetails = SensorUtil
//					.getCtrlDetails(mMainActivity,
//							mCurrentSensor.getSensorId());
//			if (listSensorCtrlDetails.size() > 0) {
//				uiHomeCtrl.setVisibility(View.VISIBLE);
//			} else {
//				uiHomeCtrl.setVisibility(View.GONE);
//			}
//
//		} catch (Exception e) {
//			Ln.e(e, "初始化失败");
//		}
//
//	}
//
//	private class CamOnClickListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			Bundle bundle = new Bundle();
//			bundle.putString(MainActivity.KEY_SENSOR_ID,
//					mCurrentSensor.getSensorId());
//			Intent intent = new Intent(mMainActivity,
//					CamActivity.class);
//			intent.putExtras(bundle);
//			startActivity(intent);
//		}
//	}
//
//	private class CtrlOnClickListener implements OnClickListener {
//		@Override
//		public void onClick(View v) {
//			Bundle bundle = new Bundle();
//			bundle.putString(MainActivity.KEY_SENSOR_ID,
//					mCurrentSensor.getSensorId());
//			Intent intent = new Intent(mMainActivity,
//					CtrlSettingActivity.class);
//			intent.putExtras(bundle);
//			startActivity(intent);
//		}
//	}
//
//	private void initDownData() {
//		initDatas = new InitDatas(mMainActivity, mParentView,
//				currentDataGridview, mCurrentSensor);
//	}
//
//	private String shotFilePath;
//	/**
//	 * 分享
//	 * 
//	 * @author lijing
//	 * @param type
//	 * @param view
//	 * @time 2014-7-22 上午10:51:31
//	 */
//	ExifInterface exif;
//
//	private void initShare() {
//
//		shareRealData.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				mMainActivity.runOnUiThread(new Runnable() {
//					@Override
//					public void run() {
//						// 截图
//						shotFilePath = ScreenShot
//								.getBitmapByView((ScrollView) mParentView
//										.findViewById(R.id.ui_scrollview));
//					}
//				});
//				try {
//					exif = new ExifInterface(shotFilePath);
//					/*
//					 * TAG_DATETIME时间日期 　　TAG_FLASH闪光灯 　　TAG_GPS_LATITUDE纬度
//					 * 　　TAG_GPS_LATITUDE_REF纬度参考 　　TAG_GPS_LONGITUDE经度
//					 * 　　TAG_GPS_LONGITUDE_REF经度参考 　　TAG_IMAGE_LENGTH图片长
//					 * 　　TAG_IMAGE_WIDTH图片宽 　　TAG_MAKE设备制造商 　　TAG_MODEL设备型号
//					 * 　　TAG_ORIENTATION方向 　　TAG_WHITE_BALANCE白平衡
//					 */
//					exif.setAttribute(
//							ExifInterface.TAG_ORIENTATION, "");
//					exif.saveAttributes();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//
//				CamUtils.share(mMainActivity, mParentView,
//						shotFilePath);
//			}
//		});
//	}
//
//	protected void showHistoryAlarm() {
//
//	}
//
//	public void onHistoryAlarm(String id) {
//		FragmentTransaction ft = getFragmentManager()
//				.beginTransaction();
//		AlarmHistoryFragment fragment = AlarmHistoryFragment
//				.getInstance();
//		fragment.setSensorId(mCurrentSensor.getSensorId());
//		fragment.setStyle(R.style.MyDialog, 0);
//		fragment.show(ft, "df");
//	}
//
//	/** 用于排名更新 */
//	private void updateRank() {
//		try {
//
//			Map<String, String> paramMap = new HashMap<String, String>();
//			paramMap.put("SCORE", uiHomeAQI.getText().toString());
//			paramMap.put("GASTYPE", "3"); // 3 ：PM2.5
//
//			MainAcUtil.send2Service(getActivity(),
//					ServerConstant.RANKING, 0, paramMap);
//
//		} catch (Exception e) {
//			Ln.e(e, "初始化数据异常");
//		}
//	}
//
//	/**
//	 * 注册监听器
//	 * 
//	 * @author 曾凡
//	 * @time 2014年7月9日 下午4:51:11
//	 */
//	private void initReceiver() {
//		/* 注册广播接收器，用来接受返回的明细和历史信息，一定要最后去监听 */
//		mReceiver = new MyReceiver();
//		IntentFilter filter = new IntentFilter();
//		filter.addAction(SmartHomeService.class.getName());
//		mMainActivity.registerReceiver(mReceiver, filter);
//	}
//
//	/**
//	 * 获取广播数据 更新页面内容
//	 */
//	private class MyReceiver extends BroadcastReceiver {
//		@Override
//		public void onReceive(Context context, Intent intent) {
//			Bundle bundle = intent.getBundleExtra("action");
//			String name = bundle.getString("name");
//			if (initDatas == null) {
//				initDownData();
//			}
//			/* [SH01_02_01] 查看历史信息 */
//			if (ServerConstant.SH01_02_01_03.equals(name)) {
//				if (bundle.getBoolean("result")) {
//					initDatas.initHistory();
//				}
//			}
//
//			/* 如果设备更新了则刷新页面 */
//			if (ServerConstant.SH01_01_01_03.equals(name)) {
//				/* 0是查询列表的结果 */
//				if (bundle.getInt("type") == 0) {
//					if (bundle.getBoolean("result")) {
//						initDatas.instantiateDatas();
//					}
//				}
//			}/* 如果设备更新了则刷新页面 */
//			if (ServerConstant.SH01_02_02_01.equals(name)) {
//				/* 0是查询列表的结果 */
//				if (bundle.getInt("type") == 0) {
//					if (bundle.getBoolean("result")) {
//						initDatas.initRealTime();
//						// 20150602 曾凡 disable此功能
//						// updateRank();
//					}
//				}
//			}
//			// 20150602 曾凡 disable此功能
//			/* 排名 */
//			// if (ServerConstant.RANKING.equals(name)) {
//			// /* 0是查询列表的结果 */
//			// String leader = bundle.getString("data");
//			// leader = leader == null || "".equals(leader) ? "全国"
//			// : leader;
//			// if (bundle.getBoolean("result")) {
//			// strRank = "领先全国" + leader + "用户 >>";
//			// uiRankingTextView.setText(strRank);
//			// }
//			// }
//		}
//	}
//
//	private void endReceiver() {
//		if (mReceiver != null) {
//			try {
//				mMainActivity.unregisterReceiver(mReceiver);
//			} catch (Exception e) {
//				Ln.e(e, "注销HomeFragment的监听器异常");
//			}
//		}
//	}
//
//	@Override
//	public void onPause() {
//		super.onPause();
//		endReceiver();
//	}
//
//	@Override
//	public void onResume() {
//		super.onResume();
//		Ln.d("onResume");
//		initReceiver();
//		ThemeCartoon.setInstance(null);
//		HomePagerUtil.chkIsShowBtns(mMainActivity, upgradeImg,
//				historyAlarm, mCurrentSensor);
//	}
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		endReceiver();
//	}
//
//}
