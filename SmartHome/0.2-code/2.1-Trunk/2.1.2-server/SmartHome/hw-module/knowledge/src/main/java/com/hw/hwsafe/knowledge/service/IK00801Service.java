
/**
 * @title IK00801Service.java
 * @package com.hw.hwsafe.knowledge.service
 * @author 王贺禧
 * @create_time 2013-8-27 下午2:03:25
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.service;

import java.util.List;

import com.hw.hwsafe.knowledge.pojo.K00801PO;
import com.hw.hwsafe.platform.service.IBaseService;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public interface IK00801Service extends IBaseService {
	
	
	/**
	 * 通过id获取实例
	 */
	K00801PO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 根据父ID得到List
	 */
	List<K00801PO> retrieveByPId (String pid) throws Exception;

	/**
	 * 添加实例
	 */
	Integer insertInstance(K00801PO k00801PO) throws Exception;

	/**
	 * 修改实例
	 */
	Integer updateInstance(K00801PO k00801PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	Integer deleteInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过pid删除实例
	 */
	Integer deleteByPId(String pid) throws Exception;

}
