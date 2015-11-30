/**
 * 文件名：ISysUserRoleService.java
 *
 * 版本信息：
 * 日期：2012-10-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hwsensor.permission.pojo.SysUserRolePO;


/**
 * 项目名称：framework
 * 类名称：ISysUserRoleService
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-11 下午10:01:35
 */
public interface ISysUserRoleService extends IBaseService {

	/**
	 * 添加用户角色关联操作
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
	 * 查询用户角色关联部门权限 retrieveRolesByMap
	 * 
	 * @param
	 * @return List<SysUserRolePO>
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<Map<String, String>> retrieveRolesByMap(Map<String, String> map)
			throws SystemException;
}
