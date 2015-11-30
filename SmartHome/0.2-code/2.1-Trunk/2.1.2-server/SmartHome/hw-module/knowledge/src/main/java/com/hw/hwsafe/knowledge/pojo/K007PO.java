package com.hw.hwsafe.knowledge.pojo;

import java.util.Date;
import java.util.List;

import com.hw.hwsafe.attachment.pojo.C004PO;
/**
 * 
 * 
 * 项目名称：hwsafe
 * 类名称：K007PO
 * 类描述：
 * 创建人：李玉梅
 * 创建时间：2012-6-6 下午3:15:23
 * 修改人：李玉梅
 * 修改时间：2012-6-6 下午3:15:23
 * 修改备注：
 * @version 
 *
 */
public class K007PO {
	/**
	 * 主键
	 */
	private String MA001;
	/**
	 * 企业ID
	 */
	private String MA002;
	/**
	 * 危险源ID
	 */
	private String MA003;
	/**
	 * 预案名称
	 */
	private String MA004;
	/**
	 * 预案类型
	 */
	private String MA005;
	/**
	 * 事故类型
	 */
	private String MA006;
	/**
	 * 事故子类型
	 */	
	private String MA007;	
	/**
	 * 预案级别
	 */
	private String MA008;
	/**
	 * 内容
	 */	
	private String MA009;
	/**
	 * 文件路径
	 */
	private String MA010;
	/**
	 * 状态
	 */	
	private String MA011;
	/**
	 * 图片id
	 */
	private String MA012;
	/**
	 * 客户端填写日期
	 */	
	private Date MA013;
	/**
	 * 填报人姓名
	 */
	private String MA014;
	/**
	 * 审核日期
	 */
	private Date MA015;
	/**
	 * 审核人姓名
	 */	
	private String MA016;
	/**
	 * 系统更新日期
	 */
	private Date MA017;
	/**
	 * 系统填表日期
	 */
	private Date MA018;
	/**
	 * 网页更新日期
	 */
	private Date MA019;
	/**
	 * 预留字段
	 */
	private String MA020;
	/**
	 * 预案类型名称
	 */
	private String yalxname;
	
	/**
	 * 附件列表的id数组
	 */
	private String[] c004ids;
	
	private List<C004PO> c004poList;
	
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
	public String getMA012() {
		return MA012;
	}
	public void setMA012(String mA012) {
		MA012 = mA012;
	}
	public Date getMA013() {
		return MA013;
	}
	public void setMA013(Date mA013) {
		MA013 = mA013;
	}
	public String getMA014() {
		return MA014;
	}
	public void setMA014(String mA014) {
		MA014 = mA014;
	}
	public Date getMA015() {
		return MA015;
	}
	public void setMA015(Date mA015) {
		MA015 = mA015;
	}
	public String getMA016() {
		return MA016;
	}
	public void setMA016(String mA016) {
		MA016 = mA016;
	}
	public Date getMA017() {
		return MA017;
	}
	public void setMA017(Date mA017) {
		MA017 = mA017;
	}
	public Date getMA018() {
		return MA018;
	}
	public void setMA018(Date mA018) {
		MA018 = mA018;
	}
	public Date getMA019() {
		return MA019;
	}
	public void setMA019(Date mA019) {
		MA019 = mA019;
	}
	public String getMA020() {
		return MA020;
	}
	public void setMA020(String mA020) {
		MA020 = mA020;
	}
	public String getYalxname() {
		return yalxname;
	}
	public void setYalxname(String yalxname) {
		this.yalxname = yalxname;
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



}
