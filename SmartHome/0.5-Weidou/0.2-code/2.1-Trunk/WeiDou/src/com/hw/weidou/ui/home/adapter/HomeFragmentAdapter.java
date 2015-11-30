package com.hw.weidou.ui.home.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.hw.weidou.R;

/**
 * 主页面内容适配器<br>
 * (maximum line width 65)
 * 
 * @author 曾凡
 * @date 2014年6月12日下午7:25:07
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

	private List<Fragment> mViewList;
	private FragmentActivity mParentActivity;

	public HomeFragmentAdapter(
			FragmentManager childFragmentManager,
			FragmentActivity parentActivity,
			List<Fragment> viewList) {
		super(childFragmentManager);
		mViewList = viewList;
		mParentActivity = parentActivity;
	}

	@Override
	public int getCount() {
		return mViewList.size();
	}

	@Override
	public Fragment getItem(int position) {
		Fragment temp = null;
		temp = mViewList.get(position);
		return temp;
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public Object instantiateItem(ViewGroup container,
			int position) {
		Object f = super.instantiateItem(container, position);
		if (position == 0) {
			if (f != mViewList.get(0)) {
				FragmentTransaction trans = mParentActivity
						.getSupportFragmentManager()
						.beginTransaction();
				trans.replace(((Fragment) f).getId(),
						mViewList.get(0));
				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				trans.addToBackStack(null);
				trans.commit();
				f = mViewList.get(0);
			}
		}
		return f;
	}
}