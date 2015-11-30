package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ICorpCrmDao;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;

public class CorpCrmDaoImpl extends BaseDaoImpl implements ICorpCrmDao {

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ICorpCrmDao.class).retrieveByPage(map);
	}
	@Override
	public CorpCrmPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).retrieveInstanceById(ma001);
	}

	@Override
	public List<CorpCrmPO> retrieveInstanceByPO(CorpCrmPO a001PO)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).retrieveInstanceByPO(a001PO);
	}

	@Override
	public void insertInstance(CorpCrmPO a001PO) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpCrmDao.class).insertInstance(a001PO);
	}

	@Override
	public void updateInstance(CorpCrmPO a001PO) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpCrmDao.class).updateInstance(a001PO);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpCrmDao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).delBatchInstance(map);
	}

	@Override
	public Integer focusBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).focusBatchInstance(map);
	}

	@Override
	public List<CorpCrmPO> retrieveInstanceByMap(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).retrieveInstanceByMap(map);
	}

	@Override
	public List<Map<String, Object>> doListDeviceByTime(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).doListDeviceByTime(map);
	}

	@Override
	public List<Map<String, Object>> doListDeviceByGeo(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).doListDeviceByGeo(map);
	}

	@Override
	public Integer countAll(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).countAll(map);
	}

	@Override
	public Integer countFocus(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).countFocus(map);
	}

	@Override
	public List<Map<String, Object>> countGeoByTime(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).countGeoByTime(map);
	}

	@Override
	public List<Map<String, Object>> doListMenu(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).doListMenu(map);
	}
	@Override
	public List<Map<String, Object>> doListMenuSensor(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpCrmDao.class).doListMenuSensor(map);
	}

}
