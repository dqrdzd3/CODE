package com.hw.hwsafe.smart.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ID005Dao;
import com.hw.hwsafe.smart.pojo.D005PO;
import com.hw.hwsafe.smart.service.ID005Service;

public class D005ServiceImpl extends BaseServiceImpl implements ID005Service {
	
	@Autowired
	private ID005Dao d005Dao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return d005Dao.retrieveByPage(map);
	}

	@Override
	public D005PO retrieveInstanceById(String ma001) throws Exception {
		return d005Dao.retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(D005PO d005po) throws Exception {
		d005Dao.insertInstance(d005po);
	}

	@Override
	public void updateInstance(D005PO d005po) throws Exception {
		d005Dao.updateInstance(d005po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		d005Dao.deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchD005(Map<String, Object> map) throws Exception {
		return d005Dao.delBatchD005(map);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage1(Map map) throws Exception {
		return d005Dao.retrieveByPage1(map);
	}

	@Override
	public List<HashMap<String, Object>> groupByMA002(
			HashMap<String, Object> map) throws Exception {
		return d005Dao.groupByMA002(map);
	}

	@Override
	public List<HashMap<String, Object>> groupByMA003(
			HashMap<String, Object> map) throws Exception {
		return d005Dao.groupByMA003(map);
	}

	@Override
	public List<HashMap<String, Object>> groupByMA007(
			HashMap<String, Object> map) throws Exception {
		return d005Dao.groupByMA007(map);
	}

}
