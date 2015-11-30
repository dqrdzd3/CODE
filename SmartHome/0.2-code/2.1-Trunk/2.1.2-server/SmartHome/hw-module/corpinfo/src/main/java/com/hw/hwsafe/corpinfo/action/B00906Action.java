package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00906PO;
import com.hw.hwsafe.corpinfo.service.IB00906Service;
import com.hw.hwsafe.platform.action.BaseAction;

public class B00906Action extends BaseAction {
	  
	private B00906PO b00906PO;
	
	@Autowired
	private IB00906Service b00906Service;
	
	private List<B00906PO> b00906List;

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
			b00906List = b00906Service.retrieveAllB00906();
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
			b00906Service.insertB00906(b00906PO,this);
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
			b00906PO=b00906Service.retrieveB00906ByID(b00906PO.getMA001());
			if(b00906PO==null) {
				setEmptyDataMsg();
				return JSON_MSG;
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
			b00906Service.updateB00906(b00906PO,this);
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
			b00906Service.deleteB00906ByID(b00906PO.getMA001(),this);
		}catch(Exception e){
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doQuery()	{
		logger.info("----------------------doList22>>" + this.getClass().getName());
		try {
			b00906List = b00906Service.retrieveB00906ByPO(b00906PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================="+b00906List);
		return "query";
	}

	public IB00906Service getB00906Service() {
		return b00906Service;
	}

	public void setB00906Service(IB00906Service b00906Service) {
		this.b00906Service = b00906Service;
	}

	public void setB00906List(List<B00906PO> b00906List) {
		this.b00906List = b00906List;
	}

	public List<B00906PO> getB00906List() {
		return b00906List;
	}

	public void setB00906PO(B00906PO b00906PO) {
		this.b00906PO = b00906PO;
	}

	public B00906PO getB00906PO() {
		return b00906PO;
	}
}
