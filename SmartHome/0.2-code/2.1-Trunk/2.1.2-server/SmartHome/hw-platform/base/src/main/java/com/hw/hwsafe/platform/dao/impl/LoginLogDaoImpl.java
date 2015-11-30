
/**
 * @title LoginLogDaoImpl.java
 * @package com.hw.hwsafe.platform.dao
 * @author 孟繁波
 * @create_time 2013-6-20 下午4:43:50
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.platform.dao.impl;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.ILoginLogDao;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class LoginLogDaoImpl extends BaseDaoImpl implements ILoginLogDao {

	@Override
	public List<Map<String, Object>> retrieveByPage(Map map) throws Exception {
		return getSqlSession().getMapper(ILoginLogDao.class)
				.retrieveByPage(map);
	}
}
