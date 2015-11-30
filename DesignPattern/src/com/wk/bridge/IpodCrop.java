package com.wk.bridge;

public class IpodCrop extends Crop{

	public IpodCrop(Product product) {
		super(product);
	}

	public void makeMoney(){
		super.makeMoney();
		System.out.println("I make plenty of money!");
	}
}
