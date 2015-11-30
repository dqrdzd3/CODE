/**
 * 文件名：IB007Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B007PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB007Dao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-11 下午6:03:20
 * 修改人：
 * 修改时间：2012-6-11 下午6:03:20
 * 修改备注：
 * @version 
 * 
 */
public interface IB007Dao extends IBaseDao {

/**
 * 	
 * @Title: retrieveAllB007
 * @Description: TODO 
 * 作者：李玉梅
 * @param @return
 * @param @throws Exception
 * @return List<B007PO>
 * @throws
 */
	public List<B007PO> retrieveAllB007() throws Exception;
	/**
	 * 
	 * @Title: retrieveB007ByID
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b007ID
	 * @param @return
	 * @param @throws Exception
	 * @return B007PO
	 * @throws
	 */

	public B007PO retrieveB007ByID(String b007ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB007ByPO
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b007PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B007PO>
	 * @throws
	 */

	public List<B007PO> retrieveB007ByPO(B007PO b007PO) throws Exception;
	/**
	 * 
	 * @Title: insertB007
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b007PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void insertB007(B007PO b007PO) throws Exception;
	/**
	 * 
	 * @Title: updateB007
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b007PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB007(B007PO b007PO) throws Exception;
	/**
	 * 
	 * @Title: deleteB007ByID
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void deleteB007ByID(String b007ID) throws Exception;
}
