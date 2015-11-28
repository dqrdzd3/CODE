package com.wg.salescount.user.service;

import com.wg.salescount.user.po.UserPO;

public interface UserService {
	
	public UserPO add(UserPO userEntity);
	
	public UserPO update(UserPO userEntity);
	
	public int delete(UserPO userEntity);
	
	public UserPO queryByUserName(String userName);
}
