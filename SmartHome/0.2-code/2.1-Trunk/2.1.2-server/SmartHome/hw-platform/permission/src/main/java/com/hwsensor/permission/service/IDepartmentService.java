/**
 * 文件名：IDepartmentService.java
 *
 * 版本信息：1.0
 * 日期：2012-5-10
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hwsensor.permission.pojo.DepartmentPO;

/**
 * 
 * 项目名称：framework
 * 类名称：IDepartmentService
 * 类描述：
 * 创建人：盛家龙
 * 创建时间：2012-5-10 上午11:12:22
 * 修改人：孟繁波
 * 修改时间：2012-5-10 上午11:12:22
 * 修改备注：
 * @version 
 * 
 */
public interface IDepartmentService extends IBaseService {

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
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateDepartmentByPO(DepartmentPO departmentPO)
			throws Exception;

	/**
	 * 
	 * @Title: retrieveDepartmentByDeptId
	 * @Description: 根据部门ID查询部门
	 * @param @param deptId
	 * @param @return
	 * @param @throws Exception
	 * @return DepartmentPO
	 * @throws
	 */
	public DepartmentPO retrieveDepartmentByDeptId(String deptId)
			throws Exception;

	/**
	 * 
	 * @Title: retrieveAllDepartment
	 * @Description: 查询所有部门
	 * @param @return
	 * @param @throws Exception
	 * @return List<DepartmentPO>
	 * @throws
	 */
	public List<DepartmentPO> retrieveAllDepartment() throws Exception;

	/**
	 * 
	 * @Title: deleteDepartmentByDeptId
	 * @Description: 根据部门ID删除部门
	 * @param @param deptId
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteDepartmentByDeptId(String deptId) throws Exception;

	/**
	 * 
	 * @Title: retrieveDepartmentsByParentId
	 * @Description: 根据上级ID查询部门
	 * @param @param parentId
	 * @param @return
	 * @param @throws Exception
	 * @return List<DepartmentPO>
	 * @throws
	 */
	public List<DepartmentPO> retrieveDepartmentsByParentId(
			HashMap<String, String> map) throws Exception;

	public List<DepartmentPO> retrieveDepartmentsByParentId(String id)
			throws Exception;

	/**
	 * 
	 * @Title: retrieveAllRootDept
	 * @Description: 查询所有root部门
	 * @param @return
	 * @param @throws Exception
	 * @return List<DepartmentPO>
	 * @throws
	 */
	public List<DepartmentPO> retrieveAllRootDept(String organ_uuid)
			throws Exception;

	/**
	 * deleteDepartmentsByParentId 根据父ID删除此部门，以及其下的所有子部门 Author: 孟繁波
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteDepartmentsByParentId(String parentId);


	/**
	 * addDepartment(添加一条部门记录) Author: 孟繁波
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public UserMessageData addDepartment(HashMap<String,String> map,DepartmentPO deptPO) throws Exception;


	/**
	 * updateDepartment(更新一条部门记录) Author: 孟繁波
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public UserMessageData updateDepartment(HashMap<String, String> map ,DepartmentPO deptPO) throws Exception;

	/**
	 * retrieveDepartments(根据条件查询分页数据) Author: 孟繁波
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<Map<String, Object>> retrieveDepartments(Map map)
			throws Exception;

	/**
	 * retrieveCounts(查询总条数) Author: 孟繁波
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public int retrieveCounts(Map map) throws Exception;
	
	/**
	 * 
	 * 方法名称：retrieveDeptTree
	 * 功能描述：查询部门树
	 * @return    String
	 * @Exception 异常对象
	 * 创建人：杜群星
	 * 创建日期：2013-4-27 下午2:48:48
	 */
	public String retrieveDeptTree() throws Exception;

	/**
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public DepartmentPO retrieveDepartmentByKey(String key) throws Exception;

	/**
	 * 查询部门编码唯一性校验
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int countDeptByKey(String key) throws Exception;

	/**
	 * 批量删除部门信息
	 * 
	 * @param map
	 * @throws Exception
	 */
	public UserMessageData delBatch(HashMap<String, String[]> map)
			throws Exception;

	/**
	 * 部门角色授权
	 * 
	 * @param map
	 * @return 
	 * @throws Exception
	 */
	public UserMessageData deptEmpower(HashMap<String, String> map) throws Exception;

	/**
	 * 获取部门已有的角色
	 */
	public List retrieveDeptRole(String uuid) throws Exception;

	/**
	 * 构造部门树
	 * 
	 * @param map
	 *            (key:corpId)
	 * @return string
	 * @throws Exception
	 */
	public String buildTree(Map map) throws Exception;

  /**
   * 方法名称：retrieveAllDepartmentByCorpID
   * 功能描述：根据机构Id查询所有部门
   * @param   id	
   * @return    List
   * @Exception 异常对象
   * 创建人：杜群星
   * 创建日期：2013-4-24 下午3:32:44
  */
  public List retrieveAllDepartmentByCorpID(String id)throws Exception;
  
	/**
	 * insertDept(添加一条部门记录),返回主键
	 * @param departmentPO
	 * @return String
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
  String insertDept(DepartmentPO departmentPO) throws Exception;
  /**
   * updateDept(删除一条部门记录),成功返回true,反之false
   * @param departmentPO
   * @return String
   * @Exception 异常对象
   * @since CodingExample　Ver(编码范例查看) 1.1
   */
  boolean updateDept(DepartmentPO departmentPO) throws Exception;
  /**
   * updateDept(删除部门记录),成功返回true,反之false
   * @param departmentPO
   * @return String
   * @Exception 异常对象
   * @since CodingExample　Ver(编码范例查看) 1.1
   */
  boolean deleteDepts(String[] ids) throws Exception;
  
  
  
}
