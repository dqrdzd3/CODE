/**
 * 文件名：ISysResourceService.java
 *
 * 版本信息：
 * 日期：2012-10-9
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.service;

import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;

import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hwsensor.permission.action.SysResourceAction;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.pojo.SysOperationPO;
import com.hwsensor.permission.pojo.SysResourcePO;

/**
 * 
 * 项目名称：framework
 * 类名称：ISysResourceService
 * 类描述：系统业务资源dao
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午1:40:15
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午1:40:15
 * 修改备注：
 * @version 
 * 
 */
public interface ISysResourceService extends IBaseService {

	/**
	 * 添加业务资源
	 */
	public Integer insertSysResource(SysResourcePO sysResourcePO)
			throws SystemException;

	/**
	 * 修改业务资源
	 */
	public Integer updateSysResource(SysResourcePO sysResourcePO)
			throws SystemException;

	/**
	 * 动态修改业务资源
	 */
	public Integer updateSysResourceByMap(Map map) throws SystemException;

	/**
	 * 删除业务资源
	 */
	public Integer deleteSysResource(SysResourcePO sysResourcePO)
			throws SystemException;

	/**
	 * 动态删除业务资源
	 */
	public Integer deleteSysResourceByMap(Map map) throws SystemException;

	/**
	 * 按条件查询业务资源列表
	 */
	public List<SysResourcePO> retrieveByCondition(Map map)
			throws SystemException;

	/**
	 * 按条件查询业务资源对象
	 */
	public SysResourcePO retrieveSysResourceByMap(Map map)
			throws SystemException;

	/**
	 * 删除系统资源业务 deleteSysResourceBP
	 * 
	 * @param name
	 * @return String
	 */
	public void deleteSysResourceBP(SysResourceAction sysResourceAction)
			throws SystemException;

	/**
	 * 修改系统资源业务 updateSysResourceBP
	 * 
	 * @param name
	 * @return String
	 */
	public void updateSysResourceBP(SysResourceAction sysResourceAction)
			throws SystemException;

	/**
	 * 查询系统资源对象业务 retrieveSysResourceBP
	 * 
	 * @param name
	 * @return String
	 */
	public SysResourcePO retrieveSysResourceBP(
			SysResourceAction sysResourceAction) throws SystemException;

	/**
	 * 添加系统资源业务 insertSysResourceBP
	 * 
	 * @param name
	 * @return String
	 */
	public void insertSysResourceBP(SysResourceAction sysResourceAction)
			throws SystemException;

	/**
	 * 查询系统资源列表 retrieveSysResourceList
	 * 
	 * @param name
	 * @return String
	 */
	public void retrieveSysResourceList(SysResourceAction sysResourceAction)
			throws SystemException;

	/**
	 * 构造业务模块和业务操作的树 buildTree
	 * 
	 * @param name
	 * @return String
	 */
	public void buildTree(SysResourceAction sysResourceAction)
			throws SystemException;

	/**
	 * 构造业务模块树 buildModuleTreeByList
	 * 
	 * @param name
	 * @return String
	 */
	public JSONArray buildModuleTreeByList(List<SysModulePO> list)
			throws SystemException;

	/**
	 * 构造业务操作树 buildOperTreeByList
	 * 
	 * @param name
	 * @return String
	 */
	public JSONArray buildOperTreeByList(List<SysOperationPO> list)
			throws SystemException;

	public List<String> retrieveAllUrl() throws SystemException;

	public List<String> retrieveUrlsByUserId(String paramString);
}
