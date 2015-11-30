/**
 * 文件名：IB003Dao.java
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

import com.hw.hwsafe.corpinfo.pojo.B003PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB003Dao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:03:06
 * 修改人：
 * 修改时间：2012-6-11 下午6:03:06
 * 修改备注：
 * @version 
 * 
 */
public interface IB003Dao extends IBaseDao {
	/**
	 * 
	 * @Title: retrieveAllB003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B003PO>
	 * @throws
	 */
	public List<B003PO> retrieveAllB003() throws Exception;
	/**
	 * 
	 * @Title: retrieveB003ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003ID
	 * @param @return
	 * @param @throws Exception
	 * @return B003PO
	 * @throws
	 */

	public B003PO retrieveB003ByID(String b003ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB003ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B003PO>
	 * @throws
	 */
	

	public List<B003PO> retrieveB003ByPO(B003PO b003PO) throws Exception;
	/**
	 * 
	 * @Title: insertB003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void insertB003(B003PO b003PO) throws Exception;
	/**
	 * 政府端查询企业特殊装备人员情况
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> retrieveByPage1(Map map) throws Exception;
	/**
	 * 
	 * @Title: updateB003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB003(B003PO b003PO) throws Exception;
	/**
	 * 
	 * @Title: deleteB003ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void deleteB003ByID(String userID) throws Exception;
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
	public int retrieveB001ByName(B003PO b003po) throws Exception;
	/**
	 * 
	 * @Title: upretrieveB002ByName
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b002PO
	 * @param @return
	 * @param @throws Exception
	 * @return int
	 * @throws
	 */
	 
	public int upretrieveB003ByName(B003PO b003PO) throws Exception;
	
	public List getInfoByname(B003PO b003PO)throws Exception;
	
	
}
