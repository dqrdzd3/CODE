
/**
 * @title K0080101DaoImpl.java
 * @package com.hw.hwsafe.knowledge.dao.impl
 * @author 王贺禧
 * @create_time 2013-8-27 下午2:01:12
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.knowledge.dao.IK0080101Dao;
import com.hw.hwsafe.knowledge.pojo.K0080101PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class K0080101DaoImpl extends BaseDaoImpl implements IK0080101Dao {
	
	
	@Override
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IK0080101Dao.class).retrieveByPage(map);
	}

	@Override
	public K0080101PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IK0080101Dao.class).retrieveInstanceById(ma001);
	}
	
	@Override
	public List<K0080101PO> retrieveByPId(Map<String, Object> map)
			throws Exception {
		
		return getSqlSession().getMapper(IK0080101Dao.class).retrieveByPId(map);
	}


	@Override
	public Integer insertInstance(K0080101PO k0080101po) throws Exception {
		return getSqlSession().getMapper(IK0080101Dao.class).insertInstance(k0080101po);
	}

	@Override
	public Integer updateInstance(K0080101PO k0080101po) throws Exception {
		return getSqlSession().getMapper(IK0080101Dao.class).updateInstance(k0080101po);
	}


	@Override
	public Integer deleteInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IK0080101Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer deleteByPId(String pid) throws Exception {
		return getSqlSession().getMapper(IK0080101Dao.class).deleteByPId(pid);
	}





}
