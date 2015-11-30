package com.hw.hwsafe.knowledge.service;

import java.util.List;

import com.hw.hwsafe.knowledge.action.K007Action;
import com.hw.hwsafe.knowledge.pojo.K007PO;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：IK007Service
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-5 下午8:54:56
 * 修改人：李玉梅
 * 修改时间：2012-6-5 下午8:54:56
 * 修改备注：
 * @version 
 *
 */
public interface IK007Service {

	
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
	 * insertK007(新增危化品知识库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertK007(K007Action k007Action) throws Exception;
	
	/**
	 * 更新危化品知识库	
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
	 * isExist(记录是否存在)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isExist(String id)throws Exception;
}
