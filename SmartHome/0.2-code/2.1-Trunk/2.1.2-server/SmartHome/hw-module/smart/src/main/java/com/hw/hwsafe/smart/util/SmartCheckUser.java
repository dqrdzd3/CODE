package com.hw.hwsafe.smart.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.platform.util.ServiceBeanUtils;
import com.hw.hwsafe.smart.constants.SmartConstants;
import com.hw.hwsafe.smart.pojo.U001PO;
import com.hw.hwsafe.smart.service.IU001Service;
/**
 * 检查用户是否合法
 */
public  class SmartCheckUser extends BaseAction{
	private  IU001Service u001Service;
	/**
	 * 构造函数初始化u001Service
	 */
	public SmartCheckUser(){
		if(u001Service == null){
			u001Service = (IU001Service) ServiceBeanUtils.getServiceBean("u001Service");
		}
	}
	/**
	 * 
	 * 用户验证是否通过
	 *  eg：
	    Map<String, Object> vertify = new SmartCheckUser().vertify(sessionId);
		callbackDataPO=(CallbackDataPO) vertify.get("callbackDataPO");
		if ((Boolean) vertify.get("isVertify")) {//验证通过
		       
		}
	 * @param sessionId
	 * @return  验证结果：1.是否通过，2.验证提示        
	 * @author liyuhao
	 * @create_time 2014年8月6日上午9:20:43
	 */
	public  Map<String, Object> vertify(String sessionId){
		Map<String, Object> map=new HashMap<String, Object>();
		boolean isVertify=false;
		String message = null;
		if(StringUtils.isBlank(sessionId)){
			message = SmartConstants.SESSION_ID_ISNULL;
		} 
		if(message != null){
			callbackDataPO = new CallbackDataPO("0",message ,0,null, "D002");
		} else {
			try {
				U001PO u001po = u001Service.checkUserBySessionId(sessionId);
				if(u001po != null){//验证通过
					isVertify=true;
				}else{
					callbackDataPO = new CallbackDataPO("0", SmartConstants.AUTHENTICATION_FAILURE,0,null,"D002");
					isVertify=false;
				}
			} catch (Exception e) {
				callbackDataPO = new CallbackDataPO("0", SmartConstants.AUTHENTICATION_FAILURE,0,null,"D002");
				e.printStackTrace();
			}
		}
		map.put("callbackDataPO", callbackDataPO);
		map.put("isVertify", isVertify);
       return map;
	}
}
