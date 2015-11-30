package com.hw.smarthome.ui.home.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.server.deal.DealWithSolution;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.homeup.HomeFragmentUpAdapter;
import com.hw.smarthome.ui.home.adapter.homeup.pager.HomeFragmentControlPager;
import com.hw.smarthome.ui.home.adapter.homeup.pager.HomeFragmentUpPager;
import com.hw.smarthome.ui.home.adapter.homeup.util.HomeMenuUtil;
import com.hw.smarthome.ui.home.adapter.homeup.util.HomePagerUtil;
import com.hw.smarthome.ui.home.constant.HomeConstant;
import com.hw.smarthome.ui.home.po.SceneUI;
import com.hw.smarthome.ui.home.scene.SceneAddActivity;
import com.hw.smarthome.ui.home.scene.adapter.CustomELVAdapter;
import com.hw.smarthome.ui.home.scene.adapter.SceneViewHolder;
import com.hw.smarthome.ui.home.util.SceneUtil;
import com.hw.smarthome.ui.sensor.SensorOneKeyFragment;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.Ln;
import com.hw.util.UIUtil;
import com.hw.util.application.SHApplication;
import com.special.ResideMenu.ResideMenu;
import com.zf.view.CircleImageView;
import com.zf.view.CustomExpandableListView;
import com.zf.view.CustomExpandableListView.OnRefreshListener;

/**
 * @author 曾凡
 * @time 2015年5月7日 下午6:03:59
 */
public abstract class HomeFragmentBase extends Fragment {

	/** 必须要用这个MainActivity，这样才能配置菜单 */
	protected MainActivity mMainActivity;
	protected SHApplication application;
	protected View parentView;
	public static View uiHomeLayout;
	private View uiHomeSensorEmpty;
	public static View homeFragment;
	private PopProgress popProgress;
	protected CircleImageView leftMenu;
	protected Button rightMenu;
	protected ResideMenu resideMenu;
	protected ImageView mainGaideImageView;
	public static ViewPager mPager;
	protected HomeFragmentUpAdapter upViewAdapter;

	public static RadioGroup homeRadioGroup;
	protected MyReceiver mReceiver;
	/** 用于执行页面数据更新 */
	protected List<SensorDetail> sensorList;
	protected List<Fragment> viewList = new ArrayList<Fragment>();

	// protected SensorListAdapter sensorListAdapter;
	// protected CustomListView homeDeviceList;
	public static CustomExpandableListView homeDeviceList;
	private CustomELVAdapter sceneAdapter;

	/** 传感器当前的页码 */
	private static int mCurrentPagerNo = 0;
	public TextView uiHomeSensorName;
	public TextView uiHomeSensorId;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mMainActivity = (MainActivity) getActivity();
		application = (SHApplication) mMainActivity
				.getApplicationContext();

		/* 更新URL和解决方案 */
		DealWithSolution.getShopUrl(mMainActivity);
		DealWithSolution.getSolutionMap(mMainActivity);

	}

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
				boolean result = bundle.getBoolean("result");
				// int type = bundle.getInt("type");

				/* [SH01_06_04_03] 查询场景 */
				if (ServerConstant.SH01_06_04_03
						.equals(actionName)) {
					updateSceneList();
				}
				/* [SH01_06_04_04] 删除场景 */
				if (ServerConstant.SH01_06_04_04
						.equals(actionName)) {
					updateSceneList();
					if (!result) {
						String message = bundle
								.getString("message");
						UIUtil.ToastMessage(
								mMainActivity,
								mMainActivity
										.getString(R.string.scene_delete_res_fail)
										+ ":" + message);
					} else {
						UIUtil.ToastMessage(
								mMainActivity,
								mMainActivity
										.getString(R.string.scene_delete_res_success));
					}
				}
				/* [SH01_01_01_03] 查看已发现设备 */
				if (ServerConstant.SH01_01_01_03
						.equals(actionName)) {
					uiHomeSensorEmpty.setVisibility(View.GONE);
					sensorList = SensorUtil
							.getSensorDetails(mMainActivity);
					/* 曾凡 20150713 19:16 */
					if (sensorList == null
							|| sensorList.size() == 0) {
						mMainActivity.toRegSensor();
					}
					initPagerViews();
				}

			} catch (Exception e) {
				Ln.e(e, "解析报警数据异常:" + jsonData);
			}
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		findView(inflater, container);
		initMenuView();
		init();
		return parentView;
	}

	// private static final String[] groupname = { "厨房", "客厅",
	// "卧室", "未分配" };
	//
	// private static final String[][] data = {
	// { "厨房A1", "厨房R1", "空净A" }, { "客厅A1", "加湿B" },
	// { "卧室A1", "R1" }, { "空气质量A1" } };
	//
	// private static final String[][] listinfo = { { "", "", "" },
	// { "", "" }, { "", "" }, { "" } };
	//
	// private static final int[] ImgBckgrnd = {
	// R.drawable.ui_home_scene_default_kitchen,
	// R.drawable.ui_home_scene_default_livingroom,
	// R.drawable.ui_home_scene_default_bedroom,
	// R.drawable.ui_home_scene_default_unsign };

	private void findView(LayoutInflater inflater,
			ViewGroup container) {
		parentView = inflater.inflate(R.layout.ui_home_v2,
				container, false);
		uiHomeLayout = parentView
				.findViewById(R.id.uiHomeLayout);
		uiHomeSensorEmpty = parentView
				.findViewById(R.id.uiHomeSensorEmpty);
		homeFragment = parentView
				.findViewById(R.id.homeFragment);
		homeRadioGroup = (RadioGroup) parentView
				.findViewById(R.id.homeRadioGroup);
		leftMenu = (CircleImageView) parentView
				.findViewById(R.id.title_bar_left_menu);
		rightMenu = (Button) parentView
				.findViewById(R.id.title_bar_right_menu);
		mPager = (ViewPager) parentView
				.findViewById(R.id.homePager);
		// homeDeviceList = (CustomListView) parentView
		// .findViewById(R.id.homeDeviceList);

		homeDeviceList = (CustomExpandableListView) parentView
				.findViewById(R.id.homeDeviceList);
		/*
		 * 只要调用了ListView的setDivider接口，mDividerHeight就会被置为0或-1，所以你根本看不到线，如果想看到线，
		 * 就要把调用顺序反过来
		 */
		homeDeviceList
				.setDivider(new ColorDrawable(Color.BLACK));
		homeDeviceList.setDividerHeight(1);

		uiHomeSensorName = (TextView) parentView
				.findViewById(R.id.uiHomeSensorName);
		uiHomeSensorId = (TextView) parentView
				.findViewById(R.id.uiHomeSensorId);
	}

	private void initMenuView() {
		resideMenu = ((MainActivity) mMainActivity)
				.getResideMenu();
		UserPO userPO = DealWithAccount.getUser(mMainActivity);
		if (userPO != null && userPO.getMa017() != null) {
			leftMenu.setImageBitmap(BitmapFactory
					.decodeByteArray(userPO.getMa017(), 0,
							userPO.getMa017().length));
		} else {
			resideMenu.setTitleImage(R.drawable.weibo_head);
		}
	}

	private void init() {
		leftMenu.setOnClickListener(new TitleBarMenuOnClick());
		rightMenu.setOnClickListener(new TitleBarMenuOnClick());
		mMainActivity.getResideMenu().init(parentView);
		mMainActivity.getResideMenu().addIgnoredView(
				uiHomeLayout);
		homeRadioGroup
				.setOnCheckedChangeListener(new HomeRadioListener());
		initPagerViews();
		initScenes();
	}

	private class TitleBarMenuOnClick implements OnClickListener {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.title_bar_left_menu:
				((MainActivity) mMainActivity).updateHead();
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
				break;
			case R.id.title_bar_right_menu:
				initPopWindow(v);
				break;
			}
		}
	}

	/**
	 * 切换设备和场景
	 * 
	 * @author 曾凡
	 * @time 2015年5月7日 下午5:51:36
	 */
	private class HomeRadioListener implements
			OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group,
				int checkedId) {
			switch (checkedId) {
			case R.id.homeSceneBtn: {
				homeFragment.setBackgroundColor(getResources()
						.getColor(R.color.white));
				mPager.setVisibility(View.GONE);
				homeDeviceList.setVisibility(View.VISIBLE);
			}
				break;
			case R.id.homeDetailBtn: {
				homeFragment
						.setBackgroundResource(R.drawable.ui_home_bg);
				mPager.setVisibility(View.VISIBLE);
				homeDeviceList.setVisibility(View.GONE);
			}
				break;
			}
		}
	}

	private void initPagerViews() {
		sensorList = SensorUtil.getSensorDetails(mMainActivity);
		viewList.clear();

		HomeFragmentUpPager fragmentPager = null;
		/* 第一个是一定要用的，哪怕没有值 */
		if (sensorList == null || sensorList.size() == 0) {
			return;
		}
		/* 如果有传感器就遍历 */
		else {
			uiHomeSensorEmpty.setVisibility(View.GONE);
			for (SensorDetail sensor : sensorList) {
				if (sensor.getCtrl() != null
						&& sensor.getCtrl().getCtrlId() != null) {
					HomeFragmentControlPager fragmentControlPager = HomeFragmentControlPager
							.getInstance();
					fragmentControlPager.init(sensor);
					viewList.add(fragmentControlPager);

				} else {

					fragmentPager = HomeFragmentUpPager
							.getInstance();
					fragmentPager.init(sensor);
					viewList.add(fragmentPager);
				}

			}
		}
		upViewAdapter = new HomeFragmentUpAdapter(
				getChildFragmentManager(), mMainActivity,
				viewList);
		upViewAdapter.notifyDataSetChanged();
		mPager.setAdapter(upViewAdapter);
		mPager.setOnPageChangeListener(new PagerListener());
		mPager.setOffscreenPageLimit(10);
		boolean isCurrentPager = mPager.getCurrentItem() == mCurrentPagerNo;
		if (sensorList != null && sensorList.size() != 0) {
			if (mCurrentPagerNo > (sensorList.size() - 1)) {
				mCurrentPagerNo = sensorList.size() - 1;
			}
			if (!isCurrentPager || mCurrentPagerNo == 0) {
				initSensorTitle(sensorList.get(mCurrentPagerNo));
				mPager.setCurrentItem(mCurrentPagerNo);
			}
		}

	}

	private class PagerListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int position) {
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageSelected(int position) {
			try {
				SensorDetail detail = sensorList.get(position);
				initSensorTitle(detail);
				mCurrentPagerNo = position;
				if (HomePagerUtil.blurBgCache.get(detail
						.getSensorId()) == null) {
					HomePagerUtil.setNormal(
							detail.getSensorId(), mMainActivity);
				} else {
					if (HomePagerUtil.blurBgCache.get(detail
							.getSensorId())) {
						HomePagerUtil.setBlur(
								detail.getSensorId(),
								mMainActivity);
					} else {
						HomePagerUtil.setNormal(
								detail.getSensorId(),
								mMainActivity);
					}
				}

			} catch (Exception e) {
				Ln.e(e, "翻页监听异常");
			}
		}
	}

	private void initSensorTitle(SensorDetail detail) {

		DealWithSensor.initIsOnLine(mMainActivity, detail);
		String sensorName = detail.getName()
				+ "["
				+ (detail.isOnline() ? UIConstant.SENSOR_ONLINE
						: UIConstant.SENSOR_OFFLINE) + "]";
		HomeMenuUtil.setTitleSensorInfo(mMainActivity,
				sensorName, detail.getSensorId());

	}

	private void initScenes() {
		homeDeviceList
				.setOnItemLongClickListener(new ListLongClick());
		homeDeviceList
				.setOnRefreshListener(new OnRefreshListener() {
					@Override
					public void onRefresh() {
						new AsyncRefresh().execute(0);
					}
				});
		homeDeviceList.setCanLoadMore(false);
		homeDeviceList.setFocusable(false);
		SceneUI sceneUI = SceneUtil.getSceneEntry(mMainActivity);
		sceneAdapter = new CustomELVAdapter(mMainActivity,
				mMainActivity, sceneUI.getSceneIds(),
				sceneUI.getGroupname(), sceneUI.getType(),
				sceneUI.getListinfo(), sceneUI.getData());
		homeDeviceList.setAdapter(sceneAdapter);
		homeDeviceList
				.setOnGroupClickListener(new OnGroupClickListener() {
					@Override
					public boolean onGroupClick(
							ExpandableListView parent, View v,
							int groupPosition, long id) {
						return false;
					}
				});
	}

	private class AsyncRefresh extends
			AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... params) {
			MainAcUtil.send2Service(mMainActivity,
					ServerConstant.SH01_06_04_03, 0);
			return 0;
		}

		@Override
		protected void onPostExecute(Integer result) {
			super.onPostExecute(result);
			updateSceneList();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

	}

	/**
	 * 更新场景
	 * 
	 * @author 曾凡
	 * @time 2015年5月12日 下午5:45:35
	 */
	private void updateSceneList() {
		sensorList = SensorUtil.getSensorDetails(mMainActivity);
		SceneUI sceneUI = SceneUtil.getSceneEntry(mMainActivity);
		sceneAdapter.updateInfo(sceneUI.getSceneIds(),
				sceneUI.getGroupname(), sceneUI.getType(),
				sceneUI.getListinfo(), sceneUI.getData());
		sceneAdapter.notifyDataSetChanged();
		homeDeviceList.onRefreshComplete();
	}

	private class ListLongClick implements
			OnItemLongClickListener {
		@Override
		public boolean onItemLongClick(AdapterView<?> parent,
				View view, int position, long id) {
			SceneViewHolder holder = sceneAdapter
					.getSceneViewHolderMap().get(position - 1);
			if (holder == null || holder.getGroupHead() == null) {
				return true;
			}
			if (!getString(R.string.scene_unsign).equals(
					holder.getGroupHead().getText() + "")) {
				holder.getDelete().setVisibility(View.VISIBLE);
			}

			return true;
		}
	}

	private void initPopWindow(View parent) {

		View contentView = LayoutInflater.from(mMainActivity)
				.inflate(R.layout.layout_list, null);

		final PopupWindow popupWindow = new PopupWindow(
				contentView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		popupWindow.setBackgroundDrawable(getResources()
				.getDrawable(R.color.halff_transparent));

		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		final String[] data = mMainActivity.getResources()
				.getStringArray(R.array.right_menu);
		ListView listView = (ListView) contentView
				.findViewById(R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				popupWindow.dismiss();
				clickItem(data, position);
			}
		});

		List<String> arrayList = new ArrayList<String>();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				mMainActivity,
				android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);
		popupWindow
				.setAnimationStyle(android.R.style.Animation_Dialog);
		popupWindow.update();
		popupWindow.showAsDropDown(parent, 0, 10);
	}

	private void clickItem(String[] arr, int positon) {
		if (arr[positon].equals(HomeConstant.RIGHTMENU1)) {
			mMainActivity.toRegSensor();
		}
		if (arr[positon].equals(HomeConstant.RIGHTMENU2)) {
			MainAcUtil.changeFragment(
					mMainActivity.getSupportFragmentManager(),
					SensorOneKeyFragment.getInstance());
		}
		if (arr[positon].equals(HomeConstant.RIGHTMENU3)) {
			Intent intent = new Intent(mMainActivity,
					SceneAddActivity.class);
			startActivity(intent);
		}
	}

	protected abstract void startReceiver();

	protected abstract void endReceiver();

	@Override
	public void onResume() {
		super.onResume();
		Ln.d("onResume");
		startReceiver();
		init();

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

	@Override
	public void onStop() {
		super.onStop();

	}

	public PopProgress getPopProgress() {
		return popProgress;
	}

	public void setPopProgress(PopProgress popProgress) {
		this.popProgress = popProgress;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		try {
			// 参数是固定写法
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

}
