package com.hw.weidou.server;

import java.util.Map;

import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.SafeAsyncTask;
import com.hw.util.context.AppContext;
import com.hw.weidou.server.constant.ServerConstant;

/**
 * 服务器信息处理（可以继承）
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
		Ln.i("请求" + paramUri);
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

					// if (AppContext.IS_TEST_SERVER) {
					// json = new JSONObject(
					// testResJson(actionName));
					// } else {
					json = NetUtil.http_post(url, params, null);
					// }

					// JSONArray jsonObject = json.getJSONArray("dataObject");

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
		/* 讨论区主题 */
		if (ServerConstant.SH01_05_01_01_01.equals(type)) {
			return "{\"code\":\"1\",\"count\":1,\"data\":null,\"dataObject\":[{\"ma001\":\"5FFFFFFF\",\"ma002\":\"我的传感器连不上网怎么办？\",\"ma003\":\"15\",\"ma004\":\"12\"},{\"ma001\":\"5FFFFFFF\",\"ma002\":\"系统太复杂了，能简单点吗？\",\"ma003\":\"15\",\"ma004\":\"12\"} ],\"message\":\"查询数据成功\",\"tableName\":\"BI_DISCUSSION\"}";
		}
		/* 留言板主题内容 */
		if (ServerConstant.SH01_05_01_01_02.equals(type)) {
			return "{\"code\":\"1\",\"count\":1,\"data\":null,\"dataObject\":[{\"ma001\":\"5FFFFFFF\",\"ma002\":\"我的传感器连不上网怎么办？\",\"ma003\":\"15\",\"ma004\":\"12\"},{\"ma001\":\"5FFFFFFF\",\"ma002\":\"系统太复杂了，能简单点吗？\",\"ma003\":\"15\",\"ma004\":\"12\"} ],\"message\":\"查询数据成功\",\"tableName\":\"BI_DISCUSSION\"}";
		}
		/* 提交留言 */
		if (ServerConstant.SH01_05_01_01_03.equals(type)) {
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
		Ln.i("处理了：" + name);
		/* [SH01_03_01_02]编辑账号信息 */
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
		Ln.i("处理了：" + name);
	}
}
