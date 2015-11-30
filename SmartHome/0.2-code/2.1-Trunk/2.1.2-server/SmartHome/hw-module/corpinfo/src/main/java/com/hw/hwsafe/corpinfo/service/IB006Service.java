/**
 * 文件名：IB006Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B006PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB006Service
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-12 上午10:20:36
 * 修改人：
 * 修改时间：2012-6-12 上午10:20:36
 * 修改备注：
 * @version 
 * 
 */
public interface IB006Service extends IBaseService {

	/**
	 * 
	 * @Title: retrieveB006ByID
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b006ID
	 * @param @return
	 * @param @throws Exception
	 * @return B006PO
	 * @throws
	 */
	public B006PO retrieveB006ByID(String b006ID) throws Exception;
	/**
	 * 
	 * @Title: insertB006
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b006PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB006(B006PO b006PO) throws Exception;
	/**
	 * 
	 * @Title: updateB006
	 * @Description: TODO 
	 * 作者：李玉梅
	 * @param @param b006PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB006(B006PO b006PO) throws Exception;
	/**
	 * 通过企业id查询周边环境
	 * @param ma002
	 * @return
	 * @throws Exception
	 */
	public List getListByMa002(String ma002) throws Exception;
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
