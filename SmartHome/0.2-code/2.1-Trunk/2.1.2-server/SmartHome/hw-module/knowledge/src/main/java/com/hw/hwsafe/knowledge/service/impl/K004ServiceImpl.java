/**
 * 文件名：K004ServiceImpl.java
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
import com.hw.hwsafe.knowledge.dao.IK004Dao;
import com.hw.hwsafe.knowledge.pojo.K004PO;
import com.hw.hwsafe.knowledge.service.IK004Service;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.message.UserMessageData;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：K004ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-8 下午3:15:40
 * 修改人：
 * 修改时间：2012-6-8 下午3:15:40
 * 修改备注：
 * @version 
 * 
 */
public class K004ServiceImpl extends BaseServiceImpl implements IK004Service{
	
	@Autowired	
	private IK004Dao iK004Dao;
	@Autowired
	private IAttachmentService attachmentService;
	
	/**
	 * 根据主键查询记录
	 */
	public K004PO retrieveK004ByID(String k004ID) throws Exception {
		return iK004Dao.retrieveK004ByID(k004ID);
	}
	/**
	 * 根据条件查询记录
	 */
	public List<K004PO> retrieveK004ByPO(K004PO k004PO) throws Exception {
		return iK004Dao.retrieveK004ByPO(k004PO);
	}
     /**
      * 添加记录
      */
	public UserMessageData insertK004(K004PO k004PO) throws Exception {
		List<K004PO> list = iK004Dao.retrieveK004ByNameAndCode(k004PO);
		if (list != null && list.size() > 0) {
			return new UserMessageData(Constants.MSG_ERROR, "名称或编号已存在！");
		}
		
		iK004Dao.insertK004(k004PO);
		//插入附件列表
		attachmentService.insertC004s(k004PO.getC004ids(), k004PO.getMa001());
		return new UserMessageData(Constants.MSG_OK, Constants.ADD_SUCCESS_MSG);
	}
	/**
     * 修改记录
     */
	public UserMessageData updateK004(K004PO k004PO) throws Exception {
		UserMessageData msg = new UserMessageData();
		K004PO oldPO = iK004Dao.retrieveK004ByID(k004PO.getMa001());
		if (oldPO != null) {
			List<K004PO> list = iK004Dao.retrieveK004ByNameAndCode(k004PO);
			if (list == null || list.size() == 0 || (list.size() == 1 && list.get(0).getMa001().equals(k004PO.getMa001()))) {
				iK004Dao.updateK004(k004PO);
				// 插入附件列表
				attachmentService.insertC004s(k004PO.getC004ids(), k004PO.getMa001());
				msg.set(Constants.MSG_OK, Constants.UPDATE_SUCCESS_MSG);
			} else {
				msg.set(Constants.MSG_ERROR, "名称或编号已存在！");
			}
		} else {
			msg.set(Constants.MSG_ERROR, Constants.EMPTY_DATA_MSG);
		}
		return msg;
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
		return iK004Dao.retrieveK004ByID(id) == null ? false : true;
	}
}
