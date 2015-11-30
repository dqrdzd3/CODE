package com.hw.weidou.update.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;

import com.android.support.v8.app.NotificationCompat;

public class AppNotification {

	public static NotificationCompat.Builder newBaseNotify(
			Context ctx, int smallIcon, String ticker,
			Bitmap large, PendingIntent pi) {
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
				ctx).setContentTitle("威豆下载")
				.setWhen(System.currentTimeMillis())
				.setContentText("下载中")
//				.setContentInfo("setContentInfo")
				.setSmallIcon(smallIcon).setContentIntent(pi)
				.setProgress(100, 0, true)
				.setLargeIcon(large).setTicker(ticker);
		return mBuilder;
	}

	// set context view
	public static Notification apiNie() {
		return null;
	}

	public static void cancel() {

	}
}
