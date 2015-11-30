/**
 * 文件名：IB00301Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00301PO;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB00301Service
 * 类描述：
 * 创建人：王勇
 * 创建时间：2012-11-26 15:20:24
 * 修改人：
 * 修改时间：2012-11-26 15:20:24
 * 修改备注：
 * @version 
 * 
 */
public interface IB00301Service extends IBaseService {
	/**
	 * 
	 * @Title: retrieveAllB00301
	 * @Description: TODO 
	 * 作者：王勇
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00301PO>
	 * @throws
	 */
	public List<B00301PO> retrieveAllB00301() throws Exception;
	
	/**
	 * 根据主键,返回一个对象
	 * @param b00301ID
	 * @return
	 * @throws Exception
	 */
	public B00301PO retrieveB00301ByID(String b00301ID) throws Exception;
	
	/**
	 * 插入
	 * @param b00301PO
	 * @param b00301Action
	 * @throws Exception
	 */
	public void insertB00301(B00301PO b00301PO) throws Exception;
	
	/**
	 * 更新
	 * @param b00301PO
	 * @param b00301Action
	 * @throws Exception
	 */
	public void updateB00301(B00301PO b00301PO) throws Exception;
	/**
	 * 删除人员培训信息
	 * @param id
	 * @throws Exception
	 */
	public UserMessageData delb00301(String id)throws Exception;
	/**
	 * 
	 * isExist(查看记录是否存在)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isExist(String id)throws Exception;
}
