package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ICorpCrmDao;
import com.hw.hwsafe.smart.dao.ICorpMaterialDao;
import com.hw.hwsafe.smart.pojo.CorpMaterialDetailPO;
import com.hw.hwsafe.smart.pojo.CorpMaterialPO;

public class CorpMaterialDaoImpl extends BaseDaoImpl implements
		ICorpMaterialDao {
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ICorpMaterialDao.class).retrieveByPage(map);
	}
	@Override
	public CorpMaterialPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpMaterialDao.class).retrieveInstanceById(ma001);
	}

	@Override
	public List<CorpMaterialPO> retrieveInstanceByPO(CorpMaterialPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpMaterialDao.class).retrieveInstanceByPO(a001po);
	}

	@Override
	public void insertInstance(CorpMaterialPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpMaterialDao.class).insertInstance(a001po);
	}

	@Override
	public void updateInstance(CorpMaterialPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpMaterialDao.class).updateInstance(a001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpMaterialDao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpMaterialDao.class).delBatchInstance(map);
	}

	@Override
	public CorpMaterialDetailPO retrieveDetailById(String ma001)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpMaterialDao.class).retrieveDetailById(ma001);
	}

	@Override
	public List<CorpMaterialDetailPO> retrieveDetailByPO(
			CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpMaterialDao.class).retrieveDetailByPO(a001po);
	}

	@Override
	public void insertDetail(CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpMaterialDao.class).insertDetail(a001po);
	}

	@Override
	public void updateDetail(CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpMaterialDao.class).updateDetail(a001po);
	}

	@Override
	public List<CorpMaterialDetailPO> retrieveDetails(
			CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpMaterialDao.class).retrieveDetails(a001po);
	}

	@Override
	public Integer countAll(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpMaterialDao.class).countAll(map);
	}

}
