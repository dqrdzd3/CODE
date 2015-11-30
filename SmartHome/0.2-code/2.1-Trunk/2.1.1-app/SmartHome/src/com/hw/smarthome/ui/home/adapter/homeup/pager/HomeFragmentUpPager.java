package com.hw.smarthome.ui.home.adapter.homeup.pager;

import android.graphics.Color;
import android.os.Handler;
import android.os.Build.VERSION;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.ui.home.themecartoon.util.ThemeCartoonUtil;
import com.hw.smarthome.ui.home.util.HomeUtil;
import com.hw.smarthome.ui.weather.CityFragment;
import com.hw.smarthome.ui.weather.util.CityUtils;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年10月29日 上午10:01:49
 */
public class HomeFragmentUpPager extends
		HomeFragmentUpPagerV2Base implements
		CityUtils.EventHandler {
	/** 更新AQI */
	private Handler mAQIHandler = new Handler();
	private Runnable updateAQIThread = new Runnable() {
		@Override
		public void run() {
			try {
				birdAnime.stop();
				birdAnime.start();
				updateAQI();
				// updateWeidou();
				mAQIHandler.postDelayed(updateAQIThread,
						3 * 1000);
			} catch (Exception e) {
				Ln.e(e, "初始化数据异常");
			}
		}
	};

	private Handler mAnimationHandler = new Handler();
	private Runnable animationThread = new Runnable() {
		@Override
		public void run() {
			try {
				// if (VERSION.SDK_INT > 11) {
				// if (birdPos > mParentView.getX()) {
				// birdPos = 0;
				// }
				slideview(1000 * 90, 0, uiHomePagerBird, 0, 1600);
				mAnimationHandler.postDelayed(animationThread,
						1000 * 15);
				// }
			} catch (Exception e) {
				Ln.e(e, "初始化数据异常");
			}
		}
	};

	public static void slideview(int durationMillis,
			int delayMillis, final View view, final float p1,
			final float p2) {
		TranslateAnimation animation = new TranslateAnimation(
				p1, p2, 0, 0);
		animation.setInterpolator(new OvershootInterpolator());
		animation.setDuration(durationMillis);
		animation.setStartOffset(delayMillis);
		animation
				.setAnimationListener(new Animation.AnimationListener() {
					@Override
					public void onAnimationStart(
							Animation animation) {
					}

					@Override
					public void onAnimationRepeat(
							Animation animation) {
					}

					@Override
					public void onAnimationEnd(
							Animation animation) {
						int left = view.getLeft()
								+ (int) (p2 - p1);
						int top = view.getTop();
						int width = view.getWidth();
						int height = view.getHeight();
						view.clearAnimation();
						view.layout(left, top, left + width, top
								+ height);
					}
				});
		view.startAnimation(animation);
	}

	// private void updateWeidou() {
	// int[] resIds = ThemeCartoonUtil.IMAGE_IDS;
	// int res = new Random().nextInt(3);
	// uiHomeThemeImage.setImageResource(resIds[res]);
	// }

	private void updateAQI() {
		SensorDetail current = HomeUtil.getSensorDetail(
				mMainActivity, mCurrentSensor);
		String sensorId = null;
		SensorAirDetail air = mCurrentSensor.getAir();
		SensorGasDetail gas = mCurrentSensor.getGas();
		boolean hasDataUpload = true;
		/* 判断空气质量的内容 */
		if (air != null) {
			sensorId = air.getSensorId();
			if (sensorId != null && !"".equals(sensorId)) {
				if (air.getCreateTime() == null
						|| "".equals(air.getCreateTime())
						|| air.getCreateTime()
								.startsWith("1900")) {
					hasDataUpload = false;
				}
				uiHomeAQILayout.setVisibility(View.VISIBLE);
				uiHomeGasLayout.setVisibility(View.GONE);
				uiHomeSolution.setVisibility(View.VISIBLE);
				uiHomePagerSumSensorLayout
						.setVisibility(View.GONE);
				uiHomePagerLoc.setVisibility(View.VISIBLE);
				cityLayout.setVisibility(View.VISIBLE);
				updateAQIView(current, hasDataUpload);
			}
		}
		if (gas != null) {
			sensorId = gas.getSensorId();
			if (sensorId != null && !"".equals(sensorId)) {
				if (gas.getCreateTime() == null
						|| "".equals(gas.getCreateTime())
						|| gas.getCreateTime()
								.startsWith("1900")) {
					hasDataUpload = false;
				}
				uiHomeAQILayout.setVisibility(View.GONE);
				uiHomeGasLayout.setVisibility(View.VISIBLE);
				uiHomeSolution.setVisibility(View.GONE);
				uiHomePagerSumSensorLayout
						.setVisibility(View.GONE);
				uiHomePagerLoc.setVisibility(View.GONE);
				cityLayout.setVisibility(View.GONE);
				updateGasView(current, hasDataUpload);
			}
		}

	}

	private void updateAQIView(SensorDetail current,
			boolean hasDataUpload) {
		String score = current == null ? "0" : HomeUtil.getAQI(
				current, mParentView) + "";
		uiHomeAQI.setText(score + " ");
		/* 更新主动上报时间 */
		uiHomeUpdateTime.setText(HomeUtil.getUpdateTime(
				mMainActivity, mCurrentSensor));
		int scoreInt = 0;
		scoreInt = "无".equals(score) ? 0 : Integer
				.valueOf(score);
		int color = 0;
		String text = "";
		if (!hasDataUpload) {
			text += "暂无数据";
			/* 曾凡 20150727 修改颜色为白色 */
			// color = Color.rgb(154, 189, 207);
		} else if (scoreInt <= ThemeCartoonUtil.PM25_VERY_GOOD) {
			text += "非常清新";
			// color = Color.rgb(72, 201, 100);
		} else if (scoreInt > ThemeCartoonUtil.PM25_VERY_GOOD
				&& scoreInt <= ThemeCartoonUtil.PM25_GOOD) {
			text += "空气适宜";
			// color = Color.rgb(72, 201, 100);
		} else if (scoreInt > ThemeCartoonUtil.PM25_GOOD
				&& scoreInt <= ThemeCartoonUtil.PM25_WEAK) {
			text += "轻度污染";
			// color = Color.rgb(207, 177, 67);
		} else if (scoreInt > ThemeCartoonUtil.PM25_WEAK
				&& scoreInt <= ThemeCartoonUtil.PM25_BAD) {
			text += "中度污染";
			// color = Color.rgb(223, 95, 50);
		} else if (scoreInt > ThemeCartoonUtil.PM25_BAD
				&& scoreInt <= ThemeCartoonUtil.PM25_VERY_BAD) {
			text += "重度污染";
			// color = Color.rgb(223, 95, 50);
		} else if (scoreInt > ThemeCartoonUtil.PM25_VERY_BAD) {
			text += "严重污染";
			// color = Color.rgb(223, 95, 50);
		}

		uiHomeScore.setText(text);
		// uiHomeScore.setTextColor(color);

	}

	private void updateGasView(SensorDetail current,
			boolean hasDataUpload) {
		/* 更新主动上报时间 */
		uiHomeUpdateTime.setText("   "
				+ HomeUtil.getUpdateTime(mMainActivity,
						mCurrentSensor));
		String ch4 = current == null ? "0" : HomeUtil
				.getCh4(current) + "";
		String text = "";
		int color = 0;
		Double tempValue = Double.valueOf(ch4);
		if (!hasDataUpload) {
			text += mMainActivity.getResources().getString(
					R.string.ui_pager_gas_no);
			color = Color.rgb(255, 255, 255);
		} else if (tempValue < ThemeCartoonUtil.CH4_THRESHOLD) {
			text = mMainActivity.getResources().getString(
					R.string.ui_pager_gas_normal);
			color = Color.rgb(255, 255, 255);
		} else if (tempValue >= ThemeCartoonUtil.CH4_THRESHOLD) {
			text = mMainActivity.getResources().getString(
					R.string.ui_pager_gas_leak);
			color = Color.rgb(223, 95, 50);
		}
		uiHomeCh4.setTextColor(color);
		uiHomeCh4.setText(text);
		String co = current == null ? "0" : HomeUtil
				.getCh4(current) + "";
		tempValue = Double.valueOf(co);
		if (!hasDataUpload) {
			text += mMainActivity.getResources().getString(
					R.string.ui_pager_gas_no);
			color = Color.rgb(255, 255, 255);
		} else if (tempValue < ThemeCartoonUtil.CO_THRESHOLD) {
			text = mMainActivity.getResources().getString(
					R.string.ui_pager_gas_normal);
			color = Color.rgb(255, 255, 255);
		} else if (tempValue >= ThemeCartoonUtil.CO_THRESHOLD) {
			text = mMainActivity.getResources().getString(
					R.string.ui_pager_gas_leak);
			color = Color.rgb(223, 95, 50);
		}
		uiHomeCO.setTextColor(color);
		uiHomeCO.setText(text);
	}

	/**
	 * TODO 把我补全
	 * 
	 * @author 曾凡
	 * @time 2015年7月6日 下午8:32:04
	 */
	@Override
	public void showCityList() {
		FragmentTransaction ft = getFragmentManager()
				.beginTransaction();
		CityFragment fragment = CityFragment.getInstance();
		fragment.setPager(this);
		fragment.setStyle(R.style.MyDialog, 0);
		fragment.show(ft, "df");
	}

	@Override
	public void onResume() {
		super.onResume();
		Ln.d("onResume");
		if (mCurrentSensor.getSensorId() != null
				&& !"".equals(mCurrentSensor.getSensorId())) {
			if (mAQIHandler != null) {
				mAQIHandler.removeCallbacks(updateAQIThread);
				mAQIHandler.post(updateAQIThread);
			}
		}
		if (mAnimationHandler != null) {
			mAnimationHandler.removeCallbacks(animationThread);
			mAnimationHandler.post(animationThread);
		}

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mAQIHandler != null) {
			mAQIHandler.removeCallbacks(updateAQIThread);
		}
		if (mAnimationHandler != null) {
			mAnimationHandler.removeCallbacks(animationThread);
		}
	}

}
