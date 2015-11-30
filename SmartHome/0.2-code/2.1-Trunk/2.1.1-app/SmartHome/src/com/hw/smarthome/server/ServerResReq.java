package com.hw.smarthome.server;

import java.util.Map;

import org.json.JSONException;

import com.hw.smarthome.login.ChangePassActivity;
import com.hw.smarthome.login.LoginActivity;
import com.hw.smarthome.login.RegActivity;
import com.hw.smarthome.po.SoluChkSumPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.server.deal.DealWithDiscuss;
import com.hw.smarthome.server.deal.DealWithHome;
import com.hw.smarthome.server.deal.DealWithScene;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.server.deal.DealWithSoluChkSum;
import com.hw.smarthome.server.deal.DealWithSolution;
import com.hw.smarthome.ui.MainActivity;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.SafeAsyncTask;
import com.hw.util.context.AppContext;

/**
 * 服务器信息处理
 * 
 * @author 曾凡
 * @time 2014年6月26日 下午3:48:30
 */
public class ServerResReq {

	/**
	 * 请求业务服务器
	 * 
	 * @author 曾凡
	 * @param activity
	 *            当前的请求页面
	 * @param paramUri
	 *            URI参数列表
	 * @param type
	 *            请求的功能类型
	 * @time 2014年6月26日 下午4:03:20
	 */
	public static void requestServer(final String paramUri,
			final String actionName) {
		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
			public Boolean call() throws Exception {
				String json = null;
				try {
					if (AppContext.IS_TEST_SERVER) {
						json = testResJson(actionName);
					} else {
						json = NetUtil
								.httpGet(ServerConstant.SERVER_BASE_URI
										+ paramUri);
					}
				} catch (Exception e) {
					Ln.e(e, "获取服务端json串异常："
							+ ServerConstant.SERVER_BASE_URI
							+ paramUri);
				} finally {
					/* 必须执行，处理服务端的请求 */
					handleRespon(json, actionName);
				}
				return true;
			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {
			}

			@Override
			protected void onFinally() throws RuntimeException {
				// hideProgress();
			}
		};
		authenticationTask.execute();
	}

	/**
	 * [SH01_02_03_02]获取单条解决方案数据
	 * 
	 * @author 曾凡
	 * @param paramUri
	 * @param actionName
	 * @time 2015年8月19日 下午1:28:17
	 */
	public static void requestSoluChkSumServer(
			final String paramUri, final SoluChkSumPO po,
			final String actionName) {
		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
			public Boolean call() throws Exception {
				String json = null;
				try {
					json = NetUtil
							.httpGet(ServerConstant.SERVER_BASE_URI
									+ paramUri);
				} catch (Exception e) {
					Ln.e(e, "获取服务端json串异常："
							+ ServerConstant.SERVER_BASE_URI
							+ paramUri);
				} finally {
					/* 必须执行，处理服务端的请求 */
					handleSoluChkSumRespon(json, po, actionName);
				}
				return true;
			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {
			}

			@Override
			protected void onFinally() throws RuntimeException {
				// hideProgress();
			}
		};
		authenticationTask.execute();
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
	public static void requestServer(
			final Map<String, String> params,
			final String paramUri, final String actionName) {

		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
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

				} finally {
					/* 必须执行，处理服务端的请求 */
					handleRespon(json, actionName);
				}
				return true;
			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {
			}

			@Override
			protected void onFinally() throws RuntimeException {
				// hideProgress();
			}
		};
		authenticationTask.execute();
	}

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
	public static void requestServerGet(
			final Map<String, String> params,
			final String paramUri, final String actionName) {

		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
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

				} finally {
					/* 必须执行，处理服务端的请求 */
					handleRespon(json, actionName);
				}
				return true;
			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {
			}

			@Override
			protected void onFinally() throws RuntimeException {
				// hideProgress();
			}
		};
		authenticationTask.execute();
	}

	/**
	 * 
	 * @author lijing
	 * @param params
	 * @param paramUri
	 * @param actionName
	 * @param type
	 *            类别
	 * @time 2014-7-24 上午11:07:13
	 */
	public static void requestServer(
			final Map<String, String> params,
			final String paramUri, final String actionName,
			final int type) {

		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
			public Boolean call() throws Exception {
				String json = null;
				String url = null;
				try {
					url = ServerConstant.SERVER_BASE_URI
							+ paramUri;
					// String newUrl = NetUtil._MakeURL(url, params);
					// json = NetUtil.httpGet(newUrl);
					json = NetUtil.http_post(url, params, null);

				} catch (Exception e) {
					Ln.e(e, "获取服务端json串异常："
							+ ServerConstant.SERVER_BASE_URI
							+ paramUri);

				} finally {
					/* 必须执行，处理服务端的请求 */
					handleRespon(json, actionName, type);
				}
				return true;
			}

			@Override
			protected void onException(final Exception e)
					throws RuntimeException {
			}

			@Override
			public void onSuccess(final Boolean authSuccess) {
			}

			@Override
			protected void onFinally() throws RuntimeException {
				// hideProgress();
			}
		};
		authenticationTask.execute();
	}

	/**
	 * 生成测试返回的Json
	 * 
	 * @author 曾凡
	 * @param type
	 * @return
	 * @time 2014年6月26日 下午5:13:42
	 */
	private static String testResJson(String type) {
		/* [SH01_01_01_02] 发现设备 上传传感器 */
		if (ServerConstant.SH01_01_01_02.equals(type)) {
			return "{\"code\":\"1\",\"count\":2,\"data\":null,\"dataObject\":[{\"sensorList\":[{\"air\":{\"co2\":\"12\",\"name\":\"空气传感器\",\"createTime\":\"2014-06-25 13:45\",\"humidity\":\"15\",\"pm25\":\"155\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"34\"},\"alert\":{\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\",\"sensorId\":\"1111\"},\"sensorId\":\"\"},{\"alert\":{\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\",\"sensorId\":\"1111\"},\"gas\":{\"name\":\"燃气传感器\",\"ch4\":\"12\",\"co\":\"14\",\"createTime\":\"2014-06-25 13:45\",\"sensorId\":\"5FFFFFFF\",\"switchStatus\":\"关\"},\"sensorId\":\"\"}]}],\"message\":\"查询数据成功\",\"tableName\":\"D002\"}";
		}
		/* [SH01_01_01_03]查看已发现设备 */
		else if (ServerConstant.SH01_01_01_03.equals(type)) {
			return "{\"code\":\"1\",\"count\":2,\"data\":null,\"dataObject\":[{\"sensorList\":[{\"air\":{\"sensorId\":\"5FFFFFFF\",\"temperature\":\"34\",\"humidity\":\"15\",\"co2\":\"12\",\"pm25\":\"155\",\"ch2o\":\"110\",\"c6h6\":\"57\",\"createTime\":\"2014-06-25 13:45\"},\"alert\":{\"sensorId\":\"5FFFFFFF\",\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\"}},{\"gas\":{\"sensorId\":\"10000001\",\"switchStatus\":\"关\",\"co\":\"14\",\"ch4\":\"12\",\"createTime\":\"2014-06-25 13:45\"},\"alert\":{\"sensorId\":\"10000001\",\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\"}}]}],\"message\":\"查询数据成功\",\"tableName\":\"D002\"}";
		}
		/* [SH01_01_01_04] 添加/删除设备 */
		else if (ServerConstant.SH01_01_01_04.equals(type)) {
			return "{\"code\":\"1\",\"count\":2,\"data\":null,\"dataObject\":[{\"sensorList\":[{\"air\":{\"co2\":\"12\",\"name\":\"空气传感器\",\"createTime\":\"2014-06-25 13:45\",\"humidity\":\"15\",\"pm25\":\"155\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"34\"},\"alert\":{\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\",\"sensorId\":\"1111\"},\"sensorId\":\"\"},{\"alert\":{\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\",\"sensorId\":\"1111\"},\"gas\":{\"name\":\"燃气传感器\",\"ch4\":\"12\",\"co\":\"14\",\"createTime\":\"2014-06-25 13:45\",\"sensorId\":\"5FFFFFFF\",\"switchStatus\":\"关\"},\"sensorId\":\"\"}]}],\"message\":\"查询数据成功\",\"tableName\":\"D002\"}";
		}
		/* [SH01_02_01]查看历史信息 */
		else if (ServerConstant.SH01_02_01_03.equals(type)) {
			return "{\"code\":\"1\",\"count\":14,\"data\":null,\"dataObject\":[{\"sensorList\":[{\"air\":{},\"alert\":{},\"gas\":{\"ch4\":\"23420140717\",\"co\":\"12320140717\",\"createTime\":\"20140717\",\"name\":\"燃气1\",\"sensorId\":\"10000001\",\"switchStatus\":\"开\"},\"name\":\"燃气1\",\"online\":false,\"sensorId\":\"10000001\",\"viewName\":\"\"},{\"air\":{},\"alert\":{},\"gas\":{\"ch4\":\"23420140716\",\"co\":\"12320140716\",\"createTime\":\"20140716\",\"name\":\"燃气1\",\"sensorId\":\"10000001\",\"switchStatus\":\"开\"},\"name\":\"燃气1\",\"online\":false,\"sensorId\":\"10000001\",\"viewName\":\"\"},{\"air\":{},\"alert\":{},\"gas\":{\"ch4\":\"23420140715\",\"co\":\"12320140715\",\"createTime\":\"20140715\",\"name\":\"燃气1\",\"sensorId\":\"10000001\",\"switchStatus\":\"开\"},\"name\":\"燃气1\",\"online\":false,\"sensorId\":\"10000001\",\"viewName\":\"\"},{\"air\":{},\"alert\":{},\"gas\":{\"ch4\":\"23420140714\",\"co\":\"12320140714\",\"createTime\":\"20140714\",\"name\":\"燃气1\",\"sensorId\":\"10000001\",\"switchStatus\":\"开\"},\"name\":\"燃气1\",\"online\":false,\"sensorId\":\"10000001\",\"viewName\":\"\"},{\"air\":{},\"alert\":{},\"gas\":{\"ch4\":\"23420140713\",\"co\":\"12320140713\",\"createTime\":\"20140713\",\"name\":\"燃气1\",\"sensorId\":\"10000001\",\"switchStatus\":\"开\"},\"name\":\"燃气1\",\"online\":false,\"sensorId\":\"10000001\",\"viewName\":\"\"},{\"air\":{},\"alert\":{},\"gas\":{\"ch4\":\"23420140712\",\"co\":\"12320140712\",\"createTime\":\"20140712\",\"name\":\"燃气1\",\"sensorId\":\"10000001\",\"switchStatus\":\"开\"},\"name\":\"燃气1\",\"online\":false,\"sensorId\":\"10000001\",\"viewName\":\"\"},{\"air\":{},\"alert\":{},\"gas\":{\"ch4\":\"23420140711\",\"co\":\"12320140711\",\"createTime\":\"20140711\",\"name\":\"燃气1\",\"sensorId\":\"10000001\",\"switchStatus\":\"开\"},\"name\":\"燃气1\",\"online\":false,\"sensorId\":\"10000001\",\"viewName\":\"\"},{\"air\":{\"c6h6\":\"41520140717\",\"ch2o\":\"13120140717\",\"co2\":\"10120140717\",\"createTime\":\"20140717\",\"humidity\":\"78920140717\",\"name\":\"空气\",\"pm25\":\"11220140717\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"45620140717\"},\"alert\":{},\"gas\":{},\"name\":\"空气\",\"online\":false,\"sensorId\":\"5FFFFFFF\",\"viewName\":\"\"},{\"air\":{\"c6h6\":\"41520140716\",\"ch2o\":\"13120140716\",\"co2\":\"10120140716\",\"createTime\":\"20140716\",\"humidity\":\"78920140716\",\"name\":\"空气\",\"pm25\":\"11220140716\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"45620140716\"},\"alert\":{},\"gas\":{},\"name\":\"空气\",\"online\":false,\"sensorId\":\"5FFFFFFF\",\"viewName\":\"\"},{\"air\":{\"c6h6\":\"41520140715\",\"ch2o\":\"13120140715\",\"co2\":\"10120140715\",\"createTime\":\"20140715\",\"humidity\":\"78920140715\",\"name\":\"空气\",\"pm25\":\"11220140715\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"45620140715\"},\"alert\":{},\"gas\":{},\"name\":\"空气\",\"online\":false,\"sensorId\":\"5FFFFFFF\",\"viewName\":\"\"},{\"air\":{\"c6h6\":\"41520140714\",\"ch2o\":\"13120140714\",\"co2\":\"10120140714\",\"createTime\":\"20140714\",\"humidity\":\"78920140714\",\"name\":\"空气\",\"pm25\":\"11220140714\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"45620140714\"},\"alert\":{},\"gas\":{},\"name\":\"空气\",\"online\":false,\"sensorId\":\"5FFFFFFF\",\"viewName\":\"\"},{\"air\":{\"c6h6\":\"41520140713\",\"ch2o\":\"13120140713\",\"co2\":\"10120140713\",\"createTime\":\"20140713\",\"humidity\":\"78920140713\",\"name\":\"空气\",\"pm25\":\"11220140713\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"45620140713\"},\"alert\":{},\"gas\":{},\"name\":\"空气\",\"online\":false,\"sensorId\":\"5FFFFFFF\",\"viewName\":\"\"},{\"air\":{\"c6h6\":\"41520140712\",\"ch2o\":\"13120140712\",\"co2\":\"10120140712\",\"createTime\":\"20140712\",\"humidity\":\"78920140712\",\"name\":\"空气\",\"pm25\":\"11220140712\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"45620140712\"},\"alert\":{},\"gas\":{},\"name\":\"空气\",\"online\":false,\"sensorId\":\"5FFFFFFF\",\"viewName\":\"\"},{\"air\":{\"c6h6\":\"41520140711\",\"ch2o\":\"13120140711\",\"co2\":\"10120140711\",\"createTime\":\"20140711\",\"humidity\":\"78920140711\",\"name\":\"空气\",\"pm25\":\"11220140711\",\"sensorId\":\"5FFFFFFF\",\"temperature\":\"45620140711\"},\"alert\":{},\"gas\":{},\"name\":\"空气\",\"online\":false,\"sensorId\":\"5FFFFFFF\",\"viewName\":\"\"}]}],\"message\":\"查询数据成功\",\"tableName\":\"D002\"}";
		}
		/* [SH01_02_02_01]查看设备实时数据 */
		else if (ServerConstant.SH01_02_02_01.equals(type)) {
			return "{\"code\":\"1\",\"count\":2,\"data\":null,\"dataObject\":[{\"sensorList\":[{\"air\":{\"sensorId\":\"5FFFFFFF\",\"temperature\":\"34\",\"humidity\":\"15\",\"co2\":\"12\",\"pm25\":\"155\",\"ch2o\":\"110\",\"c6h6\":\"57\",\"createTime\":\"2014-06-25 13:45\"},\"alert\":{\"sensorId\":\"5FFFFFFF\",\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\"}},{\"gas\":{\"sensorId\":\"10000001\",\"switchStatus\":\"关\",\"co\":\"14\",\"ch4\":\"12\",\"createTime\":\"2014-06-25 13:45\"},\"alert\":{\"sensorId\":\"10000001\",\"alertStatus\":\"不正常\",\"alertType\":\"温度\",\"alertValue\":\"60\",\"creatTime\":\"2014-06-25 13:45\"}}]}],\"message\":\"查询数据成功\",\"tableName\":\"D002\"}";
		}
		/* 讨论区主题 */
		else if (ServerConstant.SH01_05_01_01_01.equals(type)) {
			return "{\"code\":\"1\",\"count\":1,\"data\":null,\"dataObject\":[{\"ma001\":\"5FFFFFFF\",\"ma002\":\"我的传感器连不上网怎么办？\",\"ma003\":\"15\",\"ma004\":\"12\"},{\"ma001\":\"5FFFFFFF\",\"ma002\":\"系统太复杂了，能简单点吗？\",\"ma003\":\"15\",\"ma004\":\"12\"} ],\"message\":\"查询数据成功\",\"tableName\":\"BI_DISCUSSION\"}";
		}
		/* 留言板主题内容 */
		else if (ServerConstant.SH01_05_01_01_02.equals(type)) {
			return "{\"code\":\"1\",\"count\":1,\"data\":null,\"dataObject\":[{\"ma001\":\"5FFFFFFF\",\"ma002\":\"我的传感器连不上网怎么办？\",\"ma003\":\"15\",\"ma004\":\"12\"},{\"ma001\":\"5FFFFFFF\",\"ma002\":\"系统太复杂了，能简单点吗？\",\"ma003\":\"15\",\"ma004\":\"12\"} ],\"message\":\"查询数据成功\",\"tableName\":\"BI_DISCUSSION\"}";
		}
		/* 提交留言 */
		else if (ServerConstant.SH01_05_01_01_03.equals(type)) {
			return "{\"code\":\"1\",\"count\":1,\"data\":null,\"dataObject\":[{\"ma001\":\"5FFFFFFF\",\"ma002\":\"我的传感器连不上网怎么办？\",\"ma003\":\"15\",\"ma004\":\"12\"},{\"ma001\":\"5FFFFFFF\",\"ma002\":\"系统太复杂了，能简单点吗？\",\"ma003\":\"15\",\"ma004\":\"12\"} ],\"message\":\"查询数据成功\",\"tableName\":\"BI_DISCUSSION\"}";
		}
		return null;
	}

	/**
	 * 处理返回的数据
	 * 
	 * @author 曾凡
	 * @param json
	 *            返回的JSON串
	 * @param paramUri
	 *            URI参数列表
	 * @param name
	 *            请求的功能类型
	 * @time 2014年6月26日 下午4:32:11
	 */
	private static void handleRespon(String json, String name) {
		/* [SH01_01_01_02-4] 配置传感器 */
		if (ServerConstant.SH01_01_01_02.equals(name)
				|| ServerConstant.SH01_01_01_03.equals(name)
				|| ServerConstant.SH01_01_01_07.equals(name)
				|| ServerConstant.SH01_01_01_04.equals(name)) {
			DealWithSensor.dealSensor(MainActivity.mContext,
					json, name);
		}

		/* [SH01_03_01_02]编辑账号信息 */
		if (ServerConstant.SH01_03_01_02.equals(name)) {
			DealWithAccount.updateUser(MainActivity.mContext,
					json);

		}
		/* [SH01_03_02_04]登录 */
		if (ServerConstant.SH01_03_02_04.equals(name)) {
			DealWithAccount.doLoginAccount(
					LoginActivity.mContext, json);

		}
		/* [SH01_03_02_02]注册账号 */

		if (ServerConstant.SH01_03_01_01.equals(name)) {
			DealWithAccount.doViewAccount(MainActivity.mContext,
					json);
		}
		/* [SH01_03_02_02]注册账号 */

		if (ServerConstant.SH01_03_02_01.equals(name)) {
			DealWithAccount.doRegAccount(RegActivity.mContext,
					json);

		}/* [SH01_03_02_02]查找密码 */
		if (ServerConstant.SH01_03_02_02.equals(name)) {
			DealWithAccount.doResetPw(
					ChangePassActivity.mContext, json);

		}/* [SH01_02_01] 查看历史信息 */
		/* [SH01_02_01_03] 查看过去7天历史信息 */
		if (ServerConstant.SH01_02_01_03.equals(name)
				|| ServerConstant.SH01_02_01_04.equals(name)
				|| ServerConstant.SH01_02_01_05.equals(name)) {

			DealWithHome.dealHistory(MainActivity.mContext,
					json, name);
		}
		/* [SH01_02_02_01]查看设备实时数据 */
		if (ServerConstant.SH01_02_02_01.equals(name)) {
			DealWithHome.dealDetail(MainActivity.mContext, json,
					name);
		}
		/* [SH01_03_02_03]修改密码 */
		if (ServerConstant.SH01_03_02_03.equals(name)) {
			try {
				DealWithAccount.doEditPw(MainActivity.mContext,
						json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* 讨论区主题 */
		if (ServerConstant.SH01_05_01_01_01.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name);
		}
		/* 提交讨论区主题发言 */
		if (ServerConstant.SH01_05_01_01_02.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name);
		}
		/* 讨论区主题内容 */
		if (ServerConstant.SH01_05_01_01_03.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name);
		}
		/* 留言板列表 */
		if (ServerConstant.SH01_05_01_02_02.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name);
		}
		/* 提交留言板信息 */
		if (ServerConstant.SH01_05_01_02_01.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name);
		}
		/* 排名 */
		if (ServerConstant.RANKING.equals(name)) {
			DealWithHome.dealRank(MainActivity.mContext, json,
					name);
		}
		/* [SH01_02_03_01]查看知识和解决方案 */
		if (ServerConstant.SH01_02_03_01.equals(name)) {
			DealWithSolution.dealSolution(MainActivity.mContext,
					json, name);
		}
		/* [SH01_02_03_02_01] 查看在线商城 */
		if (ServerConstant.SH01_02_03_02_01.equals(name)) {
			DealWithSolution.dealShopUrl(MainActivity.mContext,
					json, name);
		}
		/* [SH01_02_03_03_02] DIY课堂 */
		if (ServerConstant.SH01_02_03_02_02.equals(name)) {
			DealWithSolution.dealDIYUrl(MainActivity.mContext,
					json, name);
		}
		/* [SH01_02_03_04_03] 我要创业 */
		if (ServerConstant.SH01_02_03_02_03.equals(name)) {
			DealWithSolution.dealJoinUrl(MainActivity.mContext,
					json, name);
		}
		/* [SH01_06_04_01-4] 配置设备 */
		if (ServerConstant.SH01_06_04_01.equals(name)
				|| ServerConstant.SH01_06_04_02.equals(name)
				|| ServerConstant.SH01_06_04_03.equals(name)
				|| ServerConstant.SH01_06_04_04.equals(name)) {
			DealWithScene.dealScene(MainActivity.mContext, json,
					name);
		}
	}

	/**
	 * 将type回传
	 * 
	 * @author lijing
	 * @param json
	 * @param name
	 * @param type
	 * @time 2014-7-24 上午11:09:37
	 */
	private static void handleRespon(String json, String name,
			int type) {
		/* [SH01_01_01_02-4] 配置传感器 */
		if (ServerConstant.SH01_01_01_02.equals(name)
				|| ServerConstant.SH01_01_01_03.equals(name)
				|| ServerConstant.SH01_01_01_07.equals(name)
				|| ServerConstant.SH01_01_01_04.equals(name)) {
			DealWithSensor.dealSensor(MainActivity.mContext,
					json, name);
		}

		/* [SH01_03_01_02]编辑账号信息 */
		if (ServerConstant.SH01_03_01_02.equals(name)) {
			DealWithAccount.updateUser(MainActivity.mContext,
					json);

		}
		/* [SH01_03_02_04]登录 */
		if (ServerConstant.SH01_03_02_04.equals(name)) {
			DealWithAccount.doLoginAccount(
					LoginActivity.mContext, json);

		}
		/* [SH01_03_02_02]注册账号 */
		if (ServerConstant.SH01_03_01_01.equals(name)) {
			DealWithAccount.doViewAccount(MainActivity.mContext,
					json);
		}
		/* [SH01_03_02_02]注册账号 */

		if (ServerConstant.SH01_03_02_01.equals(name)) {
			DealWithAccount.doRegAccount(RegActivity.mContext,
					json);

		}/* [SH01_03_02_02]查找密码 */
		if (ServerConstant.SH01_03_02_02.equals(name)) {
			DealWithAccount.doResetPw(
					ChangePassActivity.mContext, json);

		}/* [SH01_02_01] 查看历史信息 */
		if (ServerConstant.SH01_02_01_03.equals(name)) {
			DealWithHome.dealHistory(MainActivity.mContext,
					json, name);
		}
		/* [SH01_02_02_01]查看设备实时数据 */
		if (ServerConstant.SH01_02_02_01.equals(name)) {
			DealWithHome.dealDetail(MainActivity.mContext, json,
					name);
		}
		/* [SH01_03_02_03]修改密码 */
		if (ServerConstant.SH01_03_02_03.equals(name)) {
			try {
				DealWithAccount.doEditPw(
						ChangePassActivity.mContext, json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/* 讨论区主题 */
		if (ServerConstant.SH01_05_01_01_01.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name, type);
		}
		/* 提交讨论区主题发言 */
		if (ServerConstant.SH01_05_01_01_02.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name, type);
		}
		/* 讨论区主题内容 */
		if (ServerConstant.SH01_05_01_01_03.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name, type);
		}
		/* 留言板列表 */
		if (ServerConstant.SH01_05_01_02_02.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name, type);
		}
		/* 提交留言板信息 */
		if (ServerConstant.SH01_05_01_02_01.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name, type);
		}

		if (ServerConstant.SH01_02_01_02.equals(name)) {
			DealWithDiscuss.dealDiscuss(MainActivity.mContext,
					json, name, type);
		}
	}

	private static void handleSoluChkSumRespon(String json,
			SoluChkSumPO po, String actionName) {
		DealWithSoluChkSum.dealSolution(MainActivity.mContext,
				json, po, actionName);
	}

}
