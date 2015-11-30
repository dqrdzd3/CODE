package com.hw.smarthome.ui.home.adapter.homeup.adpter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import me.xiaopan.android.switchbutton.SwitchButton;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.hw.smarthome.R;
import com.hw.smarthome.ctrl.util.CtrlUtils;
import com.hw.smarthome.po.CtrlContent;
import com.hw.smarthome.po.SensorCtrlDetail;


public class ControlPagerSwitcherAdapter extends BaseAdapter {

	public SensorCtrlDetail ctrlDetail;
	private LayoutInflater mInflater;
	private Context mContext;
	private List<Map<String, Object>> ctrlList = new ArrayList<Map<String,Object>>();
	public  class ViewHolder{
			public SwitchButton switchButton;
	}	
	public ControlPagerSwitcherAdapter(Context context,SensorCtrlDetail ctrlDetail){
		this.mInflater  = LayoutInflater.from(context);
		this.mContext = context;
		this.ctrlDetail = ctrlDetail;
		
		for (Entry<String, CtrlContent> entry : ctrlDetail
				.getCtrlContent().entrySet()) {
			
			
			
			//entry.getValue().setSwitchState("01");
			if (entry.getKey()!=null) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("switchId", entry.getKey());
				if (entry.getValue()!=null && entry.getValue().getSwitchState().equals("01")){
					map.put("ctrContent", entry.getValue());
					map.put("switchStatus", Boolean.valueOf(true));
				}
				if (entry.getValue()!=null && entry.getValue().getSwitchState().equals("00")){
					map.put("ctrContent", entry.getValue());
					map.put("switchStatus", Boolean.valueOf(false));
				}
				ctrlList.add(map);
				
			}
			
		}
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return ctrlList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return ctrlList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.switcher_item, null);
	
			holder.switchButton  = (SwitchButton)convertView.findViewById(R.id.switch_main_1);
			
			holder.switchButton.setTextColor(mContext.getResources().getColor(R.color.white));
			
			holder.switchButton.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					CtrlContent ctrlContent = (CtrlContent) ctrlList.get(position).get("ctrContent");
					if (isChecked) {
						ctrlContent.setSwitchState("01");
						ctrlDetail.getCtrlContent().put((String) ctrlList.get(position).get("switchId"), ctrlContent);
						CtrlUtils.turnOnDevice(mContext, ctrlDetail);
					}else {
						ctrlContent.setSwitchState("00");
						ctrlDetail.getCtrlContent().put((String) ctrlList.get(position).get("switchId"), ctrlContent);
						CtrlUtils.turnOffDevice(mContext, ctrlDetail);
					}
					
					
				}
			});
			convertView.setTag(holder);
		}
		else {
			holder=(ViewHolder)convertView.getTag();
		}
		
		
		holder.switchButton.setChecked((Boolean) ctrlList.get(position).get("switchStatus"));
		holder.switchButton.setText(mContext.getString(R.string.switcher_name)+ctrlList.get(position).get("switchId"));
	
		return convertView;
	}
	
	private String switcherName(String code){
		/**
		 * 开关类型 TYPE_NONE = 0x00, //无连接 TYPE_LEMP = 0x01, //灯 TYPE_FAN = 0x02, //风扇
		 * TYPE_HUMIDIFIER = 0x03, //加湿器 TYPE_AIRPURIFIER = 0x04, //空气净化器
		 * TYPE_AIRCONDITIONER = 0x05, //空调
		 */
		if (code.equals("00")) {
			return "";
		}
		return "";
	}

}
	