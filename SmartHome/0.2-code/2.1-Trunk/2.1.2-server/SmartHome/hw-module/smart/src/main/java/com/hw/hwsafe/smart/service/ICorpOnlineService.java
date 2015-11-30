package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.U001PO;

public interface ICorpOnlineService extends IBaseService {

	List<U001PO> doQuery(Map<String,Object> map) throws Exception;    //查询在线用户列表
	List<CorpCrmPO> rankWithCondition(Map<String,Object> map) throws Exception;
	
	/**
	 * 计算在线
	 */
	Integer countAll(Map<String, Object> map) throws Exception;
}
