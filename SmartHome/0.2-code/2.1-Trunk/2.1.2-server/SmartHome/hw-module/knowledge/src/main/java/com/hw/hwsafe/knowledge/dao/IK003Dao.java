/**
 * 文件名：IK003Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.dao;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K003PO;
import com.hw.hwsafe.platform.dao.IBaseDao;
/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：IK003Dao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-7-13 上午11:03:24
 * 修改人：李玉梅
 * 修改时间：2012-7-13 上午11:03:24
 * 修改备注：
 * @version 
 *
 */
public interface IK003Dao extends IBaseDao {
	/**
	 * 
	 * @Title: retrieveAllK003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<K003PO>
	 * @throws
	 */
	public List<K003PO> retrieveAllK003() throws Exception;
	/**
	 * 
	 * @Title: retrieveK003ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k003ID
	 * @param @return
	 * @param @throws Exception
	 * @return K003PO
	 * @throws
	 */
	public K003PO retrieveK003ByID(String k003ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveK003ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k003PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<K003PO>
	 * @throws
	 */
	public List<K003PO> retrieveK003ByPO(K003PO k003PO) throws Exception;
	/**
	 * 
	 * @Title: insertK003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k003PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertK003(K003PO k003PO) throws Exception;
	/**
	 * 
	 * @Title: updateK003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k003PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateK003(K003PO k003PO) throws Exception;
	/**
	 * 
	 * @Title: deleteK003ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k003ID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteK003ByID(String k003ID) throws Exception;

}
