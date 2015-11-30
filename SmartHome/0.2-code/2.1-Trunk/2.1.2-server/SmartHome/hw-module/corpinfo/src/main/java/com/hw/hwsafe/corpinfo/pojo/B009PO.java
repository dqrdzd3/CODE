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
public class B009PO {
	/**
	 * 唯一标志
	 */
	private String MA001;
	/**
	 * 企业id
	 */
	private String MA002;
	/**
	 * 危险源Id
	 */
	private String MA003;
	/**
	 * 住宅区名称
	 */
	private String MA004;
	/**
	 * 住宅区人数
	 */
	private Integer MA005;
	/**
	 * 住宅区与危险源最近距离
	 */
	private Double MA006;
	/**
	 * 生产单位名称
	 */	
	private String MA007;	
	/**
	 * 生产单位人数
	 */
	private Integer MA008;
	/**
	 * 生产单位与危险源最近距离
	 */	
	private Double MA009;
	/**
	 * 机关团体名称
	 */
	private String MA010;
	/**
	 * 机关团体人数
	 */	
	private Integer MA011;
	/**
	 * 机关团体与危险源最近距离
	 */
	private Double MA012;
	/**
	 * 公共场所名称
	 */	
	private String MA013;
	/**
	 * 公共场所人数
	 */
	private Integer MA014;
	/**
	 * 公共场所与危险源最近距离
	 */
	private Double MA015;
	/**
	 * 交通要道名称
	 */	
	
	private String MA016;
	/**
	 * 交通要道人数
	 */
	private Integer MA017;
	/**
	 * 交通要道与危险源最近距离
	 */
	private Double MA018;
	/**
	 * 名称
	 */
	private String MA019;
	/**
	 * 人数
	 */
	private Integer MA020;
	/**
	 * 与危险源最近距离
	 */	
	private Double MA021;
	/**
	 * 图片名称
	 */
	private String MA022;
	/**
	 * 图片类型
	 */
	private Integer MA023;
	/**
	 * 图片表述
	 */
	private String MA024;
	/**
	 * 图片文件
	 */
	private String MA025;
	/**
	 * 上传时间
	 */
	private Date MA026 ;
	/**
	 * 图片别名-程序自定义名称,防止不同危险源同名图片重复
	 */
	private String MA027;
	/**
	 * 住宅区数量
	 */
	private Integer MA028;
	/**
	 * 生产单位数量
	 */
	private Integer MA029;
	/**
	 * 机关团体数量
	 */	
	private Integer MA030;	
	/**
	 * 公共场所数量
	 */
	private Integer MA031;
	/**
	 * 交通要道数量
	 */	
	private Integer MA032;
	/**
	 * 其他单位数量
	 */
	private Integer MA033;
	/**
	 * 火源数量
	 */	
	private Integer MA034;
	/**
	 * 火源简要说明
	 */
	private String MA035;
	/**
	 * 输配电装置
	 */	
	private String MA036;
	/**
	 * 其他
	 */
	private String MA037;
	/**
	 * 状态
	 */
	private Integer MA038;
	/**
	 * 填表人Id
	 */	
	private String MA039;
	/**
	 * 填表人姓名
	 */
	private String MA040;
	/**
	 * 填表人电话
	 */
	private String MA041;
	/**
	 * 填表日期
	 */
	private Date MA042;
	/**
	 * 修改时间
	 */
	private Date MA043;
	/**
	 * 作废时间
	 */	
	private Date MA044;
	/**
	 * 备注
	 */	
	private String MA045;
	/**
	 * 备注
	 */	
	private Integer MA046;
	/**
	 * 备注
	 */	
	private Integer MA047;
	
	/**
	 * 周边环境-住宅区
	 */
	private List<B00902PO> b00902List;
	/**
	 * 周边环境-生产单位
	 */
	private List<B00903PO> b00903List;
	/**
	 * 周边环境-机关团体
	 */
	private List<B00904PO> b00904List;
	/**
	 * 周边环境-公共场所
	 */
	private List<B00905PO> b00905List;
	/**
	 * 周边环境-交通要道
	 */
	private List<B00906PO> b00906List;
	/**
	 * 周边环境-其他
	 */
	private List<B00907PO> b00907List;
	
	
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
	public Integer getMA005() {
		return MA005;
	}
	public void setMA005(Integer mA005) {
		MA005 = mA005;
	}
	public Double getMA006() {
		return MA006;
	}
	public void setMA006(Double mA006) {
		MA006 = mA006;
	}
	public String getMA007() {
		return MA007;
	}
	public void setMA007(String mA007) {
		MA007 = mA007;
	}
	public Integer getMA008() {
		return MA008;
	}
	public void setMA008(Integer mA008) {
		MA008 = mA008;
	}
	public Double getMA009() {
		return MA009;
	}
	public void setMA009(Double mA009) {
		MA009 = mA009;
	}
	public String getMA010() {
		return MA010;
	}
	public void setMA010(String mA010) {
		MA010 = mA010;
	}
	public Integer getMA011() {
		return MA011;
	}
	public void setMA011(Integer mA011) {
		MA011 = mA011;
	}
	public Double getMA012() {
		return MA012;
	}
	public void setMA012(Double mA012) {
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
	public String getMA016() {
		return MA016;
	}
	public void setMA016(String mA016) {
		MA016 = mA016;
	}
	public Integer getMA017() {
		return MA017;
	}
	public void setMA017(Integer mA017) {
		MA017 = mA017;
	}
	public Double getMA018() {
		return MA018;
	}
	public void setMA018(Double mA018) {
		MA018 = mA018;
	}
	public String getMA019() {
		return MA019;
	}
	public void setMA019(String mA019) {
		MA019 = mA019;
	}
	public Integer getMA020() {
		return MA020;
	}
	public void setMA020(Integer mA020) {
		MA020 = mA020;
	}
	public Double getMA021() {
		return MA021;
	}
	public void setMA021(Double mA021) {
		MA021 = mA021;
	}
	public String getMA022() {
		return MA022;
	}
	public void setMA022(String mA022) {
		MA022 = mA022;
	}
	public Integer getMA023() {
		return MA023;
	}
	public void setMA023(Integer mA023) {
		MA023 = mA023;
	}
	public String getMA024() {
		return MA024;
	}
	public void setMA024(String mA024) {
		MA024 = mA024;
	}
	public String getMA025() {
		return MA025;
	}
	public void setMA025(String mA025) {
		MA025 = mA025;
	}
	public Date getMA026() {
		return MA026;
	}
	public void setMA026(Date mA026) {
		MA026 = mA026;
	}
	public String getMA027() {
		return MA027;
	}
	public void setMA027(String mA027) {
		MA027 = mA027;
	}
	public Integer getMA028() {
		return MA028;
	}
	public void setMA028(Integer mA028) {
		MA028 = mA028;
	}
	public Integer getMA029() {
		return MA029;
	}
	public void setMA029(Integer mA029) {
		MA029 = mA029;
	}
	public Integer getMA030() {
		return MA030;
	}
	public void setMA030(Integer mA030) {
		MA030 = mA030;
	}
	public Integer getMA031() {
		return MA031;
	}
	public void setMA031(Integer mA031) {
		MA031 = mA031;
	}
	public Integer getMA032() {
		return MA032;
	}
	public void setMA032(Integer mA032) {
		MA032 = mA032;
	}
	public Integer getMA033() {
		return MA033;
	}
	public void setMA033(Integer mA033) {
		MA033 = mA033;
	}
	public Integer getMA034() {
		return MA034;
	}
	public void setMA034(Integer mA034) {
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
	public String getMA037() {
		return MA037;
	}
	public void setMA037(String mA037) {
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
	public String getMA041() {
		return MA041;
	}
	public void setMA041(String mA041) {
		MA041 = mA041;
	}
	public Date getMA042() {
		return MA042;
	}
	public void setMA042(Date mA042) {
		MA042 = mA042;
	}
	public Date getMA043() {
		return MA043;
	}
	public void setMA043(Date mA043) {
		MA043 = mA043;
	}
	public Date getMA044() {
		return MA044;
	}
	public void setMA044(Date mA044) {
		MA044 = mA044;
	}
	public String getMA045() {
		return MA045;
	}
	public void setMA045(String mA045) {
		MA045 = mA045;
	}
	public Integer getMA046() {
		return MA046;
	}
	public void setMA046(Integer mA046) {
		MA046 = mA046;
	}
	public Integer getMA047() {
		return MA047;
	}
	public void setMA047(Integer mA047) {
		MA047 = mA047;
	}
	public List<B00902PO> getB00902List() {
		return b00902List;
	}
	public void setB00902List(List<B00902PO> b00902List) {
		this.b00902List = b00902List;
	}
	public List<B00903PO> getB00903List() {
		return b00903List;
	}
	public void setB00903List(List<B00903PO> b00903List) {
		this.b00903List = b00903List;
	}
	public List<B00904PO> getB00904List() {
		return b00904List;
	}
	public void setB00904List(List<B00904PO> b00904List) {
		this.b00904List = b00904List;
	}
	public List<B00905PO> getB00905List() {
		return b00905List;
	}
	public void setB00905List(List<B00905PO> b00905List) {
		this.b00905List = b00905List;
	}
	public List<B00906PO> getB00906List() {
		return b00906List;
	}
	public void setB00906List(List<B00906PO> b00906List) {
		this.b00906List = b00906List;
	}
	public List<B00907PO> getB00907List() {
		return b00907List;
	}
	public void setB00907List(List<B00907PO> b00907List) {
		this.b00907List = b00907List;
	}
	
	
}
