/**
 * 文件名：SysRoleServiceImpl.java
 *
 * 版本信息：
 * 日期：2012-10-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;
import com.hwsensor.permission.action.SysRoleAction;
import com.hwsensor.permission.dao.ISysRoleDao;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.pojo.SysRolePO;
import com.hwsensor.permission.pojo.SysRolePermPO;
import com.hwsensor.permission.pojo.SysUserRolePO;
import com.hwsensor.permission.service.ISysModuleService;
import com.hwsensor.permission.service.ISysOperationService;
import com.hwsensor.permission.service.ISysResourceService;
import com.hwsensor.permission.service.ISysRolePermService;
import com.hwsensor.permission.service.ISysRoleService;
import com.hwsensor.permission.service.ISysUserRoleService;
import com.hwsensor.permission.utils.PermiFilter;

/**
 * 项目名称：framework
 * 类名称：SysRoleServiceImpl
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-12 下午1:19:36
 */
@SuppressWarnings("unchecked")
public class SysRoleServiceImpl extends BaseServiceImpl implements
		ISysRoleService {

	@Autowired
	private ISysRoleDao sysRoleDao;
	
	@Autowired
	private ISysRolePermService sysRolePermService;
	
	@Autowired
	private ISysUserRoleService sysUserRoleService;
	
	@Autowired
	private ISysModuleService sysModuleService;
	
	@Autowired
	private ISysOperationService sysOperationService;
	
	@Autowired
	private ISysResourceService sysResourceService;

	@Override
	public Integer insertSysRole(SysRolePO sysRolePO) throws SystemException {
		return sysRoleDao.insertSysRole(sysRolePO);
	}

	@Override
	public Integer updateSysRole(SysRolePO sysRolePO) throws SystemException {
		return sysRoleDao.updateSysRole(sysRolePO);
	}

	@Override
	public Integer updateSysRoleByMap(Map map) throws SystemException {
		return sysRoleDao.updateSysRoleByMap(map);
	}

	@Override
	public Integer deleteSysRole(SysRolePO sysRolePO) throws SystemException {
		return sysRoleDao.deleteSysRole(sysRolePO);
	}

	@Override
	public Integer deleteSysRoleByMap(Map map) throws SystemException {
		return sysRoleDao.deleteSysRoleByMap(map);
	}

	@Override
	public List<SysRolePO> retrieveByCondition(Map map) throws SystemException {
		return sysRoleDao.retrieveByCondition(map);
	}

	@Override
	public SysRolePO retrieveSysRoleByMap(Map map) throws SystemException {
		return sysRoleDao.retrieveSysRoleByMap(map);
	}

	@Override
	public void insertSysRoleBP(SysRoleAction sysRoleAction)
			throws SystemException {
		try {
			UserPO userPO = SessionUtil.getUserPO();

			String ORGAN_UUID = userPO.getORGAN_UUID();// 企业或政府机构
			String CREATOR = userPO.getUUID();// 创建人

			Date CREATE_DATE = DateTimeUtils.getCurFormatDate();// 创建日期

			SysRolePO sysRolePO = sysRoleAction.getSysRolePO();// 添加的角色信息

			String UUID = UUIDGenerater.getUUID();

			sysRolePO.setUUID(UUID);
			sysRolePO.setORGAN_UUID(ORGAN_UUID);
			sysRolePO.setCREATOR(CREATOR);
			sysRolePO.setCREATE_DATE(CREATE_DATE);

			insertSysRole(sysRolePO);
		} catch (Exception e) {
			sysRoleAction.getMessage().set(Constants.MSG_ERROR, "添加失败！",
					Constants.SERVER_MSG);
			throw new SystemException(e.getMessage());
		}
		sysRoleAction.getMessage().set(Constants.MSG_OK, "添加成功！",
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public void updateSysRoleBP(SysRoleAction sysRoleAction)
			throws SystemException {
		// 取出原始角色信息
		SysRolePO oldSysRolePO = retrieveSysRoleBP(sysRoleAction);
		try {
			UserPO userPO = sysRoleAction.getSessionUserPO();

			String ORGAN_UUID = userPO.getCorpid();// 企业或政府机构
			String CREATOR = userPO.getId();// 创建人

			Date CREATE_DATE = DateTimeUtils.getCurFormatDate();// 创建日期

			SysRolePO sysRolePO = sysRoleAction.getSysRolePO();// 添加的角色信息

			oldSysRolePO.setROLE_NAME(sysRolePO.getROLE_NAME());
			oldSysRolePO.setROLE_CODE(sysRolePO.getROLE_CODE());
			oldSysRolePO.setREMARK(sysRolePO.getREMARK());

			oldSysRolePO.setORGAN_UUID(ORGAN_UUID);
			oldSysRolePO.setCREATOR(CREATOR);
			oldSysRolePO.setCREATE_DATE(CREATE_DATE);

			updateSysRole(oldSysRolePO);
		} catch (Exception e) {
			sysRoleAction.getMessage().set(Constants.MSG_ERROR, "修改失败！",
					Constants.SERVER_MSG);
			throw new SystemException();
		}
		sysRoleAction.getMessage().set(Constants.MSG_OK, "修改成功！",
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public Map deleteSysRoleBP(Map map) throws SystemException {
		Map rtnMap = new HashMap();

		String ids = map.get("ids").toString();

		String[] idsArr = ids.split(",");

		boolean flag = true;

		for (String id : idsArr) {
			if (id == null || id.isEmpty()) {
				continue;
			}

			Map checkMap = new HashMap();

			checkMap.put("UUID", id);

			Map rstMap = sysRoleDao.retrieveCountByMap(checkMap);

			if (rstMap != null && rstMap.get("MSG") != null
					&& !rstMap.get("MSG").toString().isEmpty()) {

				flag = false;
				rtnMap.put(Constants.RETURN_MSG, rstMap.get("MSG").toString());
				break;
			}

		}

		if (flag == true) {
			map.clear();
			map.put("ids", idsArr);
			sysRoleDao.deleteRoleAndRelatedByMap(map);
		}

		return rtnMap;

	}

	@Override
	public SysRolePO retrieveSysRoleBP(SysRoleAction sysRoleAction)
			throws SystemException {
		SysRolePO sysRolePO = sysRoleAction.getSysRolePO();
		String UUID = sysRolePO.getUUID();

		Map map = new HashMap();
		map.put("UUID", UUID);

		SysRolePO rolePO = retrieveSysRoleByMap(map);

		if (rolePO == null) {
			sysRoleAction.getMessage().set(Constants.MSG_ERROR,
					"信息已不存在，请刷新后重试!", Constants.SERVER_MSG);
			throw new SystemException("信息已不存在，请刷新后重试!");
		}

		return rolePO;
	}

/*	@Override
	public void buildTree(SysRoleAction sysRoleAction) throws SystemException {
		SysRolePO oldSysRolePO = retrieveSysRoleBP(sysRoleAction);
		String USER_TYPE = oldSysRolePO.getUSER_TYPE();// 角色类型
		String roleID = oldSysRolePO.getUUID();

		UserPO userPO = SessionUtil.getUserPO();

		String curUserType = userPO.getUSER_TYPE();// 当前登录用户类型
//		String corpID = userPO.getORGAN_UUID();// 企业，政府，系统ID
//		String isAdmin = userPO.getIS_ADMIN();// 当前登录用户是否管理员

		Map disabledMap = new HashMap(); // 过滤掉角色不能分配的模块，页面，操作

		Map<String, String> roleMap = new HashMap<String, String>();
		roleMap.put("ROLE_UUID", roleID);
		String haveOpers = getHaveOpers(roleMap);

		sysRoleAction.setHaveOpers(haveOpers);

		JSONArray jsonArray = new JSONArray();
		// 1.查询所有有效的目录
		Map moduleMap = new HashMap();
		moduleMap.put("MENU_TYPE", "10");
		moduleMap.put("IS_VALID", "10");
		moduleMap.put("USER_TYPE", USER_TYPE);

		// 处理过滤map
		disabledMap = PermiFilter.getDisabledMap(curUserType, USER_TYPE,
				PermiFilter.MODULE_TYPE);

		moduleMap.putAll(disabledMap);

		List<SysModulePO> sysModulePOs = sysModuleService
				.retrieveByCondition(moduleMap);

		// 2.将所有有效目录构成目录树
		JSONArray moduleArr = sysResourceService
				.buildModuleTreeByList(sysModulePOs);

		jsonArray.addAll(moduleArr);

		for (int i = 0; i < sysModulePOs.size(); i++) {
			SysModulePO modulePO = sysModulePOs.get(i);
			String moduleUUID = modulePO.getUUID();// 目录主键

			// 3.查询每个目录下的所有有效页面
			Map pageMap = new HashMap();
			pageMap.put("PARENT_UUID", moduleUUID);
			pageMap.put("MENU_TYPE", "20");
			pageMap.put("IS_VALID", "10");
			pageMap.put("USER_TYPE", USER_TYPE);

			disabledMap.clear();

			disabledMap = PermiFilter.getDisabledMap(curUserType, USER_TYPE,
					PermiFilter.PAGE_TYPE);

			pageMap.putAll(disabledMap);

			List<SysModulePO> sysPages = sysModuleService
					.retrieveByCondition(pageMap);

			// 4.将每个目录下所有有效的页面构成目录树
			JSONArray pageArr = sysResourceService
					.buildModuleTreeByList(sysPages);

			jsonArray.addAll(pageArr);

			for (int j = 0; j < sysPages.size(); j++) {
				SysModulePO pagePO = sysPages.get(j);
				String pageUUID = pagePO.getUUID();// 页面主键

				// 5.查询每个页面下所有有效的操作
				Map operMap = new HashMap();
				operMap.put("MENU_UUID", pageUUID);
				operMap.put("IS_VALID", "10");

				disabledMap.clear();

				disabledMap = PermiFilter.getDisabledMap(curUserType,
						USER_TYPE, PermiFilter.OPER_TYPE);

				pageMap.putAll(disabledMap);

				List<SysOperationPO> sysOperationPOs = sysOperationService
						.retrieveByCondition(operMap);

				// 6.将每个页面下有效的操作构成操作树

				JSONArray operArr = sysResourceService
						.buildOperTreeByList(sysOperationPOs);

				jsonArray.addAll(operArr);
			}
		}
		sysRoleAction.setOperTree(jsonArray.toString());
		sysRoleAction.setSysRolePO(oldSysRolePO);
	}
*/

	
	public List<String> queryPerms() {
		List<String> list = new ArrayList<String>();
		
		String USER_UUID = SessionUtil.getUserId();
		Map map = new HashMap();
		map.put("USER_UUID", USER_UUID);
		List<SysUserRolePO> userRoleList = sysUserRoleService.retrieveByCondition(map);

		Map m = new HashMap();
		for (int i = 0; i < userRoleList.size(); i++) {
			m.put("ROLE_UUID", userRoleList.get(i).getROLE_UUID());
			String[] ids = getHaveOpers(m).split("\\|");
			
			for (int j = 0; j < ids.length; j++) {
				if (!list.contains(ids[j]))
					list.add(ids[j]);
			}
		}
	
		return list;
	}
	
	public void buildTree(SysRoleAction sysRoleAction) throws SystemException {
		SysRolePO oldSysRolePO = retrieveSysRoleBP(sysRoleAction);
		String USER_TYPE = oldSysRolePO.getUSER_TYPE();// 角色类型
		String roleID = oldSysRolePO.getUUID();

		UserPO userPO = SessionUtil.getUserPO();

		String curUserType = userPO.getUSER_TYPE();// 当前登录用户类型
//		String corpID = userPO.getORGAN_UUID();// 企业，政府，系统ID
//		String isAdmin = userPO.getIS_ADMIN();// 当前登录用户是否管理员

		Map disabledMap = new HashMap(); // 过滤掉角色不能分配的模块，页面，操作

		Map<String, String> roleMap = new HashMap<String, String>();
		roleMap.put("ROLE_UUID", roleID);
		String haveOpers = getHaveOpers(roleMap);

		sysRoleAction.setHaveOpers(haveOpers);

		JSONArray jsonArray = new JSONArray();
		// 1.查询所有有效的目录
		Map moduleMap = new HashMap();
		moduleMap.put("MENU_TYPE", "10");
		moduleMap.put("IS_VALID", "10");
		moduleMap.put("USER_TYPE", USER_TYPE);

		// 处理过滤map
		disabledMap = PermiFilter.getDisabledMap(curUserType, USER_TYPE,
				PermiFilter.MODULE_TYPE);

		moduleMap.putAll(disabledMap);

		List<SysModulePO> sysModulePOs = sysModuleService
				.retrieveByCondition(moduleMap);

		// --------------------------------------------
		List<String> parPerms = null;
		List tmpList = null;

		if (!"SYS".equals(curUserType)) {
			parPerms = queryPerms();
			tmpList = new ArrayList();
			
			for (int i = 0; i < sysModulePOs.size(); i++)
				if (!parPerms.contains(sysModulePOs.get(i).getUUID()))
					tmpList.add(sysModulePOs.get(i));
			sysModulePOs.removeAll(tmpList);
		}

		// 2.将所有有效目录构成目录树
		JSONArray moduleArr = sysResourceService
				.buildModuleTreeByList(sysModulePOs);

		jsonArray.addAll(moduleArr);

		
		for (int i = 0; i < sysModulePOs.size(); i++) {
			SysModulePO modulePO = sysModulePOs.get(i);
			String moduleUUID = modulePO.getUUID();// 目录主键

			// 3.查询每个目录下的所有有效页面
			Map pageMap = new HashMap();
			pageMap.put("PARENT_UUID", moduleUUID);
			pageMap.put("MENU_TYPE", "20");
			pageMap.put("IS_VALID", "10");
			pageMap.put("USER_TYPE", USER_TYPE);

			disabledMap.clear();

			disabledMap = PermiFilter.getDisabledMap(curUserType, USER_TYPE,
					PermiFilter.PAGE_TYPE);

			pageMap.putAll(disabledMap);

			List<SysModulePO> sysPages = sysModuleService
					.retrieveByCondition(pageMap);

			// ----------------------------------------------
			if (!"SYS".equals(curUserType)) {
				tmpList.clear();
				for (int j = 0; j < sysPages.size(); j++)
					if (!parPerms.contains(sysPages.get(j).getUUID()))
						tmpList.add(sysPages.get(j));
				sysPages.removeAll(tmpList);
			}
			
			// 4.将每个目录下所有有效的页面构成目录树
			JSONArray pageArr = sysResourceService
					.buildModuleTreeByList(sysPages);

			jsonArray.addAll(pageArr);

			for (int j = 0; j < sysPages.size(); j++) {
				SysModulePO pagePO = sysPages.get(j);
				String pageUUID = pagePO.getUUID();// 页面主键
				
				// 5.查询每个页面下所有有效的操作
				Map operMap = new HashMap();
				operMap.put("MENU_UUID", pageUUID);
				operMap.put("IS_VALID", "10");

				disabledMap.clear();

				disabledMap = PermiFilter.getDisabledMap(curUserType,
						USER_TYPE, PermiFilter.OPER_TYPE);

				pageMap.putAll(disabledMap);

				List<SysOperationPO> sysOperationPOs = sysOperationService
						.retrieveByCondition(operMap);

				// ----------------------------
				if (!"SYS".equals(curUserType)) {
					tmpList.clear();
					for (int k = 0; k < sysOperationPOs.size(); k++)
						if (!parPerms.contains(sysOperationPOs.get(k).getUUID()))
							tmpList.add(sysOperationPOs.get(k));
					sysOperationPOs.removeAll(tmpList);
				}
				
				// 6.将每个页面下有效的操作构成操作树

				JSONArray operArr = sysResourceService
						.buildOperTreeByList(sysOperationPOs);

				jsonArray.addAll(operArr);
			}
		}
		sysRoleAction.setOperTree(jsonArray.toString());
		sysRoleAction.setSysRolePO(oldSysRolePO);
	}
	
	
	@Override
	public void roleAuthorzationBP(SysRoleAction sysRoleAction)
			throws SystemException {
		try {
			HttpServletRequest request = sysRoleAction.getRequest();

			String modules = request.getParameter("modules");
			String opers = request.getParameter("opers");
			String roleId = request.getParameter("roleId");

			UserPO userPO = SessionUtil.getUserPO();

			String userId = userPO.getId();
			String corpId = userPO.getORGAN_UUID();// 企业id

			Date curDate = DateTimeUtils.getCurFormatDate();

			Map<String, String> delMap = new HashMap<String, String>();
			delMap.put("ORGAN_UUID", corpId);
			delMap.put("ROLE_UUID", roleId);

			// 删除某企业，政府或系统下的此角色的所有模块和资源，重新添加
			sysRolePermService.deleteSysRolePermByMap(delMap);

			String[] moduleIds = (modules == null ? "" : modules).split(",");
			String[] operIds = (opers == null ? "" : opers).split(",");

			// 添加角色模块关联
			for (int i = 0; i < moduleIds.length; i++) {
				String moduleId = moduleIds[i];

				if (moduleId.isEmpty()) {
					continue;
				}
				SysRolePermPO sysRolePermPO = new SysRolePermPO();

				String UUID = UUIDGenerater.getUUID();
				sysRolePermPO.setUUID(UUID);
				sysRolePermPO.setROLE_UUID(roleId);
				sysRolePermPO.setMODULE_UUID(moduleId);
				sysRolePermPO.setORGAN_UUID(corpId);
				sysRolePermPO.setCREATE_DATE(curDate);
				sysRolePermPO.setCREATOR(userId);

				sysRolePermService.insertSysRolePerm(sysRolePermPO);
			}
			// 添加角色操作关联
			for (int i = 0; i < operIds.length; i++) {
				String operId = operIds[i];
				if (operId.isEmpty()) {
					continue;
				}
				SysRolePermPO sysRolePermPO = new SysRolePermPO();

				String UUID = UUIDGenerater.getUUID();
				sysRolePermPO.setUUID(UUID);
				sysRolePermPO.setROLE_UUID(roleId);
				sysRolePermPO.setOPERA_UUID(operId);
				sysRolePermPO.setORGAN_UUID(corpId);
				sysRolePermPO.setCREATE_DATE(curDate);
				sysRolePermPO.setCREATOR(userId);

				sysRolePermService.insertSysRolePerm(sysRolePermPO);
			}

		} catch (Exception e) {
			sysRoleAction.getMessage().set(Constants.MSG_ERROR, "保存失败！",
					Constants.SERVER_MSG);
			throw new SystemException(e.getMessage());
		}
		sysRoleAction.getMessage().set(Constants.MSG_OK, "保存成功！",
				Constants.SERVER_MSG);
		return;
	}

	@Override
	public String getHaveOpers(Map map) throws SystemException {
//		String haveOpers = "";
		StringBuffer sb = new StringBuffer("");
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
	public void assignUser(String roleId, String[] idArr)
			throws SystemException {
		String creator = SessionUtil.getUserId();
		Date creatDate = DateTimeUtils.getCurDateTime();
		for (int i = 0; i < idArr.length; i++) {
			String UUID = UUIDGenerater.getUUID();
			SysUserRolePO sysUserRolePO = new SysUserRolePO();

			sysUserRolePO.setUUID(UUID);
			sysUserRolePO.setROLE_UUID(roleId);
			sysUserRolePO.setUSER_UUID(idArr[i]);
			sysUserRolePO.setCREATOR(creator);
			sysUserRolePO.setCREATE_DATE(creatDate);

			sysUserRoleService.insertSysUserRole(sysUserRolePO);
		}
	}

}
