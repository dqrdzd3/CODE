/**
 * 文件名：IB002Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B002PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB002Dao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:02:52
 * 修改人：
 * 修改时间：2012-6-11 下午6:02:52
 * 修改备注：
 * @version 
 * 
 */
public interface IB002Dao extends IBaseDao {
	/**
	 * 
	 * @Title: retrieveAllB002
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B002PO>
	 * @throws
	 */
	public List<B002PO> retrieveAllB002() throws Exception;
	
	
	public List<B002PO> retrieveB002ByCorp(Map map) throws Exception;
/**
 * 
 * @Title: retrieveB002ByID
 * @Description: TODO 
 * 作者：刁海港
 * @param @param b002ID
 * @param @return
 * @param @throws Exception
 * @return B002PO
 * @throws
 */
	public B002PO retrieveB002ByID(String b002ID) throws Exception;

	/**
	 * 
	 * @Title: retrieveB002ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B002PO>
	 * @throws
	 */
	public List<B002PO> retrieveB002ByPO(B002PO b002PO) throws Exception;
	/**
	 * 
	 * @Title: insertB002
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void insertB002(B002PO b002PO) throws Exception;
	/**
	 * 
	 * @Title: updateB002
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB002(B002PO b002PO) throws Exception;
	
	/**
	 * 
	 * @Title: updateB002
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB002SBSJ(HashMap<String, Object> map) throws Exception;
	
	public void updateCorpSBSJ(String  corpid) throws Exception;
	public void updateCorpSBSJ2(Map map) throws Exception;
	
	/**
	 * 
	 * @Title: deleteB002ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void deleteB002ByID(String userID) throws Exception;
	/**
	 * 
		  * 
		   * 
		   * 函 数 名： retrieveB002ByName
		   * 功能描述：
		   * 适用条件：
		   * 执行流程：
		   * 使用方法：
		   * 创建人：刁海港
		   * 创建时间：2012-7-24 上午10:52:45
		   * 修改人：
		   * 修改时间：2012-7-24 上午10:52:45
		   * 修改原因描述：
	 */
	public int retrieveB002ByName(String name) throws Exception;
	
	public int upretrieveB002ByName(B002PO b002PO) throws Exception;
}