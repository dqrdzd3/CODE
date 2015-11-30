package com.hw.hwsafe.corpinfo.dao.impl;

import java.util.Map;

import com.hw.hwsafe.corpinfo.dao.IB104Dao;
import com.hw.hwsafe.corpinfo.pojo.B104PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

public class B104DaoImpl extends BaseDaoImpl implements IB104Dao {

	@Override
	public B104PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IB104Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(B104PO b104po) throws Exception {
		getSqlSession().getMapper(IB104Dao.class).insertInstance(b104po);
	}

	@Override
	public void updateInstance(B104PO b104po) throws Exception {
		getSqlSession().getMapper(IB104Dao.class).updateInstance(b104po);
	}

	@Override
	public void deleteInstanceById(String ma001) throws Exception {
		getSqlSession().getMapper(IB104Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public void updateStatus(B104PO b104po) throws Exception {
		getSqlSession().getMapper(IB104Dao.class).updateStatus(b104po);
	}

	@Override
	public Integer delBatchB104(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IB104Dao.class).delBatchB104(map);
	}

	@Override
	public void updateRefuseStatus(B104PO b104po) throws Exception {
		getSqlSession().getMapper(IB104Dao.class).updateRefuseStatus(b104po);
	}

}
