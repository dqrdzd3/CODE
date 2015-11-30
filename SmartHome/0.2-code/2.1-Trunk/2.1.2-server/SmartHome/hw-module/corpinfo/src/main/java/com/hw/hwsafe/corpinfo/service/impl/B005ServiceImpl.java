/**
 * 文件名：B005ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.corpinfo.dao.IB005Dao;
import com.hw.hwsafe.corpinfo.pojo.B005PO;
import com.hw.hwsafe.corpinfo.service.IB005Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B005ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:08:59
 * 修改人：
 * 修改时间：2012-6-11 下午6:08:59
 * 修改备注：
 * @version 
 * 
 */
public class B005ServiceImpl extends BaseServiceImpl implements IB005Service{

	@Autowired	
    private IB005Dao b005Dao;
	
	@Autowired
	private IAttachmentService attachmentService;
	
	private boolean flag=false;

	public B005PO retrieveB005ByID(String b005ID)
			throws Exception {
		return b005Dao.retrieveB005ByID(b005ID);
	}

	public List<B005PO> retrieveB005ByPO(B005PO b005PO) throws Exception {
		return b005Dao.retrieveB005ByPO(b005PO);
	}

	public void insertB005(B005PO b005PO)throws Exception{
		
		b005Dao.insertB005(b005PO);
		// 插入附件列表
		String[] c004ids = b005PO.getC004ids();
		attachmentService.insertC004s(c004ids, b005PO.getMA001());

	}
	/**
	 * 
	 * updateB005(修改记录)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public void updateB005(B005PO b005PO)
			throws Exception {
			b005Dao.updateB005(b005PO);
		   //插入附件
			attachmentService.insertC004s(b005PO.getC004ids(), b005PO.getMA001());
	}

	/**
	 * 
	 * @Title: checkCorpName
	 * @Description: TODO 
	 * 作者：刁海港
	 * @param @param b001PO
	 * @param @param b001Action
	 * @param @return
	 * @param @throws Exception
	 * @return boolean
	 * @throws
	 */
	public boolean checkCorpcertificate(B005PO b005PO)
          throws Exception {
		/**
		 * 查询企业证书编号是否重复
		 */
		B005PO retreiveb005PO = new B005PO();
		retreiveb005PO.setMA002(b005PO.getMA002());
		retreiveb005PO.setMA003(b005PO.getMA003());
		if (b005PO.getMA001() != null) {
			retreiveb005PO.setMA001(b005PO.getMA001());
		}
		int i = b005Dao.retrieveB005ByName(retreiveb005PO);
		if (i > 0){
			flag = true;
		}
		else{
			flag = false;
		}
		return flag;
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
		return b005Dao.retrieveB005ByID(id) == null ? false : true;
	}
	
	public List<B005PO> retrieveB005BycorpID(String corpid)throws Exception{
		
		return b005Dao.retrieveB005BycorpID(corpid);
	}
	public List<B005PO> retrieveB005ByCorp(Map map)throws Exception{
		
		return b005Dao.retrieveB005ByCorp(map);
	}

	@Override
	public void updateB005SBSJ(Map<String, Object> map) throws Exception {
		b005Dao.updateB005SBSJ(map);
	}
}
