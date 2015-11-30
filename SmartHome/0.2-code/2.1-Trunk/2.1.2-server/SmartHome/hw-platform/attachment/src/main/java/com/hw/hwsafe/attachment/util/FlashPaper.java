package com.hw.hwsafe.attachment.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ResourceBundle;

import jp.ne.so_net.ga2.no_ji.jcom.IDispatch;
import jp.ne.so_net.ga2.no_ji.jcom.JComException;
import jp.ne.so_net.ga2.no_ji.jcom.ReleaseManager;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class FlashPaper extends Thread {
	public static boolean converter(String sourcePath, String destPath,
			String fileName) throws Exception {
		ResourceBundle rb = ResourceBundle.getBundle("swftool");
		String location = rb.getString("swftool_home");
		boolean st_return = true;
		Runtime pro = Runtime.getRuntime();
		try {
			// 取文件的扩展名
			String extName = fileName.substring(fileName.lastIndexOf(".") + 1);// 扩展名
			extName = extName.toLowerCase();
			String command = "";
			// 目标路径不存在则建立目标路径
			// File dest = new File(destPath);
			// if (!dest.exists()) dest.mkdirs();
			// 源文件不存在则返回
			File source = new File(sourcePath);
			if (!source.exists())
				return st_return;
			// 判断要转换的文件的类型，根据文件类型调用不同的命令
			if ("pdf".equals(extName)) {

				command = location + "\\pdf2swf.exe  -t \"" + sourcePath
						+ "\" -o  \"" + destPath + "\" -s flashversion=9";
			} else if ("doc".equals(extName)) {

				command = location + "\\font2swf.exe   \"" + sourcePath
						+ "\" -o  \"" + destPath + "\" -s flashversion=9";
			} else if ("jpeg".equals(extName) || "jpg".equals(extName)) {

				command = location + "\\jpeg2swf.exe   \"" + sourcePath
						+ "\" -o  \"" + destPath + "\" -T 9";
			} else if ("png".equals(extName)) {

				command = location + "\\png2swf.exe   \"" + sourcePath
						+ "\" -o  \"" + destPath + "\" -T 9 -r 1";
			} else if ("gif".equals(extName)) {

				command = location + "\\gif2swf.exe   \"" + destPath
						+ "\" -r 1";
			} else if (extName.equals("wav")) {

				command = location + "\\wav2swf.exe  \"" + sourcePath
						+ "\" -o  \"" + destPath + "\" -S ";
			}
			// 执行cmd命令
			if (command.isEmpty()) {

				st_return = false;
			} else
				pro.exec(command);
		} catch (Exception e) {
			System.out.println("converter failed");
			e.printStackTrace();
		}
		return st_return;
	}

	/**
	 * 将doc docx文件转化成pdf文件    
	 * 要求，机器上装有Adobe Acrobat，否则报错
	 * @param sFilePath  example:d:/1.doc
	 * @param dFilePath  example:d:/1.pdf
	 * @author 陈浙东
	 */
	public static void word2Pdf(String sFilePath,String dFilePath){
		ReleaseManager rm = null;
		IDispatch app = null;
		rm = new ReleaseManager();
		try {
			app = new IDispatch(rm, "PDFMakerAPI.PDFMakerApp");
			
			app.method("CreatePDF", new Object[] {sFilePath, dFilePath});
		} catch (JComException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 将txt文件转化成pdf文件    
	 * @param sFilePath  example:d:/1.txt
	 * @param dFilePath  example:d:/1.pdf
	 * @author 陈浙东
	 */
	public static void txt2Pdf(String sFilePath,String dFilePath){
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(sFilePath)));
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(dFilePath));  
//			BaseFont chinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//			com.lowagie.text.Font FontChinese = new com.lowagie.text.Font(chinese, 12, com.lowagie.text.Font.NORMAL);
			StringBuffer sb = new StringBuffer();  
			while(true){
				String str = br.readLine();
				if(null == str) break;
				sb.append(str).append("\r\n");
			}
			document.open(); 
			document.add(new Paragraph(sb.toString()));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(br != null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
