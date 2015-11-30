/**
 * 文件名：IB103Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B103PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB103Dao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:03:06
 * 修改人：
 * 修改时间：2012-6-11 下午6:03:06
 * 修改备注：
 * @version 
 * 
 */
public interface IB103Dao extends IBaseDao {
	
	/**
	 * 
	 * @Title: retrieveB103ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b103ID
	 * @param @return
	 * @param @throws Exception
	 * @return B103PO
	 * @throws
	 */

	public B103PO retrieveB103ByID(String b103ID) throws Exception;
	
	/**
	 * 
	 * @Title: insertB103
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b103PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void insertB103(B103PO b103PO) throws Exception;
	
	/**
	 * 
	 * @Title: updateB103
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b103PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */

	public void updateB103(B103PO b103PO) throws Exception;
	
	/**
	 * 当添加一个安全生产管理人员时，修改b002表中的安全管理人员数量
	 * @throws Exception
	 */
	public void updateB002AqryNum(String zzjgid) throws Exception;
	
	/**
	 * 删除人员信息
	 * @param map
	 * @throws Exception
	 */
	public void delb103(Map map)throws Exception;
	/**
	 * 删除安全管理人员之后更新组织机构表安全管理人员数量
	 * @param map
	 * @throws Exception
	 */
	public void updateB002AqryNumtj(Map map)throws Exception;
	/**
	 * 根据人员id获取组织机构的上报状态
	 * @param ryid
	 * @return
	 * @throws Exception
	 */
	public int getB002Stat(String ryid)throws Exception;
	
}
