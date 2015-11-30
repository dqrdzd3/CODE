package com.special.ResideMenu;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by thonguyen on 15/4/14.
 */
class TouchDisableView extends ViewGroup {

	private View mContent;

	// private int mMode;
	private boolean mTouchDisabled = false;

	public TouchDisableView(Context context) {
		this(context, null);
	}

	public TouchDisableView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void setContent(View v) {
		if (mContent != null) {
			this.removeView(mContent);
		}
		mContent = v;
		addView(mContent);
	}

	public View getContent() {
		return mContent;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec,
			int heightMeasureSpec) {

		int width = getDefaultSize(0, widthMeasureSpec);
		int height = getDefaultSize(0, heightMeasureSpec);
		setMeasuredDimension(width, height);

		final int contentWidth = getChildMeasureSpec(
				widthMeasureSpec, 0, width);
		final int contentHeight = getChildMeasureSpec(
				heightMeasureSpec, 0, height);
		mContent.measure(contentWidth, contentHeight);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t,
			int r, int b) {
		final int width = r - l;
		final int height = b - t;
		mContent.layout(0, 0, width, height);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return mTouchDisabled;
	}

	void setTouchDisable(boolean disableTouch) {
		mTouchDisabled = disableTouch;
	}

	boolean isTouchDisabled() {
		return mTouchDisabled;
	}

	/**
	 * 解决2.3点击菜单没反应
	 * 
	 * @author lijing
	 * @param event
	 * @return
	 * @time 2014-8-2 上午11:35:46
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		/* 曾 必须继承父类的方法，不能复写 */
		boolean touch =  super.onTouchEvent(event);
		/* 曾  是以不能点击视图为代价实现的，所以需要跨版本 */
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
			return touch;
		}
		return !touch;
	}
}
