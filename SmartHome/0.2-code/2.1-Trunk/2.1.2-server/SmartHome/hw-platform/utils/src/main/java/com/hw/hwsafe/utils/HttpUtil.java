package com.hw.hwsafe.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

/**
 * http相关工具
 * 
 * @author 马宁
 * @创建时间 2013-04-26
 */
public final class HttpUtil {

	private HttpUtil() {}

	/**
	 * 获取远程地址
	 */
	public static String getRemoteAddr() {
		String result;
		try {
			HttpServletRequest request = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			result = request.getRemoteAddr();
		} catch (Exception e) {
			result = null;
		}
		return result;
	}

}
