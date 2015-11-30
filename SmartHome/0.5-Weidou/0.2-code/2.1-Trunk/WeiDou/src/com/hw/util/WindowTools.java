package com.hw.util;

import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.Window;
import android.view.WindowManager;

/**
 * UI Windows工具 <br>
 * (maximum line width 65)
 * 
 * @author 曾凡
 * @date 2014-4-6下午8:44:33
 */
public class WindowTools {
	/**
	 * 全透明
	 * @author 曾凡
	 * @param activity
	 * @time 2014年5月20日 下午3:43:17
	 */
	public static void transAll(Activity activity){
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			/* 使用了默认样式 @android:style/Theme.NoTitleBar */
			if(activity.getActionBar()!=null){
				activity.getActionBar().hide();
			}
		}
		initSystemBar(activity.getWindow());
	}
	public static void initSystemBar(Window win) {
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	public static void setFullscreen(Window win, boolean on) {
		WindowManager.LayoutParams winParams = win
				.getAttributes();
		int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
		setWindow(win, on, flag);
	}

	public static void setTranslucentNavigation(Window win,
			boolean on) {
		int flag = WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
		setWindow(win, on, flag);
	}

	private static void setWindow(Window win, boolean on,
			int flag) {

		WindowManager.LayoutParams winParams = win
				.getAttributes();
		if (on) {
			winParams.flags |= flag;
		} else {
			winParams.flags &= ~flag;
		}
		win.setAttributes(winParams);

	}
}
