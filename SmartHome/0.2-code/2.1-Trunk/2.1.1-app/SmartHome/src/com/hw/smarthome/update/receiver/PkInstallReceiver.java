package com.hw.smarthome.update.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PkInstallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {

		if (intent.getAction().equals(
				"android.intent.action.PACKAGE_ADDED")) {
			String packageName = intent.getDataString()
					.substring(8);
			System.out.println("安装:" + packageName + "包名的程序");
		}

		// 接收卸载广播

		if (intent.getAction().equals(
				"android.intent.action.PACKAGE_REMOVED")) {
			String packageName = intent.getDataString()
					.substring(8);
			System.out.println("卸载:" + packageName + "包名的程序");
			Intent newIntent = new Intent();
			newIntent.setClassName(packageName, packageName
					+ ".StartActivity");
			newIntent.setAction("android.intent.action.MAIN");
			newIntent
					.addCategory("android.intent.category.LAUNCHER");
			newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(newIntent);
		}
	}

}