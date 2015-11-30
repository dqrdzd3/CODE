/**
 * 文件名：DbResourcePropertyPlaceholderConfigurer.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.framework.dbresource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

/**
 * 
 * 数据库资源的PropertyPlaceholderConfigurer类 
 * 				用于spring加载properties文件
 * 
 * @author 马宁
 * @since 2012-11-28 14:08:09
 * 
 */
public class DbResourcePropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {

	/**
	 * 资源文件路径数组
	 */
	private Resource[] locations;

	/**
	 * 密匙路径
	 */
	private Resource keyLocation;

	/**
	 * 文件编码
	 */
	private String fileEncoding;

	/**
	 * 加载properties文件
	 * 
	 * @author 马宁
	 */
	@Override
	protected void loadProperties(Properties props) throws IOException {
		if (this.locations != null) {
			PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
			for (int i = 0; i < this.locations.length; i++) {
				Resource location = this.locations[i];
				if (logger.isInfoEnabled()) {
					logger.info("Loading properties file from " + location);
				}
				InputStream is = null;
				try {
					is = location.getInputStream();
					// 加载密钥
					Key key = DESEncryptUtil.getKey(keyLocation
							.getInputStream());
					// 对属性文件进行解密
					is = DESEncryptUtil.doDecrypt(key, is);
					// 将解密后的属性流装载到props中
					if (fileEncoding != null) {
						propertiesPersister.load(props, new InputStreamReader(
								is, fileEncoding));
					} else {
						propertiesPersister.load(props, is);
					}
				} finally {
					if (is != null)
						is.close();
				}
			}
		}
	}

	// -------------- setter and getter ---------------

	public Resource[] getLocations() {
		return locations;
	}

	public void setLocations(Resource[] locations) {
		this.locations = locations;
	}

	public Resource getKeyLocation() {
		return keyLocation;
	}

	public void setKeyLocation(Resource keyLocation) {
		this.keyLocation = keyLocation;
	}

	public String getFileEncoding() {
		return fileEncoding;
	}

	public void setFileEncoding(String fileEncoding) {
		this.fileEncoding = fileEncoding;
	}
}
