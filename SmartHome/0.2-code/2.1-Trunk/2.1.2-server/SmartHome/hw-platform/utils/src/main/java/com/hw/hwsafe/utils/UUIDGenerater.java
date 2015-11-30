/**
 * 文件名：UUIDGenerater.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.utils;

import java.util.UUID;

/**
 * 
 * 项目名称：framework
 * 类名称：UUIDGenerater
 * 类描述：生成UUID，用于ID生成
 * 创建人：盛家龙
 * 创建时间：2012-5-8 下午7:57:37
 * 修改人：盛家龙
 * 修改时间：2012-5-8 下午7:57:37
 * 修改备注：
 * @version 
 * 
 */
public final class UUIDGenerater {

	private UUIDGenerater(){}
	
	/**
	 * 生成UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	/**
	 * 生成32位UUID
	 *
	 */
	public static String get32UUID(){
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
}
