/**
 * 文件名：IB001Service.java
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

import com.hw.hwsafe.corpinfo.pojo.B001PO;
import com.hw.hwsafe.corpinfo.pojo.S099PO;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IBaseService;


/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB001Service
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-12 上午10:19:44
 * 修改人：
 * 修改时间：2012-6-12 上午10:19:44
 * 修改备注：
 * @version 
 * 
 */
@SuppressWarnings("rawtypes")
public interface IB001Service extends IBaseService {
	/**
	 * 
	 * retrieveB001ByID(通过主键查询企业信息)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public B001PO retrieveB001ByID(String b001ID) throws Exception;
	
	/**
	 * 
	 * retrieveB001ByPO(通过条件查询企业列表)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	
	public List<B001PO> retrieveB001ByPO(B001PO b001PO) throws Exception;
	
	/**
	 * 
	 * insertB001(插入企业基本信息)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void insertB001(B001PO b001PO) throws Exception;
	
	/**
	 * 
	 * updateB001(修改企业信息)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateB001(B001PO b001PO) throws Exception;
	
	/**
	 * 
	 * getprovinceList(获取省列表)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getprovinceList() throws Exception;
	/**
	 * 
	 * getcityList(获取市列表)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getcityList(String provinceId) throws Exception;
	/**
	 * 
	 * getcountyList(获取县区列表)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getcountyList(String cityId) throws Exception;
	/**
	 * 
	 * @Title: retrieveB001ByPinyin
	 * @Description: TODO 
	 * 作者：wangyong
	 * @param @param b001PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B001PO>
	 * @throws
	 */
	public List<B001PO> retrieveB001ByPinyin(Map map) throws Exception;
	
	/**
	 * 
	 * govStat(统计企业信息)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List govStat(String type,String cpnyType) throws Exception;
	
	/**
	 * 
	 * govStat(市局统计企业信息)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List govStatsj(String type,String cpnyType) throws Exception;
	
	public void report2Gov(String ma001) throws Exception;
	
	public String govAudit(String corpId,String auditType) throws Exception ;
	
	public List getAuditList(String auditType) throws Exception;
	
	/**
	 * 函 数 名：retrieveDangerByCorpId
	 * 功能描述：根据企业Id检索该企业下的所有危险源信息
	 * @param  corpid
	 * @throws Exception
	 * @return List<HashMap<String,Object>>
	 * 创建人：王贺喜
	 * 创建时间：2012-9-22 上午11:37:31
	 */
	public List<Map<String, Object>> retrieveDangerByCorpId(Map<String, Object> map) throws Exception ;
    /**
     * 
     * checkCorpName(验证企业名称是否重复)
     * @param   name
     * @param  @return    设定文件
     * @return String    DOM对象
     * @Exception 异常对象
     * @since  CodingExample　Ver(编码范例查看) 1.1
     */
	public boolean checkCorpName(B001PO b001PO)throws Exception;
	/**
	 * 根据企业ID获取企业下的危化品种类数量
	 * @param corpind
	 */
	public int retrieveWhpTypeNum(String corpind)throws Exception;
	/**
	 * 
	 * retrieveS099ByCorpid(这里用一句话描述这个方法的作用)
	 */
	public S099PO retrieveS099ByCorpid(String corpid)throws Exception;
	
	/**
	 * 插入企业安全生产基础信息
	 * @param s099PO
	 */
	public void insertS099(S099PO s099PO)throws Exception;
	/**
	 * 修改企业安全生产基础信息
	 */
	public void updateS099(S099PO s099PO)throws Exception;
	/**
	 *根据政府ID查询该机构管辖下的企业
	 */
	public List<B001PO> retrieveAllB001ByGovID(String govID)throws Exception;
	
	public List<Map<String, Object>> retrieveByPageHxwz(Map<String,Object > map) throws Exception;
	
	/**
	 * 根据企业id查询上级主管单位名称
	 * @return
	 * @throws Exception
	 */
	public String getGovName(String corpid)throws Exception;
}
