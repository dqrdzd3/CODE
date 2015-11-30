/**
 * 文件名：ExceptionInterceptor.java
 *
 * 版本信息：1.0
 * 日期：2012-5-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.exception.interceptor;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.jsonmsg.JsonMsgException;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IExceptionLogService;
import com.hw.hwsafe.platform.util.ServiceBeanUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 * 异常拦截器类
 * 
 * @修改人 马宁
 */
public class ExceptionInterceptor implements Interceptor {

	private static final long serialVersionUID = -85725627902114765L;

	/**
	 * 异常日志处理service
	 */
	private IExceptionLogService exceptionLogService = (IExceptionLogService) ServiceBeanUtils
			.getBean("exceptionLogService");
	
	/**
	 * 销毁
	 */
	@Override
	public void destroy() {
		// do nothing
	}

	/**
	 * 初始化
	 */
	@Override
	public void init() {
		// do nothing
	}

	/**
	 * 拦截处理
	 */
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try{
			return invocation.invoke();
		}catch (Exception e) {
			StringWriter out = new StringWriter();
			e.printStackTrace(new PrintWriter(out));
			
			String logCode = exceptionLogService.log(e.getMessage(), out.toString());
			
			if(e instanceof JsonMsgException){
				e.printStackTrace();
				JsonMsgException customE = (JsonMsgException) e;
				((BaseAction)invocation.getProxy().getAction()).setMessage(getUserMessage(customE, logCode));
				return Constants.JSON_MSG;
			}
			
	        System.out.println(out.toString());
	        
	        ActionContext actionContext = invocation.getInvocationContext();
	        actionContext.put("logCode", logCode);
			actionContext.put("exception", out.toString());
			return "exception";
		}
	}
	
	// --------- private methods --------
	
	/*
	 * 获取userMessage
	 * 
	 * @author 马宁
	 */
	private UserMessageData getUserMessage(JsonMsgException e, String logCode) {
		UserMessageData result = new UserMessageData();
		if (e.getMsgTitle() == null) {
			result.set(getMsgType(e), getMsgContent(e, logCode));
		} else {
			result.set(getMsgType(e), getMsgContent(e, logCode), e.getMsgTitle());
		}
		return result;
	}
	
	/*
	 * 获取信息类型
	 * 
	 * @author 马宁
	 */
	private int getMsgType(JsonMsgException e){
		return e.getMsgType() == null 
				? Constants.MSG_ERROR
				: e.getMsgType();
	}

	/*
	 * 获取信息内容
	 * 
	 * @author 马宁
	 */
	private String getMsgContent(JsonMsgException e, String logCode){
		return "错误信息：" + e.getMessage() + "<br>" + "错误日志编号：" + logCode;
	}
	
}
