package com.hw.hwsafe.javamail.po;

/**
 * 项目名称：hw-javamail
 * 类名称：AttachementPO
 * 类描述：外部邮件附件类PO
 * 创建人：陈浙东
 * 创建时间：2013-4-10
 *
 */
public class AttachementPO {
	private String name;
	private String path;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public AttachementPO(String name, String path) {
		this.name = name;
		this.path = path;
	}
	
	public AttachementPO() {

	}
}
