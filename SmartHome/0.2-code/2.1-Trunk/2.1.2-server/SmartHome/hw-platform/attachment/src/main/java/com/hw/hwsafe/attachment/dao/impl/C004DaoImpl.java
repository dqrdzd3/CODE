package com.hw.hwsafe.attachment.dao.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.hw.hwsafe.attachment.dao.IC004Dao;
import com.hw.hwsafe.attachment.pojo.C004PO;

public class C004DaoImpl extends SqlSessionDaoSupport implements IC004Dao{

	@Override
	public void insertC004(C004PO c004) throws Exception {
		getSqlSession().getMapper(IC004Dao.class).insertC004(c004);
	}

	@Override
	public void insertC004TEMP(C004PO c004) throws Exception {
		getSqlSession().getMapper(IC004Dao.class).insertC004TEMP(c004);
	}

	@Override
	public C004PO getC004(Map map) throws Exception {
		return getSqlSession().getMapper(IC004Dao.class).getC004(map);
	}

	@Override
	public C004PO getC004TEMP(Map map) throws Exception {
		return getSqlSession().getMapper(IC004Dao.class).getC004TEMP(map);
	}

	@Override
	public List<C004PO> getC004List(Map map) {
		return getSqlSession().getMapper(IC004Dao.class).getC004List(map);
	}

	@Override
	public void delC004(String id) throws Exception {
		getSqlSession().getMapper(IC004Dao.class).delC004(id);
	}

	@Override
	public void delAllC004(String id) throws Exception {
		getSqlSession().getMapper(IC004Dao.class).delAllC004(id);
	}
	@Override
	public void delC004Temp(String id) {
		getSqlSession().getMapper(IC004Dao.class).delC004Temp(id);
	}

}
