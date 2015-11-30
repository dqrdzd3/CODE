package com.hw.smarthome.mom.dao.shhistorymonth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ShHistoryMonth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SH_HISTORY_MONTH", schema = "SH")
public class ShHistoryMonth implements java.io.Serializable {

	private static final long serialVersionUID = -4064736337604836131L;
	// Fields
	@Id
	@Column(name = "MA001", nullable = false, length = 40)
	private String ma001;
	@Column(name = "MA002", length = 50, nullable = false)
	private String ma002;
	@Column(name = "MA003", length = 64, nullable = false)
	private String ma003;
	@Column(name = "MA004", length = 8, nullable = false)
	private String ma004;
	@Column(name = "MA005", length = 8, nullable = false)
	private String ma005;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA006", length = 7)
	private Date ma006;
	@Column(name = "MA007", length = 8)
	private String ma007;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA008", length = 7)
	private Date ma008;
	@Column(name = "MA009", length = 8)
	private String ma009;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA010", length = 7)
	private Date ma010;
	@Column(name = "MA011", length = 8)
	private String ma011;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA012", length = 7)
	private Date ma012;
	@Column(name = "MA013", length = 8)
	private String ma013;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA014", length = 7)
	private Date ma014;
	@Column(name = "MA015", length = 8)
	private String ma015;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA016", length = 7)
	private Date ma016;
	@Column(name = "MA017", length = 8)
	private String ma017;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA018", length = 7)
	private Date ma018;
	@Column(name = "MA019", length = 8)
	private String ma019;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA020", length = 7)
	private Date ma020;
	@Column(name = "MA021", length = 8)
	private String ma021;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA022", length = 7)
	private Date ma022;
	@Column(name = "MA023", length = 8)
	private String ma023;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA024", length = 7)
	private Date ma024;
	@Column(name = "MA025", length = 8)
	private String ma025;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA026", length = 7)
	private Date ma026;
	@Column(name = "MA027", length = 8)
	private String ma027;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA028", length = 7)
	private Date ma028;
	@Column(name = "MA029", length = 8)
	private String ma029;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA030", length = 7)
	private Date ma030;
	@Column(name = "MA031", length = 8)
	private String ma031;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA032", length = 7)
	private Date ma032;
	@Column(name = "MA033", length = 8)
	private String ma033;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA034", length = 7)
	private Date ma034;
	@Column(name = "MA035", length = 8)
	private String ma035;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA036", length = 7)
	private Date ma036;
	@Column(name = "MA037", length = 8)
	private String ma037;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA038", length = 7)
	private Date ma038;
	@Column(name = "MA039", length = 8)
	private String ma039;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA040", length = 7)
	private Date ma040;
	@Column(name = "MA041", length = 8)
	private String ma041;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA042", length = 7)
	private Date ma042;
	@Column(name = "MA043", length = 8)
	private String ma043;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA044", length = 7)
	private Date ma044;
	@Column(name = "MA045", length = 8)
	private String ma045;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA046", length = 7)
	private Date ma046;
	@Column(name = "MA047", length = 8)
	private String ma047;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA048", length = 7)
	private Date ma048;
	@Column(name = "MA049", length = 8)
	private String ma049;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA050", length = 7)
	private Date ma050;
	@Column(name = "MA051", length = 8)
	private String ma051;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA052", length = 7)
	private Date ma052;
	@Column(name = "MA053", length = 8)
	private String ma053;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA054", length = 7)
	private Date ma054;
	@Column(name = "MA055", length = 8)
	private String ma055;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA056", length = 7)
	private Date ma056;
	@Column(name = "MA057", length = 8)
	private String ma057;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA058", length = 7)
	private Date ma058;
	@Column(name = "MA059", length = 8)
	private String ma059;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA060", length = 7)
	private Date ma060;
	@Column(name = "MA061", length = 8)
	private String ma061;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA062", length = 7)
	private Date ma062;
	@Column(name = "MA063", length = 8)
	private String ma063;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA064", length = 7)
	private Date ma064;
	@Column(name = "MA065", length = 8)
	private String ma065;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "MA066", length = 7)
	private Date ma066;
	@Column(name = "MA067", length = 200)
	private String ma067;
	@Column(name = "MA068", length = 200)
	private String ma068;
	@Column(name = "MA069", length = 40)
	private String ma069;
	@Column(name = "MA070", length = 100)
	private String ma070;

	public String getMa001() {
		return ma001;
	}

	public void setMa001(String ma001) {
		this.ma001 = ma001;
	}

	public String getMa002() {
		return ma002;
	}

	public void setMa002(String ma002) {
		this.ma002 = ma002;
	}

	public String getMa003() {
		return ma003;
	}

	public void setMa003(String ma003) {
		this.ma003 = ma003;
	}

	public String getMa004() {
		return ma004;
	}

	public void setMa004(String ma004) {
		this.ma004 = ma004;
	}

	public String getMa005() {
		return ma005;
	}

	public void setMa005(String ma005) {
		this.ma005 = ma005;
	}

	public Date getMa006() {
		return ma006;
	}

	public void setMa006(Date ma006) {
		this.ma006 = ma006;
	}

	public String getMa007() {
		return ma007;
	}

	public void setMa007(String ma007) {
		this.ma007 = ma007;
	}

	public Date getMa008() {
		return ma008;
	}

	public void setMa008(Date ma008) {
		this.ma008 = ma008;
	}

	public String getMa009() {
		return ma009;
	}

	public void setMa009(String ma009) {
		this.ma009 = ma009;
	}

	public Date getMa010() {
		return ma010;
	}

	public void setMa010(Date ma010) {
		this.ma010 = ma010;
	}

	public String getMa011() {
		return ma011;
	}

	public void setMa011(String ma011) {
		this.ma011 = ma011;
	}

	public Date getMa012() {
		return ma012;
	}

	public void setMa012(Date ma012) {
		this.ma012 = ma012;
	}

	public String getMa013() {
		return ma013;
	}

	public void setMa013(String ma013) {
		this.ma013 = ma013;
	}

	public Date getMa014() {
		return ma014;
	}

	public void setMa014(Date ma014) {
		this.ma014 = ma014;
	}

	public String getMa015() {
		return ma015;
	}

	public void setMa015(String ma015) {
		this.ma015 = ma015;
	}

	public Date getMa016() {
		return ma016;
	}

	public void setMa016(Date ma016) {
		this.ma016 = ma016;
	}

	public String getMa017() {
		return ma017;
	}

	public void setMa017(String ma017) {
		this.ma017 = ma017;
	}

	public Date getMa018() {
		return ma018;
	}

	public void setMa018(Date ma018) {
		this.ma018 = ma018;
	}

	public String getMa019() {
		return ma019;
	}

	public void setMa019(String ma019) {
		this.ma019 = ma019;
	}

	public Date getMa020() {
		return ma020;
	}

	public void setMa020(Date ma020) {
		this.ma020 = ma020;
	}

	public String getMa021() {
		return ma021;
	}

	public void setMa021(String ma021) {
		this.ma021 = ma021;
	}

	public Date getMa022() {
		return ma022;
	}

	public void setMa022(Date ma022) {
		this.ma022 = ma022;
	}

	public String getMa023() {
		return ma023;
	}

	public void setMa023(String ma023) {
		this.ma023 = ma023;
	}

	public Date getMa024() {
		return ma024;
	}

	public void setMa024(Date ma024) {
		this.ma024 = ma024;
	}

	public String getMa025() {
		return ma025;
	}

	public void setMa025(String ma025) {
		this.ma025 = ma025;
	}

	public Date getMa026() {
		return ma026;
	}

	public void setMa026(Date ma026) {
		this.ma026 = ma026;
	}

	public String getMa027() {
		return ma027;
	}

	public void setMa027(String ma027) {
		this.ma027 = ma027;
	}

	public Date getMa028() {
		return ma028;
	}

	public void setMa028(Date ma028) {
		this.ma028 = ma028;
	}

	public String getMa029() {
		return ma029;
	}

	public void setMa029(String ma029) {
		this.ma029 = ma029;
	}

	public Date getMa030() {
		return ma030;
	}

	public void setMa030(Date ma030) {
		this.ma030 = ma030;
	}

	public String getMa031() {
		return ma031;
	}

	public void setMa031(String ma031) {
		this.ma031 = ma031;
	}

	public Date getMa032() {
		return ma032;
	}

	public void setMa032(Date ma032) {
		this.ma032 = ma032;
	}

	public String getMa033() {
		return ma033;
	}

	public void setMa033(String ma033) {
		this.ma033 = ma033;
	}

	public Date getMa034() {
		return ma034;
	}

	public void setMa034(Date ma034) {
		this.ma034 = ma034;
	}

	public String getMa035() {
		return ma035;
	}

	public void setMa035(String ma035) {
		this.ma035 = ma035;
	}

	public Date getMa036() {
		return ma036;
	}

	public void setMa036(Date ma036) {
		this.ma036 = ma036;
	}

	public String getMa037() {
		return ma037;
	}

	public void setMa037(String ma037) {
		this.ma037 = ma037;
	}

	public Date getMa038() {
		return ma038;
	}

	public void setMa038(Date ma038) {
		this.ma038 = ma038;
	}

	public String getMa039() {
		return ma039;
	}

	public void setMa039(String ma039) {
		this.ma039 = ma039;
	}

	public Date getMa040() {
		return ma040;
	}

	public void setMa040(Date ma040) {
		this.ma040 = ma040;
	}

	public String getMa041() {
		return ma041;
	}

	public void setMa041(String ma041) {
		this.ma041 = ma041;
	}

	public Date getMa042() {
		return ma042;
	}

	public void setMa042(Date ma042) {
		this.ma042 = ma042;
	}

	public String getMa043() {
		return ma043;
	}

	public void setMa043(String ma043) {
		this.ma043 = ma043;
	}

	public Date getMa044() {
		return ma044;
	}

	public void setMa044(Date ma044) {
		this.ma044 = ma044;
	}

	public String getMa045() {
		return ma045;
	}

	public void setMa045(String ma045) {
		this.ma045 = ma045;
	}

	public Date getMa046() {
		return ma046;
	}

	public void setMa046(Date ma046) {
		this.ma046 = ma046;
	}

	public String getMa047() {
		return ma047;
	}

	public void setMa047(String ma047) {
		this.ma047 = ma047;
	}

	public Date getMa048() {
		return ma048;
	}

	public void setMa048(Date ma048) {
		this.ma048 = ma048;
	}

	public String getMa049() {
		return ma049;
	}

	public void setMa049(String ma049) {
		this.ma049 = ma049;
	}

	public Date getMa050() {
		return ma050;
	}

	public void setMa050(Date ma050) {
		this.ma050 = ma050;
	}

	public String getMa051() {
		return ma051;
	}

	public void setMa051(String ma051) {
		this.ma051 = ma051;
	}

	public Date getMa052() {
		return ma052;
	}

	public void setMa052(Date ma052) {
		this.ma052 = ma052;
	}

	public String getMa053() {
		return ma053;
	}

	public void setMa053(String ma053) {
		this.ma053 = ma053;
	}

	public Date getMa054() {
		return ma054;
	}

	public void setMa054(Date ma054) {
		this.ma054 = ma054;
	}

	public String getMa055() {
		return ma055;
	}

	public void setMa055(String ma055) {
		this.ma055 = ma055;
	}

	public Date getMa056() {
		return ma056;
	}

	public void setMa056(Date ma056) {
		this.ma056 = ma056;
	}

	public String getMa057() {
		return ma057;
	}

	public void setMa057(String ma057) {
		this.ma057 = ma057;
	}

	public Date getMa058() {
		return ma058;
	}

	public void setMa058(Date ma058) {
		this.ma058 = ma058;
	}

	public String getMa059() {
		return ma059;
	}

	public void setMa059(String ma059) {
		this.ma059 = ma059;
	}

	public Date getMa060() {
		return ma060;
	}

	public void setMa060(Date ma060) {
		this.ma060 = ma060;
	}

	public String getMa061() {
		return ma061;
	}

	public void setMa061(String ma061) {
		this.ma061 = ma061;
	}

	public Date getMa062() {
		return ma062;
	}

	public void setMa062(Date ma062) {
		this.ma062 = ma062;
	}

	public String getMa063() {
		return ma063;
	}

	public void setMa063(String ma063) {
		this.ma063 = ma063;
	}

	public Date getMa064() {
		return ma064;
	}

	public void setMa064(Date ma064) {
		this.ma064 = ma064;
	}

	public String getMa065() {
		return ma065;
	}

	public void setMa065(String ma065) {
		this.ma065 = ma065;
	}

	public Date getMa066() {
		return ma066;
	}

	public void setMa066(Date ma066) {
		this.ma066 = ma066;
	}

	public String getMa067() {
		return ma067;
	}

	public void setMa067(String ma067) {
		this.ma067 = ma067;
	}

	public String getMa068() {
		return ma068;
	}

	public void setMa068(String ma068) {
		this.ma068 = ma068;
	}

	public String getMa069() {
		return ma069;
	}

	public void setMa069(String ma069) {
		this.ma069 = ma069;
	}

	public String getMa070() {
		return ma070;
	}

	public void setMa070(String ma070) {
		this.ma070 = ma070;
	}
	
	
}