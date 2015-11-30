package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B009PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

public interface IB009Dao extends IBaseDao{
	
	
	/**
	 * 
	 * @Title: retrieveAllB009
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B009PO>
	 * @throws
	 */
	public List<B009PO> retrieveAllB009() throws Exception;
/**
 * 
 * @Title: retrieveB009ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b009ID
 * @param @return
 * @param @throws Exception
 * @return B009PO
 * @throws
 */
	public B009PO retrieveB009ByID(String b009ID) throws Exception;

/**
 * 
 * @Title: retrieveB009ByPO
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b009PO
 * @param @return
 * @param @throws Exception
 * @return List<B009PO>
 * @throws
 */
	public List<B009PO> retrieveB009ByPO(B009PO b009PO) throws Exception;
/**
 * 
 * @Title: insertB009
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b009PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void insertB009(B009PO b009PO) throws Exception;
/**
 * 
 * @Title: updateB009
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b009PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void updateB009(B009PO b009PO) throws Exception;
/**
 * 
 * @Title: deleteB009ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param userID
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void deleteB009ByID(String userID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB009BywxyID
	 * @Description: TODO 
	 * 作者：盛家龙
	 * @param @param wxyID
	 * @param @return
	 * @param @throws Exception
	 * @return B009PO
	 * @throws
	 */
	public B009PO retrieveB009BywxyID(String wxyID) throws Exception;
}
