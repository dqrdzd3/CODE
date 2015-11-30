package com.hw.hwsafe.smart.service;

import java.util.List;

import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.smart.pojo.ScenePO;

public interface ISceneService extends IBaseService {
	/**
	 * 通过id获取实例
	 */
	ScenePO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过PO获取实例
	 */
	List<ScenePO> retrieveInstanceByPO(ScenePO scenePO) throws Exception;
	/**
	 * 获得全部
	 */
	List<ScenePO> retrieveAllInstance() throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(ScenePO scenePO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(ScenePO scenePO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	/**
	 * 清除该空气设备
	 */
	void clearAirAllInstance(ScenePO scenePO) throws Exception;
	/**
	 * 清除该燃气设备
	 */
	void clearGasAllInstance(ScenePO scenePO) throws Exception;
	/**
	 * 更改场景名称
	 */
	void updateSceneName(ScenePO scenePO) throws Exception;

}
