/**
 * 文件名：B002ServiceImpl.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.corpinfo.dao.IB002Dao;
import com.hw.hwsafe.corpinfo.pojo.B002PO;
import com.hw.hwsafe.corpinfo.service.IB002Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;

/**
 * 
 * 项目名称：hw-corpinfo
 * 类名称：B002ServiceImpl
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2013-4-8 下午3:26:14
  * @version 
 *
 */
public class B002ServiceImpl extends BaseServiceImpl implements IB002Service {

	@Autowired
	private IB002Dao b002Dao;
	private boolean flag = false;
	/**
	 * 添加企业机构信息
	 * 
	 */
	public void insertB002(B002PO b002PO) throws Exception {

		b002Dao.insertB002(b002PO);
	}

	/**
	 * 修改企业机构信息
	 * 
	 */
	public void updateB002(B002PO b002PO) throws Exception {
		b002Dao.updateB002(b002PO);
	}
	
	/**
	 * 查询记录是否存在
	 */
	public boolean isExist(String id)throws Exception{
		
		return b002Dao.retrieveB002ByID(id)==null?false:true;
	}

	/**
	 * 
	 * 函 数 名： checkCorpName 
	 * 功能描述： 
	 * 适用条件：
	 * 执行流程： 
	 * 使用方法： 
	 * 创建人：刁海港 
	 * 创建时间：2012-7-24  上午10:49:31 
	 * 修改人： 
	 * 修改时间：2012-7-24 上午10:49:31 
	 * 修改原因描述：
	 */
	public boolean checkCorpName(B002PO b002PO) throws Exception {
		/**
		 * 查询企业机构名称是否重复
		 */
		String Name = b002PO.getMA004();// 企业机构名称
		String corpId = SessionUtil.getOrgId();// 企业机构ID
		B002PO retreb002PO = new B002PO();
		if (b002PO.getMA001() != null) {
			retreb002PO.setMA001(b002PO.getMA001());
		}
		retreb002PO.setMA003(corpId);
		retreb002PO.setMA004(Name);
		int i = b002Dao.upretrieveB002ByName(retreb002PO);
		if (i > 0)
			flag = true;
		else
			flag = false;
		return flag;
	}
	public List<B002PO> retrieveAllB002() throws Exception {
		return b002Dao.retrieveAllB002();
	}
	public List<B002PO> retrieveB002ByCorp(Map map) throws Exception {
		return b002Dao.retrieveB002ByCorp(map);
	}

	public B002PO retrieveB002ByID(String b002ID) throws Exception {
		return b002Dao.retrieveB002ByID(b002ID);
	}

	public List<B002PO> retrieveB002ByPO(B002PO b002PO) throws Exception {
		return b002Dao.retrieveB002ByPO(b002PO);
	}

	@Override
	public void updateB002SBSJ(HashMap<String, Object> map) throws Exception {
		b002Dao.updateB002SBSJ(map);
	}
}
