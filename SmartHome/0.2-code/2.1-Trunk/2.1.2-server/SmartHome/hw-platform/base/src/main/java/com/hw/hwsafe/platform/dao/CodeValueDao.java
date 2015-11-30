package com.hw.hwsafe.platform.dao;

import java.util.List;

import com.hw.hwsafe.platform.pojo.CodeValuePO;
/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：CodeValueDao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-7 上午10:31:01
 * 修改人：李玉梅
 * 修改时间：2012-6-7 上午10:31:01
 * 修改备注：
 * @version 
 *
 */
public interface CodeValueDao extends IBaseDao {
	
	/**
	 * 
	 * retrieveCodeByYalx(查询代码表：预案中的事故类型)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<CodeValuePO> retrieveCodeByYalx() throws Exception;
	
	/**
	 * 
	 * retrieveCodeByYalxZl(查询代码表：根据预案中的事故大类，查询对应的子类
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<CodeValuePO> retrieveCodeByYalxZl(String parent) throws Exception;
	
	/**
	 * 
	 * retrieveCodeByYalx(查询代码表：预案类型：综合、专项、现场)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<CodeValuePO> retrieveCodeByYazl() throws Exception;
	/**
	 * 事故案例中的 事故类型
	
	 * retrieveCodeByCaseType(这里用一句话描述这个方法的作用)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<CodeValuePO> retrieveCodeByCaseType() throws Exception;
	/**
	 * 事故案例中的 事故行业
	
	 * retrieveCodeByCaseType(这里用一句话描述这个方法的作用)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List<CodeValuePO> retrieveCodeBySghy() throws Exception;
	/**
	 * 
	 * @Title: retrieveCodeBySslb
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<CodeValuePO>
	 * @throws
	 */
	public List<CodeValuePO> retrieveCodeBySslb() throws Exception;
	/**
	 * 
		 * 
		 * 函 数 名：retrieveCodeByType
		 * 功能描述：根据不同类型查询不同类型下的代码表的值，只查询其parent=null的代码值
		 * 适用条件：
		 * 执行流程：
		 * 使用方法：
		 * 创建人：李玉梅
		 * 创建时间：2012-6-21下午3:21:00
		 * 修改人：
		 * 修改时间：
		 * 修改原因描述：
	 */
	public List<CodeValuePO> retrieveCodeByType(String type) throws Exception;
}
