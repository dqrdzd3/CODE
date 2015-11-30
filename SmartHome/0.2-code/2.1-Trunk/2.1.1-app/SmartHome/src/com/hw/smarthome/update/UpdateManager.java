package com.hw.smarthome.update;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.xbill.DNS.RRset;

import android.R.raw;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.TaskStackBuilder;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.support.v8.app.NotificationCompat.Builder;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.update.receiver.InstallReciver;
import com.hw.smarthome.update.util.AppNotification;
import com.hw.smarthome.view.widget.MyDialog;
import com.hw.util.Ln;
import com.hw.util.SoftUtil;
import com.hw.util.UpdateUtil;

public class UpdateManager {
	/* 下载中 */
	private static final int DOWNLOAD = 1;
	/* 下载结束 */
	private static final int DOWNLOAD_FINISH = 2;
	/* 保存解析的XML信息 */
	HashMap<String, String> mHashMap;
	/* 下载保存路径 */
	private String mSavePath;
	/* 记录进度条数量 */
	private int progress;
	/* 是否取消更新 */
	private boolean cancelUpdate = false;

	private Context mContext;
	/* 是否显示没有最新版本的提示 */
	private boolean isShowNoUpdateInfo = true;

	/* 更新进度条 */
	private ProgressBar mProgress;

	private String contentString = "";

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			// 正在下载
			case DOWNLOAD:
				mBuilder.setProgress(100, progress + 1, false)
						.setContentInfo(progress + 1 + "%");
				// Displays the progress bar for the first time.
				mNotifyManager.notify(0, mBuilder.build());
				break;
			case DOWNLOAD_FINISH:
				Intent resultIntent = new Intent(mContext,
						InstallReciver.class);

				resultIntent.putExtra("installPath", mSavePath);
				resultIntent.putExtra("filename",
						mHashMap.get("name"));
				resultIntent.setAction("com.hw.installapp");
				resultIntent
						.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				TaskStackBuilder stackBuilder = TaskStackBuilder
						.create(mContext);
				// Adds the back stack for the Intent (but not the Intent
				// itself)
				stackBuilder.addParentStack(MainActivity.class);
				// Adds the Intent that starts the Activity to the top of the
				// stack
				stackBuilder.addNextIntent(resultIntent);

				final PendingIntent resultPendingIntent = PendingIntent
						.getBroadcast(
								mContext,
								0,
								resultIntent,
								PendingIntent.FLAG_UPDATE_CURRENT);

				mNotifyManager = (NotificationManager) mContext
						.getSystemService(Context.NOTIFICATION_SERVICE);
				final Bitmap large = BitmapFactory
						.decodeResource(mContext.getResources(),
								R.drawable.ic_launcher);
				mBuilder = AppNotification.newBaseNotify(
						mContext, R.drawable.ic_launcher,
						"空气电台更新", large, resultPendingIntent);
				// When the loop is finished, updates the notification
				mBuilder.setContentText("下载完成")
				// Removes the progress bar
						.setProgress(0, 0, false);
				mNotifyManager.notify(0, mBuilder.build());
				// 安装文件
				installApk();
				break;
			default:
				break;
			}
		};
	};

	public UpdateManager(Context context) {
		this.mContext = context;

	}

	/*
	 * 检查更新入口
	 */
	public void checkUpdate(boolean config) {
		isShowNoUpdateInfo = config;

		Thread thread = new Thread() {
			@Override
			public void run() {
				Message msg = new Message();
				String urlString = ServerConstant.UPDATE_URI;
				InputStream inStream;
				try {
					URL uri = new URL(urlString);
					inStream = uri.openStream();

					HashMap<String, String> map = new HashMap<String, String>();
					try {
						map = UpdateUtil.parseXml(inStream);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					msg.what = 1;
					msg.obj = map;

				} catch (Exception e) {
					e.printStackTrace();
					msg.what = 2;
					msg.obj = e;
				}
				handler.sendMessage(msg);
			}
		};

		thread.start();
	}

	String serviceName;
	private Handler handler = new Handler() {
		@Override
		// 当有消息发送出来的时候就执行Handler的这个方法
		public void handleMessage(Message msg) {
			try {
				super.handleMessage(msg);
				// 处理UI
				switch (msg.what) {
				case 1:
					mHashMap = (HashMap<String, String>) msg.obj;
					int versionCode = SoftUtil
							.getVersionCode(mContext);

					String versionName = SoftUtil
							.getVersionName(mContext);

					if (null != mHashMap) {
						if (mHashMap.get("version") != null) {
							int serviceCode = Integer
									.valueOf(mHashMap
											.get("version"));
							serviceName = mHashMap.get("name");
							// 版本判断
							if (mHashMap.get("content") != null) {
								contentString = mHashMap
										.get("content");
							}
							// 曾凡 20150901
							if (versionName.replace(".", "")
									.compareTo(
											serviceName.replace(
													".", "")) < 0) {
								showNoticeDialog();
							} else {
								if (isShowNoUpdateInfo) {
									Toast.makeText(
											mContext,
											R.string.soft_update_no,
											Toast.LENGTH_LONG)
											.show();
								}

							}
						}

					}
					break;
				case 2:
					// UIUtil.ToastMessage(mContext, "获取更新版本信息失败");
					break;
				}
			} catch (Exception e) {
				Ln.e(e, "初始化更新窗口异常");
			}

		}
	};

	/**
	 * 显示软件更新对话框
	 */
	private void showNoticeDialog() {
		final LayoutInflater inflater = LayoutInflater
				.from(mContext);
		View v = inflater
				.inflate(R.layout.ui_soft_upgrade, null);

		TextView updatecontent = (TextView) v
				.findViewById(R.id.update_text);
		TextView version = (TextView) v
				.findViewById(R.id.version);
		updatecontent.setMovementMethod(ScrollingMovementMethod
				.getInstance());// 滚动
		updatecontent.setText(Html.fromHtml(contentString));
		version.setText(serviceName);

		final MyDialog myDialog = new MyDialog(mContext,
				R.style.MyDialog);
		myDialog.setContentView(v);

		ImageButton close = (ImageButton) v
				.findViewById(R.id.serversetting_close_button);
		close.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				myDialog.dismiss();
			}
		});

		BootstrapButton upgrade = (BootstrapButton) v
				.findViewById(R.id.btnUpgrade);
		upgrade.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				myDialog.dismiss();
				// // 显示下载对话框
				showDownloadDialog();
			}
		});
		myDialog.show();

	}

	private Builder mBuilder;
	private NotificationManager mNotifyManager;

	/**
	 * 显示软件下载对话框
	 */
	private void showDownloadDialog() {
		Intent resultIntent = new Intent(mContext,
				MainActivity.class);

		resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		TaskStackBuilder stackBuilder = TaskStackBuilder
				.create(mContext);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(MainActivity.class);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);

		final PendingIntent resultPendingIntent = PendingIntent
				.getActivity(mContext, 0, resultIntent,
						PendingIntent.FLAG_UPDATE_CURRENT);

		mNotifyManager = (NotificationManager) mContext
				.getSystemService(Context.NOTIFICATION_SERVICE);
		final Bitmap large = BitmapFactory.decodeResource(
				mContext.getResources(), R.drawable.ic_launcher);
		mBuilder = AppNotification.newBaseNotify(mContext,
				R.drawable.ic_launcher, "空气电台更新", large,
				resultPendingIntent);
		downloadApk();
	}

	/**
	 * 下载apk文件
	 */
	private void downloadApk() {
		// 启动新线程下载软件
		new downloadApkThread().start();

	}

	/**
	 * 下载文件线程
	 * 
	 * @author coolszy
	 * @date 2012-4-26
	 * @blog http://blog.92coding.com
	 */
	private class downloadApkThread extends Thread {
		@Override
		public void run() {
			try {
				// 判断SD卡是否存在，并且是否具有读写权限
				if (Environment.getExternalStorageState()
						.equals(Environment.MEDIA_MOUNTED)) {
					// 获得存储卡的路径
					String sdpath = Environment
							.getExternalStorageDirectory() + "/";
					mSavePath = sdpath + "download";
					URL url = new URL(ServerConstant.BASE_URI
							+ "apksplace/" + mHashMap.get("url"));

					// 创建连接
					HttpURLConnection conn = (HttpURLConnection) url
							.openConnection();
					conn.connect();
					// 获取文件大小
					int length = conn.getContentLength();
					// 创建输入流
					InputStream is = conn.getInputStream();

					File file = new File(mSavePath);
					// 判断文件目录是否存在
					if (!file.exists()) {
						file.mkdir();
					}
					File apkFile = new File(mSavePath,
							mHashMap.get("name"));
					FileOutputStream fos = new FileOutputStream(
							apkFile);
					int count = 0;
					// 缓存
					byte buf[] = new byte[100 * 1024];
					// 写入到文件中
					do {
						int numread = is.read(buf);
						count += numread;
						// 计算进度条位置
						progress = (int) (((float) count / length) * 100);
						// 曾凡 解决下载线程卡顿的情况
						if(progress%5==0){
							// 更新进度
							mHandler.sendEmptyMessage(DOWNLOAD);
						}
						if (numread <= 0) {
							// 下载完成
							mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
							break;
						}
						// 写入文件
						fos.write(buf, 0, numread);
					} while (!cancelUpdate);// 点击取消就停止下载.
					{
						fos.close();
						is.close();
					}

				}
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	/**
	 * 安装APK文件
	 */
	private void installApk() {
		File apkfile = new File(mSavePath, mHashMap.get("name"));
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(
				Uri.parse("file://" + apkfile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}
