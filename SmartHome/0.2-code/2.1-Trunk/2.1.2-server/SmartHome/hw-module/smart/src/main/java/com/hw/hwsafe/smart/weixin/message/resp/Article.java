package com.hw.hwsafe.smart.weixin.message.resp;
/**
 * ͼ��model
 * 
 * @author liufeng
 * @date 2013-05-19
 */
public class Article {
	// ͼ����Ϣ���
	private String Title;
	// ͼ����Ϣ����
	private String Description;
	// ͼƬt�ӣ�֧��JPG��PNG��ʽ���Ϻõ�Ч��Ϊ��ͼ640*320��Сͼ80*80������ͼƬt�ӵ�������Ҫ�뿪������д�Ļ������е�Urlһ��
	private String PicUrl;
	// ���ͼ����Ϣ��תt��
	private String Url;

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return null == Description ? "" : Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicUrl() {
		return null == PicUrl ? "" : PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getUrl() {
		return null == Url ? "" : Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

}