/**
 * 文件名：IC006Service.java
 * 版本信息：
 * 日期：2012-12-21
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 */
package com.hw.hwsafe.register.service;

import java.util.List;

import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IBaseService;
import com.hw.hwsafe.register.pojo.C006PO;

/**
 * 
 * 
 * 项目名称：hw-cpnyreg
 * 类名称：IC006Service
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-12-21 下午5:08:03
 * 修改人：李玉梅
 * 修改时间：2012-12-21 下午5:08:03
 * 修改备注：
 * @version 
 *
 */
public interface IC006Service extends IBaseService {
	
/**
 * 

 * retrieveAllC006(查询所有列表
 * @param   李玉梅
 * @param  @return    设定文件
 * @return String    DOM对象
 * @Exception 异常对象
 * @since  CodingExample　Ver(编码范例查看) 1.1
 */
	public List<C006PO> retrieveAllC006() throws Exception;
/**

 * retrieveC006ByID(根据ID进行查询
 * @param   李玉梅
 * @param  @return    设定文件
 * @return String    DOM对象
 * @Exception 异常对象
 * @since  CodingExample　Ver(编码范例查看) 1.1
 */
	public C006PO retrieveC006ByID(String c006Id) throws Exception;
	/**
	 * 
	
	 * retrieveC006ByPO(根据PO进行查询
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */

	public List<C006PO> retrieveC006ByPO(C006PO c006PO) throws Exception;
	/**
	 * 
	
	 * insertC006(插入一条记录，返回值是插入成功与否的信息
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public UserMessageData insertC006(C006PO c006PO) throws Exception;
	/**
	 * 
	
	 * updateC006更新一条记录：返回结果是更新是否成功
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */

	public UserMessageData updateC006(C006PO c006PO) throws Exception;
	/**
	 * 
	
	 * deleteC006ByIds(删除一条记录，允许批量删除
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	
	public UserMessageData deleteC006ByIds(String ids) throws Exception;
	/**
	 * 
	
	 * isUniqueMa003(判断组织结构代码ma003是否唯一
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isUniqueMa003(C006PO c006PO)throws Exception;
	
	/**
	 * 
	
	 * isUniqueMa002(判断企业名称ma002是否唯一
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isUniqueMa002(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * isUniqueMa015(判断邮箱是否已经使用过
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isUniqueMa015(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * isUniqueMa010(注册的企业管理人员是已经否存在：重名
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isUniqueMa010(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * isUniqueMa014(注册的用户手机号码是否已经存在
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	
	public boolean isUniqueMa014(C006PO c006PO)throws Exception;
	/**
	 * 
	
	 * updateHCC006(政府端核查一条注册信息
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public UserMessageData updateHCC006(C006PO c006PO) throws Exception;
	/**
	 * 
	
	 * sendSMSNotice(判断短信发送是否成功
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean sendSMSNotice(C006PO c006po,int sendType) throws Exception;
	/**
	 * 
	
	 * retrieveC006ByNamePassword(根据用户名和密码查询是否
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public C006PO retrieveC006ByNamePassword(C006PO c006po) throws Exception;
	/**
	 * 获取顶级政府机构（父ID等于自身的）
	 */
	public List getZgdwList()throws Exception;
	/**
	 * 根据父ID获取该节点下的子节点
	 */
	public List getZgdwData(String pID)throws Exception;

}
