package com.hw.hwsafe.corpinfo.dao;
import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00903PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

public interface IB00903Dao extends IBaseDao{
	
	
	/**
	 * 
	 * @Title: retrieveAllB00903
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00903PO>
	 * @throws
	 */
	public List<B00903PO> retrieveAllB00903() throws Exception;
/**
 * 
 * @Title: retrieveB00903ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00903ID
 * @param @return
 * @param @throws Exception
 * @return B00903PO
 * @throws
 */
	public B00903PO retrieveB00903ByID(String b00903ID) throws Exception;

/**
 * 
 * @Title: retrieveB00903ByPO
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00903PO
 * @param @return
 * @param @throws Exception
 * @return List<B00903PO>
 * @throws
 */
	public List<B00903PO> retrieveB00903ByPO(B00903PO b00903PO) throws Exception;
/**
 * 
 * @Title: insertB00903
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00903PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void insertB00903(B00903PO b00903PO) throws Exception;
/**
 * 
 * @Title: updateB00903
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00903PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void updateB00903(B00903PO b00903PO) throws Exception;
/**
 * 
 * @Title: deleteB00903ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param userID
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void deleteB00903ByID(String userID) throws Exception;
	public List<B00903PO> retrieveB00903ByParentID(String id) throws Exception;
}

