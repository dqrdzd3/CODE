/**
 * 文件名：IB00401Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.B00401PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB00401Service
 * 类描述：
 * 创建人：wy
 * 创建时间：2012-6-12 上午10:20:36
 * 修改人：
 * 修改时间：2012-6-12 上午10:20:36
 * 修改备注：
 * @version 
 * 
 */
public interface IB00401Service extends IBaseService {

	
	/**
	 * 
	 * @Title: retrieveAllB00401
	 * @Description: TODO 
	 * 作者：wy
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00401PO>
	 * @throws
	 */
	public List<B00401PO> retrieveAllB00401() throws Exception;
	/**
	 * 
	 * @Title: retrieveB00401ByID
	 * @Description: TODO 
	 * 作者：wy
	 * @param @param B00401ID
	 * @param @return
	 * @param @throws Exception
	 * @return B00401PO
	 * @throws
	 */
	public B00401PO retrieveB00401ByID(String b00401ID) throws Exception;
	/**
	 * 
	 * @Title: retrieveB00401ByPO
	 * @Description: TODO 
	 * 作者：wy
	 * @param @param B00401PO
	 * @param @return
	 * @param @throws Exception
	 * @return List<B00401PO>
	 * @throws
	 */
	public List<B00401PO> retrieveB00401ByPO(B00401PO b00401PO) throws Exception;
	/**
	 * 
	 * @Title: insertB00401
	 * @Description: TODO 
	 * 作者：wy
	 * @param @param B00401PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void insertB00401(B00401PO b00401PO) throws Exception;
	/**
	 * 
	 * @Title: updateB00401
	 * @Description: TODO 
	 * 作者：wy
	 * @param @param B00401PO
	 * @param @throws Exception
	 * @return void
	 * @throws
	 */
	public void updateB00401(B00401PO b00401PO) throws Exception;
	
	/**
	 * 
	 * 函 数 名：retrieveB00401ByPinyin
	 * 功能描述：查询条件提示功能
	 * @param @param map
	 * @param @return
	 * @param @throws Exception
	 * @return List
	 * 创建人：王勇
	 * 创建时间：2012-12-6 下午2:10:11
	 */
	public List retrieveB00401ByPinyin(Map map) throws Exception;
	
	/**
	 * 
	 * 函 数 名：statDevide
	 * 功能描述：统计设备
	 * @param @return
	 * @param @throws Exception
	 * @return List
	 * 创建人：王勇
	 * 创建时间：2012-12-7 下午2:08:42
	 */
	public List statDevide(String id) throws Exception;
	
	/**
	 * 函 数 名：getGroupDangerCategory
	 * 功能描述：危险源类别统计
	 * @param map
	 * @return List<HashMap<String,Object>>
	 * 创建人：王贺喜
	 * 创建时间：2012-11-21 上午9:56:53
	 */
	public List<HashMap<String, Object>> getGroupDangerCategory(HashMap<String, Object> map) ;
	
	/**
	 * 函 数 名：getGroupDangerLevel
	 * 功能描述：危险源级别统计
	 * @param map
	 * @throws Exception
	 * @return HashMap<String,Object>
	 * 创建人：王贺喜
	 * 创建时间：2012-11-27 下午5:38:44
	 */
	public List<HashMap<String, Object>> getGroupDangerLevel(HashMap<String, Object> map) ;
	/**
	 * 查询政府端查看企业设备设施情况
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> retrieveByPage1(Map map) ;
	/**
	 * 函 数 名：retrieveByPage2
	 * 功能描述：危险源级别统计分页展示
	 * @param map
	 * @throws Exception
	 * @return List<Map<String,Object>>
	 * 创建人：王贺喜
	 * 创建时间：2012-11-27 下午3:09:02
	 */
	public List<Map<String, Object>> retrieveByPage2(Map map) ;
	/**
	 * 函 数 名：retrieveByPage3
	 * 功能描述：危险源详情分页展示
	 * @param map
	 * @throws Exception
	 * @return List<Map<String,Object>>
	 * 创建人：王贺喜
	 * 创建时间：2012-11-27 下午3:09:02
	 */
	public List<Map<String, Object>> retrieveByPage3(Map map) ;
	/**
	 * isExist(查询记录是否存在)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public boolean isExist(String id) throws Exception; 
}
