/**
 * 文件名：B001Action.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hw.hwsafe.attachment.pojo.C004PO;
import com.hw.hwsafe.attachment.service.IAttachmentService;
import com.hw.hwsafe.corpinfo.constants.CorpinfoConstants;
import com.hw.hwsafe.corpinfo.pojo.B00103PO;
import com.hw.hwsafe.corpinfo.pojo.B001PO;
import com.hw.hwsafe.corpinfo.pojo.B005PO;
import com.hw.hwsafe.corpinfo.pojo.C007PO;
import com.hw.hwsafe.corpinfo.pojo.S099PO;
import com.hw.hwsafe.corpinfo.service.IB00103Service;
import com.hw.hwsafe.corpinfo.service.IB001Service;
import com.hw.hwsafe.corpinfo.service.IB005Service;
import com.hw.hwsafe.corpinfo.service.IC007Service;
import com.hw.hwsafe.cpnyreg.pojo.C001PO;
import com.hw.hwsafe.cpnyreg.service.IC001Service;
import com.hw.hwsafe.platform.action.BaseAction;
import com.hw.hwsafe.platform.constants.Constants;
import com.hw.hwsafe.platform.pojo.CodeValuePO;
import com.hw.hwsafe.platform.pojo.DictionaryPO;
import com.hw.hwsafe.platform.pojo.UserPO;
import com.hw.hwsafe.platform.service.CodeValueService;
import com.hw.hwsafe.platform.service.ICodeSequenceService;
import com.hw.hwsafe.platform.service.IDictionaryService;
import com.hw.hwsafe.utils.DateTimeUtils;
import com.hw.hwsafe.utils.UUIDGenerater;

/**
 * 
 * 项目名称：hwsafe 
 * 类名称：B001Action 
 * 类描述：
 * 创建人：刁海港 
 * 创建时间：2012-6-11 下午5:58:36 
 * 修改人：
 * 修改时间：2012-6-11 下午5:58:36 
 * 修改备注：
 */

@SuppressWarnings("rawtypes")
public class B001Action extends BaseAction {

	private B001PO b001PO;
	private S099PO s099PO;
	@Autowired
	private IB001Service b001Service;

	@Autowired
	private IC001Service c001Service;
	
	@Autowired
	private IB00103Service b00103Service;

	private List<B001PO> b001List;

	@Autowired
	private CodeValueService codevalueService;// 代码表Service
	
	@Autowired
	private IDictionaryService dictService;//新的数据字段表
	
	@Autowired
	private IC007Service c007Service;
	
	@Autowired
	private ICodeSequenceService codeService;
	
	private List<CodeValuePO> codeValueSF;// 代码表：是否代码
	private List codeValueJJLX;// 代码表：经济类型
	private List<CodeValuePO> codeValueYW;//有无
	private List<DictionaryPO> codeValueWS;//是否完善
	private List<CodeValuePO> codeValueSSHY;// 代码表：所属行业
	private List<CodeValuePO> codeValueLSGX;// 代码表：隶属关系
	private List<CodeValuePO> codeValueQYLX;// 代码表：企业类型
	private List<HashMap<String, Object>> mapList;
	private List list;

	private String provinceId;// 省ID

	private String cityId;// 地市ID

	private List provinceList;// 省下拉列表

	private List cityList;// 市下拉列表

	private List countyList;// 区县下拉列表

	private List cityl;

	private List areal;

	private String statType;
	private int whptypenum;
	
	
	/**
	 * 行业门类
	 */
	private List hymlList;
	private List dlList;
	private List zlList;
	private List xlList;
	private String hymlID;
	private String dlID;
	private String zlID;
	private String xlID;
	
	/**
	 * 表示是否已经标注
	 */
	private Integer labelFlag;
	
	private List<B005PO> b005List;
	private IB005Service b005Service;
	@Autowired
	private IAttachmentService attachmentService;
	
	/**
	 * 更新企业信息，如果信息不存在，则插入企业信息，否则修改企业信息
	 * @return
	 */
	public String doUpdate(){
		try {
			 String c004id = getRequest().getParameter("c004ids");
				/**
				 * 没有点开浏览的按钮，对图片不做操作
				 */
				if("noupdate".equals(c004id)){
				}
				/**
				 * 点开过，处理图片，按c004ids的值
				 */
				else{
					String[] c004ids = c004id.split(",");
					b001PO.setC004ids(c004ids);
				}
			if(b001PO.getMA001()==null||b001PO.getMA001().isEmpty()){
				
				if (!b001Service.checkCorpName(b001PO)) {
					b001PO.setMA001(getSessionUserPO().getORGAN_UUID());// 主键取自当前登录人的企业ID
					C001PO c001po = c001Service.retrieveC001ByMA001(getSessionUserPO().getORGAN_UUID());
					if(c001po!=null){
						b001PO.setMA003(c001po.getMA003());//企业组织机构代码
						b001PO.setMA004(c001po.getMA002());//企业法人单位名称
						b001PO.setMA020(c001po.getMA007());//邮箱
						b001PO.setMA024(c001po.getMA005());//固话
					}
					String strCode=B001PO.CORPCODE_PRE+b001PO.getMA051();//编码规则为ZK+行业类型编号+
					String corpCode =strCode+codeService.retrieveSequence(strCode, 4);
					b001PO.setMA002(corpCode);// 自动对企业编号进行编号
					/**
					 * 填表人信息 获取session
					 */
					UserPO userPO = getSessionUserPO();
					b001PO.setMA028(userPO.getId());// 
					b001PO.setMA029(userPO.getUserName());
					b001PO.setMA030(userPO.getMOBILE_NUMBER());
					b001PO.setMA039(userPO.getId());// 暂定审核人ID为000002
					b001PO.setMA038(B001PO.WSH);// 审核状态 : 0- 未审核(默认) 1-待审核 2-审核通过 3-审核不通过 小刁确认；
					//取系统时间
					b001PO.setMA031(DateTimeUtils.getCurrentDate());// 调用公共方法
					b001Service.insertB001(b001PO);
					setAddSuccessMsg();
				}else {
					message.set(Constants.MSG_ERROR, CorpinfoConstants.NAME_EXIST_MSG);   //setContent("企业名称已存在，不允许重复");
				}
			}else{
				if(!b001Service.checkCorpName(b001PO)){
					b001Service.updateB001(b001PO);
					setUpdateSuccessMsg();
				}else{
					message.set(Constants.MSG_ERROR, CorpinfoConstants.NAME_EXIST_MSG);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	
	/**
	 * 更新企业信息，如果信息不存在，则插入企业信息，否则修改企业信息
	 * @return
	 */
	public String doUpdate2(){
		try {
			 String c004id = getRequest().getParameter("c004ids");
				/**
				 * 没有点开浏览的按钮，对图片不做操作
				 */
				if("noupdate".equals(c004id)){
				}
				/**
				 * 点开过，处理图片，按c004ids的值
				 */
				else{
					String[] c004ids = c004id.split(",");
					b001PO.setC004ids(c004ids);
				}
			if(b001PO.getMA001()==null||b001PO.getMA001().isEmpty()){
				
				if (!b001Service.checkCorpName(b001PO)) {
					b001PO.setMA001(getSessionUserPO().getORGAN_UUID());// 主键取自当前登录人的企业ID
					C001PO c001po = c001Service.retrieveC001ByMA001(getSessionUserPO().getORGAN_UUID());
					if(c001po!=null){
						b001PO.setMA003(c001po.getMA003());//企业组织机构代码
						b001PO.setMA004(c001po.getMA002());//企业法人单位名称
						b001PO.setMA020(c001po.getMA007());//邮箱
						b001PO.setMA024(c001po.getMA005());//固话
					}
					String strCode=B001PO.CORPCODE_PRE+b001PO.getMA051();//编码规则为ZK+行业类型编号+
					String corpCode =strCode+codeService.retrieveSequence(strCode, 4);
					b001PO.setMA002(corpCode);// 自动对企业编号进行编号
					/**
					 * 填表人信息 获取session
					 */
					UserPO userPO = getSessionUserPO();
					b001PO.setMA028(userPO.getId());// 
					b001PO.setMA029(userPO.getUserName());
					b001PO.setMA030(userPO.getMOBILE_NUMBER());
					b001PO.setMA039(userPO.getId());// 暂定审核人ID为000002
					b001PO.setMA038(B001PO.WSH);// 审核状态 : 0- 未审核(默认) 1-待审核 2-审核通过 3-审核不通过 小刁确认；
					//取系统时间
					b001PO.setMA031(DateTimeUtils.getCurrentDate());// 调用公共方法
					b001Service.insertB001(b001PO);
					setAddSuccessMsg();
				}else {
					message.set(Constants.MSG_ERROR, CorpinfoConstants.NAME_EXIST_MSG);   //setContent("企业名称已存在，不允许重复");
				}
			}else{
				if(!b001Service.checkCorpName(b001PO)){
					
					b001Service.updateB001(b001PO);
					if(b001PO.getB00103po().getMa001() == null || b001PO.getB00103po().getMa001().isEmpty()){
						B00103PO b00103po = b001PO.getB00103po();
						b00103po.setMa001(b001PO.getMA001());
						b00103Service.insertInstance(b00103po);
					}else{
						b00103Service.updateInstance(b001PO.getB00103po());
					}
					setUpdateSuccessMsg();
				}else{
					message.set(Constants.MSG_ERROR, CorpinfoConstants.NAME_EXIST_MSG);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			setUpdateFailedMsg();
		}
		return JSON_MSG;
	}
	
	/**
	 * 
	 * 查询地市信息
	 */
	public String getcityList() {
		try {
			cityl=b001Service.getcityList(provinceId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "moption";
	}

	/**
	 * 
	 * 查询区县信息
	 */
	public String getcountyList() {
		try {
			areal=b001Service.getcountyList(cityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "soption";
	}
	/**
	 * 
	 * doGetB001Details(根据企业ID查询企业信息)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doGetB001Details() {
		try {
			codeValueSF = codevalueService.retrieveCodeByType("yesOrNo");// 是否
			codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");
			b001PO = b001Service.retrieveB001ByID(b001PO.getMA001());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "details";
	}

	/**
	 * 
	 * doViewCorp(企业端查看企业概况)
	 * @param   name
	 * @param  @return    设定文件
	 * @return String    DOM对象
	 * @Exception 异常对象
	 * @since  CodingExample　Ver(编码范例查看) 1.1
	 */
	public String doViewCorp() {
		boolean isGov = false;
		try {
			
			isGov = getSessionUserPO().getUSER_TYPE().equals("GOV");
			
			String corpId = isGov ? b001PO.getMA001() : getSessionUserPO().getORGAN_UUID();
			b001PO = b001Service.retrieveB001ByID(corpId);
			String zgdwname = b001Service.getGovName(corpId);
			b001PO.setMA013(zgdwname);
			
			String govType = request.getParameter("govType");
//			if (govType == null) govType = "0";
			
			// 查询执照
			Map map = new HashMap();
			map.put("corpID", corpId);
			map.put("govType", govType);
			b005List = b005Service.retrieveB005ByCorp(map);
			if(!b005List.isEmpty()){
				for(B005PO po : b005List){
					List<C004PO> c004List = attachmentService.getC004List(po.getMA001(),null);
					po.setC004poList(c004List);
				}
			}
			
			//如果b001po为null，则对于b001是新增
	     	if(b001PO==null){
	     		b001PO=new B001PO();
	    		String corpID=getSessionUserPO().getORGAN_UUID();
	    		C001PO c001po=c001Service.retrieveC001ByMA001(corpID);//根据C001的ＩＤ，查询显示Ｂ００１和Ｃ００１共有的字段
	    		b001PO.setMA003(c001po.getMA003());//企业组织机构代码
	    		b001PO.setMA004(c001po.getMA002());//企业法人单位名称
	    		b001PO.setMA020(c001po.getMA007());//邮箱
	    		b001PO.setMA024(c001po.getMA005());//固话
	     		cityl = new ArrayList();
	     		areal = new ArrayList();
	     	}else {
	     		B00103PO b00103po = b00103Service.retrieveInstanceById(b001PO.getMA001());
	     		b001PO.setB00103po(b00103po);
	     		labelFlag=b001PO.getMA071();
	     		provinceId=b001PO.getMA007();
	     		cityId=b001PO.getMA008();
	     		cityl=b001Service.getcityList(provinceId);
				areal=b001Service.getcountyList(cityId);
				String ma011Str=b001PO.getMA011();
				String[] ss = ma011Str.split("-");
				int ssLength=ss.length;
				C007PO c007po=new C007PO();
				if(ssLength==4){
					c007po=c007Service.getC007POByValue(ss[ssLength-1]);
					b001PO.setHyxlID(c007po.getId());
					c007po=c007Service.getC007POByValue(ss[ssLength-2]);
					b001PO.setHyzlID(c007po.getId());
					c007po=c007Service.getC007POByValue(ss[ssLength-3]);
					b001PO.setHydlID(c007po.getId());
					c007po=c007Service.getC007POByValue(ss[ssLength-4]);
					b001PO.setHymlID(c007po.getId());
				}
				if(ssLength==3){
					c007po=c007Service.getC007POByValue(ss[ssLength-1]);
					b001PO.setHyzlID(c007po.getId());
					c007po=c007Service.getC007POByValue(ss[ssLength-2]);
					b001PO.setHydlID(c007po.getId());
					c007po=c007Service.getC007POByValue(ss[ssLength-3]);
					b001PO.setHymlID(c007po.getId());
				}
				if(ssLength==2){
					c007po=c007Service.getC007POByValue(ss[ssLength-1]);
					b001PO.setHydlID(c007po.getId());
					c007po=c007Service.getC007POByValue(ss[ssLength-2]);
					b001PO.setHymlID(c007po.getId());
				}
				if(ssLength==1){
					c007po=c007Service.getC007POByValue(ss[ssLength-1]);
					b001PO.setHymlID(c007po.getId());
				}
	     	}
			provinceList= b001Service.getprovinceList();//获取省份列表
			codeValueSF = codevalueService.retrieveCodeByType("yesOrNo");// 是否
			codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");
			codeValueSSHY = codevalueService.retrieveCodeByType("sshy");// 所属行业
			codeValueLSGX = codevalueService.retrieveCodeByType("lsgx");// 隶属关系
			codeValueQYLX = codevalueService.retrieveCodeByType("qylx");// 企业类型
			/**
			 * 获得行业类型
			 */
			hymlList=c007Service.getHymList();
		
			String hymlid=b001PO.getHymlID();
			String hydlid=b001PO.getHydlID();
			String hyzlid=b001PO.getHyzlID();		
			if(hymlid==null||hymlid.equals(""))
			{
				dlList=new ArrayList();
			}else {
				dlList = c007Service.getHydlList(hymlid);
			}
			if(hydlid==null||hydlid.equals(""))
			{
				zlList=new ArrayList();
				
			}else {
				zlList = c007Service.getHydlList(hydlid);
			}
			if(hyzlid==null||hyzlid.equals(""))
			{
				xlList=new ArrayList();
			}else {
				xlList = c007Service.getHydlList(hyzlid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if("1".equals(b001PO.getMA051())||"2".equals(b001PO.getMA051())){
			return isGov ? "corp_view2_zf" : "corp_view2";
		}else{
			return isGov ? "corp_view_zf" : "corp_view";
		}
	}
	
	/**
	 * 函 数 名：doGetDangerByCorpId
	 * 功能描述：通过企业ID获取危险源信息
	 * @return String
	 * 创建人：王贺喜
	 * 创建时间：2012-12-8 上午11:01:38
	 */
	
	public String doGetDangerByCorpId() throws Exception {
		try {
			pagerData = initPagerData();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pagerData", pagerData);
			paramMap.put("corpid", b001PO.getMA001());
			if (!"true".equals(request.getParameter("qc"))) {
				pagerData.setRows(b001Service.retrieveDangerByCorpId(paramMap));
			}
			paramMap.put("selectCount", true);
			pagerData.setRecords(b001Service.retrieveDangerByCorpId(paramMap));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_PAGER;
	}
	/**
	 * 上报给政府
	 * @return
	 */
	public String report(){
		try {
			b001Service.report2Gov(request.getParameter("corpid"));
			setSubmitSuccessMsg();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSON_MSG;
	}
	
	
	/**
	 * doGetReport(获取企业的安全生产情况的基本信息，包括企业的基础资料、产品、原材料、规章制度、预案等方面)
	 */
	public String doGetReport(){
		
		try {
			/**
			 * 获取企业的基础资料
			 */
			b001PO = b001Service.retrieveB001ByID(getSessionUserPO().getORGAN_UUID());
			codeValueSSHY = codevalueService.retrieveCodeByType("sshy");// 所属行业
			codeValueJJLX = dictService.retrieveChildDictListByKey("jjlx");
			codeValueQYLX = codevalueService.retrieveCodeByType("qylx");// 企业类型
			codeValueSF = codevalueService.retrieveCodeByType("yesOrNo");// 是否
			codeValueYW=codevalueService.retrieveCodeByType("yw");//有无
			codeValueWS= dictService.retrieveChildDictListByKey("ssws");//是否完善
			
			s099PO = b001Service.retrieveS099ByCorpid(getSessionUserPO().getORGAN_UUID());
			if(s099PO==null) {
				s099PO = new S099PO();
				s099PO.setMa001(UUIDGenerater.getUUID());
				s099PO.setMa039(getSessionUserPO().getORGAN_UUID());
				s099PO.setMa038(DateTimeUtils.getCurrentDate());
				s099PO.setMa007(0);
				s099PO.setMa010(0);
				s099PO.setMa044(0);
				b001Service.insertS099(s099PO);
				s099PO = b001Service.retrieveS099ByCorpid(getSessionUserPO().getORGAN_UUID());
			}
			s099PO.setMa034(s099PO.getMa032()-s099PO.getMa033());
			s099PO.setMa030(s099PO.getMa028()-s099PO.getMa029()-s099PO.getMa031());
			whptypenum = b001Service.retrieveWhpTypeNum(getSessionUserPO().getORGAN_UUID());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "report";
	}
	/**
	 * 添加企业安全生产登记情况
	 * @return
	 */
	public String doSaveAddReport(){
		try {
			if(s099PO.getMa001()!=null&&!s099PO.getMa001().isEmpty()){
				b001Service.updateS099(s099PO);
			}
			message.set(Constants.MSG_OK, Constants.ADD_SUCCESS_MSG);
		} catch (Exception e) {
           e.printStackTrace();
		}
		return JSON_MSG;
	}
	
	
	public String doGetHxwz() {
		return "hxwz";
	}
	

	public String queryDataHxwz() throws Exception {
		pagerData = initPagerData();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("pagerData", pagerData);
		paramMap.put("hxwzvalue", request.getParameter("hxwzvalue"));
		paramMap.put("hxwzdisplay", request.getParameter("hxwzdisplay"));		
		pagerData.setRows(b001Service.retrieveByPageHxwz(paramMap));
		paramMap.put("selectCount", true);
		pagerData.setRecords(b001Service.retrieveByPageHxwz(paramMap));
		return JSON_PAGER;
	}
	
	
	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public List getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List provinceList) {
		this.provinceList = provinceList;
	}

	public List getCityList() {
		return cityList;
	}

	public void setCityList(List cityList) {
		this.cityList = cityList;
	}

	public List getCountyList() {
		return countyList;
	}

	public void setCountyList(List countyList) {
		this.countyList = countyList;
	}

	public List getCityl() {
		return cityl;
	}

	public void setCityl(List cityl) {
		this.cityl = cityl;
	}

	public List getAreal() {
		return areal;
	}

	public void setAreal(List areal) {
		this.areal = areal;
	}

	public String getStatType() {
		return statType;
	}

	public void setStatType(String statType) {
		this.statType = statType;
	}

	public IB001Service getB001Service() {
		return b001Service;
	}

	public void setB001Service(IB001Service b001Service) {
		this.b001Service = b001Service;
	}

	public CodeValueService getCodevalueService() {
		return codevalueService;
	}

	public void setCodevalueService(CodeValueService codevalueService) {
		this.codevalueService = codevalueService;
	}

	public List<HashMap<String, Object>> getMapList() {
		return mapList;
	}

	public void setMapList(List<HashMap<String, Object>> mapList) {
		this.mapList = mapList;
	}
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public List<CodeValuePO> getCodeValueLSGX() {
		return codeValueLSGX;
	}

	public void setCodeValueLSGX(List<CodeValuePO> codeValueLSGX) {
		this.codeValueLSGX = codeValueLSGX;
	}

	public List<CodeValuePO> getCodeValueQYLX() {
		return codeValueQYLX;
	}

	public void setCodeValueQYLX(List<CodeValuePO> codeValueQYLX) {
		this.codeValueQYLX = codeValueQYLX;
	}

	public List getCodeValueJJLX() {
		return codeValueJJLX;
	}

	public void setCodeValueJJLX(List codeValueJJLX) {
		this.codeValueJJLX = codeValueJJLX;
	}

	public List<CodeValuePO> getCodeValueSSHY() {
		return codeValueSSHY;
	}

	public void setCodeValueSSHY(List<CodeValuePO> codeValueSSHY) {
		this.codeValueSSHY = codeValueSSHY;
	}

	public List<CodeValuePO> getCodeValueSF() {
		return codeValueSF;
	}

	public void setCodeValueSF(List<CodeValuePO> codeValueSF) {
		this.codeValueSF = codeValueSF;
	}

	public void setB001List(List<B001PO> b001List) {
		this.b001List = b001List;
	}

	public List<B001PO> getB001List() {
		return b001List;
	}

	public void setB001PO(B001PO b001PO) {
		this.b001PO = b001PO;
	}

	public B001PO getB001PO() {
		return b001PO;
	}

	public IDictionaryService getDictService() {
		return dictService;
	}

	public void setDictService(IDictionaryService dictService) {
		this.dictService = dictService;
	}
	
	/**
	 * 
	 * 查询行业大类List:根据门类
	 */
	public String doGetHydlList() {
		try {
			dlList = c007Service.getHydlList(hymlID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "dloption";
	}

	/**
	 * 
	 * 查询行业中类List:根据大类
	 */
	public String doGetHyzlList() {
		try {
			zlList = c007Service.getHydlList(dlID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "zloption";
	}
	/**
	 * 
	 * 查询行业小类List:根据中类
	 */
	public String doGetHyxlList() {
		try {
			xlList = c007Service.getHydlList(zlID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "xloption";
	}

	public IC007Service getC007Service() {
		return c007Service;
	}

	public void setC007Service(IC007Service c007Service) {
		this.c007Service = c007Service;
	}

	public List getHymlList() {
		return hymlList;
	}

	public void setHymlList(List hymlList) {
		this.hymlList = hymlList;
	}

	public List getDlList() {
		return dlList;
	}

	public void setDlList(List dlList) {
		this.dlList = dlList;
	}

	public List getZlList() {
		return zlList;
	}

	public void setZlList(List zlList) {
		this.zlList = zlList;
	}

	public List getXlList() {
		return xlList;
	}

	public void setXlList(List xlList) {
		this.xlList = xlList;
	}

	public String getHymlID() {
		return hymlID;
	}

	public void setHymlID(String hymlID) {
		this.hymlID = hymlID;
	}

	public String getDlID() {
		return dlID;
	}

	public void setDlID(String dlID) {
		this.dlID = dlID;
	}

	public String getZlID() {
		return zlID;
	}

	public void setZlID(String zlID) {
		this.zlID = zlID;
	}

	public String getXlID() {
		return xlID;
	}

	public void setXlID(String xlID) {
		this.xlID = xlID;
	}

	public Integer getLabelFlag() {
		return labelFlag;
	}

	public void setLabelFlag(Integer labelFlag) {
		this.labelFlag = labelFlag;
	}

	public IC001Service getC001Service() {
		return c001Service;
	}

	public void setC001Service(IC001Service c001Service) {
		this.c001Service = c001Service;
	}

	public ICodeSequenceService getCodeService() {
		return codeService;
	}

	public void setCodeService(ICodeSequenceService codeService) {
		this.codeService = codeService;
	}

	public S099PO getS099PO() {
		return s099PO;
	}

	public void setS099PO(S099PO s099po) {
		s099PO = s099po;
	}

	public List<CodeValuePO> getCodeValueYW() {
		return codeValueYW;
	}

	public void setCodeValueYW(List<CodeValuePO> codeValueYW) {
		this.codeValueYW = codeValueYW;
	}

	
	public List<DictionaryPO> getCodeValueWS() {
		return codeValueWS;
	}

	public void setCodeValueWS(List<DictionaryPO> codeValueWS) {
		this.codeValueWS = codeValueWS;
	}

	public int getWhptypenum() {
		return whptypenum;
	}

	public void setWhptypenum(int whptypenum) {
		this.whptypenum = whptypenum;
	}


	public List<B005PO> getB005List() {
		return b005List;
	}


	public void setB005List(List<B005PO> b005List) {
		this.b005List = b005List;
	}


	public IB005Service getB005Service() {
		return b005Service;
	}


	public void setB005Service(IB005Service b005Service) {
		this.b005Service = b005Service;
	}
	
}
