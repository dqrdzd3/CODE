/**
 * 文件名：MultiFileUpload.java
 *
 * 版本信息：1.0
 * 日期：2012-7-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.attachment.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.utils.FileNameUtil;

/**
 * 
 * 项目名称：hwsafe 
 * 类名称：FileUpload 
 * 类描述： 
 * 创建人：付强 
 * 创建时间：2012-7-12
 * 
 * @version
 * 
 */
public class FileUpload {

	private static Logger logger = Logger.getLogger(FileUpload.class);
	
	// 文件上传缓冲区大小
	private static final int BUFFER_SIZE = 16 * 1024;

	private static String DATE_FORMAT_01 = "yyyyMMdd";


	/**
	 * 判断文件及目录是否存在，若不存在则创建文件及目录
	 * 
	 * @param filepath
	 * @return
	 * @throws Exception
	 */
	public static void checkExist(String filepath) throws Exception {
		File file = new File(filepath);
		if (!file.exists()) {// 判断文件是否存在
			File file2 = new File(file.getParent());
			file2.mkdirs(); // 创建文件夹
			if (!file.isDirectory()) {
				file.createNewFile();// 创建文件
			}
		}
	}

	/**
	 * @author 付强
	 * @Description 文件拷贝
	 * @param src
	 *            源文件
	 * @param dst
	 *            目标文件
	 */
	public static void copy(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					BUFFER_SIZE);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description 单个文件上传
	 * @author 付强
	 * @param file
	 *            文件对象
	 * @param fileName
	 *            文件名
	 * @param ContentType
	 *            类型
	 * @return 上传文件存放路径
	 * @throws Exception
	 *             异常
	 */
	public static String uploadFiles(File file, String fileName,
			String ContentType, HttpServletRequest request) throws Exception {
		
		fileName = FileNameUtil.convertSuffixToLowerCase(fileName);
		
		// 如果文件存放路径未指定，在日志中发出警告
		if (null == ConfConstants.FILE_REPOSITORY_PATH || "".equals(ConfConstants.FILE_REPOSITORY_PATH)) {
			logger.warn(AttachmentConstants.FILE_REPOSITORY_PATH_NOT_DEFINED);
		}
		// 如果文件对象是空值，报错提醒。返回存放路径为空值。
		if (null == file) {
			logger.warn(AttachmentConstants.FILE_OBJECT_IS_NULL);
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_01);
		String currentDate = sdf.format(new Date());

		String relativePath = System.getProperty("file.separator")
				+ currentDate + System.getProperty("file.separator")
				+ getRandomFileName(fileName);
		// 根据服务器的文件保存地址和原文件名创建目录文件全路径
		String dstPath = "";
//		String fileType = fileName.substring(fileName.lastIndexOf("."));
		String returnPath="";
		dstPath = ConfConstants.FILE_REPOSITORY_PATH+ relativePath;
		returnPath = dstPath;

		checkExist(dstPath);
		File dstFile = new File(dstPath);
		copy(file, dstFile);
		return returnPath;
	}

	/**
	 * @Description 多文件上传
	 * @author 付强
	 * @param files
	 *            文件对象数组
	 * @param fileName
	 *            文件名数组
	 * @param ContentType
	 *            类型数组
	 * @return 上传文件存放路径数组
	 * @throws Exception
	 *             异常
	 */
	public static String[] multiUploadFiles(File[] fileArray,
			String[] fileNameArray, String[] contentType,
			HttpServletRequest request) throws Exception {
		// 如果文件存放路径未指定，在日志中发出警告
		if (null == ConfConstants.FILE_REPOSITORY_PATH || "".equals(ConfConstants.FILE_REPOSITORY_PATH)) {
			logger.warn(AttachmentConstants.FILE_REPOSITORY_PATH_NOT_DEFINED);
		}
		
		// 文件存按日期分割
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_01);
		String currentDate = sdf.format(new Date());
		/**
		 * 如果文件数组或者文件名为空值，发出警告并且返回上传文件路径数组为空值。 文件对象存在空值，发出警告并返回空值。
		 */
		if (null == fileArray || null == fileNameArray) {
			logger.warn(AttachmentConstants.FILE_UPLOAD_PARAMETER_ERROR);
			return null;
		}
		for (int i = 0; i < fileArray.length; i++) {
			if (null == fileArray[i]) {
				logger.warn(AttachmentConstants.FILE_ARRAY_FIND_NULL_NULL);
				return null;
			}
		}

		/**
		 * 如果文件名对象数组不为空，和文件对象个数对应，并且文件名都有定义，完成文件上传。 不对应就返回空值
		 */
		if (null != fileNameArray && fileArray.length == fileNameArray.length) {
			for (int i = 0; i < fileNameArray.length; i++) {
				if (null == fileNameArray[i] || "".equals(fileNameArray[i])) {
					logger.warn(AttachmentConstants.FILE_NAME_ARRAY_FIND_NULL_VALUE);
					return null;
				}
			}
			String[] dstFileArray = new String[fileArray.length];
			// 处理每个要上传的文件
			for (int i = 0; i < fileArray.length; i++) {
				String relativePath = System.getProperty("file.separator")
						+ currentDate + System.getProperty("file.separator")
						+ getRandomFileName(fileNameArray[i]);
				// 根据服务器的文件保存地址和原文件名创建目录文件全路径
//				String dstPath = request.getSession().getServletContext()
//						.getRealPath(fileRepositoryPath)
//						+ relativePath;
				String dstPath = ConfConstants.FILE_REPOSITORY_PATH+ relativePath;
				checkExist(dstPath);
				File dstFile = new File(dstPath);
				copy(fileArray[i], dstFile);
				dstFileArray[i] = relativePath;
			}
			return dstFileArray;
		}
		return null;
	}
	
	/**
	 * 文件下载
	 * @param downloadFilePath 文件的保存路径
	 * @param request
	 * @return 下载文件流
	 * @throws Exception 
	 * */
	public static InputStream getInputStream(String downloadFilePath,HttpServletRequest request) throws FileNotFoundException{
		InputStream inputStream = null;
		logger.info("下载路径："+downloadFilePath);
		try {
			inputStream = new FileInputStream(downloadFilePath);
		} catch (FileNotFoundException e) {
			logger.error("下载失败！"+e.getMessage());
			throw new FileNotFoundException("文件不存在！");
		}finally {
			if (null != inputStream) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		if(inputStream != null){
			logger.info("下载成功！");
		}
		return inputStream;
	}
	
	/**
	 * 根据文件名获得文件类型
	 * 
	 * @param fileName
	 *            文件名
	 * @return 文件扩展名
	 */
	private static String getExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private static String getRandomFileName(String fileName) {
		return UUID.randomUUID().toString() + getExt(fileName);
	}
	
}
