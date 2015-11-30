package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B009PO;
import com.hw.hwsafe.corpinfo.service.IB009Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hw-corpinfo
 * 类名称：B009Action
 * 类描述：企业周边环境类
 * 创建人：刁海港
 * 创建时间：2013-4-10 下午1:55:07
  * @version 
 *
 */
public class B009Action extends BaseAction {
	  
	private B009PO b009PO;
	@Autowired
	private IB009Service b009Service;
	private List<B009PO> b009List;
	
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
			b009PO.setMA001(UUIDGenerater.getUUID());// 主键
			b009PO.setMA002(SessionUtil.getOrgId());
			b009PO.setMA016(SessionUtil.getUserId());
			b009Service.insertB009(b009PO);
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
			b009PO=b009Service.retrieveB009ByID(b009PO.getMA001());
			if(b009PO==null) {
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
	public String doSaveEdit(){
		try{
			if(b009Service.isExist(b009PO.getMA001())){
				b009Service.updateB009(b009PO);
                setAddSuccessMsg();
			}else {
				setEmptyDataMsg();
			}
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}

	public IB009Service getB009Service() {
		return b009Service;
	}

	public void setB009Service(IB009Service b009Service) {
		this.b009Service = b009Service;
	}

	public void setB009List(List<B009PO> b009List) {
		this.b009List = b009List;
	}

	public List<B009PO> getB009List() {
		return b009List;
	}

	public void setB009PO(B009PO b009PO) {
		this.b009PO = b009PO;
	}

	public B009PO getB009PO() {
		return b009PO;
	}
}
