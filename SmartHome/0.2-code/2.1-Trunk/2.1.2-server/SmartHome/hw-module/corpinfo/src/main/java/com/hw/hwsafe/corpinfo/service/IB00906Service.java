package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.action.B00906Action;
import com.hw.hwsafe.corpinfo.pojo.B00906PO;
import com.hw.hwsafe.platform.service.IBaseService;

public interface IB00906Service extends IBaseService {
	/**
	 * 
	 * @Title: retrieveAllB00906
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00906PO>
	 * @throws
	 */
	
	public List<B00906PO> retrieveAllB00906() throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB00906ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00906ID
	 * @param @return
	 * @param @throws Exception
	 * @return B00906PO
	 * @throws
	 */
	public B00906PO retrieveB00906ByID(String b00906ID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB00906ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00906PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00906PO>
	 * @throws
	 */
	
	public List<B00906PO> retrieveB00906ByPO(B00906PO b00906PO) throws Exception;
	
	/**
	 * 
	 * @Title: insertB00906
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00906PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB00906(B00906PO b00906PO,B00906Action b00906Action) throws Exception;
	
	
	/**
	 * 
	 * @Title: updateB00906
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00906PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB00906(B00906PO b00906PO,B00906Action b00906Action) throws Exception;
	
	
	/**
	 * 
	 * @Title: deleteB00906ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteB00906ByID(String userID,B00906Action b00906Action) throws Exception;
	public List<B00906PO> retrieveB00906ByParentID(String id) throws Exception;
}

