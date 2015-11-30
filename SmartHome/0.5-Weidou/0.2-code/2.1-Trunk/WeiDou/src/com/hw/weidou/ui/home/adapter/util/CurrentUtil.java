package com.hw.weidou.ui.home.adapter.util;

import android.app.Activity;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.hw.util.Ln;
import com.hw.util.UIUtil;
import com.hw.weidou.R;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.view.chart.ChartEntity;

/**
 * @author 曾凡
 * @time 2014年11月5日 下午5:35:36
 */
public class CurrentUtil {
	/**
	 * 更新数据
	 * 
	 * @author 曾凡
	 * @param webView
	 * @param curValue
	 * @param maxValue
	 * @param unit
	 * @param status
	 * @time 2014年11月5日 下午5:10:47
	 */
	public static String updateHtmlDatas(Activity context,
			WebView webView, WeidouPo weidou, float maxValue) {
		String jsonStr = null;
		try {
			ChartEntity entity = new ChartEntity();
			int[] screen = UIUtil.getScreenMetrics(context);
			entity.setWidth(UIUtil.px2dip(context, screen[0]) - 140);
			entity.setHeight(entity.getWidth());
			entity.setCurValue(Float.valueOf(weidou
					.getDataContent()));
			entity.setMaxValue(maxValue); // 最大量程，po里是最大值
			entity.setUnit(weidou.getUnit());
			entity.setStatus(weidou.getStatus());
			entity.setMeans(weidou.getMeans());
			entity.setFunc(weidou.getFunc());
			entity.setFuncName(weidou.getFuncName());
			entity.setStart(weidou.isStart());
			jsonStr = new Gson().toJson(entity);
			// ChartAction action = new ChartAction(context, entity);
			/* 禁止ScrollView的childview自动滑动到底部 */
			webView.setFocusable(false);
			/* 返回值 */
			// webView.addJavascriptInterface(action, "action");
		} catch (Exception e) {
			Ln.e(e, "将历史数据塞入图标时失败！");
		}
		return jsonStr;
	}

	public static int getPower(WeidouPo weidou) {
		int powerRes = R.drawable.ui_home_power_0;
		if (weidou.getEquip() == 0) {
			return powerRes;
		}

		switch (weidou.getPower()) {
		case 0:
			powerRes = R.drawable.ui_home_power_0;
			break;
		case 1:
			powerRes = R.drawable.ui_home_power_1;
			break;
		case 2:
			powerRes = R.drawable.ui_home_power_2;
			break;
		case 3:
			powerRes = R.drawable.ui_home_power_3;
			break;
		case 4:
			powerRes = R.drawable.ui_home_power_4;
			break;
		default:
			powerRes = R.drawable.ui_home_power_0;
			break;
		}
		return powerRes;
	}
}
