/**
 * 文件名：ISysUserRoleDao.java
 *
 * 版本信息：1.0
 * 日期：2012-5-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hwsensor.permission.pojo.SysUserRolePO;

/**
 * 
 * 项目名称：framework
 * 类名称：ISysUserRoleDao
 * 类描述：系统用户角色关联角色关联dao
 * 创建人：杜群星
 * 创建时间：2012-10-22 上午11:01:08
 */
public interface ISysUserRoleDao extends IBaseDao {

	/**
	 * 添加用户角色关联角色关联操作
	 */
	public Integer insertSysUserRole(SysUserRolePO sysUserRolePO)
			throws SystemException;

	/**
	 * 修改用户角色关联操作
	 */
	public Integer updateSysUserRole(SysUserRolePO sysUserRolePO)
			throws SystemException;

	/**
	 * 动态修改用户角色关联操作
	 */
	public Integer updateSysUserRoleByMap(Map map) throws SystemException;

	/**
	 * 删除用户角色关联操作
	 */
	public Integer deleteSysUserRole(SysUserRolePO sysUserRolePO)
			throws SystemException;

	/**
	 * 动态删除用户角色关联操作
	 */
	public Integer deleteSysUserRoleByMap(Map map) throws SystemException;

	/**
	 * 按条件查询用户角色关联操作列表
	 */
	public List<SysUserRolePO> retrieveByCondition(Map map)
			throws SystemException;

	/**
	 * 按条件查询用户角色关联操作对象
	 */
	public SysUserRolePO retrieveSysUserRoleByMap(Map map)
			throws SystemException;

	/**
	 * 用户角色关联部门授权的角色
	 */
	public List<Map<String, String>> retrieveRolesByMap(Map<String, String> map)
			throws SystemException;

}
