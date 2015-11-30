package com.hw.hwsafe.core.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：InitServlet
 * 类描述：
 * 创建人：孟繁波
 * 创建时间：2012-7-29 上午12:10:21
 * 修改人：孟繁波
 * 修改时间：2012-7-29 上午12:10:21
 * 修改备注：
 * @version 
 * 
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init() throws ServletException {
		
		// config param
		Map<String,String> config = new HashMap<String,String>();;
		String cfgPath = "/system-config.properties";
		InputStream is = null;
		try {
			Properties prop = new Properties();
			is = getClass().getResourceAsStream(cfgPath);
			prop.load(is);
			Enumeration em = prop.propertyNames();
			while (em.hasMoreElements()) {
				String name = (String) em.nextElement();
				// 转码
				String value = new String(prop.getProperty(name).getBytes("ISO-8859-1"), "UTF-8"); 
				config.put(name, value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(is != null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		getServletContext().setAttribute("config", config);
	}
	
	
	
	
	

}
