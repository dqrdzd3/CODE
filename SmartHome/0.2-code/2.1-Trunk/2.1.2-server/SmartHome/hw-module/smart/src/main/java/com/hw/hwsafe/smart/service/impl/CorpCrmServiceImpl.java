package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ICorpCrmDao;
import com.hw.hwsafe.smart.pojo.CorpCrmPO;
import com.hw.hwsafe.smart.service.ICorpCrmService;

public  class CorpCrmServiceImpl extends BaseServiceImpl implements
		ICorpCrmService {

	@Autowired
	private ICorpCrmDao corpCrmDao;
	
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return corpCrmDao.retrieveByPage(map);
	}
	@Override
	public CorpCrmPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.retrieveInstanceById(ma001);
	}

	@Override
	public List<CorpCrmPO> retrieveInstanceByPO(CorpCrmPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.retrieveInstanceByPO(a001po);
	}

	@Override
	public void insertInstance(CorpCrmPO a001po) throws Exception {
		// TODO Auto-generated method stub
		corpCrmDao.insertInstance(a001po);
	}

	@Override
	public void updateInstance(CorpCrmPO a001po) throws Exception {
		// TODO Auto-generated method stub
        corpCrmDao.updateInstance(a001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
        corpCrmDao.deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.delBatchInstance(map);
	}

	@Override
	public List<CorpCrmPO> doListPotentialCustomers(Map<String, Object> map)
			throws Exception {
		CorpCrmPO po = new CorpCrmPO();
		po.setMa011(1);
		
		return retrieveInstanceByPO(po);
	}

	@Override
	public void focusOnPotentialCustomers(Map<String, Object> map)
			throws Exception {
		CorpCrmPO po = new CorpCrmPO();
		po.setMa001(map.get("ma001").toString());
		po.setMa011(1);
		updateInstance(po);
	}

	@Override
	public Integer focusBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.focusBatchInstance(map);
	}

	@Override
	public List<CorpCrmPO> retrieveInstanceByMap(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.retrieveInstanceByMap(map);
	}

	@Override
	public List<Map<String, Object>> doListDeviceByTime(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.doListDeviceByTime(map);
	}

	@Override
	public List<Map<String, Object>> doListDeviceByGeo(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.doListDeviceByGeo(map);
	}

	@Override
	public Integer countAll(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.countAll(map);
	}

	@Override
	public Integer countFocus(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.countFocus(map);
	}

	@Override
	public List<Map<String, Object>> countGeoByTime(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.countGeoByTime(map);
	}

	@Override
	public List<Map<String, Object>> doListMenu(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.doListMenu(map);
	}
	@Override
	public List<Map<String, Object>> doListMenuSensor(Map<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return corpCrmDao.doListMenuSensor(map);
	}

	
	

}
