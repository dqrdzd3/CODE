package com.hw.hwsafe.smart.weixin.util;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogUtil {
	private static final String LOG_TAG = "WXLOG";
	private static final String LOGOFILEDIR = "E:\\WEIXIN";
	private static final String LOGOFILENAME = "weixin.log";
	public static void writeLog(String msg) throws Exception{
		FileWriter out = null;
		out = new FileWriter(new File(getPath()),true);
		out.write(LOG_TAG+":"+msg+"\r\n");
		out.close();
	}
	
	public static void writeExceptionLog(String msg){
		FileWriter out = null;
		try {
			out = new FileWriter(new File(getPath()),true);
			out.write(LOG_TAG+":"+msg+"\r\n");
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static String getPath(){
		String path = "";
		String curLogDir = LOGOFILEDIR+File.separatorChar+GetNowDate();
		File curLogFile = new File(curLogDir); 
		if(!curLogFile.exists()){
			curLogFile.mkdirs();
		}
		path = curLogDir+File.separatorChar+LOGOFILENAME;
		return path;
	}
	private static String GetNowDate(){   
	    String temp_str="";   
	    Date dt = new Date();   
	    //����aa��ʾ�����硱�����硱    HH��ʾ24Сʱ��    ����hh��ʾ12Сʱ��   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");   
	    temp_str=sdf.format(dt);   
	    return temp_str;   
	}  

	private static String getProPath(){
		String proPath = "";
		String userDir = System.getProperty("user.dir");
		File file = new File(userDir);
		proPath = file.getParent();
		System.out.println(file.getParent());
		return proPath;
	}
//	public static void main(String[] args) {
//		Map map = System.getenv();
//		Iterator iterator  = map.keySet().iterator();
//		while (iterator.hasNext()) {
//			String k = (String)iterator.next();
//			String v = (String)map.get(k);
//			System.out.println(k+":"+v);
//			
//		}
	
//		getProPath();
//	}
}
