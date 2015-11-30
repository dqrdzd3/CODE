package com.hwsensor.permission.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.exception.system.SystemException;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hwsensor.permission.dao.ISysRolePermDao;
import com.hwsensor.permission.pojo.SysRolePermPO;
import com.hwsensor.permission.service.ISysRolePermService;


public class SysRolePermServiceImpl extends BaseServiceImpl implements
		ISysRolePermService {

	@Autowired
	private ISysRolePermDao sysRolePermDao;

	@Override
	public Integer insertSysRolePerm(SysRolePermPO sysRolePermPO)
			throws SystemException {
		return sysRolePermDao.insertSysRolePerm(sysRolePermPO);
	}

	@Override
	public Integer updateSysRolePerm(SysRolePermPO sysRolePermPO)
			throws SystemException {
		return sysRolePermDao.updateSysRolePerm(sysRolePermPO);
	}

	@Override
	public Integer updateSysRolePermByMap(Map map) throws SystemException {
		return sysRolePermDao.updateSysRolePermByMap(map);
	}

	@Override
	public Integer deleteSysRolePerm(SysRolePermPO sysRolePermPO)
			throws SystemException {
		return sysRolePermDao.deleteSysRolePerm(sysRolePermPO);
	}

	@Override
	public Integer deleteSysRolePermByMap(Map map) throws SystemException {
		return sysRolePermDao.deleteSysRolePermByMap(map);
	}

	@Override
	public List<SysRolePermPO> retrieveByCondition(Map map)
			throws SystemException {
		return sysRolePermDao.retrieveByCondition(map);
	}

	@Override
	public SysRolePermPO retrieveSysRolePermByMap(Map map)
			throws SystemException {
		return sysRolePermDao.retrieveSysRolePermByMap(map);
	}
}
