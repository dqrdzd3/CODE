package com.hw.hwsafe.knowledge.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.pojo.K001PO;
import com.hw.hwsafe.knowledge.service.IK001Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.utils.UUIDGenerater;

public class K001Action extends BaseAction{
	
	@Autowired
	private IK001Service k001Service;
	
	private K001PO k001PO;
	
	private String ids;
	

	
	public String doIndex(){
		return SUCCESS;
	}
	
	public String doAdd(){
		return "add";
	}
	
	public String doSaveAdd(){
		try {
			k001PO.setMa001(UUIDGenerater.getUUID());
			k001PO.setMa005(new Date());
			k001Service.insertInstance(k001PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doEdit(){
		try {
			k001PO = k001Service.retrieveInstanceById(k001PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	public String doSaveEdit(){
		try {
			k001Service.updateInstance(k001PO);
			setUpdateSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doDelete(){
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("idArray", ids.split(","));
			k001Service.delBatchK001(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doShow(){
		try {
			k001PO = k001Service.retrieveInstanceById(k001PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}

	public IK001Service getK001Service() {
		return k001Service;
	}

	public void setK001Service(IK001Service k001Service) {
		this.k001Service = k001Service;
	}

	public K001PO getK001PO() {
		return k001PO;
	}

	public void setK001PO(K001PO k001po) {
		k001PO = k001po;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
