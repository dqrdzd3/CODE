/**
 * 文件名：WelcomeAction.java
 *
 * 版本信息：1.0
 * 日期：2012-5-15
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.action;

import com.hw.hwsafe.platform.action.BaseAction;

/**
 * 
 * 项目名称：framework
 * 类名称：WelcomeAction
 * 类描述：欢迎界面
 * 创建人：盛家龙
 * 创建时间：2012-5-15 下午5:13:10
 * 修改人：盛家龙
 * 修改时间：2012-5-15 下午5:13:10
 * 修改备注：
 * @version 
 * 
 */
public class WelcomeAction extends BaseAction {

	public String doList() {
		logger.info("----------------------doList >>" + this.getClass().getName());
		return SUCCESS;
	}
	
	public String welcome() {
		return "welcome";		
	}
	
	public String friends() {
		return "friends";		
	}
	
	public String office() {
		return "office";		
	}

}
