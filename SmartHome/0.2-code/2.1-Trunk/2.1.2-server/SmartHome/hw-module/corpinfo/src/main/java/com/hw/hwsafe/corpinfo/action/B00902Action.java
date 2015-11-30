package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00902PO;
import com.hw.hwsafe.corpinfo.service.IB00902Service;
import com.hw.hwsafe.platform.action.BaseAction;

public class B00902Action extends BaseAction {
	  
	private B00902PO b00902PO;
	
	@Autowired
	private IB00902Service b00902Service;
	
	private List<B00902PO> b00902List;
	
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
			b00902List = b00902Service.retrieveAllB00902();
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
			b00902Service.insertB00902(b00902PO,this);
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
			b00902PO=b00902Service.retrieveB00902ByID(b00902PO.getMA001());
			if(b00902PO==null) {
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
			b00902Service.updateB00902(b00902PO,this);
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
			b00902Service.deleteB00902ByID(b00902PO.getMA001(),this);
		}catch(Exception e){
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doQuery()	{
		logger.info("----------------------doList22>>" + this.getClass().getName());
		try {
			b00902List = b00902Service.retrieveB00902ByPO(b00902PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================="+b00902List);
		return "query";
	}

	public IB00902Service getB00902Service() {
		return b00902Service;
	}

	public void setB00902Service(IB00902Service b00902Service) {
		this.b00902Service = b00902Service;
	}

	public void setB00902List(List<B00902PO> b00902List) {
		this.b00902List = b00902List;
	}

	public List<B00902PO> getB00902List() {
		return b00902List;
	}

	public void setB00902PO(B00902PO b00902PO) {
		this.b00902PO = b00902PO;
	}

	public B00902PO getB00902PO() {
		return b00902PO;
	}
}
