package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;

import com.hw.hwsafe.smart.pojo.CorpCrmPO;

public interface ICorpCrmDao extends IBaseDao {

	/**
	 * 通过id获取实例
	 */
	CorpCrmPO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<CorpCrmPO> retrieveInstanceByPO(CorpCrmPO a001PO) throws Exception;
	/**
	 * 通过PO获取实例
	 */
	List<CorpCrmPO> retrieveInstanceByMap(Map<String, Object> map) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(CorpCrmPO a001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(CorpCrmPO a001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	
	/**
	 * 批量删除实例
	 */
	Integer delBatchInstance(Map<String, Object> map) throws Exception;
	/**
	 * 批处理关注
	 */
	Integer focusBatchInstance(Map<String, Object> map) throws Exception;
	
	/**
	 * 按条件周、月、年统计发货
	 */
	List<Map<String, Object>> doListDeviceByTime(Map<String, Object> map) throws Exception;	
	
	/**
	 * 按省市  菜单     不用
	 */
	List<Map<String, Object>> doListDeviceByGeo(Map<String, Object> map) throws Exception;	
	/**
	 * 按省市  统计
	 */
	List<Map<String, Object>> countGeoByTime(Map<String, Object> map) throws Exception;	
	/**
	 * crm 总数
	 */
	Integer countAll(Map<String, Object> map) throws Exception;
	/**
	 * crm 潜在客户总数
	 */
	Integer countFocus(Map<String, Object> map) throws Exception;
	
	/**
	 * 按省市  左边菜单
	 */
	List<Map<String, Object>> doListMenu(Map<String, Object> map) throws Exception;	
	/**
	 * 按省市 查询获得 设备id
	 */
	List<Map<String, Object>> doListMenuSensor(Map<String, Object> map) throws Exception;	
}
