/**
 * 文件名：ITregulationDao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.knowledge.pojo.TregulationPO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：ITregulationDao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-6 下午3:13:10
 * 修改人：
 * 修改时间：2012-6-6 下午3:13:10
 * 修改备注：
 * @version 
 * 
 */
public interface ITregulationDao extends IBaseDao {
	/**
	 * 
	 * retrieveAllTregulation查询规章制度库
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<TregulationPO> retrieveAllTregulation() throws Exception;
	
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
	 * insertTaccidentcase(新增规章制度库）
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
	public void updateCorpSBSJ(String corpId) throws Exception;
	public void updateCorpSBSJ2(Map map) throws Exception;
	
	/**
	 * 
	 * deleteTregulationByID(根据ID删除记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteTregulationByID(String userID) throws Exception;
	/**
	 * 根据企业ID查询规章制度
	 * @param corpid
	 * @return
	 * @throws Exception
	 */
	public  List<TregulationPO> retrieveTregulationBycorpID(String corpid)throws Exception;
	public  List<TregulationPO> retrieveTregulationBycorp(Map map)throws Exception;
	
}
