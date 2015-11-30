/**
 * 文件名：IK006Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K006PO;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IK006Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午3:09:04
 * 修改人：
 * 修改时间：2012-6-8 下午3:09:04
 * 修改备注：
 * @version 
 * 
 */
public interface IK006Service {
	/**
	 * 
	 * @Title: retrieveK006ByID
	 * @Description: TODO 
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
	 * @Title: retrieveK006yPO
	 * @Description: TODO 
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
	 * @Description: TODO 
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
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k006PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateK006(K006PO k006PO) throws Exception;
	
	/**
	 * 
	 * 函 数 名：isMA002UniqueWhenAdd
	 * 功能描述：判断当添加时,MA002是否唯一
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午3:40:51
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public boolean isMA002UniqueWhenAdd(String ma002) throws Exception;
	
	/**
	 * 
	 * 函 数 名：isMA002UniqueWhenUpdate
	 * 功能描述：判断当修改时,MA002是否唯一
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午3:41:44
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public boolean isMA002UniqueWhenUpdate (K006PO po) throws Exception;
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
