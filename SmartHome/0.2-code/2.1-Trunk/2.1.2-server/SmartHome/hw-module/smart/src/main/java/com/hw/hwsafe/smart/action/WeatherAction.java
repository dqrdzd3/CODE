package com.hw.hwsafe.smart.action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hw.hwsafe.attachment.action.AppFileUpLoadAction;
import com.hw.hwsafe.attachment.util.AppFileUpLoad;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.dao.IBigScreenDao;
import com.hw.hwsafe.smart.pojo.AdvertisePO;
import com.hw.hwsafe.smart.pojo.BinUpdatePo;
import com.hw.hwsafe.smart.pojo.ControlDevicePO;
import com.hw.hwsafe.smart.pojo.D002PO;
import com.hw.hwsafe.smart.pojo.InfomationPO;
import com.hw.hwsafe.smart.pojo.SceneDispPO;
import com.hw.hwsafe.smart.pojo.SensorAirDetail;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.hwsafe.smart.pojo.SensorDetailList;
import com.hw.hwsafe.smart.pojo.SensorGasDetail;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IBigScreenService;
import com.hw.hwsafe.smart.service.IControlDeviceService;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.service.IWeatherService;
import com.hw.hwsafe.smart.service.impl.D002ServiceImpl;
import com.hw.hwsafe.smart.util.WebServiceUtil;
import com.hw.hwsafe.smart.util.base64;

public class WeatherAction extends AppFileUpLoadAction {

	@Autowired
	private IWeatherService weatherService;
	
	@Autowired
	private IControlDeviceService controlDeviceService;
	
	@Autowired
	private ID002Service d002Service;

	@Autowired
	private IU001Service u001Service;
	
	@Autowired
	private IBigScreenService bigScreenService;

	private SensorGasDetail sensorGasDetail;

	private SensorAirDetail sensorAirDetail;

	private D002PO d002PO;
	
	private List<SensorDetail> sensorDetailList;
	
	private InfomationPO messagePO = new InfomationPO();
	
	public String doShow(){
		try {
			//doViewDetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String doOther(){
		return "other";
	}
	public String getWeatherContent() {
		//String ip = request.getParameter("IP");
		String cityName = request.getParameter("city");

		String s = "";

		try {
			//String cityName = weatherService.ipToCity(ip);
			String cityCode = weatherService.cityToId(cityName);
			String jsonString = weatherService.getWeatherContent(cityCode);

			JSONObject json = JSONObject.fromObject(jsonString);
			s = json.get("weatherinfo").toString();
			callbackDataPO = new CallbackDataPO("1", "成功",
					0, json);
			
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0", "失败",
					0,
					null);
			e.printStackTrace();
		}

		return JSON_DATA;
	}
	// 获得天气 图片 信息等
	public String getWeatherParam() {
		

		try {
		//	Map<String, String> result = WebServiceUtil.getURLWeather("郑州");
			String s = WebServiceUtil.getURLWeather("郑州");
		//	JSONObject json = JSONObject.fromObject(result);
			callbackDataPO = new CallbackDataPO("1", s,
					0, null);
			
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0", "失败",
					0,
					null);
			e.printStackTrace();
		}

		return JSON_DATA;
	}
	
	/**
	 * 手动更新
	 */
	public String doContentDetail() throws Exception {
		SensorDetailList detailList = new SensorDetailList();
		List<SensorDetail> sensorDetailList = new ArrayList<SensorDetail>();
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
				d002PO = new D002PO();
				d002PO.setMa002(userId);
				List<D002PO> d002List = d002Service
						.retrieveInstanceByPO(d002PO);
				for (D002PO d002 : d002List) {
					// d002.setMa004("050000000001"); //FIXME　测试VOC
					String jsonStr = WebServiceUtil.doWebservice(d002.getMa004(),
							d002.getMa003(), d002.getMa008(),
							"retrieveCurStautsByGPRSId");
					if (jsonStr != null && jsonStr.length()>5) {
						SensorDetail sensorDetail = null;
						try {
							
							Map<String, BinUpdatePo> map = D002ServiceImpl.getBinVersionCacheMap();
							
							sensorDetail = new Gson().fromJson(
									jsonStr, SensorDetail.class);
							
							int days = 0 ;
							if (map != null && map.get(sensorDetail.getSensorId())!=null){
							sensorDetail.setRemoteHardVersion(map.get(sensorDetail.getSensorId()).getRemoteHardVersion());
							
							sensorDetail.setRemoteSoftVersion(map.get(sensorDetail.getSensorId()).getRemoteSoftVersion());
							
							 days = (int)((new Date()).getTime() - map.get(sensorDetail.getSensorId()).getDate().getTime()/86400000);
							}
//							else {
//								((D002ServiceImpl)d002Service).query09(sensorDetail.getSensorId());
//							}
//							//先查询
//		
//							
//							if( days > 1){   //大于1天，去查询
//								((D002ServiceImpl)d002Service).query09(sensorDetail.getSensorId());
//							}
//						
//							
//							if (sensorDetail.getSensorId().startsWith("5")){   //A1
//								sensorDetail.setLocalHardVersion(AppFileUpLoad.localA1HardVersion);
//								sensorDetail.setLocalSoftVersion(AppFileUpLoad.localA1SoftVersion);
//							}
//							if (sensorDetail.getSensorId().startsWith("1")){     //R1
//								sensorDetail.setLocalHardVersion(AppFileUpLoad.localR1HardVersion);
//								sensorDetail.setLocalSoftVersion(AppFileUpLoad.localR1SoftVersion);
//							}
							
							//查询控制设备信息,如果没查到，赋值为kong
//							SensorCtrlDetail sensorCtrlDetail = sensorDetail.getCtrl();
//							ControlDevicePO po = new ControlDevicePO();
//							po.setMa004(sensorCtrlDetail.getDeviceId());
//							List<ControlDevicePO> pos = controlDeviceService.retrieveInstanceByPO(po);
//							if (pos==null || pos.size()!= 1) {
//								sensorDetail.setCtrl(null);
//							}
							
							

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sensorDetailList.add(sensorDetail);
					}
				}
				//查询控制设备
				ControlDevicePO po = new ControlDevicePO();
				po.setMa002(userId);
				List<ControlDevicePO> pos = controlDeviceService.retrieveInstanceByPO(po);
				if (pos!=null) {
					for (ControlDevicePO controlDevicePO : pos) {
						String jsonStr = WebServiceUtil.doWebservice(controlDevicePO.getMa004(),
								"4", controlDevicePO.getMa012(),
								"retrieveCurStautsByGPRSId");
						if (jsonStr != null && jsonStr.length()>5) {
							SensorDetail sensorDetail = null;
							try {
								

								sensorDetail = new Gson().fromJson(
										jsonStr, SensorDetail.class);
								sensorDetail.setSensorId(controlDevicePO.getMa004());

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							sensorDetailList.add(sensorDetail);
						}
						
					}
				}
				
				
				detailList.setSensorList(sensorDetailList);
				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_SUCCESS,
						sensorDetailList.size(), null,
						JSONArray.fromObject(detailList), "D002");
			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
						"D002");
			}
		}
		return JSON_DATA;
	}
	/**
	 * 接口：历史明细
	 */
	public String doListDetailHistory() throws Exception {
		SensorDetailList detailList = new SensorDetailList();
		List<SensorDetail> sensorDetailList = new LinkedList<SensorDetail>();
		List<SensorDetail> list = new LinkedList<SensorDetail>();
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String sensorId = request.getParameter("SENSORID");
		int typeOfHis = request.getParameter("TYPE")==null?2:Integer.parseInt(request.getParameter("TYPE")); 
		int lastDaysOrHours = request.getParameter("LASTPAR")== null ? 7:Integer.parseInt(request.getParameter("LASTPAR")); 
		//int typeOfHis = 2;//Integer.parseInt(request.getParameter("TYPE"));                  // 1  代表历史小时   2 代表历史天
		//int lastDaysOrHours = 7;//Integer.parseInt(request.getParameter("LASTPAR"));         //代表过去的具体数字
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
				d002PO = new D002PO();
				d002PO.setMa002(userId);
				List<D002PO> d002List = d002Service
						.retrieveInstanceByPO(d002PO);
				for (D002PO d002 : d002List) {
					if (!StringUtils.isBlank(sensorId) && d002.getMa004().equals(sensorId)) {
						String jsonStr = WebServiceUtil.doHisWebservice(d002.getMa004(),
								d002.getMa003(),lastDaysOrHours,typeOfHis,
								"retrieveHisStautsByGPRSId");
						if (jsonStr != null && jsonStr.length()>5) {
							sensorDetailList = new Gson().fromJson(jsonStr,
									new TypeToken<List<SensorDetail>>() {
									}.getType());
							list.addAll(sensorDetailList);
						}
						break;
					}
					if (StringUtils.isBlank(sensorId) ) { 
						String jsonStr = WebServiceUtil.doHisWebservice(d002.getMa004(),
								d002.getMa003(),lastDaysOrHours,typeOfHis,
								"retrieveHisStautsByGPRSId");
						if (jsonStr != null && jsonStr.length()>5) {
							sensorDetailList = new Gson().fromJson(jsonStr,
									new TypeToken<List<SensorDetail>>() {
									}.getType());
							list.addAll(sensorDetailList);
						}
					}
					
				}
				detailList.setSensorList(list);
				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_SUCCESS, list.size(), null,
						JSONArray.fromObject(detailList), "D002");
			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
						"D002");
			}
		}
		return JSON_DATA;
	}
	/**
	 * 接口：B端获取历史明细
	 */
	public String doListDetailHistoryB() throws Exception {
		SensorDetailList detailList = new SensorDetailList();
		List<SensorDetail> sensorDetailList = new LinkedList<SensorDetail>();
		List<SensorDetail> list = new LinkedList<SensorDetail>();

		String sensorId = request.getParameter("SENSORID");
		int typeOfHis = request.getParameter("TYPE")==null?2:Integer.parseInt(request.getParameter("TYPE")); 
		int lastDaysOrHours = request.getParameter("LASTPAR")== null ? 7:Integer.parseInt(request.getParameter("LASTPAR")); 
		//int typeOfHis = 2;//Integer.parseInt(request.getParameter("TYPE"));                  // 1  代表历史小时   2 代表历史天
		//int lastDaysOrHours = 7;//Integer.parseInt(request.getParameter("LASTPAR"));         //代表过去的具体数字
		if (StringUtils.isBlank(sensorId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else {
			

						String jsonStr = WebServiceUtil.doHisWebservice(sensorId,
								"2",lastDaysOrHours,typeOfHis,
								"retrieveHisStautsByGPRSId");
						if (jsonStr != null && jsonStr.length()>5) {
							sensorDetailList = new Gson().fromJson(jsonStr,
									new TypeToken<List<SensorDetail>>() {
									}.getType());
							list.addAll(sensorDetailList);
						}
	

				detailList.setSensorList(list);
				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_SUCCESS, list.size(), null,
						JSONArray.fromObject(detailList), "D002");
			
		}
		return JSON_DATA;
	}
	/**
	 * 实时数据   toB
	 */
	public String doContentDetailB() throws Exception {
		SensorDetailList detailList = new SensorDetailList();
		List<SensorDetail> sensorDetailList = new ArrayList<SensorDetail>();
		String sensorId = request.getParameter("SENSORID");
		
		if (StringUtils.isBlank(sensorId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else {

			
					String jsonStr = WebServiceUtil.doWebservice(sensorId,
							"2", "",
							"retrieveCurStautsByGPRSId");
					if (jsonStr != null && jsonStr.length()>5) {
						SensorDetail sensorDetail = null;
						try {
							
							Map<String, BinUpdatePo> map = D002ServiceImpl.getBinVersionCacheMap();
							
							sensorDetail = new Gson().fromJson(
									jsonStr, SensorDetail.class);
							
							int days = 0 ;
							if (map != null && map.get(sensorDetail.getSensorId())!=null){
							sensorDetail.setRemoteHardVersion(map.get(sensorDetail.getSensorId()).getRemoteHardVersion());
							
							sensorDetail.setRemoteSoftVersion(map.get(sensorDetail.getSensorId()).getRemoteSoftVersion());
							
							 days = (int)((new Date()).getTime() - map.get(sensorDetail.getSensorId()).getDate().getTime()/86400000);
							}

							
							

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						sensorDetailList.add(sensorDetail);
				
					}
				detailList.setSensorList(sensorDetailList);
				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_SUCCESS,
						sensorDetailList.size(), null,
						JSONArray.fromObject(detailList), "D002");
	}

		return JSON_DATA;
	}
	
	
	/**
	 * 接口：获得pm2.5
	 */
	public String getPM25(){
		String cityISO = request.getParameter("city");
		System.out.println(cityISO);
		
		String pm25Value="-1";
		String pm25State="无法获取";
		String JString = "";
		String html = null;  
		HttpClient client = new HttpClient();
        // 设置代理服务器地址和端口      
        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
        // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
		HttpMethod method=new GetMethod("http://api.lib360.net/open/pm2.5.json?city=" + cityISO);
        //使用POST方法
        //HttpMethod method = new PostMethod("http://java.sun.com");
        try {
			client.executeMethod(method);
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
        try {
			JString = method.getResponseBodyAsString();
			JSONObject json = JSONObject.fromObject(JString);
			pm25Value = json.get("pm25").toString();
			int pmValue = Integer.parseInt(pm25Value);
			if (pmValue >= 0 && pmValue <= 50) {
				pm25State = "优";
			}
			if (pmValue >= 51 && pmValue <= 100) {
				pm25State = "良好";
			}
			if (pmValue >= 101 && pmValue <= 150) {
				pm25State = "轻度污染";
			}
			if (pmValue >= 151 && pmValue <= 200) {
				pm25State = "中度污染";
			}
			if (pmValue >= 201 && pmValue <= 300) {
				pm25State = "重度污染";
			}
			if (pmValue >= 301) {
				pm25State = "严重污染";
			}
			callbackDataPO = new CallbackDataPO("0",
					pm25State, pmValue, null,
					"weather");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			callbackDataPO = new CallbackDataPO("1",
					pm25State, -1, null,
					"weather");
		}
        //释放连接
        method.releaseConnection();
       return JSON_DATA;
	}
	
	//大屏显示
	public String doView(){
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
			 return JSON_DATA;
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
			 return JSON_DATA;
		} else {
			U001PO u001PO;
			try {
				u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					InfomationPO conditionPo = new InfomationPO();
					conditionPo.setMa003(userId);
					try {
						List<InfomationPO> messagePOs = bigScreenService.retrieveMessageByPO(conditionPo);
						if (messagePOs != null && messagePOs.size()==1) {
							messagePO = messagePOs.get(0);
						}
					} catch (Exception e) {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
						
						e.printStackTrace();
						 return JSON_DATA;
					}
				}
				else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
					 return JSON_DATA;
				}
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
				e1.printStackTrace();
				 return JSON_DATA;
			}
			
			
		}
		return "bigscreen";
	}
	
	//上传广告图片
public String uploadPic() {
			String userid = request.getParameter("USER");
			byte[] fileByte = null;
		
			if (StringUtils.isBlank(userid)) {
				callbackDataPO = new CallbackDataPO("0", "失败", 1,
						null,
						null, "Business");
				return JSON_DATA;
			}
	//		if (pictype.equals("yyzz")) {
				if (file != null) {
		
					fileByte = fileToStream(file);
					
					AdvertisePO advertisePO = new AdvertisePO();
					advertisePO.setMa003(fileByte);
					advertisePO.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
					advertisePO.setMa002(userid);
					advertisePO.setMa004(request.getParameter("MA004"));
					if (request.getParameter("MA006")!=null){
						advertisePO.setMa006("3");
					}else {
						advertisePO.setMa006("2");
					}
					
					try {
						bigScreenService.insertAdvertise(advertisePO);
						advertisePO.setMa003(null);
						callbackDataPO = new CallbackDataPO("1", "成功", 1,
								base64.toString(fileByte),
								JSONObject.fromObject(advertisePO), "Business");
					} catch (Exception e) {
						callbackDataPO = new CallbackDataPO("0", "失败", 1,
								null,
								null, "weather");
						e.printStackTrace();
					}
					
					
				}
	//		}
			/*if (pictype.equals("logo_s")) {
				if (file_logo_s != null) {
					fileByte = fileToStream(file_logo_s);
					callbackDataPO = new CallbackDataPO("1", "成功", 1,
							base64.toString(fileByte),
							null, "Business");
					businessPO.setMa012(fileByte);
				}
				
			}
			if (pictype.equals("logo_b")) {
				if (file_logo_b != null) {
					fileByte = fileToStream(file_logo_b);
					callbackDataPO = new CallbackDataPO("1", "成功", 1,
							base64.toString(fileByte),
							null, "Business");
					businessPO.setMa013(fileByte);
				}
				
			}
			*/
			
	/*		if (file != null) {
				fileByte = fileToStream(file);
				callbackDataPO = new CallbackDataPO("1", "成功", 1,
						base64.toString(fileByte),
						null, "Business");
			
			}*/
			return JSON_DATA;
}
		/**
		 * 
		 * 将文件对象转换成 byte数组
		 * 
		 * @param file
		 * @return
		 * @author 刘晓斌
		 * @create_time 2014年10月21日上午10:41:24
		 */
		private byte[] fileToStream(File file) {
			InputStream is = null;
			ByteArrayOutputStream baos = null;
			byte[] b = new byte[4096];
			int len = -1;
			try {
				is = new FileInputStream(file);
				baos = new ByteArrayOutputStream(4096);
				while ((len = is.read(b)) != -1) {
					baos.write(b, 0, len);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					is.close();
					baos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return baos.toByteArray();
		}
/**
		 * 接口：获取广告
		 * 
*/
		  public String getAdvertise(){
			  String sessionId = request.getParameter("SESSIONID");
				String userId = request.getParameter("USERID");
				
				if (StringUtils.isBlank(sessionId)) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

				} else if (StringUtils.isBlank(userId)) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

				} else {
					U001PO u001PO;
					try {
						u001PO = u001Service.checkUserBySessionId(sessionId);
						if (u001PO != null) {
							AdvertisePO condition = new AdvertisePO();
							condition.setMa002(userId);
							if(request.getParameter("MA006")!=null){
								condition.setMa006("3");
							}
							List<AdvertisePO> result = new ArrayList<AdvertisePO>();
							result = bigScreenService.retrieveAdvertiseByPO(condition);
							for (AdvertisePO tempPo : result) {
							
								tempPo.setMa008(base64.toString(tempPo.getMa003()));
								tempPo.setMa003(null);
							}
							callbackDataPO = new CallbackDataPO("1",
									"请求成功", result.size(), null, JSONArray.fromObject(result), "D002");
						}
						else {
							callbackDataPO = new CallbackDataPO("0",
									SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
					
						}
					} catch (Exception e1) {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
						e1.printStackTrace();
					
					}
					
					
				}
				return JSON_DATA;
			  
		  }
		  /**
			 * 接口：更新广告信息
			 * 
	*/
			  public String updateAdvertise(){
				  String sessionId = request.getParameter("SESSIONID");
					String userId = request.getParameter("USERID");
					
					if (StringUtils.isBlank(sessionId)) {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

					} else if (StringUtils.isBlank(userId)) {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

					} else {
						U001PO u001PO;
						try {
							u001PO = u001Service.checkUserBySessionId(sessionId);
							if (u001PO != null) {
								AdvertisePO condition = new AdvertisePO();
								condition.setMa001(request.getParameter("MA001"));
								condition.setMa004(request.getParameter("MA004"));
								List<AdvertisePO> result = new ArrayList<AdvertisePO>();
								bigScreenService.updateAdvertise(condition);
								
								result = bigScreenService.retrieveAdvertiseByPO(condition);
								for (AdvertisePO tempPo : result) {
								
									tempPo.setMa008(base64.toString(tempPo.getMa003()));
									tempPo.setMa003(null);
								}
								callbackDataPO = new CallbackDataPO("1",
										"更新成功", result.size(), null, JSONArray.fromObject(result), "D002");
							}
							else {
								callbackDataPO = new CallbackDataPO("0",
										SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
						
							}
						} catch (Exception e1) {
							callbackDataPO = new CallbackDataPO("0",
									SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
							e1.printStackTrace();
						
						}
						
						
					}
					return JSON_DATA;
				  
			  }
		  public String getOneAdvertise(){
			  String sessionId = request.getParameter("SESSIONID");
				String userId = request.getParameter("USERID");
				
				if (StringUtils.isBlank(sessionId)) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

				} else if (StringUtils.isBlank(userId)) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

				} else {
					U001PO u001PO;
					try {
						u001PO = u001Service.checkUserBySessionId(sessionId);
						if (u001PO != null) {
							String ma001 = request.getParameter("MA001");
							AdvertisePO result = new AdvertisePO();
							result = bigScreenService.retrieveAdvertiseById(ma001);
							
							result.setMa008(base64.toString(result.getMa003()));
							result.setMa003(null);
						
							callbackDataPO = new CallbackDataPO("1",
									"请求成功", 1, null, JSONObject.fromObject(result), "D002");
						}
						else {
							callbackDataPO = new CallbackDataPO("0",
									SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
					
						}
					} catch (Exception e1) {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
						e1.printStackTrace();
					
					}
					
					
				}
				return JSON_DATA;
			  
		  }
/**
		 * 接口：删除广告
*/
		public String clearAdervitise(){

			String sessionId = request.getParameter("SESSIONID");
			String userId = request.getParameter("USERID");
			String ma001 = request.getParameter("MA001");
			if (StringUtils.isBlank(sessionId)) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

			} else if (StringUtils.isBlank(userId)) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

			} else {
				U001PO u001PO;
				try {
					u001PO = u001Service.checkUserBySessionId(sessionId);
					if (u001PO != null) {
						bigScreenService.deleteAdvertiseById(ma001);
						callbackDataPO = new CallbackDataPO("1",
								"清除成功", 0, null, null, "D002");
					}
					else {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
				
					}
				} catch (Exception e1) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
					e1.printStackTrace();
				
				}
				
				
			}
			 return JSON_DATA;
		}	
/**
		 * 接口：获取通知
*/	

  public String getBSMessage(){
	  String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

		} else {
			U001PO u001PO;
			try {
				u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					InfomationPO condition = new InfomationPO();
					condition.setMa003(userId);
					if (request.getParameter("haveDate")!=null){
						condition.setMa004(new Date());
					}
					List<InfomationPO> result = new ArrayList<InfomationPO>();
					result = bigScreenService.retrieveMessageByPO(condition);
					callbackDataPO = new CallbackDataPO("1",
							"请求成功", result.size(), null, JSONObject.fromObject(result.get(0)), "D002");
				}
				else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
			
				}
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
				e1.printStackTrace();
			
			}
			
			
		}
		return JSON_DATA;
	  
  }
/**
		 * 接口：设置通知
*/	
  public String setMessage(){
	  
	  String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String ma001 = request.getParameter("MA001");
		String ma002 = request.getParameter("MA002");
		String ma004 = request.getParameter("MA004");
		String ma005 = request.getParameter("MA005");
		String ma006 = request.getParameter("MA006");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "MESSAGE");

		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "MESSAGE");
	
		} else {
			U001PO u001PO;
			try {
				u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					if (ma001 != null && ma001.trim().length()>0) {
						bigScreenService.deleteMessageById(ma001);
					}
					
					messagePO.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
					messagePO.setMa003(userId);
					messagePO.setMa002(ma002);
					try {
						messagePO.setMa006(Integer.parseInt(ma006));
					} catch (Exception e) {
						messagePO.setMa006(0);
					}
					
					if (ma004 != null && ma004.length()>0) {

						messagePO.setMa004(new Date(ma004));
						messagePO.setMa005(new Date(ma005));
					}
					
					bigScreenService.insertMessage(messagePO);
					
					callbackDataPO = new CallbackDataPO("1",
							"设置成功", 0, null, JSONObject.fromObject(messagePO), "MESSAGE");
				}
				else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "MESSAGE");
			
				}
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "MESSAGE");
				e1.printStackTrace();
			
			}
			
			
		}
		 return JSON_DATA;
  }
  /**
	 * 接口：清除通知
*/	
public String clearMessage(){

	String sessionId = request.getParameter("SESSIONID");
	String userId = request.getParameter("USERID");
	String ma001 = request.getParameter("MA001");
	if (StringUtils.isBlank(sessionId)) {
		callbackDataPO = new CallbackDataPO("0",
				SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

	} else if (StringUtils.isBlank(userId)) {
		callbackDataPO = new CallbackDataPO("0",
				SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

	} else {
		U001PO u001PO;
		try {
			u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
				bigScreenService.deleteMessageById(ma001);
				callbackDataPO = new CallbackDataPO("1",
						"清除成功", 0, null, null, "D002");
			}
			else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
		
			}
		} catch (Exception e1) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
			e1.printStackTrace();
		
		}
		
		
	}
	 return JSON_DATA;
}	

/**
	 * 接口：展示
*/	
	public String retriveSetting() {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

		} else {
			U001PO u001PO;
			try {
				u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					SceneDispPO condition = new SceneDispPO();
					condition.setMa006(userId);
					List<SceneDispPO> result = new ArrayList<SceneDispPO>();
					result = bigScreenService.retrieveDispByPO(condition);
					callbackDataPO = new CallbackDataPO("1",
							"请求成功", result.size(), null, JSONArray.fromObject(result), "D002");
				}
				else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
			
				}
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
				e1.printStackTrace();
			
			}
			
			
		}
		return JSON_DATA;
	}
	/**
	 * 接口：删除展示
*/	
	public String delDispSetting() {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String ma001 = request.getParameter("MA001");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

		} else {
			U001PO u001PO;
			try {
				u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					
					bigScreenService.deleteDispById(ma001);
					
					SceneDispPO condition = new SceneDispPO();
					condition.setMa006(userId);
					List<SceneDispPO> result = new ArrayList<SceneDispPO>();
					result = bigScreenService.retrieveDispByPO(condition);
					callbackDataPO = new CallbackDataPO("1",
							"请求成功", result.size(), null, JSONArray.fromObject(result), "D002");
				}
				else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
			
				}
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
				e1.printStackTrace();
			
			}
			
			
		}
		return JSON_DATA;
	}
	/**
	 * 接口：添加展示
*/	
	public String addDispSetting() {
		
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String ma002 = request.getParameter("MA002");
		String ma003 = request.getParameter("MA003");
		String ma004 = request.getParameter("MA004");
		String ma005 = request.getParameter("MA005");
		String ma007 = request.getParameter("MA007");
		String ma008 = request.getParameter("MA008");
		String ma009 = request.getParameter("MA009");
		String ma011 = request.getParameter("MA011");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");

		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");

		} else {
			U001PO u001PO;
			try {
				u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					
					SceneDispPO po = new SceneDispPO();
					
					po.setMa002(ma002);
					po.setMa003(Integer.parseInt(ma003));
					po.setMa004(Integer.parseInt(ma004));
					po.setMa005(ma005);
					po.setMa006(userId);	
					po.setMa007(ma007);
					po.setMa008(ma008);
					po.setMa009(ma009);
					po.setMa011(ma011);
					List<SceneDispPO> tempList = bigScreenService.retrieveDispByPO(po);					
					if (tempList == null || tempList.size() == 0) {
						po.setMa001(UUID.randomUUID().toString().trim().replaceAll("-", ""));
						bigScreenService.insertDisp(po);
						po.setMa001(null);
						po.setMa002(null);
						List<SceneDispPO> result = bigScreenService.retrieveDispByPO(po);
						callbackDataPO = new CallbackDataPO("1",
								"请求成功", result.size(), null,JSONArray.fromObject(result), "D002");
					}else {
						callbackDataPO = new CallbackDataPO("0",
								"重复配置",0 , null,null, "D002");
					}
					
					
				}
				else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
			
				}
			} catch (Exception e1) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
				e1.printStackTrace();
			
			}
			
			
		}
		return JSON_DATA;
	}
	
	
	
	public IWeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(IWeatherService weatherService) {
		this.weatherService = weatherService;
	}
	public IControlDeviceService getControlDeviceService() {
		return controlDeviceService;
	}
	public void setControlDeviceService(IControlDeviceService controlDeviceService) {
		this.controlDeviceService = controlDeviceService;
	}
	public SensorGasDetail getSensorGasDetail() {
		return sensorGasDetail;
	}
	public void setSensorGasDetail(SensorGasDetail sensorGasDetail) {
		this.sensorGasDetail = sensorGasDetail;
	}
	public SensorAirDetail getSensorAirDetail() {
		return sensorAirDetail;
	}
	public void setSensorAirDetail(SensorAirDetail sensorAirDetail) {
		this.sensorAirDetail = sensorAirDetail;
	}
	public D002PO getD002PO() {
		return d002PO;
	}
	public void setD002PO(D002PO d002po) {
		d002PO = d002po;
	}
	public ID002Service getD002Service() {
		return d002Service;
	}
	public void setD002Service(ID002Service d002Service) {
		this.d002Service = d002Service;
	}
	public IU001Service getU001Service() {
		return u001Service;
	}
	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}
	public List<SensorDetail> getSensorDetailList() {
		return sensorDetailList;
	}
	public void setSensorDetailList(List<SensorDetail> sensorDetailList) {
		this.sensorDetailList = sensorDetailList;
	}

	public IBigScreenService getBigScreenService() {
		return bigScreenService;
	}

	public void setBigScreenService(IBigScreenService bigScreenService) {
		this.bigScreenService = bigScreenService;
	}

	public InfomationPO getMessagePO() {
		return messagePO;
	}

	public void setMessagePO(InfomationPO messagePO) {
		this.messagePO = messagePO;
	}
	
}
