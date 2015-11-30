/**
 * 文件名：IK006Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.dao;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K006PO;
import com.hw.hwsafe.platform.dao.IBaseDao;



/**
 * 
 * 项目名称：hwsafe
 * 类名称：IK006Dao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午2:49:24
 * 修改人：
 * 修改时间：2012-6-8 下午2:49:24
 * 修改备注：
 * @version 
 * 
 */
public interface IK006Dao extends IBaseDao {
	/**
	 * 
	 * @Title: retrieveAllK006
	 * @Description: 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<K006PO>
	 * @throws
	 */
	public List<K006PO> retrieveAllK006() throws Exception;
	/**
	 * 
	 * @Title: retrieveK006ByID
	 * @Description: 
	 * 作者：刁海港
	 * @param @param k006ID
	 * @param @return
	 * @param @throws Exception
	 * @return K006PO
	 * @throws
	 */
	public K006PO retrieveK006ByID(String k006ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveK006ByPO
	 * @Description: 
	 * 作者：刁海港
	 * @param @param k006PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<K006PO>
	 * @throws
	 */
	public List<K006PO> retrieveK006ByPO(K006PO k006PO) throws Exception;
	/**
	 * 
	 * @Title: insertK006
	 * @Description: 
	 * 作者：刁海港
	 * @param @param k006PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertK006(K006PO k006PO) throws Exception;
	/**
	 * 
	 * @Title: updateK006
	 * @Description: 
	 * 作者：刁海港
	 * @param @param k006PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateK006(K006PO k006PO) throws Exception;
	/**
	 * 
	 * @Title: deleteK006ByID
	 * @Description: 
	 * 作者：刁海港
	 * @param @param k006ID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteK006ByID(String k006ID) throws Exception;
	
	/**
	 * 
	 * 函 数 名：countByMA002
	 * 功能描述：通过MA002查询实例数
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午4:15:28
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public Integer countByMA002 (String ma002) throws Exception;
	
	/**
	 * 
	 * 函 数 名：countByMA001AndMA002
	 * 功能描述：通过MA001和MA002查询实例数
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午4:18:42
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public Integer countByMA001AndMA002(K006PO po) throws Exception;

}
