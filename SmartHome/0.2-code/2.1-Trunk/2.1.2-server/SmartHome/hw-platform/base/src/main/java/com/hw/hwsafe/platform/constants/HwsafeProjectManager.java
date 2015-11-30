/**
 * 文件名：HwsafeProjectManager.java
 *
 * 版本信息：1.0
 * 日期：2012-7-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.constants;


/**
 * hwsafe项目管理类
 * @author 马宁
 * @since 2013-02-22 10:09
 */
public final class HwsafeProjectManager {

	/**
	 * 是否高新项目
	 */
	private static final String IS_GAOXIN_PROJECT;

	static {
		IS_GAOXIN_PROJECT = ConstansPropertiesManager.getInstance().getPropertyStr("IS_GAOXIN_PROJECT");
	}

	private HwsafeProjectManager() {}

	/**
	 * 判断hwsafe项目是否高新项目
	 * @return
	 */
	public static boolean isGaoxinProject() {
		return "1".equals(IS_GAOXIN_PROJECT);
	}
}
