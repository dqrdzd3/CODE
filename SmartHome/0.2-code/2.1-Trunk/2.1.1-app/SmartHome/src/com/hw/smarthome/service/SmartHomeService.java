package com.hw.smarthome.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.easylink.android.utils.EasyLinkWifiManager;
import com.google.gson.Gson;
import com.hw.smarthome.po.ParamMapPO;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SoluChkSumPO;
import com.hw.smarthome.server.ServerResReq;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.base.SmartHomeServiceBase;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.service.wifi.DealWithWiFiNew;
import com.hw.util.Ln;
import com.hw.util.SafeAsyncTask;

/**
 * @author 曾凡
 * @time 2014年6月26日 下午3:47:12
 */
public class SmartHomeService extends SmartHomeServiceBase {
	private Handler toastHandler = null;

	public static void setUser(String id1, String id2) {
		userId = id1;
		sessionId = id2;
	}

	public static String[] getUser() {
		return new String[] { userId, sessionId };
	}

	/**
	 * [SH01_01_01_02] 发现设备 上传传感器
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	public void executeSH01_01_01_02(String sensorId) {
		int type = SmartHomeServiceUtil
				.getSensorTypeWithId(sensorId);
		String name = SmartHomeServiceUtil
				.getDefaultSensorName(type);
		Map<String, String> paramsMap = new HashMap<String, String>();
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			paramsMap.put("DRIVERTYPE", type + "");
			paramsMap.put("DRIVERID", sensorId);
			paramsMap.put("DRIVERNAME", name);
			ServerResReq.requestServer(paramsMap,
					"d002!doSaveAddEquips",
					ServerConstant.SH01_01_01_02);
		}

	}

	public void executeSH01_01_01_07(String sensorId,
			String dname) {
		int type = SmartHomeServiceUtil
				.getSensorTypeWithId(sensorId);
		String name = SmartHomeServiceUtil
				.getDefaultSensorName(type);
		Map<String, String> paramsMap = new HashMap<String, String>();
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			paramsMap.put("DRIVERTYPE", type + "");
			paramsMap.put("DRIVERID", sensorId);
			paramsMap.put("DRIVERNAME", dname);
			ServerResReq.requestServer(paramsMap,
					"d002!doSaveEditEquips",
					ServerConstant.SH01_01_01_07);

		}

	}

	public void executeSH01_01_01_07(String sensorId,
			String dname, String ctrl) {
		int type = SmartHomeServiceUtil
				.getSensorTypeWithId(sensorId);
		String name = SmartHomeServiceUtil
				.getDefaultSensorName(type);
		Map<String, String> paramsMap = new HashMap<String, String>();
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			paramsMap.put("DRIVERTYPE", type + "");
			paramsMap.put("DRIVERID", sensorId);
			paramsMap.put("DRIVERNAME", dname);
			paramsMap.put("CTRL", ctrl);
			ServerResReq.requestServer(paramsMap,
					"d002!doSaveEditEquips",
					ServerConstant.SH01_01_01_07);

		}

	}

	private DealWithWiFiNew wifi = null;
	private EasyLinkWifiManager mWifiManager = null;
	public static int ipAddr;

	public EasyLinkWifiManager getWiFiManagerInstance() {
		if (mWifiManager == null) {
			mWifiManager = new EasyLinkWifiManager(mContext);
			ipAddr = mWifiManager
					.getCurrentIpAddressConnectedInt();
		}
		return mWifiManager;
	}

	/**
	 * easylink3.1 配网
	 * 
	 * @author 曾凡
	 * @param ssid
	 * @param passwd
	 * @time 2015年9月10日 下午2:25:39
	 */
	@Override
	public void executeSH01_01_01_05(final String ssid,
			final String passwd) {

		if (getWiFiManagerInstance().getCurrentSSID() == null
				&& getWiFiManagerInstance().getCurrentSSID()
						.length() == 0) {
			Ln.e("EasyLinkWifiManager没有找到SSID");
			return;
		}
		if ((ssid == null || "".equals(ssid))
				|| (passwd == null || "".equals(passwd))) {
			Ln.e("配网传值为空");
			return;
		}

		if (wifi != null) {
			wifi.stopPacketData2();
			wifi = null;
		}
		SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>() {
			public Boolean call() throws Exception {
				try {
					wifi = DealWithWiFiNew.getInstance(mContext);
					wifi.sendPacketData2(ssid, passwd, ipAddr);
					toastHandler = new Handler(
							Looper.getMainLooper());
					toastHandler.post(new Runnable() {
						@Override
						public void run() {
							Toast.makeText(
									getApplicationContext(),
									"后台开始配网", Toast.LENGTH_SHORT)
									.show();
						}
					});
					Timer timer = new Timer();
					TimerTask task = new TimerTask() {
						public void run() {
							toastHandler.post(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(
											getApplicationContext(),
											"后台结束",
											Toast.LENGTH_SHORT)
											.show();
								}
							});
							Ln.i("后台结束配网");
							wifi.stopPacketData2();
						}
					};
					// 正式 120s
					timer.schedule(task, 120 * 1000);
				} catch (Exception e) {
					Ln.e(e, "对WIFI模块进行配网异常");
				}
				return true;
			}
		};
		authenticationTask.execute();
	}

	// /**
	// * 配网
	// *
	// * @author 曾凡
	// * @param ssid
	// * @param passwd
	// * @time 2014年11月21日 下午7:47:18
	// */
	// @Override
	// public void executeSH01_01_01_05(final String ssid,
	// final String passwd) {
	// if ((ssid == null || "".equals(ssid))
	// || (passwd == null || "".equals(passwd))) {
	// return;
	// }
	//
	// if (wifi != null) {
	// wifi.stopPacketData();
	// wifi = null;
	// }
	// SafeAsyncTask<Boolean> authenticationTask = new SafeAsyncTask<Boolean>()
	// {
	// public Boolean call() throws Exception {
	// try {
	// wifi = new DealWithWIfi();
	// wifi.sendPacketData(mContext, ssid, passwd);
	// toastHandler = new Handler(
	// Looper.getMainLooper());
	// toastHandler.post(new Runnable() {
	// @Override
	// public void run() {
	// Toast.makeText(
	// getApplicationContext(),
	// "后台开始配网", Toast.LENGTH_SHORT)
	// .show();
	// }
	// });
	// Timer timer = new Timer();
	// TimerTask task = new TimerTask() {
	// public void run() {
	// toastHandler.post(new Runnable() {
	// @Override
	// public void run() {
	// Toast.makeText(
	// getApplicationContext(),
	// "后台结束",
	// Toast.LENGTH_SHORT)
	// .show();
	// }
	// });
	// Ln.i("后台结束配网");
	// wifi.stopPacketData();
	// }
	// };
	// // 正式 120s
	// timer.schedule(task, 120 * 1000);
	// } catch (Exception e) {
	// Ln.e(e, "对WIFI模块进行配网异常");
	// }
	// return true;
	// }
	// };
	// authenticationTask.execute();
	// }

	/**
	 * [SH01_01_01_03] 查看已发现设备
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_01_01_03() {
		if (userId != null && sessionId != null) {
			/* FIXME 修改[SH01_01_01_03] 查看已发现设备的接口参数名 */
			ServerResReq.requestServer(
					getUsrSesStr("d002!doListEquip"),
					ServerConstant.SH01_01_01_03);
		}
	}

	/**
	 * [SH01_02_02_01] 查看设备实时数据
	 * 
	 * @author 曾凡
	 * @time 2014年7月11日 上午10:25:44
	 */
	@Override
	public void executeSH01_02_02_01() {
		if (userId != null && sessionId != null) {
			/* FIXME 修改 [SH01_02_02_01]查看设备实时数据的接口参数名 */
			ServerResReq.requestServer(
					getUsrSesStr("d002!doViewDetail"),
					ServerConstant.SH01_02_02_01);
		}
	}

	/**
	 * [SH01_03_01_01] 查看用户信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_01_01() {
		StringBuilder sb = new StringBuilder(
				getUsrSesStr("d002!doEditListEquip"));
		sb.append("&USERID=").append(userId);
		sb.append("&SESSIONID=").append(sessionId);
		ServerResReq.requestServer("doListUser",
				ServerConstant.SH01_03_01_01);
	}

	/**
	 * [SH01_03_02_02] 账号注册
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_02_01(
			Map<String, String> paramsMap) {

		ServerResReq.requestServer(paramsMap,
				"u001!doCreateAccount",
				ServerConstant.SH01_03_02_01);

	}

	/**
	 * [SH01_03_02_02] 查找密码
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_02_02(
			Map<String, String> paramsMap) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			// 请求服务端

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramsMap,
					// "u001!doResetPw",
					"u001!shareSDKYzm",
					ServerConstant.SH01_03_02_02);
		}
	}

	/**
	 * [SH01_03_02_03] 修改密码
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	@Override
	public void executeSH01_03_02_03(
			Map<String, String> paramsMap) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServerGet(paramsMap,
					"u001!doEditPw",
					ServerConstant.SH01_03_02_03);
		}
	}

	/**
	 * [SH01_01_01_04] 删除设备
	 * 
	 * @author 曾凡
	 * @time 2014年7月3日 下午2:06:19
	 */
	@Override
	public void executeSH01_01_01_04(String sensorId) {
		if (userId != null && sessionId != null) {
			/* FIXME [SH01_01_01_04] 删除设备的接口参数名 */
			StringBuilder sb = new StringBuilder(
					getUsrSesStr("d002!doDeleteEquips"));
			sb.append("&DRIVERID=").append(sensorId);

			ServerResReq.requestServer(sb.toString(),
					ServerConstant.SH01_01_01_04);
		}
	}

	/**
	 * [SH01_02_01]查看历史信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月4日 上午10:10:31
	 */
	@Override
	public void executeSH01_02_01_03() {

		if (userId != null && sessionId != null) {
			/* [SH01_02_01]查看历史信息的接口参数名 */
			StringBuilder sb = new StringBuilder(
					getUsrSesStr("d002!doListDetailHistory"));
			// sb.append("&TYPE="+PreferenceUtil.getHisType(context));
			// sb.append("&LASTPAR="+PreferenceUtil.getHisLast(context));
			ServerResReq.requestServer(sb.toString(),
					ServerConstant.SH01_02_01_03);
		}
	}

	@Override
	public void executeSH01_02_01_04(String sensorId) {
		if (userId != null && sessionId != null) {
			/* [SH01_02_01]查看历史信息的接口参数名 */
			StringBuilder sb = new StringBuilder(
					getUsrSesStr("d002!doListDetailHistory"));
			sb.append("&SENSORID=").append(sensorId);
			sb.append("&TYPE=").append(
					ServerConstant.HISTORY_TYPE_HOUR);
			sb.append("&LASTPAR=").append(24);
			ServerResReq.requestServer(sb.toString(),
					ServerConstant.SH01_02_01_04);

		}
	}

	@Override
	public void executeSH01_02_01_05(String sensorId) {
		if (userId != null && sessionId != null) {
			/* [SH01_02_01]查看历史信息的接口参数名 */
			StringBuilder sb = new StringBuilder(
					getUsrSesStr("d002!doListDetailHistory"));
			sb.append("&SENSORID=").append(sensorId);
			sb.append("&TYPE=").append(
					ServerConstant.HISTORY_TYPE_DAY);
			sb.append("&LASTPAR=").append(30);
			ServerResReq.requestServer(sb.toString(),
					ServerConstant.SH01_02_01_05);
		}
	}

	@Override
	public void executeSH01_05_01_02() {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			Map<String, String> paramsMap = new HashMap<String, String>();
			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramsMap, "s004!doView",
					ServerConstant.SH01_05_01_01_01);
		}
	}

	@Override
	public void executeSH01_05_01_03() {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			/* FIXME [SH01_05_01_02]查看留言板的接口参数名 */
			StringBuilder sb = new StringBuilder(
					getUsrSesStr("doListDetailHistory"));
			ServerResReq.requestServer(sb.toString(),
					ServerConstant.SH01_05_01_01_02);
		}
	}

	/**
	 * 数据请求参数处理总方法
	 */
	@Override
	public void execute(Bundle bundle) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {
			ParamMapPO po = (ParamMapPO) bundle.get("param");
			Map<String, String> paramsMap = po.getParamMap();
			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			String name = bundle.getString("name");
			int type = bundle.getInt("type");
			ServerResReq.requestServer(paramsMap, name, name,
					type);
		}
	}

	/**
	 * 编辑用户信息
	 */
	@Override
	public void executeSH01_03_01_02(
			Map<String, String> paramsMap) {
		// TODO Auto-generated method stub
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramsMap,
					"u001!doEditInfo",
					ServerConstant.SH01_03_01_02);
		}
	}

	/**
	 * 登录
	 */
	@Override
	public void executeSH01_03_02_04(
			Map<String, String> paramsMap) {
		ServerResReq.requestServerGet(paramsMap, "u001!doLogin",
				ServerConstant.SH01_03_02_04);
	}

	@Override
	public void executeRanking(Map<String, String> paramsMap) {
		if (userId != null && sessionId != null) {

			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramsMap,
					ServerConstant.RANKING,
					ServerConstant.RANKING);
		}
	}

	@Override
	public void executeSH01_02_03_01() {
		if (userId != null && sessionId != null) {
			ServerResReq.requestServer(
					getUsrSesStr(ServerConstant.SH01_02_03_01),
					ServerConstant.SH01_02_03_01);
		}
	}

	/** [SH01_06_02_01]手动控制 */
	@Override
	public void executeSH01_06_02_01(SensorCtrlDetail po) {

		Map<String, String> paramsMap = new HashMap<String, String>();
		if (userId != null && sessionId != null) {
			paramsMap.put("USERID", userId);
			paramsMap.put("SESSIONID", sessionId);
			paramsMap.put("CTRLORDER", new Gson().toJson(po));
			ServerResReq.requestServer(paramsMap,
					ServerConstant.SH01_06_02_01,
					ServerConstant.SH01_06_02_01);
		}

	}

	/**
	 * [SH01_06_04_01] 添加场景 paramsMap.put("PO", new Gson().toJson(po));
	 */
	public void executeSH01_06_04_01(Map<String, String> paramMap) {
		// Map<String, String> paramsMap = new HashMap<String, String>();
		if (userId != null && sessionId != null) {
			paramMap.put("USERID", userId);
			paramMap.put("SESSIONID", sessionId);

			ServerResReq.requestServer(paramMap,
					ServerConstant.SH01_06_04_01,
					ServerConstant.SH01_06_04_01);
		}
	}

	/**
	 * [SH01_06_04_02] 修改场景 paramsMap.put("MA001", new Gson().toJson(po));
	 */

	public void executeSH01_06_04_02(Map<String, String> paramMap) {

		if (userId != null && sessionId != null) {
			paramMap.put("USERID", userId);
			paramMap.put("SESSIONID", sessionId);

			ServerResReq.requestServer(paramMap,
					ServerConstant.SH01_06_04_02,
					ServerConstant.SH01_06_04_02);
		}
	}

	/** [SH01_06_04_03] 查询场景 */
	public void executeSH01_06_04_03() {
		if (userId != null && sessionId != null) {
			ServerResReq.requestServer(
					getUsrSesStr(ServerConstant.SH01_06_04_03),
					ServerConstant.SH01_06_04_03);
		}
	}

	/**
	 * [SH01_06_04_04] 删除场景 paramsMap.put("MA001", new Gson().toJson(po));
	 */

	public void executeSH01_06_04_04(Map<String, String> paramMap) {
		// Map<String, String> paramsMap = new HashMap<String, String>();

		if (userId != null && sessionId != null) {
			paramMap.put("USERID", userId);
			paramMap.put("SESSIONID", sessionId);
			ServerResReq.requestServer(paramMap,
					ServerConstant.SH01_06_04_04,
					ServerConstant.SH01_06_04_04);
		}
	}

	@Override
	public void executeSH01_02_03_02_03() {
		ServerResReq
				.requestServer(ServerConstant.SH01_02_03_02_03
						+ getUsrSesStr(),
						ServerConstant.SH01_02_03_02_03);
	}

	@Override
	public void executeSH01_02_03_02_02() {
		ServerResReq
				.requestServer(ServerConstant.SH01_02_03_02_02
						+ getUsrSesStr(),
						ServerConstant.SH01_02_03_02_02);
	}

	@Override
	public void executeSH01_02_03_02_01() {
		ServerResReq
				.requestServer(ServerConstant.SH01_02_03_02_01
						+ getUsrSesStr(),
						ServerConstant.SH01_02_03_02_01);
	}

	/** [SH01_02_03_02]获取单条解决方案数据 */
	@Override
	public void executeSH01_02_03_02(SoluChkSumPO po) {
		String url = ServerConstant.SH01_02_03_02 + "?"
				+ (po != null ? po.toString() : "");
		ServerResReq.requestSoluChkSumServer(url, po,
				ServerConstant.SH01_02_03_02);
	}
}
