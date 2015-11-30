package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00901PO;
import com.hw.hwsafe.corpinfo.service.IB00901Service;
import com.hw.hwsafe.platform.action.BaseAction;

public class B00901Action extends BaseAction {
	  
	private B00901PO b00901PO;
	
	@Autowired
	private IB00901Service b00901Service;
	
	private List<B00901PO> b00901List;

	/**
	 * 索引页面
	 */
	public String doIndex() {
		logger.info("----------------------doIndex >>"
				+ this.getClass().getName());
		return "index";
	}

	/**
	 * 展示所有的企业列表
	 */
	public String doList() {
		logger.info("----------------------doList >>" + this.getClass().getName());
		try {
			b00901List = b00901Service.retrieveAllB00901();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/*
	 * 增加操作
	 */
	public String doAdd(){
		return "add";
	}
	
	/*
	 * 增加保存操作
	 */
	public String doSaveAdd(){
		try{
			b00901Service.insertB00901(b00901PO,this);
		}catch(Exception e) {
			e.printStackTrace();
			setAddFailedMsg();
		}
		return JSON_MSG;
	}
	
	/*
	 * 修改操作
	 */
	public String doEdit()	{
		try {		
			b00901PO=b00901Service.retrieveB00901ByID(b00901PO.getMA001());
			if(b00901PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
			}
			else {
			
			}
			//查询代码表
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return JSON_PO;
	}
	
	/*
	 * 修改保存操作
	 */
	public String doSaveEdit(){
		try{
			b00901Service.updateB00901(b00901PO,this);
		}catch(Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/*
	 * 删除操作
	 */
	public String doDelete(){
		logger.info("----------------------doDelete >>" + this.getClass().getName());
		try{	
			b00901Service.deleteB00901ByID(b00901PO.getMA001(),this);
		}catch(Exception e){
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doQuery()	{
		logger.info("----------------------doList22>>" + this.getClass().getName());
		try {
			b00901List = b00901Service.retrieveB00901ByPO(b00901PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================="+b00901List);
		return "query";
	}

	public IB00901Service getB00901Service() {
		return b00901Service;
	}

	public void setB00901Service(IB00901Service b00901Service) {
		this.b00901Service = b00901Service;
	}

	public void setB00901List(List<B00901PO> b00901List) {
		this.b00901List = b00901List;
	}

	public List<B00901PO> getB00901List() {
		return b00901List;
	}

	public void setB00901PO(B00901PO b00901PO) {
		this.b00901PO = b00901PO;
	}

	public B00901PO getB00901PO() {
		return b00901PO;
	}
	
}
