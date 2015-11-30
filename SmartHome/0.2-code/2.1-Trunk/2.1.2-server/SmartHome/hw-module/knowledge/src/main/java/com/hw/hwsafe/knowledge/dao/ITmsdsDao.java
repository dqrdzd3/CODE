package com.hw.hwsafe.knowledge.dao;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.TmsdsPO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：ITmsdsDao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-5-29 下午1:04:16
 * 修改人：李玉梅
 * 修改时间：2012-5-29 下午1:04:16
 * 修改备注：
 * @version 
 *
 */
public interface ITmsdsDao extends IBaseDao {
	
	/**
	 * 
	 * retrieveAllTmsds查询所有危化品知识库
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<TmsdsPO> retrieveAllTmsds() throws Exception;
	
	/**
	 * 
	 * retrieveTmsdsByID(根据Id查询）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public TmsdsPO retrieveTmsdsByID(String tmsdsID) throws Exception;
	
	/**
	 * 	
	 * retrieveTmsdsByPO(根据PO查询)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<TmsdsPO> retrieveTmsdsByPO(TmsdsPO tmsdsPO) throws Exception;
	
	/**
	 * 	
	 * insertTmsds(新增危化品知识库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertTmsds(TmsdsPO tmsdsPO) throws Exception;
	
	/**
	 * 更新危化品知识库	
	 * updateTmsds
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateTmsds(TmsdsPO tmsdsPO) throws Exception;
	
	/**
	 * 
	 * deleteTmsdsByID(根据ID删除记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteTmsdsByID(String userID) throws Exception;

	/**
	 * 
	 * 函 数 名：countByName
	 * 功能描述：通过名称查询实例数
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午5:10:17
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public int countByName(String name) throws Exception;

	/**
	 * 
	 * 函 数 名：countByIdAndName
	 * 功能描述：通过标识和名称查询实例数
	 * 创建人：马宁
	 * 创建时间：2012-10-23 下午5:11:12
	 * 修改人：
	 * 修改时间：
	 * 修改原因描述：
	 */
	public int countByIdAndName(TmsdsPO po) throws Exception;
	/**
	 * 李玉梅于2013年6月6日添加
	 * @param chName:危化品名称
	 * @return根据危化品名称进行查询
	 * @throws Exception
	 */
	public TmsdsPO retrieveTmsdsByChname(String chName) throws Exception;
	
	
}
