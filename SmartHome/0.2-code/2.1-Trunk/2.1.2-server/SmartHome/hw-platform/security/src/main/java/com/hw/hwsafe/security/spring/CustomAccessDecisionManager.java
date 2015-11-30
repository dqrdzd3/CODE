package com.hw.hwsafe.security.spring;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.DuplicationLoginException;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.session.recorder.SessionRecorder;
import com.hw.hwsafe.platform.userinfo.UserSession;

public class CustomAccessDecisionManager implements AccessDecisionManager {

	/**
	 * properties路径
	 */
//	private static final String PROPERTIES_PATH = "/filterUrl.properties";

	/**
	 * 过滤的正则表达式
	 */
//	private static final String REGEX_FILTER = "regexFilter";

	/**
	 * 不过滤的正则表达式
	 */
//	private static final String REGEX_PASS = "regexPass";

	/**
	 * filterUrl.properties
	 */
//	private static final Properties FILTER_URL_PROPERTIES = getFilterUrlProperties();

	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		
//		FilterInvocation fi = (FilterInvocation) object;
		
//		String url = fi.getRequestUrl().substring(1);
//		
//		if (url.contains("?")) {
//			url = url.substring(0, url.indexOf("?"));
//		}
//
//		if (!isUrlNeedFilter(url)) {
//			return;
//		}
//
//		url = UrlUtil.delUnnecessaryUrlSuffix(url);
		
//		HttpSession session = fi.getHttpRequest().getSession();
//
//		@SuppressWarnings("unchecked")
//		List<String> permModuleUrls = (List<String>) session
//				.getAttribute("permModuleUrl");
		
		// 验证重复登录
//		if(!isSysAdmin(session) && !SessionRecorder.isSessionLawful(session.getId())){
//			throw new DuplicationLoginException();
//		}
		
//		
//		if (configAttributes == null) {
//			return;
//		}
//		
//		if ((permModuleUrls.isEmpty()) || (!permModuleUrls.contains(url))) {
//			Logger.getLogger(this.getClass()).error("url:" + url + "没有访问权限 ");
//			throw new AccessDeniedException("没有权限访问！");
//		}
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

	// ---------------- private methods -----------------

	/**
	 * 获取FilterUrlProperties
	 * 
	 * @author 马宁
	 */
//	private static Properties getFilterUrlProperties() {
//		return PropertiesFactory.reload(PROPERTIES_PATH).getProperties();
//	}

	/**
	 * 判断url是否需要过滤
	 * 
	 * @param url
	 *            - url
	 * @author 马宁
	 */
//	private boolean isUrlNeedFilter(String url) throws AccessDeniedException {
//		if (url.startsWith("public/") 
//				|| url.startsWith("pda")
//				|| url.startsWith("register")
//				|| url.startsWith("upload")) {
//			return false;
//		}
//
//		if (url.toLowerCase().endsWith(".php")) {
//			return false;
//		}
//
//		if (!url.contains("!")) {
//			return true;
//		}
//
//		String methodName = url.substring(url.indexOf("!") + 1);
//		if (isMethodNeedFilter(methodName)) {
//			return true;
//		}
//
//		if (isMethodNeedPass(methodName)) {
//			return false;
//		}
//
//		Logger.getLogger(this.getClass()).error(
//				"url:" + url + "没有配置是否需要过滤,请修改url请求的名字");
//		throw new AccessDeniedException("该url没有配置是否需要过滤!");
//	}

	/**
	 * 判断方法是否需要过滤
	 * 
	 * @param methodName
	 *            - 方法名
	 * @author 马宁
	 */
//	private boolean isMethodNeedFilter(String methodName) {
//		String regexFilter = getRegexFilter();
//		Pattern patternFilter = Pattern.compile(regexFilter,
//				Pattern.CASE_INSENSITIVE);
//		Matcher matcherFilter = patternFilter.matcher(methodName);
//		return matcherFilter.find();
//
//	}

	/**
	 * 判断方法是否不需要过滤
	 * 
	 * @param methodName
	 *            - 方法名
	 * @author 马宁
	 */
//	private boolean isMethodNeedPass(String methodName) {
//		String regexPass = getRegexPass();
//		if(regexPass.isEmpty()){
//			return false;
//		}
//		
//		Pattern patternPass = Pattern.compile(regexPass,
//				Pattern.CASE_INSENSITIVE);
//		Matcher matcherPass = patternPass.matcher(methodName);
//		return matcherPass.find();
//	}

	/**
	 * 获取需要过滤的正则表达式字符串
	 * 
	 * @author 马宁
	 */
//	private String getRegexFilter() {
//		return FILTER_URL_PROPERTIES.getProperty(REGEX_FILTER);
//	}

	/**
	 * 获取不过滤的字符串
	 * 
	 * @author 马宁
	 */
//	private String getRegexPass() {
//		return FILTER_URL_PROPERTIES.getProperty(REGEX_PASS);
//	}
	
	/**
	 * 判断当前登录用户是否系统管理员
	 * 
	 * @author 马宁
	 */
//	private boolean isSysAdmin(final HttpSession session){
//		UserPO userPo = ((UserSession)session.getAttribute(Constants.USER_SESSION_KEY)).getUserPO();
//		return userPo.isSysAdmin();
//	}
}