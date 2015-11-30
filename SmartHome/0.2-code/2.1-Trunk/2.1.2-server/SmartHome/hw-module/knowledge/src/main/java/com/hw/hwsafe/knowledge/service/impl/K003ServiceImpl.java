/**
 * 文件名：K003ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-8
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.knowledge.dao.IK003Dao;
import com.hw.hwsafe.knowledge.pojo.K003PO;
import com.hw.hwsafe.knowledge.service.IK003Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：K003ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午3:15:40
 * 修改人：
 * 修改时间：2012-6-8 下午3:15:40
 * 修改备注：
 * @version 
 * 
 */
public class K003ServiceImpl extends BaseServiceImpl implements IK003Service{
	
	@Autowired	
	private IK003Dao iK003Dao;
	@Autowired
	private IAttachmentService attachmentService;
	
	public List<K003PO> retrieveAllK003() throws Exception {
		return iK003Dao.retrieveAllK003();
	}

	public K003PO retrieveK003ByID(String k003ID) throws Exception {
		return iK003Dao.retrieveK003ByID(k003ID);
	}

	public List<K003PO> retrieveK003ByPO(K003PO k003PO) throws Exception {
		return iK003Dao.retrieveK003ByPO(k003PO);
	}
	/**
	 * 添加记录
	 */
	public void insertK003(K003PO k003PO) throws Exception {
		
		iK003Dao.insertK003(k003PO);
		//插入附件列表
		attachmentService.insertC004s(k003PO.getC004ids(),k003PO.getMa001());
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return iK003Dao.retrieveK003ByID(id) == null ? false : true;
	}

	public void updateK003(K003PO k003PO) throws Exception {
		
		iK003Dao.updateK003(k003PO);
		attachmentService.insertC004s(k003PO.getC004ids(),k003PO.getMa001());
	}
	
	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
}
