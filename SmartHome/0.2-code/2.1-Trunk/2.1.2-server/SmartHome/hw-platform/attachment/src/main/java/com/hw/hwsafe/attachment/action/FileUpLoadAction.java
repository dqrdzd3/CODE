package com.hw.hwsafe.attachment.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.pojo.C004PO;
import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.attachment.util.FileUpload;
import com.hw.hwsafe.attachment.util.ImageUtil;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe 
 * 类名称：FileUpLoadAction 
 * 类描述： 文件上传下载
 * 创建人：杜群星
 * 创建时间：2012-7-18
 * @version
 * 
 */
public class FileUpLoadAction implements ServletRequestAware,ServletResponseAware {

//	private static String FILE_TEMPALE_PATH ="";
	
	protected Logger logger = Logger.getLogger(getClass());
	
	protected static final String JSON_DATA = "jsonData";
	protected static final String SUCCESS = "success";
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	@Autowired
	IAttachmentService attachmentService;
	
	//上传文件
	protected String upload;
	//上传文件名称
	protected String uploadFileName;
	//上传文件类型
	protected String uploadContentType;
	//下载文件名称
	protected String downloadFileName;
	//下载文件相对地址
	protected String downloadFilePath;
	//json返回内容
	protected JSONObject jsonData = new JSONObject();
	//上传文件
	protected File[] uploadFile;
	
	protected String[] uploadFileFileName;
	
	/**
	 * 单文件上传
	 * @throws Exception 
	 * */
	public String doUpLoad(){
		try {
			System.out.println("---------------------doUpload");
			File file = new File(upload);
			String filePath = FileUpload.uploadFiles(file, uploadFileName, uploadContentType, request);
			
			jsonData.put("filePath", filePath);
			jsonData.put("fileName", uploadFileName);
			logger.info("文件上传成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文件上传失败！");
			jsonData.put("errMsg", "上传失败");
		}
		return JSON_DATA;
	}
	/**
	 * 文件下载流
	 * @throws Exception 
	 * */
	public String doDownLoad() throws Exception{
		File file = new File(downloadFilePath);
		if(!file.exists()){
			throw new Exception("下载地址：『"+downloadFilePath+"』,文件:『"+downloadFileName+"』不存在！");
		}
		return "down";
	}
	
	public InputStream getInputStream() throws Exception{
		
		InputStream inputStream = null;
		try {
			inputStream = FileUpload.getInputStream(downloadFilePath, request);
		} catch (Exception e) {
			throw new Exception("下载失败！");
		}
		 return inputStream;
	}
	
	/**
	 * 图片上传，并返回缩略图路径
	 * */
	public String doUploadImage(){
		try {
			System.out.println("---------------------doUploadImage");
			File file = new File(upload);
			String filePath = FileUpload.uploadFiles(file, uploadFileName, uploadContentType, request);
			//生成缩略图
			
			String imagePath = ImageUtil.thumbnails(filePath, uploadFileName, request);
			
			jsonData.put("filePath", filePath);
			jsonData.put("fileName", uploadFileName);
			jsonData.put("imagePath", imagePath);
			logger.info("图片上传成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("图片上传失败！");
			jsonData.put("errMsg", "上传失败");
		}
		return JSON_DATA;
	}
	
	/**
	 * 视频上传
	 * @throws Exception 
	 * */
	public String doUploadVedio(){
		try {
			System.out.println("---------------------doUploadVedio");
			File file = new File(upload);
			String filePath = FileUpload.uploadFiles(file, uploadFileName, uploadContentType, request);
			
			jsonData.put("filePath", filePath);
			jsonData.put("fileName", uploadFileName);
			logger.info("视频上传成功！");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("视频上传失败！");
			jsonData.put("errMsg", "上传失败");
		}
		return JSON_DATA;
	}
	/**
	 * 上传pdf时，转换成swf格式文件，并保存原文件
	
	 * doUploadPdf
	 * @param   
	 * @return    String
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doUploadPdf(){
		try {
			System.out.println("---------------------doUploadVedio");
			File file = new File(upload);
			String filePath = FileUpload.uploadFiles(file, uploadFileName, uploadContentType, request);
			
			jsonData.put("filePath", filePath);
			jsonData.put("fileName", uploadFileName);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("文件上传失败！");
			jsonData.put("errMsg", "上传失败");
		}
		logger.info("文件上传成功！");
		return JSON_DATA;
	}
	
	/**
	 * 初始化新的文件上传，带预览
	 * @return
	 */
	public String initfileupload(){
		String MA002 = request.getParameter("MA002");
		request.setAttribute("MA002", MA002);
		String from = request.getParameter("from");
		request.setAttribute("from", from);
		return SUCCESS;
	}
	
	/**
	 * 初始化Iframe页面
	 * @return
	 */
	public String initFrame(){
		return SUCCESS;
	}
	
	public String uploadFile(){
		String picback = request.getParameter("picback");
		String dangerid = request.getParameter("dangerid");
		String from = request.getParameter("from");
		if(uploadFile != null && uploadFile.length > 0){
			try {
				// 取出上传文件的文件名称
				String name = uploadFileFileName[0];
				// 取得上传文件以后的存储路径
				
				String fileDir = ConfConstants.FILE_REPOSITORY;
				String path = ConfConstants.FILE_REPOSITORY_PATH;
				File file = new File(path);
				if(!file.exists()) file.mkdir();
				
				String extName = name.substring(name.lastIndexOf(".") + 1);//扩展名
//				String nameExceptExt = name.replace("."+extName, "");
				extName = extName.toLowerCase();
				name = UUIDGenerater.getUUID()+"."+extName;
				String outputFath = path +"/" + name;
				
	
				// 上传文件
				File uploaderFile = new File(outputFath);
				//输出源文件，没转换成swf的
				FileUtils.copyFile(uploadFile[0], uploaderFile);
				
//				//输出swf
				
//				if("doc".equals(extName) || "docx".equals(extName)){
//					FlashPaper.word2Pdf(outputFath, path+"/"+nameExceptExt+".pdf");
//					FlashPaper.converter(path+"/"+nameExceptExt+".pdf", path+"/"+nameExceptExt+".swf", "aa.pdf");
//				}else if("txt".equals(extName)){
//					FlashPaper.txt2Pdf(outputFath, path+"/"+nameExceptExt+".pdf");
//					FlashPaper.converter(path+"/"+nameExceptExt+".pdf", path+"/"+nameExceptExt+".swf", "aa.pdf");
//				}else{
//					FlashPaper.converter(outputFath, path+"/"+nameExceptExt+".swf", name);
//				}
				
//				request.setAttribute("fileName", name);
//				request.setAttribute("filePath", "/fileDir"+name);
//				request.setAttribute("swfPath", "/fileDir/"+nameExceptExt+".swf");
//				request.setAttribute("extName", extName);
//				
//				//保存到数据库，暂时没有表的情况下放session里
//				List<UploadFile> filelist = (List<UploadFile>)request.getSession().getAttribute("list");
//				if(filelist == null ) filelist = new ArrayList<UploadFile>();
//				
//				UploadFile filefile = new UploadFile();
//				filefile.setId(UUIDGenerater.getUUID());
//				filefile.setIntroduce(request.getParameter("introduce"));
//				filefile.setName(nameExceptExt+"."+extName);
//				filefile.setPath(outputFath);
//				filefile.setSrcpath("/"+fileDir+"/"+nameExceptExt+"."+extName);
//				filefile.setSwfpath("/"+fileDir+"/"+nameExceptExt+".swf");
				
				C004PO c004 = new C004PO();
				c004.setMA001(UUIDGenerater.getUUID());
				c004.setMA002("00000000");
				c004.setMA003(uploadFileFileName[0]);
				c004.setMA005(name);
				c004.setMA006("/"+fileDir+"/"+name);
				c004.setMA004(outputFath);
				c004.setMA007(extName);
				c004.setMA009(getUserIdFromSession());
				c004.setMA010(DateTimeUtils.getCurrentDate());
				
				if("true".equals(picback)){
					c004.setMA002(dangerid);
					c004.setMA008(from);
					attachmentService.insertC004(c004);
				}else{
					//保存到数据库临时表
					attachmentService.insertC004TEMP(c004);
				}
				request.setAttribute("c004", c004);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化上传文件区
	 * @return
	 */
	public String initTop(){
		
		return SUCCESS;
	}
	
	/**
	 * 初始化文件展示区
	 * @return
	 */
	public String initBottom() throws Exception{
		//获取session的数据展示
//		String page = request.getParameter("curPage");
		String picback = request.getParameter("picback");
		String from = request.getParameter("from");
		
//		int curPage = 1;
//		if(page != null && page.length() > 0){
//			curPage = Integer.parseInt(page);
//		}
		
		//1页10个
//		int begin = (curPage - 1) * 10;
//		int end = curPage * 10;
//		List<UploadFile> filelist = (List<UploadFile>)request.getSession().getAttribute("list");
//		List<UploadFile> datalist = new ArrayList<UploadFile>();
//		int total = 0;
//		int totalPage = 1;
//		if(filelist != null){
//			total = filelist.size();
//			if(filelist.size() % 10 == 0) totalPage = filelist.size() / 10;
//			else totalPage = filelist.size() / 10 + 1;
//		
//			for(int i=begin;i<end;i++){
//				try{
//					datalist.add(filelist.get(i));
//				}catch (IndexOutOfBoundsException e) {
//					break;
//				}
//			}
//		}
		List<C004PO> datalist = new ArrayList<C004PO>();
		String MA002 = request.getParameter("MA002");
		
		if(MA002 != null && MA002.length() > 0){
			List<C004PO> list = attachmentService.getC004List(MA002,from);
			for(C004PO po : list){
				datalist.add(po);
			}
		}
		
		//说明不是危险源的上传
		if(!"true".equals(picback)){
			String idss = request.getParameter("ids");
			if(idss != null){
			String[] ids = idss.split(",");
			
				for(String id : ids){
					if(id != null && id.length() > 0){
						C004PO po = attachmentService.getC004POTEMP(id);
						datalist.add(po);
					}
				}
			}
		}
		request.setAttribute("list", datalist);
//		request.setAttribute("curPage", curPage);
//		request.setAttribute("totalPage", totalPage);
		request.setAttribute("total", datalist.size());
		return SUCCESS;
	}
	
	/**
	 * 预览文档
	 * @return
	 */
	public String doView(){
//		String id = request.getParameter("id");
		String swfPath = request.getParameter("swfpath");
		
		request.setAttribute("swfPath", swfPath);
		return SUCCESS;
	}
	
	/**
	 * 危险源备案资料管理预览图片
	 * @return
	 */
	public String doViewPic(){
//		String id = request.getParameter("id");
		String path = request.getParameter("path");
		
		request.setAttribute("path", path);
		return SUCCESS;
	}
		
	/**
	 * 删除文档
	 * @return
	 */
	public String doDelete() throws Exception{
		String ids = request.getParameter("ids");
		if(ids != null){
			String[] c004ids = ids.split(",");
			for(String str : c004ids){
				if(str != null && str.length() > 0){
					attachmentService.deleteC004(str);
					attachmentService.deleteC004TEMP(str);
				}
			}
		}
		return SUCCESS;
	}
	
	
	
	public String upload(){
		StringBuffer sb = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?><files>");
		if(uploadFile != null && uploadFile.length > 0){
			for(int i = 0;i<uploadFile.length ;i++){
				// 取出上传文件的文件名称
				String name = uploadFileFileName[i];
				// 取得上传文件以后的存储路径
				
				String path = ConfConstants.FILE_REPOSITORY_PATH;
				File file = new File(path);
				if(!file.exists()) file.mkdir();
				
				
				String extName = name.substring(name.lastIndexOf(".") + 1);// 扩展名
				extName = extName.toLowerCase();
				//path
				String fileName = UUIDGenerater.getUUID();
				String outputFath = path +"/" + fileName + "."+extName;
				// 上传文件
				File uploaderFile = new File(outputFath);
				try {
					FileUtils.copyFile(uploadFile[i], uploaderFile);
					C004PO c004 = new C004PO();
					c004.setMA001(UUIDGenerater.getUUID());
					c004.setMA002("00000000");
					c004.setMA003(uploadFileFileName[i]);
					c004.setMA005(fileName + "."+extName);
					c004.setMA004(outputFath);
					c004.setMA007(extName);
//					c004.setMA009(SessionUtil.getUserId());
					c004.setMA010(DateTimeUtils.getCurrentDate());
					attachmentService.insertC004TEMP(c004);
					
//					sb.append("{'id':'" + c004.getMA001() + "','name':'" + URLEncoder.encode(c004.getMA003()) + "'},");
			    	sb.append("<file>");
			    	sb.append("<id>").append(c004.getMA001()).append("</id>");
			    	String ma003 = c004.getMA003();
			    	ma003 = new String(ma003.getBytes("UTF-8"),"ISO-8859-1");
			    	sb.append("<name>").append(ma003).append("</name>");
			    	sb.append("</file>");
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
//			sb = new StringBuffer(sb.substring(0, sb.length() - 1));
		}
		
		sb.append("</files>");
		
		
		try {
			request.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	 }
	
	public String uploadWXY(){
		String dangerid = request.getParameter("dangerid");
		String from = request.getParameter("from");
		if(uploadFile != null && uploadFile.length > 0){
			for(int i = 0;i<uploadFile.length ;i++){
				// 取出上传文件的文件名称
				String name = uploadFileFileName[i];
				// 取得上传文件以后的存储路径
				
				String path = ConfConstants.FILE_REPOSITORY_PATH;
				File file = new File(path);
				if(!file.exists()) file.mkdir();
				
				
				String extName = name.substring(name.lastIndexOf(".") + 1);// 扩展名
				extName = extName.toLowerCase();
				//path
				String fileName = UUIDGenerater.getUUID();
				String outputFath = path +"/" + fileName + "."+extName;
				// 上传文件
				File uploaderFile = new File(outputFath);
				try {
					FileUtils.copyFile(uploadFile[i], uploaderFile);
					C004PO c004 = new C004PO();
					c004.setMA001(UUIDGenerater.getUUID());
					c004.setMA002(dangerid);
					c004.setMA003(uploadFileFileName[i]);
					c004.setMA005(fileName + "."+extName);
					c004.setMA004(outputFath);
					c004.setMA007(extName);
					c004.setMA008(from);
					c004.setMA009(getUserIdFromSession());
					c004.setMA010(DateTimeUtils.getCurrentDate());
					attachmentService.insertC004(c004);
			    	
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			}
		}
		return null;
	 }
	
	/**
	 * 从C004中下载数据
	 * @return
	 */
	public String download() throws Exception{
		String id = request.getParameter("id");
		//c004表里
		C004PO c004 = attachmentService.getC004PO(id);
		//如果c004表里没有，去c004_temp表中查询数据
		if(c004 == null){
			c004 = attachmentService.getC004POTEMP(id);
		}
		//如果存在，下载
		if(c004 != null){
			String absolutePath = getAbsolutePath(c004.getMA004());
			File file = new File(absolutePath);
			
			//文件不存在
			if(!file.exists()){
				request.setAttribute("msg", "文件不存在！");
				return "msg";
			}
			//存在，实行下载
			else{
				String filename = c004.getMA003();
				
				response.setContentType("application/bin");
				String agent = (String)request.getHeader("USER-AGENT");
				
				if(agent != null && agent.indexOf("MSIE") == -1) {
//					String enableFileName = "=?UTF-8?B?" + (new String(Base64.encodeBase64(filename.getBytes("UTF-8")))) + "?=";
//					response.setHeader("Content-Disposition", "attachment; filename=" + enableFileName);
					response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"),"ISO-8859-1")); 
				} else { // IE  
					filename = URLEncoder.encode(filename, "utf-8");
					response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"),"GBK"));
				}

				
		    	FileInputStream fis = null;
		    	BufferedInputStream buff = null;    	
		    	try {
					fis = new FileInputStream(file);
					buff = new BufferedInputStream(fis);
					byte [] b=new byte[1024];
					long k=0;
					OutputStream myout = response.getOutputStream();
	
					//开始循环下载
					while(k<file.length()){
					    int j=buff.read(b,0,1024);
					    k+=j;
					    myout.write(b,0,j);
	
					}
					myout.flush();
				} catch (Exception e) {
					e.printStackTrace();
					throw e;
				} finally {
					if(buff != null){
						buff.close();
					}
					if(fis != null){
						fis.close();
					}
				}
			}
		}
		//不存在，说明数据有问题
		else{
			request.setAttribute("msg", "数据异常！");
			return "msg";
		}		
		return null;
	}
	
	/**
	 * 
	 * 获取绝对路径
	 * @param path - 路径
	 * @return String
	 * @author 马宁
	 * @create_time 2013-5-9 下午2:39:23
	 */
	private String getAbsolutePath(String path){
		return isAbsolutePath(path)
				? path
				: ConfConstants.FILE_REPOSITORY_PATH + "\\" + path;
	}
	
	/**
	 * 
	 * 判断是否绝对路径
	 * @param path - 路径
	 * @return boolean
	 * @author 马宁
	 * @create_time 2013-5-9 下午2:38:59
	 */
	private boolean isAbsolutePath(String path){
		return path.contains(":\\") || path.contains(":/");
	}
	
	/**
	 * 根据主键id获取当前数据的所有附件  MA002
	 * @param id
	 * @return
	 */
	public List<C004PO> getC004List(String id){
		List<C004PO> c004list = attachmentService.getC004List(id,null);
		return c004list;
	}
	
	/**
	 * 文件上传初始化
	 * @return
	 */
	public String doAddupload(){
		String id = request.getParameter("MA002");
		String from = request.getParameter("from");
		String limit = request.getParameter("limit");
		String type = request.getParameter("type");
		List<C004PO> c004list = new ArrayList<C004PO>();
		StringBuffer ids = new StringBuffer();
		if(id != null && !id.isEmpty()){
			//查询当前对象的附件列表
			c004list = attachmentService.getC004List(id,null);
		}
		
		for(C004PO po : c004list){
			ids.append(po.getMA001()).append(",");
		}
		request.setAttribute("list", c004list);
		request.setAttribute("from", from);
		request.setAttribute("limit", limit);
		request.setAttribute("type", type);
		request.setAttribute("ids", ids.toString());
		return "index";
	}
	
	/**
	 * 从session中获取userId
	 * 
	 * @author 马宁
	 * 
	 * @修改人 马宁
	 * @修改日期 2013-09-09 10:40
	 * @修改内容 处理火狐浏览器上传时session为null的问题
	 */
	private String getUserIdFromSession(){
		HttpSession session =  ServletActionContext.getRequest().getSession();
		return session == null 
				? ""
				: session.getAttribute(Constants.USER_ID) == null
						? ""
						: session.getAttribute(Constants.USER_ID).toString();
//		return session.getAttribute(Constants.USER_ID).toString();
	}
	
	/**
	 * 
	 * 函 数 名：initfileuploadnew
	 * 功能描述：初始化上传页面iframe
	 * @return String
	 * 创建人：陈浙东
	 * 创建时间：2013-4-22 上午9:31:56
	 */
	public String initfileuploadnew() throws Exception{
		//获取已经上传的
		List<C004PO> datalist = new ArrayList<C004PO>();
		String MA002 = request.getParameter("MA002");
		String from = request.getParameter("from");
		from = "b02006".equals(from) ? "b02006" : null;
		StringBuffer ids = new StringBuffer();
		if(MA002 != null && MA002.length() > 0){
			List<C004PO> list = attachmentService.getC004List(MA002,from);
			for(C004PO po : list){
				datalist.add(po);
				ids.append(po.getMA001()).append(",");
			}
		}
		request.setAttribute("from", from);
		request.setAttribute("list", datalist);
		request.setAttribute("MA002", MA002);
		request.setAttribute("total", datalist.size());
		request.setAttribute("ids", ids.toString());
		return SUCCESS;
	}
	
	/**
	 * 
	 * 函 数 名：uploadnew
	 * 功能描述：新的上传,将图片保存再c004_temp表中，并把id返回
	 * @return String
	 * 创建人：陈浙东
	 * 创建时间：2013-4-22 上午9:58:38
	 */
	public String uploadnew(){
		String from = request.getParameter("from");
		String ma002 = request.getParameter("MA002");	
		if(uploadFile != null && uploadFile.length > 0){
			try {
				// 取出上传文件的文件名称
				String name = uploadFileFileName[0];
				// 取得上传文件以后的存储路径
				
				String fileDir = ConfConstants.FILE_REPOSITORY;
				String path = ConfConstants.FILE_REPOSITORY_PATH;
				File file = new File(path);
				if(!file.exists()) file.mkdir();
				
				String extName = name.substring(name.lastIndexOf(".") + 1);//扩展名
				extName = extName.toLowerCase();
				name = UUIDGenerater.getUUID()+"."+extName;
				String outputFath = path +"/" + name;
				
	
				// 上传文件
				File uploaderFile = new File(outputFath);
				//输出源文件，没转换成swf的
				FileUtils.copyFile(uploadFile[0], uploaderFile);
				
				C004PO c004 = new C004PO();
				String uuid = UUIDGenerater.getUUID();
				c004.setMA001(uuid);
				c004.setMA002("00000000");
				c004.setMA003(uploadFileFileName[0]);
				c004.setMA005(name);
				c004.setMA006("/"+fileDir+"/"+name);
				c004.setMA004(outputFath);
				c004.setMA007(extName);
				c004.setMA009(getUserIdFromSession());
				c004.setMA010(DateTimeUtils.getCurrentDate());
				

				
				//如果是危险源备案资料，直接保存到C004表
				if("b02006".equals(from)){
					c004.setMA002(ma002);
					c004.setMA008("b02006");
					attachmentService.insertC004(c004);
				}else{
					//保存到数据库临时表
					attachmentService.insertC004TEMP(c004);
				}
				
				//获取ids，分别查询
				String ids = request.getParameter("ids");
				
				List<C004PO> list = new ArrayList<C004PO>();
				String[] idsarr = ids.split(",");
				for(String id : idsarr){
					if(id != null && !id.isEmpty()){
						C004PO po = attachmentService.getC004PO(id);
						if(po == null){
							po = attachmentService.getC004POTEMP(id);
						}
						if(po != null){
							list.add(po);
						}
					}
				}
				list.add(c004);
				ids += uuid + ',';
				request.setAttribute("c004", c004);
				request.setAttribute("list", list);
				request.setAttribute("total", list.size());
				request.setAttribute("ids", ids);
				
				request.setAttribute("MA002", ma002);
				request.setAttribute("from", from);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 函 数 名：doViewNew
	 * 功能描述：新的图片预览
	 * @return String
	 * 创建人：陈浙东
	 * 创建时间：2013-4-22 上午10:33:41
	 */
	public String doViewNew() throws Exception{
		String id = request.getParameter("id");
		C004PO po = attachmentService.getC004PO(id);
		if(po == null){
			po = attachmentService.getC004POTEMP(id);
		}
		request.setAttribute("po", po);
		return SUCCESS;
	}
	
	
	// -------------- getter and setter ----------------
	
	public void setJsonData(JSONObject jsonData) {
		this.jsonData = jsonData;
	}

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	public File[] getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File[] uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String[] getUploadFileFileName() {
		return uploadFileFileName;
	}

	public void setUploadFileFileName(String[] uploadFileFileName) {
		this.uploadFileFileName = uploadFileFileName;
	}

	public JSONObject getJsonData() {
		return jsonData;
	}

	public void setDownloadFilePath(String downloadFilePath)
			throws UnsupportedEncodingException {
		this.downloadFilePath = downloadFilePath;
	}

	public String getDownloadFilePath() {
		return downloadFilePath;
	}

	public void setDownloadFileName(String downloadFileName)
			throws UnsupportedEncodingException {
		this.downloadFileName = URLDecoder.decode(downloadFileName, "UTF-8");
	}

	public String getDownloadFileName() throws UnsupportedEncodingException {
		return URLEncoder.encode(downloadFileName, "UTF-8");
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getUpload() {
		return upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
}


