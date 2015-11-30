/**
 * 文件名：ServiceBeanUtils.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：framework
 * 类名称：ServiceBeanUtils
 * 类描述：获取ServiceBean
 * 创建人：盛家龙
 * 创建时间：2012-5-8 下午7:57:37
 * 修改人：盛家龙
 * 修改时间：2012-5-8 下午7:57:37
 * 修改备注：
 * @version 
 * 
 */
public class ServiceBeanUtils implements ApplicationContextAware {

    protected static ApplicationContext staticContext;
	
	protected ServiceBeanUtils() {
		
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        staticContext = context;
    }

    public static Object getBean(String strBeanName) {
    	try {
    		return staticContext.getBean(strBeanName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return null;
    }

    public static IBaseService getServiceBean(String strBeanName){
    	return (IBaseService)getBean(strBeanName);
    }

}
