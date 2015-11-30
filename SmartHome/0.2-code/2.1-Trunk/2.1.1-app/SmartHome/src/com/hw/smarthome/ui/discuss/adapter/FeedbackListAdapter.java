package com.hw.smarthome.ui.discuss.adapter;

import java.util.ArrayList;
import java.util.List;

import com.hw.smarthome.R;
import com.hw.smarthome.po.S004PO;
import com.hw.smarthome.po.S006PO;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 
 * 
 * 项目名称：SmartHome
 * 类名称：FeedbackListAdapter
 * 类描述：个人留言列表
 * 创建人：lijing
 * 创建时间：2014-7-8 下午2:13:54
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 *
 */
public class FeedbackListAdapter extends BaseAdapter {
	private static final String TAG = FeedbackListAdapter.class.getSimpleName();
	Context mContext;
	List<S006PO> list = new ArrayList<S006PO>();
	public FeedbackListAdapter(Context context) {
		// TODO Auto-generated constructor stub
		this.mContext = context;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	
	public List<S006PO> getList() {
		return list;
	}
	public void setList(List<S006PO> list) {
		this.list = list;
		notifyDataSetChanged();
	}
	@Override
	public S006PO getItem(int position) {
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
		S006PO item = getItem(position);
		themeView.setText("反馈问题："+item.getMa002());
		TextView replyView = (TextView) convertView.findViewById(R.id.reply);
		TextView replyTimeView = (TextView) convertView.findViewById(R.id.date_reply);
		View replyLayout = convertView.findViewById(R.id.layout_reply);
		if(TextUtils.isEmpty(item.getMa005())){
			replyLayout.setVisibility(View.GONE);
		}else{
			replyLayout.setVisibility(View.VISIBLE);
			replyView.setText("回复内容："+item.getMa005());
			replyTimeView.setText(item.getMa007());
		}
		
		TextView dateView = (TextView) convertView.findViewById(R.id.date);
		dateView.setText(item.getMa003());
		dateView.setVisibility(View.VISIBLE);
		return convertView;
	}

}
