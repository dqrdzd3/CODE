package com.hw.smarthome.view.scrollview;

/**
 * @author 曾凡
 * @time 2015年8月3日 上午11:58:11
 */
public interface ScrollViewListener {
	void onScrollChanged(ObservableScrollView scrollView, int x,
			int y, int oldx, int oldy);
}