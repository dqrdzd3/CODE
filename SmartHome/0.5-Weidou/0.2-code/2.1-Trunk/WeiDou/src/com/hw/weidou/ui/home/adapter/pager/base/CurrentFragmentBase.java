package com.hw.weidou.ui.home.adapter.pager.base;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.util.Ln;
import com.hw.weidou.R;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.HomeFragment;
import com.hw.weidou.ui.home.HomeUtil;
import com.hw.weidou.ui.home.adapter.util.CurrentUtil;
import com.hw.weidou.ui.util.UnitUtil;

/**
 * @author 曾凡
 * @time 2014年10月17日 上午10:15:04
 */
public abstract class CurrentFragmentBase extends Fragment {

	protected View mParentView;
	/** 累计使用时间 */
	protected TextView currentUsage;
	/** 内部电池用量 */
	protected ImageView currentPowerLevel;
	/** 当前值 */
	protected TextView currentValue;
	/** 当前最高值 */
	protected TextView currentDataHigh;
	/** 当前平均值 */
	protected TextView currentDataAvg;
	/** 当前值WebView */
	protected WebView currentData;
	/** 当前传感器状态 */
	protected TextView currentMeans;
	/** 最大量程 */
	protected float maxValue; // progress bar max value

	protected Activity parentContext;
	protected AudioManager am;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		parentContext = getActivity();
		am = (AudioManager) parentContext
				.getSystemService(Context.AUDIO_SERVICE);
		// setSpeakerphoneOn() only work when audio mode set to MODE_IN_CALL
		/* XXX 有些手机不支持 */
//		am.setMode(AudioManager.MODE_IN_COMMUNICATION);
//		am.setSpeakerphoneOn(true);
//		
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		initParentView(inflater, container);
		initView();

		return mParentView;
	}

	protected abstract void initParentView(
			LayoutInflater inflater, ViewGroup container);

	protected abstract void initView();

	/**
	 * 更新图表
	 * 
	 * @author 曾凡
	 * @time 2014年11月5日 下午6:02:30
	 */
	protected abstract void updateChart();

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

	/** 用于执行页面数据更新 */
	private Handler mUpdateDataHandler = new Handler();
	private Runnable mUpdateDataThread = new Runnable() {
		@Override
		public void run() {
			WeidouPo weidou = ParserDeamon.getCurrentWeidou();
			switch (weidou.getEquip()) {
			case EquipConstant.EQUIP_CH2O:// 甲醛
				weidou.setUnit(UIConstant.HOME_UNIT_CH2O);
				maxValue = UIConstant.CH2O_HIGH;
				break;
			case EquipConstant.EQUIP_CO:// 一氧化碳
				weidou.setUnit(UIConstant.HOME_UNIT_CO);
				maxValue = UIConstant.CO_HIGH;
				break;
			case EquipConstant.EQUIP_ALCOHOL:// 酒精
				weidou.setUnit(UIConstant.HOME_UNIT_ALCOHOL);
				maxValue = UIConstant.ALCOHOL_MG100ML_ZH_HIGH;
				break;
			}
			setSensorValue(weidou);
			// Ln.i(weidou);
			updateChart();
			setPower(weidou);
			/* 是否可以更新实时数据fragment */
			// boolean canUpdateFragment = HomeFragment.getEquip() != 0
			// && (weidou.getEquip() != HomeFragment
			// .getEquip());
			// if (weidou.getEquip() != 0) {
			// HomeFragment.setEquip(weidou.getEquip());
			// }
			// if (canUpdateFragment) {
			updateFragment(weidou);
			// }
			currentUsage.setText(weidou.getUsage() / 60 + "小时"
					+ weidou.getUsage() % 60 + "分钟");
			mUpdateDataHandler.postDelayed(mUpdateDataThread,
					100);
		}
	};

	/**
	 * 根据传感器类型切换页面
	 * 
	 * @author 曾凡
	 * @param weidou
	 * @time 2014年11月5日 下午7:11:33
	 */
	protected abstract void updateFragment(WeidouPo weidou);

	private void setPower(WeidouPo weidou) {
		currentPowerLevel.setImageResource(CurrentUtil
				.getPower(weidou));
	}

	private byte lastEquipType = 0;
	private float lastSensorValue = 0;

	/**
	 * 设置传感器值
	 */
	private void setSensorValue(WeidouPo weidou) {
		/* FIXME 测试 */
		// weidou.setDataContent("1238");
		// weidou.setEquipName("一氧化碳");
		// weidou.setEquip((byte) 0x05);
		// weidou.setPower(3);
		// weidou.setVersion("231");
		// weidou.setUsage(123456);
		int color = R.color.home_normal;
		String unit = "";
		String status = "";
		String name = ""; // 解决方案要用
		String means = "";
		byte equip = weidou.getEquip();
		/* 已经预热并启动 */
		if (weidou.isStart()) {
			currentValue.setText(UnitUtil.getValue(equip,
					weidou.getDataContent()));
			String higherData = HomeUtil.getBiggerValue(
					weidou.getDataContent(),
					(String) currentDataHigh.getText());
			float high = Float.valueOf(higherData == null
					|| "".equals(higherData) ? UnitUtil
					.getDefaultValue(equip) : higherData);

			currentDataHigh.setText(UnitUtil.getValue(equip,
					high + ""));
			weidou.setMaxValue(higherData);

			float tempData = Float.valueOf(weidou
					.getDataContent());
			/* 平均值 采用Markov(马尔科夫)过程 ：(上一个值+本次值)/2 */
			if (tempData != lastSensorValue) {
				String currentAvg = HomeUtil.getAvgValue(
						tempData, lastSensorValue);
				weidou.setAvgValue(UnitUtil.getValue(equip,
						currentAvg));
				currentDataAvg.setText(weidou.getAvgValue());

			}
			if (equip != lastEquipType) {
				currentValue.setText(UnitUtil
						.getDefaultValue(equip));
				currentDataHigh.setText(UnitUtil
						.getDefaultValue(equip));
				currentDataAvg.setText(UnitUtil
						.getDefaultValue(equip));
			} else if ("".equals(weidou.getAvgValue())
					|| weidou.getAvgValue() == null) {
				currentDataAvg.setText(UnitUtil
						.getDefaultValue(equip));
			}
			/* 比较当前设备和上一个设备并初始化最高值 */
			lastEquipType = equip;
			/* 赋值，作为下一次调用的上一个值 */
			lastSensorValue = tempData;

			switch (equip) {
			case EquipConstant.EQUIP_CH2O:// 甲醛
				unit = UIConstant.HOME_UNIT_CH2O;
				name = UIConstant.HOME_CH2O_TYPE;
				if (tempData < UIConstant.CH2O_NORMAL_VALUE) {
					status = UIConstant.CH2O_CONTENT_STATUS_N;
					means = UIConstant.CH2O_NORMAL_CONTENT;
					color = R.color.home_normal;
				} else if (tempData >= UIConstant.CH2O_NORMAL_VALUE
						&& tempData <= UIConstant.CH2O_MIDDLE_VALUE) {
					status = UIConstant.CH2O_CONTENT_STATUS_D;
					means = UIConstant.CH2O_MIDDLE_CONTENT;
					color = R.color.home_middle;
				} else if (tempData > UIConstant.CH2O_MIDDLE_VALUE
						&& tempData <= UIConstant.CH2O_HIGH_VALUE) {
					status = UIConstant.CH2O_CONTENT_STATUS_D;
					means = UIConstant.CH2O_MIDDLE_UP_CONTENT;
					color = R.color.home_middle;
				} else if (tempData > UIConstant.CH2O_HIGH_VALUE
						&& tempData <= UIConstant.CH2O_DEAD_VALUE) {
					status = UIConstant.CH2O_CONTENT_STATUS_DD;
					means = UIConstant.CH2O_HIGH_CONTENT;
					color = R.color.home_high;
				} else if (tempData >= UIConstant.CH2O_DEAD_VALUE) {
					status = UIConstant.CH2O_CONTENT_STATUS_DD;
					means = UIConstant.CH2O_HIGH_UP_CONTENT;
					color = R.color.home_high;
				}
				break;
			case EquipConstant.EQUIP_ALCOHOL:// 酒精
				unit = UIConstant.HOME_UNIT_ALCOHOL;
				if (tempData <= UIConstant.ALCOHOL_LOW) {
					status = UIConstant.ALCOHOL_CONTENT_STATUS_N;
					color = R.color.home_normal;
					means = UIConstant.ALCOHOL_CONTENT;
				} else if (tempData > UIConstant.ALCOHOL_LOW
						&& tempData < UIConstant.ALCOHOL_MIDDLE) {
					status = UIConstant.ALCOHOL_CONTENT_STATUS_D;
					color = R.color.home_middle;
					means = UIConstant.ALCOHOL_CONTENT_LOW;
				} else if (tempData >= UIConstant.ALCOHOL_MIDDLE) {
					status = UIConstant.ALCOHOL_CONTENT_STATUS_DD;
					color = R.color.home_high;
					means = UIConstant.ALCOHOL_CONTENT_MIDDLE;
				}
				break;
			case EquipConstant.EQUIP_CO:// 一氧化碳
				unit = UIConstant.HOME_UNIT_CO;
				name = UIConstant.HOME_CO_TYPE;
				if (tempData <= UIConstant.CO_1) {
					status = UIConstant.C0_CONTENT_STATUS_N;
					means = UIConstant.CO_CONTENT;
					color = R.color.home_normal;
				} else if (tempData > UIConstant.CO_1
						&& tempData <= UIConstant.CO_2) {
					status = UIConstant.C0_CONTENT_STATUS_D;
					means = UIConstant.CO_1_CONTENT;
					color = R.color.home_middle;
				} else if (tempData > UIConstant.CO_2
						&& tempData <= UIConstant.CO_3) {
					status = UIConstant.C0_CONTENT_STATUS_D;
					means = UIConstant.CO_2_CONTENT;
					color = R.color.home_middle;
				} else if (tempData > UIConstant.CO_3
						&& tempData <= UIConstant.CO_4) {
					status = UIConstant.C0_CONTENT_STATUS_D;
					color = R.color.home_middle;
					means = UIConstant.CO_3_CONTENT;
				} else if (tempData > UIConstant.CO_4
						&& tempData <= UIConstant.CO_5) {
					status = UIConstant.C0_CONTENT_STATUS_DD;
					color = R.color.home_high;
					means = UIConstant.CO_4_CONTENT;
				} else if (tempData > UIConstant.CO_5
						&& tempData <= UIConstant.CO_6) {
					status = UIConstant.C0_CONTENT_STATUS_DD;
					color = R.color.home_high;
					means = UIConstant.CO_5_CONTENT;
				} else if (tempData >= UIConstant.CO_6) {
					status = UIConstant.C0_CONTENT_STATUS_DD;
					means = UIConstant.CO_6_CONTENT;
					color = R.color.home_high;
				}
				break;
			default:
				if (HomeFragment.isWeidouOn) {
					means = UIConstant.HINT_CONTENT;
				}
				break;
			}
		} else {
			status = weidou.getStatus();
			weidou.setDataContent(UnitUtil.getValue(
					weidou.getEquip(), null));
			if (HomeFragment.isWeidouOn && equip == 0) {
				means = UIConstant.HINT_MIC_CONTENT;
				status = UIConstant.HINT_MIC_STATUS;
				currentValue.setText("");
				currentDataHigh.setText("");
				currentDataAvg.setText("");
			}
		}

		weidou.setUnit(unit);
		weidou.setStatus(status);
		weidou.setMeans(means);
		currentMeans.setText(weidou.getMeans());

		HomeFragment.getInstance().gasList.clear();
		HomeFragment.getInstance().gasList.add(name);
		int state = 0;
		switch (color) {
		case R.color.home_normal:
			state = 0;
			break;
		case R.color.home_middle:
			state = 1;
			break;
		case R.color.home_high:
			state = 2;
			break;
		}
		HomeFragment.getInstance().stateList.clear();
		HomeFragment.getInstance().stateList.add(state);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (mUpdateDataHandler != null) {
			mUpdateDataHandler
					.removeCallbacks(mUpdateDataThread);
		}
	}
}
