/**
 * 文件名：ISysRoleService.java
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
import com.hwsensor.permission.action.SysRoleAction;
import com.hwsensor.permission.pojo.SysRolePO;


/**
 * 项目名称：framework
 * 类名称：ISysRoleService
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-11 下午10:01:35
 */
public interface ISysRoleService extends IBaseService {

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
	 * 添加角色业务处理 insertSysRoleBP
	 * 
	 * @param name
	 * @return String
	 */
	public void insertSysRoleBP(SysRoleAction sysRoleAction)
			throws SystemException;

	/**
	 * 修改角色业务处理 updateSysRoleBP
	 * 
	 * @param name
	 * @return String
	 */
	public void updateSysRoleBP(SysRoleAction sysRoleAction)
			throws SystemException;

	/**
	 * 删除角色业务处理 deleteSysRoleBP
	 * 
	 * @param name
	 * @return String
	 */
	public Map deleteSysRoleBP(Map map) throws SystemException;

	/**
	 * 查询角色对象的业务处理 retrieveSysRoleBP
	 * 
	 * @param name
	 * @return String
	 */
	public SysRolePO retrieveSysRoleBP(SysRoleAction sysRoleAction)
			throws SystemException;

	/**
	 * 查询与角色的授权类型对应得的业务模块和操作资源树 buildTree
	 * 
	 * @param name
	 * @return String
	 */
	public void buildTree(SysRoleAction sysRoleAction) throws SystemException;

	/**
	 * 角色授权 roleAuthorzation
	 * 
	 * @param name
	 * @return String
	 */
	public void roleAuthorzationBP(SysRoleAction sysRoleAction)
			throws SystemException;

	/**
	 * 根据角色Id查询已拥有的操作资源 getHaveOpers
	 * 
	 * @param map
	 *            (ROLE_UUID,ORGAN_UUID)
	 * @return String
	 */
	public String getHaveOpers(Map map) throws SystemException;

	/**
	 * 给角色分配用户 assignUser
	 * 
	 * @param name
	 * @return String
	 */
	public void assignUser(String roleId, String[] idArr)
			throws SystemException;
}
