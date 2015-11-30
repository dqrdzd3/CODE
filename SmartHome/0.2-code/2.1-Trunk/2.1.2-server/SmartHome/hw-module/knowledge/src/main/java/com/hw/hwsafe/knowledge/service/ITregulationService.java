/**
 * 文件名：ITregulationService.java
 *
 * 版本信息：1.0
 * 日期：2012-6-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service;

import java.util.List;
import java.util.Map;


import com.hw.hwsafe.knowledge.pojo.TregulationPO;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：ITregulationService
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-6 下午3:07:22
 * 修改人：
 * 修改时间：2012-6-6 下午3:07:22
 * 修改备注：
 * @version 
 * 
 */
public interface ITregulationService {
	
	/**
	 * 
	 * retrieveTregulationByID(根据Id查询）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public TregulationPO retrieveTregulationByID(String tregulationID) throws Exception;
	
	/**
	 * 	
	 * retrieveTregulationByPO(根据PO查询)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<TregulationPO> retrieveTregulationByPO(TregulationPO tregulationPO) throws Exception;
	
	/**
	 * 	
	 * insertTregulation(新增规章制度库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertTregulation(TregulationPO tregulationPO) throws Exception;
	
	/**
	 * 更新规章制度知识库	
	 * updateTregulation
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateTregulation(TregulationPO tregulationPO) throws Exception;
	
	/**
	 * 这里是函数说明
	 * @param map
	 * @throws Exception          
	 * @author:王贺禧
	 * @create_time:2013-9-17下午5:03:33
	 */
	public void updateTregulationSBSJ(Map<String, Object> map) throws Exception;
	
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
	/**
	 * 根据企业ID查询该企业下的所有规章制度
	 * @param corpid
	 */
	public List<TregulationPO> retrieveTregulationBycorpID(String corpid)throws Exception;
	public List<TregulationPO> retrieveTregulationBycorp(Map map)throws Exception;
}


