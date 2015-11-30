/**
 * 文件名：IDepartmentDao.java
 *
 * 版本信息：1.0
 * 日期：2012-5-9
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hwsensor.permission.pojo.DepartmentPO;

/**
 * 
 * 项目名称：framework
 * 类名称：IDepartmentDao
 * 类描述：DepartmentDao 接口类
 * 创建人：盛家龙
 * 创建时间：2012-5-9 下午4:58:48
 * 修改人：盛家龙
 * 修改时间：2012-5-9 下午4:58:48
 * 修改备注：
 * @version 
 * 
 */
public interface IDepartmentDao extends IBaseDao {
	
	/**
	 * 
	 * @Title: insertDepartment
	 * @Description: 插入部门
	 * @param @param departmentPO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertDepartment(DepartmentPO departmentPO) throws Exception;
	
	/**
	 * 
	 * @Title: updateDepartment
	 * @Description: 更新部门
	 * @param @param departmentPO
	 * @return void
	 * @throws
	 */
	public void updateDepartment(DepartmentPO departmentPO) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveDepartmentByDeptId
	 * @Description: 根据部门ID查询部门
	 * @param @param deptId
	 * @param @return
	 * @return DepartmentPO
	 * @throws
	 */
	public DepartmentPO retrieveDepartmentByDeptId(String deptId) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveAllDepartment
	 * @Description: 查询所有部门
	 * @param @return
	 * @return List<DepartmentPO>
	 * @throws
	 */
	public List<DepartmentPO> retrieveAllDepartment() throws Exception;
	public List<DepartmentPO> retrieveAllDepartmentByCorpID(String ID) throws Exception;
	/**
	 * 
	 * @Title: deleteDepartmentByDeptId
	 * @Description: 根据id删除部门
	 * @param @param deptId
	 * @return void
	 * @throws
	 */
	public void deleteDepartmentByDeptId(String deptId) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveDepartmentsByParentId
	 * @Description: 根据
	 * @param @param parentId
	 * @param @return
	 * @return List<DepartmentPO>
	 * @throws
	 */
	public List<DepartmentPO> retrieveDepartmentsByParentId(HashMap<String, String> map) throws Exception;
	
	public List<DepartmentPO> retrieveDepartmentsByParentId(String map) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveAllRootDept
	 * @Description: 查询root部门
	 * @param @return
	 * @return List<DepartmentPO>
	 * @throws
	 */
	public List<DepartmentPO> retrieveAllRootDept(String ogran_uuid) throws Exception;

	/**
	 * deleteDepartmentsByParentId 根据父ID删除此部门，以及其下的所有子部门
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public void deleteDepartmentsByParentId(String parentId);
	
	/**
	 * retrieveDepartments(分页查询)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public List<Map<String,Object>> retrieveDepartments(Map map) throws Exception;
	
	/**
	 * retrieveCounts(查询总条数)
	 * Author: 孟繁波
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	*/
	public int retrieveCounts(Map map) throws Exception;
	/**
	 * 通过部门编码查询唯一一条部门信息
	 * @param fullkey
	 * @return
	 * @throws Exception
	 */
	public DepartmentPO retrieveDepartmentByFullkey(String fullkey)throws Exception;
	
	public int countDeptByKey(String key)throws Exception;
	/**
	 * 批量删除部门信息
	 * @param map
	 * @throws Exception
	 */
	public void delBatch(HashMap<String, String[]> map)throws Exception;
	/**
	 * 部门删除后修改该部门下的人员的一下信息
	 * @param map
	 * @throws Exception
	 */
	public void updateSysUserInfo(HashMap<String, String[]> map)throws Exception;
	/**
	 * 部门删除后删除部门角色关联表数据
	 * @param map
	 * @throws Exception
	 */
	public void delDepRole(HashMap<String, String[]> map)throws Exception;
	
	/**
	 * 部门角色授权先删除以前的部门角色授权
	 * @param map
	 * @throws Exception
	 */
	public void delDepRoleEmpower(String depart_uuid)throws Exception;
	/**
	 * 添加部门角色授权
	 * @param map
	 * @throws Exception
	 */
	public void deptEmpower(HashMap<String, String> map)throws Exception;
	/**
	 * 获取部门已有的角色
	 * @param uuid
	 * @return
	 * @throws Exception
	 */
	public List retrieveDeptRole(String uuid)throws Exception;
	/**
	 * retrieveUsernum(获取当前部门集合下用户个数)
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Integer retrieveUsernum(HashMap<String, String[]> map)throws Exception;
	/**
	 * 添加部门时查询部门名称是否已存在
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int checkDeptName(HashMap<String,String> map)throws Exception;
	
}
