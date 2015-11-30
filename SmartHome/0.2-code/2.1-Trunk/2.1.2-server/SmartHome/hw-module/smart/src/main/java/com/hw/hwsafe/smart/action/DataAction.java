package com.hw.hwsafe.smart.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.digester.xmlrules.FromXmlRuleSet;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.D002PO;
import com.hw.hwsafe.smart.pojo.DevicePO;
import com.hw.hwsafe.smart.pojo.DeviceSharePO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.smart.service.IDataService;
import com.hw.hwsafe.smart.service.IDeviceService;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.service.impl.D002ServiceImpl;
import com.hw.hwsafe.smart.util.WebServiceUtil;

public class DataAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private IDataService dataService;
	@Autowired
	private IU001Service u001Service;
	@Autowired
	private ID002Service d002Service;
	@Autowired
	private IDeviceService deviceService;
	
	
	private D002PO d002PO;

	public D002PO getD002PO() {
		return d002PO;
	}

	public void setD002PO(D002PO d002po) {
		d002PO = d002po;
	}

	// 获得网络商城地址
	public String doGetWebsite() throws Exception {
		String type = request.getParameter("TYPE");
		if (type != null) {
			if (type.equals("3")) {
				callbackDataPO = new CallbackDataPO("1", ConfConstants.WEB_DIY,
						0, null);
			}
			if (type.equals("5")) {
				callbackDataPO = new CallbackDataPO("1",
						ConfConstants.WEIDOU_SHOP, 0, null);
			}
			if (type.equals("6")) {
				callbackDataPO = new CallbackDataPO("1",
						ConfConstants.WEB_BUSINESS, 0, null);
			}
		} else {
			callbackDataPO = new CallbackDataPO("1", ConfConstants.WEIDOU_SHOP,
					0, null);
		}
		return JSON_DATA;
	}

	// 获得实时数据
	public String doViewDetail() throws Exception {

		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String deviceId = request.getParameter("DEVICEID");
		
		String fromPhone = request.getParameter("FROMPHONE");
		String toPhone = request.getParameter("TOPHONE");
		
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
				
				if(StringUtils.isBlank(toPhone)){
					//normal
					
					//判断D002表数据
					d002PO = new D002PO();
					d002PO.setMa002(userId);
					d002PO.setMa004(deviceId);
					
					List<D002PO> d002List = d002Service
							.retrieveInstanceByPO(d002PO);
					
					//判断AR_DEV表数据
					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", userId);
					map.put("deviceid", deviceId);
					
					//获得空气设备
					List<Map<String, String>> airDeviceList = deviceService.getDeviceAir(map);
					
					
					
					
					if ((d002List != null && d002List.size() > 0) || (airDeviceList != null && airDeviceList.size() > 0)) {
						
						
						//String jsonStr = WebServiceUtil.doWebservice(deviceId,
						//		"getCurDevice");
						String jsonStr = "11111111";
						if (jsonStr != null && jsonStr.length() > 5) {
							DevicePO devicePO = null;
							try {

								//devicePO = new Gson().fromJson(jsonStr, DevicePO.class);
								
								
								//测试
					            DevicePO deviceTest = new DevicePO();
					            Map<String, String> params = new HashMap<String, String>();
					            params.put(Integer.toHexString(0xD8) + "", "1460");    //PM2.5
					            params.put(Integer.toHexString(0xC9) + "", "13.6");    //温度
					            params.put(Integer.toHexString(0xCA) + "", "79.6");    //湿度
					            params.put(Integer.toHexString(0xD9) + "", "89");      //VOC
					            params.put(Integer.toHexString(0x30) + "", "1460");    //CO2
					            deviceTest.setParam(params);
					            
					            //FIXME
								callbackDataPO = new CallbackDataPO("1",
										SmartConstants.QUERY_DATA_SUCCESS, 1, null,
										JSONObject.fromObject(deviceTest), "Data");

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								callbackDataPO = new CallbackDataPO("0",
										SmartConstants.QUERY_DATA_FAILURE, 1, null,
										null, "Data");
							}

						}else{

						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.QUERY_DATA_FAILURE, 1, null, null,
								"Data");
						}
					
					}else{
						
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
								"D002");
						
					}
				}else{
					//share
					Map<String, String> map = new HashMap<String, String>();
					map.put("deviceid", deviceId);
					map.put("from", fromPhone);
					map.put("to", toPhone);
					List<DeviceSharePO> shareDeviceList = deviceService.getAllDeviceShare(map);
					if (shareDeviceList != null && shareDeviceList.size() > 0){
						
						
						//String jsonStr = WebServiceUtil.doWebservice(deviceId,
						//		"getCurDevice");
						String jsonStr = "11111111";
						if (jsonStr != null && jsonStr.length() > 5) {
							DevicePO devicePO = null;
							try {

								//devicePO = new Gson().fromJson(jsonStr, DevicePO.class);
								
								//测试
					            DevicePO deviceTest = new DevicePO();
					            Map<String, String> params = new HashMap<String, String>();
					            params.put(Integer.toHexString(0xD8) + "", "1460");    //PM2.5
					            params.put(Integer.toHexString(0xC9) + "", "13.6");    //温度
					            params.put(Integer.toHexString(0xCA) + "", "79.6");    //湿度
					            params.put(Integer.toHexString(0xD9) + "", "89");      //VOC
					            params.put(Integer.toHexString(0x30) + "", "1460");    //CO2
					            deviceTest.setParam(params);
					            
					            //FIXME
								
								callbackDataPO = new CallbackDataPO("1",
										SmartConstants.QUERY_DATA_SUCCESS, 1, null,
										JSONObject.fromObject(deviceTest), "Data");

							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								callbackDataPO = new CallbackDataPO("0",
										SmartConstants.QUERY_DATA_FAILURE, 1, null,
										null, "Data");
							}

						}else{

						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.QUERY_DATA_FAILURE, 1, null, null,
								"Data");
						}
					}else{
						
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
								"AR_DEV_SHARE");
					}
					
				}
				
				

				

			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
						"D002");
			}
		}
		return JSON_DATA;
	}

	// 设定数据
	public String setCurDevice() throws Exception {

		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String device = request.getParameter("DEVICEPO");

		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {

				String jsonStr = WebServiceUtil.doWebservice(device,
						"setCurDevice");
				if (jsonStr != null && jsonStr.length() > 5) {
					DevicePO devicePO = null;
					try {

						devicePO = new Gson().fromJson(jsonStr, DevicePO.class);

						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, 1, null,
								JSONObject.fromObject(devicePO), "Data");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_FAILURE, 1, null,
								null, "Data");
					}

				}

				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_FAILURE, 1, null, null,
						"Data");

			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
						"D002");
			}
		}
		return JSON_DATA;
	}

	// 获得配置信息
	public String getCurDeviceConfig() throws Exception {

		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String deviceId = request.getParameter("DEVICEID");

		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {

				String jsonStr = WebServiceUtil.doWebservice(deviceId,
						"getDeviceConfig");
				if (jsonStr != null && jsonStr.length() > 5) {
					DevicePO devicePO = null;
					try {

						devicePO = new Gson().fromJson(jsonStr, DevicePO.class);

						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, 1, null,
								JSONObject.fromObject(devicePO), "Data");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_FAILURE, 1, null,
								null, "Data");
					}

				}

				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_FAILURE, 1, null, null,
						"Data");

			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
						"Data");
			}
		}
		return JSON_DATA;
	}
	// 设定数据配置
	public String setCurDeviceConfig() throws Exception {

		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String device = request.getParameter("DEVICEPO");

		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {

				String jsonStr = WebServiceUtil.doWebservice(device,
						"setDeviceConfig");
				if (jsonStr != null && jsonStr.length() > 5) {
					DevicePO devicePO = null;
					try {

						devicePO = new Gson().fromJson(jsonStr, DevicePO.class);

						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, 1, null,
								JSONObject.fromObject(devicePO), "Data");

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_FAILURE, 1, null,
								null, "Data");
					}

				}

				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_FAILURE, 1, null, null,
						"Data");

			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
						"Data");
			}
		}
		return JSON_DATA;
	}
}
