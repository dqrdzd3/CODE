/**
 * 文件名：IDictionaryService.java
 *
 * 版本信息：
 * 日期：2012-8-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.platform.service;

import java.util.List;

import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.pojo.DictionaryPO;

/**
 * 
 * 项目名称：framework
 * 类名称：IDictionaryService
 * 类描述：系统字典service接口类
 * 创建人：付强
 * 创建时间：2012-8-8 上午11:28:41
 * 
 */
public interface IDictionaryService extends IBaseService {

	/**
	 * 函 数 名：retrieveDictById
	 * 功能描述：主键查询一条字典数据
	 * @param id 主键
	 * @return DictionaryPO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:16
	 */
	public DictionaryPO retrieveDictById(String id)throws Exception;
	
	/**
	 * 函 数 名：retrieveDictionaryByKey
	 * 功能描述：唯一key查询一条字典数据
	 * @param key 编码
	 * @return DictionaryPO 字典子项数据
	 * 异    常： Exception
	 * 创建人：付强
	 * 创建时间：2012-8-13 下午12:20:55
	 */
	public DictionaryPO retrieveDictByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveDictByPO
	 * 功能描述：依据查询条件获取字典列表
	 * @param dictPO 字典pojo的查询条件
	 * @return List<DictionaryPO> 字典pojo集合
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午2:36:28
	 */
	public List<DictionaryPO> retrieveDictByPO(DictionaryPO dictPO)throws Exception;
	
	/**
	 * 函 数 名：retrieveChildDictListByKey
	 * 功能描述：获取key下面的所有子项
	 * @param key 键
	 * @return List<DictionaryPO> 字典子项集合
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:15:46
	 */
	public List<DictionaryPO> retrieveChildDictListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveChildDictListByKey
	 * 功能描述：获取key下面的所有子项
	 * @param key 键
	 * @param levelNum 层级数
	 * @return List<DictionaryPO> 字典子项集合
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:15:46
	 */
	public List<DictionaryPO> retrieveDirectChildDictListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：retrieveTerminalChildDictListByKey
	 * 功能描述：获取key下面末端子项列表
	 * @param key 键
	 * @return List<DictionaryPO> 字典子项集合
	 * 异    常：Exception
	 * 创建人：付强
	 * 创建时间：2012-8-11 下午5:27:46
	 */
	public List<DictionaryPO> retrieveTermDictListByKey(String key)throws Exception;
	
	/**
	 * 函 数 名：countDictByKey
	 * 功能描述：检查Key编码的唯一性
	 * @param key 编码
	 * @param id 节点主键
	 * @return 是否唯一 
	 * 创建人：付强
	 * 创建时间：2012-8-8 下午1:15:05
	 */
	public Boolean isUniqueKey(DictionaryPO dictPO)throws Exception;
	
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
	public Boolean isUniqueValueUnderParentNode(DictionaryPO dictPO)throws Exception;
	
	 /**
	 * 函 数 名：insertDict
	 * 功能描述：新增一条字典数据
	 * @param dictPO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:27
	 */
	public UserMessageData insertDict(DictionaryPO dictPO) throws Exception;
	
	/**
	 * 函 数 名：updateDict
	 * 功能描述：更新一条字典数据
	 * @param dictPO 字典pojo
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:35
	 */
	public UserMessageData updateDict(DictionaryPO dictPO) throws Exception;
	
	/**
	 * 函 数 名：deleteDictById
	 * 功能描述：删除主键ids集合对应的字典数据
	 * @param ids 字典主键值集合
	 * 创建人：付强
	 * 创建时间：2012-8-8 上午11:30:42
	 */
	public UserMessageData deleteDictionaryByIds(String ids) throws Exception;
}
