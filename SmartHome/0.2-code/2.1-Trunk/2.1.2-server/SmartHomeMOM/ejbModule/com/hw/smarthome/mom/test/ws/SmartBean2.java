package com.hw.smarthome.mom.test.ws;
//package com.hw.smarthome.mom.ws;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//import java.rmi.RemoteException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.UUID;
//
//import javax.ejb.EJB;
//import javax.xml.datatype.XMLGregorianCalendar;
//import javax.xml.rpc.ServiceException;
//
//import org.apache.axis.client.Call;
//
//import com.google.gson.Gson;
//import com.hw.hwsafe.smart.pojo.SensorAirDetail;
//import com.hw.hwsafe.smart.pojo.SensorDetail;
//import com.hw.hwsafe.smart.pojo.SensorGasDetail;
//import com.hw.smarthome.mom.cache.MOMCache;
//import com.hw.smarthome.mom.constant.CloudConstants;
//import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHour;
//import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHourFacadeLocal;
//import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonth;
//import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonthFacadeLocal;
//import com.hw.smarthome.mom.po.DataItemAFN08MomPo;
//import com.hw.util.DateUtil;
//import com.hw.util.json.JSONArray;
//import com.hw.util.json.JSONObject;
//
////@WebService
//// 必须有 提供接口的实现方法
////@Stateless
//// 服务器端设置成无状态，速度会快很多，所以一般都设成无状态
//public class SmartBean2 implements ISmartService {
//	// public static List<Map<String,SensorDetail>> currentDevicesList = new
//	// ArrayList<Map<String,SensorDetail>>();
//	// @XmlJavaTypeAdapter(MapAdapter.class)
//
//	@EJB
//	private ShHistoryHourFacadeLocal shHistoryHourFacade;
//	@EJB
//	private ShHistoryMonthFacadeLocal shHistoryMonthFacade;
//
//	@Override
//	public String retrieveCurStautsByGPRSId(String gprsId,
//			String driverType, String driverName)
//			throws Exception {
//		SensorDetail sensorDetail = new SensorDetail();
//		sensorDetail.setSensorId(gprsId);
//		if ("1".equals(driverType)) {
//			SensorGasDetail s1 = new SensorGasDetail();
//			s1.setSensorId(gprsId);
//			s1.setName(driverName);
//			s1.setSwitchStatus("开");
//			s1.setCo(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_CO));
//			s1.setCh4(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_CH4));
//			String coTime = retrieveDetectorReceiveTime(gprsId,
//					CloudConstants.MEDIA_TYPE_CO);
//			String ch4Time = retrieveDetectorReceiveTime(gprsId,
//					CloudConstants.MEDIA_TYPE_CH4);
//			s1.setCreateTime(retrieveGasTime(coTime, ch4Time));
//			sensorDetail.setGas(s1);
//		} else if ("2".equals(driverType)) {
//			SensorAirDetail sa1 = new SensorAirDetail();
//			sa1.setSensorId(gprsId);
//			sa1.setName(driverName);
//			sa1.setVoc(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_VOC));
//			sa1.setPm25(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_PM25));
//			sa1.setTemperature(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_TEMPERATURE));
//			sa1.setHumidity(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_HUMIDITY));
//			sa1.setCo2(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_CO2));
//			sa1.setCh2o(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_CH2O));
//			sa1.setC6h6(retrieveDetectorValue(gprsId,
//					CloudConstants.MEDIA_TYPE_C6H6));
//			String temTime = retrieveDetectorReceiveTime(gprsId,
//					driverType);
//			String humTime = retrieveDetectorReceiveTime(gprsId,
//					driverType);
//			String pm25Time = retrieveDetectorReceiveTime(
//					gprsId, driverType);
//			String co2Time = retrieveDetectorReceiveTime(gprsId,
//					driverType);
//			sa1.setCreateTime(retrieveAirTime(temTime, humTime,
//					pm25Time, co2Time));
//			sensorDetail.setAir(sa1);
//		}
//		return (new Gson()).toJson(sensorDetail);// JSONObject.toJSONString(sensorDetail);
//	}
//
//	@Override
//	public String retrieveHisStautsByGPRSId(String gprsId,
//			String media, int pastHoursOrDays, int type)
//			throws Exception {
//		if (type == 1) { // 过去几个小时
//
//			ShHistoryHour po = getHistoryInHour(gprsId, media,
//					new Date(), pastHoursOrDays);
//
//			return (new Gson()).toJson(po);// JSONObject.toJSONString(po);
//
//		}
//		if (type == 2) { // 过去几天
//
//			ShHistoryMonth po = getHistoryInMonth(gprsId, media,
//					new Date(), pastHoursOrDays);
//			return (new Gson()).toJson(po);// JSONObject.toJSONString(po);
//		}
//		return null;
//
//	}
//
//	/**
//	 * 根据WIFI模块ID(或GPRSID)、探测介质和日期获取该传感器的最大值
//	 */
//	// private String retrieveMaxValue(String gprsId,String media,String
//	// dateTime){
//	// //读数据库取内容
//	// return null;
//	// }
//
//	/**
//	 * 根据WIFI模块ID(或GPRSID)和探测介质获取该传感器当前的数值
//	 */
//	private String retrieveDetectorValue(String gprsId,
//			String media) {
//		String key = gprsId + "_" + media;
//		if (MOMCache.getCurrentDevicesList().containsKey(key)) {
//			DataItemAFN08MomPo DataItemAFN08MomPoPO = MOMCache
//					.getCurrentDevicesList().get(key);
//			return DataItemAFN08MomPoPO.getChanlvalue();
//		}
//		return null;
//	}
//
//	/**
//	 * 根据WIFI模块ID(或GPRSID)和探测介质获取该传感器当前的数值
//	 */
//	private String retrieveDetectorReceiveTime(String gprsId,
//			String media) {
//		SimpleDateFormat formater = new SimpleDateFormat(
//				"yy-MM-dd HH:mm:ss");
//		String key = gprsId + "_" + media;
//		if (MOMCache.getCurrentDevicesList().containsKey(key)) {
//			DataItemAFN08MomPo DataItemAFN08MomPoPO = MOMCache
//					.getCurrentDevicesList().get(key);
//			return formater.format(DataItemAFN08MomPoPO
//					.getReciveDate());
//		}
//		return null;
//
//	}
//
//	/**
//	 * 当前日期的前七天日期
//	 */
//	private String[] get7DaysBefore() {
//		String[] days = new String[7];
//		Calendar now = Calendar.getInstance();
//		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
//		String tempDay = null;
//		for (int i = 0; i < days.length; i++) {
//			now.add(Calendar.DATE, -1);
//			tempDay = df.format(now.getTime());
//			days[i] = tempDay;
//		}
//		return days;
//	}
//
//	private String retrieveGasTime(String coTime, String ch4Time)
//			throws Exception {
//		String callbackTime = null;
//		SimpleDateFormat sf = new SimpleDateFormat(
//				"yyyyMMddHHmmss");
//		SimpleDateFormat sf2 = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		if (coTime == null && ch4Time == null) {
//			callbackTime = "19000101010101";
//		} else if (coTime != null && ch4Time == null) {
//			callbackTime = coTime;
//		} else if (coTime == null && ch4Time != null) {
//			callbackTime = ch4Time;
//		} else if (!coTime.isEmpty() && !ch4Time.isEmpty()) {
//			Date coDateTime = sf.parse(coTime);
//			Date ch4DateTime = sf.parse(ch4Time);
//			if (coDateTime.after(ch4DateTime)) {
//				callbackTime = coTime;
//			} else {
//				callbackTime = ch4Time;
//			}
//		}
//		return sf2.format(sf.parse(callbackTime));
//	}
//
//	private String retrieveAirTime(String temTime,
//			String humTime, String pm25Time, String co2Time)
//			throws Exception {
//		String callbackTime = null;
//		SimpleDateFormat sf = new SimpleDateFormat(
//				"yyyyMMddHHmmss");
//		SimpleDateFormat sf2 = new SimpleDateFormat(
//				"yyyy-MM-dd HH:mm:ss");
//		if (temTime != null && !temTime.isEmpty()) {
//			callbackTime = temTime;
//		} else if (humTime != null && !humTime.isEmpty()) {
//			callbackTime = temTime;
//		} else if (pm25Time != null && !pm25Time.isEmpty()) {
//			callbackTime = pm25Time;
//		} else if (co2Time != null && !co2Time.isEmpty()) {
//			callbackTime = co2Time;
//		} else {
//			callbackTime = "19000101010101";
//		}
//		return sf2.format(sf.parse(callbackTime));
//	}
//
//	@Override
//	public void setRealtimeList(String sensorDetail) {
//
//		JSONObject json;
//
//		try {
//
//			json = new JSONObject(sensorDetail);// JSONObject.parseObject(sensorDetail);
//
//			JSONArray jarray = json.getJSONArray("data");
//
//			for (int i = 0; i < jarray.length(); i++) {
//				Gson s = new Gson();
//				DataItemAFN08MomPo DataItemAFN08MomPo = s
//						.fromJson(jarray.getString(i),
//								DataItemAFN08MomPo.class);
//				String key = DataItemAFN08MomPo.getGprs_id()
//						+ "_" + DataItemAFN08MomPo.getMedia();
//				Calendar calendar = Calendar.getInstance();
//				DataItemAFN08MomPo.setHourInDay(calendar
//						.get(Calendar.HOUR_OF_DAY));
//				DataItemAFN08MomPo.setDayInMonth(calendar
//						.get(Calendar.DAY_OF_MONTH));
//				DataItemAFN08MomPo.setReciveDate(new Date());
//
//				MOMCache.getCurrentDevicesList().put(key,
//						DataItemAFN08MomPo);
//				if (DataItemAFN08MomPo.getStatus().equals("2")
//						|| DataItemAFN08MomPo.getStatus()
//								.equals("3")) {
//					saveAlarm(DataItemAFN08MomPo);
//				}
//				setMaxInHourList(key, DataItemAFN08MomPo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private void setMaxInHourList(String key,
//			DataItemAFN08MomPo po) { // 判断24小时最大值 po是新数据
//		if (MOMCache.getMaxInHourDevicesList().containsKey(key)) {
//			DataItemAFN08MomPo DataItemAFN08MomPoPo = (DataItemAFN08MomPo) MOMCache
//					.getMaxInHourDevicesList().get(key);
//			if (DataItemAFN08MomPoPo.getHourInDay() != po
//					.getHourInDay()) { // 如果不是同一个小时内的，直接插入
//				setMaxInDayList(key, DataItemAFN08MomPoPo); // 如果是第二小时开始，则把上一个小时的最大值保存在day的最大值中
//				saveHistoryInHour(DataItemAFN08MomPoPo); // 持久化历史数据
//				MOMCache.getMaxInHourDevicesList().put(key, po);
//
//				return;
//			} else if (!DateUtil.isSameDay(po.getReciveDate(),
//					DataItemAFN08MomPoPo.getReciveDate())) { // 如果小时一样，还要判断是否是同一天
//				setMaxInDayList(key, DataItemAFN08MomPoPo); // 如果是第二小时开始，则把上一个小时的最大值保存在day的最大值中
//				saveHistoryInHour(DataItemAFN08MomPoPo); // 持久化历史数据
//				MOMCache.getMaxInHourDevicesList().put(key, po);
//
//				return;
//			}
//			// 同一天同一个小时内，比较数值
//			if (Float.parseFloat((po.getChanlvalue())) > Float
//					.parseFloat(DataItemAFN08MomPoPo
//							.getChanlvalue())) { // 是同一个小时内的，但是有最大值，则插入
//				MOMCache.getMaxInHourDevicesList().put(key, po);
//			}
//		} else {
//			MOMCache.getMaxInHourDevicesList().put(key, po);
//		}
//
//	}
//
//	private void setMaxInDayList(String key,
//			DataItemAFN08MomPo po) { // 判断一天最大值
//		if (MOMCache.getMaxInDayDevicesList().containsKey(key)) {
//			DataItemAFN08MomPo DataItemAFN08MomPoPo = (DataItemAFN08MomPo) MOMCache
//					.getMaxInDayDevicesList().get(key);
//			if (DataItemAFN08MomPoPo.getDayInMonth() != po
//					.getDayInMonth()) {
//				saveHistoryInMonth(DataItemAFN08MomPoPo);
//				MOMCache.getMaxInDayDevicesList().put(key, po);
//
//				return;
//			} else if (!DateUtil.isSameDay(po.getReciveDate(),
//					DataItemAFN08MomPoPo.getReciveDate())) {
//				saveHistoryInMonth(DataItemAFN08MomPoPo);
//				MOMCache.getMaxInDayDevicesList().put(key, po);
//
//				return;
//			}
//			if (Float.parseFloat((po.getChanlvalue())) > Float
//					.parseFloat(DataItemAFN08MomPoPo
//							.getChanlvalue())) {
//				MOMCache.getMaxInDayDevicesList().put(key, po);
//			}
//		} else {
//			MOMCache.getMaxInDayDevicesList().put(key, po);
//		}
//
//	}
//
//	private void saveHistoryInHour(DataItemAFN08MomPo po) { // 持久化小时数据
//		// 保存或更新数据库SH_HISTORY_HOUR
//		ShHistoryHour hpo = new ShHistoryHour();
//		List<ShHistoryHour> list = shHistoryHourFacade
//				.findByProperty("ma002", po.getGprs_id(),
//						"ma004", po.getMedia(), 0);
//		if (list == null || list.size() < 1) {
//			hpo.setMa001(UUID.randomUUID().toString());
//			hpo.setMa002(po.getGprs_id());
//			hpo.setMa004(po.getMedia());
//			switch (po.getHourInDay()) {
//			case 0:
//				hpo.setMa005(po.getChanlvalue());
//				hpo.setMa006(new Date());
//				break;
//			case 1:
//				hpo.setMa007(po.getChanlvalue());
//				hpo.setMa008(new Date());
//				break;
//			case 2:
//				hpo.setMa009(po.getChanlvalue());
//				hpo.setMa010(new Date());
//				break;
//			case 3:
//				hpo.setMa011(po.getChanlvalue());
//				hpo.setMa012(new Date());
//				break;
//			case 4:
//				hpo.setMa013(po.getChanlvalue());
//				hpo.setMa014(new Date());
//				break;
//			case 5:
//				hpo.setMa015(po.getChanlvalue());
//				hpo.setMa016(new Date());
//				break;
//			case 6:
//				hpo.setMa017(po.getChanlvalue());
//				hpo.setMa018(new Date());
//				break;
//			case 7:
//				hpo.setMa019(po.getChanlvalue());
//				hpo.setMa020(new Date());
//				break;
//			case 8:
//				hpo.setMa021(po.getChanlvalue());
//				hpo.setMa022(new Date());
//				break;
//			case 9:
//				hpo.setMa023(po.getChanlvalue());
//				hpo.setMa024(new Date());
//				break;
//			case 10:
//				hpo.setMa025(po.getChanlvalue());
//				hpo.setMa026(new Date());
//				break;
//			case 11:
//				hpo.setMa027(po.getChanlvalue());
//				hpo.setMa028(new Date());
//				break;
//			case 12:
//				hpo.setMa029(po.getChanlvalue());
//				hpo.setMa030(new Date());
//				break;
//			case 13:
//				hpo.setMa031(po.getChanlvalue());
//				hpo.setMa032(new Date());
//				break;
//			case 14:
//				hpo.setMa033(po.getChanlvalue());
//				hpo.setMa034(new Date());
//				break;
//			case 15:
//				hpo.setMa035(po.getChanlvalue());
//				hpo.setMa036(new Date());
//				break;
//			case 16:
//				hpo.setMa037(po.getChanlvalue());
//				hpo.setMa038(new Date());
//				break;
//			case 17:
//				hpo.setMa039(po.getChanlvalue());
//				hpo.setMa040(new Date());
//				break;
//			case 18:
//				hpo.setMa041(po.getChanlvalue());
//				hpo.setMa042(new Date());
//				break;
//			case 19:
//				hpo.setMa043(po.getChanlvalue());
//				hpo.setMa044(new Date());
//				break;
//			case 20:
//				hpo.setMa045(po.getChanlvalue());
//				hpo.setMa046(new Date());
//				break;
//			case 21:
//				hpo.setMa047(po.getChanlvalue());
//				hpo.setMa048(new Date());
//
//				break;
//			case 22:
//				hpo.setMa049(po.getChanlvalue());
//				hpo.setMa050(new Date());
//
//				break;
//			case 23:
//				hpo.setMa051(po.getChanlvalue());
//				hpo.setMa052(new Date());
//
//				break;
//
//			}
//			shHistoryHourFacade.save(hpo);
//		} else {
//			hpo = list.get(0);
//			switch (po.getHourInDay()) {
//			case 0:
//				hpo.setMa005(po.getChanlvalue());
//				hpo.setMa006(new Date());
//				break;
//			case 1:
//				hpo.setMa007(po.getChanlvalue());
//				hpo.setMa008(new Date());
//				break;
//			case 2:
//				hpo.setMa009(po.getChanlvalue());
//				hpo.setMa010(new Date());
//				break;
//			case 3:
//				hpo.setMa011(po.getChanlvalue());
//				hpo.setMa012(new Date());
//				break;
//			case 4:
//				hpo.setMa013(po.getChanlvalue());
//				hpo.setMa014(new Date());
//				break;
//			case 5:
//				hpo.setMa015(po.getChanlvalue());
//				hpo.setMa016(new Date());
//				break;
//			case 6:
//				hpo.setMa017(po.getChanlvalue());
//				hpo.setMa018(new Date());
//				break;
//			case 7:
//				hpo.setMa019(po.getChanlvalue());
//				hpo.setMa020(new Date());
//				break;
//			case 8:
//				hpo.setMa021(po.getChanlvalue());
//				hpo.setMa022(new Date());
//				break;
//			case 9:
//				hpo.setMa023(po.getChanlvalue());
//				hpo.setMa024(new Date());
//				break;
//			case 10:
//				hpo.setMa025(po.getChanlvalue());
//				hpo.setMa026(new Date());
//				break;
//			case 11:
//				hpo.setMa027(po.getChanlvalue());
//				hpo.setMa028(new Date());
//				break;
//			case 12:
//				hpo.setMa029(po.getChanlvalue());
//				hpo.setMa030(new Date());
//				break;
//			case 13:
//				hpo.setMa031(po.getChanlvalue());
//				hpo.setMa032(new Date());
//				break;
//			case 14:
//				hpo.setMa033(po.getChanlvalue());
//				hpo.setMa034(new Date());
//				break;
//			case 15:
//				hpo.setMa035(po.getChanlvalue());
//				hpo.setMa036(new Date());
//				break;
//			case 16:
//				hpo.setMa037(po.getChanlvalue());
//				hpo.setMa038(new Date());
//				break;
//			case 17:
//				hpo.setMa039(po.getChanlvalue());
//				hpo.setMa040(new Date());
//				break;
//			case 18:
//				hpo.setMa041(po.getChanlvalue());
//				hpo.setMa042(new Date());
//				break;
//			case 19:
//				hpo.setMa043(po.getChanlvalue());
//				hpo.setMa044(new Date());
//				break;
//			case 20:
//				hpo.setMa045(po.getChanlvalue());
//				hpo.setMa046(new Date());
//				break;
//			case 21:
//				hpo.setMa047(po.getChanlvalue());
//				hpo.setMa048(new Date());
//
//				break;
//			case 22:
//				hpo.setMa049(po.getChanlvalue());
//				hpo.setMa050(new Date());
//
//				break;
//			case 23:
//				hpo.setMa051(po.getChanlvalue());
//				hpo.setMa052(new Date());
//
//				break;
//
//			}
//			shHistoryHourFacade.update(hpo);
//		}
//
//	}
//
//	private void saveHistoryInMonth(DataItemAFN08MomPo po) { // 持久化每天数据
//		// 保存或更新数据库SH_HISTORY_MONTH
//		ShHistoryMonth hpo = new ShHistoryMonth();
//		List<ShHistoryMonth> list = shHistoryMonthFacade
//				.findByProperty("MA002", po.getGprs_id(),
//						"MA004", po.getMedia(), null);
//		if (list == null || list.size() < 1) {
//			hpo.setMa001(UUID.randomUUID().toString());
//			hpo.setMa002(po.getGprs_id());
//			hpo.setMa004(po.getMedia());
//			switch (po.getHourInDay()) {
//			case 0:
//				hpo.setMa005(po.getChanlvalue());
//				hpo.setMa006(new Date());
//				break;
//			case 1:
//				hpo.setMa007(po.getChanlvalue());
//				hpo.setMa008(new Date());
//				break;
//			case 2:
//				hpo.setMa009(po.getChanlvalue());
//				hpo.setMa010(new Date());
//				break;
//			case 3:
//				hpo.setMa011(po.getChanlvalue());
//				hpo.setMa012(new Date());
//				break;
//			case 4:
//				hpo.setMa013(po.getChanlvalue());
//				hpo.setMa014(new Date());
//				break;
//			case 5:
//				hpo.setMa015(po.getChanlvalue());
//				hpo.setMa016(new Date());
//				break;
//			case 6:
//				hpo.setMa017(po.getChanlvalue());
//				hpo.setMa018(new Date());
//				break;
//			case 7:
//				hpo.setMa019(po.getChanlvalue());
//				hpo.setMa020(new Date());
//				break;
//			case 8:
//				hpo.setMa021(po.getChanlvalue());
//				hpo.setMa022(new Date());
//				break;
//			case 9:
//				hpo.setMa023(po.getChanlvalue());
//				hpo.setMa024(new Date());
//				break;
//			case 10:
//				hpo.setMa025(po.getChanlvalue());
//				hpo.setMa026(new Date());
//				break;
//			case 11:
//				hpo.setMa027(po.getChanlvalue());
//				hpo.setMa028(new Date());
//				break;
//			case 12:
//				hpo.setMa029(po.getChanlvalue());
//				hpo.setMa030(new Date());
//				break;
//			case 13:
//				hpo.setMa031(po.getChanlvalue());
//				hpo.setMa032(new Date());
//				break;
//			case 14:
//				hpo.setMa033(po.getChanlvalue());
//				hpo.setMa034(new Date());
//				break;
//			case 15:
//				hpo.setMa035(po.getChanlvalue());
//				hpo.setMa036(new Date());
//				break;
//			case 16:
//				hpo.setMa037(po.getChanlvalue());
//				hpo.setMa038(new Date());
//				break;
//			case 17:
//				hpo.setMa039(po.getChanlvalue());
//				hpo.setMa040(new Date());
//				break;
//			case 18:
//				hpo.setMa041(po.getChanlvalue());
//				hpo.setMa042(new Date());
//				break;
//			case 19:
//				hpo.setMa043(po.getChanlvalue());
//				hpo.setMa044(new Date());
//				break;
//			case 20:
//				hpo.setMa045(po.getChanlvalue());
//				hpo.setMa046(new Date());
//				break;
//			case 21:
//				hpo.setMa047(po.getChanlvalue());
//				hpo.setMa048(new Date());
//
//				break;
//			case 22:
//				hpo.setMa049(po.getChanlvalue());
//				hpo.setMa050(new Date());
//
//				break;
//			case 23:
//				hpo.setMa051(po.getChanlvalue());
//				hpo.setMa052(new Date());
//
//				break;
//			case 24:
//				hpo.setMa053(po.getChanlvalue());
//				hpo.setMa054(new Date());
//
//				break;
//			case 25:
//				hpo.setMa055(po.getChanlvalue());
//				hpo.setMa056(new Date());
//
//				break;
//			case 26:
//				hpo.setMa057(po.getChanlvalue());
//				hpo.setMa058(new Date());
//
//				break;
//			case 27:
//				hpo.setMa059(po.getChanlvalue());
//				hpo.setMa060(new Date());
//
//				break;
//			case 28:
//				hpo.setMa061(po.getChanlvalue());
//				hpo.setMa062(new Date());
//
//				break;
//			case 29:
//				hpo.setMa063(po.getChanlvalue());
//				hpo.setMa064(new Date());
//
//				break;
//			case 30:
//				hpo.setMa065(po.getChanlvalue());
//				hpo.setMa066(new Date());
//
//				break;
//
//			}
//			shHistoryMonthFacade.save(hpo);
//		} else {
//			hpo = list.get(0);
//			switch (po.getHourInDay()) {
//			case 0:
//				hpo.setMa005(po.getChanlvalue());
//				hpo.setMa006(new Date());
//				break;
//			case 1:
//				hpo.setMa007(po.getChanlvalue());
//				hpo.setMa008(new Date());
//				break;
//			case 2:
//				hpo.setMa009(po.getChanlvalue());
//				hpo.setMa010(new Date());
//				break;
//			case 3:
//				hpo.setMa011(po.getChanlvalue());
//				hpo.setMa012(new Date());
//				break;
//			case 4:
//				hpo.setMa013(po.getChanlvalue());
//				hpo.setMa014(new Date());
//				break;
//			case 5:
//				hpo.setMa015(po.getChanlvalue());
//				hpo.setMa016(new Date());
//				break;
//			case 6:
//				hpo.setMa017(po.getChanlvalue());
//				hpo.setMa018(new Date());
//				break;
//			case 7:
//				hpo.setMa019(po.getChanlvalue());
//				hpo.setMa020(new Date());
//				break;
//			case 8:
//				hpo.setMa021(po.getChanlvalue());
//				hpo.setMa022(new Date());
//				break;
//			case 9:
//				hpo.setMa023(po.getChanlvalue());
//				hpo.setMa024(new Date());
//				break;
//			case 10:
//				hpo.setMa025(po.getChanlvalue());
//				hpo.setMa026(new Date());
//				break;
//			case 11:
//				hpo.setMa027(po.getChanlvalue());
//				hpo.setMa028(new Date());
//				break;
//			case 12:
//				hpo.setMa029(po.getChanlvalue());
//				hpo.setMa030(new Date());
//				break;
//			case 13:
//				hpo.setMa031(po.getChanlvalue());
//				hpo.setMa032(new Date());
//				break;
//			case 14:
//				hpo.setMa033(po.getChanlvalue());
//				hpo.setMa034(new Date());
//				break;
//			case 15:
//				hpo.setMa035(po.getChanlvalue());
//				hpo.setMa036(new Date());
//				break;
//			case 16:
//				hpo.setMa037(po.getChanlvalue());
//				hpo.setMa038(new Date());
//				break;
//			case 17:
//				hpo.setMa039(po.getChanlvalue());
//				hpo.setMa040(new Date());
//				break;
//			case 18:
//				hpo.setMa041(po.getChanlvalue());
//				hpo.setMa042(new Date());
//				break;
//			case 19:
//				hpo.setMa043(po.getChanlvalue());
//				hpo.setMa044(new Date());
//				break;
//			case 20:
//				hpo.setMa045(po.getChanlvalue());
//				hpo.setMa046(new Date());
//				break;
//			case 21:
//				hpo.setMa047(po.getChanlvalue());
//				hpo.setMa048(new Date());
//
//				break;
//			case 22:
//				hpo.setMa049(po.getChanlvalue());
//				hpo.setMa050(new Date());
//
//				break;
//			case 23:
//				hpo.setMa051(po.getChanlvalue());
//				hpo.setMa052(new Date());
//
//				break;
//			case 24:
//				hpo.setMa053(po.getChanlvalue());
//				hpo.setMa054(new Date());
//
//				break;
//			case 25:
//				hpo.setMa055(po.getChanlvalue());
//				hpo.setMa056(new Date());
//
//				break;
//			case 26:
//				hpo.setMa057(po.getChanlvalue());
//				hpo.setMa058(new Date());
//
//				break;
//			case 27:
//				hpo.setMa059(po.getChanlvalue());
//				hpo.setMa060(new Date());
//
//				break;
//			case 28:
//				hpo.setMa061(po.getChanlvalue());
//				hpo.setMa062(new Date());
//
//				break;
//			case 29:
//				hpo.setMa063(po.getChanlvalue());
//				hpo.setMa064(new Date());
//
//				break;
//			case 30:
//				hpo.setMa065(po.getChanlvalue());
//				hpo.setMa066(new Date());
//
//				break;
//			}
//			shHistoryMonthFacade.update(hpo);
//		}
//	}
//
//	private ShHistoryHour getHistoryInHour(String gprsid,
//			String media, Date nowDate, int hours) {
//		ShHistoryHour hpo = new ShHistoryHour();
//		// 从数据取字段一个一个比较，调用Dateutil.isLastOneDay
//		// 判断是否为过去24小时,设响应的属性值，如果不是过去24小时，则为0
//		List<ShHistoryHour> list = shHistoryHourFacade
//				.findByProperty("MA002", gprsid, "MA004", media,
//						null);
//		if (list.size() == 1) {
//			hpo = list.get(0);
//			if (!DateUtil.isLastOneDay(hpo.getMa006(), nowDate,
//					hours)) {
//				hpo.setMa005("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa008(), nowDate,
//					hours)) {
//				hpo.setMa007("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa010(), nowDate,
//					hours)) {
//				hpo.setMa009("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa012(), nowDate,
//					hours)) {
//				hpo.setMa011("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa014(), nowDate,
//					hours)) {
//				hpo.setMa013("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa016(), nowDate,
//					hours)) {
//				hpo.setMa015("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa018(), nowDate,
//					hours)) {
//				hpo.setMa017("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa020(), nowDate,
//					hours)) {
//				hpo.setMa019("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa022(), nowDate,
//					hours)) {
//				hpo.setMa021("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa024(), nowDate,
//					hours)) {
//				hpo.setMa023("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa026(), nowDate,
//					hours)) {
//				hpo.setMa025("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa028(), nowDate,
//					hours)) {
//				hpo.setMa027("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa030(), nowDate,
//					hours)) {
//				hpo.setMa029("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa032(), nowDate,
//					hours)) {
//				hpo.setMa031("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa034(), nowDate,
//					hours)) {
//				hpo.setMa033("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa036(), nowDate,
//					hours)) {
//				hpo.setMa035("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa038(), nowDate,
//					hours)) {
//				hpo.setMa037("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa040(), nowDate,
//					hours)) {
//				hpo.setMa039("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa042(), nowDate,
//					hours)) {
//				hpo.setMa041("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa044(), nowDate,
//					hours)) {
//				hpo.setMa043("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa046(), nowDate,
//					hours)) {
//				hpo.setMa045("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa048(), nowDate,
//					hours)) {
//				hpo.setMa047("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa050(), nowDate,
//					hours)) {
//				hpo.setMa049("0.0");
//			}
//			if (!DateUtil.isLastOneDay(hpo.getMa052(), nowDate,
//					hours)) {
//				hpo.setMa051("0.0");
//			}
//		}
//
//		return hpo;
//	}
//
//	private ShHistoryMonth getHistoryInMonth(String gprsid,
//			String media, Date nowDate, int days) {
//		ShHistoryMonth hpo = new ShHistoryMonth();
//		// 从数据取字段一个一个比较，调用Dateutil.isLastManyDays
//		// 判断是否为过去31天,设响应的属性值，如果不是过去31天，则为0
//		List<ShHistoryMonth> list = shHistoryMonthFacade
//				.findByProperty("MA002", gprsid, "MA004", media,
//						null);
//		if (list.size() == 1) {
//			hpo = list.get(0);
//			if (!DateUtil.isLastManyDays(hpo.getMa006(),
//					nowDate, days)) {
//				hpo.setMa005("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa008(),
//					nowDate, days)) {
//				hpo.setMa007("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa010(),
//					nowDate, days)) {
//				hpo.setMa009("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa012(),
//					nowDate, days)) {
//				hpo.setMa011("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa014(),
//					nowDate, days)) {
//				hpo.setMa013("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa016(),
//					nowDate, days)) {
//				hpo.setMa015("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa018(),
//					nowDate, days)) {
//				hpo.setMa017("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa020(),
//					nowDate, days)) {
//				hpo.setMa019("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa022(),
//					nowDate, days)) {
//				hpo.setMa021("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa024(),
//					nowDate, days)) {
//				hpo.setMa023("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa026(),
//					nowDate, days)) {
//				hpo.setMa025("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa028(),
//					nowDate, days)) {
//				hpo.setMa027("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa030(),
//					nowDate, days)) {
//				hpo.setMa029("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa032(),
//					nowDate, days)) {
//				hpo.setMa031("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa034(),
//					nowDate, days)) {
//				hpo.setMa033("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa036(),
//					nowDate, days)) {
//				hpo.setMa035("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa038(),
//					nowDate, days)) {
//				hpo.setMa037("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa040(),
//					nowDate, days)) {
//				hpo.setMa039("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa042(),
//					nowDate, days)) {
//				hpo.setMa041("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa044(),
//					nowDate, days)) {
//				hpo.setMa043("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa046(),
//					nowDate, days)) {
//				hpo.setMa045("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa048(),
//					nowDate, days)) {
//				hpo.setMa047("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa050(),
//					nowDate, days)) {
//				hpo.setMa049("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa052(),
//					nowDate, days)) {
//				hpo.setMa051("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa054(),
//					nowDate, days)) {
//				hpo.setMa053("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa056(),
//					nowDate, days)) {
//				hpo.setMa055("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa058(),
//					nowDate, days)) {
//				hpo.setMa057("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa060(),
//					nowDate, days)) {
//				hpo.setMa059("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa062(),
//					nowDate, days)) {
//				hpo.setMa061("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa064(),
//					nowDate, days)) {
//				hpo.setMa063("0.0");
//			}
//			if (!DateUtil.isLastManyDays(hpo.getMa066(),
//					nowDate, days)) {
//				hpo.setMa065("0.0");
//			}
//		}
//
//		return hpo;
//	}
//
//	private void saveAlarm(DataItemAFN08MomPo po) { // 保存报警到数据库(通过应用服务器的webservice)
//		try {
//			// 1.创建service对象，通过axis自带的类创建
//			org.apache.axis.client.Service service = new org.apache.axis.client.Service();
//			// 请求服务的URL
//			URL url = new URL(
//					CloudConstants.APPLICATION_SERVER_WSDL);
//			// 2.创建服务方法的调用者对象call，设置call对象的属性
//			Call call = (Call) service.createCall();
//
//			call.setTargetEndpointAddress(url); // 给call对象设置请求的URL属性
//
//			String serviceName = "saveAddDeviceAlarm";// webservice鐨勬柟娉曞悕
//			call.setOperationName(serviceName);
//			// Object[] results = client.invoke("saveAddDeviceAlarm", new
//			// Object[] {
//			// obj.get("gprs_id"), obj.get("media"), obj.get("status"),
//			// getXMLGregorianCalendar(new Date()),
//			// Double.parseDouble(obj.getString("chanlvalue"))});
//			call.invoke(new Object[] { po.getGprs_id(),
//					po.getMedia(), po.getStatus(),
//					getXMLGregorianCalendar(new Date()),
//					Double.parseDouble(po.getChanlvalue()) });
//
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (ServiceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	private XMLGregorianCalendar getXMLGregorianCalendar(
//			Date date) throws Exception {
//		Calendar calendar = Calendar.getInstance();
//		calendar.setTime(date);
//		javax.xml.datatype.DatatypeFactory dtf = javax.xml.datatype.DatatypeFactory
//				.newInstance();
//		return dtf
//				.newXMLGregorianCalendar(
//						calendar.get(calendar.YEAR),
//						calendar.get(calendar.MONTH) + 1,
//						calendar.get(calendar.DAY_OF_MONTH),
//						calendar.get(calendar.HOUR_OF_DAY),
//						calendar.get(calendar.MINUTE),
//						calendar.get(calendar.SECOND),
//						calendar.get(calendar.MILLISECOND),
//						calendar.get(calendar.ZONE_OFFSET)
//								/ (1000 * 60));
//	}
//
//}
