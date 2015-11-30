/**
 * 文件名：TregulationServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-6
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.knowledge.dao.ITregulationDao;
import com.hw.hwsafe.knowledge.pojo.TregulationPO;
import com.hw.hwsafe.knowledge.service.ITregulationService;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：TregulationServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-6 下午3:09:50
 * 修改人：
 * 修改时间：2012-6-6 下午3:09:50
 * 修改备注：
 * @version 
 * 
 */
public class TregulationServiceImpl extends BaseServiceImpl implements ITregulationService{
	@Autowired	
	private ITregulationDao iTregulationDao;
	@Autowired
	private IAttachmentService attachmentService;
	
	public TregulationPO retrieveTregulationByID(String tregulationID)
			throws Exception {
		return iTregulationDao.retrieveTregulationByID(tregulationID);
	}

	public List<TregulationPO> retrieveTregulationByPO(
			TregulationPO tregulationPO) throws Exception {
		return iTregulationDao.retrieveTregulationByPO(tregulationPO);
	}
	/**
	 * 添加记录
	 */
	public void insertTregulation(TregulationPO tregulationPO)
			throws Exception {
		iTregulationDao.insertTregulation(tregulationPO);
		//插入附件列表
		attachmentService.insertC004s(tregulationPO.getC004ids(), tregulationPO.getMA001());
	}

	public void updateTregulation(TregulationPO tregulationPO)
			throws Exception {
		iTregulationDao.updateTregulation(tregulationPO);
		String[] c004ids = tregulationPO.getC004ids();
		attachmentService.insertC004s(c004ids, tregulationPO.getMA001());
	}

	public void deleteTregulationByID(String userID) throws Exception {
		iTregulationDao.deleteTregulationByID(userID);
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
		return iTregulationDao.retrieveTregulationByID(id) == null ? false : true;
	}
	/**
	 * 根据企业ID查询该企业的所有规章制度
	 */
	public List<TregulationPO> retrieveTregulationBycorpID(String corpid)throws Exception{
		return iTregulationDao.retrieveTregulationBycorpID(corpid);
	}
	public List<TregulationPO> retrieveTregulationBycorp(Map map)throws Exception{
		return iTregulationDao.retrieveTregulationBycorp(map);
	}

	@Override
	public void updateTregulationSBSJ(Map<String, Object> map) throws Exception {
		iTregulationDao.updateTregulationSBSJ(map);
	}
}
