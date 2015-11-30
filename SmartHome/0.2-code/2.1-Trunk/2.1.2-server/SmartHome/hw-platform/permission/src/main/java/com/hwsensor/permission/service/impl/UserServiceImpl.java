/**
 * 文件名：SysUserServiceImpl.java
 *
 * 版本信息：
 * 日期：2012-10-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.sf.json.JSONArray;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.hw.hwsafe.javamail.outer.ISendMail;
import com.hw.hwsafe.javamail.po.MailPO;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.PasswordUtil;
import com.hw.hwsafe.utils.StringUtil;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.action.UserAction;
import com.hwsensor.permission.dao.IUserDao;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.pojo.SysRolePermPO;
import com.hwsensor.permission.pojo.SysUserRolePO;
import com.hwsensor.permission.service.IDepartmentService;
import com.hwsensor.permission.service.ISysModuleService;
import com.hwsensor.permission.service.ISysOperationService;
import com.hwsensor.permission.service.ISysResourceService;
import com.hwsensor.permission.service.ISysRolePermService;
import com.hwsensor.permission.service.ISysRoleService;
import com.hwsensor.permission.service.ISysUserRoleService;
import com.hwsensor.permission.service.IUserService;
import com.hwsensor.permission.utils.PermiFilter;



/**
 * 项目名称：framework
 * 类名称：SysUserServiceImpl
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-22 下午1:47:33
 */
@SuppressWarnings("unchecked")
public class UserServiceImpl extends BaseServiceImpl implements IUserService {

	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private IUserDao sysUserDao;
	
	@Autowired
	private ISysModuleService sysModuleService;
	
	@Autowired
	private ISysOperationService sysOperationService;
	
	@Autowired
	private ISysResourceService sysResourceService;
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	@Autowired
	private ISysRolePermService sysRolePermService;
	
	@Autowired
	private IDepartmentService departmentService;
	@Autowired
	private ISendMail mailService;
	

	@Override
	public Integer insertSysUser(UserPO sysUserPO) throws SystemException {
		return sysUserDao.insertSysUser(sysUserPO);
	}

	@Override
	public Integer updateSysUser(UserPO sysUserPO) throws SystemException {
		return sysUserDao.updateSysUser(sysUserPO);
	}

	@Override
	public Integer updateSysUserByMap(Map map) throws SystemException {
		return sysUserDao.updateSysUserByMap(map);
	}

	@Override
	public Integer deleteSysUser(UserPO sysUserPO) throws SystemException {
		return sysUserDao.deleteSysUser(sysUserPO);
	}

	@Override
	public List<UserPO> retrieveByCondition(Map map) throws SystemException {
		return sysUserDao.retrieveByCondition(map);
	}

	@Override
	public UserPO retrieveSysUserByMap(Map map) throws SystemException {
		return sysUserDao.retrieveSysUserByMap(map);
	}

	@Override
	public UserMessageData insertSysUserBP(UserAction sysUserAction)
			throws SystemException {
		UserMessageData messageData = new UserMessageData();
		try {
			UserPO sysUserPO = sysUserAction.getSysUserPO();

			UserPO userPO = SessionUtil.getUserPO();

			String userId = userPO.getId();

			String orgId = sysUserPO.getORGAN_UUID();// 用户所在企业Id

			String newUserIsAdmin = sysUserPO.getIS_ADMIN();

			if (StringUtil.string2DefVal(newUserIsAdmin, "0").equals("1")) {
				Map numMap = new HashMap();

				numMap.put("ORGAN_UUID", orgId);
				numMap.put("IS_ADMIN", "1");
				List<UserPO> adminList = retrieveByCondition(numMap);

				if (adminList != null && adminList.size() > 0) {
					logger.info("本组织下已经存在一个管理员，不能再次添加");
					messageData.set(Constants.MSG_WARNING, "已经存在管理员用户，不能再次添加");
					return messageData;
				}

			}

			Date curDate = DateTimeUtils.getCurFormatDate();

			String UUID = UUIDGenerater.getUUID();
			sysUserPO.setUUID(UUID);
			sysUserPO.setCREATOR(userId);
			sysUserPO.setCREATE_DATE(curDate);

			String pwdStr = sysUserPO.getPASSWORD();
			String md5Pwd = PasswordUtil.createPassword(pwdStr);

			sysUserPO.setPASSWORD(md5Pwd);

			insertSysUser(sysUserPO);
			logger.info("添加成功");
			messageData.set(Constants.MSG_OK, "添加成功！", Constants.SERVER_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("添加失败");
			// sysUserAction.getMessage().set(Constants.MSG_ERROR, "添加失败！",
			// Constants.SERVER_MSG);
			messageData.set(Constants.MSG_ERROR, "添加失败！", Constants.SERVER_MSG);
			// throw new SystemException(e.getMessage());
		}
		// sysUserAction.getMessage().set(Constants.MSG_INFO, "添加成功！",
		// Constants.SERVER_MSG);
		return messageData;
	}

	
	@Override
	public UserMessageData updateSysUserBP(UserAction sysUserAction)
			throws SystemException {
		UserMessageData messageData = new UserMessageData();
		try {
			UserPO sysUserPO = sysUserAction.getSysUserPO();

			UserPO oldSysUserPO = retrieveSysUserBP(sysUserAction);

			UserPO userPO = SessionUtil.getUserPO();

			String userId = userPO.getId();

			Date curDate = DateTimeUtils.getCurFormatDate();

			// oldSysUserPO.setLOGIN_NAME(sysUserPO.getLOGIN_NAME());
			oldSysUserPO.setREAL_NAME(sysUserPO.getREAL_NAME());
			oldSysUserPO.setAGE(sysUserPO.getAGE());
			oldSysUserPO.setSEX(sysUserPO.getSEX());
			oldSysUserPO.setMOBILE_NUMBER(sysUserPO.getMOBILE_NUMBER());
			oldSysUserPO.setEMAIL(sysUserPO.getEMAIL());
			oldSysUserPO.setID_CARD(sysUserPO.getID_CARD());
			oldSysUserPO.setUSER_STATUS(sysUserPO.getUSER_STATUS());
			oldSysUserPO.setMOBILE_DEVICE_ACCESS(sysUserPO
					.getMOBILE_DEVICE_ACCESS());
			oldSysUserPO.setOUTER_NET_ACCESS(sysUserPO.getOUTER_NET_ACCESS());
			oldSysUserPO.setEXT1(sysUserPO.getEXT1());
			oldSysUserPO.setEXT2(sysUserPO.getEXT2());
			oldSysUserPO.setEXT3(sysUserPO.getEXT3());

			oldSysUserPO.setORGAN_UUID(sysUserPO.getORGAN_UUID());
			oldSysUserPO.setEMPLOYEE_NUM(sysUserPO.getEMPLOYEE_NUM());

			oldSysUserPO.setEDITOR(userId);
			oldSysUserPO.setMODIFIED_DATE(curDate);

			updateSysUser(oldSysUserPO);

			messageData.set(Constants.MSG_OK, "修改成功！", Constants.SERVER_MSG);
		} catch (Exception e) {
			e.printStackTrace();
			messageData.set(Constants.MSG_ERROR, "修改失败！", Constants.SERVER_MSG);
		}
		return messageData;
	}

	@Override
	public Map deleteSysUserBP(Map map) throws SystemException {
		Map rtnMap = new HashMap();

		String ids = map.get("ids").toString();
		String[] idsArr = ids.split(",");

		map.clear();

		map.put("ids", idsArr);

		sysUserDao.deleteUserAndRelatedByMap(map);

		return rtnMap;
	}

	/**
	 * 更新系统用户
	 */
	@Override
	public UserMessageData updateSysUserInfo(UserPO sysUserPO)
			throws SystemException {
		UserMessageData message = new UserMessageData();
		sysUserDao.updateUserInfo(sysUserPO);
		message.set(Constants.MSG_OK, "已经成功修改！");
		return message;
	}

	/**
	 * 修改用户密码信息
	 */
	@Override
	public UserMessageData updateSysUserPassWord(UserPO userinfo)
			throws SystemException {
		UserMessageData message = new UserMessageData();
		userinfo.setPassword(PasswordUtil.createPassword(userinfo.getPassword()));
		int i = sysUserDao.retrieveUserByUserInfoPO(userinfo);
		if (i == 1) {
			// 如果该用户记录存在，更新该用户密码
			userinfo.setPASSWORD(PasswordUtil.createPassword(userinfo
					.getNewpassword()));
			sysUserDao.updateUserInfo(userinfo);
			message.set(Constants.MSG_OK, "修改成功", "信息");
		} else {
			message.set(Constants.MSG_ERROR, "原密码错误", "错误");
		}
		return message;
	}

	/**
	 * 根据系统用户ID查询用户信息
	 */
	@Override
	public UserPO retrieveUserInfoByUserID(String userID)
			throws SystemException {
		return sysUserDao.retrieveUserInfoByUserID(userID);
	}

	@Override
	public UserPO retrieveSysUserBP(UserAction sysUserAction)
			throws SystemException {
		UserPO sysUserPO = sysUserAction.getSysUserPO();
		String UUID = sysUserPO.getUUID();

		Map map = new HashMap();
		map.put("UUID", UUID);

		UserPO userPO = retrieveSysUserByMap(map);
		if (userPO == null) {
			sysUserAction.getMessage().set(Constants.MSG_ERROR,
					"信息已不存在，请刷新后重试!", Constants.SERVER_MSG);
			throw new SystemException("信息已不存在，请刷新后重试!");
		}

		return userPO;
	}

	@Override
	public void doAssignRole(UserAction sysUserAction) throws SystemException {
		HttpServletRequest request = sysUserAction.getRequest();
		UserPO userPO = SessionUtil.getUserPO();

		String operUserId = userPO.getId();// 操作用户id
		String corpId = userPO.getCorpid();// 操作用户所在系统编号

		String userId = request.getParameter("userId");

		Map map = new HashMap();
		map.put("UUID", userId);
		map.put("operUserId", operUserId);
		map.put("corpId", corpId);

		// 根据Id,查询用户
		UserPO sysUserPO = retrieveSysUserByMap(map);

		String userType = sysUserPO.getUSER_TYPE();
		String isAdmin = sysUserPO.getIS_ADMIN();

		map.put("USER_TYPE", userType);

		map.put("isAdmin", isAdmin);

		// 查询权限树
		JSONArray jsonArray = buildOperTree(map);

		// 查询角色

		List roleList = queryRolesByMap(map);

		// 查询当前用户已拥有角色

		List<Map<String, String>> haveRoles = queryHaveRoles(map);

		List<String> haveRoleList = new ArrayList<String>();

		for (Map<String, String> haveRole : haveRoles) {
			haveRoleList.add(haveRole.get("ROLE_UUID"));
		}

		map.put("haveRoles", haveRoleList);

		// 查询当前用户拥有的操作权限
		String haveOpers = queryHaveOpers(map);

		sysUserAction.setOperTree(jsonArray.toString());
		sysUserAction.setRoleList(roleList);
		sysUserAction.setHaveRoles(haveRoles);
		sysUserAction.setHaveRoleList(haveRoleList);
		sysUserAction.setHaveOpers(haveOpers);
		sysUserAction.setSysUserPO(sysUserPO);
	}

	@Override
	public void saveAssignRole(UserAction sysUserAction) throws SystemException {
		try {
			HttpServletRequest request = sysUserAction.getRequest();

			String roles = request.getParameter("roles");

			roles = roles == null ? "" : roles;

			String userId = request.getParameter("userId");
			userId = userId == null ? "" : userId;

			UserPO userPO = SessionUtil.getUserPO();

			String operUserId = userPO.getId();
			Date curDate = DateTimeUtils.getCurFormatDate();

			// 1.删除原来的用户角色
			Map map = new HashMap();
			map.put("USER_UUID", userId);
			sysUserRoleService.deleteSysUserRoleByMap(map);
			// 2.重新添加新的用户角色
			String[] roleIds = roles.split(",");
			for (int i = 0; i < roleIds.length; i++) {
				String roleId = roleIds[i];
				if (roleId.isEmpty()) {
					continue;
				}
				SysUserRolePO sysUserRolePO = new SysUserRolePO();
				String UUID = UUIDGenerater.getUUID();
				sysUserRolePO.setUUID(UUID);
				sysUserRolePO.setUSER_UUID(userId);
				sysUserRolePO.setROLE_UUID(roleId);
				sysUserRolePO.setCREATOR(operUserId);
				sysUserRolePO.setCREATE_DATE(curDate);

				sysUserRoleService.insertSysUserRole(sysUserRolePO);
			}
		} catch (Exception e) {
			sysUserAction.getMessage().set(Constants.MSG_ERROR, "保存失败!",
					Constants.SERVER_MSG);
			throw new SystemException(e.getMessage());
		}
		sysUserAction.getMessage().set(Constants.MSG_OK, "保存成功!",
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public JSONArray buildOperTree(Map map) throws SystemException {
		Map disabledMap = new HashMap();

		JSONArray jsonArray = new JSONArray();

		String userType = (String) (map.get("USER_TYPE") == null ? "" : map
				.get("USER_TYPE"));

		String curUserType = SessionUtil.getUserType();
		// 1.查询所有有效的目录
		Map moduleMap = new HashMap();
		moduleMap.put("IS_VALID", "10");
		moduleMap.put("USER_TYPE", map.get("USER_TYPE"));

		// 先取模块
		disabledMap = PermiFilter.getDisabledMap(curUserType, userType,
				PermiFilter.MODULE_TYPE);

		moduleMap.putAll(disabledMap);

		moduleMap.put("MENU_TYPE", "10");
		List<SysModulePO> modules = sysModuleService
				.retrieveByCondition(moduleMap);

		// 再取页面
		disabledMap = PermiFilter.getDisabledMap(curUserType, userType,
				PermiFilter.PAGE_TYPE);

		moduleMap.putAll(disabledMap);
		moduleMap.put("MENU_TYPE", "20");
		List<SysModulePO> pages = sysModuleService
				.retrieveByCondition(moduleMap);

		List<SysModulePO> sysModulePOs = new ArrayList<SysModulePO>();

		sysModulePOs.addAll(modules);
		sysModulePOs.addAll(pages);

		// 2.将所有有效目录构成目录树
		JSONArray moduleArr = sysResourceService
				.buildModuleTreeByList(sysModulePOs);

		jsonArray.addAll(moduleArr);

		for (int i = 0; i < sysModulePOs.size(); i++) {
			SysModulePO modulePO = sysModulePOs.get(i);

			String moduleUUID = modulePO.getUUID();// 目录主键
			String moduleType = modulePO.getMENU_TYPE();// 类型

			if (moduleType == null || moduleType.equals("10")) {
				continue;
			}

			// 5.查询每个页面下所有有效的操作
			Map operMap = new HashMap();
			operMap.put("MENU_UUID", moduleUUID);
			operMap.put("IS_VALID", "10");

			disabledMap = PermiFilter.getDisabledMap(curUserType, userType,
					PermiFilter.OPER_TYPE);

			operMap.putAll(disabledMap);

			List<SysOperationPO> sysOperationPOs = sysOperationService
					.retrieveByCondition(operMap);

			// 6.将每个页面下有效的操作构成操作树

			JSONArray operArr = sysResourceService
					.buildOperTreeByList(sysOperationPOs);

			jsonArray.addAll(operArr);
		}

		return jsonArray;
	}

	@Override
	public String queryHaveOpers(Map map) throws SystemException {

//		String haveOpers = "";
		StringBuffer sb = new StringBuffer("");
		List roles = (List) map.get("haveRoles");

		for (int i = 0; i < roles.size(); i++) {
			String roleId = (String) roles.get(i);
			if (roleId.isEmpty()) {
				continue;
			}
//			haveOpers += queryHaveOpersByRoleID(roleId) + "|";
			sb.append(queryHaveOpersByRoleID(roleId))
				.append("|");

		}
		
		String haveOpers = sb.toString();
		if (haveOpers.endsWith("|")) {
			haveOpers = haveOpers.substring(0, haveOpers.lastIndexOf("|"));
		}

		return haveOpers;
	}

	@Override
	public String queryHaveOpersByRoleID(String id) throws SystemException {
//		String haveOpers = "";
		StringBuffer sb = new StringBuffer("");
		Map map = new HashMap();
		map.put("ROLE_UUID", id);
		List<SysRolePermPO> rolePerms = sysRolePermService
				.retrieveByCondition(map);
		for (SysRolePermPO sysRolePermPO : rolePerms) {
			String moduleId = sysRolePermPO.getMODULE_UUID();
			String operId = sysRolePermPO.getOPERA_UUID();

			String ID = (moduleId == null || moduleId.isEmpty()) 
					? ((operId == null || operId.isEmpty()) 
							? "" 
							: operId) 
					: moduleId;

			if (!ID.isEmpty()) {
//				haveOpers += ID + "|";
				sb.append(ID)
					.append("|");
			}
		}
		
		String haveOpers = sb.toString();
		if (haveOpers.endsWith("|")) {
			haveOpers = haveOpers.substring(0, haveOpers.lastIndexOf("|"));
		}
		return haveOpers;
	}

	@Override
	public List<Map<String, String>> queryHaveRoles(Map map)
			throws SystemException {
		Map uRMap = new HashMap();
		uRMap.put("USER_UUID", map.get("UUID"));

		List<Map<String, String>> uRList = null;

		uRList = sysUserRoleService.retrieveRolesByMap(uRMap);

		List roleList = new ArrayList();
		for (Map<String, String> roleMap : uRList) {
			roleList.add(roleMap);
		}
		return roleList;
	}

	@Override
	public List queryRolesByMap(Map map) throws SystemException {
		Map roleMap = new HashMap();
		roleMap.put("USER_TYPE", map.get("USER_TYPE"));
		roleMap.put("ORGAN_UUID", map.get("corpId"));

		return sysRoleService.retrieveByCondition(roleMap);
	}

	@Override
	public Map updatePassWord(Map map) throws SystemException {
		String pwdStr = "";

		Calendar c = Calendar.getInstance();

		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		String dayStr = day < 10 ? "0" + day : String.valueOf(day);
		String hourStr = hour < 10 ? "0" + hour : String.valueOf(hour);
		String minuteStr = minute < 10 ? "0" + minute : String.valueOf(minute);
		String secondStr = second < 10 ? "0" + second : String.valueOf(second);

		pwdStr = dayStr + hourStr + minuteStr + secondStr;
		
		// ****************************************************
		//     马宁于20130819修改,暂时将重置后的密码定为123456
		
		pwdStr = "123456";
		
		// ****************************************************

		String md5Pwd = PasswordUtil.createPassword(pwdStr);
		map.put("PASSWORD", md5Pwd);
		sysUserDao.updatePassWordBy(map);

		sendEmails((String[]) map.get("idArr"), pwdStr);

		return null;
	}

	@Override
	public String buildDeptTree(Map map) throws SystemException {
		String deptTree = "";
		try {
			deptTree = departmentService.buildTree(map);
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
		return deptTree;
	}

	@Override
	public UserPO retrieveUserByLoginName(String loginName)
			throws SystemException {
		return sysUserDao.retrieveUserByLoginName(loginName);
	}

	@Override
	public UserPO retrieveUserByLoginNameAndPwd(UserPO userPO)
			throws SystemException {

		return sysUserDao.retrieveUserByLoginNameAndPwd(userPO);
	}

	@Override
	public UserPO retrieveSysUserByUserID(String UUID) throws SystemException {

		return sysUserDao.retrieveSysUserByUserID(UUID);
	}

	@Override
	public List<UserPO> retrieveAllSysUser() throws SystemException {
		return sysUserDao.retrieveAllSysUser();
	}

	@Override
	public String getSysAdminOrgId() throws SystemException {
		return sysUserDao.getSysAdminOrgId();
	}

	@Override
	public List<Map<String, Object>> queryUsersList(Map map)
			throws SystemException {
		return sysUserDao.queryUsersList(map);
	}

	/**
	 * 向邮件表中插入要发送的邮件信息，以便于定时发送邮件 insertEmails
	 * 
	 * @param ids
	 * @param passWord
	 * @return
	 */
	private void sendEmails(String[] ids, String passWord)
			throws SystemException {

		String mailHeader = "密码重置成功-来自安监系统！";
		StringBuffer content = new StringBuffer();

		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];

			UserPO user = retrieveSysUserByUserID(id);
			content.append("亲爱的")
			       .append(user.getREAL_NAME())
			       .append("：您好,<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;")
			       .append("你的密码已重置为：")
			       .append(passWord)
			       .append(",请立即登录系统进行修改，以确保安全！");
			  MailPO mail = new MailPO();
			  mail.setContent(content.toString());
			  mail.setTitle(mailHeader);
			  mail.setMode("html");
			  List<String> addrList = new ArrayList<String>();
			  addrList.add(user.getEMAIL());
			  mail.setReceiveMailList(addrList);
			  mailService.sendOuterMail(mail);
		}
	}

	
	/**
	 * @param userPO
	 * @return
	 * @throws SystemException
	 */
	public String insertUser(UserPO sysUserPO) throws Exception {
		String uuid = null;
		UserPO userPO = SessionUtil.getUserPO();
		String userId = userPO.getId();
		String orgId = sysUserPO.getORGAN_UUID();// 用户所在企业Id
		String newUserIsAdmin = sysUserPO.getIS_ADMIN();

		if (StringUtil.string2DefVal(newUserIsAdmin, "0").equals("1")) {
			Map numMap = new HashMap();

			numMap.put("ORGAN_UUID", orgId);
			numMap.put("IS_ADMIN", "1");
			List<UserPO> adminList = retrieveByCondition(numMap);

			if (adminList != null && adminList.size() > 0) {
				logger.info("本组织下已经存在一个管理员，不能再次添加");
				return uuid;
			}

		}

		Date curDate = DateTimeUtils.getCurFormatDate();

		uuid = UUIDGenerater.getUUID();
		sysUserPO.setUUID(uuid);
		sysUserPO.setCREATOR(userId);
		sysUserPO.setCREATE_DATE(curDate);

		String pwdStr = sysUserPO.getPASSWORD();
		String md5Pwd = PasswordUtil.createPassword(pwdStr);
		sysUserPO.setPASSWORD(md5Pwd);
		insertSysUser(sysUserPO);
		
		return uuid;
	}
	
	public boolean updateUser(UserPO sysUserPO) throws Exception {
		UserPO oldSysUserPO = retrieveSysUserByUserID(sysUserPO.getUUID());
		
		if(oldSysUserPO == null) {
			logger.warn("用户("+sysUserPO.getUUID()+")信息不存!");
			return false;
		}
		UserPO userPO = SessionUtil.getUserPO();

		String userId = userPO.getId();

		Date curDate = DateTimeUtils.getCurFormatDate();

		oldSysUserPO.setREAL_NAME(sysUserPO.getREAL_NAME());
		oldSysUserPO.setAGE(sysUserPO.getAGE());
		oldSysUserPO.setSEX(sysUserPO.getSEX());
		oldSysUserPO.setMOBILE_NUMBER(sysUserPO.getMOBILE_NUMBER());
		oldSysUserPO.setEMAIL(sysUserPO.getEMAIL());
		oldSysUserPO.setID_CARD(sysUserPO.getID_CARD());
		oldSysUserPO.setUSER_STATUS(sysUserPO.getUSER_STATUS());
		oldSysUserPO.setMOBILE_DEVICE_ACCESS(sysUserPO
				.getMOBILE_DEVICE_ACCESS());
		oldSysUserPO.setOUTER_NET_ACCESS(sysUserPO.getOUTER_NET_ACCESS());
		oldSysUserPO.setEXT1(sysUserPO.getEXT1());
		oldSysUserPO.setEXT2(sysUserPO.getEXT2());
		oldSysUserPO.setEXT3(sysUserPO.getEXT3());

		oldSysUserPO.setORGAN_UUID(sysUserPO.getORGAN_UUID());
		oldSysUserPO.setEMPLOYEE_NUM(sysUserPO.getEMPLOYEE_NUM());

		oldSysUserPO.setEDITOR(userId);
		oldSysUserPO.setMODIFIED_DATE(curDate);

		updateSysUser(oldSysUserPO);
		return true;
	}

}
