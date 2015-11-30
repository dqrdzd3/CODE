package com.hw.hwsafe.corpinfo.action;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00904PO;
import com.hw.hwsafe.corpinfo.service.IB00904Service;
import com.hw.hwsafe.platform.action.BaseAction;

public class B00904Action extends BaseAction {
	  
	private B00904PO b00904PO;
	
	@Autowired
	private IB00904Service b00904Service;
	
	private List<B00904PO> b00904List;

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
			b00904List = b00904Service.retrieveAllB00904();
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
			b00904Service.insertB00904(b00904PO,this);
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
			b00904PO=b00904Service.retrieveB00904ByID(b00904PO.getMA001());
			if(b00904PO==null) {
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
			b00904Service.updateB00904(b00904PO,this);
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
			b00904Service.deleteB00904ByID(b00904PO.getMA001(),this);
		}catch(Exception e){
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doQuery()	{
		logger.info("----------------------doList22>>" + this.getClass().getName());
		try {
			b00904List = b00904Service.retrieveB00904ByPO(b00904PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================="+b00904List);
		return "query";
	}

	public IB00904Service getB00904Service() {
		return b00904Service;
	}

	public void setB00904Service(IB00904Service b00904Service) {
		this.b00904Service = b00904Service;
	}

	public void setB00904List(List<B00904PO> b00904List) {
		this.b00904List = b00904List;
	}

	public List<B00904PO> getB00904List() {
		return b00904List;
	}

	public void setB00904PO(B00904PO b00904PO) {
		this.b00904PO = b00904PO;
	}

	public B00904PO getB00904PO() {
		return b00904PO;
	}
}
