package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.IS006Dao;
import com.hw.hwsafe.smart.pojo.S006PO;
import com.hw.hwsafe.smart.service.IS006Service;

public class S006ServiceImpl extends BaseServiceImpl implements IS006Service {
	
	@Autowired
	private IS006Dao s006Dao;
	//分页
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return s006Dao.retrieveByPage(map);
	}
	//添加留言
	@Override
	public void addMessage(S006PO s006po)  throws Exception{
		s006Dao.addMessage(s006po);
	}
	//查询该用户的所有留言信息
	@Override
	public List<S006PO> getListMessage(String userId)  throws Exception{
		return s006Dao.getListMessage(userId);
	}
	@Override
	public Integer delBatchS006(Map<String, Object> map) throws Exception {
		return s006Dao.delBatchS006(map);
	}
	@Override
	public S006PO retrieveInstanceById(String ma001) throws Exception {
		return s006Dao.retrieveInstanceById(ma001);
	}
	@Override
	public void updateInstance(S006PO s006po) throws Exception {
		s006Dao.updateInstance(s006po);
	}
}
