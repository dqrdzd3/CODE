package com.zf.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;

/**
 * @author 曾凡
 * @time 2015年5月29日 下午5:20:57
 */
public class BottomBarView extends FrameLayout {
	private Context mContext;
	private BootstrapButton forward;
	private BootstrapButton next;

	public BottomBarView(Context context) {
		super(context);
		mContext = context;
		initView();
	}

	public BottomBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
	}

	private void initView() {
		LayoutInflater.from(mContext).inflate(
				R.layout.view_bottombar, this);
		forward = (BootstrapButton) findViewById(R.id.forward);
		next = (BootstrapButton) findViewById(R.id.next);
	}

	public void setBtnForwardText(String text) {
		forward.setText(text);
	}

	public void setForwardListener(
			OnClickListener listener) {
		forward.setOnClickListener(listener);
	}

	public void setBtnNextText(String text) {
		next.setText(text);
	}

	public void setNextListener(
			OnClickListener listener) {
		next.setOnClickListener(listener);
	}

	public BootstrapButton getForward() {
		return forward;
	}

	public void setForward(BootstrapButton forward) {
		this.forward = forward;
	}

	public BootstrapButton getNext() {
		return next;
	}

	public void setNext(BootstrapButton next) {
		this.next = next;
	}

}
