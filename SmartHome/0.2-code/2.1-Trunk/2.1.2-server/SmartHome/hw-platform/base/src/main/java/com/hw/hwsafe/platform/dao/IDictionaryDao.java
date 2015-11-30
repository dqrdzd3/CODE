/**
 * 文件名：IDictionaryDao.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.pojo.DictionaryPO;

/**
 * 
 * 项目名称：framework
 * 类名称：IDictionaryDao
 * 类描述：系统字典Dao接口类
 * 创建人：付强
 * 创建时间：2012-8-8 上午11:18:37
 * 
 */
public interface IDictionaryDao extends IBaseDao {

	/**
	 * 函 数 名：retrieveDictionaryById
	 * 功能描述：主键查询一条字典数据
	 * 输入参数：
	 * @param id 主键
	 * 返 回 值： 
	 * @return DictionaryPO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:23:10
	 */
	public DictionaryPO retrieveDictionaryById(String id)throws Exception;
	
	/**
	 * 函 数 名：retrieveDictByKey
	 * 功能描述：唯一key查询一条字典数据
	 * 输入参数：
	 * @param key 编码
	 * 返 回 值：
	 * @return DictionaryPO 字典子项数据
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-13 下午12:20:55
	 */
	public DictionaryPO retrieveDictByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveDictByPO
	 * 功能描述：依据查询条件获取字典列表
	 * 输入参数：
	 * @param dictPo 字典pojo的查询条件
	 * 返 回 值：
	 * @return List<DictionaryPO> 字典pojo集合
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午2:25:01
	 */
	public List<DictionaryPO> retrieveDictByPO(DictionaryPO dictPo)throws Exception;
	
	/**
	 * 函 数 名：retrieveChildDictListByKey
	 * 功能描述：获取key下面的所有子项
	 * @param key 编码
	 * @return 字典项列表
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:15:46
	 */
	public List<DictionaryPO> retrieveChildDictListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：countDictByKey
	 * 功能描述：依据代码统计字典数据条数
	 * @param key 代码
	 * @param id 节点主键
	 * @return 被使用的记录条数
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:04:45
	 */
	public Integer countDictByKey(DictionaryPO dictPO)throws Exception;
	
	/**
	 * 函 数 名：countDictByValueUnderParentNode
	 * 功能描述：统计父节点下value被使用的记录条数
	 * @param value 值
	 * @param parentid 父节点主键
	 * @param id 节点主键
	 * @return 被使用的记录条数
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-12-27 下午2:47:53
	 */
	public Integer countDictByValueUnderParentNode(DictionaryPO dictPO)throws Exception;
	
	/**
	 * 函 数 名：countDictByOrdernum
	 * 功能描述：(这里描述这个方法适用条件/执行流程/使用方法/注意事项)
	 * @param ordernum 排序号
	 * @param parentid 父节点主键
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2012-12-26 上午8:57:37
	 */
	public Integer countDictByOrdernum(DictionaryPO dictPO)throws Exception;
	
	/**
	 * 函 数 名：insertDictionary
	 * 功能描述：新增一条字典数据
	 * @param dictPO 字典PO
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:24:32
	 */
	public Integer insertDictionary(DictionaryPO dictPO) throws Exception;
	
	/**
	 * 函 数 名：updateDictionary
	 * 功能描述：更新一条字典数据
	 * @param dictPO 字典PO
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:25:26
	 */
	public Integer updateDictionary(DictionaryPO dictPO) throws Exception;
	
	/**
	 * 函 数 名：updateOrdernumIncrease
	 * 功能描述：排序自增长
	 * @param ordernum 排序号
	 * @param parentid 父节点主键
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2012-12-26 上午8:51:26
	 */
	public Integer updateOrdernumIncrease(DictionaryPO dictPO)throws Exception;
	
	/**
	 * 函 数 名：deleteDictionaryById
	 * 功能描述：删除主键id对应的字典数据
	 * @param id 字典主键值
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:26:10
	 */
	public Integer deleteDictionaryByIds(Map<String,Object> args) throws Exception;
	
	/**
	 * 函 数 名：deleteChildDictByKey
	 * 功能描述：依据key逻辑删除子字典项
	 * @param key 节点key
	 * @return 受影响记录数
	 * 创建人：付强
	 * 创建时间：2013-1-5 下午3:45:28
	 */
	public Integer deleteChildDictByKey(String key) throws Exception;
}
