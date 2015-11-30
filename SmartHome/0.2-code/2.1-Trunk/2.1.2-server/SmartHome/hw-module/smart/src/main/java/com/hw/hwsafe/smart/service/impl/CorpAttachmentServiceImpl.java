package com.hw.hwsafe.smart.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.smart.dao.ICorpAttachmentDao;
import com.hw.hwsafe.smart.pojo.CorpAttachmentPO;
import com.hw.hwsafe.smart.service.ICorpAttachmentService;

public class CorpAttachmentServiceImpl extends BaseServiceImpl implements
		ICorpAttachmentService {

	@Autowired
	private ICorpAttachmentDao corpAttachmentDao;
	
	@Override
	public CorpAttachmentPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return corpAttachmentDao.retrieveInstanceById(ma001);
	}

	@Override
	public List<CorpAttachmentPO> retrieveInstanceByPO(CorpAttachmentPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return corpAttachmentDao.retrieveInstanceByPO(a001po);
	}

	@Override
	public void insertInstance(CorpAttachmentPO a001po) throws Exception {
		// TODO Auto-generated method stub
		corpAttachmentDao.insertInstance(a001po);
	}

	@Override
	public void updateInstance(CorpAttachmentPO a001po) throws Exception {
		// TODO Auto-generated method stub
		corpAttachmentDao.updateInstance(a001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		corpAttachmentDao.deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return corpAttachmentDao.delBatchInstance(map);
	}

}
