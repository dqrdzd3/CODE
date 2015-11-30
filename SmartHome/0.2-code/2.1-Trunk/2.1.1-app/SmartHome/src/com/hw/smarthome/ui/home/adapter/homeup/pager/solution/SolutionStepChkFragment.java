package com.hw.smarthome.ui.home.adapter.homeup.pager.solution;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SoluChkSumPO;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.base.SolutionStepBaseFragment;
import com.hw.smarthome.ui.solution.constant.SolutionConstants;
import com.hw.smarthome.ui.util.MainAcUtil;

public class SolutionStepChkFragment extends
		SolutionStepBaseFragment {

	private static SolutionStepChkFragment instance;

	public static SolutionStepChkFragment getInstance() {
		instance = new SolutionStepChkFragment();
		return instance;
	}

	@Override
	protected void loadFragment(ViewGroup container,
			LayoutInflater inflater) {
		parentView = inflater.inflate(
				R.layout.ui_solution_step_chk_fragment,
				container, false);
	}

	protected String loadPageType() {
		return SolutionConstants.SCENE_FILE_TYPE_CHK + "";
	}

	@Override
	protected void upBtnClick() {
		SolutionStepSoluFragment fragment = SolutionStepSoluFragment
				.getInstance();
		fragment.setSensorId(mCurrentSensor.getSensorId());
		FragmentManager fManager = mMainActivity
				.getSupportFragmentManager();
		MainAcUtil.changeFragmentWithBack(fManager, fragment);
	}

	@Override
	protected void downBtnClick() {
		SolutionStepSumFragment fragment = SolutionStepSumFragment
				.getInstance();
		fragment.setSensorId(mCurrentSensor.getSensorId());
		FragmentManager fManager = mMainActivity
				.getSupportFragmentManager();
		MainAcUtil.changeFragmentWithBack(fManager, fragment);
	}

	@Override
	protected int getTitleId() {
		return R.string.ui_solution_chk;
	}

	@Override
	protected void initSolutionViews() {
		soluAllContent.setVisibility(View.VISIBLE);
		soluAllContent2.setVisibility(View.GONE);
	}
}
