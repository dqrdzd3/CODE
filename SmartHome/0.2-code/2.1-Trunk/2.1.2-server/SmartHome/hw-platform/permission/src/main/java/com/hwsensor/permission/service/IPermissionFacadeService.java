/**
 * 文件名：IPermissionFacadeService.java
 * 版本信息：
 * 日期：2013-4-2
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2013 
 * 版权所有
 */
package com.hwsensor.permission.service;

import java.util.List;
import java.util.Map;

import javax.management.ListenerNotFoundException;

import com.hw.hwsafe.platform.pojo.UserPO;
import com.hwsensor.permission.pojo.DepartmentPO;


/**
 * 项目名称：permission
 * 类名称：IPermissionFacadeService
 * 类描述： 权限系统门面类
 * 创建人：杜群星
 * 创建时间：2013-4-2 下午3:26:46
 * 修改人：杜群星
 * 修改时间：2014-4-4 8:55
 * 修改备注：
 * @version 
 */
public interface IPermissionFacadeService {
  /**
   * 方法名称：retrieveUseByMap
   * 功能描述：通过注册账号和邮箱地址或手机号查询用户密码
   * @param     map(LOGIN_NAME,EMAIL,MOBILE_NUMBER)
   * @return    UserPO
   * @Exception 异常对象
   * 创建人：杜群星
   * 创建日期：2013-4-2 下午2:22:04
   */
  public UserPO retrieveUserByMap(Map map) throws Exception;

  /**
   * 
   * 方法名称：retrieveUserByLoginName
   * 功能描述：通过注册账号查询用户
   * @param     loginName
   * @return    UserPO
   * @Exception 异常对象
   * 创建人：杜群星
   * 创建日期：2013-4-2 下午2:23:26
   */
  public UserPO retrieveUserByLoginName(String loginName) throws Exception;

  /**
   * 
   * 方法名称：updateUserPwdByMap
   * 功能描述：通过注册账号和新密码修改用户密码（新密码必须是MD5加密过的密文）
   * @param     map(LOGIN_NAME,NEWPASSWORD)
   * @return    int
   * @Exception 异常对象
   * 创建人：杜群星
   * 创建日期：2013-4-2 下午2:24:05
   */
  public int updateUserPwdByMap(Map map) throws Exception;
  
  /**
   * 
   * 方法名称：retrieveAllDepartmentsByOrgId
   * 功能描述：根据机构Id查询本机构下面所有部门
   * @param   	orgId:机构Id
   * @return    List<DepartmentPO>
   * @Exception 异常对象
   * 创建人：杜群星
   * 创建日期：2013-4-25 上午9:09:53
   */
  public List<DepartmentPO> retrieveAllDepartmentsByOrgId(String orgId) throws Exception;
  /**
   * 
   * 方法名称：retrieveDepartmentById
   * 功能描述：通过部门id查询部门
   * @param   	id:部门id
   * @return    DepartmentPO
   * @Exception 异常对象
   * 创建人：杜群星
   * 创建日期：2013-4-25 上午9:14:49
   */
  public DepartmentPO retrieveDepartmentById(String id) throws Exception;
  
  /**
   * 通过账号主键查询账号信息
   * <br>
   * 返回一个Map集合
   * @param id
   * @return {@link Map}
   * @throws Exception
   * @author 杜群星
   * @createDate 2014-04-03 13:59
   */
  public Map<String, Object> retrieveUserById(String id) throws Exception;
  
  /**
   * 查询所有用户信息
   * <br>
   * 返回一个放着map的list集合
   * @param 
   * @return {@link List}
   * @throws Exception
   * @author 杜群星
   * @createDate 2014-04-03 13:59
   */
  public List<Map<String, Object>> retrieveAllUsers() throws Exception;
  /**
   * 添加账户,返回记录的主键<br>
   * Map中的key包括：
 	<pre>
		LOGIN_NAME	用户名
		PASSWORD	密码
		REAL_NAME	真实姓名
		MOBILE_NUMBER	手机号
		EMAIL	邮箱
		ID_CARD	身份证号码
		USER_TYPE	类型(传默认值"GOV")
		IS_ADMIN	是否管理员("0")
		CREATOR	创建人
		CREATE_DATE	创建日期
		EDITOR	修改人
		MODIFIED_DATE	修改日期
   	</pre>
   * @param map
   * @return {@link String}
   * @throws Exception
   * @author 杜群星
   * @createDate 2014-04-03 13:59
   */
  public String insertUser(Map<String,Object> map) throws Exception;
  /**
   * 修改账户,成功返回true，否则返回false<br>
   * Map中的key包括：
 	<pre>
		UUID   主键
		LOGIN_NAME	用户名
		PASSWORD	密码
		REAL_NAME	真实姓名
		MOBILE_NUMBER	手机号
		EMAIL	邮箱
		ID_CARD	身份证号码
		USER_TYPE	类型(传默认值"GOV")
		IS_ADMIN	是否管理员("0")
		CREATOR	创建人
		CREATE_DATE	创建日期
		EDITOR	修改人
		MODIFIED_DATE	修改日期
   	</pre>
   * @param map
   * @return 
   * @throws Exception
   * @author 杜群星
   * @createDate 2014-04-03 13:59
   */
  public boolean updateUser(Map<String,Object> map) throws Exception;
  /**
   * 批量删除账户,成功返回true，否则返回false<br>
   * 参数为字符串集合
   * @param list
   * @return 
   * @throws Exception
   * @author 杜群星
   * @createDate 2014-04-03 13:59
   */
  public boolean deleteUsers(List<String> list) throws Exception;
  /**
   * 通过主键查询部门信息，返回map集合
   * @param id
   * @return 
   * @throws Exception
   */
  Map<String, Object> retrieveDeptById(String id) throws Exception;
  
	/**
	 * 查询所有单位部门
	 * @param
	 * @return {@link List}
	 * @throws Exception
	 */
  List<Map<String, Object>> retrieveAllDepts() throws Exception;
	/**
	 * 通过部门编码查询唯一一条部门信息
	 * @param key 
	 * @return
	 * @throws Exception
	 */
  Map<String,Object> retrieveDeptByKey(String key) throws Exception;
  /**
   * 根据机构Id查询所有部门
   * @param   orgId	
   * @return    List
   * @Exception 异常对象
  */
  List<Map<String, Object>> retrieveAllDeptByOrgId(String orgId)throws Exception;
  
  /**
   * 添加部门，返回主键
   * @param map
   * @return 
   * @throws Exception
   */
  String insertDept(Map<String, Object> map) throws Exception;
  /**
   * 修改部门，成功返回true，否则false
   * @param map
   * @return
   * @throws Exception
   */
  boolean updateDept(Map<String, Object> map) throws Exception;
  /**
   * 删除部门，成功返回true，否则false
   * @param map
   * @return
   * @throws Exception
   */
  boolean deleteDepts(List<String> list) throws Exception;
}
