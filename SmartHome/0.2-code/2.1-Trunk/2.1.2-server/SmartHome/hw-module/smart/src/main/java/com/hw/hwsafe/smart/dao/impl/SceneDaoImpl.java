package com.hw.hwsafe.smart.dao.impl;

import java.util.List;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ISceneDao;
import com.hw.hwsafe.smart.pojo.ScenePO;

public class SceneDaoImpl extends BaseDaoImpl implements
ISceneDao{

	@Override
	public ScenePO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ISceneDao.class).retrieveInstanceById(ma001);
	}

	@Override
	public List<ScenePO> retrieveInstanceByPO(ScenePO scenePO) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ISceneDao.class).retrieveInstanceByPO(scenePO);
	}

	@Override
	public void insertInstance(ScenePO scenePO) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ISceneDao.class).insertInstance(scenePO);
	}

	@Override
	public void updateInstance(ScenePO scenePO) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ISceneDao.class).updateInstance(scenePO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ISceneDao.class).deleteInstanceById(ma001);
	}

	@Override
	public List<ScenePO> retrieveAllInstance() throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ISceneDao.class).retrieveAllInstance();
	}

	@Override
	public void clearAirAllInstance(ScenePO scenePO) throws Exception {
		getSqlSession().getMapper(ISceneDao.class).clearAirAllInstance(scenePO);
		
	}

	@Override
	public void clearGasAllInstance(ScenePO scenePO) throws Exception {
		getSqlSession().getMapper(ISceneDao.class).clearAirAllInstance(scenePO);
		
	}

	
}
