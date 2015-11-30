package com.hw.weidou.ui.home.adapter.util;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.webkit.WebView;

import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;
import com.hw.util.UIUtil;
import com.hw.weidou.R;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.home.adapter.po.HistoryChartPo;
import com.hw.weidou.view.chart.ChartAction;
import com.hw.weidou.view.chart.ChartEntity;

/**
 * 历史的工具类
 * 
 * @author 曾凡
 * @time 2014年10月21日 上午10:34:48
 */
public class HistoryUtil {

	public static final short TYPE_MINUTE_CHART = 0;
	public static final short TYPE_HOUR_CHART = 1;

	public static class Service {
		/** 分钟内的历史数据 */
		private static List<Float> minuteΗistoryΑrray = new LinkedList<Float>();
		/** 分钟内的历史数据 */
		private static List<Float> hourΗistoryΑrray = new LinkedList<Float>();

		/**
		 * 插入一个值
		 * 
		 * @author 曾凡
		 * @param context
		 * @param data
		 * @param arg
		 * @time 2014年10月21日 下午1:22:00
		 */
		public static void addArray(Context context,
				HistoryChartPo data, String arg) {
			if (data == null) {
				return;
			}
			arg = (arg == null || "".equals(arg)) ? "0" : arg;
			float tempData = Float.valueOf(arg);
			switch (data.getType()) {
			case TYPE_MINUTE_CHART:
				minuteΗistoryΑrray.add(tempData);
				if (minuteΗistoryΑrray.size() == 60) {
					minuteΗistoryΑrray.remove(0);
				}
				break;
			case TYPE_HOUR_CHART:
				hourΗistoryΑrray.add(tempData);
				if (hourΗistoryΑrray.size() == 60) {
					hourΗistoryΑrray.remove(0);
				}
				break;
			}
		}

		/**
		 * 获取历史数据
		 * 
		 * @author 曾凡
		 * @param context
		 * @param charType
		 * @return
		 * @time 2014年10月21日 上午11:27:53
		 */
		public static List<Float> getArray(Context context,
				short type) {
			List<Float> tempChart = null;
			switch (type) {
			case TYPE_MINUTE_CHART:
				tempChart = minuteΗistoryΑrray;

				break;
			case TYPE_HOUR_CHART:
				tempChart = hourΗistoryΑrray;
				break;
			}
			return tempChart;
		}

	}

	/**
	 * 本地持久化数据
	 * 
	 * @author 曾凡
	 * @time 2014年10月21日 上午10:52:36
	 */
	public static class Storage {
		private static final String SAVE_FILE_NAME = "UI_HOME_HISTORY";
		private static final String MINUTE_CHART_KEY = "UI_HOME_HISTORY_MINUTE_CHART_KEY";
		private static final String HOUR_CHART_KEY = "UI_HOME_HISTORY_HOUR_CHART_KEY";

		public static void saveHistoryDatas(Context context,
				HistoryChartPo data) {
			if (data == null) {
				Ln.w("没有可以存储的历史数据");
			}
			String saveByteStr = null;
			try {
				saveByteStr = PreferenceUtil.obj2String(data);

				Editor userData = context.getSharedPreferences(
						SAVE_FILE_NAME, 0).edit();
				switch (data.getType()) {
				case TYPE_MINUTE_CHART:
					userData.putString(
							MINUTE_CHART_KEY + data.getEquip(),
							saveByteStr);
					break;
				case TYPE_HOUR_CHART:
					userData.putString(
							HOUR_CHART_KEY + data.getEquip(),
							saveByteStr);
					break;
				}
				userData.commit();
			} catch (Exception e) {
				Ln.e(e, "存储历史数据异常！");
				e.printStackTrace();
			}
		}

		public static HistoryChartPo getHistoryDatas(
				Context context, HistoryChartPo data) {
			SharedPreferences userData = context
					.getSharedPreferences(SAVE_FILE_NAME, 0);
			String saveStr = null;
			switch (data.getType()) {
			case TYPE_MINUTE_CHART:
				saveStr = userData.getString(MINUTE_CHART_KEY
						+ data.getEquip(), "");
				break;
			case TYPE_HOUR_CHART:
				saveStr = userData.getString(HOUR_CHART_KEY
						+ data.getEquip(), "");
				break;
			}

			HistoryChartPo chartView = null;
			if (saveStr != null && !"".equals(saveStr)) {
				try {
					chartView = (HistoryChartPo) PreferenceUtil
							.String2Object(saveStr);
				} catch (Exception e) {
					Ln.e(e, "获取历史数据异常！");
				}
			}
			return chartView;

		}

		/**
		 * 清除数据
		 * 
		 * @author 曾凡
		 * @param context
		 * @param data
		 * @time 2014年10月22日 下午2:07:14
		 */
		public static void clearHourArray(Context context,
				HistoryChartPo data) {
			Editor userData = context.getSharedPreferences(
					SAVE_FILE_NAME, 0).edit();
			switch (data.getType()) {
			case TYPE_MINUTE_CHART:
				userData.putString(
						MINUTE_CHART_KEY + data.getEquip(), "");
				break;
			case TYPE_HOUR_CHART:
				userData.putString(
						MINUTE_CHART_KEY + data.getEquip(), "");
				break;
			}
		}

	}

}
