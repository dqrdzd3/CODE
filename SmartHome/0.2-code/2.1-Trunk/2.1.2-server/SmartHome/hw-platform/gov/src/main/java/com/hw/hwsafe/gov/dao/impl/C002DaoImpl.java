package com.hw.hwsafe.gov.dao.impl;

import java.util.List;
import java.util.Map;
import com.hw.hwsafe.gov.dao.IC002Dao;
import com.hw.hwsafe.gov.pojo.C002PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

public class C002DaoImpl extends BaseDaoImpl implements IC002Dao{

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).retrieveByPage(map);
	}

	@Override
	public C002PO retrieveC002ByMa001(String ma001) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).retrieveC002ByMa001(ma001);
	}

	@Override
	public List<C002PO> retrieveC002ByPO(C002PO c002po) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).retrieveC002ByPO(c002po);
	}

	@Override
	public C002PO retrieveC002ByMa002(String ma002) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).retrieveC002ByMa002(ma002);
	}

	@Override
	public List<C002PO> retrieveChildC002ListByMa002(String ma002)
			throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).retrieveChildC002ListByMa002(ma002);
	}

	@Override
	public List<C002PO> retrieveAll() throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).retrieveAll();
	}

	@Override
	public List<C002PO> retrieveAllValid() throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).retrieveAllValid();
	}
	

	@Override
	public Integer countInstanceByMa003(C002PO c002PO) {
		return getSqlSession().getMapper(IC002Dao.class).countInstanceByMa003(c002PO);
	}
	
	@Override
	public Integer countC002ByMa002(String ma002) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).countC002ByMa002(ma002);
	}

	@Override
	public Integer insertC002(C002PO c002po) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).insertC002(c002po);
	}

	@Override
	public Integer updateC002(C002PO c002po) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).updateC002(c002po);
	}

	@Override
	public Integer countInstanceByCode(C002PO c002po) {
		return getSqlSession().getMapper(IC002Dao.class).countInstanceByCode(c002po);
	}

	@Override
	public Integer countInstanceByMa007(C002PO c002po) {
		return getSqlSession().getMapper(IC002Dao.class).countInstanceByMa007(c002po);
	}

	@Override
	public Integer deleteC002ByIds(Map<String,Object> args) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).deleteC002ByIds(args);
	}

	@Override
	public Integer countChildByIds(Map<String,Object> args) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).countChildByIds(args);
	}

	@Override
	public C002PO getC002InfoByCorpId(String corpId) throws Exception {
		return getSqlSession().getMapper(IC002Dao.class).getC002InfoByCorpId(corpId);
	}

}
