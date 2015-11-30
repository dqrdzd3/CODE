package com.hw.hwsafe.corpinfo.action;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00905PO;
import com.hw.hwsafe.corpinfo.service.IB00905Service;
import com.hw.hwsafe.platform.action.BaseAction;

public class B00905Action extends BaseAction {
	  
	private B00905PO b00905PO;
	
	@Autowired
	private IB00905Service b00905Service;
	
	private List<B00905PO> b00905List;

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
			b00905List = b00905Service.retrieveAllB00905();
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
			b00905Service.insertB00905(b00905PO,this);
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
			b00905PO=b00905Service.retrieveB00905ByID(b00905PO.getMA001());
			if(b00905PO==null) {
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
			b00905Service.updateB00905(b00905PO,this);
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
			b00905Service.deleteB00905ByID(b00905PO.getMA001(),this);
		}catch(Exception e){
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doQuery()	{
		logger.info("----------------------doList22>>" + this.getClass().getName());
		try {
			b00905List = b00905Service.retrieveB00905ByPO(b00905PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================="+b00905List);
		return "query";
	}

	public IB00905Service getB00905Service() {
		return b00905Service;
	}

	public void setB00905Service(IB00905Service b00905Service) {
		this.b00905Service = b00905Service;
	}

	public void setB00905List(List<B00905PO> b00905List) {
		this.b00905List = b00905List;
	}

	public List<B00905PO> getB00905List() {
		return b00905List;
	}

	public void setB00905PO(B00905PO b00905PO) {
		this.b00905PO = b00905PO;
	}

	public B00905PO getB00905PO() {
		return b00905PO;
	}
}
