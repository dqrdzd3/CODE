package com.hw.smarthome.ui.home.adapter.homeup.util;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.pedant.SweetAlert.SweetInputDialog;

import com.hw.smarthome.R;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.MainActivity;
import com.hw.smarthome.ui.home.constant.HomeConstant;
import com.hw.smarthome.ui.home.scene.SceneAddActivity;
import com.hw.smarthome.ui.sensor.SensorOneKeyFragment;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.Ln;
import com.hw.util.NetUtil;
import com.hw.util.UIUtil;

/**
 * @author 曾凡
 * @time 2015年7月6日 上午10:07:29
 */
public class HomeMenuUtil {
	private MainActivity mMainActivity = null;

	/**
	 * 设置“设备名称”和“在线时间”
	 * 
	 * @author 曾凡
	 * @param context
	 * @param name
	 * @param isOnline
	 * @time 2015年7月6日 下午1:23:05
	 */
	public static void setTitleSensorInfo(Context context,
			String name, String sensorId) {
		TextView uiHomeSensorName = null;
		try {
			MainActivity mainActivity = (MainActivity) context;
			uiHomeSensorName = ((TextView) mainActivity
					.findViewById(R.id.uiHomeSensorName));
			if (uiHomeSensorName != null) {
				// if (name != null) {
				// if (name.length() <= 12) {
				// uiHomeSensorName.setTextSize(20);
				// } else if (name.length() > 12) {
				// uiHomeSensorName.setTextSize(15);
				// }
				// }

				uiHomeSensorName.setText(name);
				((TextView) mainActivity
						.findViewById(R.id.uiHomeSensorId))
						.setText(sensorId);
				uiHomeSensorName
						.setOnClickListener(new SensorNameOnClick(
								name, sensorId, context,
								uiHomeSensorName));
			}
		} catch (Exception e) {
			Ln.e(e, "卧槽，连个名字都搞不定，整个人都斯巴达了");
		}
	}

	private static class SensorNameOnClick implements
			OnClickListener {
		TextView mNameText;
		private String mSensorName, mSensorId, mOnLine;
		private Context mContext;

		public SensorNameOnClick(String sensorName,
				String sensorId, Context context,
				TextView nameText) {
			mSensorName = sensorName.substring(0,
					sensorName.indexOf("["));
			mOnLine = sensorName.substring(sensorName
					.indexOf("["));
			mSensorId = sensorId;
			mNameText = nameText;
			mContext = context;
		}

		@Override
		public void onClick(View v) {

			SweetInputDialog dialog = new SweetInputDialog(
					mContext)
					.setTitleText("修改设备名称")
					.setCancelText("取消")

					.setConfirmText("确认")
					.showCancelButton(true)
					.setCancelClickListener(
							new SweetInputDialog.OnSweetClickListener() {
								@Override
								public void onClick(
										SweetInputDialog sDialog) {
									sDialog.dismiss();
								}
							});

			dialog.show();
			dialog.editable(true);
			dialog.setInputEditText(mSensorName);
			final EditText cEditText = dialog.getInputEditText();
			dialog.setConfirmClickListener(new SweetInputDialog.OnSweetClickListener() {

				@Override
				public void onClick(
						SweetInputDialog sweetAlertDialog) {
					if (NetUtil.getNetworkType(mContext) != 1) {
						UIUtil.ToastMessage(mContext,
								"请联网");
					}else{
						sweetAlertDialog.dismiss();
						if (NetUtil.getNetworkType(mContext) != 1) {
							UIUtil.ToastMessage(mContext, "请链接WIFI");
						} else {
							Intent intent = new Intent();
							Bundle bundle = new Bundle();
							bundle.putString("name",
									ServerConstant.SH01_01_01_07);
							bundle.putString("sensorId", mSensorId);
							bundle.putInt("type", 0);
							bundle.putString("DRIVERNAME", cEditText
									.getText().toString());
							intent.putExtra("action", bundle);
							intent.setAction(MainActivity.class
									.getName());
							mNameText.setText(cEditText.getText()
									.toString() + mOnLine);
							mContext.sendBroadcast(intent);
						}
					}
				}
			});

		}
	}

	public HomeMenuUtil(MainActivity mainActivity, View parent) {
		mMainActivity = mainActivity;
		initPopWindow(parent);
	}

	private void initPopWindow(View parent) {

		View contentView = LayoutInflater.from(mMainActivity)
				.inflate(R.layout.layout_list, null);

		final PopupWindow popupWindow = new PopupWindow(
				contentView, LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		popupWindow.setBackgroundDrawable(mMainActivity
				.getResources().getDrawable(
						R.color.halff_transparent));

		popupWindow.setFocusable(true);
		popupWindow.setOutsideTouchable(true);
		final String[] data = mMainActivity.getResources()
				.getStringArray(R.array.right_menu);
		ListView listView = (ListView) contentView
				.findViewById(R.id.list);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent,
					View view, int position, long id) {
				popupWindow.dismiss();
				clickItem(data, position);
			}
		});

		List<String> arrayList = new ArrayList<String>();

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				mMainActivity,
				android.R.layout.simple_list_item_1, data);
		listView.setAdapter(adapter);
		popupWindow
				.setAnimationStyle(android.R.style.Animation_Dialog);
		popupWindow.update();
		popupWindow.showAsDropDown(parent, 0, 10);
	}

	private void clickItem(String[] arr, int positon) {
		if (arr[positon].equals(HomeConstant.RIGHTMENU1)) {
			mMainActivity.toRegSensor();
		}
		if (arr[positon].equals(HomeConstant.RIGHTMENU2)) {
			MainAcUtil.changeFragment(
					mMainActivity.getSupportFragmentManager(),
					SensorOneKeyFragment.getInstance());
		}
		if (arr[positon].equals(HomeConstant.RIGHTMENU3)) {
			Intent intent = new Intent(mMainActivity,
					SceneAddActivity.class);
			mMainActivity.startActivity(intent);
		}
	}
}
