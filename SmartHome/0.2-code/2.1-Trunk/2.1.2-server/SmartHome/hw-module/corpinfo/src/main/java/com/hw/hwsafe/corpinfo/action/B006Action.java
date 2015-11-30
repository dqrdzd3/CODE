/**
 * 文件名：B006Action.java
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
import com.hw.hwsafe.corpinfo.pojo.B006PO;
import com.hw.hwsafe.corpinfo.service.IB001Service;
import com.hw.hwsafe.corpinfo.service.IB006Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B006Action
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:00:49
 * 修改人：
 * 修改时间：2012-6-11 下午6:00:49
 * 修改备注：
 * @version 
 * 
 */
public class B006Action extends BaseAction {
	
	private B006PO b006PO;
	
	@Autowired
	private IB006Service b006Service;
	@Autowired
	private IB001Service b001Service;
	private List<B006PO> b006List;

	/**
	 * 展示所有记录列表
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
            String c004id = getRequest().getParameter("c004ids");
			String[] c004ids = c004id.split(",");
			b006PO.setC004ids(c004ids);
			b006PO.setMa001(UUIDGenerater.getUUID());
			//以后取自session
			B001PO b001po=b001Service.retrieveB001ByID(SessionUtil.getOrgId());//根据企业ID查询企业名称
			b006PO.setMa002(SessionUtil.getOrgId());
			b006PO.setMa003(b001po.getMA004());
			b006PO.setMa015(getSessionUserPO().getId());
			b006PO.setMa016(DateTimeUtils.getCurrentTimestamp());
			b006PO.setMa017(B006PO.BZ);
			b006Service.insertB006(b006PO);
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
	public String doEdit() {
		try {
			
			b006PO=b006Service.retrieveB006ByID(b006PO.getMa001());
			if(b006PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		
			//查询代码表
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON_PO;
	}
	/**
	 * 浏览操作
	 */
	public String doView() {
		try {
			
			b006PO=b006Service.retrieveB006ByID(b006PO.getMa001());
			if(b006PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		
			//查询代码表
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
			String c004id = getRequest().getParameter("c004ids");
			String[] c004ids = c004id.split(",");
			b006PO.setC004ids(c004ids);
			if(b006Service.isExist(b006PO.getMa001())){
				b006Service.updateB006(b006PO);
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

	public IB006Service getB006Service() {
		return b006Service;
	}

	public void setB006Service(IB006Service b006Service) {
		this.b006Service = b006Service;
	}
	
	public void setB006List(List<B006PO> b006List) {
		this.b006List = b006List;
	}
	
	public List<B006PO> getB006List() {
		return b006List;
	}
	
	public void setB006PO(B006PO b006PO) {
		this.b006PO = b006PO;
	}

	public B006PO getB006PO() {
		return b006PO;
	}

	public IB001Service getB001Service() {
		return b001Service;
	}

	public void setB001Service(IB001Service b001Service) {
		this.b001Service = b001Service;
	}
}
