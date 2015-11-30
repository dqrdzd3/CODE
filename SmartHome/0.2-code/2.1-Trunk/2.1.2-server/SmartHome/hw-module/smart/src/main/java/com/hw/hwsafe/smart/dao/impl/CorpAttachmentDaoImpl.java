package com.hw.hwsafe.smart.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;
import com.hw.hwsafe.smart.dao.ICorpAttachmentDao;
import com.hw.hwsafe.smart.pojo.CorpAttachmentPO;

public class CorpAttachmentDaoImpl extends BaseDaoImpl implements ICorpAttachmentDao {

	@Override
	public CorpAttachmentPO retrieveInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpAttachmentDao.class).retrieveInstanceById(ma001);
	}

	@Override
	public List<CorpAttachmentPO> retrieveInstanceByPO(CorpAttachmentPO a001po)
			throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpAttachmentDao.class).retrieveInstanceByPO(a001po);
	}

	@Override
	public void insertInstance(CorpAttachmentPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpAttachmentDao.class).insertInstance(a001po);
	}

	@Override
	public void updateInstance(CorpAttachmentPO a001po) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpAttachmentDao.class).updateInstance(a001po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		// TODO Auto-generated method stub
		getSqlSession().getMapper(ICorpAttachmentDao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer delBatchInstance(Map<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return getSqlSession().getMapper(ICorpAttachmentDao.class).delBatchInstance(map);
	}

}
