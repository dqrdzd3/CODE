package com.hw.hwsafe.knowledge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.knowledge.action.K007Action;
import com.hw.hwsafe.knowledge.dao.IK007Dao;
import com.hw.hwsafe.knowledge.pojo.K007PO;
import com.hw.hwsafe.knowledge.service.IK007Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 
 * 项目名称：hwsafe 类名称：K007ServiceImpl 类描述： 创建人：李玉梅 创建时间：2012-6-6 下午2:59:50 修改人：李玉梅
 * 修改时间：2012-6-6 下午2:59:50 修改备注：
 * 
 * @version
 * 
 */
public class K007ServiceImpl extends BaseServiceImpl implements IK007Service {

	@Autowired
	private IK007Dao iK007Dao;
	@Autowired
	private IAttachmentService attachmentService;

	public List<K007PO> retrieveAllK007() throws Exception {
		return iK007Dao.retrieveAllK007();
	}

	public K007PO retrieveK007ByID(String k007ID) throws Exception {
		return iK007Dao.retrieveK007ByID(k007ID);
	}

	public List<K007PO> retrieveK007ByPO(K007PO k007PO) throws Exception {
		if (k007PO.getMA005().endsWith("0")) {
			k007PO.setMA005(null);
		}

		return iK007Dao.retrieveK007ByPO(k007PO);
	}

	public void insertK007(K007Action k007Action) throws Exception {
		K007PO k007PO = k007Action.getK007PO();
		String MA001 = UUIDGenerater.getUUID();
		k007PO.setMA001(MA001);
		k007PO.setMA014(k007Action.getSessionUserPO().getId());
		k007PO.setMA002(k007Action.getSessionUserPO().getCorpid());
		k007PO.setMA018(DateTimeUtils.getCurrentDate());
		iK007Dao.insertK007(k007PO);

		// 插入附件列表
		String[] c004ids = k007PO.getC004ids();
		attachmentService.insertC004s(c004ids, MA001);
	}

	public void updateK007(K007PO k007PO) throws Exception {
		iK007Dao.updateK007(k007PO);
		// 插入附件列表
		String[] c004ids = k007PO.getC004ids();
		attachmentService.insertC004s(c004ids, k007PO.getMA001());
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
		return iK007Dao.retrieveK007ByID(id) == null ? false : true;
	}
}
