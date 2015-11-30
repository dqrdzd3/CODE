package com.hw.hwsafe.smart.dao;

import java.util.HashMap;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.BusinessPO;

public interface ICorpBusinessDao extends IBaseDao {

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
