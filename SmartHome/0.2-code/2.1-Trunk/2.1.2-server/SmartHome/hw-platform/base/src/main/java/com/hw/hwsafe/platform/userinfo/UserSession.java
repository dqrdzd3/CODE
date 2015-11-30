/**
 * 文件名：UserSession.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.userinfo;

import java.io.Serializable;

import com.hw.hwsafe.platform.pojo.UserPO;

/**
 * 
 * 项目名称：framework
 * 类名称：UserSession
 * 类描述：用户信息session
 * 创建人：盛家龙
 * 创建时间：2012-5-22 下午6:18:33
 * 修改人：盛家龙
 * 修改时间：2012-5-22 下午6:18:33
 * 修改备注：
 * @version 
 * 
 */
public class UserSession implements Serializable{

	private static final long serialVersionUID = -4181833932340222929L;
	
	private UserPO userPO;

	public UserPO getUserPO() {
		return userPO;
	}

	public void setUserPO(UserPO userPO) {
		this.userPO = userPO;
	}

}
