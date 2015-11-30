package com.hw.hwsafe.knowledge.dao;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.TcorpambPO;
import com.hw.hwsafe.platform.dao.IBaseDao;
/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：ITcorpambDao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-5 下午9:01:20
 * 修改人：李玉梅
 * 修改时间：2012-6-5 下午9:01:20
 * 修改备注：
 * @version 
 *
 */
public interface ITcorpambDao extends IBaseDao {
	
	/**
	 * 
	 * retrieveAllTcorpamb查询所有预案知识库
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<TcorpambPO> retrieveAllTcorpamb() throws Exception;
	
	/**
	 * 
	 * retrieveTcorpambByID(根据Id查询）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public TcorpambPO retrieveTcorpambByID(String tcorpambID) throws Exception;
	
	/**
	 * 	
	 * retrieveTcorpambByPO(根据PO查询)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<TcorpambPO> retrieveTcorpambByPO(TcorpambPO tcorpambPO) throws Exception;
	
	/**
	 * 	
	 * insertTcorpamb(新增预案知识库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertTcorpamb(TcorpambPO tcorpambPO) throws Exception;
	
	/**
	 * 更新预案知识库	
	 * updateTcorpamb
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateTcorpamb(TcorpambPO tcorpambPO) throws Exception;
	
	/**
	 * 
	 * deleteTcorpambByID(根据ID删除记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteTcorpambByID(String tcorpambID) throws Exception;
	
	
	public List<TcorpambPO> reduplicateTcorpamb(TcorpambPO tcorpambPO)throws Exception;
	
	
	
}
