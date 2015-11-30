package com.hw.smarthome.ui.setting.sub;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.hw.smarthome.R;

/**
 * @author 曾凡
 * @time 2014年6月9日 下午2:44:31
 */
public class DevListFragment extends Fragment {
	private static DevListFragment instance;
	private View mView;

	public static DevListFragment getInstance() {
		if (instance == null) {
			instance = new DevListFragment();
		}
		return instance;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.ui_settings_devlist,
				container, false);
		initDeveloper();
		return mView;
	}

	/**
	 * 
	 * @author lijing
	 * @time 2014-7-22 下午1:21:47
	 */
	private void initDeveloper() {
		Context mContext = getActivity();
		int[] developerImages = { R.drawable.dev_zengfan,
				R.drawable.dev_yanwei, R.drawable.dev_lijing,
				R.drawable.dev_changshuai,
				R.drawable.dev_liliupin,
				R.drawable.dev_zhaoshuai };
		String[] developerNames = mContext.getResources()
				.getStringArray(R.array.developer_name);
		String[] developerEmails = mContext.getResources()
				.getStringArray(R.array.developer_email);
		int count = developerNames.length;
		TableLayout layout = (TableLayout) mView
				.findViewById(R.id.layout_developer);
		TextView nameView = null;
		ImageView imageView = null;
		TextView emailView = null;
		for (int i = 0; i < count; i++) {
			View itemView = LayoutInflater.from(mContext)
					.inflate(R.layout.ui_settings_devlist_item,
							null);
			imageView = (ImageView) itemView
					.findViewById(R.id.dev_image);
			imageView.setImageResource(developerImages[i]);
			nameView = (TextView) itemView
					.findViewById(R.id.name);
			nameView.setText(developerNames[i]);
			emailView = (TextView) itemView
					.findViewById(R.id.email);
			emailView.setText(developerEmails[i]);
			layout.addView(itemView);
		}
	}
}
