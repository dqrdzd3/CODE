/**
 * 文件名：IK004Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.dao;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K004PO;
import com.hw.hwsafe.platform.dao.IBaseDao;
/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：IK004Dao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-7-13 上午11:03:04
 * 修改人：李玉梅
 * 修改时间：2012-7-13 上午11:03:04
 * 修改备注：
 * @version 
 *
 */
public interface IK004Dao extends IBaseDao {
	/**
	 * 
	 * @Title: retrieveAllK004
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<K004PO>
	 * @throws
	 */
	public List<K004PO> retrieveAllK004() throws Exception;
	/**
	 * 
	 * @Title: retrieveK004ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k004ID
	 * @param @return
	 * @param @throws Exception
	 * @return K004PO
	 * @throws
	 */
	public K004PO retrieveK004ByID(String k004ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveK004ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k004PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<K004PO>
	 * @throws
	 */
	public List<K004PO> retrieveK004ByPO(K004PO k004PO) throws Exception;
	public List<K004PO> retrieveK004ByNameAndCode(K004PO k004PO) throws Exception;
	/**
	 * 
	 * @Title: insertK004
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k004PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertK004(K004PO k004PO) throws Exception;
	/**
	 * 
	 * @Title: updateK004
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k004PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateK004(K004PO k004PO) throws Exception;
	/**
	 * 
	 * @Title: deleteK004ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k004ID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteK004ByID(String k004ID) throws Exception;

}
