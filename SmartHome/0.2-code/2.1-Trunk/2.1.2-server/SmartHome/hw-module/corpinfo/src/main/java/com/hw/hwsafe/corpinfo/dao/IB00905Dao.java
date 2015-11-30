package com.hw.hwsafe.corpinfo.dao;
import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00905PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

public interface IB00905Dao extends IBaseDao{
	
	
	/**
	 * 
	 * @Title: retrieveAllB00905
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00905PO>
	 * @throws
	 */
	public List<B00905PO> retrieveAllB00905() throws Exception;
/**
 * 
 * @Title: retrieveB00905ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00905ID
 * @param @return
 * @param @throws Exception
 * @return B00905PO
 * @throws
 */
	public B00905PO retrieveB00905ByID(String b00905ID) throws Exception;

/**
 * 
 * @Title: retrieveB00905ByPO
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00905PO
 * @param @return
 * @param @throws Exception
 * @return List<B00905PO>
 * @throws
 */
	public List<B00905PO> retrieveB00905ByPO(B00905PO b00905PO) throws Exception;
/**
 * 
 * @Title: insertB00905
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00905PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void insertB00905(B00905PO b00905PO) throws Exception;
/**
 * 
 * @Title: updateB00905
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b00905PO
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void updateB00905(B00905PO b00905PO) throws Exception;
/**
 * 
 * @Title: deleteB00905ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param userID
 * @param @throws Exception
 * @return void
 * @throws
 */
	public void deleteB00905ByID(String userID) throws Exception;
	public List<B00905PO> retrieveB00905ByParentID(String id) throws Exception;
}

