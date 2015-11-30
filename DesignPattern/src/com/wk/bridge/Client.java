package com.wk.bridge;

public class Client {
	public static void main(String[] args){		
		House house = new House();
		System.out.println("房地产公司这样赚钱：");
		HouseCrop hc = new HouseCrop(house);
		hc.makeMoney();
		System.out.println("$$$$$$$$$$$$$$$$");
		Ipod Ipod = new Ipod();
		System.out.println("苹果公司这样赚钱：");
		IpodCrop ic = new IpodCrop(Ipod);
		ic.makeMoney();
	}
	
}
