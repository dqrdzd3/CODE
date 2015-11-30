package com.hw.util.application;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.json.JSONException;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.hw.smarthome.R;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.MainActivity;
import com.hw.util.Ln;
import com.hw.util.crash.CrashHandler;
import com.hw.util.loc.LocPo;
import com.hw.util.loc.LocUtils;

public class SHApplication extends Application implements
		Thread.UncaughtExceptionHandler {
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;

	public boolean isDayOrNight = true; // true 为day night 为false
	public int mainLayoutBg = R.layout.ui_home;

	public double lat; // 定位信息
	public double lng;
	public String mLoc; // 位置
	public String provice;
	public String city;
	public String area;

	public TextView mTv;
	public String locInfo;
	public LocPo mLocPo;

	@Override
	public void onCreate() {
		super.onCreate();
		CrashHandler crashHandler = CrashHandler.getInstance();
		crashHandler.init(getApplicationContext());

		mLocationClient = new LocationClient(
				this.getApplicationContext());
		mMyLocationListener = new MyLocationListener();
		mLocationClient
				.registerLocationListener(mMyLocationListener);
		mGeofenceClient = new GeofenceClient(
				getApplicationContext());
		//
		// //设置Thread Exception Handler
		//
		// Thread.setDefaultUncaughtExceptionHandler(this);

		initLocation();
	}

	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	private String tempcoor = "bd09ll";

	private void initLocation() {
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);
		
		option.setLocationMode(tempMode);// 设置定位模式
		option.setCoorType(tempcoor);// 返回的定位结果是百度经纬度，默认值gcj02
		int span = 1000;

		option.setScanSpan(span);// 设置发起定位请求的间隔时间为5000ms
		option.setIsNeedAddress(true);
		mLocationClient.setLocOption(option);
	}

	/**
	 * 实现实位回调监听
	 */
	public class MyLocationListener implements
			BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			StringBuffer sb = new StringBuffer(256);
			sb.append("time : ");
			sb.append(location.getTime());
			sb.append("\nerror code : ");
			sb.append(location.getLocType());
			sb.append("\nlatitude : ");
			sb.append(location.getLatitude());
			sb.append("\nlontitude : ");
			sb.append(location.getLongitude());
			sb.append("\nradius : ");
			sb.append(location.getRadius());
			if (location.getLocType() == BDLocation.TypeGpsLocation) {
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\ndirection : ");
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append(location.getDirection());
			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
			}
			lat = location.getLatitude();
			lng = location.getLongitude();
			locInfo = lat + "," + lng;
			loc();
			if (mTv != null)
				mTv.setText(lat + "," + lng);
			Log.i("BaiduLocationApiDem", sb.toString());
		}

	}

	public void loc() {
		String urlj = ServerConstant.LOC_INFO + "&location="
				+ locInfo;
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

				} finally {

				}
			}
		});

	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		System.exit(0);

		Intent intent = new Intent(this, MainActivity.class);

		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |

		Intent.FLAG_ACTIVITY_NEW_TASK);

		startActivity(intent);

	}

	public static abstract interface EventHandler {
		public abstract void onCityComplite();

		public abstract void onNetChange();
	}

}
