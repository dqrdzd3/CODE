package com.wg.salescount.weeklysales.vo;

public class WeeklySalesVO {

	private String weeklySalesId;
	private String productName;
	private String specificationsModel;
	private float unitPrice;
	private int quantity;
	private float totalPrice;
	private int week;
	private int year;

	public WeeklySalesVO() {
		super();
	}

	public WeeklySalesVO(String weeklySalesId, String productName, String specificationsModel, float unitPrice,
			int quantity, float totalPrice, int week, int year) {
		// TODO Auto-generated constructor stub
		super();
		this.weeklySalesId = weeklySalesId;
		this.productName = productName;
		this.specificationsModel = specificationsModel;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.week = week;
		this.year = year;
	}

	public String getWeeklySalesId() {
		return weeklySalesId;
	}

	public void setWeeklySalesId(String weeklySalesId) {
		this.weeklySalesId = weeklySalesId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSpecificationsModel() {
		return specificationsModel;
	}

	public void setSpecificationsModel(String specificationsModel) {
		this.specificationsModel = specificationsModel;
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

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "WeeklySales [weeklySalesId=" + weeklySalesId + ", productName=" + productName + ", specificationsModel="
				+ specificationsModel + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", totalPrice="
				+ totalPrice + ", week=" + week + ", year=" + year + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((specificationsModel == null) ? 0 : specificationsModel.hashCode());
		result = prime * result + Float.floatToIntBits(totalPrice);
		result = prime * result + Float.floatToIntBits(unitPrice);
		result = prime * result + week;
		result = prime * result + ((weeklySalesId == null) ? 0 : weeklySalesId.hashCode());
		result = prime * result + year;
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
		WeeklySalesVO other = (WeeklySalesVO) obj;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity != other.quantity)
			return false;
		if (specificationsModel == null) {
			if (other.specificationsModel != null)
				return false;
		} else if (!specificationsModel.equals(other.specificationsModel))
			return false;
		if (Float.floatToIntBits(totalPrice) != Float.floatToIntBits(other.totalPrice))
			return false;
		if (Float.floatToIntBits(unitPrice) != Float.floatToIntBits(other.unitPrice))
			return false;
		if (week != other.week)
			return false;
		if (weeklySalesId == null) {
			if (other.weeklySalesId != null)
				return false;
		} else if (!weeklySalesId.equals(other.weeklySalesId))
			return false;
		if (year != other.year)
			return false;
		return true;
	}

}
