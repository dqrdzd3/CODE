package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.smart.pojo.S001PO;
import com.hw.hwsafe.smart.pojo.S004PO;
import com.hw.hwsafe.smart.pojo.S006PO;


/**
 * 
 * @author 李羽皓
 *
 */

public interface IS004Service {
	//主题列表
	List<S004PO> getListTitle() throws Exception;
	/**
	 * 添加实例
	 */
	void insertInstance(S004PO s004PO) throws Exception;
	/**
	 * 通过id批量删除实例
	 */
	Integer delBatchS004(Map<String, Object> map) throws Exception;
	/**
	 * 通过id获取实例
	 */
	S004PO retrieveInstanceById(String ma001) throws Exception;
	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
}
