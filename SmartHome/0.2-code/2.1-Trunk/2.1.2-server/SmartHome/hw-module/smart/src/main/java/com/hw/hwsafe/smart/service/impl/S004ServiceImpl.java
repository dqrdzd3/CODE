package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IS004Dao;
import com.hw.hwsafe.smart.pojo.S004PO;
import com.hw.hwsafe.smart.service.IS004Service;

public class S004ServiceImpl extends BaseServiceImpl implements IS004Service {
	
	@Autowired
	private IS004Dao s004Dao;
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return s004Dao.retrieveByPage(map);
	}
	@Override
	public List<S004PO> getListTitle() throws Exception {
		return s004Dao.getListTitle();
	}
	@Override
	public void insertInstance(S004PO s004po) throws Exception {
		s004Dao.insertInstance(s004po);
	}
	@Override
	public Integer delBatchS004(Map<String, Object> map) throws Exception {
		return s004Dao.delBatchS004(map);
	}
	@Override
	public S004PO retrieveInstanceById(String ma001) throws Exception {
		return s004Dao.retrieveInstanceById(ma001);
	}
	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		s004Dao.deleteInstanceById(ma001);
	}
}
