/**
 * 文件名：IB006Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B006PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB006Dao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-11 下午6:03:20
 * 修改人：
 * 修改时间：2012-6-11 下午6:03:20
 * 修改备注：
 * @version 
 * 
 */
public interface IB006Dao extends IBaseDao {

/**
 * 	
 * @Title: retrieveAllB006
 * @Description: TODO 
 * 作者：李玉梅
 * @param @return
 * @param @throws Exception
 * @return List<B006PO>
 * @throws
 */
	public List<B006PO> retrieveAllB006() throws Exception;
	/**
	 * 
	 * @Title: retrieveB006ByID
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b006ID
	 * @param @return
	 * @param @throws Exception
	 * @return B006PO
	 * @throws
	 */

	public B006PO retrieveB006ByID(String b006ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB006ByPO
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b006PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B006PO>
	 * @throws
	 */

	public List<B006PO> retrieveB006ByPO(B006PO b006PO) throws Exception;
	/**
	 * 
	 * @Title: insertB006
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b006PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void insertB006(B006PO b006PO) throws Exception;
	/**
	 * 
	 * @Title: updateB006
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b006PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB006(B006PO b006PO) throws Exception;
	/**
	 * 更新标注状态
	 * @param b006PO
	 * @throws Exception
	 */
	public void updateB006XY(B006PO b006PO) throws Exception;
	/**
	 * 
	 * @Title: deleteB006ByID
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void deleteB006ByID(String b006ID) throws Exception;
	
	/**
	 * 通过企业id查询周边环境  wy
	 * @param ma002
	 * @return
	 * @throws Exception
	 */
	public List getListByMa002(String ma002) throws Exception ;
}
