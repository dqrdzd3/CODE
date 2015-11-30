package com.hw.smarthome.view.pop.progress;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager.LayoutParams;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.util.Ln;

/**
 * 弹出窗体状态栏
 * 
 * @author 曾凡
 * @time 2014年6月26日 下午3:19:07
 */
public class PopProgress {
	
	public static PopProgress singlePopProgress = null;

	public static PopProgress getInstance(Context context,
			View parent) {
		singlePopProgress = new PopProgress(context, parent);
		return singlePopProgress;
	}
	public static PopProgress getInstance(Context context,
			View parent,boolean flag,String failTest) {
		singlePopProgress = new PopProgress(context, parent,flag,failTest);
		return singlePopProgress;
	}
	public static PopProgress getInstance(Context context,
			View parent, long time) {
		if (singlePopProgress == null) {
			singlePopProgress = new PopProgress(context, parent,
					time);
		}
		return singlePopProgress;
	}

	OtherIntent otherIntent;

	public static interface OtherIntent {
		void closeIntent();
	}

	public void setOtherIntent(OtherIntent d) {
		otherIntent = d;
	}

	private PopupWindow popupWindow = null;
	private Context mContext = null;
	private View mParent = null;
	private TextView mPopProgressText = null;
	private ProgressBar mPopProgressBar = null;
	private ImageView mPopResultImage = null;
	// private long startTime;// 定义成员变量，标记pop显示的时间
	private boolean isTimeOut = false;
	private long delayTime = 60*1000;
	private boolean flag = false;     //判断是否可以中断
	private String failText = null;
	private boolean rsour = false;

	public PopProgress(Context context, View parent) {
		this.mContext = context;
		this.mParent = parent;
		setPopupWindow(initPopWindow());
		MyThread mThread = new MyThread();
	
		mThread.start();// 开启线程
		// startTime = System.currentTimeMillis();//为显示的开始时间赋值
	}

	public PopProgress(Context context, View parent, boolean  flag,String failText) {
		this.mContext = context;
		this.mParent = parent;
		this.flag = flag;
		setPopupWindow(initPopWindow());
		MyThread mThread = new MyThread();
		delayTime = 120*1000;
		this.failText = failText;
		mThread.start();// 开启线程
		// startTime = System.currentTimeMillis();//为显示的开始时间赋值
	}
	
	public PopProgress(Context context, View parent, long time) {
		this(context, parent);
		this.delayTime = time;

	}

	/**
	 * 初始化一个[弹出窗体状态栏]
	 * 
	 * @author 曾凡
	 * @time 2014年6月26日 下午3:30:43
	 */
	public PopupWindow initPopWindow() {
		View popView = LayoutInflater.from(mContext).inflate(
				R.layout.view_pop_progress, null);
		mPopProgressText = (TextView) popView
				.findViewById(R.id.popProgressText);
		mPopProgressBar = (ProgressBar) popView
				.findViewById(R.id.popProgressBar);
		mPopResultImage = (ImageView) popView
				.findViewById(R.id.popResultImage);
		mPopResultImage.setVisibility(View.GONE);

		popupWindow = new PopupWindow(popView,
				LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT, false);

		popupWindow.setOutsideTouchable(flag);
		popupWindow.setTouchable(true);

		popupWindow.getContentView().setOnTouchListener(
				new View.OnTouchListener() {
					@Override
					public boolean onTouch(View v,
							MotionEvent event) {
						if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
							popupWindow.dismiss();
						}
						return false;
					}
				});

		popupWindow.showAtLocation(mParent,
				Gravity.CENTER_VERTICAL, 0, 0);

		return popupWindow;
	}

	/**
	 * 显示结果
	 * 
	 * @author 曾凡
	 * @param res
	 *            成功或失败
	 * @param msg
	 * @time 2014年7月3日 下午12:30:18
	 */
	public void showResult(boolean res, String msg) {
		rsour = res;
		showImage(res);
		setText(msg);
		delayTime = 3000;
	}
	
	public void showImage(boolean res) {
		if (res) {
			// mPopResultImage
			// .setImageResource(R.drawable.view_pop_progress_success);
			mPopResultImage.setImageResource(R.drawable.success);
		} else {
			// mPopResultImage
			// .setImageResource(R.drawable.view_pop_progress_failure);
			mPopResultImage.setImageResource(R.drawable.fail);
		}
		mPopResultImage.setVisibility(View.VISIBLE);
		mPopProgressBar.setVisibility(View.GONE);

	}

	public void showProgress() {
		mPopProgressBar.setVisibility(View.VISIBLE);

	}

	public void hiddenProgerss() {
		mPopProgressBar.setVisibility(View.INVISIBLE);
	}

	/**
	 * 设置状态内容
	 * 
	 * @author 曾凡
	 * @param msg
	 * @time 2014年7月3日 上午11:45:43
	 */
	public void setText(String msg) {
		mPopProgressText.setText(msg);
		popupWindow.update();

	}
	public void setOutsideTouchable(Boolean flag){
		popupWindow.setOutsideTouchable(flag);
	}
	public PopupWindow getPopupWindow() {
		
		return popupWindow;
	}

	public void setPopupWindow(PopupWindow popupWindow) {
		this.popupWindow = popupWindow;

	}

	private class MyThread extends Thread {

		public void run() {
			try {
				// Thread.sleep(delayTime);
				// long endTime = System.currentTimeMillis();

				// Ln.i(endTime-startTime-5000);
				// 注意：由于程序的执行也需要耗费时间，所以结束时间和开始时间不可能完全相差5s,
				// 只能控制在一个很小的误差内，而这个误差对用户的点击间隔而言是可以忽略的，这里去误差时间为50毫秒。
				if (popupWindow != null) {
					Message msgMessage = handler
							.obtainMessage(1);
					handler.sendMessageDelayed(msgMessage, 1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	final Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				delayTime = delayTime - 1000;
				if (delayTime > 0) {
					if (failText!=null) {
						Message msgMessage = handler
								.obtainMessage(2);
						handler.sendMessageDelayed(msgMessage, 1000);
					}else{
						Message msgMessage = handler
								.obtainMessage(1);
						handler.sendMessageDelayed(msgMessage, 1000);
					}

				} else {

					if (popupWindow.isShowing()) {
						try {
							
							popupWindow.dismiss();
						} catch (Exception e) {

						}
					}
					singlePopProgress = null;
					Ln.i("计数完成");
				}
				break;
			case 2:
				delayTime = delayTime - 1000;
				if (delayTime > 0) {
					Message msgMessage = handler
							.obtainMessage(2);
					handler.sendMessageDelayed(msgMessage, 1000);

				} else {

					if (popupWindow.isShowing()) {
						try {
							if (!rsour) {
								showImage(rsour);
								setText(failText);
							}
					
						
						} catch (Exception e) {

						}
					}
					singlePopProgress = null;
					Ln.i("计数完成");
				}
				break;

			}

			
			
		}
	};

}
