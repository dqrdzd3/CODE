package com.hw.smarthome.service.base;

import java.util.Map;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import com.hw.smarthome.po.ParamMapPO;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SoluChkSumPO;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithAccount;
import com.hw.smarthome.service.gc.GcThread;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.scene.SceneAddActivity;
import com.hw.smarthome.ui.sensor.SensorRegActivity;
import com.hw.smarthome.ui.sensor.SensorWifiActivity;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年6月26日 下午3:47:12
 */
public abstract class SmartHomeServiceBase extends Service {

	protected Context mContext;
	public static String userId = null;
	public static String sessionId = null;
	// 注册广播接收器
	protected ActionReceiver receiver = null;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = this;
		intiService();
	}

	/**
	 * 初始化服务
	 * 
	 * @author 曾凡
	 * @time 2014年6月26日 下午4:36:01
	 */
	private void intiService() {
		initReceiver();
		initUser();
		initTaskSchedule();
		initData();
		/* 5分钟一次垃圾回收 */
		new GcThread(5 * 60 * 1000).start();
	}

	/**
	 * 启动Service时初始化信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月14日 下午1:17:21
	 */
	private void initData() {
		/* 进入APP后从服务端获取设备列表 */
		executeSH01_01_01_03();
		/* 请求获取历史数据 */

		executeSH01_02_01_03();

	}

	/**
	 * 初始化用户信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:30:10
	 */
	private void initUser() {

		try {
			userId = DealWithAccount.getUser(
					getApplicationContext()).getMa001();
			sessionId = DealWithAccount.getUser(
					getApplicationContext()).getMa010();
		} catch (Exception e) {
			Ln.e(e, "没有获取到UserId和SessionID");
		} finally {

		}
		// userId = "be2666a4-85d5-4d70-86c2-b854f457f3cd";// FIXME
		// sessionId = "13658E39DFFB1A7231C083915595F28B";// FIXME
	}

	public static void initAccount(String userid,
			String sessionid) {
		userId = userid;
		sessionId = sessionid;
	}

	/**
	 * 初始化任务调度
	 * 
	 * @author 曾凡
	 * @time 2014年6月26日 下午4:35:54
	 */
	private void initTaskSchedule() {
		// FIXME 初始化任务调度
	}

	private void initReceiver() {
		if (receiver == null) {
			Ln.i("注册Service广播接收器");
			// 注册广播接收器
			receiver = new ActionReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(MainActivity.class.getName());
			filter.addAction(SensorRegActivity.class.getName());
			filter.addAction(SensorWifiActivity.class.getName());
			filter.addAction(SceneAddActivity.class.getName());
			registerReceiver(receiver, filter);
		}
	}

	public class ActionReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			Message msg = new Message();
			msg.setData(bundle);
			try {
				actionHandler.sendMessage(msg);
			} catch (Exception e) {
				Ln.e(e, "Service异常！");
			}
		}
	}

	/**
	 * 处理查询的Handler
	 */
	private Handler actionHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {

			Bundle bundle = msg.getData().getBundle("action");
			/* 调用的接口 */
			String action = bundle.getString("name");
			/* 调用接口具体的实现（对应接口增删查改等方法） */
			int type = bundle.getInt("type");
			String sensorId = bundle.getString("sensorId");
			String ctrlString = bundle.getString("CTRL");

			/* [SH01_01_01_02] 发现设备 上传传感器 */
			if (action.equals(ServerConstant.SH01_01_01_02)) {
				executeSH01_01_01_02(sensorId);
			}
			/* [SH01_01_01_07] 修改设备信息 */
			else if (action.equals(ServerConstant.SH01_01_01_07)) {
				String drivewName = bundle
						.getString("DRIVERNAME");
				if (ctrlString == null || "".equals(ctrlString)) {
					executeSH01_01_01_07(sensorId, drivewName);
				} else {
					executeSH01_01_01_07(sensorId, drivewName,
							ctrlString);
				}

			}
			/* [SH01_01_01_03] 查看已发现设备 */
			else if (action.equals(ServerConstant.SH01_01_01_03)) {
				executeSH01_01_01_03();
			}/* [SH01_01_01_05] 配网 */
			else if (action.equals(ServerConstant.SH01_01_01_05)) {
				String ssid = bundle.getString("ssid");
				String passwd = bundle.getString("passwd");
				executeSH01_01_01_05(ssid, passwd);
			}
			/* [SH01_01_01_04] 添加/删除设备 */
			else if (action.equals(ServerConstant.SH01_01_01_04)) {
				executeSH01_01_01_04(sensorId);
			}

			/* [SH01_02_01_03] 查看历史7信息 */
			else if (action.equals(ServerConstant.SH01_02_01_03)) {
				executeSH01_02_01_03();

			}
			/* [SH01_02_01_04] 查看24小时历史明细信息 */
			else if (action.equals(ServerConstant.SH01_02_01_04)) {
				executeSH01_02_01_04(sensorId);
			}
			/* [SH01_02_01_05] 查看过去30天历史明细信息 */
			else if (action.equals(ServerConstant.SH01_02_01_05)) {
				executeSH01_02_01_05(sensorId);
			}
			/* [SH01_02_02_01] 查看设备实时数据 */
			else if (action.equals(ServerConstant.SH01_02_02_01)) {
				executeSH01_02_02_01();
			}
			/* [SH01_05_01_01_01] 讨论区主题 */
			else if (action
					.equals(ServerConstant.SH01_05_01_01_01)) {
				execute(bundle);
			} else if (action
					.equals(ServerConstant.SH01_03_01_02)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_01_02(po.getParamMap());
			} else if (action
					.equals(ServerConstant.SH01_03_02_01)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_01(po.getParamMap());
			} else if (action
					.equals(ServerConstant.SH01_03_02_04)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_04(po.getParamMap());
			} else if (action
					.equals(ServerConstant.SH01_03_02_02)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_02(po.getParamMap());
			} else if (action
					.equals(ServerConstant.SH01_03_02_03)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_03(po.getParamMap());
			}
			/* [SH01_03_01_01] 查看用户信息 */
			else if (action.equals(ServerConstant.SH01_03_01_01)) {
				executeSH01_03_01_01();
			}
			/* [SH01_05_01_04] 查看某主题所有讨论 */
			else if (action
					.equals(ServerConstant.SH01_05_01_01_03)) {
				execute(bundle);
			}
			/* [SH01_05_01_02_01] 留言板.提交留言板信息 */
			else if (action
					.equals(ServerConstant.SH01_05_01_02_01)) {
				execute(bundle);
			}
			/* [SH01_05_01_02_02] 留言板.提交留言板信息 */
			else if (action
					.equals(ServerConstant.SH01_05_01_02_02)) {
				execute(bundle);
			}
			/* [SH01_05_01_03] 讨论区提交发言 */
			else if (action
					.equals(ServerConstant.SH01_05_01_01_02)) {
				execute(bundle);
			}
			/* [SH01_02_01_02] 历史报警信息 */
			else if (action.equals(ServerConstant.SH01_02_01_02)) {
				execute(bundle);
			}
			/* 排名 */
			else if (action.equals(ServerConstant.RANKING)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeRanking(po.getParamMap());
			} else if (action
					.equals(ServerConstant.SH01_02_03_01)) {
				executeSH01_02_03_01();
			}
			/* [SH01_02_03_02_01] 查看在线商城 */
			else if (action
					.equals(ServerConstant.SH01_02_03_02_01)) {
				executeSH01_02_03_02_01();
			}
			/* [SH01_02_03_03_02] DIY课堂 */
			else if (action
					.equals(ServerConstant.SH01_02_03_02_02)) {
				executeSH01_02_03_02_02();
			}
			/* [SH01_02_03_04_03] 我要创业 */
			else if (action
					.equals(ServerConstant.SH01_02_03_02_03)) {
				executeSH01_02_03_02_03();
			}
			/* [SH01_06_02_01]手动控制 */
			else if (action.equals(ServerConstant.SH01_06_02_01)) {
				SensorCtrlDetail po = (SensorCtrlDetail) bundle
						.get("ctrl");
				executeSH01_06_02_01(po);
			}
			/* [SH01_06_04_01] 添加场景 */
			else if (action.equals(ServerConstant.SH01_06_04_01)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_06_04_01(po.getParamMap());
			}
			/* [SH01_06_04_02] 修改场景 */
			else if (action.equals(ServerConstant.SH01_06_04_02)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_06_04_02(po.getParamMap());
			}
			/* [SH01_06_04_03] 查询场景 */
			else if (action.equals(ServerConstant.SH01_06_04_03)) {
				executeSH01_06_04_03();
			}
			/* [SH01_06_04_04] 删除场景 */
			else if (action.equals(ServerConstant.SH01_06_04_04)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_06_04_04(po.getParamMap());
			}
			/* [SH01_02_03_02]获取单条解决方案数据 */
			else if (action.equals(ServerConstant.SH01_02_03_02)) {
				SoluChkSumPO po = (SoluChkSumPO) bundle
						.get("soluChkSum");
				executeSH01_02_03_02(po);
			}
		}

	};
	/** [SH01_02_03_02]获取单条解决方案数据 */
	public abstract void executeSH01_02_03_02(SoluChkSumPO po);
	
	public abstract void executeSH01_02_03_02_03();

	public abstract void executeSH01_02_03_02_02();

	public abstract void executeSH01_02_03_02_01();

	/** [SH01_06_04_01] 添加场景 */
	public abstract void executeSH01_06_04_01(
			Map<String, String> paramMap);

	/** [SH01_06_04_02] 修改场景 */
	public abstract void executeSH01_06_04_02(
			Map<String, String> paramMap);

	/** [SH01_06_04_03] 查询场景 */
	public abstract void executeSH01_06_04_03();

	/** [SH01_06_04_04] 删除场景 */
	public abstract void executeSH01_06_04_04(
			Map<String, String> paramMap);

	/**
	 * [SH01_02_02_01] 查看设备实时数据
	 * 
	 * @author 曾凡
	 * @time 2014年7月11日 上午10:25:44
	 */
	public abstract void executeSH01_02_02_01();

	/**
	 * 
	 * 函 数 名：execute 功能描述：数据请求处理总方法 输入参数：
	 * 
	 * @param bundle
	 *            创建人：lijing 创建时间：2014-7-8 下午3:55:03 修改人： 修改时间： 修改原因描述：
	 */
	public abstract void execute(Bundle bundle);

	/**
	 * [SH01_01_01_02] 发现设备 上传传感器
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	public abstract void executeSH01_01_01_02(String sensorId);

	public abstract void executeSH01_01_01_07(String sensorId,
			String dname);

	public abstract void executeSH01_01_01_07(String sensorId,
			String dname, String ctrl);

	/**
	 * [SH01_01_01_03] 查看已发现设备
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	public abstract void executeSH01_01_01_03();

	/**
	 * [executeSH01_01_01_05] 配网
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	public abstract void executeSH01_01_01_05(final String ssid,
			final String passwd);

	/**
	 * [SH01_03_01_02] 修改账号信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月1日 下午9:31:48
	 */
	public abstract void executeSH01_03_01_02(
			Map<String, String> paramsMap);

	/**
	 * [SH01_03_01_01] 查看用户信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月14日 上午10:01:23
	 */
	public abstract void executeSH01_03_01_01();

	/**
	 * [SH01_03_02_02] 注册账号
	 * 
	 * @author 曾凡
	 * @time 2014年7月2日 下午9:31:48
	 */
	public abstract void executeSH01_03_02_01(
			Map<String, String> paramsMap);

	/**
	 * [SH01_01_01_01] 登录
	 * 
	 * @author 曾凡
	 * @time 2014年7月2日 下午9:31:48
	 */
	public abstract void executeSH01_03_02_04(
			Map<String, String> paramsMap);

	/**
	 * [SH01_03_02_02] 查找密码
	 * 
	 * @author 曾凡
	 * @time 2014年7月2日 下午9:31:48
	 */
	public abstract void executeSH01_03_02_02(
			Map<String, String> paramsMap);

	/**
	 * [SH01_03_02_03] 修改密码
	 * 
	 * @author 曾凡
	 * @time 2014年7月2日 下午9:31:48
	 */
	public abstract void executeSH01_03_02_03(
			Map<String, String> paramsMap);

	/**
	 * [SH01_01_01_04] 删除设备
	 * 
	 * @author 曾凡
	 * @time 2014年7月3日 下午2:06:19
	 */
	public abstract void executeSH01_01_01_04(String sensorId);

	/**
	 * [SH01_02_01_03]查看历史7信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月3日 下午2:06:19
	 */

	public abstract void executeSH01_02_01_03();

	/**
	 * [SH01_02_01_04]查看24小时历史明细信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月3日 下午2:06:19
	 */
	public abstract void executeSH01_02_01_04(String sensorId);

	/**
	 * [SH01_02_01_05]查看过去30天历史明细信息
	 * 
	 * @author 曾凡
	 * @time 2014年7月3日 下午2:06:19
	 */
	public abstract void executeSH01_02_01_05(String sensorId);

	/**
	 * 讨论区主题
	 * 
	 * @author lijing
	 * @time 2014-7-15 下午2:19:31
	 * @return void
	 */
	public abstract void executeSH01_05_01_02();

	public abstract void executeSH01_05_01_03();

	/**
	 * 排名
	 * 
	 * @author 闫威
	 * @time 2014年10月11日 上午10:25:44
	 */
	public abstract void executeRanking(
			Map<String, String> paramsMap);

	/** [SH01_02_03_01]查看知识和解决方案 */
	public abstract void executeSH01_02_03_01();

	/** [SH01_06_02_01]手动控制 */
	public abstract void executeSH01_06_02_01(SensorCtrlDetail po);

	/**
	 * 获取用户的用户Id和sessionId的字符串
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月1日 下午9:33:06
	 */
	public static String getUsrSesStr(String name) {
		StringBuilder paramUri = new StringBuilder(name);// FIXME
		paramUri.append("?USERID=").append(userId);
		paramUri.append("&SESSIONID=").append(sessionId);
		return paramUri.toString();
	}

	/**
	 * 获取用户的用户Id和sessionId的字符串
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月1日 下午9:33:06
	 */
	public static String getUsrSesStr() {
		StringBuilder paramUri = new StringBuilder();
		paramUri.append("&USERID=").append(userId);
		paramUri.append("&SESSIONID=").append(sessionId);
		return paramUri.toString();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (receiver != null) {
			Ln.i("注销Service广播接收器");
			this.unregisterReceiver(receiver);
		}
	}
}
