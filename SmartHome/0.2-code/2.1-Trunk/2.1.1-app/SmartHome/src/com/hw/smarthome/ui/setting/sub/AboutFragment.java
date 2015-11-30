package com.hw.smarthome.ui.setting.sub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.update.UpdateManager;
import com.hw.util.SoftUtil;

/**
 * @author 曾凡
 * @time 2014年6月9日 下午2:45:04
 */
public class AboutFragment extends Fragment {
	private static AboutFragment instance;
	private View layout;
	public static AboutFragment getInstance() {
		if (instance == null) {
			instance = new AboutFragment();
		}
		return instance;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		layout = inflater.inflate(R.layout.ui_settings_about,
				container, false);
		initInfo();
		return layout;
	}
	
	private void initInfo(){
		TextView versionView = (TextView) layout.findViewById(R.id.versionText);
	    versionView.setText("版本   "+SoftUtil.getVersionName(getActivity()));
	    Button checkView = (Button)layout.findViewById(R.id.checkText);
	    checkView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				UpdateManager manager = new UpdateManager(getActivity());
				manager.checkUpdate(true);
//				MyDialog myDialog = new MyDialog(getActivity(),R.style.MyDialog);
//				myDialog.show();
			}
		});
	}
	
}
