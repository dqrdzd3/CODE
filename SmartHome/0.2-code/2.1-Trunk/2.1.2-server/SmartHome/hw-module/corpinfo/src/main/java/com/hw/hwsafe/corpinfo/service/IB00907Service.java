package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.action.B00907Action;
import com.hw.hwsafe.corpinfo.pojo.B00907PO;
import com.hw.hwsafe.platform.service.IBaseService;

public interface IB00907Service extends IBaseService {
	/**
	 * 
	 * @Title: retrieveAllB00907
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00907PO>
	 * @throws
	 */
	
	public List<B00907PO> retrieveAllB00907() throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB00907ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00907ID
	 * @param @return
	 * @param @throws Exception
	 * @return B00907PO
	 * @throws
	 */
	public B00907PO retrieveB00907ByID(String b00907ID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB00907ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00907PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00907PO>
	 * @throws
	 */
	
	public List<B00907PO> retrieveB00907ByPO(B00907PO b00907PO) throws Exception;
	
	/**
	 * 
	 * @Title: insertB00907
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00907PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB00907(B00907PO b00907PO,B00907Action b00907Action) throws Exception;
	
	
	/**
	 * 
	 * @Title: updateB00907
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b00907PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB00907(B00907PO b00907PO,B00907Action b00907Action) throws Exception;
	
	
	/**
	 * 
	 * @Title: deleteB00907ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteB00907ByID(String userID,B00907Action b00907Action) throws Exception;
	public List<B00907PO> retrieveB00907ByParentID(String id) throws Exception;
}
