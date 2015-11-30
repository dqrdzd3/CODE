package com.hw.hwsafe.security.spring;

import org.jasig.cas.client.authentication.DefaultGatewayResolverImpl;
import org.jasig.cas.client.authentication.GatewayResolver;
import org.jasig.cas.client.util.CommonUtils;
import org.jasig.cas.client.validation.Assertion;

import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.utils.UrlUtil;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CustomAuthenticationFilter extends CustomAbstractCasFilter {

	/**
	 * The URL to the CAS Server login.
	 */
	private String casServerLoginUrl;

	/**
	 * Whether to send the renew request or not.
	 */
	private boolean renew = false;

	/**
	 * Whether to send the gateway request or not.
	 */
	private boolean gateway = false;

	private GatewayResolver gatewayStorage = new DefaultGatewayResolverImpl();

	protected void initInternal(final FilterConfig filterConfig)
			throws ServletException {
		if (!isIgnoreInitConfiguration()) {
			super.initInternal(filterConfig);
//			setCasServerLoginUrl(getPropertyFromInitParams(filterConfig,
//					"casServerLoginUrl", null));
			setCasServerLoginUrl(ConfConstants.CAS_SERVER + "/login");
			log.trace("Loaded CasServerLoginUrl parameter: "
					+ this.casServerLoginUrl);
			setRenew(parseBoolean(getPropertyFromInitParams(filterConfig,
					"renew", "false")));
			log.trace("Loaded renew parameter: " + this.renew);
			setGateway(parseBoolean(getPropertyFromInitParams(filterConfig,
					"gateway", "false")));
			log.trace("Loaded gateway parameter: " + this.gateway);

			final String gatewayStorageClass = getPropertyFromInitParams(
					filterConfig, "gatewayStorageClass", null);

			if (gatewayStorageClass != null) {
				try {
					this.gatewayStorage = (GatewayResolver) Class.forName(
							gatewayStorageClass).newInstance();
				} catch (final Exception e) {
					log.error(e, e);
					throw new ServletException(e);
				}
			}
		}
	}

	public void init() {
		super.init();
		CommonUtils.assertNotNull(this.casServerLoginUrl,
				"casServerLoginUrl cannot be null.");
	}

	public final void doFilter(final ServletRequest servletRequest,
			final ServletResponse servletResponse, final FilterChain filterChain)
			throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) servletRequest;
		final HttpServletResponse response = (HttpServletResponse) servletResponse;
		final String originalServiceUrl = constructServiceUrl(request, response);
		final String relativePath = originalServiceUrl.substring(UrlUtil
				.getSecondSingleSlashIndex(originalServiceUrl) + 1);
		
		// 为了解决上传组件在火狐下无法正常工作,上传在此处不过滤      /不可重构/
		if(isUploadUrl(relativePath)){
			filterChain.doFilter(request, response);
			return;
		}
		
		// 为了解决grid++报表组件在火狐和谷歌下无法正常工作,报表在此处不过滤  /不可重构/
		if(isReportUrl(relativePath)){
			filterChain.doFilter(request, response);
			return;
		}
		

		// 为解决需要点击两次注册按钮才可以跳转到注册页面的问题
		final HttpSession session = isExposedUrl(relativePath)
					? request.getSession(true)
					: request.getSession(false);
//		final HttpSession session = request.getSession(false);
					
		final Assertion assertion = session != null ? (Assertion) session
				.getAttribute(CONST_CAS_ASSERTION) : null;

		if (assertion != null || isExposedUrl(relativePath)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		if(handleSessionInvalid(request, response, session)){
			return;
		}

		final String ticket = CommonUtils.safeGetParameter(request,
				getArtifactParameterName());
		final String serviceUrl = getServiceUrl(originalServiceUrl);
		final boolean wasGatewayed = this.gatewayStorage.hasGatewayedAlready(
				request, serviceUrl);

		if (CommonUtils.isNotBlank(ticket) || wasGatewayed) {
			filterChain.doFilter(request, response);
			return;
		}

		final String modifiedServiceUrl;

		log.debug("no ticket and no assertion found");
		if (this.gateway) {
			log.debug("setting gateway attribute in session");
			modifiedServiceUrl = this.gatewayStorage.storeGatewayInformation(
					request, serviceUrl);
		} else {
			modifiedServiceUrl = serviceUrl;
		}

		if (log.isDebugEnabled()) {
			log.debug("Constructed service url: " + modifiedServiceUrl);
		}

		final String urlToRedirectTo = CommonUtils.constructRedirectUrl(
				this.casServerLoginUrl, getServiceParameterName(),
				modifiedServiceUrl, this.renew, this.gateway);

		if (log.isDebugEnabled()) {
			log.debug("redirecting to \"" + urlToRedirectTo + "\"");
		}

		response.sendRedirect(urlToRedirectTo);
	}

	// ------------------private methods ---------------------

	/**
	 * 判断是否暴露的URL
	 * 
	 * @param originalServiceUrl
	 *            - 原始的serviceUrl
	 * @author 马宁
	 */
	private boolean isExposedUrl(final String relativePath) {
		return isPublicUrl(relativePath)
				||isRegisterUrl(relativePath) 
				|| isJsUrl(relativePath)
				|| isCssUrl(relativePath)
				|| isResUrl(relativePath)
				|| isPdaUrl(relativePath)
				|| isImgUrl(relativePath);
	}

	private boolean isPublicUrl(final String relativePath){
		return relativePath.startsWith("public/");
	}
	
	/**
	 * 判断是否PDA的URL
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * 
	 * @author 马宁
	 * 
	 */
	private boolean isPdaUrl(final String relativePath) {
		return relativePath.startsWith("pda");
	}

	/**
	 * 判断是否注册页面的URL
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * @author 马宁
	 */
	private boolean isRegisterUrl(final String relativePath) {
		return relativePath.startsWith("register")
				|| relativePath.startsWith("/register")
				|| relativePath.startsWith("images/register/");
	}

	/**
	 * 判断是否js
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * @author 马宁
	 */
	private boolean isJsUrl(final String relativePath) {
		return relativePath.startsWith("js/");
	}
	
	/**
	 * 判断是否res
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * @author 马宁
	 */
	private boolean isCssUrl(final String relativePath) {
		return relativePath.startsWith("css/");
	}
	
	/**
	 * 判断是否res
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * @author 马宁
	 */
	private boolean isResUrl(final String relativePath) {
		return relativePath.startsWith("res/");
	}

	/**
	 * 判断是否images
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * @author 马宁
	 */
	private boolean isImgUrl(final String relativePath) {
		return relativePath.startsWith("images/");
	}
	
	/**
	 * 判断是否上传
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * @author 马宁
	 */
	private boolean isUploadUrl(final String relativePath) {
		return relativePath.startsWith("upload/upload");
	}
	
	/**
	 * 
	 * 判断是否报表
	 * 
	 * @param relativePath
	 *            - 请求的相对路径
	 * 
	 * @author 马宁
	 * @create_time 2013-6-25 下午1:25:39
	 */
	private boolean isReportUrl(final String relativePath) {
		return relativePath.startsWith("report/");
	}
	
	/**
	 * 获取serviceUrl
	 * 
	 * @param originalServiceUrl
	 *            - 原始的serviceUrl
	 * 
	 * @author 马宁
	 */
	private String getServiceUrl(final String originalServiceUrl) {
		int secondSingleSlashIndex = UrlUtil.getSecondSingleSlashIndex(originalServiceUrl);
		return secondSingleSlashIndex == -1 
				? originalServiceUrl
				: originalServiceUrl.substring(0, UrlUtil.getSecondSingleSlashIndex(originalServiceUrl) + 1);
	}
	
	/**
	 * 处理session失效
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @return boolean - session是否失效
	 * 
	 * @author 马宁
	 */
	private boolean handleSessionInvalid(final HttpServletRequest request,
			final HttpServletResponse response, final HttpSession session)
			throws IOException {
		boolean result = false;
		
		if (session == null 
				&& !"//index.jsp".equals(request.getServletPath())
				&& !"/index.jsp".equals(request.getServletPath())) {
			final String originalServiceUrl = constructServiceUrl(request, response);
			final String relativePath = originalServiceUrl.substring(UrlUtil
					.getSecondSingleSlashIndex(originalServiceUrl) + 1);
			
			if(isAjaxRequest(request)){
				log.error("AJAX请求SESSION失效,originalServiceUrl=" + originalServiceUrl + ";relativePath=" + relativePath + ";IP=" + request.getRemoteAddr());
				handleSessionInvalidAjaxRequest(response);
			}else{
				log.error("非AJAX请求SESSION失效,originalServiceUrl=" + originalServiceUrl + ";relativePath=" + relativePath+ ";IP=" + request.getRemoteAddr());
				handleSessionInvalidNormalRequest(response);
			}
			result = true;
		}
		
		return result;
	}
	
	/**
	 * 判断是否是AJAX请求
	 * 
	 * @author 马宁
	 */
	private boolean isAjaxRequest(final HttpServletRequest request) {
		String ajaxHeader = request.getHeader("x-requested-with");
		
		return ajaxHeader != null && ajaxHeader.equalsIgnoreCase("XMLHttpRequest");
	}
	
	/**
	 * 处理session失效一般请求
	 * 
	 * @author 马宁
	 */
	private void handleSessionInvalidNormalRequest(final HttpServletResponse response) throws IOException{
		String sessionInvalidUrl = ConfConstants.CAS_SERVER + "/logout" + "?from="
				+ ConfConstants.CAS_CLIENT
				+ "&login_err=SessionInvalidException";
		response.sendRedirect(sessionInvalidUrl);
	}
	
	/**
	 * 处理session失效AJAX请求
	 * 
	 * @author 马宁
	 */
	private void handleSessionInvalidAjaxRequest(final HttpServletResponse response){
		response.setHeader("sessionStatus","timeout"); 
	}
	
	// -------------------- getter -------------------------
	
	

	// --------------------- setter ------------------------

	public final void setRenew(final boolean renew) {
		this.renew = renew;
	}

	public final void setGateway(final boolean gateway) {
		this.gateway = gateway;
	}

	public final void setCasServerLoginUrl(final String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public final void setGatewayStorage(final GatewayResolver gatewayStorage) {
		this.gatewayStorage = gatewayStorage;
	}
}
