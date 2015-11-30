package com.hw.hwsafe.smart.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.hw.hwsafe.attachment.util.AppFileUpLoad;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.BinUpdatePo;
import com.hw.hwsafe.smart.pojo.SensorConstant;
import com.hw.hwsafe.smart.pojo.SensorDetail;
import com.hw.hwsafe.smart.pojo.SensorDetailList;
import com.hw.hwsafe.smart.service.ID002Service;
import com.hw.hwsafe.smart.service.IP2BService;
import com.hw.hwsafe.smart.service.impl.D002ServiceImpl;
import com.hw.hwsafe.smart.util.DeviceUtil;
import com.hw.hwsafe.smart.util.WebServiceUtil;


public class P2BAction extends BaseAction {

	
	@Autowired
	private IP2BService p2BService;
	
	@Autowired
	private ID002Service d002Service;
	
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
	

}
