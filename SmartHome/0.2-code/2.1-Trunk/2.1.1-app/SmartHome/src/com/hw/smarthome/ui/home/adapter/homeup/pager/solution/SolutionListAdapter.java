package com.hw.smarthome.ui.home.adapter.homeup.pager.solution;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.po.S007PO;
import com.hw.util.Ln;

/**
 * Demonstration of using ListFragment to show a list of items from a canned
 * array.
 */
public class SolutionListAdapter extends BaseAdapter {

	private TextView solutionName;
	private TextView solutionStatus;
	private TextView solutionContent;
	private View shopping;

	/** 实时数据列表 */
	private List<S007PO> mSolutions;
	private LayoutInflater mInflater;
	private Context mContext;

	public SolutionListAdapter(Context context,
			List<S007PO> solutions) {
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mSolutions = solutions;
	}

	@Override
	public int getCount() {
		if (mSolutions != null) {
			return mSolutions.size();
		} else {
			return 0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView,
			ViewGroup parent) {
		S007PO po = null;
		try {
			po = mSolutions.get(position);
			if (po != null) {
				convertView = mInflater.inflate(
						R.layout.ui_home_solution_list_item,
						null);
				convertView
						.setBackgroundColor(Color.TRANSPARENT);
				solutionName = (TextView) convertView
						.findViewById(R.id.uiHomeSolutionName);
				solutionName.setText(SolutionUtils
						.getSensorNameByType(po.getMa002()));
				solutionStatus = (TextView) convertView
						.findViewById(R.id.uiHomeSolutionStatus);
				solutionStatus.setText(SolutionUtils
						.getSensorStateName(po.getMa002(),
								po.getMa003()));
				solutionContent = (TextView) convertView
						.findViewById(R.id.uiHomeSolutionContent);
				solutionContent.setText(po.getMa005());
				shopping = convertView
						.findViewById(R.id.uiHomeShopping);
				shopping.setOnClickListener(new ShopButtonListener(
						po.getMa008()));

			}
		} catch (Exception e) {
			Ln.e(e);
		}
		return convertView;

	}

	private class ShopButtonListener implements OnClickListener {
		private String mUrl;

		public ShopButtonListener(String url) {
			mUrl = url;
		}

		@Override
		public void onClick(View v) {
			Uri uri = Uri.parse(mUrl);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			mContext.startActivity(intent);
		}

	}

}
