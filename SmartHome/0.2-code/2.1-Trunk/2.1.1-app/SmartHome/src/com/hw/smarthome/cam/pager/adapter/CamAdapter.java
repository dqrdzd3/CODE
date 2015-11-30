package com.hw.smarthome.cam.pager.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

/**
 * 主页面内容适配器<br>
 * (maximum line width 65)
 * 
 * @author 曾凡
 * @date 2014年6月12日下午7:25:07
 */
public class CamAdapter extends FragmentStatePagerAdapter {

	private List<Fragment> mViewList;
	private FragmentActivity mParentActivity;

	public CamAdapter(FragmentManager childFragmentManager,
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
		Fragment temp = mViewList.get(position);
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
		Fragment temp = mViewList.get(position);
		if (position == 0) {
			if (f != temp) {
				FragmentTransaction trans = mParentActivity
						.getSupportFragmentManager()
						.beginTransaction();
				trans.replace(((Fragment) f).getId(), temp);
				trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
				trans.addToBackStack(null);
				trans.commit();
				f = mViewList.get(0);
			}
		}
		return f;
	}
}