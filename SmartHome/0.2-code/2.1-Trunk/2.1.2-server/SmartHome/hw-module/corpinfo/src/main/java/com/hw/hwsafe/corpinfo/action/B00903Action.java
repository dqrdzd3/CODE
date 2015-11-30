package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00903PO;
import com.hw.hwsafe.corpinfo.service.IB00903Service;
import com.hw.hwsafe.platform.action.BaseAction;

public class B00903Action extends BaseAction {
	  
	private B00903PO b00903PO;
	
	@Autowired
	private IB00903Service b00903Service;
	
	private List<B00903PO> b00903List;

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
			b00903List = b00903Service.retrieveAllB00903();
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
			b00903Service.insertB00903(b00903PO,this);
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
			b00903PO=b00903Service.retrieveB00903ByID(b00903PO.getMA001());
			if(b00903PO==null) {
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
			b00903Service.updateB00903(b00903PO,this);
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
			b00903Service.deleteB00903ByID(b00903PO.getMA001(),this);
		}catch(Exception e){
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doQuery()	{
		logger.info("----------------------doList22>>" + this.getClass().getName());
		try {
			b00903List = b00903Service.retrieveB00903ByPO(b00903PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================="+b00903List);
		return "query";
	}

	public IB00903Service getB00903Service() {
		return b00903Service;
	}

	public void setB00903Service(IB00903Service b00903Service) {
		this.b00903Service = b00903Service;
	}

	public void setB00903List(List<B00903PO> b00903List) {
		this.b00903List = b00903List;
	}

	public List<B00903PO> getB00903List() {
		return b00903List;
	}

	public void setB00903PO(B00903PO b00903PO) {
		this.b00903PO = b00903PO;
	}

	public B00903PO getB00903PO() {
		return b00903PO;
	}
}
