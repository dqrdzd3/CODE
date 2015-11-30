/**
 * 文件名：UploadFiles.java
 *
 * 版本信息：
 * 日期：2012-6-7
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.pojo;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：UploadFiles
 * 类描述：
 * 创建人：张成
 * 创建时间：2012-6-7 上午10:42:48
 * 修改人：张成
 * 修改时间：2012-6-7 上午10:42:48
 * 修改备注：
 * @version 
 * 
 */
public class UploadFiles
{
	private String uploadFileName;//上传的文件名称
	private String uploadContentType;//类型
	private String uploadRealName;//保存的文件真实名称，UUID

	public String getUploadFileName()
	{
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName)
	{
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType()
	{
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType)
	{
		this.uploadContentType = uploadContentType;
	}
	public String getUploadRealName()
	{
		return uploadRealName;
	}
	public void setUploadRealName(String uploadRealName)
	{
		this.uploadRealName = uploadRealName;
	}
	
	
}

