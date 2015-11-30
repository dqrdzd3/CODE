package com.hw.util;

import android.content.Context;
import android.content.Intent;

/**
 * 分享
 * @author lijing
 * @time 2014-7-21 下午2:10:11
 */
public class ShareUtil {
	
	public static void localShare(Context context,String content){
		Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送到属性
		intent.setType("text/plain"); // 分享发送到数据类型
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享"); // 分享的主题
		intent.putExtra(Intent.EXTRA_TEXT, content); // 分享的内容
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 允许intent启动新的activity
		context.startActivity(Intent.createChooser(intent, "分享")); // //目标应用选择对话框的标题

	}
}
