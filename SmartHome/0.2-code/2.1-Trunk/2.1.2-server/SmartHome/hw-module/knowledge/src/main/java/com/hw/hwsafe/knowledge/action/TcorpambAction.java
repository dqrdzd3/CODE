/**
 * 文件名：AccidentCasesAction.java
 *
 * 版本信息：
 * 日期：2012-5-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.knowledge.pojo.TcorpambPO;
import com.hw.hwsafe.knowledge.service.ITcorpambService;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：TcorpambAction
 * 类描述：政府预案库
 * 创建人：李玉梅
 * 创建时间：2012-6-6 上午9:04:35
 * 修改人：李玉梅
 * 修改时间：2012-6-6 上午9:04:35
 * 修改备注：
 * @version 
 *
 */
public class TcorpambAction  extends BaseAction {
	
	private TcorpambPO tcorpambPO;
	
	@Autowired
	private ITcorpambService tcorpambService;
	
	private List<TcorpambPO> tcorpambList;

	/**
	 * 跳转到List页面
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
	 * 添加记录
	 */
	public String doSaveAdd(){
		try{
			UserPO userPO = this.getSessionUserPO();
			tcorpambPO.setCorpid(userPO.getCorpid());
			tcorpambPO.setObjid(UUIDGenerater.getUUID());
			tcorpambPO.setCreatetime(DateTimeUtils.getCurrentDate());
			tcorpambPO.setCreator(this.getSessionUserPO().getId());
		    tcorpambService.insertTcorpamb(tcorpambPO);
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
			tcorpambPO = tcorpambService.retrieveTcorpambByID(tcorpambPO.getObjid());
			setJsonPO(tcorpambPO);
			if (tcorpambPO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PO;
	}
	
	/**
	 * 修改保存操作
	 */
	public String doSaveEdit() {
		try {
			if(tcorpambService.isExist(tcorpambPO.getObjid())){
				tcorpambService.updateTcorpamb(tcorpambPO);
				setUpdateSuccessMsg();
			}else {
				setEmptyDataMsg();
			}
		} catch (Exception e) {
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
			tcorpambPO = tcorpambService.retrieveTcorpambByID(tcorpambPO.getObjid());
			setJsonPO(tcorpambPO);
			if (tcorpambPO == null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "view";
	}

	public ITcorpambService getTcorpambService() {
		return tcorpambService;
	}

	public void setTcorpambService(ITcorpambService tcorpambService) {
		this.tcorpambService = tcorpambService;
	}

	public void setTcorpambList(List<TcorpambPO> tcorpambList) {
		this.tcorpambList = tcorpambList;
	}

	public List<TcorpambPO> getTcorpambList() {
		return tcorpambList;
	}

	public void setTcorpambPO(TcorpambPO tcorpambPO) {
		this.tcorpambPO = tcorpambPO;
	}

	public TcorpambPO getTcorpambPO() {
		return tcorpambPO;
	}
}
