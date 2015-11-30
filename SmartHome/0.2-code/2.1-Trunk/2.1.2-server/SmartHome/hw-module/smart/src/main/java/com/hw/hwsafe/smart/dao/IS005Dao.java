/**
 * 文件名：IK001Dao.java
 *
 * 版本信息：1.0
 * 日期：2012-09-29
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.smart.dao;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.platform.dao.IBaseDao;
import com.hw.hwsafe.smart.pojo.S005PO;

/**
 * S005Dao层接口
 * 
 */
public interface IS005Dao extends IBaseDao {
	// 点击某个主题回复
	public void addMessage(S005PO s005po) throws Exception;

	// 某个主题下的所有回复
	List<Map<String, Object>> getReplyList(String titleId) throws Exception;

	// 通过id删除实例
	void deleteInstanceById(String ma001) throws Exception;

	// 如果有人讨论过，则该主题不能删除
	int getCounts(String ma001) throws Exception;
}