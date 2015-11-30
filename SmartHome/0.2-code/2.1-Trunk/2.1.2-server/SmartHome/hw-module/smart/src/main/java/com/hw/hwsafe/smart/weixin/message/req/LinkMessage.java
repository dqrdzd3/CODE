package com.hw.hwsafe.smart.weixin.message.req;

/**
 * * t����Ϣ * *
 * 
 * @author liufeng *
 * @date 2013-05-19
 * */
public class LinkMessage extends BaseMessage {
	// ��Ϣ����
	private String Title;
	// ��Ϣ����
	private String Description;
	// ��Ϣt��
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}
