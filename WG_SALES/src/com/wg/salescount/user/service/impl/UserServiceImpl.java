package com.wg.salescount.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.wg.salescount.user.dao.UserDAO;
import com.wg.salescount.user.po.UserPO;
import com.wg.salescount.user.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDAO userDAO;
	
	@Override
	public UserPO add(UserPO userEntity) {
		UserPO userPO = userDAO.save(userEntity);
		return userPO;
	}

	@Override
	public UserPO update(UserPO userEntity) {
		UserPO userPO = userDAO.update(userEntity);
		return userPO;
	}

	@Override
	public int delete(UserPO userEntity) {
		int result = userDAO.delete(userEntity);
		return result;
	}

	public UserPO queryByUserName(String userName) {
		UserPO userPO = userDAO.queryByUserName(userName);
		return userPO;
	}


}
