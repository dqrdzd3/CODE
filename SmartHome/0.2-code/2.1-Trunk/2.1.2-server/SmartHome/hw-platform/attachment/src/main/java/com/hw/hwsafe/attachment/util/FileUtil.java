package com.hw.hwsafe.attachment.util;

import java.io.File;


/**
 * 
 * 项目名称：hwsafe 
 * 类名称：     FileUtil 
 * 类描述：    文件工具类
 * 创建人：    陈浙东
 * 创建时间：2012-11-21
 * 
 * @version
 * 
 */
public final class FileUtil {

	private FileUtil() {
		
	}
	
	/**
	 * 删除文件，不是文件夹，例如doc txt pdf 图片 zip rar等...
	 * @author 陈浙东
	 * @param filePath 文件路径，example：d:/1.doc
	 * @return 0     正常删除
	 * 		   10  文件不存在 
	 *    	   20  删除文件异常，可能是文件正在使用无法删除 
	 */
	public static int deleteFileitem(String filePath){
		int result = -1;
		//创建文件
		File file = new File(filePath);
		//如果文件不存在，设置返回结果为10
		if(!file.exists())  result =  10;
		
		boolean tag = file.delete();
		
		if(tag) result = 0;
		else result = 20;
		
		return result;
	}
	
	
	
	/**
	 * 删除文件夹
	 * @author 陈浙东
	 * @param filePath 文件路径，example：d:/1
	 * @return 0     正常删除
	 * 		   10  文件夹不存在  
	 * 		   20  删除文件异常，可能是文件正在使用无法删除 
	 */
	public static int deleteFiledir(String filePath){
		int result = -1;
		//创建文件
		File file = new File(filePath);
		//如果文件不存在，设置返回结果为10
		if(!file.exists())  result =  10;
		
		if(result != 10){
			//获取文件夹里所有的文件
			File[] filelist = file.listFiles();
			for(File f : filelist){
				if(f.isDirectory()){
					//递归删除文件
					deleteFiledir(f.getAbsolutePath());
				}else{
					result = deleteFileitem(f.getAbsolutePath());
				}
			}
			//删除空的文件夹
			result = deleteFileitem(file.getAbsolutePath());
		}
		return result;
	}
	
	

}
