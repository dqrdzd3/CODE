package com.hw.smarthome.mom.service.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.XMLGregorianCalendar;

import com.hw.hwsafe.smart.pojo.SensorAirDetail;
import com.hw.hwsafe.smart.pojo.SensorConstant;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.hwsafe.smart.pojo.SensorGasDetail;
import com.hw.smarthome.mom.cache.MOMCache;
import com.hw.smarthome.mom.constant.CloudConstants;
import com.hw.smarthome.mom.dao.shhistorydailymax.ShGasHistoryDailyMax;
import com.hw.smarthome.mom.dao.shhistoryhour.ShHistoryHour;
import com.hw.smarthome.mom.dao.shhistorymonth.ShHistoryMonth;
import com.hw.smarthome.mom.po.DataItemAFN08MomPo;

public class MOMServiceUtil {

	public static void reflectSetMonthValue(ShHistoryMonth hpo,
			int i, String value) throws Exception {
		Method m1 = hpo.getClass().getDeclaredMethod(
				"setMa0" + (i < 10 ? ("0" + i) : (i)),
				String.class);
		m1.invoke(hpo, value);
	}

	public static void reflectSetMonthTime(ShHistoryMonth hpo,
			int i, Date date) throws Exception {
		Method m1 = hpo.getClass().getDeclaredMethod(
				"setMa0" + (i < 10 ? ("0" + i) : (i)),
				Date.class);
		m1.invoke(hpo, date);
	}

	//new
	public static void reflectSetDayMaxValue(ShGasHistoryDailyMax shGasHistoryDailyMax,int i,String value) throws Exception{
		Method m1 = null;
		if(i == 1){
			m1 = shGasHistoryDailyMax.getClass().getDeclaredMethod("setId", String.class);
		}else if(i == 2){
			 m1 = shGasHistoryDailyMax.getClass().getDeclaredMethod("setDeviceId", String.class);
		}else if(i == 3){
			 m1 = shGasHistoryDailyMax.getClass().getDeclaredMethod("setGasType", String.class);
		}else if(i == 5){
			 m1 = shGasHistoryDailyMax.getClass().getDeclaredMethod("setValue", String.class);
		}
		m1.invoke(shGasHistoryDailyMax, value);
	}
	
	public static void reflectSetDayMaxDate(ShGasHistoryDailyMax shGasHistoryDailyMax,int i,Date date) throws Exception{
		Method m1 = null;
		if(i == 4){
			m1 = shGasHistoryDailyMax.getClass().getDeclaredMethod("setCurrentTimeNum", Date.class);
		}
		m1.invoke(shGasHistoryDailyMax, date);
	}

	
	public static Date reflectGetMonthTime(ShHistoryMonth hpo,
			int i) throws Exception {
		String methodName = "getMa0"
				+ (i < 10 ? ("0" + i) : (i));
		Method m1 = hpo.getClass().getDeclaredMethod(methodName);
		return (Date) m1.invoke(hpo);
	}

	public static void reflectSetHourValue(ShHistoryHour hpo,
			int i, String value) throws Exception {
		Method m1 = hpo.getClass().getDeclaredMethod(
				"setMa0" + (i < 10 ? ("0" + i) : (i)),
				String.class);
		m1.invoke(hpo, value);
	}

	public static void reflectSetHourTime(ShHistoryHour hpo,
			int i, Date date) throws Exception {
		Method m1 = hpo.getClass().getDeclaredMethod(
				"setMa0" + (i < 10 ? ("0" + i) : (i)),
				Date.class);
		m1.invoke(hpo, date);
	}

	public static Date reflectGetHourTime(ShHistoryHour hpo,
			int i) throws Exception {
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String methodName = "getMa0"
				+ (i < 10 ? ("0" + i) : (i));

		Method m1 = hpo.getClass().getDeclaredMethod(methodName);
		// if (m1.invoke(hpo)!=null)
		// return sdf.parse(sdf.format((Date)m1.invoke(hpo)));
		// else
		// return sdf.parse(sdf.format());
		return (Date) m1.invoke(hpo);
	}

	public static String reflectGetHourValue(ShHistoryHour hpo,
			int i) throws Exception {

		String methodName = "getMa0"
				+ (i < 10 ? ("0" + i) : (i));

		Method m1 = hpo.getClass().getDeclaredMethod(methodName);

		return (String) m1.invoke(hpo);
	}

	public static String reflectGetDayValue(ShHistoryMonth hpo,
			int i) throws Exception {

		String methodName = "getMa0"
				+ (i < 10 ? ("0" + i) : (i));

		Method m1 = hpo.getClass().getDeclaredMethod(methodName);

		return (String) m1.invoke(hpo);
	}

	/**
	 * 获取实时数据值
	 * 
	 * @author 曾凡
	 * @param sensorDetail
	 * @param deviceId
	 * @param driverType
	 * @param driverName
	 * @throws Exception
	 * @time 2015年1月27日 上午10:48:20
	 */
	public static void setCurValues(SensorDetail sensorDetail,
			String deviceId, String driverType, String driverName)
			throws Exception {

		sensorDetail.setSensorId(deviceId);
		if ((SensorConstant.SENSOR_TYPE_GAS+"").equals(driverType)) {
			SensorGasDetail s1 = new SensorGasDetail();
			s1.setSensorId(deviceId);
			s1.setName(driverName);
			s1.setCo(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_CO));
			s1.setCh4(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_CH4));
			String coTime = getTime(deviceId,
					CloudConstants.MEDIA_TYPE_CO);
			String ch4Time = getTime(deviceId,
					CloudConstants.MEDIA_TYPE_CH4);
			s1.setCreateTime(getGasTime(coTime, ch4Time));
			sensorDetail.setGas(s1);
		} else if ((SensorConstant.SENSOR_TYPE_AIR+"").equals(driverType)) {
			SensorAirDetail sa1 = new SensorAirDetail();
			sa1.setSensorId(deviceId);
			sa1.setName(driverName);
			sa1.setVoc(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_VOC));
			sa1.setPm25(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_PM25));
			sa1.setTemperature(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_TEMPERATURE));
			sa1.setHumidity(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_HUMIDITY));
			sa1.setCo2(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_CO2));
			sa1.setCh2o(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_CH2O));
			sa1.setC6h6(getValue(deviceId,
					CloudConstants.MEDIA_TYPE_C6H6));
			String temTime = getTime(deviceId,
					CloudConstants.MEDIA_TYPE_TEMPERATURE);
			String humTime = getTime(deviceId,
					CloudConstants.MEDIA_TYPE_HUMIDITY);
			String pm25Time = getTime(deviceId,
					CloudConstants.MEDIA_TYPE_PM25);
			String co2Time = getTime(deviceId,
					CloudConstants.MEDIA_TYPE_CO2);

			sa1.setCreateTime(getAirTime(temTime, humTime,
					pm25Time, co2Time));
			sensorDetail.setAir(sa1);
		}
	}

	/**
	 * 根据WIFI模块ID(或GPRSID)和探测介质获取该传感器当前的数值
	 */
	public static String getValue(String gprsId, String media) {
		String key = gprsId + "_" + media;
		if (MOMCache.getCurrentDevicesList().containsKey(key)) {
			DataItemAFN08MomPo cellPO = MOMCache
					.getCurrentDevicesList().get(key);
			return cellPO.getChanlvalue();
		}
		return null;
	}

	/**
	 * 根据WIFI模块ID(或GPRSID)和探测介质获取该传感器当前的数值
	 */
	public static String getTime(String gprsId, String media) {
 		SimpleDateFormat formater = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String key = gprsId + "_" + media;
		if (MOMCache.getCurrentDevicesList().containsKey(key)) {
			DataItemAFN08MomPo cellPO = MOMCache
					.getCurrentDevicesList().get(key);
			return formater.format(cellPO.getReciveDate());
		}
		return null;

	}

	public static String getAirTime(String temTime,
			String humTime, String pm25Time, String co2Time)
			throws Exception {
/*		if (temTime != null && !temTime.isEmpty()) {
			return temTime;
		} else if (humTime != null && !humTime.isEmpty()) {
			return temTime;
		} else if (pm25Time != null && !pm25Time.isEmpty()) {
			return temTime;
		} else if (co2Time != null && !co2Time.isEmpty()) {
			return temTime;
		}*/
		List<String> timeList = new ArrayList<String>();
		if(temTime != null && !temTime.isEmpty()){
			timeList.add(temTime);
		}
		if(humTime != null && !humTime.isEmpty()){
			timeList.add(humTime);
		}
		if(pm25Time != null && !pm25Time.isEmpty()){
			timeList.add(pm25Time);
		}
		if(co2Time != null && !co2Time.isEmpty()){
			timeList.add(co2Time);
		}
		String result = findNewTimeStr(timeList);
		if(result == ""){
			return "1900-01-01 01:01:01";
		}else{
			return result;
		}		
	}

	public static String getGasTime(String coTime, String ch4Time)
			throws Exception {
/*		if (coTime != null && !coTime.isEmpty()) {
			return coTime;
		} else if (ch4Time != null && !ch4Time.isEmpty()) {
			return ch4Time;
		}*/
		List<String> timeList = new ArrayList<String>();
		if (coTime != null && !coTime.isEmpty()) {
			timeList.add(coTime);
		}
		if (ch4Time != null && !ch4Time.isEmpty()) {
			timeList.add(ch4Time);
		}
		String result = findNewTimeStr(timeList);
		if(result == ""){
			return "1900-01-01 01:01:01";
		}else{
			return result;
		}
	}

	/**
	 * 
	 * 返回时间列表中的最新时间
	 * @param timeList
	 * @return
	 * @author 王珂
	 * @time 2015年11月18日 下午3:08:40
	 */
	private static String findNewTimeStr(List<String> timeList){
		long newTime = 0L;
		String newTimeStrConcat = "";
		if(timeList != null && !timeList.isEmpty()){
			for(String timeStr : timeList){
				long longStr = Long.valueOf(timeStr.replaceAll("[-\\s:]",""));
				if(longStr > newTime){
					newTime = longStr;
				}
			}
			String newTimeStr = Long.toString(newTime);
	        newTimeStrConcat = newTimeStr.substring(0, 4)+"-"+newTimeStr.substring(4,6)+"-"+newTimeStr.substring(6,8)+" "+newTimeStr.substring(8, 10)+":"+newTimeStr.substring(10, 12)+":"+newTimeStr.substring(12,14);	      
		}
		return newTimeStrConcat;
	}
	
	/**
	 * 当前日期的前七天日期
	 */
	public static String[] get7DaysBefore() {
		String[] days = new String[7];
		Calendar now = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String tempDay = null;
		for (int i = 0; i < days.length; i++) {
			now.add(Calendar.DATE, -1);
			tempDay = df.format(now.getTime());
			days[i] = tempDay;
		}
		return days;
	}

	public static XMLGregorianCalendar getXMLGregorianCalendar(
			Date date) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		javax.xml.datatype.DatatypeFactory dtf = javax.xml.datatype.DatatypeFactory
				.newInstance();
		return dtf
				.newXMLGregorianCalendar(
						calendar.get(calendar.YEAR),
						calendar.get(calendar.MONTH) + 1,
						calendar.get(calendar.DAY_OF_MONTH),
						calendar.get(calendar.HOUR_OF_DAY),
						calendar.get(calendar.MINUTE),
						calendar.get(calendar.SECOND),
						calendar.get(calendar.MILLISECOND),
						calendar.get(calendar.ZONE_OFFSET)
								/ (1000 * 60));
	}

	// lasthour 为之前第几个小时的数据
	public static Map<String, String> getDataFromHisHour(
			ShHistoryHour shh, int lastHour) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = Calendar.getInstance();
		int curH = calendar.get(Calendar.HOUR_OF_DAY);
		int i = 0;
		if (curH >= lastHour) {
			i = curH - lastHour;
		} else {
			i = curH + 24 - lastHour;

		}
		SimpleDateFormat sf2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		map.put("data", reflectGetHourValue(shh, 5 + i * 2));
		if (reflectGetHourTime(shh, 6 + i * 2) != null) {
			map.put("recive", sf2.format(reflectGetHourTime(shh,
					6 + i * 2)));
		}
		return map;
	}

	public static Map<String, String> getDataFromHisMonth(
			ShHistoryMonth shm, int lastDay) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Calendar calendar = Calendar.getInstance();
		int curD = calendar.get(Calendar.DAY_OF_MONTH);
		int i = 0;
		if (curD >= lastDay) {
			i = curD - lastDay;
		} else {
			i = curD + getDaysInLastMonth() - lastDay;

		}
		SimpleDateFormat sf2 = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		map.put("data", reflectGetDayValue(shm, 5 + i * 2));
		if (reflectGetMonthTime(shm, 6 + i * 2) != null) {
			map.put("recive", sf2.format(reflectGetMonthTime(
					shm, 6 + i * 2)));
		} else {

		}
		return map;
	}

	public static int getDaysInLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
	/**
	 * 
	 * 获得最新时间
	 * @return
	 * @author 王珂
	 * @time 2015年11月18日 下午2:39:41
	 */
	public static String getCurrentTime(){
		Date date = new Date();
		String dateStr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateStr = sdf.format(date);
		return dateStr;
	}
}
