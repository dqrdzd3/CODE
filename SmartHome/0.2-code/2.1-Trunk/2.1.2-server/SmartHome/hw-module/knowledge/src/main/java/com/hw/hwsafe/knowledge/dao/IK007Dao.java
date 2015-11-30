package com.hw.hwsafe.knowledge.dao;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K007PO;
import com.hw.hwsafe.platform.dao.IBaseDao;
/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：IK007Dao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-6 下午2:58:51
 * 修改人：李玉梅
 * 修改时间：2012-6-6 下午2:58:51
 * 修改备注：
 * @version 
 *
 */
public interface IK007Dao extends IBaseDao {
	
	/**
	 * 
	 * retrieveAllK007查询所有预案知识库
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<K007PO> retrieveAllK007() throws Exception;
	
	/**
	 * 
	 * retrieveK007ByID(根据Id查询）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public K007PO retrieveK007ByID(String k007ID) throws Exception;
	
	/**
	 * 	
	 * retrieveK007ByPO(根据PO查询)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<K007PO> retrieveK007ByPO(K007PO k007PO) throws Exception;
	
	/**
	 * 	
	 * insertK007(新增预案知识库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertK007(K007PO k007PO) throws Exception;
	
	/**
	 * 更新预案知识库	
	 * updateK007
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateK007(K007PO k007PO) throws Exception;
	
	/**
	 * 
	 * deleteK007ByID(根据ID删除记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void deleteK007ByID(String k007ID) throws Exception;
	
	
	
}
