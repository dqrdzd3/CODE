package com.hw.hwsafe.platform.dao;

import java.util.Map;

/**
 * 
 * 项目名称：framework 类名称：ICommonDao 类描述 通用dao类， 创建人：孟繁波 创建时间：2012-8-11 下午11:19:19
 * 修改人：孟繁波 修改时间：2012-8-11 下午11:19:19 修改备注：
 * 
 * @version
 * 
 */
public interface ICommonDao extends IBaseDao {

	/**
	 * delBatch 批量删除 Author: 孟繁波
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public int delBatch(Map map) throws Exception;
	
}
