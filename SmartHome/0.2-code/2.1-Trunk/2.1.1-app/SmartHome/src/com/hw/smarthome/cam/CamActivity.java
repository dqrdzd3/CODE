package com.hw.smarthome.cam;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.json.JSONException;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Size;
import android.hardware.Sensor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.InputFilter;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetInputDialog;

import com.baidu.location.LocationClient;
import com.hw.smarthome.R;
import com.hw.smarthome.cam.helper.CameraHelper;
import com.hw.smarthome.cam.pager.CamPagerFragment;
import com.hw.smarthome.cam.pager.adapter.CamAdapter;
import com.hw.smarthome.cam.util.CamUtils;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.weather.po.WeatherPo;
import com.hw.smarthome.ui.weather.util.CityUtils;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;
import com.hw.util.UIUtil;
import com.hw.util.application.SHApplication;
import com.hw.util.loc.LocPo;
import com.hw.util.loc.LocUtils;

public class CamActivity extends FragmentActivity {
	private Camera mCamera = null;
	private CameraPreview mPreview;
	private int deviceHeight;

	private ViewPager mCamPager;
	private List<Fragment> mCamTypeList = new ArrayList<Fragment>();
	private CamAdapter mCamAdapter;
	private String mSensorId;
	private LocationClient mLocationClient;
	private AutoFocusManager autoFocusManager;
	private int cameraCount = 0;

	private View pic;
	private Button camRingButton;
	private Button camLocButton;
	public ImageView camToggleButton;
	public ImageView camFlashButton;
	private TextView mLocTextView;

	private Activity mActivity;
	public static LocPo mLocPo;
	private SHApplication application;
	public View camTitle, camBottom, camCamControl, camLogo;
	private int cameraIndex = -1;
	private Button backButton;
	/** 是否完成拍照动作：待分享，待删除 */
	private boolean hasTakePhoto = false;
	private Button camPicBtn;
	private String currentPicPath = "";
	private TextView camDeviceName;

	public static WeatherPo weather;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Intent intent = getIntent();
		Bundle pass = intent.getExtras();
		mSensorId = pass.getString(MainActivity.KEY_SENSOR_ID);
		weather = (WeatherPo) pass
				.getSerializable(MainActivity.KEY_WEATHER);
		setContentView(R.layout.cam_activity);

		// Selecting the resolution of the Android device so we can create a
		// proportional preview
		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay();

		mCamPager = (ViewPager) findViewById(R.id.camPager);
		mCamPager.setOnPageChangeListener(new PagerListener());

		mCamAdapter = new CamAdapter(
				getSupportFragmentManager(), this, mCamTypeList);
		mCamPager.setAdapter(mCamAdapter);

		application = (SHApplication) getApplication();
		mLocationClient = application.mLocationClient;

		initReceiver();

	}

	private void initCamera() {
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		cameraCount = Camera.getNumberOfCameras();

		if (cameraCount > 0) {
			cameraIndex = 0;
			createCamera();
		}
		// get cameras number
		// for (int camIdx = 0; camIdx < cameraCount; camIdx++) {
		// Camera.getCameraInfo(camIdx, cameraInfo);
		// // get camerainfo
		// if (cameraInfo.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
		// // 代表摄像头的方位，目前有定义值两个分别为CAMERA_FACING_FRONT前置和CAMERA_FACING_BACK后置
		// try {
		// mCamera = Camera.open(camIdx);
		// } catch (RuntimeException e) {
		// e.printStackTrace();
		// }
		// }
		// }
		//
		// mCamera.unlock();

	}

	private void initViews() {
		pic = findViewById(R.id.take_pic_layout);
		camTitle = findViewById(R.id.cam_title);
		camDeviceName = (TextView) findViewById(R.id.camDeviceName);
		SensorDetail detail = HomeUtil.getSensorDetail(
				mActivity, mSensorId);
		if (detail == null) {
			camDeviceName.setText(R.string.cam_device_not_link);
		} else {
			camDeviceName.setText(detail.getName());
		}

		camBottom = findViewById(R.id.cam_bottom);
		camCamControl = findViewById(R.id.camCamControl);
		camLogo = findViewById(R.id.camLogo);

		backButton = (Button) findViewById(R.id.camBackBtn);
		backButton.setOnClickListener(new CamButtonLisener());
		// 拍照
		camRingButton = (Button) findViewById(R.id.camRingBtn);
		camRingButton.setOnClickListener(new CamButtonLisener());
		// 定位
		camLocButton = (Button) findViewById(R.id.camLocBtn);
		camLocButton.setOnClickListener(new CamButtonLisener());
		// 相册
		camPicBtn = (Button) findViewById(R.id.camPicBtn);
		camPicBtn.setOnClickListener(new CamButtonLisener());
		// 转换摄像头
		camToggleButton = (ImageView) findViewById(R.id.camToggleBtn);
		if (cameraCount < 2) {
			camToggleButton.setVisibility(View.GONE);
		} else {
			camToggleButton.setVisibility(View.VISIBLE);
			camToggleButton
					.setOnClickListener(new CamButtonLisener());
		}

		// 闪光灯
		camFlashButton = (ImageView) findViewById(R.id.camFlashBtn);
		camFlashButton.setVisibility(View.VISIBLE);
		camFlashButton
				.setOnClickListener(new CamButtonLisener());

	}

	private class CamButtonLisener implements OnClickListener {

		@Override
		public void onClick(final View v) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if (v == backButton) {
						finish();
					}
					if (v == camRingButton) {
						if (!hasTakePhoto) {
							camPicBtn
									.setBackgroundResource(R.drawable.cam_retake_selector);
							camRingButton
									.setBackgroundResource(R.drawable.cam_share);

							camLocButton
									.setBackgroundResource(R.drawable.cam_delete_selector);
							hideViews();
							currentPicPath = ServerConstant.SHOT_CAM_LOC;
							CamUtils.shot(mActivity, mCamera,
									cameraIndex, currentPicPath);

							hasTakePhoto = true;
						} else {
							camRingButton
									.setBackgroundResource(R.drawable.cam_ring_selector);
							camPicBtn
									.setBackgroundResource(R.drawable.cam_pic_selector);
							camLocButton
									.setBackgroundResource(R.drawable.cam_loc_selector);
							CamUtils.share(mActivity, pic,
									currentPicPath);

							mCamera.startPreview();
							hasTakePhoto = false;
						}

					}
					// 相册和重新拍照
					if (v == camPicBtn) {
						if (!hasTakePhoto) {
							Intent intent = new Intent();
							intent.setData(Uri
									.fromFile(new File(
											currentPicPath)));
							intent.setType("image/*");
							intent.setAction(Intent.ACTION_GET_CONTENT);
							startActivity(intent);
						}
						camRingButton
								.setBackgroundResource(R.drawable.cam_ring_selector);
						camPicBtn
								.setBackgroundResource(R.drawable.cam_pic_selector);
						camLocButton
								.setBackgroundResource(R.drawable.cam_loc_selector);
						mCamera.startPreview();
						hasTakePhoto = false;

					}
					// 定位和删除
					if (v == camLocButton) {
						if (!hasTakePhoto) {
							loc();
						} else {
							camRingButton
									.setBackgroundResource(R.drawable.cam_ring_selector);
							camPicBtn
									.setBackgroundResource(R.drawable.cam_pic_selector);
							camLocButton
									.setBackgroundResource(R.drawable.cam_loc_selector);
							File discardedPhoto = new File(
									currentPicPath);
							discardedPhoto.delete();

							mCamera.startPreview();
							hasTakePhoto = false;
						}

					}
					if (v == camToggleButton) {
						CamUtils.toggle(mActivity);
						cameraIndex = (cameraIndex + 1)
								% cameraCount;
						releaseCamera();
						mCamera = Camera.open(cameraIndex);
						setupCamera();
						mPreview.switchCamera(mCamera);
						mCamera.startPreview();
					}
					if (v == camFlashButton) {
						// CamUtils.fleshLight(mActivity, mCamera);
						mPreview.setFlashLight(mActivity,
								mCamera);

						if (PreferenceUtil
								.getCameraLight(mActivity)) {
							camFlashButton
									.setImageResource(R.drawable.cam_flash_on);
						} else {
							camFlashButton
									.setImageResource(R.drawable.cam_flash_off);
						}
					}
				}
			});
		}
	}

	public void hideViews() {
		camTitle.setVisibility(View.GONE);
		camBottom.setVisibility(View.GONE);
		camCamControl.setVisibility(View.GONE);
		camLogo.setVisibility(View.VISIBLE);
	}

	/**
	 * 还原布局
	 * 
	 * @author 曾凡
	 * @param context
	 * @time 2015年3月31日 上午11:14:53
	 */
	public void recoverViews() {
		camLogo.setVisibility(View.GONE);

		PreferenceUtil.saveCameraLight(mActivity, false);
		camCamControl.setVisibility(View.VISIBLE);
		camFlashButton.setImageResource(R.drawable.cam_flash_on);

		camTitle.setVisibility(View.VISIBLE);
		camBottom.setVisibility(View.VISIBLE);
	}

	/**
	 * 吐槽功能
	 * 
	 * @author 曾凡
	 * @param view
	 * @time 2015年3月27日 上午10:50:43
	 */
	public void camTalkListener(View view) {
		String strTmp = "点击Button03";
		inputDialog();

	}

	private void inputDialog() {
		SweetInputDialog dialog = new SweetInputDialog(mActivity)
				.setTitleText(getString(R.string.cam_talk_title))
				.setCancelText(
						getString(R.string.cam_talk_cancel))
				.setConfirmText(
						getString(R.string.cam_talk_confirm))
				.showCancelButton(true)
				.setCancelClickListener(
						new SweetInputDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetInputDialog sDialog) {
								sDialog.dismiss();
							}
						});

		dialog.show();

		dialog.editable(true);
		dialog.setInputEditText((mLocPo != null) ? mLocPo
				.getCity() : "");
		final EditText cEditText = dialog.getInputEditText();
		cEditText
				.setFilters(new InputFilter[] { new InputFilter.LengthFilter(
						10) });
		dialog.setConfirmClickListener(new SweetInputDialog.OnSweetClickListener() {
			@Override
			public void onClick(SweetInputDialog sDialog) {
				sDialog.dismiss();
				if (mLocPo == null) {
					mLocPo = new LocPo();
				}
				String text = (cEditText.getText() + "").trim();
				if ("".equals(text) || text == null) {
					loc();
				} else {
					mLocPo.setCity(text);
					mLocPo.setTalk(true);
				}
			}
		});

	}

	/**
	 * 定位
	 * 
	 * @author 曾凡
	 * @time 2015年3月23日 下午5:55:21
	 */
	public void loc() {

		String urlj = ServerConstant.LOC_INFO + "&location="
				+ application.locInfo;
		FinalHttp fhh = new FinalHttp();
		fhh.get(urlj, new AjaxCallBack<Object>() {
			@Override
			public void onSuccess(Object t) {
				try {
					mLocPo = LocUtils.parserLocPo(t.toString());
					mLocationClient.stop();
				} catch (JSONException e) {
					Ln.e(e, "解析地理信息异常");

					FinalHttp fhh = new FinalHttp();
					fhh.get(ServerConstant.LOC_BY_IP_INFO,
							new AjaxCallBack<Object>() {
								@Override
								public void onSuccess(Object t) {
									try {
										mLocPo = LocUtils.parserIpLocPo(t
												.toString());
										mLocationClient.stop();
									} catch (JSONException e) {
										Ln.e(e, "解析地理信息异常");
									} finally {

									}
								}
							});

				}
			}
		});

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (!checkCameraHardware(this)) {
			Toast.makeText(this, R.string.cam_no_cam,
					Toast.LENGTH_SHORT).show();
			finish();
		}

		mLocationClient.start();
		if (checkCameraHardware(mActivity)) {
			initCamera();
		} else {
			Toast.makeText(mActivity, "您没有摄像头或摄像头有故障",
					Toast.LENGTH_LONG).show();
		}
		loc();
		initViews();

		// createCamera();

		updatePagers();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		mLocationClient.stop();
	}

	FrameLayout preview;

	private void createCamera() {
		int[] wh = UIUtil.getScreenMetrics(this);
		deviceHeight = wh[1];
		// Create an instance of Camera
		mCamera = getCameraInstance();
		autoFocusManager = new AutoFocusManager(this, mCamera);
		preview = (FrameLayout) findViewById(R.id.camera_preview);

		Size preMax = setupCamera();
		// Create our Preview view and set it as the content of our activity.

		// mPreview = new CameraPreview(this, mCamera, preMax);
		mPreview = new CameraPreview(this);
		mPreview.setCamera(mCamera);
		// Calculating the width of the preview so it is proportional.

		// Resizing the LinearLayout so we can make a proportional preview. This
		// approach is not 100% perfect because on devices with a really small
		// screen the the image will still be distorted - there is place for
		// improvment.
		FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
				wh[0], wh[1]);
		preview.setLayoutParams(layoutParams);

		// Adding the camera preview after the FrameLayout and before the button
		// as a separated element.
		preview.addView(mPreview, 0);
	}

	// 暂不用
	private Size setupCamera() {
		Size picMax = mCamera.new Size(1000, 1600);
		try {
			// Setting the right parameters in the camera
			Camera.Parameters params = mCamera.getParameters();

			CameraHelper helper = new CameraHelper(mCamera);
			List<Size> previewSizes = helper
					.getSupportedPreviewSizes();
			Size preMax = getMaxSize(previewSizes);

			int temp = 0;

			List<Size> pictureSizes = helper
					.getSupportedPictureSizes();
			picMax = getMaxSize(pictureSizes);

			// if (preMax.width > preMax.height) {
			// temp = preMax.width;
			// preMax.width = preMax.height;
			// preMax.height = temp;
			// }
			// if (picMax.width > picMax.height) {
			// temp = picMax.width;
			// picMax.width = picMax.height;
			// picMax.height = temp;
			// }

			// float widthFloat = (float) (deviceHeight) * 4 / 3;
			// int width = Math.round(widthFloat);

			params.setPreviewSize(preMax.width, preMax.height);

			// params.setPictureSize(width, deviceHeight);
			params.setPictureSize(picMax.width, picMax.height);
			params.setPictureFormat(ImageFormat.JPEG);
			// params.setJpegQuality(100);
			// if (params.isSmoothZoomSupported()) {
			// params.setZoom(0);
			// }
			// params.setRotation(90);

			//
			mCamera.setParameters(params);
			mCamera.setDisplayOrientation(90);
		} catch (Exception e) {
			Ln.w("Could not set device specifics");
		}
		return picMax;
	}

	private Size getMaxSize(List<Size> sizes) {
		Size temp = null;
//		int i = 0;
//		int count = 0;
		if (sizes != null && sizes.size() > 0) {
			for (Size size : sizes) {
//				i++;
				if (temp == null) {
					temp = size;
				} else {
					if ((size.height * size.width) > (temp.height * temp.width)) {
						temp = size;
//						count = i;
					}
				}
			}

		} else {
			return mCamera.new Size(1000, 1600);
		}
//		temp = sizes.get(i-3); //最大分辨率-3
		return temp;
	}

	private void updatePagers() {
		mCamTypeList.clear();
		CamPagerFragment fragment = null;
		for (int i = 1; i < 6; i++) {
			fragment = CamPagerFragment.getInstance();
			fragment.initFragment(i, mSensorId);
			mCamTypeList.add(fragment);
		}
		mCamAdapter.notifyDataSetChanged();
	}

	private class PagerListener implements OnPageChangeListener {
		@Override
		public void onPageScrollStateChanged(int position) {
			Ln.d("onPageScrollStateChanged position:" + position);

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			Ln.d("onPageScrolled arg0:" + arg0 + " arg1:" + arg1
					+ " arg2:" + arg2);
		}

		@Override
		public void onPageSelected(int position) {
			Ln.d("onPageSelected position:" + position);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		// release the camera immediately on pause event
		releaseCamera();

		FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
		preview.removeViewAt(0);
	}

	private void releaseCamera() {
		if (mCamera != null) {
			mCamera.release(); // release the camera for other applications
			mCamera = null;
		}
	}

	/** Check if this device has a camera */
	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	private boolean checkSDCard() {
		boolean state = false;

		String sd = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(sd)) {
			state = true;
		}

		return state;
	}

	/**
	 * A safe way to get an instance of the Camera object.
	 */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			// attempt to get a Camera instance
			c = Camera.open();
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}

		// returns null if camera is unavailable
		return c;
	}

	/**
	 * STUFF THAT WE DON'T NEED BUT MUST BE HEAR FOR THE COMPILER TO BE HAPPY.
	 */
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	public void updateDisplayOrientation(int mCurrentFacing) {
		android.hardware.Camera.CameraInfo info = new android.hardware.Camera.CameraInfo();
		android.hardware.Camera.getCameraInfo(mCurrentFacing,
				info);
		int degrees = 0; // Util.getDisplayRotation(null);

		int result;
		if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
			result = (info.orientation + degrees) % 360;
			result = (360 - result) % 360; // compensate the mirror
		} else { // back-facing
			result = (info.orientation - degrees + 360) % 360;
		}
		mCamera.setDisplayOrientation(result);
	}

	private ReceiveBroadCast mReceiver;

	protected void initReceiver() {
		// 注册广播接收器
		mReceiver = new ReceiveBroadCast();
		IntentFilter filter = new IntentFilter();
		filter.addAction(ServerConstant.BROAD_LOC);
		registerReceiver(mReceiver, filter);
	}

	public class ReceiveBroadCast extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			// 得到广播中得到的数据，并显示出来

			mLocPo = application.mLocPo;
			unregisterReceiver(mReceiver);
		}

	}

}