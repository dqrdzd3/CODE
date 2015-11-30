
/**
 * @title K008ServiceImpl.java
 * @package com.hw.hwsafe.knowledge.service.impl
 * @author 王贺禧
 * @create_time 2013-8-27 上午11:34:40
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.dao.IK0080101Dao;
import com.hw.hwsafe.knowledge.dao.IK00801Dao;
import com.hw.hwsafe.knowledge.dao.IK008Dao;
import com.hw.hwsafe.knowledge.pojo.K0080101PO;
import com.hw.hwsafe.knowledge.pojo.K00801PO;
import com.hw.hwsafe.knowledge.pojo.K008PO;
import com.hw.hwsafe.knowledge.service.IK008Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.UUIDGenerater;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class K008ServiceImpl extends BaseServiceImpl implements IK008Service {
	
	@Autowired
	private IK008Dao k008Dao;
	@Autowired
	private IK00801Dao k00801Dao;
	@Autowired
	private IK0080101Dao k0080101Dao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return k008Dao.retrieveByPage(map);
	}


	@Override
	public K008PO retrieveInstanceById(String ma001) throws Exception {
		return k008Dao.retrieveInstanceById(ma001);
	}

	@Override
	public void insertInstance(K008PO k008po) throws Exception {
		k008Dao.insertInstance(k008po);
		List<K00801PO> k00801pos = k008po.getK00801List();
		if (k00801pos != null && k00801pos.size() > 0) {
			for (K00801PO k00801po : k00801pos) {
				k00801po.setMa001(UUIDGenerater.getUUID());
				k00801po.setMa002(k008po.getMa001());
				k00801po.setMa005(k008po.getMa008());
				k00801po.setMa006(k008po.getMa009());
				k00801Dao.insertInstance(k00801po);
				List<K0080101PO> k0080101pos = k00801po.getK0080101List();
				if (k0080101pos != null && k0080101pos.size() > 0) {
					for (K0080101PO k0080101po : k0080101pos) {
						k0080101po.setMa001(UUIDGenerater.getUUID());
						k0080101po.setMa002(k00801po.getMa001());
						k0080101po.setMa003(k008po.getMa001());
						k0080101po.setMa008(k008po.getMa008());
						k0080101po.setMa009(k008po.getMa009());
						k0080101Dao.insertInstance(k0080101po);
					}
				}
			}
		}
	}

	@Override
	public void updateInstance(K008PO k008po) throws Exception {
		k008Dao.updateInstance(k008po);
		k00801Dao.deleteByPId(k008po.getMa001());
		k0080101Dao.deleteByPId(k008po.getMa001());
		List<K00801PO> k00801pos = k008po.getK00801List();
		if (k00801pos != null && k00801pos.size() > 0) {
			for (K00801PO k00801po : k00801pos) {
				if(k00801po == null)continue;
				k00801po.setMa001(UUIDGenerater.getUUID());
				k00801po.setMa002(k008po.getMa001());
				k00801po.setMa005(k008po.getMa008());
				k00801po.setMa006(k008po.getMa009());
				k00801Dao.insertInstance(k00801po);
				List<K0080101PO> k0080101pos = k00801po.getK0080101List();
				if (k0080101pos != null && k0080101pos.size() > 0) {
					for (K0080101PO k0080101po : k0080101pos) {
						if(k0080101po != null ){
							k0080101po.setMa001(UUIDGenerater.getUUID());
							k0080101po.setMa002(k00801po.getMa001());
							k0080101po.setMa003(k008po.getMa001());
							k0080101po.setMa008(k008po.getMa008());
							k0080101po.setMa009(k008po.getMa009());
							k0080101Dao.insertInstance(k0080101po);
						}
					}
				}
			}
		}
	}

	@Override
	public Integer deleteInstanceById(Map<String, Object> map) throws Exception {
		return k008Dao.deleteInstanceById(map);
	}

	public IK008Dao getK008Dao() {
		return k008Dao;
	}

	public void setK008Dao(IK008Dao k008Dao) {
		this.k008Dao = k008Dao;
	}


	public IK00801Dao getK00801Dao() {
		return k00801Dao;
	}


	public void setK00801Dao(IK00801Dao k00801Dao) {
		this.k00801Dao = k00801Dao;
	}


	public IK0080101Dao getK0080101Dao() {
		return k0080101Dao;
	}


	public void setK0080101Dao(IK0080101Dao k0080101Dao) {
		this.k0080101Dao = k0080101Dao;
	}

}
