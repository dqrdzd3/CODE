/**
 * 文件名：ISysUserDao.java
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
import com.hw.hwsafe.platform.pojo.UserPO;

/**
 * 
 * 项目名称：framework
 * 类名称：ISysUserDao
 * 类描述：系统用户dao
 * 创建人：杜群星
 * 创建时间：2012-10-22 上午11:01:08
 */
public interface IUserDao extends IBaseDao {

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
	 * 动态删除用户操作
	 */
	public Integer deleteUserAndRelatedByMap(Map map) throws SystemException;

	/**
	 * 按条件查询用户操作列表
	 */
	public List<UserPO> retrieveByCondition(Map map) throws SystemException;

	/**
	 * 按条件查询用户操作对象
	 */
	public UserPO retrieveSysUserByMap(Map map) throws SystemException;

	/**
	 * 批量重置密码 updatePassWordBy
	 * 
	 * @param name
	 * @return String
	 */
	public void updatePassWordBy(Map map) throws SystemException;

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
	 * 获取系统管理员机构Id getSysAdminOrgId
	 * 
	 * @param name
	 * @return String
	 */
	public String getSysAdminOrgId() throws SystemException;

	/**
	 * 查询用户列表用于角色分配 queryUsersList
	 * 
	 * @param map
	 * @return list
	 */
	public List<Map<String, Object>> queryUsersList(Map map)
			throws SystemException;

	/**
	 * 往p006表中插入邮件信息 insertMail
	 * 
	 * @param name
	 * @return String
	 */
	public void insertMail(Map map) throws SystemException;

	/**
	 * 往p004表中插入邮件主题和内容 insertFile
	 * 
	 * @param name
	 * @return String
	 */
	public void insertFile(Map map) throws SystemException;

	/**
	 * 修改当前登录用户的信息
	 * 
	 * @param userinfo
	 * @throws SystemException
	 */
	public void updateUserInfo(UserPO userinfo) throws SystemException;

	/**
	 * 根据用户名和密码查询该用户是否存在
	 * 
	 * @param userinfo
	 * @return
	 * @throws SystemException
	 */
	public int retrieveUserByUserInfoPO(UserPO userinfo) throws SystemException;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SystemException
	 */
	public UserPO retrieveUserInfoByUserID(String id) throws SystemException;

}
