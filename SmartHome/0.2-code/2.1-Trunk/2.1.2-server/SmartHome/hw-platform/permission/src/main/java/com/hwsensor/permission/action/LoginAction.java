/**
 * 文件名：LoginAction.java
 *
 * 版本信息：1.0
 * 日期：2012-5-14
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.action;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.jasig.cas.client.util.AssertionHolder;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.cpnyreg.service.IC001Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.ConfConstants;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.BadUserNameOrPassWordException;
import com.hw.hwsafe.platform.exception.BannedAccountException;
import com.hw.hwsafe.platform.exception.EntNonUseException;
import com.hw.hwsafe.platform.exception.GovModuleNotExistException;
import com.hw.hwsafe.platform.exception.GovNonUseException;
import com.hw.hwsafe.platform.exception.NotAuditAccountException;
import com.hw.hwsafe.platform.exception.NumberLimitException;
import com.hw.hwsafe.platform.exception.UserInvalidException;
import com.hw.hwsafe.platform.pojo.CallbackDataPO;
import com.hw.hwsafe.platform.pojo.SysLoginLogPO;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.ISysLoginLogService;
import com.hw.hwsafe.platform.session.recorder.SessionRecorder;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.platform.userinfo.UserSession;
import com.hw.hwsafe.platform.util.ServiceBeanUtils;
import com.hw.hwsafe.utils.PasswordUtil;
import com.hw.hwsafe.utils.StringUtil;
import com.hwsensor.permission.service.IUserService;

/**
 * 
 * 项目名称：framework
 * 类名称：LoginAction
 * 类描述：
 * 创建人：盛家龙
 * 创建时间：2012-5-14 下午2:00:31
 * 修改人：盛家龙
 * 修改时间：2012-5-14 下午2:00:31
 * 修改备注：
 * @version 
 * 
 */
public class LoginAction extends BaseAction {

	private UserPO userPO;
	
	@Autowired
	private IUserService iUserService;
	@Autowired
	private IC001Service c001Service;
	
	@Autowired
	private ISysLoginLogService sysLoginLogService;
	
	@SuppressWarnings("unused")
	private String casServer;
	
	@SuppressWarnings("unused")
	private String casClient;
	

	public String index(){
		return "index";
	}
	public String login(){
		return INPUT;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String authen() throws Exception {
		UserPO tempUserPO = null;

		if (userPO == null || userPO.getLOGIN_NAME() == null) {
			return "input";
		} else {
			String loginName = userPO.getLoginName();
			String password = userPO.getPassword();
			if (loginName != null && !"".equals(loginName) && password != null
					&& !"".equals(password)) {
				this.userPO.setPassword(PasswordUtil.createPassword(password));
				try {
					tempUserPO = this.iUserService
							.retrieveUserByLoginNameAndPwd(this.userPO);
				} catch (Exception e) {
					logger.info("用户信息获取失败");
					throw new BadUserNameOrPassWordException();
				}
			}
		}

		if (tempUserPO != null) {

			String userStatus = tempUserPO.getUSER_STATUS();

			userStatus = userStatus == null ? "" : userStatus;

			logger.debug("用户{" + tempUserPO.getLOGIN_NAME() + "}，状态：{"
					+ userStatus + "}");

			if (userStatus.equals("00")) {
				logger.debug("用户{" + tempUserPO.getLOGIN_NAME() + "}未被审核");
				throw new NotAuditAccountException();
			}
			if (userStatus.equals("10")) {
				logger.debug("用户{" + tempUserPO.getLOGIN_NAME() + "}已被禁用");
				throw new BannedAccountException();
			}

			HttpSession session = getSession();

			UserSession userSession = new UserSession();
			userSession.setUserPO(tempUserPO);
			session.setAttribute(Constants.USER_SESSION_KEY, userSession);
			session.setAttribute(Constants.USER_ID, tempUserPO.getUUID());

			if (session.getAttribute("USER_SESSION_KEY") == null) {
				logger.info("超过数量限制");
				throw new NumberLimitException();
			}

			logger.debug("用户认证全部通过，可以登录");

			String userId = tempUserPO.getUUID();
			String lastAccessIP = StringUtil.string2DefVal(tempUserPO
					.getLAST_ACCESS_IP());
			Date lastLoginDate = tempUserPO.getLAST_ACCESS_DATETIME();
			lastLoginDate = lastLoginDate == null ? Calendar.getInstance()
					.getTime() : lastLoginDate;
			String lastLoginDateStr = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss").format(lastLoginDate);
			logger.debug("取出用户上次的访问IP:[" + lastAccessIP + "]和访问时间：["
					+ lastLoginDateStr + "]存入session");

			session.setAttribute(Constants.LAST_ACCESS_IP, lastAccessIP);
			session.setAttribute(Constants.LAST_ACCESS_DATETIME,
					lastLoginDateStr);
			
			String accessIP = request.getRemoteAddr();
			Date loginDate = Calendar.getInstance().getTime();
			String loginDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
					.format(loginDate);
			logger.debug("更新用户的访问IP:[" + accessIP + "]和访问时间：[" + loginDateStr
					+ "]");

			Map editMap = new HashMap();
			editMap.put("UUID", userId);
			editMap.put("LAST_ACCESS_IP", accessIP);
			editMap.put("LAST_ACCESS_DATETIME", loginDate);
			iUserService.updateSysUserByMap(editMap);

			// 保存登录用户的sessionId
			SessionRecorder.put(userId, session.getId()); 
			sysLoginLogService.log(SysLoginLogPO.NORMAL_LOGIN_TYPE, accessIP,
					tempUserPO);
			return "success";
		}
		//logger.info("用户名或密码错误");
		throw new BadUserNameOrPassWordException();
	}
	

	
	/**
	 * 
	 * @Title: doLogout
	 * @Description: 退出系统
	 * 作者：盛家龙
	 * @param 
	 * @return String
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public String logout() throws Exception {
		try {
			HttpSession session = ServletActionContext.getRequest().getSession(false);
			if ((session != null)
					&& (session.getAttribute("USER_SESSION_KEY") != null)) {
				UserPO userPO = ((UserSession) session
						.getAttribute("USER_SESSION_KEY")).getUserPO();
				
				sysLoginLogService.log(SysLoginLogPO.NORMAL_LOGOUT_TYPE, request.getRemoteAddr(), userPO);
				
				Enumeration attrs = session.getAttributeNames();

				while (attrs.hasMoreElements()) {
					String str = (String) attrs.nextElement();
				
					session.removeAttribute(str);
					logger.info("移除session属性："+str);
				}
				logger.debug(userPO.getREAL_NAME() + "--注销成功！");
			}
		} catch (Exception e) {
			throw new UserInvalidException();
		}

		return "input";
	}
	
	public void vertifyIsLogin() throws Exception{
		boolean flag = false;
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if ((session != null) && (session.getAttribute("USER_SESSION_KEY") != null)) {
			flag = true;
		}
	
		PrintWriter pw = response.getWriter();
		if(flag){
			pw.write("ture");
		}else{
			pw.write("false");
		}
		if(pw != null){
			pw.close();
		}
	}
	

	/**
	 * 
	 * 重复登录处理
	 * @return
	 * @throws Exception          
	 * @author 马宁
	 * @create_time 2013-7-26 下午1:41:13
	 */
	public String publicDuplicationLogin() throws Exception{
		return "duplicationLogin";
	}
	
	// ---------- private methods ---------------
	
	/**
	 * 处理用户所在的政府是否合法
	 * 		由于需要不依赖于gov模块,此处使用反射处理
	 * 
	 * @author 马宁
	 * 创建时间: 2013-03-07 15:21
	 */
	private void handleGovLawful(String govId) throws SecurityException,
			IllegalArgumentException, ClassNotFoundException,
			NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		
		Object c002Po = getGov(govId);
		
		if (c002Po == null) {
			logger.info("政府不存在");
			throw new GovNonUseException();
		}

		Class c002PoCls = c002Po.getClass();
		Method getMa014Method = c002PoCls.getDeclaredMethod("getMa017");
		String ma017 = (String) getMa014Method.invoke(c002Po);
		if (!"1".equals(ma017)) {
			logger.info("政府已被删除");
			throw new GovNonUseException();
		}

		Method getMa008Method = c002PoCls.getDeclaredMethod("getMa008");
		String ma008 = (String) getMa008Method.invoke(c002Po);
		if (!"10".equals(ma008)) {
			logger.info("政府无效");
			throw new GovNonUseException();
		}
	}
	
	/**
	 * 获取政府对象
	 * 		由于需要不依赖于gov模块,此处使用反射处理
	 * 
	 * @author 马宁
	 * 创建时间:213-03-07 15:23
	 */
	private Object getGov(String govId) throws ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Class c002ServiceCls = Class
				.forName("com.hw.hwsafe.gov.service.impl.C002ServiceImpl");
		Method retrieveC002ByMa001Method = c002ServiceCls.getDeclaredMethod(
				"retrieveC002ByMa001", String.class);
		return retrieveC002ByMa001Method.invoke(
				ServiceBeanUtils.getBean("c002Service"), govId);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	/**
	 * 移动端登录接口
	 */
	public String mobileAuthen() throws Exception {
		
		String loginName = request.getParameter("LOGINNAME");
		String passWord = request.getParameter("PASSWORD");
		
		UserPO tempUserPO = null;
		
		if (StringUtils.isEmpty(loginName)) {
			System.out.println("请输入用户名");
			callbackDataPO = new CallbackDataPO("0", "请输入用户名");
			return JSON_DATA;
		}else if(StringUtils.isEmpty(passWord)){
			System.out.println("请输入密码");
			callbackDataPO = new CallbackDataPO("0", "请输入密码");
			return JSON_DATA;
		}else {
				userPO = new UserPO();
				userPO.setLOGIN_NAME(loginName);
				userPO.setPassword(PasswordUtil.createPassword(passWord));
				try {
					tempUserPO = this.iUserService
							.retrieveUserByLoginNameAndPwd(this.userPO);
				} catch (Exception e) {
					logger.info("用户信息获取失败");
					throw new BadUserNameOrPassWordException();
				}
		}

		if (tempUserPO != null) {

			String userStatus = tempUserPO.getUSER_STATUS();

			userStatus = userStatus == null ? "" : userStatus;

			logger.debug("用户{" + tempUserPO.getLOGIN_NAME() + "}，状态：{"
					+ userStatus + "}");

			if (userStatus.equals("00")) {
				logger.debug("用户{" + tempUserPO.getLOGIN_NAME() + "}未被审核");
				callbackDataPO = new CallbackDataPO("0","用户{" + tempUserPO.getLOGIN_NAME() + "}未被审核");
				return JSON_DATA;
			}
			if (userStatus.equals("10")) {
				logger.debug("用户{" + tempUserPO.getLOGIN_NAME() + "}已被禁用");
				callbackDataPO = new CallbackDataPO("0","用户{" + tempUserPO.getLOGIN_NAME() + "}已被禁用");
				return JSON_DATA;
			}

//			HttpSession session = getSession();
//
//			UserSession userSession = new UserSession();
//			userSession.setUserPO(tempUserPO);
//			session.setAttribute(Constants.USER_SESSION_KEY, userSession);
//			session.setAttribute(Constants.USER_ID, tempUserPO.getUUID());

//			if (session.getAttribute("USER_SESSION_KEY") == null) {
//				logger.info("超过数量限制");
//				throw new NumberLimitException();
//			}

			logger.debug("用户认证全部通过，可以登录");

//			String userId = tempUserPO.getUUID();
//			String lastAccessIP = StringUtil.string2DefVal(tempUserPO
//					.getLAST_ACCESS_IP());
//			Date lastLoginDate = tempUserPO.getLAST_ACCESS_DATETIME();
//			lastLoginDate = lastLoginDate == null ? Calendar.getInstance()
//					.getTime() : lastLoginDate;
//			String lastLoginDateStr = new SimpleDateFormat(
//					"yyyy-MM-dd HH:mm:ss").format(lastLoginDate);
//			logger.debug("取出用户上次的访问IP:[" + lastAccessIP + "]和访问时间：["
//					+ lastLoginDateStr + "]存入session");
//
//			session.setAttribute(Constants.LAST_ACCESS_IP, lastAccessIP);
//			session.setAttribute(Constants.LAST_ACCESS_DATETIME,
//					lastLoginDateStr);
//
			String accessIP = request.getRemoteAddr();
//			Date loginDate = Calendar.getInstance().getTime();
//			String loginDateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
//					.format(loginDate);
//			logger.debug("更新用户的访问IP:[" + accessIP + "]和访问时间：[" + loginDateStr
//					+ "]");

//			Map editMap = new HashMap();
//			editMap.put("UUID", userId);
//			editMap.put("LAST_ACCESS_IP", accessIP);
//			editMap.put("LAST_ACCESS_DATETIME", loginDate);
//			iUserService.updateSysUserByMap(editMap);

			// 保存登录用户的sessionId
//			SessionRecorder.put(userId, session.getId()); 
			sysLoginLogService.log(SysLoginLogPO.NORMAL_LOGIN_TYPE, accessIP,
					tempUserPO);
			
			System.out.println("验证成功");
			callbackDataPO = new CallbackDataPO("1","验证成功",1,tempUserPO);
			return JSON_DATA;
		}
		logger.info("用户名或密码错误");
		callbackDataPO = new CallbackDataPO("0","用户名或密码错误",0,null);
		return JSON_DATA;
	}
	
	/**
	 * 接口：获取当前登陆用户UserPO对象
	 * @return
	 */
	public String doGetUserPO(){
		try {
			callbackDataPO = new CallbackDataPO("1", "查询数据成功",1,null,JSONArray.fromObject(SessionUtil.getUserPO()), "S_USER");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JSON_DATA;
	}
	
	// --------- getter and setter ----------
	
	public String getCasServer() {
		return ConfConstants.CAS_SERVER;
	}

	public String getCasClient() {
		return ConfConstants.CAS_CLIENT;
	}

	public IUserService getiUserService() {
		return iUserService;
	}

	public void setiUserService(IUserService iUserService) {
		this.iUserService = iUserService;
	}

	public IC001Service getC001Service() {
		return c001Service;
	}

	public void setC001Service(IC001Service c001Service) {
		this.c001Service = c001Service;
	}

	public void setCasServer(String casServer) {
		this.casServer = casServer;
	}

	public void setCasClient(String casClient) {
		this.casClient = casClient;
	}

	public UserPO getUserPO() {
		return userPO;
	}

	public void setUserPO(UserPO userPO) {
		this.userPO = userPO;
	}
	
}
