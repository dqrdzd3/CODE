package com.hw.smarthome.service.push;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.hanwei.androidpn.Constants;
import com.hanwei.androidpn.Notifier;
import com.hw.smarthome.ui.MainActivity;
import com.hw.util.PreferenceUtil;

/**
 * 
 * 
 * 项目名称：SmartHome 类名称：PushDataReciver 类描述：推送消息数据接收者 创建人：lijing 创建时间：2014-7-3
 * 下午1:28:26 修改人： 修改时间： 修改备注：
 * 
 * @version
 * 
 */
public class PushDataReciver extends BroadcastReceiver {

	private static final String LOGTAG = PushDataReciver.class
			.getSimpleName();
	private static final String PUSH_TYPE_ORDINARY = "00";   //普通推送
	private static final String PUSH_TYPE_ALARM = "10";   //报警推送
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String action = intent.getAction();
		if (Constants.ACTION_RECIVE_DATA.equals(action)) {
			String notificationId = intent
					.getStringExtra(Constants.NOTIFICATION_ID);
			String notificationApiKey = intent
					.getStringExtra(Constants.NOTIFICATION_API_KEY);
			String notificationTitle = intent
					.getStringExtra(Constants.NOTIFICATION_TITLE);
			String notificationMessage = intent
					.getStringExtra(Constants.NOTIFICATION_MESSAGE);
			/*00普通推送10报警推送*/
			String notificationUri = intent
					.getStringExtra(Constants.NOTIFICATION_URI);

			Log.d(LOGTAG, "notificationId=" + notificationId);
			Log.d(LOGTAG, "notificationApiKey="
					+ notificationApiKey);
			Log.d(LOGTAG, "notificationTitle="
					+ notificationTitle);
			Log.d(LOGTAG, "notificationMessage="
					+ notificationMessage);
			Log.d(LOGTAG, "notificationUri=" + notificationUri);
//			if (PreferenceUtil.getIsPushOrdinary(context)) {
//				Notifier notifier = new Notifier(context);
//				notifier.notify(notificationId,
//						notificationApiKey, notificationTitle,
//						notificationMessage, notificationUri);

//				PushMessage pushMsg = new PushMessage();
//				pushMsg.setId(notificationId);
//				pushMsg.setTitle(notificationTitle);
//				pushMsg.setMessage(notificationMessage);
//
//				Intent msgIntent = new Intent();
//				Bundle bundle = new Bundle();
//				bundle.putSerializable(
//						PushMessage.class.getSimpleName(),
//						pushMsg);
//				bundle.putBoolean("result", true);
//				bundle.putString("name",
//						ServerConstant.SH01_04_02);
//				bundle.putInt("type", 0);
//				intent.putExtra("action", bundle);
//				intent.setAction(SmartHomeService.class
//						.getName());
//				context.sendBroadcast(msgIntent);
//			}
			
			if(PUSH_TYPE_ORDINARY.equals(notificationUri)){
				if (PreferenceUtil.getIsPushOrdinary(context)) {
					Notifier notifier = new Notifier(context);
					notifier.notify(notificationId,
							notificationApiKey, notificationTitle,
							notificationMessage, notificationUri);
//					Intent msgIntent = new Intent();
//					intent.setClass(context,MainActivity.class);
//					context.startActivity(msgIntent);
				}
			}
			
			if(PUSH_TYPE_ALARM.equals(notificationUri)){
				if (PreferenceUtil.getIsPushAlarm(context)) {
					Notifier notifier = new Notifier(context);
					notifier.notify(notificationId,
							notificationApiKey, notificationTitle,
							notificationMessage, notificationUri);
					
//					Intent msgIntent = new Intent();
//					intent.setClass(context,MainActivity.class);
//					context.startActivity(msgIntent);
					
				}
				
			}

		}
	}

}
