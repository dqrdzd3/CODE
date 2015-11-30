/**
 * 文件名：ISysModuleDao.java
 *
 * 版本信息：
 * 日期：2012-10-9
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hwsensor.permission.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hwsensor.permission.pojo.SysModulePO;

/**
 * 
 * 项目名称：framework
 * 类名称：ISysModuleDao
 * 类描述：系统业务模块dao
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午1:40:15
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午1:40:15
 * 修改备注：
 * @version 
 * 
 */
public interface ISysModuleDao extends IBaseDao {

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
	 * 通过用户id查询所有权限
	 */
	public List<SysModulePO> retrieveAllUserPermByUserId(String userId)
			throws SystemException;

	/**
	 * 查询所有根权限
	 */
	public List<SysModulePO> queryRootPer(Map map) throws SystemException;

	/**
	 * 查询管理员用户权限
	 */
	public List<SysModulePO> retrieveAllUserPermByMap(Map map)
			throws SystemException;

	/**
	 * 查询企业/政府普通用户权限
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
