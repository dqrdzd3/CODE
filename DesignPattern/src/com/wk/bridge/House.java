package com.wk.bridge;

public class House extends Product{

	@Override
	public void beSaled() {
		System.out.println("house is saled");
	}

	@Override
	public void beProducted() {
		System.out.println("house is producted");
	}

}
