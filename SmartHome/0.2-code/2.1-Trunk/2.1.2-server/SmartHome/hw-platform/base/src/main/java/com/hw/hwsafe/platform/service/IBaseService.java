/**
 * 文件名：IBaseService.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.util.Page;

/**
 * 
 * 项目名称：framework
 * 类名称：IBaseService
 * 类描述：BaseService接口
 * 创建人：盛家龙
 * 创建时间：2012-5-8 下午8:01:03
 * 修改人：孟繁波
 * 修改时间：2012-6-30 下午12:01:03
 * 修改备注：
 * @version 
 * 
 */
public interface IBaseService {
	
	/**
	 * retrieveDepartments(根据条件查询分页数据)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public List<Map<String,Object>> retrieveByPage(Map map) throws Exception;
	
	public int delBatch(Map map) throws Exception;
	
	public Page listByPage(Page page, Map map) throws Exception;
}
