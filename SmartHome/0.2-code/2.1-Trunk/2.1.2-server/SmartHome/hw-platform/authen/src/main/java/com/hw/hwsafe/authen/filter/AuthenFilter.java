package com.hw.hwsafe.authen.filter;

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
import com.hw.hwsafe.platform.constants.Constants;

/**
 * Servlet Filter implementation class AuthenFilter
 * 
 * @author:杜群星
 * @create_time:2013-6-21
 */
public class AuthenFilter implements Filter {

	private static final String LOGIN_URL = "/index";
	private String noFilterPrefix;
	private String noFilterSuffix;

	/**
	 * Default constructor.
	 */
	public AuthenFilter() {

	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {

	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest servletRequest,
			ServletResponse servletResponse, FilterChain fileChain)
			throws IOException, ServletException {
		// place your code here

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(false);

		String reqUrl = request.getRequestURI();
		String targetUrl = "";
//		if(reqUrl.indexOf("/",1) > 0){
			targetUrl = reqUrl.substring(reqUrl.indexOf("/",1) + 1);
//		}else{
//			targetUrl = reqUrl.substring(reqUrl.indexOf("/") + 1);
//		}
		if (!isOpenUrl(targetUrl)) {
			boolean notLoginFlag = true;
			if (session != null) {
				Object object = session.getAttribute(Constants.USER_ID);
				if(object != null && !object.toString().isEmpty()){
					notLoginFlag = false;
				}
			}
			if (notLoginFlag) {
				response.sendRedirect(request.getContextPath() + LOGIN_URL);
				return;
			}

		}

		// pass the request along the filter chain
		fileChain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		noFilterPrefix = fConfig.getInitParameter("noFilterPrefix");
		noFilterSuffix = fConfig.getInitParameter("noFilterSuffix");
	}

	/**
	 * 
	 * 判断是否是不进行session控制的url
	 * 
	 * @param url
	 * @return
	 * @author:杜群星
	 * @create_time:2013-7-2下午4:55:08
	 */
	private boolean isOpenUrl(String url) {
		boolean flag = false;
		String[] prefixes = noFilterPrefix.split(",");
		String[] suffixes = noFilterSuffix.split(",");
		for (String prefix : prefixes) {
			if (url.startsWith(prefix)) {
				flag = true;
				break;
			}
		}
		if(!flag){
			for(String suffix : suffixes){
				if(url.endsWith(suffix)){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

}
