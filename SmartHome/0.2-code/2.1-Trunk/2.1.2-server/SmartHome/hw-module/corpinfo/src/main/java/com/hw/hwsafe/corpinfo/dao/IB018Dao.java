package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B018PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

public interface IB018Dao extends IBaseDao {
	/**
	 * 
	 * @Title: retrieveAllB018
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B018PO>
	 * @throws
	 */
	
	public List<B018PO> retrieveAllB018() throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB018ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018ID
	 * @param @return
	 * @param @throws Exception
	 * @return B018PO
	 * @throws
	 */
	
	public B018PO retrieveB018ByID(String b018ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB018ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B018PO>
	 * @throws
	 */
	
	public List<B018PO> retrieveB018ByPO(B018PO b018PO) throws Exception;
	/**
	 * 
	 * @Title: insertB018
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	
	public void insertB018(B018PO b018PO) throws Exception;
	/**
	 * 
	 * @Title: updateB018
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b018PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	
	public void updateB018(B018PO b018PO) throws Exception;
	/**
	 * 
	 * @Title: deleteB018ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	
	public void deleteB018ByID(String userID) throws Exception;

}
