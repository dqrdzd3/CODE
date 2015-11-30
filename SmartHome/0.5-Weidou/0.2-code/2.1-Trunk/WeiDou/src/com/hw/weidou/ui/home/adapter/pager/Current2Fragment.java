package com.hw.weidou.ui.home.adapter.pager;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.util.WebViewUtil;
import com.hw.weidou.R;
import com.hw.weidou.constant.EquipConstant;
import com.hw.weidou.parser.ParserDeamon;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.HomeFragment;
import com.hw.weidou.ui.home.HomeUtil;
import com.hw.weidou.ui.home.adapter.pager.alcohol.AlcoholUIFragment;
import com.hw.weidou.ui.home.adapter.pager.base.CurrentFragmentBase;
import com.hw.weidou.ui.home.adapter.util.CurrentUtil;

/**
 * @author 曾凡
 * @time 2014年10月17日 上午10:15:04
 */
public class Current2Fragment extends CurrentFragmentBase {
	private static Current2Fragment instance = null;

	public static Current2Fragment getInstance() {
		if (instance == null) {
			instance = new Current2Fragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void initParentView(LayoutInflater inflater,
			ViewGroup container) {
		View parentView = inflater
				.inflate(R.layout.ui_home_page_current2,
						container, false);
		mParentView = parentView;
	}

	@Override
	protected void initView() {

		currentUsage = (TextView) mParentView
				.findViewById(R.id.currentUsage);

		currentPowerLevel = (ImageView) mParentView
				.findViewById(R.id.currentPowerLevel);

		currentValue = (TextView) mParentView
				.findViewById(R.id.currentValue);
		currentDataHigh = (TextView) mParentView
				.findViewById(R.id.currentDataHigh);
		currentDataAvg = (TextView) mParentView
				.findViewById(R.id.currentAvg);

		currentData = (WebView) mParentView
				.findViewById(R.id.currentData);
		currentMeans = (TextView) mParentView
				.findViewById(R.id.currentMeans);

		WebViewUtil webUtil = new WebViewUtil();
		webUtil.initWebview(currentData, UIConstant.HOME_ADDR);
		// createChart();
	}

	// /**
	// * 创建图表
	// *
	// * @author 曾凡
	// * @time 2014年11月5日 下午6:02:30
	// */
	// private void createChart() {
	// currentData.loadUrl("javascript:create('" + updateData()
	// + "')");
	// }

	/**
	 * 更新图表
	 * 
	 * @author 曾凡
	 * @time 2014年11月5日 下午6:02:30
	 */
	@Override
	protected void updateChart() {
		currentData.loadUrl("javascript:update('" + updateData()
				+ "')");
	}

	/**
	 * 初始化信息
	 * 
	 * @author 曾凡
	 * @time 2014年11月5日 下午5:26:35
	 */
	private String updateData() {
		WeidouPo weidou = ParserDeamon.getCurrentWeidou();
		switch (weidou.getEquip()) {
		case EquipConstant.EQUIP_CH2O:// 甲醛
			weidou.setUnit(UIConstant.HOME_UNIT_CH2O);
			break;
		case EquipConstant.EQUIP_CO:// 一氧化碳
			weidou.setUnit(UIConstant.HOME_UNIT_CH2O);
			break;
		case EquipConstant.EQUIP_ALCOHOL:// 酒精
			weidou.setUnit(UIConstant.HOME_UNIT_ALCOHOL);
			break;
		}

		String param = CurrentUtil.updateHtmlDatas(
				getActivity(), currentData, weidou, maxValue);
		return param;
	}

	/**
	 * 更新传感器类型
	 * 
	 * @author 曾凡
	 * @param weidou
	 * @time 2014年11月5日 下午7:11:33
	 */
	@Override
	protected void updateFragment(WeidouPo weidou) {
		List<Fragment> viewList = HomeFragment.getInstance()
				.getHomeViewList();
		if (weidou.getEquip() != 0) {
			if (HomeUtil.isAlcohol(weidou)) {
				if (!(viewList.get(0) instanceof AlcoholUIFragment)) {
					Log.i("replaceFragment", "AlcoholUIFragment"
							+ weidou.toString());
					viewList.set(0,
							AlcoholUIFragment.getInstance());
					HomeFragment.getInstance()
							.getUpViewAdapter()
							.notifyDataSetChanged();
				}
			} else {
				if (!(viewList.get(0) instanceof Current2Fragment)) {

					Log.i("replaceFragment", "Current2Fragment"
							+ weidou.toString());
					viewList.set(0,
							Current2Fragment.getInstance());
					HomeFragment.getInstance()
							.getUpViewAdapter()
							.notifyDataSetChanged();
					// HomeFragment.getInstance().initPager();
				}
			}

		}
	}

}
