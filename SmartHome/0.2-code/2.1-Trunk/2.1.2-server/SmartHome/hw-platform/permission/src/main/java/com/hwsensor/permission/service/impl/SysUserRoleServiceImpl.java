/**
 * 文件名：SysUserRoleServiceImpl.java
 *
 * 版本信息：
 * 日期：2012-10-22
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hwsensor.permission.dao.ISysUserRoleDao;
import com.hwsensor.permission.pojo.SysUserRolePO;
import com.hwsensor.permission.service.ISysUserRoleService;


/**
 * 项目名称：framework
 * 类名称：SysUserRoleServiceImpl
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-22 下午3:45:38
 */
public class SysUserRoleServiceImpl extends BaseServiceImpl implements
		ISysUserRoleService {

	@Autowired
	private ISysUserRoleDao sysUserRoleDao;

	@Override
	public Integer insertSysUserRole(SysUserRolePO sysUserRolePO)
			throws SystemException {
		return sysUserRoleDao.insertSysUserRole(sysUserRolePO);
	}

	@Override
	public Integer updateSysUserRole(SysUserRolePO sysUserRolePO)
			throws SystemException {
		return sysUserRoleDao.updateSysUserRole(sysUserRolePO);
	}

	@Override
	public Integer updateSysUserRoleByMap(Map map) throws SystemException {
		return sysUserRoleDao.updateSysUserRoleByMap(map);
	}

	@Override
	public Integer deleteSysUserRole(SysUserRolePO sysUserRolePO)
			throws SystemException {
		return sysUserRoleDao.deleteSysUserRole(sysUserRolePO);
	}

	@Override
	public Integer deleteSysUserRoleByMap(Map map) throws SystemException {
		return sysUserRoleDao.deleteSysUserRoleByMap(map);
	}

	@Override
	public List<SysUserRolePO> retrieveByCondition(Map map)
			throws SystemException {
		return sysUserRoleDao.retrieveByCondition(map);
	}

	@Override
	public SysUserRolePO retrieveSysUserRoleByMap(Map map)
			throws SystemException {
		return sysUserRoleDao.retrieveSysUserRoleByMap(map);
	}

	@Override
	public List<Map<String, String>> retrieveRolesByMap(Map<String, String> map)
			throws SystemException {
		return sysUserRoleDao.retrieveRolesByMap(map);
	}
}
