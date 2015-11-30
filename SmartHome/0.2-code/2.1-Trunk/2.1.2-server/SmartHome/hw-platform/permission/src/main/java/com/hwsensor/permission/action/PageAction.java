/**
 * 文件名：PageAction.java
 *
 * 版本信息：1.0
 * 日期：2012-5-25
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.action;

import com.hw.hwsafe.platform.action.BaseAction;

/**
 * 
 * 项目名称：framework 类名称：PageAction 类描述： 创建人：盛家龙 创建时间：2012-5-25 下午4:41:02 修改人：盛家龙
 * 修改时间：2012-5-25 下午4:41:02 修改备注：
 * 
 * @version
 * 
 */
public class PageAction extends BaseAction {

	private String cpage = "1";

	private String total = "1";

	private String url;

	/**
	 * 
	 * @Title: list
	 * @Description: TODO Get data from server
	 * 作者：盛家龙
	 * @param @return
	 * @return String
	 * @throws
	 */
	public String list() {
		// Get data from server
		return SUCCESS;
	}

	public String getCpage() {
		return cpage;
	}

	public void setCpage(String cpage) {
		this.cpage = cpage;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
