package com.hw.smarthome.ui.home.adapter.homeup.pager.solution;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.home.adapter.homeup.pager.solution.base.SolutionStepBaseFragment;
import com.hw.smarthome.ui.home.adapter.homeup.util.HomePagerUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.smarthome.ui.solution.constant.SolutionConstants;

public class SolutionStepSoluFragment extends
		SolutionStepBaseFragment {

	private static SolutionStepSoluFragment instance;

	public static SolutionStepSoluFragment getInstance() {
		instance = new SolutionStepSoluFragment();
		return instance;
	}

	@Override
	protected void loadFragment(ViewGroup container,
			LayoutInflater inflater) {
		parentView = inflater.inflate(
				R.layout.ui_solution_step_solu_fragment,
				container, false);
	}

	protected String loadPageType() {
		return SolutionConstants.SCENE_FILE_TYPE_SOLU + "";
	}

	@Override
	protected void upBtnClick() {
	}

	@Override
	protected void downBtnClick() {
		Uri uri = Uri.parse(ServerConstant.SHOP_URL);
		Intent it = new Intent(Intent.ACTION_VIEW, uri);
		mMainActivity.startActivity(it);
	}

	@Override
	protected int getTitleId() {
		return setSolutionBtnText(mMainActivity, mCurrentSensor);
	}

	private int setSolutionBtnText(Context context,
			SensorDetail detail) {

		int type = 0;
		int state = 0;
		int stringId = R.string.ui_solution_solu1;
		if (detail != null) {
			type = SmartHomeServiceUtil.getSensorType(detail);
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				state = SolutionUtils.getSolutionHintState(
						context, detail);
				switch (state) {
				case HomePagerUtil.LOW:
					stringId = R.string.ui_solution_solu2;
					break;
				case HomePagerUtil.NORMAL:
					stringId = R.string.ui_solution_solu;
					break;
				case HomePagerUtil.HIGH:
					stringId = R.string.ui_solution_solu;
					break;
				}
			}
		}
		return stringId;

	}

	@Override
	protected void initSolutionViews() {
		soluAllContent.setVisibility(View.GONE);
		soluAllContent2.setVisibility(View.VISIBLE);
	}
}
