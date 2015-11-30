package com.hw.hwsafe.attachment.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.dao.IC004Dao;
import com.hw.hwsafe.attachment.pojo.C004PO;
import com.hw.hwsafe.attachment.service.IAttachmentService;

/**
 * 附件service
 */
public class AttachmentServiceImpl implements IAttachmentService {

	@Autowired
	private IC004Dao c004Dao;

	@Override
	public void insertC004TEMP(C004PO c004) throws Exception {
		c004Dao.insertC004TEMP(c004);
	}

	@Override
	public void insertC004(C004PO c004) throws Exception {
		c004Dao.insertC004(c004);
	}

	/**
	 * 下载数据
	 * 
	 * @param c004
	 * @throws Exception
	 */
	@Override
	public C004PO getC004PO(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("MA001", id);
		C004PO po = c004Dao.getC004(map);
		return po;
	}

	@Override
	public List<C004PO> getC004List(String id, String MA008) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("MA002", id);
		map.put("MA008", MA008);
		return c004Dao.getC004List(map);
	}

	/**
	 * 批量添加c004的数据，从c004_temp表中
	 * 
	 * @param c004ids
	 * @param 主表主键
	 * @throws Exception
	 */
	@Override
	public void insertC004s(String[] c004ids, String MA001) throws Exception {
		if (c004ids != null && c004ids.length > 0) {
			c004Dao.delAllC004(MA001);
			for (String id : c004ids) {
				if (id != null && id.length() > 0) {
					Map<String, String> map = new HashMap<String, String>();
					map.put("MA001", id);
					C004PO c004po = c004Dao.getC004TEMP(map);
					c004po.setMA002(MA001);

					c004Dao.insertC004(c004po);
				}
			}
		}
	}

	@Override
	public C004PO getC004POTEMP(String id) throws Exception {
		Map<String, String> map = new HashMap<String, String>();

		map.put("MA001", id);
		C004PO po = c004Dao.getC004TEMP(map);
		return po;
	}

	@Override
	public void deleteC004(String id) throws Exception {
		c004Dao.delC004(id);

	}

	@Override
	public void deleteC004TEMP(String id) {
		c004Dao.delC004Temp(id);

	}

	// -------------- getter and setter ---------------

	public IC004Dao getC004Dao() {
		return c004Dao;
	}

	public void setC004Dao(IC004Dao c004Dao) {
		this.c004Dao = c004Dao;
	}

	
	/*
	(non-Javadoc)
	* @see com.hw.hwsafe.attachment.service.IAttachmentService#deleteAllC004(java.lang.String)
	*/
		
	@Override
	public void deleteAllC004(String ma002) throws Exception {
		c004Dao.delAllC004(ma002);
		
	}
}
