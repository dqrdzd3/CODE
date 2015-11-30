/**
 * 文件名：B103ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.corpinfo.dao.IB103Dao;
import com.hw.hwsafe.corpinfo.pojo.B103PO;
import com.hw.hwsafe.corpinfo.service.IB103Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B103ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:08:30
 * 修改人：
 * 修改时间：2012-6-11 下午6:08:30
 * 修改备注：
 * @version 
 * 
 */
public class B103ServiceImpl extends BaseServiceImpl implements IB103Service {

	@Autowired	
    private IB103Dao b103Dao;
	private boolean flag=false;
	@Autowired
	private IAttachmentService attachmentService;


	public B103PO retrieveB103ByID(String b103ID) throws Exception {
		return b103Dao.retrieveB103ByID(b103ID);
	}

	/**
	 * 添加人员信息
	 */
	public void insertB103(B103PO b103PO) throws Exception {
		String[] c004ids = b103PO.getC004ids();
		attachmentService.insertC004s(c004ids, b103PO.getMA001());
		b103Dao.insertB103(b103PO);
		if(b103PO.getMA024().equals("2")){
			b103Dao.updateB002AqryNum(b103PO.getMA005());
		}

	}

	/**
	 * 修改人员信息
	 */
	public void updateB103(B103PO b103PO) throws Exception {
		b103Dao.updateB103(b103PO);
		String[] c004ids = b103PO.getC004ids();
		attachmentService.insertC004s(c004ids, b103PO.getMA001());
	}

	public int getB002Stat(String ryid)throws Exception{
		
		return b103Dao.getB002Stat(ryid);
	}
	public void delb103(String ids,String ryType)throws Exception{
		
		String[] idArr = ids.split(",");
		int stat = b103Dao.getB002Stat(idArr[0]);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("ids", idArr);
		if("2".equals(ryType)){
			HashMap<String, Object> aqrymap = new HashMap<String, Object>();
			aqrymap.put("len", idArr.length);
			aqrymap.put("ryid", idArr[0]);
			b103Dao.updateB002AqryNumtj(aqrymap);
		}
		b103Dao.delb103(map);
	}
//	/**
//	 * 检查人员编号是否重复
//	 */
//	public boolean checkCorpNum(B103PO b103PO) throws Exception {
//		B103PO b103po = new B103PO();
//		b103po.setMA002(b103PO.getMA002());
//		b103po.setMA103(SessionUtil.getOrgId());
//		if (b103PO.getMA001() != null) {
//			b103po.setMA001(b103PO.getMA001());
//		}
//		int i = b103Dao.retrieveB001ByName(b103po);
//		if (i > 0)
//			flag = true;
//		else
//			flag = false;
//		return flag;
//	}
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id) throws Exception {
		return b103Dao.retrieveB103ByID(id) == null ? false : true;
	}

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}
	
//	@Override
//	public List<Map<String, Object>> retrieveByPage1(Map map) {
//		try {
//			return b103Dao.retrieveByPage1(map);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	
}
