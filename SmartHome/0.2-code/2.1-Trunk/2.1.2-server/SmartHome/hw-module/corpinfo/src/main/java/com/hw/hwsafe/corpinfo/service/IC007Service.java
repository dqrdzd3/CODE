/**
 * 文件名：IC007ionaryService.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.IBaseService;


/**
 * 
 * 项目名称：framework
 * 类名称：IC007ionaryService
 * 类描述：系统字典service接口类
 * 创建人：付强
 * 创建时间：2012-8-8 上午11:28:41
 * 
 */
public interface IC007Service extends IBaseService {

	/**
	 * 函 数 名：retrieveC007ById
	 * 功能描述：主键查询一条字典数据
	 * @param id 主键
	 * @return C007PO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:16
	 */
	public C007PO retrieveC007ById(String id)throws Exception;
	
	/**
	 * 函 数 名：retrieveC007ionaryByKey
	 * 功能描述：唯一key查询一条字典数据
	 * @param key 编码
	 * @return C007PO 字典子项数据
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-13 下午12:20:55
	 */
	public C007PO retrieveC007ByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveC007ByPO
	 * 功能描述：依据查询条件获取字典列表
	 * @param C007PO 字典pojo的查询条件
	 * @return List<C007PO> 字典pojo集合
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午2:36:28
	 */
	public List<C007PO> retrieveC007ByPO(C007PO C007PO)throws Exception;
	
	/**
	 * 函 数 名：retrieveChildC007ListByKey
	 * 功能描述：获取key下面的所有子项
	 * @param key 键
	 * @return List<C007PO> 字典子项集合
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:15:46
	 */
	public List<C007PO> retrieveChildC007ListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveChildC007ListByKey
	 * 功能描述：获取key下面的所有子项
	 * @param key 键
	 * @param levelNum 层级数
	 * @return List<C007PO> 字典子项集合
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:15:46
	 */
	public List<C007PO> retrieveDirectChildC007ListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveTerminalChildC007ListByKey
	 * 功能描述：获取key下面末端子项列表
	 * @param key 键
	 * @return List<C007PO> 字典子项集合
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:27:46
	 */
	public List<C007PO> retrieveTermC007ListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：countC007ByKey
	 * 功能描述：检查Key编码的唯一性
	 * @param key 编码
	 * @param id 节点主键
	 * @return 是否唯一 
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:15:05
	 */
	public Boolean isUniqueKey(C007PO C007PO)throws Exception;
	
	/**
	 * 函 数 名：isUniqueValueUnderParentNode
	 * 功能描述：检查父节点下value的唯一性
	 * @param value 值
	 * @param parentid 父节点主键
	 * @return 是否唯一
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-12-27 下午2:41:33
	 */
	public Boolean isUniqueValueUnderParentNode(C007PO C007PO)throws Exception;
	
	 /**
	 * 函 数 名：insertC007
	 * 功能描述：新增一条字典数据
	 * @param C007PO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:27
	 */
	public UserMessageData insertC007(C007PO C007PO) throws Exception;
	
	/**
	 * 函 数 名：updateC007
	 * 功能描述：更新一条字典数据
	 * @param C007PO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:35
	 */
	public UserMessageData updateC007(C007PO C007PO) throws Exception;
	
	/**
	 * 函 数 名：deleteC007ById
	 * 功能描述：删除主键ids集合对应的字典数据
	 * @param ids 字典主键值集合
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:42
	 */
	public UserMessageData deleteC007ByIds(String ids) throws Exception;
	/**
	 * 
	
	 * getProvinceList(这里用一句话描述这个方法的作用)
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHymList() throws Exception;
	/**
	 * 
	
	 * getHydlList(这里用一句话描述这个方法的作用)
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHydlList(String parentid) throws Exception;
	/**
	 * 
	
	 * getHyzlList(这里用一句话描述这个方法的作用)
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHyzlList(String parentid) throws Exception;
	/**
	 * 
	
	 * getHyxlList(这里用一句话描述这个方法的作用)
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHyxlList(String parentid) throws Exception;
	/**
	 * 根据Value获得C007PO
	 */
	public C007PO getC007POByValue(String value) throws Exception;
	
	/**
	 * 这里是函数说明
	 * @return          
	 * @author 陈浙东
	 * @create_time 2013-5-16 下午2:36:04
	 */
		
	public List<C007PO> getHymlPOList();

	/**
	 * retrieveChildC007ListByParent查询第一级子数据
	 * @param id
	 * @return
	 */
	public List<C007PO> retrieveChildC007ListByParent(String id) throws Exception;
}
