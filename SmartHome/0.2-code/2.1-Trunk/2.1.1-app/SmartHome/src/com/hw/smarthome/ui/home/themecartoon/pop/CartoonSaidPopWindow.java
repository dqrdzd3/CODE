package com.hw.smarthome.ui.home.themecartoon.pop;

import android.content.Context;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hw.smarthome.R;

/**
 * 卡通说的话
 * 
 * @author 曾凡
 * @time 2014年7月10日 下午3:49:48
 */
public class CartoonSaidPopWindow {
	private static CartoonSaidPopWindow instance;
	private static Context mContext;

	public static CartoonSaidPopWindow getInstance(
			Context context) {
		instance = new CartoonSaidPopWindow(context);
		return instance;
	}

	public CartoonSaidPopWindow(Context context) {
		mContext = context;
		initPopWindow();
	}

	private PopupWindow popWindow;
	private TextView popTv;

	/**
	 * 初始化一个对话
	 * 
	 * @author 曾凡
	 * @param context
	 * @time 2014年7月10日 下午3:52:06
	 */
	private void initPopWindow() {

		View popView = LayoutInflater.from(mContext).inflate(
				R.layout.ui_home_pop_window, null);

		popWindow = new PopupWindow(popView, 450,
				LayoutParams.WRAP_CONTENT, false);
		popTv = (TextView) popView.findViewById(R.id.tv_dec);
		popTv.setVerticalScrollBarEnabled(true);

		popWindow.setOutsideTouchable(true);
		popWindow.setTouchable(true);

		popWindow.getContentView().setOnTouchListener(
				new View.OnTouchListener() {

					@Override
					public boolean onTouch(View v,
							MotionEvent event) {
						if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
							popWindow.dismiss();
							// popWindow = null;
						}
						return false;
					}
				});
	}

	public PopupWindow getPopWindow() {
		return popWindow;
	}

	public void setPopWindow(PopupWindow popWindow) {
		this.popWindow = popWindow;
	}

	public TextView getPopTv() {
		return popTv;
	}

	public void setPopTv(TextView popTv) {
		this.popTv = popTv;
	}

	public void showAtLocation(View view, int center, int width,
			int height) {
		getPopWindow().showAtLocation(view, Gravity.CENTER,
				view.getLayoutParams().width,
				view.getLayoutParams().height);
	}

}
