package com.hw.hwsafe.knowledge.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.pojo.K0080101PO;
import com.hw.hwsafe.knowledge.pojo.K00801PO;
import com.hw.hwsafe.knowledge.pojo.K008PO;
import com.hw.hwsafe.knowledge.service.IK0080101Service;
import com.hw.hwsafe.knowledge.service.IK00801Service;
import com.hw.hwsafe.knowledge.service.IK008Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

public class K008Action extends BaseAction {

	@Autowired
	private IK008Service k008Service;
	@Autowired
	private IK00801Service k00801Service;
	@Autowired
	private IK0080101Service k0080101Service;

	private K008PO k008PO;

	private String ids;

	public String doAdd() {

		return "add";
	}

	public String doSaveAdd() {
		k008PO.setMa001(UUIDGenerater.getUUID());
		k008PO.setMa005(DateTimeUtils.getCurrentDate());
		k008PO.setMa006(K008PO.ISVALID_OK);
		k008PO.setMa008(getSessionUserPO().getId());
		k008PO.setMa009(DateTimeUtils.getCurrentDate());
		try {
			k008Service.insertInstance(k008PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}

	public String doEdit() {
		retrievePO();
		return "edit";
	}
	public String doGetShow() {
		retrievePO();
		return "show";
	}
	
	public String doSaveEdit(){
		try {
			k008Service.updateInstance(k008PO);
			setUpdateSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG ;
	}

	public String doGetDelete() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids.split(","));
			k008Service.deleteInstanceById(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}

		return JSON_MSG;
	}
	
	private void retrievePO(){
		try {
			List<K00801PO> k00801pos = k00801Service.retrieveByPId(k008PO.getMa001());
			for (K00801PO k00801po : k00801pos) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("ma002", k00801po.getMa001());
				map.put("ma003", k008PO.getMa001());
				List<K0080101PO> k0080101pos = k0080101Service.retrieveByPId(map);
				k00801po.setK0080101List(k0080101pos);
			}
			k008PO = k008Service.retrieveInstanceById(k008PO.getMa001());
			k008PO.setK00801List(k00801pos);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public IK008Service getK008Service() {
		return k008Service;
	}

	public void setK008Service(IK008Service k008Service) {
		this.k008Service = k008Service;
	}

	public K008PO getK008PO() {
		return k008PO;
	}

	public void setK008PO(K008PO k008po) {
		k008PO = k008po;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public IK00801Service getK00801Service() {
		return k00801Service;
	}

	public void setK00801Service(IK00801Service k00801Service) {
		this.k00801Service = k00801Service;
	}

	public IK0080101Service getK0080101Service() {
		return k0080101Service;
	}

	public void setK0080101Service(IK0080101Service k0080101Service) {
		this.k0080101Service = k0080101Service;
	}

}
