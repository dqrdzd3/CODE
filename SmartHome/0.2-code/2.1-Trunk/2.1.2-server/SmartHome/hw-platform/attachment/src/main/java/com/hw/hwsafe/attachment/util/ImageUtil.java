package com.hw.hwsafe.attachment.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.utils.FileNameUtil;

public class ImageUtil {
	
	private static final Logger logger = Logger.getLogger(ImageUtil.class);
	
	private static final int IMG_WIDTH = 100;
	private static final int IMG_HEIGHT = 100;
	
	/**
	 * 生成缩略图
	 * 2012-07-30
	 * @throws Exception 
	 * */
	public static String thumbnails(String imgPath,String fileName,HttpServletRequest request) throws Exception{
	
		fileName = FileNameUtil.convertSuffixToLowerCase(fileName);
		
		System.out.println("生成缩略图开始！");
		
		if(ConfConstants.IMAGE_REPOSITORY_PATH == null || ConfConstants.IMAGE_REPOSITORY_PATH.isEmpty()){
			logger.warn("图片缩略图的仓库路径未定义！");
		}
		
		String relativePath = getRelativePath(fileName);
		
		String dstPath = ServletActionContext.getServletContext().getRealPath(ConfConstants.IMAGE_REPOSITORY_PATH)+relativePath;
		
		checkExist(dstPath);
		
		File imgSavePath = new File(dstPath);//缩略图文件
		
//		File imgfile = new File(fileRepositoryPath+imgPath);//保存的原始文件
		File imgfile = new File(imgPath);//保存的原始文件
		
		if(!imgfile.isFile()){
			logger.error(fileName+"不是一个可创建缩略图的图片文件！");
			throw new Exception(fileName+"不是一个可创建缩略图的图片文件！");
		}
		
		
		BufferedImage bi = ImageIO.read(imgfile);
		
		Image tempImg  =  bi.getScaledInstance(IMG_WIDTH, IMG_HEIGHT,BufferedImage.SCALE_SMOOTH);
		
		double Ratio = 0.0;

		if ((bi.getHeight() > IMG_HEIGHT) || (bi.getWidth() > IMG_WIDTH)) {   
            if (bi.getHeight() > bi.getWidth())   
            	Ratio = (double)IMG_WIDTH / bi.getHeight();   
            else  
                Ratio = (double)IMG_HEIGHT / bi.getWidth();   
            try {
            	AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(Ratio, Ratio), null);   
            	tempImg = op.filter(bi, null);   
				
			} catch (Exception e) {
                 tempImg =new   BufferedImage(tempImg.getWidth(null),tempImg.getHeight(null),BufferedImage.TYPE_INT_RGB);  
                Graphics2D   g2=   ((BufferedImage) tempImg).createGraphics();  
                g2.drawImage(tempImg,0,0,null);  
			}
            ImageIO.write((BufferedImage)tempImg, "jpg", imgSavePath);
		}else{
			ImageIO.write(bi, "jpg", imgSavePath);
		}
		
		String result = dstPath.substring(dstPath.lastIndexOf("hw_files"));
		return result.replace("\\", "/");
	}

	/**
	 * 取得缩略图 的保存目录
	 * 2012-07-30
	 * */
	private static String getRelativePath(String fileName){
		String relativePath = "";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String curDate = sdf.format(new Date());
		
		 relativePath = System.getProperty("file.separator")+curDate
				+System.getProperty("file.separator")+fileName;
		
		
//		String servicePath = ServletActionContext.getServletContext().getRealPath(imageRepositoryPath);
		
//		imgSavePath = servicePath + relativePath;
		
		return relativePath;
	}
	
	private static void checkExist(String filepath) throws Exception {
		File file = new File(filepath);
		if (!file.exists()) {// 判断文件是否存在
			File file2 = new File(file.getParent());
			file2.mkdirs(); // 创建文件夹
			if (!file.isDirectory()) {
				file.createNewFile();// 创建文件
			}
		}
	}
}
