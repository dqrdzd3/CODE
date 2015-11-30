/**
 * 文件名：B008Action.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B001PO;
import com.hw.hwsafe.corpinfo.pojo.B008PO;
import com.hw.hwsafe.corpinfo.service.IB001Service;
import com.hw.hwsafe.corpinfo.service.IB008Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B008Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:00:49
 * 修改人：
 * 修改时间：2012-6-11 下午6:00:49
 * 修改备注：
 * @version 
 * 
 */
public class B008Action extends BaseAction {
	
	private B008PO b008PO;
	
	@Autowired
	private IB008Service b008Service;
	@Autowired
	private IB001Service b001Service;
	
	private List<B008PO> b008List;

	/**
	 * 展示所有的企业列表
	 */
	public String doList() {
		return SUCCESS;
	}
	/**
	 * 跳转到添加页面
	 */
	public String doAdd(){
		return "add";
	}
	
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd(){
		try{
			b008PO.setMa001(UUIDGenerater.getUUID());
			B001PO b001po=b001Service.retrieveB001ByID(SessionUtil.getOrgId());//根据企业ID查询企业名称
			b008PO.setMa003(b001po.getMA004());
			b008PO.setMa002(b001po.getMA001());
			b008PO.setMa009(getSessionUserPO().getId());
			b008PO.setMa010(DateTimeUtils.getCurrentDate());
			b008Service.insertB008(b008PO);
			setAddSuccessMsg();
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 修改操作
	 */
	public String doEdit()	{
		try {
			b008PO=b008Service.retrieveB008ByID(b008PO.getMa001());
			if(b008PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON_PO;
	}
	/**
	 * 浏览操作查看记录
	 */
	public String doView()	{
		try {
			b008PO=b008Service.retrieveB008ByID(b008PO.getMa001());
			if(b008PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "view";
	}
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit(){
		try{
			if(b008Service.isExist(b008PO.getMa001())){
				b008Service.updateB008(b008PO);
				setUpdateSuccessMsg();
			}else {
				setEmptyDataMsg();
			}
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}

	public IB008Service getB008Service() {
		return b008Service;
	}

	public void setB008Service(IB008Service b008Service) {
		this.b008Service = b008Service;
	}

	public void setB008List(List<B008PO> b008List) {
		this.b008List = b008List;
	}

	public List<B008PO> getB008List() {
		return b008List;
	}

	public void setB008PO(B008PO b008PO) {
		this.b008PO = b008PO;
	}

	public B008PO getB008PO() {
		return b008PO;
	}
	public IB001Service getB001Service() {
		return b001Service;
	}
	public void setB001Service(IB001Service b001Service) {
		this.b001Service = b001Service;
	}
	
}
