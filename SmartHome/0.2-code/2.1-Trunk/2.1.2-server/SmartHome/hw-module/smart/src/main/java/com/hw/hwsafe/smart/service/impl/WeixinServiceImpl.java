package com.hw.hwsafe.smart.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hw.hwsafe.attachment.util.AppFileUpLoad;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.BinUpdatePo;
import com.hw.hwsafe.smart.pojo.D002PO;
import com.hw.hwsafe.smart.pojo.SensorAirDetail;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.hwsafe.smart.pojo.SensorDetailList;
import com.hw.hwsafe.smart.pojo.SensorGasDetail;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.service.IWeixinService;
import com.hw.hwsafe.smart.util.WebServiceUtil;
import com.hw.hwsafe.smart.weixin.service.CoreService;

public class WeixinServiceImpl extends BaseServiceImpl implements
		IWeixinService {
	
	private static Log log = LogFactory.getLog(WeixinServiceImpl.class);
	private CallbackDataPO callbackDataPO;

	@Autowired
	private IU001Service u001Service;
	@Autowired
	private ID002Service d002Service;

	@Override
	public String getSensorDetailToWX(String tel) throws Exception {

		StringBuffer detailResultString = new StringBuffer();

		SensorDetailList detailList = new SensorDetailList();
		List<SensorDetail> sensorDetailList = new ArrayList<SensorDetail>();

		U001PO tempU001po = new U001PO();
		tempU001po.setMa006(tel);

		List<U001PO> u001POList = u001Service.retrieveInstanceByPO(tempU001po);
		if (u001POList != null && u001POList.size() == 1) {
			U001PO u001po = u001POList.get(0);

			D002PO d002PO = new D002PO();
			d002PO.setMa002(u001po.getMa001());

			List<D002PO> d002List = d002Service.retrieveInstanceByPO(d002PO);
			for (D002PO d002 : d002List) {

				String jsonStr = WebServiceUtil.doWebservice(d002.getMa004(),
						d002.getMa003(), d002.getMa008(),
						"retrieveCurStautsByGPRSId");
				if (jsonStr != null && jsonStr.length() > 5) {
					SensorDetail sensorDetail = null;
					try {

						Map<String, BinUpdatePo> map = D002ServiceImpl
								.getBinVersionCacheMap();

						sensorDetail = new Gson().fromJson(jsonStr,
								SensorDetail.class);

						int days = 0;
						if (map != null
								&& map.get(sensorDetail.getSensorId()) != null) {
							sensorDetail.setRemoteHardVersion(map.get(
									sensorDetail.getSensorId())
									.getRemoteHardVersion());

							sensorDetail.setRemoteSoftVersion(map.get(
									sensorDetail.getSensorId())
									.getRemoteSoftVersion());

							days = (int) ((new Date()).getTime() - map
									.get(sensorDetail.getSensorId()).getDate()
									.getTime() / 86400000);
						} else {
							((D002ServiceImpl) d002Service)
									.query09(sensorDetail.getSensorId());
						}
						// 先查询

						if (days > 1) { // 大于1天，去查询
							((D002ServiceImpl) d002Service)
									.query09(sensorDetail.getSensorId());
						}

						if (sensorDetail.getSensorId().startsWith("5")) { // A1
							sensorDetail
									.setLocalHardVersion(AppFileUpLoad.localA1HardVersion);
							sensorDetail
									.setLocalSoftVersion(AppFileUpLoad.localA1SoftVersion);
						}
						if (sensorDetail.getSensorId().startsWith("1")) { // R1
							sensorDetail
									.setLocalHardVersion(AppFileUpLoad.localR1HardVersion);
							sensorDetail
									.setLocalSoftVersion(AppFileUpLoad.localR1SoftVersion);
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sensorDetailList.add(sensorDetail);
				}
			}
			detailList.setSensorList(sensorDetailList);
			callbackDataPO = new CallbackDataPO("1",
					SmartConstants.QUERY_DATA_SUCCESS, sensorDetailList.size(),
					null, JSONArray.fromObject(detailList), "D002");
			detailResultString.append(parseListToDetail(detailList));
		} else {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
					"D002");

			detailResultString.append("数据请求异常，请重试！");
		}

		// return new Gson().toJson(callbackDataPO);
		return detailResultString.toString();
	}

	public String parseListToDetail(SensorDetailList sensorDetailList) throws ParseException {
		if (sensorDetailList == null
				|| sensorDetailList.toString().length() < 5) {
			return ("无设备");
		} else {
			StringBuffer stringBuffer = new StringBuffer();
			List<SensorDetail> sensorList = sensorDetailList.getSensorList();

			for (int i = 0; i < sensorList.size(); i++) {
				if (sensorList.get(i).getAir() != null) {
					

					SensorAirDetail airDetail = sensorList.get(i).getAir();
					String online = comTwoDate(airDetail.getCreateTime())<1000*60*5 ? "在线" : "不在线";

					stringBuffer.append("设备名称：" + airDetail.getName() + "\n");
					stringBuffer.append("设备编号：" + airDetail.getSensorId()
							+ "\n");
					stringBuffer.append("联网状态：" + online + "\n");
					stringBuffer.append("温度：" + airDetail.getTemperature()
							+ "\n");
					stringBuffer.append("湿度：" + airDetail.getHumidity() + "\n");
					stringBuffer.append("CO2：" + airDetail.getCo2() + "\n");
					stringBuffer.append("PM2.5：" + airDetail.getPm25() + "\n");
					stringBuffer.append("VOC：" + airDetail.getVoc() + "\n");
					stringBuffer.append("上报时间：" + airDetail.getCreateTime()
							+ "\n");
					stringBuffer.append("\n");
					stringBuffer
							.append("  -------------------------------------   "
									+ "\n");

				}
				if (sensorList.get(i).getGas() != null) {

					SensorGasDetail gasDetail = sensorList.get(i).getGas();
					String online = comTwoDate(gasDetail.getCreateTime())<1000*60*5 ? "在线" : "不在线";
					stringBuffer.append("设备名称：" + gasDetail.getName() + "\n");
					stringBuffer.append("设备编号：" + gasDetail.getSensorId()
							+ "\n");
					stringBuffer.append("联网状态：" + online + "\n");
					stringBuffer.append("CO：" + gasDetail.getCo() + "\n");
					stringBuffer.append("燃气：" + gasDetail.getCh4() + "\n");
					stringBuffer.append("上报时间：" + gasDetail.getCreateTime()
							+ "\n");
					stringBuffer.append("\n");
					stringBuffer
							.append("  --------------------------------------   "
									+ "\n");

				}
			}

			return stringBuffer.toString();
		}

	}
	/*
	 * 判断日期之间的毫秒数
	 * @see com.hw.hwsafe.smart.service.IWeixinService#getSensorHistoryToWX(java.lang.String)
	 */
	private  long comTwoDate(String datetimeString) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date date1 = df.parse(datetimeString);
		return new Date().getTime() - date1.getTime();
		
	}
	
	@Override
	public String getSensorHistoryToWX(String sensorid) throws Exception {

		StringBuffer detailResultString = new StringBuffer();

		List<SensorDetail> sensorDetailList = new LinkedList<SensorDetail>();

		String sensorId = sensorid.toUpperCase();
		// int typeOfHis =
		// request.getParameter("TYPE")==null?2:Integer.parseInt(request.getParameter("TYPE"));
		// int lastDaysOrHours = request.getParameter("LASTPAR")== null ?
		// 7:Integer.parseInt(request.getParameter("LASTPAR"));
		int typeOfHis = 2;// Integer.parseInt(request.getParameter("TYPE")); //
							// 1 代表历史小时 2 代表历史天
		int lastDaysOrHours = 7;// Integer.parseInt(request.getParameter("LASTPAR"));
								// //代表过去的具体数字

		List<D002PO> d002poList = d002Service.retrieveInstanceByDevId(sensorId);
		if (d002poList != null && d002poList.size() >= 1) {
			D002PO d002 = d002poList.get(0);
	
			String jsonStr = WebServiceUtil.doHisWebservice(d002.getMa004(),
					d002.getMa003(), lastDaysOrHours, typeOfHis,
					"retrieveHisStautsByGPRSId");

			if (jsonStr != null && jsonStr.length() > 5) {
//				sensorDetailList = new Gson().fromJson(jsonStr,
//						new TypeToken<List<SensorDetail>>() {
//						}.getType());
				org.json.JSONArray jarray = new org.json.JSONArray(jsonStr);
				for (int i = 0; i < jarray.length(); i++) {
					SensorDetail sensorDetail = new Gson().fromJson(jarray.get(i).toString(), SensorDetail.class);
				
					sensorDetailList.add(sensorDetail);
				}
			}
			
			detailResultString.append(parseListToHistory(sensorDetailList));

		} else {

			detailResultString.append("数据请求异常，请重试！");
		}
		
		return detailResultString.toString();
	}
	public String parseListToHistory(List<SensorDetail> sensorList) throws ParseException {
		if (sensorList == null
				|| sensorList.size()==0) {
			return ("无设备");
		} else {
			String[] days = get7DaysBeforCn();
			StringBuffer stringBuffer = new StringBuffer();

			for (int i = sensorList.size() - 1;  i >= 0 ; i--) {
				if (sensorList.get(i).getAir() != null) {
					

					SensorAirDetail airDetail = sensorList.get(i).getAir();
					//String online = comTwoDate(airDetail.getCreateTime())<1000*60*5 ? "在线" : "不在线";
					stringBuffer.append("历史日期：" + days[i]
							+ "\n");
					stringBuffer.append("设备名称：" + airDetail.getName() + "\n");
					stringBuffer.append("设备编号：" + airDetail.getSensorId()
							+ "\n");
//					stringBuffer.append("联网状态：" + online + "\n");
					stringBuffer.append("温度：" + airDetail.getTemperature()
							+ "\n");
					stringBuffer.append("湿度：" + airDetail.getHumidity() + "\n");
					stringBuffer.append("CO2：" + airDetail.getCo2() + "\n");
					stringBuffer.append("PM2.5：" + airDetail.getPm25() + "\n");
					stringBuffer.append("VOC：" + airDetail.getVoc() + "\n");
//					stringBuffer.append("上报时间：" + airDetail.getCreateTime()
//							+ "\n");
					stringBuffer.append("\n");
					stringBuffer
							.append("  -------------------------------------   "
									+ "\n");

				}
				if (sensorList.get(i).getGas() != null) {

					SensorGasDetail gasDetail = sensorList.get(i).getGas();
					//String online = comTwoDate(gasDetail.getCreateTime())<1000*60*5 ? "在线" : "不在线";
					stringBuffer.append("历史日期：" + days[i]
							+ "\n");
					stringBuffer.append("设备名称：" + gasDetail.getName() + "\n");
					stringBuffer.append("设备编号：" + gasDetail.getSensorId()
							+ "\n");
				//	stringBuffer.append("联网状态：" + online + "\n");
					stringBuffer.append("CO：" + gasDetail.getCo() + "\n");
					stringBuffer.append("燃气：" + gasDetail.getCh4() + "\n");
//					stringBuffer.append("上报时间：" + gasDetail.getCreateTime()
//							+ "\n");
					stringBuffer.append("\n");
					stringBuffer
							.append("  --------------------------------------   "
									+ "\n");

				}
			}

			return stringBuffer.toString();
		}

	}
	public static String[] get7DaysBeforCn() {
		String[] days = new String[7];
		Calendar now = Calendar.getInstance();
		String tempDay = "";
		for (int i = days.length - 1; i >= 0; i--) {
			now.add(Calendar.DATE, -1);
			tempDay = getWeekOfDateCn(now.getTime());
			days[i] = tempDay;
		}

		return days;

	}

	/**
	 * 获取当前日期是周几<br>
	 * 
	 * @param dt
	 * @return 当前日期是周几
	 */
	public static String getWeekOfDateCn(Date dt) {
		String[] weekDays = { "周日", "周一", "周二", "周三", "周四",
				"周五", "周六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

}
