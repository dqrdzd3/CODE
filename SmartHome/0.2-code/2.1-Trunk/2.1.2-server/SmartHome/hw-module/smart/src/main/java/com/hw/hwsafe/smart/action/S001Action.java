package com.hw.hwsafe.smart.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.smart.pojo.S001PO;
import com.hw.hwsafe.smart.service.IS001Service;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

public class S001Action extends BaseAction{
	
	@Autowired
	private IS001Service s001Service;
	
	private S001PO s001PO;
	
	private String ids;
	

	public String doIndex(){
		return SUCCESS;
	}
	
	public String doAdd(){
		return "add";
	}
	
	public String doSaveAdd(){
		try {
			s001PO.setMa001(UUIDGenerater.getUUID());
			s001PO.setMa004(DateTimeUtils.getCurFormatDate());
			s001Service.insertInstance(s001PO);
			setAddSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doEdit(){
		try {
			s001PO = s001Service.retrieveInstanceById(s001PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "edit";
	}
	public String doSaveEdit(){
		try {
			s001PO.setMa004(DateTimeUtils.getCurFormatDate());
			s001Service.updateInstance(s001PO);
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
			s001Service.delBatchS001(map);
			setDelSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	/**
	 * 
	 * 点击查看详细
	 * @return          
	 * @author liyuhao
	 * @create_time 2014年7月23日下午6:51:41
	 */
	public String doShow(){
		try {
			s001PO = s001Service.retrieveInstanceById(s001PO.getMa001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "show";
	}
	/**
	 * 
	 * 点击浏览按钮
	 * @return          
	 * @author liyuhao
	 * @create_time 2014年7月23日下午6:52:24
	 */
	public String doBrowse(){
		List<Map<String, Object>> qaList = null;
		try {
			qaList=s001Service.getQAList();
			request.setAttribute("qaList", qaList);
		} catch (Exception e) {
			e.printStackTrace();
		}//调用service,查询数据库
		return "browse";
	}
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public S001PO getS001PO() {
		return s001PO;
	}

	public void setS001PO(S001PO s001po) {
		s001PO = s001po;
	}

	public IS001Service getS001Service() {
		return s001Service;
	}

	public void setS001Service(IS001Service s001Service) {
		this.s001Service = s001Service;
	}
}
