/**
 * 文件名：IB005Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B005PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB005Dao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:03:36
 * 修改人：
 * 修改时间：2012-6-11 下午6:03:36
 * 修改备注：
 * @version 
 * 
 */
public interface IB005Dao extends IBaseDao {

	/**
	 * 
	 * @Title: retrieveAllB005
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B005PO>
	 * @throws
	 */
	public List<B005PO> retrieveAllB005() throws Exception;
	/**
	 * 
	 * @Title: retrieveB005ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005ID
	 * @param @return
	 * @param @throws Exception
	 * @return B005PO
	 * @throws
	 */

	public B005PO retrieveB005ByID(String b005ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB005ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B005PO>
	 * @throws
	 */

	public List<B005PO> retrieveB005ByPO(B005PO b005PO) throws Exception;
	/**
	 * 
	 * @Title: insertB005
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void insertB005(B005PO b005PO) throws Exception;
	/**
	 * 
	 * @Title: updateB005
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB005(B005PO b005PO) throws Exception;
	
	/**
	 * 
	 * @Title: updateB005
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	
	public void updateB005SBSJ(Map<String, Object> map) throws Exception;
	
	
	public void updateCorpSBSJ(String corpId) throws Exception;
	public void updateCorpSBSJ2(Map map) throws Exception;
	/**
	 * 
	 * @Title: deleteB005ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void deleteB005ByID(String userID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB001ByName
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @throws
	 */
    public int	retrieveB005ByName(B005PO b005PO) throws Exception;
   /**
    * 
    * @Title: upretrieveB005ByName
    * @Description: TODO 
    * 作者：刁海港
    * @param @param b005PO
    * @param @return
    * @param @throws Exception
    * @return int
    * @throws
    */
    public int  upretrieveB005ByName(B005PO b005PO) throws Exception;
    /**
     * 根据企业ID查询企业的执照信息
     */
    public List<B005PO> retrieveB005BycorpID(String corpid)throws Exception;
    public List<B005PO> retrieveB005ByCorp(Map map)throws Exception;
}
