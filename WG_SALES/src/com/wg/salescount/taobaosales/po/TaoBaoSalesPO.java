package com.wg.salescount.taobaosales.po;

import java.util.Date;

public class TaoBaoSalesPO {
	
	private String taobaoSalesId;
	private String buyersUsername;
	private String buyersAlipayAccount;
	private float payables;
	private float actualPayment;
	private float totalPrice; 
	private int orderStatus; 
	private String buyersMessage;
	private String consigneeName;
	private String receiverAddr;
	private String transportMethods;
	private String phoneNo;
	private String cellphoneNo;
	private Date ordersCreatedTime; 
	private Date ordersPayedTime;
	private String productTitle;
	private String productSort;
	private int quantity; 
	private String logisticsNo;
	private String logisticsCompany;
	private String storeId;
	private String storeName;
	private String closedReason;
	
	public TaoBaoSalesPO() {
		super();
	}

	public TaoBaoSalesPO(String taobaoSalesId, String buyersUsername, String buyersAlipayAccount, float payables,
			float actualPayment, float totalPrice, int orderStatus, String buyersMessage, String consigneeName,
			String receiverAddr, String transportMethods, String phoneNo, String cellphoneNo, Date ordersCreatedTime,
			Date ordersPayedTime, String productTitle, String productSort, int quantity, String logisticsNo,
			String logisticsCompany, String storeId, String storeName, String closedReason) {
		super();
		this.taobaoSalesId = taobaoSalesId;
		this.buyersUsername = buyersUsername;
		this.buyersAlipayAccount = buyersAlipayAccount;
		this.payables = payables;
		this.actualPayment = actualPayment;
		this.totalPrice = totalPrice;
		this.orderStatus = orderStatus;
		this.buyersMessage = buyersMessage;
		this.consigneeName = consigneeName;
		this.receiverAddr = receiverAddr;
		this.transportMethods = transportMethods;
		this.phoneNo = phoneNo;
		this.cellphoneNo = cellphoneNo;
		this.ordersCreatedTime = ordersCreatedTime;
		this.ordersPayedTime = ordersPayedTime;
		this.productTitle = productTitle;
		this.productSort = productSort;
		this.quantity = quantity;
		this.logisticsNo = logisticsNo;
		this.logisticsCompany = logisticsCompany;
		this.storeId = storeId;
		this.storeName = storeName;
		this.closedReason = closedReason;
	}
	
	public String getTaobaoSalesId() {
		return taobaoSalesId;
	}
	public void setTaobaoSalesId(String taobaoSalesId) {
		this.taobaoSalesId = taobaoSalesId;
	}
	public String getBuyersUsername() {
		return buyersUsername;
	}
	public void setBuyersUsername(String buyersUsername) {
		this.buyersUsername = buyersUsername;
	}
	public String getBuyersAlipayAccount() {
		return buyersAlipayAccount;
	}
	public void setBuyersAlipayAccount(String buyersAlipayAccount) {
		this.buyersAlipayAccount = buyersAlipayAccount;
	}
	public float getPayables() {
		return payables;
	}
	public void setPayables(float payables) {
		this.payables = payables;
	}
	public float getActualPayment() {
		return actualPayment;
	}
	public void setActualPayment(float actualPayment) {
		this.actualPayment = actualPayment;
	}
	public float getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getBuyersMessage() {
		return buyersMessage;
	}
	public void setBuyersMessage(String buyersMessage) {
		this.buyersMessage = buyersMessage;
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
	public String getTransportMethods() {
		return transportMethods;
	}
	public void setTransportMethods(String transportMethods) {
		this.transportMethods = transportMethods;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getCellphoneNo() {
		return cellphoneNo;
	}
	public void setCellphoneNo(String cellphoneNo) {
		this.cellphoneNo = cellphoneNo;
	}
	public Date getOrdersCreatedTime() {
		return ordersCreatedTime;
	}
	public void setOrdersCreatedTime(Date ordersCreatedTime) {
		this.ordersCreatedTime = ordersCreatedTime;
	}
	public Date getOrdersPayedTime() {
		return ordersPayedTime;
	}
	public void setOrdersPayedTime(Date ordersPayedTime) {
		this.ordersPayedTime = ordersPayedTime;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}
	public String getProductSort() {
		return productSort;
	}
	public void setProductSort(String productSort) {
		this.productSort = productSort;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getLogisticsNo() {
		return logisticsNo;
	}
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
	public String getLogisticsCompany() {
		return logisticsCompany;
	}
	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getClosedReason() {
		return closedReason;
	}
	public void setClosedReason(String closedReason) {
		this.closedReason = closedReason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(actualPayment);
		result = prime * result + ((buyersAlipayAccount == null) ? 0 : buyersAlipayAccount.hashCode());
		result = prime * result + ((buyersMessage == null) ? 0 : buyersMessage.hashCode());
		result = prime * result + ((buyersUsername == null) ? 0 : buyersUsername.hashCode());
		result = prime * result + ((cellphoneNo == null) ? 0 : cellphoneNo.hashCode());
		result = prime * result + ((closedReason == null) ? 0 : closedReason.hashCode());
		result = prime * result + ((consigneeName == null) ? 0 : consigneeName.hashCode());
		result = prime * result + ((logisticsCompany == null) ? 0 : logisticsCompany.hashCode());
		result = prime * result + ((logisticsNo == null) ? 0 : logisticsNo.hashCode());
		result = prime * result + orderStatus;
		result = prime * result + ((ordersCreatedTime == null) ? 0 : ordersCreatedTime.hashCode());
		result = prime * result + ((ordersPayedTime == null) ? 0 : ordersPayedTime.hashCode());
		result = prime * result + Float.floatToIntBits(payables);
		result = prime * result + ((phoneNo == null) ? 0 : phoneNo.hashCode());
		result = prime * result + ((productSort == null) ? 0 : productSort.hashCode());
		result = prime * result + ((productTitle == null) ? 0 : productTitle.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((receiverAddr == null) ? 0 : receiverAddr.hashCode());
		result = prime * result + ((storeId == null) ? 0 : storeId.hashCode());
		result = prime * result + ((storeName == null) ? 0 : storeName.hashCode());
		result = prime * result + ((taobaoSalesId == null) ? 0 : taobaoSalesId.hashCode());
		result = prime * result + Float.floatToIntBits(totalPrice);
		result = prime * result + ((transportMethods == null) ? 0 : transportMethods.hashCode());
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
		TaoBaoSalesPO other = (TaoBaoSalesPO) obj;
		if (Float.floatToIntBits(actualPayment) != Float.floatToIntBits(other.actualPayment))
			return false;
		if (buyersAlipayAccount == null) {
			if (other.buyersAlipayAccount != null)
				return false;
		} else if (!buyersAlipayAccount.equals(other.buyersAlipayAccount))
			return false;
		if (buyersMessage == null) {
			if (other.buyersMessage != null)
				return false;
		} else if (!buyersMessage.equals(other.buyersMessage))
			return false;
		if (buyersUsername == null) {
			if (other.buyersUsername != null)
				return false;
		} else if (!buyersUsername.equals(other.buyersUsername))
			return false;
		if (cellphoneNo == null) {
			if (other.cellphoneNo != null)
				return false;
		} else if (!cellphoneNo.equals(other.cellphoneNo))
			return false;
		if (closedReason == null) {
			if (other.closedReason != null)
				return false;
		} else if (!closedReason.equals(other.closedReason))
			return false;
		if (consigneeName == null) {
			if (other.consigneeName != null)
				return false;
		} else if (!consigneeName.equals(other.consigneeName))
			return false;
		if (logisticsCompany == null) {
			if (other.logisticsCompany != null)
				return false;
		} else if (!logisticsCompany.equals(other.logisticsCompany))
			return false;
		if (logisticsNo == null) {
			if (other.logisticsNo != null)
				return false;
		} else if (!logisticsNo.equals(other.logisticsNo))
			return false;
		if (orderStatus != other.orderStatus)
			return false;
		if (ordersCreatedTime == null) {
			if (other.ordersCreatedTime != null)
				return false;
		} else if (!ordersCreatedTime.equals(other.ordersCreatedTime))
			return false;
		if (ordersPayedTime == null) {
			if (other.ordersPayedTime != null)
				return false;
		} else if (!ordersPayedTime.equals(other.ordersPayedTime))
			return false;
		if (Float.floatToIntBits(payables) != Float.floatToIntBits(other.payables))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (productSort == null) {
			if (other.productSort != null)
				return false;
		} else if (!productSort.equals(other.productSort))
			return false;
		if (productTitle == null) {
			if (other.productTitle != null)
				return false;
		} else if (!productTitle.equals(other.productTitle))
			return false;
		if (quantity != other.quantity)
			return false;
		if (receiverAddr == null) {
			if (other.receiverAddr != null)
				return false;
		} else if (!receiverAddr.equals(other.receiverAddr))
			return false;
		if (storeId == null) {
			if (other.storeId != null)
				return false;
		} else if (!storeId.equals(other.storeId))
			return false;
		if (storeName == null) {
			if (other.storeName != null)
				return false;
		} else if (!storeName.equals(other.storeName))
			return false;
		if (taobaoSalesId == null) {
			if (other.taobaoSalesId != null)
				return false;
		} else if (!taobaoSalesId.equals(other.taobaoSalesId))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		if (transportMethods == null) {
			if (other.transportMethods != null)
				return false;
		} else if (!transportMethods.equals(other.transportMethods))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TaoBaoSales [taobaoSalesId=" + taobaoSalesId + ", buyersUsername=" + buyersUsername
				+ ", buyersAlipayAccount=" + buyersAlipayAccount + ", payables=" + payables + ", actualPayment="
				+ actualPayment + ", totalPrice=" + totalPrice + ", orderStatus=" + orderStatus + ", buyersMessage="
				+ buyersMessage + ", consigneeName=" + consigneeName + ", receiverAddr=" + receiverAddr
				+ ", transportMethods=" + transportMethods + ", phoneNo=" + phoneNo + ", cellphoneNo=" + cellphoneNo
				+ ", ordersCreatedTime=" + ordersCreatedTime + ", ordersPayedTime=" + ordersPayedTime
				+ ", productTitle=" + productTitle + ", productSort=" + productSort + ", quantity=" + quantity
				+ ", logisticsNo=" + logisticsNo + ", logisticsCompany=" + logisticsCompany + ", storeId=" + storeId
				+ ", storeName=" + storeName + ", closedReason=" + closedReason + "]";
	}
	
	
}
