/**
 * 文件名：IB012Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-7-2
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B012PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB012Dao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-7-2 上午10:51:00
 * 修改人：
 * 修改时间：2012-7-2 上午10:51:00
 * 修改备注：
 * @version 
 * 
 */
public interface IB012Dao extends IBaseDao {
	
	public List<B012PO> retrieveAllB012() throws Exception;

	public B012PO retrieveB012ByID(String b012ID) throws Exception;

	public List<B012PO> retrieveB012ByPO(B012PO b012PO) throws Exception;

	public void insertB012(B012PO b012PO) throws Exception;

	public void updateB012(B012PO b012PO) throws Exception;
	/**
	 * 更新标注状态
	 * @param b012PO
	 * @throws Exception
	 */
	public void updateB012XY(B012PO b012PO) throws Exception;
	public void deleteB012ByID(String userID) throws Exception;
	
	public int retrieveB012ByName(B012PO b012PO) throws Exception;
 
	public int upretrieveB012ByName(B012PO b012PO) throws Exception;
}
