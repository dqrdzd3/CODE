/**
 * 文件名：ISysRolePermService.java
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
import com.hwsensor.permission.pojo.SysRolePermPO;

/**
 * 
 * 项目名称：framework
 * 类名称：ISysRolePermService
 * 类描述：系统业务模块操作关联dao
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午1:40:15
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午1:40:15
 * 修改备注：
 * @version 
 * 
 */
public interface ISysRolePermService extends IBaseService {

	/**
	 * 添加业务模块操作关联
	 */
	public Integer insertSysRolePerm(SysRolePermPO sysRolePermPO)
			throws SystemException;

	/**
	 * 修改业务模块操作关联
	 */
	public Integer updateSysRolePerm(SysRolePermPO sysRolePermPO)
			throws SystemException;

	/**
	 * 动态修改业务模块操作关联
	 */
	public Integer updateSysRolePermByMap(Map map) throws SystemException;

	/**
	 * 删除业务模块操作关联
	 */
	public Integer deleteSysRolePerm(SysRolePermPO sysRolePermPO)
			throws SystemException;

	/**
	 * 动态删除业务模块操作关联
	 */
	public Integer deleteSysRolePermByMap(Map map) throws SystemException;

	/**
	 * 按条件查询业务模块操作关联列表
	 */
	public List<SysRolePermPO> retrieveByCondition(Map map)
			throws SystemException;

	/**
	 * 按条件查询业务模块操作关联对象
	 */
	public SysRolePermPO retrieveSysRolePermByMap(Map map)
			throws SystemException;
}
