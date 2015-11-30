package com.hw.smarthome.util;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author 曾凡
 * 
 *         使用XMl配置文件来配置日志的输入
 */
public class Ln {
	static {
		DOMConfigurator.configure("conf/log4j.xml");
	}
	public static boolean IS_DEBUG = false;
	static {
		IS_DEBUG = "DEBUG".equalsIgnoreCase(Logger
				.getLogger(Ln.class).getParent().getLevel()
				.toString());
	}

	public static void e(Class clazz, String content,
			Exception ex) {
		Logger logger = Logger.getLogger(clazz);
		logger.error(content, ex);
		ex.printStackTrace();
		// 存储到本地数据库
	}
}
