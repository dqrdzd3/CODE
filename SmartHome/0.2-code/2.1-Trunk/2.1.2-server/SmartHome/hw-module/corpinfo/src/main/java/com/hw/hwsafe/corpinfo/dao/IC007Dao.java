/**
 * 文件名：IC007Dao.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.platform.dao.IBaseDao;

/**
 * 
 * 项目名称：framework
 * 类名称：IC007Dao
 * 类描述：系统字典Dao接口类
 * 创建人：付强
 * 创建时间：2012-8-8 上午11:18:37
 * 
 */
public interface IC007Dao extends IBaseDao {

	/**
	 * 函 数 名：retrieveC007ById
	 * 功能描述：主键查询一条字典数据
	 * 输入参数：
	 * @param id 主键
	 * 返 回 值： 
	 * @return C007PO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:23:10
	 */
	public C007PO retrieveC007ById(String id)throws Exception;
	
	/**
	 * 函 数 名：retrieveC007ByKey
	 * 功能描述：唯一key查询一条字典数据
	 * 输入参数：
	 * @param key 编码
	 * 返 回 值：
	 * @return C007PO 字典子项数据
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-13 下午12:20:55
	 */
	public C007PO retrieveC007ByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveC007ByPO
	 * 功能描述：依据查询条件获取字典列表
	 * 输入参数：
	 * @param C007Po 字典pojo的查询条件
	 * 返 回 值：
	 * @return List<C007PO> 字典pojo集合
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午2:25:01
	 */
	public List<C007PO> retrieveC007ByPO(C007PO C007Po)throws Exception;
	
	/**
	 * 函 数 名：retrieveChildC007ListByKey
	 * 功能描述：获取key下面的所有子项
	 * @param key 编码
	 * @return 字典项列表
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:15:46
	 */
	public List<C007PO> retrieveChildC007ListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：countC007ByKey
	 * 功能描述：依据代码统计字典数据条数
	 * @param key 代码
	 * @param id 节点主键
	 * @return 被使用的记录条数
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:04:45
	 */
	public Integer countC007ByKey(C007PO C007PO)throws Exception;
	
	/**
	 * 函 数 名：countC007ByValueUnderParentNode
	 * 功能描述：统计父节点下value被使用的记录条数
	 * @param value 值
	 * @param parentid 父节点主键
	 * @param id 节点主键
	 * @return 被使用的记录条数
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-12-27 下午2:47:53
	 */
	public Integer countC007ByValueUnderParentNode(C007PO C007PO)throws Exception;
	
	/**
	 * 函 数 名：countC007ByOrdernum
	 * 功能描述：(这里描述这个方法适用条件/执行流程/使用方法/注意事项)
	 * @param ordernum 排序号
	 * @param parentid 父节点主键
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2012-12-26 上午8:57:37
	 */
	public Integer countC007ByOrdernum(C007PO C007PO)throws Exception;
	
	/**
	 * 函 数 名：insertC007
	 * 功能描述：新增一条字典数据
	 * @param C007PO 字典PO
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:24:32
	 */
	public Integer insertC007(C007PO C007PO) throws Exception;
	
	/**
	 * 函 数 名：updateC007
	 * 功能描述：更新一条字典数据
	 * @param C007PO 字典PO
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:25:26
	 */
	public Integer updateC007(C007PO C007PO) throws Exception;
	
	/**
	 * 函 数 名：updateOrdernumIncrease
	 * 功能描述：排序自增长
	 * @param ordernum 排序号
	 * @param parentid 父节点主键
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2012-12-26 上午8:51:26
	 */
	public Integer updateOrdernumIncrease(C007PO C007PO)throws Exception;
	
	/**
	 * 函 数 名：deleteC007ById
	 * 功能描述：删除主键id对应的字典数据
	 * @param id 字典主键值
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:26:10
	 */
	public Integer deleteC007ByIds(Map<String,Object> args) throws Exception;
	
	/**
	 * 函 数 名：deleteChildC007ByKey
	 * 功能描述：依据key逻辑删除子字典项
	 * @param key 节点key
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2013-1-5 下午3:45:28
	 */
	public Integer deleteChildC007ByKey(String key) throws Exception;
	/**
	 * 
	
	 * getHymlList(获得行业门类列表
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHymlList() throws Exception;
	/**
	 * 
	
	 * getHydlList(获得行业大类列表，根据父节点ID
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHydlList(String parentid) throws Exception;
	/**
	 * 
	
	 * getHyzlList(获得行业中类列表：根据父节点ＩＤ
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHyzlList(String parentid) throws Exception;
	/**
	 * 
	
	 * getHylList(获得行业小类列表，根据父节点ＩＤ
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public List getHyxlList(String parentid) throws Exception;
	/**
	 * 根据Value获得C007PO
	
	 * getC007POByValue(这里用一句话描述这个方法的作用)
	 * @param   李玉梅
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public C007PO getC007POByValue(String value) throws Exception;
	
	/**
	 * 这里是函数说明
	 * @return          
	 * @author 陈浙东
	 * @create_time 2013-5-16 下午2:36:37
	 */
		
	public List<C007PO> getHymlPOList();
	
	/**
	 * retrieveChildC007ListByParent
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public List<C007PO> retrieveChildC007ListByParent(String partentid)throws Exception;
	
}
