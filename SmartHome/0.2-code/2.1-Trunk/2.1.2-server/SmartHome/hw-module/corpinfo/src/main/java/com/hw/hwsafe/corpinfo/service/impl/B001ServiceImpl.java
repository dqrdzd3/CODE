/**
 * 文件名：B001ServiceImpl.java
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

import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.corpinfo.action.B001Action;
import com.hw.hwsafe.corpinfo.dao.IB001Dao;
import com.hw.hwsafe.corpinfo.dao.IC007Dao;
import com.hw.hwsafe.corpinfo.pojo.B001PO;
import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.corpinfo.pojo.S099PO;
import com.hw.hwsafe.corpinfo.service.IB001Service;
import com.hw.hwsafe.platform.service.impl.BaseServiceImpl;
import com.hw.hwsafe.platform.userinfo.SessionUtil;
import com.hw.hwsafe.utils.StringUtil;

/**
 * 
 * 项目名称：hwsafe 
 * 类名称：B001ServiceImpl 
 * 类描述： 
 * 创建人：刁海港 
 * 创建时间：2012-6-11 下午6:08:04 修改人：
 * 修改时间：2012-6-11 下午6:08:04 修改备注：
 * 
 * @version
 * 
 */
@SuppressWarnings("rawtypes")
public class B001ServiceImpl extends BaseServiceImpl implements IB001Service {

	@Autowired
	private IB001Dao b001Dao;
	@Autowired
	private IC007Dao c007Dao;

	@Autowired
	private IAttachmentService attachmentService;

	/**
	 * 获取省列表
	 */
	public List getprovinceList() throws Exception {
		return b001Dao.getProvinceList();
	}

	/**
	 * 获取地市列表
	 */
	public List getcityList(String provinceid) throws Exception {
		return b001Dao.getCityList(provinceid);
	}

	/**
	 * 获取区县列表
	 */
	public List getcountyList(String cityid) throws Exception {
		return b001Dao.getCountyList(cityid);
	}

	/**
	 * 
	 * 函 数 名： retrieveB001ByID 
	 * 功能描述：查询 
	 * 适用条件： 执行流程： 
	 * 使用方法： 注意事项 输入参数： 
	 * 创建人：刁海港
	 * 创建时间：2012-7-23 下午4:04:14 修改人： 修改时间： 修改原因描述：
	 */
	public B001PO retrieveB001ByID(String b001ID, B001Action b001Action)
			throws Exception {
		B001PO retrieveB001PO = b001Dao.retrieveB001ByID(b001ID);
		b001Action.setProvinceList(b001Dao.getProvinceList());
		b001Action.setCityl(b001Dao.getCityList(retrieveB001PO.getMA007()));
		b001Action.setAreal(b001Dao.getCountyList(retrieveB001PO.getMA008()));
		return retrieveB001PO;
	}

	/**
	 * 
	 * 函 数 名： retrieveB001ByPO 
	 * 功能描述：条件查询 建议查询条件拼接 适用条件： 
	 * 执行流程： 使用方法： 注意事项 输入参数：
	 * 创建人：刁海港 创建时间：2012-7-23 下午4:04:14 
	 */
	public List<B001PO> retrieveB001ByPO(B001PO b001PO) throws Exception {
		return b001Dao.retrieveB001ByPO(b001PO);
	}

	/**
	 * 
	 * 函 数 名： insertB001 功能描述：新增企业 
	 * 适用条件：新增企业 
	 * 执行流程：1.判断企业名称是否重复 ，是；返回；否，新增； 使用方法：
	 * 注意事项：企业名称不允许重复； 输入参数：
	 *  创建人：刁海港 创建时间：2012-7-20 
	 *  下午1:10:14 修改人： lm
	 * 修改时间：2012-7-23 下午4:04:14 
	 * 修改原因描述：新增需验证企业名称是否重复。
	 */
	public void insertB001(B001PO b001PO) throws Exception {
		b001PO.setMA011(jointHylx(b001PO));
		b001PO.setMA069(StringUtil.toJP(b001PO.getMA004(), 0));
		b001Dao.insertB001(b001PO);
		// 插入附件列表
		attachmentService.insertC004s(b001PO.getC004ids(), b001PO.getMA001());
	}

	/**
	 * 
	 * jointHylx(拼接行业类型字段)
	 * 
	 * @param name
	 * @param @return 设定文件
	 * @return String DOM对象
	 * @throws Exception
	 * @Exception 异常对象
	 * @since CodingExample　Ver(编码范例查看) 1.1
	 */
	public String jointHylx(B001PO b001PO) throws Exception {
		// 拼接行业类型字符串
		String hyStr = "";
		C007PO c007po = new C007PO();
		if (b001PO.getHymlID() != null && !b001PO.getHymlID().isEmpty()) {// 如果行业门类不为空，则添加行业门类，根据ＩＤ，获得对应的值
			c007po = c007Dao.retrieveC007ById(b001PO.getHymlID());
			hyStr += c007po.getValue();
		}
		if (b001PO.getHydlID() != null && !b001PO.getHydlID().isEmpty()) {
			c007po = c007Dao.retrieveC007ById(b001PO.getHydlID());
			hyStr = hyStr + "-" + c007po.getValue();
		}
		if (b001PO.getHyzlID() != null && !b001PO.getHyzlID().isEmpty()) {
			c007po = c007Dao.retrieveC007ById(b001PO.getHyzlID());
			hyStr = hyStr + "-" + c007po.getValue();
		}
		if (b001PO.getHyxlID() != null && !b001PO.getHyxlID().isEmpty()) {
			c007po = c007Dao.retrieveC007ById(b001PO.getHyxlID());
			hyStr = hyStr + "-" + c007po.getValue();
		}
		return hyStr;
	}

	/**
	 * 
	 * 函 数 名： updateB001 
	 * 功能描述：新增企业 
	 * 适用条件：新增企业 
	 * 执行流程：1.判断企业名称是否重复 ，是；返回；否，新增；
	 * 使用方法：
	 * 注意事项：企业名称不允许重复；
	 * 输入参数： 创建人：刁海港 
	 * 创建时间：2012-7-20 下午1:10:14 
	 * 修改人： lm
	 * 修改时间：2012-7-23 下午4:04:14 
	 * 修改原因描述：新增需验证企业名称是否重复
	 */
	public void updateB001(B001PO b001PO) throws Exception {
		b001PO.setMA011(jointHylx(b001PO));
		b001Dao.updateB001(b001PO);
		// 插入附件列表
		attachmentService.insertC004s(b001PO.getC004ids(), b001PO.getMA001());
	}

	/**
	 * 添加修改企业信息时查看企业名称是否已经存在
	 */
	public boolean checkCorpName(B001PO b001PO) throws Exception {
		B001PO po = new B001PO();
		po.setMA004(b001PO.getMA004());
		if (b001PO.getMA001() != null) {
			po.setMA001(b001PO.getMA001());
		}
		int i = b001Dao.retrieveB001ByName(po);
		return i == 0 ? false : true;
	}

	/**
	 * 获取企业危化品种类数量
	 */
	public int retrieveWhpTypeNum(String corpid)throws Exception{
		return  b001Dao.retrieveWhpTypeNum(corpid);
	}
	/**
	 * 根据企业ID或获取企业安全生产登记情况信息
	 */
	
	public S099PO retrieveS099ByCorpid(String corpid)throws Exception{
		return  b001Dao.retrieveS099ByCorpid(corpid);
	}
	/**
	 * 保存企业的安全生产登记信息
	 */
	public void insertS099(S099PO s099PO)throws Exception{
		
		 b001Dao.insertS099(s099PO);
	}
	/**
	 * 修改企业安全生产登记信息
	 */
	public void updateS099(S099PO s099PO)throws Exception{
		b001Dao.updateS099(s099PO);
	}
	@Override
	public B001PO retrieveB001ByID(String b001id) throws Exception {
		return b001Dao.retrieveB001ByID(b001id);
	}
  /**
   * 根据政府ID查询政府下的管辖企业列表
   */
	public List<B001PO> retrieveAllB001ByGovID(String govID)throws Exception{
		
		return b001Dao.retrieveAllB001ByGovID(govID);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<B001PO> retrieveB001ByPinyin(Map map) throws Exception {
		return b001Dao.retrieveB001ByPinyin(map);
	}

	@Override
	public List govStat(String type,String cpnyType) throws Exception {
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("type", type);
		temp.put("zgdwid", SessionUtil.getOrgId());
		temp.put("cpnyType", cpnyType);
		return b001Dao.govStat(temp);
	}
	
	@Override
	public List govStatsj(String type,String cpnyType) throws Exception {
		HashMap<String, String> temp = new HashMap<String, String>();
		temp.put("type", type);
		temp.put("zgdwid", SessionUtil.getOrgId());
		temp.put("cpnyType", cpnyType);
		return b001Dao.govStatsj(temp);
	}

	@Override
	public String getGovName(String  corpid)throws Exception{
		
		return b001Dao.getGovName(corpid);
	}
	@Override
	public void report2Gov(String ma001) throws Exception {
		b001Dao.report2Gov(ma001);
	}

	@Override
	public String govAudit(String corpId, String auditType) throws Exception {
		return b001Dao.govAudit(corpId, auditType);
	}

	@Override
	public List getAuditList(String auditType) throws Exception {
		return null;
	}

	@Override
	public List<Map<String, Object>> retrieveDangerByCorpId(
			Map<String, Object> map) throws Exception {
		return b001Dao.retrieveDangerByCorpId(map);
	}

	public IAttachmentService getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(IAttachmentService attachmentService) {
		this.attachmentService = attachmentService;
	}

	@Override
	public List<Map<String, Object>> retrieveByPageHxwz(Map<String, Object> map) throws Exception {
		return b001Dao.retrieveByPageHxwz(map);
	}
}
