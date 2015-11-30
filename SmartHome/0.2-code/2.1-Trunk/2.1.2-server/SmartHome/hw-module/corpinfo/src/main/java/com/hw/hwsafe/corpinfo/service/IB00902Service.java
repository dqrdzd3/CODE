package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.action.B00902Action;
import com.hw.hwsafe.corpinfo.pojo.B00902PO;
import com.hw.hwsafe.platform.service.IBaseService;

public interface IB00902Service extends IBaseService {
	/**
	 * 
	 * @Title: retrieveAllB00902
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00902PO>
	 * @throws
	 */
	
	public List<B00902PO> retrieveAllB00902() throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB00902ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00902ID
	 * @param @return
	 * @param @throws Exception
	 * @return B00902PO
	 * @throws
	 */
	public B00902PO retrieveB00902ByID(String b00902ID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB00902ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00902PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00902PO>
	 * @throws
	 */
	
	public List<B00902PO> retrieveB00902ByPO(B00902PO b00902PO) throws Exception;
	
	/**
	 * 
	 * @Title: insertB00902
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00902PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB00902(B00902PO b00902PO,B00902Action b00902Action) throws Exception;
	
	
	/**
	 * 
	 * @Title: updateB00902
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00902PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB00902(B00902PO b00902PO,B00902Action b00902Action) throws Exception;
	
	
	/**
	 * 
	 * @Title: deleteB00902ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteB00902ByID(String userID,B00902Action b00902Action) throws Exception;
	
	public List<B00902PO> retrieveB00902ByParentID(String id) throws Exception;
}

