package com.hw.smarthome.ui.setting;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.hw.smarthome.R;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.discuss.FeedbackFragment;
import com.hw.smarthome.ui.setting.sub.AboutFragment;
import com.hw.smarthome.ui.setting.sub.AccountFragment;
import com.hw.smarthome.ui.setting.sub.HelpCenterFragment;
import com.hw.smarthome.ui.setting.sub.SettingQuesFragment;
import com.hw.smarthome.ui.util.MainAcUtil;

/**
 * 选项
 * 
 * @author 曾凡
 * @time 2014年6月9日 上午10:46:50
 */
public class SettingsFragment extends Fragment implements
		OnClickListener {
	private static SettingsFragment instance;

	private TableRow acountInfo;
	private FragmentManager fManager;
	private TableRow helpCenter;
	private TableRow feedback;
	private TableRow abouTableRow;
	private TableRow settingQues;

	public static SettingsFragment getInstance() {
		if (instance == null) {
			instance = new SettingsFragment();
		}
		return instance;
	}

	private View parentView;

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		parentView = inflater.inflate(R.layout.setting_more,
				container, false);
		return parentView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		fManager = getActivity().getSupportFragmentManager();
		acountInfo = (TableRow) parentView
				.findViewById(R.id.more_page_row1);
		acountInfo.setOnClickListener(this);

		settingQues = (TableRow) parentView
				.findViewById(R.id.settingQues);
		settingQues.setOnClickListener(this);

		helpCenter = (TableRow) parentView
				.findViewById(R.id.more_page_row4);
		helpCenter.setOnClickListener(this);

		feedback = (TableRow) parentView
				.findViewById(R.id.more_page_row5);
		feedback.setOnClickListener(this);

		abouTableRow = (TableRow) parentView
				.findViewById(R.id.more_page_row7);
		abouTableRow.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v == acountInfo) {
			MainAcUtil.changeFragmentWithBack(fManager,
					AccountFragment.getInstance());

		}
		if (v == helpCenter) {
			HelpCenterFragment helpFragment = HelpCenterFragment
					.getInstance();
			String url = ServerConstant.SERVER_BASE_URI
					+ ServerConstant.SH01_05_02_02;
			helpFragment.setUrl(url);
			MainAcUtil.changeFragmentWithBack(fManager,
					helpFragment);
		}
		if (v == feedback) {
			MainAcUtil.changeFragmentWithBack(fManager,
					FeedbackFragment.getInstance());
		}
		if (v == abouTableRow) {
			MainAcUtil.changeFragmentWithBack(fManager,
					AboutFragment.getInstance());
		}
		if (v == settingQues) {
			SettingQuesFragment helpFragment = SettingQuesFragment
					.getInstance();
			helpFragment.setUrl(ServerConstant.QUES);
			MainAcUtil.changeFragmentWithBack(fManager,
					helpFragment);
			MainAcUtil.changeFragmentWithBack(fManager,
					SettingQuesFragment.getInstance());
		}

	}

}
