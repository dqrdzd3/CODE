package com.hw.smarthome.ui.home.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.util.Ln;

public class GridviewAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> listItems;
	private LayoutInflater listContainer;
	private int itemViewResource;
	private int clickTemp = -1;

	public GridviewAdapter(Context context,
			List<Map<String, Object>> data, int resource) {
		this.context = context;
		this.listContainer = LayoutInflater.from(context); // 创建视图容器并设置上下文
		this.itemViewResource = resource;
		this.listItems = data;
	}

	// 标识选择的Item
	public void setSeclection(int position) {
		clickTemp = position;
	}

	@Override
	public int getCount() {
		return listItems.size();
	}

	@Override
	public Object getItem(int position) {
		return listItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView,
			ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					itemViewResource, null);
		}
		try {
			if (((Integer) listItems.get(position).get("img")) == 0) {
				return convertView;
			}
			if (clickTemp == position) {
				int d = context.getResources().getColor(
						R.color.gridview_item_click);
				convertView.setBackgroundColor(d);
			} else {
				convertView
						.setBackgroundColor(Color.TRANSPARENT);

			}
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.imagetype);
			TextView textView = (TextView) convertView
					.findViewById(R.id.typevalue);
			textView.setTextColor(Color.WHITE);
			imageView.setImageResource(((Integer) listItems.get(
					position).get("img")).intValue());
			textView.setText(listItems.get(position).get("text")
					.toString());

			TextView status = (TextView) convertView
					.findViewById(R.id.status);
			status.setTextColor(Color.WHITE);
			status.setText(listItems.get(position).get("status")
					.toString());

			TextView solution = (TextView) convertView
					.findViewById(R.id.solution);
			solution.setTextColor(Color.WHITE);
			solution.setText(listItems.get(position)
					.get("solution").toString());

			TextView url = (TextView) convertView
					.findViewById(R.id.url);
			url.setTextColor(Color.WHITE);
			url.setText(listItems.get(position).get("url")
					.toString());
			ProgressBar bar = (ProgressBar) convertView
					.findViewById(R.id.ui_home_value_progressBar);
			int progressCur = (Integer) listItems.get(position)
					.get("progressCur");
			int progressStatus = (Integer) listItems.get(
					position).get("progressStatus");
			Drawable bgColor = null;
			switch (progressStatus) {
			case UIConstant.SOLUTION_STATUS_LOW:
				bgColor = context.getResources().getDrawable(
						R.drawable.ui_home_pager_progressbg1);
				break;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				bgColor = context.getResources().getDrawable(
						R.drawable.ui_home_pager_progressbg2);
				break;
			case UIConstant.SOLUTION_STATUS_HIGH:
				bgColor = context.getResources().getDrawable(
						R.drawable.ui_home_pager_progressbg3);
				break;
			}
			bar.setProgressDrawable(bgColor);
			bar.setProgress(progressCur);
			bar.setMax(100);

		} catch (Exception e) {
			Ln.e(e, "构建实时数据Gird异常");
		}
		return convertView;
	}
}