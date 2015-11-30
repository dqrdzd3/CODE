package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.pojo.U001PO;

public interface ICorpOnlineDao extends IBaseDao {

	List<U001PO> doQuery(Map<String,Object> map) throws Exception;    //查询在线用户列表
	List<CorpCrmPO> rankWithConditionByWeek(Map<String,Object> map) throws Exception;   //按条件 周排名
	List<CorpCrmPO> rankWithConditionByMonth(Map<String,Object> map) throws Exception;
	/**
	 * 计算在线
	 */
	Integer countAll(Map<String, Object> map) throws Exception;
}
