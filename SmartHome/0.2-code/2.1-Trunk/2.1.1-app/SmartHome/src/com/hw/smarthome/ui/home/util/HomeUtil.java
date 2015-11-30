package com.hw.smarthome.ui.home.util;

import java.util.List;

import android.content.Context;
import android.view.View;

import com.hw.smarthome.po.SensorAirDetail;
import com.hw.smarthome.po.SensorCtrlDetail;
import com.hw.smarthome.po.SensorDetail;
import com.hw.smarthome.po.SensorDetailList;
import com.hw.smarthome.po.SensorGasDetail;
import com.hw.smarthome.server.deal.DealWithHome;
import com.hw.smarthome.server.deal.DealWithScene;
import com.hw.smarthome.server.deal.DealWithSensor;
import com.hw.smarthome.service.util.SmartHomeServiceUtil;
import com.hw.smarthome.ui.home.adapter.homeup.InitDatas;
import com.hw.smarthome.ui.home.adapter.util.RealTimeUtil;
import com.hw.smarthome.ui.home.po.HomeAirHistory;
import com.hw.smarthome.ui.home.po.HomeGasHistory;
import com.hw.smarthome.ui.home.po.HomeHistoryList;
import com.hw.smarthome.ui.sensor.constant.SensorConstant;
import com.hw.util.DateUtils;
import com.hw.util.Ln;

/**
 * @author 曾凡
 * @time 2014年7月11日 上午9:01:26
 */
public class HomeUtil {

	/**
	 * 获取传感器实时数据列表
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月1日 下午5:25:53
	 */
	public static List<SensorDetail> getSensorDetails(
			Context context) {
		SensorDetailList detailList = DealWithHome
				.getDetail(context);
		List<SensorDetail> list = null;
		if (detailList != null) {
			list = detailList.getSensorList();
		}
		return list;
	}

	/**
	 * 获取传感器实时数据列表
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年7月1日 下午5:25:53
	 */
	public static SensorDetail getSensorDetail(Context context,
			SensorDetail equip) {
		return getSensorDetail(getSensorDetails(context), equip);
	}

	public static SensorDetail getSensorDetail(Context context,
			String sensorId) {
		SensorDetail equip = new SensorDetail();
		equip.setSensorId(sensorId);
		return getSensorDetail(getSensorDetails(context), equip);
	}

	/**
	 * 匹配设备列表和查询的实时明细列表，并返回对应的空气质量信息
	 * 
	 * @author 曾凡
	 * @param detailList
	 *            [SH01_02_02_01] 查看设备实时数据
	 * @param equip
	 *            [SH01_01_01_03] 查看已发现设备
	 * @return
	 * @time 2014年7月11日 上午9:05:10
	 */
	public static SensorAirDetail getAirSensorDetail(
			List<SensorDetail> detailList, SensorDetail equip) {
		SensorDetail detail = getSensorDetail(detailList, equip);
		int type = 0;
		if (detail != null) {
			/* 获取到当前传感器的类型 */
			type = SmartHomeServiceUtil
					.getSensorTypeWithId(detail.getSensorId());
			/** 空气质量设备类型 */
			if (type == SensorConstant.SENSOR_TYPE_AIR) {
				return detail.getAir();
			}
		}
		return null;
	}

	/**
	 * 匹配设备列表和查询的实时明细列表，并返回对应的燃气信息
	 * 
	 * @author 曾凡
	 * @param detailList
	 *            [SH01_02_02_01] 查看设备实时数据
	 * @param equip
	 *            [SH01_01_01_03] 查看已发现设备
	 * @return
	 * @time 2014年7月11日 上午9:05:10
	 */
	public static SensorGasDetail getGasSensorDetail(
			List<SensorDetail> detailList, SensorDetail equip) {
		SensorDetail detail = getSensorDetail(detailList, equip);
		int type = 0;
		if (detail != null) {
			/* 获取到当前传感器的类型 */
			type = SmartHomeServiceUtil
					.getSensorTypeWithId(detail.getSensorId());
			/** 空气质量设备类型 */
			if (type == SensorConstant.SENSOR_TYPE_GAS) {
				return detail.getGas();
			}
		}
		return null;
	}

	/**
	 * 对比SensorId并返回传感器的明细信息
	 * 
	 * @author 曾凡
	 * @param detailList
	 *            [SH01_02_02_01] 查看设备实时数据
	 * @param equip
	 *            [SH01_01_01_03] 查看已发现设备
	 * @return
	 * @time 2014年7月11日 上午9:07:28
	 */
	private static SensorDetail getSensorDetail(
			List<SensorDetail> detailList, SensorDetail equip) {
		try {
			if (detailList == null) {
				Ln.w("在本地缓存里没有发现[SH01_02_02_01]的实时数据");
				return null;
			}
			if (equip != null) {
				for (SensorDetail detail : detailList) {
					if (equip.getSensorId().equals(
							detail.getSensorId())) {
						return detail;
					}
				}
			}
		} catch (Exception e) {
			Ln.e(e, "查看设备实时数据");
		}
		return null;
	}

	/**
	 * 获取更新时间
	 * 
	 * @author 曾凡
	 * @return
	 * @time 2014年8月5日 上午10:37:46
	 */
	@SuppressWarnings("finally")
	public static String getUpdateTime(Context context,
			SensorDetail detail) {
		/* 刷新更新时间 */
		String date = "";
		try {
			detail = getSensorDetail(context, detail);
			if (detail != null) {
				SensorAirDetail air = detail.getAir();
				SensorGasDetail gas = detail.getGas();
				SensorCtrlDetail ctrl = detail.getCtrl();
				/* 判断空气质量的内容 */
				if (air != null && air.getCreateTime() != null) {
					date += DateUtils.getDaysFromNow(air
							.getCreateTime());
					// date += air.getCreateTime().substring(11,
					// air.getCreateTime().length());
				} else if (gas != null
						&& gas.getCreateTime() != null) {
					date += DateUtils.getDaysFromNow(gas
							.getCreateTime());
					// date += gas.getCreateTime().substring(11,
					// gas.getCreateTime().length());
				} else if (ctrl != null
						&& ctrl.getCreateTime() != null) {
					date += DateUtils.getDaysFromNow(ctrl
							.getCreateTime());
					// date += gas.getCreateTime().substring(11,
					// gas.getCreateTime().length());
				}
			} else {
				date = "无内容更新";
			}
		} catch (Exception e) {
			Ln.e(e, "更新时间异常");
		} finally {
			if (date != null
					&& (date.contains("1900-01-01") || date
							.contains("01 01:01:01"))) {
				date = "传感器未联网";
			}
			return "更新时间:" + date;
		}
	}

	/**
	 * AQI算法，用来获取AQI
	 * 
	 * @author 曾凡
	 * @param sensorList
	 *            传感器实时明细列表
	 * @return
	 * @time 2014年8月1日 下午1:17:36
	 */
	@SuppressWarnings("finally")
	public static String getAvgAQI(
			List<SensorDetail> sensorList, View parentView) {
		int avgAqi = 0;
		try {
			if (sensorList == null || sensorList.size() == 0) {
				return "没有传感器，无法获取具体的空气质量值";
			}
			int[] aqis = new int[sensorList.size()];
			SensorDetail detail = null;
			for (int i = 0; i < sensorList.size(); i++) {
				detail = sensorList.get(i);
				aqis[i] = getAQI(detail, parentView);
			}
			int countSize = sensorList.size();
			for (int tempAqi : aqis) {
				if (tempAqi == 0) {
					countSize--;
				}
				avgAqi += tempAqi;
			}

			if (avgAqi != 0) {
				avgAqi = avgAqi / countSize;
			}
		} catch (Exception e) {
			Ln.e(e, "获取AQI指数异常");
		} finally {
			if (avgAqi == 0) {
				return "无";
			} else {
				return avgAqi + "";
			}
		}

	}

	public static int getAQI(SensorDetail detail, View parentView) {
		String sensorId = null;
		SensorAirDetail air = detail.getAir();
		SensorAirDetail gas = detail.getAir();
		/* 判断空气质量的内容 */
		if (air != null) {
			sensorId = air.getSensorId();
			if (sensorId != null && !"".equals(sensorId)) {
				int pm25 = air.getPm25() != null
						&& !"".equals(air.getPm25()) ? ((int) (Double
						.parseDouble(air.getPm25()))) : 0;
				return pm25;
			}
		}
		if (gas != null) {
			// parentView.findViewById(R.id.uiHomeRankingLayout)
			// .setVisibility(View.GONE);
			// if (parentView.findViewById(R.id.uiHomeRankingLayout)!=null) {
			// parentView.findViewById(R.id.uiHomeRankingLayout)
			// .setVisibility(View.GONE);
			// }

			// parentView.findViewById(R.id.uiHomeAQILayout)
			// .setVisibility(View.INVISIBLE);
		}
		return 0;
	}

	public static int getCh4(SensorDetail detail) {
		SensorGasDetail gas = detail.getGas();
		if (gas != null) {
			int ch4 = gas.getCh4() != null
					&& !"".equals(gas.getCh4()) ? ((int) (Double
					.parseDouble(gas.getCh4()))) : 0;
			return ch4;
		}
		return 0;
	}

	public static int getCO(SensorDetail detail) {
		SensorGasDetail gas = detail.getGas();
		if (gas != null) {
			int co = gas.getCo() != null
					&& !"".equals(gas.getCo()) ? ((int) (Double
					.parseDouble(gas.getCo()))) : 0;
			return co;
		}
		return 0;
	}

	public static HomeAirHistory getAirHistoryData(
			Context context, String sensorId) {
		/* 获取历史参数信息 */
		HomeHistoryList historyList = getHomeHistoryList(
				context, sensorId);
		/* 当前设备的类型 */
		int type = SmartHomeServiceUtil
				.getSensorTypeWithId(sensorId);
		if (historyList != null) {
			List<HomeAirHistory> airSensors = historyList
					.getAirSensors();
			/* 遍历缓存中的历史，并将页面初始化 */
			if (airSensors != null && airSensors.size() > 0
					&& type == SensorConstant.SENSOR_TYPE_AIR) {
				for (HomeAirHistory airHistory : airSensors) {
					if (airHistory.getSensorId() == null
							|| sensorId == null) {
						break;
					}
					if (airHistory.getSensorId()
							.equals(sensorId)) {
						return airHistory;
					}
				}
			}
		}
		return null;
	}

	public static HomeGasHistory getGasHistoryData(
			Context context, String sensorId) {
		/* 获取历史参数信息 */
		HomeHistoryList historyList = getHomeHistoryList(
				context, sensorId);
		/* 当前设备的类型 */
		int type = SmartHomeServiceUtil
				.getSensorTypeWithId(sensorId);
		if (historyList != null) {
			List<HomeGasHistory> gasSensors = historyList
					.getGasSensors();
			/* 遍历缓存中的历史，并将页面初始化 */
			if (gasSensors != null && gasSensors.size() > 0
					&& type == SensorConstant.SENSOR_TYPE_GAS) {
				for (HomeGasHistory gasHistory : gasSensors) {
					if (gasHistory.getSensorId() == null
							|| sensorId == null) {
						break;
					}
					if (gasHistory.getSensorId()
							.equals(sensorId)) {
						return gasHistory;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取历史列表
	 * 
	 * @author 曾凡
	 * @param context
	 * @param sensorId
	 * @return
	 * @time 2015年2月25日 下午6:58:30
	 */
	public static HomeHistoryList getHomeHistoryList(
			Context context, String sensorId) {
		/* 获取历史参数信息 */
		HomeHistoryList historyList = null;
		if (!InitDatas.getHistoryType().containsKey(sensorId)) {
			InitDatas.getHistoryType().put(sensorId,
					InitDatas.HOUR_24);
		}
		String type = InitDatas.getHistoryType().get(sensorId);
		if (type.equals(InitDatas.DAY_30)) {
			historyList = DealWithHome.get30DaysHistory(context);
		} else if (type.equals(InitDatas.DAY_7)) {
			historyList = DealWithHome.get7DaysHistory(context);
		} else if (type.equals(InitDatas.HOUR_24)) {
			historyList = DealWithHome
					.get24HoursHistory(context);
		}
		return historyList;
	}

	/**
	 * 退出
	 * 
	 * @author 闫威
	 * 
	 * @time 2014年7月21日 上午9:07:28
	 */
	public static void quit(Context context) {
		DealWithHome.clearDetail(context);
		DealWithHome.clearHistory(context);
		DealWithSensor.clearDetailList(context);
		RealTimeUtil.setCurrentDetail(null);
		DealWithScene.clearScenes(context);
		// DealWithAccount.clearAccount(context);
	}

}
