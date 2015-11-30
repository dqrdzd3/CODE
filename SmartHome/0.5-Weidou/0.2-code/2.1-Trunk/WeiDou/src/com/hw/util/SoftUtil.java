package com.hw.util;

import java.io.File;
import java.util.ArrayList;


import android.R.integer;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
/**
 * 
 * <p>
 * 项目工程的信息<br>
 * </p>
 */
public class SoftUtil {

	
	/**
	 * 获取App安装包信息
	 * 
	 * @return
	 */
	public static PackageInfo getPackageInfo(Context context) {
		PackageInfo info = null;
		try {
			info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace(System.err);
		}
		if (info == null)
			info = new PackageInfo();
		return info;
	}

	/**
	 * 获取软件版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getVersionCode(Context context) {
		int versionCode = 0;
		try {
			// 获取软件版本号，
			versionCode = getPackageInfo(context).versionCode;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionCode;
	}

	/**
	 * 获取软件版本号名称
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		String versionName = "";
		try {
			// 获取软件版本号，
			versionName = getPackageInfo(context).versionName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
	// 获取AppKey
	public static String getMetaValue(Context context, String metaKey) {
		Bundle metaData = null;
		String apiKey = null;
		if (context == null || metaKey == null) {
			return null;
		}
		try {
			ApplicationInfo ai = context.getPackageManager()
					.getApplicationInfo(context.getPackageName(),
							PackageManager.GET_META_DATA);
			if (null != ai) {
				metaData = ai.metaData;
			}
			if (null != metaData) {
				apiKey = metaData.getString(metaKey);
			}
		} catch (NameNotFoundException e) {

		}
		return apiKey;
	}
	
	// 查询所有联系人的姓名，电话，邮箱
	public static void TestContact(Context context) throws Exception {
		Uri uri = Uri.parse("content://com.android.contacts/contacts");
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(uri, new String[] { "_id" }, null, null,
				null);
		while (cursor.moveToNext()) {
			int contractID = cursor.getInt(0);
			StringBuilder sb = new StringBuilder("contractID=");
			sb.append(contractID);
			uri = Uri.parse("content://com.android.contacts/contacts/"
					+ contractID + "/data");
			Cursor cursor1 = resolver.query(uri, new String[] { "mimetype",
					"data1", "data2" }, null, null, null);
			while (cursor1.moveToNext()) {
				String data1 = cursor1.getString(cursor1
						.getColumnIndex("data1"));
				String mimeType = cursor1.getString(cursor1
						.getColumnIndex("mimetype"));
				if ("vnd.android.cursor.item/name".equals(mimeType)) { // 是姓名
					sb.append(",name=" + data1);
				} else if ("vnd.android.cursor.item/email_v2".equals(mimeType)) { // 邮箱
					sb.append(",email=" + data1);
				} else if ("vnd.android.cursor.item/phone_v2".equals(mimeType)) { // 手机
					sb.append(",phone=" + data1);
				}
			}
			cursor1.close();
		
		}
		cursor.close();
	}

	// 查询指定电话的联系人姓名，邮箱
	public static void testContactNameByNumber(Context context) throws Exception {
		String number = "18052369652";
		Uri uri = Uri
				.parse("content://com.android.contacts/data/phones/filter/"
						+ number);
		ContentResolver resolver = context.getContentResolver();
		Cursor cursor = resolver.query(uri, new String[] { "display_name" },
				null, null, null);
		if (cursor.moveToFirst()) {
			String name = cursor.getString(0);
		
		}
		cursor.close();
	}

	// 添加联系人，使用事务
	public static void testAddContact(Context context,String name,String phone) throws Exception {
		Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
		ContentResolver resolver = context.getContentResolver();
		ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();
		ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
				.withValue("account_name", null).build();
		operations.add(op1);
//添加姓名
		uri = Uri.parse("content://com.android.contacts/data");
		ContentProviderOperation op2 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/name")
				.withValue("data2", name).build();
		operations.add(op2);
//添加手机
		ContentProviderOperation op3 = ContentProviderOperation.newInsert(uri)
				.withValueBackReference("raw_contact_id", 0)
				.withValue("mimetype", "vnd.android.cursor.item/phone_v2")
				.withValue("data1", phone).withValue("data2", "2")
				.build();
		operations.add(op3);
//添加邮箱
//		ContentProviderOperation op4 = ContentProviderOperation.newInsert(uri)
//				.withValueBackReference("raw_contact_id", 0)
//				.withValue("mimetype", "vnd.android.cursor.item/email_v2")
//				.withValue("data1", "asdfasfad@163.com")
//				.withValue("data2", "2").build();
//		operations.add(op4);

		resolver.applyBatch("com.android.contacts", operations);
	}
	
	/**
	 * 创建桌面快捷方式
	 */
	public void createShut(Context context,int appName,int drawable) {
		// 创建添加快捷方式的Intent
		Intent addIntent = new Intent(
				"com.android.launcher.action.INSTALL_SHORTCUT");
		String title = context.getResources().getString(appName);
		// 加载快捷方式的图标
		Parcelable icon = Intent.ShortcutIconResource.fromContext(
				context, drawable);
		// 创建点击快捷方式后操作Intent,该处当点击创建的快捷方式后，再次启动该程序
		Intent myIntent = new Intent(context,
				context.getClass());
		// 设置快捷方式的标题
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, title);
		// 设置快捷方式的图标
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
		// 设置快捷方式对应的Intent
		addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, myIntent);
		// 发送广播添加快捷方式
		context.sendBroadcast(addIntent);
	
	}
	
	
	/**
	 * 安装APK文件
	 */
	public static  void installApk(File dir,String name,Context mContext) {
		File apkfile = new File(dir, name);
		if (!apkfile.exists()) {
			return;
		}
		// 通过Intent安装APK文件
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
		mContext.startActivity(i);
	}

}
