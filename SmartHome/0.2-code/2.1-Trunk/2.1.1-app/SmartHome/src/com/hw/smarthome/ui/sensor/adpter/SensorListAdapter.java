package com.hw.smarthome.ui.sensor.adpter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.home.HomeFragment;
import com.hw.smarthome.ui.sensor.SensorOneKeyFragment;
import com.hw.smarthome.ui.sensor.po.SensorListView;
import com.hw.smarthome.ui.sensor.util.SensorUtil;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.smarthome.view.pop.progress.PopProgress;
import com.hw.util.Ln;
import com.hw.util.NetUtil;

/**
 * Demonstration of using ListFragment to show a list of items from a canned
 * array.
 */
public class SensorListAdapter extends BaseAdapter {

	private TextView sensorName;
	private TextView sensorId;
	private TextView isOnline;
	private BootstrapButton deleteButton;
	private LayoutInflater mInflater;
	private Context mContext;
	private View mSensorView;
	/** 业务数据库获取的传感器 */
	private List<SensorDetail> detailList;

	public SensorListAdapter(Context context,
			List<SensorDetail> sensorList, View sensorView) {
		this.mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.detailList = sensorList;
		this.mSensorView = sensorView;
	}

	@Override
	public int getCount() {
		if (detailList != null) {
			return detailList.size();
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
		try {
			/* 绑定的传感器不为空且大小不能大于position */
			if (detailList != null
					&& detailList.size() > position) {
				SensorListView listView = null;
				String sensorIdStr = detailList.get(position)
						.getSensorId();

				if (convertView == null) {
					listView = new SensorListView();
					convertView = mInflater.inflate(
							R.layout.ui_sensor_list_item, null);
					sensorId = (TextView) convertView
							.findViewById(R.id.uiSensorId);
					// sensorId.setVisibility(View.GONE);
					sensorName = (TextView) convertView
							.findViewById(R.id.uiSensorName);

					isOnline = (TextView) convertView
							.findViewById(R.id.uiSensorisOnlineText);

					deleteButton = (BootstrapButton) convertView
							.findViewById(R.id.uiSensorDelete);
					deleteButton.setVisibility(View.INVISIBLE);
					deleteButton
							.setOnClickListener(new DeleteButtonListener(
									position));
					listView.setSensorId(sensorId);
					listView.setDeleteButton(deleteButton);
					listView.setIsOnline(isOnline);
					listView.setSensorName(sensorName);

					convertView.setTag(listView);

				} else {
					listView = (SensorListView) convertView
							.getTag();
				}
				/* 赋值 */
				String sensorName = detailList.get(position)
						.getName();
				if (sensorName == null || "".equals(sensorName)) {
					int type = SmartHomeServiceUtil
							.getSensorTypeWithId(sensorIdStr);
					String name = SmartHomeServiceUtil
							.getDefaultSensorName(type);
					sensorName = name;
				}

				listView.getSensorId().setText(sensorIdStr);
				listView.getSensorName().setText(sensorName);
				/* 初始化在线信息 */
				DealWithSensor
						.initIsOnLine(mContext, detailList);
				listView.getIsOnline()
						.setText(
								detailList.get(position)
										.isOnline() ? "在线"
										: "不在线");
			}
		} catch (Exception e) {
			Ln.e(e);
		}
		return convertView;

	}

	private class DeleteButtonListener implements
			OnClickListener {
		private int mPosition;
		private PopProgress popProgress;

		public DeleteButtonListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View v) {
			try {
				if (!NetUtil.isNetworkConnected(mContext)) {
					popProgress = PopProgress.getInstance(
							mContext, mSensorView);
					popProgress.setText("网络不正常，请稍后重试");
					popProgress.showProgress();
					return;
				}
				popProgress = PopProgress.getInstance(mContext,
						mSensorView);
				popProgress.setText("删除设备中");
				popProgress.showProgress();
				SensorOneKeyFragment.getInstance()
						.setPopProgress(popProgress);
				HomeFragment.getInstance().setPopProgress(
						popProgress);
				/* 查询业务数据库获取的传感器 */
				List<SensorDetail> detailList = SensorUtil
						.getSensorDetails(mContext);
				if (mPosition <= (detailList.size() - 1)) {
					String sensorId = detailList.get(mPosition)
							.getSensorId();

					/* 从服务器获取最新的列表 */
					MainAcUtil.send2Service(mContext,
							ServerConstant.SH01_01_01_04, 0,
							sensorId);
				}
				v.setVisibility(View.INVISIBLE);
			} catch (Exception e) {
				Ln.e(e, "点击删除按钮异常");
			}
		}

	}

	public List<SensorDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<SensorDetail> detailList) {
		this.detailList = detailList;
	}
}
