package com.hw.hwsafe.smart.service;

import java.util.HashMap;
import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.BusinessPO;

public interface ICorpBusinessService extends IBaseService {
	BusinessPO doCorpLogin(Map<String, Object> map) throws Exception;
	
	void doManageAcount(BusinessPO po) throws Exception;
	
	BusinessPO resetPWD(BusinessPO po) throws Exception;
	
	/**
	 * 寻找密码成功后清除验证码
	 */
	void updateYZM(String ma001) throws Exception;
	/**
	 * 发送验证码
	 */
	void sendYZM(HashMap<String, String> map) throws Exception;
}
