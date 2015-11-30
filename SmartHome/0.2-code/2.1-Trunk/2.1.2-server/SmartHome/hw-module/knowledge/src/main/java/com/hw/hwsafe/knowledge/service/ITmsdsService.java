/**
 * 文件名：IRolePermissionService.java
 *
 * 版本信息：
 * 日期：2012-5-17
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service;


import com.hw.hwsafe.knowledge.pojo.TmsdsPO;


/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：IRolePermissionService
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-5-29 下午1:10:39
 * 修改人：李玉梅
 * 修改时间：2012-5-29 下午1:10:39
 * 修改备注：
 * @version 
 *
 */
public interface ITmsdsService {

	
	/**
	 * 
	 * retrieveTmsdsByID(根据Id查询）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public TmsdsPO retrieveTmsdsByID(String tmsdsID) throws Exception;
	
	/**
	 * 	
	 * insertTmsds(新增危化品知识库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertTmsds(TmsdsPO tmsdsPO) throws Exception;
	
	/**
	 * 更新危化品知识库	
	 * updateTmsds
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateTmsds(TmsdsPO tmsdsPO) throws Exception;
	

	/**
	 * 
	 * 函 数 名：isNameUniqueWhenAdd
	 * 功能描述：判断当添加时,名称是否唯一
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午5:03:01
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public boolean isNameUniqueWhenAdd(String name) throws Exception;

	/**
	 * 
	 * 函 数 名：isNameUniqueWhenUpdate
	 * 功能描述：判断当修改时,名称是否唯一
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午5:03:28
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public boolean isNameUniqueWhenUpdate(TmsdsPO po) throws Exception;
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
	/**
	 * 	李玉梅于2013年6月6日修改
	 * @return
	 * @throws Exception
	 */
	public TmsdsPO retrieveTmsdsByChname(String chName) throws Exception;
	
}
