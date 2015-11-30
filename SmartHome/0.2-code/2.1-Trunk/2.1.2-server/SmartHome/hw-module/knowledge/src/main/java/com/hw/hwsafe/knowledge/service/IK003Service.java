/**
 * 文件名：IK003Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K003PO;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IK003Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午3:09:04
 * 修改人：
 * 修改时间：2012-6-8 下午3:09:04
 * 修改备注：
 * @version 
 * 
 */
public interface IK003Service {
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
	 * @Title: retrieveK003yPO
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
	 * isExist(记录是否存在)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isExist(String id)throws Exception;

}
