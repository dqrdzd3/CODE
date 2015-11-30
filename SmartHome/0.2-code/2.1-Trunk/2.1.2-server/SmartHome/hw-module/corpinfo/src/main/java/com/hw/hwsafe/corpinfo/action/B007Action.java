/**
 * 文件名：B007Action.java
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
import com.hw.hwsafe.corpinfo.pojo.B007PO;
import com.hw.hwsafe.corpinfo.service.IB001Service;
import com.hw.hwsafe.corpinfo.service.IB007Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B007Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:00:49
 * 修改人：
 * 修改时间：2012-6-11 下午6:00:49
 * 修改备注：
 * @version 
 * 
 */
public class B007Action extends BaseAction {
	
	private B007PO b007PO;
	
	@Autowired
	private IB007Service b007Service;
	
	@Autowired
	private IB001Service b001Service;
	
	private List<B007PO> b007List;

	/**
	 * 展示所有的企业列表
	 */
	public String doList() {
		return SUCCESS;
	}
	
	/**
	 * 增加操作
	 */
	public String doAdd() throws Exception{
		try {
			b007PO=new B007PO();
			UserPO userPO = this.getSessionUserPO();
			String corpid=userPO.getCorpid();
			B001PO b001po=b001Service.retrieveB001ByID(corpid);//根据企业ID查询企业名称
			b007PO.setMa002(corpid);//
			if(b001po!=null)
			b007PO.setMa004(b001po.getMA004());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "add";
	}
	
	/**
	 * 增加保存操作
	 */
	public String doSaveAdd(){
		try{
			b007PO.setMa001(UUIDGenerater.getUUID());
			B001PO b001po=b001Service.retrieveB001ByID(SessionUtil.getOrgId());//根据企业ID查询企业名称
	        if(b001po!=null)
			b007PO.setMa004(b001po.getMA004());
			b007PO.setMa002(SessionUtil.getOrgId());//
			b007PO.setMa019(SessionUtil.getUserId());
			b007PO.setMa020(DateTimeUtils.getCurrentDate());
			b007Service.insertB007(b007PO);
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

			b007PO = b007Service.retrieveB007ByID(b007PO.getMa001());
			if (b007PO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	/**
	 * 浏览参考操作
	 */
	public String doView() {
		try {

			b007PO = b007Service.retrieveB007ByID(b007PO.getMa001());
			if (b007PO == null) {
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
			if(b007Service.isExist(b007PO.getMa001())){
				b007Service.updateB007(b007PO);
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
	
	public IB007Service getB007Service() {
		return b007Service;
	}

	public void setB007Service(IB007Service b007Service) {
		this.b007Service = b007Service;
	}

	public IB001Service getB001Service() {
		return b001Service;
	}

	public void setB001Service(IB001Service b001Service) {
		this.b001Service = b001Service;
	}
	
	public void setB007List(List<B007PO> b007List) {
		this.b007List = b007List;
	}
	
	public List<B007PO> getB007List() {
		return b007List;
	}
	
	public void setB007PO(B007PO b007PO) {
		this.b007PO = b007PO;
	}

	public B007PO getB007PO() {
		return b007PO;
	}
	
}
