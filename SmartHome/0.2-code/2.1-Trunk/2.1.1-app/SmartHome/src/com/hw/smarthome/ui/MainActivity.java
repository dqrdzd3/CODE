package com.hw.smarthome.ui;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.hw.smarthome.R;
import com.hw.smarthome.login.LoginActivity;
import com.hw.smarthome.login.RegActivity;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.discuss.DiscussThemeFragment;
import com.hw.smarthome.ui.discuss.FeedbackFragment;
import com.hw.smarthome.ui.home.HomeFragment;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.SensorOneKeyFragment;
import com.hw.smarthome.ui.sensor.SensorRegActivity;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.setting.SettingsFragment;
import com.hw.smarthome.ui.setting.sub.AboutFragment;
import com.hw.smarthome.ui.setting.sub.AccountFragment;
import com.hw.smarthome.ui.setting.sub.HelpCenterFragment;
import com.hw.smarthome.ui.solution.SmartSolutionsFragment;
import com.hw.smarthome.ui.solution.constant.SolutionConstants;
import com.hw.smarthome.ui.theme.ThemeFragment;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.ui.util.ResideMenuSm;
import com.hw.smarthome.update.UpdateManager;
import com.hw.util.FileUtils;
import com.hw.util.ImageUtil;
import com.hw.util.Ln;
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

	private ResideMenuSm resideMenu;
	public static MainActivity mContext;
	/* 左侧菜单 */
	private ResideMenuItem itemHome;
	private ResideMenuItem itemTheme;
	private ResideMenuItem itemShopping;
	private ResideMenuItem itemLogout;

	private ResideMenuItem itemDiscussArea;// 讨论区
	private ResideMenuItem itemFeedback;// 留言板
	/* 右侧菜单 */
	private ResideMenuItem itemSensor;
	private ResideMenuItem itemSensorOneKey;
	// private ResideMenuItem itemSettings;
	private ResideMenuItem itemAccountSetting;
	private ResideMenuItem itemhelpCenter;
	private ResideMenuItem itemAbout;

	private ResideMenuItem itemQuestion; // 咨询问题
	private ResideMenuItem itemDIY;// DIY
	private ResideMenuItem itemMonitor; // 免费监测
	private ResideMenuItem itemBusiness; // 我要创业

	private List<ResideMenuItem> leftMenuItems;
	private List<ResideMenuItem> rightMenuItems;
	private FragmentManager fManager = getSupportFragmentManager();
	public List<Bitmap> imageList = new ArrayList<Bitmap>();
	// private List<File> uploadFile = new ArrayList<File>();
	private final int IMAGE_REQUEST_CODE = 0; // 相册请求
	String basePath = RegActivity.LOCAL_PIC_URL
			+ RegActivity.LOCAL_PIC;
	Handler handler;

	public static String KEY_SENSOR_ID = "KEY_SENSOR_ID";
	public static String KEY_WEATHER = "KEY_WEATHER";
	private static final int HANDLE_MESSAGE_IMAGELIST = -201;
	ProgressDialog pDialog;
	String orgPath = "";// 图片原始地址
	private TextView settingTextView;
	private ImageView headImageView;
	private TextView logoutTextView;

	private static final String FONT_LOG_CAT_TAG = "FONT";
	private static final boolean ENABLE_FONT_LOGGING = false;
	private Typeface fontType;

	/**
	 * Called when the activity is first created.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		fontType = Typeface.createFromAsset(getAssets(),
				"fontawesome-webfont.ttf");
		setContentView(R.layout.ui_main);
		mContext = this;
		initWebRes();
		setUpMenu();

		MainAcUtil.changeFragment(fManager, resideMenu,
				HomeFragment.getInstance());
		UpdateManager manager = new UpdateManager(
				MainActivity.this);
		manager.checkUpdate(false);

	}

	private void initWebRes() {
		/* 获取最新的商店地址和所有解决方案 */
		MainAcUtil.send2Service(mContext,
				ServerConstant.SH01_02_03_01, 0);
		MainAcUtil.send2Service(mContext,
				ServerConstant.SH01_02_03_02_01, 0);
		MainAcUtil.send2Service(mContext,
				ServerConstant.SH01_02_03_02_02, 0);
		MainAcUtil.send2Service(mContext,
				ServerConstant.SH01_02_03_02_03, 0);
	}

	public void toRegSensor() {
		Intent intent = new Intent();
		intent.setClass(MainActivity.this,
				SensorRegActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Bundle bundle = getIntent().getExtras();
		if (bundle != null && bundle.containsKey("flag")) {

			String flag = bundle.getString("flag");
			if (flag == "notify") {
				MainAcUtil.changeFragment(fManager, resideMenu,
						HomeFragment.getInstance());
			}
		}
		/* 推送 */
		initPush();
	}

	private void initPush() {
		UserPO user = DealWithAccount
				.getUser(getApplicationContext());
		if (user != null) {
			String phone = user.getMa006();
			if (!TextUtils.isEmpty(phone)) {

				com.hanwei.androidpn.ServiceManager serviceManager = new com.hanwei.androidpn.ServiceManager(
						this);
				serviceManager.setUserName(phone);
				serviceManager.setPassword("123456");
				serviceManager
						.setXmppHost(ServerConstant.PUSH_URI);
				serviceManager
						.setNotificationIcon(R.drawable.ic_launcher);
				serviceManager.startService();
			}
		}
	}

	public void updateHead() {
		UserPO user = DealWithAccount
				.getUser(getApplicationContext());
		if (user.getMa017() != null) {

			resideMenu.setTitleImage(BitmapFactory
					.decodeByteArray(user.getMa017(), 0,
							user.getMa017().length));
		} else {
			resideMenu.setTitleImage(R.drawable.weibo_head);
		}
	}

	private void setUpMenu() {
		UserPO user = DealWithAccount
				.getUser(getApplicationContext());
		leftMenuItems = new ArrayList<ResideMenuItem>();
		rightMenuItems = new ArrayList<ResideMenuItem>();

		// attach to current activity;

		if (user != null) {
			resideMenu = new ResideMenuSm(this, user.getMa008());
			// resideMenu.setBackground(R.drawable.qq_com_bg_pic);
			resideMenu.attachToActivity(this);

			resideMenu.setTel(user.getMa006());
			updateHead();

		} else {
			resideMenu = new ResideMenuSm(this, "");
			resideMenu.setBackground(R.drawable.qq_com_bg_pic);
			resideMenu.attachToActivity(this);
			resideMenu.setTitleImage(R.drawable.weibo_head);
			resideMenu.setTel("");
		}

		settingTextView = resideMenu.getSettingTextView();

		settingTextView.setOnClickListener(this);

		headImageView = resideMenu.getProfileImage();
		headImageView.setOnClickListener(this);

		logoutTextView = resideMenu.getLogoutTextView();
		logoutTextView.setOnClickListener(this);
		// create menu items;
		itemHome = new ResideMenuItem(this, R.drawable.u1_home,
				UIConstant.HOME);
		// create menu items;

		itemTheme = new ResideMenuItem(this,
				R.drawable.icon_settings, UIConstant.THEME);
		// itemLogout = new ResideMenuItem(this,
		// R.drawable.u_logout, UIConstant.LOGOUT);
		itemDiscussArea = new ResideMenuItem(this,
				R.drawable.u_question, UIConstant.DISCUSSAREA);
		itemFeedback = new ResideMenuItem(this,
				R.drawable.u_feedback, UIConstant.FEEDBACK);
		itemShopping = new ResideMenuItem(this,
				R.drawable.u1_shop, UIConstant.SHOPPING);

		itemQuestion = new ResideMenuItem(this,
				R.drawable.u_question, UIConstant.QUESTIONS);
		itemDIY = new ResideMenuItem(this, R.drawable.u_diy,
				UIConstant.DIY);
		itemMonitor = new ResideMenuItem(this,
				R.drawable.u_monitor, UIConstant.MONITOR);
		itemBusiness = new ResideMenuItem(this,
				R.drawable.u_business, UIConstant.BUSINESS);

		itemSensor = new ResideMenuItem(this,
				R.drawable.u_setting, UIConstant.SENSOR);
		itemSensorOneKey = new ResideMenuItem(this,
				R.drawable.u_setting2, UIConstant.SENSOR_ONE_KEY);

		itemAccountSetting = new ResideMenuItem(this,
				R.drawable.u_user, UIConstant.ACCOUNT_SETTING);
		itemhelpCenter = new ResideMenuItem(this,
				R.drawable.u_help, UIConstant.HELP_CENTER);
		itemAbout = new ResideMenuItem(this, R.drawable.u_about,
				UIConstant.ABOUT);

		itemHome.setOnClickListener(this);
		itemSensor.setOnClickListener(this);
		itemSensorOneKey.setOnClickListener(this);
		// itemSettings.setOnClickListener(this);
		itemAccountSetting.setOnClickListener(this);
		itemhelpCenter.setOnClickListener(this);
		itemAbout.setOnClickListener(this);
		itemTheme.setOnClickListener(this);
		itemShopping.setOnClickListener(this);
		itemQuestion.setOnClickListener(this);
		itemDIY.setOnClickListener(this);
		itemMonitor.setOnClickListener(this);
		itemBusiness.setOnClickListener(this);

		itemDiscussArea.setOnClickListener(this);
		itemFeedback.setOnClickListener(this);
		leftMenuItems.add(itemHome);
//		leftMenuItems.add(itemDiscussArea);
		leftMenuItems.add(itemDIY);
		// leftMenuItems.add(itemMonitor);

		leftMenuItems.add(itemShopping); // FIXME =需要在加上商城
		leftMenuItems.add(itemBusiness);
		/* 左右侧菜单内的控件 */
		resideMenu.setMenuItems(leftMenuItems,
				ResideMenu.DIRECTION_LEFT);
		resideMenu.setMenuItems(rightMenuItems,
				ResideMenu.DIRECTION_RIGHT);

	}

	private long mExitTime;

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getRepeatCount() == 0) {

			int fragmentStackCount = getSupportFragmentManager()
					.getBackStackEntryCount();
			if (!resideMenu.isOpened()) {
				if (fragmentStackCount > 0) {
					getSupportFragmentManager().popBackStack();
				} else if (((Fragment) (getSupportFragmentManager()
						.findFragmentById(R.id.main_fragment))) instanceof HomeFragment
						&& (System.currentTimeMillis() - mExitTime) > 2000) {
					Toast.makeText(this, "再按一次退出程序",
							Toast.LENGTH_SHORT).show();
					mExitTime = System.currentTimeMillis();

				} else if (((Fragment) (getSupportFragmentManager()
						.findFragmentById(R.id.main_fragment))) instanceof HomeFragment
						&& (System.currentTimeMillis() - mExitTime) < 2000) {
					// System.exit(0);
					finish();
				} else {
					List<SensorDetail> sensorList = SensorUtil
							.getSensorDetails(this);
					if (sensorList == null
							|| sensorList.size() == 0) {
						HomeUtil.quit(mContext);
						Intent startMain = new Intent(
								Intent.ACTION_MAIN);
						startMain.setClass(mContext,
								LoginActivity.class);
						startActivity(startMain);
						finish();
					} else {
						MainAcUtil.changeFragment(fManager,
								resideMenu,
								HomeFragment.getInstance());
					}
				}
			}
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

		if (view == settingTextView) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					SettingsFragment.getInstance());
		}
		if (view == headImageView) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					AccountFragment.getInstance());
		}
		if (view == logoutTextView) {
			logoutDialog();
		}
		if (view == itemHome) {

			List<SensorDetail> sensorList = SensorUtil
					.getSensorDetails(this);
			if (sensorList == null || sensorList.size() == 0) {
				HomeUtil.quit(mContext);
				Intent startMain = new Intent(Intent.ACTION_MAIN);
				startMain
						.setClass(mContext, LoginActivity.class);
				startActivity(startMain);
				finish();
			} else {
				MainAcUtil.changeFragment(fManager, resideMenu,
						HomeFragment.getInstance());
			}
		} else if (view == itemLogout) {
			logoutDialog();
		} else if (view == itemSensor) {
			toRegSensor();
		} else if (view == itemSensorOneKey) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					SensorOneKeyFragment.getInstance());
		}
		if (view == itemTheme) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					ThemeFragment.getInstance());
		} else if (view == itemDiscussArea) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					DiscussThemeFragment.getInstance());
		} else if (view == itemFeedback) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					FeedbackFragment.getInstance());
		} else if (view == itemShopping) {
			MainAcUtil
					.changeFragment(
							fManager,
							resideMenu,
							SmartSolutionsFragment
									.getInstance(SolutionConstants.SHOPPING));
		} else if (view == itemAccountSetting) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					AccountFragment.getInstance());
		} else if (view == itemhelpCenter) {
			HelpCenterFragment helpFragment = HelpCenterFragment
					.getInstance();
			String url = ServerConstant.SERVER_BASE_URI
					+ ServerConstant.SH01_05_02_02;
			helpFragment.setUrl(url);
			MainAcUtil.changeFragment(fManager, resideMenu,
					helpFragment);
		} else if (view == itemAbout) {
			MainAcUtil.changeFragment(fManager, resideMenu,
					AboutFragment.getInstance());
		} else if (view == itemQuestion) {
			// toSolution(2);
			MainAcUtil
					.changeFragment(
							fManager,
							resideMenu,
							SmartSolutionsFragment
									.getInstance(SolutionConstants.QUESTION));
		} else if (view == itemDIY) {
			// toSolution(3);
			MainAcUtil.changeFragment(fManager, resideMenu,
					SmartSolutionsFragment
							.getInstance(SolutionConstants.DIY));
		} else if (view == itemMonitor) {
			// toSolution(4);
			MainAcUtil
					.changeFragment(
							fManager,
							resideMenu,
							SmartSolutionsFragment
									.getInstance(SolutionConstants.MONITOR));
		} else if (view == itemBusiness) {
			// toSolution(6);
			MainAcUtil
					.changeFragment(
							fManager,
							resideMenu,
							SmartSolutionsFragment
									.getInstance(SolutionConstants.BUSINESS));
		}

		resideMenu.closeMenu();
	}

	/**
	 * 退出对话框
	 * 
	 * @author 曾凡
	 * @time 2014年11月3日 下午3:18:01
	 */
	protected void logoutDialog() {
		new SweetAlertDialog(mContext,
				SweetAlertDialog.WARNING_TYPE)
				.setTitleText("确定要退出吗?")
				.setCancelText("取消")
				.setConfirmText("确认")
				.showCancelButton(true)
				.setCancelClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();

							}
						})
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
								HomeUtil.quit(MainActivity.this);
								Intent startMain = new Intent(
										Intent.ACTION_MAIN);
								startMain.setClass(mContext,
										LoginActivity.class);
								startActivity(startMain);
								mContext.finish();
							}
						}).show();
	}

	public ResideMenuSm getResideMenu() {
		return resideMenu;
	}

	String takePhotoName = "";

	@Override
	protected void onActivityResult(int requestCode,
			int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		// 外界的程序访问ContentProvider所提供数据 可以通过ContentResolver接口
		if (resultCode == -1) {

			ContentResolver resolver = getContentResolver();
			if (requestCode == 65537) {
				takePhotoName = AccountFragment.getInstance()
						.getFilename();
				File bb = new File(basePath, takePhotoName);
				if (bb.exists()) {
					DealWithAccount.saveMypic(this, basePath
							+ takePhotoName);
					AccountFragment.getInstance().setFile(bb);
					// Bitmap bm = BitmapFactory.decodeFile(Uri
					// .fromFile(bb).getPath());
					Bitmap bm = decodeFile(bb);
					File f = null;

					String fileName = getPhotoFileName();

					f = FileUtils.createFile(basePath, fileName);

					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					bm.compress(CompressFormat.PNG,
							0 /* ignored for PNG */, bos);
					byte[] bitmapdata = bos.toByteArray();

					// write the bytes in file
					FileOutputStream fos;
					try {
						fos = new FileOutputStream(f);
						fos.write(bitmapdata);

						DealWithAccount.saveMypic(this, basePath
								+ fileName);
						AccountFragment.getInstance().setFile(f);
						bb.delete();
						fos.flush();
						fos.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					AccountFragment.getInstance().headImageView
							.setImageBitmap(bm);

				}

				// reciveImage(bb);
				return;
			}
			if (requestCode == IMAGE_REQUEST_CODE
					|| requestCode == 65536) {
				try {
					if (data == null)
						return;

					Uri originalUri = data.getData(); // 获得图片的uri

					ContentResolver cr = this
							.getContentResolver();

					try {

						BitmapFactory.Options opt = new BitmapFactory.Options();

						opt.inPreferredConfig = Bitmap.Config.RGB_565;

						opt.inJustDecodeBounds = true;
						opt.inPurgeable = true;

						opt.inInputShareable = true;

						Bitmap bmp = BitmapFactory
								.decodeStream(cr
										.openInputStream(originalUri));

						bmp = ImageUtil.scaleImg(bmp, 90, 90);

						AccountFragment.getInstance().headImageView
								.setImageBitmap(bmp);
						File f = null;

						String fileName = getPhotoFileName();

						f = FileUtils.createFile(basePath,
								fileName);

						ByteArrayOutputStream bos = new ByteArrayOutputStream();
						bmp.compress(CompressFormat.PNG,
								0 /* ignored for PNG */, bos);
						byte[] bitmapdata = bos.toByteArray();

						// write the bytes in file
						FileOutputStream fos = new FileOutputStream(
								f);
						fos.write(bitmapdata);

						DealWithAccount.saveMypic(this, basePath
								+ fileName);
						AccountFragment.getInstance().setFile(f);
						// uploadFile.add(f);
						fos.flush();
						fos.close();

					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (Exception e) {

					Log.e("TAG-->Error", e.toString());

				}
			}
		}
	}

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
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
