package com.hw.smarthome.cam.util;

import java.io.File;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;

import com.hw.smarthome.R;
import com.hw.smarthome.cam.CamActivity;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.share.SharePicActivity;
import com.hw.util.Ln;
import com.hw.util.PreferenceUtil;
import com.hw.util.ScreenShot;
import com.hw.util.widget.SelectCustomPopupWindow;

/**
 * @author 曾凡
 * @time 2015年3月23日 下午5:59:11
 */
public class CamUtils {

	public static void shot(final Activity context,
			Camera mCamera, final int cameraOriation,
			final String path) {

		// ExifInterface exif;
		// 拍照
		mCamera.takePicture(null, null, new PictureCallback() {

			public void onPictureTaken(byte[] data, Camera camera) {

				try {
					// 生成照片
					Bitmap bg = takePic(data, camera);
					if (cameraOriation == 1) {
						bg = rotateBitmapByDegree(bg, -90);
					} else {
						bg = rotateBitmapByDegree(bg, 90);
					}
					Bitmap layout = ScreenShot
							.takeScreenShot(context);

					ScreenShot.savePic(
							combine2Bitmap(bg, layout), path);

					((CamActivity) context).recoverViews();
				} catch (Exception e) {
					Ln.e(e, "分享异常");
				}
			}
		});
	}

	public static void share(Activity context, View parent,String path) {
//		Intent intent = new Intent();
		File file = new File(path);
//		intent.putExtra("picPath", Uri.fromFile(file));
//		intent.setClass(context, SharePicActivity.class);
//		context.startActivity(intent);
		
		SelectCustomPopupWindow customPopupWindow = new SelectCustomPopupWindow(context, Uri.fromFile(file), R.layout.share_main);
		
		customPopupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
//		File file = new File(path);
//		Intent intent = new Intent(Intent.ACTION_SEND);
//		intent.setType("image/jpg");
//		intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
//		// intent.putExtra(Intent.EXTRA_TITLE, "com.sina.weibo");
//		// intent.putExtra(Intent.EXTRA_TITLE, "com.tencent.mm");
//		// intent.putExtra(Intent.EXTRA_TITLE, "com.tencent.wblog");
//		// intent.setPackage("com.sina.weibo");
//		// intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
//		// intent.putExtra(Intent.EXTRA_TEXT, "空气电台");
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		List<ResolveInfo> resInfo = context.getPackageManager()
//				.queryIntentActivities(intent,
//						PackageManager.MATCH_DEFAULT_ONLY);
//		if (!resInfo.isEmpty()) {
//			ActivityInfo activityInfo = null;
//			List<Intent> targetedShareIntents = new ArrayList<Intent>();
//			for (ResolveInfo info : resInfo) {
//				boolean flag = false;
//				activityInfo = info.activityInfo;
//				if (activityInfo.packageName
//						.contains("tencent.")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("com.sina.weibo")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("renren.")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("yixin.")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("kaixin.")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("douban.")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("mail.")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("sohu.")) {
//					flag = true;
//				} else if (activityInfo.packageName
//						.contains("weixin")) {
//					flag = true;
//				} else {
//					continue;
//				}
//				if (flag) {
//					Intent targeted = new Intent(
//							Intent.ACTION_SEND);
//					targeted.setType("image/jpg");
//					targeted.putExtra(Intent.EXTRA_STREAM,
//							Uri.fromFile(file));
//					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					targeted.setPackage(activityInfo.packageName);
//					targetedShareIntents.add(targeted);
//				}
//
//			}
//			// A Parcelable[] of Intent or LabeledIntent objects as set with
//			// putExtra(String, Parcelable[]) of additional activities to place
//			// a the front of the list of choices, when shown to the user with a
//			// ACTION_CHOOSER.
//			Intent chooserIntent = Intent.createChooser(intent,
//					"分享");
//			chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
//					targetedShareIntents
//							.toArray(new Parcelable[] {}));
//			try {
//				context.startActivity(chooserIntent);
//			} catch (android.content.ActivityNotFoundException ex) {
//				Toast.makeText(context,
//						"Can't find share component to share",
//						Toast.LENGTH_SHORT).show();
//			}
//		}

	}

	public static void shotShareRecover(final Activity context,
			Camera mCamera,View parent, final int cameraOriation) {
		shot(context, mCamera, cameraOriation,
				ServerConstant.SHOT_CAM_LOC);
		share(context,parent, ServerConstant.SHOT_CAM_LOC);
		((CamActivity) context).camLogo.setVisibility(View.GONE);

		PreferenceUtil.saveCameraLight(context, false);
		((CamActivity) context).camCamControl
				.setVisibility(View.VISIBLE);
		((CamActivity) context).camFlashButton
				.setImageResource(R.drawable.cam_flash_on);

		((CamActivity) context).camTitle
				.setVisibility(View.VISIBLE);
		((CamActivity) context).camBottom
				.setVisibility(View.VISIBLE);

	}

	public static void shotAndShare(final Activity context,
			Camera mCamera, final int cameraOriation) {
		// ExifInterface exif;
		// 拍照
		mCamera.takePicture(null, null, new PictureCallback() {

			public void onPictureTaken(byte[] data, Camera camera) {

				try {
					// 生成照片
					Bitmap bg = takePic(data, camera);
					if (cameraOriation == 1) {
						bg = rotateBitmapByDegree(bg, -90);
					} else {
						bg = rotateBitmapByDegree(bg, 90);
					}
					// ScreenShot.savePic(pic,
					// ServerConstant.SHOT_CAM_TEMP_BG_LOC);
					// 截取layout的图片并进行合成
					// pic = ScreenShot.getScreenShotBm(context,
					// new BitmapDrawable(pic));
					Bitmap layout = ScreenShot
							.takeScreenShot(context);
					// ScreenShot.savePic(otherBitmap,
					// ServerConstant.SHOT_PIC_LOC);

					ScreenShot.savePic(
							combine2Bitmap(bg, layout),
							ServerConstant.SHOT_CAM_LOC);
				} catch (Exception e) {
					Ln.e(e, "分享异常");
				}
				File file = new File(ServerConstant.SHOT_CAM_LOC);
				Intent intent = new Intent(Intent.ACTION_SEND);
				intent.setType("image/*");
				intent.putExtra(Intent.EXTRA_STREAM,
						Uri.fromFile(file));
				intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
				intent.putExtra(Intent.EXTRA_TEXT, "空气电台");
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(Intent.createChooser(
						intent, "请选择"));

				((CamActivity) context).camLogo
						.setVisibility(View.GONE);

				PreferenceUtil.saveCameraLight(context, false);
				((CamActivity) context).camCamControl
						.setVisibility(View.VISIBLE);
				((CamActivity) context).camFlashButton
						.setImageResource(R.drawable.cam_flash_on);

				((CamActivity) context).camTitle
						.setVisibility(View.VISIBLE);
				((CamActivity) context).camBottom
						.setVisibility(View.VISIBLE);
			}
		});
		// try {
		// ScreenShot.takeScreenShot(context);
		// exif = new ExifInterface(ServerConstant.SHOT_PIC_LOC);
		// /*
		// * TAG_DATETIME时间日期 　　TAG_FLASH闪光灯 　　TAG_GPS_LATITUDE纬度
		// * 　　TAG_GPS_LATITUDE_REF纬度参考 　　TAG_GPS_LONGITUDE经度
		// * 　　TAG_GPS_LONGITUDE_REF经度参考 　　TAG_IMAGE_LENGTH图片长
		// * 　　TAG_IMAGE_WIDTH图片宽 　　TAG_MAKE设备制造商 　　TAG_MODEL设备型号
		// * 　　TAG_ORIENTATION方向 　　TAG_WHITE_BALANCE白平衡
		// */
		// exif.setAttribute(ExifInterface.TAG_ORIENTATION, "");
		// exif.saveAttributes();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	/**
	 * 合成图片
	 */
	public static Bitmap combinePic(Bitmap source, Bitmap target) {

		Bitmap newBitmap = null;

		newBitmap = Bitmap.createBitmap(target);
		Canvas canvas = new Canvas(newBitmap);

		int w = target.getWidth();
		int h = target.getHeight();

		int w_2 = source.getWidth();
		int h_2 = source.getHeight();

		canvas.drawRect(0, 0, target.getWidth(),
				target.getHeight(), null);

		canvas.drawBitmap(source, Math.abs(w - w_2) / 2,
				Math.abs(h - h_2) / 2, null);
		canvas.save(Canvas.ALL_SAVE_FLAG);
		// 存储新合成的图片
		canvas.restore();
		return newBitmap;

	}

	public static Bitmap combine2Bitmap(Bitmap bg, Bitmap layout) {
		Drawable[] array = new Drawable[2];
		// 曾凡 20150330
		// layout = big(layout, bg.getWidth(),
		// bg.getHeight());
		array[0] = new BitmapDrawable(bg);
		array[1] = new BitmapDrawable(layout);
		LayerDrawable la = new LayerDrawable(array);
		// 其中第一个参数为层的索引号，后面的四个参数分别为left、top、right和bottom
		la.setLayerInset(1, 0, 0, 0, 0);
		la.setLayerInset(0, 0, 0, 0, 0);
		return drawableToBitmap(la,bg.getWidth(),bg.getHeight());
//		return bg;
	}
	public static Bitmap drawableToBitmap(Drawable drawable,int w,int h) {
//		// 取 drawable 的长宽
//		int w = drawable.getIntrinsicWidth();
//		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}
	// 按一定大小放大或缩小
	public static Bitmap big(Bitmap b, float x, float y) {
		int w = b.getWidth();
		int h = b.getHeight();
		float sx = (float) x / w;// 要强制转换，不转换我的在这总是死掉。
		float sy = (float) y / h;
		Matrix matrix = new Matrix();
		matrix.postScale(sx, sy); // 长和宽放大缩小的比例
		Bitmap resizeBmp = Bitmap.createBitmap(b, 0, 0, w, h,
				matrix, true);
		return resizeBmp;
	}

	// 旋转角度获取图片
	public static Bitmap rotateBitmapByDegree(Bitmap bm,
			int degree) {
		Bitmap returnBm = null;

		// 根据旋转角度，生成旋转矩阵
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		try {
			// 将原始图片按照旋转矩阵进行旋转，并得到新的图片
			returnBm = Bitmap.createBitmap(bm, 0, 0,
					bm.getWidth(), bm.getHeight(), matrix, true);
		} catch (OutOfMemoryError e) {
		}
		if (returnBm == null) {
			returnBm = bm;
		}
		if (bm != returnBm) {
			bm.recycle();
		}
		return returnBm;
	}

	public static Bitmap drawableToBitmap(Drawable drawable) {
		// 取 drawable 的长宽
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// 取 drawable 的颜色格式
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// 建立对应 bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// 建立对应 bitmap 的画布
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// 把 drawable 内容画到画布中
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * 照相机照片
	 * 
	 * @author 曾凡
	 * @time 2015年3月26日 上午10:41:15
	 */
	public static Bitmap takePic(byte[] data, Camera camera) {

		Bitmap res = BitmapFactory.decodeByteArray(data, 0,
				data.length);
		// ScreenShot.savePic(res,
		// ServerConstant.SHOT_CAM_TEMP_BG_LOC);
		return res;
	}

	public static void toggle(Context context) {
		String camOri = PreferenceUtil
				.getCameraOriation(context);
		if (camOri.equals("front")) { // 切换到后置摄像头
			PreferenceUtil.saveCameraOriation(context, "back");
		}
		if (camOri.equals("back")) { // 切换到前置摄像头
			PreferenceUtil.saveCameraOriation(context, "front");
		}

	}

	/*
	 * public static void fleshLight(Activity context, Camera camera) {
	 * 
	 * if (!PreferenceUtil.getCameraLight(context)) {
	 * PreferenceUtil.saveCameraLight(context, true); // 打开 Camera.Parameters
	 * parameter = camera.getParameters();
	 * 
	 * parameter.setFlashMode(Parameters.FLASH_MODE_TORCH);
	 * 
	 * camera.setParameters(parameter); } else { // 关闭
	 * PreferenceUtil.saveCameraLight(context, false); Camera.Parameters
	 * parameter = camera.getParameters();
	 * parameter.setFlashMode(Parameters.FLASH_MODE_OFF);
	 * 
	 * camera.setParameters(parameter); }
	 * 
	 * }
	 */
}
