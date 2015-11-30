/**
 * 文件名：IBaseDao.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.dao;

import java.util.List;
import java.util.Map;

/**
 * 
 * 项目名称：framework
 * 类名称：IBaseDao
 * 类描述：BaseDao接口
 * 创建人：盛家龙
 * 创建时间：2012-5-8 下午7:57:37
 * 修改人：孟繁波
 * 修改时间：2012-6-30 下午4:00:00
 * 修改备注：
 * @version 
 * 
 */
public interface IBaseDao {

	/**
	 * retrieveByPage(这里用一句话描述这个方法的作用)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	List<Map<String, Object>> retrieveByPage(Map map) throws Exception;
	
	/**
	 * 
	 * 分页查询
	 * @param map
	 * @return
	 * @throws Exception          
	 * @author 陈浙东
	 * @create_time 2013-5-21 上午11:15:40
	 */
	List listByPage(Map map) throws Exception;
	
	String getTotal(Map map) throws Exception;
}
