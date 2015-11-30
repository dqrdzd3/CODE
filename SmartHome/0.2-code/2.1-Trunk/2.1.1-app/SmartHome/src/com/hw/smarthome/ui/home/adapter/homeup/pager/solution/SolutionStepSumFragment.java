package com.hw.smarthome.ui.home.adapter.homeup.pager.solution;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hw.smarthome.R;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.base.SolutionStepBaseFragment;
import com.hw.smarthome.ui.solution.constant.SolutionConstants;

public class SolutionStepSumFragment extends
		SolutionStepBaseFragment {

	private static SolutionStepSumFragment instance;

	public static SolutionStepSumFragment getInstance() {
		instance = new SolutionStepSumFragment();
		return instance;
	}

	@Override
	protected void loadFragment(ViewGroup container,
			LayoutInflater inflater) {
		parentView = inflater.inflate(
				R.layout.ui_solution_step_sum_fragment,
				container, false);
	}

	protected String loadPageType() {
		return SolutionConstants.SCENE_FILE_TYPE_SUM + "";
	}

	@Override
	protected void upBtnClick() {
	}

	@Override
	protected void downBtnClick() {
	}

	@Override
	protected int getTitleId() {
		return R.string.ui_solution_sum;
	}

	@Override
	protected void initSolutionViews() {
		soluAllContent.setVisibility(View.GONE);
		soluAllContent2.setVisibility(View.VISIBLE);
	}
}
