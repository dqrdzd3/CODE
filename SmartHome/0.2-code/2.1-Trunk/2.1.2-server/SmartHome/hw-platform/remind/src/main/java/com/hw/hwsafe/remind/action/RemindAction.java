/**
 * 文件名：RemindAction.java
 *
 * 版本信息：
 * 日期：2013-4-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 *
 */
package com.hw.hwsafe.remind.action;

import java.util.List;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.remind.outer.IRemindService;
import com.hw.hwsafe.remind.po.RemindPO;

/**
 * 
 * 项目名称：hw-remind
 * 类名称：RemindAction
 * 类描述：
 * 创建人：陈浙东
 * 创建时间：2013-4-11 下午3:49:08
 * 修改人：陈浙东
 * 修改时间：2013-4-11 下午3:49:08
 * 修改备注：
 * @version 
 * 
 */
public class RemindAction extends BaseAction {
	private IRemindService remindService;
	/**
	 * 
	 * 函 数 名：doList
	 * 功能描述：查询当前登录用户的消息提醒列表
	 * 返 回 值： 
	 * @return String
	 * 创建人：陈浙东
	 * 创建时间：2013-4-11 下午4:19:16
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public String doList(){
		String receive = SessionUtil.getUserId();
		List<RemindPO> remindList = remindService.getRemindList(receive);
		getRequest().setAttribute("remindList", remindList);
		return "doList";
	}
	
	public String doView(){
		//获取消息id
		String remindId = getRequest().getParameter("id");
		RemindPO po = remindService.getRemindInfo(remindId);
		remindService.deleteRemind(remindId);
		getRequest().setAttribute("po", po);
		return "doView";
	}
	
	
	
	public IRemindService getRemindService() {
		return remindService;
	}
	public void setRemindService(IRemindService remindService) {
		this.remindService = remindService;
	}
	
	
}
