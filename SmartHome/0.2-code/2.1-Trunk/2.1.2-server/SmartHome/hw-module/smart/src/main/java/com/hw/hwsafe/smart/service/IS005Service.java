package com.hw.hwsafe.smart.service;

import java.util.List;
import java.util.Map;

import com.hw.hwsafe.smart.pojo.S005PO;
/**
 * 
 * <p>
 * 回复讨论区接口<br>
 * </p>
 */
public interface IS005Service {
	//点击某个主题回复
	public void addMessage(S005PO s005po) throws Exception;
	//某个主题下的所有回复
	List<Map<String, Object>> getReplyList(String titleId) throws Exception;
	 // 通过id删除实例
	void deleteInstanceById(String ma001) throws Exception;
	//如果有人讨论过，则该主题不能删除
	int getCounts(String ma001) throws Exception;
}
