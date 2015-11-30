
/**
 * @title K00801DaoImpl.java
 * @package com.hw.hwsafe.knowledge.dao.impl
 * @author 王贺禧
 * @create_time 2013-8-27 下午1:59:30
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.dao.impl;

import java.util.List;
import java.util.Map;
import com.hw.hwsafe.knowledge.dao.IK00801Dao;
import com.hw.hwsafe.knowledge.pojo.K00801PO;
import com.hw.hwsafe.platform.dao.impl.BaseDaoImpl;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class K00801DaoImpl extends BaseDaoImpl implements IK00801Dao {
	
	
	@Override
	@SuppressWarnings("rawtypes")
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(IK00801Dao.class).retrieveByPage(map);
	}

	@Override
	public K00801PO retrieveInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IK00801Dao.class).retrieveInstanceById(ma001);
	}
	

	@Override
	public List<K00801PO> retrieveByPId(String pid) throws Exception {
		return getSqlSession().getMapper(IK00801Dao.class).retrieveByPId(pid);
	}


	@Override
	public Integer insertInstance(K00801PO k00801po) throws Exception {
		return getSqlSession().getMapper(IK00801Dao.class).insertInstance(k00801po);
	}


	@Override
	public Integer updateInstance(K00801PO k00801po) throws Exception {
		return getSqlSession().getMapper(IK00801Dao.class).updateInstance(k00801po);
	}

	@Override
	public Integer deleteInstanceById(String ma001) throws Exception {
		return getSqlSession().getMapper(IK00801Dao.class).deleteInstanceById(ma001);
	}

	@Override
	public Integer deleteByPId(String pid) throws Exception {
		return getSqlSession().getMapper(IK00801Dao.class).deleteByPId(pid);
	}




}
