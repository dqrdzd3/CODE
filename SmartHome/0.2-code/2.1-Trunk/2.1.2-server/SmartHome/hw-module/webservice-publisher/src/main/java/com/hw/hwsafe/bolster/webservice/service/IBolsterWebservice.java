/**
 * 文件名：IBolsterWebservice.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.bolster.webservice.service;

/**
 * 支撑平台webservice接口
 * 
 * @author 马宁
 *
 */
public interface IBolsterWebservice {

	/**
	 * 是否允许用户登录子系统
	 * 
	 * @param loginName - 登录名
	 * @param subsystemCode - 子系统编码
	 * 
	 * @return String - true/false
	 */
	String isAllowable(String loginName, String subsystemCode) throws Exception;
	
	/**
	 * 用户是否已在子系统激活
	 * 
	 * @param loginName - 登录名
	 * @param subsystemCode - 子系统编码
	 * 
	 * @return String - true/false
	 */
	String isActivable(String loginName, String subsystemCode) throws Exception;
	
	/**
	 * 更新用户在子系统的激活状态
	 * 
	 * @param loginName - 登录名
	 * @param subsystemCode - 子系统编码
	 * @param activeFlag - 激活状态(true/false)
	 */
	void updateActiveFlag(String loginName, String subsystemCode, String activeFlag) throws Exception;
	
	/**
	 * 通过登录名获取用户的基础信息
	 * 
	 * @param loginName - 登录名
	 * 
	 * @return String - JSON字符串, 字段:loginName登录名, username真实姓名, telNum电话, email邮箱
	 */
	String getUserInfo(String loginName) throws Exception;
}
