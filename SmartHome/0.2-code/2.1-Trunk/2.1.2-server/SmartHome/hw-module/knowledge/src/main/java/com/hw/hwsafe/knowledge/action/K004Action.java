/**
 * 文件名：K004Action.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.pojo.C004PO;
import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.knowledge.pojo.K004PO;
import com.hw.hwsafe.knowledge.service.IK004Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：K004Action
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-7-13 上午11:02:49
 * 修改人：李玉梅
 * 修改时间：2012-7-13 上午11:02:49
 * 修改备注：
 * @version 
 *
 */
public class K004Action extends BaseAction {

	private K004PO k004PO;

	@Autowired
	private IK004Service k004Service;//k004Service
	@Autowired
	private IAttachmentService attachmentService;
	
	private List<K004PO> k004List;

	/**
	 * 展示所有的预案列表
	 */
	public String doList() {
		return SUCCESS;
	}
	/**
	 * 跳转到添加页面
	 */
	public String doAdd() {
		return "add";
	}
	
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd(){
		try{
			String[] c004ids = request.getParameter("c004ids").split(",");
			k004PO.setC004ids(c004ids);
			k004PO.setMa014(getSessionUserPO().getId());
			k004PO.setMa001(UUIDGenerater.getUUID());
			k004PO.setMa015(DateTimeUtils.getCurrentDate());
			message = k004Service.insertK004(k004PO);
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	
	/**
	 * 跳转到修改页面
	 */
	public String doEdit() {
		try {
			k004PO = k004Service.retrieveK004ByID(k004PO.getMa001());
			if (k004PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			List<C004PO> c004poList = attachmentService.getC004List(k004PO.getMa001(),null);
			k004PO.setC004poList(c004poList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit(){
		try{
			if(k004Service.isExist(k004PO.getMa001())){
				String c004id =request.getParameter("c004ids");
				if(c004id!=null){
					String[] c004ids = c004id.split(",");
					k004PO.setC004ids(c004ids);
				}
				message = k004Service.updateK004(k004PO);
			}else {
				setEmptyDataMsg();
			}
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 浏览操作
	 */
	public String doView() {
		try {
			k004PO = k004Service.retrieveK004ByID(k004PO.getMa001());
			if (k004PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			List<C004PO> c004poList = attachmentService.getC004List(k004PO.getMa001(),null);
			k004PO.setC004poList(c004poList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "view";
	}

	public IK004Service getK004Service() {
		return k004Service;
	}

	public void setK004Service(IK004Service k004Service) {
		this.k004Service = k004Service;
	}

	public K004PO getK004PO() {
		return k004PO;
	}

	public void setK004PO(K004PO k004po) {
		k004PO = k004po;
	}

	public List<K004PO> getK004List() {
		return k004List;
	}

	public void setK004List(List<K004PO> k004List) {
		this.k004List = k004List;
	}
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
}
	

