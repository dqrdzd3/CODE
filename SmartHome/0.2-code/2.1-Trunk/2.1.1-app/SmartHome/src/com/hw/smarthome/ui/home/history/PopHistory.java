package com.hw.smarthome.ui.home.history;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.PopupWindow;

import com.hw.smarthome.R;
import com.hw.smarthome.server.deal.DealWithHome;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.constant.HomeConstant;
import com.hw.smarthome.ui.home.po.HomeAirHistory;
import com.hw.smarthome.ui.home.po.HomeGasHistory;
import com.hw.smarthome.ui.home.po.HomeHistoryList;
import com.hw.smarthome.view.chart.ChartAction;
import com.hw.smarthome.view.chart.ChartEntity;
import com.hw.util.Ln;
import com.hw.util.UIUtil;
import com.hw.util.WebViewUtil;

/**
 * 弹出窗体状态栏
 * 
 * @author 曾凡
 * @time 2014年6月26日 下午3:19:07
 */
public class PopHistory extends PopupWindow {

	private View conentView;
	private Context mContext;
	private WebView mWebView;

	public PopHistory(final Context context) {
		super(context);
		mContext = context;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		conentView = inflater.inflate(
				R.layout.ui_home_history_pop_window, null);
		mWebView = (WebView) conentView
				.findViewById(R.id.uiHomePopWindowWebView);
		// int h = context.getWindowManager().getDefaultDisplay().getHeight();
		// int w = context.getWindowManager().getDefaultDisplay().getWidth();
		int h = 500;
		int w = 800;
		// 设置SelectPicPopupWindow的View
		this.setContentView(conentView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		this.setOutsideTouchable(true);
		// 刷新状态
		this.update();
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0000000000);
		// 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
		this.setBackgroundDrawable(dw);
		// mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.AnimationPreview);

	}

	/**
	 * 显示popupWindow
	 * 
	 * @param parent
	 */
	public void showPopupWindow(View parent) {
		if (!this.isShowing()) {
			// 以下拉方式显示popupwindow
//			 this.showAsDropDown(parent, parent.getLayoutParams().width / 2,
//			 -180);
			this.showAtLocation(parent, Gravity.CENTER,

					parent.getLayoutParams().width ,
					parent.getLayoutParams().height);

		} else {
			this.dismiss();
		}
	}

	/**
	 * 显示结果
	 * 
	 * @author 曾凡
	 * @param res
	 *            成功或失败
	 * @param msg
	 * @param args
	 * @param unit
	 * @time 2014年7月3日 下午12:30:18
	 */
	private void showHistory(String msg, String unit,
			String[] args) {
		initDatas(msg, unit, args);
		WebViewUtil webUtil = new WebViewUtil();
		webUtil.setmContext(mContext);
		webUtil.initWebview(mWebView,
				HomeConstant.HOME_HISTORY_ADDR);
	}

	private void initDatas(String name, String unit,
			String[] args) {
		try {
			/* FIXME 以下为测试数据 */
			ChartEntity entity = new ChartEntity();
			int[] screen = UIUtil
					.getScreenMetrics(MainActivity.mContext);
			entity.setWidth(UIUtil.px2dip(mContext, screen[0])
					- 40 + "");
			entity.setName(name);
			entity.setUnit(unit);
			entity.setMonday(args[0]);
			entity.setTuesday(args[1]);
			entity.setWednesday(args[2]);
			entity.setThursday(args[3]);
			entity.setFriday(args[4]);
			entity.setSaturday(args[5]);
			entity.setSunday(args[6]);
			ChartAction action = new ChartAction(
					MainActivity.mContext, entity);

			/* 禁止ScrollView的childview自动滑动到底部 */
			mWebView.setFocusable(false);
			/* 返回值 */
			mWebView.addJavascriptInterface(action, "action");
		} catch (Exception e) {
			Ln.e(e, "将历史数据塞入图标时失败！");
		}

	}

	public void showAirHistory(String sensorId,
			Map<String, Object> webContent) {
		/* 获取历史参数信息 */
		HomeHistoryList historyList = DealWithHome
				.get7DaysHistory(mContext);
		List<HomeAirHistory> airHistory = historyList
				.getAirSensors();
		int typeId = (Integer) webContent.get("img");
		String name = "";
		String unit = "";
		String[] args = null;
		for (HomeAirHistory air : airHistory) {
			if (!sensorId.equals(air.getSensorId())) {
				break;
			}
			if (typeId == R.drawable.ui_home_unit_temperature) {
				name = UIConstant.HOME_NAME_TEMPERATURE;
				args = air.getTemperatures();
				unit = UIConstant.HOME_UNIT_TEMPERATURE;
			} else if (typeId == R.drawable.ui_home_unit_humidity) {
				name = UIConstant.HOME_NAME_HUMIDITY;
				args = air.getHumiditys();
				unit = UIConstant.HOME_UNIT_HUMIDITY;
			} else if (typeId == R.drawable.ui_home_unit_co2) {
				name = UIConstant.HOME_NAME_CO2;
				args = air.getCo2s();
				unit = UIConstant.HOME_UNIT_CO2;
			} else if (typeId == R.drawable.ui_home_unit_pm25) {
				name = UIConstant.HOME_NAME_PM25;
				args = air.getPm25s();
				unit = UIConstant.HOME_UNIT_PM25;
			} else if (typeId == R.drawable.ui_home_unit_c6h6) {
				name = UIConstant.HOME_NAME_C6H6;
				args = air.getC6h6s();
				unit = UIConstant.HOME_UNIT_C6H6;
			} else if (typeId == R.drawable.ui_home_unit_ch2o) {
				name = UIConstant.HOME_NAME_CH2O;
				args = air.getCh2os();
				unit = UIConstant.HOME_UNIT_CH2O;
			}
		}
		initDatas(name, unit, args);
		WebViewUtil webUtil = new WebViewUtil();
		webUtil.setmContext(mContext);
		webUtil.initWebview(mWebView,
				HomeConstant.HOME_HISTORY_ADDR);
	}

	public void showGasHistory(String sensorId,
			Map<String, Object> webContent) {
		/* 获取历史参数信息 */
		HomeHistoryList historyList = DealWithHome
				.get7DaysHistory(mContext);
		List<HomeGasHistory> gasHistory = historyList
				.getGasSensors();
		int typeId = (Integer) webContent.get("img");
		String name = "";
		String unit = "";
		String[] args = null;
		for (HomeGasHistory gas : gasHistory) {
			if (!sensorId.equals(gas.getSensorId())) {
				break;
			}
			if (typeId == R.drawable.ui_home_unit_co) {
				name = UIConstant.HOME_NAME_CO;
				args = gas.getCos();
				unit = UIConstant.HOME_UNIT_CO;
			} else if (typeId == R.drawable.ui_home_unit_ch4) {
				name = UIConstant.HOME_NAME_CH4;
				args = gas.getCh4s();
				unit = UIConstant.HOME_UNIT_CH4;
			}
		}
		initDatas(name, unit, args);
		WebViewUtil webUtil = new WebViewUtil();
		webUtil.setmContext(mContext);
		webUtil.initWebview(mWebView,
				HomeConstant.HOME_HISTORY_ADDR);
	}
}
