package com.hw.smarthome.ui.home.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import android.content.Context;
import cn.pedant.SweetAlert.SweetAlertDialog;

import com.hw.smarthome.po.HistoryAlarm;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.deal.DealWithHome;
import com.hw.smarthome.ui.sensor.util.SensorUtil;

/**
 * @author 曾凡
 * @time 2014年12月11日 下午4:38:33
 */
public class AlarmUtil {
	public static void CompareAlarm(Context context,
			List<HistoryAlarm> remoteAlarms) {
		Map<String, HistoryAlarm> localAlarms = DealWithHome
				.getAlarm(context);
		HistoryAlarm local = null;
		String remoteSensorId = "";
		if (remoteAlarms.size() > 0) {
			/* 如果本地没有报警则弹出历史最新的报警 */
			// remoteAlarms 只有一个元素
			for (HistoryAlarm remote : remoteAlarms) {
				remoteSensorId = remoteAlarms.get(0).getMa006();
				if (localAlarms == null) {
					localAlarms = new HashMap<String, HistoryAlarm>();
				}
				if (localAlarms.containsKey(remoteSensorId)) {
					local = localAlarms.get(remoteSensorId);
					/* 如果存在设备号相同但是时间不相同的数据，则更新设备内容 */
					if (!remote.getMa005().equals(
							local.getMa005())) {
						local.setMa001(remote.getMa001());
						local.setMa002(remote.getMa002());
						local.setMa003(remote.getMa003());
						local.setMa004(remote.getMa004());
						local.setMa005(remote.getMa005());
						local.setMa006(remote.getMa006());
						local.setMa007(remote.getMa007());
						local.setMa008(remote.getMa008());

						alertDialog(context, local);
					}
				} else {
					localAlarms.put(remoteSensorId, remote);
					alertDialog(context, remote);
				}
				DealWithHome.saveAlarm(context, localAlarms);
			}
		}
	}

	/**
	 * 退出对话框
	 * 
	 * @author 曾凡
	 * @time 2014年11月3日 下午3:18:01
	 */
	private static void alertDialog(Context context,
			HistoryAlarm alarm) {
		String unit = "甲烷".equals(alarm.getMa004()) ? "%LEL"
				: "ppm";
		String name = "";
		List<SensorDetail> sensorList = SensorUtil
				.getSensorDetails(context);
		for (SensorDetail detail : sensorList) {
			if (detail.getSensorId().equals(alarm.getMa006())) {
				name = detail.getName();
			}
		}
		new SweetAlertDialog(context,
				SweetAlertDialog.WARNING_TYPE)
				.setTitleText(
						alarm.getMa004() + "浓度值 "
								+ alarm.getMa002() + unit)
				.setContentText(
						"报警时间：" + alarm.getMa005() + "\n设备名："
								+ name + "\n设备ID："
								+ alarm.getMa006())
				.setConfirmText("确认")
				.setConfirmClickListener(
						new SweetAlertDialog.OnSweetClickListener() {
							@Override
							public void onClick(
									SweetAlertDialog sDialog) {
								sDialog.dismiss();
							}
						}).show();
	}
}
