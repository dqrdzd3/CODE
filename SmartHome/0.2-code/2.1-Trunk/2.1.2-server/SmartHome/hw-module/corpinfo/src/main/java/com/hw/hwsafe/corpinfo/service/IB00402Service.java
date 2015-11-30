/**
 * 文件名：IB00402Service.java
 *
 * 版本信息：1.0
 * 日期：2012-6-12
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service;

import java.util.List;

import com.hw.hwsafe.corpinfo.pojo.B00402PO;
import com.hw.hwsafe.platform.service.IBaseService;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：IB00401Service
 * 类描述：设备设施从表
 * 创建人：dhg
 */
public interface IB00402Service extends IBaseService {

	
	/**
	 * 根据ID查询设备设施从表信息
	 */
	public B00402PO retrieveB00402ByID(String b00402ID) throws Exception;
	/**
	 * 根据条件查询设备设施从表信息
	 */
	public List<B00402PO> retrieveB00402ByPO(B00402PO b00402PO) throws Exception;
	/**
	 * 添加设备设施的从表信息
	 * @Title: insertB00402
	 */
	public void insertB00402(B00402PO b00402PO) throws Exception;
	/**
	 * 修改设备设施的从表信息
	 */
	public void updateB00402(B00402PO b00402PO) throws Exception;
	/**
	 * 修改记录时查询记录是否存在
	 */
	public boolean isExist(String id)throws Exception;
	
	
}
