/**
 * 文件名：ISysRoleDao.java
 *
 * 版本信息：
 * 日期：2012-10-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hwsensor.permission.pojo.SysRolePO;


/**
 * 项目名称：framework
 * 类名称：ISysRoleDao
 * 类描述：系统角色dao
 * 创建人：杜群星
 * 创建时间：2012-10-11 下午9:47:17
 */
public interface ISysRoleDao extends IBaseDao {

	/**
	 * 添加角色操作
	 */
	public Integer insertSysRole(SysRolePO sysRolePO) throws SystemException;

	/**
	 * 修改角色操作
	 */
	public Integer updateSysRole(SysRolePO sysRolePO) throws SystemException;

	/**
	 * 动态修改角色操作
	 */
	public Integer updateSysRoleByMap(Map map) throws SystemException;

	/**
	 * 删除角色操作
	 */
	public Integer deleteSysRole(SysRolePO sysRolePO) throws SystemException;

	/**
	 * 动态删除角色操作
	 */
	public Integer deleteSysRoleByMap(Map map) throws SystemException;

	/**
	 * 按条件查询角色操作列表
	 */
	public List<SysRolePO> retrieveByCondition(Map map) throws SystemException;

	/**
	 * 按条件查询角色操作对象
	 */
	public SysRolePO retrieveSysRoleByMap(Map map) throws SystemException;

	/**
	 * 查询有没有正在使用的角色
	 */
	public Map retrieveCountByMap(Map checkMap) throws SystemException;

	/**
	 * 删除角色信息以及角色权限，部门角色，用户角色的关联信息
	 */
	public void deleteRoleAndRelatedByMap(Map map) throws SystemException;
}
