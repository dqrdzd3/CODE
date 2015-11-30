/**
 * 文件名：B001PO.java
 *
 * 版本信息：1.0
 * 日期：2012-6-11
 * Copyright 河南汉威电子股份有限公司软件部 Corporation 2012 
 * 版权所有
 *
 */
package com.hw.hwsafe.corpinfo.pojo;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.hw.hwsafe.attachment.pojo.C004PO;

/**
 * 
 * 项目名称：hwsafe
 * 类名称：B001PO
 * 类描述：
 * 创建人：刁海港
 * 创建时间：2012-6-11 下午6:04:19
 * 修改人：
 * 修改时间：2012-6-11 下午6:04:19
 * 修改备注：
 * @version 
 * 
 */
public class B001PO {
	/**
	 * 企业编号前缀ZK
	 */
	public final static String CORPCODE_PRE = "ZK";
	/**
	 * 唯一标志
	 */
	private String MA001;
	/**
	 * 单据编号
	 */
	private String MA002;
	/**
	 * 组织机构代码
	 */
	private String MA003;
	/**
	 * 法人单位名称
	 */
	private String MA004;
	/**
	 * 状态变更记录
	 */
	private String MA005;
	/**
	 * 区域id
	 */
	private String MA006;
	/**
	 * 省份id
	 */	
	private String MA007;	
	/**
	 * 城市id
	 */
	private String MA008;
	/**
	 * 县区id
	 */	
	private String MA009;
	/**
	 * 经济类型
	 */
	private String MA010;
	/**
	 * 行业类型
	 */	
	private String MA011;
	/**
	 * 成立时间
	 */
	private Date MA012;
	/**
	 * 行政主管部门
	 */	
	private String MA013;
	/**
	 * 职工总数
	 */
	private Integer MA014;
	/**
	 * 固定资产总值
	 */
	private Double MA015;
	/**
	 * 年总收入
	 */	
	
	private Double MA016;
	/**
	 * 年利润
	 */
	private Double MA017;
	/**
	 * 主要产品
	 */
	private String MA018;
	/**
	 * 是否中央企业
	 */
	private Integer MA019;
	/**
	 * 企业email地址
	 */
	private String MA020;
	/**
	 * 银行名称-保留字段
	 */	
	private String MA021;
	/**
	 * 银行帐号-保留字段
	 */
	private String MA022;
	/**
	 * 税号-保留字段
	 */
	private String MA023;
	/**
	 * 电话-保留字段
	 */
	private String MA024;
	/**
	 * 占地面积
	 */
	private Double MA025;
	/**
	 * 填报单位负责人姓名
	 */
	private String MA026 ;
	/**
	 * 填报单位负责人电话
	 */
	private String MA027;
	/**
	 * 填报人Id
	 */
	private String MA028;
	/**
	 * 填表人姓名
	 */
	private String MA029;
	/**
	 * 填表人电话
	 */	
	private String MA030;	
	/**
	 * 填表日期
	 */
	private Date MA031;
	/**
	 * 修改时间
	 */	
	private Date MA032;
	/**
	 * 通讯地址
	 */
	private String MA033;
	/**
	 * 邮政编码
	 */	
	private String MA034;
	/**
	 * 经度
	 */
	private String MA035;
	/**
	 * 纬度
	 */	
	private String MA036;
	/**
	 * 上报时间
	 */
	private Date MA037;
	/**
	 * 审核状态
	 */
	private Integer MA038;
	/**
	 * 审核人Id
	 */	
	private String MA039;
	/**
	 * 审核人
	 */
	private String MA040;
	/**
	 * 审核时间
	 */
	private Date MA041;
	/**
	 * 审核意见
	 */
	private String MA042;
	/**
	 * 类别
	 */
	private Integer MA043;
	/**
	 * 备注
	 */	
	private String MA044;
	
	/**
	 * 注册地址
	 */	
	private String MA045;
	/**
	 * 所在化工园
	 */
	private String MA046;
	/**
	 * 法定代表人
	 */
	private String MA047;
	/**
	 * 安全生产负责人
	 */
	private String MA048;
	/**
	 * 安全生产负责人联系电话
	 */	
	private String MA049;
	/**
	 * 传真
	 */
	private String MA050;
	/**
	 * 企业类型
	 */
	private String MA051;
	/**
	 * 有无重大危险源
	 */
	private String MA052;
	/**
	 * 隶属关系
	 */
	private String MA053;
	/**
	 * 营业执照注册号
	 */	
	private String MA054;
	
	/**
	 * 安全生产许可证编号
	 */	
	private String MA055;
	/**
	 * 危险化学品登记证编号
	 */
	private String MA056;
	/**
	 * 安全生产标准化证书编号
	 */
	private String MA057;
	/**
	 * 高新技术企业认定证书号
	 */
	private String MA058;
	/**
	 * 主要负责人人数
	 */	
	private Integer MA059;
	/**
	 * 分管负责人和安全管理人员人数
	 */
	private Integer MA060;
	/**
	 * 特种作业人数
	 */
	private Integer MA061;
	/**
	 * 其他从业人数
	 */
	private Integer MA062;
	/**
	 * 农民工人数
	 */
	private Integer MA063;
	/**
	 * 是否存在重大事故隐患
	 */	
	private String MA064;
	
	/**
	 * 是否有专兼职应急救援队伍
	 */	
	private String MA065;
	/**
	 * 厂区平面图
	 */
	private String MA066;
	/**
	 * 管网图
	 */
	private String MA067;
	/**
	 * 重大危险源图片
	 */
	private String MA068;
	/**
	 * 拼音代码
	 */
	private String MA069;
	/**
	 * 英文名字
	 */
	private String MA070;
	
	//判断是否已经在地图上进行了标注
	private String islabel;
	/**
	 * 附件列表的id数组
	 */
	private String[] c004ids;
	
	private List<C004PO> c004poList;
	
	/**
	 * 行业门类ID
	 */
	private String hymlID;
	/**
	 * 行业门类Name
	 */
	private String hymlName;
	/**
	 * 行业大类ID
	 */
	private String hydlID;
	/**
	 * 行业大类Name
	 */
	private String hydlName;
	/**
	 * 行业中类ID
	 */
	private String hyzlID;
	/**
	 * 行业中类Name
	 */
	private String hyzlName;
	/**
	 * 行业小类ID
	 */
	private String hyxlID;
	/**
	 * 行业小类Name
	 */
	private String hyxlName;
	/**
	 * 是否标注：0未标注，1标注过
	 */
	private Integer MA071;
	
	private String aqjcqk;
	
	private B00103PO b00103po;
	
	private String MA080;
	 /**
	  * 审核状态 : 0- 未审核(默认) 1-待审核 2-审核通过 3-审核不通过；
	  */
	public static final Integer WSH=0;
	public static final Integer DSH=1;
	public static final Integer SHTG=2;
	public static final Integer SHBTG=3;
	
	
	public String getMA001() {
		return MA001;
	}
	public void setMA001(String mA001) {
		MA001 = mA001;
	}
	public String getMA002() {
		return MA002;
	}
	public void setMA002(String mA002) {
		MA002 = mA002;
	}
	public String getMA003() {
		return MA003;
	}
	public void setMA003(String mA003) {
		MA003 = mA003;
	}
	public String getMA004() {
		return MA004;
	}
	public void setMA004(String mA004) {
		MA004 = mA004;
	}
	public String getMA005() {
		return MA005;
	}
	public void setMA005(String mA005) {
		MA005 = mA005;
	}
	public String getMA006() {
		return MA006;
	}
	public void setMA006(String mA006) {
		MA006 = mA006;
	}
	public String getMA007() {
		return MA007;
	}
	public void setMA007(String mA007) {
		MA007 = mA007;
	}
	public String getMA008() {
		return MA008;
	}
	public void setMA008(String mA008) {
		MA008 = mA008;
	}
	public String getMA009() {
		return MA009;
	}
	public void setMA009(String mA009) {
		MA009 = mA009;
	}
	public String getMA010() {
		return MA010;
	}
	public void setMA010(String mA010) {
		MA010 = mA010;
	}
	public String getMA011() {
		return MA011;
	}
	public void setMA011(String mA011) {
		MA011 = mA011;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getMA012() {
		return MA012;
	}
	@JSON(format="yyyy-MM-dd")
	public void setMA012(Date mA012) {
		MA012 = mA012;
	}
	public String getMA013() {
		return MA013;
	}
	public void setMA013(String mA013) {
		MA013 = mA013;
	}
	public Integer getMA014() {
		return MA014;
	}
	public void setMA014(Integer mA014) {
		MA014 = mA014;
	}
	public Double getMA015() {
		return MA015;
	}
	public void setMA015(Double mA015) {
		MA015 = mA015;
	}
	public Double getMA016() {
		return MA016;
	}
	public void setMA016(Double mA016) {
		MA016 = mA016;
	}
	public Double getMA017() {
		return MA017;
	}
	public void setMA017(Double mA017) {
		MA017 = mA017;
	}
	public String getMA018() {
		return MA018;
	}
	public void setMA018(String mA018) {
		MA018 = mA018;
	}
	public Integer getMA019() {
		return MA019;
	}
	public void setMA019(Integer mA019) {
		MA019 = mA019;
	}
	public String getMA020() {
		return MA020;
	}
	public void setMA020(String mA020) {
		MA020 = mA020;
	}
	public String getMA021() {
		return MA021;
	}
	public void setMA021(String mA021) {
		MA021 = mA021;
	}
	public String getMA022() {
		return MA022;
	}
	public void setMA022(String mA022) {
		MA022 = mA022;
	}
	public String getMA023() {
		return MA023;
	}
	public void setMA023(String mA023) {
		MA023 = mA023;
	}
	public String getMA024() {
		return MA024;
	}
	public void setMA024(String mA024) {
		MA024 = mA024;
	}
	public Double getMA025() {
		return MA025;
	}
	public void setMA025(Double mA025) {
		MA025 = mA025;
	}
	public String getMA026() {
		return MA026;
	}
	public void setMA026(String mA026) {
		MA026 = mA026;
	}
	public String getMA027() {
		return MA027;
	}
	public void setMA027(String mA027) {
		MA027 = mA027;
	}
	public String getMA028() {
		return MA028;
	}
	public void setMA028(String mA028) {
		MA028 = mA028;
	}
	public String getMA029() {
		return MA029;
	}
	public void setMA029(String mA029) {
		MA029 = mA029;
	}
	public String getMA030() {
		return MA030;
	}
	public void setMA030(String mA030) {
		MA030 = mA030;
	}
	public Date getMA031() {
		return MA031;
	}
	public void setMA031(Date mA031) {
		MA031 = mA031;
	}
	public Date getMA032() {
		return MA032;
	}
	public void setMA032(Date mA032) {
		MA032 = mA032;
	}
	public String getMA033() {
		return MA033;
	}
	public void setMA033(String mA033) {
		MA033 = mA033;
	}
	public String getMA034() {
		return MA034;
	}
	public void setMA034(String mA034) {
		MA034 = mA034;
	}
	public String getMA035() {
		return MA035;
	}
	public void setMA035(String mA035) {
		MA035 = mA035;
	}
	public String getMA036() {
		return MA036;
	}
	public void setMA036(String mA036) {
		MA036 = mA036;
	}
	public Date getMA037() {
		return MA037;
	}
	public void setMA037(Date mA037) {
		MA037 = mA037;
	}
	public Integer getMA038() {
		return MA038;
	}
	public void setMA038(Integer mA038) {
		MA038 = mA038;
	}
	public String getMA039() {
		return MA039;
	}
	public void setMA039(String mA039) {
		MA039 = mA039;
	}
	public String getMA040() {
		return MA040;
	}
	public void setMA040(String mA040) {
		MA040 = mA040;
	}
	public Date getMA041() {
		return MA041;
	}
	public void setMA041(Date mA041) {
		MA041 = mA041;
	}
	public String getMA042() {
		return MA042;
	}
	public void setMA042(String mA042) {
		MA042 = mA042;
	}
	public Integer getMA043() {
		return MA043;
	}
	public void setMA043(Integer mA043) {
		MA043 = mA043;
	}
	public String getMA044() {
		return MA044;
	}
	public void setMA044(String mA044) {
		MA044 = mA044;
	}
	public String getMA045() {
		return MA045;
	}
	public void setMA045(String mA045) {
		MA045 = mA045;
	}
	public String getIslabel() {
		return islabel;
	}
	public void setIslabel(String islabel) {
		this.islabel = islabel;
	}
	public String getMA046() {
		return MA046;
	}
	public void setMA046(String mA046) {
		MA046 = mA046;
	}
	public String getMA047() {
		return MA047;
	}
	public void setMA047(String mA047) {
		MA047 = mA047;
	}
	public String getMA048() {
		return MA048;
	}
	public void setMA048(String mA048) {
		MA048 = mA048;
	}
	public String getMA049() {
		return MA049;
	}
	public void setMA049(String mA049) {
		MA049 = mA049;
	}
	public String getMA050() {
		return MA050;
	}
	public void setMA050(String mA050) {
		MA050 = mA050;
	}
	public String getMA051() {
		return MA051;
	}
	public void setMA051(String mA051) {
		MA051 = mA051;
	}
	public String getMA052() {
		return MA052;
	}
	public void setMA052(String mA052) {
		MA052 = mA052;
	}
	public String getMA053() {
		return MA053;
	}
	public void setMA053(String mA053) {
		MA053 = mA053;
	}
	public String getMA054() {
		return MA054;
	}
	public void setMA054(String mA054) {
		MA054 = mA054;
	}
	public String getMA055() {
		return MA055;
	}
	public void setMA055(String mA055) {
		MA055 = mA055;
	}
	public String getMA056() {
		return MA056;
	}
	public void setMA056(String mA056) {
		MA056 = mA056;
	}
	public String getMA057() {
		return MA057;
	}
	public void setMA057(String mA057) {
		MA057 = mA057;
	}
	public String getMA058() {
		return MA058;
	}
	public void setMA058(String mA058) {
		MA058 = mA058;
	}
	
	public String getMA064() {
		return MA064;
	}
	public void setMA064(String mA064) {
		MA064 = mA064;
	}
	public String getMA065() {
		return MA065;
	}
	public void setMA065(String mA065) {
		MA065 = mA065;
	}
	public String getMA066() {
		return MA066;
	}
	public void setMA066(String mA066) {
		MA066 = mA066;
	}
	public String getMA067() {
		return MA067;
	}
	public void setMA067(String mA067) {
		MA067 = mA067;
	}
	public String getMA068() {
		return MA068;
	}
	public void setMA068(String mA068) {
		MA068 = mA068;
	}
	public String getMA069() {
		return MA069;
	}
	public void setMA069(String mA069) {
		MA069 = mA069;
	}
	public String getMA070() {
		return MA070;
	}
	public void setMA070(String mA070) {
		MA070 = mA070;
	}
	public Integer getMA059() {
		return MA059;
	}
	public void setMA059(Integer mA059) {
		MA059 = mA059;
	}
	public Integer getMA060() {
		return MA060;
	}
	public void setMA060(Integer mA060) {
		MA060 = mA060;
	}
	public Integer getMA061() {
		return MA061;
	}
	public void setMA061(Integer mA061) {
		MA061 = mA061;
	}
	public Integer getMA062() {
		return MA062;
	}
	public void setMA062(Integer mA062) {
		MA062 = mA062;
	}
	public Integer getMA063() {
		return MA063;
	}
	public void setMA063(Integer mA063) {
		MA063 = mA063;
	}
	public String[] getC004ids() {
		return c004ids;
	}
	public void setC004ids(String[] c004ids) {
		this.c004ids = c004ids;
	}
	public List<C004PO> getC004poList() {
		return c004poList;
	}
	public void setC004poList(List<C004PO> c004poList) {
		this.c004poList = c004poList;
	}
	public String getHymlID() {
		return hymlID;
	}
	public void setHymlID(String hymlID) {
		this.hymlID = hymlID;
	}
	public String getHymlName() {
		return hymlName;
	}
	public void setHymlName(String hymlName) {
		this.hymlName = hymlName;
	}
	public String getHydlID() {
		return hydlID;
	}
	public void setHydlID(String hydlID) {
		this.hydlID = hydlID;
	}
	public String getHydlName() {
		return hydlName;
	}
	public void setHydlName(String hydlName) {
		this.hydlName = hydlName;
	}
	public String getHyzlID() {
		return hyzlID;
	}
	public void setHyzlID(String hyzlID) {
		this.hyzlID = hyzlID;
	}
	public String getHyzlName() {
		return hyzlName;
	}
	public void setHyzlName(String hyzlName) {
		this.hyzlName = hyzlName;
	}
	public String getHyxlID() {
		return hyxlID;
	}
	public void setHyxlID(String hyxlID) {
		this.hyxlID = hyxlID;
	}
	public String getHyxlName() {
		return hyxlName;
	}
	public void setHyxlName(String hyxlName) {
		this.hyxlName = hyxlName;
	}
	public Integer getMA071() {
		return MA071;
	}
	public void setMA071(Integer mA071) {
		MA071 = mA071;
	}
	public String getAqjcqk() {
		return aqjcqk;
	}
	public void setAqjcqk(String aqjcqk) {
		this.aqjcqk = aqjcqk;
	}
	public B00103PO getB00103po() {
		return b00103po;
	}
	public void setB00103po(B00103PO b00103po) {
		this.b00103po = b00103po;
	}
	public String getMA080() {
		return MA080;
	}
	public void setMA080(String mA080) {
		MA080 = mA080;
	}
}
