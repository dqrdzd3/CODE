package com.hw.weidou.ui.home.adapter.pager;

import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.hw.util.WifiUtils;
import com.hw.util.crash.CrashApplication;
import com.hw.weidou.R;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.parser.alcohol.AlcoholAlgorithm;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.server.constant.ServerConstant;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.HomeUtil;
import com.hw.weidou.ui.home.adapter.po.HistoryChartPo;
import com.hw.weidou.ui.home.adapter.util.HistoryUtil;
import com.hw.weidou.ui.util.UnitUtil;

/**
 * @author 曾凡
 * @time 2014年10月17日 上午10:15:04
 */
public class HistoryFragment extends Fragment {
	private static HistoryFragment instance = null;

	public static HistoryFragment getInstance() {
		if (instance == null) {
			instance = new HistoryFragment();
		}
		return instance;
	}

	private View mParentView;

	/** 当前值 */
	private TextView curDataContent;
	/** 一分钟内的最高值 */
	private String minuteHighData;

	private LineChart minuteView;
	private HistoryChartPo minuteData;

	private LineChart hourView;
	private HistoryChartPo hourData;
	private TextView historyDataUnit;
	private Context mContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		View parentView = inflater.inflate(
				R.layout.ui_home_page_history, container, false);

		mParentView = parentView;
		initView();
		initData();
		return parentView;
	}

	public void initView() {
		curDataContent = (TextView) mParentView
				.findViewById(R.id.historyCurDataContent);
		historyDataUnit = (TextView) mParentView
				.findViewById(R.id.historyDataUnit);

		minuteView = (LineChart) mParentView
				.findViewById(R.id.historyMinuteContent);
		minuteView.setDrawYValues(false);
		minuteView.setDrawGridBackground(false);
		minuteView.setDescription("");
		minuteView.invalidate();

		minuteView.setStartAtZero(true);
		minuteView.setDragEnabled(false);
		minuteView.setScaleEnabled(false);
		minuteView.setHighlightIndicatorEnabled(false);
		minuteView.setDoubleTapToZoomEnabled(false);

		createChart(minuteView);
		hourView = (LineChart) mParentView
				.findViewById(R.id.historyHourContent);
		hourView.setDrawYValues(false);
		hourView.setDrawGridBackground(false);
		hourView.setDescription("");
		hourView.invalidate();

		hourView.setStartAtZero(true);
		hourView.setDragEnabled(false);
		hourView.setScaleEnabled(false);
		hourView.setHighlightIndicatorEnabled(false);
		hourView.setDoubleTapToZoomEnabled(false);
		createChart(hourView);
	}

	/**
	 * 初始化页面
	 * 
	 * @author 曾凡
	 * @time 2014年10月22日 下午2:30:04
	 */
	public void initData() {
		/* 获取最新威豆 */
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		minuteData = new HistoryChartPo();
		minuteData.setType(HistoryUtil.TYPE_MINUTE_CHART);
		minuteData.setName("分钟历史曲线");
		minuteData.setEquip(weidou.getEquip());

		hourData = new HistoryChartPo();
		hourData.setType(HistoryUtil.TYPE_HOUR_CHART);
		hourData.setName("小时历史曲线");
		hourData.setEquip(weidou.getEquip());

	}

	@Override
	public void onResume() {
		super.onResume();

		/* 更新当前值 */
		if (mUpdateCurHandler != null) {
			mUpdateCurHandler.removeCallbacks(mUpdateCurThread);
			mUpdateCurHandler.post(mUpdateCurThread);
		}
		/* 初始化分钟内历史的数据Array和View */
		if (mUpdateHourHandler != null) {
			mUpdateHourHandler
					.removeCallbacks(mUpdateHourThread);
			mUpdateHourHandler.post(mUpdateHourThread);
		}
		/* 初始化小时内历史的数据Array和View */
		if (mUpdateMinuteHandler != null) {
			mUpdateMinuteHandler
					.removeCallbacks(mUpdateMinuteThread);
			mUpdateMinuteHandler.post(mUpdateMinuteThread);
		}
	}

	/** 更新当前值 */
	private Handler mUpdateCurHandler = new Handler();
	private Runnable mUpdateCurThread = new Runnable() {
		@Override
		public void run() {
			/* 获取最新威豆 */
			WeidouPo weidou = ParserDeamon.getCurrentWeidou();
			String unit = "";
			switch (weidou.getEquip()) {
			case EquipConstant.EQUIP_CH2O:// 甲醛
				unit = UIConstant.HOME_UNIT_CH2O;
				curDataContent.setText(UnitUtil.getValue(
						weidou.getEquip(),
						weidou.getDataContent()));
				break;
			case EquipConstant.EQUIP_ALCOHOL:// 酒精
				unit = UIConstant.HOME_UNIT_ALCOHOL;
				curDataContent.setText(AlcoholAlgorithm.currentValue);
				break;
			case EquipConstant.EQUIP_CO:// 一氧化碳
				unit = UIConstant.HOME_UNIT_CO;
				curDataContent.setText(UnitUtil.getValue(
						weidou.getEquip(),
						weidou.getDataContent()));
				break;
			default:
				break;
			}
			historyDataUnit.setText(unit);
			mUpdateCurHandler.postDelayed(mUpdateCurThread, 100);
		}
	};
	/** 更新分钟的的历史 */
	private Handler mUpdateMinuteHandler = new Handler();
	private Runnable mUpdateMinuteThread = new Runnable() {
		@Override
		public void run() {
			updateMinute();
			mUpdateMinuteHandler.postDelayed(
					mUpdateMinuteThread, 1000);
		}
	};
	/** 更新小时内的历史(要求是1分钟内的最高值) */
	private Handler mUpdateHourHandler = new Handler();
	private Runnable mUpdateHourThread = new Runnable() {
		@Override
		public void run() {
			updateHour();
			mUpdateHourHandler.postDelayed(mUpdateHourThread,
					60 * 1000);
		}
	};

	/**
	 * 更新分钟内的秒
	 * 
	 * @author 曾凡
	 * @time 2014年10月21日 上午10:30:10
	 */
	private void updateMinute() {
		/* 获取最新威豆 */
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		minuteData.setEquip(weidou.getEquip());
		String value = "";
		if (EquipConstant.EQUIP_ALCOHOL == weidou.getEquip()) {
			value = AlcoholAlgorithm.currentValue;
		} else {
			value = weidou.getDataContent();
		}
		/* 获取分钟内的最高值 */
		String higherData = HomeUtil.getBiggerValue(value,
				getMinuteHighData());
		setMinuteHighData(higherData);
		/* 只有设备在线，设备存在线才会更新列表 */
		if (weidou.isOnline()) {
			HistoryUtil.Service.addArray(mContext, minuteData,
					value);

			addEntry(minuteView, Float.valueOf(value),
					minuteData.getType());
		}

	}

	/**
	 * 更新小时内的分钟
	 * 
	 * @author 曾凡
	 * @time 2014年10月21日 上午10:29:57
	 */
	private void updateHour() {
		/* 获取最新威豆 */
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		hourData.setEquip(weidou.getEquip());
		if (weidou.isOnline()) {
			/* 更新数据 */
			HistoryUtil.Service.addArray(mContext, hourData,
					getMinuteHighData());

			addEntry(hourView,
					Float.valueOf(getMinuteHighData()),
					hourData.getType());
			// setMinuteHighData("0");
			// 上报内容

			if (Float.valueOf(getMinuteHighData()) > 0)
				sendServer(getMinuteHighData(),
						hourData.getType() + "");

		}
	}

	private List<Float> minuteList = new LinkedList<Float>();
	private List<Float> hourList = new LinkedList<Float>();

	private void addEntry(LineChart chartView, Float value,
			short type) {
		LineData data = chartView.getDataOriginal();
		if (data != null) {
			data.removeDataSet(0);
			LineDataSet set = data.getDataSetByIndex(0);
			if (set == null) {
				set = createSet(type);
				data.addDataSet(set);
			}
			switch (type) {
			case HistoryUtil.TYPE_MINUTE_CHART:
				if (minuteList.size() > 60) {
					minuteList.remove(0);
				}
				minuteList.add(value);
				for (Float f : minuteList) {
					data.addEntry(
							new Entry(f, set.getEntryCount()), 0);
				}
				break;
			case HistoryUtil.TYPE_HOUR_CHART:
				if (hourList.size() > 60) {
					hourList.remove(0);
				}
				hourList.add(value);
				for (Float f : hourList) {
					data.addEntry(
							new Entry(f, set.getEntryCount()), 0);
				}
				break;
			}

			chartView.notifyDataSetChanged();
			chartView.invalidate();
		}
	}

	private void createChart(LineChart hourView) {

		String[] xVals = new String[60];

		for (int i = 0; i < 60; i++)
			xVals[i] = "" + i;

		LineData data = new LineData(xVals);

		hourView.setData(data);
		hourView.invalidate();
	}

	private LineDataSet createSet(short type) {
		String name = "";
		switch (type) {
		case HistoryUtil.TYPE_MINUTE_CHART:
			name = "分钟内";
			break;
		case HistoryUtil.TYPE_HOUR_CHART:
			name = "小时内";
			break;
		}
		LineDataSet set = new LineDataSet(null, name + "历史记录");
		set.setLineWidth(2.5f);
		set.setCircleSize(4.5f);
		set.setColor(Color.rgb(240, 99, 99));
		set.setCircleColor(Color.rgb(240, 99, 99));
		set.setHighLightColor(Color.rgb(190, 190, 190));

		return set;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mUpdateCurHandler != null) {
			mUpdateCurHandler.removeCallbacks(mUpdateCurThread);
		}
		if (mUpdateMinuteHandler != null) {
			mUpdateMinuteHandler
					.removeCallbacks(mUpdateMinuteThread);
		}
		if (mUpdateHourHandler != null) {
			mUpdateHourHandler
					.removeCallbacks(mUpdateHourThread);
		}
	}

	public String getMinuteHighData() {
		return (minuteHighData == null || ""
				.equals(minuteHighData)) ? "0" : minuteHighData;
	}

	public void setMinuteHighData(String minuteHighData) {
		this.minuteHighData = minuteHighData;
	}

	private void sendServer(String value, String vType) {
		String url = ServerConstant.SERVER_BASE_URI
				+ ServerConstant.WD_01_01;
		CrashApplication application = (CrashApplication) getActivity()
				.getApplication();
		FinalHttp fh = new FinalHttp();
		AjaxParams paramMap = new AjaxParams();

		try {
			paramMap.put("MA002",
					WifiUtils.getWIFILocalIpAddress(mContext));
		} catch (UnknownHostException e) {
			paramMap.put("MA002", WifiUtils.getLocalIpAddress());

		}

		paramMap.put("MA003",
				WifiUtils.getLocalMacAddress(mContext));
		paramMap.put("MA004", android.os.Build.VERSION.RELEASE);
		paramMap.put("MA005", android.os.Build.MANUFACTURER);
		paramMap.put("MA006", android.os.Build.MODEL);
		paramMap.put("MA007", application.provice);
		paramMap.put("MA008", application.city);
		paramMap.put("MA009", application.area);
		paramMap.put("MA010", application.mLng);
		paramMap.put("MA011", application.mLat);
		paramMap.put("MA012", value);
		paramMap.put("MA013", vType);
		paramMap.put("MA015", "");
		paramMap.put("MA016", "");
		System.out.println(url);
		/**
		 * 设置错误连接次数
		 */
		fh.configRequestExecutionRetryCount(0);
		fh.post(url, paramMap, new AjaxCallBack<Object>() {

			@Override
			public void onSuccess(Object t) {

			}

			@Override
			public void onFailure(Throwable t, int errorNo,
					String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);

			}

		});
	}
}
