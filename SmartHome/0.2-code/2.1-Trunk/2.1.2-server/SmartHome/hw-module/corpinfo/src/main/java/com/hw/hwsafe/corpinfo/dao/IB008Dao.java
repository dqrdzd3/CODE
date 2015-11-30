/**
 * 文件名：IB008Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B008PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB008Dao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-11 下午6:03:20
 * 修改人：
 * 修改时间：2012-6-11 下午6:03:20
 * 修改备注：
 * @version 
 * 
 */
public interface IB008Dao extends IBaseDao {
	/**
	 * 
	 * @Title: retrieveB008ByID
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b008ID
	 * @param @return
	 * @param @throws Exception
	 * @return B008PO
	 * @throws
	 */

	public B008PO retrieveB008ByID(String b008ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB008ByPO
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b008PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B008PO>
	 * @throws
	 */

	public List<B008PO> retrieveB008ByPO(B008PO b008PO) throws Exception;
	/**
	 * 
	 * @Title: insertB008
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b008PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void insertB008(B008PO b008PO) throws Exception;
	/**
	 * 
	 * @Title: updateB008
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b008PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB008(B008PO b008PO) throws Exception;
}
