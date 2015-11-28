package com.wg.salescount.delivery.vo;

public class DeliveryBySupplierVO {

	public DeliveryBySupplierVO() {
	}
	
	private String p001PrimaryKey;//p001主键
	private String equipmentId;//p001设备id
	private String corpBusinessId;//2B公司CORP_BUSINESS id
	private String companyName;//公司名称
	public String getP001PrimaryKey() {
		return p001PrimaryKey;
	}
	public void setP001PrimaryKey(String p001PrimaryKey) {
		this.p001PrimaryKey = p001PrimaryKey;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getCorpBusinessId() {
		return corpBusinessId;
	}
	public void setCorpBusinessId(String corpBusinessId) {
		this.corpBusinessId = corpBusinessId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "DeliveryBySupplierVO [p001PrimaryKey=" + p001PrimaryKey + ", equipmentId=" + equipmentId
				+ ", corpBusinessId=" + corpBusinessId + ", companyName=" + companyName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyName == null) ? 0 : companyName.hashCode());
		result = prime * result + ((corpBusinessId == null) ? 0 : corpBusinessId.hashCode());
		result = prime * result + ((equipmentId == null) ? 0 : equipmentId.hashCode());
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
		DeliveryBySupplierVO other = (DeliveryBySupplierVO) obj;
		if (companyName == null) {
			if (other.companyName != null)
				return false;
		} else if (!companyName.equals(other.companyName))
			return false;
		if (corpBusinessId == null) {
			if (other.corpBusinessId != null)
				return false;
		} else if (!corpBusinessId.equals(other.corpBusinessId))
			return false;
		if (equipmentId == null) {
			if (other.equipmentId != null)
				return false;
		} else if (!equipmentId.equals(other.equipmentId))
			return false;
		if (p001PrimaryKey == null) {
			if (other.p001PrimaryKey != null)
				return false;
		} else if (!p001PrimaryKey.equals(other.p001PrimaryKey))
			return false;
		return true;
	}
	public DeliveryBySupplierVO(String p001PrimaryKey, String equipmentId, String corpBusinessId, String companyName) {
		super();
		this.p001PrimaryKey = p001PrimaryKey;
		this.equipmentId = equipmentId;
		this.corpBusinessId = corpBusinessId;
		this.companyName = companyName;
	}
	
	
}
