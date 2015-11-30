/**
 * 文件名：ISysLoginLogService.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service;

import com.hw.hwsafe.platform.pojo.UserPO;

/**
 * 登录日志service接口
 * 
 * @author 马宁
 * @创建时间 2013-06-06
 */
public interface ISysLoginLogService extends IBaseService {

	/**
	 * 记录日志
	 * 
	 * @param type
	 *            - 日志类型
	 * @param ip
	 *            - ip
	 * @param userPo
	 *            - UserPO
	 * @throws Exception
	 * @author 马宁
	 * @create_time 2013-6-6 下午4:19:12
	 */
	void log(Integer type, String ip, UserPO userPo) throws Exception;

}
