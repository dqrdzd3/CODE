package com.hw.util;


import java.io.InputStream;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.hw.weidou.R;

public class UIUtil {
	public final static int LISTVIEW_ACTION_INIT = 0x01;
	public final static int LISTVIEW_ACTION_REFRESH = 0x02;
	public final static int LISTVIEW_ACTION_SCROLL = 0x03;
	public final static int LISTVIEW_ACTION_CHANGE_CATALOG = 0x04;

	public final static int LISTVIEW_DATA_MORE = 0x01;
	public final static int LISTVIEW_DATA_LOADING = 0x02;
	public final static int LISTVIEW_DATA_FULL = 0x03;
	public final static int LISTVIEW_DATA_EMPTY = 0x04;
	/**
	 * 弹出Toast消息
	 * 
	 * @param msg
	 */
	public static void ToastMessage(Context cont, String msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, int msg) {
		Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
	}

	public static void ToastMessage(Context cont, String msg,
			int time) {
		Toast.makeText(cont, msg, time).show();
	}
	/**
	 * dip转px
	 * dip即device independent pixel，设备独立像素
	 * @author 曾凡
	 * @param context
	 * @param dipValue
	 * @return
	 * @time 2014年7月10日 下午2:31:19
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources()
				.getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
	/**
	 * px转dip
	 * px即pixels，是绝对像素，有多少个像素点就是多少个像素点。
	 * @author 曾凡
	 * @param context
	 * @param pxValue
	 * @return
	 * @time 2014年7月10日 下午2:31:58
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources()
				.getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 获取屏幕的宽高
	 * 
	 * @author 曾凡
	 * @param context
	 * @return
	 * @time 2014年7月10日 下午2:12:48
	 */
	public static int[] getScreenMetrics(Activity context) {
		int WIDTH = 0;
		int HEIGHT = 0;
		if (WIDTH == 0 || HEIGHT == 0) {
			DisplayMetrics dm = new DisplayMetrics();
			context.getWindowManager().getDefaultDisplay()
					.getMetrics(dm);

			WIDTH = dm.widthPixels;
			HEIGHT = dm.heightPixels;
		}
		return new int[] { WIDTH, HEIGHT };
	}
	/**
	 * 判断是否联网并提示
	 * @author lijing
	 * @param context
	 * @time 2014-7-18 下午1:18:46
	 * @return boolean
	 */
	public static boolean networkConnectedHint(Context context){
		boolean isConnected = NetUtil.isNetworkConnected(context);
		if(!isConnected){
			ToastMessage(context, R.string.network_not_connected);
		}
		return isConnected;
	}
	
	public static void setListViewHeightBasedOnChildren(ListView listView) {  
        ListAdapter listAdapter = listView.getAdapter();   
        if (listAdapter == null) {  
            // pre-condition  
            return;  
        }  
  
        int totalHeight = 0;  
        for (int i = 0; i < listAdapter.getCount(); i++) {  
            View listItem = listAdapter.getView(i, null, listView);  
            listItem.measure(0, 0);  
            totalHeight += listItem.getMeasuredHeight();  
        }  
  
        ViewGroup.LayoutParams params = listView.getLayoutParams();  
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));  
        listView.setLayoutParams(params);  
    }  
	/**
	 * 关闭软键盘
	 * @author lijing
	 * @param context
	 * @time 2014-8-4 下午12:35:43
	 */
	public static void hideSoftKeyboard(Activity context){
		View view = context.getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
	}

	public static Bitmap readBitMap(Context context, int resId,int compressSize) {
		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inPreferredConfig = Bitmap.Config.RGB_565;
		opt.inPurgeable = true;
		opt.inInputShareable = true;
		opt.inSampleSize = compressSize;
		// 获取资源图片
		InputStream is = context.getResources().openRawResource(resId);
		return BitmapFactory.decodeStream(is, null, opt);
		}

}
