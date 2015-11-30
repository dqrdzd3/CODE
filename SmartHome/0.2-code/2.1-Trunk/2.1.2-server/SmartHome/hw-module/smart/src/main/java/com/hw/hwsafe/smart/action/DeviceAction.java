package com.hw.hwsafe.smart.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.DevicePO;
import com.hw.hwsafe.smart.pojo.DeviceSharePO;
import com.hw.hwsafe.smart.pojo.DeviceShareViewPO;
import com.hw.hwsafe.smart.pojo.LinkagePO;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IDeviceService;
import com.hw.hwsafe.smart.service.IU001Service;
import com.hw.hwsafe.utils.UUIDGenerater;

public class DeviceAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private IU001Service u001Service;
	
	@Autowired
	private IDeviceService deviceService;
	
	//设备管理
	public String addDevice(){
		return doSaveAddEquips();
	}

	//修改设备信息
	public String updateDevice(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String air = request.getParameter("DEVICE");
		String link = request.getParameter("LINKAGE");
		String share = request.getParameter("SHARE");
		
		DevicePO airDevicePO = null;
		LinkagePO linkagePO = null;
		DeviceShareViewPO shareViewPO = null;
		DeviceSharePO sharePO = null;
		if (air != null) {
			airDevicePO = new Gson().fromJson(air, DevicePO.class);
		}
		if (link != null) {
			linkagePO = new Gson().fromJson(link, LinkagePO.class);
		}
		if (share != null) {
			shareViewPO = new Gson().fromJson(share, DeviceShareViewPO.class);
		}


		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

					
					if (airDevicePO != null) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("ID", UUIDGenerater.get32UUID());
						
						map.put("USERID", u001PO.getMa001());
						
						map.put("MANUFACTURER", airDevicePO.getManufacturer());
						map.put("NAME",airDevicePO.getName());
						map.put("DEVICEID", airDevicePO.getDeviceId());
						map.put("CATEGORY", airDevicePO.getCategory());
						map.put("TYPE", airDevicePO.getType());
						map.put("CREATE_TIME",sdf.format(new Date()));
						
						 deviceService.insertDeviceAir(map);
					}
					if (linkagePO != null) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("ID", UUIDGenerater.get32UUID());
						map.put("USERID", u001PO.getMa001());
						map.put("MASTER_DEVICE", linkagePO.getMasterDevice());
						map.put("NAME", linkagePO.getName());
						
						map.put("LINKAGE_ID", linkagePO.getLinkageIds());
						map.put("LINKAGE_STATE", ""+linkagePO.getLinkageState());
						map.put("CREATE_TIME",sdf.format(new Date()));
						
						 deviceService.insertDeviceLinkage(map);
					}
					if (shareViewPO != null) {
						
						 shareViewPO.setUserId(u001PO.getMa001());
						 
						 //获得设备ID
						 //DevicePO tmpDevicePO = shareViewPO.getDevicePO();
						 sharePO.setCreate_time(shareViewPO.getCreate_time());
						 sharePO.setDeviceId(shareViewPO.getDevicePO().getDeviceId());
						 sharePO.setFrom_phone(shareViewPO.getFrom_phone());
						 sharePO.setId(shareViewPO.getId());
						 sharePO.setIs_shared(shareViewPO.getIs_shared());
						 sharePO.setTo_phone(shareViewPO.getTo_phone());
						 sharePO.setUserId(shareViewPO.getUserId());
						 
						 
						 
						 
						 
						 deviceService.insertDeviceShare(sharePO);
					}
			
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.ADD_DRIVER_SUCCESS, 1,
								null,null, "DEVICE");
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.ADD_DRIVER_FAILURE, 0, null, "DEVICE");
			}
			
		}
		
		
		return JSON_DATA;
	}
	//删除设备信息
		public String delDevice(){
			String userId = request.getParameter("USERID");
			String sessionId = request.getParameter("SESSIONID");
			
			String id = request.getParameter("ID");
			String type = request.getParameter("TYPE");

			if (StringUtils.isBlank(sessionId)) {
				throw new NullArgumentException("sessionID");
			} else if (StringUtils.isBlank(userId)) {
				throw new NullArgumentException("userId");
			} else {
				try {
					U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
					if (u001PO != null) {

						Map<String, String> map = new HashMap<String, String>();
						map.put("id", id);
							
						 deviceService.deleteShareDeviceById(map);
						 deviceService.deleteDeviceAirById(map);
						 deviceService.deleteDeviceLinkageById(map);
							callbackDataPO = new CallbackDataPO("1",
									SmartConstants.DELETE_DRIVER_SUCCESS, 1,
									null,null, "DEVICE");
						
						

					} else {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
					}
				} catch (Exception e) {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.DELETE_DRIVER_FAILURE, 0, null, "DEVICE");
				}
				
			}
			
			return JSON_DATA;
		}
	
	//查询设备
	public String getDevice(){
		
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		
		String deviceId = request.getParameter("DEVICEID");
		
		String poType = request.getParameter("POTYPE");
		if(StringUtils.isBlank(poType)){
			throw new NullArgumentException("poType");
		}else if(StringUtils.isBlank(deviceId)){
			throw new NullArgumentException("deviceID");
		}else if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					
					//验证身份成功
					

					if( poType.equals("DEVICE") ){
						//device
						
						Map<String, String> map = new HashMap<String, String>();
						map.put("DEVICEID", deviceId);
						map.put("USERID", userId);
						DevicePO tmpMapDevicePO = new DevicePO();
						List<Map<String, String>> tmpmapDeviceList = new LinkedList<Map<String, String>>();
						try {
							tmpmapDeviceList = deviceService.getDeviceAir(map);
							if(tmpmapDeviceList != null && tmpmapDeviceList.size() > 0){
								
								tmpMapDevicePO.setCategory(tmpmapDeviceList.get(0).get("CATEGORY"));
								tmpMapDevicePO.setCreateTime(tmpmapDeviceList.get(0).get("CREATE_TIME"));
								tmpMapDevicePO.setDeviceId(tmpmapDeviceList.get(0).get("DEVICEID"));
								tmpMapDevicePO.setId(tmpmapDeviceList.get(0).get("ID"));
								
								tmpMapDevicePO.setIsOnline(0);
								tmpMapDevicePO.setParam(null);
								tmpMapDevicePO.setManufacturer(tmpmapDeviceList.get(0).get("MANUFACTURER"));
								tmpMapDevicePO.setName(tmpmapDeviceList.get(0).get("NAME"));
								tmpMapDevicePO.setType(tmpmapDeviceList.get(0).get("TYPE"));
								tmpMapDevicePO.setUserId(tmpmapDeviceList.get(0).get("USERID"));
								
								callbackDataPO = new CallbackDataPO("1",
										SmartConstants.QUERY_DATA_SUCCESS, 1,
										null,JSONArray.fromObject(tmpMapDevicePO), "DEVICE");
								}else{
									callbackDataPO = new CallbackDataPO("0",
											SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
								}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							callbackDataPO = new CallbackDataPO("0",
									SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
						}
						
						
						
					}else if(poType.equals("LINKAGE")){
						//Linkage
						
						Map<String, String> map = new HashMap<String, String>();
						map.put("MASTER_DEVICE", deviceId);
						map.put("USERID", userId);
						LinkagePO tmpLinkagePO = new LinkagePO();
						List<Map<String, String>> tmpLinkageList = new LinkedList<Map<String, String>>();
						try {
							tmpLinkageList = deviceService.getDeviceLinkage(map);
							if(tmpLinkageList != null && tmpLinkageList.size() > 0){
								
								tmpLinkagePO.setBgnTime(tmpLinkageList.get(0).get("BEGIN_TIME"));
								tmpLinkagePO.setCreateTime(tmpLinkageList.get(0).get("CREATE_TIME"));
								tmpLinkagePO.setEndTime(tmpLinkageList.get(0).get("END_TIME"));
								tmpLinkagePO.setId(tmpLinkageList.get(0).get("ID"));
								tmpLinkagePO.setLinkageIds(tmpLinkageList.get(0).get("LINKAGE_IDS"));
								
								String[] tmpLinkgeArray = tmpLinkagePO.getLinkageIds().split(",");
								List<String> tmpLinkage = new LinkedList<String>();
								for (String string : tmpLinkgeArray) {
									tmpLinkage.add(string);
								}
								
								tmpLinkagePO.setDeviceList(tmpLinkage);
								tmpLinkagePO.setLinkageState( Integer.parseInt(tmpLinkageList.get(0).get("LINKAGE_STATE")));
								
								tmpLinkagePO.setMasterDevice(deviceId);
								tmpLinkagePO.setName(tmpLinkageList.get(0).get("NAME"));
								
								tmpLinkagePO.setParam(null);
								
								tmpLinkagePO.setParamYang(null);
								//(tmpLinkageList.get(0).get("MANUFACTURER"));
								tmpLinkagePO.setParamYin(null);
								
								
								tmpLinkagePO.setUserId(tmpLinkageList.get(0).get("USERID"));
								
								callbackDataPO = new CallbackDataPO("1",
										SmartConstants.QUERY_DATA_SUCCESS, 1,
										null,JSONArray.fromObject(tmpLinkagePO), "DEVICE");
								}else{
									callbackDataPO = new CallbackDataPO("0",
											SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
								}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							callbackDataPO = new CallbackDataPO("0",
									SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
						}
						
						
						
						
						
					}else{
						//share
						
						
						Map<String, String> map = new HashMap<String, String>();
						map.put("userid", u001PO.getMa001());
						
						
						 List<DeviceSharePO> messageList = deviceService.getAllDeviceShare(map);
						 
						 //List<DeviceShareViewPO> messageViewList = new LinkedList<DeviceShareViewPO>();
						 
						 DeviceShareViewPO deviceShareViewPO = new DeviceShareViewPO();
						 
						 DeviceSharePO tmpSharePO = new DeviceSharePO();
						 
						 if(messageList != null && messageList.size() >0){
							 
							 tmpSharePO = messageList.get(0);
							deviceShareViewPO.setCreate_time(tmpSharePO.getCreate_time());
							deviceShareViewPO.setFrom_phone(tmpSharePO.getFrom_phone());
							deviceShareViewPO.setId(tmpSharePO.getId());
							deviceShareViewPO.setIs_shared(tmpSharePO.getIs_shared());
							deviceShareViewPO.setTo_phone(tmpSharePO.getTo_phone());
							deviceShareViewPO.setUserId(tmpSharePO.getUserId());
							
							
							Map<String, String> mapDevice = new HashMap<String, String>();
							mapDevice.put("DEVICEID", tmpSharePO.getDeviceId());
							DevicePO tmpMapDevicePO = new DevicePO();
							List<Map<String, String>> mapDeviceList = new LinkedList<Map<String, String>>();
							mapDeviceList = deviceService.getDeviceAir(mapDevice);
							
							if(mapDeviceList != null && mapDeviceList.size() > 0){
							tmpMapDevicePO.setCategory(mapDeviceList.get(0).get("CATEGORY"));
							tmpMapDevicePO.setCreateTime(mapDeviceList.get(0).get("CREATE_TIME"));
							tmpMapDevicePO.setDeviceId(mapDeviceList.get(0).get("DEVICEID"));
							tmpMapDevicePO.setId(mapDeviceList.get(0).get("ID"));
							
							tmpMapDevicePO.setIsOnline(0);
							tmpMapDevicePO.setParam(null);
							tmpMapDevicePO.setManufacturer(mapDeviceList.get(0).get("MANUFACTURER"));
							tmpMapDevicePO.setName(mapDeviceList.get(0).get("NAME"));
							tmpMapDevicePO.setType(mapDeviceList.get(0).get("TYPE"));
							tmpMapDevicePO.setUserId(mapDeviceList.get(0).get("USERID"));
							}
							
							
							
							
							deviceShareViewPO.setDevicePO(tmpMapDevicePO);
							
							callbackDataPO = new CallbackDataPO("1",
									SmartConstants.QUERY_DATA_SUCCESS, 1,
									null,JSONArray.fromObject(deviceShareViewPO), "DEVICE");
							
							
							
						
					}else{
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
					}
						
						
					}
					
					
					
					
					
					
						
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.DELETE_DRIVER_FAILURE, 0, null, "DEVICE");
			}
			
		}
		
		
		
		
		
		
		
		
		return JSON_DATA;
	}
		
		
		
		
	//分享设备
	
	public String getAllDeviceShare(){
		
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");

		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", u001PO.getMa001());
						
					 List<DeviceSharePO> messageList = deviceService.getAllDeviceShare(map);
					 
					 List<DeviceShareViewPO> messageViewList = new LinkedList<DeviceShareViewPO>();
					 
					 DeviceShareViewPO deviceShareViewPO = new DeviceShareViewPO();
					 
					 if(messageList != null && messageList.size() >0){
					 
					 for (DeviceSharePO tmpSharePO : messageList) {
						deviceShareViewPO.setCreate_time(tmpSharePO.getCreate_time());
						deviceShareViewPO.setFrom_phone(tmpSharePO.getFrom_phone());
						deviceShareViewPO.setId(tmpSharePO.getId());
						deviceShareViewPO.setIs_shared(tmpSharePO.getIs_shared());
						deviceShareViewPO.setTo_phone(tmpSharePO.getTo_phone());
						deviceShareViewPO.setUserId(tmpSharePO.getUserId());
						
						
						Map<String, String> mapDevice = new HashMap<String, String>();
						mapDevice.put("DEVICEID", tmpSharePO.getDeviceId());
						DevicePO tmpMapDevicePO = new DevicePO();
						List<Map<String, String>> mapDeviceList = new LinkedList<Map<String, String>>();
						mapDeviceList = deviceService.getDeviceAir(mapDevice);
						
						if(mapDeviceList != null && mapDeviceList.size() > 0){
						tmpMapDevicePO.setCategory(mapDeviceList.get(0).get("CATEGORY"));
						tmpMapDevicePO.setCreateTime(mapDeviceList.get(0).get("CREATE_TIME"));
						tmpMapDevicePO.setDeviceId(mapDeviceList.get(0).get("DEVICEID"));
						tmpMapDevicePO.setId(mapDeviceList.get(0).get("ID"));
						
						tmpMapDevicePO.setIsOnline(0);
						tmpMapDevicePO.setParam(null);
						tmpMapDevicePO.setManufacturer(mapDeviceList.get(0).get("MANUFACTURER"));
						tmpMapDevicePO.setName(mapDeviceList.get(0).get("NAME"));
						tmpMapDevicePO.setType(mapDeviceList.get(0).get("TYPE"));
						tmpMapDevicePO.setUserId(mapDeviceList.get(0).get("USERID"));
						}
						
						
						
						
						deviceShareViewPO.setDevicePO(tmpMapDevicePO);
						messageViewList.add(deviceShareViewPO);
					}
				}
					 
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, messageList.size(),
								null, JSONArray.fromObject(messageList), "DEVICE");
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.QUERY_DATA_FAILURE, 0, null, "DEVICE");
			}
			
		}
		
		return JSON_DATA;
	}
	
	public String delDeviceShare(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		
		String id = request.getParameter("ID");

		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

					Map<String, String> map = new HashMap<String, String>();
					map.put("id", id);
						
					 deviceService.deleteShareDeviceById(map);
						callbackDataPO = new CallbackDataPO("1",
								SmartConstants.DELETE_DRIVER_SUCCESS, 1,
								null,null, "DEVICE");
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.DELETE_DRIVER_FAILURE, 0, null, "DEVICE");
			}
			
		}
		
		return JSON_DATA;
	}
	public String addDeviceShare(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		
		String from = request.getParameter("FROM_PHONE");
		String to = request.getParameter("TO_PHONE");
		String deviceId = request.getParameter("DEVICEID");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		
		
		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

			
					DeviceSharePO sharePO = new DeviceSharePO();
					sharePO.setId(UUIDGenerater.get32UUID());
					sharePO.setDeviceId(deviceId);
					sharePO.setFrom_phone(from);
					sharePO.setTo_phone(to);
					sharePO.setIs_shared(1);
					sharePO.setUserId(u001PO.getMa001());
					sharePO.setCreate_time(sdf.format(new Date()));
					deviceService.insertDeviceShare(sharePO);
							callbackDataPO = new CallbackDataPO("1",
									SmartConstants.ADD_DRIVER_SUCCESS,1,
									null, JSONObject.fromObject(sharePO), "DEVICE");
					
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.QUERY_DATA_FAILURE, 0, null, "DEVICE");
			}
			
		}
		
		
		
		return JSON_DATA;
	}
	
	
	//绑定设备
	
	//获得
	public  String  getDeviceList() {
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");

		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {
					
					
					
				//	List<Map<String, String>> deviceList = new LinkedList<Map<String, String>>();
					List<Map<String, Object>> deviceList = new LinkedList<Map<String, Object>>();
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", u001PO.getMa001());
					//获得空气设备
					List<Map<String, String>> airDeviceList = deviceService.getDeviceAir(map);
					
					for (int i = 0; i < airDeviceList.size(); i++) {
						Map<String, String> temp = airDeviceList.get(i);
						DevicePO air = new DevicePO();
						air.setId(temp.get("ID"));
						air.setCategory(temp.get("CATEGORY"));
						air.setCreateTime(temp.get("CREATE_TIME"));
						air.setDeviceId(temp.get("DEVICEID"));
						air.setManufacturer(temp.get("MANUFACTURER"));
						air.setName(temp.get("NAME"));
						air.setType(temp.get("TYPE"));
	/*				       Map<String, String> airMap = new HashMap<String, String>();
					       airMap.put("DEVICE", new Gson().toJson(air));*/
					       Map<String, Object> airMap = new HashMap<String, Object>();
					       airMap.put("DEVICE", air);
					       deviceList.add(airMap);
					}
					
					//获得联动设备
					List<Map<String, String>> LinkedDeviceList = deviceService.getDeviceLinkage(map);
					for (int i = 0; i < LinkedDeviceList.size(); i++) {
						Map<String, String> temp = LinkedDeviceList.get(i);
						LinkagePO link = new LinkagePO();
						link.setBgnTime(temp.get("BEGIN_TIME"));
						link.setCreateTime(temp.get("CREATE_TIME"));
						link.setEndTime(temp.get("END_TIME"));
						link.setId(temp.get("ID"));
						link.setLinkageIds(temp.get("LINKAGE_IDS"));
						List<String> deviceListTmp = new ArrayList<String>();
						String deviceLst[] = temp.get("LINKAGE_IDS").split(",");
						for (int j=0;j<deviceLst.length;j++){
							deviceListTmp.add(deviceLst[j]);
						}
						link.setDeviceList(deviceListTmp);
					    try {
					    	link.setLinkageState(Integer.parseInt(temp.get("LINKAGE_STATE")));
						} catch (Exception e) {
							link.setLinkageState(0);    //手动
						}
						
						link.setMasterDevice(temp.get("MASTER_DEVICE"));
						link.setName(temp.get("NAME"));
						
					       Map<String, Object> linkMap = new HashMap<String, Object>();
					       linkMap.put("LINKAGE", link);
					       deviceList.add(linkMap);
					}
					
					//获得分享设备
					 List<DeviceSharePO> shareDeviceList = deviceService.getAllDeviceShare(map);
					 for (int i = 0; i < shareDeviceList.size(); i++) {
						   DeviceSharePO sharePO = shareDeviceList.get(i);
						   Map<String, Object> linkMap = new HashMap<String, Object>();
					       linkMap.put("LINKAGE", sharePO);
					       deviceList.add(linkMap);
					 }
				
					 
					callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, deviceList.size(),
								null, JSONArray.fromObject(deviceList), "DEVICE");
					
					

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.QUERY_DATA_FAILURE, 0, null, "DEVICE");
			}
			
		}
		
		return JSON_DATA;
	}
	
	//绑定
	
	public String doSaveAddEquips(){
		String userId = request.getParameter("USERID");
		String sessionId = request.getParameter("SESSIONID");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		String air = request.getParameter("DEVICE");
		String link = request.getParameter("LINKAGE");
		String share = request.getParameter("SHARE");
		
		DevicePO airDevicePO = null;
		LinkagePO linkagePO = null;
		DeviceSharePO sharePO = null;
		if (air != null) {
			airDevicePO = new Gson().fromJson(air, DevicePO.class);
		}
		if (link != null) {
			linkagePO = new Gson().fromJson(link, LinkagePO.class);
		}
		if (share != null) {
			sharePO = new Gson().fromJson(share, DeviceSharePO.class);
		}


		if (StringUtils.isBlank(sessionId)) {
			throw new NullArgumentException("sessionID");
		} else if (StringUtils.isBlank(userId)) {
			throw new NullArgumentException("userId");
		} else {
			try {
				U001PO u001PO = u001Service.checkUserBySessionId(sessionId);
				if (u001PO != null) {

					
					if (airDevicePO != null) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("ID", UUIDGenerater.get32UUID());
						
						map.put("USERID", u001PO.getMa001());
						
						map.put("MANUFACTURER", airDevicePO.getManufacturer());
						map.put("NAME",airDevicePO.getName());
						map.put("DEVICEID", airDevicePO.getDeviceId());
						map.put("CATEGORY", airDevicePO.getCategory());
						map.put("TYPE", airDevicePO.getType());
						map.put("CREATE_TIME",sdf.format(new Date()));
						List<Map<String, String>> reList = deviceService.OnlyDeviceAirInvalid(map);
						if (reList == null || reList.size() == 0) {
							deviceService.insertDeviceAir(map);
							/*callbackDataPO = new CallbackDataPO("1",
									SmartConstants.ADD_DRIVER_SUCCESS, 1,
									null,null, "DEVICE");*/
						}else {
							callbackDataPO = new CallbackDataPO("0",
									"数据重复添加", 0, null, "DEVICE");
							return JSON_DATA;
						}
						 
					}else if (linkagePO != null) {
						Map<String, String> map = new HashMap<String, String>();
						map.put("ID", UUIDGenerater.get32UUID());
						map.put("USERID", u001PO.getMa001());
						map.put("MASTER_DEVICE", linkagePO.getMasterDevice());
						map.put("NAME", linkagePO.getName());
						//map.put("DEVICELIST", )
						String linkAgeID = "";
						List<String> linkAge = linkagePO.getDeviceList();
						

						map.put("LINKAGE_IDS", linkagePO.getLinkageIds());

						for(String tmp:linkAge)  
				        {  
							linkAgeID += tmp+",";
				            //System.out.println(tmp);  
				        }
						
						linkAgeID = linkAgeID.substring(0,linkAgeID.length()-1);
						
						
						map.put("LINKAGE_IDS", linkAgeID);

						map.put("LINKAGE_STATE", ""+linkagePO.getLinkageState());
						map.put("CREATE_TIME",sdf.format(new Date()));
						List<Map<String, String>> reList = deviceService.OnlyDeviceLinkageInvalid(map);
						if (reList == null || reList.size() == 0) {
							 deviceService.insertDeviceLinkage(map);
							/* callbackDataPO = new CallbackDataPO("1",
										SmartConstants.ADD_DRIVER_SUCCESS, 1,
										null,null, "DEVICE");*/
						}else {
							callbackDataPO = new CallbackDataPO("0",
									"数据重复添加", 0, null, "DEVICE");
							return JSON_DATA;
						}
						
					}else if (sharePO != null) {
						
						 sharePO.setUserId(u001PO.getMa001());	
						 Map<String, String> map = new HashMap<String, String>();
						 map.put("USERID", sharePO.getUserId());
						 map.put("DEVICEID", sharePO.getDeviceId());
						 List<Map<String, String>> reList = deviceService.OnlyDeviceShareInvalid(map);
							if (reList == null || reList.size() == 0) {
								deviceService.insertDeviceShare(sharePO);
							/*	callbackDataPO = new CallbackDataPO("1",
										SmartConstants.ADD_DRIVER_SUCCESS, 1,
										null,null, "DEVICE");*/
							}else {
								callbackDataPO = new CallbackDataPO("0",
										"数据重复添加", 0, null, "DEVICE");
								return JSON_DATA;
							}
						 
					}else {
						callbackDataPO = new CallbackDataPO("0",
								SmartConstants.ADD_DRIVER_FAILURE, 0,
								null,null, "DEVICE");
						return JSON_DATA;
						
					}
					List<Map<String, Object>> deviceList = new LinkedList<Map<String, Object>>();
					
					Map<String, String> map = new HashMap<String, String>();
					map.put("userid", u001PO.getMa001());
					//获得空气设备
					List<Map<String, String>> airDeviceList = deviceService.getDeviceAir(map);
					
					for (int i = 0; i < airDeviceList.size(); i++) {
						Map<String, String> temp = airDeviceList.get(i);
						DevicePO airT = new DevicePO();
						airT.setId(temp.get("ID"));
						airT.setCategory(temp.get("CATEGORY"));
						airT.setCreateTime(temp.get("CREATE_TIME"));
						airT.setDeviceId(temp.get("DEVICEID"));
						airT.setManufacturer(temp.get("MANUFACTURER"));
						airT.setName(temp.get("NAME"));
						airT.setType(temp.get("TYPE"));

					       Map<String, Object> airMap = new HashMap<String, Object>();
					       airMap.put("DEVICE", airT);
					       deviceList.add(airMap);
					}
					
					//获得联动设备
					List<Map<String, String>> LinkedDeviceList = deviceService.getDeviceLinkage(map);
					for (int i = 0; i < LinkedDeviceList.size(); i++) {
						Map<String, String> temp = LinkedDeviceList.get(i);
						LinkagePO linkT = new LinkagePO();
						linkT.setBgnTime(temp.get("BEGIN_TIME"));
						linkT.setCreateTime(temp.get("CREATE_TIME"));
						linkT.setEndTime(temp.get("END_TIME"));
						linkT.setId(temp.get("ID"));
						linkT.setLinkageIds(temp.get("LINKAGE_ID"));
					    try {
					    	linkT.setLinkageState(Integer.parseInt(temp.get("LINKAGE_STATE")));
						} catch (Exception e) {
							linkT.setLinkageState(0);    //手动
						}
						
						linkT.setMasterDevice(temp.get("MASTER_DEVICE"));
						linkT.setName(temp.get("NAME"));
						
					       Map<String, Object> linkMap = new HashMap<String, Object>();
					       linkMap.put("LINKAGE", linkT);
					       deviceList.add(linkMap);
					}
					
					//获得分享设备
					 List<DeviceSharePO> shareDeviceList = deviceService.getAllDeviceShare(map);
					 for (int i = 0; i < shareDeviceList.size(); i++) {
						   DeviceSharePO sharePOT = shareDeviceList.get(i);
						   DeviceShareViewPO shareViewPO = new DeviceShareViewPO();
						   if (sharePOT!=null) {
							shareViewPO.setId(sharePOT.getId());
							shareViewPO.setFrom_phone(sharePOT.getFrom_phone());
							shareViewPO.setTo_phone(sharePOT.getTo_phone());
							String deviceId = sharePOT.getDeviceId();
							Map<String, String> mapT = new HashMap<String, String>();
							mapT.put("userid", sharePOT.getUserId());
							mapT.put("deviceid", sharePOT.getDeviceId());
							List<Map<String, String>> tempList = deviceService.getDeviceAir(mapT);
							DevicePO tempPo = new DevicePO();
							if (tempList.size() == 1) {
								tempPo.setCategory(tempList.get(0).get("CATEGORY"));
								tempPo.setDeviceId(tempList.get(0).get("DEVICEID"));
								tempPo.setManufacturer(tempList.get(0).get("MANUFACTURER"));
								tempPo.setName(tempList.get(0).get("NAME"));
								tempPo.setType(tempList.get(0).get("TYPE"));
								tempPo.setUserId(tempList.get(0).get("USERID"));
							}
							shareViewPO.setDevicePO(tempPo);
							shareViewPO.setUserId(sharePOT.getUserId());
							shareViewPO.setCreate_time(sharePOT.getCreate_time());
						}
						   Map<String, Object> linkMap = new HashMap<String, Object>();
					       linkMap.put("LINKAGE", sharePOT);


					 }
					 
					 
					callbackDataPO = new CallbackDataPO("1",
								SmartConstants.QUERY_DATA_SUCCESS, deviceList.size(),
								null, JSONArray.fromObject(deviceList), "DEVICE");
						
					return JSON_DATA;

				} else {
					callbackDataPO = new CallbackDataPO("0",
							SmartConstants.AUTHENTICATION_FAILURE, 0, null, "DEVICE");
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				callbackDataPO = new CallbackDataPO("0",
						SmartConstants.ADD_DRIVER_FAILURE, 0, null, "DEVICE");
			}
			
		}
		
		
		return JSON_DATA;
	}
}
