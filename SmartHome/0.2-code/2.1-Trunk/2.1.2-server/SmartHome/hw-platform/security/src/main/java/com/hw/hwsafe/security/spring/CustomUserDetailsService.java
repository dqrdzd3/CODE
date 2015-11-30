package com.hw.hwsafe.security.spring;

import com.hw.hwsafe.platform.pojo.UserPO;
import com.hwsensor.permission.pojo.SysModulePO;
import com.hwsensor.permission.service.ISysModuleService;
import com.hwsensor.permission.service.IUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@SuppressWarnings("deprecation")
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private IUserService sysUserService;

	@Autowired
	private ISysModuleService iSysModuleService;

	@SuppressWarnings("unchecked")
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		try {
			Collection auths = new ArrayList();

			UserPO userPo = sysUserService.retrieveUserByLoginName(username);

			List<SysModulePO> userPerm = iSysModuleService
					.retrieveAllUserPermByUserId(userPo.getId());

			for (SysModulePO permPo : userPerm) {
				if ((permPo == null) || (permPo.getMENU_CODE() == null)
						|| ("".equals(permPo.getMENU_CODE()))) {
					continue;
				}
				GrantedAuthorityImpl authority = new GrantedAuthorityImpl(
						permPo.getMENU_CODE());
				auths.add(authority);
			}

			return new User(username, userPo.getPassword(), true, true, true,
					true, auths);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ISysModuleService getiSysModuleService() {
		return this.iSysModuleService;
	}

	public IUserService getSysUserService() {
		return this.sysUserService;
	}

	public void setSysUserService(IUserService sysUserService) {
		this.sysUserService = sysUserService;
	}

	public void setiSysModuleService(ISysModuleService iSysModuleService) {
		this.iSysModuleService = iSysModuleService;
	}
}