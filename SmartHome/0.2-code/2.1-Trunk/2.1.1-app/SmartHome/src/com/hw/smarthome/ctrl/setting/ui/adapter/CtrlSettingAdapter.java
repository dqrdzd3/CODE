package com.hw.smarthome.ctrl.setting.ui.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.OnWheelChangedListener;
import antistatic.spinnerwheel.OnWheelScrollListener;
import antistatic.spinnerwheel.adapters.NumericWheelAdapter;

import com.hw.smarthome.R;
import com.hw.smarthome.cache.SysCache;
import com.hw.smarthome.ctrl.po.SettingStatusPo;
import com.hw.smarthome.ctrl.util.CtrlUtils;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2015年4月17日 上午11:10:01
 */
public class CtrlSettingAdapter extends BaseAdapter {
	private LayoutInflater mInflater;
	private Context mContext;
	private SensorDetail mSensorDetail;

	private static final int CO2_ON_THRESHOLD = 800;
	private static final int TEMPERATURE_ON_THRESHOLD = 26;
	private static final int HUMIDITY_ON_THRESHOLD = 45;
	private static final int PM25_ON_THRESHOLD = 75;
	private static final int VOC_ON_THRESHOLD = 20;
	private static final int CH4_ON_THRESHOLD = 7;
	private static final int CO_ON_THRESHOLD = 99;

	private static final int CO2_OFF_THRESHOLD = 500;
	private static final int TEMPERATURE_OFF_THRESHOLD = 23;
	private static final int HUMIDITY_OFF_THRESHOLD = 45;
	private static final int PM25_OFF_THRESHOLD = 65;
	private static final int VOC_OFF_THRESHOLD = 10;
	private static final int CH4_OFF_THRESHOLD = 6;
	private static final int CO_OFF_THRESHOLD = 89;

	public CtrlSettingAdapter(Context context,
			SensorDetail sensorDetail) {
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mSensorDetail = sensorDetail;
	}

	@Override
	public int getCount() {
		if (mSensorDetail != null) {
			if (mSensorDetail.getAir() != null) {
				return 5;
			} else if (mSensorDetail.getGas() != null) {
				return 2;
			}
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView,
			ViewGroup parent) {
		AbstractWheel ctrlWheel9999 = null;
		AbstractWheel ctrlWhee99 = null;
		String media = "";
		try {
			convertView = mInflater.inflate(
					R.layout.ctrl_setting_ui_item, null);
			TextView ctrlSensorName = (TextView) convertView
					.findViewById(R.id.ctrlSensorName);
			TextView ctrlSensorValue = (TextView) convertView
					.findViewById(R.id.ctrlSensorValue);

			if (mSensorDetail.getAir() != null
					&& mSensorDetail.getAir().getSensorId() != null
					&& !"".equals(mSensorDetail.getAir()
							.getSensorId())) {
				SensorAirDetail air = mSensorDetail.getAir();
				switch (position) {
				/* 温度 */
				case 0: {
					media = SensorConstant.MEDIA_TYPE_TEMPERATURE;
					initCtrlSensors(convertView,
							mSensorDetail.getSensorId(), media);
					ctrlWheel9999 = init9999Wheel(convertView,
							media);
					ctrlWheel9999
							.setCurrentItem(TEMPERATURE_ON_THRESHOLD);

					ctrlWhee99 = init99Wheel(convertView, media);
					ctrlWhee99
							.setCurrentItem(TEMPERATURE_OFF_THRESHOLD);

					ctrlSensorName
							.setText(R.string.ctrl_temperature);
					ctrlSensorValue
							.setText(air.getTemperature());
				}
					break;
				/* 湿度 */
				case 1: {
					media = SensorConstant.MEDIA_TYPE_HUMIDITY;
					initCtrlSensors(convertView,
							mSensorDetail.getSensorId(), media);
					ctrlWheel9999 = init9999Wheel(convertView,
							media);
					ctrlWheel9999
							.setCurrentItem(HUMIDITY_ON_THRESHOLD);

					ctrlWhee99 = init99Wheel(convertView, media);
					ctrlWhee99
							.setCurrentItem(HUMIDITY_OFF_THRESHOLD);
					ctrlSensorName
							.setText(R.string.ctrl_humidity);
					ctrlSensorValue.setText(air.getHumidity());
				}
					break;
				/* CO2 */
				case 2: {
					media = SensorConstant.MEDIA_TYPE_CO2;
					initCtrlSensors(convertView,
							mSensorDetail.getSensorId(), media);
					ctrlWheel9999 = init9999Wheel(convertView,
							media);
					ctrlWheel9999
							.setCurrentItem(CO2_ON_THRESHOLD);

					ctrlWhee99 = init99Wheel(convertView, media);
					ctrlWhee99.setCurrentItem(CO2_OFF_THRESHOLD);
					ctrlSensorName.setText(R.string.ctrl_co2);
					ctrlSensorValue.setText(air.getCo2());
				}
					break;
				/* PM2.5 */
				case 3: {
					media = SensorConstant.MEDIA_TYPE_PM25;
					initCtrlSensors(convertView,
							mSensorDetail.getSensorId(), media);
					ctrlWheel9999 = init9999Wheel(convertView,
							media);
					ctrlWheel9999
							.setCurrentItem(PM25_ON_THRESHOLD);

					ctrlWhee99 = init99Wheel(convertView, media);
					ctrlWhee99
							.setCurrentItem(PM25_OFF_THRESHOLD);
					ctrlSensorName.setText(R.string.ctrl_pm25);
					ctrlSensorValue.setText(air.getPm25());
				}
					break;
				/* VOC */
				case 4: {
					media = SensorConstant.MEDIA_TYPE_VOC;
					initCtrlSensors(convertView,
							mSensorDetail.getSensorId(), media);
					ctrlWheel9999 = init9999Wheel(convertView,
							media);
					ctrlWheel9999
							.setCurrentItem(VOC_ON_THRESHOLD);

					ctrlWhee99 = init99Wheel(convertView, media);
					ctrlWhee99.setCurrentItem(VOC_OFF_THRESHOLD);
					ctrlSensorName.setText(R.string.ctrl_voc);
					ctrlSensorValue.setText(air.getVoc());
				}
					break;
				default:
					break;
				}
			} else if (mSensorDetail.getGas() != null
					&& mSensorDetail.getGas().getSensorId() != null
					&& !"".equals(mSensorDetail.getGas()
							.getSensorId())) {
				SensorGasDetail gas = mSensorDetail.getGas();
				switch (position) {
				/* 一氧化碳 */
				case 0: {
					media = SensorConstant.MEDIA_TYPE_CO;
					initCtrlSensors(convertView,
							mSensorDetail.getSensorId(), media);
					ctrlWheel9999 = init9999Wheel(convertView,
							media);
					ctrlWheel9999
							.setCurrentItem(CO_ON_THRESHOLD);
					ctrlWhee99 = init99Wheel(convertView, media);
					ctrlWhee99.setCurrentItem(CO_OFF_THRESHOLD);
					ctrlSensorName.setText(R.string.ctrl_co);
					ctrlSensorValue.setText(gas.getCo());
				}
					break;
				/* 燃气 */
				case 1: {

					media = SensorConstant.MEDIA_TYPE_CH4;
					initCtrlSensors(convertView,
							mSensorDetail.getSensorId(), media);
					ctrlWheel9999 = init9999Wheel(convertView,
							media);
					ctrlWheel9999
							.setCurrentItem(CH4_ON_THRESHOLD);
					ctrlWhee99 = init99Wheel(convertView, media);
					ctrlWhee99.setCurrentItem(CH4_OFF_THRESHOLD);
					ctrlSensorName.setText(R.string.ctrl_ch4);
					ctrlSensorValue.setText(gas.getCh4());
				}
					break;
				}
			}
			/* 将上次的缓存赋值 */
			initWheel(mSensorDetail.getSensorId(), media,
					ctrlWheel9999, ctrlWhee99);

		} catch (Exception e) {
			Ln.e(e);
		}
		return convertView;
	}

	private AbstractWheel init99Wheel(View convertView,
			String media) {
		AbstractWheel wheel = (AbstractWheel) convertView
				.findViewById(R.id.ctrlWhee99);
		AbstractWheel wheel9999 = (AbstractWheel) convertView
				.findViewById(R.id.ctrlWheel9999);
		/* 整数不够3列就补上0 */
		wheel.setViewAdapter(new NumericWheelAdapter(mContext,
				0, 999, "%03d"));
		// wheel.setCurrentItem((int) (Math.random() * 10));
		wheel.addScrollingListener(new ScrollListener(media,
				wheel9999, wheel));
		wheel.setCyclic(true);
		wheel.setInterpolator(new AnticipateOvershootInterpolator());
		wheel.addChangingListener(new ChangedListener(media,
				wheel9999, wheel));
		return wheel;
	}

	private AbstractWheel init9999Wheel(View convertView,
			String media) {
		AbstractWheel wheel = (AbstractWheel) convertView
				.findViewById(R.id.ctrlWheel9999);
		AbstractWheel wheel99 = (AbstractWheel) convertView
				.findViewById(R.id.ctrlWhee99);
		wheel.setViewAdapter(new NumericWheelAdapter(mContext,
				0, 999, "%03d"));
		// wheel.setCurrentItem((int) (Math.random() * 10));
		wheel.addScrollingListener(new ScrollListener(media,
				wheel, wheel99));
		wheel.setCyclic(true);
		wheel.setInterpolator(new AnticipateOvershootInterpolator());
		wheel.addChangingListener(new ChangedListener(media,
				wheel, wheel99));
		return wheel;
	}

	/**
	 * 初始化滚轮的值
	 * 
	 * @author 曾凡
	 * @param wheel9999
	 * @param wheel99
	 * @time 2015年4月23日 上午9:32:21
	 */
	private void initWheel(String sensorId, String media,
			AbstractWheel wheel9999, AbstractWheel wheel99) {
		Map<String, SettingStatusPo> sensorSetting = null;
		SettingStatusPo mediaSetting = null;
		try {
			sensorSetting = SysCache.getWheelCache().get(
					sensorId);
			if (sensorSetting == null) {
				sensorSetting = new HashMap<String, SettingStatusPo>();
				mediaSetting = new SettingStatusPo(wheel9999,
						wheel99);
				sensorSetting.put(media, mediaSetting);
				SysCache.getWheelCache().put(sensorId,
						sensorSetting);
			} else {
				mediaSetting = sensorSetting.get(media);
				if (mediaSetting == null) {
					mediaSetting = new SettingStatusPo(
							wheel9999, wheel99);
					sensorSetting.put(media, mediaSetting);
				} else {
					mediaSetting = sensorSetting.get(media);
					wheel9999.setCurrentItem(mediaSetting
							.getWheel9999().getCurrentItem());
					wheel99.setCurrentItem(mediaSetting
							.getWheel99().getCurrentItem());
				}
			}
		} catch (Exception e) {
			Ln.e(e);
		}
	}

	/**
	 * 在滚轮变化的时候给缓存赋值
	 * 
	 * @author 曾凡
	 * @param sensorId
	 * @param media
	 * @param wheel9999
	 * @param wheel99
	 * @time 2015年4月23日 上午10:03:10
	 */
	private void setWheel(String sensorId, String media,
			AbstractWheel wheel9999, AbstractWheel wheel99) {
		Map<String, SettingStatusPo> sensorSetting = null;
		SettingStatusPo mediaSetting = null;
		try {
			sensorSetting = SysCache.getWheelCache().get(
					sensorId);
			mediaSetting = sensorSetting.get(media);
			Ln.i("wheel99 设置到:" + wheel99.getCurrentItem());
			Ln.i("wheel9999 设置到:" + wheel9999.getCurrentItem());
			mediaSetting.setWheel99(wheel99);
			mediaSetting.setWheel9999(wheel9999);
			sensorSetting.put(media, mediaSetting);
			SysCache.getWheelCache()
					.put(sensorId, sensorSetting);
		} catch (Exception e) {
			Ln.e(e);
		}
	}

	private class ScrollListener implements
			OnWheelScrollListener {
		private boolean wheelScrolled = false;
		private String mMedia;
		private AbstractWheel ctrlWheel9999 = null;
		private AbstractWheel ctrlWhee99 = null;

		public ScrollListener(String mMedia,
				AbstractWheel ctrlWheel9999,
				AbstractWheel ctrlWhee99) {
			super();
			this.mMedia = mMedia;
			this.ctrlWheel9999 = ctrlWheel9999;
			this.ctrlWhee99 = ctrlWhee99;
		}

		public void onScrollingStarted(AbstractWheel wheel) {
			wheelScrolled = true;
		}

		public void onScrollingFinished(AbstractWheel wheel) {
			wheelScrolled = false;
			if(ctrlWhee99.getCurrentItem() >ctrlWheel9999.getCurrentItem()){
				ctrlWhee99.setCurrentItem(ctrlWheel9999.getCurrentItem());
			}
			setWheel(mSensorDetail.getSensorId(), mMedia,
					ctrlWheel9999, ctrlWhee99);
			updateStatus(mMedia, ctrlWheel9999, ctrlWhee99);
		}
	}

	private class ChangedListener implements
			OnWheelChangedListener {
		private String mMedia;
		private AbstractWheel ctrlWheel9999 = null;
		private AbstractWheel ctrlWhee99 = null;

		public ChangedListener(String media,
				AbstractWheel ctrlWheel9999,
				AbstractWheel ctrlWhee99) {
			mMedia = media;
			this.ctrlWheel9999 = ctrlWheel9999;
			this.ctrlWhee99 = ctrlWhee99;
		}

		@Override
		public void onChanged(AbstractWheel wheel, int oldValue,
				int newValue) {
			if(ctrlWhee99.getCurrentItem() >ctrlWheel9999.getCurrentItem()){
				ctrlWhee99.setCurrentItem(ctrlWheel9999.getCurrentItem());
			}
			updateStatus(mMedia, ctrlWheel9999, ctrlWhee99);
		}

	}

	private void updateStatus(String media,
			AbstractWheel ctrlWheel9999, AbstractWheel ctrlWhee99) {
		SensorCtrlDetail cur = null;
		if (mSensorDetail != null) {
			String sensorId = mSensorDetail.getSensorId();
			for (SensorCtrlDetail ctrl : CtrlUtils.getDevices(
					mContext, sensorId, media)) {
				cur = CtrlUtils.getCurCtrlDevice(mContext,
						ctrl.getDeviceId());
				if(cur ==null){
					Ln.i("被控设备没有主动上报");
					return;
				}
				CtrlUtils.compareSensorValue(mContext,
						mSensorDetail, cur,
						ctrlWheel9999.getCurrentItem(),
						ctrlWhee99.getCurrentItem());
			}
		}
	}

	/**
	 * 初始化三个值
	 * 
	 * @author 曾凡
	 * @param convertView
	 * @param sensorId
	 * @param mMedia
	 * @time 2015年4月23日 上午9:51:09
	 */
	private void initCtrlSensors(View convertView,
			String sensorId, String mMedia) {
		List<SensorCtrlDetail> devices = CtrlUtils.getDevices(
				mContext, sensorId, mMedia);
		SensorCtrlDetail temp = null;
		String deviceId = "";
		if (devices != null) {
			for (int i = 0; i < devices.size(); i++) {
				temp = devices.get(i);
				deviceId = temp.getDeviceId();
				/* A1联动设备 bgn 组多只能添加三个设备 */
				if (i == 0) {
					TextView ctrlDeviceId1 = (TextView) convertView
							.findViewById(R.id.ctrlDeviceId1);
					ctrlDeviceId1.setText(deviceId);
					ImageView ctrlDeviceImg1 = (ImageView) convertView
							.findViewById(R.id.ctrlDeviceImg1);
					TextView ctrlDeviceTxt1 = (TextView) convertView
							.findViewById(R.id.ctrlDeviceTxt1);
					initCtrlDeviceItem(ctrlDeviceImg1,
							ctrlDeviceTxt1, temp);
				}

				if (i == 1) {
					TextView ctrlDeviceId2 = (TextView) convertView
							.findViewById(R.id.ctrlDeviceId2);
					ctrlDeviceId2.setText(deviceId);
					ImageView ctrlDeviceImg2 = (ImageView) convertView
							.findViewById(R.id.ctrlDeviceImg2);
					TextView ctrlDeviceTxt2 = (TextView) convertView
							.findViewById(R.id.ctrlDeviceTxt2);
					initCtrlDeviceItem(ctrlDeviceImg2,
							ctrlDeviceTxt2, temp);
				}
				if (i == 2) {
					TextView ctrlDeviceId3 = (TextView) convertView
							.findViewById(R.id.ctrlDeviceId3);
					ctrlDeviceId3.setText(deviceId);
					ImageView ctrlDeviceImg3 = (ImageView) convertView
							.findViewById(R.id.ctrlDeviceImg3);
					TextView ctrlDeviceTxt3 = (TextView) convertView
							.findViewById(R.id.ctrlDeviceTxt3);
					initCtrlDeviceItem(ctrlDeviceImg3,
							ctrlDeviceTxt3, temp);
				}
				/* A1联动设备 end 组多只能添加三个设备 */
			}

		}
	}

	private void initCtrlDeviceItem(ImageView ctrlDeviceImg,
			TextView ctrlDeviceTxt, SensorCtrlDetail ctrlDetail) {
		if (ctrlDetail.getDeviceType().startsWith("C1")) {
			ctrlDeviceImg
					.setImageResource(R.drawable.ctrl_icon_c1);
		}
		ctrlDeviceTxt.setText(ctrlDetail.getName());
	}

	public SensorDetail getmSensorDetail() {
		return mSensorDetail;
	}

	public void setmSensorDetail(SensorDetail mSensorDetail) {
		this.mSensorDetail = mSensorDetail;
	}

}
