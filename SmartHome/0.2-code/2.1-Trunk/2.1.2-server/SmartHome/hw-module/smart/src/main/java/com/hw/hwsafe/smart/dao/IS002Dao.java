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
import com.hw.hwsafe.smart.pojo.S002PO;
import com.hw.hwsafe.smart.pojo.U001PO;

/**
 * S002Dao层接口
 *
 */
public interface IS002Dao extends IBaseDao {

	/**
	 * 获取所有的要推送的人
	 */
	List<U001PO> retrieveInstance() throws Exception;
	/**
	 * 获取所有省份
	 */
	List<U001PO> retrieveProvinceList() throws Exception;
	/**
	 * 获取所有市
	 */
	List<U001PO> retrieveCityList() throws Exception;
	/**
	 * 获取所有区
	 */
	List<U001PO> retrieveAreaList() throws Exception;
	 /**
     * 
     * 通过姓名模糊搜索
     * @param name
     * @return          
     * @author liyuhao
     * @create_time 2014年7月11日下午4:54:17
     */
	List<U001PO> retrieveInstanceByName(String name)throws Exception;
	/**
	 * 
	 * 将值插入到推送记录表
	 * @param s002PO
	 * @throws Exception          
	 * @author liyuhao
	 * @create_time 2014年7月14日下午2:21:11
	 */
	public void insertInstance(S002PO s002PO) throws Exception;
	/**
	 * 通过id批量删除推送记录
	 */
	Integer delBatchS002(Map<String, Object> map) throws Exception;
	/**
	 * 通过id获取推送记录
	 */
	S002PO retrieveInstanceById(String ma001) throws Exception;
	
}