/**
 * 文件名：IB008Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B008PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：IB008Service
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-8-23 上午10:29:36
 * 修改人：李玉梅
 * 修改时间：2012-8-23 上午10:29:36
 * 修改备注：
 * @version 
 *
 */
public interface IB008Service extends IBaseService {

/**
 * 
 * retrieveB008ByID(这里用一句话描述这个方法的作用)
 * @param   李玉梅
 * @param  @return    设定文件
 * @return String    DOM对象
 * @Exception 异常对象
 * @since  CodingExample　Ver(编码范例查看) 1.1
 */
	public B008PO retrieveB008ByID(String b008ID) throws Exception;
/**
 * 

 * retrieveB008ByPO(这里用一句话描述这个方法的作用)
 * @param   李玉梅
 * @param  @return    设定文件
 * @return String    DOM对象
 * @Exception 异常对象
 * @since  CodingExample　Ver(编码范例查看) 1.1
 */
	public List<B008PO> retrieveB008ByPO(B008PO b008PO) throws Exception;
	/**
	 * 
	 * @Title: insertB008
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b008PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB008(B008PO b008PO) throws Exception;
	/**
	 * 
	 * @Title: updateB008
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b008PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB008(B008PO b008PO) throws Exception;
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
