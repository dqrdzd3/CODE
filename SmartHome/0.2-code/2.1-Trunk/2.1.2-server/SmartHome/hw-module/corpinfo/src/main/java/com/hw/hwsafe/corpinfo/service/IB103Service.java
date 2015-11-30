/**
 * 文件名：IB103Service.java
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

import com.hw.hwsafe.corpinfo.pojo.B103PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB103Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-12 上午10:20:24
 * 修改人：
 * 修改时间：2012-6-12 上午10:20:24
 * 修改备注：
 * @version 
 * 
 */
public interface IB103Service extends IBaseService {
	/**
	 * 
	 * @Title: retrieveB103ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b103ID
	 * @param @return
	 * @param @throws Exception
	 * @return B103PO
	 * @throws
	 */
	public B103PO retrieveB103ByID(String b103ID) throws Exception;
	/**
	 * 
	 * @Title: insertB103
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b103PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB103(B103PO b103PO) throws Exception;
	/**
	 * 
	 * @Title: updateB103
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b103PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB103(B103PO b103PO) throws Exception;
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
	 * 删除管理人员
	 * @param ids
	 * @throws Exception
	 */
	public void delb103(String ids,String ryType)throws Exception;
	
	
	public int getB002Stat(String ryid)throws Exception;
	
	
//	/**
//	 * 查询政府端查看企业特殊工种人员情况
//	 * @param map
//	 * @return
//	 */
//	public List<Map<String, Object>> retrieveByPage1(Map map) ;
	
//	/**
//	 * 通过企业ID和员工姓名查询员工基本信息
//	 */
//	public List getInfoByname(B103PO b103PO)throws Exception;

}
