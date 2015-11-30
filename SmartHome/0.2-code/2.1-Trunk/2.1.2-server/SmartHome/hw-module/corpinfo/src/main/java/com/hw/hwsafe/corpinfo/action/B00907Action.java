package com.hw.hwsafe.corpinfo.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.pojo.B00907PO;
import com.hw.hwsafe.corpinfo.service.IB00907Service;
import com.hw.hwsafe.platform.action.BaseAction;

public class B00907Action extends BaseAction {
	  
	private B00907PO b00907PO;
	
	@Autowired
	private IB00907Service b00907Service;
	
	private List<B00907PO> b00907List;
	
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
			b00907List = b00907Service.retrieveAllB00907();
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
			b00907Service.insertB00907(b00907PO,this);
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
			b00907PO=b00907Service.retrieveB00907ByID(b00907PO.getMA001());
			if(b00907PO==null) {
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
			b00907Service.updateB00907(b00907PO,this);
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
			b00907Service.deleteB00907ByID(b00907PO.getMA001(),this);
		}catch(Exception e){
			e.printStackTrace();
			setDelFailedMsg();
		}
		return JSON_MSG;
	}
	
	public String doQuery()	{
		logger.info("----------------------doList22>>" + this.getClass().getName());
		try {
			b00907List = b00907Service.retrieveB00907ByPO(b00907PO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("================="+b00907List);
		return "query";
	}

	public IB00907Service getB00907Service() {
		return b00907Service;
	}

	public void setB00907Service(IB00907Service b00907Service) {
		this.b00907Service = b00907Service;
	}

	public void setB00907List(List<B00907PO> b00907List) {
		this.b00907List = b00907List;
	}

	public List<B00907PO> getB00907List() {
		return b00907List;
	}

	public void setB00907PO(B00907PO b00907PO) {
		this.b00907PO = b00907PO;
	}

	public B00907PO getB00907PO() {
		return b00907PO;
	}
}
