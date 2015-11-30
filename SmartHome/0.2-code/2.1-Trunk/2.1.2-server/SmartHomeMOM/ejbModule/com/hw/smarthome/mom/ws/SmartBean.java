package com.hw.smarthome.mom.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.google.gson.Gson;
import com.hw.hwsafe.smart.pojo.SensorAirDetail;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.hwsafe.smart.pojo.SensorGasDetail;
import com.hw.smarthome.mom.constant.CloudConstants;
import com.hw.smarthome.mom.constant.HintConstant;
import com.hw.smarthome.mom.constant.SensorConstant;
import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHour;
import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonth;
import com.hw.smarthome.mom.po.DataItemAFN08MomPo;
import com.hw.smarthome.mom.service.MOMServiceLocal;
import com.hw.smarthome.mom.service.util.MOMServiceUtil;
import com.hw.util.DateUtil;
import com.hw.util.Ln;

@WebService
// 必须有 提供接口的实现方法
@Stateless
// 服务器端设置成无状态，速度会快很多，所以一般都设成无状态
public class SmartBean implements ISmartService {
	{
		DOMConfigurator.configure("log4j.xml");
	}
	private static Logger log = Logger
			.getLogger(SmartBean.class);
	@EJB
	private MOMServiceLocal service;

	@Override
	public String retrieveCurStautsByGPRSId(String gprsId,
			String driverType, String driverName)
			throws Exception {
		SensorDetail sensorDetail = service.getCurSensors(
				gprsId, driverType, driverName);
		String res = (new Gson()).toJson(sensorDetail);
		log.debug("返回服务端实时数据:" + res);
		return res;
	}

	@Override
	public String retrieveHisStautsByGPRSId(String gprsId,
			String driverType, int pastHoursOrDays, int type)
			throws Exception {
		List<SensorDetail> sensorDetailList = new ArrayList<SensorDetail>();

		switch (type) {
		case SensorConstant.HISTORY_TYPE_HOUR: {
			ShHistoryHour po_co = null;
			ShHistoryHour po_ch4 = null;
			if ("1".equals(driverType)) {
				po_co = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_CO,
						new Date(), pastHoursOrDays);
				po_ch4 = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_CH4,
						new Date(), pastHoursOrDays);
			}
			ShHistoryHour po_voc = null;
			ShHistoryHour po_tmp = null;
			ShHistoryHour po_hum = null;
			ShHistoryHour po_co2 = null;
			ShHistoryHour po_pm = null;
			ShHistoryHour po_c6h6 = null;
			ShHistoryHour po_ch2o = null;
			if ("2".equals(driverType)) {
				po_voc = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_VOC,
						new Date(), pastHoursOrDays);
				po_tmp = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_TEMPERATURE,
						new Date(), pastHoursOrDays);
				po_hum = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_HUMIDITY,
						new Date(), pastHoursOrDays);
				po_co2 = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_CO2,
						new Date(), pastHoursOrDays);
				po_pm = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_PM25,
						new Date(), pastHoursOrDays);
				po_c6h6 = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_C6H6,
						new Date(), pastHoursOrDays);
				po_ch2o = service.getSensorHourOfDay(gprsId,
						CloudConstants.MEDIA_TYPE_CH2O,
						new Date(), pastHoursOrDays);
			}
			for (int i = pastHoursOrDays; i > 0; i--) {
				SensorDetail sensorDetail = new SensorDetail();
				sensorDetail.setSensorId(gprsId);
				if ("1".equals(driverType)) {
					SensorGasDetail s1 = new SensorGasDetail();
					s1.setSensorId(gprsId);
					s1.setName("可燃气体");
					s1.setSwitchStatus("开");

					// ShHistoryHour po_co = service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_CO, new Date(),
					// pastHoursOrDays);
					Map<String, String> map;
					if (po_co != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_co, i);
						s1.setCo(map.get("data"));
					} else {
						s1.setCo("");
					}

					// ShHistoryHour po_ch4 = service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_CH4, new Date(),
					// pastHoursOrDays);
					if (po_ch4 != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_ch4, i);
						s1.setCh4(map.get("data"));
						s1.setCreateTime(map.get("recive"));
					} else {
						s1.setCh4("");
					}
					sensorDetail.setGas(s1);
					sensorDetailList.add(sensorDetail);
				} else if ("2".equals(driverType)) {
					SensorAirDetail s1 = new SensorAirDetail();
					s1.setSensorId(gprsId);
					s1.setName("空气质量");

					// ShHistoryHour po_voc = service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_VOC, new Date(),
					// pastHoursOrDays);
					Map<String, String> map;
					if (po_voc != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_voc, i);
						s1.setVoc(map.get("data"));
					} else {
						s1.setVoc("");
					}

					// ShHistoryHour po_tmp = service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_TEMPERATURE, new Date(),
					// pastHoursOrDays);
					if (po_tmp != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_tmp, i);
						s1.setTemperature(map.get("data"));
					} else {
						s1.setTemperature("");
					}

					// ShHistoryHour po_hum = service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_HUMIDITY, new Date(),
					// pastHoursOrDays);
					if (po_hum != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_hum, i);
						s1.setHumidity(map.get("data"));
						s1.setCreateTime(map.get("recive"));
					} else {
						s1.setHumidity("");
					}

					// ShHistoryHour po_co2 = service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_CO2, new Date(),
					// pastHoursOrDays);
					if (po_co2 != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_co2, i);
						s1.setCo2(map.get("data"));
					} else {
						s1.setCo2("");
					}

					// ShHistoryHour po_pm = service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_PM25, new Date(),
					// pastHoursOrDays);
					if (po_pm != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_pm, i);
						s1.setPm25(map.get("data"));
					} else {
						s1.setPm25("");
					}

					// ShHistoryHour po_c6h6 =
					// service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_C6H6, new Date(),
					// pastHoursOrDays);
					if (po_c6h6 != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_c6h6, i);
						s1.setC6h6(map.get("data"));
					} else {
						s1.setC6h6("");
					}

					// ShHistoryHour po_ch2o =
					// service.getSensorHourOfDay(gprsId,
					// CloudConstants.MEDIA_TYPE_CH2O, new Date(),
					// pastHoursOrDays);
					if (po_ch2o != null) {
						map = MOMServiceUtil.getDataFromHisHour(
								po_ch2o, i);
						s1.setCh2o(map.get("data"));
					} else {
						s1.setCh2o("");
					}

					sensorDetail.setAir(s1);
					sensorDetailList.add(sensorDetail);
				}
			}

		}
			break;
		case SensorConstant.HISTORY_TYPE_DAY: {
			ShHistoryMonth po_co = null;
			ShHistoryMonth po_ch4 = null;
			if ("1".equals(driverType)) {
				po_co = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_CO,
						new Date(), pastHoursOrDays);
				po_ch4 = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_CH4,
						new Date(), pastHoursOrDays);
			}
			ShHistoryMonth po_voc = null;
			ShHistoryMonth po_tmp = null;
			ShHistoryMonth po_hum = null;
			ShHistoryMonth po_co2 = null;
			ShHistoryMonth po_pm = null;
			ShHistoryMonth po_c6h6 = null;
			ShHistoryMonth po_ch2o = null;
			if ("2".equals(driverType)) {
				po_voc = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_VOC,
						new Date(), pastHoursOrDays);
				po_tmp = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_TEMPERATURE,
						new Date(), pastHoursOrDays);
				po_hum = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_HUMIDITY,
						new Date(), pastHoursOrDays);
				po_co2 = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_CO2,
						new Date(), pastHoursOrDays);
				po_pm = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_PM25,
						new Date(), pastHoursOrDays);
				po_c6h6 = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_C6H6,
						new Date(), pastHoursOrDays);
				po_ch2o = service.getSensorDayOfMonth(gprsId,
						CloudConstants.MEDIA_TYPE_CH2O,
						new Date(), pastHoursOrDays);
			}
			for (int i = pastHoursOrDays; i > 0; i--) {
				SensorDetail sensorDetail = new SensorDetail();
				sensorDetail.setSensorId(gprsId);
				if ("1".equals(driverType)) {
					SensorGasDetail s1 = new SensorGasDetail();
					s1.setSensorId(gprsId);
					s1.setName("可燃气体");
					s1.setSwitchStatus("开");

					// ShHistoryMonth po_co =
					// service.getSensorDayOfMonth(gprsId,
					// CloudConstants.MEDIA_TYPE_CO, new Date(),
					// pastHoursOrDays);
					Map<String, String> map;
					if (po_co != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_co, i);
						s1.setCo(map.get("data"));
					} else {
						s1.setCo("");
					}

					// ShHistoryMonth po_ch4 =
					// service.getSensorDayOfMonth(gprsId,
					// CloudConstants.MEDIA_TYPE_CH4, new Date(),
					// pastHoursOrDays);
					if (po_ch4 != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_ch4, i);
						s1.setCh4(map.get("data"));
					} else {
						s1.setCh4("");
					}
					s1.setCreateTime(DateUtil.getStrDateBefore(
							new Date(), i));
					sensorDetail.setGas(s1);
					sensorDetailList.add(sensorDetail);

				} else if ("2".equals(driverType)) {
					SensorAirDetail s1 = new SensorAirDetail();
					s1.setSensorId(gprsId);
					s1.setName("空气质量");

					// ShHistoryMonth po_voc =
					// service.getSensorDayOfMonth(gprsId,
					// CloudConstants.MEDIA_TYPE_VOC, new Date(),
					// pastHoursOrDays);
					Map<String, String> map;
					if (po_voc != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_voc, i);
						s1.setVoc(map.get("data"));
					} else {
						s1.setVoc("");
					}

					// ShHistoryMonth po_tmp =
					// service.getSensorDayOfMonth(gprsId,
					// CloudConstants.MEDIA_TYPE_TEMPERATURE, new Date(),
					// pastHoursOrDays);
					if (po_tmp != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_tmp, i);
						s1.setTemperature(map.get("data"));
						s1.setCreateTime(map.get("recive"));
					} else {
						s1.setTemperature("");
					}

					// ShHistoryMonth po_hum =
					// service.getSensorDayOfMonth(gprsId,
					// CloudConstants.MEDIA_TYPE_HUMIDITY, new Date(),
					// pastHoursOrDays);
					if (po_hum != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_hum, i);
						s1.setHumidity(map.get("data"));
					} else {
						s1.setHumidity("");
					}

					// ShHistoryMonth po_co2 =
					// service.getSensorDayOfMonth(gprsId,
					// CloudConstants.MEDIA_TYPE_CO2, new Date(),
					// pastHoursOrDays);
					if (po_co2 != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_co2, i);
						s1.setCo2(map.get("data"));
					} else {
						s1.setCo2("");
					}

					// ShHistoryMonth po_pm =
					// service.getSensorDayOfMonth(gprsId,
					// CloudConstants.MEDIA_TYPE_PM25, new Date(),
					// pastHoursOrDays);
					if (po_pm != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_pm, i);
						s1.setPm25(map.get("data"));
					} else {
						s1.setPm25("");
					}

					// ShHistoryMonth po_c6h6 = service.getSensorDayOfMonth(
					// gprsId, CloudConstants.MEDIA_TYPE_C6H6, new Date(),
					// pastHoursOrDays);
					if (po_c6h6 != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_c6h6, i);
						s1.setC6h6(map.get("data"));
					} else {
						s1.setC6h6("");
					}

					// ShHistoryMonth po_ch2o = service.getSensorDayOfMonth(
					// gprsId, CloudConstants.MEDIA_TYPE_CH2O, new Date(),
					// pastHoursOrDays);
					if (po_ch2o != null) {
						map = MOMServiceUtil
								.getDataFromHisMonth(po_ch2o, i);
						s1.setCh2o(map.get("data"));
					} else {
						s1.setCh2o("");
					}

					s1.setCreateTime(DateUtil.getStrDateBefore(
							new Date(), i));
					sensorDetail.setAir(s1);
					sensorDetailList.add(sensorDetail);
				}
			}
		}
		}
		String res = JSONArray.fromObject(sensorDetailList)
				.toString();
		log.debug("返回服务端历史数据:" + res);
		return res;
	}

	@Override
	public void setRealtimeList(String sensorDetail) {
		if (log.isDebugEnabled()) {
			log.debug("使用WS返回服务端：" + sensorDetail);
		}
		JSONObject json = null;
		try {

			json = JSONObject.fromObject(sensorDetail);// new
														// JSONObject(sensorDetail);//
														// JSONObject.parseObject(sensorDetail);
			JSONArray jarray = json.getJSONArray("data");

			for (int i = 0; i < jarray.size(); i++) {
				Gson s = new Gson();
				DataItemAFN08MomPo dataItemAFN08MomPo = s
						.fromJson(jarray.getString(i),
								DataItemAFN08MomPo.class);
				service.setCurHisSensors(dataItemAFN08MomPo);
			}
		} catch (Exception e) {
			Ln.e(this.getClass(), HintConstant.WS_RECEIVE_WARN
					+ ":" + sensorDetail, e);
		}
	}

	@Override
	public String retrieveHisStauts(String gprsId, String media,
			int pastHoursOrDays, int type) throws Exception {
		if (type == 1) {
			ShHistoryHour po_hour = service.getSensorHourOfDay(
					gprsId, media, new Date(), pastHoursOrDays);
			if (po_hour == null)
				po_hour = new ShHistoryHour();
			return new Gson().toJson(po_hour);// .fromObject(po_hour).toString();
		} else if (type == 2) {

			ShHistoryMonth po_day = service.getSensorDayOfMonth(
					gprsId, media, new Date(), pastHoursOrDays);
			if (po_day == null) {
				po_day = new ShHistoryMonth();
			}
			return JSONObject.fromObject(po_day).toString();
		} else {
			return "";
		}

	}
}
