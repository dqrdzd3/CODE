package com.hw.smarthome.ui.home.adapter;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hw.smarthome.R;
import com.hw.smarthome.po.HistoryAlarm;

public class AlarmHistoryAdapter extends BaseAdapter {
	List<HistoryAlarm> institutions = new ArrayList<HistoryAlarm>();
	Context mContext;

	public AlarmHistoryAdapter(Context context) {
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	public void setData(List<HistoryAlarm> list) {
		this.institutions.clear();
		this.institutions = list;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return institutions.size();
	}

	@Override
	public HistoryAlarm getItem(int position) {
		// TODO Auto-generated method stub
		return institutions.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView,
			ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = LayoutInflater.from(mContext).inflate(
				R.layout.ui_home_alarm_history_item, null);
		TextView nameView = (TextView) convertView
				.findViewById(R.id.name);
		TextView desView = (TextView) convertView
				.findViewById(R.id.description);
		HistoryAlarm in = getItem(position);
		initValues(in);
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		// nameView.setText(sdf.format(in.getRecdatetime()));
		nameView.setText(in.getMa004() + "\n" + in.getMa005());
		desView.setText(mContext.getText(R.string.ui_alarm_status_abnomal) + "       "
				+ in.getMa002());
		return convertView;
	}

	private void initValues(HistoryAlarm alarm) {
//		MA003,'00','预热','01','正常','02','低报','03','高报','04','传感器故障','05','通讯故障','06','求救信号') as "ma003",
//		,'04','一氧化碳','01','甲烷','30','二氧化碳','D8','PM2.5','CA','湿度','C9','温度','17','甲醛','1B','苯')
	}
}
