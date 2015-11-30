package com.hw.smarthome.ui.home.adapter.homeup;

import java.util.List;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.hw.smarthome.R;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.constant.ServerConstant;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.homeup.base.InitDatasBase;
import com.hw.smarthome.ui.home.po.HomeAirHistory;
import com.hw.smarthome.ui.home.po.HomeGasHistory;
import com.hw.smarthome.ui.util.MainAcUtil;
import com.hw.util.PreferenceUtil;

/**
 * 主页面内容适配器<br>
 * (maximum line width 65)
 * 
 * @author 曾凡
 * @date 2014年6月12日下午7:25:07
 */
public class InitDatas extends InitDatasBase {
	private Context mContext;
	public InitDatas(FragmentActivity parentActivity,
			View homeUpView, View gridView, SensorDetail detail) {
		super(parentActivity, homeUpView, gridView, detail);
		mContext = parentActivity;
	}

	/**
	 * 初始化燃气传感页面
	 * 
	 * @author 曾凡
	 * @param parentView
	 *            父视图
	 * @param gasSensors
	 *            查询历史数据得到的列表
	 * @time 2014年7月8日 下午3:10:27
	 */
	protected boolean initGas(final View parentView,
			List<HomeGasHistory> gasSensors, final SensorDetail sensor) {
		if (gasSensors == null) {
			return false;
		}
		final LineChart co = (LineChart) parentView
				.findViewById(R.id.coChart);
		final LineChart ch4 = (LineChart) parentView
				.findViewById(R.id.ch4Chart);
		final TextView sensortype = (TextView)parentView.findViewById(R.id.sensortypename);
		final LinearLayout img_ch4 = (LinearLayout)parentView.findViewById(R.id.ll_ch4);
		final LinearLayout img_co = (LinearLayout)parentView.findViewById(R.id.ll_co);
		
	
		
		img_ch4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PreferenceUtil.saveHisType(parentView.getContext(),sensor.getSensorId(),UIConstant.HOME_NAME_CH4);
				sensortype.setText(UIConstant.HOME_NAME_CH4);
				ch4.setVisibility(View.VISIBLE);
				co.setVisibility(View.GONE);
				img_ch4.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg_click));
				img_co.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
			}
		});
	
		img_co.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				PreferenceUtil.saveHisType(parentView.getContext(),sensor.getSensorId(),UIConstant.HOME_NAME_CO);
				sensortype.setText(UIConstant.HOME_NAME_CO);
				co.setVisibility(View.VISIBLE);
				ch4.setVisibility(View.GONE);
				//img_co.setAlpha(0.5f);
				img_co.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg_click));
				img_ch4.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
			}
		});
		int historyCount = 0;
		/* 遍历[SH01_02_01] 历史信息的列表 */
		for (HomeGasHistory gasHistory : gasSensors) {
			if (gasHistory.getSensorId() == null
					|| sensor.getSensorId() == null) {
				break;
			}
			if (gasHistory.getSensorId().equals(
					sensor.getSensorId())) {
//				if (!isNullParam(gasHistory.getCos())) {
					//co.setVisibility(View.VISIBLE);
					initHistoryDatas(co,
							UIConstant.HOME_NAME_CO,
							UIConstant.HOME_UNIT_CO,
							gasHistory.getCos());
					historyCount++;
//				}
//				if (!isNullParam(gasHistory.getCh4s())) {
					//ch4.setVisibility(View.VISIBLE);
					initHistoryDatas(ch4,
							UIConstant.HOME_NAME_CH4,
							UIConstant.HOME_UNIT_CH4,
							gasHistory.getCh4s());
					historyCount++;
//				}
			}
		}
		return true;
	}

	/**
	 * 初始化空气传感页面
	 * 
	 * @author 曾凡
	 * @param parentView
	 *            父视图
	 * @param airSensors
	 *            查询历史数据得到的列表
	 * @time 2014年7月8日 下午3:10:27
	 */
	@Override
	protected boolean initAir(final View parentView,
			List<HomeAirHistory> airSensors, final SensorDetail sensor) {
		if (airSensors == null) {
			return false;
		}
		final LineChart temperature = (LineChart) parentView
				.findViewById(R.id.temperatureChart);
		final LineChart humidity = (LineChart) parentView
				.findViewById(R.id.humidityChart);
		final LineChart co2 = (LineChart) parentView
				.findViewById(R.id.co2Chart);
		final LineChart voc = (LineChart) parentView
				.findViewById(R.id.vocChart);
		final LineChart pm25 = (LineChart) parentView
				.findViewById(R.id.pm25Chart);
		
		final TextView sensortype = (TextView)parentView.findViewById(R.id.sensortypename);
		final LinearLayout temp = (LinearLayout)parentView.findViewById(R.id.ll_temp);
		final LinearLayout hum = (LinearLayout)parentView.findViewById(R.id.ll_hum);
		final LinearLayout ll_co2 = (LinearLayout)parentView.findViewById(R.id.ll_co2);
		final LinearLayout ll_voc = (LinearLayout)parentView.findViewById(R.id.ll_voc);
		final LinearLayout ll_pm = (LinearLayout)parentView.findViewById(R.id.ll_pm);
		
	
		
		temp.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PreferenceUtil.saveHisType(parentView.getContext(),sensor.getSensorId(),UIConstant.HOME_NAME_TEMPERATURE);
				sensortype.setText(UIConstant.HOME_NAME_TEMPERATURE);
				temperature.setVisibility(View.VISIBLE);
				humidity.setVisibility(View.GONE);
				co2.setVisibility(View.GONE);
				voc.setVisibility(View.GONE);
				pm25.setVisibility(View.GONE);
				
				temp.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg_click));
				hum.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_co2.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_voc.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_pm.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
			}
		});
	
		hum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PreferenceUtil.saveHisType(parentView.getContext(),sensor.getSensorId(),UIConstant.HOME_NAME_HUMIDITY);
				sensortype.setText(UIConstant.HOME_NAME_HUMIDITY);
				temperature.setVisibility(View.GONE);
				humidity.setVisibility(View.VISIBLE);
				co2.setVisibility(View.GONE);
				voc.setVisibility(View.GONE);
				pm25.setVisibility(View.GONE);
				
				hum.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg_click));
				temp.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_co2.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_voc.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_pm.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
			}
		});
		
		ll_co2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PreferenceUtil.saveHisType(parentView.getContext(),sensor.getSensorId(),UIConstant.HOME_NAME_CO2);
				sensortype.setText(UIConstant.HOME_NAME_CO2);
				co2.setVisibility(View.VISIBLE);
				humidity.setVisibility(View.GONE);
				temperature.setVisibility(View.GONE);
				voc.setVisibility(View.GONE);
				pm25.setVisibility(View.GONE);
				
				ll_co2.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg_click));
				hum.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				temp.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_voc.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_pm.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
			}
		});
		
		ll_voc.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PreferenceUtil.saveHisType(parentView.getContext(),sensor.getSensorId(),UIConstant.HOME_NAME_VOC);
				sensortype.setText(UIConstant.HOME_NAME_VOC);
				voc.setVisibility(View.VISIBLE);
				humidity.setVisibility(View.GONE);
				temperature.setVisibility(View.GONE);
				co2.setVisibility(View.GONE);
				pm25.setVisibility(View.GONE);
				
				ll_voc.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg_click));
				hum.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_co2.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				temp.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_pm.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
			}
		});
		
		ll_pm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				PreferenceUtil.saveHisType(parentView.getContext(),sensor.getSensorId(),UIConstant.HOME_NAME_PM25);
				sensortype.setText(UIConstant.HOME_NAME_PM25);
				pm25.setVisibility(View.VISIBLE);
				humidity.setVisibility(View.GONE);
				temperature.setVisibility(View.GONE);
				co2.setVisibility(View.GONE);
				voc.setVisibility(View.GONE);
				
				ll_pm.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg_click));
				hum.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_co2.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				ll_voc.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
				temp.setBackgroundColor(parentView.getResources().getColor(R.color.ui_home_view_bg));
			}
		});
		
		int historyCount = 0;
		/* 遍历[SH01_02_01] 历史信息的列表 */
		for (HomeAirHistory airHistory : airSensors) {
			if (airHistory.getSensorId() == null
					|| sensor.getSensorId() == null) {
				break;
			}
			if (airHistory.getSensorId().equals(
					sensor.getSensorId())) {

//				if (!isNullParam(airHistory.getPm25s())) {
					//pm25.setVisibility(View.VISIBLE);
					initHistoryDatas(pm25,
							UIConstant.HOME_NAME_PM25,
							UIConstant.HOME_UNIT_PM25,
							airHistory.getPm25s());
					historyCount++;
//				}
//				if (!isNullParam(airHistory.getCo2s())) {
					//co2.setVisibility(View.VISIBLE);
					initHistoryDatas(co2,
							UIConstant.HOME_NAME_CO2,
							UIConstant.HOME_UNIT_CO2,
							airHistory.getCo2s());
					historyCount++;
//				}
//				if (!isNullParam(airHistory.getTemperatures())) {
					//temperature.setVisibility(View.VISIBLE);
					initHistoryDatas(temperature,
							UIConstant.HOME_NAME_TEMPERATURE,
							UIConstant.HOME_UNIT_TEMPERATURE,
							airHistory.getTemperatures());
					historyCount++;
//				}

//				if (!isNullParam(airHistory.getHumiditys())) {
					//humidity.setVisibility(View.VISIBLE);
					initHistoryDatas(humidity,
							UIConstant.HOME_NAME_HUMIDITY,
							UIConstant.HOME_UNIT_HUMIDITY,
							airHistory.getHumiditys());
					historyCount++;
//				}
//				if (!isNullParam(airHistory.getVocs())) {
					//voc.setVisibility(View.VISIBLE);
					initHistoryDatas(voc,
							UIConstant.HOME_NAME_VOC,
							UIConstant.HOME_UNIT_VOC,
							airHistory.getVocs());
					historyCount++;
//				}
			}
		}
		// /* 初始化数据History List bgn */
		// List<HomeHistoryViewPo> historyList = new
		// LinkedList<HomeHistoryViewPo>();
		// HomeHistoryViewPo tempPo = null;
		// for (HomeAirHistory airHistory : airSensors) {
		// if (airHistory.getSensorId() == null
		// || sensor.getSensorId() == null) {
		// break;
		// }
		// if (airHistory.getSensorId().equals(
		// sensor.getSensorId())) {
		//
		// if (!isNullParam(airHistory.getCo2s())) {
		// tempPo = new HomeHistoryViewPo();
		// tempPo.setName(UIConstant.HOME_NAME_CO2);
		// tempPo.setUnit(UIConstant.HOME_UNIT_CO2);
		// tempPo.setValues(airHistory.getCo2s());
		// historyList.add(tempPo);
		// }
		// if (!isNullParam(airHistory.getTemperatures())) {
		// tempPo = new HomeHistoryViewPo();
		// tempPo.setName(UIConstant.HOME_NAME_TEMPERATURE);
		// tempPo.setUnit(UIConstant.HOME_UNIT_TEMPERATURE);
		// tempPo.setValues(airHistory
		// .getTemperatures());
		// historyList.add(tempPo);
		// }
		// if (!isNullParam(airHistory.getPm25s())) {
		// tempPo = new HomeHistoryViewPo();
		// tempPo.setName(UIConstant.HOME_NAME_PM25);
		// tempPo.setUnit(UIConstant.HOME_UNIT_PM25);
		// tempPo.setValues(airHistory.getPm25s());
		// historyList.add(tempPo);
		// }
		// if (!isNullParam(airHistory.getHumiditys())) {
		// tempPo = new HomeHistoryViewPo();
		// tempPo.setName(UIConstant.HOME_NAME_HUMIDITY);
		// tempPo.setUnit(UIConstant.HOME_UNIT_HUMIDITY);
		// tempPo.setValues(airHistory.getHumiditys());
		// historyList.add(tempPo);
		// }
		// if (!isNullParam(airHistory.getC6h6s())) {
		// tempPo = new HomeHistoryViewPo();
		// tempPo.setName(UIConstant.HOME_NAME_C6H6);
		// tempPo.setUnit(UIConstant.HOME_UNIT_C6H6);
		// tempPo.setValues(airHistory.getC6h6s());
		// historyList.add(tempPo);
		// }
		// if (!isNullParam(airHistory.getCh2os())) {
		// tempPo = new HomeHistoryViewPo();
		// tempPo.setName(UIConstant.HOME_NAME_CH2O);
		// tempPo.setUnit(UIConstant.HOME_UNIT_CH2O);
		// tempPo.setValues(airHistory.getCh2os());
		// historyList.add(tempPo);
		// }
		//
		// }
		// }
		//

		// HomeHistoryListAdapter listAdapter = HomeHistoryListAdapter
		// .getInstance(mParentActivity, historyList);
		// View temp = parentView
		// .findViewById(R.id.homeHistoryAirList);
		// ListView lv = (ListView) temp;
		// lv.setAdapter(listAdapter);
		return true;
	}

	@Override
	protected void initHistoryTime(final View mHomeUpView) {
		final String sensorId = mSensor.getSensorId();
		final TextView sensorTime = (TextView)mHomeUpView.findViewById(R.id.sensortypetime);
		sensorTime.setText(mHomeUpView.getResources().getString(R.string.home_history_tab_last7Days));
		final TextView lasthours = (TextView)mHomeUpView.findViewById(R.id.last24hours);
		final TextView lastdays = (TextView)mHomeUpView.findViewById(R.id.last7days);
		final TextView lastmonth = (TextView)mHomeUpView.findViewById(R.id.last1month);
		final TextView sensortype = (TextView)mHomeUpView.findViewById(R.id.sensortypename);

		
		lasthours.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg_click));
		
		lastdays.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getHistoryType().put(sensorId, DAY_7);
				MainAcUtil.send2Service(mContext,
						ServerConstant.SH01_02_01_03, 0,sensorId);
				sensorTime.setText(mHomeUpView.getResources().getString(R.string.home_history_tab_last7Days));
				lastdays.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg_click));
				lastmonth.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg));
				lasthours.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg));
			

				initHistory();
				
				sensortype.setText(PreferenceUtil.getHisType(mHomeUpView.getContext(),sensorId));   //顺序要在initHistory后
			}
		});
		lasthours.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getHistoryType().put(sensorId, HOUR_24);
				MainAcUtil.send2Service(mContext,
						ServerConstant.SH01_02_01_04, 0,sensorId);

				sensorTime.setText(mHomeUpView.getResources().getString(R.string.home_history_tab_last24hours));
				lasthours.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg_click));
				lastmonth.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg));
				lastdays.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg));
				

				initHistory();
				
				sensortype.setText(PreferenceUtil.getHisType(mHomeUpView.getContext(),sensorId));
			}
		});
		lastmonth.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getHistoryType().put(sensorId, DAY_30);
				MainAcUtil.send2Service(mContext,
						ServerConstant.SH01_02_01_05, 0,sensorId);

				sensorTime.setText(mHomeUpView.getResources().getString(R.string.home_history_tab_last30Days));
				lastmonth.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg_click));
				lastdays.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg));
				lasthours.setBackgroundColor(mHomeUpView.getResources().getColor(R.color.ui_home_view_bg));
				

				initHistory();
				
				sensortype.setText(PreferenceUtil.getHisType(mHomeUpView.getContext(),sensorId));
			}
		});
	}
	 
}
