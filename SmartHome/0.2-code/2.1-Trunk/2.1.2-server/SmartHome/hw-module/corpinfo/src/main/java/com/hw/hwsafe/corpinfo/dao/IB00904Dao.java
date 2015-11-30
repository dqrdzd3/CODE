package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00904PO;
import com.hw.hwsafe.platform.dao.IBaseDao;
public interface IB00904Dao extends IBaseDao{
	
	
	/**
	 * 
	 * @Title: retrieveAllB00904
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00904PO>
	 * @throws
	 */
	public List<B00904PO> retrieveAllB00904() throws Exception;
/**
 * 
 * @Title: retrieveB00904ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00904ID
 * @param @return
 * @param @throws Exception
 * @return B00904PO
 * @throws
 */
	public B00904PO retrieveB00904ByID(String b00904ID) throws Exception;

/**
 * 
 * @Title: retrieveB00904ByPO
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00904PO
 * @param @return
 * @param @throws Exception
 * @return List<B00904PO>
 * @throws
 */
	public List<B00904PO> retrieveB00904ByPO(B00904PO b00904PO) throws Exception;
/**
 * 
 * @Title: insertB00904
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00904PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void insertB00904(B00904PO b00904PO) throws Exception;
/**
 * 
 * @Title: updateB00904
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00904PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void updateB00904(B00904PO b00904PO) throws Exception;
/**
 * 
 * @Title: deleteB00904ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param userID
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void deleteB00904ByID(String userID) throws Exception;
	public List<B00904PO> retrieveB00904ByParentID(String id) throws Exception;
}

