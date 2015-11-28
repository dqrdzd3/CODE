package com.wg.salescount.delivery.vo;

public class DeliveryByThirdPartyVO {

	public DeliveryByThirdPartyVO() {
	}
	
	private String p001PrimaryKey;//p001主键
	private String equipmentId;//p001设备id
	private String interfaceCompanyId;//第三方接口公司id
	private String companyName;//公司名称
	public String getPo001PrimaryKey() {
		return p001PrimaryKey;
	}
	public void setPo001PrimaryKey(String po001PrimaryKey) {
		this.p001PrimaryKey = po001PrimaryKey;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getInterfaceCompanyId() {
		return interfaceCompanyId;
	}
	public void setInterfaceCompanyId(String interfaceCompanyId) {
		this.interfaceCompanyId = interfaceCompanyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "DeliveryByThirdPartyVO [p001PrimaryKey=" + p001PrimaryKey + ", equipmentId=" + equipmentId
				+ ", interfaceCompanyId=" + interfaceCompanyId + ", companyName=" + companyName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((equipmentId == null) ? 0 : equipmentId.hashCode());
		result = prime * result + ((interfaceCompanyId == null) ? 0 : interfaceCompanyId.hashCode());
		result = prime * result + ((p001PrimaryKey == null) ? 0 : p001PrimaryKey.hashCode());
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
		DeliveryByThirdPartyVO other = (DeliveryByThirdPartyVO) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (equipmentId == null) {
			if (other.equipmentId != null)
				return false;
		} else if (!equipmentId.equals(other.equipmentId))
			return false;
		if (interfaceCompanyId == null) {
			if (other.interfaceCompanyId != null)
				return false;
		} else if (!interfaceCompanyId.equals(other.interfaceCompanyId))
			return false;
		if (p001PrimaryKey == null) {
			if (other.p001PrimaryKey != null)
				return false;
		} else if (!p001PrimaryKey.equals(other.p001PrimaryKey))
			return false;
		return true;
	}
	public DeliveryByThirdPartyVO(String po001PrimaryKey, String equipmentId, String interfaceCompanyId,
			String companyName) {
		super();
		this.p001PrimaryKey = p001PrimaryKey;
		this.equipmentId = equipmentId;
		this.interfaceCompanyId = interfaceCompanyId;
		this.companyName = companyName;
	}
	
	
}
