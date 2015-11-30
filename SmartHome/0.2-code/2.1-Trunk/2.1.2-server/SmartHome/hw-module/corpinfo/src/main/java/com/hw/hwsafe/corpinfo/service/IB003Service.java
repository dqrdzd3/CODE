/**
 * 文件名：IB003Service.java
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

import com.hw.hwsafe.corpinfo.pojo.B003PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB003Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-12 上午10:20:24
 * 修改人：
 * 修改时间：2012-6-12 上午10:20:24
 * 修改备注：
 * @version 
 * 
 */
public interface IB003Service extends IBaseService {
	/**
	 * 
	 * @Title: retrieveB003ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003ID
	 * @param @return
	 * @param @throws Exception
	 * @return B003PO
	 * @throws
	 */
	public B003PO retrieveB003ByID(String b003ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB003ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B003PO>
	 * @throws
	 */
	public List<B003PO> retrieveB003ByPO(B003PO b003PO) throws Exception;
	/**
	 * 
	 * @Title: insertB003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB003(B003PO b003PO) throws Exception;
	/**
	 * 
	 * @Title: updateB003
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b003PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB003(B003PO b003PO) throws Exception;
	/**
	 * 
	 * checkCorpNum(检查人员编号是否重复)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean checkCorpNum(B003PO b003PO)throws Exception;
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
	 * 查询政府端查看企业特殊工种人员情况
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> retrieveByPage1(Map map) ;
	
	/**
	 * 通过企业ID和员工姓名查询员工基本信息
	 */
	public List getInfoByname(B003PO b003PO)throws Exception;

}
