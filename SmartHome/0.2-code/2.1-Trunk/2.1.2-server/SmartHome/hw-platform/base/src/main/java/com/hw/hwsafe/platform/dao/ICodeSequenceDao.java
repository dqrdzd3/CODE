/**
 * 文件名：ICodeSequenceDao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.dao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：ICodeSequenceDao
 * 类描述：
 * 创建人：马宁
 * 创建时间：2012-11-12 下午2:41:49
 * 修改人：马宁
 * 修改时间：2012-11-12 下午2:41:49
 * 修改备注：
 * @version 
 *
 */
public interface ICodeSequenceDao {

	/**
	 * 
	 * 函 数 名：retrieveSequenceByCode
	 * 功能描述：通过代码查询序列号
	 * 创建人：马宁
	 * 创建时间：2012-11-12 下午3:21:52
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	Integer retrieveSequenceByCode(String code) throws Exception;

	/**
	 * 
	 * 函 数 名：addNewSequence
	 * 功能描述：添加新的序列号
	 * 创建人：马宁
	 * 创建时间：2012-11-12 下午3:22:32
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	void addNewSequence(String code);

	/**
	 * 
	 * 函 数 名：increaseSequence
	 * 功能描述：增加序列号
	 * @param code 代码
	 * 创建人：马宁
	 * 创建时间：2012-11-12 下午4:09:56
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	void increaseSequence(String code);

}
