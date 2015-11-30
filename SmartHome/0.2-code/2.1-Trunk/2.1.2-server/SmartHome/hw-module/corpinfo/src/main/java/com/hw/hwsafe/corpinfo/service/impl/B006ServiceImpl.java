/**
 * 文件名：B006ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.corpinfo.dao.IB006Dao;
import com.hw.hwsafe.corpinfo.pojo.B006PO;
import com.hw.hwsafe.corpinfo.service.IB006Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B006ServiceImpl
 * 类描述：
 * 创建人：李玉梅
 * @version 
 * 
 */
public class B006ServiceImpl extends BaseServiceImpl implements IB006Service{

	@Autowired	
    private IB006Dao b006Dao;
	
	@Autowired
	private IAttachmentService attachmentService;

	public B006PO retrieveB006ByID(String b006ID)
			throws Exception {
		return b006Dao.retrieveB006ByID(b006ID);
	}
	/**
	 * 添加记录
	 */
	public void insertB006(B006PO b006PO)
			throws Exception {
		b006Dao.insertB006(b006PO);
		//插入附件列表
		attachmentService.insertC004s(b006PO.getC004ids(), b006PO.getMa001());
	}
     /**
      * 修改记录
      */
	public void updateB006(B006PO b006PO) throws Exception {
		b006Dao.updateB006(b006PO);
		attachmentService.insertC004s(b006PO.getC004ids(), b006PO.getMa001());
	}

	public List getListByMa002(String ma002) throws Exception {
		return null;
	}

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b006Dao.retrieveB006ByID(id) == null ? false : true;
	}
}
