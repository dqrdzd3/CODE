/**
 * 文件名：IB012Service.java
 *
 * 版本信息：1.0
 * 日期：2012-7-2
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B012PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB012Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-7-2 上午11:02:06
 * 修改人：
 * 修改时间：2012-7-2 上午11:02:06
 * 修改备注：
 * @version 
 * 
 */
public interface IB012Service extends IBaseService {

   /**
    * 
    * retrieveB012ByID(根据主键查询记录)
    * @param   name
    * @param  @return    设定文件
    * @return String    DOM对象
    * @Exception 异常对象
    * @since  CodingExample　Ver(编码范例查看) 1.1
    */
	public B012PO retrieveB012ByID(String b012ID) throws Exception;
	/**
	 * retrieveB012ByPO(根据条件查询记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<B012PO> retrieveB012ByPO(B012PO b012PO) throws Exception;
		
	/**
	 * 	
	 * insertB012(添加记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertB012(B012PO b012PO) throws Exception;
			
	/**
	 * 		
	 * updateB012(更新记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateB012(B012PO b012PO) throws Exception;
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
