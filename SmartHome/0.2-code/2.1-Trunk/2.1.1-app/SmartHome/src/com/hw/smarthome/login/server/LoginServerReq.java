package com.hw.smarthome.login.server;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hw.smarthome.login.LoginActivity;
import com.hw.smarthome.login.RegActivity;
import com.hw.smarthome.po.UserPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.server.util.SmartHomeJsonUtil;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.SafeAsyncTask;
import com.hw.util.UIUtil;

public class LoginServerReq {
	/**
	 * 请求业务服务器 GEt
	 * 
	 * @author 闫威
	 * @param params
	 *            请求参数
	 * @param paramUri
	 *            URI接口名称
	 * @param actionName
	 *            请求的功能类型
	 * @time 2014年7月7日 下午4:03:20
	 */
	private Activity mActivity;
	private View mView;
	protected PopProgress popProgress = null;

	public LoginServerReq(Activity activity) {
		mActivity = activity;
	}

	public LoginServerReq(Activity activity, View view) {
		mActivity = activity;
		mView = view;
	}

	/**
	 * 请求业务服务器
	 * 
	 * @author 闫威
	 * @param params
	 *            请求参数
	 * @param paramUri
	 *            URI接口名称
	 * @param actionName
	 *            请求的功能类型
	 * @time 2014年7月7日 下午4:03:20
	 */
	public void requestServer(final Map<String, String> params,
			final String paramUri, final String actionName) {
		if (NetUtil.getNetworkType(mActivity) == 0) {
			UIUtil.ToastMessage(mActivity, "请打开网络...");
			return;
		}
		if (!NetUtil.isNetworkConnected(mActivity)) {
			UIUtil.ToastMessage(mActivity, "请检查网络状态...");
			return;
		}

		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
			@SuppressWarnings("finally")
			public Boolean call() throws Exception {
				String json = null;
				String url = null;
				try {
					url = ServerConstant.SERVER_BASE_URI
							+ paramUri;

					json = NetUtil.http_post(url, params, null);

				} catch (Exception e) {
					Ln.e(e, "获取服务端json串异常："
							+ ServerConstant.SERVER_BASE_URI
							+ paramUri);
					return false;
				} finally {
					/* 必须执行，处理服务端的请求 */
					return handleRespon(json, actionName);
				}

			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {

				if (authSuccess) {

					UIUtil.ToastMessage(mActivity, jss);
					Intent intent = new Intent();
					// intent.setClass(LoginActivity.this,
					// ForgetPassActivity.class);
					intent.setClass(mActivity,
							MainActivity.class);
					mActivity.startActivity(intent);
					mActivity.finish();
				} else {
					if (jss != null && jss.length() > 0) {
						if (jss.contains("200")) {
							jss = "发送短信成功";
						} else if (jss.contains("512")) {
							jss = "服务器拒绝访问，或者拒绝操作";
						} else if (jss.contains("513")) {
							jss = "验证码求Appkey不存在或被禁用";
						} else if (jss.contains("514")) {
							jss = "验证码权限不足";
						} else if (jss.contains("515")) {
							jss = "验证码服务器内部错误";
						} else if (jss.contains("517")) {
							jss = "缺少必要的请求参数";
						} else if (jss.contains("518")) {
							jss = "请求中用户的手机号格式不正确（包括手机的区号）";
						} else if (jss.contains("519")) {
							jss = "请求发送验证码次数超出限制";
						} else if (jss.contains("520")) {
							jss = "无效验证码";
						} else if (jss.contains("526")) {
							jss = "验证码短信余额不足";
						}

						UIUtil.ToastMessage(mActivity, jss);
						jss = null;
					}
				}

			}

			@Override
			protected void onFinally() throws RuntimeException {

			}
		};
		authenticationTask.execute();
	}

	private String jss;

	public void requestServerGet(
			final Map<String, String> params,
			final String paramUri, final String actionName) {
		popProgress = PopProgress.getInstance(mActivity, mView);
		popProgress.setText("登录中");
		popProgress.showProgress();
		if (NetUtil.getNetworkType(mActivity) == 0) {
			UIUtil.ToastMessage(mActivity, "请打开网络...");
		}
		if (!NetUtil.isNetworkConnected(mActivity)) {
			UIUtil.ToastMessage(mActivity, "请检查网络状态...");
		}
		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
			@SuppressWarnings("finally")
			public Boolean call() throws Exception {
				String json = null;
				String url = null;
				try {
					url = ServerConstant.SERVER_BASE_URI
							+ paramUri;

					String newUrl = NetUtil
							._MakeURL(url, params);
					json = NetUtil.httpGet(newUrl);

				} catch (Exception e) {
					Ln.e(e, "获取服务端json串异常："
							+ ServerConstant.SERVER_BASE_URI
							+ paramUri);
					return false;

				} finally {
					/* 必须执行，处理服务端的请求 */
					return handleRespon(json, actionName);
				}

			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {
				// if (authSuccess) {
				// popProgress.showResult(true, "登录成功");
				// } else {
				// popProgress.showResult(false, "登录失败");
				// }
				if (jss != null && jss.length() > 0) {
					UIUtil.ToastMessage(mActivity, jss);
					jss = null;
				}

			}

			@Override
			protected void onFinally() throws RuntimeException {
				// hideProgress();
				// popProgress.hiddenProgerss();
			}
		};
		authenticationTask.execute();
	}

	public void requestServerGetLogin(
			final Map<String, String> params,
			final String paramUri, final String actionName) {
		popProgress = PopProgress.getInstance(mActivity, mView,
				10000);

		if (NetUtil.getNetworkType(mActivity) == 0) {

			popProgress.setText("请打开网络...");
			return;
		}
		if (!NetUtil.isNetworkConnected(mActivity)) {

			popProgress.setText("请检查网络状态...");
			return;
		}
		popProgress.setText("登录中");
		popProgress.showProgress();

		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
			@SuppressWarnings("finally")
			public Boolean call() throws Exception {
				String json = null;
				String url = null;
				try {
					url = ServerConstant.SERVER_BASE_URI
							+ paramUri;

					String newUrl = NetUtil
							._MakeURL(url, params);
					json = NetUtil.httpGet(newUrl);

				} catch (Exception e) {
					Ln.e(e, "获取服务端json串异常："
							+ ServerConstant.SERVER_BASE_URI
							+ paramUri);
					return false;

				} finally {
					/* 必须执行，处理服务端的请求 */
					return handleRespon(json, actionName);
				}

			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {
				try {
					if (authSuccess) {
						popProgress.showResult(true, "登录成功");
					} else {
						popProgress.showResult(false, "登录失败");
					}
					popProgress = null;
				} catch (Exception e) {

				}
			}

			@Override
			protected void onFinally() throws RuntimeException {
				if (popProgress != null) {
					popProgress.showResult(true, "连接失败,请稍后重试");

				}

			}
		};
		authenticationTask.execute();
	}

	private boolean handleRespon(String json, String name) {
		/* [SH01_03_02_01]注册 */
		if (ServerConstant.SH01_03_02_01.equals(name)) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(json);
				jss = jsonObject.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return doRegAccount(RegActivity.mContext, json);

		}
		/* [SH01_03_02_04]登录 */
		if (ServerConstant.SH01_03_02_04.equals(name)) {
			return doLoginAccount(mActivity, json);

		}

		/* [SH01_03_02_02]查找密码 */
		if (ServerConstant.SH01_03_02_02.equals(name)) {
			JSONObject jsonObject;
			try {
				jsonObject = new JSONObject(json);
				jss = jsonObject.getString("message");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return doResetPw(mActivity, json);

		}
		JSONObject jsonObject;
		try {
			jsonObject = new JSONObject(json);
			jss = jsonObject.getString("message");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}

	/**
	 * 
	 * 处理[SH01_01_01_01]登录
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public boolean doLoginAccount(Context context, String json) {
		boolean result = false;
		if (json != null && !json.equals("null")) {

			try {
				JSONObject jsonObject = new JSONObject(json);
				String code = jsonObject.getString("code");
				int count = jsonObject.getInt("count");

				if (code.equals("1") && count == 1) {

					/* 1.解析json */

					String userString = jsonObject
							.getString("data");
					UserPO userPO = SmartHomeJsonUtil
							.getUserPOFrom(userString);
					if (userPO != null) {
						/* 2.将结果持久化 */
						DealWithAccount
								.saveUser(context, userPO);
						SmartHomeService.setUser(
								userPO.getMa001(),
								userPO.getMa010());
						result = true;
					}
				} else if (code.equals("0")) {
					result = false;
				}
				// popProgress.hiddenProgerss();
				LoginActivity.onAuthenticationResult(result);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			} finally {
				/* 3.发送处理结果广播，页面随之更新 */

			}

		}
		return result;
	}

	/**
	 * 
	 * 处理[SH01_03_02_01]注册账号
	 * 
	 * @author 闫威
	 * @param context
	 * @param json
	 * @time 2014年6月26日 下午4:45:53
	 */
	public boolean doRegAccount(Context context, String json) {
		boolean result = false;
		if (json != null && !json.equals("null")) {

			try {
				JSONObject jsonObject = new JSONObject(json);
				String code = jsonObject.getString("code");
				int count = jsonObject.getInt("count");

				if (code.equals("1") && count == 1) {

					/* 1.解析json */

					String userString = jsonObject
							.getString("data");
					UserPO userPO = SmartHomeJsonUtil
							.getUserPOFrom(userString);
					if (userPO != null) {
						/* 2.将结果持久化 */
						DealWithAccount
								.saveUser(context, userPO);
						result = true;
					}
				} else if (code.equals("0")) {
					result = false;
					// UIUtil.ToastMessage(context,
					// jsonObject.getString("message"));
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			} finally {
				/* 3.发送处理结果广播，页面随之更新 */

			}

		}
		return result;
	}

	/*
	 * 处理[SH01_03_02_02]寻找密码
	 * 
	 * @author 闫威
	 * 
	 * @param context
	 * 
	 * @param json
	 * 
	 * @time 2014年6月26日 下午4:45:53
	 */
	public boolean doResetPw(Context context, String json) {
		boolean result = false;
		if (json != null && !json.equals("null")) {

			try {
				JSONObject jsonObject = new JSONObject(json);
				String code = jsonObject.getString("code");
				int count = jsonObject.getInt("count");

				if (code.equals("1") && count == 1) {

					/* 1.解析json */

					String userString = jsonObject
							.getString("data");
					UserPO userPO = SmartHomeJsonUtil
							.getUserPOFrom(userString);
					if (userPO != null) {
						/* 2.将结果持久化 */
						DealWithAccount
								.saveUser(context, userPO);
						result = true;
					}
				} else if (code.equals("0")) {
					result = false;
				}

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result = false;
			} finally {
				/* 3.发送处理结果广播，页面随之更新 */

			}

		}
		return result;
	}
}
