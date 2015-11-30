package com.hw.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import android.widget.ScrollView;

import com.hw.smarthome.R;
import com.hw.smarthome.server.constant.ServerConstant;

/**
 * android之截屏(包括截取scrollview与listview的)
 * 
 * @author 闫威
 * 
 */
public class ScreenShot {
	public static Bitmap getScreenShotBm(Activity activity,
			Drawable draw) {
		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			view.setBackgroundDrawable(draw);
		} else {
			view.setBackground(draw);
		}
		// view.setDrawingCacheEnabled(true);
		// view.buildDrawingCache();
		// Bitmap b1 = view.getDrawingCache();
		//
		// // 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		// System.out.println(statusBarHeight);
		//
		// // 获取屏幕长和高
		int width = activity.getWindowManager()
				.getDefaultDisplay().getWidth();
		int height = activity.getWindowManager()
				.getDefaultDisplay().getHeight();
		// // 去掉标题栏
		// // Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
		// Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
		// - statusBarHeight);
		Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
				view.getHeight(), Bitmap.Config.ARGB_8888);
		Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, width,
				height);
		final Canvas canvas = new Canvas(b);

		view.draw(canvas);
		view.destroyDrawingCache();
		return b;
	}

	public static Bitmap getScreenShotBm(Activity activity) {
		// View是你需要截图的View
		View view = activity.getWindow().getDecorView();
		// view.setDrawingCacheEnabled(true);
		// view.buildDrawingCache();
		// Bitmap b1 = view.getDrawingCache();
		//
		// // 获取状态栏高度
		Rect frame = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(frame);
		int statusBarHeight = frame.top;
		// System.out.println(statusBarHeight);
		//
		// // 获取屏幕长和高
		int width = activity.getWindowManager()
				.getDefaultDisplay().getWidth();
		int height = activity.getWindowManager()
				.getDefaultDisplay().getHeight();
		// // 去掉标题栏
		// // Bitmap b = Bitmap.createBitmap(b1, 0, 25, 320, 455);
		// Bitmap b = Bitmap.createBitmap(b1, 0, statusBarHeight, width, height
		// - statusBarHeight);
		Bitmap bitmap = Bitmap.createBitmap(view.getWidth(),
				view.getHeight(), Bitmap.Config.ARGB_8888);
		Bitmap b = Bitmap.createBitmap(bitmap, 0, 0, width,
				height);
		final Canvas canvas = new Canvas(b);

		view.draw(canvas);
		view.destroyDrawingCache();
		return b;
	}

	public static Bitmap takeScreenShot(Activity activity) {
		Bitmap b = getScreenShotBm(activity);
		// savePic(b, ServerConstant.SHOT_CAM_LOC);
		return b;
	}

	// 保存到sdcard
	public static void savePic(Bitmap b, String strFileName) {
		FileOutputStream fos = null;
		try {
			if (ensureSDCardAccess()) {
				File file = new File(strFileName);
				File parent = new File(file.getParent());
				if (!parent.exists()) {
					parent.mkdirs();
				}
				if (!file.exists()) {
					file.createNewFile();
				}

				fos = new FileOutputStream(strFileName);
				if (null != fos) {
					b.compress(Bitmap.CompressFormat.JPEG, 100,
							fos);
					fos.flush();
					fos.close();
				}

			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 把View对象转换成bitmap
	 * */
	public static Bitmap convertViewToBitmap(View view) {
		view.measure(MeasureSpec.makeMeasureSpec(0,
				MeasureSpec.UNSPECIFIED), MeasureSpec
				.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
		view.layout(0, 0, view.getMeasuredWidth(),
				view.getMeasuredHeight());
		view.buildDrawingCache();
		Bitmap bitmap = view.getDrawingCache();
		if (bitmap != null) {
			System.out.println("这不是nullde1");
			Log.d("nullde1", "nullde1");
		} else {
			System.out.println("这nullnulllnulnlul");
		}
		return bitmap;
	}

	// 程序入口1
	public static void shoot(Activity a) {
		ScreenShot.savePic(ScreenShot.takeScreenShot(a),
				"/sdcard/screen_test.png");
	}

	// 程序入口2
	public static void shootView(View view) {
		ScreenShot.savePic(ScreenShot.convertViewToBitmap(view),
				"sdcard/xx.png");
	}

	public static Bitmap getViewBitmap(View v) {
		v.clearFocus();
		v.setPressed(false);

		boolean willNotCache = v.willNotCacheDrawing();
		v.setWillNotCacheDrawing(false);

		// Reset the drawing cache background color to fully transparent
		// for the duration of this operation
		int color = v.getDrawingCacheBackgroundColor();
		v.setDrawingCacheBackgroundColor(0);

		if (color != 0) {
			v.destroyDrawingCache();
		}
		v.buildDrawingCache();

		Bitmap cacheBitmap = v.getDrawingCache();
		// cacheBitmap = Bitmap.createBitmap(v.getWidth(),v.getHeight(),
		// Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(cacheBitmap);
		canvas.drawBitmap(cacheBitmap, 0, 0, null);
		v.draw(canvas);

		if (cacheBitmap == null) {
			Log.e("TTTTTTTTActivity", "failed getViewBitmap("
					+ v + ")", new RuntimeException());
			return null;
		}

		Bitmap bitmap = Bitmap.createBitmap(cacheBitmap);

		// 测试输出
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(
					ServerConstant.SHOT_PIC_LOC);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if (null != out) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
						out);

				out.flush();
				out.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		// Restore the view
		// v.destroyDrawingCache();
		// v.setWillNotCacheDrawing(willNotCache);
		// v.setDrawingCacheBackgroundColor(color);

		return bitmap;
	}

	/**
	 * 截取scrollview的屏幕
	 * **/
	public static String getBitmapByView(ScrollView scrollView) {
		String filePath = ServerConstant.SHOT_PIC_LOC;
		int h = 0;
		Bitmap bitmap = null;
		// 获取listView实际高度
		for (int i = 0; i < scrollView.getChildCount(); i++) {
			h += scrollView.getChildAt(i).getHeight();
			scrollView.getChildAt(i).setBackgroundResource(
					R.drawable.ui_home_bg_long_blur);
		}
		Log.d(TAG, "实际高度:" + h);
		Log.d(TAG, " 高度:" + scrollView.getHeight());
		// 创建对应大小的bitmap
		bitmap = Bitmap.createBitmap(scrollView.getWidth(), h,
				Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(bitmap);

		// 增加的
		// Paint paint = new Paint();
		// paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
		// canvas.drawBitmap(, matrix, paint);
		// 结束

		scrollView.draw(canvas);
		// 测试输出
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if (null != out) {
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100,
						out);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		return filePath;
	}

	private static String TAG = "Listview and ScrollView item 截图:";

	/**
	 * 截图listview
	 * **/
	public static Bitmap getbBitmap(ListView listView) {
		int h = 0;
		Bitmap bitmap = null;
		// 获取listView实际高度
		for (int i = 0; i < listView.getChildCount(); i++) {
			h += listView.getChildAt(i).getHeight();
		}
		Log.d(TAG, "实际高度:" + h);
		Log.d(TAG, "list 高度:" + listView.getHeight());
		// 创建对应大小的bitmap
		bitmap = Bitmap.createBitmap(listView.getWidth(), h,
				Bitmap.Config.ARGB_8888);
		final Canvas canvas = new Canvas(bitmap);
		listView.draw(canvas);
		// 测试输出
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("/sdcard/screen_test.png");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			if (null != out) {
				bitmap.compress(Bitmap.CompressFormat.PNG, 100,
						out);
				out.flush();
				out.close();
			}
		} catch (IOException e) {
			// TODO: handle exception
		}
		return bitmap;
	}

	private static boolean ensureSDCardAccess() {
		File file = new File(ServerConstant.APP_FILE_ROOT);
		if (file.exists()) {
			return true;
		} else if (file.mkdirs()) {
			return true;
		}
		return false;
	}

}
