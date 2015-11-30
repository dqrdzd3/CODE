package com.hw.smarthome.ui.home.adapter.homeup.pager.solution;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.hw.smarthome.R;
import com.hw.smarthome.po.S007PO;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.server.deal.DealWithSolution;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.constant.UIConstant;
import com.hw.smarthome.ui.home.adapter.homeup.util.HomePagerUtil;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.util.MathUtils;

/**
 * @author 曾凡
 * @time 2015年3月12日 下午3:14:12
 */
public class SolutionUtils {

	/**
	 * 获取[检测评估]页面的主提示
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2015年8月19日 下午3:17:11
	 */
	public static String getSolutionHint(Context context,
			SensorDetail detail) {
		int type = 0;
		int state = 0;
		String res = context.getResources().getString(
				R.string.ui_solution_default);
		if (detail != null) {
			type = SmartHomeServiceUtil.getSensorType(detail);
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				state = getSolutionHintState(context, detail);
				switch (state) {
				case HomePagerUtil.LOW:
					res = context.getResources().getString(
							R.string.ui_solution_chk_hint_1);
					break;
				case HomePagerUtil.NORMAL:
					res = context.getResources().getString(
							R.string.ui_solution_chk_hint_2);
					break;
				case HomePagerUtil.HIGH:
					res = context.getResources().getString(
							R.string.ui_solution_chk_hint_3);
					break;
				default:
					break;
				}
			}
		}

		return res;
	}

	public static int getSolutionHintState(Context context,
			SensorDetail detail) {

		int type = 0;
		int state = 0;
		if (detail != null) {
			type = SmartHomeServiceUtil.getSensorType(detail);
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				int vocType = getSensorState(
						UIConstant.SOLUTION_VOC, detail);
				int pm25Type = getSensorState(
						UIConstant.SOLUTION_PM25, detail);
				int co2Type = getSensorState(
						UIConstant.SOLUTION_CO2, detail);
				int[] arrays = { vocType, pm25Type, co2Type };
				state = MathUtils.find(arrays);
			}
		}

		return state;

	}

	public static Map<Integer, S007PO> getSolutionMap(
			SensorDetail detail) {

		int type = 0;
		Map<Integer, S007PO> map = new HashMap<Integer, S007PO>();
		S007PO temp = null;
		if (detail != null) {
			type = SmartHomeServiceUtil
					.getSensorTypeWithId(detail.getSensorId());
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_TEMPERATURE
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_TEMPERATURE,
											detail));
					if (temp != null) {
						map.put(UIConstant.SOLUTION_TEMPERATURE,
								temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_HUMIDITY
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_HUMIDITY,
											detail));
					if (temp != null) {
						map.put(UIConstant.SOLUTION_HUMIDITY,
								temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_CO2
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_CO2,
											detail));
					if (temp != null) {
						map.put(UIConstant.SOLUTION_CO2, temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_PM25
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_PM25,
											detail));
					if (temp != null) {
						map.put(UIConstant.SOLUTION_PM25, temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_VOC
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_VOC,
											detail));
					if (temp != null) {
						map.put(UIConstant.SOLUTION_VOC, temp);
					}
				}
			} else {
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_CO
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_CO,
											detail));
					if (temp != null) {
						map.put(UIConstant.SOLUTION_CO, temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_CH4
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_CH4,
											detail));
					if (temp != null) {
						map.put(UIConstant.SOLUTION_CH4, temp);
					}
				}
			}

		}
		return map;

	}

	public static List<S007PO> getSolution(SensorDetail detail) {
		int type = 0;
		List<S007PO> list = new LinkedList<S007PO>();
		S007PO temp = null;
		if (detail != null) {
			type = SmartHomeServiceUtil
					.getSensorTypeWithId(detail.getSensorId());
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_TEMPERATURE
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_TEMPERATURE,
											detail));
					if (temp != null) {
						list.add(temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_HUMIDITY
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_HUMIDITY,
											detail));
					if (temp != null) {
						list.add(temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_CO2
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_CO2,
											detail));
					if (temp != null) {
						list.add(temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_PM25
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_PM25,
											detail));
					if (temp != null) {
						list.add(temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_VOC
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_VOC,
											detail));
					if (temp != null) {
						list.add(temp);
					}
				}
			} else {
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_CO
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_CO,
											detail));
					if (temp != null) {
						list.add(temp);
					}
				}
				{
					temp = DealWithSolution
							.getSolutions()
							.get(UIConstant.SOLUTION_CH4
									+ "_"
									+ getSensorState(
											UIConstant.SOLUTION_CH4,
											detail));
					if (temp != null) {
						list.add(temp);
					}
				}
			}

		}
		return list;
	}

	public static String getSensorNameByType(int type) {
		switch (type) {
		case UIConstant.SOLUTION_TEMPERATURE:
			return UIConstant.HOME_NAME_TEMPERATURE;
		case UIConstant.SOLUTION_HUMIDITY:
			return UIConstant.HOME_NAME_HUMIDITY;
		case UIConstant.SOLUTION_CO2:
			return UIConstant.HOME_NAME_CO2;
		case UIConstant.SOLUTION_PM25:
			return UIConstant.HOME_NAME_PM25;
		case UIConstant.SOLUTION_VOC:
			return UIConstant.HOME_NAME_VOC;
		case UIConstant.SOLUTION_CO:
			return UIConstant.HOME_NAME_CO;
		case UIConstant.SOLUTION_CH4:
			return UIConstant.HOME_NAME_CH4;
		}
		return UIConstant.WAIT;
	}

	/** 获取状态的名称 */
	public static String getSensorStateName(int type, int state) {
		switch (type) {
		case UIConstant.SOLUTION_TEMPERATURE:
			switch (state) {
			case UIConstant.SOLUTION_STATUS_LOW:
				return UIConstant.SOLUTION_TEM_LOW;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				return UIConstant.SOLUTION_TEM_MIDDLE;
			case UIConstant.SOLUTION_STATUS_HIGH:
				return UIConstant.SOLUTION_TEM_HIGH;

			}
		case UIConstant.SOLUTION_HUMIDITY:
			switch (state) {
			case UIConstant.SOLUTION_STATUS_LOW:
				return UIConstant.SOLUTION_HUM_LOW;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				return UIConstant.SOLUTION_HUM_MIDDLE;
			case UIConstant.SOLUTION_STATUS_HIGH:
				return UIConstant.SOLUTION_HUM_HIGH;

			}
		case UIConstant.SOLUTION_CO2:
			switch (state) {
			case UIConstant.SOLUTION_STATUS_LOW:
				return UIConstant.SOLUTION_CO2_LOW;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				return UIConstant.SOLUTION_CO2_MIDDLE;
			case UIConstant.SOLUTION_STATUS_HIGH:
				return UIConstant.SOLUTION_CO2_HIGH;

			}
		case UIConstant.SOLUTION_PM25:
			switch (state) {
			case UIConstant.SOLUTION_STATUS_LOW:
				return UIConstant.SOLUTION_PM25_LOW;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				return UIConstant.SOLUTION_PM25_MIDDLE;
			case UIConstant.SOLUTION_STATUS_HIGH:
				return UIConstant.SOLUTION_PM25_HIGH;

			}
		case UIConstant.SOLUTION_VOC:
			switch (state) {
			case UIConstant.SOLUTION_STATUS_LOW:
				return UIConstant.SOLUTION_VOC_LOW;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				return UIConstant.SOLUTION_VOC_MIDDLE;
			case UIConstant.SOLUTION_STATUS_HIGH:
				return UIConstant.SOLUTION_VOC_HIGH;

			}
		case UIConstant.SOLUTION_CO:
			switch (state) {
			case UIConstant.SOLUTION_STATUS_LOW:
				return UIConstant.SOLUTION_STATUS_NORMAL;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				// return UIConstant.SOLUTION_STATUS_MIDDLE4_NAME;
				return UIConstant.SOLUTION_STATUS_NORMAL;
			case UIConstant.SOLUTION_STATUS_HIGH:
				return UIConstant.SOLUTION_STATUS_HIGH4_NAME;

			}
		case UIConstant.SOLUTION_CH4:
			switch (state) {
			case UIConstant.SOLUTION_STATUS_LOW:
				return UIConstant.SOLUTION_STATUS_NORMAL;
			case UIConstant.SOLUTION_STATUS_MIDDLE:
				// return UIConstant.SOLUTION_STATUS_MIDDLE4_NAME;
				return UIConstant.SOLUTION_STATUS_NORMAL;
			case UIConstant.SOLUTION_STATUS_HIGH:
				return UIConstant.SOLUTION_STATUS_HIGH4_NAME;

			}
		}
		return UIConstant.WAIT;
	}

	public static int getSensorState(int type,
			SensorDetail detail) {
		int deviceType = SmartHomeServiceUtil
				.getSensorTypeWithId(detail.getSensorId());
		if (deviceType == SensorConstant.SENSOR_TYPE_AIR) {
			switch (type) {
			case UIConstant.SOLUTION_TEMPERATURE:
				return HomePagerUtil.getTemperatureState(detail
						.getAir().getTemperature());
			case UIConstant.SOLUTION_HUMIDITY:
				return HomePagerUtil.getHumidityState(detail
						.getAir().getHumidity());
			case UIConstant.SOLUTION_CO2:
				return HomePagerUtil.getCo2State(detail.getAir()
						.getCo2());
			case UIConstant.SOLUTION_PM25:
				return HomePagerUtil.getPm25State(detail
						.getAir().getPm25());
			case UIConstant.SOLUTION_VOC:
				return HomePagerUtil.getVocState(detail.getAir()
						.getVoc());
			}
		} else {
			switch (type) {
			case UIConstant.SOLUTION_CO:
				return HomePagerUtil.getCOState(detail.getGas()
						.getCo());
			case UIConstant.SOLUTION_CH4:
				return HomePagerUtil.getCH4State(detail.getGas()
						.getCh4());
			}
		}
		return UIConstant.SOLUTION_NULL;
	}
}
