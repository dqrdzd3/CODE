
/**
 * @title IK008Service.java
 * @package com.hw.hwsafe.knowledge.service
 * @author 王贺禧
 * @create_time 2013-8-27 上午11:33:59
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.service;

import java.util.Map;

import com.hw.hwsafe.knowledge.pojo.K008PO;
import com.hw.hwsafe.platform.service.IBaseService;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public interface IK008Service extends IBaseService {
	
	/**
	 * 通过id获取实例
	 */
	K008PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(K008PO k008PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(K008PO k008PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	Integer deleteInstanceById(Map<String, Object> map) throws Exception;

}
