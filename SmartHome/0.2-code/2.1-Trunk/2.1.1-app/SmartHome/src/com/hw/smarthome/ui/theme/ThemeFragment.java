package com.hw.smarthome.ui.theme;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hw.smarthome.R;
import com.hw.smarthome.ui.sensor.SensorOneKeyFragment;

/**
 * 主题
 * 
 * @author 曾凡
 * @time 2014年6月9日 上午10:46:50
 */
public class ThemeFragment extends Fragment {
	private static ThemeFragment instance;

	public static ThemeFragment getInstance() {
		if (instance == null) {
			instance = new ThemeFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
//		return inflater.inflate(R.layout.ui_home, container,
//				false);
		return inflater.inflate(R.layout.ui_home_control_page, container,
				false);
	}

}
