package com.hw.hwsafe.quartz;

import java.util.Date;
import java.util.Map;

public interface ISchedulerManageService {
	
	/**
	 * 添加job
	 * addJob
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void addJob(Map map,Class classz) throws RuntimeException;
	
	/**
	 * 删除job
	 * deleteJob
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteJob(Map map) throws RuntimeException;
	
	/**
	 * 暂停job
	 * pauseJob
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void pauseJob(Map map) throws RuntimeException;
	
	/**
	 * 恢复job
	 * resumeJob
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void resumeJob(Map map) throws RuntimeException;
	
	/**
	 * 暂停trigger
	 * pauseTrigger
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void pauseTrigger(Map map) throws RuntimeException;
	
	/**
	 * 恢复trigger
	 * resumeTrigger
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void resumeTrigger(Map map) throws RuntimeException;
	
	/**
	 * 恢复trigger
	 * deleteTrigger
	 * @param   
	 * @return    void
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteTrigger(Map map) throws RuntimeException;
	
	/**
	 * 获取当前日期的触发器表达式形式
	 * getCronExpression
	 * @param   
	 * @return    String
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public  String getCronExpression();
	
	/**
	 * 获取日期字符串的触发器的表达式形式）
	 * getCronExpression
	 * @param   
	 * @return    String
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public  String getCronExpression(Date date);
	
}
