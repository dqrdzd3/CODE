/**

 * 文件名：IC006Dao.java
 * 版本信息：
 * 日期：2012-12-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.register.dao;

import java.util.List;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.register.pojo.C006PO;

/**
 * 
 * 
 * 项目名称：hw-cpnyreg
 * 类名称：IC006Dao
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-12-21 下午5:06:36
 * 修改人：李玉梅
 * 修改时间：2012-12-21 下午5:06:36
 * 修改备注：
 * @version 
 *
 */
public interface IC006Dao extends IBaseDao {


	public List<C006PO> retrieveAllC006() throws Exception;

	public C006PO retrieveC006ByID(String c006Id) throws Exception;

	public List<C006PO> retrieveC006ByPO(C006PO c006PO) throws Exception;

	public Integer insertC006(C006PO c006PO) throws Exception;
	

	public Integer updateC006(C006PO c006PO) throws Exception;
	/**
	 * 
	
	 * updateHCC006(政府端核查一条企业信息
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Integer updateHCC006(C006PO c006PO) throws Exception;
	
	public Integer deleteC006ByIds(String ids) throws Exception;
	/**
	 * 
	
	 * countC006ByMa003(依据企业名称是否唯一
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Integer countC006ByMa002(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * countC006ByMa003(依据组织机构代码统计企业数
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Integer countC006ByMa003(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * countC006ByMa003(根据邮箱是否使用
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Integer countC006ByMa015(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * countC006ByMa010(注册的用户名是否已经存在
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Integer countC006ByMa010(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * countC006ByMa014(注册的手机号码是否存在
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public Integer countC006ByMa014(C006PO c006PO)throws Exception;
	
	public C006PO retrieveC006ByNamePassword(C006PO c006PO) throws Exception;
	
	public List getZgdwList()throws Exception;
	
	public List getZgdwData(String pId)throws Exception;
	
	
	

}
