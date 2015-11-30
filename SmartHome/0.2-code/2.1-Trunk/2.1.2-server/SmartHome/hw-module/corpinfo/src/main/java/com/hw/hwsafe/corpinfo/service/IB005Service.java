/**
 * 文件名：IB005Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B005PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB005Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-12 上午10:20:46
 * 修改人：
 * 修改时间：2012-6-12 上午10:20:46
 * 修改备注：
 * @version 
 * 
 */
public interface IB005Service extends IBaseService {

	/**
	 * 
	 * @Title: retrieveB005ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005ID
	 * @param @return
	 * @param @throws Exception
	 * @return B005PO
	 * @throws
	 */
	public B005PO retrieveB005ByID(String b005ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB005ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B005PO>
	 * @throws
	 */
	public List<B005PO> retrieveB005ByPO(B005PO b005PO) throws Exception;
	/**
	 * 
	 * @Title: insertB005
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB005(B005PO b005PO) throws Exception;
	/**
	 * 
	 * @Title: updateB005
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB005(B005PO b005PO) throws Exception;
	
	
	/**
	 * 
	 * @Title: updateB005
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b005PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	
	public void updateB005SBSJ(Map<String, Object> map) throws Exception;
	/**
	 * 
	 * checkCorpcertificate(检查证书编号是否重复)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean checkCorpcertificate(B005PO b005PO)throws Exception;
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
	 * 根据企业ID查询该企业的执照信息
	 */
	public  List<B005PO> retrieveB005BycorpID(String corpid) throws Exception;
	public  List<B005PO> retrieveB005ByCorp(Map map) throws Exception;
}
