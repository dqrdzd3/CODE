/**
 * 文件名：BolsterWebserviceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.bolster.webservice.service.impl;

//import java.util.Map;
//import net.sf.json.JSONSerializer;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.hw.hwsafe.bolster.usersubsystem.service.IUserService;
//import com.hw.hwsafe.bolster.usersubsystem.service.IUserSubsystemService;
import com.hw.hwsafe.bolster.webservice.service.IBolsterWebservice;

/**
 * 支撑平台webservice实现类
 * 
 * @author 马宁
 *
 */
public class BolsterWebserviceImpl implements IBolsterWebservice {

//	@Autowired
//	private IUserSubsystemService userSubsystemService;
//	
//	@Autowired
//	private IUserService bolsterUserService;
	
	@Override
	public String isAllowable(String loginName, String subsystemCode)
			throws Exception {
		
//		return userSubsystemService.isAllowable(loginName, subsystemCode)
//				? "true"
//				: "false";
		throw new RuntimeException();
	}

	@Override
	public String isActivable(String loginName, String subsystemCode)
			throws Exception {
		
//		return userSubsystemService.isActivable(loginName, subsystemCode)
//				? "true"
//				: "false";
		throw new RuntimeException();
	}

	@Override
	public void updateActiveFlag(String loginName, String subsystemCode,
			String activeFlag) throws Exception {
//		String activable = Boolean.valueOf(activeFlag) ? "1" : "0";
//		userSubsystemService.updateActiveFlag(subsystemCode, loginName, activable);
	}

	@Override
	public String getUserInfo(String loginName) throws Exception {
//		Map<String, String> userInfoMap = bolsterUserService.retrieveBaseInfoByLoginName(loginName);
//		return JSONSerializer.toJSON(userInfoMap).toString();
		throw new RuntimeException();
	}
	
}
