package com.hw.hwsafe.knowledge.service;

import java.util.List;
import com.hw.hwsafe.knowledge.pojo.TcorpambPO;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：ITcorpambService
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-5 下午8:54:56
 * 修改人：李玉梅
 * 修改时间：2012-6-5 下午8:54:56
 * 修改备注：
 * @version 
 *
 */
public interface ITcorpambService {

	
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
	 * insertTcorpamb(新增危化品知识库）
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertTcorpamb(TcorpambPO tcorpambPO) throws Exception;
	
	/**
	 * 更新危化品知识库	
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
	 * isExist(记录是否存在)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isExist(String id)throws Exception;
	
}
