/**
 * 文件名：ISysUserService.java
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
import net.sf.json.JSONArray;

import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hwsensor.permission.action.UserAction;


/**
 * 项目名称：framework
 * 类名称：ISysUserService
 * 类描述：
 * 创建人：杜群星
 * 创建时间：2012-10-11 下午10:01:35
 */
public interface IUserService extends IBaseService {

	/**
	 * 添加用户操作
	 */
	public Integer insertSysUser(UserPO sysUserPO) throws SystemException;

	/**
	 * 修改用户操作
	 */
	public Integer updateSysUser(UserPO sysUserPO) throws SystemException;

	/**
	 * 动态修改用户操作
	 */
	public Integer updateSysUserByMap(Map map) throws SystemException;

	/**
	 * 删除用户操作
	 */
	public Integer deleteSysUser(UserPO sysUserPO) throws SystemException;

	/**
	 * 按条件查询用户操作列表
	 */
	public List<UserPO> retrieveByCondition(Map map) throws SystemException;

	/**
	 * 按条件查询用户操作对象
	 */
	public UserPO retrieveSysUserByMap(Map map) throws SystemException;

	/**
	 * 添加用户业务处理 insertSysUserBP
	 * 
	 * @param name
	 * @return String
	 */
	public UserMessageData insertSysUserBP(UserAction sysUserAction)
			throws SystemException;

	/**
	 * 修改用户业务处理 updateSysUserBP
	 * 
	 * @param name
	 * @return String
	 */
	public UserMessageData updateSysUserBP(UserAction sysUserAction)
			throws SystemException;

	/**
	 * 查询用户对象的业务处理 retrieveSysUserBP
	 * 
	 * @param name
	 * @return String
	 */
	public UserPO retrieveSysUserBP(UserAction sysUserAction)
			throws SystemException;

	/**
	 * 权限分配时查询方法 doAssignRole
	 * 
	 * @param name
	 * @return String
	 */
	public void doAssignRole(UserAction sysUserAction) throws SystemException;

	/**
	 * 权限分配数据保存 doAssignRoleSave
	 * 
	 * @param name
	 * @return String
	 */
	public void saveAssignRole(UserAction sysUserAction) throws SystemException;

	/**
	 * 构造某企业/政府/系统的权限树 buildTree
	 * 
	 * @param name
	 * @return String
	 */
	public JSONArray buildOperTree(Map map) throws SystemException;

	/**
	 * 查询某企业/政府/系统用户已拥有的操作权限 queryHaveOpers
	 * 
	 * @param name
	 * @return String
	 */
	public String queryHaveOpers(Map map) throws SystemException;

	/**
	 * 查询某企业/政府/系统的某用户拥有的角色 queryHaveRoles
	 * 
	 * @param name
	 * @return String
	 */
	public List<Map<String, String>> queryHaveRoles(Map map)
			throws SystemException;

	/**
	 * 查询某企业/政府/系统拥有的角色 queryRolesByMap
	 * 
	 * @param name
	 * @return String
	 */
	public List queryRolesByMap(Map map) throws SystemException;

	/**
	 * 根据用户角色Id,查询拥有的操作 queryHaveOpersByRoleID
	 * 
	 * @param name
	 * @return String
	 */
	public String queryHaveOpersByRoleID(String id) throws SystemException;

	/**
	 * 对系统用户密码重置（默认:月中日期+时分秒） doReSetPassWord
	 * 
	 * @param name
	 * @return String
	 */
	public Map updatePassWord(Map map) throws SystemException;

	/**
	 * 构造部门树 buildDeptTree
	 * 
	 * @param name
	 * @return String
	 */
	public String buildDeptTree(Map map) throws SystemException;

	/**
	 * 根据登录账号查询用户 retrieveUserByLoginName
	 * 
	 * @param name
	 * @return String
	 */
	public UserPO retrieveUserByLoginName(String loginName)
			throws SystemException;

	/**
	 * 根据账号和密码查询用户 retrieveUserByLoginNameAndPwd
	 * 
	 * @param name
	 * @return String
	 */
	public UserPO retrieveUserByLoginNameAndPwd(UserPO userPO)
			throws SystemException;

	/**
	 * 根据用户ID查询用户信息 retrieveSysUserByUserID
	 * 
	 * @param name
	 * @return String
	 */
	public UserPO retrieveSysUserByUserID(String UUID) throws SystemException;

	/**
	 * 查询所有用户信息列表 retrieveAllSysUser
	 * 
	 * @param name
	 * @return String
	 */
	public List<UserPO> retrieveAllSysUser() throws SystemException;

	/**
	 * 获取系统管理员的机构Id getSysAdminOrgId
	 * 
	 * @param name
	 * @return String
	 */
	public String getSysAdminOrgId() throws SystemException;

	/**
	 * 查询用户列表用于角色分配 queryUsersList
	 * 
	 * @param name
	 * @return String
	 */
	public List<Map<String, Object>> queryUsersList(Map map)
			throws SystemException;

	/**
	 * 修改当前登录用户的密码
	 * 
	 * @param sysUserAction
	 * @throws SystemException
	 */
	public UserMessageData updateSysUserPassWord(UserPO userinfo)
			throws SystemException;

	/**
	 * 查询当前登录用户的用户信息
	 * 
	 * @param userid
	 * @return
	 * @throws SystemException
	 */
	public UserPO retrieveUserInfoByUserID(String userid)
			throws SystemException;

	/**
	 * 修改当前登录用户的信息
	 * 
	 * @param userinfo
	 * @throws SystemException
	 */
	public UserMessageData updateSysUserInfo(UserPO userinfo)
			throws SystemException;

	/**
	 * 删除用户及关联的用户部门，用户角色信息 deleteSysUserBP
	 * 
	 * @param name
	 * @return String
	 */
	public Map deleteSysUserBP(Map map) throws SystemException;
	/**
	 * 添加用户
	 * @param sysUserPO
	 * @return
	 * @throws Exception
	 * @author 杜群星
	 * @createDate 2014-04-03 15:11
	 */
	public String insertUser(UserPO sysUserPO) throws Exception;
	/**
	 * 修改用户
	 * @param sysUserPO
	 * @return
	 * @throws Exception
	 * @author 杜群星
	 * @createDate 2014-04-03 15:11
	 */
	public boolean updateUser(UserPO sysUserPO) throws Exception;
}
