package com.hw.smarthome.ui.setting.sub;

import me.xiaopan.android.switchbutton.SwitchButton;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.hw.smarthome.R;
import com.hw.util.PreferenceUtil;

/**
 * @author 曾凡
 * @time 2014年6月9日 下午2:44:19
 */
public class PushFragment extends Fragment {
	private static PushFragment instance;
	View mLayout;
	public static PushFragment getInstance() {
		if (instance == null) {
			instance = new PushFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		mLayout = inflater.inflate(R.layout.ui_settings_push,
				container, false);
		initInfo();
		return mLayout;
	}
	
	private void initInfo(){
		SwitchButton alarmBtn = (SwitchButton) mLayout.findViewById(R.id.push_alarm);
		alarmBtn.setChecked(PreferenceUtil.getIsPushAlarm(getActivity()));
		alarmBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				PreferenceUtil.saveIsPushAlarm(getActivity(), isChecked);
			}
		});
		SwitchButton warnBtn = (SwitchButton) mLayout.findViewById(R.id.push_warn);
		warnBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				PreferenceUtil.saveIsPushWarn(getActivity(), isChecked);
			}
		});
		warnBtn.setChecked(PreferenceUtil.getIsPushWarn(getActivity()));
		SwitchButton ordinaryBtn = (SwitchButton) mLayout.findViewById(R.id.push_ordinary);
		ordinaryBtn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				PreferenceUtil.saveIsPushOrdinary(getActivity(), isChecked);
			}
		});
		ordinaryBtn.setChecked(PreferenceUtil.getIsPushOrdinary(getActivity()));
		
	}
}
