package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.smart.pojo.S001PO;
import com.hw.hwsafe.smart.pojo.S004PO;

public interface IS001Service {
	
	/**
	 * 通过id获取实例
	 */
	S001PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(S001PO s001PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(S001PO s001PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;
	
	/**
	 * 通过id批量删除实例
	 */
	Integer delBatchS001(Map<String, Object> map) throws Exception;
	/**
	 * 
	 * 获取问答列表
	 * @return
	 * @throws Exception          
	 * @author liyuhao
	 * @create_time 2014年7月23日下午7:10:34
	 */
    List<Map<String, Object>> getQAList() throws Exception;

}
