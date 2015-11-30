/**
 * 
 */
package com.hw.hwsafe.knowledge.dao.impl;



import java.util.List;
import java.util.Map;

import com.hw.hwsafe.knowledge.dao.IK008Dao;
import com.hw.hwsafe.knowledge.pojo.K008PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;

/**
 * @author 王贺禧
 *
 */
public class K008DaoImpl extends BaseDaoImpl implements IK008Dao  {
	
	
	@Override
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IK008Dao.class).retrieveByPage(map);
	}


	@Override
	public K008PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IK008Dao.class).retrieveInstanceById(ma001);
	}

	@Override
	public Integer insertInstance(K008PO k008po) throws Exception {
		return getSqlSession().getMapper(IK008Dao.class).insertInstance(k008po);
	}

	@Override
	public Integer updateInstance(K008PO k008po) throws Exception {
		return getSqlSession().getMapper(IK008Dao.class).updateInstance(k008po);
	}

	@Override
	public Integer deleteInstanceById(Map<String, Object> map) throws Exception {
		return getSqlSession().getMapper(IK008Dao.class).deleteInstanceById(map);
	}




}
