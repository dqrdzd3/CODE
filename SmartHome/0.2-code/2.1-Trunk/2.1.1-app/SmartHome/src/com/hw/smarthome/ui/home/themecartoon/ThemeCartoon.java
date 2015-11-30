package com.hw.smarthome.ui.home.themecartoon;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.adapter.util.RealTimeUtil;
import com.hw.smarthome.ui.home.themecartoon.po.CartoonInfo;
import com.hw.smarthome.ui.home.themecartoon.pop.CartoonSaidPopWindow;
import com.hw.smarthome.ui.home.themecartoon.util.ThemeCartoonUtil;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.util.Ln;
import com.hw.util.UIUtil;

/**
 * 卡通形象
 * 
 * @author 曾凡
 * @time 2014年7月11日 上午9:51:05
 */
public class ThemeCartoon implements OnClickListener {
	private ImageView mImage;
	private CartoonSaidPopWindow mSaidPop;
	private static ThemeCartoon instance;
	private String currentCursonId;

	public static ThemeCartoon getInstance(ImageView image,
			CartoonSaidPopWindow saidPop, String sid) {
		// if (instance == null) {
		instance = new ThemeCartoon(image, saidPop, sid);
		// }
		return instance;
	}

	public static void setInstance(ThemeCartoon in) {
		instance = in;
	}

	public ThemeCartoon(ImageView image,
			CartoonSaidPopWindow saidPop, String sid) {
		mImage = image;
		mSaidPop = saidPop;
		currentCursonId = sid;
	}

	/** 记录点击 */
	private int clickCount;
	private List<CartoonInfo> cartoonList;
	private int listSum;

	@Override
	public void onClick(View v) {
//		try {
//			List<SensorDetail> sensorList = HomeUtil
//					.getSensorDetails(MainActivity.mContext);
//			// if (RealTimeUtil.getCurrentDetail() == null) {
//			ThemeCartoonUtil.currentSensorid = currentCursonId;
//			cartoonList = ThemeCartoonUtil
//					.getCartoonInfos(sensorList);
//			// } else {
//			// cartoonList = ThemeCartoonUtil
//			// .getCartoonInfos(RealTimeUtil
//			// .getCurrentDetail());
//			// }
//
//			listSum = cartoonList.size();
//			CartoonInfo info = null;
//			if (clickCount == listSum || clickCount > listSum) {
//				clickCount = 0;
//			} else {
//
//			}
//			info = cartoonList.get(clickCount++);
//			if (info != null) {
//				/* 避免出现OOM */
//				// Resources res = MainActivity.mContext
//				// .getResources();
//				// Drawable drawable = res.getDrawable(info.getImageId());
//
//				Bitmap bitMap = UIUtil.readBitMap(
//						MainActivity.mContext
//								.getApplicationContext(), info
//								.getImageId(), 1);
//				mImage.setImageBitmap(bitMap);
//				mSaidPop.getPopTv().setText(info.getTalkText());
//				int y = mImage.getMeasuredHeight();
//				int pY = UIUtil.dip2px(MainActivity.mContext,
//						mSaidPop.getPopWindow().getHeight());
//				if (y > 0) {
//					mSaidPop.getPopWindow().showAsDropDown(mImage);
//				} else {
//					int[] location = new int[2];
//					mImage.getLocationOnScreen(location);
//					mSaidPop.showAtLocation(v,
//							Gravity.NO_GRAVITY, location[0],
//							-location[1]);
//				}
//
//				mSaidPop.getPopWindow().update();
//			}
//		} catch (Exception e) {
//			Ln.e(e, "点击卡通形象异常！");
//		}
	}

	/**
	 * 
	 * @author lijing
	 * @param image
	 *            卡通表情
	 * @param talkText
	 *            卡通语句
	 * @time 2014-8-1 下午2:45:23
	 */
	private void updateCartoon(int image, String talkText) {
		if (image != 0)
			mImage.setImageResource(image);
		mSaidPop.getPopTv().setText(talkText);
		int y = mImage.getMeasuredHeight();
		if (y > 0) {

			mSaidPop.getPopWindow().showAsDropDown(mImage, -200,
					-y - 50);
			mSaidPop.getPopWindow().update();

		}
	}

	public void resetParam() {
		clickCount = 0;
	}

	public ImageView getmImage() {
		return mImage;
	}

	public void setmImage(ImageView mImage) {
		this.mImage = mImage;
	}

	public CartoonSaidPopWindow getmSaidPop() {
		return mSaidPop;
	}

	public void setmSaidPop(CartoonSaidPopWindow mSaidPop) {
		this.mSaidPop = mSaidPop;
	}
}