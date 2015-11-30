package com.hw.smarthome.login.reg.util;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.hw.smarthome.login.LoginActivity;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.UIUtil;
import com.hw.util.application.SHApplication;

/**
 * @author 曾凡
 * @time 2015年5月6日 下午3:20:21
 */
public class RegUtils {
	public static void submitReg(final Activity context,
			final String telphone, final String pass,
			final String userName, SHApplication application) {

		if (!NetUtil.isNetworkConnected(context)) {
			UIUtil.ToastMessage(context, "网络不通");
			return;
		}

		String urlj = ServerConstant.LOC_INFO + "&location="
				+ application.mTv.getText();
		FinalHttp fhh = new FinalHttp();
		fhh.get(urlj, new AjaxCallBack<Object>() {
			private String latString, lngString, locString,
					province, city, area;

			@Override
			public void onSuccess(Object t) {

				try {
					JSONObject jsonObject = new JSONObject(t
							.toString());
					jsonObject = jsonObject
							.getJSONObject("result");
					if (jsonObject.has("location")) {
						latString = jsonObject.getJSONObject(
								"location").getString("lat");
						lngString = jsonObject.getJSONObject(
								"location").getString("lng");

						locString = jsonObject
								.getString("formatted_address");
					}

					if (jsonObject.has("addressComponent")) {
						province = jsonObject.getJSONObject(
								"addressComponent").getString(
								"province");
						city = jsonObject.getJSONObject(
								"addressComponent").getString(
								"city");
						area = jsonObject.getJSONObject(
								"addressComponent").getString(
								"district");
					}

				} catch (JSONException e) {
					Ln.e(e, "地理信息解析异常");
					e.printStackTrace();
				} finally {
					submitReg(context, telphone, pass, userName,
							latString, lngString, locString,
							province, city, area);
				}
			}
		});
	}

	/**
	 * 注册
	 * 
	 * @author 曾凡
	 * @param view
	 * @time 2015年5月6日 下午3:22:14
	 */
	public static void submitReg(final Activity context,
			final String telphone, final String pass,
			String userName, String latString, String lngString,
			String locString, String province, String city,
			String area) {
		AjaxParams paramMap = new AjaxParams();

		paramMap.put("USERNAME", userName);
		paramMap.put("PHONE", telphone);
		paramMap.put("PASSWORD", pass);
		paramMap.put("LAT", latString);
		paramMap.put("LNG", lngString);
		paramMap.put("LOC", locString);
		paramMap.put("PROVICE", province);
		paramMap.put("CITY", city);
		paramMap.put("AREA", area);

		/* 登陆这个页面后从服务器获取最新的列表 */
		String url = ServerConstant.SERVER_BASE_URI
				+ "u001!doCreateAccount";
		FinalHttp fh = new FinalHttp();

		/**
		 * 设置错误连接次数
		 */
		fh.configRequestExecutionRetryCount(0);
		fh.post(url, paramMap, new AjaxCallBack<Object>() {

			@Override
			public void onSuccess(Object t) {
				resHandler(context, t, telphone, pass);
			}

			@Override
			public void onFailure(Throwable t, int errorNo,
					String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				Toast.makeText(context, "网络不正常，请稍后再试", 5).show();
			}

		});

	}

	private static void resHandler(Activity context, Object t,
			String telphone, String pass) {
		JSONObject jo = null;
		try {
			jo = new JSONObject(t.toString());
			Toast.makeText(context, jo.getString("message"), 5)
					.show();
			if (jo.getString("code").equals("1")) {
				String userString = jo.getString("data");
				UserPO userPO = SmartHomeJsonUtil
						.getUserPOFrom(userString);
				if (userPO != null) {
					DealWithAccount.saveUser(context, userPO);
					DealWithAccount.saveAccountAndPwd(context,
							telphone, pass);
				}
				Intent intent = new Intent();
				intent.setClass(context, LoginActivity.class);
				context.startActivity(intent);
				LocationClient mLocationClient = ((SHApplication) context
						.getApplication()).mLocationClient;
				mLocationClient.stop();
				context.finish();
			}

		} catch (JSONException e) {
			Ln.e(e);
			Toast.makeText(context, "提交失败", 5).show();
		}

	}
}
