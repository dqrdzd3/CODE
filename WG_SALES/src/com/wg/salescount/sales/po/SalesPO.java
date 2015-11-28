package com.wg.salescount.sales.po;

import java.util.Date;

public class SalesPO {
	
	private String salesId;
	private String customerName;
	private String consigneeName;
	private String receiverAddr;
	private String phoneNo;
	private Date deliveryTime;
	private String buyersNickname;
	private String productId;
	private String productName;
	private String modelType;
	private String color;
	private float unitPrice; 
	private int quantity; 
	private float totalPrice; 
	private int moneyStatus; 
	private int invoice; 
	private String invoiceNo;
	private int salesPlatform;
	private String courierCompany;
	private String courierNo;
	private Date signTime; 
	private float courierCost; 
	private float insuredCost;
	private String city;
	private int gender; 
	private int age;
	private String jobs;
	private String remark;
	
	public SalesPO() {
		super();
	}
	
	public SalesPO(String salesId, String customerName, String consigneeName, String receiverAddr, String phoneNo,
			Date deliveryTime, String buyersNickname, String productName, String productId, String modelType,
			String color, float unitPrice, int quantity, float totalPrice, int moneyStatus, int invoice,
			String invoiceNo, int salesPlatform, String courierCompany, String courierNo, Date signTime,
			float courierCost, float insuredCost, String city, int gender, int age, String jobs, String remark) {
		super();
		this.salesId = salesId;
		this.customerName = customerName;
		this.consigneeName = consigneeName;
		this.receiverAddr = receiverAddr;
		this.phoneNo = phoneNo;
		this.deliveryTime = deliveryTime;
		this.buyersNickname = buyersNickname;
		this.productId = productId;
		this.productName = productName;
		this.modelType = modelType;
		this.color = color;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.moneyStatus = moneyStatus;
		this.invoice = invoice;
		this.invoiceNo = invoiceNo;
		this.salesPlatform = salesPlatform;
		this.courierCompany = courierCompany;
		this.courierNo = courierNo;
		this.signTime = signTime;
		this.courierCost = courierCost;
		this.insuredCost = insuredCost;
		this.city = city;
		this.gender = gender;
		this.age = age;
		this.jobs = jobs;
		this.remark = remark;
	}
		
	public String getSalesId() {
		return salesId;
	}
	public void setSalesId(String salesId) {
		this.salesId = salesId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getReceiverAddr() {
		return receiverAddr;
	}
	public void setReceiverAddr(String receiverAddr) {
		this.receiverAddr = receiverAddr;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public Date getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getBuyersNickname() {
		return buyersNickname;
	}
	public void setBuyersNickname(String buyersNickname) {
		this.buyersNickname = buyersNickname;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getModelType() {
		return modelType;
	}
	public void setModelType(String modelType) {
		this.modelType = modelType;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getMoneyStatus() {
		return moneyStatus;
	}
	public void setMoneyStatus(int moneyStatus) {
		this.moneyStatus = moneyStatus;
	}
	public int getInvoice() {
		return invoice;
	}
	public void setInvoice(int invoice) {
		this.invoice = invoice;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public int getSalesPlatform() {
		return salesPlatform;
	}
	public void setSalesPlatform(int salesPlatform) {
		this.salesPlatform = salesPlatform;
	}
	public String getCourierCompany() {
		return courierCompany;
	}
	public void setCourierCompany(String courierCompany) {
		this.courierCompany = courierCompany;
	}
	public String getCourierNo() {
		return courierNo;
	}
	public void setCourierNo(String courierNo) {
		this.courierNo = courierNo;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public float getCourierCost() {
		return courierCost;
	}
	public void setCourierCost(float courierCost) {
		this.courierCost = courierCost;
	}
	public float getInsuredCost() {
		return insuredCost;
	}
	public void setInsuredCost(float insuredCost) {
		this.insuredCost = insuredCost;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}	
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Sales [salesId=" + salesId + ", customerName=" + customerName + ", consigneeName=" + consigneeName
				+ ", receiverAddr=" + receiverAddr + ", phoneNo=" + phoneNo + ", deliveryTime=" + deliveryTime
				+ ", buyersNickname=" + buyersNickname + ", productId=" + productId + ", productName=" + productName
				+ ", modelType=" + modelType + ", color=" + color + ", unitPrice=" + unitPrice + ", quantity="
				+ quantity + ", totalPrice=" + totalPrice + ", moneyStatus=" + moneyStatus + ", invoice=" + invoice
				+ ", invoiceNo=" + invoiceNo + ", salesPlatform=" + salesPlatform + ", courierCompany=" + courierCompany
				+ ", courierNo=" + courierNo + ", signTime=" + signTime + ", courierCost=" + courierCost
				+ ", insuredCost=" + insuredCost + ", city=" + city + ", gender=" + gender + ", age=" + age + ", jobs="
				+ jobs + ", remark=" + remark + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((buyersNickname == null) ? 0 : buyersNickname.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((consigneeName == null) ? 0 : consigneeName.hashCode());
		result = prime * result + ((courierCompany == null) ? 0 : courierCompany.hashCode());
		result = prime * result + Float.floatToIntBits(courierCost);
		result = prime * result + ((courierNo == null) ? 0 : courierNo.hashCode());
		result = prime * result + ((customerName == null) ? 0 : customerName.hashCode());
		result = prime * result + ((deliveryTime == null) ? 0 : deliveryTime.hashCode());
		result = prime * result + gender;
		result = prime * result + Float.floatToIntBits(insuredCost);
		result = prime * result + invoice;
		result = prime * result + ((invoiceNo == null) ? 0 : invoiceNo.hashCode());
		result = prime * result + ((jobs == null) ? 0 : jobs.hashCode());
		result = prime * result + ((modelType == null) ? 0 : modelType.hashCode());
		result = prime * result + moneyStatus;
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((receiverAddr == null) ? 0 : receiverAddr.hashCode());
		result = prime * result + ((salesId == null) ? 0 : salesId.hashCode());
		result = prime * result + salesPlatform;
		result = prime * result + ((signTime == null) ? 0 : signTime.hashCode());
		result = prime * result + Float.floatToIntBits(totalPrice);
		result = prime * result + Float.floatToIntBits(unitPrice);
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
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
		SalesPO other = (SalesPO) obj;
		if (age != other.age)
			return false;
		if (buyersNickname == null) {
			if (other.buyersNickname != null)
				return false;
		} else if (!buyersNickname.equals(other.buyersNickname))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (consigneeName == null) {
			if (other.consigneeName != null)
				return false;
		} else if (!consigneeName.equals(other.consigneeName))
			return false;
		if (courierCompany == null) {
			if (other.courierCompany != null)
				return false;
		} else if (!courierCompany.equals(other.courierCompany))
			return false;
		if (Float.floatToIntBits(courierCost) != Float.floatToIntBits(other.courierCost))
			return false;
		if (courierNo == null) {
			if (other.courierNo != null)
				return false;
		} else if (!courierNo.equals(other.courierNo))
			return false;
		if (customerName == null) {
			if (other.customerName != null)
				return false;
		} else if (!customerName.equals(other.customerName))
			return false;
		if (deliveryTime == null) {
			if (other.deliveryTime != null)
				return false;
		} else if (!deliveryTime.equals(other.deliveryTime))
			return false;
		if (gender != other.gender)
			return false;
		if (Float.floatToIntBits(insuredCost) != Float.floatToIntBits(other.insuredCost))
			return false;
		if (invoice != other.invoice)
			return false;
		if (invoiceNo == null) {
			if (other.invoiceNo != null)
				return false;
		} else if (!invoiceNo.equals(other.invoiceNo))
			return false;
		if (jobs == null) {
			if (other.jobs != null)
				return false;
		} else if (!jobs.equals(other.jobs))
			return false;
		if (modelType == null) {
			if (other.modelType != null)
				return false;
		} else if (!modelType.equals(other.modelType))
			return false;
		if (moneyStatus != other.moneyStatus)
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity != other.quantity)
			return false;
		if (receiverAddr == null) {
			if (other.receiverAddr != null)
				return false;
		} else if (!receiverAddr.equals(other.receiverAddr))
			return false;
		if (salesId == null) {
			if (other.salesId != null)
				return false;
		} else if (!salesId.equals(other.salesId))
			return false;
		if (salesPlatform != other.salesPlatform) {
				return false;
		}
		if (signTime == null) {
			if (other.signTime != null)
				return false;
		} else if (!signTime.equals(other.signTime))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		if (Float.floatToIntBits(unitPrice) != Float.floatToIntBits(other.unitPrice))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		return true;
	}
	
}
