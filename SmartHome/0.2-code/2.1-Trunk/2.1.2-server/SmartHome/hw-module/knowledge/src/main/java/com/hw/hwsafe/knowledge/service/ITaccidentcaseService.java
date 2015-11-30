/**
 * 文件名：IAccidentCasesService.java
 *
 * 版本信息：
 * 日期：2012-5-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.TaccidentcasePO;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：ITaccidentcaseService
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-4 上午10:22:00
 * 修改人：李玉梅
 * 修改时间：2012-6-4 上午10:22:00
 * 修改备注：
 * @version 
 *
 */
public interface ITaccidentcaseService extends BaseService {
	
	/**
	 * 
	 * retrieveTaccidentcaseByID(根据Id查询）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public TaccidentcasePO retrieveTaccidentcaseByID(String taccidentcaseID) throws Exception;
	
	/**
	 * 	
	 * retrieveTaccidentcaseByPO(根据PO查询)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<TaccidentcasePO> retrieveTaccidentcaseByPO(TaccidentcasePO taccidentcasePO) throws Exception;
	
	/**
	 * 	
	 * insertTaccidentcase(新增事故案例知识库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertTaccidentcase(TaccidentcasePO taccidentcasePO) throws Exception;
	
	/**
	 * 更新事故案例知识库	
	 * updateTaccidentcase
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateTaccidentcase(TaccidentcasePO taccidentcasePO) throws Exception;
	
	/**
	 * 
	 * isExist(记录是否存在)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isExist(String id)throws Exception;
}

