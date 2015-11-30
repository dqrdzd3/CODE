package com.wk.bridge;

public class HouseCrop extends Crop{

	public HouseCrop(Product product) {
		super(product);
	}

	public void makeMoney(){
		super.makeMoney();
		System.out.println("I make huge money!");
	}
}
