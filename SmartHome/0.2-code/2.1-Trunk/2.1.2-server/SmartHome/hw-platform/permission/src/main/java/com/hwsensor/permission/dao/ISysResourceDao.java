/**
 * 文件名：ISysResourceDao.java
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
import com.hwsensor.permission.pojo.SysResourcePO;

/**
 * 
 * 项目名称：framework
 * 类名称：ISysResourceDao
 * 类描述：系统业务资源dao
 * 创建人：杜群星
 * 创建时间：2012-10-9 下午1:40:15
 * 修改人：Administrator
 * 修改时间：2012-10-9 下午1:40:15
 * 修改备注：
 * @version 
 * 
 */
public interface ISysResourceDao extends IBaseDao {

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

	public List<String> retrieveAllUrl();

	public List<String> retrieveUrlByUserId(String paramString);
}
