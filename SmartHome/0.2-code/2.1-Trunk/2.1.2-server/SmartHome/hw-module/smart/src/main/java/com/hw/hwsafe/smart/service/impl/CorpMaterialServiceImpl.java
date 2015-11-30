package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ICorpMaterialDao;
import com.hw.hwsafe.smart.pojo.CorpMaterialDetailPO;
import com.hw.hwsafe.smart.pojo.CorpMaterialPO;
import com.hw.hwsafe.smart.service.ICorpMaterialService;

public class CorpMaterialServiceImpl extends BaseServiceImpl implements
		ICorpMaterialService {

	@Autowired
	private ICorpMaterialDao corpMaterialDao;
	
	
	@Override
	public CorpMaterialPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return corpMaterialDao.retrieveInstanceById(ma001);
	}

	@Override
	public List<CorpMaterialPO> retrieveInstanceByPO(CorpMaterialPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return corpMaterialDao.retrieveInstanceByPO(a001po);
	}

	@Override
	public void insertInstance(CorpMaterialPO a001po) throws Exception {
		// TODO Auto-generated method stub
		corpMaterialDao.insertInstance(a001po);
	}

	@Override
	public void updateInstance(CorpMaterialPO a001po) throws Exception {
		// TODO Auto-generated method stub
		corpMaterialDao.updateInstance(a001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		corpMaterialDao.deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpMaterialDao.delBatchInstance(map);
	}

	@Override
	public CorpMaterialDetailPO retrieveDetailById(String ma001)
			throws Exception {
		// TODO Auto-generated method stub
		return corpMaterialDao.retrieveDetailById(ma001);
	}

	@Override
	public List<CorpMaterialDetailPO> retrieveDetailByPO(
			CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		return corpMaterialDao.retrieveDetailByPO(a001po);
	}

	@Override
	public void insertDetail(CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		corpMaterialDao.insertDetail(a001po);
	}

	@Override
	public void updateDetail(CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		corpMaterialDao.updateDetail(a001po);
	}

	@Override
	public List<CorpMaterialDetailPO> retrieveDetails(
			CorpMaterialDetailPO a001po) throws Exception {
		// TODO Auto-generated method stub
		return corpMaterialDao.retrieveDetails(a001po);
	}

	@Override
	public Integer countAll(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpMaterialDao.countAll(map);
	}

}
