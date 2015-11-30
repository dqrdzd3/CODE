package com.hw.smarthome.ui.home.adapter.homeup;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.hw.smarthome.ui.MainActivity;

/**
 * @author 曾凡
 * @time 2014年10月17日 下午5:53:47
 */
public class HomeFragmentUpAdapter extends FragmentPagerAdapter {

	private List<Fragment> mViewList;

	public HomeFragmentUpAdapter(
			FragmentManager childFragmentManager,
			MainActivity mMainActivity, List<Fragment> viewList) {
		super(childFragmentManager);
		mViewList = viewList;
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
	
//	@Override
//	public int getItemPosition(Object object) {
//		return PagerAdapter.POSITION_NONE;
//	}
//
//	@Override
//	public Object instantiateItem(ViewGroup container,
//			int position) {
//		HomeFragmentUpPager temp = (HomeFragmentUpPager) super
//				.instantiateItem(container, position);
//		return temp;
//	}

	public List<Fragment> getViewList() {
		return mViewList;
	}

	public void setViewList(List<Fragment> viewList) {
		this.mViewList = viewList;
	}

}