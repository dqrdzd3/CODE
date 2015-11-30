package com.wk.bridge;

public abstract class Crop {
	private Product product;
	
	public Crop(Product product){
		this.product = product;
	}
	
	public void makeMoney(){
		product.beProducted();
		product.beSaled();
	}
}
