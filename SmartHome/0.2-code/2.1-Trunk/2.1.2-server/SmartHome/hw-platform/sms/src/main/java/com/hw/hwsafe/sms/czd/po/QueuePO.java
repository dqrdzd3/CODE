package com.hw.hwsafe.sms.czd.po;

/**
 * 项目名称：hw-sms
 * 类名称：QueuePO
 * 类描述：队列PO，用来和数据库交互
 * 创建人：陈浙东
 * 创建时间：2013-4-09
 *
 */
public class QueuePO {
	private String id;
	private String telphone;
	private String content;
	private int sort;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

}
