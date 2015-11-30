package com.hw.util;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.widget.Toast;

import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

/**
 * 分享
 * @author lijing
 * @time 2014-7-21 下午2:10:11
 */
public class ShareUtil {
	/**
	 * 微信分享使用
	 */
	public static final String APP_WX_ID = "wx55ada1de78e391c9";
	//public static final String APP_WX_ID = "wxd3e721f5a1dc61ca";

	public static IWXAPI api;
	
	public static void localShare(Context context,String content){
		Intent intent = new Intent(Intent.ACTION_SEND); // 启动分享发送到属性
		intent.setType("text/plain"); // 分享发送到数据类型
		intent.putExtra(Intent.EXTRA_SUBJECT, "分享"); // 分享的主题
		intent.putExtra(Intent.EXTRA_TEXT, content); // 分享的内容
		//intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 允许intent启动新的activity
		context.startActivity(Intent.createChooser(intent, "分享")); // //目标应用选择对话框的标题

	}
//	public static void showShare(Context context,String content,String url){
//		//图片保存在sd卡根目录里
//		ByteArrayOutputStream baosArrayOutputStream = new ByteArrayOutputStream();
//		Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
//		bitmap.compress(Bitmap.CompressFormat.PNG, 100, baosArrayOutputStream);
//		FileUtils.writeFile(baosArrayOutputStream.toByteArray(), "smarthome.png");
//		//调用sharesdk
//		ShareSDK.initSDK(context);
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        
//        // 分享时Notification的图标和文字
//        oks.setNotification(R.drawable.ic_launcher, context.getString(R.string.app_name));
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle("空气电台");
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl(url);
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText(content);
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/smarthome.png");
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(url);
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment(content);
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(context.getString(R.string.app_name));
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//       oks.setSiteUrl("http://m.hanwei.cn");
//
//        // 启动分享GUI
//        oks.show(context);
//        
//
//	}
//	public void showShare(Context context,String title,String titleUrl,String text,String imgPath,String imgUrl,String comment,String site,String siteUrl){
//		ShareSDK.initSDK(context);
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        
//        // 分享时Notification的图标和文字
//        oks.setNotification(R.drawable.ic_launcher, context.getString(R.string.app_name));
//        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
//        oks.setTitle(title);
//        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
//        oks.setTitleUrl(titleUrl);
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText(text);
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath(imgPath);
//        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl(imgUrl);
//        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment(comment);
//        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite(site);
//        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl(siteUrl);
//
//        // 启动分享GUI
//        oks.show(context);
//	}
	/*
	 * 以下是单独使用微信接口实现的
	 */
	public static void WXWebpageShare(Context context,WXMediaMessage msg){
		api = WXAPIFactory.createWXAPI(context, APP_WX_ID);
		api.registerApp(APP_WX_ID);
		if(api.isWXAppInstalled()){
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("webpage");
		req.message = msg;
		req.scene = SendMessageToWX.Req.WXSceneSession;
		api.sendReq(req);
		}else{
			Toast.makeText(context,"您没有安装微信客户端，无法使用该功能！", Toast.LENGTH_LONG).show();
		}
	}
	private static String buildTransaction(final String type) {
		return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
	}
	public static void WXImageShare(Context context,WXMediaMessage msg){
		api = WXAPIFactory.createWXAPI(context, APP_WX_ID);
		api.registerApp(APP_WX_ID);
		if(api.isWXAppInstalled()){
		SendMessageToWX.Req req = new SendMessageToWX.Req();
		req.transaction = buildTransaction("img");
		req.message = msg;
		req.scene = SendMessageToWX.Req.WXSceneSession;
		api.sendReq(req);
		}else{
			Toast.makeText(context,"您没有安装微信客户端，无法使用该功能！", Toast.LENGTH_LONG).show();
		}
	}

	
	public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		bmp.compress(CompressFormat.PNG, 100, output);
		if (needRecycle) {
			bmp.recycle();
		}
		
		byte[] result = output.toByteArray();
		try {
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean isUsableWX(Context context){
		boolean isUsable = true;
		api = WXAPIFactory.createWXAPI(context, APP_WX_ID);
		api.registerApp(APP_WX_ID);
		if(api.isWXAppInstalled()){
			
		}
		return isUsable;
	}
	

	
}
