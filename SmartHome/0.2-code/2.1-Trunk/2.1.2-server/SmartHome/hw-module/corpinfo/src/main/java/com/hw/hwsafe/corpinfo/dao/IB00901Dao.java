package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00901PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

public interface IB00901Dao extends IBaseDao{
	
	
	/**
	 * 
	 * @Title: retrieveAllB00901
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00901PO>
	 * @throws
	 */
	public List<B00901PO> retrieveAllB00901() throws Exception;
/**
 * 
 * @Title: retrieveB00901ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00901ID
 * @param @return
 * @param @throws Exception
 * @return B00901PO
 * @throws
 */
	public B00901PO retrieveB00901ByID(String b00901ID) throws Exception;

/**
 * 
 * @Title: retrieveB00901ByPO
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00901PO
 * @param @return
 * @param @throws Exception
 * @return List<B00901PO>
 * @throws
 */
	public List<B00901PO> retrieveB00901ByPO(B00901PO b00901PO) throws Exception;
/**
 * 
 * @Title: insertB00901
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00901PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void insertB00901(B00901PO b00901PO) throws Exception;
/**
 * 
 * @Title: updateB00901
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00901PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void updateB00901(B00901PO b00901PO) throws Exception;
/**
 * 
 * @Title: deleteB00901ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param userID
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void deleteB00901ByID(String userID) throws Exception;
}
