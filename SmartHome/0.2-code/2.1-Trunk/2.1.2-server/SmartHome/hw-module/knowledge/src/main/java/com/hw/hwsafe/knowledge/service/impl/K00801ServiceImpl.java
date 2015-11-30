
/**
 * @title K00801Service.java
 * @package com.hw.hwsafe.knowledge.service
 * @author 王贺禧
 * @create_time 2013-8-27 下午2:31:08
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.knowledge.dao.IK00801Dao;
import com.hw.hwsafe.knowledge.pojo.K00801PO;
import com.hw.hwsafe.knowledge.service.IK00801Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class K00801ServiceImpl extends BaseServiceImpl implements IK00801Service {
	
	@Autowired
	private IK00801Dao k00801Dao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return k00801Dao.retrieveByPage(map);
	}


	@Override
	public K00801PO retrieveInstanceById(String ma001) throws Exception {
		return k00801Dao.retrieveInstanceById(ma001);
	}


	@Override
	public Integer insertInstance(K00801PO k00801po) throws Exception {
		return k00801Dao.insertInstance(k00801po);
	}


	@Override
	public Integer updateInstance(K00801PO k00801po) throws Exception {
		return k00801Dao.updateInstance(k00801po);
	}


	@Override
	public Integer deleteInstanceById(String ma001) throws Exception {
		return k00801Dao.deleteInstanceById(ma001);
	}
	

	@Override
	public List<K00801PO> retrieveByPId(String pid) throws Exception {
		return k00801Dao.retrieveByPId(pid);
	}
	

	@Override
	public Integer deleteByPId(String pid) throws Exception {
		return k00801Dao.deleteByPId(pid);
	}



	public IK00801Dao getK00801Dao() {
		return k00801Dao;
	}


	public void setK00801Dao(IK00801Dao k00801Dao) {
		this.k00801Dao = k00801Dao;
	}





}
