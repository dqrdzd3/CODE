package com.wg.salescount.user.dao;

import com.wg.salescount.basedao.BaseDAO;
import com.wg.salescount.user.po.UserPO;

public interface UserDAO extends BaseDAO<UserPO>{
	public UserPO queryByUserName(String userName);
}
