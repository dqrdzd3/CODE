package com.hw.smarthome.ui.discuss.adapter;

import java.util.ArrayList;
import java.util.List;

import com.hw.smarthome.R;
import com.hw.smarthome.po.S004PO;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 
 * 
 * 项目名称：SmartHome
 * 类名称：DiscussThemeListAdapter
 * 类描述：讨论区主题列表项适配器
 * 创建人：lijing
 * 创建时间：2014-7-4 下午1:40:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 *
 */
public class DiscussThemeListAdapter extends BaseAdapter {
	private static final String TAG = DiscussThemeListAdapter.class.getSimpleName();
	Context mContext;
	List<S004PO> list = new ArrayList<S004PO>();
	public DiscussThemeListAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	
	public List<S004PO> getList() {
		return list;
	}
	public void setList(List<S004PO> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public S004PO getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(mContext).inflate(R.layout.ui_discuss_theme_item, null);	
		TextView themeView = (TextView) convertView.findViewById(R.id.content);
		S004PO item = getItem(position);
		themeView.setText(item.getMa002());
		return convertView;
	}

}
