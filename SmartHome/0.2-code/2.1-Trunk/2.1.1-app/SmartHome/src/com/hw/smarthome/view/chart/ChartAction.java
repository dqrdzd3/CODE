package com.hw.smarthome.view.chart;

import android.app.Activity;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hw.util.Strings;

/**
 * js方法一定要加@JavascriptInterface 否则 does not work on API 17
 * 
 * @author 曾凡
 * @time 2014年5月16日 下午1:27:49
 */

public class ChartAction {

	private Activity mActivity;
	private ChartEntity entity;

	public ChartAction(Activity activity,
			ChartEntity entity) {
		this.mActivity = activity;
		this.entity = entity;
	}

	/**
	 * java 将数据传给js 通过loadUrl()调用 js方法
	 */
	@JavascriptInterface
	public String initDatas() {
		String jsonStr = new Gson().toJson(entity);
		return jsonStr;
	}

	/**
	 * js 将数据传给 java 通过WebView 的addJavascriptInterface()方法 映射一个对象
	 * 然后再js中通过javascript：对象.方法(参数)的方式调用
	 * 
	 * @param s
	 */
	@JavascriptInterface
	public void updateDatas(String jsMsg) {
		ChartEntity entity = new Gson().fromJson(
				Strings.toString(jsMsg), ChartEntity.class);
		Toast.makeText(mActivity,
				"我是从Js星来的JSON，我现在是JAVA类:" + entity.toString(),
				Toast.LENGTH_SHORT).show();
	}

}