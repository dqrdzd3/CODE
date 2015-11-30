/**
 * 文件名：IB001Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B001PO;
import com.hw.hwsafe.corpinfo.pojo.S099PO;
import com.hw.hwsafe.platform.dao.IBaseDao;


/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB001Dao
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:02:28
 * 修改人：
 * 修改时间：2012-6-11 下午6:02:28
 * 修改备注：
 * @version 
 * 
 */
public interface IB001Dao extends IBaseDao {
	
	/**
	 * 
	 * @Title: retrieveAllB001
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @return
	 * @param @throws Exception
	 * @return List<B001PO>
	 * @throws
	 */
	public List<B001PO> retrieveAllB001() throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB001ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b001ID
	 * @param @return
	 * @param @throws Exception
	 * @return B001PO
	 * @throws
	 */
	public B001PO retrieveB001ByID(String b001ID) throws Exception;
	
	/**
	 * 
	 * @Title: retrieveB001ByPO
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b001PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B001PO>
	 * @throws
	 */
	public List<B001PO> retrieveB001ByPO(B001PO b001PO) throws Exception;
	
	/**
	 * 
	 * @Title: insertB001
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b001PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB001(B001PO b001PO) throws Exception;
	
	/**
	 * 
	 * @Title: updateB001
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b001PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB001(B001PO b001PO) throws Exception;
	
	/**
	 * 
	 * updateB001XY(根据ID修改X和Y坐标
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateB001XY(B001PO b001PO) throws Exception;
	
	
	public void updateCorpSBSJ(String corpid) throws Exception;
	public void updateCorpSBSJ2(Map map) throws Exception;
	
	/**
	 * 
	 * @Title: deleteB001ByID
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param userID
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void deleteB001ByID(String userID) throws Exception;
	
	/**
	 * 
	 * 
	 * 
	 * 函 数 名： retrieveB001ByName 
	 * 功能描述：添加企业信息时查询企业名称是否重复 
	 * 适用条件： 
	 * 执行流程： 
	 * 使用方法：
	 * 创建人：刁海港 
	 * 创建时间：2012-7-24 上午10:11:21 
	 * 修改人： 
	 * 修改时间：2012-7-24 上午10:11:21 
	 * 修改原因描述：
	 */
	public int retrieveB001ByName(B001PO  b001PO) throws Exception;
	
	public int upretrieveB001ByName(B001PO b005PO) throws Exception;

	public List getProvinceList() throws Exception;
	
	public List getCityList(String provinceid) throws Exception;
	
	public List getCountyList(String cityid) throws Exception;
	
	/**
	 * 
	 * @Title: "retrieveB001ByPinyin"
	 * @Description: TODO 
	 * 作者：wangyong
	 * @param @param ma069
	 * @param @return
	 * @param @throws Exception
	 * @return B001PO
	 * @throws
	 */
	public List retrieveB001ByPinyin(Map map) throws Exception;
	
	/**
	 * 根据类型统计
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List govStat(HashMap<String, String> pmap) throws Exception;
	
	/**
	 * 市局统计企业信息
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public List govStatsj(HashMap<String, String> pmap) throws Exception;
	
	/**
	 * 企业基础信息上报
	 * @param ma001
	 * @throws Exception
	 */
	public void report2Gov(String corpId) throws Exception;
	
	/**
	 * 政府审核
	 * @param ma001
	 * @return
	 * @throws Exception
	 */
	public String govAudit(String corpId,String auditType) throws Exception;
	
	public List getAuditList(String type) throws Exception;
	/**
	 * 函 数 名：updateCorpinfo
	 * 功能描述：修改为审核的企业信息
	 * 创建人：刁海港
	 * 创建时间：2012-12-8 下午3:17:48
	 */
	public void updateCorpinfo(String id)throws Exception;
	
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
	 * 根据企业Id检索该企业下的所有危险源信息
	 * @param corpid
	 * @return
	 * @throws Exception
	 */
	public List<HashMap<String, Object>> retrieveDangerByCorpid(String corpid) throws Exception ;
	/**
	 * 根据企业ID获取企业的危化品种类数量
	 * @param corpid
	 */
	public int retrieveWhpTypeNum(String corpid)throws Exception;
	
	/**
	 * 
	 * retrieveS099ByCorpid(根据企业ID查询企业安全情况登记)
	 */
	public S099PO retrieveS099ByCorpid(String corpid)throws Exception;
	/**
	 * 插入企业安全生产登记信息
	 */
	public void insertS099(S099PO s099PO)throws Exception;
	/**
	 * 修改企业安全生产登记信息
	 */
	public void updateS099(S099PO s099PO)throws Exception;
	
	/**
	 * 根据政府ID查询所辖的企业
	 */
	public List<B001PO> retrieveAllB001ByGovID(String govID)throws Exception;
	
	public List<Map<String, Object>> retrieveByPageHxwz(Map<String,Object > map) throws Exception ;
	
	/**
	 * 根据企业id查询上级主管单位名称
	 * @return
	 * @throws Exception
	 */
	public String getGovName(String corpid)throws Exception;
}
