package com.hw.weidou.ui;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.hw.util.ImageUtil;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;
import com.hw.util.WindowTools;
import com.hw.weidou.R;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;
import com.hw.weidou.service.WeidouService;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.HomeFragment;
import com.hw.weidou.ui.setting.AlcoholSettingActivity;
import com.hw.weidou.ui.util.MainAcUtil;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

/**
 * MainUI
 * 
 * @author 曾凡
 * @time 2014年6月9日 上午10:59:14
 */
public class MainActivity extends FragmentActivity implements
		View.OnClickListener {

	private ResideMenu resideMenu;
	public static MainActivity mContext;
	/* 左侧菜单 */
	private ResideMenuItem itemHome;
	private ResideMenuItem itemTheme;
	private ResideMenuItem itemLogout;

	private ResideMenuItem itemDiscussArea;// 讨论区
	private ResideMenuItem itemFeedback;// 留言板
	/* 右侧菜单 */
	private ResideMenuItem itemSensor;
	private ResideMenuItem itemSettings;

	private List<ResideMenuItem> leftMenuItems;
	private List<ResideMenuItem> rightMenuItems;
	private FragmentManager fManager = getSupportFragmentManager();
	public List<Bitmap> imageList = new ArrayList<Bitmap>();
	// private List<File> uploadFile = new ArrayList<File>();
	private final int IMAGE_REQUEST_CODE = 0; // 相册请求
	private final int CAMERA_REQUEST_CODE = 1; // 相机请求
	Handler handler;

	private static final int HANDLE_MESSAGE_IMAGELIST = -201;
	ProgressDialog pDialog;
	String orgPath = "";// 图片原始地址

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		/* 禁止休眠 */
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.ui_main);
		mContext = this;

		PreferenceUtil.saveIsShowGuide(mContext, false);
		setUpMenu();
		WindowTools.initSystemBar(getWindow());

		MainAcUtil.changeFragment(fManager, resideMenu,
				HomeFragment.getInstance());

	}

	private PowerManager.WakeLock wl = null;

	@Override
	protected void onResume() {
		super.onResume();
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		if (wl == null) {
			wl = pm.newWakeLock(
					PowerManager.SCREEN_DIM_WAKE_LOCK, "MyTag");
		} else {
			wl.release();
		}
		wl.acquire();
	}

	@Override
	protected void onPause() {
		super.onPause();
		wl.release();
		wl = null;
	}

	private void setUpMenu() {

		leftMenuItems = new ArrayList<ResideMenuItem>();
		rightMenuItems = new ArrayList<ResideMenuItem>();

		// attach to current activity;
		resideMenu = new ResideMenu(this);
		resideMenu.setBackground(R.drawable.ui_background);
		resideMenu.attachToActivity(this);

		// create menu items;
		itemHome = new ResideMenuItem(this,
				R.drawable.icon_home, UIConstant.HOME);
		// create menu items;

		itemTheme = new ResideMenuItem(this,
				R.drawable.icon_settings, UIConstant.THEME);
		itemLogout = new ResideMenuItem(this,
				R.drawable.icon_logout, UIConstant.LOGOUT);
		itemDiscussArea = new ResideMenuItem(this,
				R.drawable.icon_topic, UIConstant.DISCUSSAREA);
		itemFeedback = new ResideMenuItem(this,
				R.drawable.icon_feedback, UIConstant.FEEDBACK);

		itemSensor = new ResideMenuItem(this,
				R.drawable.icon_settings, UIConstant.TEST);
		itemSettings = new ResideMenuItem(this,
				R.drawable.icon_settings_center,
				UIConstant.SETTINGS);

		itemHome.setOnClickListener(this);
		itemSensor.setOnClickListener(this);
		itemSettings.setOnClickListener(this);
		itemTheme.setOnClickListener(this);
		itemLogout.setOnClickListener(this);
		itemDiscussArea.setOnClickListener(this);
		itemFeedback.setOnClickListener(this);
		leftMenuItems.add(itemHome);
		// leftMenuItems.add(itemDiscussArea);
		// leftMenuItems.add(itemFeedback);
		// leftMenuItems.add(itemLogout);
		leftMenuItems.add(itemSettings);
		// 暂时不去实现主题
		// leftMenuItems.add(itemTheme);
		// rightMenuItems.add(itemSensor);
		// rightMenuItems.add(itemSettings);
		/* 左右侧菜单内的控件 */
		resideMenu.setMenuItems(leftMenuItems,
				ResideMenu.DIRECTION_LEFT);
		MainAcUtil.settingsOnClick(fManager, mContext,
				leftMenuItems, resideMenu);
		resideMenu.setMenuItems(rightMenuItems,
				ResideMenu.DIRECTION_RIGHT);

	}

	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {
			finish();
			return false;
		}
		return super.onKeyDown(keyCode, event);
	}

	/**
	 * 处理左右拖动的事件
	 * 
	 * @author 曾凡
	 * @param ev
	 * @return
	 * @time 2014年6月9日 上午11:44:31
	 */
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		boolean res = true;
		try {
			res = resideMenu.onInterceptTouchEvent(ev)
					|| super.dispatchTouchEvent(ev);
		} catch (Exception e) {
			Ln.e(e, "处理左右拖动事件异常");
		} finally {
			return res;
		}
	}

	/**
	 * 左右侧菜单的点击事件
	 * 
	 * @author 曾凡
	 * @param view
	 * @time 2014年6月9日 上午11:01:40
	 */
	@Override
	public void onClick(View view) {

		if (view == itemHome) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					HomeFragment.getInstance());
		} else if (view == itemSettings) {
			// MainAcUtil.settingsOnClick(fManager, mContext,
			// leftMenuItems, resideMenu);
			Intent intent = new Intent(mContext,
					AlcoholSettingActivity.class);
			startActivity(intent);
			return;
		}

		resideMenu.closeMenu();
	}

	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	String takePhotoName = "";

	/**
	 * 
	 * 
	 * 函 数 名：reciveImage 功能描述：(显示从相册或相机拍照获取的图片) 输入参数：(按照参数定义顺序,
	 * 
	 * @param后面空格后跟着参数的变量名字（不是类型）， 空格后跟着对该参数的描述.) 返 回 值： (返回为空（void）的构造函数或者函数
	 * @return可以省略; 如果返回值就是输入参数， 必须用与输入参数的@param相同的描述信息; 必要的时候注明特殊条件写的返回值) 异 常：
	 *              (按照异常名字的字母顺序) 创建人：lijing 创建时间：2013-11-13 上午10:10:15 修改人：
	 *              修改时间： 修改原因描述：
	 */
	private void reciveImage(final File file) {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Bitmap bm = decodeFile(file);
					imageList.add(bm);
					Message msg = new Message();
					msg.what = HANDLE_MESSAGE_IMAGELIST;
					handler.sendMessage(msg);

				} catch (Exception e) {

				}
			}
		});
		thread.start();
	}

	/**
	 * 用时间戳生成照片名称
	 * 
	 * @return
	 */
	private String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		int n = (int) (Math.random() * 1000);
		return dateFormat.format(date) + "_" + n + ".jpg";
	}

	public static Bitmap decodeFile(File f) {

		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;

		int temp = 1;

		long fileSize = f.length();

		if (fileSize > 4000000) {
			temp = 20;
		} else if (fileSize > 1000000) {
			temp = 10;
		} else if (fileSize > 100000) {
			temp = (int) (fileSize / (1024 * 100));
		} else {
			temp = 1;
		}
		opts.inSampleSize = temp;
		opts.inJustDecodeBounds = false;
		opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
		try {
			return ImageUtil.scaleImg(BitmapFactory.decodeFile(
					f.getAbsolutePath(), opts), 90, 90);
		} catch (OutOfMemoryError err) {

		}

		return null;

	}

	@Override
	protected void onStart() {
		super.onStart();
		if (!isServiceWorked()) {
			startService(new Intent(this, WeidouService.class));
		}
	}

	public boolean isServiceWorked() {
		ActivityManager myManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager
				.getRunningServices(30);
		for (int i = 0; i < runningService.size(); i++) {
			if (runningService.get(i).service.getClassName()
					.toString()
					.equals(WeidouService.class.getName())) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected void onStop() {
		super.onStop();
		stopService(new Intent(this, WeidouService.class));
	}

}
