
/**
 * @title K0080101ServiceImpl.java
 * @package com.hw.hwsafe.knowledge.service.impl
 * @author 王贺禧
 * @create_time 2013-8-27 下午2:49:36
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.dao.IK0080101Dao;
import com.hw.hwsafe.knowledge.pojo.K0080101PO;
import com.hw.hwsafe.knowledge.service.IK0080101Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class K0080101ServiceImpl extends BaseServiceImpl implements
		IK0080101Service {
	
	@Autowired
	private IK0080101Dao k0080101Dao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return k0080101Dao.retrieveByPage(map);
	}

	@Override
	public K0080101PO retrieveInstanceById(String ma001) throws Exception {
		return k0080101Dao.retrieveInstanceById(ma001);
	}
	
	
	@Override
	public List<K0080101PO> retrieveByPId(Map<String, Object> map)
			throws Exception {
		return k0080101Dao.retrieveByPId(map);
	}

	@Override
	public Integer insertInstance(K0080101PO k0080101po) throws Exception {
		return k0080101Dao.insertInstance(k0080101po);
	}


	@Override
	public Integer updateInstance(K0080101PO k0080101po) throws Exception {
		return k0080101Dao.updateInstance(k0080101po);
	}


	@Override
	public Integer deleteInstanceById(String ma001) throws Exception {
		return k0080101Dao.deleteInstanceById(ma001);
	}
	

	@Override
	public Integer deleteByPId(String pid) throws Exception {
		return k0080101Dao.deleteByPId(pid);
	}



	public IK0080101Dao getK0080101Dao() {
		return k0080101Dao;
	}


	public void setK0080101Dao(IK0080101Dao k0080101Dao) {
		this.k0080101Dao = k0080101Dao;
	}



}
