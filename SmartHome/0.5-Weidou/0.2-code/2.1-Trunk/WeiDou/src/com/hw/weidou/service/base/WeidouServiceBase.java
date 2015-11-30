package com.hw.weidou.service.base;

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

import com.hw.util.Ln;
import com.hw.weidou.decode.FourierDecoder;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.po.ParamMapPO;
import com.hw.weidou.server.constant.ServerConstant;
import com.hw.weidou.service.gc.GcThread;
import com.hw.weidou.ui.MainActivity;

/**
 * @author 曾凡
 * @time 2014年6月26日 下午3:47:12
 */
public abstract class WeidouServiceBase extends Service {

	protected Context mContext;
	protected static String userId = null;
	protected static String sessionId = null;
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
		startThread();
		/* 5分钟一次垃圾回收 */
		new GcThread(5 * 60 * 1000).start();
	}

	// private void test() {
	//
	// try {
	// new Thread(new Runnable() {
	//
	// @Override
	// public void run() {
	// while (true) {
	// System.out.println("发送一个UDP广播");
	// SendUDP send = SendUDP.getInstance();
	// send.sendData("sb941");
	// try {
	// TimeUnit.SECONDS.sleep(2);
	// } catch (InterruptedException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// }
	// }).start();
	//
	// } catch (Exception e) {
	// Ln.e(e, "发送UDP广播失败");
	// }
	//
	// }
	private FourierDecoder fourierDecoder = null;
	private ParserDeamon parserDeamon = null;

	private void startThread() {

		try {
			if (fourierDecoder == null) {
				fourierDecoder = FourierDecoder.getInstance();
				fourierDecoder.setName("波形解析线程");
				fourierDecoder.start();
			}
		} catch (Exception e) {
			Ln.e(e, "线程启动异常");
		}
		fourierDecoder.Go();
		try {
			if(parserDeamon==null){
				parserDeamon = ParserDeamon.getInstance();
				parserDeamon.setName("帧解析线程");
				parserDeamon.start();
			}
		} catch (Exception e) {
			Ln.e(e, "线程启动异常");
		}
		parserDeamon.Go();
	}

	private void pauseThread() {

		try {
			fourierDecoder = FourierDecoder.getInstance();
			if (fourierDecoder != null
					&& fourierDecoder.getIsRunning()) {
				fourierDecoder.Pause();
			}

		} catch (Exception e) {
			Ln.e(e, "线程启动异常");
		}
		try {
			parserDeamon = ParserDeamon.getInstance();
			if (parserDeamon != null
					&& parserDeamon.getIsRunning()) {
				parserDeamon.Pause();
			}

		} catch (Exception e) {
			Ln.e(e, "线程启动异常");
		}
	}

	private void initReceiver() {
		if (receiver == null) {
			Ln.i("注册Service广播接收器");
			// 注册广播接收器
			receiver = new ActionReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction(MainActivity.class.getName());

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
			/* type */
			int type = bundle.getInt("type");
			/* 调用接口具体的实现（对应接口增删查改等方法） */
			Ln.i("请求了：" + action);
			/* [SH01_05_01_01_01] 讨论区主题 */
			if (action.equals(ServerConstant.SH01_05_01_01_01)) {
				execute(bundle);
			}
			if (action.equals(ServerConstant.SH01_03_01_02)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_01_02(po.getParamMap());
			}
			if (action.equals(ServerConstant.SH01_03_02_01)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_01(po.getParamMap());
			}
			if (action.equals(ServerConstant.SH01_03_02_04)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_04(po.getParamMap());
			}
			if (action.equals(ServerConstant.SH01_03_02_02)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_02(po.getParamMap());
			}
			if (action.equals(ServerConstant.SH01_03_02_03)) {
				ParamMapPO po = (ParamMapPO) bundle.get("param");
				executeSH01_03_02_03(po.getParamMap());
			}
			/* [SH01_03_01_01] 查看用户信息 */
			if (action.equals(ServerConstant.SH01_03_01_01)) {
				executeSH01_03_01_01();
			}
			/* [SH01_05_01_04] 查看某主题所有讨论 */
			if (action.equals(ServerConstant.SH01_05_01_01_03)) {
				execute(bundle);
			}
			/* [SH01_05_01_02_01] 留言板.提交留言板信息 */
			if (action.equals(ServerConstant.SH01_05_01_02_01)) {
				execute(bundle);
			}
			/* [SH01_05_01_02_02] 留言板.提交留言板信息 */
			if (action.equals(ServerConstant.SH01_05_01_02_02)) {
				execute(bundle);
			}
			/* [SH01_05_01_03] 讨论区提交发言 */
			if (action.equals(ServerConstant.SH01_05_01_01_02)) {
				execute(bundle);
			}
			/* [WD_01_01_02]MIC口接受并解调传感器值信号 */
			if (action.equals(ServerConstant.WD_01_01_02)) {
				executeWD_01_01_02(type);
			}
		}

	};

	/** [WD_01_01_02]MIC口接受并解调传感器值信号，随着主页面的生命周期而改变 */
	private void executeWD_01_01_02(int type) {
		if (1 == type) {
			startThread();
		} else if (0 == type) {
			pauseThread();
		}
	}

	/**
	 * 
	 * 函 数 名：execute 功能描述：数据请求处理总方法 输入参数：
	 * 
	 * @param bundle
	 *            创建人：lijing 创建时间：2014-7-8 下午3:55:03 修改人： 修改时间： 修改原因描述：
	 */
	public abstract void execute(Bundle bundle);

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
	 * 讨论区主题
	 * 
	 * @author lijing
	 * @time 2014-7-15 下午2:19:31
	 * @return void
	 */
	public abstract void executeSH01_05_01_02();

	public abstract void executeSH01_05_01_03();

	/**
	 * 获取用户的用户Id和sessionId的字符串
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月1日 下午9:33:06
	 */
	protected String getUsrSesStr(String name) {
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
	protected String getUsrSesStr() {
		StringBuilder paramUri = new StringBuilder();
		paramUri.append("&USERID=").append(userId);
		paramUri.append("&SESSIONID=").append(sessionId);
		return paramUri.toString();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (fourierDecoder != null) {
			fourierDecoder.ExitThread();
		}
		if (parserDeamon != null) {
			parserDeamon.ExitThread();
		}
		if (receiver != null) {
			Ln.i("注销Service广播接收器");
			this.unregisterReceiver(receiver);
		}
	}
}
