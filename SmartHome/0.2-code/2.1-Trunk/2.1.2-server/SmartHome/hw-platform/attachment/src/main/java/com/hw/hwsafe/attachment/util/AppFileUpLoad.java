package com.hw.hwsafe.attachment.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.FileCopyUtils;

public class AppFileUpLoad {
	
	//属性文件路径
	private static final String CONF_FILE = "/conf.properties";
	//图片仓库属性
	private static final String IMGS_REPOSITORY_KEY = "SMART_IMGS_REPOSITORY";
	private static final String IMGS_REPOSITORY_PATH = "SMART_IMGS_PATH";
	//图片附件仓库目录
	private static  String imgsRepository;
	private static  String imgsPath;
	public static boolean isCheckDeviceInDB;
	public static String syncFilename;
	
	public static String localR1Type;
	public static String localA1Type;
	
	public static String localR1SoftVersion;
	public static String localR1HardVersion;
	
	public static String localA1SoftVersion;
	public static String localA1HardVersion;
	
	public static String socket_remote_ip;
	public static String socket_remote_port;
	public static String socket_local_port;
	
	static{
		loadProperties();
	}
	
	private static void loadProperties(){
		Properties properties  = new Properties();
		InputStream is = AppFileUpLoad.class.getResourceAsStream(CONF_FILE);
		try {
			properties.load(is);
			imgsRepository = properties.getProperty(IMGS_REPOSITORY_KEY);
			imgsPath = properties.getProperty(IMGS_REPOSITORY_PATH);
			isCheckDeviceInDB = Boolean.parseBoolean(properties.getProperty("device.checkValid","true"));
			syncFilename = properties.getProperty("SyncDataFilename");
			
			localR1Type = properties.getProperty("r1_local_hardware").trim();
			localR1SoftVersion = properties.getProperty("r1_local_hardware_soft_version").trim();
			localR1HardVersion = properties.getProperty("r1_local_hardware_hard_version").trim();
			
			localA1Type = properties.getProperty("a1_local_hardware").trim();
			localA1SoftVersion = properties.getProperty("a1_local_hardware_soft_version").trim();
			localA1HardVersion = properties.getProperty("a1_local_hardware_hard_version").trim();
			
			socket_local_port = properties.getProperty("socket_local_port").trim();
			socket_remote_ip = properties.getProperty("socket_remote_ip").trim();
			socket_remote_port = properties.getProperty("socket_remote_port").trim();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 单附件上传
	 * @notice 可扩展成通用的单附件上传，在创建附件存储路径时进行判断，创建相应的附件路径即可
	 * @param b
	 * @param fileName
	 * @return
	 * @throws Exception          
	 * @author:杜群星
	 * @create_time:2014-3-21下午2:17:08
	 */
	public static String uploadOne(File f,String fileName) throws Exception{
		String filePath = "";
		//创建图片的存储路径
		filePath = createImgFile(fileName);
	
		FileCopyUtils.copy(f, new File(filePath));
		System.out.println(filePath.lastIndexOf(imgsRepository));
		System.out.println(filePath);
		//返回的相对路径
		String relativePath = filePath.substring(filePath.lastIndexOf(imgsPath)+12);
		relativePath = relativePath.replace("\\", "/");
		return relativePath;
	}
	/**
	 * 
	 * 多附件上传
	 * 
	 * @param list
	 * @param fileNames 
	 * @return {@link List}
	 * @throws Exception          
	 * @author:杜群星
	 * @create_time:2014-3-21下午2:34:16
	 */
	public static List<String> uploadMulti(List<File> list,List<String> fileNames ) throws Exception{
		List<String> filePathList = new ArrayList<String>();
		for (int i = 0; i < list.size();i++) {
			String filePath = uploadOne(list.get(i), fileNames.get(i));
			filePathList.add(filePath);
		}
		return filePathList;
		
	}
	/**
	 * 
	 * 移除文件
	 * @param path
	 * @return
	 * @throws Exception          
	 * @author:杜群星
	 * @create_time:2014-3-28下午5:01:49
	 */
	public static boolean removeFile(String path) throws Exception{
		boolean flag = true;
		String absoutltPath = getRealPath("/")+path;
		File file = new File(absoutltPath);
		if(file.exists()){
			file.delete();
		}
		return flag;
	}
	/**
	 * 
	 * 批量移除文件
	 * @param list
	 * @return
	 * @throws Exception          
	 * @author:杜群星
	 * @create_time:2014-3-28下午5:02:07
	 */
	public static boolean removeFiles(List<String> list)throws Exception{
		boolean flag = true;
		for (String path : list) {
			removeFile(path);
		}
		return flag;
	}
	/**
	 * 
	 * 创建要存放的图片文件绝对路径
	 * @param fileName
	 * @return {@link String}
	 * @throws Exception          
	 * @author:杜群星
	 * @create_time:2014-3-21下午1:48:02
	 */
	private static String createImgFile(String fileName) throws Exception{
		String imgPath = "";
		imgPath = createRepository()+File.separator+fileName;
		return imgPath;
	}
	
	/**
	 * 
	 * 创建附件存储目录
	 * @notice 可扩展成通用的附件存储目录，传递附件类型进行判断分别创建自己的目录即可
	 * @return
	 * @throws Exception          
	 * @author:杜群星
	 * @create_time:2014-3-21下午2:14:12
	 */
	private static String createRepository() throws Exception{
		String repository = "";
		//String imgsRepositoryPath = getRealPath(imgsRepository);//附件仓库路径
		//String imgsRepositoryPath = imgsRepository;//附件仓库路径
		String dateDir = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		repository = imgsRepository+File.separator+dateDir;
		File repositoryDir = new File(repository);
		if(!repositoryDir.exists()){
			repositoryDir.mkdirs();
		}
		return repository;
	}
	/**
	 * 
	 * 获取目录的绝对路径
	 * @param dir
	 * @return 
	 * @throws Exception          
	 * @author:杜群星
	 * @create_time:2014-3-28下午4:50:53
	 */
	private static String getRealPath(String dir) throws Exception{
		return ServletActionContext.getServletContext().getRealPath(dir);
	}

}
