/**
 * 文件名：PermissionFacadeServiceImpl.java
 * 版本信息：
 * 日期：2013-4-2
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 */
package com.hwsensor.permission.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.utils.ConverUtil;
import com.hwsensor.permission.pojo.DepartmentPO;
import com.hwsensor.permission.service.IDepartmentService;
import com.hwsensor.permission.service.IPermissionFacadeService;
import com.hwsensor.permission.service.IUserService;


/**
 * 项目名称：permission
 * 类名称：PermissionFacadeServiceImplO
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2013-4-2 下午3:27:31
 * 修改人：
 * 修改时间：
 * 修改备注：
 * @version 
 */
public class PermissionFacadeServiceImpl implements IPermissionFacadeService {

  private static Logger log = Logger.getLogger(PermissionFacadeServiceImpl.class);
  @Autowired
  private IUserService userService;
  @Autowired
  private IDepartmentService iDepartmentService;
  
  @Override
  public UserPO retrieveUserByMap(Map map) throws Exception {
  
    return userService.retrieveSysUserByMap(map);
  }

  @Override
  public UserPO retrieveUserByLoginName(String loginName) throws Exception {
  
    return userService.retrieveUserByLoginName(loginName);
  }

  @Override
  public int updateUserPwdByMap(Map map) throws Exception {
  
    return userService.updateSysUserByMap(map);
  }

  @Override
  public List<DepartmentPO> retrieveAllDepartmentsByOrgId(String orgId) throws Exception {
  
    return iDepartmentService.retrieveAllDepartmentByCorpID(orgId);
  }

	@Override
	public DepartmentPO retrieveDepartmentById(String id) throws Exception {
		return StringUtils.isBlank(id) 
				? new DepartmentPO()
				: iDepartmentService.retrieveDepartmentByDeptId(id);
	}

	@Override
	public Map<String, Object> retrieveUserById(String id) throws Exception {
		UserPO userPO = userService.retrieveUserInfoByUserID(id);
		return ConverUtil.PO2Map(userPO);
	}

	@Override
	public List<Map<String, Object>> retrieveAllUsers() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<UserPO> userList = userService.retrieveAllSysUser();
		for (UserPO userPO : userList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map = ConverUtil.PO2Map(userPO);
			list.add(map);
		}
		return list;
	}

	@Override
	public String insertUser(Map<String, Object> map) throws Exception {
		UserPO userPO = new UserPO();
		userPO = (UserPO)ConverUtil.map2PO(map,userPO);
		log.info("添加用户："+userPO.getLOGIN_NAME());
		return userService.insertUser(userPO);
	}

	@Override
	public boolean updateUser(Map<String, Object> map) throws Exception {
		UserPO userPO = new UserPO();
		userPO = (UserPO) ConverUtil.map2PO(map,userPO);
		log.info("修改用户："+userPO.getUUID());
		return userService.updateUser(userPO);
	}

	@Override
	public boolean deleteUsers(List<String> list) throws Exception {
		String ids = "";
		for (String s : list) {
			ids +=  s+",";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		log.info("删除用户："+ids);
		userService.deleteSysUserBP(map);
		return true;
	}
	


	@Override
	public Map<String, Object> retrieveDeptById(String id) throws Exception {
		DepartmentPO departmentPO = iDepartmentService.retrieveDepartmentByDeptId(id);
		return ConverUtil.PO2Map(departmentPO);
	}

	@Override
	public String insertDept(Map<String, Object> map) throws Exception {
		DepartmentPO departmentPO = new DepartmentPO();
		departmentPO = (DepartmentPO) ConverUtil.map2PO(map, departmentPO);
		return iDepartmentService.insertDept(departmentPO);
	}

	@Override
	public boolean updateDept(Map<String, Object> map) throws Exception {
		DepartmentPO departmentPO = new DepartmentPO();
		departmentPO = (DepartmentPO) ConverUtil.map2PO(map, departmentPO);
		return iDepartmentService.updateDept(departmentPO);
	}

	@Override
	public boolean deleteDepts(List<String> list) throws Exception {
		String[] ids = new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			ids[i] = list.get(i);
		}
		
		return iDepartmentService.deleteDepts(ids);
	}
	

	@Override
	public List<Map<String, Object>> retrieveAllDepts() throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<DepartmentPO> deptList = iDepartmentService.retrieveAllDepartment();
		for (DepartmentPO departmentPO : deptList) {
			list.add(ConverUtil.PO2Map(departmentPO));
		}
		return list;
	}

	@Override
	public Map<String, Object> retrieveDeptByKey(String key) throws Exception {
		DepartmentPO departmentPO = new DepartmentPO();
		departmentPO = iDepartmentService.retrieveDepartmentByKey(key);
		return ConverUtil.PO2Map(departmentPO);
	}

	@Override
	public List<Map<String, Object>> retrieveAllDeptByOrgId(String orgId)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		List<DepartmentPO> deptList = iDepartmentService.retrieveAllDepartmentByCorpID(orgId);
		for (DepartmentPO departmentPO : deptList) {
			list.add(ConverUtil.PO2Map(departmentPO));
		}
		return list;
	}
}
