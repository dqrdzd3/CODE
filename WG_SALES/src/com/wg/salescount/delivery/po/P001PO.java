package com.wg.salescount.delivery.po;

import oracle.sql.TIMESTAMP;

public class P001PO {

	public P001PO() {
	}

	private String MA001;//主键
	private String MA002;//设备id
	private String MA003;//接口公司id,关联对外接口表
	private String MA004;//2B公司CORP_BUSINESS.MA001  corpid
	private String MA005;//备用
	private TIMESTAMP MA006;//生成日期
	
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
	public TIMESTAMP getMA006() {
		return MA006;
	}
	public void setMA006(TIMESTAMP mA006) {
		MA006 = mA006;
	}
	@Override
	public String toString() {
		return "P001 [MA001=" + MA001 + ", MA002=" + MA002 + ", MA003=" + MA003 + ", MA004=" + MA004 + ", MA005="
				+ MA005 + ", MA006=" + MA006 + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((MA001 == null) ? 0 : MA001.hashCode());
		result = prime * result + ((MA002 == null) ? 0 : MA002.hashCode());
		result = prime * result + ((MA003 == null) ? 0 : MA003.hashCode());
		result = prime * result + ((MA004 == null) ? 0 : MA004.hashCode());
		result = prime * result + ((MA005 == null) ? 0 : MA005.hashCode());
		result = prime * result + ((MA006 == null) ? 0 : MA006.hashCode());
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
		P001PO other = (P001PO) obj;
		if (MA001 == null) {
			if (other.MA001 != null)
				return false;
		} else if (!MA001.equals(other.MA001))
			return false;
		if (MA002 == null) {
			if (other.MA002 != null)
				return false;
		} else if (!MA002.equals(other.MA002))
			return false;
		if (MA003 == null) {
			if (other.MA003 != null)
				return false;
		} else if (!MA003.equals(other.MA003))
			return false;
		if (MA004 == null) {
			if (other.MA004 != null)
				return false;
		} else if (!MA004.equals(other.MA004))
			return false;
		if (MA005 == null) {
			if (other.MA005 != null)
				return false;
		} else if (!MA005.equals(other.MA005))
			return false;
		if (MA006 == null) {
			if (other.MA006 != null)
				return false;
		} else if (!MA006.equals(other.MA006))
			return false;
		return true;
	}
	public P001PO(String mA001, String mA002, String mA003, String mA004, String mA005, TIMESTAMP mA006) {
		super();
		MA001 = mA001;
		MA002 = mA002;
		MA003 = mA003;
		MA004 = mA004;
		MA005 = mA005;
		MA006 = mA006;
	}
	
	
}
