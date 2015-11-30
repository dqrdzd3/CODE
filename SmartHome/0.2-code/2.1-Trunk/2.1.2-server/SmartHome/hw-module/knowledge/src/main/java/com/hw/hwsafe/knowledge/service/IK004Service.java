/**
 * 文件名：IK004Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K004PO;
import com.hw.hwsafe.platform.message.UserMessageData;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IK004Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午3:09:04
 * 修改人：
 * 修改时间：2012-6-8 下午3:09:04
 * 修改备注：
 * @version 
 * 
 */
public interface IK004Service {
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
	 * @Title: retrieveK004yPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param k004PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<K004PO>
	 * @throws
	 */
	public List<K004PO> retrieveK004ByPO(K004PO k004PO) throws Exception;
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
	public UserMessageData insertK004(K004PO k004PO) throws Exception;
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
	public UserMessageData updateK004(K004PO k004PO) throws Exception;
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
