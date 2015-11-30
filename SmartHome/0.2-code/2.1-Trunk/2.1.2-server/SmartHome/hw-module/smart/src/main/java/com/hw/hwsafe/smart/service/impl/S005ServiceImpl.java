package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IS005Dao;
import com.hw.hwsafe.smart.pojo.S005PO;
import com.hw.hwsafe.smart.service.IS005Service;

public class S005ServiceImpl extends BaseServiceImpl implements IS005Service {
	
	@Autowired
	private IS005Dao s005Dao;
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return s005Dao.retrieveByPage(map);
	}
	@Override
	public void addMessage(S005PO s005po) throws Exception {
		s005Dao.addMessage(s005po);
	}

	@Override
	public List<Map<String, Object>> getReplyList(String titleId) throws Exception{
		return s005Dao.getReplyList(titleId);
	}
	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		s005Dao.deleteInstanceById(ma001);
	}
	@Override
	public int getCounts(String ma001) throws Exception {
		return s005Dao.getCounts(ma001);
	}
}
