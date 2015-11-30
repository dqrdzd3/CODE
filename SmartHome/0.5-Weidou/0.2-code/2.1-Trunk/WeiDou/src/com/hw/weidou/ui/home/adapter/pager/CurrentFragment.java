package com.hw.weidou.ui.home.adapter.pager;

import java.util.List;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.hw.util.WebViewUtil;
import com.hw.weidou.R;
import com.hw.weidou.po.WeidouPo;
import com.hw.weidou.ui.constant.UIConstant;
import com.hw.weidou.ui.home.HomeFragment;
import com.hw.weidou.ui.home.HomeUtil;
import com.hw.weidou.ui.home.adapter.pager.alcohol.AlcoholUIFragment;
import com.hw.weidou.ui.home.adapter.pager.base.CurrentFragmentBase;

/**
 * @author 曾凡
 * @time 2014年10月17日 上午10:15:04
 */
public class CurrentFragment extends CurrentFragmentBase {
	private static CurrentFragment instance = null;

	public static CurrentFragment getInstance() {
		if (instance == null) {
			instance = new CurrentFragment();
		}
		return instance;
	}

	@Override
	protected void initParentView(LayoutInflater inflater,
			ViewGroup container) {
		View parentView = inflater.inflate(
				R.layout.ui_home_page_current, container, false);
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
	}

	@Override
	protected void updateChart() {

	}

	@Override
	protected void updateFragment(WeidouPo weidou) {
		List<Fragment> viewList = HomeFragment.getInstance().getHomeViewList();
		if (weidou.getEquip() != 0) {
			if (HomeUtil.isAlcohol(weidou)) {
				if (!(viewList.get(0) instanceof AlcoholUIFragment)) {
					viewList.set(0,
							AlcoholUIFragment.getInstance());
				}
				viewList.set(0, CurrentFragment.getInstance());
			} else {
				if (!(viewList.get(0) instanceof CurrentFragment)) {
					viewList.set(0,
							CurrentFragment.getInstance());
				}
			}
			HomeFragment.getInstance().getUpViewAdapter()
					.notifyDataSetChanged();
		}
	}

}
