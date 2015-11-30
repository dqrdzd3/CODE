package com.hw.smarthome.ui.weather.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.InputSource;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.baidu.location.LocationClient;
import com.hw.smarthome.ui.weather.db.CityDB;
import com.hw.smarthome.ui.weather.db.po.City;
import com.hw.smarthome.ui.weather.po.WeatherPo;
import com.hw.smarthome.ui.weather.xml.service.SAXPersonService;
import com.hw.util.CodeUtil;
import com.hw.util.Ln;

public class CityUtils {
	public static ArrayList<EventHandler> mListeners = new ArrayList<EventHandler>();
	private static final String FORMAT = "^[a-z,A-Z].*$";
	private CityDB mCityDB;
	private List<City> mCityList;
	// 首字母集
	private List<String> mSections;
	// 根据首字母存放数据
	private Map<String, List<City>> mMap;
	// 首字母位置集
	private List<Integer> mPositions;
	// 首字母对应的位置
	private Map<String, Integer> mIndexer;
	private boolean isCityListComplite;

	private LocationClient mLocationClient = null;
	public static int mNetWorkState;
	private static final int CITY_LIST_SCUESS = 0;
	private static final int CITY_WEATHER_SCUESS = 1;

	private WeatherPo weather;

	public CityUtils(String cityName) {
		initCityWeather(cityName);
	}

	public CityUtils(Context context) {
		initCityList(context);
	}

	public synchronized CityDB getCityDB(Context context) {
		if (mCityDB == null)
			mCityDB = openCityDB(context);
		return mCityDB;
	}

	private CityDB openCityDB(Context context) {
		String path = "/data"
				+ Environment.getDataDirectory()
						.getAbsolutePath() + File.separator
				+ "com.hw.smarthome" + File.separator
				+ CityDB.CITY_DB_NAME;
		File db = new File(path);
		if (!db.exists()) {
			// L.i("db is not exists");
			try {
				InputStream is = context.getAssets().open(
						"city.db");
				FileOutputStream fos = new FileOutputStream(db);
				int len = -1;
				byte[] buffer = new byte[1024];
				while ((len = is.read(buffer)) != -1) {
					fos.write(buffer, 0, len);
					fos.flush();
				}
				fos.close();
				is.close();
			} catch (IOException e) {
				Ln.e(e, "读取地理信息数据库异常");
			}
		}
		return new CityDB(context, path);
	}

	private void initCityList(Context context) {
		mCityList = new ArrayList<City>();
		mSections = new ArrayList<String>();
		mMap = new HashMap<String, List<City>>();
		mPositions = new ArrayList<Integer>();
		mIndexer = new HashMap<String, Integer>();
		mCityDB = openCityDB(context);// 这个必须最先复制完,所以我放在单线程中处理
		new Thread(new Runnable() {
			@Override
			public void run() {
				isCityListComplite = false;
				prepareCityList();
				mHandler.sendEmptyMessage(CITY_LIST_SCUESS);
			}
		}).start();
	}

	/**
	 * 根据城市名去获取当地天气信息
	 * 
	 * @author 曾凡
	 * @param cityName
	 * @time 2015年7月8日 下午3:46:15
	 */
	public void initCityWeather(final String cityName) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				isCityListComplite = false;
				setWeather(prepareCityWeather(cityName));
				mHandler.sendEmptyMessage(CITY_WEATHER_SCUESS);
			}
		}).start();

	}

	private WeatherPo prepareCityWeather(String cityName) {
		WeatherPo res = null;
		try {
			String city = CodeUtil.transUtf2Iso(cityName);
			URL url = new URL(
					"http://wthrcdn.etouch.cn/WeatherApi?city="
							+ city);
			InputStream in = url.openStream();
			InputSource inputSource = null;
			// 通过InputStreamReader设定编码方式
			// 是utf-8编码
			inputSource = new InputSource(url.openStream());
			inputSource.setEncoding("UTF-8");
			res = SAXPersonService.readXML(inputSource);
		} catch (Exception e) {
			Ln.e(e, "获取当地天气异常");
		} finally {
			return res;
		}
	}

	private boolean prepareCityList() {
		mCityList = mCityDB.getAllCity();// 获取数据库中所有城市
		for (City city : mCityList) {
			String firstName = city.getFirstPY();// 第一个字拼音的第一个字母
			if (firstName.matches(FORMAT)) {
				if (mSections.contains(firstName)) {
					mMap.get(firstName).add(city);
				} else {
					mSections.add(firstName);
					List<City> list = new ArrayList<City>();
					list.add(city);
					mMap.put(firstName, list);
				}
			} else {
				if (mSections.contains("#")) {
					mMap.get("#").add(city);
				} else {
					mSections.add("#");
					List<City> list = new ArrayList<City>();
					list.add(city);
					mMap.put("#", list);
				}
			}
		}
		Collections.sort(mSections);// 按照字母重新排序
		int position = 0;
		for (int i = 0; i < mSections.size(); i++) {
			mIndexer.put(mSections.get(i), position);// 存入map中，key为首字母字符串，value为首字母在listview中位置
			mPositions.add(position);// 首字母在listview中位置，存入list中
			position += mMap.get(mSections.get(i)).size();// 计算下一个首字母在listview的位置
		}
		return true;
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case CITY_LIST_SCUESS:
				isCityListComplite = true;
				if (mListeners.size() > 0)// 通知接口完成加载
					for (EventHandler handler : mListeners) {
						handler.onCityComplite();
					}
				break;
			case CITY_WEATHER_SCUESS:
				if (mListeners.size() > 0)// 通知接口完成加载
					for (EventHandler handler : mListeners) {
						handler.onCityWeatherComplite();
					}
				break;
			default:
				break;
			}
		}
	};

	public List<City> getCityList() {
		return mCityList;
	}

	public List<String> getSections() {
		return mSections;
	}

	public Map<String, List<City>> getMap() {
		return mMap;
	}

	public List<Integer> getPositions() {
		return mPositions;
	}

	public Map<String, Integer> getIndexer() {
		return mIndexer;
	}

	public boolean isCityListComplite() {
		return isCityListComplite;
	}

	public WeatherPo getWeather() {
		return weather;
	}

	public void setWeather(WeatherPo weather) {
		this.weather = weather;
	}

	public static abstract interface EventHandler {
		public abstract void onCityComplite();

		public abstract void onNetChange();

		public abstract void onCityWeatherComplite();
	}

}
