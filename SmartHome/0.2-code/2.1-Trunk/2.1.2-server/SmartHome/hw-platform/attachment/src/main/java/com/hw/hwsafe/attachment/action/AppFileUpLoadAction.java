
/**
 * @title FileUpLoadAction.java
 * @package com.hw.hwsafe.services.action
 * @author 杜群星
 * @create_time 2014-4-17 上午10:13:00
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2014</p>
 */
	
package com.hw.hwsafe.attachment.action;

import java.io.File;
import java.util.List;

import com.hw.hwsafe.platform.action.BaseAction;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class AppFileUpLoadAction extends BaseAction{
	
	/**
	 * @fields serialVersionUID
	 */
		
	private static final long serialVersionUID = 1L;
	//单文件上传
	protected File file;
	protected String fileContentType;
	protected String fileFileName;
	//多文件上传
	protected List<File> files;
	protected List<String> filesContentType;
	protected List<String> filesFileName;

	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public List<File> getFiles() {
		return files;
	}
	public void setFiles(List<File> files) {
		this.files = files;
	}
	public List<String> getFilesContentType() {
		return filesContentType;
	}
	public void setFilesContentType(List<String> filesContentType) {
		this.filesContentType = filesContentType;
	}
	public List<String> getFilesFileName() {
		return filesFileName;
	}
	public void setFilesFileName(List<String> filesFileName) {
		this.filesFileName = filesFileName;
	}
	
}
