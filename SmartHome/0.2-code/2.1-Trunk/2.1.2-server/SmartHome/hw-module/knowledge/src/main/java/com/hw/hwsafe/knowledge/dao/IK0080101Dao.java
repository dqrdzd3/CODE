
/**
 * @title IK0080101Dao.java
 * @package com.hw.hwsafe.knowledge.dao
 * @author 王贺禧
 * @create_time 2013-8-27 下午1:58:07
 * <p>Copyright 河南汉威电子股份有限公司软件部 Corporation 2013</p>
 */
	
package com.hw.hwsafe.knowledge.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.knowledge.pojo.K0080101PO;
import com.hw.hwsafe.platform.dao.IBaseDao;


/**
 * <p>
 * 这是写类描述，如果有换行则用<br>
 * </p>
 */

public interface IK0080101Dao extends IBaseDao {
	
	/**
	 * 通过id获取实例
	 */
	K0080101PO retrieveInstanceById(String ma001) throws Exception;
	
	/**
	 * 根据法律法规ID和章节ID得到所有条数
	 */
	List<K0080101PO> retrieveByPId(Map<String, Object> map) throws Exception; 

	/**
	 * 添加实例
	 */
	Integer insertInstance(K0080101PO k0080101PO) throws Exception;

	/**
	 * 修改实例
	 */
	Integer updateInstance(K0080101PO k0080101PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	Integer deleteInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过pid删除实例
	 */
	Integer deleteByPId(String pid) throws Exception;

}
