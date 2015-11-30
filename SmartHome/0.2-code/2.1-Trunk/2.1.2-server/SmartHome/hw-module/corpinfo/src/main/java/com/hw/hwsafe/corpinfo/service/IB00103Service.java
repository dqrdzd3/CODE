package com.hw.hwsafe.corpinfo.service;

import com.hw.hwsafe.corpinfo.pojo.B00103PO;

public interface IB00103Service {
	

	/**
	 * 通过id获取实例
	 */
	B00103PO retrieveInstanceById(String ma001) throws Exception;

	/**
	 * 添加实例
	 */
	void insertInstance(B00103PO b00103PO) throws Exception;

	/**
	 * 修改实例
	 */
	void updateInstance(B00103PO b00103PO) throws Exception;

	/**
	 * 通过id删除实例
	 */
	void deleteInstanceById(String ma001) throws Exception;

}
