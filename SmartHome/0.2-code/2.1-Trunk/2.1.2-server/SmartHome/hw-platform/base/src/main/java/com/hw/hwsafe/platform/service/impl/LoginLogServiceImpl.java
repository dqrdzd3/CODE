
/**
 * @title LoginLogServiceImpl.java
 * @package com.hw.hwsafe.platform.service.impl
 * @author 孟繁波
 * @create_time 2013-6-20 下午4:15:37
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.platform.dao.ILoginLogDao;
import com.hw.hwsafe.platform.service.ILoginLogService;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public class LoginLogServiceImpl extends BaseServiceImpl implements
		ILoginLogService {

	@Autowired
	private ILoginLogDao loginLogDao; 
	
	
}
