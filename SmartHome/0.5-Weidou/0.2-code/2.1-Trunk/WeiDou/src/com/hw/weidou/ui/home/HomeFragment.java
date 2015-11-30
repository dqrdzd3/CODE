package com.hw.weidou.ui.home;

import java.lang.reflect.Field;

import android.os.Handler;
import android.support.v4.app.Fragment;

import com.hw.util.Ln;
import com.hw.weidou.R;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.base.HomeFragmentBase;

/**
 * 主页面
 * 
 * @author 曾凡
 * @time 2014年6月9日 上午10:48:12
 */
public class HomeFragment extends HomeFragmentBase {
	private static HomeFragment instance;
	public static byte equip;

	public static HomeFragment getInstance() {
		if (instance == null) {
			instance = new HomeFragment();
		}
		return instance;
	}

	@Override
	protected void initThread() {
		mUpdateDataHandler.post(mUpdateDataThread);
	}

	/** 用于执行页面数据更新 */
	private Handler mUpdateDataHandler = new Handler();
	private Runnable mUpdateDataThread = new Runnable() {
		@Override
		public void run() {
			WeidouPo weidou = ParserDeamon.getCurrentWeidou();
			homeEquipName.setText(weidou.getEquipName());
			if (weidou.getVersion() != null
					&& !"".equals(weidou.getVersion())) {
				homeVersion.setText("[" + weidou.getVersion()
						+ "]");
			}
			chkSensorLink(weidou);
			mUpdateDataHandler.postDelayed(mUpdateDataThread,
					100);
		}
	};

	/**
	 * 检查时候连接设备
	 * 
	 * @author 曾凡
	 * @param weidou
	 * @time 2014年10月15日 下午7:20:17
	 */
	private void chkSensorLink(WeidouPo weidou) {

		if (isWeidouOn) {
			homeIsLinked.setText(UIConstant.SENSOR_LINK_TRUE);
			homeIsLinked.setTextColor(getResources().getColor(
					R.color.black));

		} else {
			homeIsLinked.setText(UIConstant.SENSOR_LINK_FALSE);
			homeIsLinked.setTextColor(getResources().getColor(
					R.color.red));
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		Ln.d("onResume");
		if (mUpdateDataHandler != null) {
			mUpdateDataHandler
					.removeCallbacks(mUpdateDataThread);
			mUpdateDataHandler.post(mUpdateDataThread);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mUpdateDataHandler != null) {
			mUpdateDataHandler
					.removeCallbacks(mUpdateDataThread);
		}
	}

	/**
	 * fragment套fragment
	 * 
	 * @author 曾凡
	 * @time 2014年10月22日 下午6:19:35
	 */
	@Override
	public void onDetach() {
		super.onDetach();
		try {
			// 参数是固定写法
			Field childFragmentManager = Fragment.class
					.getDeclaredField("mChildFragmentManager");
			childFragmentManager.setAccessible(true);
			childFragmentManager.set(this, null);
		} catch (NoSuchFieldException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte getEquip() {
		return equip;
	}

	public static void setEquip(byte equip) {
		HomeFragment.equip = equip;
	}
}
