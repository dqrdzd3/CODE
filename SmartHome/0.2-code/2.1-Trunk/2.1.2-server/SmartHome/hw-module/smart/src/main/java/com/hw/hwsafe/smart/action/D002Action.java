package com.hw.hwsafe.smart.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.namespace.QName;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.axiom.om.OMElement;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hw.hwsafe.attachment.util.AppFileUpLoad;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.BinUpdatePo;
import com.hw.hwsafe.smart.pojo.ControlDevicePO;
import com.hw.hwsafe.smart.pojo.D002PO;
import com.hw.hwsafe.smart.pojo.NoisePO;
import com.hw.hwsafe.smart.pojo.SensorAirDetail;
import com.hw.hwsafe.smart.pojo.SensorCtrlDetail;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.hwsafe.smart.pojo.SensorDetailList;
import com.hw.hwsafe.smart.pojo.SensorGasDetail;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IControlDeviceService;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.smart.service.IP2BService;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.smart.service.impl.D002ServiceImpl;
import com.hw.hwsafe.smart.util.AllProtocol;
import com.hw.hwsafe.smart.util.CodecUtil;
import com.hw.hwsafe.smart.util.DeviceUtil;
import com.hw.hwsafe.smart.util.WebServiceUtil;
import com.hw.hwsafe.utils.UUIDGenerater;

public class D002Action extends BaseAction {

	@Autowired
	private ID002Service d002Service;

	@Autowired
	private IU001Service u001Service;
	
	@Autowired
	private IControlDeviceService controlDeviceService;
	
	@Autowired
	private IP2BService p2BService;

	private SensorGasDetail sensorGasDetail;

	private SensorAirDetail sensorAirDetail;

	private D002PO d002PO;

	private String ids;

	public String doAdd() {
		return "add";
	}

	public String doSaveAdd() {
		d002PO.setMa001(UUIDGenerater.getUUID());
		d002PO.setMa002(SessionUtil.getUserId());
		d002PO.setMa005(new Date());
		try {
			d002Service.insertD002(d002PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			setAddFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doEdit() {
		try {
			d002PO = d002Service.retrieveInstanceById(d002PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}

	public String doSaveEdit() {
		try {
			d002Service.updateD002(d002PO);
			setUpdateSuccessMsg();
		} catch (Exception e) {
			setUpdateFailedMsg();
			e.printStackTrace();
		}
		return JSON_MSG;
	}

	public String doDelete() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("idArray", ids.split(","));
			d002Service.delBatchD002(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}

	public String doShow() {
		try {
			d002PO = d002Service.retrieveInstanceById(d002PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	public String doShowInstant() {
		try {
			d002PO = d002Service.retrieveInstanceById(d002PO.getMa001());
			String jsonStr = WebServiceUtil.doWebservice(d002PO.getMa004(),
					d002PO.getMa003(), d002PO.getMa008(),
					"retrieveCurStautsByGPRSId");
			if (jsonStr != null) {
				SensorDetail sensorDetail = new Gson().fromJson(jsonStr,
						SensorDetail.class);
				if ("1".equals(d002PO.getMa003())) {
					sensorGasDetail = sensorDetail.getGas();
					return "gasinstant";
				} else if ("2".equals(d002PO.getMa003())) {
					sensorAirDetail = sensorDetail.getAir();
					return "airinstant";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String doShowHistory1() throws Exception {
		d002PO = d002Service.retrieveInstanceById(d002PO.getMa001());
		return "showhis";
	}
	//获得网络商城地址
	public String doGetWebsite() throws Exception {
		String type = request.getParameter("TYPE");
		if (type!=null) {
			if (type.equals("3")) {
				callbackDataPO = new CallbackDataPO("1", ConfConstants.WEB_DIY,
						0,
						null);
			}
			if (type.equals("5")) {
				callbackDataPO = new CallbackDataPO("1", ConfConstants.WEIDOU_SHOP,
						0,
						null);
			}
			if (type.equals("6")) {
				callbackDataPO = new CallbackDataPO("1", ConfConstants.WEB_BUSINESS,
						0,
						null);
			}
		}else{
			callbackDataPO = new CallbackDataPO("1", ConfConstants.WEIDOU_SHOP,
					0,
					null);
		}
		return JSON_DATA;
	}
	public String doShowHistory() {
		try {
			List<SensorDetail> sensorDetailList = new LinkedList<SensorDetail>();
			d002PO = d002Service.retrieveInstanceById(d002PO.getMa001());
			String jsonStr = WebServiceUtil.doWebservice(d002PO.getMa004(),
					d002PO.getMa003(), d002PO.getMa008(),
					"retrieveHisStautsByGPRSId");
			if (jsonStr != null) {
				sensorDetailList = new Gson().fromJson(jsonStr,
						new TypeToken<List<SensorDetail>>() {
						}.getType());

			}
			callbackDataPO = new CallbackDataPO("1", "查询数据成功",
					sensorDetailList.size(),
					JSONArray.fromObject(sensorDetailList));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_DATA;
	}
	
	/**
	 * 接口：发现设备或新增设备
	 */
	public String doSaveAddEquips() {
		try {
			prepareData();
			if (AppFileUpLoad.isCheckDeviceInDB) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ma002", d002PO.getMa004());
				Integer intR = d002Service.retrieveDeviceFromP001(map);
				if (intR == 0) {
					callbackDataPO = new CallbackDataPO("0", "请核实设备！", 0, null,
							null, "P001");
					return JSON_DATA;
				}
//				if (intR > 1) {
//					callbackDataPO = new CallbackDataPO("0", "设备已注册！",
//							0, null, null, "P001");
//					return JSON_DATA;
//				}
			}

			
			List<D002PO> d002POList = d002Service
					.retrieveDuplicateInstanceByPO(d002PO);
			if (d002POList != null && d002POList.size() > 0) {
				callbackDataPO = new CallbackDataPO("1",
						"设备已注册",d002POList.size(), null, null,
						"D002");
			} else {
				d002PO.setMa001(UUIDGenerater.getUUID());
				List<SensorDetail> sensorList = d002Service
						.insertInstance(d002PO);
				SensorDetailList sensorDetailList = new SensorDetailList();
				sensorDetailList.setSensorList(sensorList);
				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.ADD_DRIVER_SUCCESS, sensorList.size(),
						null, JSONArray.fromObject(sensorDetailList), "D002");
			}
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.ADD_DRIVER_FAILURE, 0, null, null, "D002");
			e.printStackTrace();
		}

		return JSON_DATA;
	}

	/**
	 * 接口：修改或更新设备
	 */
	public String doSaveEditEquips() throws Exception {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String driverId = request.getParameter("DRIVERID");
		String drivewName = request.getParameter("DRIVERNAME");
		String driveType = request.getParameter("CTRL");
		String message = null;
		if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(driverId)) {
			message = SmartConstants.DRIVER_ID_ISNULL;
		} else if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
			
				prepareData();
				if (driveType == null || driveType.length() == 0) {
					if (AppFileUpLoad.isCheckDeviceInDB) {

						Map<String, Object> map = new HashMap<String, Object>();
						map.put("ma002", d002PO.getMa004());
						Integer intR = d002Service.retrieveDeviceFromP001(map);
						if (intR == 0) {
							callbackDataPO = new CallbackDataPO("0",
									"当前设备未注册入库，请核实！", 0, null, null, "P001");
							return JSON_DATA;
						}
						if (intR > 1) {
							callbackDataPO = new CallbackDataPO("0",
									"当前设备异常，请联系售后人员！", 0, null, null, "P001");
							return JSON_DATA;
						}
					}
					d002PO.setMa002(userId);
					d002PO.setMa004(driverId);
					d002PO.setMa008(drivewName);
					List<SensorDetail> sensorList = d002Service
							.updateInstance(d002PO);
					SensorDetailList sensorDetailList = new SensorDetailList();
					sensorDetailList.setSensorList(sensorList);
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.EDIT_DRIVER_SUCCESS, sensorList.size(),
							null, JSONArray.fromObject(sensorDetailList), "D002");
				}else {
					ControlDevicePO conditionPo = new ControlDevicePO();
					conditionPo.setMa004(driverId);
					conditionPo.setMa012(drivewName);
					controlDeviceService.updateInstanceName(conditionPo);
					SensorDetailList sensorDetailList = new SensorDetailList();
					List<SensorDetail> sensorList = d002Service.covertionDriver(userId);
					sensorDetailList.setSensorList(sensorList);
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.EDIT_DRIVER_SUCCESS, sensorList.size(),
							null, JSONArray.fromObject(sensorDetailList), "D002");
				}
				

			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, "D002");
			}
		}
		return JSON_DATA;
	}

	/**
	 * 接口：删除设备
	 */
	public String doDeleteEquips() throws Exception {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String driverId = request.getParameter("DRIVERID");
		String message = null;
		if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(driverId)) {
			message = SmartConstants.DRIVER_ID_ISNULL;
		} else if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "D002");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
				d002PO = new D002PO();
				d002PO.setMa002(userId);
				d002PO.setMa004(driverId);
				List<SensorDetail> sensorList = d002Service.deleteInstanceById(
						d002PO, u001PO.getMa001());
				SensorDetailList sensorDetailList = new SensorDetailList();
				sensorDetailList.setSensorList(sensorList);
				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_SUCCESS, sensorList.size(),
						null, JSONArray.fromObject(sensorDetailList), "D002");
			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, "D002");
			}
		}
		return JSON_DATA;
	}

	/**
	 * 接口：查看已发现的设备
	 */
	public String doListEquip() throws Exception {
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		if (StringUtils.isBlank(sessionId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, "D002");
			throw new NullArgumentException("SESSIONID");
		} else if (StringUtils.isBlank(userId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.USER_ID_ISNULL, 0, null, "D002");
			throw new NullArgumentException("USERID");
		} else {
			U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
			if (u001PO != null) {
				try {
					List<SensorDetail> list = d002Service
							.covertionDriver(userId);
					SensorDetailList sensorList1 = new SensorDetailList();
					sensorList1.setSensorList(list);
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.QUERY_DATA_SUCCESS, list.size(),
							new Gson().toJson(sensorList1),
							JSONArray.fromObject(sensorList1), "D002");
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.QUERY_DATA_FAILURE, 0, null, "D002");
				}
			} else {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, "D002");
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
	 * 手动更新
	 */
	public String doViewDetail() throws Exception {
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
							}else {
								((D002ServiceImpl)d002Service).query09(sensorDetail.getSensorId());
							}
							//先查询
		
							
							if( days > 1){   //大于1天，去查询
								((D002ServiceImpl)d002Service).query09(sensorDetail.getSensorId());
							}
						
							
							if (sensorDetail.getSensorId().startsWith("5")){   //A1
								sensorDetail.setLocalHardVersion(AppFileUpLoad.localA1HardVersion);
								sensorDetail.setLocalSoftVersion(AppFileUpLoad.localA1SoftVersion);
							}
							if (sensorDetail.getSensorId().startsWith("1")){     //R1
								sensorDetail.setLocalHardVersion(AppFileUpLoad.localR1HardVersion);
								sensorDetail.setLocalSoftVersion(AppFileUpLoad.localR1SoftVersion);
							}
							
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
	 * 噪音数据上传
	 */
	public String setNoiseData(){
		
		String sensorId = request.getParameter("SENSORID");
		String strDbValue = request.getParameter("DBVALUE"); 
		Double dbValue = 0.0;
		if (StringUtils.isBlank(sensorId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "D002");
		} else {
			
			try {
				
				dbValue = Double.valueOf(strDbValue);
					Map<String, NoisePO> map = D002ServiceImpl.getNoiseCacheMap();
					NoisePO po = new NoisePO();
					po.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
					po.setSensorId(sensorId);
					po.setdBValue(dbValue);
					po.setCreateTime(new Date());
					map.put(po.getSensorId(), po);
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.QUERY_DATA_SUCCESS, 1, null,
							JSONArray.fromObject(po), "D002");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return JSON_DATA;
	}
	/**
	 * 噪音数据获得
	 */
	public String getNoiseData(){
		String sensorId = request.getParameter("SENSORID");

		if (StringUtils.isBlank(sensorId)) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.SESSION_ID_ISNULL, 0, null, null, "NOISE");
		} else {
			
			try {
				
			
					Map<String, NoisePO> map = D002ServiceImpl.getNoiseCacheMap();
					NoisePO po = map.get(sensorId);
				
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.QUERY_DATA_SUCCESS, 1, null,
							JSONArray.fromObject(po), "NOISE");
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				callbackDataPO = new CallbackDataPO("o",
						SmartConstants.QUERY_DATA_FAILURE, 0, null,
						null, "NOISE");
			}
			
		}
		return JSON_DATA;
	}
	/**
	 * 生产专用
	 */
	public String getDetailForPro() throws Exception {
		String pass = request.getParameter("PASSWORD");
		String sensorId = request.getParameter("SENSORID");
		String deviceType = request.getParameter("DEVICETYPE");
		if (StringUtils.isBlank(sensorId)) {
			callbackDataPO = new CallbackDataPO("0",
					"id为空", 0, null, null, "D002");
		} else if (StringUtils.isBlank(pass)) {
			callbackDataPO = new CallbackDataPO("0",
					"密码为空", 0, null, null, "D002");
		} else if (!StringUtils.equals(pass, ConfConstants.PRODUCT_TOKEN)){
			callbackDataPO = new CallbackDataPO("0",
					"密码错误", 0, null, null, "D002");
		}else {
			
			String jsonStr = WebServiceUtil.doWebservice(sensorId,
					deviceType, "",
					"retrieveCurStautsByGPRSId");
			if (jsonStr != null && jsonStr.length()>5) {
				SensorDetail sensorDetail = null;

					sensorDetail = new Gson().fromJson(
							jsonStr, SensorDetail.class);
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.QUERY_DATA_SUCCESS,
							1, null,
							JSONObject.fromObject(sensorDetail), "D002");
			}else {
				callbackDataPO = new CallbackDataPO("1",
						SmartConstants.QUERY_DATA_SUCCESS,
						1, null,
						"", "D002");
			}
		}
		
		return JSON_DATA;
	}

	/**
	 * 同步文件中的数据到数据库P001中
	 */
	public String syncData() throws Exception {
		try {

			
			if (request.getParameter("from")!=null) {
				
				int mFrom = Integer.parseInt(request.getParameter("from"));
				int mTo = Integer.parseInt(request.getParameter("to"));
				String mType = request.getParameter("type");    // 5 表示 空气      1表示燃气    
				
				if (!mType.equals("5")&&!mType.equals("1")) {
					callbackDataPO = new CallbackDataPO("0", "类型错误！", 0, null,
							null, "P001");
					return JSON_DATA;
				}
				
				List<String> result = insertSync(mFrom,mTo,mType);
				if (result==null) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.ADD_DRIVER_FAILURE, 0,
							null, null, "D002");
				}else if (result.size()>0) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.ADD_DRIVER_FAILURE, result.size(),
							null, JSONArray.fromObject(result), "D002");
				}else {
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.ADD_DRIVER_SUCCESS, 0,
							null, null, "D002");
				}

			}
			else {   //查询文本获得
				String path = this.getClass().getClassLoader().getResource("/").getPath()+AppFileUpLoad.syncFilename;
				List<String> result = insertSyncFromFile(path);
				if (result==null) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.ADD_DRIVER_FAILURE,0,
							null, null, "D002");
				}else if (result.size()>0) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.ADD_DRIVER_FAILURE, result.size(),
							null, JSONArray.fromObject(result), "D002");
				}else{
					callbackDataPO = new CallbackDataPO("1",
							SmartConstants.ADD_DRIVER_SUCCESS, 0,
							null, null, "D002");
				}

			}
	           
		

			
		} catch (Exception e) {
			callbackDataPO = new CallbackDataPO("0",
					SmartConstants.ADD_DRIVER_FAILURE, 0, null, null, "D002");
			e.printStackTrace();
		}

		return JSON_DATA;
	}
	//计算同步数据过程中出错的设备号
	private List<String> insertSync(int from,int to,String type){
		List<String> errorDevice = new ArrayList<String>();
		for (int j = from; j <= to; j++) {
			byte[] b = CodecUtil.HexString2Bytes(type+String.format("%07X", j));
	    	
	    	
	    	

	  	    	byte[] test = CodecUtil.XorBytes(b);    //异或
	  	    	
	  	    	short[] data =  CodecUtil.crc16Shorts(test);
	  	    	
	  	    	short d = AllProtocol.CRC16(data);   //crc����
	  	    	
	  	    	
	  	       byte[] crc =  CodecUtil.short2bytes(d);
	  	      
	  	       
	  	        byte[] testc = new byte[test.length + 2];
	  	        for (int i = 0; i < b.length; i++) {
	  	            testc[i] = b[i];
	  	        }
	  	        testc[test.length] = crc[1];
	  	        testc[test.length + 1] = crc[0];

	  	      String filename =  CodecUtil.Bytes2HexString(testc);
	  	  //检验唯一性
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ma002", filename.toUpperCase());
				Integer intR;
				try {
					intR = d002Service.retrieveDeviceFromP001(map);
					if (intR > 0) {
						errorDevice.add(filename.toUpperCase());
						continue;
					}
				} catch (Exception e1) {
					errorDevice.add(filename.toUpperCase());
				
					e1.printStackTrace();
					continue;
				}
		
				
				map.put("ma001", UUIDGenerater.getUUID());
				map.put("ma003", "");
				map.put("ma004", "");
				map.put("ma005", "");
				map.put("ma006", new Date());
				try {
					d002Service.insertInstanceP(map);
					
				} catch (Exception e) {
					errorDevice.add(filename.toUpperCase());
					e.printStackTrace();
					continue;
				}
			
	  	      
		}
		
		
		return errorDevice;
	}
	
	//计算同步数据过程中出错的设备号
	private List<String> insertSyncFromFile(String path){
		List<String> errorDevice = new ArrayList<String>();
		String content = readToString(path);
		if (content==null || content.length()==0) {
			return null;
		}
		String[] devices = content.split(",");
		for (int i = 0; i < devices.length; i++) {
			   String filename = devices[i];
			  	  //检验唯一性
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("ma002", filename.toUpperCase().trim());
						Integer intR;
						try {
							intR = d002Service.retrieveDeviceFromP001(map);
							if (intR > 0) {
								errorDevice.add(filename.toUpperCase());
								continue;
							}
						} catch (Exception e1) {
							errorDevice.add(filename.toUpperCase());
						
							e1.printStackTrace();
							continue;
						}
				
						
						map.put("ma001", UUIDGenerater.getUUID());
						map.put("ma003", "");
						map.put("ma004", "");
						map.put("ma005", "");
						map.put("ma006", new Date());
						try {
							d002Service.insertInstanceP(map);
							
						} catch (Exception e) {
							errorDevice.add(filename.toUpperCase());
							e.printStackTrace();
							continue;
						}
		}
		
		return errorDevice;
	}
	/**
	 * 	//硬件升级
	 * @return
	 * @throws Exception
	 */

	public String checkUpgrade() throws Exception {
		String sessionId = request.getParameter("SESSIONID");
		String userId = request.getParameter("USERID");
		String driverId = request.getParameter("DRIVERID");
		String daqPoString = request.getParameter("DATA");
	
		String message = null;
		if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(driverId)) {
			message = SmartConstants.DRIVER_ID_ISNULL;
		} else if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		}else if (StringUtils.isBlank(daqPoString)) {
				message = "信息为空";
			}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "D002");
		} else {
	
			String a1type = AppFileUpLoad.localA1Type;
			String a1hard = AppFileUpLoad.localA1HardVersion;
			String a1soft = AppFileUpLoad.localA1SoftVersion;
			
			String r1type = AppFileUpLoad.localR1Type;
			String r1Hard = AppFileUpLoad.localR1HardVersion;
			String r1soft = AppFileUpLoad.localR1SoftVersion;
		
			Gson s = new Gson();
			SensorDetail detail = s.fromJson(daqPoString, SensorDetail.class);
			
			//比较版本
			
			if (detail.getSensorId().startsWith("5")){
				if (!detail.getRemoteHardVersion().equals(a1hard) 
						|| !detail.getRemoteSoftVersion().equals(a1soft)){
					((D002ServiceImpl)d002Service).setConf05(detail.getSensorId(),"A101",a1hard,a1soft);
				}
			}
			if (detail.getSensorId().startsWith("1")){  //燃气R1
				if (!detail.getRemoteHardVersion().equals(r1Hard) 
						|| !detail.getRemoteSoftVersion().equals(r1soft)){
					((D002ServiceImpl)d002Service).setConf05(detail.getSensorId(),"B101",r1Hard,r1soft);
				}
			}
			
			
		}
	
		return JSON_DATA;
	}
	public String checkUpgradeInfo() throws Exception {
		
		String driverId = request.getParameter("DRIVERID");
		String message = null;
		if (StringUtils.isBlank(driverId)) {
			message = SmartConstants.DRIVER_ID_ISNULL;
		} 
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "D002");
		} else {
			Map<String, String> map = new HashMap<String, String>();
			BinUpdatePo po = ((D002ServiceImpl)d002Service).getBinVersionCacheMap().get(driverId);
			if (po == null){
				message = "该设备未联网";
				callbackDataPO = new CallbackDataPO("0", message, 0, null, "D002");}
			else {
				//判断是否超时，升级失败，比较时间
				long minitus = ((new Date()).getTime()- po.getDate().getTime())/(60*1000);   //获得相差分钟
				if (minitus > 2 ) {
					callbackDataPO = new CallbackDataPO("0", "升级失败", 0, null, "D002");
				}else{
					map.put("totalLength", String.valueOf(po.getFileSize()));
					map.put("reciveLength", String.valueOf(po.getFileOffset()+po.getDataLen()));
					callbackDataPO = new CallbackDataPO("1", "", 1, null, JSONObject.fromObject(map), "D002");
				}
			}
			
		}
		return JSON_DATA;
	}
	private String readToString(String fileName) {
		String encoding = "utf-8";
		File file = new File(fileName);
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			return new String(filecontent, encoding);
		} catch (UnsupportedEncodingException e) {
			System.err.println("The OS does not support " + encoding);
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
	private void prepareData() {
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		String driverType = request.getParameter("DRIVERTYPE");
		String driverId = request.getParameter("DRIVERID");
		String driverName = request.getParameter("DRIVERNAME");
		String message = null;
		if (StringUtils.isBlank(userId)) {
			message = SmartConstants.USER_ID_ISNULL;
		} else if (StringUtils.isBlank(sessionId)) {
			message = SmartConstants.SESSION_ID_ISNULL;
		} else if (StringUtils.isBlank(driverType)) {
			message = SmartConstants.DRIVER_TYPE_ISNULL;
		} else if (StringUtils.isBlank(driverId)) {
			message = SmartConstants.DRIVER_ID_ISNULL;
		} else if (StringUtils.isBlank(driverName)) {
			message = SmartConstants.DRIVER_NAME_ISNULL;
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "D002");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					d002PO = new D002PO();
					d002PO.setMa002(userId);
					d002PO.setMa003(driverType);
					d002PO.setMa004(driverId.toUpperCase());
					d002PO.setMa005(new Date());
					d002PO.setMa008(driverName);
				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null,
							"D002");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public String retrieveRealData(){
		List<SensorDetail> sensorDetailList = new ArrayList<SensorDetail>();
		SensorDetailList detailList = new SensorDetailList();
		String driverId = request.getParameter("SENSORID");
		String keyString = request.getParameter("KEY");
		String sensorType = "2";
		
		
		String message = null;
		if (StringUtils.isBlank(driverId)) {
			message = SmartConstants.DRIVER_ID_ISNULL;
		} else if (StringUtils.isBlank(keyString)) {
			message = "不合法用户";
		}
		if (message != null) {
			callbackDataPO = new CallbackDataPO("0", message, 0, null, "P2B");
		} else {
			sensorType = DeviceUtil.getSensorTypeWithId(driverId)+"";
			try {
				Map<String, String> parma = new HashMap<String, String>();
				parma.put("sensorid", driverId);
				parma.put("key", keyString);
				String sensorIdString = p2BService.authByIdAndKey(parma);
				if (sensorIdString != null) {
					String jsonStr = WebServiceUtil.doWebservice(sensorIdString,
							sensorType, "",
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
							}else {
								((D002ServiceImpl)d002Service).query09(sensorDetail.getSensorId());
							}
							//先查询
		
							
							if( days > 1){   //大于1天，去查询
								((D002ServiceImpl)d002Service).query09(sensorDetail.getSensorId());
							}
						
							
							if (sensorDetail.getSensorId().startsWith("5")){   //A1
								sensorDetail.setLocalHardVersion(AppFileUpLoad.localA1HardVersion);
								sensorDetail.setLocalSoftVersion(AppFileUpLoad.localA1SoftVersion);
							}
							if (sensorDetail.getSensorId().startsWith("1")){     //R1
								sensorDetail.setLocalHardVersion(AppFileUpLoad.localR1HardVersion);
								sensorDetail.setLocalSoftVersion(AppFileUpLoad.localR1SoftVersion);
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
							JSONArray.fromObject(detailList), "P2B");
				}else {
					detailList.setSensorList(sensorDetailList);
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
							"P2B");
				}
				
				
			
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.AUTHENTICATION_FAILURE, 0, null, null,
						"P2B");
			}
			
		}
		return JSON_DATA;
	}
	public ID002Service getD002Service() {
		return d002Service;
	}

	public void setD002Service(ID002Service d002Service) {
		this.d002Service = d002Service;
	}

	public D002PO getD002PO() {
		return d002PO;
	}

	public void setD002PO(D002PO d002po) {
		d002PO = d002po;
	}

	public IU001Service getU001Service() {
		return u001Service;
	}

	public void setU001Service(IU001Service u001Service) {
		this.u001Service = u001Service;
	}

	public String getIds() {
		return ids;
	}

	public IControlDeviceService getControlDeviceService() {
		return controlDeviceService;
	}

	public void setControlDeviceService(IControlDeviceService controlDeviceService) {
		this.controlDeviceService = controlDeviceService;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public IP2BService getP2BService() {
		return p2BService;
	}

	public void setP2BService(IP2BService p2bService) {
		p2BService = p2bService;
	}
	
}
