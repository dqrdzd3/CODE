package com.hw.hwsafe.smart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ISceneDao;
import com.hw.hwsafe.smart.pojo.ScenePO;
import com.hw.hwsafe.smart.service.ISceneService;

public class SceneServiceImpl extends BaseServiceImpl implements
ISceneService{
	@Autowired
	private ISceneDao sceneDao;
	@Override
	public ScenePO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return sceneDao.retrieveInstanceById(ma001);
	}

	@Override
	public List<ScenePO> retrieveInstanceByPO(ScenePO scenePO) throws Exception {
		// TODO Auto-generated method stub
		return sceneDao.retrieveInstanceByPO(scenePO);
	}

	@Override
	public void insertInstance(ScenePO scenePO) throws Exception {
		// TODO Auto-generated method stub
		sceneDao.insertInstance(scenePO);
	}

	@Override
	public void updateInstance(ScenePO scenePO) throws Exception {
		// TODO Auto-generated method stub
		sceneDao.updateInstance(scenePO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		sceneDao.deleteInstanceById(ma001);
	}

	@Override
	public List<ScenePO> retrieveAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return sceneDao.retrieveAllInstance();
	}

	@Override
	public void clearAirAllInstance(ScenePO scenePO) throws Exception {
		sceneDao.clearAirAllInstance(scenePO);
		
	}

	@Override
	public void clearGasAllInstance(ScenePO scenePO) throws Exception {
		sceneDao.clearGasAllInstance(scenePO);
		
	}

	@Override
	public void updateSceneName(ScenePO scenePO) throws Exception {
		sceneDao.clearAirAllInstance(scenePO);
		
	}

}
