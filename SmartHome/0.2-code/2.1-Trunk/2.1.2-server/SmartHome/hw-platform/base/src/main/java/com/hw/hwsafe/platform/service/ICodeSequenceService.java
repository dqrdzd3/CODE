/**
 * 文件名：ICodeSequenceService.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：ICodeSequenceService
 * 类描述：ICodeSequenceService接口
 * 创建人：马宁
 * 创建时间：2012-11-12 下午2:20:09
 * 修改人：马宁
 * 修改时间：2012-11-12 下午2:20:09
 * 修改备注：
 * @version 
 *
 */
public interface ICodeSequenceService {
	
	/**
	 * 
	 * 函 数 名：retrieveSequence
	 * 功能描述：获取序列号
	 * @param code 5位预案类别编码+5位适用领域编码+9位组织机构代码
	 * @param digits 序列号位数
	 * @return 序列号
	 * 必须用与输入参数的@param相同的描述信息; 
	 * 必要的时候注明特殊条件写的返回值) 
	 * 异    常： (按照异常名字的字母顺序)
	 * 创建人：马宁
	 * 创建时间：2012-11-12 下午2:37:37
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	String retrieveSequence(String code, int digits) throws Exception;
	
}
