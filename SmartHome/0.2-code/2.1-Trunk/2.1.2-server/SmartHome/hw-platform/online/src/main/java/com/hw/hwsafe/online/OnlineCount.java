/**
 * 文件名：OnlineCount.java
 *
 * 版本信息：
 * 日期：2012-8-1
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.online;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.cpnyreg.service.IC001Service;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.userinfo.UserSession;
import com.hw.hwsafe.platform.util.ServiceBeanUtils;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：OnlineCount
 * 类描述：在线统计
 * 创建人：付强
 * 创建时间：2012-8-1 上午10:55:35
 * 修改人: 马宁
 * 修改时间: 2013-01-15
 */
public class OnlineCount{
	
	/**
	 * 存储企业的登录人数统计信息
	 */
	private static Map<String, OnlineNumPO> corpOnlineMap = new HashMap<String, OnlineNumPO>();

	/**
	 * 存储政府季后的登录人数统计信息
	 */
	private static Map<String, OnlineNumPO> govOnlineMap = new HashMap<String, OnlineNumPO>();
	
	/**
	 * 在线用户名集合
	 */
	private static List<String> onlineUsernames = new ArrayList<String>(0);
	
	private static IC001Service c001Service = (IC001Service) ServiceBeanUtils.getBean("c001Service");
	
	private static Logger logger=Logger.getLogger(OnlineCount.class);
	

	/**
	 * 函 数 名：doUser
	 * 功能描述：统计
	 * 输入参数：
	 * @param loginFlag 操作标识，true登录，false注销
	 * 空格后跟着对该参数的描述.)
	 * 创建人：付强
	 * 创建时间：2012-8-1 上午10:44:33
	 */
	public static boolean doUser(UserSession userSession, boolean loginFlag) {

		UserPO userPo = userSession.getUserPO();

		if (isAdmin(userPo)) {
			// 如果是系统管理员允许登录或注销操作，不做登录统计和限定。
			if (loginFlag) {
				logger.debug("管理员登录！");
			} else {
				logger.debug("管理员注销！");
			}
			return true;
		}

		if (loginFlag && hasLogined(userPo)) {
			logger.debug(userPo.getLOGIN_NAME() + "再次登录");
			return true;
		}

		return hasCorpId(userPo)
				? isGovType(userPo)
						? handleGovUser(userPo, loginFlag)
						: handleCorpUser(userPo, loginFlag)
				: false;
	}
	
	// ---------------- private methods ------------------
	
	/**
	 * 处理政府用户
	 * 
	 * @author 马宁
	 */
	private static boolean handleGovUser(UserPO userPo, boolean loginFlag){
		String govId = userPo.getORGAN_UUID();
		return hasGovLogined(govId)
				? handleGovUserHasInited(userPo, loginFlag)
				: handleGovUserHasNotInited(userPo, loginFlag);
	}
	
	/**
	 * 处理已经初始化的政府用户
	 * 
	 * @author 马宁
	 */
	private static boolean handleGovUserHasInited(UserPO userPo, boolean loginFlag){
		return loginFlag
				? govUserLogin(userPo)
				: govUserLogout(userPo);
	}
	
	/**
	 * 政府用户登录
	 * 
	 * @author 马宁
	 */
	private static boolean govUserLogin(UserPO userPo) {
		String govId = userPo.getORGAN_UUID();
		String loginName = userPo.getLOGIN_NAME();
		OnlineNumPO govOnlineNumPo = govOnlineMap.get(govId);
		if (govOnlineNumPo.getMaxOnlineNum() 
				> govOnlineNumPo.getOnlineNum()) {
			govOnlineNumPo.setOnlineNum(
					govOnlineNumPo.getOnlineNum() + 1);
			onlineUsernames.add(loginName);
			logger.debug("政府标识：" + govId + "有人员(" + loginName + "登录！");
			return true;
		}
		govOnlineNumPo.setOnlineNum(govOnlineNumPo.getOnlineNum() + 1);
		logger.info("政府标识：" + govId + "已经达到同时在线人数上限，不允许再有人员登录！");
		return false;
	}
	
	/**
	 * 政府用户登出
	 * 
	 * @author 马宁
	 */
	private static boolean govUserLogout(UserPO userPo) {
		String govId = userPo.getORGAN_UUID();
		String loginName = userPo.getLOGIN_NAME();
		OnlineNumPO govOnlineNumPo = govOnlineMap.get(govId);
		if (govOnlineNumPo.getOnlineNum() >= 1) {
			govOnlineNumPo.setOnlineNum(
					govOnlineNumPo.getOnlineNum() - 1);
			onlineUsernames.remove(loginName);
			logger.debug("政府标识：" + govId + "有人员(" + loginName + ")注销！");
			return true;
		}
		logger.info("政府标识：" + govId + "人员在线统计出错！");
		return false;
	}
	
	/**
	 * 处理没有初始化的政府用户
	 * 
	 * @author 马宁
	 */
	private static boolean handleGovUserHasNotInited(UserPO userPo,
			boolean loginFlag) {
		if (loginFlag) {
			return initGovOnlineData(userPo);
		}
		logger.debug("政府标识：" + userPo.getORGAN_UUID() + "没有人员登录，不需要注销！");
		return false;
	}
	
	/**
	 * 初始化政府在线数据
	 * 
	 * @author 马宁
	 */
	private static boolean initGovOnlineData(UserPO userPo) {
		String govId = userPo.getORGAN_UUID();
		String loginName = userPo.getLOGIN_NAME();
		Integer govLimitLoginCount = 0;
		try {
			govLimitLoginCount = getGovLimitLoginCount(govId);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("获取政府在线限制人数失败");
			return false;
		} 
		if (govLimitLoginCount != null && govLimitLoginCount > 0) {
			OnlineNumPO govOnlineNumPo = new OnlineNumPO();
			govOnlineNumPo.setPrincipalCode(govId);
			govOnlineNumPo.setOnlineNum(1);
			govOnlineNumPo.setMaxOnlineNum(govLimitLoginCount);
			govOnlineMap.put(govId, govOnlineNumPo);
			onlineUsernames.add(loginName);
			logger.debug("政府标识：" + govId + "初次有人员登录！");
			return true;
		}
		logger.info("政府标识：" + govId + "最大同时在线访问人数小于1，不允许登录！");
		return false;
	}
	
	/**
	 * 获取政府限制登录人数
	 * 		由于需要与gov模块解耦,此处采用反射的方式实现
	 * @param govId - 政府标识
	 * 
	 * @author 马宁
	 */
	@SuppressWarnings("unchecked")
	private static Integer getGovLimitLoginCount(String govId)
			throws Exception {
		
		Class c002ServiceCls = Class
				.forName("com.hw.hwsafe.gov.service.impl.C002ServiceImpl");
		Method retrieveC002ByMa001Method = c002ServiceCls.getDeclaredMethod(
				"retrieveC002ByMa001", String.class);
		Object c002Po = retrieveC002ByMa001Method.invoke(
				ServiceBeanUtils.getBean("c002Service"), govId);

		if(c002Po == null){
			return 0;
		}
		
		Class c002PoCls = c002Po.getClass();
		Method getMa014Method = c002PoCls.getDeclaredMethod("getMa014");
		return (Integer) getMa014Method.invoke(c002Po);
	}
	
	/**
	 * 处理企业用户
	 * 
	 * @author 马宁
	 */
	private static boolean handleCorpUser(UserPO userPo, boolean loginFlag) {
		String corpId = userPo.getORGAN_UUID();
		return hasCorpLogined(corpId)
				? handleCorpUserHasInited(userPo, loginFlag)
				: handleCorpUserHasNotInited(userPo, loginFlag);
	}
	
	/**
	 * 处理已经初始化的企业用户
	 * 
	 * @author 马宁
	 */
	private static boolean handleCorpUserHasInited(UserPO userPo, boolean loginFlag){
		return loginFlag
				? corpUserLogin(userPo)
				: corpUserLogout(userPo);
	}
	
	/**
	 * 企业用户登录
	 * 
	 * @author 马宁
	 */
	private static boolean corpUserLogin(UserPO userPo) {
		String corpId = userPo.getORGAN_UUID();
		String loginName = userPo.getLOGIN_NAME();
		OnlineNumPO corOnlineNum = corpOnlineMap.get(corpId);
//		if (corOnlineNum.getMaxOnlineNum() > corOnlineNum.getOnlineNum()) {
		if (getMaxCorpOnlineNum(corpId) > corOnlineNum.getOnlineNum()) {
			corOnlineNum.setOnlineNum(corOnlineNum.getOnlineNum() + 1);
			onlineUsernames.add(loginName);
			logger.debug("企业代码：" + corpId + "有人员(" + loginName + "登录！");
			return true;
		}
		corOnlineNum.setOnlineNum(corOnlineNum.getOnlineNum() + 1);
		logger.info("企业代码：" + corpId + "已经达到同时在线人数上限，不允许再有人员登录！");
		return false;
	}
	
	/**
	 * 获取企业最大同时在先数
	 * 
	 * @author 马宁
	 */
	private static int getMaxCorpOnlineNum(String corpId) {
		int result = 0;
		try {
			result = c001Service.retrieveMa009ByMa001(corpId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 企业用户登出
	 * 
	 * @author 马宁
	 */
	private static boolean corpUserLogout(UserPO userPo) {
		String corpId = userPo.getORGAN_UUID();
		String loginName = userPo.getLOGIN_NAME();
		OnlineNumPO corOnlineNum = corpOnlineMap.get(corpId);
		if (corOnlineNum.getOnlineNum() >= 1) {
			corOnlineNum.setOnlineNum(corOnlineNum.getOnlineNum() - 1);
			onlineUsernames.remove(loginName);
			logger.debug("企业代码：" + corpId + "有人员(" + loginName + ")注销！");
			return true;
		}
		logger.info("企业代码：" + corpId + "人员在线统计出错！");
		return false;
	}
	
	/**
	 * 处理没有初始化的企业用户
	 * 
	 * @author 马宁
	 */
	private static boolean handleCorpUserHasNotInited(UserPO userPo,
			boolean loginFlag) {
		if (loginFlag) {
			return initCorpOnlineData(userPo);
		}
		logger.debug("企业代码：" + userPo.getORGAN_UUID() + "没有人员登录，不需要注销！");
		return false;
	}
	
	/**
	 * 初始化企业在线数据
	 * 
	 * @author 马宁
	 */
	private static boolean initCorpOnlineData(UserPO userPo) {
		String corpId = userPo.getORGAN_UUID();
		String loginName = userPo.getLOGIN_NAME();
		Integer coprLimitLoginCount = 0;
		try {
			C001PO c001Po = c001Service.retrieveC001ByMA001(corpId);
			if (c001Po != null) {
				coprLimitLoginCount = c001Po.getMA009();
			} else {
				logger.debug("企业代码：" + corpId + "未找到对应企业信息！");
				return false;
			}
		} catch (Exception e) {
			logger.debug("企业Service获取失败！");
			e.printStackTrace();
		}
		if (coprLimitLoginCount > 0) {
			OnlineNumPO corOnlineNumPO = new OnlineNumPO();
			corOnlineNumPO.setPrincipalCode(corpId);
			corOnlineNumPO.setOnlineNum(1);
			corOnlineNumPO.setMaxOnlineNum(coprLimitLoginCount);
			corpOnlineMap.put(corpId, corOnlineNumPO);
			onlineUsernames.add(loginName);
			logger.debug("企业代码：" + corpId + "初次有人员登录！");
			return true;
		}
		logger.info("企业代码：" + corpId + "最大同时在线访问人数小于1，不允许登录！");
		return false;
	}
	
	/**
	 * 判断是否已经登录
	 * @param userPo
	 * 
	 * @author 马宁
	 */
	private static boolean hasLogined(UserPO userPo){
		return onlineUsernames.contains(userPo.getLOGIN_NAME());
	}
	
	/**
	 * 判断用户是否政府用户
	 * @param userSession
	 */
	private static boolean isGovType(UserPO userPo){
		return "GOV".equals(userPo.getUSER_TYPE());
	}
	
	/**
	 * 判断是否管理员
	 * @param userPo
	 * 
	 * @author 马宁
	 */
	private static boolean isAdmin(UserPO userPo){
		return userPo!=null 
				&& ("1".equals(userPo.getIS_ADMIN())
						|| "0".equals(userPo.getIS_ADMIN()))
				&& "SYS".equals(userPo.getUSER_TYPE());
	}
	
	/**
	 * 判断是否拥有corpId
	 * @param userPo
	 * 
	 * @author 马宁
	 */
	private static boolean hasCorpId(UserPO userPo){
		return userPo!=null 
				&& userPo.getCorpid()!=null 
				&& !"".equals(userPo.getCorpid());
	}
	
	/**
	 * 判断政府是否已经有用户登录
	 * 
	 * @param govId
	 *            - 政府标识
	 * 
	 * @author 马宁
	 */
	private static boolean hasGovLogined(String govId) {
		return govOnlineMap.get(govId) != null;
	}
	
	/**
	 * 判断企业是否已经有用户登录
	 * 
	 * @param corpId
	 *            - 企业标识
	 * 
	 * @author 马宁
	 */
	private static boolean hasCorpLogined(String corpId) {
		return corpOnlineMap.get(corpId) != null;
	}
}

