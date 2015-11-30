package com.hw.util.crash;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.hw.weidou.server.constant.ServerConstant;

public class CrashApplication extends Application implements
		Thread.UncaughtExceptionHandler {
	public LocationClient mLocationClient;
	public GeofenceClient mGeofenceClient;
	public MyLocationListener mMyLocationListener;

	public double lat; // 定位信息
	public double lng;
	public String mLoc; // 位置
	public String provice;
	public String city;
	public String area;
	public String mLat;
	public String mLng;

	public TextView mTv;

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
		
		InitLocation();
		//定位开始
		mLocationClient.start();
		//
		// //设置Thread Exception Handler
		//
		// Thread.setDefaultUncaughtExceptionHandler(this);
		
	}
	private LocationMode tempMode = LocationMode.Hight_Accuracy;
	private String tempcoor="bd09ll";
	private void InitLocation(){
		LocationClientOption option = new LocationClientOption();
		option.setLocationMode(tempMode);//设置定位模式
		option.setCoorType(tempcoor);//返回的定位结果是百度经纬度，默认值gcj02
		int span=1000;
		
		option.setScanSpan(span);//设置发起定位请求的间隔时间为5000ms
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
			mTv = new TextView(getApplicationContext());
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
			if (mTv != null)
			{
				mTv.setText(lat + "," + lng);
				getLocation();
			}
			Log.i("BaiduLocationApiDem", sb.toString());
		}

	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	/**
	 * 百度定位获得地址
	 */
	public void getLocation() {
		String urlj = ServerConstant.LOC_INFO+"&location="+mTv.getText();
		FinalHttp fhh = new FinalHttp();
		fhh.get(urlj, new AjaxCallBack<Object>(){
			@Override
			public void onSuccess(Object t) {
				try {
					JSONObject jsonObject = new JSONObject(t.toString());
					jsonObject = jsonObject.getJSONObject("result");
					if (jsonObject.has("location")) {
						mLat = jsonObject.getJSONObject("location").getString("lat");
						mLng = jsonObject.getJSONObject("location").getString("lng");
						
						mLoc =  jsonObject.getString("formatted_address");
					}
					
					if (jsonObject.has("addressComponent")) {
						provice = jsonObject.getJSONObject("addressComponent").getString("province");
						city  = jsonObject.getJSONObject("addressComponent").getString("city");
						area  = jsonObject.getJSONObject("addressComponent").getString("district");
					}
					mLocationClient.stop();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	

	}
}
