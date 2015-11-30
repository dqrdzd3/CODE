package com.hw.smarthome.mom.dao.shhistoryhour;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * ShHistoryHour entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name = "SH_HISTORY_HOUR", schema = "SH")
public class ShHistoryHour implements java.io.Serializable {

	private static final long serialVersionUID = 4455435951687384339L;
	// Fields
	@Id
	@Column(name = "MA001", nullable = false, length = 40)
	private String ma001;
	@Column(name = "MA002", nullable = false, length = 50)
	private String ma002;
	@Column(name = "MA003", nullable = false, length = 64)
	private String ma003;
	@Column(name = "MA004", nullable = false, length = 8)
	private String ma004;
	@Column(name = "MA005", length = 8)
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

	@Column(name = "MA053", length = 20)
	private String ma053;
	@Column(name = "MA054", length = 40)
	private String ma054;
	@Column(name = "MA055", length = 40)
	private String ma055;
	@Column(name = "MA056", length = 40)
	private String ma056;
	@Column(name = "MA057", length = 40)
	private String ma057;
	@Column(name = "MA058", length = 40)
	private String ma058;
	@Column(name = "MA059", length = 20)
	private String ma059;
	@Column(name = "MA060", length = 20)
	private String ma060;

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

	public String getMa054() {
		return ma054;
	}

	public void setMa054(String ma054) {
		this.ma054 = ma054;
	}

	public String getMa055() {
		return ma055;
	}

	public void setMa055(String ma055) {
		this.ma055 = ma055;
	}

	public String getMa056() {
		return ma056;
	}

	public void setMa056(String ma056) {
		this.ma056 = ma056;
	}

	public String getMa057() {
		return ma057;
	}

	public void setMa057(String ma057) {
		this.ma057 = ma057;
	}

	public String getMa058() {
		return ma058;
	}

	public void setMa058(String ma058) {
		this.ma058 = ma058;
	}

	public String getMa059() {
		return ma059;
	}

	public void setMa059(String ma059) {
		this.ma059 = ma059;
	}

	public String getMa060() {
		return ma060;
	}

	public void setMa060(String ma060) {
		this.ma060 = ma060;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((ma001 == null) ? 0 : ma001.hashCode());
		result = prime * result
				+ ((ma002 == null) ? 0 : ma002.hashCode());
		result = prime * result
				+ ((ma003 == null) ? 0 : ma003.hashCode());
		result = prime * result
				+ ((ma004 == null) ? 0 : ma004.hashCode());
		result = prime * result
				+ ((ma005 == null) ? 0 : ma005.hashCode());
		result = prime * result
				+ ((ma006 == null) ? 0 : ma006.hashCode());
		result = prime * result
				+ ((ma007 == null) ? 0 : ma007.hashCode());
		result = prime * result
				+ ((ma008 == null) ? 0 : ma008.hashCode());
		result = prime * result
				+ ((ma009 == null) ? 0 : ma009.hashCode());
		result = prime * result
				+ ((ma010 == null) ? 0 : ma010.hashCode());
		result = prime * result
				+ ((ma011 == null) ? 0 : ma011.hashCode());
		result = prime * result
				+ ((ma012 == null) ? 0 : ma012.hashCode());
		result = prime * result
				+ ((ma013 == null) ? 0 : ma013.hashCode());
		result = prime * result
				+ ((ma014 == null) ? 0 : ma014.hashCode());
		result = prime * result
				+ ((ma015 == null) ? 0 : ma015.hashCode());
		result = prime * result
				+ ((ma016 == null) ? 0 : ma016.hashCode());
		result = prime * result
				+ ((ma017 == null) ? 0 : ma017.hashCode());
		result = prime * result
				+ ((ma018 == null) ? 0 : ma018.hashCode());
		result = prime * result
				+ ((ma019 == null) ? 0 : ma019.hashCode());
		result = prime * result
				+ ((ma020 == null) ? 0 : ma020.hashCode());
		result = prime * result
				+ ((ma021 == null) ? 0 : ma021.hashCode());
		result = prime * result
				+ ((ma022 == null) ? 0 : ma022.hashCode());
		result = prime * result
				+ ((ma023 == null) ? 0 : ma023.hashCode());
		result = prime * result
				+ ((ma024 == null) ? 0 : ma024.hashCode());
		result = prime * result
				+ ((ma025 == null) ? 0 : ma025.hashCode());
		result = prime * result
				+ ((ma026 == null) ? 0 : ma026.hashCode());
		result = prime * result
				+ ((ma027 == null) ? 0 : ma027.hashCode());
		result = prime * result
				+ ((ma028 == null) ? 0 : ma028.hashCode());
		result = prime * result
				+ ((ma029 == null) ? 0 : ma029.hashCode());
		result = prime * result
				+ ((ma030 == null) ? 0 : ma030.hashCode());
		result = prime * result
				+ ((ma031 == null) ? 0 : ma031.hashCode());
		result = prime * result
				+ ((ma032 == null) ? 0 : ma032.hashCode());
		result = prime * result
				+ ((ma033 == null) ? 0 : ma033.hashCode());
		result = prime * result
				+ ((ma034 == null) ? 0 : ma034.hashCode());
		result = prime * result
				+ ((ma035 == null) ? 0 : ma035.hashCode());
		result = prime * result
				+ ((ma036 == null) ? 0 : ma036.hashCode());
		result = prime * result
				+ ((ma037 == null) ? 0 : ma037.hashCode());
		result = prime * result
				+ ((ma038 == null) ? 0 : ma038.hashCode());
		result = prime * result
				+ ((ma039 == null) ? 0 : ma039.hashCode());
		result = prime * result
				+ ((ma040 == null) ? 0 : ma040.hashCode());
		result = prime * result
				+ ((ma041 == null) ? 0 : ma041.hashCode());
		result = prime * result
				+ ((ma042 == null) ? 0 : ma042.hashCode());
		result = prime * result
				+ ((ma043 == null) ? 0 : ma043.hashCode());
		result = prime * result
				+ ((ma044 == null) ? 0 : ma044.hashCode());
		result = prime * result
				+ ((ma045 == null) ? 0 : ma045.hashCode());
		result = prime * result
				+ ((ma046 == null) ? 0 : ma046.hashCode());
		result = prime * result
				+ ((ma047 == null) ? 0 : ma047.hashCode());
		result = prime * result
				+ ((ma048 == null) ? 0 : ma048.hashCode());
		result = prime * result
				+ ((ma049 == null) ? 0 : ma049.hashCode());
		result = prime * result
				+ ((ma050 == null) ? 0 : ma050.hashCode());
		result = prime * result
				+ ((ma051 == null) ? 0 : ma051.hashCode());
		result = prime * result
				+ ((ma052 == null) ? 0 : ma052.hashCode());
		result = prime * result
				+ ((ma053 == null) ? 0 : ma053.hashCode());
		result = prime * result
				+ ((ma054 == null) ? 0 : ma054.hashCode());
		result = prime * result
				+ ((ma055 == null) ? 0 : ma055.hashCode());
		result = prime * result
				+ ((ma056 == null) ? 0 : ma056.hashCode());
		result = prime * result
				+ ((ma057 == null) ? 0 : ma057.hashCode());
		result = prime * result
				+ ((ma058 == null) ? 0 : ma058.hashCode());
		result = prime * result
				+ ((ma059 == null) ? 0 : ma059.hashCode());
		result = prime * result
				+ ((ma060 == null) ? 0 : ma060.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShHistoryHour other = (ShHistoryHour) obj;
		if (ma001 == null) {
			if (other.ma001 != null)
				return false;
		} else if (!ma001.equals(other.ma001))
			return false;
		if (ma002 == null) {
			if (other.ma002 != null)
				return false;
		} else if (!ma002.equals(other.ma002))
			return false;
		if (ma003 == null) {
			if (other.ma003 != null)
				return false;
		} else if (!ma003.equals(other.ma003))
			return false;
		if (ma004 == null) {
			if (other.ma004 != null)
				return false;
		} else if (!ma004.equals(other.ma004))
			return false;
		if (ma005 == null) {
			if (other.ma005 != null)
				return false;
		} else if (!ma005.equals(other.ma005))
			return false;
		if (ma006 == null) {
			if (other.ma006 != null)
				return false;
		} else if (!ma006.equals(other.ma006))
			return false;
		if (ma007 == null) {
			if (other.ma007 != null)
				return false;
		} else if (!ma007.equals(other.ma007))
			return false;
		if (ma008 == null) {
			if (other.ma008 != null)
				return false;
		} else if (!ma008.equals(other.ma008))
			return false;
		if (ma009 == null) {
			if (other.ma009 != null)
				return false;
		} else if (!ma009.equals(other.ma009))
			return false;
		if (ma010 == null) {
			if (other.ma010 != null)
				return false;
		} else if (!ma010.equals(other.ma010))
			return false;
		if (ma011 == null) {
			if (other.ma011 != null)
				return false;
		} else if (!ma011.equals(other.ma011))
			return false;
		if (ma012 == null) {
			if (other.ma012 != null)
				return false;
		} else if (!ma012.equals(other.ma012))
			return false;
		if (ma013 == null) {
			if (other.ma013 != null)
				return false;
		} else if (!ma013.equals(other.ma013))
			return false;
		if (ma014 == null) {
			if (other.ma014 != null)
				return false;
		} else if (!ma014.equals(other.ma014))
			return false;
		if (ma015 == null) {
			if (other.ma015 != null)
				return false;
		} else if (!ma015.equals(other.ma015))
			return false;
		if (ma016 == null) {
			if (other.ma016 != null)
				return false;
		} else if (!ma016.equals(other.ma016))
			return false;
		if (ma017 == null) {
			if (other.ma017 != null)
				return false;
		} else if (!ma017.equals(other.ma017))
			return false;
		if (ma018 == null) {
			if (other.ma018 != null)
				return false;
		} else if (!ma018.equals(other.ma018))
			return false;
		if (ma019 == null) {
			if (other.ma019 != null)
				return false;
		} else if (!ma019.equals(other.ma019))
			return false;
		if (ma020 == null) {
			if (other.ma020 != null)
				return false;
		} else if (!ma020.equals(other.ma020))
			return false;
		if (ma021 == null) {
			if (other.ma021 != null)
				return false;
		} else if (!ma021.equals(other.ma021))
			return false;
		if (ma022 == null) {
			if (other.ma022 != null)
				return false;
		} else if (!ma022.equals(other.ma022))
			return false;
		if (ma023 == null) {
			if (other.ma023 != null)
				return false;
		} else if (!ma023.equals(other.ma023))
			return false;
		if (ma024 == null) {
			if (other.ma024 != null)
				return false;
		} else if (!ma024.equals(other.ma024))
			return false;
		if (ma025 == null) {
			if (other.ma025 != null)
				return false;
		} else if (!ma025.equals(other.ma025))
			return false;
		if (ma026 == null) {
			if (other.ma026 != null)
				return false;
		} else if (!ma026.equals(other.ma026))
			return false;
		if (ma027 == null) {
			if (other.ma027 != null)
				return false;
		} else if (!ma027.equals(other.ma027))
			return false;
		if (ma028 == null) {
			if (other.ma028 != null)
				return false;
		} else if (!ma028.equals(other.ma028))
			return false;
		if (ma029 == null) {
			if (other.ma029 != null)
				return false;
		} else if (!ma029.equals(other.ma029))
			return false;
		if (ma030 == null) {
			if (other.ma030 != null)
				return false;
		} else if (!ma030.equals(other.ma030))
			return false;
		if (ma031 == null) {
			if (other.ma031 != null)
				return false;
		} else if (!ma031.equals(other.ma031))
			return false;
		if (ma032 == null) {
			if (other.ma032 != null)
				return false;
		} else if (!ma032.equals(other.ma032))
			return false;
		if (ma033 == null) {
			if (other.ma033 != null)
				return false;
		} else if (!ma033.equals(other.ma033))
			return false;
		if (ma034 == null) {
			if (other.ma034 != null)
				return false;
		} else if (!ma034.equals(other.ma034))
			return false;
		if (ma035 == null) {
			if (other.ma035 != null)
				return false;
		} else if (!ma035.equals(other.ma035))
			return false;
		if (ma036 == null) {
			if (other.ma036 != null)
				return false;
		} else if (!ma036.equals(other.ma036))
			return false;
		if (ma037 == null) {
			if (other.ma037 != null)
				return false;
		} else if (!ma037.equals(other.ma037))
			return false;
		if (ma038 == null) {
			if (other.ma038 != null)
				return false;
		} else if (!ma038.equals(other.ma038))
			return false;
		if (ma039 == null) {
			if (other.ma039 != null)
				return false;
		} else if (!ma039.equals(other.ma039))
			return false;
		if (ma040 == null) {
			if (other.ma040 != null)
				return false;
		} else if (!ma040.equals(other.ma040))
			return false;
		if (ma041 == null) {
			if (other.ma041 != null)
				return false;
		} else if (!ma041.equals(other.ma041))
			return false;
		if (ma042 == null) {
			if (other.ma042 != null)
				return false;
		} else if (!ma042.equals(other.ma042))
			return false;
		if (ma043 == null) {
			if (other.ma043 != null)
				return false;
		} else if (!ma043.equals(other.ma043))
			return false;
		if (ma044 == null) {
			if (other.ma044 != null)
				return false;
		} else if (!ma044.equals(other.ma044))
			return false;
		if (ma045 == null) {
			if (other.ma045 != null)
				return false;
		} else if (!ma045.equals(other.ma045))
			return false;
		if (ma046 == null) {
			if (other.ma046 != null)
				return false;
		} else if (!ma046.equals(other.ma046))
			return false;
		if (ma047 == null) {
			if (other.ma047 != null)
				return false;
		} else if (!ma047.equals(other.ma047))
			return false;
		if (ma048 == null) {
			if (other.ma048 != null)
				return false;
		} else if (!ma048.equals(other.ma048))
			return false;
		if (ma049 == null) {
			if (other.ma049 != null)
				return false;
		} else if (!ma049.equals(other.ma049))
			return false;
		if (ma050 == null) {
			if (other.ma050 != null)
				return false;
		} else if (!ma050.equals(other.ma050))
			return false;
		if (ma051 == null) {
			if (other.ma051 != null)
				return false;
		} else if (!ma051.equals(other.ma051))
			return false;
		if (ma052 == null) {
			if (other.ma052 != null)
				return false;
		} else if (!ma052.equals(other.ma052))
			return false;
		if (ma053 == null) {
			if (other.ma053 != null)
				return false;
		} else if (!ma053.equals(other.ma053))
			return false;
		if (ma054 == null) {
			if (other.ma054 != null)
				return false;
		} else if (!ma054.equals(other.ma054))
			return false;
		if (ma055 == null) {
			if (other.ma055 != null)
				return false;
		} else if (!ma055.equals(other.ma055))
			return false;
		if (ma056 == null) {
			if (other.ma056 != null)
				return false;
		} else if (!ma056.equals(other.ma056))
			return false;
		if (ma057 == null) {
			if (other.ma057 != null)
				return false;
		} else if (!ma057.equals(other.ma057))
			return false;
		if (ma058 == null) {
			if (other.ma058 != null)
				return false;
		} else if (!ma058.equals(other.ma058))
			return false;
		if (ma059 == null) {
			if (other.ma059 != null)
				return false;
		} else if (!ma059.equals(other.ma059))
			return false;
		if (ma060 == null) {
			if (other.ma060 != null)
				return false;
		} else if (!ma060.equals(other.ma060))
			return false;
		return true;
	}

}