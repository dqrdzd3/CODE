package com.hw.smarthome.ui.home.adapter.homeup.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.Legend.LegendForm;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;
import com.hw.smarthome.R;
import com.hw.smarthome.po.S007PO;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.homeup.InitDatas;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.SolutionListAdapter;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.SolutionUtils;
import com.hw.smarthome.ui.home.adapter.homeup.util.HomeMenuUtil;
import com.hw.smarthome.ui.home.adapter.homeup.util.MyMarkerView;
import com.hw.smarthome.ui.home.adapter.util.RealTimeUtil;
import com.hw.smarthome.ui.home.po.HomeAirHistory;
import com.hw.smarthome.ui.home.po.HomeGasHistory;
import com.hw.smarthome.ui.home.po.HomeHistoryList;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.util.DateUtils;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年10月17日 下午7:16:04
 */
public abstract class InitDatasBase {
	/** 记录了点击历史的记录 */
	private static Map<String, String> historyType = new HashMap<String, String>();
	public static String DAY_7 = "DAY_7";
	public static String DAY_30 = "DAY_30";
	public static String HOUR_24 = "HOUR_24";

	protected FragmentActivity mParentActivity;
	/** 传感器 */
	protected SensorDetail mSensor = null;
	private int type = 0;
	private View mHomeUpView = null;
	private View mGridView = null;

	public InitDatasBase(FragmentActivity parentActivity,
			View homeUpView, View gridView, SensorDetail detail) {
		mParentActivity = parentActivity;
		mGridView = gridView;
		mHomeUpView = homeUpView;
		mSensor = detail;

		instantiateDatas();
	}

	/**
	 * 实例化一个item
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月11日 下午4:12:58
	 */
	public void instantiateDatas() {
		/* 获取设备标识 */
		final String viewSensorId = mSensor.getSensorId();
		if (mSensor != null && !"".equals(mSensor)) {
			/* 当前设备的类型 */
			type = SmartHomeServiceUtil
					.getSensorTypeWithId(viewSensorId);

			/* 0. 设置传感器的名称 */
			initName();
			/* 1. 获取主动上报的实时信息 */
			initRealTime();
			initHistoryTime(mHomeUpView);
			/* 2. 获取历史参数信息 */
			initHistory();
			/* 4. 初始化解决方案 */
			initSolutions();

		}
	}

	/**
	 * 初始化传感器的名称
	 * 
	 * @author 曾凡
	 * @param mHomeUpView
	 * @time 2014年7月11日 下午4:15:31
	 */
	public void initName() {
		DealWithSensor.initIsOnLine(mParentActivity, mSensor);
		try {
			TextView uiHomeSensorName = (TextView) mHomeUpView
					.findViewById(R.id.uiHomeSensorName);
			String sensorName = mSensor.getViewName()
					+ "["
					+ (mSensor.isOnline() ? UIConstant.SENSOR_ONLINE
							: UIConstant.SENSOR_OFFLINE) + "]";
			/* 传感器的名称赋值 */
			uiHomeSensorName.setText(sensorName
					+ mSensor.getSensorId());
			TextView uiHomeSensorId = (TextView) mHomeUpView
					.findViewById(R.id.uiHomeSensorId);
			uiHomeSensorId.setText(mSensor.getSensorId());
			ImageView sensorType = (ImageView) mHomeUpView
					.findViewById(R.id.sensorType);
			if (mSensor.getSensorId().startsWith("1")) {
				sensorType
						.setImageResource(R.drawable.sensor_gas);
			} else if (mSensor.getSensorId().startsWith("5")) {
				sensorType
						.setImageResource(R.drawable.sensor_air);
			}
			String titleSensorId = ((TextView) mParentActivity
					.findViewById(R.id.uiHomeSensorId))
					.getText()
					+ "";
			/* 更新标题 */
			if (mSensor.getSensorId().equals(titleSensorId)) {
				HomeMenuUtil.setTitleSensorInfo(mParentActivity,
						sensorName, mSensor.getSensorId());
			}

		} catch (Exception e) {
			Ln.e(e, "获取传感器失败");
		}
	}

	/**
	 * 初始化实时信息
	 * 
	 * @author 曾凡
	 * @param mHomeUpView
	 * @param type
	 * @param gridView
	 * @time 2014年7月11日 下午3:49:55
	 */
	public void initRealTime() {
		initName();
		/* 更新上半部分 */
		RealTimeUtil.updateCurrentDetailView(mParentActivity,
				mHomeUpView, mGridView, mSensor);

	}

	/**
	 * 初始化更新历史
	 * 
	 * @author 曾凡
	 * @param type
	 * @param mHomeUpView
	 * @time 2014年7月11日 下午3:12:47
	 */
	public void initHistory() {
		/* 获取历史参数信息 */
		HomeHistoryList historyList = HomeUtil
				.getHomeHistoryList(mParentActivity,
						mSensor.getSensorId());

		View uiHomePageUp = mHomeUpView
				.findViewById(R.id.uiHomePageUp);
		View air = mHomeUpView.findViewById(R.id.airSensors);
		View gas = mHomeUpView.findViewById(R.id.gasSensors);
		if (historyList != null) {
			List<HomeAirHistory> airSensors = historyList
					.getAirSensors();
			List<HomeGasHistory> gasSensors = historyList
					.getGasSensors();

			boolean isShowUpArrow = false;
			/* 遍历缓存中的历史，并将页面初始化 */
			if (airSensors != null && airSensors.size() > 0
					&& type == SensorConstant.SENSOR_TYPE_AIR) {
				isShowUpArrow = initAir(mHomeUpView, airSensors,
						mSensor);
				if (isShowUpArrow) {
					air.setVisibility(View.VISIBLE);
					gas.setVisibility(View.GONE);
					mHomeUpView.findViewById(R.id.tbl_kq)
							.setVisibility(View.VISIBLE);
					mHomeUpView.findViewById(R.id.tbl_rq)
							.setVisibility(View.GONE);
					((TextView) mHomeUpView
							.findViewById(R.id.sensortypename))
							.setText(UIConstant.HOME_NAME_TEMPERATURE);
				}
			}
			if (gasSensors != null && gasSensors.size() > 0
					&& type == SensorConstant.SENSOR_TYPE_GAS) {

				isShowUpArrow = initGas(mHomeUpView, gasSensors,
						mSensor);
				if (isShowUpArrow) {
					air.setVisibility(View.GONE);
					gas.setVisibility(View.VISIBLE);
					mHomeUpView.findViewById(R.id.tbl_rq)
							.setVisibility(View.VISIBLE);
					mHomeUpView.findViewById(R.id.tbl_kq)
							.setVisibility(View.GONE);
					((TextView) mHomeUpView
							.findViewById(R.id.sensortypename))
							.setText(UIConstant.HOME_NAME_CH4);
				}
			}
			/* 上箭头默认就是存在 */
			// if (isShowUpArrow) {
			// uiHomePageUp.setVisibility(View.VISIBLE);
			// } else {
			// uiHomePageUp.setVisibility(View.GONE);
			// }
			switch (type) {
			case SensorConstant.SENSOR_TYPE_AIR:
				air.setVisibility(View.VISIBLE);
				gas.setVisibility(View.GONE);
				mHomeUpView.findViewById(R.id.tbl_kq)
						.setVisibility(View.VISIBLE);
				mHomeUpView.findViewById(R.id.tbl_rq)
						.setVisibility(View.GONE);
				break;
			case SensorConstant.SENSOR_TYPE_GAS:
				air.setVisibility(View.GONE);
				gas.setVisibility(View.VISIBLE);
				mHomeUpView.findViewById(R.id.tbl_kq)
						.setVisibility(View.GONE);
				mHomeUpView.findViewById(R.id.tbl_rq)
						.setVisibility(View.VISIBLE);
				break;
			}
		} else {
			switch (type) {
			case SensorConstant.SENSOR_TYPE_AIR:
				air.setVisibility(View.INVISIBLE);
				gas.setVisibility(View.GONE);
				mHomeUpView.findViewById(R.id.tbl_kq)
						.setVisibility(View.VISIBLE);
				mHomeUpView.findViewById(R.id.tbl_rq)
						.setVisibility(View.GONE);
				break;
			case SensorConstant.SENSOR_TYPE_GAS:
				air.setVisibility(View.GONE);
				gas.setVisibility(View.INVISIBLE);
				mHomeUpView.findViewById(R.id.tbl_kq)
						.setVisibility(View.GONE);
				mHomeUpView.findViewById(R.id.tbl_rq)
						.setVisibility(View.VISIBLE);
				break;
			}
		}
	}

	/**
	 * 初始化燃气传感页面
	 * 
	 * @author 曾凡
	 * @param mHomeUpView
	 * @param sensorDetail
	 * @time 2014年7月8日 下午3:10:27
	 */
	protected abstract boolean initGas(View mHomeUpView,
			List<HomeGasHistory> gasSensors,
			SensorDetail sensorDetail);

	/**
	 * 初始化空气传感页面
	 * 
	 * @author 曾凡
	 * @param mHomeUpView
	 * @param sensorDetail
	 * @time 2014年7月8日 下午3:10:27
	 */
	protected abstract boolean initAir(View mHomeUpView,
			List<HomeAirHistory> airSensors,
			SensorDetail sensorDetail);

	/**
	 * 初始化历史曲线时间
	 * 
	 * @author 闫威
	 * @param mHomeUpView
	 * @param sensorDetail
	 * @time 2015年2月10日 下午3:10:27
	 */
	protected abstract void initHistoryTime(View mHomeUpView);

	/**
	 * 传感器参数是否为空
	 * 
	 * @author 曾凡
	 * @param param
	 * @return
	 * @time 2014年7月7日 下午4:33:40
	 */
	protected boolean isNullParam(String[] param) {
		if (param == null) {
			return true;
		}
		try {
			String str = null;
			for (int i = 0; i < param.length; i++) {
				str = param[i];
				if ("".equals(str) || str == null) {
					param[i] = "0";
				}
			}
		} catch (Exception e) {
			Ln.e(e, "判断数据为空异常");
			return true;
		}
		return false;
	}

	/**
	 * 创建一个图表
	 * 
	 * @author 曾凡
	 * @param chartView
	 * @param name
	 * @param unit
	 * @param args
	 * @time 2014年10月29日 下午4:54:24
	 */
	protected void initHistoryDatas(LineChart chartView,
			String name, String unit, String[] args) {
		try {
			chartView.clear();
			// LineData data = chartView.getDataCurrent();
			LineData data = null;
			if (data == null) {
				// data = new LineData(DateUtils.get7DaysBeforCn());
				String historyType = InitDatas.getHistoryType()
						.get(mSensor.getSensorId());
				if (historyType.equals(InitDatas.DAY_30)) {
					data = new LineData(
							DateUtils.get30HoursBeforeCn());
				} else if (historyType.equals(InitDatas.DAY_7)) {
					data = new LineData(
							DateUtils.get7DaysBeforCn());
				} else if (historyType.equals(InitDatas.HOUR_24)) {
					data = new LineData(
							DateUtils.get24HoursBeforeCn());
				}
				chartView.setData(data);
				chartView.setDrawBorder(false);
				chartView.setDrawVerticalGrid(true);
				chartView.setDrawHorizontalGrid(true);
				chartView.setDrawGridBackground(false);
				chartView.setStartAtZero(true);
				chartView.setDrawYValues(false);
				Legend l = chartView.getLegend();
				l.setForm(LegendForm.CIRCLE);
				l.setFormSize(5f);
				l.setTextColor(Color.WHITE);

				YLabels y = chartView.getYLabels();
				y.setTextColor(Color.WHITE);

				XLabels x = chartView.getXLabels();
				x.setTextColor(Color.WHITE);

				chartView.setDescription("");
				chartView.setHighlightEnabled(true);
				chartView.setTouchEnabled(true);
				chartView.setDragEnabled(true);
				chartView.setScaleEnabled(true);
				chartView.setPinchZoom(true);
				chartView.setHighlightIndicatorEnabled(false);
				chartView.setDoubleTapToZoomEnabled(false);
				XLabels xl = chartView.getXLabels();
				xl.setAvoidFirstLastClipping(true);

				MyMarkerView mv = new MyMarkerView(
						mParentActivity,
						R.layout.ui_home_marker_view);
				mv.setOffsets(-mv.getMeasuredWidth() / 2,
						-mv.getMeasuredHeight());
				chartView.setMarkerView(mv);
			}
			ArrayList<LineDataSet> dataSets = data.getDataSets();
			if (dataSets == null) {
				dataSets = new ArrayList<LineDataSet>();
			}
			ArrayList<Entry> values = new ArrayList<Entry>();
			int i = 0;
			for (String val : args) {
				val = (val == null || "".equals(val)) ? "" : val;
				if (!"".equals(val)) {
					values.add(new Entry(Float.valueOf(val), i));
				}
				i++;
			}

			LineDataSet set = createSet(values, name, unit);
			boolean exist = false;
			if (dataSets.size() > 0) {
				for (LineDataSet temp : dataSets) {
					if (temp.getLabel().startsWith(name)) {
						exist = true;
					}
				}

			}
			if (!exist) {
				dataSets.add(set);
			}

			// data = new LineData(DateUtils.get7DaysBeforCn(),
			// dataSets);
			String historyType = InitDatas.getHistoryType().get(
					mSensor.getSensorId());
			if (historyType.equals(InitDatas.DAY_30)) {
				data = new LineData(
						DateUtils.get30HoursBeforeCn(), dataSets);
			} else if (historyType.equals(InitDatas.DAY_7)) {
				data = new LineData(DateUtils.get7DaysBeforCn(),
						dataSets);
			} else if (historyType.equals(InitDatas.HOUR_24)) {
				data = new LineData(
						DateUtils.get24HoursBeforeCn(), dataSets);
			}
			chartView.setData(data);
			chartView.notifyDataSetChanged();
			chartView.invalidate();
		} catch (Exception e) {
			Ln.e(e, "历史列表初始化异常");
		}
	}

	private LineDataSet createSet(ArrayList<Entry> values,
			String name, String unit) {
		LineDataSet set = new LineDataSet(values, name + "["
				+ unit + "]");
		set.setLineWidth(2.5f);
		set.setCircleSize(4.5f);
		// int color = mColors[new Random().nextInt(4)];
		/*
		 * if (UIConstant.HOME_NAME_CO.equals(name)) { color = Color.rgb(137,
		 * 230, 81); } else if (UIConstant.HOME_NAME_CO2.equals(name)) { color =
		 * Color.rgb(137, 230, 81); } else if
		 * (UIConstant.HOME_NAME_CH4.equals(name)) { color = Color.rgb(250, 104,
		 * 104); } else if (UIConstant.HOME_NAME_PM25.equals(name)) { color =
		 * Color.rgb(240, 240, 30); } else if
		 * (UIConstant.HOME_NAME_VOC.equals(name)) { color = Color.rgb(240, 99,
		 * 99); } else if (UIConstant.HOME_NAME_TEMPERATURE.equals(name)) {
		 * color = Color.rgb(137, 230, 81); } else if
		 * (UIConstant.HOME_NAME_HUMIDITY.equals(name)) { color = Color.rgb(89,
		 * 199, 250); }
		 */
		set.setColor(Color.WHITE);
		set.setCircleColor(Color.WHITE);
		set.setHighLightColor(Color.WHITE);

		return set;
	}

	private void initSolutions() {
		ListView lv = (ListView) mHomeUpView
				.findViewById(R.id.uiHomeSolutionList);

		/* 获取到实时数据 */
		SensorDetail detail = HomeUtil.getSensorDetail(
				mParentActivity, mSensor);
		List<S007PO> solutions = SolutionUtils
				.getSolution(detail);
		SolutionListAdapter adapter = new SolutionListAdapter(
				mParentActivity, solutions);
		lv.setAdapter(adapter);
	}

	public SensorDetail getmSensor() {
		return mSensor;
	}

	public void setmSensor(SensorDetail mSensor) {
		this.mSensor = mSensor;
	}

	public FragmentActivity getmParentActivity() {
		return mParentActivity;
	}

	public void setmParentActivity(
			FragmentActivity mParentActivity) {
		this.mParentActivity = mParentActivity;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public View getmHomeUpView() {
		return mHomeUpView;
	}

	public void setmHomeUpView(View mHomeUpView) {
		this.mHomeUpView = mHomeUpView;
	}

	public View getGridView() {
		return mGridView;
	}

	public void setGridView(View gridView) {
		this.mGridView = gridView;
	}

	public static Map<String, String> getHistoryType() {
		return historyType;
	}

	public static void setHistoryType(
			Map<String, String> historyType) {
		InitDatasBase.historyType = historyType;
	}
}
