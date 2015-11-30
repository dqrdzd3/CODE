package com.hw.smarthome.ui.home.adapter.homeup.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONObject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.android.support.v8.app.NotificationCompat.Builder;
import com.google.gson.Gson;
import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.service.SmartHomeService;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.update.receiver.InstallReciver;
import com.hw.smarthome.update.util.AppNotification;
import com.hw.util.Ln;
import com.hw.util.NetUtil;

/**
 * 判断硬件是否升级
 * 
 * @author 闫威
 * 
 */

public class HardUpgradeUtil {

	private static Context mMainActivity;
	private static SensorDetail mCurrentSensor;

	/**
	 * 判断是否显示此按钮
	 * 
	 * @author 曾凡
	 * @param context
	 * @param detail
	 * @time 2015年6月2日 下午3:56:34
	 */
	public static boolean chkIsShow(Context context,
			SensorDetail sensor) {
		boolean res = false;
		try {
			//FIXME 开发三部异常
//			SensorDetail detail = HomeUtil.getSensorDetail(
//					context, sensor);
//			String lHard = detail.getLocalHardVersion();
//			String rHard = detail.getRemoteHardVersion();
//			String local = detail.getLocalSoftVersion();
//			String remote = detail.getRemoteSoftVersion();
//			if (lHard == null || rHard == null || local == null
//					|| remote == null) {
//				return res;
//			}
//			mMainActivity = context;
//			mCurrentSensor = detail;
//			DealWithSensor.initIsOnLine(mMainActivity, detail);
//			if (detail.isOnline() && lHard.trim().length() > 0
//					&& rHard.trim().length() > 0
//					&& lHard.equals(rHard)) {
//				if (local.compareTo(remote) > 0) {
//					res = true;
//				} else {
//					res = false;
//				}
//
//			} else {
//				res = false;
//			}

		} catch (Exception e) {
			res = false;
			e.printStackTrace();
		} finally {
			return res;
		}
	}

	public static void checkHard(Context context,
			SensorDetail detail, boolean showNoUpgradeDialog) {
		String local = detail.getLocalSoftVersion();
		String remote = detail.getRemoteSoftVersion();
		if (local == null || remote == null)
			return;
		mMainActivity = context;
		mCurrentSensor = detail;
		DealWithSensor.initIsOnLine(mMainActivity, detail);
		if (!detail.isOnline()) {
			dialogOffLine(context, detail.getSensorId());
		} else if (local.trim().length() > 0
				&& remote.trim().length() > 0
				&& !local.equals(remote)) {

			dialog(context, detail);

		} else if (local.trim().length() > 0
				&& remote.trim().length() == 0) {
			dialogNotQuery(context, detail.getSensorId());
		} else {
			if (showNoUpgradeDialog)
				dialogNoUpdate(context, detail.getSensorId());
		}
	}

	// 升级对话框
	public static void dialog(final Context context,
			final SensorDetail detail) {

		new SweetAlertDialog(context,
				SweetAlertDialog.NORMAL_TYPE)
				.setTitleText(detail.getSensorId() + "固件有新版本")
				.setContentText(
						"当前版本号：" + detail.getRemoteSoftVersion()
								+ ",升级后的版本号："
								+ detail.getLocalSoftVersion())
				.setCancelText("取消")
				.setConfirmText("确认")
				.showCancelButton(true)
				.setCancelClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();

							}
						})
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
								doStartUpgrade(context, detail);
							}
						}).show();

	}

	// 无升级对话框
	public static void dialogNoUpdate(Context context,
			String sensorid) {

		new SweetAlertDialog(context,
				SweetAlertDialog.NORMAL_TYPE)
				.setContentText(sensorid + "当前固件为最新版本！")
				.setConfirmText("确认")
				.showCancelButton(false)
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
							}
						}).show();
	}

	// 不在线对话框
	public static void dialogOffLine(Context context,
			String sensorid) {

		new SweetAlertDialog(context,
				SweetAlertDialog.NORMAL_TYPE)
				.setContentText(sensorid + "当前未联网！")
				.setConfirmText("确认")
				.showCancelButton(false)
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
							}
						}).show();
	}

	// 不在线对话框
	public static void dialogNotQuery(Context context,
			String sensorid) {

		new SweetAlertDialog(context,
				SweetAlertDialog.NORMAL_TYPE)
				.setContentText(sensorid + "设备未返回版本信息！")
				.setConfirmText("确认")
				.showCancelButton(false)
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
							}
						}).show();
	}

	// 升级开始
	private static void doStartUpgrade(final Context context,
			SensorDetail detail) {
		AjaxParams paramMap = new AjaxParams();
		paramMap.put("USERID", SmartHomeService.getUser()[0]);
		paramMap.put("SESSIONID", SmartHomeService.getUser()[1]);
		paramMap.put("DRIVERID", detail.getSensorId());
		paramMap.put("DATA", new Gson().toJson(detail));
		String url = ServerConstant.SERVER_BASE_URI
				+ ServerConstant.UPGRADE;
		FinalHttp fh = new FinalHttp();

		/**
		 * 设置错误连接次数
		 */
		fh.configRequestExecutionRetryCount(0);
		fh.post(url, paramMap, new AjaxCallBack<Object>() {

			@Override
			public void onSuccess(Object t) {
				JSONObject jo;

				try {

					Intent resultIntent = new Intent(context,
							MainActivity.class);

					resultIntent
							.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					TaskStackBuilder stackBuilder = TaskStackBuilder
							.create(context);
					// Adds the back stack for the Intent (but not the Intent
					// itself)
					stackBuilder
							.addParentStack(MainActivity.class);
					// Adds the Intent that starts the Activity to the top of
					// the stack
					stackBuilder.addNextIntent(resultIntent);
					final PendingIntent resultPendingIntent = PendingIntent
							.getActivity(
									context,
									0,
									resultIntent,
									PendingIntent.FLAG_UPDATE_CURRENT);
					final Bitmap large = BitmapFactory
							.decodeResource(
									context.getResources(),
									R.drawable.ic_launcher);
					mNotifyManager = (NotificationManager) context
							.getSystemService(Context.NOTIFICATION_SERVICE);
					mBuilder = AppNotification.newBaseNotify(
							context, R.drawable.ic_launcher,
							"空气电台固件升级", large,
							resultPendingIntent);
					if (isCancel) {
						timer = new Timer();
					}
					timer.schedule(task, 10000, 9000);

				} catch (Exception e) {
					Ln.e(e);
					try {
						Toast.makeText(context, "提交失败", 5)
								.show();
					} catch (Exception e1) {
						Ln.e(e1, "弹出提交失败异常");
					}
				}

			}

			@Override
			public void onFailure(Throwable t, int errorNo,
					String strMsg) {
				// TODO Auto-generated method stub
				super.onFailure(t, errorNo, strMsg);
				Toast.makeText(context, strMsg, 5).show();
			}

		});
	}

	// 升级请求
	private static void doUpgrade() {
		// 开始升级

		Message msg = new Message();
		try {
			HashMap<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("USERID", SmartHomeService.getUser()[0]);
			paramMap.put("SESSIONID",
					SmartHomeService.getUser()[1]);
			paramMap.put("DRIVERID",
					mCurrentSensor.getSensorId());
			paramMap.put("DATA",
					new Gson().toJson(mCurrentSensor));
			DealWithSensor.initIsOnLine(mMainActivity,
					mCurrentSensor);
			String url = ServerConstant.SERVER_BASE_URI
					+ ServerConstant.UPGRADEINFO;

			JSONObject jo;
			Map<String, String> map = null;
			try {
				if (!mCurrentSensor.isOnline()) {
					msg.what = DOWNLOAD_FAIL;
					msg.obj = "error";
				} else {
					jo = new JSONObject(NetUtil.http_post(url,
							paramMap, null));

					if (jo.getString("code").equals("0")) {
						msg.what = DOWNLOAD_FAIL;
						msg.obj = jo.getString("message");
					} else {

						if (jo.has("dataObject")) {
							map = new HashMap<String, String>();
							JSONObject site = jo
									.getJSONObject("dataObject");

							map.put("reciveLength", site
									.getString("reciveLength"));
							map.put("totalLength", site
									.getString("totalLength"));
							msg.what = DOWNLOAD_HARDWARE;
							msg.obj = map;
						}
					}
				}

			} catch (Exception e) {
				msg.what = DOWNLOAD_FAIL;
				msg.obj = e;
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg.what = DOWNLOAD_FAIL;
			msg.obj = e;
		}
		mHandler.sendMessage(msg);

	}

	/* 下载中 */
	private static final int DOWNLOAD = 1;
	/* 下载结束 */
	private static final int DOWNLOAD_FINISH = 2;
	/* 记录进度条数量 */
	/* 下载中 */
	private static final int DOWNLOAD_HARDWARE = 3;
	private static final int MESSAGE_STATE_LOADERROR = 4;
	// 下载失败
	private static final int DOWNLOAD_FAIL = 5;
	private static int progress;
	private static NotificationManager mNotifyManager;
	private static Builder mBuilder;
	private static Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				mBuilder.setProgress(100, progress + 1, false)
						.setContentInfo(progress + 1 + "%");
				// Displays the progress bar for the first time.
				mNotifyManager.notify(0, mBuilder.build());
				break;
			case DOWNLOAD_HARDWARE:
				Map<String, String> map = (Map<String, String>) msg.obj;
				if (map == null
						|| !map.containsKey("totalLength")
						|| map.get("totalLength").trim()
								.length() == 0)
					return;
				long recLen = Long.parseLong(map
						.get("reciveLength"));
				long totalLen = Long.parseLong(map
						.get("totalLength"));
				if (totalLen == 0)
					return;
				progress = (int) ((recLen * 100) / totalLen);
				mBuilder.setProgress(100, progress, false)
						.setContentInfo(progress + "%");

				mNotifyManager.notify(0, mBuilder.build());

				break;
			case DOWNLOAD_FINISH: {
				Intent resultIntent = new Intent(mMainActivity,
						InstallReciver.class);

				resultIntent.setAction("com.hw.installapp");
				resultIntent
						.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				TaskStackBuilder stackBuilder = TaskStackBuilder
						.create(mMainActivity);
				// Adds the back stack for the Intent (but not the Intent
				// itself)
				stackBuilder.addParentStack(MainActivity.class);
				// Adds the Intent that starts the Activity to the top of the
				// stack
				stackBuilder.addNextIntent(resultIntent);

				final PendingIntent resultPendingIntent = PendingIntent
						.getBroadcast(
								mMainActivity,
								0,
								resultIntent,
								PendingIntent.FLAG_UPDATE_CURRENT);

				mNotifyManager = (NotificationManager) mMainActivity
						.getSystemService(Context.NOTIFICATION_SERVICE);
				final Bitmap large = BitmapFactory
						.decodeResource(
								mMainActivity.getResources(),
								R.drawable.ic_launcher);
				mBuilder = AppNotification.newBaseNotify(
						mMainActivity, R.drawable.ic_launcher,
						"空气电台固件升级", large, resultPendingIntent);
				// When the loop is finished, updates the notification
				mBuilder.setContentText("硬件升级完成")
				// Removes the progress bar
						.setProgress(0, 0, false);
				mNotifyManager.notify(0, mBuilder.build());
				isCancel = true;
				timer.cancel();
				break;
			}
			case MESSAGE_STATE_LOADERROR:
				break;

			case DOWNLOAD_FAIL: {
				Intent resultIntent = new Intent(mMainActivity,
						InstallReciver.class);

				resultIntent.setAction("com.hw.installapp");
				resultIntent
						.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				TaskStackBuilder stackBuilder = TaskStackBuilder
						.create(mMainActivity);
				// Adds the back stack for the Intent (but not the Intent
				// itself)
				stackBuilder.addParentStack(MainActivity.class);
				// Adds the Intent that starts the Activity to the top of the
				// stack
				stackBuilder.addNextIntent(resultIntent);

				final PendingIntent resultPendingIntent = PendingIntent
						.getBroadcast(
								mMainActivity,
								0,
								resultIntent,
								PendingIntent.FLAG_UPDATE_CURRENT);

				mNotifyManager = (NotificationManager) mMainActivity
						.getSystemService(Context.NOTIFICATION_SERVICE);
				final Bitmap large = BitmapFactory
						.decodeResource(
								mMainActivity.getResources(),
								R.drawable.ic_launcher);
				mBuilder = AppNotification.newBaseNotify(
						mMainActivity, R.drawable.ic_launcher,
						"空气电台固件升级", large, resultPendingIntent);
				// When the loop is finished, updates the notification
				mBuilder.setContentText("硬件升级失败")
				// Removes the progress bar
						.setProgress(0, 0, false);
				mNotifyManager.notify(0, mBuilder.build());
				isCancel = true;
				timer.cancel();
				break;
			}
			}
		};
	};
	private static boolean isCancel = false;
	static Timer timer = new Timer();

	static TimerTask task = new TimerTask() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Message message = new Message();
			if (progress < 100) {
				message.what = DOWNLOAD_HARDWARE;
				// progress+=1;
				doUpgrade();
			} else {
				message.what = DOWNLOAD_FINISH;
			}
			mHandler.sendMessage(message);
		}
	};
}
