package com.hw.smarthome.mom.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.smarthome.daq.po.base.DataItemAFN07;
import com.hw.smarthome.mom.cache.MOMCache;
import com.hw.smarthome.mom.constant.HintConstant;
import com.hw.smarthome.mom.constant.SensorConstant;
import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHour;
import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHourFacadeLocal;
import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonth;
import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonthFacadeLocal;
import com.hw.smarthome.mom.po.DataItemAFN08MomPo;
import com.hw.smarthome.mom.service.MOMServiceLocal;
import com.hw.smarthome.mom.service.impl.handler.AFN07Handler;
import com.hw.smarthome.mom.service.util.MOMServiceUtil;
import com.hw.smarthome.mom.service.ws.AlarmWs;
import com.hw.util.DateUtil;
import com.hw.util.Ln;

@Stateless
public class MOMServiceImpl implements MOMServiceLocal {
	private static Logger log = Logger
			.getLogger(MOMServiceImpl.class);

	@EJB
	private ShHistoryHourFacadeLocal shHistoryHourFacade;
	@EJB
	private ShHistoryMonthFacadeLocal shHistoryMonthFacade;

	/**
	 * 设置实时和历史数据
	 * 
	 * @author 曾凡
	 * @param po
	 * @time 2015年1月27日 上午11:32:26
	 */
	public void setCurHisSensors(DataItemAFN08MomPo po) {
		try {
			StringBuilder key = new StringBuilder(
					po.getGprs_id());
			key.append("_").append(po.getMedia());
			Calendar calendar = Calendar.getInstance();
			po.setHourInDay(calendar.get(Calendar.HOUR_OF_DAY));
			po.setDayInMonth(calendar.get(Calendar.DAY_OF_MONTH));
			po.setReciveDate(calendar.getTime());
			MOMCache.getCurrentDevicesList().put(key.toString(),
					po);
			int status = Integer
					.valueOf((po.getStatus() == null || ""
							.equals(po.getStatus())) ? "0" : po
							.getStatus());
			if ((status == SensorConstant.SENSOR_STATUS_ALERT_HIGH_ALERT)
					|| (status == SensorConstant.SENSOR_STATUS_ALERT_LOW_ALERT)) {
				pushAlarm(po);
			}
			setMaxInHourList(key.toString(), po);
		} catch (Exception e) {
			log.error(HintConstant.SET_HISTORY_ERROR, e);
		}
	}

	/**
	 * 判断24小时最大值 po是新数据
	 * 
	 * @author 曾凡
	 * @param key
	 * @param po
	 * @time 2015年9月9日 上午10:31:41
	 */
	private void setMaxInHourList(String key,
			DataItemAFN08MomPo po) {
		try {
			if (MOMCache.getMaxInHourDevicesList().containsKey(
					key)) {
				DataItemAFN08MomPo cellPo = (DataItemAFN08MomPo) MOMCache
						.getMaxInHourDevicesList().get(key);
				if (cellPo.getHourInDay() != po.getHourInDay()) { // 如果不是同一个小时内的，直接插入
					setMaxInDayList(key, cellPo); // 如果是第二小时开始，则把上一个小时的最大值保存在day的最大值中
					updateSensorHourOfDay(cellPo); // 持久化历史数据
					MOMCache.getMaxInHourDevicesList().put(key,
							po);

					return;
				} else if (!DateUtil.isSameDay(
						po.getReciveDate(),
						cellPo.getReciveDate())) { // 如果小时一样，还要判断是否是同一天
					setMaxInDayList(key, cellPo); // 如果是第二小时开始，则把上一个小时的最大值保存在day的最大值中
					updateSensorHourOfDay(cellPo); // 持久化历史数据
					MOMCache.getMaxInHourDevicesList().put(key,
							po);

					return;
				}
				// 同一天同一个小时内，比较数值
				if (Float.parseFloat((po.getChanlvalue())) > Float
						.parseFloat(cellPo.getChanlvalue())) { // 是同一个小时内的，但是有最大值，则插入
					setMaxInDayList(key, po);
					MOMCache.getMaxInHourDevicesList().put(key,
							po);
					updateSensorHourOfDay(po);
				}
			} else {
				MOMCache.getMaxInHourDevicesList().put(key, po);
			}
		} catch (Exception e) {
			log.error(HintConstant.SET_HOURS_MAX_ERROR, e);
		}
	}

	/**
	 * 判断一天最大值
	 * 
	 * @author 曾凡
	 * @param key
	 * @param po
	 * @time 2015年9月9日 上午10:32:38
	 */
	private void setMaxInDayList(String key,
			DataItemAFN08MomPo po) {
		try {
			if (MOMCache.getMaxInDayDevicesList().containsKey(
					key)) {
				DataItemAFN08MomPo cellPo = (DataItemAFN08MomPo) MOMCache
						.getMaxInDayDevicesList().get(key);
				if (cellPo.getDayInMonth() != po.getDayInMonth()) {
					updateSensorDayOfMonth(cellPo);
					MOMCache.getMaxInDayDevicesList().put(key,
							po);

					return;
				} else if (!DateUtil.isSameDay(
						po.getReciveDate(),
						cellPo.getReciveDate())) {
					updateSensorDayOfMonth(cellPo);
					MOMCache.getMaxInDayDevicesList().put(key,
							po);

					return;
				}
				if (Float.parseFloat((po.getChanlvalue())) > Float
						.parseFloat(cellPo.getChanlvalue())) {
					MOMCache.getMaxInDayDevicesList().put(key,
							po);
					updateSensorDayOfMonth(po);
				}
			} else {
				MOMCache.getMaxInDayDevicesList().put(key, po);
			}
		} catch (Exception e) {
			log.error(HintConstant.SET_DAYS_MAX_ERROR, e);
		}
	}

	@Override
	public void setCurSensors(DataItemAFN08MomPo po) {

	}

	@Override
	public SensorDetail getCurSensors(String deviceId,
			String driverType, String driverName) {
		if ("4".equals(driverType)) {
			return getCtrlCurHisSensors(deviceId);
		} else {
			SensorDetail sensorDetail = new SensorDetail();
			try {
				MOMServiceUtil.setCurValues(sensorDetail,
						deviceId, driverType, driverName);
			} catch (Exception e) {
				log.error(HintConstant.GET_CUR_SENSORS_ERROR, e);
			}
			return sensorDetail;
		}
	}

	@Override
	public void pushAlarm(final DataItemAFN08MomPo po) {
		try {
			new AlarmWs(po).start();
		} catch (Exception e) {

			Ln.e(MOMServiceImpl.class, HintConstant.SEND_WS_FAIL
					+ ":" + po.getGprs_id(), e);
		}
	}

	@Override
	public void updateSensorHourOfDay(DataItemAFN08MomPo po) {
		// 持久化小时数据
		// 保存或更新数据库SH_HISTORY_HOUR
		ShHistoryHour hpo = new ShHistoryHour();
		try {
			List<ShHistoryHour> list = shHistoryHourFacade
					.findByProperty("ma002", po.getGprs_id(),
							"ma004", po.getMedia(), 0);
			int count = 6 + po.getHourInDay() * 2;
			if (list == null || list.size() < 1) {
				hpo.setMa001(UUID.randomUUID().toString());
				hpo.setMa002(po.getGprs_id());
				hpo.setMa004(po.getMedia());

				MOMServiceUtil.reflectSetHourValue(hpo,
						(count - 1), po.getChanlvalue());
				MOMServiceUtil.reflectSetHourTime(hpo, count,
						po.getReciveDate());

				shHistoryHourFacade.save(hpo);
			} else {
				hpo = list.get(0);
				MOMServiceUtil.reflectSetHourValue(hpo,
						(count - 1), po.getChanlvalue());
				MOMServiceUtil.reflectSetHourTime(hpo, count,
						po.getReciveDate());
				shHistoryHourFacade.update(hpo);
			}
		} catch (Exception e) {
			log.error(HintConstant.SET_HOURS_ERROR, e);
		}

	}

	@Override
	public ShHistoryHour getSensorHourOfDay(String deviceId,
			String media, Date nowDate, int hours) {

		ShHistoryHour hpo = new ShHistoryHour();
		try {
			// 从数据取字段一个一个比较，调用Dateutil.isLastOneDay
			// 判断是否为过去24小时,设响应的属性值，如果不是过去24小时，则为0
			List<ShHistoryHour> list = shHistoryHourFacade
					.findByProperty("ma002", deviceId, "ma004",
							media, null);
			Date curDay = null;
			if (list.size() == 1) {
				hpo = list.get(0);
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				for (int i = 6; i <= 52; i += 2) {
					curDay = MOMServiceUtil.reflectGetHourTime(
							hpo, i);

					if (curDay == null
							|| !DateUtil.isLastOneDay(curDay,
									nowDate, hours)) {
						MOMServiceUtil.reflectSetHourValue(hpo,
								i - 1, "");
					}

				}
			}
		} catch (Exception e) {
			log.error(HintConstant.SET_HOURS_ERROR, e);
		}
		return hpo;
	}

	@Override
	public void updateSensorDayOfMonth(DataItemAFN08MomPo po) {
		// 持久化每天数据
		// 保存或更新数据库SH_HISTORY_MONTH
		ShHistoryMonth hpo = new ShHistoryMonth();
		try {
			List<ShHistoryMonth> list = shHistoryMonthFacade
					.findByProperty("ma002", po.getGprs_id(),
							"ma004", po.getMedia(), null);
			int count = 4 + po.getDayInMonth() * 2;
			if (list == null || list.size() < 1) {
				hpo.setMa001(UUID.randomUUID().toString());
				hpo.setMa002(po.getGprs_id());
				hpo.setMa004(po.getMedia());

				MOMServiceUtil.reflectSetMonthValue(hpo,
						(count - 1), po.getChanlvalue());
				MOMServiceUtil.reflectSetMonthTime(hpo, count,
						po.getReciveDate());
				shHistoryMonthFacade.save(hpo);
			} else {
				hpo = list.get(0);
				MOMServiceUtil.reflectSetMonthValue(hpo,
						(count - 1), po.getChanlvalue());
				MOMServiceUtil.reflectSetMonthTime(hpo, count,
						po.getReciveDate());
				shHistoryMonthFacade.update(hpo);
			}
		} catch (Exception e) {
			log.error(HintConstant.SET_DAYS_ERROR, e);
		}

	}

	@Override
	public ShHistoryMonth getSensorDayOfMonth(String deviceId,
			String media, Date nowDate, int days) {
		ShHistoryMonth hpo = null;
		try {
			// 从数据取字段一个一个比较，调用Dateutil.isLastManyDays
			// 判断是否为过去31天,设响应的属性值，如果不是过去31天，则为0
			List<ShHistoryMonth> list = shHistoryMonthFacade
					.findByProperty("ma002", deviceId, "ma004",
							media, null);
			Date curDay = null;
			if (list.size() == 1) {
				hpo = list.get(0);
				for (int i = 6; i <= 66; i += 2) {

					curDay = MOMServiceUtil.reflectGetMonthTime(
							hpo, i);
					if (curDay == null
							|| !DateUtil.isLastManyDays(curDay,
									nowDate, days)) {
						MOMServiceUtil.reflectSetMonthValue(hpo,
								i - 1, "");
					}

				}
			}
		} catch (Exception e) {
			log.error(HintConstant.GET_DAYS_ERROR, e);
		}
		return hpo;
	}

	@Override
	public void setCtrlCurHisSensors(DataItemAFN07 po) {
		new AFN07Handler().saveCtrlData(po);
	}

	@Override
	public SensorDetail getCtrlCurHisSensors(String deviceId) {
		return new AFN07Handler().queryCtrlData(deviceId);
	}
}
