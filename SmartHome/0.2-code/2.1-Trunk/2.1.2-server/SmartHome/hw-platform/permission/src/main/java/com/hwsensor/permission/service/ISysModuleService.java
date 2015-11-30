/**
 * 文件名：ISysModuleService.java
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

import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hwsensor.permission.action.SysModuleAction;
import com.hwsensor.permission.pojo.SysModulePO;

/**
 * 
 * 项目名称：framework
 * 类名称：ISysModuleService
 * 类描述：系统业务模块service
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午1:40:15
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午1:40:15
 * 修改备注：
 * @version 
 * 
 */
public interface ISysModuleService extends IBaseService {

	/**
	 * 添加业务操作模块
	 */
	public Integer insertSysModule(SysModulePO sysModulePO)
			throws SystemException;

	/**
	 * 修改业务操作模块
	 */
	public Integer updateSysModule(SysModulePO sysModulePO)
			throws SystemException;

	/**
	 * 动态修改业务操作模块
	 */
	public Integer updateSysModuleByMap(Map map) throws SystemException;

	/**
	 * 删除业务操作模块
	 */
	public Integer deleteSysModule(SysModulePO sysModulePO)
			throws SystemException;

	/**
	 * 动态删除业务操作模块
	 */
	public Integer deleteSysModuleByMap(Map map) throws SystemException;

	/**
	 * 按条件查询业务操作模块列表
	 */
	public List<SysModulePO> retrieveByCondition(Map map)
			throws SystemException;

	/**
	 * 按条件查询业务操作模块对象
	 */
	public SysModulePO retrieveSysModuleByMap(Map map) throws SystemException;

	/**
	 * 构造系统模块树
	 */
	public void bulidSysMoudleTree(SysModuleAction sysModuleAction)
			throws SystemException;

	/**
	 * 添加系统模块的业务处理 insertSysModuleBP
	 * 
	 * @param name
	 * @return String
	 */
	public void insertSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException;

	/**
	 * 修改系统模块的业务处理 updateSysModuleBP
	 * 
	 * @param name
	 * @return String
	 */
	public void updateSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException;

	/**
	 * 删除系统模块的业务处理 deleteSysModuleBP
	 * 
	 * @param name
	 * @return String
	 */
	public void deleteSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException;

	/**
	 * 查询系统模块的业务处理 retrieveSysModuleBP
	 * 
	 * @param name
	 * @return String
	 */
	public SysModulePO retrieveSysModuleBP(SysModuleAction sysModuleAction)
			throws SystemException;

	/**
	 * 通过用户id取得用户所有权限 retrieveAllUserPermByUserId
	 * 
	 * @param name
	 * @return String
	 */
	public List<SysModulePO> retrieveAllUserPermByUserId(String userId)
			throws SystemException;

	/**
	 * 查询根节点功能 queryRootPer
	 * 
	 * @param name
	 * @return String
	 */
	public List<SysModulePO> queryRootPer(Map map) throws SystemException;

	/**
	 * 查询用户权限 retrieveAllUserPermByMap
	 * 
	 * @param name
	 * @return String
	 */
	public List<SysModulePO> retrieveAllUserPermByMap(Map map)
			throws SystemException;

	/**
	 * 查询企业/政府用户的权限 retrieveOrgUserPermByMap
	 * 
	 * @param name
	 * @return String
	 */
	public List<SysModulePO> retrieveOrgUserPermByMap(Map map)
			throws SystemException;

	/**
	 * 查询模块的排序号 queryModuleOrderNum
	 * 
	 * @param name
	 * @return String
	 */
	public int queryModuleOrderNum() throws SystemException;
}
