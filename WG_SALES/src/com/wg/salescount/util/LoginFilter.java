package com.wg.salescount.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter{

	public LoginFilter() {
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		
		String path = servletRequest.getRequestURI();
		//从session中取得用户Id
		String userId = (String)session.getAttribute("userId");
		//登录页面不用过滤
		if(path.indexOf("index.jsp") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}/*else if(path.indexOf("register.jsp") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}*/
		//判断没有用户信息，就跳转到登录页面
		if(userId == null || "".equals(userId)){
			servletResponse.sendRedirect("index.jsp");
		}else{
			//已登录，继续此次请求
			chain.doFilter(request, response);
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {		
	}

}
