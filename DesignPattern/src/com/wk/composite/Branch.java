package com.wk.composite;

import java.util.ArrayList;
import java.util.List;

public class Branch implements IBranch, ICrop {

	private String name = "";
	private String position = "";
	private int salary = 0;
	List<ICrop> cropList = new ArrayList<ICrop>(); 
	
	public Branch(String name,String position,int salary){
		super();
		this.name = name;
		this.salary = salary;
		this.position = position;
	}
	
	@Override
	public void addSubordinate(ICrop crop) {
		this.cropList.add(crop);
	}

	@Override
	public List<ICrop> getSubordinate() {
		return cropList;

	}

	@Override
	public String getInfo() {
/*		StringBuilder info = null;
		info.append("姓名：").append(name).append("\t薪水：").append(salary).append("\t职位：").append(position);
		String infoStr  = info.toString();
		return infoStr;
		*/
		String info = "";
		info = "姓名:" + this.name;
		info = info + "\t职位:" + this.position;
		info = info + "\t薪水:" + this.salary;
		return info;
		
	}

}