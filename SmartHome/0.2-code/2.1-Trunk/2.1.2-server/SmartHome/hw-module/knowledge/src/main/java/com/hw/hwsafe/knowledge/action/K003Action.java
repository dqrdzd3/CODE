/**
 * 文件名：K003Action.java
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
import com.hw.hwsafe.knowledge.pojo.K003PO;
import com.hw.hwsafe.knowledge.service.IK003Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：K003Action
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-7-13 上午11:02:39
 * 修改人：李玉梅
 * 修改时间：2012-7-13 上午11:02:39
 * 修改备注：
 * @version 
 */
public class K003Action extends BaseAction {

	private K003PO k003PO;

	@Autowired
	private IK003Service k003Service;//k003Service
	
	private List<K003PO> k003List;
	
	@Autowired
	private IAttachmentService attachmentService;


	/**
	 * 展示所有记录列表
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
			k003PO.setC004ids(c004ids);
			k003PO.setMa001(UUIDGenerater.getUUID());
			k003PO.setMa014(getSessionUserPO().getId());
			k003PO.setMa015(DateTimeUtils.getCurrentDate());
			k003Service.insertK003(k003PO);
			setAddSuccessMsg();
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
			k003PO = k003Service.retrieveK003ByID(k003PO.getMa001());
			if (k003PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			List<C004PO> c004poList = attachmentService.getC004List(k003PO.getMa001(),null);
			k003PO.setC004poList(c004poList);
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
			if(k003Service.isExist(k003PO.getMa001())){
				String c004id =request.getParameter("c004ids");
				if(c004id!=null){
					String[] c004ids = c004id.split(",");
					k003PO.setC004ids(c004ids);
				}
				k003Service.updateK003(k003PO);
				setUpdateSuccessMsg();
			}else{
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
			k003PO = k003Service.retrieveK003ByID(k003PO.getMa001());
			if (k003PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			List<C004PO> c004poList = attachmentService.getC004List(k003PO.getMa001(),null);
			k003PO.setC004poList(c004poList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	public IK003Service getK003Service() {
		return k003Service;
	}

	public void setK003Service(IK003Service k003Service) {
		this.k003Service = k003Service;
	}

	public K003PO getK003PO() {
		return k003PO;
	}

	public void setK003PO(K003PO k003po) {
		k003PO = k003po;
	}

	public List<K003PO> getK003List() {
		return k003List;
	}

	public void setK003List(List<K003PO> k003List) {
		this.k003List = k003List;
	}
	
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
}
	

