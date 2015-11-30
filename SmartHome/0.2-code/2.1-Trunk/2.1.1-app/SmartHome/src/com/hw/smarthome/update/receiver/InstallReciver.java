package com.hw.smarthome.update.receiver;

import java.io.File;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class InstallReciver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("可以安装");
		String pathString = intent.getExtras().getString("installPath");
		String fileString = intent.getExtras().getString("filename");
		File apkfile = new File(pathString,fileString);
		// 通过Intent安装APK文件
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				i.setDataAndType(
						Uri.parse("file://" + apkfile.toString()),
						"application/vnd.android.package-archive");
				context.startActivity(i);
	}

}
