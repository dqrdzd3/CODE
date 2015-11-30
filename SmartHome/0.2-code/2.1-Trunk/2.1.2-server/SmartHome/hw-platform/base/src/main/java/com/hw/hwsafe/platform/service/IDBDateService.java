package com.hw.hwsafe.platform.service;

import java.util.Date;

import com.hw.hwsafe.platform.exception.system.SystemException;

public interface IDBDateService {
	
	/**
	 * 获取数据库服务器日期时间
	
	 * getDBDate
	 * @param   
	 * @return    Date
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Date getDBDate() throws SystemException;
}
