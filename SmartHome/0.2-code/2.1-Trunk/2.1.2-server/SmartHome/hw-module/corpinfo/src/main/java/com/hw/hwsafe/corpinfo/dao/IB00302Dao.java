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

import java.util.HashMap;
import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00302PO;
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
public interface IB00302Dao extends IBaseDao {
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
	public List<B00302PO> retrieveAllB00302() throws Exception;
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

	public B00302PO retrieveB00302ByID(String b00302ID) throws Exception;
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

	public List<B00302PO> retrieveB00302ByPO(B00302PO b00302PO) throws Exception;
	
	/**
	 * 
	 * @Title: insertB00302
	 * @Description: 
	 * 作者：wangyong
	 * @param @param b00302PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB00302(B00302PO b00302PO) throws Exception;
	
	/**
	 * 
	 * @Title: updateB00302
	 * @Description: TODO 
	 * 作者：wangyong
	 * @param @param b003PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB00302(B00302PO b00302PO) throws Exception;
	/**
	 * 
	 * @Title: deleteB00302ByB003ID
	 * @Description: 根据主表主键删除从表数据
	 * 作者：王勇
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteB00302ByB003ID(String userID) throws Exception;
	
	/**
	 * 批量删除人员证书信息
	 * @param map
	 * @throws Exception
	 */
	public int delb00302(HashMap<String, String[]> map) throws Exception;
	
	
}
